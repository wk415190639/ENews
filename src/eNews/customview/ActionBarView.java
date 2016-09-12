package eNews.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 标签View 控件
 */
public class ActionBarView extends GridView {

	public ActionBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			return true;// true:禁止滚动
		}
		return super.dispatchTouchEvent(ev);
	}

}
