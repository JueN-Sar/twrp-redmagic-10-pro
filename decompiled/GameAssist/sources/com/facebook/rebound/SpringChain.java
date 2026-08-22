package com.facebook.rebound;

import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class SpringChain implements SpringListener {

    /* renamed from: d, reason: collision with root package name */
    private static final SpringConfigRegistry f10030d = SpringConfigRegistry.c();

    /* renamed from: e, reason: collision with root package name */
    private static int f10031e = 0;

    /* renamed from: a, reason: collision with root package name */
    private final CopyOnWriteArrayList f10032a;

    /* renamed from: b, reason: collision with root package name */
    private final CopyOnWriteArrayList f10033b;

    /* renamed from: c, reason: collision with root package name */
    private int f10034c;

    @Override // com.facebook.rebound.SpringListener
    public void a(Spring spring) {
        int i2;
        int i3;
        int indexOf = this.f10033b.indexOf(spring);
        SpringListener springListener = (SpringListener) this.f10032a.get(indexOf);
        int i4 = this.f10034c;
        if (indexOf == i4) {
            i3 = indexOf - 1;
            i2 = indexOf + 1;
        } else if (indexOf < i4) {
            i3 = indexOf - 1;
            i2 = -1;
        } else if (indexOf > i4) {
            i2 = indexOf + 1;
            i3 = -1;
        } else {
            i2 = -1;
            i3 = -1;
        }
        if (i2 > -1 && i2 < this.f10033b.size()) {
            ((Spring) this.f10033b.get(i2)).n(spring.d());
        }
        if (i3 > -1 && i3 < this.f10033b.size()) {
            ((Spring) this.f10033b.get(i3)).n(spring.d());
        }
        springListener.a(spring);
    }

    @Override // com.facebook.rebound.SpringListener
    public void b(Spring spring) {
        ((SpringListener) this.f10032a.get(this.f10033b.indexOf(spring))).b(spring);
    }

    @Override // com.facebook.rebound.SpringListener
    public void c(Spring spring) {
        ((SpringListener) this.f10032a.get(this.f10033b.indexOf(spring))).c(spring);
    }

    @Override // com.facebook.rebound.SpringListener
    public void d(Spring spring) {
        ((SpringListener) this.f10032a.get(this.f10033b.indexOf(spring))).d(spring);
    }
}
