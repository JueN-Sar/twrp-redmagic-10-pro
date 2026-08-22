package androidx.collection;

import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class MutableIntSet extends IntSet {

    /* renamed from: e, reason: collision with root package name */
    private int f1325e;

    public MutableIntSet(int i2) {
        super(null);
        if (i2 < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.".toString());
        }
        g(ScatterMapKt.d(i2));
    }

    private final void e() {
        this.f1325e = ScatterMapKt.a(b()) - this.f1255d;
    }

    private final void f(int i2) {
        long[] jArr;
        if (i2 == 0) {
            jArr = ScatterMapKt.f1393a;
        } else {
            jArr = new long[((i2 + 15) & (-8)) >> 3];
            ArraysKt___ArraysJvmKt.o(jArr, -9187201950435737472L, 0, 0, 6, null);
        }
        this.f1252a = jArr;
        int i3 = i2 >> 3;
        long j2 = 255 << ((i2 & 7) << 3);
        jArr[i3] = (jArr[i3] & (~j2)) | j2;
        e();
    }

    private final void g(int i2) {
        int max = i2 > 0 ? Math.max(7, ScatterMapKt.c(i2)) : 0;
        this.f1254c = max;
        f(max);
        this.f1253b = new int[max];
    }
}
