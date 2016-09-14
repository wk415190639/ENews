package eNews.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import eNews.adapter.CollectAdapter;
import eNews.app.R;
import eNews.bean.CollectModel;
import eNews.common.DataBaseHelper;
import eNews.dao.CollectManage;
import eNews.thirdParty.TencentThirdParty;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 我的收藏Activity
 * 
 */
public class CollectActivity extends Activity implements
		OnCheckedChangeListener, OnLongClickListener {

	private RadioGroup radioGroup;
	private ImageButton backBtn;
	private ListView collectLv;
	private CollectAdapter collectAdapter;
	private String openId;
	private RadioButton radioAll;
	private RadioButton radioText;
	private RadioButton radioPic;
	private RadioButton radioVideo;
	CollectManage manage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.collect);
		manage = CollectManage.getInstance(this);
		getActionBar().hide();

		collectAdapter = new CollectAdapter(this);

		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(this);

		radioAll = (RadioButton) findViewById(R.id.radioAll);
		radioAll.setOnLongClickListener(this);

		radioText = (RadioButton) findViewById(R.id.radioText);
		radioText.setOnLongClickListener(this);

		radioPic = (RadioButton) findViewById(R.id.radioPicture);
		radioPic.setOnLongClickListener(this);

		radioVideo = (RadioButton) findViewById(R.id.radioVideo);
		radioVideo.setOnLongClickListener(this);

		backBtn = (ImageButton) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
			}
		});

		setOpenId(TencentThirdParty.getInstance(getApplicationContext())
				.getOpenId());
		collectLv = (ListView) findViewById(R.id.collectLv);
		collectLv.setAdapter(collectAdapter);
		updateAdapter(CollectManage.getInstance(this).query(null, getOpenId()));

	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub

		switch (checkedId) {

		case R.id.radioAll:
			System.out.println("---------------radioAll");

			updateAdapter(CollectManage.getInstance(this).query(null,
					getOpenId()));

			break;

		case R.id.radioText:
			System.out.println("-----------------radioText");
			updateAdapter(CollectManage.getInstance(this).query(
					DataBaseHelper.TEXT, getOpenId()));

			break;

		case R.id.radioPicture:
			System.out.println("----------------radioPicture");
			updateAdapter(CollectManage.getInstance(this).query(
					DataBaseHelper.PICTURE, getOpenId()));

			break;

		case R.id.radioVideo:
			System.out.println("---------------radioVideo");
			updateAdapter(CollectManage.getInstance(this).query(
					DataBaseHelper.VIDEO, getOpenId()));

			break;

		}

	}

	public void updateAdapter(List<CollectModel> lists) {

		collectAdapter.clear();
		collectAdapter.appendList(lists);

	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.radioAll:
			deleteCollectSet(null, "");
			break;

		case R.id.radioText:
			deleteCollectSet(DataBaseHelper.TEXT, "文本");
			break;

		case R.id.radioPicture:
			deleteCollectSet(DataBaseHelper.PICTURE, "图片");
			break;

		case R.id.radioVideo:
			deleteCollectSet(DataBaseHelper.VIDEO, "视频");
			break;

		}

		return true;
	}

	private void deleteCollectSet(String type, String question) {

		new AlertDialog.Builder(this).setTitle("是否要删除所有" + question + "收藏???")
				.setPositiveButton("删除", new NegativeButtonListener(type))
				.setNegativeButton("再等等", new PositiveButtonListener())
				.setIcon(android.R.drawable.ic_dialog_info).show();
	}

	// 取消
	public class PositiveButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

		}
	}

	// 确定
	public class NegativeButtonListener implements
			DialogInterface.OnClickListener {

		String type;

		public NegativeButtonListener(String type) {
			// TODO Auto-generated constructor stub
			this.type = type;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

			if (type != null && type.equals(DataBaseHelper.PICTURE))
				manage.deleteCollectSet(DataBaseHelper.MEITU);
			manage.deleteCollectSet(type);
			updateList(radioGroup.getCheckedRadioButtonId());

		}

	}

	private void updateList(int id) {
		switch (id) {

		case R.id.radioAll:
			radioAll.setChecked(false);
			radioAll.setChecked(true);

			break;

		case R.id.radioText:
			radioText.setChecked(false);
			radioText.setChecked(true);

			break;

		case R.id.radioPicture:
			radioPic.setChecked(false);
			radioPic.setChecked(true);

			break;

		case R.id.radioVideo:
			radioVideo.setChecked(false);
			radioVideo.setChecked(true);

			break;

		}
	}

}
