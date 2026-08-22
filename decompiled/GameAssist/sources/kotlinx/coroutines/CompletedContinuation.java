package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class CompletedContinuation {

    /* renamed from: a, reason: collision with root package name */
    public final Object f18839a;

    /* renamed from: b, reason: collision with root package name */
    public final CancelHandler f18840b;

    /* renamed from: c, reason: collision with root package name */
    public final Function1 f18841c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f18842d;

    /* renamed from: e, reason: collision with root package name */
    public final Throwable f18843e;

    public CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th) {
        this.f18839a = obj;
        this.f18840b = cancelHandler;
        this.f18841c = function1;
        this.f18842d = obj2;
        this.f18843e = th;
    }

    public static /* synthetic */ CompletedContinuation b(CompletedContinuation completedContinuation, Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i2, Object obj3) {
        if ((i2 & 1) != 0) {
            obj = completedContinuation.f18839a;
        }
        if ((i2 & 2) != 0) {
            cancelHandler = completedContinuation.f18840b;
        }
        CancelHandler cancelHandler2 = cancelHandler;
        if ((i2 & 4) != 0) {
            function1 = completedContinuation.f18841c;
        }
        Function1 function12 = function1;
        if ((i2 & 8) != 0) {
            obj2 = completedContinuation.f18842d;
        }
        Object obj4 = obj2;
        if ((i2 & 16) != 0) {
            th = completedContinuation.f18843e;
        }
        return completedContinuation.a(obj, cancelHandler2, function12, obj4, th);
    }

    public final CompletedContinuation a(Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th) {
        return new CompletedContinuation(obj, cancelHandler, function1, obj2, th);
    }

    public final boolean c() {
        return this.f18843e != null;
    }

    public final void d(CancellableContinuationImpl cancellableContinuationImpl, Throwable th) {
        CancelHandler cancelHandler = this.f18840b;
        if (cancelHandler != null) {
            cancellableContinuationImpl.l(cancelHandler, th);
        }
        Function1 function1 = this.f18841c;
        if (function1 != null) {
            cancellableContinuationImpl.o(function1, th);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompletedContinuation)) {
            return false;
        }
        CompletedContinuation completedContinuation = (CompletedContinuation) obj;
        return Intrinsics.a(this.f18839a, completedContinuation.f18839a) && Intrinsics.a(this.f18840b, completedContinuation.f18840b) && Intrinsics.a(this.f18841c, completedContinuation.f18841c) && Intrinsics.a(this.f18842d, completedContinuation.f18842d) && Intrinsics.a(this.f18843e, completedContinuation.f18843e);
    }

    public int hashCode() {
        Object obj = this.f18839a;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        CancelHandler cancelHandler = this.f18840b;
        int hashCode2 = (hashCode + (cancelHandler == null ? 0 : cancelHandler.hashCode())) * 31;
        Function1 function1 = this.f18841c;
        int hashCode3 = (hashCode2 + (function1 == null ? 0 : function1.hashCode())) * 31;
        Object obj2 = this.f18842d;
        int hashCode4 = (hashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.f18843e;
        return hashCode4 + (th != null ? th.hashCode() : 0);
    }

    public String toString() {
        return "CompletedContinuation(result=" + this.f18839a + ", cancelHandler=" + this.f18840b + ", onCancellation=" + this.f18841c + ", idempotentResume=" + this.f18842d + ", cancelCause=" + this.f18843e + ')';
    }

    public /* synthetic */ CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i2 & 2) != 0 ? null : cancelHandler, (i2 & 4) != 0 ? null : function1, (i2 & 8) != 0 ? null : obj2, (i2 & 16) != 0 ? null : th);
    }
}
