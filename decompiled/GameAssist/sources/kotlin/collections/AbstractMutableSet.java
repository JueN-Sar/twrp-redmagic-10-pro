package kotlin.collections;

import java.util.Set;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.markers.KMutableSet;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public abstract class AbstractMutableSet<E> extends java.util.AbstractSet<E> implements Set<E>, KMutableSet {
    protected AbstractMutableSet() {
    }

    public abstract int b();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return b();
    }
}
