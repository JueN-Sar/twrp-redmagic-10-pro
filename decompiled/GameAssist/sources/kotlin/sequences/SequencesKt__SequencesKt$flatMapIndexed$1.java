package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata
@DebugMetadata(c = "kotlin.sequences.SequencesKt__SequencesKt$flatMapIndexed$1", f = "Sequences.kt", l = {332}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class SequencesKt__SequencesKt$flatMapIndexed$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Object, Iterator<Object>> $iterator;
    final /* synthetic */ Sequence<Object> $source;
    final /* synthetic */ Function2<Integer, Object, Object> $transform;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt__SequencesKt$flatMapIndexed$1(Sequence<Object> sequence, Function2<? super Integer, Object, Object> function2, Function1<Object, ? extends Iterator<Object>> function1, Continuation<? super SequencesKt__SequencesKt$flatMapIndexed$1> continuation) {
        super(2, continuation);
        this.$source = sequence;
        this.$transform = function2;
        this.$iterator = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        SequencesKt__SequencesKt$flatMapIndexed$1 sequencesKt__SequencesKt$flatMapIndexed$1 = new SequencesKt__SequencesKt$flatMapIndexed$1(this.$source, this.$transform, this.$iterator, continuation);
        sequencesKt__SequencesKt$flatMapIndexed$1.L$0 = obj;
        return sequencesKt__SequencesKt$flatMapIndexed$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        SequenceScope sequenceScope;
        int i2;
        Iterator it;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.b(obj);
            sequenceScope = (SequenceScope) this.L$0;
            i2 = 0;
            it = this.$source.iterator();
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = this.I$0;
            it = (Iterator) this.L$1;
            sequenceScope = (SequenceScope) this.L$0;
            ResultKt.b(obj);
            i2 = i4;
        }
        while (it.hasNext()) {
            Object next = it.next();
            Function2<Integer, Object, Object> function2 = this.$transform;
            int i5 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.m();
            }
            Iterator it2 = (Iterator) this.$iterator.c(function2.y(Boxing.b(i2), next));
            this.L$0 = sequenceScope;
            this.L$1 = it;
            this.I$0 = i5;
            this.label = 1;
            if (sequenceScope.c(it2, this) == d2) {
                return d2;
            }
            i2 = i5;
        }
        return Unit.f18288a;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public final Object y(SequenceScope sequenceScope, Continuation continuation) {
        return ((SequencesKt__SequencesKt$flatMapIndexed$1) o(sequenceScope, continuation)).v(Unit.f18288a);
    }
}
