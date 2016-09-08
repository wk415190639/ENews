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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import eNews.activity.MainWindows;
import eNews.adapter.PictureNewsActionBarAdapter;
import eNews.adapter.PictureNewsAdapter;
import eNews.app.R;
import eNews.bean.PictureModel;
import eNews.customview.ActionBarView;
import eNews.httpContent.GetPictureNewsContent;
import eNews.url.Url;

public class PictureFragment extends Fragment {

	private View view;
	private ImageButton backBtn;
	private ActionBarView actionbar;
	private ListView pictureListView;
	private PictureNewsActionBarAdapter pictureNewsActionBarAdapter;
	private PictureNewsAdapter pictureNewsAdapter;
	private String finalLink;

	private String selectTag;
	private int pageCount = 1;
	private boolean isLoadContent = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.picture_news, null);
		selectTag = "精选";
		init();

		pictureListView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

				if (pictureListView.getLastVisiblePosition() == (pictureListView
						.getCount() - 1)) {

					System.out.println("到底了");
					if (!isLoadContent) {
						isLoadContent = true;
						String url = "";
						if (selectTag.equals("美图")) {
							url = Url.TuPianMeitu
									+ String.valueOf(pageCount++ * 20);
							GetPictureNewsContent.getMeiTuContent(url,
									PictureFragment.this);
						} else {
							url = Url.TuJi + finalLink + ".json";

							GetPictureNewsContent.getNewsContent(url,
									PictureFragment.this);
						}

					}
					showToast("正在加载中...........");
				}

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});

		return view;
	}

	private void init() {

		getActivity().getActionBar().hide();
		actionbar = (ActionBarView) view
				.findViewById(R.id.pictureNewsactionBar);

		backBtn = (ImageButton) view.findViewById(R.id.backBtn);
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
		actionbar.setNumColumns(3);
		actionbar.setOnItemClickListener(new PictureItemClick());

		pictureListView.setAdapter(pictureNewsAdapter);

		selectTag = "精选";

		pageCount = 1;
		String url = Url.TuJiInit;
		GetPictureNewsContent.getNewsContent(url, this);

	}

	class PictureItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View tv, int position,
				long id) {
			// TODO Auto-generated method stub

			pictureNewsActionBarAdapter.setSelectedIndex(position);

			pictureNewsAdapter.clear();
			String text = ((TextView) tv
					.findViewById(R.id.gridview_bar_item_Tv)).getText()
					.toString();

			pageCount = 1;
			selectTag = text;
			String url = "";
			if (selectTag.equals("美图")) {
				url = Url.TuPianMeitu + "0";
				GetPictureNewsContent
						.getMeiTuContent(url, PictureFragment.this);
			}

			else if (selectTag.equals("体坛")) {
				url = Url.TuPianTiTanInit;
				GetPictureNewsContent.getNewsContent(url, PictureFragment.this);
			} else if (selectTag.equals("精选")) {
				url = Url.TuJiInit;
				GetPictureNewsContent.getNewsContent(url, PictureFragment.this);
			}

		}
	}

	public void updateAdapter(List<PictureModel> lists) {

		pictureNewsAdapter.appendList(lists, selectTag);

		if (!selectTag.equals("美图")) {
			finalLink = lists.get(lists.size() - 1).getSetId();
			System.out.println("finalLink->" + finalLink);
		}

		isLoadContent = false;
	}

	public void showToast(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}
}
