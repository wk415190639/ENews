package eNews.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
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
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import eNews.activity.PictureDetailActivity;
import eNews.app.R;
import eNews.bean.PictureModel;

public class PictureNewsAdapter extends BaseAdapter {

	List<PictureModel> lists = new ArrayList<PictureModel>();

	List<ImageView> topImageLists = new ArrayList<ImageView>();

	Context context;

	public PictureNewsAdapter(Context context) {

		this.context = context;

	}

	public void appendList(List<PictureModel> list) {
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
	public View getView(final int position, View picture_item, ViewGroup parent) {
		// TODO Auto-generated method stub
		RequestQueue rq = Volley.newRequestQueue(context);
		if (picture_item == null)
			picture_item = LayoutInflater.from(context).inflate(
					R.layout.picturenews_item, null);

		final PictureModel pictureModel = lists.get(position);

		final ImageView pictureImage = (ImageView) picture_item
				.findViewById(R.id.pictureImage);

		TextView title = (TextView) picture_item
				.findViewById(R.id.pictureTitle);
		title.setText(pictureModel.getTitle());

		ImageRequest imageRequest = new ImageRequest(pictureModel.getKpic(),
				new imageRequestListener(pictureImage), 80, 80,
				Config.ARGB_8888, new imageRequestErrorListener());

		rq.add(imageRequest);

		picture_item.setOnClickListener(new newsItemOnclick(pictureModel));

		return picture_item;
	}

	class imageRequestListener implements Listener<Bitmap> {

		private ImageView iv;

		public imageRequestListener(ImageView iv) {

			this.iv = iv;
		}

		@Override
		public void onResponse(Bitmap bitmap) {

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

		private PictureModel pictureModel;

		public newsItemOnclick(PictureModel pictureModel) {
			// TODO Auto-generated constructor stub

			this.pictureModel = pictureModel;
		}

		@Override
		public void onClick(View v) {

			Intent intent = new Intent(context, PictureDetailActivity.class);
			intent.putExtra("kpic", pictureModel.getId());
			context.startActivity(intent);

		}

	}

}
