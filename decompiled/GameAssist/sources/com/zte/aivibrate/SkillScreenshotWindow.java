package com.zte.aivibrate;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class SkillScreenshotWindow extends FrameLayout {
    private static final float MIN_MOVE_DISTANCE = 20.0f;
    private boolean mIsAdd;
    protected ImageView mIvSource;
    protected ImageView mIvTarget;
    private float mStartX;
    private float mStartY;
    private float mStopX;
    private float mStopY;
    protected TextView mTvNum;
    private Handler mUIHandler;
    private float mViewDownX;
    private float mViewDownY;
    protected WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;
    private View root;

    public SkillScreenshotWindow(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private boolean d() {
        return Math.abs(this.mStopX - this.mStartX) > MIN_MOVE_DISTANCE || Math.abs(this.mStopY - this.mStartY) > MIN_MOVE_DISTANCE;
    }

    private void e() {
        View inflate = View.inflate(getContext(), R.layout.layout_skill_window, null);
        this.root = inflate;
        this.mIvSource = (ImageView) inflate.findViewById(R.id.iv_source);
        this.mIvTarget = (ImageView) this.root.findViewById(R.id.iv_target);
        this.mTvNum = (TextView) this.root.findViewById(R.id.tv_num);
        addView(this.root);
        this.mUIHandler = new Handler(Looper.getMainLooper());
        this.mWindowManager = (WindowManager) getContext().getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(Bitmap bitmap) {
        this.mIvSource.setImageBitmap(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(Bitmap bitmap) {
        this.mIvTarget.setImageBitmap(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(String str) {
        this.mTvNum.setText(str);
    }

    private void i(float f2, float f3) {
        WindowManager.LayoutParams layoutParams = this.mWindowParams;
        layoutParams.x = (int) f2;
        layoutParams.y = (int) f3;
        try {
            this.mWindowManager.updateViewLayout(this, layoutParams);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.mStopX = motionEvent.getRawX();
        this.mStopY = motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 2 && d()) {
                return true;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }
        this.mStartX = motionEvent.getRawX();
        this.mStartY = motionEvent.getRawY();
        this.mViewDownX = motionEvent.getX();
        this.mViewDownY = motionEvent.getY();
        setAlpha(1.0f);
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mViewDownX = motionEvent.getX();
            this.mViewDownY = motionEvent.getY();
            return true;
        }
        if (action != 2) {
            return true;
        }
        i(motionEvent.getRawX() - this.mViewDownX, motionEvent.getRawY() - this.mViewDownY);
        return true;
    }

    public void setBitmap(final Bitmap bitmap) {
        this.mUIHandler.post(new Runnable() { // from class: com.zte.aivibrate.c
            @Override // java.lang.Runnable
            public final void run() {
                SkillScreenshotWindow.this.f(bitmap);
            }
        });
    }

    public void setTargetBitmap(final Bitmap bitmap) {
        this.mUIHandler.post(new Runnable() { // from class: com.zte.aivibrate.b
            @Override // java.lang.Runnable
            public final void run() {
                SkillScreenshotWindow.this.g(bitmap);
            }
        });
    }

    public void setText(final String str) {
        this.mUIHandler.post(new Runnable() { // from class: com.zte.aivibrate.a
            @Override // java.lang.Runnable
            public final void run() {
                SkillScreenshotWindow.this.h(str);
            }
        });
    }

    public SkillScreenshotWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SkillScreenshotWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        e();
    }
}
