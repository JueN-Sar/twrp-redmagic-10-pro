package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$2 implements Iterable<Byte>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ byte[] f18323c;

    @Override // java.lang.Iterable
    public Iterator<Byte> iterator() {
        return ArrayIteratorsKt.b(this.f18323c);
    }
}
