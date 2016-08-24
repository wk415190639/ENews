package eNews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import common.GetTypeId;

import eNews.adapter.ActionBarAdapter;
import eNews.adapter.NewsAdapter;
import eNews.adapter.NewsListViewAnimationAdapter;
import eNews.adapter.TopViewPageAdapter;
import eNews.app.R;
import eNews.http.GetNewsContent;
import eNews.url.Url;

public class MainActivity extends Activity {

	public NewsAdapter newsAdapter;
	public TopViewPageAdapter topViewPageAdapter;
	private ListView newsListView;
	public ViewPager topViewPager;
	private GridView actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		newsListView = (ListView) findViewById(R.id.newsListView);
		topViewPager = (ViewPager) findViewById(R.id.topViewPager);
		actionBar = (GridView) findViewById(R.id.actionBar);

		ActionBarAdapter actionbar = new ActionBarAdapter(
				getApplicationContext());
		actionBar.setAdapter(actionbar);
		actionBar.setNumColumns(actionbar.getCount());
		LayoutParams params = actionBar.getLayoutParams();
		params.width = 50 * actionbar.getCount();
		actionBar.setLayoutParams(params);
		actionBar.setOnItemClickListener(new ActionBarItemOnListener());

		topViewPageAdapter = new TopViewPageAdapter(getApplicationContext());

		topViewPager.setAdapter(topViewPageAdapter);
		topViewPager.setPageTransformer(true, new CubeOutTransformer());

		newsAdapter = new NewsAdapter(getApplicationContext(), this);
		NewsListViewAnimationAdapter LvAnimationAdapter = new NewsListViewAnimationAdapter(
				newsAdapter);
		LvAnimationAdapter.setAbsListView(newsListView);
		newsListView.setAdapter(LvAnimationAdapter);

		GetNewsContent.getNewsContent("nc/article/headline/", Url.TopId, "0",
				this);

	}

	public void updateAdapter(finishGetJsonObject update) {
		update.UpdateAdapter();
	}

	public interface finishGetJsonObject {
		public void UpdateAdapter();
	}

	class ActionBarItemOnListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View tv, int position,
				long id) {

			String text = ((TextView) tv).getText().toString();

			System.out.println(GetTypeId.getTypeId(text));

			String typeId = GetTypeId.getTypeId(text);

			newsListView.setSelection(0);
			topViewPager.setCurrentItem(0);
			GetNewsContent.getNewsContent("nc/article/headline/", typeId, "0",
					MainActivity.this);

		}

	}
}
