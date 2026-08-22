package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class ObjectLongMap<K> {

    /* renamed from: a, reason: collision with root package name */
    public long[] f1378a;

    /* renamed from: b, reason: collision with root package name */
    public Object[] f1379b;

    /* renamed from: c, reason: collision with root package name */
    public long[] f1380c;

    /* renamed from: d, reason: collision with root package name */
    public int f1381d;

    /* renamed from: e, reason: collision with root package name */
    public int f1382e;

    public /* synthetic */ ObjectLongMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final int a(Object obj) {
        int i2 = 0;
        int hashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i3 = hashCode ^ (hashCode << 16);
        int i4 = i3 & 127;
        int i5 = this.f1381d;
        int i6 = i3 >>> 7;
        while (true) {
            int i7 = i6 & i5;
            long[] jArr = this.f1378a;
            int i8 = i7 >> 3;
            int i9 = (i7 & 7) << 3;
            long j2 = ((jArr[i8 + 1] << (64 - i9)) & ((-i9) >> 63)) | (jArr[i8] >>> i9);
            long j3 = (i4 * 72340172838076673L) ^ j2;
            for (long j4 = (~j3) & (j3 - 72340172838076673L) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i7) & i5;
                if (Intrinsics.a(this.f1379b[numberOfTrailingZeros], obj)) {
                    return numberOfTrailingZeros;
                }
            }
            if ((j2 & ((~j2) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i2 += 8;
            i6 = i7 + i2;
        }
    }

    public final long b(Object obj) {
        int a2 = a(obj);
        if (a2 >= 0) {
            return this.f1380c[a2];
        }
        throw new NoSuchElementException("There is no key " + obj + " in the map");
    }

    public final int c() {
        return this.f1381d;
    }

    public final int d() {
        return this.f1382e;
    }

    public final boolean e() {
        return this.f1382e == 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectLongMap)) {
            return false;
        }
        ObjectLongMap objectLongMap = (ObjectLongMap) obj;
        if (objectLongMap.d() != d()) {
            return false;
        }
        Object[] objArr = this.f1379b;
        long[] jArr = this.f1380c;
        long[] jArr2 = this.f1378a;
        int length = jArr2.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j2 = jArr2[i2];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j2) < 128) {
                            int i5 = (i2 << 3) + i4;
                            if (jArr[i5] != objectLongMap.b(objArr[i5])) {
                                return false;
                            }
                        }
                        j2 >>= 8;
                    }
                    if (i3 != 8) {
                        break;
                    }
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return true;
    }

    public int hashCode() {
        Object[] objArr = this.f1379b;
        long[] jArr = this.f1380c;
        long[] jArr2 = this.f1378a;
        int length = jArr2.length - 2;
        int i2 = 0;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                long j2 = jArr2[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((255 & j2) < 128) {
                            int i7 = (i3 << 3) + i6;
                            Object obj = objArr[i7];
                            i4 += (obj != null ? obj.hashCode() : 0) ^ Long.hashCode(jArr[i7]);
                        }
                        j2 >>= 8;
                    }
                    if (i5 != 8) {
                        return i4;
                    }
                }
                if (i3 == length) {
                    i2 = i4;
                    break;
                }
                i3++;
            }
        }
        return i2;
    }

    public String toString() {
        int i2;
        int i3;
        if (e()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        Object[] objArr = this.f1379b;
        long[] jArr = this.f1380c;
        long[] jArr2 = this.f1378a;
        int length = jArr2.length - 2;
        if (length >= 0) {
            int i4 = 0;
            int i5 = 0;
            while (true) {
                long j2 = jArr2[i4];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i6 = 8 - ((~(i4 - length)) >>> 31);
                    int i7 = 0;
                    while (i7 < i6) {
                        if ((255 & j2) < 128) {
                            int i8 = (i4 << 3) + i7;
                            Object obj = objArr[i8];
                            i3 = i4;
                            long j3 = jArr[i8];
                            if (obj == this) {
                                obj = "(this)";
                            }
                            sb.append(obj);
                            sb.append("=");
                            sb.append(j3);
                            i5++;
                            if (i5 < this.f1382e) {
                                sb.append(',');
                                sb.append(' ');
                            }
                        } else {
                            i3 = i4;
                        }
                        j2 >>= 8;
                        i7++;
                        i4 = i3;
                    }
                    int i9 = i4;
                    if (i6 != 8) {
                        break;
                    }
                    i2 = i9;
                } else {
                    i2 = i4;
                }
                if (i2 == length) {
                    break;
                }
                i4 = i2 + 1;
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "s.append('}').toString()");
        return sb2;
    }

    private ObjectLongMap() {
        this.f1378a = ScatterMapKt.f1393a;
        this.f1379b = ContainerHelpersKt.f1415c;
        this.f1380c = LongSetKt.a();
    }
}
