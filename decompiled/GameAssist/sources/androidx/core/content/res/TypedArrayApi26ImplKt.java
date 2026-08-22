package androidx.core.content.res;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleableRes;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@RequiresApi
@Metadata
/* loaded from: classes.dex */
final class TypedArrayApi26ImplKt {

    /* renamed from: a, reason: collision with root package name */
    public static final TypedArrayApi26ImplKt f2899a = new TypedArrayApi26ImplKt();

    private TypedArrayApi26ImplKt() {
    }

    @JvmStatic
    @DoNotInline
    @NotNull
    public static final Typeface a(@NotNull TypedArray typedArray, @StyleableRes int i2) {
        Typeface font = typedArray.getFont(i2);
        Intrinsics.b(font);
        return font;
    }
}
