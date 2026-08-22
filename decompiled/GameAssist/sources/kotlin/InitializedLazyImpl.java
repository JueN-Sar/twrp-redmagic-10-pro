package kotlin;

import java.io.Serializable;

@Metadata
/* loaded from: classes2.dex */
public final class InitializedLazyImpl<T> implements Lazy<T>, Serializable {
    private final T value;

    public InitializedLazyImpl(T t) {
        this.value = t;
    }

    @Override // kotlin.Lazy
    public Object getValue() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(getValue());
    }
}
