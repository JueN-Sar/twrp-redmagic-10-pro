package androidx.constraintlayout.core.dsl;

import java.util.Arrays;

/* loaded from: classes.dex */
public class KeyAttributes extends Keys {

    /* renamed from: a, reason: collision with root package name */
    protected String f1607a;

    /* renamed from: b, reason: collision with root package name */
    private String[] f1608b;

    /* renamed from: c, reason: collision with root package name */
    private String f1609c;

    /* renamed from: d, reason: collision with root package name */
    private Fit f1610d;

    /* renamed from: e, reason: collision with root package name */
    private int[] f1611e;

    /* renamed from: f, reason: collision with root package name */
    private Visibility[] f1612f;

    /* renamed from: g, reason: collision with root package name */
    private float[] f1613g;

    /* renamed from: h, reason: collision with root package name */
    private float[] f1614h;

    /* renamed from: i, reason: collision with root package name */
    private float[] f1615i;

    /* renamed from: j, reason: collision with root package name */
    private float[] f1616j;

    /* renamed from: k, reason: collision with root package name */
    private float[] f1617k;

    /* renamed from: l, reason: collision with root package name */
    private float[] f1618l;

    /* renamed from: m, reason: collision with root package name */
    private float[] f1619m;

    /* renamed from: n, reason: collision with root package name */
    private float[] f1620n;

    /* renamed from: o, reason: collision with root package name */
    private float[] f1621o;

    /* renamed from: p, reason: collision with root package name */
    private float[] f1622p;

    /* renamed from: q, reason: collision with root package name */
    private float[] f1623q;

    /* renamed from: r, reason: collision with root package name */
    private float[] f1624r;

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
        d(sb, "target", this.f1608b);
        sb.append("frame:");
        sb.append(Arrays.toString(this.f1611e));
        sb.append(",\n");
        b(sb, "easing", this.f1609c);
        if (this.f1610d != null) {
            sb.append("fit:'");
            sb.append(this.f1610d);
            sb.append("',\n");
        }
        if (this.f1612f != null) {
            sb.append("visibility:'");
            sb.append(Arrays.toString(this.f1612f));
            sb.append("',\n");
        }
        c(sb, "alpha", this.f1613g);
        c(sb, "rotationX", this.f1615i);
        c(sb, "rotationY", this.f1616j);
        c(sb, "rotationZ", this.f1614h);
        c(sb, "pivotX", this.f1617k);
        c(sb, "pivotY", this.f1618l);
        c(sb, "pathRotate", this.f1619m);
        c(sb, "scaleX", this.f1620n);
        c(sb, "scaleY", this.f1621o);
        c(sb, "translationX", this.f1622p);
        c(sb, "translationY", this.f1623q);
        c(sb, "translationZ", this.f1624r);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1607a);
        sb.append(":{\n");
        f(sb);
        sb.append("},\n");
        return sb.toString();
    }
}
