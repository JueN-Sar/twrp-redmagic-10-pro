package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

@Metadata
/* loaded from: classes2.dex */
public enum CoroutineStart {
    DEFAULT,
    LAZY,
    ATOMIC,
    UNDISPATCHED;

    @Metadata
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f18857a;

        static {
            int[] iArr = new int[CoroutineStart.values().length];
            iArr[CoroutineStart.DEFAULT.ordinal()] = 1;
            iArr[CoroutineStart.ATOMIC.ordinal()] = 2;
            iArr[CoroutineStart.UNDISPATCHED.ordinal()] = 3;
            iArr[CoroutineStart.LAZY.ordinal()] = 4;
            f18857a = iArr;
        }
    }

    public final void d(Function2 function2, Object obj, Continuation continuation) {
        int i2 = WhenMappings.f18857a[ordinal()];
        if (i2 == 1) {
            CancellableKt.e(function2, obj, continuation, null, 4, null);
            return;
        }
        if (i2 == 2) {
            ContinuationKt.a(function2, obj, continuation);
        } else if (i2 == 3) {
            UndispatchedKt.a(function2, obj, continuation);
        } else if (i2 != 4) {
            throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean e() {
        return this == LAZY;
    }
}
