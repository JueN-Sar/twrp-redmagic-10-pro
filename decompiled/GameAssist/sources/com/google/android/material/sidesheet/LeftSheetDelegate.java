package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes.dex */
final class LeftSheetDelegate extends SheetDelegate {

    /* renamed from: a, reason: collision with root package name */
    final SideSheetBehavior f15229a;

    LeftSheetDelegate(SideSheetBehavior sideSheetBehavior) {
        this.f15229a = sideSheetBehavior;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.leftMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    float b(int i2) {
        float e2 = e();
        return (i2 - e2) / (d() - e2);
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int c(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.leftMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int d() {
        return Math.max(0, this.f15229a.p0() + this.f15229a.n0());
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int e() {
        return (-this.f15229a.g0()) - this.f15229a.n0();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int f() {
        return this.f15229a.n0();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int g() {
        return -this.f15229a.g0();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int h(View view) {
        return view.getRight() + this.f15229a.n0();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int i(CoordinatorLayout coordinatorLayout) {
        return coordinatorLayout.getLeft();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int j() {
        return 1;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    boolean k(float f2) {
        return f2 > 0.0f;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    boolean l(View view) {
        return view.getRight() < (d() - e()) / 2;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    boolean m(float f2, float f3) {
        return SheetUtils.a(f2, f3) && Math.abs(f2) > ((float) this.f15229a.r0());
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    boolean n(View view, float f2) {
        return Math.abs(((float) view.getLeft()) + (f2 * this.f15229a.l0())) > this.f15229a.m0();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    void o(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
        marginLayoutParams.leftMargin = i2;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    void p(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3) {
        if (i2 <= this.f15229a.q0()) {
            marginLayoutParams.leftMargin = i3;
        }
    }
}
