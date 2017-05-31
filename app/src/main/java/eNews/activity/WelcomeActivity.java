package eNews.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import eNews.adapter.WelcomePagerAdapter;
import eNews.app.R;

public class WelcomeActivity extends Activity {

	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		checkFirst();

	}


	private void checkFirst()
	{

		SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
		boolean isFirst = pref.getBoolean("first", true);
		if (!isFirst) {
			startActivity(new Intent(this, MainWindows.class));
			finish();
		} else {
			SharedPreferences.Editor editor = getSharedPreferences("data",
					MODE_PRIVATE).edit();
			editor.putBoolean("first", false);
			editor.commit();
		}

		getActionBar().hide();
		pager = (ViewPager) findViewById(R.id.welcomePage);
		pager.setAdapter(new WelcomePagerAdapter(this));
	}
	public void startMainActivity() {
		// TODO Auto-generated method stub
		startActivity(new Intent(WelcomeActivity.this, MainWindows.class));
		finish();
	}

}
