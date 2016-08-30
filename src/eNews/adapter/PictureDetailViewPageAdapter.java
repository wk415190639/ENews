package eNews.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
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
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import eNews.bean.PictureDetailModel;

public class PictureDetailViewPageAdapter extends PagerAdapter {

	List<PictureDetailModel> lists = new ArrayList<PictureDetailModel>();

	Context context;
	ArrayList<View> arrayList;

	public PictureDetailViewPageAdapter(Context context) {
		this.context = context;
		arrayList = new ArrayList<View>();
	}

	public void appendList(List<PictureDetailModel> list) {
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
				tv.setText(lists.get(i).getAlt());
				tv.setPadding(15, 5, 10, 5);

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

		RequestQueue rq = Volley.newRequestQueue(context);
		ImageRequest ir = new ImageRequest(lists.get(position).getKpic(),
				new ImageRequestListener(position), 400, 300, Config.ARGB_8888,
				new ErrorListener() {

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

	class ImageRequestListener implements Listener<Bitmap> {

		private int position;

		public ImageRequestListener(int position) {
			// TODO Auto-generated constructor stub
			this.position = position;
		}

		@Override
		public void onResponse(Bitmap bitmap) {

			ImageView iv = (ImageView) ((LinearLayout) arrayList.get(position))
					.getChildAt(0);
			TextView tv = (TextView) ((LinearLayout) arrayList.get(position))
					.getChildAt(1);

			iv.setImageBitmap(bitmap);
			tv.setText(lists.get(position).getAlt());
			System.out.println(lists.get(position).getAlt());

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
