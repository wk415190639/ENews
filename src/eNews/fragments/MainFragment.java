package eNews.fragments;

import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;

import eNews.activity.ChannelActivity;
import eNews.adapter.NewsActionBarAdapter;
import eNews.adapter.NewsAdapter;
import eNews.adapter.TopViewPageAdapter;
import eNews.adapter.ViewAnimationAdapter;
import eNews.app.R;
import eNews.bean.NewsModel;
import eNews.common.GetTypeId;
import eNews.customview.ActionBarView;
import eNews.dao.ChannelManage;
import eNews.httpContent.GetNewsContent;
import eNews.url.Url;

public class MainFragment extends Fragment {

	private View view;
	public NewsAdapter newsAdapter;
	public NewsActionBarAdapter actionbarAdapter;
	public TopViewPageAdapter topViewPageAdapter;
	public ListView newsListView;
	public ViewPager topViewPager;
	public ActionBarView actionBar;
	private TextView channelManageBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.activity_main, null);
		newsListView = (ListView) view.findViewById(R.id.newsListView);
		topViewPager = (ViewPager) view.findViewById(R.id.topViewPager);
		actionBar = (ActionBarView) view.findViewById(R.id.actionBar);
		channelManageBtn = (TextView) view.findViewById(R.id.channelManageBtn);
		actionbarAdapter = new NewsActionBarAdapter(getActivity(),
				ChannelManage.getInstance(getActivity())
						.getDefaultUserChannelsList(), actionBar);
		actionBar.setAdapter(actionbarAdapter);
		actionBar.setOnItemClickListener(new ActionBarItemOnListener());

		actionBar.setOnItemClickListener(new ActionBarItemOnListener());

		topViewPageAdapter = new TopViewPageAdapter(getActivity()
				.getApplicationContext());

		topViewPager.setAdapter(topViewPageAdapter);
		topViewPager.setPageTransformer(true, new CubeOutTransformer());

		newsAdapter = new NewsAdapter(getActivity());
		ViewAnimationAdapter LvAnimationAdapter = new ViewAnimationAdapter(
				newsAdapter);
		LvAnimationAdapter.setAbsListView(newsListView);
		newsListView.setAdapter(LvAnimationAdapter);

		GetNewsContent.getNewsContent("nc/article/headline/", Url.TopId, "0",
				this);

		channelManageBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getActivity(), ChannelActivity.class);
				startActivityForResult(intent, 1000);
			}
		});

		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		actionbarAdapter.updateList(ChannelManage.getInstance(getActivity())
				.getDefaultUserChannelsList());
		actionBar.setSelection(0);
			
		System.out.println("onActivityResult");

	}

	public void updateAdapter(List<NewsModel> lists) {

		if (newsAdapter.getCount() > 0) {
			newsAdapter.clear();
		}
		if (topViewPageAdapter.getCount() > 0) {
			topViewPageAdapter.clear();
			topViewPager.removeAllViews();

		}

		newsAdapter.appendList(lists);
		topViewPageAdapter.appendList(lists);

		System.out.println("--------appendList");
	}

	class ActionBarItemOnListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View tv, int position,
				long id) {

			String text = ((TextView) tv.findViewById(R.id.gridview_bar_item_Tv)).getText().toString();

			// System.out.println(GetTypeId.getTypeId(text));

			String typeId = GetTypeId.getTypeId(text);

			newsListView.setSelection(0);
			topViewPager.setCurrentItem(0);
			GetNewsContent.getNewsContent("nc/article/headline/", typeId, "0",
					MainFragment.this);

			actionbarAdapter.setSelectedIndex(position);
			actionbarAdapter.notifyDataSetChanged();

		}

	}

}
