package cn.nubia.multisubscreen.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.zte.gameassist.common.DensityHelper;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;

/* loaded from: classes.dex */
public class MultiSubScreenBaseActivity extends Activity implements GameMonitor.Callback, LifecycleOwner {

    /* renamed from: c, reason: collision with root package name */
    private Handler f8114c = new Handler();

    /* renamed from: h, reason: collision with root package name */
    protected LifecycleRegistry f8115h;

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle a() {
        return this.f8115h;
    }

    public void e(boolean z) {
        if (z) {
            getWindow().getDecorView().setSystemUiVisibility(5382);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.f8115h = lifecycleRegistry;
        lifecycleRegistry.m(Lifecycle.State.CREATED);
        DensityHelper.d(this);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 3;
        getWindow().setAttributes(attributes);
        getWindow().addFlags(512);
        getWindow().addFlags(256);
        SystemMgr.y(getApplicationContext()).h(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SystemMgr.y(getApplicationContext()).i(this);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.f8115h.m(Lifecycle.State.STARTED);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.f8115h.m(Lifecycle.State.RESUMED);
        e(true);
        this.f8114c.postDelayed(new Runnable() { // from class: cn.nubia.multisubscreen.ui.MultiSubScreenBaseActivity.1
            @Override // java.lang.Runnable
            public void run() {
                MultiSubScreenBaseActivity.this.e(true);
            }
        }, 1000L);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        e(z);
    }
}
