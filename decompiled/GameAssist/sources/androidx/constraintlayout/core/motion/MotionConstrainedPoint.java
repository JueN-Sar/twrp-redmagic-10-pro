package androidx.constraintlayout.core.motion;

/* loaded from: classes.dex */
class MotionConstrainedPoint implements Comparable<MotionConstrainedPoint> {

    /* renamed from: h, reason: collision with root package name */
    static String[] f1686h = {"position", "x", "y", "width", "height", "pathRotate"};

    /* renamed from: c, reason: collision with root package name */
    private float f1687c;

    @Override // java.lang.Comparable
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public int compareTo(MotionConstrainedPoint motionConstrainedPoint) {
        return Float.compare(this.f1687c, motionConstrainedPoint.f1687c);
    }
}
