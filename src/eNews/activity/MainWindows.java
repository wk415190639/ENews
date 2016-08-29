package eNews.activity;

import eNews.app.R;
import eNews.fragments.MainFragment;
import eNews.fragments.VideoFragment;
import eNews.fragments.WeatherFragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainWindows extends Activity implements OnClickListener {

	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;

	private LinearLayout menu_left;
	private LinearLayout menu_mainLayout;
	private LinearLayout menu_picLayout;
	private LinearLayout menu_videoLayout;
	private LinearLayout menu_weatherLayout;
	private LinearLayout menu_moreLayout;

	public MainFragment mainFragment;
	public VideoFragment videoFragment;
	public WeatherFragment weatherFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainwindows);
		init();
		initActionBarDrawerToggle();
		initFragment();

	}

	private void init() {

		mainFragment = new MainFragment();
		videoFragment = new VideoFragment();
		weatherFragment = new WeatherFragment();

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		drawerLayout.setDrawerShadow(R.drawable.shadow,
				GravityCompat.START);
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
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		actionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		actionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return actionBarDrawerToggle.onOptionsItemSelected(item)
				|| super.onOptionsItemSelected(item);
	}

	private void initActionBarDrawerToggle() {

		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.newsicon, R.string.app_name, R.string.app_name);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);

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

			if (mainFragment == null)
				mainFragment = new MainFragment();

			transaction.replace(R.id.frame_content, mainFragment);

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
			if (mainFragment == null)
				mainFragment = new MainFragment();
			transaction.replace(R.id.frame_content, mainFragment);
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

	class ActionBarDrawerToggleListener implements DrawerListener {

		@Override
		public void onDrawerClosed(View drawerView) {
			// TODO Auto-generated method stub
			actionBarDrawerToggle.onDrawerClosed(drawerView);
		}

		@Override
		public void onDrawerOpened(View drawerView) {
			// TODO Auto-generated method stub

			actionBarDrawerToggle.onDrawerOpened(drawerView);

		}

		@Override
		public void onDrawerSlide(View drawerView, float offset) {
			// TODO Auto-generated method stub
			actionBarDrawerToggle.onDrawerSlide(drawerView, offset);

		}

		@Override
		public void onDrawerStateChanged(int newState) {
			// TODO Auto-generated method stub
			actionBarDrawerToggle.onDrawerStateChanged(newState);

		}

	}

}
