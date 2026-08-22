package cn.nubia.plugin.timer;

import android.os.Handler;
import android.os.SystemClock;

/* loaded from: classes.dex */
public class TimerCount {

    /* renamed from: b, reason: collision with root package name */
    private TimerCountCallback f8717b;

    /* renamed from: a, reason: collision with root package name */
    private Handler f8716a = new Handler();

    /* renamed from: c, reason: collision with root package name */
    private long f8718c = 0;

    /* renamed from: d, reason: collision with root package name */
    private int f8719d = 0;

    /* renamed from: e, reason: collision with root package name */
    private int f8720e = 0;

    /* renamed from: f, reason: collision with root package name */
    private Runnable f8721f = new Runnable() { // from class: cn.nubia.plugin.timer.TimerCount.1
        @Override // java.lang.Runnable
        public void run() {
            TimerCount.this.d();
        }
    };

    public interface TimerCountCallback {
        void a(int i2);

        void b(int i2);
    }

    public TimerCount(TimerCountCallback timerCountCallback) {
        this.f8717b = timerCountCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        int elapsedRealtime = (int) ((SystemClock.elapsedRealtime() - this.f8718c) / 1000);
        this.f8720e = elapsedRealtime;
        if (elapsedRealtime >= this.f8719d) {
            this.f8717b.b(elapsedRealtime);
            this.f8717b.a(this.f8720e);
        } else {
            this.f8716a.postDelayed(this.f8721f, 1000L);
            this.f8717b.b(this.f8720e);
        }
    }

    public void b(int i2) {
        this.f8719d = i2;
        this.f8718c = SystemClock.elapsedRealtime();
        this.f8716a.postDelayed(this.f8721f, 5L);
    }

    public void c() {
        this.f8716a.removeCallbacks(this.f8721f);
    }
}
