package kotlin.enums;

import java.io.Serializable;
import java.lang.Enum;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin
@Metadata
@ExperimentalStdlibApi
/* loaded from: classes2.dex */
final class EnumEntriesList<T extends Enum<T>> extends AbstractList<T> implements EnumEntries<T>, Serializable {

    @Nullable
    private volatile T[] _entries;

    @NotNull
    private final Function0<T[]> entriesProvider;

    public EnumEntriesList(@NotNull Function0<T[]> entriesProvider) {
        Intrinsics.e(entriesProvider, "entriesProvider");
        this.entriesProvider = entriesProvider;
    }

    private final Enum[] g() {
        T[] tArr = this._entries;
        if (tArr != null) {
            return tArr;
        }
        T[] tArr2 = (T[]) ((Enum[]) this.entriesProvider.a());
        this._entries = tArr2;
        return tArr2;
    }

    private final Object writeReplace() {
        return new EnumEntriesSerializationProxy(g());
    }

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return g().length;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Enum) {
            return d((Enum) obj);
        }
        return false;
    }

    public boolean d(Enum element) {
        Object C;
        Intrinsics.e(element, "element");
        C = ArraysKt___ArraysKt.C(g(), element.ordinal());
        return ((Enum) C) == element;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public Enum get(int i2) {
        Enum[] g2 = g();
        AbstractList.Companion.b(i2, g2.length);
        return g2[i2];
    }

    public int h(Enum element) {
        Object C;
        Intrinsics.e(element, "element");
        int ordinal = element.ordinal();
        C = ArraysKt___ArraysKt.C(g(), ordinal);
        if (((Enum) C) == element) {
            return ordinal;
        }
        return -1;
    }

    public int i(Enum element) {
        Intrinsics.e(element, "element");
        return indexOf(element);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Enum) {
            return h((Enum) obj);
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Enum) {
            return i((Enum) obj);
        }
        return -1;
    }
}
