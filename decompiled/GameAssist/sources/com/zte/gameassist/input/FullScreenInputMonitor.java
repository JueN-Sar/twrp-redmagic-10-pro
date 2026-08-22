package com.zte.gameassist.input;

import android.content.Context;
import android.os.Looper;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.zte.gameassist.common.DisplayMgr;
import com.zte.gameassist.input.FullScreenInputMonitor;
import com.zte.gameassist.input.InterfaceEventListener;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.InputManagerWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class FullScreenInputMonitor implements InputManagerWrapper.InputEventListener {

    /* renamed from: c, reason: collision with root package name */
    private static volatile FullScreenInputMonitor f16693c;

    /* renamed from: a, reason: collision with root package name */
    private final List f16694a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private InputManagerWrapper.ZteInputEventReceiver f16695b;

    private void d(final InputEvent inputEvent) {
        boolean z = inputEvent instanceof MotionEvent;
        if (!j(z, !z && (inputEvent instanceof KeyEvent), inputEvent) && z) {
            synchronized (this.f16694a) {
                this.f16694a.forEach(new Consumer() { // from class: q.e
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        FullScreenInputMonitor.f(inputEvent, (InterfaceEventListener) obj);
                    }
                });
            }
        }
    }

    public static FullScreenInputMonitor e() {
        if (f16693c == null) {
            synchronized (DisplayMgr.class) {
                try {
                    if (f16693c == null) {
                        f16693c = new FullScreenInputMonitor();
                    }
                } finally {
                }
            }
        }
        return f16693c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void f(InputEvent inputEvent, InterfaceEventListener interfaceEventListener) {
        interfaceEventListener.f((MotionEvent) inputEvent);
    }

    private boolean j(boolean z, boolean z2, InputEvent inputEvent) {
        return !z2 && (!z || (inputEvent.getSource() & 2) == 0);
    }

    public boolean c(InterfaceEventListener interfaceEventListener) {
        synchronized (this.f16694a) {
            try {
                if (this.f16694a.contains(interfaceEventListener)) {
                    return false;
                }
                this.f16694a.add(interfaceEventListener);
                if (this.f16694a.size() == 1) {
                    h(ContextWrapper.getContext());
                }
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public synchronized void h(Context context) {
        if (this.f16695b != null) {
            GaLog.e("FullScreenInputMonitor", "registerInputEventListener already exist");
        } else {
            this.f16695b = InputManagerWrapper.initGestureInputMonitor(context, "gameassist_fullscreen", Looper.getMainLooper(), this);
            GaLog.e("FullScreenInputMonitor", "registerInputEventListener");
        }
    }

    public boolean i(InterfaceEventListener interfaceEventListener) {
        synchronized (this.f16694a) {
            try {
                if (!this.f16694a.contains(interfaceEventListener)) {
                    return false;
                }
                this.f16694a.remove(interfaceEventListener);
                if (this.f16694a.size() == 0) {
                    k();
                }
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public synchronized void k() {
        if (this.f16695b != null && this.f16694a.size() == 0) {
            this.f16695b.dispose();
            this.f16695b = null;
            GaLog.e("FullScreenInputMonitor", "unregisterInputEventListener");
        }
    }

    @Override // com.zte.shared.wrapper.InputManagerWrapper.InputEventListener
    public void onDispose() {
        this.f16694a.forEach(new Consumer() { // from class: q.d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((InterfaceEventListener) obj).onDispose();
            }
        });
    }

    @Override // com.zte.shared.wrapper.InputManagerWrapper.InputEventListener
    public void onInputEvent(InputEvent inputEvent) {
        d(inputEvent);
    }
}
