package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class NestedScrollingParentHelper {

    /* renamed from: a, reason: collision with root package name */
    private int f3354a;

    /* renamed from: b, reason: collision with root package name */
    private int f3355b;

    public NestedScrollingParentHelper(ViewGroup viewGroup) {
    }

    public int a() {
        return this.f3355b | this.f3354a;
    }

    public void b(View view, View view2, int i2) {
        c(view, view2, i2, 0);
    }

    public void c(View view, View view2, int i2, int i3) {
        if (i3 == 1) {
            this.f3355b = i2;
        } else {
            this.f3354a = i2;
        }
    }

    public void d(View view) {
        e(view, 0);
    }

    public void e(View view, int i2) {
        if (i2 == 1) {
            this.f3355b = 0;
        } else {
            this.f3354a = 0;
        }
    }
}
