package com.zte.gameassist.view;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.InputMonitor;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.PrintWriter;

/* loaded from: classes2.dex */
public class PassThroughView extends FrameLayout {
    private static final String TAG = "PassThroughView";
    private InputMonitor inputMonitor;
    private boolean mCanTouch;
    private String mChannel;
    private InputEventReceiver mInputEventReceiver;
    protected InputManager mInputManager;
    private Looper mLooper;
    private boolean mRegisterInputMonitor;
    private WindowManager mWindowManager;

    public PassThroughView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 7) {
            return;
        }
        if (!g(motionEvent)) {
            if (action == 0 || action == 9 || action == 11) {
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                int[] iArr = new int[2];
                getLocationOnScreen(iArr);
                if (!i(rawX, rawY, iArr)) {
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.setAction(4);
                    dispatchTouchEvent(obtain);
                    obtain.recycle();
                }
            }
            l(false);
            return;
        }
        float rawX2 = motionEvent.getRawX();
        float rawY2 = motionEvent.getRawY();
        int[] iArr2 = new int[2];
        getLocationOnScreen(iArr2);
        int i2 = iArr2[0];
        int i3 = iArr2[1];
        MotionEvent obtain2 = MotionEvent.obtain(motionEvent);
        if (action == 0) {
            boolean i4 = i(rawX2, rawY2, iArr2);
            GaLog.a(TAG, "dispatchEvent pointInView:" + i4);
            if (i4) {
                l(true);
                h();
            }
        } else if (action != 2 && !i(rawX2, rawY2, iArr2)) {
            obtain2.setAction(4);
        }
        obtain2.offsetLocation(-i2, -i3);
        dispatchTouchEvent(obtain2);
        obtain2.recycle();
    }

    private void e() {
        this.mWindowManager = (WindowManager) getContext().getSystemService("window");
        this.mInputManager = (InputManager) getContext().getSystemService("input");
    }

    private void f() {
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getLayoutParams();
        if (layoutParams == null) {
            GaLog.b(TAG, "window param is null");
            return;
        }
        layoutParams.flags = (layoutParams.flags | 16) & (-262145);
        try {
            this.mWindowManager.updateViewLayout(this, layoutParams);
            this.mCanTouch = false;
        } catch (Exception e2) {
            GaLog.b(TAG, "error init window param " + e2);
        }
    }

    private boolean g(MotionEvent motionEvent) {
        return (motionEvent.getSource() & 4098) == 4098 && motionEvent.getDeviceId() >= 0;
    }

    private void h() {
        InputMonitor inputMonitor = this.inputMonitor;
        if (inputMonitor != null) {
            inputMonitor.pilferPointers();
        }
    }

    private boolean i(float f2, float f3, int[] iArr) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        return f2 >= ((float) i2) && f2 <= ((float) (getWidth() + i2)) && f3 >= ((float) i3) && f3 <= ((float) (getHeight() + i3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k(boolean z, boolean z2, InputEvent inputEvent) {
        return !z2 && (!z || (inputEvent.getSource() & 2) == 0);
    }

    private void l(boolean z) {
        if (this.mCanTouch == z) {
            return;
        }
        this.mCanTouch = z;
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getLayoutParams();
        if (z) {
            layoutParams.flags = (layoutParams.flags & (-17)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OVERRIDE_LAYOUT_IN_DISPLAY_CUTOUT_MODE;
        } else {
            layoutParams.flags = (layoutParams.flags | 16) & (-262145);
        }
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        layoutParams.x = iArr[0];
        layoutParams.y = iArr[1];
        GaLog.a(TAG, "updateTouchState: " + z + iArr[0] + "," + iArr[1]);
        try {
            this.mWindowManager.updateViewLayout(this, layoutParams);
        } catch (Exception e2) {
            GaLog.b(TAG, "error update touch state " + e2);
        }
    }

    public void d(PrintWriter printWriter, String[] strArr) {
        printWriter.println("PassThroughView Status:");
        printWriter.println("    mCanTouch:" + this.mCanTouch);
        printWriter.println("    mRegisterInputMonitor:" + this.mRegisterInputMonitor);
        printWriter.println("    mChannel:" + this.mChannel);
    }

    public void j(String str, Looper looper) {
        this.mChannel = str;
        this.mLooper = looper;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRegisterInputMonitor) {
            return;
        }
        GaLog.a(TAG, "onAttachedToWindow");
        try {
            try {
                this.inputMonitor = this.mInputManager.monitorGestureInput(this.mChannel, getContext().getDisplay().getDisplayId());
                this.mInputEventReceiver = new InputEventReceiver(this.inputMonitor.getInputChannel(), this.mLooper) { // from class: com.zte.gameassist.view.PassThroughView.1
                    public void onInputEvent(InputEvent inputEvent) {
                        boolean z;
                        try {
                            try {
                                z = inputEvent instanceof MotionEvent;
                            } catch (Exception e2) {
                                GaLog.b(PassThroughView.TAG, "error input event " + e2);
                            }
                            if (PassThroughView.this.k(z, !z && (inputEvent instanceof KeyEvent), inputEvent)) {
                                super.onInputEvent(inputEvent);
                                return;
                            }
                            if (z) {
                                PassThroughView.this.c((MotionEvent) inputEvent);
                            }
                            super.onInputEvent(inputEvent);
                        } catch (Throwable th) {
                            super.onInputEvent(inputEvent);
                            throw th;
                        }
                    }
                };
                this.mRegisterInputMonitor = true;
            } catch (Exception e2) {
                GaLog.b(TAG, "error method " + e2);
            }
        } finally {
            f();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mRegisterInputMonitor) {
            GaLog.a(TAG, "onDetachedFromWindow");
            InputMonitor inputMonitor = this.inputMonitor;
            if (inputMonitor != null) {
                inputMonitor.dispose();
                this.inputMonitor = null;
            }
            InputEventReceiver inputEventReceiver = this.mInputEventReceiver;
            if (inputEventReceiver != null) {
                inputEventReceiver.dispose();
                this.mInputEventReceiver = null;
            }
            this.mRegisterInputMonitor = false;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean g2 = g(motionEvent);
        GaLog.a(TAG, "onInterceptTouchEvent: " + motionEvent.getAction() + ",isTouch:" + g2);
        if (!g2) {
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 1 || action == 3) {
            l(false);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 4) {
            return super.onTouchEvent(motionEvent);
        }
        View childAt = getChildAt(0);
        if (childAt == null) {
            return true;
        }
        childAt.onTouchEvent(motionEvent);
        return true;
    }

    public PassThroughView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PassThroughView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mCanTouch = false;
        this.mRegisterInputMonitor = false;
        this.mChannel = "PassThrough";
        e();
    }
}
