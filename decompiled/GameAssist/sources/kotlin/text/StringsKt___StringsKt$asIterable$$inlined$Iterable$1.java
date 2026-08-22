package kotlin.text;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class StringsKt___StringsKt$asIterable$$inlined$Iterable$1 implements Iterable<Character>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ CharSequence f18788c;

    @Override // java.lang.Iterable
    public Iterator<Character> iterator() {
        return StringsKt__StringsKt.x(this.f18788c);
    }
}
