package kotlin.random;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class XorWowRandom extends Random implements Serializable {

    @NotNull
    private static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = 0;
    private int addend;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    @Metadata
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public XorWowRandom(int i2, int i3, int i4, int i5, int i6, int i7) {
        this.x = i2;
        this.y = i3;
        this.z = i4;
        this.w = i5;
        this.v = i6;
        this.addend = i7;
        if ((i2 | i3 | i4 | i5 | i6) == 0) {
            throw new IllegalArgumentException("Initial state must have at least one non-zero element.".toString());
        }
        for (int i8 = 0; i8 < 64; i8++) {
            h();
        }
    }

    @Override // kotlin.random.Random
    public int b(int i2) {
        return RandomKt.d(h(), i2);
    }

    @Override // kotlin.random.Random
    public int h() {
        int i2 = this.x;
        int i3 = i2 ^ (i2 >>> 2);
        this.x = this.y;
        this.y = this.z;
        this.z = this.w;
        int i4 = this.v;
        this.w = i4;
        int i5 = ((i3 ^ (i3 << 1)) ^ i4) ^ (i4 << 4);
        this.v = i5;
        int i6 = this.addend + 362437;
        this.addend = i6;
        return i5 + i6;
    }

    public XorWowRandom(int i2, int i3) {
        this(i2, i3, 0, 0, ~i2, (i2 << 10) ^ (i3 >>> 4));
    }
}
