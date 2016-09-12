package eNews.activity;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import eNews.app.R;
import eNews.bean.CollectModel;
import eNews.bean.VideoModel;
import eNews.common.DataBaseHelper;
import eNews.customview.MorePopupWindow;
import eNews.dao.CollectManage;
import eNews.thirdParty.AppConstant;
import eNews.thirdParty.TencentThirdParty;

/**
 * 
 * @author ����
 * @date 2016-9-12 ��Ƶ����Activity
 */
public class VideoPlayActivity extends Activity implements OnClickListener,
		CollectNewsInterface {
	private ImageButton backBtn;
	private VideoView mVideoView;
	private TextView videoTitle;
	private MorePopupWindow morePopupWindow;
	private ImageButton actionbar_more;
	VideoModel videoModel;
	private String openId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setOpenId(TencentThirdParty.getInstance(getApplicationContext())
				.getOpenId());
		if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.videoview);
		getActionBar().hide();
		int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
		// ��õ�ǰ�������
		Window window = getWindow();
		// ���õ�ǰ����Ϊȫ����ʾ
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
			System.out.println("�����shareLy");
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
		case R.id.shareLyqq:

			System.out.println("�����shareLyQQ");
			if (morePopupWindow.isShowing()) {

				if (videoModel != null) {

					TencentThirdParty.getInstance(this).shareToQQ(this,
							videoModel.getTitle(), videoModel.getTitle(),
							videoModel.getMp4_url(), AppConstant.logoUrl);
				}

				morePopupWindow.dismiss();
			}

			break;

		case R.id.collectLy:
			System.out.println("�����collectLy");
			collectNews();
			break;
		case R.id.actionbar_more:
			System.out.println("�����actionbar_more");
			if (!morePopupWindow.isShowing()) {
				morePopupWindow.showAsDropDown(actionbar_more);
				System.out.println("popupwindow");
			}
			break;
		}

	}

	private void collectNews() {
		if (morePopupWindow.isShowing()) {
			morePopupWindow.dismiss();

			if (TencentThirdParty.getInstance(getApplicationContext())
					.checkIsLogged()) {

				collect();

			} else {
				new AlertDialog.Builder(this).setTitle("���ȵ�¼")
						.setPositiveButton("��¼", new NegativeButtonListener())
						.setNegativeButton("�ٵȵ�", new PositiveButtonListener())
						.setIcon(android.R.drawable.ic_dialog_info).show();
			}

		}
	}

	// ȡ��
	public class PositiveButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

		}
	}

	// ��¼
	public class NegativeButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

			TencentThirdParty.getInstance(getApplicationContext()).userLogin(
					VideoPlayActivity.this);

		}

	}

	@Override
	public void collectNewsAfterLogin(String openId) {
		// TODO Auto-generated method stub

		setOpenId(openId);
		collect();

	}

	private void collect() {
		CollectManage manage = CollectManage.getInstance(this);
		CollectModel collectModel = new CollectModel();

		collectModel.setUserId(getOpenId());
		collectModel.setDesc(videoModel.getTitle());
		collectModel.setTitle(videoModel.getTitle());
		collectModel.setType(DataBaseHelper.VIDEO);
		collectModel.setImgurl(videoModel.getTopicImg());
		collectModel.setUrl(videoModel.getMp4_url());
		manage.insertCollect(collectModel);

		Toast.makeText(getApplicationContext(), "�ղسɹ�!!!", Toast.LENGTH_LONG)
				.show();

	}

}
