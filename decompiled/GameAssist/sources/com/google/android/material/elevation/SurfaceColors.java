package com.google.android.material.elevation;

import androidx.annotation.DimenRes;
import com.google.android.material.R;

/* loaded from: classes.dex */
public enum SurfaceColors {
    SURFACE_0(R.dimen.m3_sys_elevation_level0),
    SURFACE_1(R.dimen.m3_sys_elevation_level1),
    SURFACE_2(R.dimen.m3_sys_elevation_level2),
    SURFACE_3(R.dimen.m3_sys_elevation_level3),
    SURFACE_4(R.dimen.m3_sys_elevation_level4),
    SURFACE_5(R.dimen.m3_sys_elevation_level5);

    private final int elevationResId;

    SurfaceColors(@DimenRes int i2) {
        this.elevationResId = i2;
    }
}
