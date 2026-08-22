package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$5 implements Iterable<Long>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ long[] f18326c;

    @Override // java.lang.Iterable
    public Iterator<Long> iterator() {
        return ArrayIteratorsKt.g(this.f18326c);
    }
}
