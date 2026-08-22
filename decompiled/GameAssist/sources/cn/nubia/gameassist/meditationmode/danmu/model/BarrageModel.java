package cn.nubia.gameassist.meditationmode.danmu.model;

import cn.nubia.gameassist.meditationmode.danmu.DanmuNotificationBean;

/* loaded from: classes.dex */
public class BarrageModel extends DanmuNotificationBean {
    public static int y = 16;

    /* renamed from: n, reason: collision with root package name */
    private BarrageChannel f6674n;

    /* renamed from: o, reason: collision with root package name */
    protected int f6675o;

    /* renamed from: p, reason: collision with root package name */
    protected int f6676p;

    /* renamed from: q, reason: collision with root package name */
    private float f6677q;
    private float t;
    private boolean v;
    private boolean w;
    private int x;

    /* renamed from: r, reason: collision with root package name */
    private float f6678r = -1.0f;

    /* renamed from: s, reason: collision with root package name */
    private int f6679s = y;
    private boolean u = true;

    public void G() {
        this.f6677q += this.t * this.f6679s;
    }

    public void H(int i2, int i3) {
        S(i2);
    }

    public boolean J(float f2, float f3) {
        float f4 = this.f6677q;
        if (f2 <= f4 && f2 >= f4 - this.f6675o) {
            float f5 = this.f6678r;
            if (f3 >= f5 && f3 <= f5 + this.f6676p) {
                return true;
            }
        }
        return false;
    }

    public void K() {
        N(false);
    }

    public void M(boolean z) {
        if (!z) {
            K();
        }
        this.u = z;
    }

    public void N(boolean z) {
        this.v = z;
    }

    public void O(BarrageChannel barrageChannel) {
        this.f6674n = barrageChannel;
        this.x = barrageChannel.c().e(f());
        this.w = this.f6674n.c().t(f(), i());
    }

    public void P(int i2) {
        this.f6679s = i2;
    }

    public void Q(int i2) {
        this.f6676p = i2;
    }

    public void R(float f2) {
        this.f6678r = f2;
    }

    public void S(float f2) {
        this.t = f2 / this.f6674n.c().j();
    }

    public void T(int i2) {
        this.f6675o = i2;
    }

    public void l() {
        this.f6674n.a(this);
    }

    public boolean m() {
        return true;
    }

    public BarrageChannel n() {
        return this.f6674n;
    }

    public int o() {
        return this.f6676p;
    }

    public int q() {
        return this.x;
    }

    public float r() {
        return this.f6677q;
    }

    public float u() {
        return this.f6678r;
    }

    public BarrageStyle v() {
        return this.f6674n.c();
    }

    public int w() {
        return this.f6675o;
    }

    public boolean x() {
        return this.u;
    }

    public boolean y() {
        return this.v;
    }

    public boolean z() {
        return this.w;
    }
}
