package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.selects.SelectClause2;

@Metadata
/* loaded from: classes2.dex */
public interface SendChannel<E> {

    @Metadata
    public static final class DefaultImpls {
        public static /* synthetic */ boolean a(SendChannel sendChannel, Throwable th, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: close");
            }
            if ((i2 & 1) != 0) {
                th = null;
            }
            return sendChannel.J(th);
        }
    }

    boolean J(Throwable th);

    Object M(Object obj, Continuation continuation);

    boolean N();

    SelectClause2 o();

    void u(Function1 function1);
}
