package com.google.android.material.color;

import android.content.Context;
import android.os.Build;
import androidx.core.os.BuildCompat;
import java.util.Map;

/* loaded from: classes2.dex */
public interface ColorResourcesOverride {
    static ColorResourcesOverride getInstance() {
        if (Build.VERSION.SDK_INT > 33 && !BuildCompat.isAtLeastU()) {
            return null;
        }
        return ResourcesLoaderColorResourcesOverride.getInstance();
    }

    boolean applyIfPossible(Context context, Map<Integer, Integer> map);

    Context wrapContextIfPossible(Context context, Map<Integer, Integer> map);
}
