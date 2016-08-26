package eNews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import eNews.adapter.ViewAnimationAdapter;
import eNews.adapter.OtherChannelAdapter;
import eNews.adapter.UserChannelAdapter;
import eNews.app.R;
import eNews.bean.ChannelItemModel;
import eNews.dao.ChannelManage;

public class ChannelActivity extends Activity {

	private GridView userChannelGV;
	private GridView otherChannelGV;
	OtherChannelAdapter otherChannelAdapter;
	UserChannelAdapter userChannelAdapter;

	private TextView channelManageBack;
	ViewAnimationAdapter viewAnimationAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.channel_manage);

		channelManageBack = (TextView) findViewById(R.id.channelManageBack);

		userChannelGV = (GridView) findViewById(R.id.userChannelGV);
		userChannelAdapter = new UserChannelAdapter(this, ChannelManage
				.getInstance(getApplicationContext())
				.getDefaultUserChannelsList());

		viewAnimationAdapter = new ViewAnimationAdapter(userChannelAdapter);
		viewAnimationAdapter.setAbsListView(userChannelGV);
		userChannelGV.setAdapter(viewAnimationAdapter);

		
		otherChannelAdapter = new OtherChannelAdapter(this, ChannelManage
				.getInstance(getApplicationContext())
				.getDefaultOtherChannelsList());
		otherChannelGV = (GridView) findViewById(R.id.otherChannelGV);
		otherChannelGV.setAdapter(otherChannelAdapter);

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
