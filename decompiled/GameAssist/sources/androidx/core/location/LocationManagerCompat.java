package androidx.core.location;

import android.annotation.SuppressLint;
import android.location.GnssMeasurementsEvent;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.collection.SimpleArrayMap;
import androidx.core.location.GnssStatusCompat;
import androidx.core.location.LocationManagerCompat;
import androidx.core.util.Consumer;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes.dex */
public final class LocationManagerCompat {

    /* renamed from: a, reason: collision with root package name */
    static final WeakHashMap f3022a = new WeakHashMap();

    static class Api19Impl {

        /* renamed from: a, reason: collision with root package name */
        private static Class f3023a;

        /* renamed from: b, reason: collision with root package name */
        private static Method f3024b;

        @DoNotInline
        @SuppressLint({"BanUncheckedReflection"})
        static boolean a(LocationManager locationManager, String str, LocationRequestCompat locationRequestCompat, LocationListenerCompat locationListenerCompat, Looper looper) {
            try {
                if (f3023a == null) {
                    f3023a = Class.forName("android.location.LocationRequest");
                }
                if (f3024b == null) {
                    Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", f3023a, LocationListener.class, Looper.class);
                    f3024b = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                LocationRequest i2 = locationRequestCompat.i(str);
                if (i2 == null) {
                    return false;
                }
                f3024b.invoke(locationManager, i2, locationListenerCompat, looper);
                return true;
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
                return false;
            }
        }

        @RequiresPermission
        @DoNotInline
        @SuppressLint({"BanUncheckedReflection"})
        static boolean b(LocationManager locationManager, String str, LocationRequestCompat locationRequestCompat, LocationListenerTransport locationListenerTransport) {
            try {
                if (f3023a == null) {
                    f3023a = Class.forName("android.location.LocationRequest");
                }
                if (f3024b == null) {
                    Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", f3023a, LocationListener.class, Looper.class);
                    f3024b = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                LocationRequest i2 = locationRequestCompat.i(str);
                if (i2 == null) {
                    return false;
                }
                synchronized (LocationManagerCompat.f3022a) {
                    f3024b.invoke(locationManager, i2, locationListenerTransport, Looper.getMainLooper());
                    LocationManagerCompat.a(locationManager, locationListenerTransport);
                }
                return true;
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
                return false;
            }
        }
    }

    @RequiresApi
    static class Api24Impl {
        @RequiresPermission
        @DoNotInline
        static boolean a(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback) {
            return locationManager.registerGnssMeasurementsCallback(callback);
        }

        @RequiresPermission
        @DoNotInline
        static boolean b(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback, @NonNull Handler handler) {
            return locationManager.registerGnssMeasurementsCallback(callback, handler);
        }

        @RequiresPermission
        @DoNotInline
        static boolean c(LocationManager locationManager, Handler handler, Executor executor, GnssStatusCompat.Callback callback) {
            Preconditions.a(handler != null);
            SimpleArrayMap simpleArrayMap = GnssListenersHolder.f3033a;
            synchronized (simpleArrayMap) {
                try {
                    PreRGnssStatusTransport preRGnssStatusTransport = (PreRGnssStatusTransport) simpleArrayMap.get(callback);
                    if (preRGnssStatusTransport == null) {
                        preRGnssStatusTransport = new PreRGnssStatusTransport(callback);
                    } else {
                        preRGnssStatusTransport.j();
                    }
                    preRGnssStatusTransport.i(executor);
                    if (!locationManager.registerGnssStatusCallback(preRGnssStatusTransport, handler)) {
                        return false;
                    }
                    simpleArrayMap.put(callback, preRGnssStatusTransport);
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @DoNotInline
        static void d(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback) {
            locationManager.unregisterGnssMeasurementsCallback(callback);
        }

        @DoNotInline
        static void e(LocationManager locationManager, Object obj) {
            if (obj instanceof PreRGnssStatusTransport) {
                ((PreRGnssStatusTransport) obj).j();
            }
            locationManager.unregisterGnssStatusCallback((GnssStatus.Callback) obj);
        }
    }

    @RequiresApi
    private static class Api28Impl {
        @DoNotInline
        static String a(LocationManager locationManager) {
            return locationManager.getGnssHardwareModelName();
        }

        @DoNotInline
        static int b(LocationManager locationManager) {
            return locationManager.getGnssYearOfHardware();
        }

        @DoNotInline
        static boolean c(LocationManager locationManager) {
            return locationManager.isLocationEnabled();
        }
    }

    @RequiresApi
    private static class Api30Impl {

        /* renamed from: a, reason: collision with root package name */
        private static Class f3025a;

        /* renamed from: b, reason: collision with root package name */
        private static Method f3026b;

        @RequiresPermission
        @DoNotInline
        static void a(LocationManager locationManager, @NonNull String str, @Nullable CancellationSignal cancellationSignal, @NonNull Executor executor, @NonNull final Consumer<Location> consumer) {
            Objects.requireNonNull(consumer);
            locationManager.getCurrentLocation(str, cancellationSignal, executor, new java.util.function.Consumer() { // from class: androidx.core.location.a
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Consumer.this.accept((Location) obj);
                }
            });
        }

        @RequiresPermission
        @DoNotInline
        public static boolean b(LocationManager locationManager, Handler handler, Executor executor, GnssStatusCompat.Callback callback) {
            SimpleArrayMap simpleArrayMap = GnssListenersHolder.f3033a;
            synchronized (simpleArrayMap) {
                try {
                    GnssStatusTransport gnssStatusTransport = (GnssStatusTransport) simpleArrayMap.get(callback);
                    if (gnssStatusTransport == null) {
                        gnssStatusTransport = new GnssStatusTransport(callback);
                    }
                    if (!locationManager.registerGnssStatusCallback(executor, gnssStatusTransport)) {
                        return false;
                    }
                    simpleArrayMap.put(callback, gnssStatusTransport);
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @DoNotInline
        public static boolean c(LocationManager locationManager, String str, LocationRequestCompat locationRequestCompat, Executor executor, LocationListenerCompat locationListenerCompat) {
            try {
                if (f3025a == null) {
                    f3025a = Class.forName("android.location.LocationRequest");
                }
                if (f3026b == null) {
                    Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", f3025a, Executor.class, LocationListener.class);
                    f3026b = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                LocationRequest i2 = locationRequestCompat.i(str);
                if (i2 == null) {
                    return false;
                }
                f3026b.invoke(locationManager, i2, executor, locationListenerCompat);
                return true;
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
                return false;
            }
        }
    }

    @RequiresApi
    private static class Api31Impl {
        @DoNotInline
        static boolean a(LocationManager locationManager, @NonNull String str) {
            return locationManager.hasProvider(str);
        }

        @RequiresPermission
        @DoNotInline
        static boolean b(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssMeasurementsEvent.Callback callback) {
            return locationManager.registerGnssMeasurementsCallback(executor, callback);
        }

        @RequiresPermission
        @DoNotInline
        static void c(LocationManager locationManager, @NonNull String str, @NonNull LocationRequest locationRequest, @NonNull Executor executor, @NonNull LocationListener locationListener) {
            locationManager.requestLocationUpdates(str, locationRequest, executor, locationListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class CancellableLocationListener implements LocationListener {

        /* renamed from: a, reason: collision with root package name */
        private final LocationManager f3027a;

        /* renamed from: b, reason: collision with root package name */
        private final Executor f3028b;

        /* renamed from: c, reason: collision with root package name */
        private final Handler f3029c;

        /* renamed from: d, reason: collision with root package name */
        private Consumer f3030d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f3031e;

        /* renamed from: f, reason: collision with root package name */
        Runnable f3032f;

        private void b() {
            this.f3030d = null;
            this.f3027a.removeUpdates(this);
            Runnable runnable = this.f3032f;
            if (runnable != null) {
                this.f3029c.removeCallbacks(runnable);
                this.f3032f = null;
            }
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(final Location location) {
            synchronized (this) {
                try {
                    if (this.f3031e) {
                        return;
                    }
                    this.f3031e = true;
                    final Consumer consumer = this.f3030d;
                    this.f3028b.execute(new Runnable() { // from class: androidx.core.location.b
                        @Override // java.lang.Runnable
                        public final void run() {
                            Consumer.this.accept(location);
                        }
                    });
                    b();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            onLocationChanged((Location) null);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i2, Bundle bundle) {
        }
    }

    private static class GnssListenersHolder {

        /* renamed from: a, reason: collision with root package name */
        static final SimpleArrayMap f3033a = new SimpleArrayMap();

        /* renamed from: b, reason: collision with root package name */
        static final SimpleArrayMap f3034b = new SimpleArrayMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi
    static class GnssMeasurementsTransport extends GnssMeasurementsEvent.Callback {

        /* renamed from: a, reason: collision with root package name */
        final GnssMeasurementsEvent.Callback f3035a;

        /* renamed from: b, reason: collision with root package name */
        volatile Executor f3036b;

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(Executor executor, GnssMeasurementsEvent gnssMeasurementsEvent) {
            if (this.f3036b != executor) {
                return;
            }
            this.f3035a.onGnssMeasurementsReceived(gnssMeasurementsEvent);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(Executor executor, int i2) {
            if (this.f3036b != executor) {
                return;
            }
            this.f3035a.onStatusChanged(i2);
        }

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onGnssMeasurementsReceived(final GnssMeasurementsEvent gnssMeasurementsEvent) {
            final Executor executor = this.f3036b;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.c
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.GnssMeasurementsTransport.this.c(executor, gnssMeasurementsEvent);
                }
            });
        }

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onStatusChanged(final int i2) {
            final Executor executor = this.f3036b;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.d
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.GnssMeasurementsTransport.this.d(executor, i2);
                }
            });
        }
    }

    @RequiresApi
    private static class GnssStatusTransport extends GnssStatus.Callback {

        /* renamed from: a, reason: collision with root package name */
        final GnssStatusCompat.Callback f3037a;

        GnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.b(callback != null, "invalid null callback");
            this.f3037a = callback;
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int i2) {
            this.f3037a.a(i2);
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            this.f3037a.b(GnssStatusCompat.a(gnssStatus));
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            this.f3037a.c();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            this.f3037a.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class GpsStatusTransport implements GpsStatus.Listener {

        /* renamed from: a, reason: collision with root package name */
        private final LocationManager f3038a;

        /* renamed from: b, reason: collision with root package name */
        final GnssStatusCompat.Callback f3039b;

        /* renamed from: c, reason: collision with root package name */
        volatile Executor f3040c;

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(Executor executor) {
            if (this.f3040c != executor) {
                return;
            }
            this.f3039b.c();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(Executor executor) {
            if (this.f3040c != executor) {
                return;
            }
            this.f3039b.d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void g(Executor executor, int i2) {
            if (this.f3040c != executor) {
                return;
            }
            this.f3039b.a(i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h(Executor executor, GnssStatusCompat gnssStatusCompat) {
            if (this.f3040c != executor) {
                return;
            }
            this.f3039b.b(gnssStatusCompat);
        }

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int i2) {
            GpsStatus gpsStatus;
            final Executor executor = this.f3040c;
            if (executor == null) {
                return;
            }
            if (i2 == 1) {
                executor.execute(new Runnable() { // from class: androidx.core.location.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocationManagerCompat.GpsStatusTransport.this.e(executor);
                    }
                });
                return;
            }
            if (i2 == 2) {
                executor.execute(new Runnable() { // from class: androidx.core.location.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocationManagerCompat.GpsStatusTransport.this.f(executor);
                    }
                });
                return;
            }
            if (i2 != 3) {
                if (i2 == 4 && (gpsStatus = this.f3038a.getGpsStatus(null)) != null) {
                    final GnssStatusCompat b2 = GnssStatusCompat.b(gpsStatus);
                    executor.execute(new Runnable() { // from class: androidx.core.location.h
                        @Override // java.lang.Runnable
                        public final void run() {
                            LocationManagerCompat.GpsStatusTransport.this.h(executor, b2);
                        }
                    });
                    return;
                }
                return;
            }
            GpsStatus gpsStatus2 = this.f3038a.getGpsStatus(null);
            if (gpsStatus2 != null) {
                final int timeToFirstFix = gpsStatus2.getTimeToFirstFix();
                executor.execute(new Runnable() { // from class: androidx.core.location.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocationManagerCompat.GpsStatusTransport.this.g(executor, timeToFirstFix);
                    }
                });
            }
        }
    }

    private static final class InlineHandlerExecutor implements Executor {

        /* renamed from: c, reason: collision with root package name */
        private final Handler f3041c;

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (Looper.myLooper() == this.f3041c.getLooper()) {
                runnable.run();
            } else {
                if (this.f3041c.post((Runnable) Preconditions.h(runnable))) {
                    return;
                }
                throw new RejectedExecutionException(this.f3041c + " is shutting down");
            }
        }
    }

    private static class LocationListenerKey {

        /* renamed from: a, reason: collision with root package name */
        final String f3042a;

        /* renamed from: b, reason: collision with root package name */
        final LocationListenerCompat f3043b;

        public boolean equals(Object obj) {
            if (!(obj instanceof LocationListenerKey)) {
                return false;
            }
            LocationListenerKey locationListenerKey = (LocationListenerKey) obj;
            return this.f3042a.equals(locationListenerKey.f3042a) && this.f3043b.equals(locationListenerKey.f3043b);
        }

        public int hashCode() {
            return ObjectsCompat.b(this.f3042a, this.f3043b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi
    static class PreRGnssStatusTransport extends GnssStatus.Callback {

        /* renamed from: a, reason: collision with root package name */
        final GnssStatusCompat.Callback f3046a;

        /* renamed from: b, reason: collision with root package name */
        volatile Executor f3047b;

        PreRGnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.b(callback != null, "invalid null callback");
            this.f3046a = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(Executor executor, int i2) {
            if (this.f3047b != executor) {
                return;
            }
            this.f3046a.a(i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(Executor executor, GnssStatus gnssStatus) {
            if (this.f3047b != executor) {
                return;
            }
            this.f3046a.b(GnssStatusCompat.a(gnssStatus));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void g(Executor executor) {
            if (this.f3047b != executor) {
                return;
            }
            this.f3046a.c();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h(Executor executor) {
            if (this.f3047b != executor) {
                return;
            }
            this.f3046a.d();
        }

        public void i(Executor executor) {
            Preconditions.b(executor != null, "invalid null executor");
            Preconditions.j(this.f3047b == null);
            this.f3047b = executor;
        }

        public void j() {
            this.f3047b = null;
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(final int i2) {
            final Executor executor = this.f3047b;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.o
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.this.e(executor, i2);
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(final GnssStatus gnssStatus) {
            final Executor executor = this.f3047b;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.p
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.this.f(executor, gnssStatus);
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            final Executor executor = this.f3047b;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.r
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.this.g(executor);
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            final Executor executor = this.f3047b;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.q
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.this.h(executor);
                }
            });
        }
    }

    static void a(LocationManager locationManager, LocationListenerTransport locationListenerTransport) {
        WeakReference weakReference = (WeakReference) f3022a.put(locationListenerTransport.g(), new WeakReference(locationListenerTransport));
        LocationListenerTransport locationListenerTransport2 = weakReference != null ? (LocationListenerTransport) weakReference.get() : null;
        if (locationListenerTransport2 != null) {
            locationListenerTransport2.n();
            locationManager.removeUpdates(locationListenerTransport2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class LocationListenerTransport implements LocationListener {

        /* renamed from: a, reason: collision with root package name */
        volatile LocationListenerKey f3044a;

        /* renamed from: b, reason: collision with root package name */
        final Executor f3045b;

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h(int i2) {
            LocationListenerKey locationListenerKey = this.f3044a;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.f3043b.onFlushComplete(i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void i(Location location) {
            LocationListenerKey locationListenerKey = this.f3044a;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.f3043b.onLocationChanged(location);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void j(List list) {
            LocationListenerKey locationListenerKey = this.f3044a;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.f3043b.onLocationChanged(list);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void k(String str) {
            LocationListenerKey locationListenerKey = this.f3044a;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.f3043b.onProviderDisabled(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void l(String str) {
            LocationListenerKey locationListenerKey = this.f3044a;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.f3043b.onProviderEnabled(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void m(String str, int i2, Bundle bundle) {
            LocationListenerKey locationListenerKey = this.f3044a;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.f3043b.onStatusChanged(str, i2, bundle);
        }

        public LocationListenerKey g() {
            return (LocationListenerKey) ObjectsCompat.c(this.f3044a);
        }

        public void n() {
            this.f3044a = null;
        }

        @Override // android.location.LocationListener
        public void onFlushComplete(final int i2) {
            if (this.f3044a == null) {
                return;
            }
            this.f3045b.execute(new Runnable() { // from class: androidx.core.location.m
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.h(i2);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(final Location location) {
            if (this.f3044a == null) {
                return;
            }
            this.f3045b.execute(new Runnable() { // from class: androidx.core.location.l
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.i(location);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(final String str) {
            if (this.f3044a == null) {
                return;
            }
            this.f3045b.execute(new Runnable() { // from class: androidx.core.location.j
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.k(str);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(final String str) {
            if (this.f3044a == null) {
                return;
            }
            this.f3045b.execute(new Runnable() { // from class: androidx.core.location.i
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.l(str);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(final String str, final int i2, final Bundle bundle) {
            if (this.f3044a == null) {
                return;
            }
            this.f3045b.execute(new Runnable() { // from class: androidx.core.location.n
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.m(str, i2, bundle);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(final List list) {
            if (this.f3044a == null) {
                return;
            }
            this.f3045b.execute(new Runnable() { // from class: androidx.core.location.k
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.j(list);
                }
            });
        }
    }
}
