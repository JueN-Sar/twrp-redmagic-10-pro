package androidx.constraintlayout.motion.utils;

import androidx.constraintlayout.core.motion.utils.SpringStopEngine;
import androidx.constraintlayout.core.motion.utils.StopEngine;
import androidx.constraintlayout.core.motion.utils.StopLogicEngine;
import androidx.constraintlayout.motion.widget.MotionInterpolator;

/* loaded from: classes.dex */
public class StopLogic extends MotionInterpolator {

    /* renamed from: a, reason: collision with root package name */
    private StopLogicEngine f2095a;

    /* renamed from: b, reason: collision with root package name */
    private SpringStopEngine f2096b;

    /* renamed from: c, reason: collision with root package name */
    private StopEngine f2097c;

    public StopLogic() {
        StopLogicEngine stopLogicEngine = new StopLogicEngine();
        this.f2095a = stopLogicEngine;
        this.f2097c = stopLogicEngine;
    }

    @Override // androidx.constraintlayout.motion.widget.MotionInterpolator
    public float a() {
        return this.f2097c.a();
    }

    public void b(float f2, float f3, float f4, float f5, float f6, float f7) {
        StopLogicEngine stopLogicEngine = this.f2095a;
        this.f2097c = stopLogicEngine;
        stopLogicEngine.d(f2, f3, f4, f5, f6, f7);
    }

    public boolean c() {
        return this.f2097c.b();
    }

    public void d(float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i2) {
        if (this.f2096b == null) {
            this.f2096b = new SpringStopEngine();
        }
        SpringStopEngine springStopEngine = this.f2096b;
        this.f2097c = springStopEngine;
        springStopEngine.d(f2, f3, f4, f5, f6, f7, f8, i2);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        return this.f2097c.getInterpolation(f2);
    }
}
