package kotlin.reflect;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KProperty;

@Metadata
/* loaded from: classes2.dex */
public interface KProperty2<D, E, V> extends KProperty<V>, Function2<D, E, V> {

    @Metadata
    public interface Getter<D, E, V> extends KProperty.Getter<V>, Function2<D, E, V> {
    }

    Object E(Object obj, Object obj2);

    Getter b();
}
