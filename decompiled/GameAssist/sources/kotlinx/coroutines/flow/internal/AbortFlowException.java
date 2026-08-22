package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class AbortFlowException extends CancellationException {

    @JvmField
    @NotNull
    public final transient FlowCollector<?> owner;

    public AbortFlowException(@NotNull FlowCollector<?> flowCollector) {
        super("Flow was aborted, no more elements needed");
        this.owner = flowCollector;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
