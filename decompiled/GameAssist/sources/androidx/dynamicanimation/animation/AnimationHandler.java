package androidx.dynamicanimation.animation;

import android.animation.ValueAnimator;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Choreographer;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.collection.SimpleArrayMap;
import androidx.dynamicanimation.animation.AnimationHandler;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AnimationHandler {

    /* renamed from: h, reason: collision with root package name */
    private static final ThreadLocal f3640h = new ThreadLocal();

    /* renamed from: e, reason: collision with root package name */
    private FrameCallbackScheduler f3645e;

    @RestrictTo
    @VisibleForTesting
    public DurationScaleChangeListener mDurationScaleChangeListener;

    /* renamed from: a, reason: collision with root package name */
    private final SimpleArrayMap f3641a = new SimpleArrayMap();

    /* renamed from: b, reason: collision with root package name */
    final ArrayList f3642b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    private final AnimationCallbackDispatcher f3643c = new AnimationCallbackDispatcher();

    /* renamed from: d, reason: collision with root package name */
    private final Runnable f3644d = new Runnable() { // from class: androidx.dynamicanimation.animation.a
        @Override // java.lang.Runnable
        public final void run() {
            AnimationHandler.this.j();
        }
    };

    /* renamed from: f, reason: collision with root package name */
    long f3646f = 0;

    /* renamed from: g, reason: collision with root package name */
    private boolean f3647g = false;

    @RestrictTo
    @VisibleForTesting
    public float mDurationScale = 1.0f;

    private class AnimationCallbackDispatcher {
        private AnimationCallbackDispatcher() {
        }

        void a() {
            AnimationHandler.this.f3646f = SystemClock.uptimeMillis();
            AnimationHandler animationHandler = AnimationHandler.this;
            animationHandler.f(animationHandler.f3646f);
            if (AnimationHandler.this.f3642b.size() > 0) {
                AnimationHandler.this.f3645e.a(AnimationHandler.this.f3644d);
            }
        }
    }

    interface AnimationFrameCallback {
        boolean doAnimationFrame(long j2);
    }

    @RestrictTo
    @VisibleForTesting
    public interface DurationScaleChangeListener {
        boolean register();

        boolean unregister();
    }

    @VisibleForTesting
    @RequiresApi
    @RestrictTo
    public class DurationScaleChangeListener33 implements DurationScaleChangeListener {
        ValueAnimator.DurationScaleChangeListener mListener;

        public DurationScaleChangeListener33() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$register$0(float f2) {
            AnimationHandler.this.mDurationScale = f2;
        }

        @Override // androidx.dynamicanimation.animation.AnimationHandler.DurationScaleChangeListener
        public boolean register() {
            if (this.mListener != null) {
                return true;
            }
            ValueAnimator.DurationScaleChangeListener durationScaleChangeListener = new ValueAnimator.DurationScaleChangeListener() { // from class: androidx.dynamicanimation.animation.b
                @Override // android.animation.ValueAnimator.DurationScaleChangeListener
                public final void onChanged(float f2) {
                    AnimationHandler.DurationScaleChangeListener33.this.lambda$register$0(f2);
                }
            };
            this.mListener = durationScaleChangeListener;
            return ValueAnimator.registerDurationScaleChangeListener(durationScaleChangeListener);
        }

        @Override // androidx.dynamicanimation.animation.AnimationHandler.DurationScaleChangeListener
        public boolean unregister() {
            boolean unregisterDurationScaleChangeListener = ValueAnimator.unregisterDurationScaleChangeListener(this.mListener);
            this.mListener = null;
            return unregisterDurationScaleChangeListener;
        }
    }

    static final class FrameCallbackScheduler16 implements FrameCallbackScheduler {

        /* renamed from: a, reason: collision with root package name */
        private final Choreographer f3649a = Choreographer.getInstance();

        /* renamed from: b, reason: collision with root package name */
        private final Looper f3650b = Looper.myLooper();

        FrameCallbackScheduler16() {
        }

        @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
        public void a(final Runnable runnable) {
            this.f3649a.postFrameCallback(new Choreographer.FrameCallback() { // from class: androidx.dynamicanimation.animation.c
                @Override // android.view.Choreographer.FrameCallback
                public final void doFrame(long j2) {
                    runnable.run();
                }
            });
        }

        @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
        public boolean b() {
            return Thread.currentThread() == this.f3650b.getThread();
        }
    }

    public AnimationHandler(FrameCallbackScheduler frameCallbackScheduler) {
        this.f3645e = frameCallbackScheduler;
    }

    private void e() {
        if (this.f3647g) {
            for (int size = this.f3642b.size() - 1; size >= 0; size--) {
                if (this.f3642b.get(size) == null) {
                    this.f3642b.remove(size);
                }
            }
            if (this.f3642b.size() == 0) {
                this.mDurationScaleChangeListener.unregister();
            }
            this.f3647g = false;
        }
    }

    static AnimationHandler g() {
        ThreadLocal threadLocal = f3640h;
        if (threadLocal.get() == null) {
            threadLocal.set(new AnimationHandler(new FrameCallbackScheduler16()));
        }
        return (AnimationHandler) threadLocal.get();
    }

    private boolean h(AnimationFrameCallback animationFrameCallback, long j2) {
        Long l2 = (Long) this.f3641a.get(animationFrameCallback);
        if (l2 == null) {
            return true;
        }
        if (l2.longValue() >= j2) {
            return false;
        }
        this.f3641a.remove(animationFrameCallback);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j() {
        this.f3643c.a();
    }

    void d(AnimationFrameCallback animationFrameCallback, long j2) {
        if (this.f3642b.size() == 0) {
            this.f3645e.a(this.f3644d);
            this.mDurationScale = ValueAnimator.getDurationScale();
            if (this.mDurationScaleChangeListener == null) {
                this.mDurationScaleChangeListener = new DurationScaleChangeListener33();
            }
            this.mDurationScaleChangeListener.register();
        }
        if (!this.f3642b.contains(animationFrameCallback)) {
            this.f3642b.add(animationFrameCallback);
        }
        if (j2 > 0) {
            this.f3641a.put(animationFrameCallback, Long.valueOf(SystemClock.uptimeMillis() + j2));
        }
    }

    void f(long j2) {
        long uptimeMillis = SystemClock.uptimeMillis();
        for (int i2 = 0; i2 < this.f3642b.size(); i2++) {
            AnimationFrameCallback animationFrameCallback = (AnimationFrameCallback) this.f3642b.get(i2);
            if (animationFrameCallback != null && h(animationFrameCallback, uptimeMillis)) {
                animationFrameCallback.doAnimationFrame(j2);
            }
        }
        e();
    }

    @VisibleForTesting
    public float getDurationScale() {
        return this.mDurationScale;
    }

    boolean i() {
        return this.f3645e.b();
    }

    void k(AnimationFrameCallback animationFrameCallback) {
        this.f3641a.remove(animationFrameCallback);
        int indexOf = this.f3642b.indexOf(animationFrameCallback);
        if (indexOf >= 0) {
            this.f3642b.set(indexOf, null);
            this.f3647g = true;
        }
    }
}
