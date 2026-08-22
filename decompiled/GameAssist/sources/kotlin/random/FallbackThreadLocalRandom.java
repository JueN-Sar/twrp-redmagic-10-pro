package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class FallbackThreadLocalRandom extends AbstractPlatformRandom {

    /* renamed from: c, reason: collision with root package name */
    private final FallbackThreadLocalRandom$implStorage$1 f18590c = new ThreadLocal<java.util.Random>() { // from class: kotlin.random.FallbackThreadLocalRandom$implStorage$1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public java.util.Random initialValue() {
            return new java.util.Random();
        }
    };

    @Override // kotlin.random.AbstractPlatformRandom
    public java.util.Random l() {
        java.util.Random random = get();
        Intrinsics.d(random, "implStorage.get()");
        return random;
    }
}
