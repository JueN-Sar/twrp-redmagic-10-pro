package androidx.core.view;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.RestrictTo;

/* loaded from: classes.dex */
public abstract class ActionProvider {

    /* renamed from: a, reason: collision with root package name */
    private final Context f3310a;

    /* renamed from: b, reason: collision with root package name */
    private SubUiVisibilityListener f3311b;

    /* renamed from: c, reason: collision with root package name */
    private VisibilityListener f3312c;

    @RestrictTo
    public interface SubUiVisibilityListener {
        void onSubUiVisibilityChanged(boolean z);
    }

    public interface VisibilityListener {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public ActionProvider(Context context) {
        this.f3310a = context;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public abstract View c();

    public View d(MenuItem menuItem) {
        return c();
    }

    public boolean e() {
        return false;
    }

    public void f(SubMenu subMenu) {
    }

    public boolean g() {
        return false;
    }

    public void h() {
        this.f3312c = null;
        this.f3311b = null;
    }

    public void i(SubUiVisibilityListener subUiVisibilityListener) {
        this.f3311b = subUiVisibilityListener;
    }

    public void j(VisibilityListener visibilityListener) {
        if (this.f3312c != null && visibilityListener != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f3312c = visibilityListener;
    }

    public void k(boolean z) {
        SubUiVisibilityListener subUiVisibilityListener = this.f3311b;
        if (subUiVisibilityListener != null) {
            subUiVisibilityListener.onSubUiVisibilityChanged(z);
        }
    }
}
