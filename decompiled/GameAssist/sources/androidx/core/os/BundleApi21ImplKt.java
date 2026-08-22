package androidx.core.os;

import android.os.Bundle;
import android.util.Size;
import android.util.SizeF;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiresApi
@Metadata
/* loaded from: classes.dex */
final class BundleApi21ImplKt {

    /* renamed from: a, reason: collision with root package name */
    public static final BundleApi21ImplKt f3105a = new BundleApi21ImplKt();

    private BundleApi21ImplKt() {
    }

    @JvmStatic
    @DoNotInline
    public static final void a(@NotNull Bundle bundle, @NotNull String str, @Nullable Size size) {
        bundle.putSize(str, size);
    }

    @JvmStatic
    @DoNotInline
    public static final void b(@NotNull Bundle bundle, @NotNull String str, @Nullable SizeF sizeF) {
        bundle.putSizeF(str, sizeF);
    }
}
