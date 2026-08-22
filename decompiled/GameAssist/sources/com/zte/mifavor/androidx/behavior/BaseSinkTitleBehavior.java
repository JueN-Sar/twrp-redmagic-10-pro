package com.zte.mifavor.androidx.behavior;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

/* loaded from: classes2.dex */
public abstract class BaseSinkTitleBehavior extends CoordinatorLayout.Behavior<View> {

    /* renamed from: c, reason: collision with root package name */
    protected int f17109c;

    /* renamed from: h, reason: collision with root package name */
    protected int f17110h;

    /* renamed from: i, reason: collision with root package name */
    protected int f17111i;

    /* renamed from: j, reason: collision with root package name */
    protected int f17112j;

    public void J(int i2) {
        this.f17109c = i2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean j(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return view2 instanceof AppBarLayout;
    }
}
