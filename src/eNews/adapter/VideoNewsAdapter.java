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

import eNews.activity.VideoPlayActivity;
import eNews.app.R;
import eNews.bean.VideoModel;

public class VideoNewsAdapter extends BaseAdapter {

	List<VideoModel> lists = new ArrayList<VideoModel>();

	List<ImageView> topImageLists = new ArrayList<ImageView>();

	Context context;

	public VideoNewsAdapter(Context context) {

		this.context = context;

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

	@Override
	public View getView(final int position, View video_item, ViewGroup parent) {
		// TODO Auto-generated method stub
		RequestQueue rq = Volley.newRequestQueue(context);
		if (video_item == null)
			video_item = LayoutInflater.from(context).inflate(
					R.layout.videonews_item, null);
		VideoModel videoModel = lists.get(position);
		ImageView videoNewsItemImg = (ImageView) video_item
				.findViewById(R.id.videoNewsItemImg);

		TextView title = (TextView) video_item
				.findViewById(R.id.videoNewsItemTitle);
		title.setText(videoModel.getTitle());
		TextView time = (TextView) video_item
				.findViewById(R.id.videoNewsItemTime);
		time.setText(videoModel.getLength());

		ImageRequest imageRequest = new ImageRequest(videoModel.getTopicImg(),
				new imageRequestListener(videoNewsItemImg), 80, 80,
				Config.ARGB_8888, new imageRequestErrorListener());

		rq.add(imageRequest);

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
			intent.putExtra("videoUrl", videoModel.getMp4_url());
			context.startActivity(intent);

		}

	}

}
