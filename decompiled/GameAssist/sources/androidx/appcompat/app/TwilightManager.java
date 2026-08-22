package androidx.appcompat.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.PermissionChecker;
import java.util.Calendar;

/* loaded from: classes.dex */
class TwilightManager {

    /* renamed from: d, reason: collision with root package name */
    private static TwilightManager f319d;

    /* renamed from: a, reason: collision with root package name */
    private final Context f320a;

    /* renamed from: b, reason: collision with root package name */
    private final LocationManager f321b;

    /* renamed from: c, reason: collision with root package name */
    private final TwilightState f322c = new TwilightState();

    private static class TwilightState {

        /* renamed from: a, reason: collision with root package name */
        boolean f323a;

        /* renamed from: b, reason: collision with root package name */
        long f324b;

        TwilightState() {
        }
    }

    @VisibleForTesting
    TwilightManager(@NonNull Context context, @NonNull LocationManager locationManager) {
        this.f320a = context;
        this.f321b = locationManager;
    }

    static TwilightManager a(Context context) {
        if (f319d == null) {
            Context applicationContext = context.getApplicationContext();
            f319d = new TwilightManager(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f319d;
    }

    private Location b() {
        Location c2 = PermissionChecker.b(this.f320a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location c3 = PermissionChecker.b(this.f320a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (c3 == null || c2 == null) ? c3 != null ? c3 : c2 : c3.getTime() > c2.getTime() ? c3 : c2;
    }

    private Location c(String str) {
        try {
            if (this.f321b.isProviderEnabled(str)) {
                return this.f321b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e2) {
            Log.d("TwilightManager", "Failed to get last known location", e2);
            return null;
        }
    }

    private boolean e() {
        return this.f322c.f324b > System.currentTimeMillis();
    }

    private void f(Location location) {
        long j2;
        TwilightState twilightState = this.f322c;
        long currentTimeMillis = System.currentTimeMillis();
        TwilightCalculator b2 = TwilightCalculator.b();
        b2.a(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        b2.a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = b2.f318c == 1;
        long j3 = b2.f317b;
        long j4 = b2.f316a;
        b2.a(currentTimeMillis + 86400000, location.getLatitude(), location.getLongitude());
        long j5 = b2.f317b;
        if (j3 == -1 || j4 == -1) {
            j2 = currentTimeMillis + 43200000;
        } else {
            if (currentTimeMillis > j4) {
                j3 = j5;
            } else if (currentTimeMillis > j3) {
                j3 = j4;
            }
            j2 = j3 + 60000;
        }
        twilightState.f323a = z;
        twilightState.f324b = j2;
    }

    @VisibleForTesting
    static void setInstance(TwilightManager twilightManager) {
        f319d = twilightManager;
    }

    boolean d() {
        TwilightState twilightState = this.f322c;
        if (e()) {
            return twilightState.f323a;
        }
        Location b2 = b();
        if (b2 != null) {
            f(b2);
            return twilightState.f323a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i2 = Calendar.getInstance().get(11);
        return i2 < 6 || i2 >= 22;
    }
}
