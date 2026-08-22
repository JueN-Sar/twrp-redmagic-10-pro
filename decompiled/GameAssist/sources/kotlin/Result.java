package kotlin;

import java.io.Serializable;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin
@Metadata
@JvmInline
/* loaded from: classes2.dex */
public final class Result<T> implements Serializable {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @Nullable
    private final Object value;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    public static final class Failure implements Serializable {

        @JvmField
        @NotNull
        public final Throwable exception;

        public Failure(@NotNull Throwable exception) {
            Intrinsics.e(exception, "exception");
            this.exception = exception;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Failure) && Intrinsics.a(this.exception, ((Failure) obj).exception);
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        public String toString() {
            return "Failure(" + this.exception + ')';
        }
    }

    @PublishedApi
    private /* synthetic */ Result(Object obj) {
        this.value = obj;
    }

    public static final /* synthetic */ Result a(Object obj) {
        return new Result(obj);
    }

    public static Object b(Object obj) {
        return obj;
    }

    public static boolean c(Object obj, Object obj2) {
        return (obj2 instanceof Result) && Intrinsics.a(obj, ((Result) obj2).i());
    }

    public static final Throwable d(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).exception;
        }
        return null;
    }

    public static int e(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static final boolean f(Object obj) {
        return obj instanceof Failure;
    }

    public static final boolean g(Object obj) {
        return !(obj instanceof Failure);
    }

    public static String h(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).toString();
        }
        return "Success(" + obj + ')';
    }

    public boolean equals(Object obj) {
        return c(this.value, obj);
    }

    public int hashCode() {
        return e(this.value);
    }

    public final /* synthetic */ Object i() {
        return this.value;
    }

    public String toString() {
        return h(this.value);
    }
}
