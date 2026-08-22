package androidx.fragment.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
public class FragmentController {

    /* renamed from: a, reason: collision with root package name */
    private final FragmentHostCallback f4031a;

    private FragmentController(FragmentHostCallback fragmentHostCallback) {
        this.f4031a = fragmentHostCallback;
    }

    public static FragmentController b(FragmentHostCallback fragmentHostCallback) {
        return new FragmentController((FragmentHostCallback) Preconditions.i(fragmentHostCallback, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        FragmentHostCallback fragmentHostCallback = this.f4031a;
        fragmentHostCallback.f4037k.n(fragmentHostCallback, fragmentHostCallback, fragment);
    }

    public void c() {
        this.f4031a.f4037k.A();
    }

    public boolean d(MenuItem menuItem) {
        return this.f4031a.f4037k.D(menuItem);
    }

    public void e() {
        this.f4031a.f4037k.E();
    }

    public void f() {
        this.f4031a.f4037k.G();
    }

    public void g() {
        this.f4031a.f4037k.P();
    }

    public void h() {
        this.f4031a.f4037k.T();
    }

    public void i() {
        this.f4031a.f4037k.U();
    }

    public void j() {
        this.f4031a.f4037k.W();
    }

    public boolean k() {
        return this.f4031a.f4037k.d0(true);
    }

    public FragmentManager l() {
        return this.f4031a.f4037k;
    }

    public void m() {
        this.f4031a.f4037k.c1();
    }

    public View n(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f4031a.f4037k.B0().onCreateView(view, str, context, attributeSet);
    }
}
