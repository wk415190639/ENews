package eNews.fragments;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import eNews.activity.MainWindows;
import eNews.adapter.PictureNewsActionBarAdapter;
import eNews.adapter.PictureNewsAdapter;
import eNews.app.R;
import eNews.bean.PictureModel;
import eNews.common.GetTypeId;
import eNews.customview.ActionBarView;
import eNews.httpContent.GetPictureNewsContent;

public class PictureFragment extends Fragment {

	private View view;
	private Button backBtn;
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

		getActivity().getActionBar().hide();
		actionbar = (ActionBarView) view
				.findViewById(R.id.pictureNewsactionBar);

		backBtn = (Button) view.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((MainWindows) getActivity()).showMainFragment();

			}
		});

		pictureListView = (ListView) view.findViewById(R.id.pictureList);
		pictureNewsActionBarAdapter = new PictureNewsActionBarAdapter(
				getActivity());
		pictureNewsAdapter = new PictureNewsAdapter(getActivity());
		actionbar.setAdapter(pictureNewsActionBarAdapter);
		actionbar.setNumColumns(4);
		actionbar.setOnItemClickListener(new PictureItemClick());

		pictureListView.setAdapter(pictureNewsAdapter);

		String url = GetTypeId.getTypeId("新浪精选");
		System.out.println(url);
		GetPictureNewsContent.getNewsContent(url, this);

	}

	class PictureItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View tv, int position,
				long id) {
			// TODO Auto-generated method stub

			pictureNewsActionBarAdapter.setSelectedIndex(position);

			String text = ((TextView) tv
					.findViewById(R.id.gridview_bar_item_Tv)).getText()
					.toString();

			String url = GetTypeId.getTypeId("新浪" + text);
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
