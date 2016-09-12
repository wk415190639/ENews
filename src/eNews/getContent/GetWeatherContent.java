package eNews.getContent;

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

/**
 * 
 * @author 王凯
 * @date 2016-9-12 获取天气数据
 */
public class GetWeatherContent {

	static RequestQueue queue;

	static public void getNewsContent(String localCityName,
			WeatherFragment weatherFragment)
			throws UnsupportedEncodingException {
		queue = Volley.newRequestQueue(weatherFragment.getActivity());
		JSONObject jsonObject = null;

		if (localCityName == null)
			localCityName = getLocalCityName();

		JsonObjectRequest jrq = new JsonObjectRequest(Url.WeatherHost
				+ URLEncoder.encode(localCityName, "utf-8"), jsonObject,
				new JsonListener(weatherFragment), new JsonErrorListener(
						weatherFragment));
		queue.add(jrq);

	}

	static class JsonListener implements Listener<JSONObject> {

		private WeatherFragment weatherFragment;

		public JsonListener(WeatherFragment weatherFragment) {

			this.weatherFragment = weatherFragment;

		}

		@Override
		public void onResponse(final JSONObject rootJsons) {

			try {

				WeatherInfo info;
				ArrayList<WeatherInfo> lists = new ArrayList<WeatherInfo>();
				// 默认使用 ISO-8859-1 字符集。 ISO-8859-1的别名叫做Latin1
				JSONObject rootJson = new JSONObject(new String(rootJsons
						.toString().getBytes("Latin1"),
						Charset.forName("utf-8")));
				JSONObject dataJsonObj = rootJson.getJSONObject("data");
				JSONArray forecaseJsonArr = dataJsonObj
						.getJSONArray("forecast");
				JSONObject yesterdayJsonObj = dataJsonObj
						.getJSONObject("yesterday");

				// 昨天天气
				info = new WeatherInfo();
				info.setFengxiang(yesterdayJsonObj.getString("fx"));
				info.setFengli(yesterdayJsonObj.getString("fl"));
				info.setHigh(yesterdayJsonObj.getString("high"));
				info.setType(yesterdayJsonObj.getString("type"));
				info.setLow(yesterdayJsonObj.getString("low"));

				String date = yesterdayJsonObj.getString("date");
				info.setDate("昨天 ");

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
					date = tempJsonObject.getString("date");

					if (i > 0)
						info.setDate("周"
								+ date.substring(date.length() - 1,
										date.length()));
					else
						info.setDate(date);
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
		WeatherFragment weatherFragment;

		public JsonErrorListener(WeatherFragment weatherFragment) {
			// TODO Auto-generated constructor stub
			this.weatherFragment = weatherFragment;
		}

		@Override
		public void onErrorResponse(VolleyError error) {

			System.out.println("Json array volley error->" + error);
			// if(weatherFragment!=null)
			// weatherFragment.showToast("加载失败");
		}

	}

	public static String getLocalCityName() {
		return "大连";
	}

}
