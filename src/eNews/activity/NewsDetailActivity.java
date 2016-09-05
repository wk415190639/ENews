package eNews.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import eNews.app.R;
import eNews.bean.NewsDetailModel;
import eNews.httpContent.GetNewsDetailContent;

public class NewsDetailActivity extends Activity {

	private TextView newsDetailText;
	private String postId;
	private NewsDetailModel newsDetailModel;
	private ArrayList<Drawable> arrayDrawable;

	String strList[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.news_detail);
		init();

	}

	private void init() {

		newsDetailText = (TextView) findViewById(R.id.news_detail_text);
		Intent intent = getIntent();
		postId = intent.getStringExtra("postId");
		arrayDrawable = new ArrayList<Drawable>();
		GetNewsDetailContent.getNewsContent(postId, this);

	}

	public void setContent(NewsDetailModel newsDetailModel) {
		this.newsDetailModel = newsDetailModel;
		strList = newsDetailModel.getBody().split("<!--IMG#\\d*-->");
	//	System.out.println("setContent");
		getDetailImages();
	}

	private void getDetailImages() {

		RequestQueue jr = Volley.newRequestQueue(getApplicationContext());
		//System.out.println("getDetailImages");
		
		if(newsDetailModel.getImg().size()==0)
		{
			setNewsDetailText();
		}
		else
		for (int i = 0; i < newsDetailModel.getImg().size(); i++) {

			System.out.println(newsDetailModel.getImg().get(i));
			ImageRequest ir = new ImageRequest(newsDetailModel.getImg().get(i),
					new ImageRequestListener(i), 300, 300, Config.ARGB_8888,
					new ImageRequestError());

			jr.add(ir);
		}

	}

	private void setNewsDetailText() {

		System.out.println(strList.length+"<----->"+arrayDrawable.size());
		for (int i = 0; i < strList.length; i++) {
			if (i < strList.length - 1)
				newsDetailText.append(Html.fromHtml(strList[i] + "<img src="
						+ String.valueOf(i) + "></img><br>",
						new MyImageGetter(), null));
			else
				newsDetailText.append(Html.fromHtml(strList[i]));

		}
	}

	class MyImageGetter implements ImageGetter {

		@Override
		public Drawable getDrawable(String position) {
			// TODO Auto-generated method stub

		//	System.out.println("position->"+position);
			return arrayDrawable.get(Integer.valueOf(position));
		}

	}

	public class ImageRequestListener implements Listener<Bitmap> {

		private int position;

		public ImageRequestListener(int position) {

			this.position = position;
		}

		@Override
		public void onResponse(Bitmap bitmap) {
			// TODO Auto-generated method stub
			Drawable drawable = new BitmapDrawable(getResources(), bitmap);

			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());

			arrayDrawable.add(drawable);

			if (arrayDrawable.size() == newsDetailModel.getImg().size())
			{
				System.out.println("�������� ->arrayDrawable ->"+arrayDrawable.size()+" getImg().size()-1->"+(newsDetailModel.getImg().size()-1));
				setNewsDetailText();
			}

		}

	}

	class ImageRequestError implements ErrorListener {

		@Override
		public void onErrorResponse(VolleyError arg0) {
			// TODO Auto-generated method stub

			System.out.println("ErrorListener "+arg0.toString());
		}

	}

}
