package com.google.android.material.internal;

import android.animation.TypeEvaluator;
import android.graphics.Rect;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class RectEvaluator implements TypeEvaluator<Rect> {

    /* renamed from: a, reason: collision with root package name */
    private final Rect f14754a;

    public RectEvaluator(Rect rect) {
        this.f14754a = rect;
    }

    @Override // android.animation.TypeEvaluator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Rect evaluate(float f2, Rect rect, Rect rect2) {
        this.f14754a.set(rect.left + ((int) ((rect2.left - r0) * f2)), rect.top + ((int) ((rect2.top - r1) * f2)), rect.right + ((int) ((rect2.right - r2) * f2)), rect.bottom + ((int) ((rect2.bottom - r6) * f2)));
        return this.f14754a;
    }
}
