package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class LongList {

    /* renamed from: a, reason: collision with root package name */
    public long[] f1270a;

    /* renamed from: b, reason: collision with root package name */
    public int f1271b;

    public /* synthetic */ LongList(int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2);
    }

    public static /* synthetic */ String b(LongList longList, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence5 = (i3 & 2) != 0 ? "" : charSequence2;
        CharSequence charSequence6 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        return longList.a(charSequence, charSequence5, charSequence6, i4, charSequence4);
    }

    public final String a(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated) {
        Intrinsics.e(separator, "separator");
        Intrinsics.e(prefix, "prefix");
        Intrinsics.e(postfix, "postfix");
        Intrinsics.e(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        long[] jArr = this.f1270a;
        int i3 = this.f1271b;
        int i4 = 0;
        while (true) {
            if (i4 >= i3) {
                sb.append(postfix);
                break;
            }
            long j2 = jArr[i4];
            if (i4 == i2) {
                sb.append(truncated);
                break;
            }
            if (i4 != 0) {
                sb.append(separator);
            }
            sb.append(j2);
            i4++;
        }
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public boolean equals(Object obj) {
        IntRange h2;
        if (obj instanceof LongList) {
            LongList longList = (LongList) obj;
            int i2 = longList.f1271b;
            int i3 = this.f1271b;
            if (i2 == i3) {
                long[] jArr = this.f1270a;
                long[] jArr2 = longList.f1270a;
                h2 = RangesKt___RangesKt.h(0, i3);
                int g2 = h2.g();
                int h3 = h2.h();
                if (g2 > h3) {
                    return true;
                }
                while (jArr[g2] == jArr2[g2]) {
                    if (g2 == h3) {
                        return true;
                    }
                    g2++;
                }
                return false;
            }
        }
        return false;
    }

    public int hashCode() {
        long[] jArr = this.f1270a;
        int i2 = this.f1271b;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += Long.hashCode(jArr[i4]) * 31;
        }
        return i3;
    }

    public String toString() {
        return b(this, null, "[", "]", 0, null, 25, null);
    }

    private LongList(int i2) {
        this.f1270a = i2 == 0 ? LongSetKt.a() : new long[i2];
    }
}
