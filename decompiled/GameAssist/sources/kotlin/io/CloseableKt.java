package kotlin.io;

import java.io.Closeable;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.JvmName;

@Metadata
@JvmName
/* loaded from: classes2.dex */
public final class CloseableKt {
    public static final void a(Closeable closeable, Throwable th) {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                ExceptionsKt__ExceptionsKt.a(th, th2);
            }
        }
    }
}
