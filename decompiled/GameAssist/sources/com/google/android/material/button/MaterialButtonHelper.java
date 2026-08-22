package com.google.android.material.button;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleDrawableCompat;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;

@RestrictTo
/* loaded from: classes.dex */
class MaterialButtonHelper {
    private static final boolean u = true;
    private static final boolean v = false;

    /* renamed from: a, reason: collision with root package name */
    private final MaterialButton f14078a;

    /* renamed from: b, reason: collision with root package name */
    private ShapeAppearanceModel f14079b;

    /* renamed from: c, reason: collision with root package name */
    private int f14080c;

    /* renamed from: d, reason: collision with root package name */
    private int f14081d;

    /* renamed from: e, reason: collision with root package name */
    private int f14082e;

    /* renamed from: f, reason: collision with root package name */
    private int f14083f;

    /* renamed from: g, reason: collision with root package name */
    private int f14084g;

    /* renamed from: h, reason: collision with root package name */
    private int f14085h;

    /* renamed from: i, reason: collision with root package name */
    private PorterDuff.Mode f14086i;

    /* renamed from: j, reason: collision with root package name */
    private ColorStateList f14087j;

    /* renamed from: k, reason: collision with root package name */
    private ColorStateList f14088k;

    /* renamed from: l, reason: collision with root package name */
    private ColorStateList f14089l;

    /* renamed from: m, reason: collision with root package name */
    private Drawable f14090m;

    /* renamed from: q, reason: collision with root package name */
    private boolean f14094q;

    /* renamed from: s, reason: collision with root package name */
    private LayerDrawable f14096s;
    private int t;

    /* renamed from: n, reason: collision with root package name */
    private boolean f14091n = false;

    /* renamed from: o, reason: collision with root package name */
    private boolean f14092o = false;

    /* renamed from: p, reason: collision with root package name */
    private boolean f14093p = false;

    /* renamed from: r, reason: collision with root package name */
    private boolean f14095r = true;

    MaterialButtonHelper(MaterialButton materialButton, ShapeAppearanceModel shapeAppearanceModel) {
        this.f14078a = materialButton;
        this.f14079b = shapeAppearanceModel;
    }

    private void G(int i2, int i3) {
        int z = ViewCompat.z(this.f14078a);
        int paddingTop = this.f14078a.getPaddingTop();
        int y = ViewCompat.y(this.f14078a);
        int paddingBottom = this.f14078a.getPaddingBottom();
        int i4 = this.f14082e;
        int i5 = this.f14083f;
        this.f14083f = i3;
        this.f14082e = i2;
        if (!this.f14092o) {
            H();
        }
        ViewCompat.y0(this.f14078a, z, (paddingTop + i2) - i4, y, (paddingBottom + i3) - i5);
    }

    private void H() {
        this.f14078a.setInternalBackground(a());
        MaterialShapeDrawable f2 = f();
        if (f2 != null) {
            f2.Z(this.t);
            f2.setState(this.f14078a.getDrawableState());
        }
    }

    private void I(ShapeAppearanceModel shapeAppearanceModel) {
        if (v && !this.f14092o) {
            int z = ViewCompat.z(this.f14078a);
            int paddingTop = this.f14078a.getPaddingTop();
            int y = ViewCompat.y(this.f14078a);
            int paddingBottom = this.f14078a.getPaddingBottom();
            H();
            ViewCompat.y0(this.f14078a, z, paddingTop, y, paddingBottom);
            return;
        }
        if (f() != null) {
            f().setShapeAppearanceModel(shapeAppearanceModel);
        }
        if (n() != null) {
            n().setShapeAppearanceModel(shapeAppearanceModel);
        }
        if (e() != null) {
            e().setShapeAppearanceModel(shapeAppearanceModel);
        }
    }

    private void J() {
        MaterialShapeDrawable f2 = f();
        MaterialShapeDrawable n2 = n();
        if (f2 != null) {
            f2.k0(this.f14085h, this.f14088k);
            if (n2 != null) {
                n2.j0(this.f14085h, this.f14091n ? MaterialColors.d(this.f14078a, R.attr.colorSurface) : 0);
            }
        }
    }

    private InsetDrawable K(Drawable drawable) {
        return new InsetDrawable(drawable, this.f14080c, this.f14082e, this.f14081d, this.f14083f);
    }

    private Drawable a() {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.f14079b);
        materialShapeDrawable.P(this.f14078a.getContext());
        DrawableCompat.o(materialShapeDrawable, this.f14087j);
        PorterDuff.Mode mode = this.f14086i;
        if (mode != null) {
            DrawableCompat.p(materialShapeDrawable, mode);
        }
        materialShapeDrawable.k0(this.f14085h, this.f14088k);
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(this.f14079b);
        materialShapeDrawable2.setTint(0);
        materialShapeDrawable2.j0(this.f14085h, this.f14091n ? MaterialColors.d(this.f14078a, R.attr.colorSurface) : 0);
        if (u) {
            MaterialShapeDrawable materialShapeDrawable3 = new MaterialShapeDrawable(this.f14079b);
            this.f14090m = materialShapeDrawable3;
            DrawableCompat.n(materialShapeDrawable3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(RippleUtils.d(this.f14089l), K(new LayerDrawable(new Drawable[]{materialShapeDrawable2, materialShapeDrawable})), this.f14090m);
            this.f14096s = rippleDrawable;
            return rippleDrawable;
        }
        RippleDrawableCompat rippleDrawableCompat = new RippleDrawableCompat(this.f14079b);
        this.f14090m = rippleDrawableCompat;
        DrawableCompat.o(rippleDrawableCompat, RippleUtils.d(this.f14089l));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{materialShapeDrawable2, materialShapeDrawable, this.f14090m});
        this.f14096s = layerDrawable;
        return K(layerDrawable);
    }

    private MaterialShapeDrawable g(boolean z) {
        LayerDrawable layerDrawable = this.f14096s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return u ? (MaterialShapeDrawable) ((LayerDrawable) ((InsetDrawable) this.f14096s.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0) : (MaterialShapeDrawable) this.f14096s.getDrawable(!z ? 1 : 0);
    }

    private MaterialShapeDrawable n() {
        return g(true);
    }

    void A(boolean z) {
        this.f14091n = z;
        J();
    }

    void B(ColorStateList colorStateList) {
        if (this.f14088k != colorStateList) {
            this.f14088k = colorStateList;
            J();
        }
    }

    void C(int i2) {
        if (this.f14085h != i2) {
            this.f14085h = i2;
            J();
        }
    }

    void D(ColorStateList colorStateList) {
        if (this.f14087j != colorStateList) {
            this.f14087j = colorStateList;
            if (f() != null) {
                DrawableCompat.o(f(), this.f14087j);
            }
        }
    }

    void E(PorterDuff.Mode mode) {
        if (this.f14086i != mode) {
            this.f14086i = mode;
            if (f() == null || this.f14086i == null) {
                return;
            }
            DrawableCompat.p(f(), this.f14086i);
        }
    }

    void F(boolean z) {
        this.f14095r = z;
    }

    int b() {
        return this.f14084g;
    }

    public int c() {
        return this.f14083f;
    }

    public int d() {
        return this.f14082e;
    }

    public Shapeable e() {
        LayerDrawable layerDrawable = this.f14096s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.f14096s.getNumberOfLayers() > 2 ? (Shapeable) this.f14096s.getDrawable(2) : (Shapeable) this.f14096s.getDrawable(1);
    }

    MaterialShapeDrawable f() {
        return g(false);
    }

    ColorStateList h() {
        return this.f14089l;
    }

    ShapeAppearanceModel i() {
        return this.f14079b;
    }

    ColorStateList j() {
        return this.f14088k;
    }

    int k() {
        return this.f14085h;
    }

    ColorStateList l() {
        return this.f14087j;
    }

    PorterDuff.Mode m() {
        return this.f14086i;
    }

    boolean o() {
        return this.f14092o;
    }

    boolean p() {
        return this.f14094q;
    }

    boolean q() {
        return this.f14095r;
    }

    void r(TypedArray typedArray) {
        this.f14080c = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetLeft, 0);
        this.f14081d = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetRight, 0);
        this.f14082e = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetTop, 0);
        this.f14083f = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetBottom, 0);
        if (typedArray.hasValue(R.styleable.MaterialButton_cornerRadius)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_cornerRadius, -1);
            this.f14084g = dimensionPixelSize;
            z(this.f14079b.w(dimensionPixelSize));
            this.f14093p = true;
        }
        this.f14085h = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_strokeWidth, 0);
        this.f14086i = ViewUtils.r(typedArray.getInt(R.styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.f14087j = MaterialResources.a(this.f14078a.getContext(), typedArray, R.styleable.MaterialButton_backgroundTint);
        this.f14088k = MaterialResources.a(this.f14078a.getContext(), typedArray, R.styleable.MaterialButton_strokeColor);
        this.f14089l = MaterialResources.a(this.f14078a.getContext(), typedArray, R.styleable.MaterialButton_rippleColor);
        this.f14094q = typedArray.getBoolean(R.styleable.MaterialButton_android_checkable, false);
        this.t = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_elevation, 0);
        this.f14095r = typedArray.getBoolean(R.styleable.MaterialButton_toggleCheckedStateOnClick, true);
        int z = ViewCompat.z(this.f14078a);
        int paddingTop = this.f14078a.getPaddingTop();
        int y = ViewCompat.y(this.f14078a);
        int paddingBottom = this.f14078a.getPaddingBottom();
        if (typedArray.hasValue(R.styleable.MaterialButton_android_background)) {
            t();
        } else {
            H();
        }
        ViewCompat.y0(this.f14078a, z + this.f14080c, paddingTop + this.f14082e, y + this.f14081d, paddingBottom + this.f14083f);
    }

    void s(int i2) {
        if (f() != null) {
            f().setTint(i2);
        }
    }

    void t() {
        this.f14092o = true;
        this.f14078a.setSupportBackgroundTintList(this.f14087j);
        this.f14078a.setSupportBackgroundTintMode(this.f14086i);
    }

    void u(boolean z) {
        this.f14094q = z;
    }

    void v(int i2) {
        if (this.f14093p && this.f14084g == i2) {
            return;
        }
        this.f14084g = i2;
        this.f14093p = true;
        z(this.f14079b.w(i2));
    }

    public void w(int i2) {
        G(this.f14082e, i2);
    }

    public void x(int i2) {
        G(i2, this.f14083f);
    }

    void y(ColorStateList colorStateList) {
        if (this.f14089l != colorStateList) {
            this.f14089l = colorStateList;
            boolean z = u;
            if (z && (this.f14078a.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f14078a.getBackground()).setColor(RippleUtils.d(colorStateList));
            } else {
                if (z || !(this.f14078a.getBackground() instanceof RippleDrawableCompat)) {
                    return;
                }
                ((RippleDrawableCompat) this.f14078a.getBackground()).setTintList(RippleUtils.d(colorStateList));
            }
        }
    }

    void z(ShapeAppearanceModel shapeAppearanceModel) {
        this.f14079b = shapeAppearanceModel;
        I(shapeAppearanceModel);
    }
}
