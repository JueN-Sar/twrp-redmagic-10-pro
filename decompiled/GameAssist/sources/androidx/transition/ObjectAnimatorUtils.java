package androidx.transition;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.util.Property;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
class ObjectAnimatorUtils {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static <T, V> ObjectAnimator a(T t, Property<T, V> property, Path path) {
            return ObjectAnimator.ofObject(t, property, (TypeConverter) null, path);
        }
    }

    static ObjectAnimator a(Object obj, Property property, Path path) {
        return Api21Impl.a(obj, property, path);
    }
}
