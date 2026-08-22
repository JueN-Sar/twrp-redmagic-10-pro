package androidx.activity.contextaware;

import android.content.Context;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class ContextAwareHelper {

    /* renamed from: a, reason: collision with root package name */
    private final Set f86a = new CopyOnWriteArraySet();

    /* renamed from: b, reason: collision with root package name */
    private volatile Context f87b;

    public final void a(OnContextAvailableListener listener) {
        Intrinsics.e(listener, "listener");
        Context context = this.f87b;
        if (context != null) {
            listener.a(context);
        }
        this.f86a.add(listener);
    }

    public final void b() {
        this.f87b = null;
    }

    public final void c(Context context) {
        Intrinsics.e(context, "context");
        this.f87b = context;
        Iterator it = this.f86a.iterator();
        while (it.hasNext()) {
            ((OnContextAvailableListener) it.next()).a(context);
        }
    }

    public final void d(OnContextAvailableListener listener) {
        Intrinsics.e(listener, "listener");
        this.f86a.remove(listener);
    }
}
