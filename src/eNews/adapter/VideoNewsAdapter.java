package eNews.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import eNews.activity.VideoPlayActivity;
import eNews.app.R;
import eNews.bean.VideoModel;
import eNews.common.BitmapCache;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 视频新闻适配器
 */
public class VideoNewsAdapter extends BaseAdapter {

	List<VideoModel> lists = new ArrayList<VideoModel>();
	RequestQueue rq;
	List<ImageView> topImageLists = new ArrayList<ImageView>();
	Context context;

	private BitmapCache bitmapCache;

	public VideoNewsAdapter(Context context) {

		this.context = context;
		bitmapCache = BitmapCache.instance();
		rq = Volley.newRequestQueue(context.getApplicationContext());

	}

	public void appendList(List<VideoModel> list) {
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
		public ImageView videoNewsItemImg;
		public TextView videoNewsItemTime;
		public TextView videoNewsItemTitle;

	}

	@Override
	public View getView(final int position, View video_item, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder viewHolder = null;
		VideoModel videoModel = lists.get(position);
		if (video_item == null) {
			video_item = LayoutInflater.from(context).inflate(
					R.layout.videonews_item, null);
			viewHolder = new ViewHolder();

			viewHolder.videoNewsItemImg = (ImageView) video_item
					.findViewById(R.id.videoNewsItemImg);
			viewHolder.videoNewsItemTime = (TextView) video_item
					.findViewById(R.id.videoNewsItemTime);
			viewHolder.videoNewsItemTitle = (TextView) video_item
					.findViewById(R.id.videoNewsItemTitle);
			video_item.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) video_item.getTag();
		}
		viewHolder.videoNewsItemTitle.setText(videoModel.getTitle());

		SimpleDateFormat format = new SimpleDateFormat("mm:ss", Locale.US);
		String times = format.format(new Date(Integer.valueOf(videoModel
				.getLength()) * 1000L));
		System.out.print("日期格式---->" + times);
		viewHolder.videoNewsItemTime.setText(times);
		ImageLoader imageLoader = new ImageLoader(rq, bitmapCache);

		ImageListener listener = ImageLoader.getImageListener(
				viewHolder.videoNewsItemImg, R.drawable.video_default,
				R.drawable.video_default);

		imageLoader.get(videoModel.getTopicImg(), listener);

		video_item.setOnClickListener(new newsItemOnclick(videoModel));

		return video_item;
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

		private VideoModel videoModel;

		public newsItemOnclick(VideoModel videoModel) {
			// TODO Auto-generated constructor stub

			this.videoModel = videoModel;
		}

		@Override
		public void onClick(View v) {

			Intent intent = new Intent(context, VideoPlayActivity.class);
			intent.putExtra("videoModel", videoModel);
			context.startActivity(intent);

		}

	}

}
