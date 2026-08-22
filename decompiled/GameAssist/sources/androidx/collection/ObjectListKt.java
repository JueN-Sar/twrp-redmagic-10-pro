package androidx.collection;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class ObjectListKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Object[] f1376a = new Object[0];

    /* renamed from: b, reason: collision with root package name */
    private static final ObjectList f1377b = new MutableObjectList(0);

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(List list, int i2) {
        int size = list.size();
        if (i2 < 0 || i2 >= size) {
            throw new IndexOutOfBoundsException("Index " + i2 + " is out of bounds. The list has " + size + " elements.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void e(List list, int i2, int i3) {
        int size = list.size();
        if (i2 > i3) {
            throw new IllegalArgumentException("Indices are out of order. fromIndex (" + i2 + ") is greater than toIndex (" + i3 + ").");
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("fromIndex (" + i2 + ") is less than 0.");
        }
        if (i3 <= size) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i3 + ") is more than than the list size (" + size + ')');
    }
}
