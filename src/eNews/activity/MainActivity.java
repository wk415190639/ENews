package eNews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;

import eNews.adapter.ActionBarAdapter;
import eNews.adapter.NewsAdapter;
import eNews.adapter.ViewAnimationAdapter;
import eNews.adapter.TopViewPageAdapter;
import eNews.app.R;
import eNews.common.GetTypeId;
import eNews.customview.ActionBarView;
import eNews.dao.ChannelManage;
import eNews.http.GetNewsContent;
import eNews.url.Url;

public class MainActivity extends Activity {

	public NewsAdapter newsAdapter;
	ActionBarAdapter actionbarAdapter;
	public TopViewPageAdapter topViewPageAdapter;
	public ListView newsListView;
	public ViewPager topViewPager;
	 private ActionBarView actionBar;
	private TextView channelManageBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		newsListView = (ListView) findViewById(R.id.newsListView);
		topViewPager = (ViewPager) findViewById(R.id.topViewPager);
		actionBar = (ActionBarView) findViewById(R.id.actionBar);
		channelManageBtn = (TextView) findViewById(R.id.channelManageBtn);

		actionbarAdapter = new ActionBarAdapter(getApplicationContext(),
				ChannelManage.getInstance(MainActivity.this)
						.getDefaultUserChannelsList(),actionBar);
		actionBar.setAdapter(actionbarAdapter);
		actionBar.setOnItemClickListener(new ActionBarItemOnListener());
		
	
		
		actionBar.setOnItemClickListener(new ActionBarItemOnListener());

		
		
		topViewPageAdapter = new TopViewPageAdapter(getApplicationContext());

		topViewPager.setAdapter(topViewPageAdapter);
		topViewPager.setPageTransformer(true, new CubeOutTransformer());

		
		
		newsAdapter = new NewsAdapter(getApplicationContext(), this);
		ViewAnimationAdapter LvAnimationAdapter = new ViewAnimationAdapter(
				newsAdapter);
		LvAnimationAdapter.setAbsListView(newsListView);
		newsListView.setAdapter(LvAnimationAdapter);

		
		GetNewsContent.getNewsContent("nc/article/headline/", Url.TopId, "0",
			
				this);

		
		
		channelManageBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						ChannelActivity.class);
				startActivityForResult(intent, 1000);
			}
		});

	}

	public void updateAdapter(finishGetJsonObject update) {
		update.UpdateAdapter();
	}

	public interface finishGetJsonObject {
		public void UpdateAdapter();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		actionbarAdapter.updateList(ChannelManage
				.getInstance(MainActivity.this).getDefaultUserChannelsList());
		actionBar.setSelection(0);
		System.out.println("onActivityResult");

	}

	class ActionBarItemOnListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View tv, int position,
				long id) {

			String text = ((TextView) tv).getText().toString();

			// System.out.println(GetTypeId.getTypeId(text));

			String typeId = GetTypeId.getTypeId(text);

			newsListView.setSelection(0);
			topViewPager.setCurrentItem(0);
			GetNewsContent.getNewsContent("nc/article/headline/", typeId, "0",
					MainActivity.this);

			actionbarAdapter.setSelectedIndex(position);
			actionbarAdapter.notifyDataSetChanged();

		}

	}
}
