package com.google.android.material.progressindicator;

import android.content.ContentResolver;
import android.provider.Settings;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;

@RestrictTo
/* loaded from: classes.dex */
public class AnimatorDurationScaleProvider {

    /* renamed from: a, reason: collision with root package name */
    private static float f14863a = 1.0f;

    @VisibleForTesting
    public static void setDefaultSystemAnimatorDurationScale(float f2) {
        f14863a = f2;
    }

    public float a(ContentResolver contentResolver) {
        return Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f);
    }
}
