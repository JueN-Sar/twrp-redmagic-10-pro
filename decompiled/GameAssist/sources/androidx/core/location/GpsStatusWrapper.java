package androidx.core.location;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.util.Iterator;

@RestrictTo
/* loaded from: classes.dex */
class GpsStatusWrapper extends GnssStatusCompat {

    /* renamed from: a, reason: collision with root package name */
    private final GpsStatus f3013a;

    /* renamed from: b, reason: collision with root package name */
    private int f3014b;

    /* renamed from: c, reason: collision with root package name */
    private Iterator f3015c;

    /* renamed from: d, reason: collision with root package name */
    private int f3016d;

    /* renamed from: e, reason: collision with root package name */
    private GpsSatellite f3017e;

    GpsStatusWrapper(GpsStatus gpsStatus) {
        GpsStatus gpsStatus2 = (GpsStatus) Preconditions.h(gpsStatus);
        this.f3013a = gpsStatus2;
        this.f3014b = -1;
        this.f3015c = gpsStatus2.getSatellites().iterator();
        this.f3016d = -1;
        this.f3017e = null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GpsStatusWrapper) {
            return this.f3013a.equals(((GpsStatusWrapper) obj).f3013a);
        }
        return false;
    }

    public int hashCode() {
        return this.f3013a.hashCode();
    }
}
