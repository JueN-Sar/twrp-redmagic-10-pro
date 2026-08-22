package com.zte.gameassist.input;

import android.content.Context;
import android.os.Looper;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.zte.gameassist.input.EventDispatcher;
import com.zte.gameassist.input.InterfaceEventListener;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.InputManagerWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class EventDispatcher implements InputManagerWrapper.InputEventListener {

    /* renamed from: a, reason: collision with root package name */
    private final List f16689a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private String f16690b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f16691c;

    /* renamed from: d, reason: collision with root package name */
    private InputManagerWrapper.ZteInputEventReceiver f16692d;

    private void e(final InputEvent inputEvent) {
        boolean z = inputEvent instanceof MotionEvent;
        if (!l(z, !z && (inputEvent instanceof KeyEvent), inputEvent) && z) {
            int action = ((MotionEvent) inputEvent).getAction();
            if (action == 0) {
                this.f16691c = true;
            } else if (action == 1 || action == 3) {
                this.f16691c = false;
            }
            synchronized (this.f16689a) {
                this.f16689a.forEach(new Consumer() { // from class: q.a
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        EventDispatcher.f(inputEvent, (InterfaceEventListener) obj);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void f(InputEvent inputEvent, InterfaceEventListener interfaceEventListener) {
        interfaceEventListener.f((MotionEvent) inputEvent);
    }

    private boolean l(boolean z, boolean z2, InputEvent inputEvent) {
        return !z2 && (!z || (inputEvent.getSource() & 2) == 0);
    }

    public boolean d(InterfaceEventListener interfaceEventListener) {
        synchronized (this.f16689a) {
            try {
                if (this.f16689a.contains(interfaceEventListener)) {
                    return false;
                }
                this.f16689a.add(interfaceEventListener);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void i() {
        InputManagerWrapper.ZteInputEventReceiver zteInputEventReceiver = this.f16692d;
        if (zteInputEventReceiver != null) {
            zteInputEventReceiver.pilferPointers();
        }
    }

    public synchronized void j(Context context, String str) {
        if (this.f16692d != null) {
            GaLog.e("EventDispatcher", "not registerInputEventListener " + str);
            return;
        }
        this.f16690b = str;
        ContextWrapper.updateDisplay(context);
        this.f16692d = InputManagerWrapper.initGestureInputMonitor(context, str, Looper.getMainLooper(), this);
        GaLog.e("EventDispatcher", "registerInputEventListener " + this.f16690b);
    }

    public boolean k(InterfaceEventListener interfaceEventListener) {
        synchronized (this.f16689a) {
            try {
                if (!this.f16689a.contains(interfaceEventListener)) {
                    return false;
                }
                this.f16689a.remove(interfaceEventListener);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public synchronized void m() {
        InputManagerWrapper.ZteInputEventReceiver zteInputEventReceiver = this.f16692d;
        if (zteInputEventReceiver != null) {
            zteInputEventReceiver.dispose();
            this.f16692d = null;
            this.f16690b = null;
            GaLog.e("EventDispatcher", "unregisterInputEventListener " + this.f16690b + " " + this.f16691c);
        }
    }

    @Override // com.zte.shared.wrapper.InputManagerWrapper.InputEventListener
    public void onDispose() {
        this.f16689a.forEach(new Consumer() { // from class: q.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((InterfaceEventListener) obj).onDispose();
            }
        });
        this.f16691c = false;
    }

    @Override // com.zte.shared.wrapper.InputManagerWrapper.InputEventListener
    public void onInputEvent(InputEvent inputEvent) {
        e(inputEvent);
    }

    @Override // com.zte.shared.wrapper.InputManagerWrapper.InputEventListener
    public void onReceiverInit(InputManagerWrapper.ZteInputEventReceiver zteInputEventReceiver) {
        this.f16689a.forEach(new Consumer() { // from class: q.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((InterfaceEventListener) obj).e();
            }
        });
    }
}
