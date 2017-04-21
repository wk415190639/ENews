package eNews.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import eNews.app.R;
import eNews.bean.ChannelItemModel;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 选中的频道标签适配器
 */
public class UserChannelAdapter extends BaseAdapter {

	private ArrayList<ChannelItemModel> lists;
	private Context context;

	public UserChannelAdapter(Context context, ArrayList<ChannelItemModel> list) {
		this.context = context;
		this.lists = list;

	}

	public void remove(int position, OtherChannelAdapter otherChannelAdapter) {

		ChannelItemModel tmpChannelItemModel = lists.get(position);
		lists.remove(position);
		otherChannelAdapter.add(tmpChannelItemModel);
		notifyDataSetChanged();
	}

	public void add(ChannelItemModel addChannelItemModel) {
		lists.add(addChannelItemModel);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView tv = new TextView(context);
		tv.setText(lists.get(position).getName());
		tv.setGravity(Gravity.CENTER);
		tv.setBackgroundResource(R.drawable.channel_item_style);

		return tv;
	}

}
