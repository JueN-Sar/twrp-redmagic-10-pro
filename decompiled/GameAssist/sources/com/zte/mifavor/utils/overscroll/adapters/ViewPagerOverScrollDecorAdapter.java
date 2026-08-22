package com.zte.mifavor.utils.overscroll.adapters;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes2.dex */
public class ViewPagerOverScrollDecorAdapter implements IOverScrollDecoratorAdapter, ViewPager.OnPageChangeListener {

    /* renamed from: c, reason: collision with root package name */
    protected final ViewPager f17511c;

    /* renamed from: h, reason: collision with root package name */
    protected int f17512h;

    /* renamed from: i, reason: collision with root package name */
    protected float f17513i;

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean a() {
        if (this.f17512h == this.f17511c.getAdapter().e() - 1) {
            float f2 = this.f17513i;
            if (-1.0E-4f < f2 && f2 < 1.0E-4f) {
                return true;
            }
        }
        return false;
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean b() {
        if (this.f17512h == 0) {
            float f2 = this.f17513i;
            if (-1.0E-4f < f2 && f2 < 1.0E-4f) {
                return true;
            }
        }
        return false;
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public View c() {
        return this.f17511c;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void d(int i2, float f2, int i3) {
        this.f17512h = i2;
        this.f17513i = f2;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void f(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void g(int i2) {
    }
}
