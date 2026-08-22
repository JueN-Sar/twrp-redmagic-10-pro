package com.google.android.material.color;

import android.app.Activity;
import com.google.android.material.color.DynamicColors;

/* loaded from: classes.dex */
public class DynamicColorsOptions {

    /* renamed from: e, reason: collision with root package name */
    private static final DynamicColors.Precondition f14282e = new DynamicColors.Precondition() { // from class: com.google.android.material.color.DynamicColorsOptions.1
        @Override // com.google.android.material.color.DynamicColors.Precondition
        public boolean a(Activity activity, int i2) {
            return true;
        }
    };

    /* renamed from: f, reason: collision with root package name */
    private static final DynamicColors.OnAppliedCallback f14283f = new DynamicColors.OnAppliedCallback() { // from class: com.google.android.material.color.DynamicColorsOptions.2
        @Override // com.google.android.material.color.DynamicColors.OnAppliedCallback
        public void a(Activity activity) {
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final int f14284a;

    /* renamed from: b, reason: collision with root package name */
    private final DynamicColors.Precondition f14285b;

    /* renamed from: c, reason: collision with root package name */
    private final DynamicColors.OnAppliedCallback f14286c;

    /* renamed from: d, reason: collision with root package name */
    private Integer f14287d;

    public static class Builder {
    }

    public Integer a() {
        return this.f14287d;
    }

    public DynamicColors.OnAppliedCallback b() {
        return this.f14286c;
    }

    public DynamicColors.Precondition c() {
        return this.f14285b;
    }

    public int d() {
        return this.f14284a;
    }
}
