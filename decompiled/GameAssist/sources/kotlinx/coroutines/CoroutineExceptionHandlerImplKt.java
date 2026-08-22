package kotlinx.coroutines;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;

@Metadata
/* loaded from: classes2.dex */
public final class CoroutineExceptionHandlerImplKt {

    /* renamed from: a, reason: collision with root package name */
    private static final List f18851a;

    static {
        Sequence c2;
        List l2;
        c2 = SequencesKt__SequencesKt.c(ServiceLoader.load(CoroutineExceptionHandler.class, CoroutineExceptionHandler.class.getClassLoader()).iterator());
        l2 = SequencesKt___SequencesKt.l(c2);
        f18851a = l2;
    }

    public static final void a(CoroutineContext coroutineContext, Throwable th) {
        Iterator it = f18851a.iterator();
        while (it.hasNext()) {
            try {
                ((CoroutineExceptionHandler) it.next()).P(coroutineContext, th);
            } catch (Throwable th2) {
                Thread currentThread = Thread.currentThread();
                currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, CoroutineExceptionHandlerKt.b(th, th2));
            }
        }
        Thread currentThread2 = Thread.currentThread();
        try {
            Result.Companion companion = Result.Companion;
            ExceptionsKt__ExceptionsKt.a(th, new DiagnosticCoroutineContextException(coroutineContext));
            Result.b(Unit.f18288a);
        } catch (Throwable th3) {
            Result.Companion companion2 = Result.Companion;
            Result.b(ResultKt.a(th3));
        }
        currentThread2.getUncaughtExceptionHandler().uncaughtException(currentThread2, th);
    }
}
