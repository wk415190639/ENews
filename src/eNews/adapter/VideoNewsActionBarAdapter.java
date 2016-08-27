package eNews.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import eNews.app.R;

public class VideoNewsActionBarAdapter extends BaseAdapter {

	private Context context;
	private int selectedIndex = -1;

	private String[] actionbarList;

	public VideoNewsActionBarAdapter(Context context) {
		actionbarList = new String[] { "热点", "娱乐", "搞笑", "精品" };
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
		// TODO Auto-generated method stub
		TextView tv = new TextView(context);
		tv.setText(actionbarList[position]);
		tv.setGravity(Gravity.CENTER);

		if (selectedIndex == position)
			tv.setBackgroundResource(R.drawable.actionbar_text_style);

		return tv;
	}

}
