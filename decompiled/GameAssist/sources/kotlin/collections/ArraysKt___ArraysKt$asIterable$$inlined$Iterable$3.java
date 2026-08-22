package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$3 implements Iterable<Short>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ short[] f18324c;

    @Override // java.lang.Iterable
    public Iterator<Short> iterator() {
        return ArrayIteratorsKt.h(this.f18324c);
    }
}
