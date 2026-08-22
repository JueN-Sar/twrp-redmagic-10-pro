package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;

@Metadata
/* loaded from: classes2.dex */
public final class StackTraceFrame implements CoroutineStackFrame {

    /* renamed from: c, reason: collision with root package name */
    private final CoroutineStackFrame f19085c;

    /* renamed from: h, reason: collision with root package name */
    private final StackTraceElement f19086h;

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement B() {
        return this.f19086h;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame f() {
        return this.f19085c;
    }
}
