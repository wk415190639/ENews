package eNews.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;
import eNews.adapter.PictureDetailViewPageAdapter;
import eNews.app.R;
import eNews.bean.CollectModel;
import eNews.bean.PictureDetailModel;
import eNews.bean.PictureModel;
import eNews.common.DataBaseHelper;
import eNews.customview.MorePopupWindow;
import eNews.dao.CollectManage;
import eNews.thirdParty.AppConstant;
import eNews.thirdParty.TencentThirdParty;

public class PictureDetailActivity extends Activity implements OnClickListener,
		CollectNewsInterface {

	private ViewPager viewPager;
	private PictureDetailViewPageAdapter detailViewPageAdapter;
	private PictureModel model;
	private ImageButton backBtn;
	private MorePopupWindow morePopupWindow;
	private ImageButton actionbar_more;
	private String openId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setOpenId(TencentThirdParty.getInstance(getApplicationContext())
				.getOpenId());
		setContentView(R.layout.picture_detail);
		Intent intent = getIntent();
		morePopupWindow = new MorePopupWindow(this);
		actionbar_more = (ImageButton) findViewById(R.id.actionbar_more);
		actionbar_more.setOnClickListener(this);
		getActionBar().hide();
		model = (PictureModel) intent.getSerializableExtra("picModel");

		init();

		backBtn = (ImageButton) findViewById(R.id.backBtn);

		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
			}
		});
		// GetPictureNewsDetailContent.getNewsContent(kpic, this);

		if (model.getPics()!=null&&model.getPics().size() > 0)
			detailViewPageAdapter.appendList(model);
	}

	private void init() {

		viewPager = (ViewPager) findViewById(R.id.pictureViewPager);
		detailViewPageAdapter = new PictureDetailViewPageAdapter(this);
		viewPager.setAdapter(detailViewPageAdapter);

	}

	public void updateAdapter(ArrayList<PictureDetailModel> lists) {
		// detailViewPageAdapter.appendList(lists);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.shareLy: // ����QQ�ռ�
			System.out.println("�����shareLy");
			if (morePopupWindow.isShowing()) {

				if (model != null) {

					ArrayList<String> arrayList = new ArrayList<String>();
					arrayList.add(AppConstant.logoUrl);

					TencentThirdParty.getInstance(this).shareToQzone(this,
							model.getTitle(), model.getDesc(),
							model.getSetUrl(), arrayList);
				}

				morePopupWindow.dismiss();
			}
			break;

		case R.id.shareLyqq: // �����QQ����

			System.out.println("�����shareLyQQ");
			if (morePopupWindow.isShowing()) {

				if (model != null) {

					TencentThirdParty.getInstance(this).shareToQQ(this,
							model.getTitle(), model.getDesc(),
							model.getSetUrl(), AppConstant.logoUrl);
				}

				morePopupWindow.dismiss();
			}

			break;
		case R.id.collectLy: // �ղ�
			System.out.println("�����collectLy");
			collectNews();

			break;
		case R.id.actionbar_more:
			System.out.println("�����actionbar_more");
			if (!morePopupWindow.isShowing()) {
				morePopupWindow.showAsDropDown(actionbar_more);
				System.out.println("popupwindow");
			}
			break;
		}
	}

	private void collectNews() {
		if (morePopupWindow.isShowing()) {
			morePopupWindow.dismiss();

			if (TencentThirdParty.getInstance(getApplicationContext())
					.checkIsLogged()) {

				collect();

			} else {
				new AlertDialog.Builder(this).setTitle("���ȵ�¼")
						.setPositiveButton("��¼", new NegativeButtonListener())
						.setNegativeButton("�ٵȵ�", new PositiveButtonListener())
						.setIcon(android.R.drawable.ic_dialog_info).show();
			}

		}
	}

	// ȡ��
	public class PositiveButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

		}
	}

	// ��¼
	public class NegativeButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

			TencentThirdParty.getInstance(getApplicationContext()).userLogin(
					PictureDetailActivity.this);

		}

	}

	@Override
	public void collectNewsAfterLogin(String openId) {
		// TODO Auto-generated method stub

		setOpenId(openId);

		collect();

	}

	private void collect() {
		CollectManage manage = CollectManage.getInstance(this);
		CollectModel collectModel = new CollectModel();

		collectModel.setUserId(getOpenId());
		collectModel.setDesc(model.getDesc());
		collectModel.setTitle(model.getTitle());
		collectModel.setType(DataBaseHelper.PICTURE);
		collectModel.setImgurl(model.getImgsrc());
		collectModel.setPics(model.getPics());
		manage.insertCollect(collectModel);

		Toast.makeText(getApplicationContext(), "�ղسɹ�!!!", Toast.LENGTH_SHORT)
				.show();

	}
}
