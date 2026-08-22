package kotlin.reflect;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public interface KProperty<V> extends KCallable<V> {

    @Metadata
    public interface Accessor<V> {
    }

    @Metadata
    public static final class DefaultImpls {
    }

    @Metadata
    public interface Getter<V> extends Accessor<V>, KFunction<V> {
    }
}
