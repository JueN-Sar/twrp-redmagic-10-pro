package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class SparseArrayCompatKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f1408a = new Object();

    public static final Object c(SparseArrayCompat sparseArrayCompat, int i2) {
        Object obj;
        Intrinsics.e(sparseArrayCompat, "<this>");
        int a2 = ContainerHelpersKt.a(sparseArrayCompat.f1405h, sparseArrayCompat.f1407j, i2);
        if (a2 < 0 || (obj = sparseArrayCompat.f1406i[a2]) == f1408a) {
            return null;
        }
        return obj;
    }

    public static final Object d(SparseArrayCompat sparseArrayCompat, int i2, Object obj) {
        Object obj2;
        Intrinsics.e(sparseArrayCompat, "<this>");
        int a2 = ContainerHelpersKt.a(sparseArrayCompat.f1405h, sparseArrayCompat.f1407j, i2);
        return (a2 < 0 || (obj2 = sparseArrayCompat.f1406i[a2]) == f1408a) ? obj : obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void e(SparseArrayCompat sparseArrayCompat) {
        int i2 = sparseArrayCompat.f1407j;
        int[] iArr = sparseArrayCompat.f1405h;
        Object[] objArr = sparseArrayCompat.f1406i;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f1408a) {
                if (i4 != i3) {
                    iArr[i3] = iArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        sparseArrayCompat.f1404c = false;
        sparseArrayCompat.f1407j = i3;
    }
}
