package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes.dex */
abstract class SheetDelegate {
    SheetDelegate() {
    }

    abstract int a(ViewGroup.MarginLayoutParams marginLayoutParams);

    abstract float b(int i2);

    abstract int c(ViewGroup.MarginLayoutParams marginLayoutParams);

    abstract int d();

    abstract int e();

    abstract int f();

    abstract int g();

    abstract int h(View view);

    abstract int i(CoordinatorLayout coordinatorLayout);

    abstract int j();

    abstract boolean k(float f2);

    abstract boolean l(View view);

    abstract boolean m(float f2, float f3);

    abstract boolean n(View view, float f2);

    abstract void o(ViewGroup.MarginLayoutParams marginLayoutParams, int i2);

    abstract void p(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3);
}
