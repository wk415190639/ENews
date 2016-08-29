package eNews.fragments;

import java.util.List;

import eNews.adapter.VideoNewsActionBarAdapter;
import eNews.adapter.VideoNewsAdapter;
import eNews.app.R;
import eNews.bean.VideoModel;
import eNews.common.GetTypeId;
import eNews.customview.ActionBarView;
import eNews.httpContent.GetVideoNewsContent;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class VideoFragment extends Fragment {

	private View view;
	private ActionBarView actionBarView;
	public VideoNewsAdapter newsAdapter;
	private ListView videoList;

	private VideoNewsActionBarAdapter videoNewsAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.video_news, null);

		videoList = (ListView) view.findViewById(R.id.videoList);
		actionBarView = (ActionBarView) view
				.findViewById(R.id.videoNewsactionBar);
		videoNewsAdapter = new VideoNewsActionBarAdapter(getActivity());
		actionBarView.setAdapter(videoNewsAdapter);
		actionBarView.setNumColumns(4);
		actionBarView.setOnItemClickListener(new VideoItemClick());

		newsAdapter = new VideoNewsAdapter(getActivity());
		videoList.setAdapter(newsAdapter);
		GetVideoNewsContent.getNewsContent(GetTypeId.getTypeId("热点视频"), "0", VideoFragment.this);

		return view;
	}

	class VideoItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View tv, int position,
				long id) {
			// TODO Auto-generated method stub

			videoNewsAdapter.setSelectedIndex(position);

			String text = ((TextView) tv).getText().toString();

			String typeId = GetTypeId.getTypeId(text + "视频");
			System.out.println(typeId);
			GetVideoNewsContent.getNewsContent(typeId, "0", VideoFragment.this);
		}
	}

	public void updateAdapter(List<VideoModel> lists) {

		if (newsAdapter.getCount() > 0) {
			newsAdapter.clear();
		}
		newsAdapter.appendList(lists);
	}

}
