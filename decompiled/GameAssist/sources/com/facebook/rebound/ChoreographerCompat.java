package com.facebook.rebound;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;

/* loaded from: classes.dex */
public class ChoreographerCompat {

    /* renamed from: c, reason: collision with root package name */
    private static final boolean f10005c = true;

    /* renamed from: d, reason: collision with root package name */
    private static ChoreographerCompat f10006d = new ChoreographerCompat();

    /* renamed from: a, reason: collision with root package name */
    private Handler f10007a;

    /* renamed from: b, reason: collision with root package name */
    private Choreographer f10008b;

    public static abstract class FrameCallback {

        /* renamed from: a, reason: collision with root package name */
        private Runnable f10009a;

        /* renamed from: b, reason: collision with root package name */
        private Choreographer.FrameCallback f10010b;

        public abstract void a(long j2);

        Choreographer.FrameCallback b() {
            if (this.f10010b == null) {
                this.f10010b = new Choreographer.FrameCallback() { // from class: com.facebook.rebound.ChoreographerCompat.FrameCallback.1
                    @Override // android.view.Choreographer.FrameCallback
                    public void doFrame(long j2) {
                        FrameCallback.this.a(j2);
                    }
                };
            }
            return this.f10010b;
        }

        Runnable c() {
            if (this.f10009a == null) {
                this.f10009a = new Runnable() { // from class: com.facebook.rebound.ChoreographerCompat.FrameCallback.2
                    @Override // java.lang.Runnable
                    public void run() {
                        FrameCallback.this.a(System.nanoTime());
                    }
                };
            }
            return this.f10009a;
        }
    }

    private ChoreographerCompat() {
        if (f10005c) {
            this.f10008b = b();
        } else {
            this.f10007a = new Handler(Looper.getMainLooper());
        }
    }

    private void a(Choreographer.FrameCallback frameCallback) {
        this.f10008b.postFrameCallback(frameCallback);
    }

    private Choreographer b() {
        return Choreographer.getInstance();
    }

    public void c(FrameCallback frameCallback) {
        if (f10005c) {
            a(frameCallback.b());
        } else {
            this.f10007a.postDelayed(frameCallback.c(), 0L);
        }
    }
}
