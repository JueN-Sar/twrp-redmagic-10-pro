package kotlinx.coroutines.selects;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class SeqNumber {

    /* renamed from: a, reason: collision with root package name */
    private static final /* synthetic */ AtomicLongFieldUpdater f19493a = AtomicLongFieldUpdater.newUpdater(SeqNumber.class, "number");

    @NotNull
    private volatile /* synthetic */ long number = 1;

    public final long a() {
        return f19493a.incrementAndGet(this);
    }
}
