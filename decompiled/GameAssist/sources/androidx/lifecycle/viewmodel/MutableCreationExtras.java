package androidx.lifecycle.viewmodel;

import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class MutableCreationExtras extends CreationExtras {
    /* JADX WARN: Multi-variable type inference failed */
    public MutableCreationExtras() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // androidx.lifecycle.viewmodel.CreationExtras
    public Object a(CreationExtras.Key key) {
        Intrinsics.e(key, "key");
        return b().get(key);
    }

    public final void c(CreationExtras.Key key, Object obj) {
        Intrinsics.e(key, "key");
        b().put(key, obj);
    }

    public MutableCreationExtras(CreationExtras initialExtras) {
        Intrinsics.e(initialExtras, "initialExtras");
        b().putAll(initialExtras.b());
    }

    public /* synthetic */ MutableCreationExtras(CreationExtras creationExtras, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? CreationExtras.Empty.f4421b : creationExtras);
    }
}
