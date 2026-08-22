package kotlin.random;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@SinceKotlin
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public abstract class Random {

    @NotNull
    public static final Default Default = new Default(null);

    @NotNull
    private static final Random defaultRandom = PlatformImplementationsKt.f18428a.b();

    @Metadata
    public static final class Default extends Random implements Serializable {

        @Metadata
        private static final class Serialized implements Serializable {

            @NotNull
            public static final Serialized INSTANCE = new Serialized();
            private static final long serialVersionUID = 0;

            private Serialized() {
            }

            private final Object readResolve() {
                return Random.Default;
            }
        }

        public /* synthetic */ Default(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Object writeReplace() {
            return Serialized.INSTANCE;
        }

        @Override // kotlin.random.Random
        public int b(int i2) {
            return Random.defaultRandom.b(i2);
        }

        @Override // kotlin.random.Random
        public boolean c() {
            return Random.defaultRandom.c();
        }

        @Override // kotlin.random.Random
        public byte[] d(byte[] array) {
            Intrinsics.e(array, "array");
            return Random.defaultRandom.d(array);
        }

        @Override // kotlin.random.Random
        public byte[] e(byte[] array, int i2, int i3) {
            Intrinsics.e(array, "array");
            return Random.defaultRandom.e(array, i2, i3);
        }

        @Override // kotlin.random.Random
        public double f() {
            return Random.defaultRandom.f();
        }

        @Override // kotlin.random.Random
        public float g() {
            return Random.defaultRandom.g();
        }

        @Override // kotlin.random.Random
        public int h() {
            return Random.defaultRandom.h();
        }

        @Override // kotlin.random.Random
        public int i(int i2) {
            return Random.defaultRandom.i(i2);
        }

        @Override // kotlin.random.Random
        public int j(int i2, int i3) {
            return Random.defaultRandom.j(i2, i3);
        }

        @Override // kotlin.random.Random
        public long k() {
            return Random.defaultRandom.k();
        }

        private Default() {
        }
    }

    public abstract int b(int i2);

    public boolean c() {
        return b(1) != 0;
    }

    public byte[] d(byte[] array) {
        Intrinsics.e(array, "array");
        return e(array, 0, array.length);
    }

    public byte[] e(byte[] array, int i2, int i3) {
        Intrinsics.e(array, "array");
        if (!new IntRange(0, array.length).l(i2) || !new IntRange(0, array.length).l(i3)) {
            throw new IllegalArgumentException(("fromIndex (" + i2 + ") or toIndex (" + i3 + ") are out of range: 0.." + array.length + '.').toString());
        }
        if (i2 > i3) {
            throw new IllegalArgumentException(("fromIndex (" + i2 + ") must be not greater than toIndex (" + i3 + ").").toString());
        }
        int i4 = (i3 - i2) / 4;
        for (int i5 = 0; i5 < i4; i5++) {
            int h2 = h();
            array[i2] = (byte) h2;
            array[i2 + 1] = (byte) (h2 >>> 8);
            array[i2 + 2] = (byte) (h2 >>> 16);
            array[i2 + 3] = (byte) (h2 >>> 24);
            i2 += 4;
        }
        int i6 = i3 - i2;
        int b2 = b(i6 * 8);
        for (int i7 = 0; i7 < i6; i7++) {
            array[i2 + i7] = (byte) (b2 >>> (i7 * 8));
        }
        return array;
    }

    public double f() {
        return PlatformRandomKt.a(b(26), b(27));
    }

    public float g() {
        return b(24) / 1.6777216E7f;
    }

    public int h() {
        return b(32);
    }

    public int i(int i2) {
        return j(0, i2);
    }

    public int j(int i2, int i3) {
        int h2;
        int i4;
        int i5;
        RandomKt.b(i2, i3);
        int i6 = i3 - i2;
        if (i6 > 0 || i6 == Integer.MIN_VALUE) {
            if (((-i6) & i6) == i6) {
                i5 = b(RandomKt.c(i6));
            } else {
                do {
                    h2 = h() >>> 1;
                    i4 = h2 % i6;
                } while ((h2 - i4) + (i6 - 1) < 0);
                i5 = i4;
            }
            return i2 + i5;
        }
        while (true) {
            int h3 = h();
            if (i2 <= h3 && h3 < i3) {
                return h3;
            }
        }
    }

    public long k() {
        return (h() << 32) + h();
    }
}
