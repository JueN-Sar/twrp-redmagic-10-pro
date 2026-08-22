package com.airbnb.lottie.model.content;

import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;

/* loaded from: classes.dex */
public class Mask {

    /* renamed from: a, reason: collision with root package name */
    private final MaskMode f9673a;

    /* renamed from: b, reason: collision with root package name */
    private final AnimatableShapeValue f9674b;

    /* renamed from: c, reason: collision with root package name */
    private final AnimatableIntegerValue f9675c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f9676d;

    public enum MaskMode {
        MASK_MODE_ADD,
        MASK_MODE_SUBTRACT,
        MASK_MODE_INTERSECT,
        MASK_MODE_NONE
    }

    public Mask(MaskMode maskMode, AnimatableShapeValue animatableShapeValue, AnimatableIntegerValue animatableIntegerValue, boolean z) {
        this.f9673a = maskMode;
        this.f9674b = animatableShapeValue;
        this.f9675c = animatableIntegerValue;
        this.f9676d = z;
    }

    public MaskMode a() {
        return this.f9673a;
    }

    public AnimatableShapeValue b() {
        return this.f9674b;
    }

    public AnimatableIntegerValue c() {
        return this.f9675c;
    }

    public boolean d() {
        return this.f9676d;
    }
}
