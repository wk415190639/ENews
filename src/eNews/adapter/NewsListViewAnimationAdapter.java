package eNews.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

public class NewsListViewAnimationAdapter extends AnimationAdapter {

	public NewsListViewAnimationAdapter(BaseAdapter baseAdapter) {
		super(baseAdapter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Animator[] getAnimators(ViewGroup parent, View view) {

		Animator bottomInAnimator = ObjectAnimator.ofFloat(view,
				"translationY", 300, 0);
//		Animator rightInAnimator = ObjectAnimator.ofFloat(view, "translationX",
//				parent.getWidth(), 0);
		return new Animator[] { bottomInAnimator };
	}

}
