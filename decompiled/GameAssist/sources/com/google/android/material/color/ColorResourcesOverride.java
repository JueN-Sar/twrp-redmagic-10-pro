package com.google.android.material.color;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RestrictTo;
import java.util.Map;

@RestrictTo
/* loaded from: classes.dex */
public interface ColorResourcesOverride {
    static ColorResourcesOverride a() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 > 33 && i2 < 34) {
            return null;
        }
        return ResourcesLoaderColorResourcesOverride.a();
    }

    boolean b(Context context, Map map);
}
