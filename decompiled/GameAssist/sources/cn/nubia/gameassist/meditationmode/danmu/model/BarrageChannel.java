package cn.nubia.gameassist.meditationmode.danmu.model;

/* loaded from: classes.dex */
public class BarrageChannel {

    /* renamed from: a, reason: collision with root package name */
    private int f6654a;

    /* renamed from: b, reason: collision with root package name */
    private int f6655b;

    /* renamed from: c, reason: collision with root package name */
    private int f6656c;

    /* renamed from: d, reason: collision with root package name */
    private BarrageModel f6657d;

    /* renamed from: e, reason: collision with root package name */
    private BarrageConfig f6658e;

    /* renamed from: f, reason: collision with root package name */
    private BarrageStyle f6659f;

    public void a(BarrageModel barrageModel) {
        if (barrageModel.y()) {
            return;
        }
        barrageModel.S(this.f6654a);
        BarrageModel barrageModel2 = this.f6657d;
        int r2 = barrageModel2 != null ? (int) (barrageModel2.r() - this.f6657d.w()) : 0;
        BarrageModel barrageModel3 = this.f6657d;
        if (barrageModel3 == null || !barrageModel3.x() || r2 > this.f6658e.c()) {
            barrageModel.N(true);
            this.f6657d = barrageModel;
        }
    }

    public BarrageConfig b() {
        return this.f6658e;
    }

    public BarrageStyle c() {
        return this.f6659f;
    }

    public int d() {
        return this.f6654a;
    }

    public int e() {
        return this.f6656c;
    }

    public void f() {
        this.f6657d = null;
    }

    public void g(BarrageConfig barrageConfig) {
        this.f6658e = barrageConfig;
    }

    public void h(BarrageStyle barrageStyle) {
        this.f6659f = barrageStyle;
    }

    public void i(int i2) {
        this.f6654a = i2;
    }

    public void j(int i2) {
        this.f6656c = i2;
    }

    public void k(int i2) {
        this.f6655b = i2;
    }
}
