package com.google.android.material.tooltip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MarkerEdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.OffsetEdgeTreatment;

@RestrictTo
/* loaded from: classes.dex */
public class TooltipDrawable extends MaterialShapeDrawable implements TextDrawableHelper.TextDrawableDelegate {
    private static final int X = R.style.Widget_MaterialComponents_Tooltip;
    private static final int Y = R.attr.tooltipStyle;
    private CharSequence F;
    private final Context G;
    private final Paint.FontMetrics H;
    private final TextDrawableHelper I;
    private final View.OnLayoutChangeListener J;
    private final Rect K;
    private int L;
    private int M;
    private int N;
    private int O;
    private boolean P;
    private int Q;
    private int R;
    private float S;
    private float T;
    private final float U;
    private float V;
    private float W;

    private TooltipDrawable(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.H = new Paint.FontMetrics();
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.I = textDrawableHelper;
        this.J = new View.OnLayoutChangeListener() { // from class: com.google.android.material.tooltip.TooltipDrawable.1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
                TooltipDrawable.this.E0(view);
            }
        };
        this.K = new Rect();
        this.S = 1.0f;
        this.T = 1.0f;
        this.U = 0.5f;
        this.V = 0.5f;
        this.W = 1.0f;
        this.G = context;
        textDrawableHelper.g().density = context.getResources().getDisplayMetrics().density;
        textDrawableHelper.g().setTextAlign(Paint.Align.CENTER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E0(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.R = iArr[0];
        view.getWindowVisibleDisplayFrame(this.K);
    }

    private float r0() {
        int i2;
        if (((this.K.right - getBounds().right) - this.R) - this.O < 0) {
            i2 = ((this.K.right - getBounds().right) - this.R) - this.O;
        } else {
            if (((this.K.left - getBounds().left) - this.R) + this.O <= 0) {
                return 0.0f;
            }
            i2 = ((this.K.left - getBounds().left) - this.R) + this.O;
        }
        return i2;
    }

    private float s0() {
        this.I.g().getFontMetrics(this.H);
        Paint.FontMetrics fontMetrics = this.H;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    private float t0(Rect rect) {
        return rect.centerY() - s0();
    }

    public static TooltipDrawable u0(Context context, AttributeSet attributeSet, int i2, int i3) {
        TooltipDrawable tooltipDrawable = new TooltipDrawable(context, attributeSet, i2, i3);
        tooltipDrawable.z0(attributeSet, i2, i3);
        return tooltipDrawable;
    }

    private EdgeTreatment v0() {
        float f2 = -r0();
        float width = ((float) (getBounds().width() - (this.Q * Math.sqrt(2.0d)))) / 2.0f;
        return new OffsetEdgeTreatment(new MarkerEdgeTreatment(this.Q), Math.min(Math.max(f2, -width), width));
    }

    private void x0(Canvas canvas) {
        if (this.F == null) {
            return;
        }
        int t0 = (int) t0(getBounds());
        if (this.I.e() != null) {
            this.I.g().drawableState = getState();
            this.I.n(this.G);
            this.I.g().setAlpha((int) (this.W * 255.0f));
        }
        CharSequence charSequence = this.F;
        canvas.drawText(charSequence, 0, charSequence.length(), r0.centerX(), t0, this.I.g());
    }

    private float y0() {
        CharSequence charSequence = this.F;
        if (charSequence == null) {
            return 0.0f;
        }
        return this.I.h(charSequence.toString());
    }

    private void z0(AttributeSet attributeSet, int i2, int i3) {
        TypedArray i4 = ThemeEnforcement.i(this.G, attributeSet, R.styleable.Tooltip, i2, i3, new int[0]);
        this.Q = this.G.getResources().getDimensionPixelSize(R.dimen.mtrl_tooltip_arrowSize);
        boolean z = i4.getBoolean(R.styleable.Tooltip_showMarker, true);
        this.P = z;
        if (z) {
            setShapeAppearanceModel(getShapeAppearanceModel().v().s(v0()).m());
        } else {
            this.Q = 0;
        }
        C0(i4.getText(R.styleable.Tooltip_android_text));
        TextAppearance h2 = MaterialResources.h(this.G, i4, R.styleable.Tooltip_android_textAppearance);
        if (h2 != null && i4.hasValue(R.styleable.Tooltip_android_textColor)) {
            h2.j(MaterialResources.a(this.G, i4, R.styleable.Tooltip_android_textColor));
        }
        D0(h2);
        a0(ColorStateList.valueOf(i4.getColor(R.styleable.Tooltip_backgroundTint, MaterialColors.k(ColorUtils.k(MaterialColors.c(this.G, android.R.attr.colorBackground, TooltipDrawable.class.getCanonicalName()), 229), ColorUtils.k(MaterialColors.c(this.G, R.attr.colorOnBackground, TooltipDrawable.class.getCanonicalName()), 153)))));
        l0(ColorStateList.valueOf(MaterialColors.c(this.G, R.attr.colorSurface, TooltipDrawable.class.getCanonicalName())));
        this.L = i4.getDimensionPixelSize(R.styleable.Tooltip_android_padding, 0);
        this.M = i4.getDimensionPixelSize(R.styleable.Tooltip_android_minWidth, 0);
        this.N = i4.getDimensionPixelSize(R.styleable.Tooltip_android_minHeight, 0);
        this.O = i4.getDimensionPixelSize(R.styleable.Tooltip_android_layout_margin, 0);
        i4.recycle();
    }

    public void A0(View view) {
        if (view == null) {
            return;
        }
        E0(view);
        view.addOnLayoutChangeListener(this.J);
    }

    public void B0(float f2) {
        this.V = 1.2f;
        this.S = f2;
        this.T = f2;
        this.W = AnimationUtils.b(0.0f, 1.0f, 0.19f, 1.0f, f2);
        invalidateSelf();
    }

    public void C0(CharSequence charSequence) {
        if (TextUtils.equals(this.F, charSequence)) {
            return;
        }
        this.F = charSequence;
        this.I.m(true);
        invalidateSelf();
    }

    public void D0(TextAppearance textAppearance) {
        this.I.k(textAppearance, this.G);
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void a() {
        invalidateSelf();
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.save();
        float r0 = r0();
        float f2 = (float) (-((this.Q * Math.sqrt(2.0d)) - this.Q));
        canvas.scale(this.S, this.T, getBounds().left + (getBounds().width() * 0.5f), getBounds().top + (getBounds().height() * this.V));
        canvas.translate(r0, f2);
        super.draw(canvas);
        x0(canvas);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) Math.max(this.I.g().getTextSize(), this.N);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) Math.max((this.L * 2) + y0(), this.M);
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.P) {
            setShapeAppearanceModel(getShapeAppearanceModel().v().s(v0()).m());
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void w0(View view) {
        if (view == null) {
            return;
        }
        view.removeOnLayoutChangeListener(this.J);
    }
}
