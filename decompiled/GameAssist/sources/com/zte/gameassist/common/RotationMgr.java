package com.zte.gameassist.common;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class RotationMgr implements GameMonitor.Callback {

    /* renamed from: p, reason: collision with root package name */
    private static final String f16539p = "RotationMgr";

    /* renamed from: q, reason: collision with root package name */
    private static volatile RotationMgr f16540q;

    /* renamed from: r, reason: collision with root package name */
    protected static int f16541r;

    /* renamed from: s, reason: collision with root package name */
    public static int f16542s;
    public static int t;

    /* renamed from: c, reason: collision with root package name */
    private DisplayManager f16543c;

    /* renamed from: m, reason: collision with root package name */
    private Context f16549m;

    /* renamed from: h, reason: collision with root package name */
    private final Handler f16544h = new Handler(Looper.getMainLooper());

    /* renamed from: i, reason: collision with root package name */
    private Point f16545i = new Point();

    /* renamed from: j, reason: collision with root package name */
    private boolean f16546j = false;

    /* renamed from: k, reason: collision with root package name */
    private boolean f16547k = false;

    /* renamed from: l, reason: collision with root package name */
    private boolean f16548l = false;

    /* renamed from: n, reason: collision with root package name */
    private final List f16550n = new ArrayList();

    /* renamed from: o, reason: collision with root package name */
    private Runnable f16551o = new Runnable() { // from class: com.zte.gameassist.common.RotationMgr.1
        @Override // java.lang.Runnable
        public void run() {
            SystemMgr.y(RotationMgr.this.f16549m).q();
        }
    };

    public interface Callback {
        void onRotationChanged(int i2);
    }

    private RotationMgr(Context context) {
        if (this.f16543c == null) {
            this.f16549m = context;
            this.f16543c = (DisplayManager) context.getSystemService("display");
        }
        SystemMgr.y(this.f16549m).h(this);
    }

    public static RotationMgr e(Context context) {
        if (f16540q == null) {
            synchronized (RotationMgr.class) {
                try {
                    if (f16540q == null) {
                        f16540q = new RotationMgr(context);
                    }
                } finally {
                }
            }
        }
        return f16540q;
    }

    public static int f() {
        return t;
    }

    public static int g() {
        return f16542s;
    }

    public static int h() {
        return f16541r;
    }

    public static boolean j() {
        int i2 = f16541r;
        return i2 == 1 || i2 == 3;
    }

    public static boolean k() {
        int i2 = f16541r;
        return i2 == 0 || i2 == 2;
    }

    private void l(int i2) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.f16550n) {
            arrayList.addAll(this.f16550n);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onRotationChanged(i2);
        }
    }

    public void c(Callback callback) {
        synchronized (this.f16550n) {
            try {
                if (!this.f16550n.contains(callback)) {
                    this.f16550n.add(callback);
                    if (callback != null) {
                        callback.onRotationChanged(f16541r);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void d(PrintWriter printWriter) {
        printWriter.println(f16539p + ":");
        printWriter.println("  sLcdWidth=" + f16542s);
        printWriter.println("  sLcdHeight=" + t);
        printWriter.println("  sDeviceRotation=" + f16541r);
        printWriter.println("  mIsHorizontal=" + this.f16546j);
    }

    public void i() {
        GaLog.a(f16539p, "init");
        m(this.f16549m.getResources().getConfiguration(), true);
    }

    public void m(Configuration configuration, boolean z) {
        if (this.f16547k) {
            this.f16547k = false;
            o();
        }
        boolean z2 = configuration.orientation == 2;
        if (z2 != this.f16546j || z) {
            this.f16546j = z2;
            GaLog.a(f16539p, "onConfigurationChanged mIsHorizontal=" + this.f16546j);
            o();
            l(f16541r);
            if (!k()) {
                this.f16544h.removeCallbacks(this.f16551o);
            } else {
                this.f16544h.removeCallbacks(this.f16551o);
                this.f16544h.postDelayed(this.f16551o, 500L);
            }
        }
    }

    public void n() {
        this.f16547k = true;
    }

    public void o() {
        Display display = this.f16543c.getDisplay(0);
        f16541r = display.getRotation();
        display.getRealSize(this.f16545i);
        Point point = this.f16545i;
        int i2 = point.x;
        int i3 = point.y;
        f16542s = i2 < i3 ? i2 : i3;
        if (i2 <= i3) {
            i2 = i3;
        }
        t = i2;
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public void onGameStart() {
        if (this.f16548l) {
            return;
        }
        this.f16548l = true;
        this.f16544h.postDelayed(new Runnable() { // from class: com.zte.gameassist.common.RotationMgr.2
            @Override // java.lang.Runnable
            public void run() {
                GaLog.a(RotationMgr.f16539p, "onGameStart mAlreadyStartGame");
                RotationMgr rotationMgr = RotationMgr.this;
                rotationMgr.m(rotationMgr.f16549m.getResources().getConfiguration(), true);
            }
        }, 2000L);
    }

    public void p(Callback callback) {
        synchronized (this.f16550n) {
            try {
                if (this.f16550n.contains(callback)) {
                    this.f16550n.remove(callback);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
