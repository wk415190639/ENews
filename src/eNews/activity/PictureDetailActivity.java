package eNews.activity;

import java.util.ArrayList;

import eNews.adapter.PictureDetailViewPageAdapter;
import eNews.app.R;
import eNews.bean.PictureDetailModel;
import eNews.bean.PictureModel;
import eNews.httpContent.GetPictureNewsDetailContent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class PictureDetailActivity extends Activity {

	private ViewPager viewPager;
	private PictureDetailViewPageAdapter detailViewPageAdapter;
	private PictureModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_detail);
		Intent intent = getIntent();

		model = (PictureModel) intent.getSerializableExtra("picModel");

		init();

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
}
