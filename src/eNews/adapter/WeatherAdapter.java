package eNews.adapter;

import java.util.ArrayList;

import eNews.app.R;
import eNews.bean.WeatherInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherAdapter extends BaseAdapter {

	ArrayList<WeatherInfo> lists;
	private Context context;

	public WeatherAdapter(ArrayList<WeatherInfo> list, Context context) {
		// TODO Auto-generated constructor stub
		lists = new ArrayList<WeatherInfo>();
		this.context = context;
		this.lists = list;
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
					R.layout.weather, null);

		TextView weatherDate = (TextView) weatherView
				.findViewById(R.id.weatherDate);
		ImageView weatherIcon = (ImageView) weatherView
				.findViewById(R.id.weatherIcon);

		TextView weatherTemp = (TextView) weatherView
				.findViewById(R.id.weatherTemp);

		TextView weatherType = (TextView) weatherView
				.findViewById(R.id.weatherType);

		TextView weatherFengXiang = (TextView) weatherView
				.findViewById(R.id.weatherFengXiang);

		WeatherInfo weatherInfo = lists.get(position);
		weatherDate.setText(weatherInfo.getDate());

		weatherTemp.setText(weatherInfo.getHigh());
		weatherType.setText(weatherInfo.getType());
		weatherFengXiang.setText(weatherInfo.getFengxiang());

		// weatherIcon

		return weatherView;
	}

}
