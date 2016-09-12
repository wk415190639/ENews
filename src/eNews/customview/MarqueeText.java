package eNews.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 滚动的TextView 控件
 */
public class MarqueeText extends TextView {
	public MarqueeText(Context context) {
		super(context);
	}

	public MarqueeText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean isFocused() {
		return true;
	}

}
