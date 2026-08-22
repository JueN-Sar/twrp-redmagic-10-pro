package com.zte.mifavor.androidx.widget.swipe;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SwipeMenu {

    /* renamed from: a, reason: collision with root package name */
    private SwipeMenuLayout f17216a;

    /* renamed from: b, reason: collision with root package name */
    private int f17217b = 0;

    /* renamed from: c, reason: collision with root package name */
    private List f17218c = new ArrayList(2);

    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    public SwipeMenu(SwipeMenuLayout swipeMenuLayout) {
        this.f17216a = swipeMenuLayout;
    }

    public List a() {
        return this.f17218c;
    }

    public int b() {
        return this.f17217b;
    }

    public boolean c() {
        return !this.f17218c.isEmpty();
    }
}
