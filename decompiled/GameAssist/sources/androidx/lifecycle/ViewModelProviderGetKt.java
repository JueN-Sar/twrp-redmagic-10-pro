package androidx.lifecycle;

import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@JvmName
/* loaded from: classes.dex */
public final class ViewModelProviderGetKt {
    public static final CreationExtras a(ViewModelStoreOwner owner) {
        Intrinsics.e(owner, "owner");
        return owner instanceof HasDefaultViewModelProviderFactory ? ((HasDefaultViewModelProviderFactory) owner).s() : CreationExtras.Empty.f4421b;
    }
}
