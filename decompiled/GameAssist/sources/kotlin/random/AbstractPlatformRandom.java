package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public abstract class AbstractPlatformRandom extends Random {
    @Override // kotlin.random.Random
    public int b(int i2) {
        return RandomKt.d(l().nextInt(), i2);
    }

    @Override // kotlin.random.Random
    public boolean c() {
        return l().nextBoolean();
    }

    @Override // kotlin.random.Random
    public byte[] d(byte[] array) {
        Intrinsics.e(array, "array");
        l().nextBytes(array);
        return array;
    }

    @Override // kotlin.random.Random
    public double f() {
        return l().nextDouble();
    }

    @Override // kotlin.random.Random
    public float g() {
        return l().nextFloat();
    }

    @Override // kotlin.random.Random
    public int h() {
        return l().nextInt();
    }

    @Override // kotlin.random.Random
    public int i(int i2) {
        return l().nextInt(i2);
    }

    @Override // kotlin.random.Random
    public long k() {
        return l().nextLong();
    }

    public abstract java.util.Random l();
}
