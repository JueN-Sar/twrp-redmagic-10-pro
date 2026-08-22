package androidx.core.graphics;

import android.graphics.Typeface;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
public class TypefaceCompatApi28Impl extends TypefaceCompatApi26Impl {
    @Override // androidx.core.graphics.TypefaceCompatApi26Impl
    protected Typeface l(Object obj) {
        try {
            Object newInstance = Array.newInstance((Class<?>) this.f2948g, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.f2954m.invoke(null, newInstance, "sans-serif", -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }
}
