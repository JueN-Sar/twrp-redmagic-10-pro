package kotlin.collections;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.markers.KMutableCollection;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public abstract class AbstractMutableCollection<E> extends java.util.AbstractCollection<E> implements Collection<E>, KMutableCollection {
    protected AbstractMutableCollection() {
    }

    public abstract int b();

    @Override // java.util.AbstractCollection, java.util.Collection
    public final /* bridge */ int size() {
        return b();
    }
}
