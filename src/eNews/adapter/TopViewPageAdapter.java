package eNews.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
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

public class TopViewPageAdapter extends PagerAdapter {

	List<NewsModel> lists = new ArrayList<NewsModel>();

	private int [] masks = {R.drawable.mask1,R.drawable.mask2,R.drawable.mask3,R.drawable.mask4};
	Context context;
	ArrayList<View> arrayList;

	private BitmapCache bitmapCache;
	RequestQueue rq;

	public TopViewPageAdapter(Context context) {
		this.context = context;
		arrayList = new ArrayList<View>();
		bitmapCache = BitmapCache.instance();
		rq = Volley.newRequestQueue(context);
	}

	public void appendList(List<NewsModel> list) {
		if (!lists.containsAll(list) && list != null && list.size() > 0) {
			lists.addAll(list);
			// System.out.println("Size ->" + lists.size() + "");

			if (lists.size() < 4)
				return;

			arrayList = new ArrayList<View>();
			ImageView iv;
			TextView tv;
			RelativeLayout relativeLayout;

			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);

			LayoutParams textParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			textParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
					RelativeLayout.TRUE);

			for (int i = 0; i < 4; i++) {

				relativeLayout = new RelativeLayout(context);
				relativeLayout.setClickable(true);

				relativeLayout.setLayoutParams(params);
				iv = new ImageView(context);
				iv.setLayoutParams(params);
				iv.setScaleType(ScaleType.CENTER_CROP);

				ImageView shadowIv = new ImageView(context);
				shadowIv.setLayoutParams(params);
				shadowIv.setScaleType(ScaleType.CENTER_CROP);
				shadowIv.setImageResource(masks[i]);

				tv = new TextView(context);
				tv.setText(lists.get(i).getTitle());
				tv.setLayoutParams(textParams);
				tv.setTextColor(Color.WHITE);
//				tv.setTextSize(15);
				tv.setSingleLine(true);
				tv.setPadding(15, 0, 10, 5);

				relativeLayout.addView(iv);
				relativeLayout.addView(tv);
				relativeLayout.addView(shadowIv);
				arrayList.add(relativeLayout);

			}

		}
		notifyDataSetChanged();

	}

	public void clear() {
		lists.clear();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size() > 4 ? 4 : 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(View top_news, final int position) {
		// TODO Auto-generated method stub

		RelativeLayout relativeLayout = (RelativeLayout) arrayList
				.get(position);
		ImageView iv = (ImageView) relativeLayout.getChildAt(0);

		ImageLoader imageLoader = new ImageLoader(rq, bitmapCache);

		ImageListener listener = ImageLoader.getImageListener(iv,
				R.drawable.video_default, R.drawable.video_default);

		imageLoader.get(lists.get(position).getImagesrc(), listener);
		System.out.println(lists.get(position).getImagesrc()
				+ "---------------");

		relativeLayout.setOnClickListener(new newsItemOnclick(lists
				.get(position)));

		((ViewPager) top_news).addView(arrayList.get(position));

		return arrayList.get(position);

	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView(arrayList.get(position));
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
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
			intent.putExtra("postId", model.getDocid());
			context.startActivity(intent);
		}

	}

	// class newsItemOnclick implements OnClickListener {
	//
	// private VideoModel videoModel;
	//
	// public newsItemOnclick(VideoModel videoModel) {
	// // TODO Auto-generated constructor stub
	//
	// this.videoModel = videoModel;
	// }
	//
	// @Override
	// public void onClick(View v) {
	//
	// Intent intent = new Intent(context, VideoPlayActivity.class);
	// intent.putExtra("videoUrl", videoModel.getMp4_url());
	// context.startActivity(intent);
	//
	// }
	//
	// }

}
