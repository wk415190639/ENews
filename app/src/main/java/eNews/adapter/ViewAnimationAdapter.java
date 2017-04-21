package eNews.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 频道管理动画适配器
 */
public class ViewAnimationAdapter extends AnimationAdapter {

	public ViewAnimationAdapter(BaseAdapter baseAdapter) {
		super(baseAdapter);
		// TODO Auto-generated constructor stub

	}

	@Override
	public Animator[] getAnimators(ViewGroup parent, View view) {

		Animator bottomInAnimator = ObjectAnimator.ofFloat(view,
				"translationY", 300, 0);
		// Animator rightInAnimator = ObjectAnimator.ofFloat(view,
		// "translationX",
		// parent.getWidth(), 0);
		return new Animator[] { bottomInAnimator };
	}

	@Override
	protected long getAnimationDelayMillis() {
		return DEFAULTANIMATIONDELAYMILLIS;
	}

	@Override
	protected long getAnimationDurationMillis() {
		return DEFAULTANIMATIONDURATIONMILLIS;
	}
}
