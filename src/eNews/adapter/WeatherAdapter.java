package eNews.adapter;

import java.util.ArrayList;

import eNews.app.R;
import eNews.bean.WeatherInfo;
import eNews.common.GetWeatherIconTypeId;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 天气预报适配器
 */
public class WeatherAdapter extends BaseAdapter {

	ArrayList<WeatherInfo> lists;
	private Context context;

	public WeatherAdapter(Context context) {
		lists = new ArrayList<WeatherInfo>();
		this.context = context;
	}

	public WeatherAdapter(ArrayList<WeatherInfo> list, Context context) {
		this(context);
		this.lists = list;
	}

	public void updateAdapter(ArrayList<WeatherInfo> list) {

		if (!lists.containsAll(list) && list != null && list.size() > 0) {
			lists.clear();
			lists.addAll(list);

		}
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
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View weatherView, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (weatherView == null)
			weatherView = LayoutInflater.from(context).inflate(
					R.layout.weatherinfo_item, null);

		TextView weatherDate = (TextView) weatherView
				.findViewById(R.id.weatherDate);
		ImageView weatherIcon = (ImageView) weatherView
				.findViewById(R.id.weatherIcon);

		TextView weatherTemp = (TextView) weatherView
				.findViewById(R.id.weatherTemp);

		TextView weatherType = (TextView) weatherView
				.findViewById(R.id.weatherType);

		WeatherInfo weatherInfo = lists.get(position);

		String date = weatherInfo.getDate();

		weatherDate.setText(date);

		weatherTemp.setText(weatherInfo.getLow().substring(2).trim() + "~"
				+ weatherInfo.getHigh().substring(2).trim());
		weatherType.setText(weatherInfo.getType() + "  "
				+ weatherInfo.getFengxiang() + weatherInfo.getFengli());

		weatherIcon.setImageResource(GetWeatherIconTypeId.getTypeId(weatherInfo
				.getType()));

		return weatherView;
	}
}
