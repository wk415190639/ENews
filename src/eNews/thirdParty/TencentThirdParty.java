package eNews.thirdParty;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import eNews.activity.MainWindows;
import eNews.activity.MeiTuDetailActivity;
import eNews.activity.NewsDetailActivity;
import eNews.activity.PictureDetailActivity;
import eNews.activity.VideoPlayActivity;

public class TencentThirdParty {

	private static TencentThirdParty instance;
	private static Tencent mTencent;
	private Bitmap userLogo;
	private String userName;
	public LoginIUListener loginIUListener;
	private boolean isLogin;
	private UserInfo mInfo;
	private Context context;
	private String openId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Bitmap getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(Bitmap userLogo) {
		this.userLogo = userLogo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private TencentThirdParty(Context context) {
		this.context = context;
		mTencent = Tencent.createInstance(AppConstant.appId, context);

	}

	public static TencentThirdParty getInstance(Context context) {

		if (instance == null)
			instance = new TencentThirdParty(context);

		return instance;
	}

	public Tencent getTencentInstance() {

		return mTencent;
	}

	public void shareToQzone(Activity activity, final String title,
			final String summary, final String targetUrl,
			final ArrayList<String> imageUrlList/* , IUiListener shareUiListener */) {

		Bundle params = new Bundle();
		// 分享类型
		params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE,
				QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);// 必填

		params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "新闻e眼客户端(第三方分享测试)->"
				+ title);// 必填

		params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, targetUrl);// 必填

		params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, summary); // 选填

		params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL,
				imageUrlList); // 选填

		mTencent.shareToQzone(activity, params, new ShareUiListener());
		System.out.println("分享了");

	}

	public void shareToQQ(Activity activity, String title, String summary,
			String targetUrl, String imgUrl) {
		final Bundle params = new Bundle();
		params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,
				QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
		params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
		params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
		params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
		params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrl);
		params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "新闻e眼");
		// params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,
		// QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
		mTencent.shareToQQ(activity, params, new ShareUiListener());
	}

	public void userLogout() {
		mTencent.logout(this.context);

		setUserLogo(null);
		setUserName("未登录");
		MainWindows.setUserName(getUserName());
		MainWindows.setUserImgBtn(getUserLogo());

	}

	public void userLogin(Activity activity) {

		loginIUListener = new LoginIUListener(activity);
		if (!isLogin) {
			if (!mTencent.isSessionValid()) {
				mTencent.login(activity, "all", loginIUListener);

			} else {

				mTencent.logout(this.context);
				mTencent.login(activity, "all", loginIUListener);

			}
		} else {
			System.out.println("已登录.....");

			new AlertDialog.Builder(activity).setTitle("已经登录,是否注销")
					.setPositiveButton("注销", new NegativeButtonListener())
					.setNegativeButton("取消", new PositiveButtonListener())
					.setIcon(android.R.drawable.ic_dialog_info).show();

		}
	}

	class ShareUiListener implements IUiListener {

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			System.out.println("-------------onCancel");
		}

		@Override
		public void onComplete(Object arg0) {
			// TODO Auto-generated method stub
			System.out.println("------------------onComplete");

		}

		@Override
		public void onError(UiError error) {

			System.out.println("-----error >" + error.errorCode + " > "
					+ error.errorDetail + " > " + error.errorMessage);

		}

	}

	class LoginIUListener implements IUiListener {

		private Activity activity;

		public LoginIUListener(Activity activity) {
			// TODO Auto-generated constructor stub
			this.activity = activity;
		}

		@Override
		public void onCancel() {

			System.out.println("--------OnCancel");

		}

		@Override
		public void onComplete(Object respone) {
			// // TODO Auto-generated method stub
			isLogin = true;

			JSONObject root = (JSONObject) respone;

			try {

				String openId = root.getString(Constants.PARAM_OPEN_ID);
				setOpenId(openId);

				String activityClass = activity.getClass().getSimpleName();
				if (activityClass.equals(NewsDetailActivity.class
						.getSimpleName())) {

					System.out.println("-----NewsDetailActivity");
					NewsDetailActivity detailActivity = (NewsDetailActivity) activity;
					detailActivity.setOpenId(getOpenId());
					detailActivity.collectNewsAfterLogin(openId);

				} else if (activityClass.equals(PictureDetailActivity.class
						.getSimpleName())) {

					System.out.println("-----PictureDetailActivity");
					PictureDetailActivity detailActivity = (PictureDetailActivity) activity;
					detailActivity.setOpenId(getOpenId());
					detailActivity.collectNewsAfterLogin(openId);

				} else if (activityClass.equals(MeiTuDetailActivity.class
						.getSimpleName())) {

					System.out.println("-----MeiTuDetailActivity");
					MeiTuDetailActivity detailActivity = (MeiTuDetailActivity) activity;
					detailActivity.setOpenId(getOpenId());
					detailActivity.collectNewsAfterLogin(openId);

				} else if (activityClass.equals(VideoPlayActivity.class
						.getSimpleName())) {

					System.out.println("-----VideoPlayActivity");
					VideoPlayActivity detailActivity = (VideoPlayActivity) activity;
					detailActivity.setOpenId(getOpenId());
					detailActivity.collectNewsAfterLogin(openId);

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			initOpenidAndToken(root);
			updateUserInfo(context);

		}

		@Override
		public void onError(UiError arg0) {

			System.out.println("-----------onError");
		}

	}

	private void initOpenidAndToken(JSONObject jsonObject) {
		try {
			String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
			String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
			String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);

			if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
					&& !TextUtils.isEmpty(openId)) {
				mTencent.setAccessToken(token, expires);
				mTencent.setOpenId(openId);
			}
		} catch (Exception e) {
		}

	}

	private void updateUserInfo(Context context) {

		if (mTencent != null && mTencent.isSessionValid()) {

			mInfo = new UserInfo(context, mTencent.getQQToken());
			mInfo.getUserInfo(new UserInfoIUiListener());

		}

	}

	public static Bitmap makeRoundCorner(Bitmap bitmap, int px) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Bitmap output = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		int color = 0xff424242;
		Paint paint = new Paint();
		Rect rect = new Rect(0, 0, width, height);
		RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, px, px, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	// 取消
	public class PositiveButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

		}
	}

	// 注销按钮监听
	public class NegativeButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			userLogout();
			isLogin = false;
		}

	}

	// 检查是否已登录
	public boolean checkIsLogged() {

		return isLogin;
	}

	// 收藏时询问是否登录
	public class NegativeLoginBtnListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

		}

	}

	class UserInfoIUiListener implements IUiListener {

		@Override
		public void onError(UiError e) {
			System.out.println(e.errorMessage);
		}

		@Override
		public void onComplete(final Object response) {

			JSONObject root = (JSONObject) response;

			String url = null;
			try {
				url = root.getString("figureurl_qq_2");
				setUserName(root.getString("nickname"));
				MainWindows.setUserName(getUserName());
				RequestQueue rq = Volley.newRequestQueue(context);
				ImageRequest imageRequest = new ImageRequest(url,
						new ImageListener(), 100, 100, Config.ARGB_8888,
						new ImageErrorListener());

				rq.add(imageRequest);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void onCancel() {

		}
	}

	class ImageListener implements Listener<Bitmap> {

		@Override
		public void onResponse(Bitmap bitmap) {

			setUserLogo(makeRoundCorner(bitmap, 50));
			MainWindows.setUserImgBtn(getUserLogo());

		}
	}

	class ImageErrorListener implements ErrorListener {

		@Override
		public void onErrorResponse(VolleyError error) {
			System.out.println("imageResponse" + error);
		}

	}

}
