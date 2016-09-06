package eNews.httpContent;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import eNews.activity.PictureDetailActivity;
import eNews.bean.PictureDetailModel;
import eNews.url.Url;

public class GetPictureNewsDetailContent {

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

	static public void getNewsContent(String pid,
			PictureDetailActivity detailActivity) {

		JSONObject jsonObject = null;
		RequestQueue queue = Volley.newRequestQueue(detailActivity);

		JsonObjectRequest jrq = new JsonObjectRequest(Url.JINGXUANDETAIL_ID
				+ pid, jsonObject, new JsonListener(detailActivity),
				new JsonErrorListener(detailActivity));
		queue.add(jrq);

	}

	static class JsonListener implements Listener<JSONObject> {

		private PictureDetailActivity pictureDetailActivity;

		public JsonListener(PictureDetailActivity pictureDetailActivity) {

			this.pictureDetailActivity = pictureDetailActivity;

		}

		@Override
		public void onResponse(final JSONObject jo) {

			// System.out.println("Josn OK->" + jo.toString());
			PictureDetailModel pictureDetailModel;
			final ArrayList<PictureDetailModel> lists = new ArrayList<PictureDetailModel>();
			try {
				JSONObject dataJsonObject = jo.getJSONObject("data");
				JSONArray rootAyyar = dataJsonObject.getJSONArray("pics");
				for (int i = 0; i < rootAyyar.length(); i++) {

					JSONObject newsItemObject = rootAyyar.getJSONObject(i);
					pictureDetailModel = new PictureDetailModel();

					pictureDetailModel.setAlt(newsItemObject.getString("alt"));
					pictureDetailModel
							.setKpic(newsItemObject.getString("kpic"));

					lists.add(pictureDetailModel);
				}

				pictureDetailActivity.updateAdapter(lists);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	static class JsonErrorListener implements ErrorListener {
		PictureDetailActivity detailActivity;

		public JsonErrorListener(PictureDetailActivity detailActivity) {
			// TODO Auto-generated constructor stub
			this.detailActivity = detailActivity;
		}

		@Override
		public void onErrorResponse(VolleyError error) {

			System.out.println("Json array volley error->" + error);
			if (detailActivity != null)
				Toast.makeText(detailActivity, "数据加载失败!", Toast.LENGTH_SHORT)
						.show();
		}

	}
}
