package androidx.core.location;

import android.location.Location;
import android.os.Bundle;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public final class LocationCompat {

    /* renamed from: a, reason: collision with root package name */
    private static Field f3018a;

    /* renamed from: b, reason: collision with root package name */
    private static Integer f3019b;

    /* renamed from: c, reason: collision with root package name */
    private static Integer f3020c;

    /* renamed from: d, reason: collision with root package name */
    private static Integer f3021d;

    @RequiresApi
    private static class Api26Impl {
        @DoNotInline
        static float a(Location location) {
            return location.getBearingAccuracyDegrees();
        }

        @DoNotInline
        static float b(Location location) {
            return location.getSpeedAccuracyMetersPerSecond();
        }

        @DoNotInline
        static float c(Location location) {
            return location.getVerticalAccuracyMeters();
        }

        @DoNotInline
        static boolean d(Location location) {
            return location.hasBearingAccuracy();
        }

        @DoNotInline
        static boolean e(Location location) {
            return location.hasSpeedAccuracy();
        }

        @DoNotInline
        static boolean f(Location location) {
            return location.hasVerticalAccuracy();
        }

        @DoNotInline
        static void g(Location location) {
            try {
                LocationCompat.a().setByte(location, (byte) (LocationCompat.a().getByte(location) & (~LocationCompat.b())));
            } catch (IllegalAccessException e2) {
                IllegalAccessError illegalAccessError = new IllegalAccessError();
                illegalAccessError.initCause(e2);
                throw illegalAccessError;
            } catch (NoSuchFieldException e3) {
                NoSuchFieldError noSuchFieldError = new NoSuchFieldError();
                noSuchFieldError.initCause(e3);
                throw noSuchFieldError;
            }
        }

        @DoNotInline
        static void h(Location location) {
            try {
                LocationCompat.a().setByte(location, (byte) (LocationCompat.a().getByte(location) & (~LocationCompat.c())));
            } catch (IllegalAccessException e2) {
                IllegalAccessError illegalAccessError = new IllegalAccessError();
                illegalAccessError.initCause(e2);
                throw illegalAccessError;
            } catch (NoSuchFieldException e3) {
                NoSuchFieldError noSuchFieldError = new NoSuchFieldError();
                noSuchFieldError.initCause(e3);
                throw noSuchFieldError;
            }
        }

        @DoNotInline
        static void i(Location location) {
            try {
                LocationCompat.a().setByte(location, (byte) (LocationCompat.a().getByte(location) & (~LocationCompat.d())));
            } catch (IllegalAccessException | NoSuchFieldException e2) {
                IllegalAccessError illegalAccessError = new IllegalAccessError();
                illegalAccessError.initCause(e2);
                throw illegalAccessError;
            }
        }

        @DoNotInline
        static void j(Location location, float f2) {
            location.setBearingAccuracyDegrees(f2);
        }

        @DoNotInline
        static void k(Location location, float f2) {
            location.setSpeedAccuracyMetersPerSecond(f2);
        }

        @DoNotInline
        static void l(Location location, float f2) {
            location.setVerticalAccuracyMeters(f2);
        }
    }

    @RequiresApi
    private static class Api28Impl {
        @DoNotInline
        static void a(Location location) {
            if (location.hasBearingAccuracy()) {
                String provider = location.getProvider();
                long time = location.getTime();
                long elapsedRealtimeNanos = location.getElapsedRealtimeNanos();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                boolean hasAltitude = location.hasAltitude();
                double altitude = location.getAltitude();
                boolean hasSpeed = location.hasSpeed();
                float speed = location.getSpeed();
                boolean hasBearing = location.hasBearing();
                float bearing = location.getBearing();
                boolean hasAccuracy = location.hasAccuracy();
                float accuracy = location.getAccuracy();
                boolean hasVerticalAccuracy = location.hasVerticalAccuracy();
                float verticalAccuracyMeters = location.getVerticalAccuracyMeters();
                boolean hasSpeedAccuracy = location.hasSpeedAccuracy();
                float speedAccuracyMetersPerSecond = location.getSpeedAccuracyMetersPerSecond();
                Bundle extras = location.getExtras();
                location.reset();
                location.setProvider(provider);
                location.setTime(time);
                location.setElapsedRealtimeNanos(elapsedRealtimeNanos);
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                if (hasAltitude) {
                    location.setAltitude(altitude);
                }
                if (hasSpeed) {
                    location.setSpeed(speed);
                }
                if (hasBearing) {
                    location.setBearing(bearing);
                }
                if (hasAccuracy) {
                    location.setAccuracy(accuracy);
                }
                if (hasVerticalAccuracy) {
                    location.setVerticalAccuracyMeters(verticalAccuracyMeters);
                }
                if (hasSpeedAccuracy) {
                    location.setBearingAccuracyDegrees(speedAccuracyMetersPerSecond);
                }
                if (extras != null) {
                    location.setExtras(extras);
                }
            }
        }

        @DoNotInline
        static void b(Location location) {
            if (location.hasSpeedAccuracy()) {
                String provider = location.getProvider();
                long time = location.getTime();
                long elapsedRealtimeNanos = location.getElapsedRealtimeNanos();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                boolean hasAltitude = location.hasAltitude();
                double altitude = location.getAltitude();
                boolean hasSpeed = location.hasSpeed();
                float speed = location.getSpeed();
                boolean hasBearing = location.hasBearing();
                float bearing = location.getBearing();
                boolean hasAccuracy = location.hasAccuracy();
                float accuracy = location.getAccuracy();
                boolean hasVerticalAccuracy = location.hasVerticalAccuracy();
                float verticalAccuracyMeters = location.getVerticalAccuracyMeters();
                boolean hasBearingAccuracy = location.hasBearingAccuracy();
                float bearingAccuracyDegrees = location.getBearingAccuracyDegrees();
                Bundle extras = location.getExtras();
                location.reset();
                location.setProvider(provider);
                location.setTime(time);
                location.setElapsedRealtimeNanos(elapsedRealtimeNanos);
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                if (hasAltitude) {
                    location.setAltitude(altitude);
                }
                if (hasSpeed) {
                    location.setSpeed(speed);
                }
                if (hasBearing) {
                    location.setBearing(bearing);
                }
                if (hasAccuracy) {
                    location.setAccuracy(accuracy);
                }
                if (hasVerticalAccuracy) {
                    location.setVerticalAccuracyMeters(verticalAccuracyMeters);
                }
                if (hasBearingAccuracy) {
                    location.setBearingAccuracyDegrees(bearingAccuracyDegrees);
                }
                if (extras != null) {
                    location.setExtras(extras);
                }
            }
        }

        @DoNotInline
        static void c(Location location) {
            if (location.hasVerticalAccuracy()) {
                String provider = location.getProvider();
                long time = location.getTime();
                long elapsedRealtimeNanos = location.getElapsedRealtimeNanos();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                boolean hasAltitude = location.hasAltitude();
                double altitude = location.getAltitude();
                boolean hasSpeed = location.hasSpeed();
                float speed = location.getSpeed();
                boolean hasBearing = location.hasBearing();
                float bearing = location.getBearing();
                boolean hasAccuracy = location.hasAccuracy();
                float accuracy = location.getAccuracy();
                boolean hasSpeedAccuracy = location.hasSpeedAccuracy();
                float speedAccuracyMetersPerSecond = location.getSpeedAccuracyMetersPerSecond();
                boolean hasBearingAccuracy = location.hasBearingAccuracy();
                float bearingAccuracyDegrees = location.getBearingAccuracyDegrees();
                Bundle extras = location.getExtras();
                location.reset();
                location.setProvider(provider);
                location.setTime(time);
                location.setElapsedRealtimeNanos(elapsedRealtimeNanos);
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                if (hasAltitude) {
                    location.setAltitude(altitude);
                }
                if (hasSpeed) {
                    location.setSpeed(speed);
                }
                if (hasBearing) {
                    location.setBearing(bearing);
                }
                if (hasAccuracy) {
                    location.setAccuracy(accuracy);
                }
                if (hasSpeedAccuracy) {
                    location.setSpeedAccuracyMetersPerSecond(speedAccuracyMetersPerSecond);
                }
                if (hasBearingAccuracy) {
                    location.setBearingAccuracyDegrees(bearingAccuracyDegrees);
                }
                if (extras != null) {
                    location.setExtras(extras);
                }
            }
        }
    }

    @RequiresApi
    private static class Api29Impl {
        @DoNotInline
        static void a(Location location) {
            if (location.hasBearingAccuracy()) {
                double elapsedRealtimeUncertaintyNanos = location.getElapsedRealtimeUncertaintyNanos();
                Api28Impl.a(location);
                location.setElapsedRealtimeUncertaintyNanos(elapsedRealtimeUncertaintyNanos);
            }
        }

        @DoNotInline
        static void b(Location location) {
            if (location.hasSpeedAccuracy()) {
                double elapsedRealtimeUncertaintyNanos = location.getElapsedRealtimeUncertaintyNanos();
                Api28Impl.b(location);
                location.setElapsedRealtimeUncertaintyNanos(elapsedRealtimeUncertaintyNanos);
            }
        }

        @DoNotInline
        static void c(Location location) {
            if (location.hasVerticalAccuracy()) {
                double elapsedRealtimeUncertaintyNanos = location.getElapsedRealtimeUncertaintyNanos();
                Api28Impl.c(location);
                location.setElapsedRealtimeUncertaintyNanos(elapsedRealtimeUncertaintyNanos);
            }
        }
    }

    @RequiresApi
    private static class Api33Impl {
        @DoNotInline
        static void a(Location location) {
            location.removeBearingAccuracy();
        }

        @DoNotInline
        static void b(Location location) {
            location.removeSpeedAccuracy();
        }

        @DoNotInline
        static void c(Location location) {
            location.removeVerticalAccuracy();
        }
    }

    @RequiresApi
    private static class Api34Impl {
        @DoNotInline
        static float a(Location location) {
            return location.getMslAltitudeAccuracyMeters();
        }

        @DoNotInline
        static double b(Location location) {
            return location.getMslAltitudeMeters();
        }

        @DoNotInline
        static boolean c(Location location) {
            return location.hasMslAltitude();
        }

        @DoNotInline
        static boolean d(Location location) {
            return location.hasMslAltitudeAccuracy();
        }

        @DoNotInline
        static void e(Location location) {
            location.removeMslAltitude();
        }

        @DoNotInline
        static void f(Location location) {
            location.removeMslAltitudeAccuracy();
        }

        @DoNotInline
        static void g(Location location, float f2) {
            location.setMslAltitudeAccuracyMeters(f2);
        }

        @DoNotInline
        static void h(Location location, double d2) {
            location.setMslAltitudeMeters(d2);
        }
    }

    static Field a() {
        if (f3018a == null) {
            Field declaredField = Location.class.getDeclaredField("mFieldsMask");
            f3018a = declaredField;
            declaredField.setAccessible(true);
        }
        return f3018a;
    }

    static int b() {
        if (f3020c == null) {
            Field declaredField = Location.class.getDeclaredField("HAS_BEARING_ACCURACY_MASK");
            declaredField.setAccessible(true);
            f3020c = Integer.valueOf(declaredField.getInt(null));
        }
        return f3020c.intValue();
    }

    static int c() {
        if (f3019b == null) {
            Field declaredField = Location.class.getDeclaredField("HAS_SPEED_ACCURACY_MASK");
            declaredField.setAccessible(true);
            f3019b = Integer.valueOf(declaredField.getInt(null));
        }
        return f3019b.intValue();
    }

    static int d() {
        if (f3021d == null) {
            Field declaredField = Location.class.getDeclaredField("HAS_VERTICAL_ACCURACY_MASK");
            declaredField.setAccessible(true);
            f3021d = Integer.valueOf(declaredField.getInt(null));
        }
        return f3021d.intValue();
    }
}
