package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class GeneratorSequence<T> implements Sequence<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Function0 f18698a;

    /* renamed from: b, reason: collision with root package name */
    private final Function1 f18699b;

    public GeneratorSequence(Function0 getInitialValue, Function1 getNextValue) {
        Intrinsics.e(getInitialValue, "getInitialValue");
        Intrinsics.e(getNextValue, "getNextValue");
        this.f18698a = getInitialValue;
        this.f18699b = getNextValue;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new GeneratorSequence$iterator$1(this);
    }
}
