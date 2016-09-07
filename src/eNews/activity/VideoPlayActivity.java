package eNews.activity;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import eNews.app.R;
import eNews.bean.VideoModel;

public class VideoPlayActivity extends Activity {

	private VideoView mVideoView;
	private TextView videoTitle;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.videoview);
		getActionBar().hide();
		videoTitle = (TextView) findViewById(R.id.videoTitle);

		VideoModel videoModel = (VideoModel) getIntent().getSerializableExtra(
				"videoModel");
		videoTitle.setText(videoModel.getTitle());
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		mVideoView.setVideoPath(videoModel.getMp4_url());
		mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
		mVideoView.setMediaController(new MediaController(this));
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (mVideoView != null)
			mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
		super.onConfigurationChanged(newConfig);
	}
}
