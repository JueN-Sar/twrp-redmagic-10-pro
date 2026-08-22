package com.google.android.material.chip;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.text.BidiFormatter;
import com.google.android.gms.common.api.Api;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ChipDrawable extends MaterialShapeDrawable implements TintAwareDrawable, Drawable.Callback, TextDrawableHelper.TextDrawableDelegate {
    private static final int[] P0 = {R.attr.state_enabled};
    private static final ShapeDrawable Q0 = new ShapeDrawable(new OvalShape());
    private boolean A0;
    private int B0;
    private int C0;
    private ColorFilter D0;
    private PorterDuffColorFilter E0;
    private ColorStateList F;
    private ColorStateList F0;
    private ColorStateList G;
    private PorterDuff.Mode G0;
    private float H;
    private int[] H0;
    private float I;
    private boolean I0;
    private ColorStateList J;
    private ColorStateList J0;
    private float K;
    private WeakReference K0;
    private ColorStateList L;
    private TextUtils.TruncateAt L0;
    private CharSequence M;
    private boolean M0;
    private boolean N;
    private int N0;
    private Drawable O;
    private boolean O0;
    private ColorStateList P;
    private float Q;
    private boolean R;
    private boolean S;
    private Drawable T;
    private Drawable U;
    private ColorStateList V;
    private float W;
    private CharSequence X;
    private boolean Y;
    private boolean Z;
    private Drawable a0;
    private ColorStateList b0;
    private MotionSpec c0;
    private MotionSpec d0;
    private float e0;
    private float f0;
    private float g0;
    private float h0;
    private float i0;
    private float j0;
    private float k0;
    private float l0;
    private final Context m0;
    private final Paint n0;
    private final Paint o0;
    private final Paint.FontMetrics p0;
    private final RectF q0;
    private final PointF r0;
    private final Path s0;
    private final TextDrawableHelper t0;
    private int u0;
    private int v0;
    private int w0;
    private int x0;
    private int y0;
    private int z0;

    public interface Delegate {
        void a();
    }

    private ChipDrawable(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.I = -1.0f;
        this.n0 = new Paint(1);
        this.p0 = new Paint.FontMetrics();
        this.q0 = new RectF();
        this.r0 = new PointF();
        this.s0 = new Path();
        this.C0 = 255;
        this.G0 = PorterDuff.Mode.SRC_IN;
        this.K0 = new WeakReference(null);
        P(context);
        this.m0 = context;
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.t0 = textDrawableHelper;
        this.M = "";
        textDrawableHelper.g().density = context.getResources().getDisplayMetrics().density;
        this.o0 = null;
        int[] iArr = P0;
        setState(iArr);
        r2(iArr);
        this.M0 = true;
        if (RippleUtils.f14980a) {
            Q0.setTint(-1);
        }
    }

    private boolean A0() {
        return this.Z && this.a0 != null && this.Y;
    }

    private void A1(AttributeSet attributeSet, int i2, int i3) {
        TypedArray i4 = ThemeEnforcement.i(this.m0, attributeSet, com.google.android.material.R.styleable.Chip, i2, i3, new int[0]);
        this.O0 = i4.hasValue(com.google.android.material.R.styleable.Chip_shapeAppearance);
        h2(MaterialResources.a(this.m0, i4, com.google.android.material.R.styleable.Chip_chipSurfaceColor));
        L1(MaterialResources.a(this.m0, i4, com.google.android.material.R.styleable.Chip_chipBackgroundColor));
        Z1(i4.getDimension(com.google.android.material.R.styleable.Chip_chipMinHeight, 0.0f));
        if (i4.hasValue(com.google.android.material.R.styleable.Chip_chipCornerRadius)) {
            N1(i4.getDimension(com.google.android.material.R.styleable.Chip_chipCornerRadius, 0.0f));
        }
        d2(MaterialResources.a(this.m0, i4, com.google.android.material.R.styleable.Chip_chipStrokeColor));
        f2(i4.getDimension(com.google.android.material.R.styleable.Chip_chipStrokeWidth, 0.0f));
        E2(MaterialResources.a(this.m0, i4, com.google.android.material.R.styleable.Chip_rippleColor));
        J2(i4.getText(com.google.android.material.R.styleable.Chip_android_text));
        TextAppearance h2 = MaterialResources.h(this.m0, i4, com.google.android.material.R.styleable.Chip_android_textAppearance);
        h2.k(i4.getDimension(com.google.android.material.R.styleable.Chip_android_textSize, h2.i()));
        K2(h2);
        int i5 = i4.getInt(com.google.android.material.R.styleable.Chip_android_ellipsize, 0);
        if (i5 == 1) {
            w2(TextUtils.TruncateAt.START);
        } else if (i5 == 2) {
            w2(TextUtils.TruncateAt.MIDDLE);
        } else if (i5 == 3) {
            w2(TextUtils.TruncateAt.END);
        }
        Y1(i4.getBoolean(com.google.android.material.R.styleable.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            Y1(i4.getBoolean(com.google.android.material.R.styleable.Chip_chipIconEnabled, false));
        }
        R1(MaterialResources.e(this.m0, i4, com.google.android.material.R.styleable.Chip_chipIcon));
        if (i4.hasValue(com.google.android.material.R.styleable.Chip_chipIconTint)) {
            V1(MaterialResources.a(this.m0, i4, com.google.android.material.R.styleable.Chip_chipIconTint));
        }
        T1(i4.getDimension(com.google.android.material.R.styleable.Chip_chipIconSize, -1.0f));
        u2(i4.getBoolean(com.google.android.material.R.styleable.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            u2(i4.getBoolean(com.google.android.material.R.styleable.Chip_closeIconEnabled, false));
        }
        i2(MaterialResources.e(this.m0, i4, com.google.android.material.R.styleable.Chip_closeIcon));
        s2(MaterialResources.a(this.m0, i4, com.google.android.material.R.styleable.Chip_closeIconTint));
        n2(i4.getDimension(com.google.android.material.R.styleable.Chip_closeIconSize, 0.0f));
        D1(i4.getBoolean(com.google.android.material.R.styleable.Chip_android_checkable, false));
        K1(i4.getBoolean(com.google.android.material.R.styleable.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            K1(i4.getBoolean(com.google.android.material.R.styleable.Chip_checkedIconEnabled, false));
        }
        F1(MaterialResources.e(this.m0, i4, com.google.android.material.R.styleable.Chip_checkedIcon));
        if (i4.hasValue(com.google.android.material.R.styleable.Chip_checkedIconTint)) {
            H1(MaterialResources.a(this.m0, i4, com.google.android.material.R.styleable.Chip_checkedIconTint));
        }
        H2(MotionSpec.c(this.m0, i4, com.google.android.material.R.styleable.Chip_showMotionSpec));
        x2(MotionSpec.c(this.m0, i4, com.google.android.material.R.styleable.Chip_hideMotionSpec));
        b2(i4.getDimension(com.google.android.material.R.styleable.Chip_chipStartPadding, 0.0f));
        B2(i4.getDimension(com.google.android.material.R.styleable.Chip_iconStartPadding, 0.0f));
        z2(i4.getDimension(com.google.android.material.R.styleable.Chip_iconEndPadding, 0.0f));
        P2(i4.getDimension(com.google.android.material.R.styleable.Chip_textStartPadding, 0.0f));
        M2(i4.getDimension(com.google.android.material.R.styleable.Chip_textEndPadding, 0.0f));
        p2(i4.getDimension(com.google.android.material.R.styleable.Chip_closeIconStartPadding, 0.0f));
        k2(i4.getDimension(com.google.android.material.R.styleable.Chip_closeIconEndPadding, 0.0f));
        P1(i4.getDimension(com.google.android.material.R.styleable.Chip_chipEndPadding, 0.0f));
        D2(i4.getDimensionPixelSize(com.google.android.material.R.styleable.Chip_android_maxWidth, Api.BaseClientBuilder.API_PRIORITY_OTHER));
        i4.recycle();
    }

    public static ChipDrawable B0(Context context, AttributeSet attributeSet, int i2, int i3) {
        ChipDrawable chipDrawable = new ChipDrawable(context, attributeSet, i2, i3);
        chipDrawable.A1(attributeSet, i2, i3);
        return chipDrawable;
    }

    private void C0(Canvas canvas, Rect rect) {
        if (T2()) {
            r0(rect, this.q0);
            RectF rectF = this.q0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.a0.setBounds(0, 0, (int) this.q0.width(), (int) this.q0.height());
            this.a0.draw(canvas);
            canvas.translate(-f2, -f3);
        }
    }

    private boolean C1(int[] iArr, int[] iArr2) {
        boolean z;
        boolean onStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList = this.F;
        int l2 = l(colorStateList != null ? colorStateList.getColorForState(iArr, this.u0) : 0);
        boolean z2 = true;
        if (this.u0 != l2) {
            this.u0 = l2;
            onStateChange = true;
        }
        ColorStateList colorStateList2 = this.G;
        int l3 = l(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.v0) : 0);
        if (this.v0 != l3) {
            this.v0 = l3;
            onStateChange = true;
        }
        int k2 = MaterialColors.k(l2, l3);
        if ((this.w0 != k2) | (x() == null)) {
            this.w0 = k2;
            a0(ColorStateList.valueOf(k2));
            onStateChange = true;
        }
        ColorStateList colorStateList3 = this.J;
        int colorForState = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.x0) : 0;
        if (this.x0 != colorForState) {
            this.x0 = colorForState;
            onStateChange = true;
        }
        int colorForState2 = (this.J0 == null || !RippleUtils.e(iArr)) ? 0 : this.J0.getColorForState(iArr, this.y0);
        if (this.y0 != colorForState2) {
            this.y0 = colorForState2;
            if (this.I0) {
                onStateChange = true;
            }
        }
        int colorForState3 = (this.t0.e() == null || this.t0.e().h() == null) ? 0 : this.t0.e().h().getColorForState(iArr, this.z0);
        if (this.z0 != colorForState3) {
            this.z0 = colorForState3;
            onStateChange = true;
        }
        boolean z3 = t1(getState(), R.attr.state_checked) && this.Y;
        if (this.A0 == z3 || this.a0 == null) {
            z = false;
        } else {
            float s0 = s0();
            this.A0 = z3;
            if (s0 != s0()) {
                onStateChange = true;
                z = true;
            } else {
                z = false;
                onStateChange = true;
            }
        }
        ColorStateList colorStateList4 = this.F0;
        int colorForState4 = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.B0) : 0;
        if (this.B0 != colorForState4) {
            this.B0 = colorForState4;
            this.E0 = DrawableUtils.o(this, this.F0, this.G0);
        } else {
            z2 = onStateChange;
        }
        if (y1(this.O)) {
            z2 |= this.O.setState(iArr);
        }
        if (y1(this.a0)) {
            z2 |= this.a0.setState(iArr);
        }
        if (y1(this.T)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            z2 |= this.T.setState(iArr3);
        }
        if (RippleUtils.f14980a && y1(this.U)) {
            z2 |= this.U.setState(iArr2);
        }
        if (z2) {
            invalidateSelf();
        }
        if (z) {
            B1();
        }
        return z2;
    }

    private void D0(Canvas canvas, Rect rect) {
        if (this.O0) {
            return;
        }
        this.n0.setColor(this.v0);
        this.n0.setStyle(Paint.Style.FILL);
        this.n0.setColorFilter(r1());
        this.q0.set(rect);
        canvas.drawRoundRect(this.q0, O0(), O0(), this.n0);
    }

    private void E0(Canvas canvas, Rect rect) {
        if (U2()) {
            r0(rect, this.q0);
            RectF rectF = this.q0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.O.setBounds(0, 0, (int) this.q0.width(), (int) this.q0.height());
            this.O.draw(canvas);
            canvas.translate(-f2, -f3);
        }
    }

    private void F0(Canvas canvas, Rect rect) {
        if (this.K <= 0.0f || this.O0) {
            return;
        }
        this.n0.setColor(this.x0);
        this.n0.setStyle(Paint.Style.STROKE);
        if (!this.O0) {
            this.n0.setColorFilter(r1());
        }
        RectF rectF = this.q0;
        float f2 = rect.left;
        float f3 = this.K;
        rectF.set(f2 + (f3 / 2.0f), rect.top + (f3 / 2.0f), rect.right - (f3 / 2.0f), rect.bottom - (f3 / 2.0f));
        float f4 = this.I - (this.K / 2.0f);
        canvas.drawRoundRect(this.q0, f4, f4, this.n0);
    }

    private void G0(Canvas canvas, Rect rect) {
        if (this.O0) {
            return;
        }
        this.n0.setColor(this.u0);
        this.n0.setStyle(Paint.Style.FILL);
        this.q0.set(rect);
        canvas.drawRoundRect(this.q0, O0(), O0(), this.n0);
    }

    private void H0(Canvas canvas, Rect rect) {
        if (V2()) {
            u0(rect, this.q0);
            RectF rectF = this.q0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.T.setBounds(0, 0, (int) this.q0.width(), (int) this.q0.height());
            if (RippleUtils.f14980a) {
                this.U.setBounds(this.T.getBounds());
                this.U.jumpToCurrentState();
                this.U.draw(canvas);
            } else {
                this.T.draw(canvas);
            }
            canvas.translate(-f2, -f3);
        }
    }

    private void I0(Canvas canvas, Rect rect) {
        this.n0.setColor(this.y0);
        this.n0.setStyle(Paint.Style.FILL);
        this.q0.set(rect);
        if (!this.O0) {
            canvas.drawRoundRect(this.q0, O0(), O0(), this.n0);
        } else {
            h(new RectF(rect), this.s0);
            super.p(canvas, this.n0, this.s0, u());
        }
    }

    private void J0(Canvas canvas, Rect rect) {
        Paint paint = this.o0;
        if (paint != null) {
            paint.setColor(ColorUtils.k(-16777216, 127));
            canvas.drawRect(rect, this.o0);
            if (U2() || T2()) {
                r0(rect, this.q0);
                canvas.drawRect(this.q0, this.o0);
            }
            if (this.M != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.o0);
            }
            if (V2()) {
                u0(rect, this.q0);
                canvas.drawRect(this.q0, this.o0);
            }
            this.o0.setColor(ColorUtils.k(-65536, 127));
            t0(rect, this.q0);
            canvas.drawRect(this.q0, this.o0);
            this.o0.setColor(ColorUtils.k(-16711936, 127));
            v0(rect, this.q0);
            canvas.drawRect(this.q0, this.o0);
        }
    }

    private void K0(Canvas canvas, Rect rect) {
        if (this.M != null) {
            Paint.Align z0 = z0(rect, this.r0);
            x0(rect, this.q0);
            if (this.t0.e() != null) {
                this.t0.g().drawableState = getState();
                this.t0.n(this.m0);
            }
            this.t0.g().setTextAlign(z0);
            int i2 = 0;
            boolean z = Math.round(this.t0.h(n1().toString())) > Math.round(this.q0.width());
            if (z) {
                i2 = canvas.save();
                canvas.clipRect(this.q0);
            }
            CharSequence charSequence = this.M;
            if (z && this.L0 != null) {
                charSequence = TextUtils.ellipsize(charSequence, this.t0.g(), this.q0.width(), this.L0);
            }
            CharSequence charSequence2 = charSequence;
            int length = charSequence2.length();
            PointF pointF = this.r0;
            canvas.drawText(charSequence2, 0, length, pointF.x, pointF.y, this.t0.g());
            if (z) {
                canvas.restoreToCount(i2);
            }
        }
    }

    private boolean T2() {
        return this.Z && this.a0 != null && this.A0;
    }

    private boolean U2() {
        return this.N && this.O != null;
    }

    private boolean V2() {
        return this.S && this.T != null;
    }

    private void W2(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    private void X2() {
        this.J0 = this.I0 ? RippleUtils.d(this.L) : null;
    }

    private void Y2() {
        this.U = new RippleDrawable(RippleUtils.d(l1()), this.T, Q0);
    }

    private float f1() {
        Drawable drawable = this.A0 ? this.a0 : this.O;
        float f2 = this.Q;
        if (f2 > 0.0f || drawable == null) {
            return f2;
        }
        float ceil = (float) Math.ceil(ViewUtils.h(this.m0, 24));
        return ((float) drawable.getIntrinsicHeight()) <= ceil ? drawable.getIntrinsicHeight() : ceil;
    }

    private float g1() {
        Drawable drawable = this.A0 ? this.a0 : this.O;
        float f2 = this.Q;
        return (f2 > 0.0f || drawable == null) ? f2 : drawable.getIntrinsicWidth();
    }

    private void h2(ColorStateList colorStateList) {
        if (this.F != colorStateList) {
            this.F = colorStateList;
            onStateChange(getState());
        }
    }

    private void q0(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        DrawableCompat.m(drawable, DrawableCompat.f(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.T) {
            if (drawable.isStateful()) {
                drawable.setState(c1());
            }
            DrawableCompat.o(drawable, this.V);
            return;
        }
        Drawable drawable2 = this.O;
        if (drawable == drawable2 && this.R) {
            DrawableCompat.o(drawable2, this.P);
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
    }

    private void r0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (U2() || T2()) {
            float f2 = this.e0 + this.f0;
            float g1 = g1();
            if (DrawableCompat.f(this) == 0) {
                float f3 = rect.left + f2;
                rectF.left = f3;
                rectF.right = f3 + g1;
            } else {
                float f4 = rect.right - f2;
                rectF.right = f4;
                rectF.left = f4 - g1;
            }
            float f1 = f1();
            float exactCenterY = rect.exactCenterY() - (f1 / 2.0f);
            rectF.top = exactCenterY;
            rectF.bottom = exactCenterY + f1;
        }
    }

    private ColorFilter r1() {
        ColorFilter colorFilter = this.D0;
        return colorFilter != null ? colorFilter : this.E0;
    }

    private void t0(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (V2()) {
            float f2 = this.l0 + this.k0 + this.W + this.j0 + this.i0;
            if (DrawableCompat.f(this) == 0) {
                rectF.right = rect.right - f2;
            } else {
                rectF.left = rect.left + f2;
            }
        }
    }

    private static boolean t1(int[] iArr, int i2) {
        if (iArr == null) {
            return false;
        }
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    private void u0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (V2()) {
            float f2 = this.l0 + this.k0;
            if (DrawableCompat.f(this) == 0) {
                float f3 = rect.right - f2;
                rectF.right = f3;
                rectF.left = f3 - this.W;
            } else {
                float f4 = rect.left + f2;
                rectF.left = f4;
                rectF.right = f4 + this.W;
            }
            float exactCenterY = rect.exactCenterY();
            float f5 = this.W;
            float f6 = exactCenterY - (f5 / 2.0f);
            rectF.top = f6;
            rectF.bottom = f6 + f5;
        }
    }

    private void v0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (V2()) {
            float f2 = this.l0 + this.k0 + this.W + this.j0 + this.i0;
            if (DrawableCompat.f(this) == 0) {
                float f3 = rect.right;
                rectF.right = f3;
                rectF.left = f3 - f2;
            } else {
                int i2 = rect.left;
                rectF.left = i2;
                rectF.right = i2 + f2;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private void x0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.M != null) {
            float s0 = this.e0 + s0() + this.h0;
            float w0 = this.l0 + w0() + this.i0;
            if (DrawableCompat.f(this) == 0) {
                rectF.left = rect.left + s0;
                rectF.right = rect.right - w0;
            } else {
                rectF.left = rect.left + w0;
                rectF.right = rect.right - s0;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private static boolean x1(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    private float y0() {
        this.t0.g().getFontMetrics(this.p0);
        Paint.FontMetrics fontMetrics = this.p0;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    private static boolean y1(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    private static boolean z1(TextAppearance textAppearance) {
        return (textAppearance == null || textAppearance.h() == null || !textAppearance.h().isStateful()) ? false : true;
    }

    public void A2(int i2) {
        z2(this.m0.getResources().getDimension(i2));
    }

    protected void B1() {
        Delegate delegate = (Delegate) this.K0.get();
        if (delegate != null) {
            delegate.a();
        }
    }

    public void B2(float f2) {
        if (this.f0 != f2) {
            float s0 = s0();
            this.f0 = f2;
            float s02 = s0();
            invalidateSelf();
            if (s0 != s02) {
                B1();
            }
        }
    }

    public void C2(int i2) {
        B2(this.m0.getResources().getDimension(i2));
    }

    public void D1(boolean z) {
        if (this.Y != z) {
            this.Y = z;
            float s0 = s0();
            if (!z && this.A0) {
                this.A0 = false;
            }
            float s02 = s0();
            invalidateSelf();
            if (s0 != s02) {
                B1();
            }
        }
    }

    public void D2(int i2) {
        this.N0 = i2;
    }

    public void E1(int i2) {
        D1(this.m0.getResources().getBoolean(i2));
    }

    public void E2(ColorStateList colorStateList) {
        if (this.L != colorStateList) {
            this.L = colorStateList;
            X2();
            onStateChange(getState());
        }
    }

    public void F1(Drawable drawable) {
        if (this.a0 != drawable) {
            float s0 = s0();
            this.a0 = drawable;
            float s02 = s0();
            W2(this.a0);
            q0(this.a0);
            invalidateSelf();
            if (s0 != s02) {
                B1();
            }
        }
    }

    public void F2(int i2) {
        E2(AppCompatResources.a(this.m0, i2));
    }

    public void G1(int i2) {
        F1(AppCompatResources.b(this.m0, i2));
    }

    void G2(boolean z) {
        this.M0 = z;
    }

    public void H1(ColorStateList colorStateList) {
        if (this.b0 != colorStateList) {
            this.b0 = colorStateList;
            if (A0()) {
                DrawableCompat.o(this.a0, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void H2(MotionSpec motionSpec) {
        this.c0 = motionSpec;
    }

    public void I1(int i2) {
        H1(AppCompatResources.a(this.m0, i2));
    }

    public void I2(int i2) {
        H2(MotionSpec.d(this.m0, i2));
    }

    public void J1(int i2) {
        K1(this.m0.getResources().getBoolean(i2));
    }

    public void J2(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (TextUtils.equals(this.M, charSequence)) {
            return;
        }
        this.M = charSequence;
        this.t0.m(true);
        invalidateSelf();
        B1();
    }

    public void K1(boolean z) {
        if (this.Z != z) {
            boolean T2 = T2();
            this.Z = z;
            boolean T22 = T2();
            if (T2 != T22) {
                if (T22) {
                    q0(this.a0);
                } else {
                    W2(this.a0);
                }
                invalidateSelf();
                B1();
            }
        }
    }

    public void K2(TextAppearance textAppearance) {
        this.t0.k(textAppearance, this.m0);
    }

    public Drawable L0() {
        return this.a0;
    }

    public void L1(ColorStateList colorStateList) {
        if (this.G != colorStateList) {
            this.G = colorStateList;
            onStateChange(getState());
        }
    }

    public void L2(int i2) {
        K2(new TextAppearance(this.m0, i2));
    }

    public ColorStateList M0() {
        return this.b0;
    }

    public void M1(int i2) {
        L1(AppCompatResources.a(this.m0, i2));
    }

    public void M2(float f2) {
        if (this.i0 != f2) {
            this.i0 = f2;
            invalidateSelf();
            B1();
        }
    }

    public ColorStateList N0() {
        return this.G;
    }

    public void N1(float f2) {
        if (this.I != f2) {
            this.I = f2;
            setShapeAppearanceModel(getShapeAppearanceModel().w(f2));
        }
    }

    public void N2(int i2) {
        M2(this.m0.getResources().getDimension(i2));
    }

    public float O0() {
        return this.O0 ? I() : this.I;
    }

    public void O1(int i2) {
        N1(this.m0.getResources().getDimension(i2));
    }

    public void O2(float f2) {
        TextAppearance o1 = o1();
        if (o1 != null) {
            o1.k(f2);
            this.t0.g().setTextSize(f2);
            a();
        }
    }

    public float P0() {
        return this.l0;
    }

    public void P1(float f2) {
        if (this.l0 != f2) {
            this.l0 = f2;
            invalidateSelf();
            B1();
        }
    }

    public void P2(float f2) {
        if (this.h0 != f2) {
            this.h0 = f2;
            invalidateSelf();
            B1();
        }
    }

    public Drawable Q0() {
        Drawable drawable = this.O;
        if (drawable != null) {
            return DrawableCompat.q(drawable);
        }
        return null;
    }

    public void Q1(int i2) {
        P1(this.m0.getResources().getDimension(i2));
    }

    public void Q2(int i2) {
        P2(this.m0.getResources().getDimension(i2));
    }

    public float R0() {
        return this.Q;
    }

    public void R1(Drawable drawable) {
        Drawable Q02 = Q0();
        if (Q02 != drawable) {
            float s0 = s0();
            this.O = drawable != null ? DrawableCompat.r(drawable).mutate() : null;
            float s02 = s0();
            W2(Q02);
            if (U2()) {
                q0(this.O);
            }
            invalidateSelf();
            if (s0 != s02) {
                B1();
            }
        }
    }

    public void R2(boolean z) {
        if (this.I0 != z) {
            this.I0 = z;
            X2();
            onStateChange(getState());
        }
    }

    public ColorStateList S0() {
        return this.P;
    }

    public void S1(int i2) {
        R1(AppCompatResources.b(this.m0, i2));
    }

    boolean S2() {
        return this.M0;
    }

    public float T0() {
        return this.H;
    }

    public void T1(float f2) {
        if (this.Q != f2) {
            float s0 = s0();
            this.Q = f2;
            float s02 = s0();
            invalidateSelf();
            if (s0 != s02) {
                B1();
            }
        }
    }

    public float U0() {
        return this.e0;
    }

    public void U1(int i2) {
        T1(this.m0.getResources().getDimension(i2));
    }

    public ColorStateList V0() {
        return this.J;
    }

    public void V1(ColorStateList colorStateList) {
        this.R = true;
        if (this.P != colorStateList) {
            this.P = colorStateList;
            if (U2()) {
                DrawableCompat.o(this.O, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public float W0() {
        return this.K;
    }

    public void W1(int i2) {
        V1(AppCompatResources.a(this.m0, i2));
    }

    public Drawable X0() {
        Drawable drawable = this.T;
        if (drawable != null) {
            return DrawableCompat.q(drawable);
        }
        return null;
    }

    public void X1(int i2) {
        Y1(this.m0.getResources().getBoolean(i2));
    }

    public CharSequence Y0() {
        return this.X;
    }

    public void Y1(boolean z) {
        if (this.N != z) {
            boolean U2 = U2();
            this.N = z;
            boolean U22 = U2();
            if (U2 != U22) {
                if (U22) {
                    q0(this.O);
                } else {
                    W2(this.O);
                }
                invalidateSelf();
                B1();
            }
        }
    }

    public float Z0() {
        return this.k0;
    }

    public void Z1(float f2) {
        if (this.H != f2) {
            this.H = f2;
            invalidateSelf();
            B1();
        }
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void a() {
        B1();
        invalidateSelf();
    }

    public float a1() {
        return this.W;
    }

    public void a2(int i2) {
        Z1(this.m0.getResources().getDimension(i2));
    }

    public float b1() {
        return this.j0;
    }

    public void b2(float f2) {
        if (this.e0 != f2) {
            this.e0 = f2;
            invalidateSelf();
            B1();
        }
    }

    public int[] c1() {
        return this.H0;
    }

    public void c2(int i2) {
        b2(this.m0.getResources().getDimension(i2));
    }

    public ColorStateList d1() {
        return this.V;
    }

    public void d2(ColorStateList colorStateList) {
        if (this.J != colorStateList) {
            this.J = colorStateList;
            if (this.O0) {
                l0(colorStateList);
            }
            onStateChange(getState());
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i2 = this.C0;
        int a2 = i2 < 255 ? CanvasCompat.a(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i2) : 0;
        G0(canvas, bounds);
        D0(canvas, bounds);
        if (this.O0) {
            super.draw(canvas);
        }
        F0(canvas, bounds);
        I0(canvas, bounds);
        E0(canvas, bounds);
        C0(canvas, bounds);
        if (this.M0) {
            K0(canvas, bounds);
        }
        H0(canvas, bounds);
        J0(canvas, bounds);
        if (this.C0 < 255) {
            canvas.restoreToCount(a2);
        }
    }

    public void e1(RectF rectF) {
        v0(getBounds(), rectF);
    }

    public void e2(int i2) {
        d2(AppCompatResources.a(this.m0, i2));
    }

    public void f2(float f2) {
        if (this.K != f2) {
            this.K = f2;
            this.n0.setStrokeWidth(f2);
            if (this.O0) {
                super.m0(f2);
            }
            invalidateSelf();
        }
    }

    public void g2(int i2) {
        f2(this.m0.getResources().getDimension(i2));
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.C0;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.D0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.H;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.e0 + s0() + this.h0 + this.t0.h(n1().toString()) + this.i0 + w0() + this.l0), this.N0);
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if (this.O0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.I);
        } else {
            outline.setRoundRect(bounds, this.I);
        }
        outline.setAlpha(getAlpha() / 255.0f);
    }

    public TextUtils.TruncateAt h1() {
        return this.L0;
    }

    public MotionSpec i1() {
        return this.d0;
    }

    public void i2(Drawable drawable) {
        Drawable X0 = X0();
        if (X0 != drawable) {
            float w0 = w0();
            this.T = drawable != null ? DrawableCompat.r(drawable).mutate() : null;
            if (RippleUtils.f14980a) {
                Y2();
            }
            float w02 = w0();
            W2(X0);
            if (V2()) {
                q0(this.T);
            }
            invalidateSelf();
            if (w0 != w02) {
                B1();
            }
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return x1(this.F) || x1(this.G) || x1(this.J) || (this.I0 && x1(this.J0)) || z1(this.t0.e()) || A0() || y1(this.O) || y1(this.a0) || x1(this.F0);
    }

    public float j1() {
        return this.g0;
    }

    public void j2(CharSequence charSequence) {
        if (this.X != charSequence) {
            this.X = BidiFormatter.c().h(charSequence);
            invalidateSelf();
        }
    }

    public float k1() {
        return this.f0;
    }

    public void k2(float f2) {
        if (this.k0 != f2) {
            this.k0 = f2;
            invalidateSelf();
            if (V2()) {
                B1();
            }
        }
    }

    public ColorStateList l1() {
        return this.L;
    }

    public void l2(int i2) {
        k2(this.m0.getResources().getDimension(i2));
    }

    public MotionSpec m1() {
        return this.c0;
    }

    public void m2(int i2) {
        i2(AppCompatResources.b(this.m0, i2));
    }

    public CharSequence n1() {
        return this.M;
    }

    public void n2(float f2) {
        if (this.W != f2) {
            this.W = f2;
            invalidateSelf();
            if (V2()) {
                B1();
            }
        }
    }

    public TextAppearance o1() {
        return this.t0.e();
    }

    public void o2(int i2) {
        n2(this.m0.getResources().getDimension(i2));
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i2) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i2);
        if (U2()) {
            onLayoutDirectionChanged |= DrawableCompat.m(this.O, i2);
        }
        if (T2()) {
            onLayoutDirectionChanged |= DrawableCompat.m(this.a0, i2);
        }
        if (V2()) {
            onLayoutDirectionChanged |= DrawableCompat.m(this.T, i2);
        }
        if (!onLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i2) {
        boolean onLevelChange = super.onLevelChange(i2);
        if (U2()) {
            onLevelChange |= this.O.setLevel(i2);
        }
        if (T2()) {
            onLevelChange |= this.a0.setLevel(i2);
        }
        if (V2()) {
            onLevelChange |= this.T.setLevel(i2);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        if (this.O0) {
            super.onStateChange(iArr);
        }
        return C1(iArr, c1());
    }

    public float p1() {
        return this.i0;
    }

    public void p2(float f2) {
        if (this.j0 != f2) {
            this.j0 = f2;
            invalidateSelf();
            if (V2()) {
                B1();
            }
        }
    }

    public float q1() {
        return this.h0;
    }

    public void q2(int i2) {
        p2(this.m0.getResources().getDimension(i2));
    }

    public boolean r2(int[] iArr) {
        if (Arrays.equals(this.H0, iArr)) {
            return false;
        }
        this.H0 = iArr;
        if (V2()) {
            return C1(getState(), iArr);
        }
        return false;
    }

    float s0() {
        if (U2() || T2()) {
            return this.f0 + g1() + this.g0;
        }
        return 0.0f;
    }

    public boolean s1() {
        return this.I0;
    }

    public void s2(ColorStateList colorStateList) {
        if (this.V != colorStateList) {
            this.V = colorStateList;
            if (V2()) {
                DrawableCompat.o(this.T, colorStateList);
            }
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j2);
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.C0 != i2) {
            this.C0 = i2;
            invalidateSelf();
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.D0 != colorFilter) {
            this.D0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        if (this.F0 != colorStateList) {
            this.F0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.G0 != mode) {
            this.G0 = mode;
            this.E0 = DrawableUtils.o(this, this.F0, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (U2()) {
            visible |= this.O.setVisible(z, z2);
        }
        if (T2()) {
            visible |= this.a0.setVisible(z, z2);
        }
        if (V2()) {
            visible |= this.T.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public void t2(int i2) {
        s2(AppCompatResources.a(this.m0, i2));
    }

    public boolean u1() {
        return this.Y;
    }

    public void u2(boolean z) {
        if (this.S != z) {
            boolean V2 = V2();
            this.S = z;
            boolean V22 = V2();
            if (V2 != V22) {
                if (V22) {
                    q0(this.T);
                } else {
                    W2(this.T);
                }
                invalidateSelf();
                B1();
            }
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public boolean v1() {
        return y1(this.T);
    }

    public void v2(Delegate delegate) {
        this.K0 = new WeakReference(delegate);
    }

    float w0() {
        if (V2()) {
            return this.j0 + this.W + this.k0;
        }
        return 0.0f;
    }

    public boolean w1() {
        return this.S;
    }

    public void w2(TextUtils.TruncateAt truncateAt) {
        this.L0 = truncateAt;
    }

    public void x2(MotionSpec motionSpec) {
        this.d0 = motionSpec;
    }

    public void y2(int i2) {
        x2(MotionSpec.d(this.m0, i2));
    }

    Paint.Align z0(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.M != null) {
            float s0 = this.e0 + s0() + this.h0;
            if (DrawableCompat.f(this) == 0) {
                pointF.x = rect.left + s0;
            } else {
                pointF.x = rect.right - s0;
                align = Paint.Align.RIGHT;
            }
            pointF.y = rect.centerY() - y0();
        }
        return align;
    }

    public void z2(float f2) {
        if (this.g0 != f2) {
            float s0 = s0();
            this.g0 = f2;
            float s02 = s0();
            invalidateSelf();
            if (s0 != s02) {
                B1();
            }
        }
    }
}
