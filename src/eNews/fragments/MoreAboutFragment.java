package eNews.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import eNews.activity.MainWindows;
import eNews.app.R;

public class MoreAboutFragment extends Fragment implements OnClickListener {

	private View view;
	private Button getSourceCodeBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.more_about, null);

		init();

		return view;
	}

	private void init() {

		getSourceCodeBtn = (Button) view.findViewById(R.id.getSourceCodeBtn);
		getSourceCodeBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.getSourceCodeBtn:

//			ArrayList<String> arrayList = new ArrayList<String>();
//			arrayList
//					.add("https://github.com/wk415190639/ENews/blob/master/res/drawable-hdpi/icon.png?raw=true");
//			((MainWindows) getActivity()).shareToQzone("源代码", "新闻e眼客户端",
//					"https://github.com/wk415190639/ENews", arrayList);
//			break;

		default:
			break;
		}

	}

}
