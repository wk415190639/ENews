package eNews.fragments;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import eNews.adapter.PictureNewsActionBarAdapter;
import eNews.adapter.PictureNewsAdapter;
import eNews.app.R;
import eNews.bean.PictureModel;
import eNews.common.GetTypeId;
import eNews.customview.ActionBarView;
import eNews.httpContent.GetPictureNewsContent;

public class PictureFragment extends Fragment {

	private View view;
	private ActionBarView actionbar;
	private ListView pictureListView;
	private PictureNewsActionBarAdapter pictureNewsActionBarAdapter;
	private PictureNewsAdapter pictureNewsAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.picture_news, null);

		init();

		return view;
	}

	private void init() {
		actionbar = (ActionBarView) view
				.findViewById(R.id.pictureNewsactionBar);

		pictureListView = (ListView) view.findViewById(R.id.pictureList);
		pictureNewsActionBarAdapter = new PictureNewsActionBarAdapter(
				getActivity());
		pictureNewsAdapter = new PictureNewsAdapter(getActivity());
		actionbar.setAdapter(pictureNewsActionBarAdapter);
		actionbar.setNumColumns(4);
		actionbar.setOnItemClickListener(new PictureItemClick());

		pictureListView.setAdapter(pictureNewsAdapter);

		GetPictureNewsContent.getNewsContent(GetTypeId.getTypeId("新浪精选"),
				PictureFragment.this);
	}

	class PictureItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View tv, int position,
				long id) {
			// TODO Auto-generated method stub

			pictureNewsActionBarAdapter.setSelectedIndex(position);

			String text = ((TextView) tv).getText().toString();

			String url = GetTypeId.getTypeId("新浪" + text);
			System.out.println(url);
			GetPictureNewsContent.getNewsContent(url, PictureFragment.this);
		}
	}

	public void updateAdapter(List<PictureModel> lists) {

		if (pictureNewsAdapter.getCount() > 0) {
			pictureNewsAdapter.clear();
		}
		pictureListView.setSelection(0);
		pictureNewsAdapter.appendList(lists);
	}
}
