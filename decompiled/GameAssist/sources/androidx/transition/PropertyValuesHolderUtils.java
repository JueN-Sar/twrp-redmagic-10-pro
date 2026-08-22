package androidx.transition;

import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.util.Property;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
class PropertyValuesHolderUtils {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static <V> PropertyValuesHolder a(Property<?, V> property, Path path) {
            return PropertyValuesHolder.ofObject(property, (TypeConverter) null, path);
        }
    }

    static PropertyValuesHolder a(Property property, Path path) {
        return Api21Impl.a(property, path);
    }
}
