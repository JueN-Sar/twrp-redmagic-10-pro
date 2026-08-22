package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$9 implements Iterable<Character>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ char[] f18330c;

    @Override // java.lang.Iterable
    public Iterator<Character> iterator() {
        return ArrayIteratorsKt.c(this.f18330c);
    }
}
