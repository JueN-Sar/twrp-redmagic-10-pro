package com.google.android.material.color;

import android.content.Context;
import android.content.res.loader.ResourcesLoader;
import androidx.annotation.RequiresApi;
import java.util.Map;

@RequiresApi
/* loaded from: classes.dex */
final class ResourcesLoaderUtils {
    static boolean a(Context context, Map map) {
        ResourcesLoader a2 = ColorResourcesLoaderCreator.a(context, map);
        if (a2 == null) {
            return false;
        }
        context.getResources().addLoaders(a2);
        return true;
    }
}
