package kotlin.random.jdk8;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.AbstractPlatformRandom;

@Metadata
/* loaded from: classes2.dex */
public final class PlatformThreadLocalRandom extends AbstractPlatformRandom {
    @Override // kotlin.random.Random
    public int j(int i2, int i3) {
        return ThreadLocalRandom.current().nextInt(i2, i3);
    }

    @Override // kotlin.random.AbstractPlatformRandom
    public Random l() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        Intrinsics.d(current, "current()");
        return current;
    }
}
