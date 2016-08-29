package eNews.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import eNews.app.R;
import eNews.bean.NewsModel;

public class NewsAdapter extends BaseAdapter {

	List<NewsModel> lists = new ArrayList<NewsModel>();

	Context context;

	public NewsAdapter(Context context) {

		this.context = context;

	}

	public void appendList(List<NewsModel> list) {
		if (!lists.containsAll(list) && list != null && list.size() > 0) {
			lists.addAll(list);

		}
		notifyDataSetChanged();

	}

	public void clear() {
		lists.clear();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View news_item, ViewGroup parent) {
		// TODO Auto-generated method stub
		RequestQueue rq = Volley.newRequestQueue(context);
		if (news_item == null)
			news_item = LayoutInflater.from(context).inflate(
					R.layout.news_item, null);

		final ImageView news_item_p1 = (ImageView) news_item
				.findViewById(R.id.news_item_p1);

		TextView news_item_title = (TextView) news_item
				.findViewById(R.id.news_item_title);
		TextView news_item_digest = (TextView) news_item
				.findViewById(R.id.news_item_digest);
		NewsModel newsModel = lists.get(position);

		news_item_title.setText(newsModel.getTitle());
		news_item_digest.setText(newsModel.getDigest());

		ImageRequest imageRequest = new ImageRequest(newsModel.getImagesrc(),
				new Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap bitmap) {
						// TODO Auto-generated method stub
						news_item_p1.setImageBitmap(bitmap);
						// System.out.println("set image " + position);
					}

				}, 80, 80, Config.ARGB_8888, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub

					}
				});

		rq.add(imageRequest);
		return news_item;
	}
}
