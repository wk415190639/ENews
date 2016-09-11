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

import eNews.activity.MeiTuDetailActivity;
import eNews.activity.PictureDetailActivity;
import eNews.app.R;
import eNews.bean.PictureModel;
import eNews.common.BitmapCache;

public class PictureNewsAdapter extends BaseAdapter {

	List<PictureModel> lists = new ArrayList<PictureModel>();

	List<ImageView> topImageLists = new ArrayList<ImageView>();

	private String selectTag;
	Context context;
	private BitmapCache bitmapCache;
	RequestQueue rq;

	public PictureNewsAdapter(Context context) {

		this.context = context;
		selectTag = "¾«Æ·";
		bitmapCache = BitmapCache.instance();
		rq = Volley.newRequestQueue(context.getApplicationContext());

	}

	public void appendList(List<PictureModel> list, String selectTag) {
		this.selectTag = selectTag;
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

	static class ViewHolder {
		private ImageView pictureImage;
		private TextView title;
		private TextView replayNum;

	}

	@Override
	public View getView(final int position, View picture_item, ViewGroup parent) {
		// TODO Auto-generated method stub

		final PictureModel pictureModel = lists.get(position);
		ViewHolder holder = null;

		if (picture_item == null) {
			picture_item = LayoutInflater.from(context).inflate(
					R.layout.picturenews_item, null);
			holder = new ViewHolder();
			holder.pictureImage = (ImageView) picture_item
					.findViewById(R.id.pictureImage);

			holder.title = (TextView) picture_item
					.findViewById(R.id.pictureTitle);

			holder.replayNum = (TextView) picture_item
					.findViewById(R.id.pictureReplay);
			picture_item.setTag(holder);

		} else {

			holder = (ViewHolder) picture_item.getTag();

		}

		holder.title.setText(pictureModel.getTitle());
		holder.replayNum.setText(pictureModel.getReplyid());

		ImageLoader imageLoader = new ImageLoader(rq, bitmapCache);

		ImageListener listener = ImageLoader.getImageListener(
				holder.pictureImage, R.drawable.picture_default,
				R.drawable.picture_default);

		imageLoader.get(pictureModel.getImgsrc(), listener);

		if (!selectTag.equals("ÃÀÍ¼"))
			picture_item.setOnClickListener(new newsItemOnclick(pictureModel,
					PictureDetailActivity.class));
		else
			picture_item.setOnClickListener(new newsItemOnclick(pictureModel,
					MeiTuDetailActivity.class));

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
		private Class<?> cls;

		public newsItemOnclick(PictureModel pictureModel, Class<?> cls) {
			// TODO Auto-generated constructor stub

			this.pictureModel = pictureModel;
			this.cls = cls;
		}

		@Override
		public void onClick(View v) {

			Intent intent = new Intent(context, cls);
			intent.putExtra("picModel", pictureModel);
			context.startActivity(intent);

		}

	}

}
