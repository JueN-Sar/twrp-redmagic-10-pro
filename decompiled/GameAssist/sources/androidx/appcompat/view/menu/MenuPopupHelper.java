package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.view.GravityCompat;

@RestrictTo
/* loaded from: classes.dex */
public class MenuPopupHelper implements MenuHelper {

    /* renamed from: a, reason: collision with root package name */
    private final Context f606a;

    /* renamed from: b, reason: collision with root package name */
    private final MenuBuilder f607b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f608c;

    /* renamed from: d, reason: collision with root package name */
    private final int f609d;

    /* renamed from: e, reason: collision with root package name */
    private final int f610e;

    /* renamed from: f, reason: collision with root package name */
    private View f611f;

    /* renamed from: g, reason: collision with root package name */
    private int f612g;

    /* renamed from: h, reason: collision with root package name */
    private boolean f613h;

    /* renamed from: i, reason: collision with root package name */
    private MenuPresenter.Callback f614i;

    /* renamed from: j, reason: collision with root package name */
    private MenuPopup f615j;

    /* renamed from: k, reason: collision with root package name */
    private PopupWindow.OnDismissListener f616k;

    /* renamed from: l, reason: collision with root package name */
    private final PopupWindow.OnDismissListener f617l;

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z, int i2) {
        this(context, menuBuilder, view, z, i2, 0);
    }

    private MenuPopup a() {
        Display defaultDisplay = ((WindowManager) this.f606a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        MenuPopup cascadingMenuPopup = Math.min(point.x, point.y) >= this.f606a.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width) ? new CascadingMenuPopup(this.f606a, this.f611f, this.f609d, this.f610e, this.f608c) : new StandardMenuPopup(this.f606a, this.f607b, this.f611f, this.f609d, this.f610e, this.f608c);
        cascadingMenuPopup.g(this.f607b);
        cascadingMenuPopup.p(this.f617l);
        cascadingMenuPopup.k(this.f611f);
        cascadingMenuPopup.c(this.f614i);
        cascadingMenuPopup.m(this.f613h);
        cascadingMenuPopup.n(this.f612g);
        return cascadingMenuPopup;
    }

    private void l(int i2, int i3, boolean z, boolean z2) {
        MenuPopup c2 = c();
        c2.q(z2);
        if (z) {
            if ((GravityCompat.b(this.f612g, this.f611f.getLayoutDirection()) & 7) == 5) {
                i2 -= this.f611f.getWidth();
            }
            c2.o(i2);
            c2.r(i3);
            int i4 = (int) ((this.f606a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            c2.l(new Rect(i2 - i4, i3 - i4, i2 + i4, i3 + i4));
        }
        c2.show();
    }

    public void b() {
        if (d()) {
            this.f615j.dismiss();
        }
    }

    public MenuPopup c() {
        if (this.f615j == null) {
            this.f615j = a();
        }
        return this.f615j;
    }

    public boolean d() {
        MenuPopup menuPopup = this.f615j;
        return menuPopup != null && menuPopup.isShowing();
    }

    protected void e() {
        this.f615j = null;
        PopupWindow.OnDismissListener onDismissListener = this.f616k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(View view) {
        this.f611f = view;
    }

    public void g(boolean z) {
        this.f613h = z;
        MenuPopup menuPopup = this.f615j;
        if (menuPopup != null) {
            menuPopup.m(z);
        }
    }

    public void h(int i2) {
        this.f612g = i2;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.f616k = onDismissListener;
    }

    public void j(MenuPresenter.Callback callback) {
        this.f614i = callback;
        MenuPopup menuPopup = this.f615j;
        if (menuPopup != null) {
            menuPopup.c(callback);
        }
    }

    public void k() {
        if (!m()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f611f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i2, int i3) {
        if (d()) {
            return true;
        }
        if (this.f611f == null) {
            return false;
        }
        l(i2, i3, true, true);
        return true;
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z, int i2, int i3) {
        this.f612g = 8388611;
        this.f617l = new PopupWindow.OnDismissListener() { // from class: androidx.appcompat.view.menu.MenuPopupHelper.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                MenuPopupHelper.this.e();
            }
        };
        this.f606a = context;
        this.f607b = menuBuilder;
        this.f611f = view;
        this.f608c = z;
        this.f609d = i2;
        this.f610e = i3;
    }
}
