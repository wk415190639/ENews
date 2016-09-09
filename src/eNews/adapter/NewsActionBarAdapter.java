package eNews.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;
import eNews.app.R;
import eNews.bean.ChannelItemModel;
import eNews.customview.ActionBarView;

public class NewsActionBarAdapter extends BaseAdapter {

	private Context context;
	private int selectedIndex = 0;

	private ArrayList<ChannelItemModel> actionbarList;
	private ActionBarView actionBarView;

	public NewsActionBarAdapter(Context context,
			ArrayList<ChannelItemModel> lists, ActionBarView actionBarView) {

		actionbarList = new ArrayList<ChannelItemModel>();
		this.context = context;
		this.actionbarList = lists;
		this.actionBarView = actionBarView;
		updateActionBar();

	}

	private void updateActionBar() {
		actionBarView.setNumColumns(getCount());
		LayoutParams params = actionBarView.getLayoutParams();
		params.width = 100 * getCount();
		actionBarView.setLayoutParams(params);

	}

	public void updateList(ArrayList<ChannelItemModel> lists) {

		actionbarList = lists;
		notifyDataSetChanged();
		updateActionBar();
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return actionbarList.size();
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

		if (bar == null)
			bar = LayoutInflater.from(context).inflate(
					R.layout.gridview_bar_item, null);

		TextView tv = (TextView) bar.findViewById(R.id.gridview_bar_item_Tv);
		tv.setText(actionbarList.get(position).getName());
		tv.setHeight(actionBarView.getMeasuredHeight());

		if (selectedIndex != position) {
			tv.setBackgroundResource(R.drawable.gridview_bar_item);
			// tv.setTextColor(Color.BLACK);
			TextPaint tp = tv.getPaint();
			tp.setFakeBoldText(false);
			tv.setTextSize(15);

		} else {
			tv.setBackgroundResource(R.drawable.actionbar_text_style);
			// tv.setTextColor(Color.WHITE);
			TextPaint tp = tv.getPaint();
			tp.setFakeBoldText(true);
			tv.setTextSize(18);

		}

		return bar;
	}

}
