package kotlin;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
final class SafePublicationLazyImpl<T> implements Lazy<T>, Serializable {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final AtomicReferenceFieldUpdater<SafePublicationLazyImpl<?>, Object> valueUpdater = AtomicReferenceFieldUpdater.newUpdater(SafePublicationLazyImpl.class, Object.class, "_value");

    @Nullable
    private volatile Object _value;

    /* renamed from: final, reason: not valid java name */
    @NotNull
    private final Object f0final;

    @Nullable
    private volatile Function0<? extends T> initializer;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SafePublicationLazyImpl(@NotNull Function0<? extends T> initializer) {
        Intrinsics.e(initializer, "initializer");
        this.initializer = initializer;
        UNINITIALIZED_VALUE uninitialized_value = UNINITIALIZED_VALUE.f18282a;
        this._value = uninitialized_value;
        this.f0final = uninitialized_value;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    public boolean a() {
        return this._value != UNINITIALIZED_VALUE.f18282a;
    }

    @Override // kotlin.Lazy
    public Object getValue() {
        Object obj = this._value;
        UNINITIALIZED_VALUE uninitialized_value = UNINITIALIZED_VALUE.f18282a;
        if (obj != uninitialized_value) {
            return obj;
        }
        Function0<? extends T> function0 = this.initializer;
        if (function0 != null) {
            Object a2 = function0.a();
            if (valueUpdater.compareAndSet(this, uninitialized_value, a2)) {
                this.initializer = null;
                return a2;
            }
        }
        return this._value;
    }

    public String toString() {
        return a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
