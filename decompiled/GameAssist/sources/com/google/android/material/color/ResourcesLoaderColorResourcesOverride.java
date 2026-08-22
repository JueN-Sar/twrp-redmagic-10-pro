package com.google.android.material.color;

import android.content.Context;
import androidx.annotation.RequiresApi;
import com.google.android.material.R;
import java.util.Map;

@RequiresApi
/* loaded from: classes.dex */
class ResourcesLoaderColorResourcesOverride implements ColorResourcesOverride {

    private static class ResourcesLoaderColorResourcesOverrideSingleton {

        /* renamed from: a, reason: collision with root package name */
        private static final ResourcesLoaderColorResourcesOverride f14291a = new ResourcesLoaderColorResourcesOverride();
    }

    private ResourcesLoaderColorResourcesOverride() {
    }

    static ColorResourcesOverride a() {
        return ResourcesLoaderColorResourcesOverrideSingleton.f14291a;
    }

    @Override // com.google.android.material.color.ColorResourcesOverride
    public boolean b(Context context, Map map) {
        if (!ResourcesLoaderUtils.a(context, map)) {
            return false;
        }
        ThemeUtils.a(context, R.style.ThemeOverlay_Material3_PersonalizedColors);
        return true;
    }
}
