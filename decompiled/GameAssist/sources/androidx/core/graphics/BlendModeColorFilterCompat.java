package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.ColorFilter;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.BlendModeUtils;

/* loaded from: classes.dex */
public class BlendModeColorFilterCompat {

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static ColorFilter a(int i2, Object obj) {
            return new BlendModeColorFilter(i2, (BlendMode) obj);
        }
    }

    public static ColorFilter a(int i2, BlendModeCompat blendModeCompat) {
        Object a2 = BlendModeUtils.Api29Impl.a(blendModeCompat);
        if (a2 != null) {
            return Api29Impl.a(i2, a2);
        }
        return null;
    }
}
