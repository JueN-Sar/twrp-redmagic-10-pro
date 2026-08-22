package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$zipWithNext$2", f = "_Sequences.kt", l = {2855}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class SequencesKt___SequencesKt$zipWithNext$2 extends RestrictedSuspendLambda implements Function2<SequenceScope<Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Sequence<Object> $this_zipWithNext;
    final /* synthetic */ Function2<Object, Object, Object> $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$zipWithNext$2(Sequence<Object> sequence, Function2<Object, Object, Object> function2, Continuation<? super SequencesKt___SequencesKt$zipWithNext$2> continuation) {
        super(2, continuation);
        this.$this_zipWithNext = sequence;
        this.$transform = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        SequencesKt___SequencesKt$zipWithNext$2 sequencesKt___SequencesKt$zipWithNext$2 = new SequencesKt___SequencesKt$zipWithNext$2(this.$this_zipWithNext, this.$transform, continuation);
        sequencesKt___SequencesKt$zipWithNext$2.L$0 = obj;
        return sequencesKt___SequencesKt$zipWithNext$2;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0045  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x005b -> B:5:0x0018). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object v(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L22
            if (r1 != r2) goto L1a
            java.lang.Object r1 = r6.L$2
            java.lang.Object r3 = r6.L$1
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r6.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.b(r7)
        L18:
            r7 = r1
            goto L3f
        L1a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L22:
            kotlin.ResultKt.b(r7)
            java.lang.Object r7 = r6.L$0
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            kotlin.sequences.Sequence<java.lang.Object> r1 = r6.$this_zipWithNext
            java.util.Iterator r1 = r1.iterator()
            boolean r3 = r1.hasNext()
            if (r3 != 0) goto L38
            kotlin.Unit r6 = kotlin.Unit.f18288a
            return r6
        L38:
            java.lang.Object r3 = r1.next()
            r4 = r7
            r7 = r3
            r3 = r1
        L3f:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L5e
            java.lang.Object r1 = r3.next()
            kotlin.jvm.functions.Function2<java.lang.Object, java.lang.Object, java.lang.Object> r5 = r6.$transform
            java.lang.Object r7 = r5.y(r7, r1)
            r6.L$0 = r4
            r6.L$1 = r3
            r6.L$2 = r1
            r6.label = r2
            java.lang.Object r7 = r4.b(r7, r6)
            if (r7 != r0) goto L18
            return r0
        L5e:
            kotlin.Unit r6 = kotlin.Unit.f18288a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$zipWithNext$2.v(java.lang.Object):java.lang.Object");
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public final Object y(SequenceScope sequenceScope, Continuation continuation) {
        return ((SequencesKt___SequencesKt$zipWithNext$2) o(sequenceScope, continuation)).v(Unit.f18288a);
    }
}
