package kotlin.io;

import java.io.BufferedReader;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.sequences.Sequence;

@Metadata
/* loaded from: classes2.dex */
final class LinesSequence implements Sequence<String> {

    /* renamed from: a, reason: collision with root package name */
    private final BufferedReader f18466a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new LinesSequence$iterator$1(this);
    }
}
