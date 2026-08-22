package com.google.android.material.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import androidx.annotation.RestrictTo;
import com.google.android.material.R;

/* loaded from: classes.dex */
public class ShapeAppearanceModel {

    /* renamed from: m, reason: collision with root package name */
    public static final CornerSize f15133m = new RelativeCornerSize(0.5f);

    /* renamed from: a, reason: collision with root package name */
    CornerTreatment f15134a;

    /* renamed from: b, reason: collision with root package name */
    CornerTreatment f15135b;

    /* renamed from: c, reason: collision with root package name */
    CornerTreatment f15136c;

    /* renamed from: d, reason: collision with root package name */
    CornerTreatment f15137d;

    /* renamed from: e, reason: collision with root package name */
    CornerSize f15138e;

    /* renamed from: f, reason: collision with root package name */
    CornerSize f15139f;

    /* renamed from: g, reason: collision with root package name */
    CornerSize f15140g;

    /* renamed from: h, reason: collision with root package name */
    CornerSize f15141h;

    /* renamed from: i, reason: collision with root package name */
    EdgeTreatment f15142i;

    /* renamed from: j, reason: collision with root package name */
    EdgeTreatment f15143j;

    /* renamed from: k, reason: collision with root package name */
    EdgeTreatment f15144k;

    /* renamed from: l, reason: collision with root package name */
    EdgeTreatment f15145l;

    @RestrictTo
    public interface CornerSizeUnaryOperator {
        CornerSize a(CornerSize cornerSize);
    }

    public static Builder a() {
        return new Builder();
    }

    public static Builder b(Context context, int i2, int i3) {
        return c(context, i2, i3, 0);
    }

    private static Builder c(Context context, int i2, int i3, int i4) {
        return d(context, i2, i3, new AbsoluteCornerSize(i4));
    }

    private static Builder d(Context context, int i2, int i3, CornerSize cornerSize) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
        if (i3 != 0) {
            contextThemeWrapper = new ContextThemeWrapper(contextThemeWrapper, i3);
        }
        TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(R.styleable.ShapeAppearance);
        try {
            int i4 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamily, 0);
            int i5 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyTopLeft, i4);
            int i6 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyTopRight, i4);
            int i7 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomRight, i4);
            int i8 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomLeft, i4);
            CornerSize m2 = m(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSize, cornerSize);
            CornerSize m3 = m(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeTopLeft, m2);
            CornerSize m4 = m(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeTopRight, m2);
            CornerSize m5 = m(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeBottomRight, m2);
            return new Builder().C(i5, m3).G(i6, m4).x(i7, m5).t(i8, m(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeBottomLeft, m2));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static Builder e(Context context, AttributeSet attributeSet, int i2, int i3) {
        return f(context, attributeSet, i2, i3, 0);
    }

    public static Builder f(Context context, AttributeSet attributeSet, int i2, int i3, int i4) {
        return g(context, attributeSet, i2, i3, new AbsoluteCornerSize(i4));
    }

    public static Builder g(Context context, AttributeSet attributeSet, int i2, int i3, CornerSize cornerSize) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialShape, i2, i3);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.MaterialShape_shapeAppearanceOverlay, 0);
        obtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, cornerSize);
    }

    private static CornerSize m(TypedArray typedArray, int i2, CornerSize cornerSize) {
        TypedValue peekValue = typedArray.peekValue(i2);
        if (peekValue == null) {
            return cornerSize;
        }
        int i3 = peekValue.type;
        return i3 == 5 ? new AbsoluteCornerSize(TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics())) : i3 == 6 ? new RelativeCornerSize(peekValue.getFraction(1.0f, 1.0f)) : cornerSize;
    }

    public EdgeTreatment h() {
        return this.f15144k;
    }

    public CornerTreatment i() {
        return this.f15137d;
    }

    public CornerSize j() {
        return this.f15141h;
    }

    public CornerTreatment k() {
        return this.f15136c;
    }

    public CornerSize l() {
        return this.f15140g;
    }

    public EdgeTreatment n() {
        return this.f15145l;
    }

    public EdgeTreatment o() {
        return this.f15143j;
    }

    public EdgeTreatment p() {
        return this.f15142i;
    }

    public CornerTreatment q() {
        return this.f15134a;
    }

    public CornerSize r() {
        return this.f15138e;
    }

    public CornerTreatment s() {
        return this.f15135b;
    }

    public CornerSize t() {
        return this.f15139f;
    }

    public boolean u(RectF rectF) {
        boolean z = this.f15145l.getClass().equals(EdgeTreatment.class) && this.f15143j.getClass().equals(EdgeTreatment.class) && this.f15142i.getClass().equals(EdgeTreatment.class) && this.f15144k.getClass().equals(EdgeTreatment.class);
        float a2 = this.f15138e.a(rectF);
        return z && ((this.f15139f.a(rectF) > a2 ? 1 : (this.f15139f.a(rectF) == a2 ? 0 : -1)) == 0 && (this.f15141h.a(rectF) > a2 ? 1 : (this.f15141h.a(rectF) == a2 ? 0 : -1)) == 0 && (this.f15140g.a(rectF) > a2 ? 1 : (this.f15140g.a(rectF) == a2 ? 0 : -1)) == 0) && ((this.f15135b instanceof RoundedCornerTreatment) && (this.f15134a instanceof RoundedCornerTreatment) && (this.f15136c instanceof RoundedCornerTreatment) && (this.f15137d instanceof RoundedCornerTreatment));
    }

    public Builder v() {
        return new Builder(this);
    }

    public ShapeAppearanceModel w(float f2) {
        return v().o(f2).m();
    }

    public ShapeAppearanceModel x(CornerSize cornerSize) {
        return v().p(cornerSize).m();
    }

    public ShapeAppearanceModel y(CornerSizeUnaryOperator cornerSizeUnaryOperator) {
        return v().F(cornerSizeUnaryOperator.a(r())).J(cornerSizeUnaryOperator.a(t())).w(cornerSizeUnaryOperator.a(j())).A(cornerSizeUnaryOperator.a(l())).m();
    }

    private ShapeAppearanceModel(Builder builder) {
        this.f15134a = builder.f15146a;
        this.f15135b = builder.f15147b;
        this.f15136c = builder.f15148c;
        this.f15137d = builder.f15149d;
        this.f15138e = builder.f15150e;
        this.f15139f = builder.f15151f;
        this.f15140g = builder.f15152g;
        this.f15141h = builder.f15153h;
        this.f15142i = builder.f15154i;
        this.f15143j = builder.f15155j;
        this.f15144k = builder.f15156k;
        this.f15145l = builder.f15157l;
    }

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private CornerTreatment f15146a;

        /* renamed from: b, reason: collision with root package name */
        private CornerTreatment f15147b;

        /* renamed from: c, reason: collision with root package name */
        private CornerTreatment f15148c;

        /* renamed from: d, reason: collision with root package name */
        private CornerTreatment f15149d;

        /* renamed from: e, reason: collision with root package name */
        private CornerSize f15150e;

        /* renamed from: f, reason: collision with root package name */
        private CornerSize f15151f;

        /* renamed from: g, reason: collision with root package name */
        private CornerSize f15152g;

        /* renamed from: h, reason: collision with root package name */
        private CornerSize f15153h;

        /* renamed from: i, reason: collision with root package name */
        private EdgeTreatment f15154i;

        /* renamed from: j, reason: collision with root package name */
        private EdgeTreatment f15155j;

        /* renamed from: k, reason: collision with root package name */
        private EdgeTreatment f15156k;

        /* renamed from: l, reason: collision with root package name */
        private EdgeTreatment f15157l;

        public Builder() {
            this.f15146a = MaterialShapeUtils.b();
            this.f15147b = MaterialShapeUtils.b();
            this.f15148c = MaterialShapeUtils.b();
            this.f15149d = MaterialShapeUtils.b();
            this.f15150e = new AbsoluteCornerSize(0.0f);
            this.f15151f = new AbsoluteCornerSize(0.0f);
            this.f15152g = new AbsoluteCornerSize(0.0f);
            this.f15153h = new AbsoluteCornerSize(0.0f);
            this.f15154i = MaterialShapeUtils.c();
            this.f15155j = MaterialShapeUtils.c();
            this.f15156k = MaterialShapeUtils.c();
            this.f15157l = MaterialShapeUtils.c();
        }

        private static float n(CornerTreatment cornerTreatment) {
            if (cornerTreatment instanceof RoundedCornerTreatment) {
                return ((RoundedCornerTreatment) cornerTreatment).f15132a;
            }
            if (cornerTreatment instanceof CutCornerTreatment) {
                return ((CutCornerTreatment) cornerTreatment).f15086a;
            }
            return -1.0f;
        }

        public Builder A(CornerSize cornerSize) {
            this.f15152g = cornerSize;
            return this;
        }

        public Builder B(EdgeTreatment edgeTreatment) {
            this.f15154i = edgeTreatment;
            return this;
        }

        public Builder C(int i2, CornerSize cornerSize) {
            return D(MaterialShapeUtils.a(i2)).F(cornerSize);
        }

        public Builder D(CornerTreatment cornerTreatment) {
            this.f15146a = cornerTreatment;
            float n2 = n(cornerTreatment);
            if (n2 != -1.0f) {
                E(n2);
            }
            return this;
        }

        public Builder E(float f2) {
            this.f15150e = new AbsoluteCornerSize(f2);
            return this;
        }

        public Builder F(CornerSize cornerSize) {
            this.f15150e = cornerSize;
            return this;
        }

        public Builder G(int i2, CornerSize cornerSize) {
            return H(MaterialShapeUtils.a(i2)).J(cornerSize);
        }

        public Builder H(CornerTreatment cornerTreatment) {
            this.f15147b = cornerTreatment;
            float n2 = n(cornerTreatment);
            if (n2 != -1.0f) {
                I(n2);
            }
            return this;
        }

        public Builder I(float f2) {
            this.f15151f = new AbsoluteCornerSize(f2);
            return this;
        }

        public Builder J(CornerSize cornerSize) {
            this.f15151f = cornerSize;
            return this;
        }

        public ShapeAppearanceModel m() {
            return new ShapeAppearanceModel(this);
        }

        public Builder o(float f2) {
            return E(f2).I(f2).z(f2).v(f2);
        }

        public Builder p(CornerSize cornerSize) {
            return F(cornerSize).J(cornerSize).A(cornerSize).w(cornerSize);
        }

        public Builder q(int i2, float f2) {
            return r(MaterialShapeUtils.a(i2)).o(f2);
        }

        public Builder r(CornerTreatment cornerTreatment) {
            return D(cornerTreatment).H(cornerTreatment).y(cornerTreatment).u(cornerTreatment);
        }

        public Builder s(EdgeTreatment edgeTreatment) {
            this.f15156k = edgeTreatment;
            return this;
        }

        public Builder t(int i2, CornerSize cornerSize) {
            return u(MaterialShapeUtils.a(i2)).w(cornerSize);
        }

        public Builder u(CornerTreatment cornerTreatment) {
            this.f15149d = cornerTreatment;
            float n2 = n(cornerTreatment);
            if (n2 != -1.0f) {
                v(n2);
            }
            return this;
        }

        public Builder v(float f2) {
            this.f15153h = new AbsoluteCornerSize(f2);
            return this;
        }

        public Builder w(CornerSize cornerSize) {
            this.f15153h = cornerSize;
            return this;
        }

        public Builder x(int i2, CornerSize cornerSize) {
            return y(MaterialShapeUtils.a(i2)).A(cornerSize);
        }

        public Builder y(CornerTreatment cornerTreatment) {
            this.f15148c = cornerTreatment;
            float n2 = n(cornerTreatment);
            if (n2 != -1.0f) {
                z(n2);
            }
            return this;
        }

        public Builder z(float f2) {
            this.f15152g = new AbsoluteCornerSize(f2);
            return this;
        }

        public Builder(ShapeAppearanceModel shapeAppearanceModel) {
            this.f15146a = MaterialShapeUtils.b();
            this.f15147b = MaterialShapeUtils.b();
            this.f15148c = MaterialShapeUtils.b();
            this.f15149d = MaterialShapeUtils.b();
            this.f15150e = new AbsoluteCornerSize(0.0f);
            this.f15151f = new AbsoluteCornerSize(0.0f);
            this.f15152g = new AbsoluteCornerSize(0.0f);
            this.f15153h = new AbsoluteCornerSize(0.0f);
            this.f15154i = MaterialShapeUtils.c();
            this.f15155j = MaterialShapeUtils.c();
            this.f15156k = MaterialShapeUtils.c();
            this.f15157l = MaterialShapeUtils.c();
            this.f15146a = shapeAppearanceModel.f15134a;
            this.f15147b = shapeAppearanceModel.f15135b;
            this.f15148c = shapeAppearanceModel.f15136c;
            this.f15149d = shapeAppearanceModel.f15137d;
            this.f15150e = shapeAppearanceModel.f15138e;
            this.f15151f = shapeAppearanceModel.f15139f;
            this.f15152g = shapeAppearanceModel.f15140g;
            this.f15153h = shapeAppearanceModel.f15141h;
            this.f15154i = shapeAppearanceModel.f15142i;
            this.f15155j = shapeAppearanceModel.f15143j;
            this.f15156k = shapeAppearanceModel.f15144k;
            this.f15157l = shapeAppearanceModel.f15145l;
        }
    }

    public ShapeAppearanceModel() {
        this.f15134a = MaterialShapeUtils.b();
        this.f15135b = MaterialShapeUtils.b();
        this.f15136c = MaterialShapeUtils.b();
        this.f15137d = MaterialShapeUtils.b();
        this.f15138e = new AbsoluteCornerSize(0.0f);
        this.f15139f = new AbsoluteCornerSize(0.0f);
        this.f15140g = new AbsoluteCornerSize(0.0f);
        this.f15141h = new AbsoluteCornerSize(0.0f);
        this.f15142i = MaterialShapeUtils.c();
        this.f15143j = MaterialShapeUtils.c();
        this.f15144k = MaterialShapeUtils.c();
        this.f15145l = MaterialShapeUtils.c();
    }
}
