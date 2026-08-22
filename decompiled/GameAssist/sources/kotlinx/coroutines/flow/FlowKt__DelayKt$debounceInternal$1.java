package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1", f = "Delay.kt", l = {222, 355}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class FlowKt__DelayKt$debounceInternal$1 extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<Object> $this_debounceInternal;
    final /* synthetic */ Function1<Object, Long> $timeoutMillisSelector;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounceInternal$1(Function1<Object, Long> function1, Flow<Object> flow, Continuation<? super FlowKt__DelayKt$debounceInternal$1> continuation) {
        super(3, continuation);
        this.$timeoutMillisSelector = function1;
        this.$this_debounceInternal = flow;
    }

    @Override // kotlin.jvm.functions.Function3
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object u(CoroutineScope coroutineScope, FlowCollector flowCollector, Continuation continuation) {
        FlowKt__DelayKt$debounceInternal$1 flowKt__DelayKt$debounceInternal$1 = new FlowKt__DelayKt$debounceInternal$1(this.$timeoutMillisSelector, this.$this_debounceInternal, continuation);
        flowKt__DelayKt$debounceInternal$1.L$0 = coroutineScope;
        flowKt__DelayKt$debounceInternal$1.L$1 = flowCollector;
        return flowKt__DelayKt$debounceInternal$1.v(Unit.f18288a);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:9|(4:11|(1:13)|14|(2:26|27)(2:16|(5:18|(1:20)|21|(1:23)|25)))|28|29|30|31|(1:33)|34|35|(1:37)|(1:39)|6|7|(2:45|46)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0022, code lost:
    
        if (r15 != r0) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00d7, code lost:
    
        r15 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e6, code lost:
    
        r7.j0(r15);
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00cc A[Catch: all -> 0x00d7, TryCatch #0 {all -> 0x00d7, blocks: (B:31:0x00c8, B:33:0x00cc, B:34:0x00d9), top: B:30:0x00c8 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x006a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object v(java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1.v(java.lang.Object):java.lang.Object");
    }
}
