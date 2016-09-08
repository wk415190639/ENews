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

public class PictureDetailActivity extends Activity {

	private ViewPager viewPager;
	private PictureDetailViewPageAdapter detailViewPageAdapter;
	private PictureModel model;
	private ImageButton backBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_detail);
		Intent intent = getIntent();

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
}
