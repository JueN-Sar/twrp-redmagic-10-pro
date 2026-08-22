package com.zte.aivibrate.scene;

import android.content.Context;
import android.view.MotionEvent;
import com.zte.aivibrate.util.AIVibrateLog;
import com.zte.gameassist.input.FullScreenInputMonitor;
import com.zte.gameassist.input.InterfaceEventListener;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public abstract class BaseScene implements InterfaceEventListener {

    /* renamed from: c, reason: collision with root package name */
    protected boolean f16239c = false;

    /* renamed from: h, reason: collision with root package name */
    private I4DVibrateScene f16240h;

    public BaseScene(I4DVibrateScene i4DVibrateScene) {
        this.f16240h = i4DVibrateScene;
    }

    public void b(PrintWriter printWriter) {
    }

    public abstract void c();

    public void d(VibrateSceneState vibrateSceneState) {
    }

    @Override // com.zte.gameassist.input.InterfaceEventListener
    public void f(MotionEvent motionEvent) {
    }

    public synchronized void g(Context context) {
        if (!this.f16239c) {
            AIVibrateLog.a("register input monitor");
            FullScreenInputMonitor.e().h(context);
            FullScreenInputMonitor.e().c(this);
            this.f16239c = true;
        }
    }

    public void h(VibrateSceneState vibrateSceneState) {
        this.f16240h.b(vibrateSceneState);
    }

    public abstract void i();

    public synchronized void j() {
        if (this.f16239c) {
            AIVibrateLog.a("unregister input monitor");
            FullScreenInputMonitor.e().i(this);
            FullScreenInputMonitor.e().k();
            this.f16239c = false;
        }
    }
}
