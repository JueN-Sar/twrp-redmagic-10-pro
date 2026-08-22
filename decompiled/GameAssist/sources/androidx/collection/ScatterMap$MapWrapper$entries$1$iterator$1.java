package androidx.collection;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequenceScope;

/* JADX INFO: Add missing generic type declarations: [V, K] */
@Metadata
@DebugMetadata(c = "androidx.collection.ScatterMap$MapWrapper$entries$1$iterator$1", f = "ScatterMap.kt", l = {701}, m = "invokeSuspend")
@SourceDebugExtension
/* loaded from: classes.dex */
final class ScatterMap$MapWrapper$entries$1$iterator$1<K, V> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Map.Entry<? extends K, ? extends V>>, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ ScatterMap<K, V> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScatterMap$MapWrapper$entries$1$iterator$1(ScatterMap<K, V> scatterMap, Continuation<? super ScatterMap$MapWrapper$entries$1$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = scatterMap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        ScatterMap$MapWrapper$entries$1$iterator$1 scatterMap$MapWrapper$entries$1$iterator$1 = new ScatterMap$MapWrapper$entries$1$iterator$1(this.this$0, continuation);
        scatterMap$MapWrapper$entries$1$iterator$1.L$0 = obj;
        return scatterMap$MapWrapper$entries$1$iterator$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0069  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0098 -> B:5:0x009b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x009e -> B:6:0x009f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0053 -> B:7:0x0067). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00ab -> B:18:0x00ac). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object v(java.lang.Object r22) {
        /*
            Method dump skipped, instructions count: 181
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterMap$MapWrapper$entries$1$iterator$1.v(java.lang.Object):java.lang.Object");
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public final Object y(SequenceScope sequenceScope, Continuation continuation) {
        return ((ScatterMap$MapWrapper$entries$1$iterator$1) o(sequenceScope, continuation)).v(Unit.f18288a);
    }
}
