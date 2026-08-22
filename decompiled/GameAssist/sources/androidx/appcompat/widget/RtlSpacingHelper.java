package androidx.appcompat.widget;

/* loaded from: classes.dex */
class RtlSpacingHelper {

    /* renamed from: a, reason: collision with root package name */
    private int f935a = 0;

    /* renamed from: b, reason: collision with root package name */
    private int f936b = 0;

    /* renamed from: c, reason: collision with root package name */
    private int f937c = Integer.MIN_VALUE;

    /* renamed from: d, reason: collision with root package name */
    private int f938d = Integer.MIN_VALUE;

    /* renamed from: e, reason: collision with root package name */
    private int f939e = 0;

    /* renamed from: f, reason: collision with root package name */
    private int f940f = 0;

    /* renamed from: g, reason: collision with root package name */
    private boolean f941g = false;

    /* renamed from: h, reason: collision with root package name */
    private boolean f942h = false;

    RtlSpacingHelper() {
    }

    public int a() {
        return this.f941g ? this.f935a : this.f936b;
    }

    public int b() {
        return this.f935a;
    }

    public int c() {
        return this.f936b;
    }

    public int d() {
        return this.f941g ? this.f936b : this.f935a;
    }

    public void e(int i2, int i3) {
        this.f942h = false;
        if (i2 != Integer.MIN_VALUE) {
            this.f939e = i2;
            this.f935a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f940f = i3;
            this.f936b = i3;
        }
    }

    public void f(boolean z) {
        if (z == this.f941g) {
            return;
        }
        this.f941g = z;
        if (!this.f942h) {
            this.f935a = this.f939e;
            this.f936b = this.f940f;
            return;
        }
        if (z) {
            int i2 = this.f938d;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f939e;
            }
            this.f935a = i2;
            int i3 = this.f937c;
            if (i3 == Integer.MIN_VALUE) {
                i3 = this.f940f;
            }
            this.f936b = i3;
            return;
        }
        int i4 = this.f937c;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f939e;
        }
        this.f935a = i4;
        int i5 = this.f938d;
        if (i5 == Integer.MIN_VALUE) {
            i5 = this.f940f;
        }
        this.f936b = i5;
    }

    public void g(int i2, int i3) {
        this.f937c = i2;
        this.f938d = i3;
        this.f942h = true;
        if (this.f941g) {
            if (i3 != Integer.MIN_VALUE) {
                this.f935a = i3;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.f936b = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f935a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f936b = i3;
        }
    }
}
