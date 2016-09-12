package eNews.adapter;

import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import eNews.app.R;
import eNews.customview.ActionBarView;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 图片新闻标签适配器
 */
public class PictureNewsActionBarAdapter extends BaseAdapter {

	private Context context;
	private int selectedIndex = 0;
	private ActionBarView actionBarView;
	private String[] actionbarList;

	public PictureNewsActionBarAdapter(Context context,
			ActionBarView actionBarView) {
		actionbarList = new String[] { "精选", "体坛", "美图" };
		this.context = context;
		this.actionBarView = actionBarView;

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
