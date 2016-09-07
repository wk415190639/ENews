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

import eNews.bean.VideoModel;
import eNews.fragments.VideoFragment;
import eNews.url.Url;

public class GetVideoNewsContent {

	//

	// public static final String Video = host + "nc/video/list/";
	// public static final String VideoCenter = "/n/";
	// public static final String videoEndUrl = "-10.html";

	//  ”∆µ http://c.3g.163.com/ nc/video/list/ V9LG4B3A0 /n/ 10 -10.html
	// host url typeId /n/ startNum endUrl
	static public void getNewsContent(String typeId, String startNum,
			VideoFragment videoNewsFragment) {

		JSONObject jsonObject = null;
		RequestQueue queue = Volley.newRequestQueue(videoNewsFragment
				.getActivity());

		JsonObjectRequest jrq = new JsonObjectRequest(Url.Video + typeId
				+ Url.VideoCenter + startNum + Url.videoEndUrl, jsonObject,
				new JsonListener(videoNewsFragment, typeId),
				new JsonErrorListener(videoNewsFragment));
		queue.add(jrq);

	}

	static class JsonListener implements Listener<JSONObject> {

		private String typeId;
		private VideoFragment videoFragment;

		public JsonListener(VideoFragment videoFragment, String typeId) {

			this.videoFragment = videoFragment;
			this.typeId = typeId;

		}

		@Override
		public void onResponse(final JSONObject jo) {

			// System.out.println("Josn OK->" + jo.toString());
			VideoModel videoModel;
			final List<VideoModel> lists = new ArrayList<VideoModel>();
			try {
				JSONArray rootAyyar = jo.getJSONArray(typeId);

				for (int i = 0; i < rootAyyar.length(); i++) {

					JSONObject newsItemObject = rootAyyar.getJSONObject(i);
					videoModel = new VideoModel();

					videoModel.setTitle(newsItemObject.getString("title"));
					videoModel.setMp4Hd_url(newsItemObject
							.getString("mp4Hd_url"));

					if (!typeId.equals("00850FRB")) {
						videoModel.setTopicDesc(newsItemObject
								.getString("topicDesc"));
						videoModel.setVideosource(newsItemObject
								.getString("videosource"));
					}

					videoModel.setTopicImg(newsItemObject.getString("cover"));
					videoModel.setMp4_url(newsItemObject.getString("mp4_url"));

					videoModel.setLength(newsItemObject.getString("length"));

					lists.add(videoModel);

					System.out.println(videoModel.getMp4_url());

				}

				videoFragment.updateAdapter(lists);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	static class JsonErrorListener implements ErrorListener {
		VideoFragment videoNewsFragment;

		public JsonErrorListener(VideoFragment videoNewsFragment) {
			// TODO Auto-generated constructor stub
			this.videoNewsFragment = videoNewsFragment;
		}

		@Override
		public void onErrorResponse(VolleyError error) {

			System.out.println("Json array volley error->" + error);

			// if (videoNewsFragment.getActivity() != null) {
			// videoNewsFragment.showToast("º”‘ÿ ß∞‹");
			//
			// }
		}

	}
}
