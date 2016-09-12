package eNews.adapter;

import io.vov.vitamio.utils.CPU;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import eNews.activity.MeiTuDetailActivity;
import eNews.activity.NewsDetailActivity;
import eNews.activity.PictureDetailActivity;
import eNews.activity.VideoPlayActivity;
import eNews.app.R;
import eNews.bean.CollectModel;
import eNews.bean.NewsModel;
import eNews.bean.PictureModel;
import eNews.bean.VideoModel;
import eNews.common.BitmapCache;
import eNews.common.DataBaseHelper;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CollectAdapter extends BaseAdapter {

	List<CollectModel> lists = new ArrayList<CollectModel>();
	Context context;
	RequestQueue rq;
	private BitmapCache bitmapCache;
	CollectModel collectModel;

	public CollectAdapter(Context context) {

		this.context = context;
		bitmapCache = BitmapCache.instance();
		rq = Volley.newRequestQueue(context.getApplicationContext());

	}

	public void appendList(List<CollectModel> list) {
		if (!lists.containsAll(list) && list != null && list.size() > 0) {
			lists.addAll(list);

		}
		notifyDataSetChanged();

	}

	public void clear() {
		System.gc();
		lists.clear();
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	static class ViewHolder {
		public ImageView collect_item_p1;
		public TextView collect_item_title;
		public TextView collect_item_digest;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		collectModel = lists.get(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.collect_item, null);
			viewHolder = new ViewHolder();
			viewHolder.collect_item_p1 = (ImageView) convertView
					.findViewById(R.id.collect_item_p1);
			viewHolder.collect_item_title = (TextView) convertView
					.findViewById(R.id.collect_item_title);
			viewHolder.collect_item_digest = (TextView) convertView
					.findViewById(R.id.collect_item_digest);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();

		}

		viewHolder.collect_item_digest.setText(collectModel.getDesc());
		viewHolder.collect_item_title.setText(collectModel.getTitle());

		ImageLoader imageLoader = new ImageLoader(rq, bitmapCache);

		ImageListener listener = ImageLoader.getImageListener(
				viewHolder.collect_item_p1, R.drawable.picture_default,
				R.drawable.picture_default);

		imageLoader.get(collectModel.getImgurl(), listener);

		switch (collectModel.getType()) {

		case DataBaseHelper.TEXT:
			NewsModel textModel = new NewsModel();

			textModel.setTitle(collectModel.getTitle());
			textModel.setDigest(collectModel.getDesc());
			textModel.setDocid(collectModel.getId());

			convertView.setOnClickListener(new newsTextItemOnclick(textModel));

			break;

		case DataBaseHelper.VIDEO:

			VideoModel videoModel = new VideoModel();
			videoModel.setMp4_url(collectModel.getUrl());
			videoModel.setTitle(collectModel.getTitle());
			convertView
					.setOnClickListener(new newsVideoItemOnclick(videoModel));
			break;

		case DataBaseHelper.PICTURE:

			PictureModel pictureModel = new PictureModel();
			pictureModel.setTitle(collectModel.getTitle());
			pictureModel.setDesc(collectModel.getDesc());
			pictureModel.setDesc(collectModel.getUrl());
			pictureModel.setPics(collectModel.getPics());

			convertView.setOnClickListener(new newsPictureItemOnclick(
					pictureModel, PictureDetailActivity.class));

			break;

		case DataBaseHelper.MEITU:

			PictureModel meituModel = new PictureModel();
			meituModel.setTitle(collectModel.getTitle());
			meituModel.setDesc(collectModel.getDesc());
			meituModel.setDesc(collectModel.getUrl());
			meituModel.setDocid(collectModel.getId());
			convertView.setOnClickListener(new newsPictureItemOnclick(
					meituModel, MeiTuDetailActivity.class));

			break;
		}

		collectModel = null;
		return convertView;
	}

	class newsVideoItemOnclick implements OnClickListener {

		private VideoModel videoModel;

		public newsVideoItemOnclick(VideoModel videoModel) {
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

	class newsTextItemOnclick implements OnClickListener {

		private NewsModel model;

		public newsTextItemOnclick(NewsModel model) {
			// TODO Auto-generated constructor stub

			this.model = model;
		}

		@Override
		public void onClick(View v) {

			Intent intent = new Intent(context, NewsDetailActivity.class);
			intent.putExtra("model", model);
			context.startActivity(intent);
		}

	}

	class newsPictureItemOnclick implements OnClickListener {

		private PictureModel pictureModel;
		private Class<?> cls;

		public newsPictureItemOnclick(PictureModel pictureModel, Class<?> cls) {
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
