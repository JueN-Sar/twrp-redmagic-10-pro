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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$runningReduceIndexed$1", f = "_Sequences.kt", l = {2377, 2381}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class SequencesKt___SequencesKt$runningReduceIndexed$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<Integer, Object, Object, Object> $operation;
    final /* synthetic */ Sequence<Object> $this_runningReduceIndexed;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt___SequencesKt$runningReduceIndexed$1(Sequence<Object> sequence, Function3<? super Integer, Object, Object, Object> function3, Continuation<? super SequencesKt___SequencesKt$runningReduceIndexed$1> continuation) {
        super(2, continuation);
        this.$this_runningReduceIndexed = sequence;
        this.$operation = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        SequencesKt___SequencesKt$runningReduceIndexed$1 sequencesKt___SequencesKt$runningReduceIndexed$1 = new SequencesKt___SequencesKt$runningReduceIndexed$1(this.$this_runningReduceIndexed, this.$operation, continuation);
        sequencesKt___SequencesKt$runningReduceIndexed$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningReduceIndexed$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        SequenceScope sequenceScope;
        Iterator it;
        Object next;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        int i3 = 1;
        if (i2 == 0) {
            ResultKt.b(obj);
            sequenceScope = (SequenceScope) this.L$0;
            it = this.$this_runningReduceIndexed.iterator();
            if (it.hasNext()) {
                next = it.next();
                this.L$0 = sequenceScope;
                this.L$1 = it;
                this.L$2 = next;
                this.label = 1;
                if (sequenceScope.b(next, this) == d2) {
                    return d2;
                }
            }
            return Unit.f18288a;
        }
        if (i2 == 1) {
            next = this.L$2;
            it = (Iterator) this.L$1;
            sequenceScope = (SequenceScope) this.L$0;
            ResultKt.b(obj);
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = this.I$0;
            Object obj2 = this.L$2;
            it = (Iterator) this.L$1;
            sequenceScope = (SequenceScope) this.L$0;
            ResultKt.b(obj);
            i3 = i4;
            next = obj2;
        }
        while (it.hasNext()) {
            Function3<Integer, Object, Object, Object> function3 = this.$operation;
            int i5 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt__CollectionsKt.m();
            }
            Object u = function3.u(Boxing.b(i3), next, it.next());
            this.L$0 = sequenceScope;
            this.L$1 = it;
            this.L$2 = u;
            this.I$0 = i5;
            this.label = 2;
            if (sequenceScope.b(u, this) == d2) {
                return d2;
            }
            next = u;
            i3 = i5;
        }
        return Unit.f18288a;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public final Object y(SequenceScope sequenceScope, Continuation continuation) {
        return ((SequencesKt___SequencesKt$runningReduceIndexed$1) o(sequenceScope, continuation)).v(Unit.f18288a);
    }
}
