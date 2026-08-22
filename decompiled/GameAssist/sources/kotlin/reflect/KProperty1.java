package kotlin.reflect;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;

@Metadata
/* loaded from: classes2.dex */
public interface KProperty1<T, V> extends KProperty<V>, Function1<T, V> {

    @Metadata
    public interface Getter<T, V> extends KProperty.Getter<V>, Function1<T, V> {
    }

    Getter b();

    Object get(Object obj);
}
