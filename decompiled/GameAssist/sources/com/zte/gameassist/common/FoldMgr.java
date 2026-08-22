package com.zte.gameassist.common;

import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import com.zte.gameassist.BaseApplication;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class FoldMgr {

    /* renamed from: e, reason: collision with root package name */
    private static final String f16487e = "FoldMgr";

    /* renamed from: f, reason: collision with root package name */
    private static volatile FoldMgr f16488f;

    /* renamed from: a, reason: collision with root package name */
    private DisplayManager f16489a;

    /* renamed from: b, reason: collision with root package name */
    private Handler f16490b = new Handler(Looper.getMainLooper());

    /* renamed from: c, reason: collision with root package name */
    private final List f16491c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    private int f16492d = 0;

    public interface Callback {
        void onDisplayInUseStateChanged(int i2);
    }

    private FoldMgr() {
        if (this.f16489a == null) {
            this.f16489a = (DisplayManager) BaseApplication.a().getSystemService("display");
        }
    }

    public static FoldMgr c() {
        if (f16488f == null) {
            synchronized (FoldMgr.class) {
                try {
                    if (f16488f == null) {
                        f16488f = new FoldMgr();
                    }
                } finally {
                }
            }
        }
        return f16488f;
    }

    public static boolean f() {
        return ZteFeature.isSupportFoldBig();
    }

    public void a(Callback callback) {
        synchronized (this.f16491c) {
            try {
                if (!this.f16491c.contains(callback)) {
                    this.f16491c.add(callback);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void b(PrintWriter printWriter) {
        printWriter.println(f16487e + ":");
        printWriter.println("  mState: " + this.f16492d);
    }

    public void d() {
        if (ZteFeature.isSupportFoldBig()) {
            this.f16489a.registerDisplayInUseStateListener(this.f16490b, new DisplayManager.DisplayInUseStateListener() { // from class: com.zte.gameassist.common.g
                public final void onDisplayInUseStateChanged(int i2) {
                    FoldMgr.this.g(i2);
                }
            });
            this.f16492d = this.f16489a.getDisplayInUseState();
            GaLog.e(f16487e, "int state=" + this.f16492d);
        }
    }

    public boolean e() {
        return this.f16492d == 0;
    }

    public void g(int i2) {
        GaLog.e(f16487e, "onDisplayInUseStateChanged state=" + i2);
        this.f16492d = i2;
        RotationMgr.e(BaseApplication.a()).n();
        ArrayList arrayList = new ArrayList();
        synchronized (this.f16491c) {
            arrayList.addAll(this.f16491c);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onDisplayInUseStateChanged(i2);
        }
    }

    public void h(Callback callback) {
        synchronized (this.f16491c) {
            try {
                if (this.f16491c.contains(callback)) {
                    this.f16491c.remove(callback);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
