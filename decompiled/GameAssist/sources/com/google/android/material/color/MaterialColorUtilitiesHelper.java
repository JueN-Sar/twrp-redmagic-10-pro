package com.google.android.material.color;

import androidx.annotation.RestrictTo;
import com.google.android.material.R;
import com.google.android.material.color.utilities.DynamicColor;
import com.google.android.material.color.utilities.DynamicScheme;
import com.google.android.material.color.utilities.MaterialDynamicColors;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestrictTo
/* loaded from: classes.dex */
public final class MaterialColorUtilitiesHelper {

    /* renamed from: a, reason: collision with root package name */
    private static final MaterialDynamicColors f14289a;

    /* renamed from: b, reason: collision with root package name */
    private static final Map f14290b;

    static {
        MaterialDynamicColors materialDynamicColors = new MaterialDynamicColors();
        f14289a = materialDynamicColors;
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_primary), materialDynamicColors.B3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_primary), materialDynamicColors.q3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_primary_inverse), materialDynamicColors.j1());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_primary_container), materialDynamicColors.C3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_primary_container), materialDynamicColors.r3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_secondary), materialDynamicColors.D3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_secondary), materialDynamicColors.s3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_secondary_container), materialDynamicColors.E3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_secondary_container), materialDynamicColors.t3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_tertiary), materialDynamicColors.O3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_tertiary), materialDynamicColors.w3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_tertiary_container), materialDynamicColors.P3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_tertiary_container), materialDynamicColors.x3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_background), materialDynamicColors.a1());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_background), materialDynamicColors.n3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_surface), materialDynamicColors.F3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_surface), materialDynamicColors.u3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_surface_variant), materialDynamicColors.N3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_surface_variant), materialDynamicColors.v3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_surface_inverse), materialDynamicColors.k1());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_surface_inverse), materialDynamicColors.i1());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_surface_bright), materialDynamicColors.G3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_surface_dim), materialDynamicColors.M3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_surface_container), materialDynamicColors.H3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_surface_container_low), materialDynamicColors.K3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_surface_container_high), materialDynamicColors.I3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_surface_container_lowest), materialDynamicColors.L3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_surface_container_highest), materialDynamicColors.J3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_outline), materialDynamicColors.y3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_outline_variant), materialDynamicColors.z3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_error), materialDynamicColors.e1());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_error), materialDynamicColors.o3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_error_container), materialDynamicColors.f1());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_on_error_container), materialDynamicColors.p3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_control_activated), materialDynamicColors.b1());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_control_normal), materialDynamicColors.d1());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_control_highlight), materialDynamicColors.c1());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_text_primary_inverse), materialDynamicColors.R3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_text_secondary_and_tertiary_inverse), materialDynamicColors.T3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_text_secondary_and_tertiary_inverse_disabled), materialDynamicColors.U3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_text_primary_inverse_disable_only), materialDynamicColors.S3());
        hashMap.put(Integer.valueOf(R.color.material_personalized_color_text_hint_foreground_inverse), materialDynamicColors.Q3());
        f14290b = Collections.unmodifiableMap(hashMap);
    }

    public static Map a(DynamicScheme dynamicScheme) {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : f14290b.entrySet()) {
            hashMap.put((Integer) entry.getKey(), Integer.valueOf(((DynamicColor) entry.getValue()).d(dynamicScheme)));
        }
        return Collections.unmodifiableMap(hashMap);
    }
}
