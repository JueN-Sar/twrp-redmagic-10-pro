package androidx.core.text.util;

import android.icu.number.NumberFormatter;
import android.icu.number.UnlocalizedNumberFormatter;
import android.icu.text.DateFormat;
import android.icu.text.DateTimePatternGenerator;
import android.icu.util.Calendar;
import android.icu.util.MeasureUnit;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

@RequiresApi
/* loaded from: classes.dex */
public final class LocalePreferences {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f3249a = {"BS", "BZ", "KY", "PR", "PW", "US"};

    /* renamed from: androidx.core.text.util.LocalePreferences$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3250a;

        static {
            int[] iArr = new int[DateFormat.HourCycle.values().length];
            f3250a = iArr;
            try {
                iArr[DateFormat.HourCycle.HOUR_CYCLE_11.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3250a[DateFormat.HourCycle.HOUR_CYCLE_12.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3250a[DateFormat.HourCycle.HOUR_CYCLE_23.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3250a[DateFormat.HourCycle.HOUR_CYCLE_24.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @RequiresApi
    private static class Api24Impl {
        @DoNotInline
        static String a(@NonNull Locale locale) {
            return Calendar.getInstance(locale).getType();
        }

        @DoNotInline
        static Locale b() {
            return Locale.getDefault(Locale.Category.FORMAT);
        }
    }

    @RequiresApi
    private static class Api33Impl {
        @DoNotInline
        static String a(@NonNull Locale locale) {
            return b(DateTimePatternGenerator.getInstance(locale).getDefaultHourCycle());
        }

        private static String b(DateFormat.HourCycle hourCycle) {
            int i2 = AnonymousClass1.f3250a[hourCycle.ordinal()];
            return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "" : "h24" : "h23" : "h12" : "h11";
        }

        @DoNotInline
        static String c(@NonNull Locale locale) {
            String identifier = ((UnlocalizedNumberFormatter) ((UnlocalizedNumberFormatter) NumberFormatter.with().usage("weather")).unit(MeasureUnit.CELSIUS)).locale(locale).format(1L).getOutputUnit().getIdentifier();
            return identifier.startsWith("fahrenhe") ? "fahrenhe" : identifier;
        }
    }

    public static class CalendarType {

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface CalendarTypes {
        }
    }

    public static class FirstDayOfWeek {

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface Days {
        }
    }

    public static class HourCycle {

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface HourCycleTypes {
        }
    }

    public static class TemperatureUnit {

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface TemperatureUnits {
        }
    }
}
