package kotlin.random;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
final class PlatformRandom extends AbstractPlatformRandom implements Serializable {

    @NotNull
    private static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = 0;

    @NotNull
    private final java.util.Random impl;

    @Metadata
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PlatformRandom(@NotNull java.util.Random impl) {
        Intrinsics.e(impl, "impl");
        this.impl = impl;
    }

    @Override // kotlin.random.AbstractPlatformRandom
    public java.util.Random l() {
        return this.impl;
    }
}
