package androidx.core.view;

import android.view.View;
import android.view.ViewParent;

/* loaded from: classes.dex */
public class NestedScrollingChildHelper {

    /* renamed from: a, reason: collision with root package name */
    private ViewParent f3349a;

    /* renamed from: b, reason: collision with root package name */
    private ViewParent f3350b;

    /* renamed from: c, reason: collision with root package name */
    private final View f3351c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3352d;

    /* renamed from: e, reason: collision with root package name */
    private int[] f3353e;

    public NestedScrollingChildHelper(View view) {
        this.f3351c = view;
    }

    private boolean g(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        ViewParent h2;
        int i7;
        int i8;
        int[] iArr3;
        if (!l() || (h2 = h(i6)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f3351c.getLocationInWindow(iArr);
            i7 = iArr[0];
            i8 = iArr[1];
        } else {
            i7 = 0;
            i8 = 0;
        }
        if (iArr2 == null) {
            int[] i9 = i();
            i9[0] = 0;
            i9[1] = 0;
            iArr3 = i9;
        } else {
            iArr3 = iArr2;
        }
        ViewParentCompat.d(h2, this.f3351c, i2, i3, i4, i5, i6, iArr3);
        if (iArr != null) {
            this.f3351c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i7;
            iArr[1] = iArr[1] - i8;
        }
        return true;
    }

    private ViewParent h(int i2) {
        if (i2 == 0) {
            return this.f3349a;
        }
        if (i2 != 1) {
            return null;
        }
        return this.f3350b;
    }

    private int[] i() {
        if (this.f3353e == null) {
            this.f3353e = new int[2];
        }
        return this.f3353e;
    }

    private void n(int i2, ViewParent viewParent) {
        if (i2 == 0) {
            this.f3349a = viewParent;
        } else {
            if (i2 != 1) {
                return;
            }
            this.f3350b = viewParent;
        }
    }

    public boolean a(float f2, float f3, boolean z) {
        ViewParent h2;
        if (!l() || (h2 = h(0)) == null) {
            return false;
        }
        return ViewParentCompat.a(h2, this.f3351c, f2, f3, z);
    }

    public boolean b(float f2, float f3) {
        ViewParent h2;
        if (!l() || (h2 = h(0)) == null) {
            return false;
        }
        return ViewParentCompat.b(h2, this.f3351c, f2, f3);
    }

    public boolean c(int i2, int i3, int[] iArr, int[] iArr2) {
        return d(i2, i3, iArr, iArr2, 0);
    }

    public boolean d(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        ViewParent h2;
        int i5;
        int i6;
        if (!l() || (h2 = h(i4)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.f3351c.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i6 = iArr2[1];
        } else {
            i5 = 0;
            i6 = 0;
        }
        if (iArr == null) {
            iArr = i();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        ViewParentCompat.c(h2, this.f3351c, i2, i3, iArr, i4);
        if (iArr2 != null) {
            this.f3351c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i6;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public void e(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        g(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public boolean f(int i2, int i3, int i4, int i5, int[] iArr) {
        return g(i2, i3, i4, i5, iArr, 0, null);
    }

    public boolean j() {
        return k(0);
    }

    public boolean k(int i2) {
        return h(i2) != null;
    }

    public boolean l() {
        return this.f3352d;
    }

    public void m(boolean z) {
        if (this.f3352d) {
            ViewCompat.H0(this.f3351c);
        }
        this.f3352d = z;
    }

    public boolean o(int i2) {
        return p(i2, 0);
    }

    public boolean p(int i2, int i3) {
        if (k(i3)) {
            return true;
        }
        if (!l()) {
            return false;
        }
        View view = this.f3351c;
        for (ViewParent parent = this.f3351c.getParent(); parent != null; parent = parent.getParent()) {
            if (ViewParentCompat.f(parent, view, this.f3351c, i2, i3)) {
                n(i3, parent);
                ViewParentCompat.e(parent, view, this.f3351c, i2, i3);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public void q() {
        r(0);
    }

    public void r(int i2) {
        ViewParent h2 = h(i2);
        if (h2 != null) {
            ViewParentCompat.g(h2, this.f3351c, i2);
            n(i2, null);
        }
    }
}
