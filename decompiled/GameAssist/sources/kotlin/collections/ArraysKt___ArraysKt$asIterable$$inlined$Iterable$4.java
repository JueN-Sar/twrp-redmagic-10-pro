package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$4 implements Iterable<Integer>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ int[] f18325c;

    @Override // java.lang.Iterable
    public Iterator<Integer> iterator() {
        return ArrayIteratorsKt.f(this.f18325c);
    }
}
