package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class UnsafeLazyImpl<T> implements Lazy<T>, Serializable {

    @Nullable
    private Object _value;

    @Nullable
    private Function0<? extends T> initializer;

    public UnsafeLazyImpl(@NotNull Function0<? extends T> initializer) {
        Intrinsics.e(initializer, "initializer");
        this.initializer = initializer;
        this._value = UNINITIALIZED_VALUE.f18282a;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    public boolean a() {
        return this._value != UNINITIALIZED_VALUE.f18282a;
    }

    @Override // kotlin.Lazy
    public Object getValue() {
        if (this._value == UNINITIALIZED_VALUE.f18282a) {
            Function0<? extends T> function0 = this.initializer;
            Intrinsics.b(function0);
            this._value = function0.a();
            this.initializer = null;
        }
        return this._value;
    }

    public String toString() {
        return a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
