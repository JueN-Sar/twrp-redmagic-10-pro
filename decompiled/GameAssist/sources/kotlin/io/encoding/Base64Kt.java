package kotlin.io.encoding;

import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class Base64Kt {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f18476a;

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f18477b;

    /* renamed from: c, reason: collision with root package name */
    private static final byte[] f18478c;

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f18479d;

    static {
        byte[] bArr = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        f18476a = bArr;
        int[] iArr = new int[256];
        int i2 = 0;
        ArraysKt___ArraysJvmKt.n(iArr, -1, 0, 0, 6, null);
        iArr[61] = -2;
        int length = bArr.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            iArr[bArr[i3]] = i4;
            i3++;
            i4++;
        }
        f18477b = iArr;
        byte[] bArr2 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        f18478c = bArr2;
        int[] iArr2 = new int[256];
        ArraysKt___ArraysJvmKt.n(iArr2, -1, 0, 0, 6, null);
        iArr2[61] = -2;
        int length2 = bArr2.length;
        int i5 = 0;
        while (i2 < length2) {
            iArr2[bArr2[i2]] = i5;
            i2++;
            i5++;
        }
        f18479d = iArr2;
    }

    public static final boolean e(int i2) {
        if (i2 >= 0) {
            int[] iArr = f18477b;
            if (i2 < iArr.length && iArr[i2] != -1) {
                return true;
            }
        }
        return false;
    }
}
