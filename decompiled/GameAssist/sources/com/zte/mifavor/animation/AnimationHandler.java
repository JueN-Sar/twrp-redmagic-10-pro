package com.zte.mifavor.animation;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.ArrayMap;
import android.view.Choreographer;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class AnimationHandler {

    /* renamed from: i, reason: collision with root package name */
    private static final ThreadLocal f17249i = new ThreadLocal();

    /* renamed from: a, reason: collision with root package name */
    final ArrayList f17250a;

    /* renamed from: b, reason: collision with root package name */
    private final AnimationCallbackDispatcher f17251b;

    /* renamed from: c, reason: collision with root package name */
    private Choreographer f17252c;

    /* renamed from: d, reason: collision with root package name */
    long f17253d;

    /* renamed from: e, reason: collision with root package name */
    private final ArrayMap f17254e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f17255f;

    /* renamed from: g, reason: collision with root package name */
    private final Runnable f17256g;

    /* renamed from: h, reason: collision with root package name */
    private FrameCallbackScheduler f17257h;

    private class AnimationCallbackDispatcher {
        void a() {
            try {
                Method declaredMethod = Choreographer.class.getDeclaredMethod("getFrameTimeNanos", null);
                declaredMethod.setAccessible(true);
                AnimationHandler animationHandler = AnimationHandler.this;
                animationHandler.f17253d = ((Long) declaredMethod.invoke(animationHandler.f17252c, null)).longValue();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            AnimationHandler animationHandler2 = AnimationHandler.this;
            animationHandler2.f(animationHandler2.f17253d);
            if (AnimationHandler.this.f17250a.size() > 0) {
                AnimationHandler.this.f17257h.a(AnimationHandler.this.f17256g);
            }
        }

        private AnimationCallbackDispatcher() {
        }
    }

    public interface AnimationFrameCallback {
        boolean doAnimationFrame(long j2);
    }

    static final class AsyncFrameCallbackScheduler implements FrameCallbackScheduler {

        /* renamed from: a, reason: collision with root package name */
        private final Choreographer f17261a = Choreographer.getInstance();

        /* renamed from: b, reason: collision with root package name */
        private final Looper f17262b = Looper.myLooper();

        AsyncFrameCallbackScheduler() {
        }

        @Override // com.zte.mifavor.animation.AnimationHandler.FrameCallbackScheduler
        public void a(final Runnable runnable) {
            this.f17261a.postFrameCallback(new Choreographer.FrameCallback(this) { // from class: com.zte.mifavor.animation.AnimationHandler.AsyncFrameCallbackScheduler.1
                @Override // android.view.Choreographer.FrameCallback
                public final void doFrame(long j2) {
                    runnable.run();
                }
            });
        }

        @Override // com.zte.mifavor.animation.AnimationHandler.FrameCallbackScheduler
        public boolean b() {
            return Thread.currentThread() == this.f17262b.getThread();
        }
    }

    public interface FrameCallbackScheduler {
        void a(Runnable runnable);

        boolean b();
    }

    public static class FrameCallbackScheduler14 implements FrameCallbackScheduler {

        /* renamed from: a, reason: collision with root package name */
        private final Handler f17264a;

        /* renamed from: b, reason: collision with root package name */
        private long f17265b;

        @Override // com.zte.mifavor.animation.AnimationHandler.FrameCallbackScheduler
        public void a(final Runnable runnable) {
            this.f17264a.postDelayed(new Runnable() { // from class: com.zte.mifavor.animation.AnimationHandler.FrameCallbackScheduler14.1
                @Override // java.lang.Runnable
                public final void run() {
                    FrameCallbackScheduler14.this.c(runnable);
                }
            }, Math.max(10 - (SystemClock.uptimeMillis() - this.f17265b), 0L));
        }

        @Override // com.zte.mifavor.animation.AnimationHandler.FrameCallbackScheduler
        public boolean b() {
            return Thread.currentThread() == this.f17264a.getLooper().getThread();
        }

        public void c(Runnable runnable) {
            this.f17265b = SystemClock.uptimeMillis();
            runnable.run();
        }
    }

    static final class FrameCallbackScheduler16 implements FrameCallbackScheduler {

        /* renamed from: a, reason: collision with root package name */
        private final Choreographer f17268a = Choreographer.getInstance();

        /* renamed from: b, reason: collision with root package name */
        private final Looper f17269b = Looper.myLooper();

        FrameCallbackScheduler16() {
        }

        @Override // com.zte.mifavor.animation.AnimationHandler.FrameCallbackScheduler
        public void a(final Runnable runnable) {
            this.f17268a.postFrameCallback(new Choreographer.FrameCallback(this) { // from class: com.zte.mifavor.animation.AnimationHandler.FrameCallbackScheduler16.1
                @Override // android.view.Choreographer.FrameCallback
                public final void doFrame(long j2) {
                    runnable.run();
                }
            });
        }

        @Override // com.zte.mifavor.animation.AnimationHandler.FrameCallbackScheduler
        public boolean b() {
            return Thread.currentThread() == this.f17269b.getThread();
        }
    }

    public AnimationHandler(FrameCallbackScheduler frameCallbackScheduler) {
        this.f17254e = new ArrayMap();
        this.f17250a = new ArrayList();
        this.f17251b = new AnimationCallbackDispatcher();
        this.f17256g = new Runnable() { // from class: com.zte.mifavor.animation.AnimationHandler.1
            @Override // java.lang.Runnable
            public final void run() {
                AnimationHandler.this.k();
            }
        };
        this.f17253d = 0L;
        this.f17255f = false;
        this.f17252c = Choreographer.getInstance();
        this.f17257h = frameCallbackScheduler;
    }

    private void e() {
        if (this.f17255f) {
            for (int size = this.f17250a.size() - 1; size >= 0; size--) {
                if (this.f17250a.get(size) == null) {
                    this.f17250a.remove(size);
                }
            }
            this.f17255f = false;
        }
    }

    public static AnimationHandler g() {
        ThreadLocal threadLocal = f17249i;
        if (threadLocal.get() == null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                threadLocal.set(new AnimationHandler(new FrameCallbackScheduler16()));
            } else {
                threadLocal.set(new AnimationHandler(new AsyncFrameCallbackScheduler(), Choreographer.getInstance()));
            }
        }
        return (AnimationHandler) threadLocal.get();
    }

    private boolean h(AnimationFrameCallback animationFrameCallback, long j2) {
        Long l2 = (Long) this.f17254e.get(animationFrameCallback);
        if (l2 == null) {
            return true;
        }
        if (l2.longValue() >= j2) {
            return false;
        }
        this.f17254e.remove(animationFrameCallback);
        return true;
    }

    public void d(AnimationFrameCallback animationFrameCallback, long j2) {
        if (this.f17250a.size() == 0) {
            this.f17257h.a(this.f17256g);
        }
        if (!this.f17250a.contains(animationFrameCallback)) {
            this.f17250a.add(animationFrameCallback);
        }
        if (j2 > 0) {
            this.f17254e.put(animationFrameCallback, Long.valueOf(SystemClock.uptimeMillis() + j2));
        }
    }

    void f(long j2) {
        long j3;
        try {
            Method declaredMethod = Choreographer.class.getDeclaredMethod("getFrameTimeNanos", null);
            declaredMethod.setAccessible(true);
            j3 = ((Long) declaredMethod.invoke(this.f17252c, null)).longValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            j3 = 0;
        }
        for (int i2 = 0; i2 < this.f17250a.size(); i2++) {
            AnimationFrameCallback animationFrameCallback = (AnimationFrameCallback) this.f17250a.get(i2);
            if (animationFrameCallback != null && h(animationFrameCallback, j3)) {
                animationFrameCallback.doAnimationFrame(j2);
            }
        }
        e();
    }

    public boolean i() {
        return this.f17257h.b();
    }

    public void j(AnimationFrameCallback animationFrameCallback) {
        this.f17254e.remove(animationFrameCallback);
        int indexOf = this.f17250a.indexOf(animationFrameCallback);
        if (indexOf >= 0) {
            this.f17250a.set(indexOf, null);
            this.f17255f = true;
        }
    }

    public void k() {
        this.f17251b.a();
    }

    public AnimationHandler(FrameCallbackScheduler frameCallbackScheduler, Choreographer choreographer) {
        this.f17254e = new ArrayMap();
        this.f17250a = new ArrayList();
        this.f17251b = new AnimationCallbackDispatcher();
        this.f17256g = new Runnable() { // from class: com.zte.mifavor.animation.AnimationHandler.2
            @Override // java.lang.Runnable
            public final void run() {
                AnimationHandler.this.k();
            }
        };
        this.f17253d = 0L;
        this.f17255f = false;
        Choreographer.getInstance();
        this.f17257h = frameCallbackScheduler;
        this.f17252c = choreographer;
    }
}
