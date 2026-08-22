package androidx.constraintlayout.core.dsl;

/* loaded from: classes.dex */
public class KeyAttribute extends Keys {

    /* renamed from: a, reason: collision with root package name */
    protected String f1589a;

    /* renamed from: b, reason: collision with root package name */
    private String f1590b;

    /* renamed from: c, reason: collision with root package name */
    private int f1591c;

    /* renamed from: d, reason: collision with root package name */
    private String f1592d;

    /* renamed from: e, reason: collision with root package name */
    private Fit f1593e;

    /* renamed from: f, reason: collision with root package name */
    private Visibility f1594f;

    /* renamed from: g, reason: collision with root package name */
    private float f1595g;

    /* renamed from: h, reason: collision with root package name */
    private float f1596h;

    /* renamed from: i, reason: collision with root package name */
    private float f1597i;

    /* renamed from: j, reason: collision with root package name */
    private float f1598j;

    /* renamed from: k, reason: collision with root package name */
    private float f1599k;

    /* renamed from: l, reason: collision with root package name */
    private float f1600l;

    /* renamed from: m, reason: collision with root package name */
    private float f1601m;

    /* renamed from: n, reason: collision with root package name */
    private float f1602n;

    /* renamed from: o, reason: collision with root package name */
    private float f1603o;

    /* renamed from: p, reason: collision with root package name */
    private float f1604p;

    /* renamed from: q, reason: collision with root package name */
    private float f1605q;

    /* renamed from: r, reason: collision with root package name */
    private float f1606r;

    public enum Fit {
        SPLINE,
        LINEAR
    }

    public enum Visibility {
        VISIBLE,
        INVISIBLE,
        GONE
    }

    protected void f(StringBuilder sb) {
        b(sb, "target", this.f1590b);
        sb.append("frame:");
        sb.append(this.f1591c);
        sb.append(",\n");
        b(sb, "easing", this.f1592d);
        if (this.f1593e != null) {
            sb.append("fit:'");
            sb.append(this.f1593e);
            sb.append("',\n");
        }
        if (this.f1594f != null) {
            sb.append("visibility:'");
            sb.append(this.f1594f);
            sb.append("',\n");
        }
        a(sb, "alpha", this.f1595g);
        a(sb, "rotationX", this.f1597i);
        a(sb, "rotationY", this.f1598j);
        a(sb, "rotationZ", this.f1596h);
        a(sb, "pivotX", this.f1599k);
        a(sb, "pivotY", this.f1600l);
        a(sb, "pathRotate", this.f1601m);
        a(sb, "scaleX", this.f1602n);
        a(sb, "scaleY", this.f1603o);
        a(sb, "translationX", this.f1604p);
        a(sb, "translationY", this.f1605q);
        a(sb, "translationZ", this.f1606r);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1589a);
        sb.append(":{\n");
        f(sb);
        sb.append("},\n");
        return sb.toString();
    }
}
