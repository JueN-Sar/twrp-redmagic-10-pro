package androidx.constraintlayout.core.motion.key;

import java.util.HashMap;

/* loaded from: classes.dex */
public class MotionKeyTimeCycle extends MotionKey {

    /* renamed from: g, reason: collision with root package name */
    private String f1712g;

    /* renamed from: h, reason: collision with root package name */
    private int f1713h = -1;

    /* renamed from: i, reason: collision with root package name */
    private float f1714i = Float.NaN;

    /* renamed from: j, reason: collision with root package name */
    private float f1715j = Float.NaN;

    /* renamed from: k, reason: collision with root package name */
    private float f1716k = Float.NaN;

    /* renamed from: l, reason: collision with root package name */
    private float f1717l = Float.NaN;

    /* renamed from: m, reason: collision with root package name */
    private float f1718m = Float.NaN;

    /* renamed from: n, reason: collision with root package name */
    private float f1719n = Float.NaN;

    /* renamed from: o, reason: collision with root package name */
    private float f1720o = Float.NaN;

    /* renamed from: p, reason: collision with root package name */
    private float f1721p = Float.NaN;

    /* renamed from: q, reason: collision with root package name */
    private float f1722q = Float.NaN;

    /* renamed from: r, reason: collision with root package name */
    private float f1723r = Float.NaN;

    /* renamed from: s, reason: collision with root package name */
    private float f1724s = Float.NaN;
    private float t = Float.NaN;
    private int u = 0;
    private String v = null;
    private float w = Float.NaN;
    private float x = 0.0f;

    public MotionKeyTimeCycle() {
        this.f1697d = 3;
        this.f1698e = new HashMap();
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /* renamed from: a */
    public MotionKey clone() {
        return new MotionKeyTimeCycle().c(this);
    }

    public MotionKeyTimeCycle c(MotionKey motionKey) {
        super.b(motionKey);
        MotionKeyTimeCycle motionKeyTimeCycle = (MotionKeyTimeCycle) motionKey;
        this.f1712g = motionKeyTimeCycle.f1712g;
        this.f1713h = motionKeyTimeCycle.f1713h;
        this.u = motionKeyTimeCycle.u;
        this.w = motionKeyTimeCycle.w;
        this.x = motionKeyTimeCycle.x;
        this.t = motionKeyTimeCycle.t;
        this.f1714i = motionKeyTimeCycle.f1714i;
        this.f1715j = motionKeyTimeCycle.f1715j;
        this.f1716k = motionKeyTimeCycle.f1716k;
        this.f1719n = motionKeyTimeCycle.f1719n;
        this.f1717l = motionKeyTimeCycle.f1717l;
        this.f1718m = motionKeyTimeCycle.f1718m;
        this.f1720o = motionKeyTimeCycle.f1720o;
        this.f1721p = motionKeyTimeCycle.f1721p;
        this.f1722q = motionKeyTimeCycle.f1722q;
        this.f1723r = motionKeyTimeCycle.f1723r;
        this.f1724s = motionKeyTimeCycle.f1724s;
        return this;
    }
}
