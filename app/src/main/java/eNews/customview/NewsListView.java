package eNews.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 新闻列表控件
 */
public class NewsListView extends ListView {

	public NewsListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
