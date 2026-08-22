package kotlin.reflect;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.reflect.KMutableProperty;

@Metadata
/* loaded from: classes2.dex */
public interface KMutableProperty2<D, E, V> extends KProperty2<D, E, V>, KMutableProperty<V> {

    @Metadata
    public interface Setter<D, E, V> extends KMutableProperty.Setter<V>, Function3<D, E, V, Unit> {
    }
}
