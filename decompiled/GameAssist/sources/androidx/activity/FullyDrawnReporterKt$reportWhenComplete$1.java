package androidx.activity;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@DebugMetadata(c = "androidx.activity.FullyDrawnReporterKt", f = "FullyDrawnReporter.kt", l = {185}, m = "reportWhenComplete")
@SourceDebugExtension
/* loaded from: classes.dex */
final class FullyDrawnReporterKt$reportWhenComplete$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    FullyDrawnReporterKt$reportWhenComplete$1(Continuation<? super FullyDrawnReporterKt$reportWhenComplete$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FullyDrawnReporterKt.a(null, null, this);
    }
}
