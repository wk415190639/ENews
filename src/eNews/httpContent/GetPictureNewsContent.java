package eNews.httpContent;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import eNews.bean.PictureModel;
import eNews.fragments.PictureFragment;

public class GetPictureNewsContent {

	static public void getNewsContent(String url,
			PictureFragment pictureFragment) {

		// http://c.m.163.com/photo/api/morelist/0096/54GM0096/ 42262.json

		System.out.println(url);
		RequestQueue queue = Volley.newRequestQueue(pictureFragment
				.getActivity());
		JsonArrayRequest jaq = new JsonArrayRequest(url, new JsonListener(
				pictureFragment), new JsonErrorListener(pictureFragment));

		queue.add(jaq);

	}

	static class JsonListener implements Listener<JSONArray> {

		private PictureFragment pictureFragment;

		public JsonListener(PictureFragment pictureFragment) {

			this.pictureFragment = pictureFragment;

		}

		@Override
		public void onResponse(JSONArray ja) {

			PictureModel pictureModel;
			System.out.println(ja);
			final List<PictureModel> lists = new ArrayList<PictureModel>();
			try {

				for (int i = 0; i < ja.length(); i++) {

					System.out.println("精品");
					JSONObject newsItemObject = ja.getJSONObject(i);
					pictureModel = new PictureModel();

					pictureModel.setSetId(newsItemObject.getString("setid"));
					pictureModel
							.setSetName(newsItemObject.getString("setname"));

					JSONArray jsonPics = newsItemObject.getJSONArray("pics");
					for (int j = 0; i < jsonPics.length(); j++) {
						pictureModel.getPics().add(jsonPics.getString(j));
					}

					pictureModel.setImgsrc(jsonPics.getString(0));

					lists.add(pictureModel);
				}

				pictureFragment.updateAdapter(lists);

			} catch (Exception e) {

			}
		}
	}

	static public void getMeiTuContent(String url,
			PictureFragment pictureFragment) {

		// http://c.m.163.com/photo/api/morelist/0096/54GM0096/ 42262.json

		System.out.println(url);
		RequestQueue queue = Volley.newRequestQueue(pictureFragment
				.getActivity());

		JsonObjectRequest joq = new JsonObjectRequest(url, null,
				new JsonMeituListener(pictureFragment), new JsonErrorListener(
						pictureFragment));
		queue.add(joq);

	}

	static class JsonMeituListener implements Listener<JSONObject> {
		private PictureFragment pictureFragment;

		public JsonMeituListener(PictureFragment pictureFragment) {
			// TODO Auto-generated constructor stub
			this.pictureFragment = pictureFragment;
		}

		@Override
		public void onResponse(JSONObject jo) {
			// TODO Auto-generated method stub
			PictureModel pictureModel;
			final List<PictureModel> lists = new ArrayList<PictureModel>();
			try {

				JSONArray root = jo.getJSONArray("美女");

				for (int i = 0; i < root.length(); i++) {

					JSONObject newsItemObject = root.getJSONObject(i);
					pictureModel = new PictureModel();

					pictureModel.setDigest(newsItemObject.getString("digest"));

					pictureModel.setDocid(newsItemObject.getString("docid"));
					pictureModel.setDownTimes(newsItemObject
							.getString("downTimes"));
					pictureModel.setImg(newsItemObject.getString("img"));
					pictureModel.setImgsrc(newsItemObject.getString("imgsrc"));
					pictureModel.setSource(newsItemObject.getString("source"));
					pictureModel.setTitle(newsItemObject.getString("title"));
					pictureModel
							.setUpTimes(newsItemObject.getString("upTimes"));

					lists.add(pictureModel);
				}

				pictureFragment.updateAdapter(lists);

			} catch (Exception e) {

			}

		}

	}

	static class JsonErrorListener implements ErrorListener {

		private PictureFragment pictureFragment;

		public JsonErrorListener(PictureFragment pictureFragment) {
			// TODO Auto-generated constructor stub
			this.pictureFragment = pictureFragment;
		}

		@Override
		public void onErrorResponse(VolleyError error) {

			System.out.println("Json array volley error->" + error);

		}

	}
}
