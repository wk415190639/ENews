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

import eNews.bean.PictureModel;
import eNews.fragments.PictureFragment;

public class GetPictureNewsContent {

	// // 精选列表
	// public static final String JINGXUAN_ID =
	// "http://api.sina.cn/sinago/list.json?channel=hdpic_toutiao&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=&imei=867064013906290&uid=802909da86d9f5fc&p=";
	// // 图片详情
	// public static final String JINGXUANDETAIL_ID =
	// "http://api.sina.cn/sinago/article.json?postt=hdpic_hdpic_toutiao_4&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&id=";
	// // 趣图列表
	// public static final String QUTU_ID =
	// "http://api.sina.cn/sinago/list.json?channel=hdpic_funny&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";
	// // 美图列表
	// public static final String MEITU_ID =
	// "http://api.sina.cn/sinago/list.json?channel=hdpic_pretty&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";
	// // 故事列表
	// public static final String GUSHI_ID =
	// "http://api.sina.cn/sinago/list.json?channel=hdpic_story&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";

	static public void getNewsContent(String url,
			PictureFragment pictureFragment) {

		JSONObject jsonObject = null;
		RequestQueue queue = Volley.newRequestQueue(pictureFragment
				.getActivity());

		JsonObjectRequest jrq = new JsonObjectRequest(url, jsonObject,
				new JsonListener(pictureFragment), new JsonErrorListener());
		queue.add(jrq);

	}

	static class JsonListener implements Listener<JSONObject> {

		private PictureFragment pictureFragment;

		public JsonListener(PictureFragment pictureFragment) {

			this.pictureFragment = pictureFragment;

		}

		@Override
		public void onResponse(final JSONObject jo) {

			// System.out.println("Josn OK->" + jo.toString());
			PictureModel pictureModel;
			final List<PictureModel> lists = new ArrayList<PictureModel>();
			try {
				JSONObject dataJsonObject = jo.getJSONObject("data");
				JSONArray rootAyyar = dataJsonObject.getJSONArray("list");
				for (int i = 0; i < rootAyyar.length(); i++) {

					JSONObject newsItemObject = rootAyyar.getJSONObject(i);
					pictureModel = new PictureModel();
					pictureModel.setId(newsItemObject.getString("id"));
					pictureModel.setKpic(newsItemObject.getString("kpic"));
					pictureModel.setLong_title(newsItemObject
							.getString("long_title"));
					pictureModel.setTitle(newsItemObject.getString("title"));

					lists.add(pictureModel);
				}

				pictureFragment.updateAdapter(lists);

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
