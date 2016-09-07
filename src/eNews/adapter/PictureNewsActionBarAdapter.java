package eNews.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import eNews.app.R;

public class PictureNewsActionBarAdapter extends BaseAdapter {

	private Context context;
	private int selectedIndex = 0;

	private String[] actionbarList;

	public PictureNewsActionBarAdapter(Context context) {
		actionbarList = new String[] { "精选", "体坛", "美图" };
		this.context = context;

	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return actionbarList.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View bar, ViewGroup parent) {

		if (bar == null)
			bar = LayoutInflater.from(context).inflate(
					R.layout.gridview_bar_item, null);

		TextView tv = (TextView) bar.findViewById(R.id.gridview_bar_item_Tv);
		tv.setText(actionbarList[position]);
		if (selectedIndex == position) {
			tv.setBackgroundResource(R.drawable.actionbar_text_style);
			tv.setTextColor(Color.BLUE);
		} else {
			tv.setBackgroundResource(R.drawable.gridview_bar_item);
			tv.setTextColor(Color.BLACK);
		}

		return bar;
	}

}
