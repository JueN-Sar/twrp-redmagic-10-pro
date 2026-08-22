package androidx.core.os;

import android.os.PersistableBundle;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiresApi
@Metadata
/* loaded from: classes.dex */
final class PersistableBundleApi22ImplKt {

    /* renamed from: a, reason: collision with root package name */
    public static final PersistableBundleApi22ImplKt f3125a = new PersistableBundleApi22ImplKt();

    private PersistableBundleApi22ImplKt() {
    }

    @JvmStatic
    @DoNotInline
    public static final void a(@NotNull PersistableBundle persistableBundle, @Nullable String str, boolean z) {
        persistableBundle.putBoolean(str, z);
    }

    @JvmStatic
    @DoNotInline
    public static final void b(@NotNull PersistableBundle persistableBundle, @Nullable String str, @NotNull boolean[] zArr) {
        persistableBundle.putBooleanArray(str, zArr);
    }
}
