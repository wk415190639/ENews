package eNews.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import eNews.adapter.WeatherAdapter;
import eNews.app.R;
import eNews.bean.WeatherInfo;
import eNews.common.GetWeatherIcon;
import eNews.common.GetWeatherIconTypeId;

public class WeatherActivity extends Activity {

	private TextView TopDateTv;
	private ImageView TopIconIv;
	private TextView TopTempTv;
	private TextView TopTypeTv;
	private TextView TopFengXiangTv;
	public GridView weekWeathersGridView;

	private Button getLocateCityBtn;

	private WeatherAdapter weatherAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.weather);
		TopDateTv = (TextView) findViewById(R.id.weatherDate_top);
		TopIconIv = (ImageView) findViewById(R.id.weatherIcon_top);
		TopTempTv = (TextView) findViewById(R.id.weatherTemp_top);
		TopTypeTv = (TextView) findViewById(R.id.weatherType_top);
		TopFengXiangTv = (TextView) findViewById(R.id.weatherFengXinag_top);
		weekWeathersGridView = (GridView) findViewById(R.id.weekWeathersListView);
		weatherAdapter = new WeatherAdapter(getApplicationContext());
		weekWeathersGridView.setAdapter(weatherAdapter);
		// try {
		// // GetWeatherContent.getNewsContent(null, this);
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public void fillTopWindows(WeatherInfo waInfo) {
		TopDateTv.setText(waInfo.getDate());
		TopIconIv.setImageResource(GetWeatherIconTypeId.getTypeId(waInfo
				.getType()));
		TopTempTv.setText(waInfo.getLow());
		TopTypeTv.setText(waInfo.getType());
		TopFengXiangTv.setText(waInfo.getFengxiang());
		TopIconIv.setImageResource(GetWeatherIcon.get(waInfo.getType()));

	}

	public void fillAdapter(ArrayList<WeatherInfo> lists) {

		fillTopWindows(lists.get(0));
		System.out.println("fillAdapter");
		lists.remove(1);
		weatherAdapter.updateAdapter(lists);
		weekWeathersGridView.setNumColumns(weatherAdapter.getCount());
		LayoutParams params = weekWeathersGridView.getLayoutParams();
		params.width = 200 * weatherAdapter.getCount();
		weekWeathersGridView.setLayoutParams(params);

	}

}
