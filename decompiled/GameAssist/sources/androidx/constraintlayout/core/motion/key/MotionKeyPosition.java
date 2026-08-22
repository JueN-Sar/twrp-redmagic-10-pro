package androidx.constraintlayout.core.motion.key;

/* loaded from: classes.dex */
public class MotionKeyPosition extends MotionKey {

    /* renamed from: g, reason: collision with root package name */
    public int f1699g;

    /* renamed from: h, reason: collision with root package name */
    public String f1700h;

    /* renamed from: i, reason: collision with root package name */
    public int f1701i;

    /* renamed from: j, reason: collision with root package name */
    public int f1702j;

    /* renamed from: k, reason: collision with root package name */
    public float f1703k;

    /* renamed from: l, reason: collision with root package name */
    public float f1704l;

    /* renamed from: m, reason: collision with root package name */
    public float f1705m;

    /* renamed from: n, reason: collision with root package name */
    public float f1706n;

    /* renamed from: o, reason: collision with root package name */
    public float f1707o;

    /* renamed from: p, reason: collision with root package name */
    public float f1708p;

    /* renamed from: q, reason: collision with root package name */
    public int f1709q;

    /* renamed from: r, reason: collision with root package name */
    private float f1710r;

    /* renamed from: s, reason: collision with root package name */
    private float f1711s;

    public MotionKeyPosition() {
        int i2 = MotionKey.f1693f;
        this.f1699g = i2;
        this.f1700h = null;
        this.f1701i = i2;
        this.f1702j = 0;
        this.f1703k = Float.NaN;
        this.f1704l = Float.NaN;
        this.f1705m = Float.NaN;
        this.f1706n = Float.NaN;
        this.f1707o = Float.NaN;
        this.f1708p = Float.NaN;
        this.f1709q = 0;
        this.f1710r = Float.NaN;
        this.f1711s = Float.NaN;
        this.f1697d = 2;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /* renamed from: a */
    public MotionKey clone() {
        return new MotionKeyPosition().b(this);
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKey b(MotionKey motionKey) {
        super.b(motionKey);
        MotionKeyPosition motionKeyPosition = (MotionKeyPosition) motionKey;
        this.f1700h = motionKeyPosition.f1700h;
        this.f1701i = motionKeyPosition.f1701i;
        this.f1702j = motionKeyPosition.f1702j;
        this.f1703k = motionKeyPosition.f1703k;
        this.f1704l = Float.NaN;
        this.f1705m = motionKeyPosition.f1705m;
        this.f1706n = motionKeyPosition.f1706n;
        this.f1707o = motionKeyPosition.f1707o;
        this.f1708p = motionKeyPosition.f1708p;
        this.f1710r = motionKeyPosition.f1710r;
        this.f1711s = motionKeyPosition.f1711s;
        return this;
    }
}
