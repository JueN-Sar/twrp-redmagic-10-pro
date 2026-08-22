package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

@KeepForSdk
/* loaded from: classes.dex */
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    /* renamed from: k, reason: collision with root package name */
    private static final BackgroundDetector f10558k = new BackgroundDetector();

    /* renamed from: c, reason: collision with root package name */
    private final AtomicBoolean f10559c = new AtomicBoolean();

    /* renamed from: h, reason: collision with root package name */
    private final AtomicBoolean f10560h = new AtomicBoolean();

    /* renamed from: i, reason: collision with root package name */
    private final ArrayList f10561i = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    private boolean f10562j = false;

    @KeepForSdk
    public interface BackgroundStateChangeListener {
        void a(boolean z);
    }

    private BackgroundDetector() {
    }

    public static BackgroundDetector b() {
        return f10558k;
    }

    public static void c(Application application) {
        BackgroundDetector backgroundDetector = f10558k;
        synchronized (backgroundDetector) {
            try {
                if (!backgroundDetector.f10562j) {
                    application.registerActivityLifecycleCallbacks(backgroundDetector);
                    application.registerComponentCallbacks(backgroundDetector);
                    backgroundDetector.f10562j = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void f(boolean z) {
        synchronized (f10558k) {
            try {
                Iterator it = this.f10561i.iterator();
                while (it.hasNext()) {
                    ((BackgroundStateChangeListener) it.next()).a(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void a(BackgroundStateChangeListener backgroundStateChangeListener) {
        synchronized (f10558k) {
            this.f10561i.add(backgroundStateChangeListener);
        }
    }

    public boolean d() {
        return this.f10559c.get();
    }

    public boolean e(boolean z) {
        if (!this.f10560h.get()) {
            if (!PlatformVersion.a()) {
                return z;
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.f10560h.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.f10559c.set(true);
            }
        }
        return d();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        AtomicBoolean atomicBoolean = this.f10560h;
        boolean compareAndSet = this.f10559c.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (compareAndSet) {
            f(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        AtomicBoolean atomicBoolean = this.f10560h;
        boolean compareAndSet = this.f10559c.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (compareAndSet) {
            f(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i2) {
        if (i2 == 20 && this.f10559c.compareAndSet(false, true)) {
            this.f10560h.set(true);
            f(true);
        }
    }
}
