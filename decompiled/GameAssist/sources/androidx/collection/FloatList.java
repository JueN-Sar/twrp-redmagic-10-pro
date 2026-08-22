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
public abstract class FloatList {

    /* renamed from: a, reason: collision with root package name */
    public float[] f1200a;

    /* renamed from: b, reason: collision with root package name */
    public int f1201b;

    public /* synthetic */ FloatList(int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2);
    }

    public static /* synthetic */ String b(FloatList floatList, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, int i3, Object obj) {
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
        return floatList.a(charSequence, charSequence5, charSequence6, i4, charSequence4);
    }

    public final String a(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated) {
        Intrinsics.e(separator, "separator");
        Intrinsics.e(prefix, "prefix");
        Intrinsics.e(postfix, "postfix");
        Intrinsics.e(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        float[] fArr = this.f1200a;
        int i3 = this.f1201b;
        int i4 = 0;
        while (true) {
            if (i4 >= i3) {
                sb.append(postfix);
                break;
            }
            float f2 = fArr[i4];
            if (i4 == i2) {
                sb.append(truncated);
                break;
            }
            if (i4 != 0) {
                sb.append(separator);
            }
            sb.append(f2);
            i4++;
        }
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public boolean equals(Object obj) {
        IntRange h2;
        if (obj instanceof FloatList) {
            FloatList floatList = (FloatList) obj;
            int i2 = floatList.f1201b;
            int i3 = this.f1201b;
            if (i2 == i3) {
                float[] fArr = this.f1200a;
                float[] fArr2 = floatList.f1200a;
                h2 = RangesKt___RangesKt.h(0, i3);
                int g2 = h2.g();
                int h3 = h2.h();
                if (g2 > h3) {
                    return true;
                }
                while (fArr[g2] == fArr2[g2]) {
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
        float[] fArr = this.f1200a;
        int i2 = this.f1201b;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += Float.hashCode(fArr[i4]) * 31;
        }
        return i3;
    }

    public String toString() {
        return b(this, null, "[", "]", 0, null, 25, null);
    }

    private FloatList(int i2) {
        this.f1200a = i2 == 0 ? FloatSetKt.a() : new float[i2];
    }
}
