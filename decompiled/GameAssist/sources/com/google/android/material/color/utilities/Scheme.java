package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import com.google.errorprone.annotations.CheckReturnValue;

@CheckReturnValue
@RestrictTo
/* loaded from: classes.dex */
public class Scheme {
    private int A;
    private int B;
    private int C;

    /* renamed from: a, reason: collision with root package name */
    private int f14350a;

    /* renamed from: b, reason: collision with root package name */
    private int f14351b;

    /* renamed from: c, reason: collision with root package name */
    private int f14352c;

    /* renamed from: d, reason: collision with root package name */
    private int f14353d;

    /* renamed from: e, reason: collision with root package name */
    private int f14354e;

    /* renamed from: f, reason: collision with root package name */
    private int f14355f;

    /* renamed from: g, reason: collision with root package name */
    private int f14356g;

    /* renamed from: h, reason: collision with root package name */
    private int f14357h;

    /* renamed from: i, reason: collision with root package name */
    private int f14358i;

    /* renamed from: j, reason: collision with root package name */
    private int f14359j;

    /* renamed from: k, reason: collision with root package name */
    private int f14360k;

    /* renamed from: l, reason: collision with root package name */
    private int f14361l;

    /* renamed from: m, reason: collision with root package name */
    private int f14362m;

    /* renamed from: n, reason: collision with root package name */
    private int f14363n;

    /* renamed from: o, reason: collision with root package name */
    private int f14364o;

    /* renamed from: p, reason: collision with root package name */
    private int f14365p;

    /* renamed from: q, reason: collision with root package name */
    private int f14366q;

    /* renamed from: r, reason: collision with root package name */
    private int f14367r;

    /* renamed from: s, reason: collision with root package name */
    private int f14368s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scheme) || !super.equals(obj)) {
            return false;
        }
        Scheme scheme = (Scheme) obj;
        return this.f14350a == scheme.f14350a && this.f14351b == scheme.f14351b && this.f14352c == scheme.f14352c && this.f14353d == scheme.f14353d && this.f14354e == scheme.f14354e && this.f14355f == scheme.f14355f && this.f14356g == scheme.f14356g && this.f14357h == scheme.f14357h && this.f14358i == scheme.f14358i && this.f14359j == scheme.f14359j && this.f14360k == scheme.f14360k && this.f14361l == scheme.f14361l && this.f14362m == scheme.f14362m && this.f14363n == scheme.f14363n && this.f14364o == scheme.f14364o && this.f14365p == scheme.f14365p && this.f14366q == scheme.f14366q && this.f14367r == scheme.f14367r && this.f14368s == scheme.f14368s && this.t == scheme.t && this.u == scheme.u && this.v == scheme.v && this.w == scheme.w && this.x == scheme.x && this.y == scheme.y && this.z == scheme.z && this.A == scheme.A && this.B == scheme.B && this.C == scheme.C;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((super.hashCode() * 31) + this.f14350a) * 31) + this.f14351b) * 31) + this.f14352c) * 31) + this.f14353d) * 31) + this.f14354e) * 31) + this.f14355f) * 31) + this.f14356g) * 31) + this.f14357h) * 31) + this.f14358i) * 31) + this.f14359j) * 31) + this.f14360k) * 31) + this.f14361l) * 31) + this.f14362m) * 31) + this.f14363n) * 31) + this.f14364o) * 31) + this.f14365p) * 31) + this.f14366q) * 31) + this.f14367r) * 31) + this.f14368s) * 31) + this.t) * 31) + this.u) * 31) + this.v) * 31) + this.w) * 31) + this.x) * 31) + this.y) * 31) + this.z) * 31) + this.A) * 31) + this.B) * 31) + this.C;
    }

    public String toString() {
        return "Scheme{primary=" + this.f14350a + ", onPrimary=" + this.f14351b + ", primaryContainer=" + this.f14352c + ", onPrimaryContainer=" + this.f14353d + ", secondary=" + this.f14354e + ", onSecondary=" + this.f14355f + ", secondaryContainer=" + this.f14356g + ", onSecondaryContainer=" + this.f14357h + ", tertiary=" + this.f14358i + ", onTertiary=" + this.f14359j + ", tertiaryContainer=" + this.f14360k + ", onTertiaryContainer=" + this.f14361l + ", error=" + this.f14362m + ", onError=" + this.f14363n + ", errorContainer=" + this.f14364o + ", onErrorContainer=" + this.f14365p + ", background=" + this.f14366q + ", onBackground=" + this.f14367r + ", surface=" + this.f14368s + ", onSurface=" + this.t + ", surfaceVariant=" + this.u + ", onSurfaceVariant=" + this.v + ", outline=" + this.w + ", outlineVariant=" + this.x + ", shadow=" + this.y + ", scrim=" + this.z + ", inverseSurface=" + this.A + ", inverseOnSurface=" + this.B + ", inversePrimary=" + this.C + '}';
    }
}
