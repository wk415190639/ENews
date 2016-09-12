package eNews.getContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import eNews.activity.MeiTuDetailActivity;
import eNews.bean.NewsDetailModel;
import eNews.url.Url;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 获取美图新闻数据
 */
public class GetMeiTuDetailContent {

	static public void getNewsContent(String postId,
			MeiTuDetailActivity meiTuDetailActivity) {

		JSONObject jsonObject = null;

		RequestQueue queue = Volley.newRequestQueue(meiTuDetailActivity);

		System.out.println(Url.NewDetail + postId + Url.endDetailUrl);
		JsonObjectRequest jrq = new JsonObjectRequest(Url.NewDetail + postId
				+ Url.endDetailUrl, jsonObject, new JsonListener(
				meiTuDetailActivity, postId), new JsonErrorListener(
				meiTuDetailActivity));
		queue.add(jrq);

	}

	static class JsonListener implements Listener<JSONObject> {

		private String postId;
		private MeiTuDetailActivity meiTuDetailActivity;

		public JsonListener(MeiTuDetailActivity meiTuDetailActivity,
				String postId) {

			this.postId = postId;
			this.meiTuDetailActivity = meiTuDetailActivity;

		}

		@Override
		public void onResponse(final JSONObject jo) {

			// System.out.println("Josn OK->" + jo.toString());
			try {

				// System.out.println(jo);
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

				if (root.has("shareLink")) {
					detailModel.setShareLink(root.getString("shareLink"));
				} else if (root.has("source_url")) {
					detailModel.setShareLink(root.getString("source_url"));
				} else {
					detailModel.setShareLink("https://www.baidu.com");
				}

				System.out.println("---------------"
						+ detailModel.getShareLink());

				meiTuDetailActivity.setContent(detailModel);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	static class JsonErrorListener implements ErrorListener {
		MeiTuDetailActivity meiTuDetailActivity;

		public JsonErrorListener(MeiTuDetailActivity meiTuDetailActivity) {
			// TODO Auto-generated constructor stub

			this.meiTuDetailActivity = meiTuDetailActivity;
		}

		@Override
		public void onErrorResponse(VolleyError error) {

			System.out.println("Json array volley error->" + error);
			// if(meiTuDetailActivity!=null)
			// Toast.makeText(meiTuDetailActivity, "数据加载失败!",
			// Toast.LENGTH_SHORT)
			// .show();
		}

	}
}
