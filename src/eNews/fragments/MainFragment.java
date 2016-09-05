package eNews.fragments;

import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ScrollView;
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
import eNews.customview.NewsListView;
import eNews.dao.ChannelManage;
import eNews.httpContent.GetNewsContent;
import eNews.url.Url;

public class MainFragment extends Fragment {

	private int TopCount;
	private View view;
	public NewsAdapter newsAdapter;
	public NewsActionBarAdapter actionbarAdapter;
	public TopViewPageAdapter topViewPageAdapter;
	public NewsListView newsListView;
	public ViewPager topViewPager;
	public ActionBarView actionBar;
	private TextView channelManageBtn;
	ScrollView scrollView;
	private String selectTag;
	private int pageCount = 1;
	public boolean isLoadContent = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.activity_main, null);
		newsListView = (NewsListView) view.findViewById(R.id.newsListView);
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
		selectTag = "头条";
		GetNewsContent.getNewsContent("nc/article/headline/", Url.TopId, "0",
				this);

		channelManageBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getActivity(), ChannelActivity.class);
				startActivityForResult(intent, 1000);
			}
		});

		scrollView = (ScrollView) view.findViewById(R.id.scrollView1);

		scrollView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				if (scrollView.getHeight() + scrollView.getScrollY() >= getNewsListViewHeight()) {
					System.out.println(scrollView.getHeight()
							+ scrollView.getScrollY() + "在底部"
							+ getNewsListViewHeight());

					// System.out.println(GetTypeId.getTypeId(text));

					String typeId = GetTypeId.getTypeId(selectTag);

					if (!isLoadContent) {
						isLoadContent = true;
						if (selectTag.equals("头条")) {
							GetNewsContent.getNewsContent(
									"nc/article/headline/", typeId,
									String.valueOf(pageCount++ * 20),
									MainFragment.this);
						} else {
							GetNewsContent.getNewsContent("nc/article/list/",
									typeId, String.valueOf(pageCount++ * 20),
									MainFragment.this);
						}

					}
				}
				return false;
			}
		});
		return view;
	}

	private int getNewsListViewHeight() {
		return scrollView.getChildAt(0).getMeasuredHeight();

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

		if (!isLoadContent) {
			if (topViewPageAdapter.getCount() > 0) {
				topViewPageAdapter.clear();
				topViewPager.removeAllViews();

			}
			topViewPageAdapter.appendList(lists);
		}

		LayoutParams layoutParams = newsListView.getLayoutParams();
		layoutParams.height += 80 * 20;
		newsListView.setLayoutParams(layoutParams);

		newsAdapter.appendList(lists);
		TopCount = topViewPageAdapter.getCount();
		isLoadContent = false;
	}

	class ActionBarItemOnListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View tv, int position,
				long id) {

			TopCount = 0;
			String text = ((TextView) tv
					.findViewById(R.id.gridview_bar_item_Tv)).getText()
					.toString();
			selectTag = text;
			// System.out.println(GetTypeId.getTypeId(text));

			String typeId = GetTypeId.getTypeId(text);

			topViewPager.setCurrentItem(0);
			pageCount = 0;
			newsAdapter.clear();

			LayoutParams layoutParams = newsListView.getLayoutParams();
			layoutParams.height = 0;
			newsListView.setLayoutParams(layoutParams);
			GetNewsContent.getNewsContent("nc/article/list/", typeId,
					String.valueOf(pageCount), MainFragment.this);

			actionbarAdapter.setSelectedIndex(position);
			actionbarAdapter.notifyDataSetChanged();
			scrollView.setScrollY(0);

		}

	}

	class ScrollTopWindow extends Thread {

		private boolean start = true;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();

			try {

				while (start) {

					Thread.sleep(5000);

					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (TopCount > 0)
								topViewPager.setCurrentItem((topViewPager
										.getCurrentItem() + 1) % TopCount);

						}
					});
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void stopScroll() {
			if (start) {
				start = false;
			}
		}
	}

}
