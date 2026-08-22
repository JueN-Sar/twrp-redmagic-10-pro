package cn.nubia.gameassist.tips.learn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class UserGuideLayout extends LinearLayout {
    private static final int THRESHOLD_VELOCITY = 108;
    private float mDownX;
    private final UserGuideController mUserGuideController;

    public UserGuideLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        View findViewById = findViewById(R.id.image_user_guide);
        if (findViewById != null) {
            findViewById.setScaleX(i2 < i3 ? 0.8f : 1.0f);
            findViewById.setScaleY(i2 < i3 ? 0.8f : 1.0f);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mDownX = motionEvent.getRawX();
        } else if (actionMasked == 2) {
            float rawX = motionEvent.getRawX() - this.mDownX;
            if (rawX < -108.0f) {
                this.mUserGuideController.d();
                return true;
            }
            if (rawX > 108.0f) {
                this.mUserGuideController.c();
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public UserGuideLayout(Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public UserGuideLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mUserGuideController = UserGuideController.e(context);
    }
}
