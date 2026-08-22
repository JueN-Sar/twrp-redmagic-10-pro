package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public abstract class CancelHandlerBase implements Function1<Throwable, Unit> {
    public abstract void d(Throwable th);
}
