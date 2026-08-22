package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
class FragmentLifecycleCallbacksDispatcher {

    /* renamed from: a, reason: collision with root package name */
    private final CopyOnWriteArrayList f4041a = new CopyOnWriteArrayList();

    /* renamed from: b, reason: collision with root package name */
    private final FragmentManager f4042b;

    private static final class FragmentLifecycleCallbacksHolder {

        /* renamed from: a, reason: collision with root package name */
        final FragmentManager.FragmentLifecycleCallbacks f4043a;

        /* renamed from: b, reason: collision with root package name */
        final boolean f4044b;

        FragmentLifecycleCallbacksHolder(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
            this.f4043a = fragmentLifecycleCallbacks;
            this.f4044b = z;
        }
    }

    FragmentLifecycleCallbacksDispatcher(FragmentManager fragmentManager) {
        this.f4042b = fragmentManager;
    }

    void a(Fragment fragment, Bundle bundle, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().a(fragment, bundle, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.a(this.f4042b, fragment, bundle);
            }
        }
    }

    void b(Fragment fragment, boolean z) {
        Context r2 = this.f4042b.A0().r();
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().b(fragment, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.b(this.f4042b, fragment, r2);
            }
        }
    }

    void c(Fragment fragment, Bundle bundle, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().c(fragment, bundle, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.c(this.f4042b, fragment, bundle);
            }
        }
    }

    void d(Fragment fragment, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().d(fragment, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.d(this.f4042b, fragment);
            }
        }
    }

    void e(Fragment fragment, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().e(fragment, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.e(this.f4042b, fragment);
            }
        }
    }

    void f(Fragment fragment, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().f(fragment, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.f(this.f4042b, fragment);
            }
        }
    }

    void g(Fragment fragment, boolean z) {
        Context r2 = this.f4042b.A0().r();
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().g(fragment, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.g(this.f4042b, fragment, r2);
            }
        }
    }

    void h(Fragment fragment, Bundle bundle, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().h(fragment, bundle, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.h(this.f4042b, fragment, bundle);
            }
        }
    }

    void i(Fragment fragment, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().i(fragment, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.i(this.f4042b, fragment);
            }
        }
    }

    void j(Fragment fragment, Bundle bundle, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().j(fragment, bundle, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.j(this.f4042b, fragment, bundle);
            }
        }
    }

    void k(Fragment fragment, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().k(fragment, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.k(this.f4042b, fragment);
            }
        }
    }

    void l(Fragment fragment, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().l(fragment, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.l(this.f4042b, fragment);
            }
        }
    }

    void m(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().m(fragment, view, bundle, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.m(this.f4042b, fragment, view, bundle);
            }
        }
    }

    void n(Fragment fragment, boolean z) {
        Fragment D0 = this.f4042b.D0();
        if (D0 != null) {
            D0.O().C0().n(fragment, true);
        }
        Iterator it = this.f4041a.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder = (FragmentLifecycleCallbacksHolder) it.next();
            if (!z || fragmentLifecycleCallbacksHolder.f4044b) {
                fragmentLifecycleCallbacksHolder.f4043a.n(this.f4042b, fragment);
            }
        }
    }

    public void o(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        this.f4041a.add(new FragmentLifecycleCallbacksHolder(fragmentLifecycleCallbacks, z));
    }

    public void p(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        synchronized (this.f4041a) {
            try {
                int size = this.f4041a.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    if (((FragmentLifecycleCallbacksHolder) this.f4041a.get(i2)).f4043a == fragmentLifecycleCallbacks) {
                        this.f4041a.remove(i2);
                        break;
                    }
                    i2++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
