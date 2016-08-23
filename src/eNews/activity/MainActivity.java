package eNews.activity;

import eNews.adapter.NewsAdapter;
import eNews.app.R;
import eNews.http.GetNewsContent;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	public NewsAdapter newsAdapter;
	private ListView newsListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		newsListView = (ListView) findViewById(R.id.newsListView);
		newsAdapter = new NewsAdapter(getApplicationContext());
		newsListView.setAdapter(newsAdapter);
		GetNewsContent.getNewsContent("nc/article/headline/",
				"T1348647853363/", "0", this);

	}

	public void updateAdapter(finishGetJsonObject update) {
		update.UpdateAdapter();
	}

	public interface finishGetJsonObject {
		public void UpdateAdapter();
	}
}
