package com.airbnb.lottie.model.layer;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.parser.DropShadowEffect;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class Layer {

    /* renamed from: a, reason: collision with root package name */
    private final List f9759a;

    /* renamed from: b, reason: collision with root package name */
    private final LottieComposition f9760b;

    /* renamed from: c, reason: collision with root package name */
    private final String f9761c;

    /* renamed from: d, reason: collision with root package name */
    private final long f9762d;

    /* renamed from: e, reason: collision with root package name */
    private final LayerType f9763e;

    /* renamed from: f, reason: collision with root package name */
    private final long f9764f;

    /* renamed from: g, reason: collision with root package name */
    private final String f9765g;

    /* renamed from: h, reason: collision with root package name */
    private final List f9766h;

    /* renamed from: i, reason: collision with root package name */
    private final AnimatableTransform f9767i;

    /* renamed from: j, reason: collision with root package name */
    private final int f9768j;

    /* renamed from: k, reason: collision with root package name */
    private final int f9769k;

    /* renamed from: l, reason: collision with root package name */
    private final int f9770l;

    /* renamed from: m, reason: collision with root package name */
    private final float f9771m;

    /* renamed from: n, reason: collision with root package name */
    private final float f9772n;

    /* renamed from: o, reason: collision with root package name */
    private final float f9773o;

    /* renamed from: p, reason: collision with root package name */
    private final float f9774p;

    /* renamed from: q, reason: collision with root package name */
    private final AnimatableTextFrame f9775q;

    /* renamed from: r, reason: collision with root package name */
    private final AnimatableTextProperties f9776r;

    /* renamed from: s, reason: collision with root package name */
    private final AnimatableFloatValue f9777s;
    private final List t;
    private final MatteType u;
    private final boolean v;
    private final BlurEffect w;
    private final DropShadowEffect x;
    private final LBlendMode y;

    public enum LayerType {
        PRE_COMP,
        SOLID,
        IMAGE,
        NULL,
        SHAPE,
        TEXT,
        UNKNOWN
    }

    public enum MatteType {
        NONE,
        ADD,
        INVERT,
        LUMA,
        LUMA_INVERTED,
        UNKNOWN
    }

    public Layer(List list, LottieComposition lottieComposition, String str, long j2, LayerType layerType, long j3, String str2, List list2, AnimatableTransform animatableTransform, int i2, int i3, int i4, float f2, float f3, float f4, float f5, AnimatableTextFrame animatableTextFrame, AnimatableTextProperties animatableTextProperties, List list3, MatteType matteType, AnimatableFloatValue animatableFloatValue, boolean z, BlurEffect blurEffect, DropShadowEffect dropShadowEffect, LBlendMode lBlendMode) {
        this.f9759a = list;
        this.f9760b = lottieComposition;
        this.f9761c = str;
        this.f9762d = j2;
        this.f9763e = layerType;
        this.f9764f = j3;
        this.f9765g = str2;
        this.f9766h = list2;
        this.f9767i = animatableTransform;
        this.f9768j = i2;
        this.f9769k = i3;
        this.f9770l = i4;
        this.f9771m = f2;
        this.f9772n = f3;
        this.f9773o = f4;
        this.f9774p = f5;
        this.f9775q = animatableTextFrame;
        this.f9776r = animatableTextProperties;
        this.t = list3;
        this.u = matteType;
        this.f9777s = animatableFloatValue;
        this.v = z;
        this.w = blurEffect;
        this.x = dropShadowEffect;
        this.y = lBlendMode;
    }

    public LBlendMode a() {
        return this.y;
    }

    public BlurEffect b() {
        return this.w;
    }

    LottieComposition c() {
        return this.f9760b;
    }

    public DropShadowEffect d() {
        return this.x;
    }

    public long e() {
        return this.f9762d;
    }

    List f() {
        return this.t;
    }

    public LayerType g() {
        return this.f9763e;
    }

    List h() {
        return this.f9766h;
    }

    MatteType i() {
        return this.u;
    }

    public String j() {
        return this.f9761c;
    }

    long k() {
        return this.f9764f;
    }

    float l() {
        return this.f9774p;
    }

    float m() {
        return this.f9773o;
    }

    public String n() {
        return this.f9765g;
    }

    List o() {
        return this.f9759a;
    }

    int p() {
        return this.f9770l;
    }

    int q() {
        return this.f9769k;
    }

    int r() {
        return this.f9768j;
    }

    float s() {
        return this.f9772n / this.f9760b.e();
    }

    AnimatableTextFrame t() {
        return this.f9775q;
    }

    public String toString() {
        return z("");
    }

    AnimatableTextProperties u() {
        return this.f9776r;
    }

    AnimatableFloatValue v() {
        return this.f9777s;
    }

    float w() {
        return this.f9771m;
    }

    AnimatableTransform x() {
        return this.f9767i;
    }

    public boolean y() {
        return this.v;
    }

    public String z(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(j());
        sb.append("\n");
        Layer t = this.f9760b.t(k());
        if (t != null) {
            sb.append("\t\tParents: ");
            sb.append(t.j());
            Layer t2 = this.f9760b.t(t.k());
            while (t2 != null) {
                sb.append("->");
                sb.append(t2.j());
                t2 = this.f9760b.t(t2.k());
            }
            sb.append(str);
            sb.append("\n");
        }
        if (!h().isEmpty()) {
            sb.append(str);
            sb.append("\tMasks: ");
            sb.append(h().size());
            sb.append("\n");
        }
        if (r() != 0 && q() != 0) {
            sb.append(str);
            sb.append("\tBackground: ");
            sb.append(String.format(Locale.US, "%dx%d %X\n", Integer.valueOf(r()), Integer.valueOf(q()), Integer.valueOf(p())));
        }
        if (!this.f9759a.isEmpty()) {
            sb.append(str);
            sb.append("\tShapes:\n");
            for (Object obj : this.f9759a) {
                sb.append(str);
                sb.append("\t\t");
                sb.append(obj);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
