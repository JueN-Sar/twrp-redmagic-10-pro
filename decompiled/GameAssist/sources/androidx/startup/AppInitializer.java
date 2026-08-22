package androidx.startup;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.tracing.Trace;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class AppInitializer {

    /* renamed from: d, reason: collision with root package name */
    private static volatile AppInitializer f5359d;

    /* renamed from: e, reason: collision with root package name */
    private static final Object f5360e = new Object();

    /* renamed from: c, reason: collision with root package name */
    final Context f5363c;

    /* renamed from: b, reason: collision with root package name */
    final Set f5362b = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    final Map f5361a = new HashMap();

    AppInitializer(Context context) {
        this.f5363c = context.getApplicationContext();
    }

    private Object d(Class cls, Set set) {
        Object obj;
        if (Trace.isEnabled()) {
            try {
                Trace.beginSection(cls.getSimpleName());
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        if (set.contains(cls)) {
            throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", cls.getName()));
        }
        if (this.f5361a.containsKey(cls)) {
            obj = this.f5361a.get(cls);
        } else {
            set.add(cls);
            try {
                Initializer initializer = (Initializer) cls.getDeclaredConstructor(null).newInstance(null);
                List<Class> a2 = initializer.a();
                if (!a2.isEmpty()) {
                    for (Class cls2 : a2) {
                        if (!this.f5361a.containsKey(cls2)) {
                            d(cls2, set);
                        }
                    }
                }
                obj = initializer.create(this.f5363c);
                set.remove(cls);
                this.f5361a.put(cls, obj);
            } catch (Throwable th2) {
                throw new StartupException(th2);
            }
        }
        Trace.endSection();
        return obj;
    }

    public static AppInitializer e(Context context) {
        if (f5359d == null) {
            synchronized (f5360e) {
                try {
                    if (f5359d == null) {
                        f5359d = new AppInitializer(context);
                    }
                } finally {
                }
            }
        }
        return f5359d;
    }

    void a() {
        try {
            try {
                Trace.beginSection("Startup");
                b(this.f5363c.getPackageManager().getProviderInfo(new ComponentName(this.f5363c.getPackageName(), InitializationProvider.class.getName()), 128).metaData);
            } catch (PackageManager.NameNotFoundException e2) {
                throw new StartupException(e2);
            }
        } finally {
            Trace.endSection();
        }
    }

    void b(Bundle bundle) {
        String string = this.f5363c.getString(R.string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet = new HashSet();
                for (String str : bundle.keySet()) {
                    if (string.equals(bundle.getString(str, null))) {
                        Class<?> cls = Class.forName(str);
                        if (Initializer.class.isAssignableFrom(cls)) {
                            this.f5362b.add(cls);
                        }
                    }
                }
                Iterator it = this.f5362b.iterator();
                while (it.hasNext()) {
                    d((Class) it.next(), hashSet);
                }
            } catch (ClassNotFoundException e2) {
                throw new StartupException(e2);
            }
        }
    }

    Object c(Class cls) {
        Object obj;
        synchronized (f5360e) {
            try {
                obj = this.f5361a.get(cls);
                if (obj == null) {
                    obj = d(cls, new HashSet());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    public Object f(Class cls) {
        return c(cls);
    }

    public boolean g(Class cls) {
        return this.f5362b.contains(cls);
    }
}
