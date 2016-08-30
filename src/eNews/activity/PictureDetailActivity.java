package eNews.activity;

import java.util.ArrayList;

import eNews.adapter.PictureDetailViewPageAdapter;
import eNews.app.R;
import eNews.bean.PictureDetailModel;
import eNews.httpContent.GetPictureNewsDetailContent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class PictureDetailActivity extends Activity {

	private ViewPager viewPager;
	private PictureDetailViewPageAdapter detailViewPageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_detail);
		Intent intent = getIntent();
		String kpic = intent.getStringExtra("kpic");
		
		
		init();
		
		GetPictureNewsDetailContent.getNewsContent(kpic, this);
	}

	private void init() {

		viewPager = (ViewPager) findViewById(R.id.pictureViewPager);
		detailViewPageAdapter = new PictureDetailViewPageAdapter(this);
		viewPager.setAdapter(detailViewPageAdapter);

	}

	public void updateAdapter(ArrayList<PictureDetailModel> lists) {
		detailViewPageAdapter.appendList(lists);

	}
}
