package eNews.http;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import u.aly.ac;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import eNews.activity.MainActivity;
import eNews.activity.MainActivity.finishGetJsonObject;
import eNews.bean.NewsModel;

public class GetNewsContent {

	static public void getNewsContent(String url, String typeId,
			String startNum, MainActivity mainActivity) {

		JSONObject jsonObject = null;
		RequestQueue queue = Volley.newRequestQueue(mainActivity
				.getApplicationContext());

		// http://c.m.163.com/ nc/article/headline/ T1350383429665/ 0 -20.html
		// host url typeId startNum endUrl

		JsonObjectRequest jrq = new JsonObjectRequest(Url.host + url + typeId
				+ startNum + Url.endUrl, jsonObject, new JsonListener(
				mainActivity, typeId), new JsonErrorListener());
		queue.add(jrq);

	}

	static class JsonListener implements Listener<JSONObject> {

		private MainActivity mainActivity;
		private String typeId;

		public JsonListener(MainActivity a, String typeId) {

			this.mainActivity = a;
			this.typeId = typeId;

		}

		@Override
		public void onResponse(final JSONObject jo) {

			// System.out.println("Josn OK->" + jo.toString());
			mainActivity.updateAdapter(new finishGetJsonObject() {

				private NewsModel newsModel;
				private List<NewsModel> lists = new ArrayList<NewsModel>();

				@Override
				public void UpdateAdapter() {

					try {
						JSONArray rootAyyar = jo.getJSONArray(typeId.substring(
								0, typeId.length() - 1));

						for (int i = 0; i < rootAyyar.length(); i++) {

							newsModel = new NewsModel();
							JSONObject newsItemObject = rootAyyar
									.getJSONObject(i);

							System.out.println(newsItemObject
									.getString("title") + "---->" + i);

							newsModel.setTitle(newsItemObject
									.getString("title"));
							newsModel.setDigest(newsItemObject
									.getString("digest"));

							newsModel.setImagesrc(newsItemObject
									.getString("imgsrc"));

							System.out.println(newsModel.getTitle()+"<-->"+newsModel.getImagesrc()+newsModel.getDigest());
							lists.add(newsModel);

						}
						mainActivity.newsAdapter.appendList(lists);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});

		}

	}

	static class JsonErrorListener implements ErrorListener {

		@Override
		public void onErrorResponse(VolleyError error) {

			System.out.println("Json array volley error->" + error);
		}

	}
}
