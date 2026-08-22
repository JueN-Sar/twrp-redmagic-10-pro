package kotlin.reflect;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KMutableProperty;

@Metadata
/* loaded from: classes2.dex */
public interface KMutableProperty1<T, V> extends KProperty1<T, V>, KMutableProperty<V> {

    @Metadata
    public interface Setter<T, V> extends KMutableProperty.Setter<V>, Function2<T, V, Unit> {
    }
}
