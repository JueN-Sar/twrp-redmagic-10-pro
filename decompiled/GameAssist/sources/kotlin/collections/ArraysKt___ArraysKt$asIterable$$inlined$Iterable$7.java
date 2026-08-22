package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$7 implements Iterable<Double>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ double[] f18328c;

    @Override // java.lang.Iterable
    public Iterator<Double> iterator() {
        return ArrayIteratorsKt.d(this.f18328c);
    }
}
