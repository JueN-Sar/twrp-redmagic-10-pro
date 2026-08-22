package androidx.collection;

import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class MutableFloatFloatMap extends FloatFloatMap {

    /* renamed from: f, reason: collision with root package name */
    private int f1316f;

    public MutableFloatFloatMap(int i2) {
        super(null);
        if (i2 < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.".toString());
        }
        h(ScatterMapKt.d(i2));
    }

    private final void f() {
        this.f1316f = ScatterMapKt.a(c()) - this.f1191e;
    }

    private final void g(int i2) {
        long[] jArr;
        if (i2 == 0) {
            jArr = ScatterMapKt.f1393a;
        } else {
            jArr = new long[((i2 + 15) & (-8)) >> 3];
            ArraysKt___ArraysJvmKt.o(jArr, -9187201950435737472L, 0, 0, 6, null);
        }
        this.f1187a = jArr;
        int i3 = i2 >> 3;
        long j2 = 255 << ((i2 & 7) << 3);
        jArr[i3] = (jArr[i3] & (~j2)) | j2;
        f();
    }

    private final void h(int i2) {
        int max = i2 > 0 ? Math.max(7, ScatterMapKt.c(i2)) : 0;
        this.f1190d = max;
        g(max);
        this.f1188b = new float[max];
        this.f1189c = new float[max];
    }
}
