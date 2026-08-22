package com.facebook.rebound;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes.dex */
public class Spring {

    /* renamed from: o, reason: collision with root package name */
    private static int f10013o;

    /* renamed from: a, reason: collision with root package name */
    private SpringConfig f10014a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f10015b;

    /* renamed from: c, reason: collision with root package name */
    private final String f10016c;

    /* renamed from: d, reason: collision with root package name */
    private final PhysicsState f10017d;

    /* renamed from: e, reason: collision with root package name */
    private final PhysicsState f10018e;

    /* renamed from: f, reason: collision with root package name */
    private final PhysicsState f10019f;

    /* renamed from: g, reason: collision with root package name */
    private double f10020g;

    /* renamed from: h, reason: collision with root package name */
    private double f10021h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f10022i = true;

    /* renamed from: j, reason: collision with root package name */
    private double f10023j = 0.005d;

    /* renamed from: k, reason: collision with root package name */
    private double f10024k = 0.005d;

    /* renamed from: l, reason: collision with root package name */
    private CopyOnWriteArraySet f10025l = new CopyOnWriteArraySet();

    /* renamed from: m, reason: collision with root package name */
    private double f10026m = 0.0d;

    /* renamed from: n, reason: collision with root package name */
    private final BaseSpringSystem f10027n;

    private static class PhysicsState {

        /* renamed from: a, reason: collision with root package name */
        double f10028a;

        /* renamed from: b, reason: collision with root package name */
        double f10029b;

        private PhysicsState() {
        }
    }

    Spring(BaseSpringSystem baseSpringSystem) {
        this.f10017d = new PhysicsState();
        this.f10018e = new PhysicsState();
        this.f10019f = new PhysicsState();
        if (baseSpringSystem == null) {
            throw new IllegalArgumentException("Spring cannot be created outside of a BaseSpringSystem");
        }
        this.f10027n = baseSpringSystem;
        StringBuilder sb = new StringBuilder();
        sb.append("spring:");
        int i2 = f10013o;
        f10013o = i2 + 1;
        sb.append(i2);
        this.f10016c = sb.toString();
        o(SpringConfig.f10035c);
    }

    private double e(PhysicsState physicsState) {
        return Math.abs(this.f10021h - physicsState.f10028a);
    }

    private void h(double d2) {
        PhysicsState physicsState = this.f10017d;
        double d3 = physicsState.f10028a * d2;
        PhysicsState physicsState2 = this.f10018e;
        double d4 = 1.0d - d2;
        physicsState.f10028a = d3 + (physicsState2.f10028a * d4);
        physicsState.f10029b = (physicsState.f10029b * d2) + (physicsState2.f10029b * d4);
    }

    public Spring a(SpringListener springListener) {
        if (springListener == null) {
            throw new IllegalArgumentException("newListener is required");
        }
        this.f10025l.add(springListener);
        return this;
    }

    void b(double d2) {
        double d3;
        boolean z;
        boolean z2;
        boolean i2 = i();
        if (i2 && this.f10022i) {
            return;
        }
        this.f10026m += d2 <= 0.064d ? d2 : 0.064d;
        SpringConfig springConfig = this.f10014a;
        double d4 = springConfig.f10037b;
        double d5 = springConfig.f10036a;
        PhysicsState physicsState = this.f10017d;
        double d6 = physicsState.f10028a;
        double d7 = physicsState.f10029b;
        PhysicsState physicsState2 = this.f10019f;
        double d8 = physicsState2.f10028a;
        double d9 = physicsState2.f10029b;
        while (true) {
            d3 = this.f10026m;
            if (d3 < 0.001d) {
                break;
            }
            double d10 = d3 - 0.001d;
            this.f10026m = d10;
            if (d10 < 0.001d) {
                PhysicsState physicsState3 = this.f10018e;
                physicsState3.f10028a = d6;
                physicsState3.f10029b = d7;
            }
            double d11 = this.f10021h;
            double d12 = ((d11 - d8) * d4) - (d5 * d7);
            double d13 = d7 + (d12 * 0.001d * 0.5d);
            double d14 = ((d11 - (((d7 * 0.001d) * 0.5d) + d6)) * d4) - (d5 * d13);
            double d15 = d7 + (d14 * 0.001d * 0.5d);
            double d16 = ((d11 - (d6 + ((d13 * 0.001d) * 0.5d))) * d4) - (d5 * d15);
            double d17 = d6 + (d15 * 0.001d);
            double d18 = d7 + (d16 * 0.001d);
            d6 += (d7 + ((d13 + d15) * 2.0d) + d18) * 0.16666666666666666d * 0.001d;
            d7 += (d12 + ((d14 + d16) * 2.0d) + (((d11 - d17) * d4) - (d5 * d18))) * 0.16666666666666666d * 0.001d;
            d8 = d17;
            d9 = d18;
        }
        PhysicsState physicsState4 = this.f10019f;
        physicsState4.f10028a = d8;
        physicsState4.f10029b = d9;
        PhysicsState physicsState5 = this.f10017d;
        physicsState5.f10028a = d6;
        physicsState5.f10029b = d7;
        if (d3 > 0.0d) {
            h(d3 / 0.001d);
        }
        boolean z3 = true;
        if (i() || (this.f10015b && j())) {
            if (d4 > 0.0d) {
                double d19 = this.f10021h;
                this.f10020g = d19;
                this.f10017d.f10028a = d19;
            } else {
                double d20 = this.f10017d.f10028a;
                this.f10021h = d20;
                this.f10020g = d20;
            }
            p(0.0d);
            z = true;
        } else {
            z = i2;
        }
        if (this.f10022i) {
            this.f10022i = false;
            z2 = true;
        } else {
            z2 = false;
        }
        if (z) {
            this.f10022i = true;
        } else {
            z3 = false;
        }
        Iterator it = this.f10025l.iterator();
        while (it.hasNext()) {
            SpringListener springListener = (SpringListener) it.next();
            if (z2) {
                springListener.c(this);
            }
            springListener.a(this);
            if (z3) {
                springListener.d(this);
            }
        }
    }

    public double c() {
        return e(this.f10017d);
    }

    public double d() {
        return this.f10017d.f10028a;
    }

    public double f() {
        return this.f10021h;
    }

    public String g() {
        return this.f10016c;
    }

    public boolean i() {
        return Math.abs(this.f10017d.f10029b) <= this.f10023j && (e(this.f10017d) <= this.f10024k || this.f10014a.f10037b == 0.0d);
    }

    public boolean j() {
        return this.f10014a.f10037b > 0.0d && ((this.f10020g < this.f10021h && d() > this.f10021h) || (this.f10020g > this.f10021h && d() < this.f10021h));
    }

    public Spring k() {
        PhysicsState physicsState = this.f10017d;
        double d2 = physicsState.f10028a;
        this.f10021h = d2;
        this.f10019f.f10028a = d2;
        physicsState.f10029b = 0.0d;
        return this;
    }

    public Spring l(double d2) {
        return m(d2, true);
    }

    public Spring m(double d2, boolean z) {
        this.f10020g = d2;
        this.f10017d.f10028a = d2;
        this.f10027n.a(g());
        Iterator it = this.f10025l.iterator();
        while (it.hasNext()) {
            ((SpringListener) it.next()).a(this);
        }
        if (z) {
            k();
        }
        return this;
    }

    public Spring n(double d2) {
        if (this.f10021h == d2 && i()) {
            return this;
        }
        this.f10020g = d();
        this.f10021h = d2;
        this.f10027n.a(g());
        Iterator it = this.f10025l.iterator();
        while (it.hasNext()) {
            ((SpringListener) it.next()).b(this);
        }
        return this;
    }

    public Spring o(SpringConfig springConfig) {
        if (springConfig == null) {
            throw new IllegalArgumentException("springConfig is required");
        }
        this.f10014a = springConfig;
        return this;
    }

    public Spring p(double d2) {
        PhysicsState physicsState = this.f10017d;
        if (d2 == physicsState.f10029b) {
            return this;
        }
        physicsState.f10029b = d2;
        this.f10027n.a(g());
        return this;
    }

    public boolean q() {
        return (i() && r()) ? false : true;
    }

    public boolean r() {
        return this.f10022i;
    }
}
