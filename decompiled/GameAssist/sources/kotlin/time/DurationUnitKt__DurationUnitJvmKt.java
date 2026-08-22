package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
class DurationUnitKt__DurationUnitJvmKt {

    @Metadata
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f18808a;

        static {
            int[] iArr = new int[TimeUnit.values().length];
            try {
                iArr[TimeUnit.NANOSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TimeUnit.MICROSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TimeUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TimeUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[TimeUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[TimeUnit.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            f18808a = iArr;
        }
    }

    public static final double a(double d2, DurationUnit sourceUnit, DurationUnit targetUnit) {
        Intrinsics.e(sourceUnit, "sourceUnit");
        Intrinsics.e(targetUnit, "targetUnit");
        long convert = targetUnit.d().convert(1L, sourceUnit.d());
        return convert > 0 ? d2 * convert : d2 / sourceUnit.d().convert(1L, targetUnit.d());
    }

    public static final long b(long j2, DurationUnit sourceUnit, DurationUnit targetUnit) {
        Intrinsics.e(sourceUnit, "sourceUnit");
        Intrinsics.e(targetUnit, "targetUnit");
        return targetUnit.d().convert(j2, sourceUnit.d());
    }

    public static final long c(long j2, DurationUnit sourceUnit, DurationUnit targetUnit) {
        Intrinsics.e(sourceUnit, "sourceUnit");
        Intrinsics.e(targetUnit, "targetUnit");
        return targetUnit.d().convert(j2, sourceUnit.d());
    }
}
