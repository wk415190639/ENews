package eNews.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import eNews.adapter.PictureDetailViewPageAdapter;
import eNews.app.R;
import eNews.bean.PictureDetailModel;
import eNews.bean.PictureModel;
import eNews.customview.MorePopupWindow;
import eNews.thirdParty.AppConstant;
import eNews.thirdParty.TencentThirdParty;

public class PictureDetailActivity extends Activity implements OnClickListener {

	private ViewPager viewPager;
	private PictureDetailViewPageAdapter detailViewPageAdapter;
	private PictureModel model;
	private ImageButton backBtn;
	private MorePopupWindow morePopupWindow;
	private ImageButton actionbar_more;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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

		if (model.getPics().size() > 0)
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

		case R.id.shareLy:
			System.out.println("点击了shareLy");
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

		case R.id.collectLy:
			System.out.println("点击了collectLy");
			if (morePopupWindow.isShowing()) {
				morePopupWindow.dismiss();
			}
			break;
		case R.id.actionbar_more:
			System.out.println("点击了actionbar_more");
			if (!morePopupWindow.isShowing()) {
				morePopupWindow.showAsDropDown(actionbar_more);
				System.out.println("popupwindow");
			}
			break;
		}
	}
}
