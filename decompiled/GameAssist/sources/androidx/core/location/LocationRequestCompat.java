package androidx.core.location;

import android.location.LocationRequest;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.util.TimeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class LocationRequestCompat {

    /* renamed from: a, reason: collision with root package name */
    final int f3048a;

    /* renamed from: b, reason: collision with root package name */
    final long f3049b;

    /* renamed from: c, reason: collision with root package name */
    final long f3050c;

    /* renamed from: d, reason: collision with root package name */
    final long f3051d;

    /* renamed from: e, reason: collision with root package name */
    final int f3052e;

    /* renamed from: f, reason: collision with root package name */
    final float f3053f;

    /* renamed from: g, reason: collision with root package name */
    final long f3054g;

    private static class Api19Impl {
    }

    @RequiresApi
    private static class Api31Impl {
        @DoNotInline
        public static LocationRequest a(LocationRequestCompat locationRequestCompat) {
            return new LocationRequest.Builder(locationRequestCompat.b()).setQuality(locationRequestCompat.g()).setMinUpdateIntervalMillis(locationRequestCompat.f()).setDurationMillis(locationRequestCompat.a()).setMaxUpdates(locationRequestCompat.d()).setMinUpdateDistanceMeters(locationRequestCompat.e()).setMaxUpdateDelayMillis(locationRequestCompat.c()).build();
        }
    }

    public static final class Builder {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Quality {
    }

    public long a() {
        return this.f3051d;
    }

    public long b() {
        return this.f3049b;
    }

    public long c() {
        return this.f3054g;
    }

    public int d() {
        return this.f3052e;
    }

    public float e() {
        return this.f3053f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationRequestCompat)) {
            return false;
        }
        LocationRequestCompat locationRequestCompat = (LocationRequestCompat) obj;
        return this.f3048a == locationRequestCompat.f3048a && this.f3049b == locationRequestCompat.f3049b && this.f3050c == locationRequestCompat.f3050c && this.f3051d == locationRequestCompat.f3051d && this.f3052e == locationRequestCompat.f3052e && Float.compare(locationRequestCompat.f3053f, this.f3053f) == 0 && this.f3054g == locationRequestCompat.f3054g;
    }

    public long f() {
        long j2 = this.f3050c;
        return j2 == -1 ? this.f3049b : j2;
    }

    public int g() {
        return this.f3048a;
    }

    public LocationRequest h() {
        return Api31Impl.a(this);
    }

    public int hashCode() {
        int i2 = this.f3048a * 31;
        long j2 = this.f3049b;
        int i3 = (i2 + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.f3050c;
        return i3 + ((int) (j3 ^ (j3 >>> 32)));
    }

    public LocationRequest i(String str) {
        return h();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request[");
        if (this.f3049b != Long.MAX_VALUE) {
            sb.append("@");
            TimeUtils.e(this.f3049b, sb);
            int i2 = this.f3048a;
            if (i2 == 100) {
                sb.append(" HIGH_ACCURACY");
            } else if (i2 == 102) {
                sb.append(" BALANCED");
            } else if (i2 == 104) {
                sb.append(" LOW_POWER");
            }
        } else {
            sb.append("PASSIVE");
        }
        if (this.f3051d != Long.MAX_VALUE) {
            sb.append(", duration=");
            TimeUtils.e(this.f3051d, sb);
        }
        if (this.f3052e != Integer.MAX_VALUE) {
            sb.append(", maxUpdates=");
            sb.append(this.f3052e);
        }
        long j2 = this.f3050c;
        if (j2 != -1 && j2 < this.f3049b) {
            sb.append(", minUpdateInterval=");
            TimeUtils.e(this.f3050c, sb);
        }
        if (this.f3053f > 0.0d) {
            sb.append(", minUpdateDistance=");
            sb.append(this.f3053f);
        }
        if (this.f3054g / 2 > this.f3049b) {
            sb.append(", maxUpdateDelay=");
            TimeUtils.e(this.f3054g, sb);
        }
        sb.append(']');
        return sb.toString();
    }
}
