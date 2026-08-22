package kotlinx.coroutines.scheduling;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class NanoTimeSource extends SchedulerTimeSource {

    /* renamed from: a, reason: collision with root package name */
    public static final NanoTimeSource f19454a = new NanoTimeSource();

    private NanoTimeSource() {
    }

    @Override // kotlinx.coroutines.scheduling.SchedulerTimeSource
    public long a() {
        return System.nanoTime();
    }
}
