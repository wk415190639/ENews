package eNews.fragments;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import eNews.activity.MainWindows;
import eNews.adapter.VideoNewsActionBarAdapter;
import eNews.adapter.VideoNewsAdapter;
import eNews.app.R;
import eNews.bean.VideoModel;
import eNews.common.GetTypeId;
import eNews.customview.ActionBarView;
import eNews.getContent.GetVideoNewsContent;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 视频新闻碎片
 */
public class VideoFragment extends Fragment {

	private View view;
	private ActionBarView actionBarView;
	public VideoNewsAdapter newsAdapter;
	private ListView videoList;
	private String selectTag;
	private int pageCount = 0;
	private boolean isLoadContent = false;

	private ImageButton backBtn;

	private VideoNewsActionBarAdapter videoNewsAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getActionBar().hide();
		view = inflater.inflate(R.layout.video_news, null);
		selectTag = "热点";
		backBtn = (ImageButton) view.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((MainWindows) getActivity()).showMainFragment();

			}
		});

		videoList = (ListView) view.findViewById(R.id.videoList);
		actionBarView = (ActionBarView) view
				.findViewById(R.id.videoNewsactionBar);
		videoNewsAdapter = new VideoNewsActionBarAdapter(getActivity(),
				actionBarView);
		actionBarView.setAdapter(videoNewsAdapter);
		actionBarView.setNumColumns(4);
		actionBarView.setOnItemClickListener(new VideoItemClick());

		newsAdapter = new VideoNewsAdapter(getActivity());
		videoList.setAdapter(newsAdapter);
		GetVideoNewsContent.getNewsContent(
				GetTypeId.getTypeId(selectTag + "视频"),
				String.valueOf(pageCount), VideoFragment.this);

		videoList.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

				if (videoList.getLastVisiblePosition() == (videoList.getCount() - 1)) {

					System.out.println("到底了");
					if (!isLoadContent) {
						isLoadContent = true;
						String typeId = GetTypeId.getTypeId(selectTag + "视频");
						GetVideoNewsContent.getNewsContent(typeId,
								String.valueOf(pageCount++ * 20),
								VideoFragment.this);
					}

				}
				showToast("正在加载中...........");
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});

		return view;
	}

	class VideoItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View tv, int position,
				long id) {
			// TODO Auto-generated method stub

			videoNewsAdapter.setSelectedIndex(position);

			String text = ((TextView) tv
					.findViewById(R.id.gridview_bar_item_Tv)).getText()
					.toString();
			selectTag = text;
			pageCount = 0;
			newsAdapter.clear();
			String typeId = GetTypeId.getTypeId(selectTag + "视频");
			System.out.println(typeId);
			GetVideoNewsContent.getNewsContent(typeId,
					String.valueOf(pageCount), VideoFragment.this);
		}
	}

	public void updateAdapter(List<VideoModel> lists) {

		newsAdapter.appendList(lists);
		isLoadContent = false;
	}

	public void showToast(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}
}
