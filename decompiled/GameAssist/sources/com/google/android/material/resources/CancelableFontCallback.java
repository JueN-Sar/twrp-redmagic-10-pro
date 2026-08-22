package com.google.android.material.resources;

import android.graphics.Typeface;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public final class CancelableFontCallback extends TextAppearanceFontCallback {

    /* renamed from: a, reason: collision with root package name */
    private final Typeface f14950a;

    /* renamed from: b, reason: collision with root package name */
    private final ApplyFont f14951b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f14952c;

    public interface ApplyFont {
        void a(Typeface typeface);
    }

    public CancelableFontCallback(ApplyFont applyFont, Typeface typeface) {
        this.f14950a = typeface;
        this.f14951b = applyFont;
    }

    private void d(Typeface typeface) {
        if (this.f14952c) {
            return;
        }
        this.f14951b.a(typeface);
    }

    @Override // com.google.android.material.resources.TextAppearanceFontCallback
    public void a(int i2) {
        d(this.f14950a);
    }

    @Override // com.google.android.material.resources.TextAppearanceFontCallback
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.f14952c = true;
    }
}
