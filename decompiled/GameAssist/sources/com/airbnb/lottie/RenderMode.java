package com.airbnb.lottie;

/* loaded from: classes.dex */
public enum RenderMode {
    AUTOMATIC,
    HARDWARE,
    SOFTWARE;

    /* renamed from: com.airbnb.lottie.RenderMode$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9337a;

        static {
            int[] iArr = new int[RenderMode.values().length];
            f9337a = iArr;
            try {
                iArr[RenderMode.HARDWARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9337a[RenderMode.SOFTWARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9337a[RenderMode.AUTOMATIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public boolean d(int i2, boolean z, int i3) {
        int i4 = AnonymousClass1.f9337a[ordinal()];
        if (i4 == 1) {
            return false;
        }
        if (i4 != 2) {
            return (z && i2 < 28) || i3 > 4 || i2 <= 25;
        }
        return true;
    }
}
