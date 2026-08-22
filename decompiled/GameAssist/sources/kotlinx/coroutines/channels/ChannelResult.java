package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.InternalCoroutinesApi;

@Metadata
@JvmInline
/* loaded from: classes2.dex */
public final class ChannelResult<T> {

    /* renamed from: b, reason: collision with root package name */
    public static final Companion f19005b = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private static final Failed f19006c = new Failed();

    /* renamed from: a, reason: collision with root package name */
    private final Object f19007a;

    @Metadata
    public static final class Closed extends Failed {

        /* renamed from: a, reason: collision with root package name */
        public final Throwable f19008a;

        public Closed(Throwable th) {
            this.f19008a = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Closed) && Intrinsics.a(this.f19008a, ((Closed) obj).f19008a);
        }

        public int hashCode() {
            Throwable th = this.f19008a;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @Override // kotlinx.coroutines.channels.ChannelResult.Failed
        public String toString() {
            return "Closed(" + this.f19008a + ')';
        }
    }

    @InternalCoroutinesApi
    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final Object a(Throwable th) {
            return ChannelResult.c(new Closed(th));
        }

        public final Object b() {
            return ChannelResult.c(ChannelResult.f19006c);
        }

        public final Object c(Object obj) {
            return ChannelResult.c(obj);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    public static class Failed {
        public String toString() {
            return "Failed";
        }
    }

    private /* synthetic */ ChannelResult(Object obj) {
        this.f19007a = obj;
    }

    public static final /* synthetic */ ChannelResult b(Object obj) {
        return new ChannelResult(obj);
    }

    public static Object c(Object obj) {
        return obj;
    }

    public static boolean d(Object obj, Object obj2) {
        return (obj2 instanceof ChannelResult) && Intrinsics.a(obj, ((ChannelResult) obj2).k());
    }

    public static final Throwable e(Object obj) {
        Closed closed = obj instanceof Closed ? (Closed) obj : null;
        if (closed != null) {
            return closed.f19008a;
        }
        return null;
    }

    public static final Object f(Object obj) {
        if (obj instanceof Failed) {
            return null;
        }
        return obj;
    }

    public static final Object g(Object obj) {
        Throwable th;
        if (!(obj instanceof Failed)) {
            return obj;
        }
        if ((obj instanceof Closed) && (th = ((Closed) obj).f19008a) != null) {
            throw th;
        }
        throw new IllegalStateException(("Trying to call 'getOrThrow' on a failed channel result: " + obj).toString());
    }

    public static int h(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static final boolean i(Object obj) {
        return obj instanceof Closed;
    }

    public static String j(Object obj) {
        if (obj instanceof Closed) {
            return ((Closed) obj).toString();
        }
        return "Value(" + obj + ')';
    }

    public boolean equals(Object obj) {
        return d(this.f19007a, obj);
    }

    public int hashCode() {
        return h(this.f19007a);
    }

    public final /* synthetic */ Object k() {
        return this.f19007a;
    }

    public String toString() {
        return j(this.f19007a);
    }
}
