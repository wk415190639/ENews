package eNews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import eNews.adapter.ViewAnimationAdapter;
import eNews.adapter.OtherChannelAdapter;
import eNews.adapter.UserChannelAdapter;
import eNews.app.R;
import eNews.bean.ChannelItemModel;
import eNews.dao.ChannelManage;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 
 * 频道管理Activity
 */
public class ChannelActivity extends Activity {

	private GridView userChannelGV;
	private GridView otherChannelGV;
	OtherChannelAdapter otherChannelAdapter;
	UserChannelAdapter userChannelAdapter;

	private ImageButton channelManageBack;
	ViewAnimationAdapter userViewAnimationAdapter;
	ViewAnimationAdapter otherViewAnimationAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.channel_manage);
		getActionBar().hide();
		channelManageBack = (ImageButton) findViewById(R.id.backBtn);

		userChannelGV = (GridView) findViewById(R.id.userChannelGV);
		userChannelAdapter = new UserChannelAdapter(this, ChannelManage
				.getInstance(getApplicationContext())
				.getDefaultUserChannelsList());

		userViewAnimationAdapter = new ViewAnimationAdapter(userChannelAdapter);
		userViewAnimationAdapter.setAbsListView(userChannelGV);
		userChannelGV.setAdapter(userViewAnimationAdapter);

		otherChannelAdapter = new OtherChannelAdapter(this, ChannelManage
				.getInstance(getApplicationContext())
				.getDefaultOtherChannelsList());
		otherChannelGV = (GridView) findViewById(R.id.otherChannelGV);

		otherViewAnimationAdapter = new ViewAnimationAdapter(
				otherChannelAdapter);
		otherViewAnimationAdapter.setAbsListView(otherChannelGV);

		otherChannelGV.setAdapter(otherViewAnimationAdapter);

		userChannelGV
				.setOnItemClickListener(new UserChannelGvItemClickListener());

		otherChannelGV
				.setOnItemClickListener(new OtherChannelGvItemClickListener());

		channelManageBack.setOnClickListener(new ChannelManageBackListener());

	}

	class UserChannelGvItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			if (id != 0)
				userChannelAdapter.remove(position, otherChannelAdapter);

		}

	}

	class OtherChannelGvItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			otherChannelAdapter.remove(position, userChannelAdapter);
		}

	}

	class ChannelManageBackListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			System.out.println("back click ");

			int i = 0;
			for (i = 0; i < userChannelAdapter.getCount(); i++) {

				ChannelItemModel tmpItemModel = (ChannelItemModel) userChannelAdapter
						.getItem(i);
				ChannelManage.getInstance(getApplicationContext()).updateUser(
						tmpItemModel.getName(), String.valueOf(i));

			}

			for (i = 0; i < otherChannelAdapter.getCount(); i++) {

				ChannelItemModel tmpItemModel = (ChannelItemModel) otherChannelAdapter
						.getItem(i);

				ChannelManage.getInstance(getApplicationContext()).updateOther(
						tmpItemModel.getName(), String.valueOf(i));

			}

			finish();

		}
	}

}
