package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt__AppendableKt;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class CollectionsKt___CollectionsKt extends CollectionsKt___CollectionsJvmKt {
    public static final int A(Iterable iterable, Object obj) {
        Intrinsics.e(iterable, "<this>");
        if (iterable instanceof List) {
            return ((List) iterable).indexOf(obj);
        }
        int i2 = 0;
        for (Object obj2 : iterable) {
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.m();
            }
            if (Intrinsics.a(obj, obj2)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static final Appendable B(Iterable iterable, Appendable buffer, CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 function1) {
        Intrinsics.e(iterable, "<this>");
        Intrinsics.e(buffer, "buffer");
        Intrinsics.e(separator, "separator");
        Intrinsics.e(prefix, "prefix");
        Intrinsics.e(postfix, "postfix");
        Intrinsics.e(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (Object obj : iterable) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            StringsKt__AppendableKt.a(buffer, obj, function1);
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static final String D(Iterable iterable, CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 function1) {
        Intrinsics.e(iterable, "<this>");
        Intrinsics.e(separator, "separator");
        Intrinsics.e(prefix, "prefix");
        Intrinsics.e(postfix, "postfix");
        Intrinsics.e(truncated, "truncated");
        String sb = ((StringBuilder) B(iterable, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        Intrinsics.d(sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    public static /* synthetic */ String E(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
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
        return D(iterable, charSequence, charSequence5, charSequence6, i4, charSequence7, function1);
    }

    public static Object F(List list) {
        Intrinsics.e(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(CollectionsKt__CollectionsKt.i(list));
    }

    public static Comparable G(Iterable iterable) {
        Intrinsics.e(iterable, "<this>");
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Comparable comparable = (Comparable) it.next();
        while (it.hasNext()) {
            Comparable comparable2 = (Comparable) it.next();
            if (comparable.compareTo(comparable2) > 0) {
                comparable = comparable2;
            }
        }
        return comparable;
    }

    public static Object H(Iterable iterable) {
        Intrinsics.e(iterable, "<this>");
        if (iterable instanceof List) {
            return I((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        Object next = it.next();
        if (it.hasNext()) {
            throw new IllegalArgumentException("Collection has more than one element.");
        }
        return next;
    }

    public static final Object I(List list) {
        Intrinsics.e(list, "<this>");
        int size = list.size();
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        if (size == 1) {
            return list.get(0);
        }
        throw new IllegalArgumentException("List has more than one element.");
    }

    public static Object J(List list) {
        Intrinsics.e(list, "<this>");
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    public static final Collection K(Iterable iterable, Collection destination) {
        Intrinsics.e(iterable, "<this>");
        Intrinsics.e(destination, "destination");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            destination.add(it.next());
        }
        return destination;
    }

    public static List L(Iterable iterable) {
        Intrinsics.e(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return CollectionsKt__CollectionsKt.k(M(iterable));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return CollectionsKt__CollectionsKt.g();
        }
        if (size != 1) {
            return N(collection);
        }
        return CollectionsKt__CollectionsJVMKt.e(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static final List M(Iterable iterable) {
        Intrinsics.e(iterable, "<this>");
        return iterable instanceof Collection ? N((Collection) iterable) : (List) K(iterable, new ArrayList());
    }

    public static final List N(Collection collection) {
        Intrinsics.e(collection, "<this>");
        return new ArrayList(collection);
    }

    public static List O(Iterable iterable, Iterable other) {
        Intrinsics.e(iterable, "<this>");
        Intrinsics.e(other, "other");
        Iterator it = iterable.iterator();
        Iterator it2 = other.iterator();
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.n(iterable, 10), CollectionsKt__IterablesKt.n(other, 10)));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(TuplesKt.a(it.next(), it2.next()));
        }
        return arrayList;
    }

    public static Sequence w(final Iterable iterable) {
        Intrinsics.e(iterable, "<this>");
        return new Sequence<Object>() { // from class: kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator iterator() {
                return iterable.iterator();
            }
        };
    }

    public static boolean x(Iterable iterable, Object obj) {
        Intrinsics.e(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).contains(obj) : A(iterable, obj) >= 0;
    }

    public static Object y(Iterable iterable) {
        Intrinsics.e(iterable, "<this>");
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static Object z(List list) {
        Intrinsics.e(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
