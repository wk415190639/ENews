package eNews.fragments;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import eNews.activity.MainWindows;
import eNews.adapter.WeatherAdapter;
import eNews.app.R;
import eNews.bean.WeatherInfo;
import eNews.common.GetWeatherIconTypeId;
import eNews.httpContent.GetWeatherContent;
import eNews.url.Url;

public class WeatherFragment extends Fragment {

	private View view;

	private TextView TopDateTv;
	private ImageView TopIconIv;
	private TextView TopTempTv;
	private TextView TopTypeTv;
	private TextView TopFengXiangTv;
	private TextView TopWeek;
	public ListView weekWeathersListView;
	private TextView getLocateCityTv;
	private ProgressDialog progressDialog;
	private TextView weatherGanMaoTv;
	private Button backBtn;

	private myLocationListener locationListener;

	private WeatherAdapter weatherAdapter;

	private String locationProvider;
	LocationManager locationManager;
	Location location;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getActionBar().hide();
		view = inflater.inflate(R.layout.weather, null);

		backBtn = (Button) view.findViewById(R.id.backBtn);

		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((MainWindows) getActivity()).showMainFragment();
			}
		});
		TopWeek = (TextView) view.findViewById(R.id.weatherWeek_top);
		weatherGanMaoTv = (TextView) view.findViewById(R.id.weatherGanMao_top);
		TopDateTv = (TextView) view.findViewById(R.id.weatherDate_top);
		TopIconIv = (ImageView) view.findViewById(R.id.weatherIcon_top);
		TopTempTv = (TextView) view.findViewById(R.id.weatherTemp_top);
		TopTypeTv = (TextView) view.findViewById(R.id.weatherType_top);
		TopFengXiangTv = (TextView) view
				.findViewById(R.id.weatherFengXinag_top);
		weekWeathersListView = (ListView) view
				.findViewById(R.id.weekWeathersListView);
		weatherAdapter = new WeatherAdapter(getActivity());
		weekWeathersListView.setAdapter(weatherAdapter);

		initLocate();

		getLocateCityTv = (TextView) view.findViewById(R.id.getLocateCityTv);
		getLocateCityTv.setOnClickListener(new getLocateCityClick());

		return view;
	}

	public void fillTopWindows(WeatherInfo waInfo) {

		String date = waInfo.getDate();

		TopDateTv.setText(date.substring(0, date.length() - 3));
		TopWeek.setText(date.substring(date.length() - 3, date.length()));
		TopIconIv.setImageResource(GetWeatherIconTypeId.getTypeId(waInfo
				.getType()));
		System.out.println(waInfo.getType() + "----------------->");
		TopTempTv.setText(waInfo.getWendu() + "℃");
		TopTypeTv.setText(waInfo.getType());
		TopFengXiangTv
				.setText(waInfo.getFengxiang() + " " + waInfo.getFengli());
		weatherGanMaoTv.setText(waInfo.getGanmao());
		System.out.println(waInfo.getGanmao());

	}

	public void fillAdapter(ArrayList<WeatherInfo> lists) {

		fillTopWindows(lists.get(1));
		System.out.println("fillAdapter");
		lists.remove(1);
		weatherAdapter.updateAdapter(lists);

	}

	class getLocateCityClick implements OnClickListener {

		@Override
		public void onClick(View v) {

			initLocate();

		}
	}

	private void getLocationJson(Location location) {
		RequestQueue rq = Volley.newRequestQueue(getActivity());

		String locationUrl = Url.GeocoderUrl + "latlng="
				+ location.getLatitude() + "," + location.getLongitude();

		System.out.println(locationUrl);
		JsonObjectRequest jr = new JsonObjectRequest(locationUrl, null,
				new GetLocationListener(), new GetLocationError());
		rq.add(jr);

	}

	private void initLocate() {
		locationManager = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);

		List<String> provides = locationManager.getProviders(true);
		for (String provider : provides) {
			System.out.println(provider);
		}

		if (provides.contains(LocationManager.NETWORK_PROVIDER)) {
			locationProvider = LocationManager.NETWORK_PROVIDER;
		} else if (provides.contains(LocationManager.GPS_PROVIDER)) {
			locationProvider = LocationManager.GPS_PROVIDER;
		} else if (provides.contains(LocationManager.PASSIVE_PROVIDER)) {
			locationProvider = LocationManager.PASSIVE_PROVIDER;
		} else {
			Toast.makeText(getActivity(), "没有可用的位置提供器", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		System.out.println(locationProvider);
		locationListener = new myLocationListener();
		locationManager.requestLocationUpdates(locationProvider, 60000, 1,
				locationListener);
		location = locationManager.getLastKnownLocation(locationProvider);
		if (location == null)
			Toast.makeText(getActivity(), "正在定位...........", Toast.LENGTH_SHORT)
					.show();
		else
			getLocationJson(location);
	}

	class GetLocationListener implements Listener<JSONObject> {

		@Override
		public void onResponse(JSONObject jsObject) {
			try {
				JSONArray resultsArray = jsObject.getJSONArray("results");

				JSONObject firstResultObject = resultsArray.getJSONObject(0);

				String longLocateName = firstResultObject
						.getString("formatted_address");

				int before = longLocateName.indexOf("省") + 1;
				int after = longLocateName.indexOf("市");

				if (before == -1 || after == -1) {
					progressDialog.dismiss();
					System.out.println("没有找到");

					try {
						GetWeatherContent.getNewsContent(null,
								WeatherFragment.this);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// TODO Auto-generated catch block
					return;
				}

				String cityName = longLocateName.substring(before, after);

				try {
					System.out.println(cityName);
					GetWeatherContent.getNewsContent(cityName,
							WeatherFragment.this);
					getLocateCityTv.setText(cityName);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					progressDialog.dismiss();
					e.printStackTrace();
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				progressDialog.dismiss();
				e.printStackTrace();
			}

		}

	}

	class GetLocationError implements ErrorListener {

		@Override
		public void onErrorResponse(VolleyError arg0) {

			System.out.println(arg0.toString());
			progressDialog.dismiss();
		}

	}

	class myLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub

			WeatherFragment.this.location = location;
			locationManager.removeUpdates(locationListener);
			System.out.println("location");
			getLocationJson(location);

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

	}

}
