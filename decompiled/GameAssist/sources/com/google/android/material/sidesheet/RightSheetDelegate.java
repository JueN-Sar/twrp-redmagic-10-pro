package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes.dex */
final class RightSheetDelegate extends SheetDelegate {

    /* renamed from: a, reason: collision with root package name */
    final SideSheetBehavior f15230a;

    RightSheetDelegate(SideSheetBehavior sideSheetBehavior) {
        this.f15230a = sideSheetBehavior;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.rightMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    float b(int i2) {
        float e2 = e();
        return (e2 - i2) / (e2 - d());
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int c(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.rightMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int d() {
        return Math.max(0, (e() - this.f15230a.g0()) - this.f15230a.n0());
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int e() {
        return this.f15230a.q0();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int f() {
        return this.f15230a.q0();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int g() {
        return d();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int h(View view) {
        return view.getLeft() - this.f15230a.n0();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int i(CoordinatorLayout coordinatorLayout) {
        return coordinatorLayout.getRight();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    int j() {
        return 0;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    boolean k(float f2) {
        return f2 < 0.0f;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    boolean l(View view) {
        return view.getLeft() > (e() + d()) / 2;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    boolean m(float f2, float f3) {
        return SheetUtils.a(f2, f3) && Math.abs(f2) > ((float) this.f15230a.r0());
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    boolean n(View view, float f2) {
        return Math.abs(((float) view.getRight()) + (f2 * this.f15230a.l0())) > this.f15230a.m0();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    void o(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
        marginLayoutParams.rightMargin = i2;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    void p(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3) {
        int q0 = this.f15230a.q0();
        if (i2 <= q0) {
            marginLayoutParams.rightMargin = q0 - i2;
        }
    }
}
