package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class ScatterMapKt {

    /* renamed from: a, reason: collision with root package name */
    public static final long[] f1393a = {-9187201950435737345L, -1};

    /* renamed from: b, reason: collision with root package name */
    private static final MutableScatterMap f1394b = new MutableScatterMap(0);

    public static final int a(int i2) {
        if (i2 == 7) {
            return 6;
        }
        return i2 - (i2 / 8);
    }

    public static final int b(int i2) {
        if (i2 == 0) {
            return 6;
        }
        return (i2 * 2) + 1;
    }

    public static final int c(int i2) {
        if (i2 > 0) {
            return (-1) >>> Integer.numberOfLeadingZeros(i2);
        }
        return 0;
    }

    public static final int d(int i2) {
        if (i2 == 7) {
            return 8;
        }
        return i2 + ((i2 - 1) / 7);
    }
}
