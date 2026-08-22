package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class FontScale {

    /* renamed from: e, reason: collision with root package name */
    private static boolean f17615e = false;

    /* renamed from: f, reason: collision with root package name */
    private static final Float[] f17616f = {Float.valueOf(0.85f), Float.valueOf(1.0f), Float.valueOf(1.15f), Float.valueOf(1.3f)};

    /* renamed from: g, reason: collision with root package name */
    private static final float[][] f17617g = {new float[]{0.833f, 1.0f, 1.166f, 1.333f}, new float[]{0.857f, 1.0f, 1.142f, 1.285f}, new float[]{0.947f, 1.0f, 1.053f, 1.105f}, new float[]{0.875f, 1.0f, 1.25f, 1.5f}, new float[]{0.941f, 1.0f, 1.059f, 1.117f}, new float[]{0.85f, 1.0f, 1.05f, 1.1f}};

    /* renamed from: a, reason: collision with root package name */
    private Context f17618a;

    /* renamed from: b, reason: collision with root package name */
    private TextView f17619b;

    /* renamed from: c, reason: collision with root package name */
    private float f17620c;

    /* renamed from: d, reason: collision with root package name */
    private int f17621d;

    public FontScale() {
        this(0.0f);
    }

    private void e() {
        if (this.f17621d != 0) {
            this.f17619b.setTextSize(a());
        }
    }

    public float a() {
        if (this.f17621d == 0) {
            return this.f17620c;
        }
        float f2 = this.f17620c;
        float f3 = Settings.System.getFloat(this.f17618a.getContentResolver(), "font_scale", 1.0f);
        int indexOf = Arrays.asList(f17616f).indexOf(Float.valueOf(f3));
        if (indexOf >= 0) {
            f2 = (this.f17620c / f3) * f17617g[this.f17621d - 1][indexOf];
        }
        if (f17615e) {
            Log.d("FontScale", "getScaledTextSize curFontScale=" + f3 + ", textSize=" + f2);
        }
        return f2;
    }

    public void b(TextView textView, AttributeSet attributeSet, int i2, int i3) {
        this.f17619b = textView;
        Context context = textView.getContext();
        this.f17618a = context;
        Resources.Theme theme = context.getTheme();
        int[] iArr = {R.attr.textSize, R.attr.numStars};
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, com.zte.extres.R.styleable.TextViewAppearance, i2, i3);
        int resourceId = obtainStyledAttributes.getResourceId(com.zte.extres.R.styleable.TextViewAppearance_android_textAppearance, -1);
        obtainStyledAttributes.recycle();
        float f2 = this.f17618a.getResources().getDisplayMetrics().scaledDensity;
        if (resourceId != -1) {
            TypedArray obtainStyledAttributes2 = theme.obtainStyledAttributes(resourceId, iArr);
            if (obtainStyledAttributes2.hasValue(0)) {
                this.f17620c = obtainStyledAttributes2.getDimensionPixelSize(0, 0) / f2;
            }
            this.f17621d = obtainStyledAttributes2.getInt(1, this.f17621d);
            obtainStyledAttributes2.recycle();
        }
        TypedArray obtainStyledAttributes3 = theme.obtainStyledAttributes(attributeSet, iArr, i2, i3);
        if (obtainStyledAttributes3.hasValue(0)) {
            this.f17620c = obtainStyledAttributes3.getDimensionPixelSize(0, 0) / f2;
        }
        this.f17621d = obtainStyledAttributes3.getInt(1, this.f17621d);
        obtainStyledAttributes3.recycle();
        e();
    }

    public void c(int i2) {
        this.f17621d = i2;
        e();
    }

    public void d(int i2) {
        float f2 = this.f17618a.getResources().getDisplayMetrics().scaledDensity;
        TypedArray obtainStyledAttributes = this.f17618a.getTheme().obtainStyledAttributes(i2, new int[]{R.attr.textSize});
        if (obtainStyledAttributes.hasValue(0)) {
            this.f17620c = obtainStyledAttributes.getDimensionPixelSize(0, 0) / f2;
        }
        obtainStyledAttributes.recycle();
        e();
    }

    public FontScale(float f2) {
        this.f17620c = f2;
        this.f17621d = 0;
    }
}
