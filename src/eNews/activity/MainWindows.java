package eNews.activity;

import java.lang.ref.WeakReference;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;
import eNews.app.R;
import eNews.common.HandlerWhat;
import eNews.fragments.MainFragment;
import eNews.fragments.MoreAboutFragment;
import eNews.fragments.PictureFragment;
import eNews.fragments.VideoFragment;
import eNews.fragments.WeatherFragment;
import eNews.thirdParty.TencentThirdParty;

/**
 * 
 * @author k 主界面
 */
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
	private LinearLayout menu_collectLayout;

	public MainFragment mainFragment;
	public PictureFragment pictureFragment;
	public VideoFragment videoFragment;
	public WeatherFragment weatherFragment;
	public MoreAboutFragment aboutFragment;

	private static ImageButton userImgBtn;
	private static TextView userName;
	private showCollectActivity showcollectActivityListener;

	private boolean isOpen;

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
		TencentThirdParty.getInstance(getApplicationContext())
				.getTencentInstance();

	}

	private void init() {

		mainWindowsHandler = new MainWindowsHandler(this);
		mainFragment = new MainFragment();
		videoFragment = new VideoFragment();
		weatherFragment = new WeatherFragment();
		pictureFragment = new PictureFragment();
		aboutFragment = new MoreAboutFragment();
		showcollectActivityListener = new showCollectActivity();

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

		menu_collectLayout = (LinearLayout) findViewById(R.id.menuCollect);
		menu_collectLayout.setOnClickListener(showcollectActivityListener);

		userImgBtn = (ImageButton) findViewById(R.id.userImg);
		userImgBtn.setOnClickListener(new LoginBtnClick());

		userName = (TextView) findViewById(R.id.userName);
		userName.setOnClickListener(showcollectActivityListener);

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

	class showCollectActivity implements OnClickListener {

		@Override
		public void onClick(View v) {

			if (TencentThirdParty.getInstance(getApplicationContext())
					.checkIsLogged()) {
				Intent intent = new Intent(MainWindows.this,
						CollectActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(getApplicationContext(), "请点击头像登录",
						Toast.LENGTH_SHORT).show();
			}

		}

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

		TencentThirdParty.getInstance(getApplicationContext()).userLogin(this);
		// updateUserInfo();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == Constants.REQUEST_LOGIN
				|| requestCode == Constants.REQUEST_APPBAR) {
			Tencent.onActivityResultData(
					requestCode,
					resultCode,
					data,
					TencentThirdParty.getInstance(getApplicationContext()).loginIUListener);
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	public static void setUserImgBtn(Bitmap bitmap) {

		if (bitmap == null)
			userImgBtn.setImageResource(R.drawable.people1);
		else
			userImgBtn.setImageBitmap(bitmap);

	}

	public static void setUserName(String name) {
		userName.setText(name);

	}

}
