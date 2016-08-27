package eNews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import eNews.app.R;

public class WeatherActivity extends Activity {

	private TextView TopDateTv;
	private ImageView TopIconIv;
	private TextView TopTempTv;
	private TextView TopTypeTv;
	private TextView TopFengXiangTv;
	public GridView weekWeathersGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.weather);
		TopDateTv = (TextView) findViewById(R.id.weatherDate_top);
		TopIconIv = (ImageView) findViewById(R.id.weatherIcon_top);
		TopTempTv = (TextView) findViewById(R.id.weatherTemp_top);
		TopTypeTv = (TextView) findViewById(R.id.weatherType_top);
		TopFengXiangTv = (TextView) findViewById(R.id.weatherFengXinag_top);
		weekWeathersGridView = (GridView) findViewById(R.id.weekWeathersGridView);

	}

	public void fillAdapter(FillAdapter fillAdapter) {
		fillAdapter.Fill();
	}

	public interface FillAdapter {
		void Fill();
	}

}
