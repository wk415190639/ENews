package eNews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import eNews.adapter.VideoNewsActionBarAdapter;
import eNews.adapter.VideoNewsAdapter;
import eNews.app.R;
import eNews.common.GetTypeId;
import eNews.customview.ActionBarView;
import eNews.httpContent.GetVideoNewsContent;

public class VideoNewsActivity extends Activity {

	private ActionBarView actionBarView;
	public VideoNewsAdapter newsAdapter;
	private ListView videoList ;
	
	private VideoNewsActionBarAdapter videoNewsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.video_news);
		videoList =(ListView) findViewById(R.id.videoList);
		actionBarView = (ActionBarView) findViewById(R.id.videoNewsactionBar);
		videoNewsAdapter = new VideoNewsActionBarAdapter(
				getApplicationContext());
		actionBarView.setAdapter(videoNewsAdapter);
		actionBarView.setNumColumns(4);		
		actionBarView.setOnItemClickListener(new VideoItemClick());
		
		newsAdapter =new VideoNewsAdapter(this);
		videoList.setAdapter(newsAdapter );
		
	}

	class VideoItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View tv, int position,
				long id) {
			// TODO Auto-generated method stub

			videoNewsAdapter.setSelectedIndex(position);
			
			String text = ((TextView) tv).getText().toString();

			String typeId = GetTypeId.getTypeId(text+"สำฦต");
			System.out.println(typeId);
			GetVideoNewsContent.getNewsContent(typeId, "0", VideoNewsActivity.this);
		}
	}
	
	public void updateAdapter(finishGetJsonObject update) {
		update.UpdateAdapter();
	}
	public interface finishGetJsonObject {
		public void UpdateAdapter();
	}

}
