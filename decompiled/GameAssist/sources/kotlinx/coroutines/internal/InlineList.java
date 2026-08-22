package kotlinx.coroutines.internal;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@JvmInline
/* loaded from: classes2.dex */
public final class InlineList<E> {

    /* renamed from: a, reason: collision with root package name */
    private final Object f19359a;

    public static Object a(Object obj) {
        return obj;
    }

    public static /* synthetic */ Object b(Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 1) != 0) {
            obj = null;
        }
        return a(obj);
    }

    public static boolean c(Object obj, Object obj2) {
        return (obj2 instanceof InlineList) && Intrinsics.a(obj, ((InlineList) obj2).g());
    }

    public static int d(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static final Object e(Object obj, Object obj2) {
        if (obj == null) {
            return a(obj2);
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return a(obj);
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return a(arrayList);
    }

    public static String f(Object obj) {
        return "InlineList(holder=" + obj + ')';
    }

    public boolean equals(Object obj) {
        return c(this.f19359a, obj);
    }

    public final /* synthetic */ Object g() {
        return this.f19359a;
    }

    public int hashCode() {
        return d(this.f19359a);
    }

    public String toString() {
        return f(this.f19359a);
    }
}
