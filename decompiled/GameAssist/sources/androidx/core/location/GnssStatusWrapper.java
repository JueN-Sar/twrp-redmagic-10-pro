package androidx.core.location;

import android.location.GnssStatus;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
class GnssStatusWrapper extends GnssStatusCompat {

    /* renamed from: a, reason: collision with root package name */
    private final GnssStatus f3012a;

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static float a(GnssStatus gnssStatus, int i2) {
            return gnssStatus.getCarrierFrequencyHz(i2);
        }

        @DoNotInline
        static boolean b(GnssStatus gnssStatus, int i2) {
            return gnssStatus.hasCarrierFrequencyHz(i2);
        }
    }

    @RequiresApi
    static class Api30Impl {
        @DoNotInline
        static float a(GnssStatus gnssStatus, int i2) {
            return gnssStatus.getBasebandCn0DbHz(i2);
        }

        @DoNotInline
        static boolean b(GnssStatus gnssStatus, int i2) {
            return gnssStatus.hasBasebandCn0DbHz(i2);
        }
    }

    GnssStatusWrapper(Object obj) {
        this.f3012a = (GnssStatus) Preconditions.h((GnssStatus) obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GnssStatusWrapper) {
            return this.f3012a.equals(((GnssStatusWrapper) obj).f3012a);
        }
        return false;
    }

    public int hashCode() {
        return this.f3012a.hashCode();
    }
}
