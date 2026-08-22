package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public abstract class ActivityResultContract<I, O> {

    @Metadata
    public static final class SynchronousResult<T> {

        /* renamed from: a, reason: collision with root package name */
        private final Object f128a;

        public SynchronousResult(Object obj) {
            this.f128a = obj;
        }

        public final Object a() {
            return this.f128a;
        }
    }

    public abstract Intent a(Context context, Object obj);

    public SynchronousResult b(Context context, Object obj) {
        Intrinsics.e(context, "context");
        return null;
    }

    public abstract Object c(int i2, Intent intent);
}
