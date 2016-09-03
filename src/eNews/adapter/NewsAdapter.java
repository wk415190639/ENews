package eNews.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;

import eNews.activity.NewsDetailActivity;
import eNews.app.R;
import eNews.bean.NewsModel;
import eNews.common.BitmapCache;
import eNews.common.GetTypeId;

public class NewsAdapter extends BaseAdapter {

	List<NewsModel> lists = new ArrayList<NewsModel>();
	long sumTime;
	Context context;
	private BitmapCache bitmapCache;

	public NewsAdapter(Context context) {

		this.context = context;
		bitmapCache = BitmapCache.instance();

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

	class ViewHolder {
		public ImageView news_item_p1;
		public TextView news_item_title;
		public TextView news_item_digest;

	}

	@Override
	public View getView(final int position, View news_item, ViewGroup parent) {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		NewsModel newsModel = lists.get(position);
		ViewHolder viewHolder = null;
		 ImageView iv = null;

		if (news_item == null) {

			viewHolder = new ViewHolder();
			news_item = LayoutInflater.from(context).inflate(
					R.layout.news_item, null);

			//viewHolder.news_item_p1
			viewHolder.news_item_p1= (ImageView) news_item
					.findViewById(R.id.news_item_p1);

			viewHolder.news_item_title = (TextView) news_item
					.findViewById(R.id.news_item_title);
			viewHolder.news_item_digest = (TextView) news_item
					.findViewById(R.id.news_item_digest);

			viewHolder.news_item_title.setText(newsModel.getTitle());
			viewHolder.news_item_digest.setText(newsModel.getDigest());
			news_item.setTag(viewHolder);
			System.out.println("setTag");

		} else {
			if (GetTypeId.isBvNews(newsModel.getDocid())) {

				
				viewHolder = (ViewHolder) news_item.getTag();
				viewHolder.news_item_title.setText(newsModel.getTitle());
				viewHolder.news_item_digest.setText(newsModel.getDigest());

				RequestQueue rq = Volley.newRequestQueue(context);
				// ImageRequest imageRequest = new ImageRequest(
				// newsModel.getImagesrc(), new imageRequestListener(
				// news_item_p1), 0, 0, Config.ARGB_8888,
				// new imageRequestErrorListener());
				//
				// rq.add(imageRequest);
/*
				ImageLoader imageLoader = new ImageLoader(rq, bitmapCache);

				ImageListener listener = ImageLoader.getImageListener(
						iv, R.drawable.p1, R.drawable.p2);

				imageLoader.get(newsModel.getImagesrc(), listener);
*/
			}
		}

		long endTime = System.nanoTime();
		// ºÄÊ±
		long spendTime = (endTime - startTime);

		sumTime += spendTime;
		System.out.println("position at:" + position + "--sumTime:"
				+ String.valueOf(sumTime));
		news_item.setOnClickListener(new newsItemOnclick(newsModel));
		return news_item;
	}

	class imageRequestListener implements Listener<Bitmap> {

		private ImageView iv;

		public imageRequestListener(ImageView iv) {

			this.iv = iv;
		}

		@Override
		public void onResponse(Bitmap bitmap) {
			// TODO Auto-generated method stub

			iv.setImageBitmap(bitmap);

		}

	}

	class imageRequestErrorListener implements ErrorListener {

		@Override
		public void onErrorResponse(VolleyError error) {

			System.out.println(error.toString());
		}

	}

	class newsItemOnclick implements OnClickListener {

		private NewsModel model;

		public newsItemOnclick(NewsModel model) {
			// TODO Auto-generated constructor stub

			this.model = model;
		}

		@Override
		public void onClick(View v) {

			Intent intent = new Intent(context, NewsDetailActivity.class);
			intent.putExtra("postId", model.getPostid());
			context.startActivity(intent);
		}

	}

}
