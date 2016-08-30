package eNews.fragments;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import eNews.adapter.WeatherAdapter;
import eNews.app.R;
import eNews.bean.WeatherInfo;
import eNews.common.GetWeatherIconTypeId;
import eNews.httpContent.GetWeatherContent;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherFragment extends Fragment {

	private View view;

	private TextView TopDateTv;
	private ImageView TopIconIv;
	private TextView TopTempTv;
	private TextView TopTypeTv;
	private TextView TopFengXiangTv;
	public GridView weekWeathersGridView;

	private WeatherAdapter weatherAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.weather, null);

		TopDateTv = (TextView) view.findViewById(R.id.weatherDate_top);
		TopIconIv = (ImageView) view.findViewById(R.id.weatherIcon_top);
		TopTempTv = (TextView) view.findViewById(R.id.weatherTemp_top);
		TopTypeTv = (TextView) view.findViewById(R.id.weatherType_top);
		TopFengXiangTv = (TextView) view
				.findViewById(R.id.weatherFengXinag_top);
		weekWeathersGridView = (GridView) view
				.findViewById(R.id.weekWeathersGridView);
		weatherAdapter = new WeatherAdapter(getActivity());
		weekWeathersGridView.setAdapter(weatherAdapter);
		try {
			GetWeatherContent.getNewsContent(null, this);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return view;
	}

	public void fillTopWindows(WeatherInfo waInfo) {
		TopDateTv.setText(waInfo.getDate());
		TopIconIv.setImageResource(GetWeatherIconTypeId.getTypeId(waInfo
				.getType()));
		System.out.println(waInfo.getType() + "----------------->");
		TopTempTv.setText(waInfo.getLow());
		TopTypeTv.setText(waInfo.getType());
		TopFengXiangTv.setText(waInfo.getFengxiang());

	}

	public void fillAdapter(ArrayList<WeatherInfo> lists) {

		fillTopWindows(lists.get(1));
		System.out.println("fillAdapter");
		lists.remove(1);
		weatherAdapter.updateAdapter(lists);
		weekWeathersGridView.setNumColumns(weatherAdapter.getCount());
		LayoutParams params = weekWeathersGridView.getLayoutParams();
		params.width = 200 * weatherAdapter.getCount();
		weekWeathersGridView.setLayoutParams(params);

	}
}
