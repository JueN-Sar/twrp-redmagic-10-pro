package androidx.core.view;

import android.view.MotionEvent;

/* loaded from: classes.dex */
class VelocityTrackerFallback {

    /* renamed from: a, reason: collision with root package name */
    private final float[] f3368a = new float[20];

    /* renamed from: b, reason: collision with root package name */
    private final long[] f3369b = new long[20];

    /* renamed from: c, reason: collision with root package name */
    private float f3370c = 0.0f;

    /* renamed from: d, reason: collision with root package name */
    private int f3371d = 0;

    /* renamed from: e, reason: collision with root package name */
    private int f3372e = 0;

    VelocityTrackerFallback() {
    }

    private void b() {
        this.f3371d = 0;
        this.f3370c = 0.0f;
    }

    private float e() {
        long[] jArr;
        long j2;
        int i2 = this.f3371d;
        if (i2 < 2) {
            return 0.0f;
        }
        int i3 = this.f3372e;
        int i4 = ((i3 + 20) - (i2 - 1)) % 20;
        long j3 = this.f3369b[i3];
        while (true) {
            jArr = this.f3369b;
            j2 = jArr[i4];
            if (j3 - j2 <= 100) {
                break;
            }
            this.f3371d--;
            i4 = (i4 + 1) % 20;
        }
        int i5 = this.f3371d;
        if (i5 < 2) {
            return 0.0f;
        }
        if (i5 == 2) {
            int i6 = (i4 + 1) % 20;
            if (j2 == jArr[i6]) {
                return 0.0f;
            }
            return this.f3368a[i6] / (r2 - j2);
        }
        float f2 = 0.0f;
        int i7 = 0;
        for (int i8 = 0; i8 < this.f3371d - 1; i8++) {
            int i9 = i8 + i4;
            long[] jArr2 = this.f3369b;
            long j4 = jArr2[i9 % 20];
            int i10 = (i9 + 1) % 20;
            if (jArr2[i10] != j4) {
                i7++;
                float f3 = f(f2);
                float f4 = this.f3368a[i10] / (this.f3369b[i10] - j4);
                f2 += (f4 - f3) * Math.abs(f4);
                if (i7 == 1) {
                    f2 *= 0.5f;
                }
            }
        }
        return f(f2);
    }

    private static float f(float f2) {
        return (f2 < 0.0f ? -1.0f : 1.0f) * ((float) Math.sqrt(Math.abs(f2) * 2.0f));
    }

    void a(MotionEvent motionEvent) {
        long eventTime = motionEvent.getEventTime();
        if (this.f3371d != 0 && eventTime - this.f3369b[this.f3372e] > 40) {
            b();
        }
        int i2 = (this.f3372e + 1) % 20;
        this.f3372e = i2;
        int i3 = this.f3371d;
        if (i3 != 20) {
            this.f3371d = i3 + 1;
        }
        this.f3368a[i2] = motionEvent.getAxisValue(26);
        this.f3369b[this.f3372e] = eventTime;
    }

    void c(int i2, float f2) {
        float e2 = e() * i2;
        this.f3370c = e2;
        if (e2 < (-Math.abs(f2))) {
            this.f3370c = -Math.abs(f2);
        } else if (this.f3370c > Math.abs(f2)) {
            this.f3370c = Math.abs(f2);
        }
    }

    float d(int i2) {
        if (i2 != 26) {
            return 0.0f;
        }
        return this.f3370c;
    }
}
