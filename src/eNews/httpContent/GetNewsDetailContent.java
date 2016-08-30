package eNews.httpContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import eNews.activity.NewsDetailActivity;
import eNews.bean.NewsDetailModel;
import eNews.url.Url;

public class GetNewsDetailContent {

	static public void getNewsContent(String postId,
			NewsDetailActivity newsDetailActivity) {

		JSONObject jsonObject = null;
		RequestQueue queue = Volley.newRequestQueue(newsDetailActivity);

		JsonObjectRequest jrq = new JsonObjectRequest(Url.NewDetail + postId
				+ Url.endDetailUrl, jsonObject, new JsonListener(
				newsDetailActivity, postId), new JsonErrorListener());
		queue.add(jrq);

	}

	static class JsonListener implements Listener<JSONObject> {

		private String postId;
		private NewsDetailActivity newsDetailActivity;

		public JsonListener(NewsDetailActivity newsDetailActivity, String postId) {

			this.postId = postId;
			this.newsDetailActivity = newsDetailActivity;

		}

		@Override
		public void onResponse(final JSONObject jo) {

			// System.out.println("Josn OK->" + jo.toString());
			try {

				NewsDetailModel detailModel = new NewsDetailModel();

				JSONObject root = jo.getJSONObject(postId);

				detailModel.setBody(root.getString("body"));

				detailModel.setTitle(root.getString("title"));

				detailModel.setPtime(root.getString("ptime"));

				JSONArray imgJsonArray = root.getJSONArray("img");

				for (int i = 0; i < imgJsonArray.length(); i++) {

					JSONObject tmepJsonObject = imgJsonArray.getJSONObject(i);
					detailModel.getImg().add(tmepJsonObject.getString("src"));
				}

				newsDetailActivity.setContent(detailModel);
				System.out.println("setContent");

			} catch (JSONException e) {
				// TODO Auto-generated catch block
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
}