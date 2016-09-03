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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import eNews.bean.PictureModel;
import eNews.fragments.PictureFragment;
import eNews.url.Url;

public class GetPictureNewsContent {

	static public void getNewsContent(String url,
			PictureFragment pictureFragment) {

		// http://c.m.163.comphoto/api/morelist/0096/54GM0096/ 42262.json

		System.out.println(url);
		RequestQueue queue = Volley.newRequestQueue(pictureFragment
				.getActivity());

		JsonArrayRequest jaq = new JsonArrayRequest(url, new JsonListener(
				pictureFragment), new JsonErrorListener());

		// new JsonArrayRequest(url, null,
		// new JsonListener(pictureFragment), ));
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
			final List<PictureModel> lists = new ArrayList<PictureModel>();
			try {

				for (int i = 0; i < ja.length(); i++) {

					JSONObject newsItemObject = ja.getJSONObject(i);
					pictureModel = new PictureModel();

					pictureModel.setSetId(newsItemObject.getString("setid"));
					pictureModel.setSetName(newsItemObject.getString("setname"));
					System.out.println(pictureModel.getSetId());
					lists.add(pictureModel);
				}

				pictureFragment.updateAdapter(lists);

			} catch (Exception e) {

			}
		}
	}

	static public void getMeiTuContent(String url,
			PictureFragment pictureFragment) {

		// http://c.m.163.comphoto/api/morelist/0096/54GM0096/ 42262.json

		System.out.println(url);
		RequestQueue queue = Volley.newRequestQueue(pictureFragment
				.getActivity());

		JsonObjectRequest joq = new JsonObjectRequest(url, null,
				new JsonObjectListener(pictureFragment),
				new JsonErrorListener());
		queue.add(joq);

	}

	static class JsonObjectListener implements Listener<JSONObject> {
		private PictureFragment pictureFragment;

		public JsonObjectListener(PictureFragment pictureFragment) {
			// TODO Auto-generated constructor stub
			this.pictureFragment = pictureFragment;
		}

		@Override
		public void onResponse(JSONObject jo) {
			// TODO Auto-generated method stub
			PictureModel pictureModel;
			final List<PictureModel> lists = new ArrayList<PictureModel>();
			try {

				JSONArray root = jo.getJSONArray("√¿≈Æ");

				for (int i = 0; i < root.length(); i++) {

					JSONObject newsItemObject = root.getJSONObject(i);
					pictureModel = new PictureModel();

					pictureModel.setDigest(newsItemObject.getString("digest"));

					pictureModel.setDocid(newsItemObject.getString("docid"));
					pictureModel.setDownTimes(newsItemObject
							.getString("downTimes"));
					pictureModel.setImg(newsItemObject.getString("img"));
					pictureModel.setImg(newsItemObject.getString("imgsrc"));
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

		@Override
		public void onErrorResponse(VolleyError error) {

			System.out.println("Json array volley error->" + error);
		}

	}
}
