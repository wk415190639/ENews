package eNews.httpContent;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import eNews.bean.NewsModel;
import eNews.fragments.MainFragment;
import eNews.url.Url;

public class GetNewsContent {

	// http://c.m.163.com/ nc/article/headline/ T1350383429665/ 0 -20.html
	// host url typeId startNum endUrl

	//
	// //  ”∆µ http://c.3g.163.com/ nc/video/list/ V9LG4B3A0 /n/10-10.html

	// public static final String Video = host + "nc/video/list/";
	// public static final String VideoCenter = "/n/";
	// public static final String videoEndUrl = "-10.html";

	static public void getNewsContent(String url, String typeId,
			String startNum, MainFragment mainActivity) {

		System.gc();
		String httpUrl = Url.host + url + typeId + "/" + startNum + Url.endUrl;
		JSONObject jsonObject = null;
		RequestQueue queue = Volley.newRequestQueue(mainActivity.getActivity()
				.getApplicationContext());

		JsonObjectRequest jrq = new JsonObjectRequest(httpUrl, jsonObject,
				new JsonListener(mainActivity, typeId), new JsonErrorListener(
						mainActivity));
		queue.add(jrq);

		System.out.println(httpUrl);

	}

	static class JsonListener implements Listener<JSONObject> {

		private MainFragment mainFragment;
		private String typeId;

		public JsonListener(MainFragment a, String typeId) {

			this.mainFragment = a;
			this.typeId = typeId;

		}

		@Override
		public void onResponse(final JSONObject jo) {

			// System.out.println("Josn OK->" + jo.toString());
			NewsModel newsModel;
			final List<NewsModel> lists = new ArrayList<NewsModel>();
			try {
				JSONArray rootAyyar = jo.getJSONArray(typeId);

				for (int i = 0; i < rootAyyar.length(); i++) {

					JSONObject newsItemObject = rootAyyar.getJSONObject(i);
					// if
					// (GetTypeId.isBvNews(newsItemObject.getString("postid")))
					// {
					newsModel = new NewsModel();
					newsModel.setTitle(newsItemObject.getString("title"));
					newsModel.setDigest(newsItemObject.getString("digest"));
					newsModel.setImagesrc(newsItemObject.getString("imgsrc"));
					// newsModel.setPostid(newsItemObject.getString("postid"));
					newsModel.setDocid(newsItemObject.getString("docid"));
					lists.add(newsModel);
					// }
				}
				mainFragment.updateAdapter(lists);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	static class JsonErrorListener implements ErrorListener {

		private MainFragment mainFragment;

		public JsonErrorListener(MainFragment mainFragment) {
			// TODO Auto-generated constructor stub
			this.mainFragment = mainFragment;
		}

		@Override
		public void onErrorResponse(VolleyError error) {

			mainFragment.isLoadContent = false;
			System.out.println("Json array volley error->" + error);
			mainFragment.showToast("º”‘ÿ ß∞‹");

		}

	}
}
