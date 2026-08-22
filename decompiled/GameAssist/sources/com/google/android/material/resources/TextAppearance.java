package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.R;

@RestrictTo
/* loaded from: classes.dex */
public class TextAppearance {

    /* renamed from: a, reason: collision with root package name */
    public final ColorStateList f14953a;

    /* renamed from: b, reason: collision with root package name */
    public final ColorStateList f14954b;

    /* renamed from: c, reason: collision with root package name */
    public final ColorStateList f14955c;

    /* renamed from: d, reason: collision with root package name */
    public final String f14956d;

    /* renamed from: e, reason: collision with root package name */
    public final int f14957e;

    /* renamed from: f, reason: collision with root package name */
    public final int f14958f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f14959g;

    /* renamed from: h, reason: collision with root package name */
    public final float f14960h;

    /* renamed from: i, reason: collision with root package name */
    public final float f14961i;

    /* renamed from: j, reason: collision with root package name */
    public final float f14962j;

    /* renamed from: k, reason: collision with root package name */
    public final boolean f14963k;

    /* renamed from: l, reason: collision with root package name */
    public final float f14964l;

    /* renamed from: m, reason: collision with root package name */
    private ColorStateList f14965m;

    /* renamed from: n, reason: collision with root package name */
    private float f14966n;

    /* renamed from: o, reason: collision with root package name */
    private final int f14967o;

    /* renamed from: p, reason: collision with root package name */
    private boolean f14968p = false;

    /* renamed from: q, reason: collision with root package name */
    private Typeface f14969q;

    public TextAppearance(Context context, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, R.styleable.TextAppearance);
        k(obtainStyledAttributes.getDimension(R.styleable.TextAppearance_android_textSize, 0.0f));
        j(MaterialResources.a(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColor));
        this.f14953a = MaterialResources.a(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColorHint);
        this.f14954b = MaterialResources.a(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColorLink);
        this.f14957e = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_textStyle, 0);
        this.f14958f = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_typeface, 1);
        int g2 = MaterialResources.g(obtainStyledAttributes, R.styleable.TextAppearance_fontFamily, R.styleable.TextAppearance_android_fontFamily);
        this.f14967o = obtainStyledAttributes.getResourceId(g2, 0);
        this.f14956d = obtainStyledAttributes.getString(g2);
        this.f14959g = obtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        this.f14955c = MaterialResources.a(context, obtainStyledAttributes, R.styleable.TextAppearance_android_shadowColor);
        this.f14960h = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.f14961i = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.f14962j = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(i2, R.styleable.MaterialTextAppearance);
        this.f14963k = obtainStyledAttributes2.hasValue(R.styleable.MaterialTextAppearance_android_letterSpacing);
        this.f14964l = obtainStyledAttributes2.getFloat(R.styleable.MaterialTextAppearance_android_letterSpacing, 0.0f);
        obtainStyledAttributes2.recycle();
    }

    private void d() {
        String str;
        if (this.f14969q == null && (str = this.f14956d) != null) {
            this.f14969q = Typeface.create(str, this.f14957e);
        }
        if (this.f14969q == null) {
            int i2 = this.f14958f;
            if (i2 == 1) {
                this.f14969q = Typeface.SANS_SERIF;
            } else if (i2 == 2) {
                this.f14969q = Typeface.SERIF;
            } else if (i2 != 3) {
                this.f14969q = Typeface.DEFAULT;
            } else {
                this.f14969q = Typeface.MONOSPACE;
            }
            this.f14969q = Typeface.create(this.f14969q, this.f14957e);
        }
    }

    private boolean l(Context context) {
        if (TextAppearanceConfig.a()) {
            return true;
        }
        int i2 = this.f14967o;
        return (i2 != 0 ? ResourcesCompat.c(context, i2) : null) != null;
    }

    public Typeface e() {
        d();
        return this.f14969q;
    }

    public void f(final Context context, final TextPaint textPaint, final TextAppearanceFontCallback textAppearanceFontCallback) {
        o(context, textPaint, e());
        g(context, new TextAppearanceFontCallback() { // from class: com.google.android.material.resources.TextAppearance.2
            @Override // com.google.android.material.resources.TextAppearanceFontCallback
            public void a(int i2) {
                textAppearanceFontCallback.a(i2);
            }

            @Override // com.google.android.material.resources.TextAppearanceFontCallback
            public void b(Typeface typeface, boolean z) {
                TextAppearance.this.o(context, textPaint, typeface);
                textAppearanceFontCallback.b(typeface, z);
            }
        });
    }

    public void g(Context context, final TextAppearanceFontCallback textAppearanceFontCallback) {
        if (l(context)) {
            getFont(context);
        } else {
            d();
        }
        int i2 = this.f14967o;
        if (i2 == 0) {
            this.f14968p = true;
        }
        if (this.f14968p) {
            textAppearanceFontCallback.b(this.f14969q, true);
            return;
        }
        try {
            ResourcesCompat.i(context, i2, new ResourcesCompat.FontCallback() { // from class: com.google.android.material.resources.TextAppearance.1
                @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                /* renamed from: h */
                public void f(int i3) {
                    TextAppearance.this.f14968p = true;
                    textAppearanceFontCallback.a(i3);
                }

                @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                /* renamed from: i */
                public void g(Typeface typeface) {
                    TextAppearance textAppearance = TextAppearance.this;
                    textAppearance.f14969q = Typeface.create(typeface, textAppearance.f14957e);
                    TextAppearance.this.f14968p = true;
                    textAppearanceFontCallback.b(TextAppearance.this.f14969q, false);
                }
            }, null);
        } catch (Resources.NotFoundException unused) {
            this.f14968p = true;
            textAppearanceFontCallback.a(1);
        } catch (Exception e2) {
            Log.d("TextAppearance", "Error loading font " + this.f14956d, e2);
            this.f14968p = true;
            textAppearanceFontCallback.a(-3);
        }
    }

    @NonNull
    @VisibleForTesting
    public Typeface getFont(@NonNull Context context) {
        if (this.f14968p) {
            return this.f14969q;
        }
        if (!context.isRestricted()) {
            try {
                Typeface g2 = ResourcesCompat.g(context, this.f14967o);
                this.f14969q = g2;
                if (g2 != null) {
                    this.f14969q = Typeface.create(g2, this.f14957e);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e2) {
                Log.d("TextAppearance", "Error loading font " + this.f14956d, e2);
            }
        }
        d();
        this.f14968p = true;
        return this.f14969q;
    }

    public ColorStateList h() {
        return this.f14965m;
    }

    public float i() {
        return this.f14966n;
    }

    public void j(ColorStateList colorStateList) {
        this.f14965m = colorStateList;
    }

    public void k(float f2) {
        this.f14966n = f2;
    }

    public void m(Context context, TextPaint textPaint, TextAppearanceFontCallback textAppearanceFontCallback) {
        n(context, textPaint, textAppearanceFontCallback);
        ColorStateList colorStateList = this.f14965m;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f2 = this.f14962j;
        float f3 = this.f14960h;
        float f4 = this.f14961i;
        ColorStateList colorStateList2 = this.f14955c;
        textPaint.setShadowLayer(f2, f3, f4, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void n(Context context, TextPaint textPaint, TextAppearanceFontCallback textAppearanceFontCallback) {
        if (l(context)) {
            o(context, textPaint, getFont(context));
        } else {
            f(context, textPaint, textAppearanceFontCallback);
        }
    }

    public void o(Context context, TextPaint textPaint, Typeface typeface) {
        Typeface a2 = TypefaceUtils.a(context, typeface);
        if (a2 != null) {
            typeface = a2;
        }
        textPaint.setTypeface(typeface);
        int i2 = this.f14957e & (~typeface.getStyle());
        textPaint.setFakeBoldText((i2 & 1) != 0);
        textPaint.setTextSkewX((i2 & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f14966n);
        if (this.f14963k) {
            textPaint.setLetterSpacing(this.f14964l);
        }
    }
}
