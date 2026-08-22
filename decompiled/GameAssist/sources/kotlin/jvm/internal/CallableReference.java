package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.SinceKotlin;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KCallable;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes2.dex */
public abstract class CallableReference implements KCallable, Serializable {

    @SinceKotlin
    public static final Object NO_RECEIVER = NoReceiver.INSTANCE;

    @SinceKotlin
    private final boolean isTopLevel;

    @SinceKotlin
    private final String name;

    @SinceKotlin
    private final Class owner;

    @SinceKotlin
    protected final Object receiver;
    private transient KCallable reflected;

    @SinceKotlin
    private final String signature;

    @SinceKotlin
    private static class NoReceiver implements Serializable {
        private static final NoReceiver INSTANCE = new NoReceiver();

        private NoReceiver() {
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    public CallableReference() {
        this(NO_RECEIVER);
    }

    public KCallable g() {
        KCallable kCallable = this.reflected;
        if (kCallable != null) {
            return kCallable;
        }
        KCallable h2 = h();
        this.reflected = h2;
        return h2;
    }

    protected abstract KCallable h();

    public Object i() {
        return this.receiver;
    }

    public String k() {
        return this.name;
    }

    public KDeclarationContainer m() {
        Class cls = this.owner;
        if (cls == null) {
            return null;
        }
        return this.isTopLevel ? Reflection.c(cls) : Reflection.b(cls);
    }

    protected KCallable n() {
        KCallable g2 = g();
        if (g2 != this) {
            return g2;
        }
        throw new KotlinReflectionNotSupportedError();
    }

    public String o() {
        return this.signature;
    }

    @SinceKotlin
    protected CallableReference(Object obj) {
        this(obj, null, null, null, false);
    }

    @SinceKotlin
    protected CallableReference(Object obj, Class cls, String str, String str2, boolean z) {
        this.receiver = obj;
        this.owner = cls;
        this.name = str;
        this.signature = str2;
        this.isTopLevel = z;
    }
}
