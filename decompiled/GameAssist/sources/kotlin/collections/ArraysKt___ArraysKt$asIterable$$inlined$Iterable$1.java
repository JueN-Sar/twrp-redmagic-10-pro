package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$1 implements Iterable<Object>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Object[] f18322c;

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return ArrayIteratorKt.a(this.f18322c);
    }
}
