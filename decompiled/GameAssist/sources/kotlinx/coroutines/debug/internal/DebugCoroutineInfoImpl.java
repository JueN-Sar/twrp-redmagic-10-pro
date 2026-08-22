package kotlinx.coroutines.debug.internal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;
import kotlin.sequences.SequencesKt___SequencesKt;

@Metadata
/* loaded from: classes2.dex */
public final class DebugCoroutineInfoImpl {

    /* renamed from: a, reason: collision with root package name */
    private final StackTraceFrame f19064a;

    /* renamed from: b, reason: collision with root package name */
    public final long f19065b;

    /* renamed from: c, reason: collision with root package name */
    private final WeakReference f19066c;

    /* renamed from: d, reason: collision with root package name */
    private String f19067d;

    /* renamed from: e, reason: collision with root package name */
    public Thread f19068e;

    /* renamed from: f, reason: collision with root package name */
    private WeakReference f19069f;

    private final List b() {
        Sequence b2;
        List l2;
        StackTraceFrame stackTraceFrame = this.f19064a;
        if (stackTraceFrame == null) {
            return CollectionsKt__CollectionsKt.g();
        }
        b2 = SequencesKt__SequenceBuilderKt.b(new DebugCoroutineInfoImpl$creationStackTrace$1(this, stackTraceFrame, null));
        l2 = SequencesKt___SequencesKt.l(b2);
        return l2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0049 -> B:11:0x0060). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x005a -> B:10:0x005d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object i(kotlin.sequences.SequenceScope r6, kotlin.coroutines.jvm.internal.CoroutineStackFrame r7, kotlin.coroutines.Continuation r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1 r0 = (kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1 r0 = new kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r5 = r0.L$2
            kotlin.coroutines.jvm.internal.CoroutineStackFrame r5 = (kotlin.coroutines.jvm.internal.CoroutineStackFrame) r5
            java.lang.Object r6 = r0.L$1
            kotlin.sequences.SequenceScope r6 = (kotlin.sequences.SequenceScope) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl r7 = (kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl) r7
            kotlin.ResultKt.b(r8)
            goto L5d
        L35:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3d:
            kotlin.ResultKt.b(r8)
        L40:
            if (r7 != 0) goto L45
            kotlin.Unit r5 = kotlin.Unit.f18288a
            return r5
        L45:
            java.lang.StackTraceElement r8 = r7.B()
            if (r8 == 0) goto L60
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r8 = r6.b(r8, r0)
            if (r8 != r1) goto L5a
            return r1
        L5a:
            r4 = r7
            r7 = r5
            r5 = r4
        L5d:
            r4 = r7
            r7 = r5
            r5 = r4
        L60:
            kotlin.coroutines.jvm.internal.CoroutineStackFrame r7 = r7.f()
            if (r7 == 0) goto L67
            goto L40
        L67:
            kotlin.Unit r5 = kotlin.Unit.f18288a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.i(kotlin.sequences.SequenceScope, kotlin.coroutines.jvm.internal.CoroutineStackFrame, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final CoroutineContext c() {
        return (CoroutineContext) this.f19066c.get();
    }

    public final StackTraceFrame d() {
        return this.f19064a;
    }

    public final List e() {
        return b();
    }

    public final CoroutineStackFrame f() {
        WeakReference weakReference = this.f19069f;
        if (weakReference != null) {
            return (CoroutineStackFrame) weakReference.get();
        }
        return null;
    }

    public final String g() {
        return this.f19067d;
    }

    public final List h() {
        CoroutineStackFrame f2 = f();
        if (f2 == null) {
            return CollectionsKt__CollectionsKt.g();
        }
        ArrayList arrayList = new ArrayList();
        while (f2 != null) {
            StackTraceElement B = f2.B();
            if (B != null) {
                arrayList.add(B);
            }
            f2 = f2.f();
        }
        return arrayList;
    }

    public String toString() {
        return "DebugCoroutineInfo(state=" + g() + ",context=" + c() + ')';
    }
}
