package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public interface DefaultLifecycleObserver extends LifecycleObserver {
    default void a(LifecycleOwner owner) {
        Intrinsics.e(owner, "owner");
    }

    default void d(LifecycleOwner owner) {
        Intrinsics.e(owner, "owner");
    }

    default void h(LifecycleOwner owner) {
        Intrinsics.e(owner, "owner");
    }

    default void i(LifecycleOwner owner) {
        Intrinsics.e(owner, "owner");
    }

    default void j(LifecycleOwner owner) {
        Intrinsics.e(owner, "owner");
    }

    default void k(LifecycleOwner owner) {
        Intrinsics.e(owner, "owner");
    }
}
