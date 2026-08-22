package com.zte.gameassist.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.view.LandscapeView;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes2.dex */
public abstract class ActivityWindow extends FrameLayout implements GameMonitor.Callback, FoldMgr.Callback {
    protected static final String TAG = "ActivityWindow";
    protected Handler mHandler;
    private LandscapeView mLandscapeView;
    protected WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;

    public ActivityWindow(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void e() {
        getContext().setTheme(R.style.GameAssist_Theme_ZTE_Light);
        this.mWindowManager = (WindowManager) getContext().getSystemService("window");
        this.mHandler = new Handler(Looper.getMainLooper());
        h();
        g();
    }

    private void g() {
        SystemMgr.y(getContext()).h(this);
        FoldMgr.c().a(this);
        setOnKeyListener(new View.OnKeyListener() { // from class: com.zte.gameassist.common.b
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i2, KeyEvent keyEvent) {
                boolean j2;
                j2 = ActivityWindow.this.j(view, i2, keyEvent);
                return j2;
            }
        });
    }

    private void h() {
        WindowManager.LayoutParams createOverlayLayoutParams = WindowManagerWrapper.createOverlayLayoutParams();
        this.mWindowParams = createOverlayLayoutParams;
        createOverlayLayoutParams.width = -1;
        createOverlayLayoutParams.height = -1;
        createOverlayLayoutParams.type = 2003;
        createOverlayLayoutParams.setTitle("GameAssistActivityWindow");
        WindowManager.LayoutParams layoutParams = this.mWindowParams;
        layoutParams.screenOrientation = 6;
        layoutParams.flags = 83887904;
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        WindowManagerWrapper.LayoutParams.setFitInsetsTypes(this.mWindowParams);
        this.mWindowParams.layoutInDisplayCutoutMode = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i() {
        LandscapeView landscapeView = this.mLandscapeView;
        if (landscapeView != null) {
            try {
                try {
                    this.mWindowManager.removeViewImmediate(landscapeView);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                this.mLandscapeView = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean j(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i2 != 4) {
            return false;
        }
        d();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k() {
        try {
            if (this.mLandscapeView == null) {
                this.mLandscapeView = new LandscapeView(getContext());
                this.mLandscapeView.addView(this, new FrameLayout.LayoutParams(-1, -1));
                this.mWindowManager.addView(this.mLandscapeView, this.mWindowParams);
                this.mLandscapeView.setSystemUiVisibility(1542);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void d() {
        GaLog.a(TAG, "hide " + this.mLandscapeView);
        this.mHandler.post(new Runnable() { // from class: com.zte.gameassist.common.c
            @Override // java.lang.Runnable
            public final void run() {
                ActivityWindow.this.i();
            }
        });
    }

    public void l() {
        GaLog.a(TAG, "show " + this.mLandscapeView);
        this.mHandler.post(new Runnable() { // from class: com.zte.gameassist.common.a
            @Override // java.lang.Runnable
            public final void run() {
                ActivityWindow.this.k();
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        GaLog.a(TAG, "onDetachedFromWindow " + this.mLandscapeView);
        SystemMgr.y(getContext()).i(this);
        FoldMgr.c().h(this);
    }

    @Override // com.zte.gameassist.common.FoldMgr.Callback
    public void onDisplayInUseStateChanged(int i2) {
        d();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        d();
    }

    public ActivityWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        e();
    }
}
