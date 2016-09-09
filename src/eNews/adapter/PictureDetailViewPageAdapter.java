package eNews.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;

import eNews.app.R;
import eNews.bean.PictureModel;
import eNews.common.BitmapCache;

public class PictureDetailViewPageAdapter extends PagerAdapter {

	ArrayList<String> lists = new ArrayList<String>();
	RequestQueue rq;
	Context context;
	ArrayList<View> arrayList;
	private BitmapCache bitmapCache;

	public PictureDetailViewPageAdapter(Context context) {
		this.context = context;
		arrayList = new ArrayList<View>();
		bitmapCache = BitmapCache.instance();
		rq = Volley.newRequestQueue(context);
	}

	public void appendList(PictureModel model) {
		ArrayList<String> list = model.getPics();
		if (!lists.containsAll(list) && list != null && list.size() > 0) {
			lists.addAll(list);

			if (lists.size() < 1)
				return;

			arrayList = new ArrayList<View>();
			ImageView iv;
			TextView tv;
			LinearLayout linearLayout;

			LayoutParams linearParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			LayoutParams imageParams = new LayoutParams(
					LayoutParams.WRAP_CONTENT, 0);
			imageParams.weight = 1;

			LayoutParams textParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, 0);
			textParams.weight = 1;

			for (int i = 0; i < lists.size(); i++) {

				linearLayout = new LinearLayout(context);
				linearLayout.setLayoutParams(linearParams);
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				iv = new ImageView(context);
				iv.setPadding(10, 5, 10, 5);
				iv.setLayoutParams(imageParams);
				iv.setScaleType(ScaleType.FIT_XY);

				tv = new TextView(context);
				tv.setLayoutParams(textParams);
				tv.setPadding(15, 5, 10, 5);

				String desc = "\t ͼ " + String.valueOf(i + 1) + " \n";
				if (i == 0)
					desc += model.getTitle() + "\n" + model.getDesc();
				else
					desc += model.getTitle();

				tv.setText(desc);

				linearLayout.addView(iv);
				linearLayout.addView(tv);
				arrayList.add(linearLayout);

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
		return lists.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(View top_news, final int position) {
		// TODO Auto-generated method stub

		ImageView iv = (ImageView) ((LinearLayout) arrayList.get(position))
				.getChildAt(0);
		ImageLoader imageLoader = new ImageLoader(rq, bitmapCache);

		ImageListener listener = ImageLoader.getImageListener(iv,
				R.drawable.video_default, R.drawable.video_default);

		imageLoader.get(lists.get(position), listener);

		((ViewPager) top_news).addView(arrayList.get(position));

		return arrayList.get(position);

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

}
