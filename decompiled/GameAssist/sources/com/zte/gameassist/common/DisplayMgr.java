package com.zte.gameassist.common;

import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import com.zte.gameassist.BaseApplication;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.DisplayWrapper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DisplayMgr implements DisplayManager.DisplayListener {

    /* renamed from: j, reason: collision with root package name */
    private static final String f16477j = "DisplayMgr";

    /* renamed from: k, reason: collision with root package name */
    private static volatile DisplayMgr f16478k = null;

    /* renamed from: l, reason: collision with root package name */
    public static volatile int f16479l = -1;

    /* renamed from: c, reason: collision with root package name */
    private DisplayManager f16480c;

    /* renamed from: h, reason: collision with root package name */
    private Handler f16481h = new Handler(Looper.getMainLooper());

    /* renamed from: i, reason: collision with root package name */
    private final List f16482i = new ArrayList();

    public interface Callback {
        default void on3DDisplayAdded(int i2) {
        }

        default void on3DDisplayRemoved(int i2) {
        }

        default void onDisplayAdded(int i2) {
        }

        default void onDisplayChanged(int i2) {
        }

        default void onDisplayRemoved(int i2) {
        }
    }

    private DisplayMgr() {
        if (this.f16480c == null) {
            this.f16480c = (DisplayManager) BaseApplication.a().getSystemService("display");
        }
    }

    private void b() {
        for (Display display : this.f16480c.getDisplays()) {
            String name = display.getName();
            int type = DisplayWrapper.getType(display);
            if ("app_3d_display".equals(name) && type == 5) {
                SystemMgr.J = display.getDisplayId();
                f16479l = display.getDisplayId();
                GaLog.e(f16477j, "check3DDisplay 3D id=" + display.getDisplayId());
                ArrayList arrayList = new ArrayList();
                synchronized (this.f16482i) {
                    arrayList.addAll(this.f16482i);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Callback) it.next()).on3DDisplayAdded(f16479l);
                }
            }
        }
    }

    public static DisplayMgr d() {
        if (f16478k == null) {
            synchronized (DisplayMgr.class) {
                try {
                    if (f16478k == null) {
                        f16478k = new DisplayMgr();
                    }
                } finally {
                }
            }
        }
        return f16478k;
    }

    public void a(Callback callback) {
        synchronized (this.f16482i) {
            try {
                if (!this.f16482i.contains(callback)) {
                    this.f16482i.add(callback);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void c(PrintWriter printWriter) {
        printWriter.println(f16477j + ":");
    }

    public void e() {
        this.f16480c.registerDisplayListener(this, this.f16481h);
        b();
    }

    public void f(Callback callback) {
        synchronized (this.f16482i) {
            try {
                if (this.f16482i.contains(callback)) {
                    this.f16482i.remove(callback);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i2) {
        GaLog.e(f16477j, "onDisplayAdded " + i2);
        b();
        ArrayList arrayList = new ArrayList();
        synchronized (this.f16482i) {
            arrayList.addAll(this.f16482i);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onDisplayAdded(i2);
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i2) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.f16482i) {
            arrayList.addAll(this.f16482i);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onDisplayChanged(i2);
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i2) {
        GaLog.e(f16477j, "onDisplayRemoved " + i2);
        ArrayList arrayList = new ArrayList();
        synchronized (this.f16482i) {
            arrayList.addAll(this.f16482i);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onDisplayRemoved(i2);
        }
        if (i2 == f16479l) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((Callback) it2.next()).on3DDisplayRemoved(i2);
            }
        }
    }
}
