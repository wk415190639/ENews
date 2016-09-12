package eNews.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import eNews.adapter.CollectAdapter;
import eNews.app.R;
import eNews.bean.CollectModel;
import eNews.common.DataBaseHelper;
import eNews.dao.CollectManage;
import eNews.thirdParty.TencentThirdParty;

public class CollectActivity extends Activity implements
		OnCheckedChangeListener {

	private RadioGroup radioGroup;
	private ImageButton backBtn;
	private ListView collectLv;
	private CollectAdapter collectAdapter;
	private String openId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.collect);

		getActionBar().hide();

		collectAdapter = new CollectAdapter(this);

		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(this);
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

}
