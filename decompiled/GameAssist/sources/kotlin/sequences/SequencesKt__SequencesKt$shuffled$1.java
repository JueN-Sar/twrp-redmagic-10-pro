package kotlin.sequences;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.random.Random;

@Metadata
@DebugMetadata(c = "kotlin.sequences.SequencesKt__SequencesKt$shuffled$1", f = "Sequences.kt", l = {145}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class SequencesKt__SequencesKt$shuffled$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Random $random;
    final /* synthetic */ Sequence<Object> $this_shuffled;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SequencesKt__SequencesKt$shuffled$1(Sequence<Object> sequence, Random random, Continuation<? super SequencesKt__SequencesKt$shuffled$1> continuation) {
        super(2, continuation);
        this.$this_shuffled = sequence;
        this.$random = random;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        SequencesKt__SequencesKt$shuffled$1 sequencesKt__SequencesKt$shuffled$1 = new SequencesKt__SequencesKt$shuffled$1(this.$this_shuffled, this.$random, continuation);
        sequencesKt__SequencesKt$shuffled$1.L$0 = obj;
        return sequencesKt__SequencesKt$shuffled$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        List m2;
        SequenceScope sequenceScope;
        Object r2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            SequenceScope sequenceScope2 = (SequenceScope) this.L$0;
            m2 = SequencesKt___SequencesKt.m(this.$this_shuffled);
            sequenceScope = sequenceScope2;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            m2 = (List) this.L$1;
            sequenceScope = (SequenceScope) this.L$0;
            ResultKt.b(obj);
        }
        while (!m2.isEmpty()) {
            int i3 = this.$random.i(m2.size());
            r2 = CollectionsKt__MutableCollectionsKt.r(m2);
            if (i3 < m2.size()) {
                r2 = m2.set(i3, r2);
            }
            this.L$0 = sequenceScope;
            this.L$1 = m2;
            this.label = 1;
            if (sequenceScope.b(r2, this) == d2) {
                return d2;
            }
        }
        return Unit.f18288a;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public final Object y(SequenceScope sequenceScope, Continuation continuation) {
        return ((SequencesKt__SequencesKt$shuffled$1) o(sequenceScope, continuation)).v(Unit.f18288a);
    }
}
