package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.RestrictsSuspension;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;

@SinceKotlin
@RestrictsSuspension
@Metadata
/* loaded from: classes2.dex */
public abstract class SequenceScope<T> {
    public abstract Object b(Object obj, Continuation continuation);

    public abstract Object c(Iterator it, Continuation continuation);

    public final Object e(Sequence sequence, Continuation continuation) {
        Object d2;
        Object c2 = c(sequence.iterator(), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return c2 == d2 ? c2 : Unit.f18288a;
    }
}
