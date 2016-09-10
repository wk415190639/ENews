package eNews.customview;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.PopupWindow;
import eNews.app.R;

public class MorePopupWindow extends PopupWindow {

	private View conentView;

	public MorePopupWindow(Activity activity) {
		// TODO Auto-generated constructor stub

		LayoutInflater inflater = LayoutInflater.from(activity);
		conentView = inflater.inflate(R.layout.more_popupwindow, null);

		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int h = metrics.heightPixels;
		int w = metrics.widthPixels;
		// ����SelectPicPopupWindow��View
		this.setContentView(conentView);
		// ����SelectPicPopupWindow��������Ŀ�
		this.setWidth(w / 2 + 50);
		// ����SelectPicPopupWindow��������ĸ�
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// ����SelectPicPopupWindow��������ɵ��
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// ˢ��״̬
		this.update();
		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(0000000000);
		// ��back���������ط�ʹ����ʧ,������������ܴ���OnDismisslistener �����������ؼ��仯�Ȳ���
		this.setBackgroundDrawable(dw);
		// this.setAnimationStyle(R.style.AnimationPreview);

		setOnclickListener(activity);

	}

	private void setOnclickListener(Activity activity) {

		conentView.findViewById(R.id.shareLy).setOnClickListener(
				(OnClickListener) activity);
		conentView.findViewById(R.id.collectLy).setOnClickListener(
				(OnClickListener) activity);

	}

}
