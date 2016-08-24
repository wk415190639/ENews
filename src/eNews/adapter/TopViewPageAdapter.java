package eNews.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import eNews.bean.NewsModel;

public class TopViewPageAdapter extends PagerAdapter {

	List<NewsModel> lists = new ArrayList<NewsModel>();

	Context context;
	ArrayList<View> arrayList = new ArrayList<View>();

	public TopViewPageAdapter(Context context) {
		this.context = context;
	}

	public void clear() {
	
		
		notifyDataSetChanged();
	}

	public void appendList(List<NewsModel> list) {
		if (!lists.containsAll(list) && list != null && list.size() > 0) {
			lists.addAll(list);
			System.out.println("Size ->" + lists.size() + "");

			ImageView iv;
			TextView tv;
			RelativeLayout relativeLayout;

			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);

			LayoutParams textParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, 40);
			textParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
					RelativeLayout.TRUE);

			for (int i = 0; i < 4; i++) {

				relativeLayout = new RelativeLayout(context);

				relativeLayout.setLayoutParams(params);
				iv = new ImageView(context);
				iv.setBackgroundColor(Color.BLACK);
				iv.setPadding(10, 5, 10, 5);
				iv.setLayoutParams(params);
				iv.setScaleType(ScaleType.FIT_XY);

				tv = new TextView(context);
				tv.setText(lists.get(i).getTitle());
				tv.setLayoutParams(textParams);
				tv.setSingleLine(true);
				tv.setBackgroundColor(Color.argb(100, 0, 0, 100));
				tv.setPadding(15, 5, 10, 5);

				relativeLayout.addView(iv);
				relativeLayout.addView(tv);
				arrayList.add(relativeLayout);

				System.out.println(i + "-->top");

			}

		}
		notifyDataSetChanged();

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(View top_news, final int position) {
		// TODO Auto-generated method stub
		RequestQueue rq = Volley.newRequestQueue(context);
		System.out.println(lists.get(position).getImagesrc());
		ImageRequest ir = new ImageRequest(lists.get(position).getImagesrc(),
				new Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap bitmap) {
						// TODO Auto-generated method stub

						RelativeLayout relativeLayout = (RelativeLayout) arrayList
								.get(position);
						ImageView iv = (ImageView) relativeLayout.getChildAt(0);
						iv.setImageBitmap(bitmap);

					}
				}, 400, 300, Config.ARGB_8888, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub

						System.out.println("erro ErrorListener "
								+ arg0.toString());

					}
				});
		rq.add(ir);

		((ViewPager) top_news).addView(arrayList.get(position));

		return arrayList.get(position);

	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView(arrayList.get(position));
	}

}
