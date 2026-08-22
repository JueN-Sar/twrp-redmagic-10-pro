package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$8 implements Iterable<Boolean>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ boolean[] f18329c;

    @Override // java.lang.Iterable
    public Iterator<Boolean> iterator() {
        return ArrayIteratorsKt.a(this.f18329c);
    }
}
