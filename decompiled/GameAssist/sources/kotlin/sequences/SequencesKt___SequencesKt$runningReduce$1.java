package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$runningReduce$1", f = "_Sequences.kt", l = {2348, 2351}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class SequencesKt___SequencesKt$runningReduce$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<Object, Object, Object> $operation;
    final /* synthetic */ Sequence<Object> $this_runningReduce;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$runningReduce$1(Sequence<Object> sequence, Function2<Object, Object, Object> function2, Continuation<? super SequencesKt___SequencesKt$runningReduce$1> continuation) {
        super(2, continuation);
        this.$this_runningReduce = sequence;
        this.$operation = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        SequencesKt___SequencesKt$runningReduce$1 sequencesKt___SequencesKt$runningReduce$1 = new SequencesKt___SequencesKt$runningReduce$1(this.$this_runningReduce, this.$operation, continuation);
        sequencesKt___SequencesKt$runningReduce$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningReduce$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        SequenceScope sequenceScope;
        Object next;
        Iterator it;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            sequenceScope = (SequenceScope) this.L$0;
            Iterator it2 = this.$this_runningReduce.iterator();
            if (it2.hasNext()) {
                next = it2.next();
                this.L$0 = sequenceScope;
                this.L$1 = it2;
                this.L$2 = next;
                this.label = 1;
                if (sequenceScope.b(next, this) == d2) {
                    return d2;
                }
                it = it2;
            }
            return Unit.f18288a;
        }
        if (i2 != 1 && i2 != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        next = this.L$2;
        it = (Iterator) this.L$1;
        sequenceScope = (SequenceScope) this.L$0;
        ResultKt.b(obj);
        while (it.hasNext()) {
            next = this.$operation.y(next, it.next());
            this.L$0 = sequenceScope;
            this.L$1 = it;
            this.L$2 = next;
            this.label = 2;
            if (sequenceScope.b(next, this) == d2) {
                return d2;
            }
        }
        return Unit.f18288a;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public final Object y(SequenceScope sequenceScope, Continuation continuation) {
        return ((SequencesKt___SequencesKt$runningReduce$1) o(sequenceScope, continuation)).v(Unit.f18288a);
    }
}
