package androidx.recyclerview.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
class ViewBoundsCheck {

    /* renamed from: a, reason: collision with root package name */
    final Callback f5322a;

    /* renamed from: b, reason: collision with root package name */
    BoundFlags f5323b = new BoundFlags();

    static class BoundFlags {

        /* renamed from: a, reason: collision with root package name */
        int f5324a = 0;

        /* renamed from: b, reason: collision with root package name */
        int f5325b;

        /* renamed from: c, reason: collision with root package name */
        int f5326c;

        /* renamed from: d, reason: collision with root package name */
        int f5327d;

        /* renamed from: e, reason: collision with root package name */
        int f5328e;

        BoundFlags() {
        }

        void a(int i2) {
            this.f5324a = i2 | this.f5324a;
        }

        boolean b() {
            int i2 = this.f5324a;
            if ((i2 & 7) != 0 && (i2 & c(this.f5327d, this.f5325b)) == 0) {
                return false;
            }
            int i3 = this.f5324a;
            if ((i3 & 112) != 0 && (i3 & (c(this.f5327d, this.f5326c) << 4)) == 0) {
                return false;
            }
            int i4 = this.f5324a;
            if ((i4 & 1792) != 0 && (i4 & (c(this.f5328e, this.f5325b) << 8)) == 0) {
                return false;
            }
            int i5 = this.f5324a;
            return (i5 & 28672) == 0 || ((c(this.f5328e, this.f5326c) << 12) & i5) != 0;
        }

        int c(int i2, int i3) {
            if (i2 > i3) {
                return 1;
            }
            return i2 == i3 ? 2 : 4;
        }

        void d() {
            this.f5324a = 0;
        }

        void e(int i2, int i3, int i4, int i5) {
            this.f5325b = i2;
            this.f5326c = i3;
            this.f5327d = i4;
            this.f5328e = i5;
        }
    }

    interface Callback {
        View a(int i2);

        int b(View view);

        int c();

        int d();

        int e(View view);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewBounds {
    }

    ViewBoundsCheck(Callback callback) {
        this.f5322a = callback;
    }

    View a(int i2, int i3, int i4, int i5) {
        int c2 = this.f5322a.c();
        int d2 = this.f5322a.d();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        while (i2 != i3) {
            View a2 = this.f5322a.a(i2);
            this.f5323b.e(c2, d2, this.f5322a.b(a2), this.f5322a.e(a2));
            if (i4 != 0) {
                this.f5323b.d();
                this.f5323b.a(i4);
                if (this.f5323b.b()) {
                    return a2;
                }
            }
            if (i5 != 0) {
                this.f5323b.d();
                this.f5323b.a(i5);
                if (this.f5323b.b()) {
                    view = a2;
                }
            }
            i2 += i6;
        }
        return view;
    }

    boolean b(View view, int i2) {
        this.f5323b.e(this.f5322a.c(), this.f5322a.d(), this.f5322a.b(view), this.f5322a.e(view));
        if (i2 == 0) {
            return false;
        }
        this.f5323b.d();
        this.f5323b.a(i2);
        return this.f5323b.b();
    }
}
