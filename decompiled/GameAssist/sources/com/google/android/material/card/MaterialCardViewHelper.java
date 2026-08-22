package com.google.android.material.card;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;

@RestrictTo
/* loaded from: classes.dex */
class MaterialCardViewHelper {

    /* renamed from: a, reason: collision with root package name */
    private final MaterialCardView f14105a;

    /* renamed from: c, reason: collision with root package name */
    private final MaterialShapeDrawable f14107c;

    /* renamed from: d, reason: collision with root package name */
    private final MaterialShapeDrawable f14108d;

    /* renamed from: e, reason: collision with root package name */
    private int f14109e;

    /* renamed from: f, reason: collision with root package name */
    private int f14110f;

    /* renamed from: g, reason: collision with root package name */
    private int f14111g;

    /* renamed from: h, reason: collision with root package name */
    private int f14112h;

    /* renamed from: i, reason: collision with root package name */
    private Drawable f14113i;

    /* renamed from: j, reason: collision with root package name */
    private Drawable f14114j;

    /* renamed from: k, reason: collision with root package name */
    private ColorStateList f14115k;

    /* renamed from: l, reason: collision with root package name */
    private ColorStateList f14116l;

    /* renamed from: m, reason: collision with root package name */
    private ShapeAppearanceModel f14117m;

    /* renamed from: n, reason: collision with root package name */
    private ColorStateList f14118n;

    /* renamed from: o, reason: collision with root package name */
    private Drawable f14119o;

    /* renamed from: p, reason: collision with root package name */
    private LayerDrawable f14120p;

    /* renamed from: q, reason: collision with root package name */
    private MaterialShapeDrawable f14121q;

    /* renamed from: r, reason: collision with root package name */
    private MaterialShapeDrawable f14122r;
    private boolean t;
    private ValueAnimator u;
    private final TimeInterpolator v;
    private final int w;
    private final int x;
    private static final double z = Math.cos(Math.toRadians(45.0d));
    private static final Drawable A = null;

    /* renamed from: b, reason: collision with root package name */
    private final Rect f14106b = new Rect();

    /* renamed from: s, reason: collision with root package name */
    private boolean f14123s = false;
    private float y = 0.0f;

    public MaterialCardViewHelper(MaterialCardView materialCardView, AttributeSet attributeSet, int i2, int i3) {
        this.f14105a = materialCardView;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(materialCardView.getContext(), attributeSet, i2, i3);
        this.f14107c = materialShapeDrawable;
        materialShapeDrawable.P(materialCardView.getContext());
        materialShapeDrawable.g0(-12303292);
        ShapeAppearanceModel.Builder v = materialShapeDrawable.getShapeAppearanceModel().v();
        TypedArray obtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, R.styleable.CardView, i2, R.style.CardView);
        if (obtainStyledAttributes.hasValue(R.styleable.CardView_cardCornerRadius)) {
            v.o(obtainStyledAttributes.getDimension(R.styleable.CardView_cardCornerRadius, 0.0f));
        }
        this.f14108d = new MaterialShapeDrawable();
        Z(v.m());
        this.v = MotionUtils.g(materialCardView.getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.f13814a);
        this.w = MotionUtils.f(materialCardView.getContext(), R.attr.motionDurationShort2, 300);
        this.x = MotionUtils.f(materialCardView.getContext(), R.attr.motionDurationShort1, 300);
        obtainStyledAttributes.recycle();
    }

    private Drawable D(Drawable drawable) {
        int i2;
        int i3;
        if (this.f14105a.getUseCompatPadding()) {
            i3 = (int) Math.ceil(f());
            i2 = (int) Math.ceil(e());
        } else {
            i2 = 0;
            i3 = 0;
        }
        return new InsetDrawable(drawable, i2, i3, i2, i3) { // from class: com.google.android.material.card.MaterialCardViewHelper.1
            @Override // android.graphics.drawable.Drawable
            public int getMinimumHeight() {
                return -1;
            }

            @Override // android.graphics.drawable.Drawable
            public int getMinimumWidth() {
                return -1;
            }

            @Override // android.graphics.drawable.InsetDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
            public boolean getPadding(Rect rect) {
                return false;
            }
        };
    }

    private boolean G() {
        return (this.f14111g & 80) == 80;
    }

    private boolean H() {
        return (this.f14111g & 8388613) == 8388613;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f14114j.setAlpha((int) (255.0f * floatValue));
        this.y = floatValue;
    }

    private float c() {
        return Math.max(Math.max(d(this.f14117m.q(), this.f14107c.I()), d(this.f14117m.s(), this.f14107c.J())), Math.max(d(this.f14117m.k(), this.f14107c.t()), d(this.f14117m.i(), this.f14107c.s())));
    }

    private float d(CornerTreatment cornerTreatment, float f2) {
        if (cornerTreatment instanceof RoundedCornerTreatment) {
            return (float) ((1.0d - z) * f2);
        }
        if (cornerTreatment instanceof CutCornerTreatment) {
            return f2 / 2.0f;
        }
        return 0.0f;
    }

    private boolean d0() {
        return this.f14105a.getPreventCornerOverlap() && !g();
    }

    private float e() {
        return this.f14105a.getMaxCardElevation() + (e0() ? c() : 0.0f);
    }

    private boolean e0() {
        return this.f14105a.getPreventCornerOverlap() && g() && this.f14105a.getUseCompatPadding();
    }

    private float f() {
        return (this.f14105a.getMaxCardElevation() * 1.5f) + (e0() ? c() : 0.0f);
    }

    private boolean f0() {
        if (this.f14105a.isClickable()) {
            return true;
        }
        View view = this.f14105a;
        while (view.isDuplicateParentStateEnabled() && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        return view.isClickable();
    }

    private boolean g() {
        return this.f14107c.S();
    }

    private Drawable h() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        MaterialShapeDrawable j2 = j();
        this.f14121q = j2;
        j2.a0(this.f14115k);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, this.f14121q);
        return stateListDrawable;
    }

    private Drawable i() {
        if (!RippleUtils.f14980a) {
            return h();
        }
        this.f14122r = j();
        return new RippleDrawable(this.f14115k, null, this.f14122r);
    }

    private MaterialShapeDrawable j() {
        return new MaterialShapeDrawable(this.f14117m);
    }

    private void j0(Drawable drawable) {
        if (this.f14105a.getForeground() instanceof InsetDrawable) {
            ((InsetDrawable) this.f14105a.getForeground()).setDrawable(drawable);
        } else {
            this.f14105a.setForeground(D(drawable));
        }
    }

    private void l0() {
        Drawable drawable;
        if (RippleUtils.f14980a && (drawable = this.f14119o) != null) {
            ((RippleDrawable) drawable).setColor(this.f14115k);
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = this.f14121q;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.a0(this.f14115k);
        }
    }

    private Drawable t() {
        if (this.f14119o == null) {
            this.f14119o = i();
        }
        if (this.f14120p == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.f14119o, this.f14108d, this.f14114j});
            this.f14120p = layerDrawable;
            layerDrawable.setId(2, R.id.mtrl_card_checked_layer_id);
        }
        return this.f14120p;
    }

    private float v() {
        if (this.f14105a.getPreventCornerOverlap() && this.f14105a.getUseCompatPadding()) {
            return (float) ((1.0d - z) * this.f14105a.getCardViewRadius());
        }
        return 0.0f;
    }

    ColorStateList A() {
        return this.f14118n;
    }

    int B() {
        return this.f14112h;
    }

    Rect C() {
        return this.f14106b;
    }

    boolean E() {
        return this.f14123s;
    }

    boolean F() {
        return this.t;
    }

    void J(TypedArray typedArray) {
        ColorStateList a2 = MaterialResources.a(this.f14105a.getContext(), typedArray, R.styleable.MaterialCardView_strokeColor);
        this.f14118n = a2;
        if (a2 == null) {
            this.f14118n = ColorStateList.valueOf(-1);
        }
        this.f14112h = typedArray.getDimensionPixelSize(R.styleable.MaterialCardView_strokeWidth, 0);
        boolean z2 = typedArray.getBoolean(R.styleable.MaterialCardView_android_checkable, false);
        this.t = z2;
        this.f14105a.setLongClickable(z2);
        this.f14116l = MaterialResources.a(this.f14105a.getContext(), typedArray, R.styleable.MaterialCardView_checkedIconTint);
        R(MaterialResources.e(this.f14105a.getContext(), typedArray, R.styleable.MaterialCardView_checkedIcon));
        U(typedArray.getDimensionPixelSize(R.styleable.MaterialCardView_checkedIconSize, 0));
        T(typedArray.getDimensionPixelSize(R.styleable.MaterialCardView_checkedIconMargin, 0));
        this.f14111g = typedArray.getInteger(R.styleable.MaterialCardView_checkedIconGravity, MaterialCardView.CHECKED_ICON_GRAVITY_TOP_END);
        ColorStateList a3 = MaterialResources.a(this.f14105a.getContext(), typedArray, R.styleable.MaterialCardView_rippleColor);
        this.f14115k = a3;
        if (a3 == null) {
            this.f14115k = ColorStateList.valueOf(MaterialColors.d(this.f14105a, R.attr.colorControlHighlight));
        }
        N(MaterialResources.a(this.f14105a.getContext(), typedArray, R.styleable.MaterialCardView_cardForegroundColor));
        l0();
        i0();
        m0();
        this.f14105a.setBackgroundInternal(D(this.f14107c));
        Drawable t = f0() ? t() : this.f14108d;
        this.f14113i = t;
        this.f14105a.setForeground(D(t));
    }

    void K(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        if (this.f14120p != null) {
            if (this.f14105a.getUseCompatPadding()) {
                i4 = (int) Math.ceil(f() * 2.0f);
                i5 = (int) Math.ceil(e() * 2.0f);
            } else {
                i4 = 0;
                i5 = 0;
            }
            int i8 = H() ? ((i2 - this.f14109e) - this.f14110f) - i5 : this.f14109e;
            int i9 = G() ? this.f14109e : ((i3 - this.f14109e) - this.f14110f) - i4;
            int i10 = H() ? this.f14109e : ((i2 - this.f14109e) - this.f14110f) - i5;
            int i11 = G() ? ((i3 - this.f14109e) - this.f14110f) - i4 : this.f14109e;
            if (ViewCompat.v(this.f14105a) == 1) {
                i7 = i10;
                i6 = i8;
            } else {
                i6 = i10;
                i7 = i8;
            }
            this.f14120p.setLayerInset(2, i7, i11, i6, i9);
        }
    }

    void L(boolean z2) {
        this.f14123s = z2;
    }

    void M(ColorStateList colorStateList) {
        this.f14107c.a0(colorStateList);
    }

    void N(ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = this.f14108d;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        materialShapeDrawable.a0(colorStateList);
    }

    void O(boolean z2) {
        this.t = z2;
    }

    public void P(boolean z2) {
        Q(z2, false);
    }

    public void Q(boolean z2, boolean z3) {
        Drawable drawable = this.f14114j;
        if (drawable != null) {
            if (z3) {
                b(z2);
            } else {
                drawable.setAlpha(z2 ? 255 : 0);
                this.y = z2 ? 1.0f : 0.0f;
            }
        }
    }

    void R(Drawable drawable) {
        if (drawable != null) {
            Drawable mutate = DrawableCompat.r(drawable).mutate();
            this.f14114j = mutate;
            DrawableCompat.o(mutate, this.f14116l);
            P(this.f14105a.isChecked());
        } else {
            this.f14114j = A;
        }
        LayerDrawable layerDrawable = this.f14120p;
        if (layerDrawable != null) {
            layerDrawable.setDrawableByLayerId(R.id.mtrl_card_checked_layer_id, this.f14114j);
        }
    }

    void S(int i2) {
        this.f14111g = i2;
        K(this.f14105a.getMeasuredWidth(), this.f14105a.getMeasuredHeight());
    }

    void T(int i2) {
        this.f14109e = i2;
    }

    void U(int i2) {
        this.f14110f = i2;
    }

    void V(ColorStateList colorStateList) {
        this.f14116l = colorStateList;
        Drawable drawable = this.f14114j;
        if (drawable != null) {
            DrawableCompat.o(drawable, colorStateList);
        }
    }

    void W(float f2) {
        Z(this.f14117m.w(f2));
        this.f14113i.invalidateSelf();
        if (e0() || d0()) {
            h0();
        }
        if (e0()) {
            k0();
        }
    }

    void X(float f2) {
        this.f14107c.b0(f2);
        MaterialShapeDrawable materialShapeDrawable = this.f14108d;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.b0(f2);
        }
        MaterialShapeDrawable materialShapeDrawable2 = this.f14122r;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.b0(f2);
        }
    }

    void Y(ColorStateList colorStateList) {
        this.f14115k = colorStateList;
        l0();
    }

    void Z(ShapeAppearanceModel shapeAppearanceModel) {
        this.f14117m = shapeAppearanceModel;
        this.f14107c.setShapeAppearanceModel(shapeAppearanceModel);
        this.f14107c.f0(!r0.S());
        MaterialShapeDrawable materialShapeDrawable = this.f14108d;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
        MaterialShapeDrawable materialShapeDrawable2 = this.f14122r;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.setShapeAppearanceModel(shapeAppearanceModel);
        }
        MaterialShapeDrawable materialShapeDrawable3 = this.f14121q;
        if (materialShapeDrawable3 != null) {
            materialShapeDrawable3.setShapeAppearanceModel(shapeAppearanceModel);
        }
    }

    void a0(ColorStateList colorStateList) {
        if (this.f14118n == colorStateList) {
            return;
        }
        this.f14118n = colorStateList;
        m0();
    }

    public void b(boolean z2) {
        float f2 = z2 ? 1.0f : 0.0f;
        float f3 = z2 ? 1.0f - this.y : this.y;
        ValueAnimator valueAnimator = this.u;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.u = null;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.y, f2);
        this.u = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.card.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                MaterialCardViewHelper.this.I(valueAnimator2);
            }
        });
        this.u.setInterpolator(this.v);
        this.u.setDuration((long) ((z2 ? this.w : this.x) * f3));
        this.u.start();
    }

    void b0(int i2) {
        if (i2 == this.f14112h) {
            return;
        }
        this.f14112h = i2;
        m0();
    }

    void c0(int i2, int i3, int i4, int i5) {
        this.f14106b.set(i2, i3, i4, i5);
        h0();
    }

    void g0() {
        Drawable drawable = this.f14113i;
        Drawable t = f0() ? t() : this.f14108d;
        this.f14113i = t;
        if (drawable != t) {
            j0(t);
        }
    }

    void h0() {
        int c2 = (int) (((d0() || e0()) ? c() : 0.0f) - v());
        MaterialCardView materialCardView = this.f14105a;
        Rect rect = this.f14106b;
        materialCardView.m(rect.left + c2, rect.top + c2, rect.right + c2, rect.bottom + c2);
    }

    void i0() {
        this.f14107c.Z(this.f14105a.getCardElevation());
    }

    void k() {
        Drawable drawable = this.f14119o;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int i2 = bounds.bottom;
            this.f14119o.setBounds(bounds.left, bounds.top, bounds.right, i2 - 1);
            this.f14119o.setBounds(bounds.left, bounds.top, bounds.right, i2);
        }
    }

    void k0() {
        if (!E()) {
            this.f14105a.setBackgroundInternal(D(this.f14107c));
        }
        this.f14105a.setForeground(D(this.f14113i));
    }

    MaterialShapeDrawable l() {
        return this.f14107c;
    }

    ColorStateList m() {
        return this.f14107c.x();
    }

    void m0() {
        this.f14108d.k0(this.f14112h, this.f14118n);
    }

    ColorStateList n() {
        return this.f14108d.x();
    }

    Drawable o() {
        return this.f14114j;
    }

    int p() {
        return this.f14111g;
    }

    int q() {
        return this.f14109e;
    }

    int r() {
        return this.f14110f;
    }

    ColorStateList s() {
        return this.f14116l;
    }

    float u() {
        return this.f14107c.I();
    }

    float w() {
        return this.f14107c.y();
    }

    ColorStateList x() {
        return this.f14115k;
    }

    ShapeAppearanceModel y() {
        return this.f14117m;
    }

    int z() {
        ColorStateList colorStateList = this.f14118n;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }
}
