package eNews.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

public class ActionBarView extends GridView {

	public ActionBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			return true;// true:½ûÖ¹¹ö¶¯
		}
		return super.dispatchTouchEvent(ev);
	}

}
