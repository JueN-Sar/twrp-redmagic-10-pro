package androidx.appcompat.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.content.ContextCompat;

/* loaded from: classes.dex */
public final class AppCompatResources {
    public static ColorStateList a(Context context, int i2) {
        return ContextCompat.d(context, i2);
    }

    public static Drawable b(Context context, int i2) {
        return ResourceManagerInternal.g().i(context, i2);
    }
}
