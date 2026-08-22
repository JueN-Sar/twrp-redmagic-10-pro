package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import org.jetbrains.annotations.NotNull;

@SinceKotlin
@Metadata
@WasExperimental
/* loaded from: classes2.dex */
public enum DurationUnit {
    NANOSECONDS(TimeUnit.NANOSECONDS),
    MICROSECONDS(TimeUnit.MICROSECONDS),
    MILLISECONDS(TimeUnit.MILLISECONDS),
    SECONDS(TimeUnit.SECONDS),
    MINUTES(TimeUnit.MINUTES),
    HOURS(TimeUnit.HOURS),
    DAYS(TimeUnit.DAYS);


    @NotNull
    private final TimeUnit timeUnit;

    DurationUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public final TimeUnit d() {
        return this.timeUnit;
    }
}
