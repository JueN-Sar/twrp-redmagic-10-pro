package com.google.android.material.expandable;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes.dex */
public final class ExpandableWidgetHelper {

    /* renamed from: a, reason: collision with root package name */
    private final View f14578a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f14579b = false;

    /* renamed from: c, reason: collision with root package name */
    private int f14580c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public ExpandableWidgetHelper(ExpandableWidget expandableWidget) {
        this.f14578a = (View) expandableWidget;
    }

    private void a() {
        ViewParent parent = this.f14578a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).p(this.f14578a);
        }
    }

    public int b() {
        return this.f14580c;
    }

    public boolean c() {
        return this.f14579b;
    }

    public void d(Bundle bundle) {
        this.f14579b = bundle.getBoolean("expanded", false);
        this.f14580c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.f14579b) {
            a();
        }
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.f14579b);
        bundle.putInt("expandedComponentIdHint", this.f14580c);
        return bundle;
    }

    public void f(int i2) {
        this.f14580c = i2;
    }
}
