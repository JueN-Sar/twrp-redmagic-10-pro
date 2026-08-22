package com.zte.mifavor.widget;

import android.util.Log;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes2.dex */
public class SpringChain implements SpringListener {

    /* renamed from: a, reason: collision with root package name */
    public boolean f17769a;

    /* renamed from: b, reason: collision with root package name */
    private int f17770b;

    /* renamed from: c, reason: collision with root package name */
    private final SpringSystem f17771c;

    /* renamed from: d, reason: collision with root package name */
    private final CopyOnWriteArrayList f17772d;

    /* renamed from: e, reason: collision with root package name */
    private final CopyOnWriteArrayList f17773e;

    /* renamed from: f, reason: collision with root package name */
    private int f17774f;

    /* renamed from: g, reason: collision with root package name */
    private final SpringConfig f17775g;

    /* renamed from: h, reason: collision with root package name */
    private final SpringConfig f17776h;

    /* renamed from: i, reason: collision with root package name */
    private String f17777i;

    @Override // com.facebook.rebound.SpringListener
    public void a(Spring spring) {
        int i2;
        int i3;
        String obj = spring.toString();
        if (obj != null) {
            this.f17777i = "[" + obj.substring(obj.length() - 4) + "] ";
        }
        int indexOf = this.f17773e.indexOf(spring);
        if (indexOf == -1) {
            return;
        }
        SpringListener springListener = (SpringListener) this.f17772d.get(indexOf);
        int i4 = this.f17774f;
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
        if (i2 > -1 && i2 < this.f17773e.size()) {
            ((Spring) this.f17773e.get(i2)).n(i2 <= this.f17770b ? (this.f17769a || spring.d() == 0.0d) ? (float) spring.d() : ((float) spring.d()) + (((i2 + 1) - this.f17774f) * 30) : 0.0f);
        }
        if (i3 > -1 && i3 < this.f17773e.size()) {
            ((Spring) this.f17773e.get(i3)).n(i3 >= (this.f17773e.size() + (-1)) - this.f17770b ? (this.f17769a || spring.d() == 0.0d) ? (float) spring.d() : ((float) spring.d()) - (((this.f17774f + 1) - i3) * 30) : 0.0f);
        }
        springListener.a(spring);
    }

    @Override // com.facebook.rebound.SpringListener
    public void b(Spring spring) {
        int indexOf = this.f17773e.indexOf(spring);
        if (indexOf == -1) {
            return;
        }
        ((SpringListener) this.f17772d.get(indexOf)).b(spring);
    }

    @Override // com.facebook.rebound.SpringListener
    public void c(Spring spring) {
        int indexOf = this.f17773e.indexOf(spring);
        if (indexOf == -1) {
            return;
        }
        ((SpringListener) this.f17772d.get(indexOf)).c(spring);
    }

    @Override // com.facebook.rebound.SpringListener
    public void d(Spring spring) {
        int indexOf = this.f17773e.indexOf(spring);
        if (indexOf == -1) {
            return;
        }
        ((SpringListener) this.f17772d.get(indexOf)).d(spring);
    }

    public List e() {
        return this.f17773e;
    }

    public Spring f() {
        Log.d("Scroll#SpringChain", "getControlSpring mControlSpringIndex=" + this.f17774f);
        int i2 = this.f17774f;
        if (i2 == -1) {
            return null;
        }
        return (Spring) this.f17773e.get(i2);
    }

    public int g() {
        Log.d("Scroll#SpringChain", "get Max Spring ID = " + this.f17770b);
        return this.f17770b;
    }

    public SpringChain h(int i2) {
        this.f17774f = i2;
        if (((Spring) this.f17773e.get(i2)) == null) {
            return null;
        }
        Iterator it = this.f17771c.d().iterator();
        while (it.hasNext()) {
            ((Spring) it.next()).o(this.f17776h);
        }
        Spring f2 = f();
        if (f2 != null) {
            f2.o(this.f17775g);
        }
        return this;
    }

    public void i(int i2) {
        this.f17770b = i2;
        Log.d("Scroll#SpringChain", "set Max Spring ID = " + this.f17770b);
    }
}
