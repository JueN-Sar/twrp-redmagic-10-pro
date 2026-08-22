package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

@Metadata
/* loaded from: classes2.dex */
public abstract class CompletionHandlerBase extends LockFreeLinkedListNode implements Function1<Throwable, Unit> {
    public abstract void d0(Throwable th);
}
