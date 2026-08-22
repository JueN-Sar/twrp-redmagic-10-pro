package com.google.android.gms.internal.mlkit_vision_common;

import android.os.SystemClock;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class zzlx implements Closeable {

    /* renamed from: n, reason: collision with root package name */
    private static final Map f12593n = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private final String f12594c;

    /* renamed from: h, reason: collision with root package name */
    private int f12595h;

    /* renamed from: i, reason: collision with root package name */
    private double f12596i;

    /* renamed from: j, reason: collision with root package name */
    private long f12597j;

    /* renamed from: k, reason: collision with root package name */
    private long f12598k;

    /* renamed from: l, reason: collision with root package name */
    private long f12599l;

    /* renamed from: m, reason: collision with root package name */
    private long f12600m;

    private zzlx(String str) {
        this.f12599l = 2147483647L;
        this.f12600m = -2147483648L;
        this.f12594c = str;
    }

    private final void a() {
        this.f12595h = 0;
        this.f12596i = 0.0d;
        this.f12597j = 0L;
        this.f12599l = 2147483647L;
        this.f12600m = -2147483648L;
    }

    public static zzlx h(String str) {
        zzlv zzlvVar;
        zzmw.a();
        if (!zzmw.b()) {
            zzlvVar = zzlv.f12592o;
            return zzlvVar;
        }
        Map map = f12593n;
        if (map.get("detectorTaskWithResource#run") == null) {
            map.put("detectorTaskWithResource#run", new zzlx("detectorTaskWithResource#run"));
        }
        return (zzlx) map.get("detectorTaskWithResource#run");
    }

    public zzlx c() {
        this.f12597j = SystemClock.elapsedRealtimeNanos() / 1000;
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long j2 = this.f12597j;
        if (j2 == 0) {
            throw new IllegalStateException("Did you forget to call start()?");
        }
        e(j2);
    }

    public void d(long j2) {
        long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos() / 1000;
        long j3 = this.f12598k;
        if (j3 != 0 && elapsedRealtimeNanos - j3 >= 1000000) {
            a();
        }
        this.f12598k = elapsedRealtimeNanos;
        this.f12595h++;
        this.f12596i += j2;
        this.f12599l = Math.min(this.f12599l, j2);
        this.f12600m = Math.max(this.f12600m, j2);
        if (this.f12595h % 50 == 0) {
            String.format(Locale.US, "[%s] cur=%dus, counts=%d, min=%dus, max=%dus, avg=%dus", this.f12594c, Long.valueOf(j2), Integer.valueOf(this.f12595h), Long.valueOf(this.f12599l), Long.valueOf(this.f12600m), Integer.valueOf((int) (this.f12596i / this.f12595h)));
            zzmw.a();
        }
        if (this.f12595h % 500 == 0) {
            a();
        }
    }

    public void e(long j2) {
        d((SystemClock.elapsedRealtimeNanos() / 1000) - j2);
    }
}
