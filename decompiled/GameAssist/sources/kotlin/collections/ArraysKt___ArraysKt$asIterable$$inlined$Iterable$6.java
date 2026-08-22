package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$6 implements Iterable<Float>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ float[] f18327c;

    @Override // java.lang.Iterable
    public Iterator<Float> iterator() {
        return ArrayIteratorsKt.e(this.f18327c);
    }
}
