package eNews.customview;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import eNews.app.R;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 自定义MorePopupWindow类
 */
public class MorePopupWindow extends PopupWindow {
	int h;
	int w;
	private View conentView;

	public MorePopupWindow(Activity activity) {
		// TODO Auto-generated constructor stub

		LayoutInflater inflater = LayoutInflater.from(activity);
		conentView = inflater.inflate(R.layout.more_popupwindow, null);

		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		h = metrics.heightPixels;
		w = metrics.widthPixels;
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(w / 2);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		// this.setAnimationStyle(R.style.AnimationPreview);

		setOnclickListener(activity);

	}

	private void setOnclickListener(Activity activity) {

		conentView.findViewById(R.id.shareLy).setOnClickListener(
				(OnClickListener) activity);
		conentView.findViewById(R.id.collectLy).setOnClickListener(
				(OnClickListener) activity);
		conentView.findViewById(R.id.shareLyqq).setOnClickListener(
				(OnClickListener) activity);

	}

	@Override
	public void showAsDropDown(View anchor) {
		// TODO Auto-generated method stub
		super.showAsDropDown(anchor, -300, 0);
	}

}
