package androidx.collection;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class ObjectList<E> {

    /* renamed from: a, reason: collision with root package name */
    public Object[] f1374a;

    /* renamed from: b, reason: collision with root package name */
    public int f1375b;

    public /* synthetic */ ObjectList(int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2);
    }

    public static /* synthetic */ String h(ObjectList objectList, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
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
        CharSequence charSequence7 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return objectList.g(charSequence, charSequence5, charSequence6, i4, charSequence7, function1);
    }

    public final boolean a(Object obj) {
        return e(obj) >= 0;
    }

    public final boolean b(Iterable elements) {
        Intrinsics.e(elements, "elements");
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            if (!a(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final Object c(int i2) {
        if (i2 >= 0 && i2 < this.f1375b) {
            return this.f1374a[i2];
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Index ");
        sb.append(i2);
        sb.append(" must be in 0..");
        sb.append(this.f1375b - 1);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final int d() {
        return this.f1375b;
    }

    public final int e(Object obj) {
        int i2 = 0;
        if (obj == null) {
            Object[] objArr = this.f1374a;
            int i3 = this.f1375b;
            while (i2 < i3) {
                if (objArr[i2] == null) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        Object[] objArr2 = this.f1374a;
        int i4 = this.f1375b;
        while (i2 < i4) {
            if (obj.equals(objArr2[i2])) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        IntRange h2;
        if (obj instanceof ObjectList) {
            ObjectList objectList = (ObjectList) obj;
            int i2 = objectList.f1375b;
            int i3 = this.f1375b;
            if (i2 == i3) {
                Object[] objArr = this.f1374a;
                Object[] objArr2 = objectList.f1374a;
                h2 = RangesKt___RangesKt.h(0, i3);
                int g2 = h2.g();
                int h3 = h2.h();
                if (g2 > h3) {
                    return true;
                }
                while (Intrinsics.a(objArr[g2], objArr2[g2])) {
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

    public final boolean f() {
        return this.f1375b == 0;
    }

    public final String g(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 function1) {
        Intrinsics.e(separator, "separator");
        Intrinsics.e(prefix, "prefix");
        Intrinsics.e(postfix, "postfix");
        Intrinsics.e(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        Object[] objArr = this.f1374a;
        int i3 = this.f1375b;
        int i4 = 0;
        while (true) {
            if (i4 >= i3) {
                sb.append(postfix);
                break;
            }
            Object obj = objArr[i4];
            if (i4 == i2) {
                sb.append(truncated);
                break;
            }
            if (i4 != 0) {
                sb.append(separator);
            }
            if (function1 == null) {
                sb.append(obj);
            } else {
                sb.append((CharSequence) function1.c(obj));
            }
            i4++;
        }
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public int hashCode() {
        Object[] objArr = this.f1374a;
        int i2 = this.f1375b;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            i3 += (obj != null ? obj.hashCode() : 0) * 31;
        }
        return i3;
    }

    public final int i(Object obj) {
        if (obj == null) {
            Object[] objArr = this.f1374a;
            for (int i2 = this.f1375b - 1; -1 < i2; i2--) {
                if (objArr[i2] == null) {
                    return i2;
                }
            }
        } else {
            Object[] objArr2 = this.f1374a;
            for (int i3 = this.f1375b - 1; -1 < i3; i3--) {
                if (obj.equals(objArr2[i3])) {
                    return i3;
                }
            }
        }
        return -1;
    }

    public String toString() {
        return h(this, null, "[", "]", 0, null, new Function1<E, CharSequence>(this) { // from class: androidx.collection.ObjectList$toString$1
            final /* synthetic */ ObjectList<E> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final CharSequence c(Object obj) {
                return obj == this.this$0 ? "(this)" : String.valueOf(obj);
            }
        }, 25, null);
    }

    private ObjectList(int i2) {
        this.f1374a = i2 == 0 ? ObjectListKt.f1376a : new Object[i2];
    }
}
