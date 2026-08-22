package cn.nubia.gamelauncher.redmagicplanet.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import cn.nubia.gamelauncher.redmagicplanet.util.RedMagicVideoPlayerManager;

/* loaded from: classes.dex */
public class CustomHorizontalScrollView extends HorizontalScrollView {
    public CustomHorizontalScrollView(Context context) {
        super(context);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (RedMagicVideoPlayerManager.instance().getViewPagerOnTouch()) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
