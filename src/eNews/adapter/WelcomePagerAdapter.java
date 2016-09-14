package eNews.adapter;

import java.util.ArrayList;

import eNews.activity.MainWindows;
import eNews.activity.WelcomeActivity;
import eNews.app.R;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class WelcomePagerAdapter extends PagerAdapter {

	ArrayList<View> arrayList;
	private Button startBtn;
	private WelcomeActivity activity;

	public WelcomePagerAdapter(final WelcomeActivity activity) {
		// TODO Auto-generated constructor stub
		arrayList = new ArrayList<>();
		this.activity = activity;
		LayoutInflater inflater = LayoutInflater.from(activity);
		arrayList.add(inflater.inflate(R.layout.w1, null));
		arrayList.add(inflater.inflate(R.layout.w2, null));
		arrayList.add(inflater.inflate(R.layout.w3, null));

		startBtn = (Button) arrayList.get(2).findViewById(R.id.startBtn);
		startBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activity.startMainActivity();
			}
		});

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub

		((ViewPager) container).addView(arrayList.get(position));
		return arrayList.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub

		((ViewPager) container).removeView(arrayList.get(position));
	}

}
