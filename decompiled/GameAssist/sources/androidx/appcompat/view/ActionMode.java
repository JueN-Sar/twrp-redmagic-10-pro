package androidx.appcompat.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* loaded from: classes.dex */
public abstract class ActionMode {

    /* renamed from: c, reason: collision with root package name */
    private Object f421c;

    /* renamed from: h, reason: collision with root package name */
    private boolean f422h;

    public interface Callback {
        void a(ActionMode actionMode);

        boolean b(ActionMode actionMode, Menu menu);

        boolean c(ActionMode actionMode, MenuItem menuItem);

        boolean d(ActionMode actionMode, Menu menu);
    }

    public abstract void c();

    public abstract View d();

    public abstract Menu e();

    public abstract MenuInflater f();

    public abstract CharSequence g();

    public Object h() {
        return this.f421c;
    }

    public abstract CharSequence i();

    public boolean j() {
        return this.f422h;
    }

    public abstract void k();

    public boolean l() {
        return false;
    }

    public abstract void m(View view);

    public abstract void n(int i2);

    public abstract void o(CharSequence charSequence);

    public void p(Object obj) {
        this.f421c = obj;
    }

    public abstract void q(int i2);

    public abstract void r(CharSequence charSequence);

    public void s(boolean z) {
        this.f422h = z;
    }
}
