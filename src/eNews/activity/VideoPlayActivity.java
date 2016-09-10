package eNews.activity;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import java.util.ArrayList;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import eNews.app.R;
import eNews.bean.VideoModel;
import eNews.customview.MorePopupWindow;
import eNews.thirdParty.AppConstant;
import eNews.thirdParty.TencentThirdParty;

public class VideoPlayActivity extends Activity implements OnClickListener {
	private ImageButton backBtn;
	private VideoView mVideoView;
	private TextView videoTitle;
	private MorePopupWindow morePopupWindow;
	private ImageButton actionbar_more;
	VideoModel videoModel;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.videoview);
		getActionBar().hide();
		int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
		// 获得当前窗体对象
		Window window = getWindow();
		// 设置当前窗体为全屏显示
		window.setFlags(flag, flag);
		morePopupWindow = new MorePopupWindow(this);
		actionbar_more = (ImageButton) findViewById(R.id.actionbar_more);
		actionbar_more.setOnClickListener(this);
		backBtn = (ImageButton) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
			}
		});

		videoTitle = (TextView) findViewById(R.id.videoTitle);
		videoModel = (VideoModel) getIntent()
				.getSerializableExtra("videoModel");
		videoTitle.setText(videoModel.getTitle());
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		mVideoView.setVideoPath(videoModel.getMp4_url());
		mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
		mVideoView.setMediaController(new MediaController(this));

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// if (mVideoView != null)
		// mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.shareLy:
			System.out.println("点击了shareLy");
			if (morePopupWindow.isShowing()) {

				if (videoModel != null) {

					ArrayList<String> arrayList = new ArrayList<String>();
					arrayList.add(AppConstant.logoUrl);

					TencentThirdParty.getInstance(this).shareToQzone(this,
							videoModel.getTitle(), videoModel.getTitle(),
							videoModel.getMp4_url(), arrayList);
				}

				morePopupWindow.dismiss();
			}
			break;

		case R.id.collectLy:
			System.out.println("点击了collectLy");
			if (morePopupWindow.isShowing()) {
				morePopupWindow.dismiss();
			}
			break;
		case R.id.actionbar_more:
			System.out.println("点击了actionbar_more");
			if (!morePopupWindow.isShowing()) {
				morePopupWindow.showAsDropDown(actionbar_more);
				System.out.println("popupwindow");
			}
			break;
		}

	}
}
