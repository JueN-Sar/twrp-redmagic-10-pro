package androidx.core.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class MenuHostHelper {

    /* renamed from: a, reason: collision with root package name */
    private final Runnable f3340a;

    /* renamed from: b, reason: collision with root package name */
    private final CopyOnWriteArrayList f3341b = new CopyOnWriteArrayList();

    /* renamed from: c, reason: collision with root package name */
    private final Map f3342c = new HashMap();

    private static class LifecycleContainer {

        /* renamed from: a, reason: collision with root package name */
        final Lifecycle f3343a;

        /* renamed from: b, reason: collision with root package name */
        private LifecycleEventObserver f3344b;

        void a() {
            this.f3343a.c(this.f3344b);
            this.f3344b = null;
        }
    }

    public MenuHostHelper(Runnable runnable) {
        this.f3340a = runnable;
    }

    public void a(MenuProvider menuProvider) {
        this.f3341b.add(menuProvider);
        this.f3340a.run();
    }

    public void b(Menu menu, MenuInflater menuInflater) {
        Iterator it = this.f3341b.iterator();
        while (it.hasNext()) {
            ((MenuProvider) it.next()).d(menu, menuInflater);
        }
    }

    public void c(Menu menu) {
        Iterator it = this.f3341b.iterator();
        while (it.hasNext()) {
            ((MenuProvider) it.next()).a(menu);
        }
    }

    public boolean d(MenuItem menuItem) {
        Iterator it = this.f3341b.iterator();
        while (it.hasNext()) {
            if (((MenuProvider) it.next()).c(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void e(Menu menu) {
        Iterator it = this.f3341b.iterator();
        while (it.hasNext()) {
            ((MenuProvider) it.next()).b(menu);
        }
    }

    public void f(MenuProvider menuProvider) {
        this.f3341b.remove(menuProvider);
        LifecycleContainer lifecycleContainer = (LifecycleContainer) this.f3342c.remove(menuProvider);
        if (lifecycleContainer != null) {
            lifecycleContainer.a();
        }
        this.f3340a.run();
    }
}
