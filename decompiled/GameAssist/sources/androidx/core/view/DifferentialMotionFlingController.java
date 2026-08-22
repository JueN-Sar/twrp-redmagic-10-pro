package androidx.core.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public class DifferentialMotionFlingController {

    /* renamed from: a, reason: collision with root package name */
    private final Context f3327a;

    /* renamed from: b, reason: collision with root package name */
    private final DifferentialMotionFlingTarget f3328b;

    /* renamed from: c, reason: collision with root package name */
    private final FlingVelocityThresholdCalculator f3329c;

    /* renamed from: d, reason: collision with root package name */
    private final DifferentialVelocityProvider f3330d;

    /* renamed from: e, reason: collision with root package name */
    private VelocityTracker f3331e;

    /* renamed from: f, reason: collision with root package name */
    private float f3332f;

    /* renamed from: g, reason: collision with root package name */
    private int f3333g;

    /* renamed from: h, reason: collision with root package name */
    private int f3334h;

    /* renamed from: i, reason: collision with root package name */
    private int f3335i;

    /* renamed from: j, reason: collision with root package name */
    private final int[] f3336j;

    @VisibleForTesting
    interface DifferentialVelocityProvider {
        float getCurrentVelocity(VelocityTracker velocityTracker, MotionEvent motionEvent, int i2);
    }

    @VisibleForTesting
    interface FlingVelocityThresholdCalculator {
        void calculateFlingVelocityThresholds(Context context, int[] iArr, MotionEvent motionEvent, int i2);
    }

    public DifferentialMotionFlingController(Context context, DifferentialMotionFlingTarget differentialMotionFlingTarget) {
        this(context, differentialMotionFlingTarget, new FlingVelocityThresholdCalculator() { // from class: androidx.core.view.b
            @Override // androidx.core.view.DifferentialMotionFlingController.FlingVelocityThresholdCalculator
            public final void calculateFlingVelocityThresholds(Context context2, int[] iArr, MotionEvent motionEvent, int i2) {
                DifferentialMotionFlingController.c(context2, iArr, motionEvent, i2);
            }
        }, new DifferentialVelocityProvider() { // from class: androidx.core.view.c
            @Override // androidx.core.view.DifferentialMotionFlingController.DifferentialVelocityProvider
            public final float getCurrentVelocity(VelocityTracker velocityTracker, MotionEvent motionEvent, int i2) {
                float f2;
                f2 = DifferentialMotionFlingController.f(velocityTracker, motionEvent, i2);
                return f2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, int[] iArr, MotionEvent motionEvent, int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        iArr[0] = ViewConfigurationCompat.g(context, viewConfiguration, motionEvent.getDeviceId(), i2, motionEvent.getSource());
        iArr[1] = ViewConfigurationCompat.f(context, viewConfiguration, motionEvent.getDeviceId(), i2, motionEvent.getSource());
    }

    private boolean d(MotionEvent motionEvent, int i2) {
        int source = motionEvent.getSource();
        int deviceId = motionEvent.getDeviceId();
        if (this.f3334h == source && this.f3335i == deviceId && this.f3333g == i2) {
            return false;
        }
        this.f3329c.calculateFlingVelocityThresholds(this.f3327a, this.f3336j, motionEvent, i2);
        this.f3334h = source;
        this.f3335i = deviceId;
        this.f3333g = i2;
        return true;
    }

    private float e(MotionEvent motionEvent, int i2) {
        if (this.f3331e == null) {
            this.f3331e = VelocityTracker.obtain();
        }
        return this.f3330d.getCurrentVelocity(this.f3331e, motionEvent, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float f(VelocityTracker velocityTracker, MotionEvent motionEvent, int i2) {
        VelocityTrackerCompat.a(velocityTracker, motionEvent);
        VelocityTrackerCompat.b(velocityTracker, 1000);
        return VelocityTrackerCompat.d(velocityTracker, i2);
    }

    public void g(MotionEvent motionEvent, int i2) {
        boolean d2 = d(motionEvent, i2);
        if (this.f3336j[0] == Integer.MAX_VALUE) {
            VelocityTracker velocityTracker = this.f3331e;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f3331e = null;
                return;
            }
            return;
        }
        float e2 = e(motionEvent, i2) * this.f3328b.b();
        float signum = Math.signum(e2);
        if (d2 || (signum != Math.signum(this.f3332f) && signum != 0.0f)) {
            this.f3328b.c();
        }
        float abs = Math.abs(e2);
        int[] iArr = this.f3336j;
        if (abs < iArr[0]) {
            return;
        }
        float max = Math.max(-r6, Math.min(e2, iArr[1]));
        this.f3332f = this.f3328b.a(max) ? max : 0.0f;
    }

    @VisibleForTesting
    DifferentialMotionFlingController(Context context, DifferentialMotionFlingTarget differentialMotionFlingTarget, FlingVelocityThresholdCalculator flingVelocityThresholdCalculator, DifferentialVelocityProvider differentialVelocityProvider) {
        this.f3333g = -1;
        this.f3334h = -1;
        this.f3335i = -1;
        this.f3336j = new int[]{Api.BaseClientBuilder.API_PRIORITY_OTHER, 0};
        this.f3327a = context;
        this.f3328b = differentialMotionFlingTarget;
        this.f3329c = flingVelocityThresholdCalculator;
        this.f3330d = differentialVelocityProvider;
    }
}
