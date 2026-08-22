package com.google.android.material.internal;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.annotation.RestrictTo;
import com.google.android.gms.common.api.Api;

@RestrictTo
/* loaded from: classes.dex */
final class StaticLayoutBuilderCompat {

    /* renamed from: o, reason: collision with root package name */
    static final int f14764o = 1;

    /* renamed from: a, reason: collision with root package name */
    private CharSequence f14765a;

    /* renamed from: b, reason: collision with root package name */
    private final TextPaint f14766b;

    /* renamed from: c, reason: collision with root package name */
    private final int f14767c;

    /* renamed from: e, reason: collision with root package name */
    private int f14769e;

    /* renamed from: l, reason: collision with root package name */
    private boolean f14776l;

    /* renamed from: n, reason: collision with root package name */
    private StaticLayoutBuilderConfigurer f14778n;

    /* renamed from: d, reason: collision with root package name */
    private int f14768d = 0;

    /* renamed from: f, reason: collision with root package name */
    private Layout.Alignment f14770f = Layout.Alignment.ALIGN_NORMAL;

    /* renamed from: g, reason: collision with root package name */
    private int f14771g = Api.BaseClientBuilder.API_PRIORITY_OTHER;

    /* renamed from: h, reason: collision with root package name */
    private float f14772h = 0.0f;

    /* renamed from: i, reason: collision with root package name */
    private float f14773i = 1.0f;

    /* renamed from: j, reason: collision with root package name */
    private int f14774j = f14764o;

    /* renamed from: k, reason: collision with root package name */
    private boolean f14775k = true;

    /* renamed from: m, reason: collision with root package name */
    private TextUtils.TruncateAt f14777m = null;

    static class StaticLayoutBuilderCompatException extends Exception {
        StaticLayoutBuilderCompatException(Throwable th) {
            super("Error thrown initializing StaticLayout " + th.getMessage(), th);
        }
    }

    private StaticLayoutBuilderCompat(CharSequence charSequence, TextPaint textPaint, int i2) {
        this.f14765a = charSequence;
        this.f14766b = textPaint;
        this.f14767c = i2;
        this.f14769e = charSequence.length();
    }

    public static StaticLayoutBuilderCompat b(CharSequence charSequence, TextPaint textPaint, int i2) {
        return new StaticLayoutBuilderCompat(charSequence, textPaint, i2);
    }

    public StaticLayout a() {
        if (this.f14765a == null) {
            this.f14765a = "";
        }
        int max = Math.max(0, this.f14767c);
        CharSequence charSequence = this.f14765a;
        if (this.f14771g == 1) {
            charSequence = TextUtils.ellipsize(charSequence, this.f14766b, max, this.f14777m);
        }
        int min = Math.min(charSequence.length(), this.f14769e);
        this.f14769e = min;
        if (this.f14776l && this.f14771g == 1) {
            this.f14770f = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder obtain = StaticLayout.Builder.obtain(charSequence, this.f14768d, min, this.f14766b, max);
        obtain.setAlignment(this.f14770f);
        obtain.setIncludePad(this.f14775k);
        obtain.setTextDirection(this.f14776l ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.f14777m;
        if (truncateAt != null) {
            obtain.setEllipsize(truncateAt);
        }
        obtain.setMaxLines(this.f14771g);
        float f2 = this.f14772h;
        if (f2 != 0.0f || this.f14773i != 1.0f) {
            obtain.setLineSpacing(f2, this.f14773i);
        }
        if (this.f14771g > 1) {
            obtain.setHyphenationFrequency(this.f14774j);
        }
        StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer = this.f14778n;
        if (staticLayoutBuilderConfigurer != null) {
            staticLayoutBuilderConfigurer.a(obtain);
        }
        return obtain.build();
    }

    public StaticLayoutBuilderCompat c(Layout.Alignment alignment) {
        this.f14770f = alignment;
        return this;
    }

    public StaticLayoutBuilderCompat d(TextUtils.TruncateAt truncateAt) {
        this.f14777m = truncateAt;
        return this;
    }

    public StaticLayoutBuilderCompat e(int i2) {
        this.f14774j = i2;
        return this;
    }

    public StaticLayoutBuilderCompat f(boolean z) {
        this.f14775k = z;
        return this;
    }

    public StaticLayoutBuilderCompat g(boolean z) {
        this.f14776l = z;
        return this;
    }

    public StaticLayoutBuilderCompat h(float f2, float f3) {
        this.f14772h = f2;
        this.f14773i = f3;
        return this;
    }

    public StaticLayoutBuilderCompat i(int i2) {
        this.f14771g = i2;
        return this;
    }

    public StaticLayoutBuilderCompat j(StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer) {
        this.f14778n = staticLayoutBuilderConfigurer;
        return this;
    }
}
