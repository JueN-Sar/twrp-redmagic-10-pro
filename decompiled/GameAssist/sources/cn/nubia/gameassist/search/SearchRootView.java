package cn.nubia.gameassist.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class SearchRootView extends FrameLayout {
    private static final String TAG = "SearchRootView";
    private RelativeLayout mHorLayoutView;
    private RelativeLayout mVerLayoutView;

    public SearchRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHorLayoutView = (RelativeLayout) findViewById(R.id.search_view_hor_root_layout);
        this.mVerLayoutView = (RelativeLayout) findViewById(R.id.search_view_ver_root_layout);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        SearchWindowManager.i(getContext()).f("touch root view");
        return super.onTouchEvent(motionEvent);
    }

    public SearchRootView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public SearchRootView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
