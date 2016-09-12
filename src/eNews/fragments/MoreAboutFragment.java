package eNews.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import eNews.app.R;
import eNews.thirdParty.TencentThirdParty;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 更多碎片
 */
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

			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList
					.add("https://github.com/wk415190639/ENews/blob/master/res/drawable-hdpi/icon.png?raw=true");
			TencentThirdParty
					.getInstance(getActivity().getApplicationContext())
					.shareToQzone(getActivity(), "源代码", "新闻e眼客户端",
							"https://github.com/wk415190639/ENews", arrayList);
			break;

		default:
			break;
		}

	}

}
