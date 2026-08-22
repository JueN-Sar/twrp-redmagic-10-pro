package androidx.core.location;

import android.location.GnssStatus;
import android.location.GpsStatus;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class GnssStatusCompat {

    public static abstract class Callback {
        public void a(int i2) {
        }

        public void b(GnssStatusCompat gnssStatusCompat) {
        }

        public void c() {
        }

        public void d() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ConstellationType {
    }

    GnssStatusCompat() {
    }

    public static GnssStatusCompat a(GnssStatus gnssStatus) {
        return new GnssStatusWrapper(gnssStatus);
    }

    public static GnssStatusCompat b(GpsStatus gpsStatus) {
        return new GpsStatusWrapper(gpsStatus);
    }
}
