package kotlin.sequences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class SequencesKt___SequencesKt extends SequencesKt___SequencesJvmKt {
    public static int f(Sequence sequence) {
        Intrinsics.e(sequence, "<this>");
        Iterator it = sequence.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            it.next();
            i2++;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.l();
            }
        }
        return i2;
    }

    public static final Sequence g(Sequence sequence, Function1 predicate) {
        Intrinsics.e(sequence, "<this>");
        Intrinsics.e(predicate, "predicate");
        return new FilteringSequence(sequence, true, predicate);
    }

    public static final Sequence h(Sequence sequence, Function1 predicate) {
        Intrinsics.e(sequence, "<this>");
        Intrinsics.e(predicate, "predicate");
        return new FilteringSequence(sequence, false, predicate);
    }

    public static Object i(Sequence sequence) {
        Intrinsics.e(sequence, "<this>");
        Iterator it = sequence.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Sequence is empty.");
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    public static Sequence j(Sequence sequence, Function1 transform) {
        Intrinsics.e(sequence, "<this>");
        Intrinsics.e(transform, "transform");
        return new TransformingSequence(sequence, transform);
    }

    public static final Collection k(Sequence sequence, Collection destination) {
        Intrinsics.e(sequence, "<this>");
        Intrinsics.e(destination, "destination");
        Iterator it = sequence.iterator();
        while (it.hasNext()) {
            destination.add(it.next());
        }
        return destination;
    }

    public static List l(Sequence sequence) {
        Intrinsics.e(sequence, "<this>");
        return CollectionsKt__CollectionsKt.k(m(sequence));
    }

    public static final List m(Sequence sequence) {
        Intrinsics.e(sequence, "<this>");
        return (List) k(sequence, new ArrayList());
    }
}
