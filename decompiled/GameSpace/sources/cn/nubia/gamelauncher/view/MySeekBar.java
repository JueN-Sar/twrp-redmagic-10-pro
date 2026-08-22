package cn.nubia.gamelauncher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/* loaded from: classes.dex */
public class MySeekBar extends SeekBar {
    private boolean mTouchEnable;

    public MySeekBar(Context context) {
        super(context);
        this.mTouchEnable = false;
    }

    public MySeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTouchEnable = false;
    }

    public MySeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTouchEnable = false;
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mTouchEnable) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setTouch(boolean z) {
        this.mTouchEnable = z;
    }
}
