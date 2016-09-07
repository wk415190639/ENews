package eNews.activity;

import java.lang.ref.WeakReference;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import eNews.app.R;
import eNews.common.HandlerWhat;
import eNews.fragments.MainFragment;
import eNews.fragments.MoreAboutFragment;
import eNews.fragments.PictureFragment;
import eNews.fragments.VideoFragment;
import eNews.fragments.WeatherFragment;

public class MainWindows extends Activity implements OnClickListener {

	@SuppressWarnings("unused")
	private MainWindowsHandler mainWindowsHandler;
	private ImageView logo;
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;

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

	private boolean isOpen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainwindows);
		init();
		// initActionBarDrawerToggle();
		initFragment();

		int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024 / 1024);
		System.out.println("Max memory is " + maxMemory + "m");

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
		// ActionBarDrawerToggleListener

		drawerLayout.setDrawerListener(new ActionBarDrawerToggleListener());
		menu_left = (LinearLayout) findViewById(R.id.menuLayout);

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

		System.out.println("----------onClick");
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
			
			logo.setRotation(0);

		}

		@Override
		public void onDrawerOpened(View drawerView) {
			// TODO Auto-generated method stub

			System.out.println("onDrawerClosed ->" + isOpen + "");
			logo.setRotation(180);

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

}
