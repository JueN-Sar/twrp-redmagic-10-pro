package com.facebook.rebound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes.dex */
public class BaseSpringSystem {

    /* renamed from: c, reason: collision with root package name */
    private final SpringLooper f10002c;

    /* renamed from: a, reason: collision with root package name */
    private final Map f10000a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Set f10001b = new CopyOnWriteArraySet();

    /* renamed from: d, reason: collision with root package name */
    private final CopyOnWriteArraySet f10003d = new CopyOnWriteArraySet();

    /* renamed from: e, reason: collision with root package name */
    private boolean f10004e = true;

    public BaseSpringSystem(SpringLooper springLooper) {
        if (springLooper == null) {
            throw new IllegalArgumentException("springLooper is required");
        }
        this.f10002c = springLooper;
        springLooper.a(this);
    }

    void a(String str) {
        Spring spring = (Spring) this.f10000a.get(str);
        if (spring == null) {
            throw new IllegalArgumentException("springId " + str + " does not reference a registered spring");
        }
        this.f10001b.add(spring);
        if (e()) {
            this.f10004e = false;
            this.f10002c.b();
        }
    }

    void b(double d2) {
        for (Spring spring : this.f10001b) {
            if (spring.q()) {
                spring.b(d2 / 1000.0d);
            } else {
                this.f10001b.remove(spring);
            }
        }
    }

    public Spring c() {
        Spring spring = new Spring(this);
        g(spring);
        return spring;
    }

    public List d() {
        Collection values = this.f10000a.values();
        return Collections.unmodifiableList(values instanceof List ? (List) values : new ArrayList(values));
    }

    public boolean e() {
        return this.f10004e;
    }

    public void f(double d2) {
        Iterator it = this.f10003d.iterator();
        while (it.hasNext()) {
            ((SpringSystemListener) it.next()).a(this);
        }
        b(d2);
        if (this.f10001b.isEmpty()) {
            this.f10004e = true;
        }
        Iterator it2 = this.f10003d.iterator();
        while (it2.hasNext()) {
            ((SpringSystemListener) it2.next()).b(this);
        }
        if (this.f10004e) {
            this.f10002c.c();
        }
    }

    void g(Spring spring) {
        if (spring == null) {
            throw new IllegalArgumentException("spring is required");
        }
        if (this.f10000a.containsKey(spring.g())) {
            throw new IllegalArgumentException("spring is already registered");
        }
        this.f10000a.put(spring.g(), spring);
    }
}
