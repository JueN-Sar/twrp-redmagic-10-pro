package kotlin.math;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class Constants {

    /* renamed from: a, reason: collision with root package name */
    public static final Constants f18582a = new Constants();

    /* renamed from: b, reason: collision with root package name */
    public static final double f18583b = Math.log(2.0d);

    /* renamed from: c, reason: collision with root package name */
    public static final double f18584c;

    /* renamed from: d, reason: collision with root package name */
    public static final double f18585d;

    /* renamed from: e, reason: collision with root package name */
    public static final double f18586e;

    /* renamed from: f, reason: collision with root package name */
    public static final double f18587f;

    /* renamed from: g, reason: collision with root package name */
    public static final double f18588g;

    static {
        double ulp = Math.ulp(1.0d);
        f18584c = ulp;
        double sqrt = Math.sqrt(ulp);
        f18585d = sqrt;
        double sqrt2 = Math.sqrt(sqrt);
        f18586e = sqrt2;
        double d2 = 1;
        f18587f = d2 / sqrt;
        f18588g = d2 / sqrt2;
    }

    private Constants() {
    }
}
