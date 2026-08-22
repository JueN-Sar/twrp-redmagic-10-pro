package com.zte.mifavor.androidx.widget.swipe;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes2.dex */
class Horizontal {

    /* renamed from: a, reason: collision with root package name */
    private View f17213a;

    public Horizontal(View view) {
        this.f17213a = view;
    }

    public int a() {
        View view = this.f17213a;
        if (view instanceof SwipeMenuView) {
            return ((SwipeMenuView) view).getChildCount();
        }
        return 0;
    }

    public int b() {
        View view = this.f17213a;
        if (view instanceof SwipeMenuView) {
            return ((SwipeMenuView) view).ITEM_WIDTH;
        }
        return 0;
    }

    public View c() {
        return this.f17213a;
    }

    public int d() {
        View view = this.f17213a;
        if (view instanceof SwipeMenuView) {
            return ((SwipeMenuView) view).ITEM_WIDTH * a();
        }
        return 0;
    }

    public boolean e() {
        View view = this.f17213a;
        return (view instanceof ViewGroup) && 1 == ((ViewGroup) view).getChildCount();
    }
}
