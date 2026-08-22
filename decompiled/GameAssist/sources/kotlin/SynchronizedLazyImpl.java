package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
final class SynchronizedLazyImpl<T> implements Lazy<T>, Serializable {

    @Nullable
    private volatile Object _value;

    @Nullable
    private Function0<? extends T> initializer;

    @NotNull
    private final Object lock;

    public SynchronizedLazyImpl(@NotNull Function0<? extends T> initializer, @Nullable Object obj) {
        Intrinsics.e(initializer, "initializer");
        this.initializer = initializer;
        this._value = UNINITIALIZED_VALUE.f18282a;
        this.lock = obj == null ? this : obj;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    public boolean a() {
        return this._value != UNINITIALIZED_VALUE.f18282a;
    }

    @Override // kotlin.Lazy
    public Object getValue() {
        Object obj;
        Object obj2 = this._value;
        UNINITIALIZED_VALUE uninitialized_value = UNINITIALIZED_VALUE.f18282a;
        if (obj2 != uninitialized_value) {
            return obj2;
        }
        synchronized (this.lock) {
            obj = this._value;
            if (obj == uninitialized_value) {
                Function0<? extends T> function0 = this.initializer;
                Intrinsics.b(function0);
                obj = function0.a();
                this._value = obj;
                this.initializer = null;
            }
        }
        return obj;
    }

    public String toString() {
        return a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    public /* synthetic */ SynchronizedLazyImpl(Function0 function0, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, (i2 & 2) != 0 ? null : obj);
    }
}
