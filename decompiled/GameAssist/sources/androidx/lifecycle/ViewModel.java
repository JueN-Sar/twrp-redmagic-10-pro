package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class ViewModel {

    /* renamed from: a, reason: collision with root package name */
    private final Map f4392a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Set f4393b = new LinkedHashSet();

    /* renamed from: c, reason: collision with root package name */
    private volatile boolean f4394c = false;

    private static void b(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    final void a() {
        this.f4394c = true;
        Map map = this.f4392a;
        if (map != null) {
            synchronized (map) {
                try {
                    Iterator it = this.f4392a.values().iterator();
                    while (it.hasNext()) {
                        b(it.next());
                    }
                } finally {
                }
            }
        }
        Set set = this.f4393b;
        if (set != null) {
            synchronized (set) {
                try {
                    Iterator it2 = this.f4393b.iterator();
                    while (it2.hasNext()) {
                        b((Closeable) it2.next());
                    }
                } finally {
                }
            }
        }
        d();
    }

    Object c(String str) {
        Object obj;
        Map map = this.f4392a;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            obj = this.f4392a.get(str);
        }
        return obj;
    }

    protected void d() {
    }

    Object e(String str, Object obj) {
        Object obj2;
        synchronized (this.f4392a) {
            try {
                obj2 = this.f4392a.get(str);
                if (obj2 == null) {
                    this.f4392a.put(str, obj);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (obj2 != null) {
            obj = obj2;
        }
        if (this.f4394c) {
            b(obj);
        }
        return obj;
    }
}
