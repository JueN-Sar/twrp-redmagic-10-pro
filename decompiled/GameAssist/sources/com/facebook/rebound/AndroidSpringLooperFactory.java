package com.facebook.rebound;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Choreographer;

/* loaded from: classes.dex */
abstract class AndroidSpringLooperFactory {

    @TargetApi(16)
    private static class ChoreographerAndroidSpringLooper extends SpringLooper {

        /* renamed from: b, reason: collision with root package name */
        private final Choreographer f9982b;

        /* renamed from: c, reason: collision with root package name */
        private final Choreographer.FrameCallback f9983c = new Choreographer.FrameCallback() { // from class: com.facebook.rebound.AndroidSpringLooperFactory.ChoreographerAndroidSpringLooper.1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j2) {
                if (!ChoreographerAndroidSpringLooper.this.f9984d || ChoreographerAndroidSpringLooper.this.f10040a == null) {
                    return;
                }
                long uptimeMillis = SystemClock.uptimeMillis();
                ChoreographerAndroidSpringLooper.this.f10040a.f(uptimeMillis - r0.f9985e);
                ChoreographerAndroidSpringLooper.this.f9985e = uptimeMillis;
                ChoreographerAndroidSpringLooper.this.f9982b.postFrameCallback(ChoreographerAndroidSpringLooper.this.f9983c);
            }
        };

        /* renamed from: d, reason: collision with root package name */
        private boolean f9984d;

        /* renamed from: e, reason: collision with root package name */
        private long f9985e;

        public ChoreographerAndroidSpringLooper(Choreographer choreographer) {
            this.f9982b = choreographer;
        }

        public static ChoreographerAndroidSpringLooper i() {
            return new ChoreographerAndroidSpringLooper(Choreographer.getInstance());
        }

        @Override // com.facebook.rebound.SpringLooper
        public void b() {
            if (this.f9984d) {
                return;
            }
            this.f9984d = true;
            this.f9985e = SystemClock.uptimeMillis();
            this.f9982b.removeFrameCallback(this.f9983c);
            this.f9982b.postFrameCallback(this.f9983c);
        }

        @Override // com.facebook.rebound.SpringLooper
        public void c() {
            this.f9984d = false;
            this.f9982b.removeFrameCallback(this.f9983c);
        }
    }

    private static class LegacyAndroidSpringLooper extends SpringLooper {

        /* renamed from: b, reason: collision with root package name */
        private final Handler f9987b;

        /* renamed from: c, reason: collision with root package name */
        private final Runnable f9988c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f9989d;

        /* renamed from: e, reason: collision with root package name */
        private long f9990e;

        /* renamed from: com.facebook.rebound.AndroidSpringLooperFactory$LegacyAndroidSpringLooper$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ LegacyAndroidSpringLooper f9991c;

            @Override // java.lang.Runnable
            public void run() {
                if (!this.f9991c.f9989d || this.f9991c.f10040a == null) {
                    return;
                }
                long uptimeMillis = SystemClock.uptimeMillis();
                this.f9991c.f10040a.f(uptimeMillis - r2.f9990e);
                this.f9991c.f9990e = uptimeMillis;
                this.f9991c.f9987b.post(this.f9991c.f9988c);
            }
        }

        @Override // com.facebook.rebound.SpringLooper
        public void b() {
            if (this.f9989d) {
                return;
            }
            this.f9989d = true;
            this.f9990e = SystemClock.uptimeMillis();
            this.f9987b.removeCallbacks(this.f9988c);
            this.f9987b.post(this.f9988c);
        }

        @Override // com.facebook.rebound.SpringLooper
        public void c() {
            this.f9989d = false;
            this.f9987b.removeCallbacks(this.f9988c);
        }
    }

    public static SpringLooper a() {
        return ChoreographerAndroidSpringLooper.i();
    }
}
