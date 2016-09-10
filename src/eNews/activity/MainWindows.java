package eNews.activity;

import java.lang.ref.WeakReference;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import eNews.app.R;
import eNews.common.HandlerWhat;
import eNews.fragments.MainFragment;
import eNews.fragments.MoreAboutFragment;
import eNews.fragments.PictureFragment;
import eNews.fragments.VideoFragment;
import eNews.fragments.WeatherFragment;
import eNews.thirdParty.TencentThirdParty;

public class MainWindows extends Activity implements OnClickListener {

	@SuppressWarnings("unused")
	private MainWindowsHandler mainWindowsHandler;
	private ImageView logo;
	private DrawerLayout drawerLayout;

	private LinearLayout menu_left;
	private LinearLayout menu_mainLayout;
	private LinearLayout menu_picLayout;
	private LinearLayout menu_videoLayout;
	private LinearLayout menu_weatherLayout;
	private LinearLayout menu_moreLayout;

	public MainFragment mainFragment;
	public PictureFragment pictureFragment;
	public VideoFragment videoFragment;
	public WeatherFragment weatherFragment;
	public MoreAboutFragment aboutFragment;

	private ImageButton userImgBtn;
	private TextView userName;
	private UserInfo mInfo;
	private boolean isLogin;

	private LoginIUListener iuListener;

	private boolean isOpen;
	private static Tencent mTencent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainwindows);
		initTencentInstance();
		init();
		initFragment();

	}

	private void initTencentInstance() {
		// mTencent = Tencent.createInstance(AppConstant.appId, this);
		mTencent = TencentThirdParty.getInstance(getApplicationContext()).getTencentInstance();

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

	private void init() {

		mainWindowsHandler = new MainWindowsHandler(this);
		mainFragment = new MainFragment();
		videoFragment = new VideoFragment();
		weatherFragment = new WeatherFragment();
		pictureFragment = new PictureFragment();
		aboutFragment = new MoreAboutFragment();

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		drawerLayout.setDrawerShadow(R.drawable.shadow, GravityCompat.START);

		drawerLayout.setDrawerListener(new ActionBarDrawerToggleListener());
		menu_left = (LinearLayout) findViewById(R.id.menuLayout);
		menu_left.setOnTouchListener(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
		});

		menu_mainLayout = (LinearLayout) findViewById(R.id.menuMain);
		menu_mainLayout.setOnClickListener(this);

		menu_picLayout = (LinearLayout) findViewById(R.id.menuPic);
		menu_picLayout.setOnClickListener(this);

		menu_videoLayout = (LinearLayout) findViewById(R.id.menuVideo);
		menu_videoLayout.setOnClickListener(this);

		menu_weatherLayout = (LinearLayout) findViewById(R.id.menuWeather);
		menu_weatherLayout.setOnClickListener(this);

		menu_moreLayout = (LinearLayout) findViewById(R.id.menuMore);
		menu_moreLayout.setOnClickListener(this);

		userImgBtn = (ImageButton) findViewById(R.id.userImg);
		userImgBtn.setOnClickListener(new LoginBtnClick());

		userName = (TextView) findViewById(R.id.userName);

		iuListener = new LoginIUListener();
	}

	private void initFragment() {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.frame_content, mainFragment);
		transaction.commit();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.actionbar_layout);
		actionBar.setDisplayShowCustomEnabled(true);

		logo = (ImageView) actionBar.getCustomView().findViewById(
				R.id.actionbar_logo);

		logo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!isOpen) {

					drawerLayout.openDrawer(Gravity.LEFT);
					isOpen = true;

				} else {
					drawerLayout.closeDrawer(Gravity.LEFT);
					isOpen = false;

					logo.setTranslationX(0);
				}

			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		switch (v.getId()) {

		case R.id.menuMain:

			drawerLayout.closeDrawer(menu_left);

			if (mainFragment == null)
				mainFragment = new MainFragment();
			transaction.replace(R.id.frame_content, mainFragment);

			System.out.println("menuVideo");
			break;

		case R.id.menuPic:

			drawerLayout.closeDrawer(menu_left);

			if (pictureFragment == null)
				pictureFragment = new PictureFragment();

			transaction.replace(R.id.frame_content, pictureFragment);

			System.out.println("menuPic");
			break;

		case R.id.menuVideo:

			drawerLayout.closeDrawer(menu_left);

			if (videoFragment == null)
				videoFragment = new VideoFragment();
			transaction.replace(R.id.frame_content, videoFragment);

			System.out.println("menuVideo");
			break;
		case R.id.menuWeather:

			drawerLayout.closeDrawer(menu_left);
			if (weatherFragment == null)
				weatherFragment = new WeatherFragment();
			transaction.replace(R.id.frame_content, weatherFragment);
			System.out.println("menuWeather");
			break;
		case R.id.menuMore:

			drawerLayout.closeDrawer(menu_left);
			if (aboutFragment == null)
				aboutFragment = new MoreAboutFragment();
			transaction.replace(R.id.frame_content, aboutFragment);
			System.out.println("menuMore");
			break;

		default:
			if (mainFragment == null)
				mainFragment = new MainFragment();
			transaction.replace(R.id.frame_content, mainFragment);
			break;
		}
		transaction.commit();

	}

	class LoginBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {

			login();

		}

	}

	public void showMainFragment() {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		if (mainFragment == null)
			mainFragment = new MainFragment();
		transaction.replace(R.id.frame_content, mainFragment);
		transaction.commit();

		getActionBar().show();

	}

	class ActionBarDrawerToggleListener implements DrawerListener {

		@Override
		public void onDrawerClosed(View drawerView) {
			// TODO Auto-generated method stub

			System.out.println("onDrawerOpen ->" + isOpen + "");

			logo.setImageResource(R.drawable.menu_close);

		}

		@Override
		public void onDrawerOpened(View drawerView) {
			// TODO Auto-generated method stub

			System.out.println("onDrawerClosed ->" + isOpen + "");
			logo.setImageResource(R.drawable.menu_open);

		}

		@Override
		public void onDrawerSlide(View drawerView, float offset) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onDrawerStateChanged(int newState) {
			// TODO Auto-generated method stub

		}

	}

	static class MainWindowsHandler extends Handler {
		WeakReference<MainWindows> mainReference;

		public MainWindowsHandler(MainWindows mainWindows) {
			// TODO Auto-generated constructor stub
			mainReference = new WeakReference<MainWindows>(mainWindows);

		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			MainWindows mainWindows = mainReference.get();

			if (mainWindows != null) {
				System.out.println("mainReference.get()");

				switch (msg.what) {

				case HandlerWhat.mainNews:
					break;

				case HandlerWhat.pictureNews:
					break;

				case HandlerWhat.videoNews:
					break;

				case HandlerWhat.weatherNews:
					break;

				default:
					break;
				}

			}

		}
	}

	private void login() {

		if (!isLogin) {
			System.out.println("正在登录......");

			if (!mTencent.isSessionValid()) {
				mTencent.login(this, "all", iuListener);

			} else {

				mTencent.logout(this);
				mTencent.login(this, "all", iuListener);

			}

			updateUserInfo();
		} else {
			System.out.println("已登录.....");

			new AlertDialog.Builder(this).setTitle("已经登录,是否注销")
					.setPositiveButton("注销", new NegativeButtonListener())
					.setNegativeButton("取消", new PositiveButtonListener())
					.setIcon(android.R.drawable.ic_dialog_info).show();

		}
	}

	private void logout() {
		mTencent.logout(this);
		updateUserInfo();
	}

	public class NegativeButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			logout();
			isLogin = false;
		}

	}

	public class PositiveButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == Constants.REQUEST_LOGIN
				|| requestCode == Constants.REQUEST_APPBAR) {
			Tencent.onActivityResultData(requestCode, resultCode, data,
					iuListener);
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	class LoginIUListener implements IUiListener {

		@Override
		public void onCancel() {

			System.out.println("OnCancel");

		}

		@Override
		public void onComplete(Object respone) {
			// TODO Auto-generated method stub
			isLogin = true;

			doComplete(respone);

		}

		private void doComplete(Object respone) {

			JSONObject root = (JSONObject) respone;
			initOpenidAndToken(root);
			updateUserInfo();

		}

		@Override
		public void onError(UiError arg0) {

			System.out.println("onError");
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

	private void updateUserInfo() {

		if (mTencent != null && mTencent.isSessionValid()) {

			mInfo = new UserInfo(this, mTencent.getQQToken());
			mInfo.getUserInfo(new UserInfoIUiListener());

		} else {
			userName.setText("未登录");
			userImgBtn.setImageResource(R.drawable.people1);
		}

	}

	class UserInfoIUiListener implements IUiListener {
		@Override
		public void onError(UiError e) {

		}

		@Override
		public void onComplete(final Object response) {

			JSONObject root = (JSONObject) response;

			System.out.println("-------getUserInfo>" + response.toString());
			String url = null;
			try {
				url = root.getString("figureurl_qq_2");
				userName.setText(root.getString("nickname"));

				RequestQueue rq = Volley.newRequestQueue(MainWindows.this);
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
	};

	class ImageListener implements Listener<Bitmap> {

		@Override
		public void onResponse(Bitmap bitmap) {

			userImgBtn.setImageBitmap(makeRoundCorner(bitmap, 50));

		}

	}

	class ImageErrorListener implements ErrorListener {

		@Override
		public void onErrorResponse(VolleyError error) {
			System.out.println("imageResponse" + error);
		}

	}

}
