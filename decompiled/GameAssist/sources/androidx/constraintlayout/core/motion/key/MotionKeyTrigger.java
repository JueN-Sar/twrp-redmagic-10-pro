package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.utils.FloatRect;
import java.util.HashMap;

/* loaded from: classes.dex */
public class MotionKeyTrigger extends MotionKey {

    /* renamed from: g, reason: collision with root package name */
    private int f1725g = -1;

    /* renamed from: h, reason: collision with root package name */
    private String f1726h = null;

    /* renamed from: i, reason: collision with root package name */
    private int f1727i;

    /* renamed from: j, reason: collision with root package name */
    private String f1728j;

    /* renamed from: k, reason: collision with root package name */
    private String f1729k;

    /* renamed from: l, reason: collision with root package name */
    private int f1730l;

    /* renamed from: m, reason: collision with root package name */
    private int f1731m;

    /* renamed from: n, reason: collision with root package name */
    float f1732n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f1733o;

    /* renamed from: p, reason: collision with root package name */
    private boolean f1734p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f1735q;

    /* renamed from: r, reason: collision with root package name */
    private float f1736r;

    /* renamed from: s, reason: collision with root package name */
    private float f1737s;
    private boolean t;
    int u;
    int v;
    int w;
    FloatRect x;
    FloatRect y;

    public MotionKeyTrigger() {
        int i2 = MotionKey.f1693f;
        this.f1727i = i2;
        this.f1728j = null;
        this.f1729k = null;
        this.f1730l = i2;
        this.f1731m = i2;
        this.f1732n = 0.1f;
        this.f1733o = true;
        this.f1734p = true;
        this.f1735q = true;
        this.f1736r = Float.NaN;
        this.t = false;
        this.u = i2;
        this.v = i2;
        this.w = i2;
        this.x = new FloatRect();
        this.y = new FloatRect();
        this.f1697d = 5;
        this.f1698e = new HashMap();
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /* renamed from: a */
    public MotionKey clone() {
        return new MotionKeyTrigger().c(this);
    }

    public MotionKeyTrigger c(MotionKey motionKey) {
        super.b(motionKey);
        MotionKeyTrigger motionKeyTrigger = (MotionKeyTrigger) motionKey;
        this.f1725g = motionKeyTrigger.f1725g;
        this.f1726h = motionKeyTrigger.f1726h;
        this.f1727i = motionKeyTrigger.f1727i;
        this.f1728j = motionKeyTrigger.f1728j;
        this.f1729k = motionKeyTrigger.f1729k;
        this.f1730l = motionKeyTrigger.f1730l;
        this.f1731m = motionKeyTrigger.f1731m;
        this.f1732n = motionKeyTrigger.f1732n;
        this.f1733o = motionKeyTrigger.f1733o;
        this.f1734p = motionKeyTrigger.f1734p;
        this.f1735q = motionKeyTrigger.f1735q;
        this.f1736r = motionKeyTrigger.f1736r;
        this.f1737s = motionKeyTrigger.f1737s;
        this.t = motionKeyTrigger.t;
        this.x = motionKeyTrigger.x;
        this.y = motionKeyTrigger.y;
        return this;
    }
}
