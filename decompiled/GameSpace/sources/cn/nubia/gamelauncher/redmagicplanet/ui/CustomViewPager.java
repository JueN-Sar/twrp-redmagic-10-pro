package cn.nubia.gamelauncher.redmagicplanet.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;
import cn.nubia.gamelauncher.redmagicplanet.util.RedMagicVideoPlayerManager;

/* loaded from: classes.dex */
public class CustomViewPager extends ViewPager {
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                RedMagicVideoPlayerManager.instance().setViewPagerOnTouch(false);
            } else if (action != 2) {
                RedMagicVideoPlayerManager.instance().setViewPagerOnTouch(false);
            }
            return super.onTouchEvent(motionEvent);
        }
        RedMagicVideoPlayerManager.instance().setViewPagerOnTouch(true);
        return super.onTouchEvent(motionEvent);
    }
}
