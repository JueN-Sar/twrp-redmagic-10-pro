package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes.dex */
class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    /* renamed from: c, reason: collision with root package name */
    private ViewOffsetHelper f13890c;

    /* renamed from: h, reason: collision with root package name */
    private int f13891h;

    /* renamed from: i, reason: collision with root package name */
    private int f13892i;

    public ViewOffsetBehavior() {
        this.f13891h = 0;
        this.f13892i = 0;
    }

    public int J() {
        ViewOffsetHelper viewOffsetHelper = this.f13890c;
        if (viewOffsetHelper != null) {
            return viewOffsetHelper.c();
        }
        return 0;
    }

    protected void K(CoordinatorLayout coordinatorLayout, View view, int i2) {
        coordinatorLayout.M(view, i2);
    }

    public boolean L(int i2) {
        ViewOffsetHelper viewOffsetHelper = this.f13890c;
        if (viewOffsetHelper != null) {
            return viewOffsetHelper.f(i2);
        }
        this.f13891h = i2;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean q(CoordinatorLayout coordinatorLayout, View view, int i2) {
        K(coordinatorLayout, view, i2);
        if (this.f13890c == null) {
            this.f13890c = new ViewOffsetHelper(view);
        }
        this.f13890c.d();
        this.f13890c.a();
        int i3 = this.f13891h;
        if (i3 != 0) {
            this.f13890c.f(i3);
            this.f13891h = 0;
        }
        int i4 = this.f13892i;
        if (i4 == 0) {
            return true;
        }
        this.f13890c.e(i4);
        this.f13892i = 0;
        return true;
    }

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13891h = 0;
        this.f13892i = 0;
    }
}
