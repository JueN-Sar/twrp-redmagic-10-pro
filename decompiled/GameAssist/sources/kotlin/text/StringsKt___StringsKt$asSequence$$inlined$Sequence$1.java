package kotlin.text;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class StringsKt___StringsKt$asSequence$$inlined$Sequence$1 implements Sequence<Character> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ CharSequence f18789a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return StringsKt__StringsKt.x(this.f18789a);
    }
}
