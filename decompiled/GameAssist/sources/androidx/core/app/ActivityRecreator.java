package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@RestrictTo
/* loaded from: classes.dex */
final class ActivityRecreator {

    /* renamed from: a, reason: collision with root package name */
    protected static final Class f2622a;

    /* renamed from: b, reason: collision with root package name */
    protected static final Field f2623b;

    /* renamed from: c, reason: collision with root package name */
    protected static final Field f2624c;

    /* renamed from: d, reason: collision with root package name */
    protected static final Method f2625d;

    /* renamed from: e, reason: collision with root package name */
    protected static final Method f2626e;

    /* renamed from: f, reason: collision with root package name */
    protected static final Method f2627f;

    /* renamed from: g, reason: collision with root package name */
    private static final Handler f2628g = new Handler(Looper.getMainLooper());

    /* renamed from: androidx.core.app.ActivityRecreator$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ LifecycleCheckCallbacks f2629c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ Object f2630h;

        @Override // java.lang.Runnable
        public void run() {
            this.f2629c.f2635c = this.f2630h;
        }
    }

    /* renamed from: androidx.core.app.ActivityRecreator$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Application f2631c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ LifecycleCheckCallbacks f2632h;

        @Override // java.lang.Runnable
        public void run() {
            this.f2631c.unregisterActivityLifecycleCallbacks(this.f2632h);
        }
    }

    private static final class LifecycleCheckCallbacks implements Application.ActivityLifecycleCallbacks {

        /* renamed from: c, reason: collision with root package name */
        Object f2635c;

        /* renamed from: h, reason: collision with root package name */
        private Activity f2636h;

        /* renamed from: i, reason: collision with root package name */
        private final int f2637i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f2638j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f2639k;

        /* renamed from: l, reason: collision with root package name */
        private boolean f2640l;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f2636h == activity) {
                this.f2636h = null;
                this.f2639k = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f2639k || this.f2640l || this.f2638j || !ActivityRecreator.h(this.f2635c, this.f2637i, activity)) {
                return;
            }
            this.f2640l = true;
            this.f2635c = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f2636h == activity) {
                this.f2638j = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static {
        Class a2 = a();
        f2622a = a2;
        f2623b = b();
        f2624c = f();
        f2625d = d(a2);
        f2626e = c(a2);
        f2627f = e(a2);
    }

    private static Class a() {
        try {
            return Class.forName("android.app.ActivityThread");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Field b() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mMainThread");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method c(Class cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method d(Class cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE, String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method e(Class cls) {
        if (g() && cls != null) {
            try {
                Class cls2 = Integer.TYPE;
                Class cls3 = Boolean.TYPE;
                Method declaredMethod = cls.getDeclaredMethod("requestRelaunchActivity", IBinder.class, List.class, List.class, cls2, cls3, Configuration.class, Configuration.class, cls3, cls3);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private static Field f() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mToken");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean g() {
        return false;
    }

    protected static boolean h(Object obj, int i2, Activity activity) {
        try {
            final Object obj2 = f2624c.get(activity);
            if (obj2 == obj && activity.hashCode() == i2) {
                final Object obj3 = f2623b.get(activity);
                f2628g.postAtFrontOfQueue(new Runnable() { // from class: androidx.core.app.ActivityRecreator.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Method method = ActivityRecreator.f2625d;
                            if (method != null) {
                                method.invoke(obj3, obj2, Boolean.FALSE, "AppCompat recreation");
                            } else {
                                ActivityRecreator.f2626e.invoke(obj3, obj2, Boolean.FALSE);
                            }
                        } catch (RuntimeException e2) {
                            if (e2.getClass() == RuntimeException.class && e2.getMessage() != null && e2.getMessage().startsWith("Unable to stop")) {
                                throw e2;
                            }
                        } catch (Throwable th) {
                            Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
                        }
                    }
                });
                return true;
            }
            return false;
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while fetching field values", th);
            return false;
        }
    }
}
