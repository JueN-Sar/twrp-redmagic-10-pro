package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.DifferentialInterpolator;
import androidx.constraintlayout.core.motion.utils.TypedValues;

/* loaded from: classes.dex */
public class Motion implements TypedValues {

    /* renamed from: a, reason: collision with root package name */
    private MotionPaths f1684a;

    /* renamed from: b, reason: collision with root package name */
    private MotionPaths f1685b;

    /* renamed from: androidx.constraintlayout.core.motion.Motion$1, reason: invalid class name */
    class AnonymousClass1 implements DifferentialInterpolator {
    }

    public String toString() {
        return " start: x: " + this.f1684a.f1690h + " y: " + this.f1684a.f1691i + " end: x: " + this.f1685b.f1690h + " y: " + this.f1685b.f1691i;
    }
}
