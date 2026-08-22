package kotlin.io.encoding;

import com.google.android.gms.common.api.Api;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ExperimentalEncodingApi
@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public class Base64 {

    /* renamed from: c, reason: collision with root package name */
    public static final Default f18470c = new Default(null);

    /* renamed from: d, reason: collision with root package name */
    private static final byte[] f18471d = {13, 10};

    /* renamed from: e, reason: collision with root package name */
    private static final Base64 f18472e = new Base64(true, false);

    /* renamed from: f, reason: collision with root package name */
    private static final Base64 f18473f = new Base64(false, true);

    /* renamed from: a, reason: collision with root package name */
    private final boolean f18474a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f18475b;

    @Metadata
    public static final class Default extends Base64 {
        public /* synthetic */ Default(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final byte[] m() {
            return Base64.f18471d;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private Default() {
            /*
                r2 = this;
                r0 = 0
                r1 = 0
                r2.<init>(r0, r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.io.encoding.Base64.Default.<init>():void");
        }
    }

    public /* synthetic */ Base64(boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2);
    }

    private final void b(int i2, int i3, int i4) {
        if (i3 < 0 || i3 > i2) {
            throw new IndexOutOfBoundsException("destination offset: " + i3 + ", destination size: " + i2);
        }
        int i5 = i3 + i4;
        if (i5 < 0 || i5 > i2) {
            throw new IndexOutOfBoundsException("The destination array does not have enough capacity, destination offset: " + i3 + ", destination size: " + i2 + ", capacity needed: " + i4);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00cf, code lost:
    
        if (r7 == (-2)) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00d1, code lost:
    
        r0 = l(r19, r5, r23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d5, code lost:
    
        if (r0 < r23) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d9, code lost:
    
        return r8 - r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00da, code lost:
    
        r1 = r19[r0] & 255;
        r3 = new java.lang.StringBuilder();
        r3.append("Symbol '");
        r3.append((char) r1);
        r3.append("'(");
        r4 = kotlin.text.CharsKt__CharJVMKt.a(8);
        r1 = java.lang.Integer.toString(r1, r4);
        kotlin.jvm.internal.Intrinsics.d(r1, "toString(this, checkRadix(radix))");
        r3.append(r1);
        r3.append(") at index ");
        r3.append(r0 - 1);
        r3.append(" is prohibited after the pad character");
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0115, code lost:
    
        throw new java.lang.IllegalArgumentException(r3.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x011d, code lost:
    
        throw new java.lang.IllegalArgumentException("The last unit of input does not have enough bits");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int d(byte[] r19, byte[] r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.encoding.Base64.d(byte[], byte[], int, int, int):int");
    }

    private final int f(byte[] bArr, int i2, int i3) {
        int[] iArr;
        int i4 = i3 - i2;
        if (i4 == 0) {
            return 0;
        }
        if (i4 == 1) {
            throw new IllegalArgumentException("Input should have at list 2 symbols for Base64 decoding, startIndex: " + i2 + ", endIndex: " + i3);
        }
        if (this.f18475b) {
            while (true) {
                if (i2 >= i3) {
                    break;
                }
                int i5 = bArr[i2] & 255;
                iArr = Base64Kt.f18477b;
                int i6 = iArr[i5];
                if (i6 < 0) {
                    if (i6 == -2) {
                        i4 -= i3 - i2;
                        break;
                    }
                    i4--;
                }
                i2++;
            }
        } else if (bArr[i3 - 1] == 61) {
            i4 = bArr[i3 + (-2)] == 61 ? i4 - 2 : i4 - 1;
        }
        return (int) ((i4 * 6) / 8);
    }

    private final int i(int i2) {
        int i3 = (i2 + 2) / 3;
        int i4 = (i3 * 4) + ((this.f18475b ? (i3 - 1) / 19 : 0) * 2);
        if (i4 >= 0) {
            return i4;
        }
        throw new IllegalArgumentException("Input is too big");
    }

    private final int j(byte[] bArr, int i2, int i3, int i4) {
        if (i4 == -8) {
            throw new IllegalArgumentException("Redundant pad character at index " + i2);
        }
        if (i4 != -6) {
            if (i4 == -4) {
                int l2 = l(bArr, i2 + 1, i3);
                if (l2 != i3 && bArr[l2] == 61) {
                    return l2 + 1;
                }
                throw new IllegalArgumentException("Missing one pad character at index " + l2);
            }
            if (i4 != -2) {
                throw new IllegalStateException("Unreachable".toString());
            }
        }
        return i2 + 1;
    }

    private final int l(byte[] bArr, int i2, int i3) {
        int[] iArr;
        if (!this.f18475b) {
            return i2;
        }
        while (i2 < i3) {
            int i4 = bArr[i2] & 255;
            iArr = Base64Kt.f18477b;
            if (iArr[i4] != -1) {
                return i2;
            }
            i2++;
        }
        return i2;
    }

    public final void c(int i2, int i3, int i4) {
        AbstractList.Companion.a(i3, i4, i2);
    }

    public final int e(byte[] source, byte[] destination, int i2, int i3, int i4) {
        Intrinsics.e(source, "source");
        Intrinsics.e(destination, "destination");
        c(source.length, i3, i4);
        b(destination.length, i2, f(source, i3, i4));
        return d(source, destination, i2, i3, i4);
    }

    public final int g(byte[] source, byte[] destination, int i2, int i3, int i4) {
        Intrinsics.e(source, "source");
        Intrinsics.e(destination, "destination");
        return h(source, destination, i2, i3, i4);
    }

    public final int h(byte[] source, byte[] destination, int i2, int i3, int i4) {
        Intrinsics.e(source, "source");
        Intrinsics.e(destination, "destination");
        c(source.length, i3, i4);
        b(destination.length, i2, i(i4 - i3));
        byte[] bArr = this.f18474a ? Base64Kt.f18478c : Base64Kt.f18476a;
        int i5 = this.f18475b ? 19 : Api.BaseClientBuilder.API_PRIORITY_OTHER;
        int i6 = i2;
        while (i3 + 2 < i4) {
            int min = Math.min((i4 - i3) / 3, i5);
            for (int i7 = 0; i7 < min; i7++) {
                int i8 = source[i3] & 255;
                int i9 = i3 + 2;
                int i10 = source[i3 + 1] & 255;
                i3 += 3;
                int i11 = (i10 << 8) | (i8 << 16) | (source[i9] & 255);
                destination[i6] = bArr[i11 >>> 18];
                destination[i6 + 1] = bArr[(i11 >>> 12) & 63];
                int i12 = i6 + 3;
                destination[i6 + 2] = bArr[(i11 >>> 6) & 63];
                i6 += 4;
                destination[i12] = bArr[i11 & 63];
            }
            if (min == i5 && i3 != i4) {
                int i13 = i6 + 1;
                byte[] bArr2 = f18471d;
                destination[i6] = bArr2[0];
                i6 += 2;
                destination[i13] = bArr2[1];
            }
        }
        int i14 = i4 - i3;
        if (i14 == 1) {
            int i15 = (source[i3] & 255) << 4;
            destination[i6] = bArr[i15 >>> 6];
            destination[i6 + 1] = bArr[i15 & 63];
            int i16 = i6 + 3;
            destination[i6 + 2] = 61;
            i6 += 4;
            destination[i16] = 61;
            i3++;
        } else if (i14 == 2) {
            int i17 = i3 + 1;
            int i18 = source[i3] & 255;
            i3 += 2;
            int i19 = ((source[i17] & 255) << 2) | (i18 << 10);
            destination[i6] = bArr[i19 >>> 12];
            destination[i6 + 1] = bArr[(i19 >>> 6) & 63];
            int i20 = i6 + 3;
            destination[i6 + 2] = bArr[i19 & 63];
            i6 += 4;
            destination[i20] = 61;
        }
        if (i3 == i4) {
            return i6 - i2;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final boolean k() {
        return this.f18475b;
    }

    private Base64(boolean z, boolean z2) {
        this.f18474a = z;
        this.f18475b = z2;
        if (z && z2) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }
}
