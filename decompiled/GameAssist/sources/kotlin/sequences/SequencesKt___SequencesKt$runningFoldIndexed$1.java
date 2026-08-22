package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$runningFoldIndexed$1", f = "_Sequences.kt", l = {2318, 2323}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class SequencesKt___SequencesKt$runningFoldIndexed$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $initial;
    final /* synthetic */ Function3<Integer, Object, Object, Object> $operation;
    final /* synthetic */ Sequence<Object> $this_runningFoldIndexed;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt___SequencesKt$runningFoldIndexed$1(Object obj, Sequence<Object> sequence, Function3<? super Integer, Object, Object, Object> function3, Continuation<? super SequencesKt___SequencesKt$runningFoldIndexed$1> continuation) {
        super(2, continuation);
        this.$initial = obj;
        this.$this_runningFoldIndexed = sequence;
        this.$operation = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        SequencesKt___SequencesKt$runningFoldIndexed$1 sequencesKt___SequencesKt$runningFoldIndexed$1 = new SequencesKt___SequencesKt$runningFoldIndexed$1(this.$initial, this.$this_runningFoldIndexed, this.$operation, continuation);
        sequencesKt___SequencesKt$runningFoldIndexed$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningFoldIndexed$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0055  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object v(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r1 = r8.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L30
            if (r1 == r3) goto L28
            if (r1 != r2) goto L20
            int r1 = r8.I$0
            java.lang.Object r3 = r8.L$2
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r8.L$1
            java.lang.Object r5 = r8.L$0
            kotlin.sequences.SequenceScope r5 = (kotlin.sequences.SequenceScope) r5
            kotlin.ResultKt.b(r9)
            r9 = r4
            r4 = r1
            goto L4f
        L20:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L28:
            java.lang.Object r1 = r8.L$0
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            kotlin.ResultKt.b(r9)
            goto L45
        L30:
            kotlin.ResultKt.b(r9)
            java.lang.Object r9 = r8.L$0
            r1 = r9
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            java.lang.Object r9 = r8.$initial
            r8.L$0 = r1
            r8.label = r3
            java.lang.Object r9 = r1.b(r9, r8)
            if (r9 != r0) goto L45
            return r0
        L45:
            java.lang.Object r9 = r8.$initial
            kotlin.sequences.Sequence<java.lang.Object> r3 = r8.$this_runningFoldIndexed
            java.util.Iterator r3 = r3.iterator()
            r4 = 0
            r5 = r1
        L4f:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L7e
            java.lang.Object r1 = r3.next()
            kotlin.jvm.functions.Function3<java.lang.Integer, java.lang.Object, java.lang.Object, java.lang.Object> r6 = r8.$operation
            int r7 = r4 + 1
            if (r4 >= 0) goto L62
            kotlin.collections.CollectionsKt.m()
        L62:
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.b(r4)
            java.lang.Object r4 = r6.u(r4, r9, r1)
            r8.L$0 = r5
            r8.L$1 = r4
            r8.L$2 = r3
            r8.I$0 = r7
            r8.label = r2
            java.lang.Object r9 = r5.b(r4, r8)
            if (r9 != r0) goto L7b
            return r0
        L7b:
            r9 = r4
            r4 = r7
            goto L4f
        L7e:
            kotlin.Unit r8 = kotlin.Unit.f18288a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$runningFoldIndexed$1.v(java.lang.Object):java.lang.Object");
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public final Object y(SequenceScope sequenceScope, Continuation continuation) {
        return ((SequencesKt___SequencesKt$runningFoldIndexed$1) o(sequenceScope, continuation)).v(Unit.f18288a);
    }
}
