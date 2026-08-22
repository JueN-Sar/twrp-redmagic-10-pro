package androidx.constraintlayout.core.motion;

/* loaded from: classes.dex */
public class MotionPaths implements Comparable<MotionPaths> {

    /* renamed from: j, reason: collision with root package name */
    static String[] f1688j = {"position", "x", "y", "width", "height", "pathRotate"};

    /* renamed from: c, reason: collision with root package name */
    float f1689c;

    /* renamed from: h, reason: collision with root package name */
    float f1690h;

    /* renamed from: i, reason: collision with root package name */
    float f1691i;

    @Override // java.lang.Comparable
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public int compareTo(MotionPaths motionPaths) {
        return Float.compare(this.f1689c, motionPaths.f1689c);
    }
}
