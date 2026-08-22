package com.google.android.material.appbar;

import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
class ViewOffsetHelper {

    /* renamed from: a, reason: collision with root package name */
    private final View f13893a;

    /* renamed from: b, reason: collision with root package name */
    private int f13894b;

    /* renamed from: c, reason: collision with root package name */
    private int f13895c;

    /* renamed from: d, reason: collision with root package name */
    private int f13896d;

    /* renamed from: e, reason: collision with root package name */
    private int f13897e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f13898f = true;

    /* renamed from: g, reason: collision with root package name */
    private boolean f13899g = true;

    public ViewOffsetHelper(View view) {
        this.f13893a = view;
    }

    void a() {
        View view = this.f13893a;
        ViewCompat.T(view, this.f13896d - (view.getTop() - this.f13894b));
        View view2 = this.f13893a;
        ViewCompat.S(view2, this.f13897e - (view2.getLeft() - this.f13895c));
    }

    public int b() {
        return this.f13894b;
    }

    public int c() {
        return this.f13896d;
    }

    void d() {
        this.f13894b = this.f13893a.getTop();
        this.f13895c = this.f13893a.getLeft();
    }

    public boolean e(int i2) {
        if (!this.f13899g || this.f13897e == i2) {
            return false;
        }
        this.f13897e = i2;
        a();
        return true;
    }

    public boolean f(int i2) {
        if (!this.f13898f || this.f13896d == i2) {
            return false;
        }
        this.f13896d = i2;
        a();
        return true;
    }
}
