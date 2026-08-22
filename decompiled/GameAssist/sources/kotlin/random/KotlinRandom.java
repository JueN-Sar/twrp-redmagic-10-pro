package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
final class KotlinRandom extends java.util.Random {

    @NotNull
    private static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = 0;

    @NotNull
    private final Random impl;
    private boolean seedInitialized;

    @Metadata
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KotlinRandom(@NotNull Random impl) {
        Intrinsics.e(impl, "impl");
        this.impl = impl;
    }

    @Override // java.util.Random
    protected int next(int i2) {
        return this.impl.b(i2);
    }

    @Override // java.util.Random
    public boolean nextBoolean() {
        return this.impl.c();
    }

    @Override // java.util.Random
    public void nextBytes(byte[] bytes) {
        Intrinsics.e(bytes, "bytes");
        this.impl.d(bytes);
    }

    @Override // java.util.Random
    public double nextDouble() {
        return this.impl.f();
    }

    @Override // java.util.Random
    public float nextFloat() {
        return this.impl.g();
    }

    @Override // java.util.Random
    public int nextInt() {
        return this.impl.h();
    }

    @Override // java.util.Random
    public long nextLong() {
        return this.impl.k();
    }

    @Override // java.util.Random
    public void setSeed(long j2) {
        if (this.seedInitialized) {
            throw new UnsupportedOperationException("Setting seed is not supported.");
        }
        this.seedInitialized = true;
    }

    @Override // java.util.Random
    public int nextInt(int i2) {
        return this.impl.i(i2);
    }
}
