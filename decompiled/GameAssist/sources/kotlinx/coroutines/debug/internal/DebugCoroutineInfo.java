package kotlinx.coroutines.debug.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;

@Metadata
@PublishedApi
/* loaded from: classes2.dex */
public final class DebugCoroutineInfo {

    /* renamed from: a, reason: collision with root package name */
    private final CoroutineContext f19056a;

    /* renamed from: b, reason: collision with root package name */
    private final CoroutineStackFrame f19057b;

    /* renamed from: c, reason: collision with root package name */
    private final long f19058c;

    /* renamed from: d, reason: collision with root package name */
    private final List f19059d;

    /* renamed from: e, reason: collision with root package name */
    private final String f19060e;

    /* renamed from: f, reason: collision with root package name */
    private final Thread f19061f;

    /* renamed from: g, reason: collision with root package name */
    private final CoroutineStackFrame f19062g;

    /* renamed from: h, reason: collision with root package name */
    private final List f19063h;

    public DebugCoroutineInfo(DebugCoroutineInfoImpl debugCoroutineInfoImpl, CoroutineContext coroutineContext) {
        this.f19056a = coroutineContext;
        this.f19057b = debugCoroutineInfoImpl.d();
        this.f19058c = debugCoroutineInfoImpl.f19065b;
        this.f19059d = debugCoroutineInfoImpl.e();
        this.f19060e = debugCoroutineInfoImpl.g();
        this.f19061f = debugCoroutineInfoImpl.f19068e;
        this.f19062g = debugCoroutineInfoImpl.f();
        this.f19063h = debugCoroutineInfoImpl.h();
    }
}
