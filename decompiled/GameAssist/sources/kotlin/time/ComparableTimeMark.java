package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;

@SinceKotlin
@Metadata
@ExperimentalTime
/* loaded from: classes2.dex */
public interface ComparableTimeMark extends TimeMark, Comparable<ComparableTimeMark> {

    @Metadata
    public static final class DefaultImpls {
        public static int a(ComparableTimeMark comparableTimeMark, ComparableTimeMark other) {
            Intrinsics.e(other, "other");
            return Duration.h(comparableTimeMark.g(other), Duration.f18801h.a());
        }
    }

    long g(ComparableTimeMark comparableTimeMark);
}
