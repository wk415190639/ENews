package eNews.adapter;

import eNews.app.R;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ActionBarAdapter extends BaseAdapter {

	private Context context;
	private int selectedIndex=-1;
	
	

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public ActionBarAdapter(Context context) {

		this.context = context;

	}

	String actionbarList[] = { "头条", "足球", "体育", "财经", "科技", "电影", "NBA", "数码",
			"移动", "NBA", "数码", "移动" };

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
		
		if(selectedIndex==position)
		tv.setBackgroundResource(R.drawable.actionbar_text_style);

		return tv;
	}

}
