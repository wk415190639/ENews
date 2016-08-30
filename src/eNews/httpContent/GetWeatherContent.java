package eNews.httpContent;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import eNews.bean.WeatherInfo;
import eNews.fragments.WeatherFragment;
import eNews.url.Url;

public class GetWeatherContent {

	static public void getNewsContent(String localCityName,
			WeatherFragment weatherFragment)
			throws UnsupportedEncodingException {

		JSONObject jsonObject = null;

		if (localCityName == null)
			localCityName = getLocalCityName();
		RequestQueue queue = Volley.newRequestQueue(weatherFragment.getActivity());

		JsonObjectRequest jrq = new JsonObjectRequest(Url.WeatherHost
				+ URLEncoder.encode(localCityName, "utf-8"), jsonObject,
				new JsonListener(weatherFragment), new JsonErrorListener());
		queue.add(jrq);

	}

	static class JsonListener implements Listener<JSONObject> {

		private WeatherFragment weatherFragment;

		public JsonListener(WeatherFragment weatherFragment) {

			this.weatherFragment =weatherFragment;

		}

		@Override
		public void onResponse(final JSONObject rootJsons) {

			try {

				WeatherInfo info;
				ArrayList<WeatherInfo> lists = new ArrayList<WeatherInfo>();
				// Ĭ��ʹ�� ISO-8859-1 �ַ����� ISO-8859-1�ı�������Latin1
				JSONObject rootJson = new JSONObject(new String(rootJsons
						.toString().getBytes("Latin1"),
						Charset.forName("utf-8")));
				JSONObject dataJsonObj = rootJson.getJSONObject("data");
				JSONArray forecaseJsonArr = dataJsonObj
						.getJSONArray("forecast");
				JSONObject yesterdayJsonObj = dataJsonObj
						.getJSONObject("yesterday");

				info = new WeatherInfo();
				info.setFengxiang(yesterdayJsonObj.getString("fx"));
				info.setFengli(yesterdayJsonObj.getString("fl"));
				info.setHigh(yesterdayJsonObj.getString("high"));
				info.setType(yesterdayJsonObj.getString("type"));
				info.setLow(yesterdayJsonObj.getString("low"));
				info.setDate(yesterdayJsonObj.getString("date"));
				lists.add(info);

				for (int i = 0; i < forecaseJsonArr.length(); i++) {
					JSONObject tempJsonObject = forecaseJsonArr
							.getJSONObject(i);
					info = new WeatherInfo();
					info.setFengxiang(tempJsonObject.getString("fengxiang"));
					info.setFengli(tempJsonObject.getString("fengli"));
					info.setHigh(tempJsonObject.getString("high"));
					info.setType(tempJsonObject.getString("type"));
					info.setLow(tempJsonObject.getString("low"));
					info.setDate(tempJsonObject.getString("date"));
					lists.add(info);

				}

				lists.get(1).setGanmao(dataJsonObj.getString("ganmao"));
				lists.get(1).setWendu(dataJsonObj.getString("wendu"));
				weatherFragment.fillAdapter(lists);
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}

	static class JsonErrorListener implements ErrorListener {

		@Override
		public void onErrorResponse(VolleyError error) {

			System.out.println("Json array volley error->" + error);
		}

	}

	public static String getLocalCityName() {
		return "����";
	}

}