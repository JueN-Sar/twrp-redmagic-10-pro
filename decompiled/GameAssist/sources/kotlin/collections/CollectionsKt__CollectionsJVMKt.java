package kotlin.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.builders.ListBuilder;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class CollectionsKt__CollectionsJVMKt {
    public static List a(List builder) {
        Intrinsics.e(builder, "builder");
        return ((ListBuilder) builder).k();
    }

    public static final Object[] b(Object[] objArr, boolean z) {
        Intrinsics.e(objArr, "<this>");
        if (z && Intrinsics.a(objArr.getClass(), Object[].class)) {
            return objArr;
        }
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length, Object[].class);
        Intrinsics.d(copyOf, "copyOf(this, this.size, Array<Any?>::class.java)");
        return copyOf;
    }

    public static List c() {
        return new ListBuilder();
    }

    public static List d(int i2) {
        return new ListBuilder(i2);
    }

    public static List e(Object obj) {
        List singletonList = Collections.singletonList(obj);
        Intrinsics.d(singletonList, "singletonList(element)");
        return singletonList;
    }
}
