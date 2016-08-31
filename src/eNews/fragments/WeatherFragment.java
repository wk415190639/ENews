package eNews.fragments;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
	public GridView weekWeathersGridView;
	private Button getLocateCityBtn;
	private ProgressDialog progressDialog;
	private Location location;

	private WeatherAdapter weatherAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.weather, null);

		LocationManager locationManager = (LocationManager) getActivity()
				.getSystemService(Context.LOCATION_SERVICE);
		if (locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) {
			{
				locationManager.requestLocationUpdates(
						LocationManager.GPS_PROVIDER, 0, 0,
						new LocationChangeListener());
				location = locationManager
						.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			}
		} else
			Toast.makeText(getActivity(), "无法定位", Toast.LENGTH_SHORT).show();
		getLocationJson(location);
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

		getLocateCityBtn = (Button) view.findViewById(R.id.getLocateCityBtn);
		getLocateCityBtn.setOnClickListener(new getLocateCityClick());

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

	class getLocateCityClick implements OnClickListener {

		@Override
		public void onClick(View v) {

			getLocationJson(location);

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

		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMessage("Loading...");
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	class LocationChangeListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
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

	class GetLocationListener implements Listener<JSONObject> {

		@Override
		public void onResponse(JSONObject jsObject) {
			try {
				JSONArray resultsArray = jsObject.getJSONArray("results");

				System.out.println(resultsArray.toString());
				JSONObject firstResultObject = resultsArray.getJSONObject(0);

				String longLocateName = firstResultObject
						.getString("formatted_address");

				int before = longLocateName.indexOf("省") + 1;
				int after = longLocateName.indexOf("市");

				if (before == -1 || after == -1) {
					progressDialog.dismiss();
					System.out.println("没有找到");
					return;
				}

				String cityName = longLocateName.substring(before, after);

				try {
					System.out.println(cityName);
					GetWeatherContent.getNewsContent(cityName,
							WeatherFragment.this);
					getLocateCityBtn.setText(cityName);
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

			progressDialog.dismiss();
		}

	}

	class GetLocationError implements ErrorListener {

		@Override
		public void onErrorResponse(VolleyError arg0) {

			System.out.println(arg0.toString());
			progressDialog.dismiss();
		}

	}

}
