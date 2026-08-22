package kotlinx.coroutines.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata
/* loaded from: classes2.dex */
final class MissingMainCoroutineDispatcher extends MainCoroutineDispatcher implements Delay {

    /* renamed from: i, reason: collision with root package name */
    private final Throwable f19395i;

    /* renamed from: j, reason: collision with root package name */
    private final String f19396j;

    public /* synthetic */ MissingMainCoroutineDispatcher(Throwable th, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(th, (i2 & 2) != 0 ? null : str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0023, code lost:
    
        if (r1 == null) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.Void q0() {
        /*
            r4 = this;
            java.lang.Throwable r0 = r4.f19395i
            if (r0 == 0) goto L36
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Module with the Main dispatcher had failed to initialize"
            r0.append(r1)
            java.lang.String r1 = r4.f19396j
            if (r1 == 0) goto L25
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ". "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            if (r1 != 0) goto L27
        L25:
            java.lang.String r1 = ""
        L27:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.Throwable r4 = r4.f19395i
            r1.<init>(r0, r4)
            throw r1
        L36:
            kotlinx.coroutines.internal.MainDispatchersKt.d()
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.MissingMainCoroutineDispatcher.q0():java.lang.Void");
    }

    @Override // kotlinx.coroutines.Delay
    public DisposableHandle B(long j2, Runnable runnable, CoroutineContext coroutineContext) {
        q0();
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean l0(CoroutineContext coroutineContext) {
        q0();
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher
    public MainCoroutineDispatcher n0() {
        return this;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: p0, reason: merged with bridge method [inline-methods] */
    public Void j0(CoroutineContext coroutineContext, Runnable runnable) {
        q0();
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.coroutines.Delay
    /* renamed from: r0, reason: merged with bridge method [inline-methods] */
    public Void k(long j2, CancellableContinuation cancellableContinuation) {
        q0();
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Dispatchers.Main[missing");
        if (this.f19395i != null) {
            str = ", cause=" + this.f19395i;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(']');
        return sb.toString();
    }

    public MissingMainCoroutineDispatcher(Throwable th, String str) {
        this.f19395i = th;
        this.f19396j = str;
    }
}
