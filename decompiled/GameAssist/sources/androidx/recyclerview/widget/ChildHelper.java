package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class ChildHelper {

    /* renamed from: a, reason: collision with root package name */
    final Callback f4920a;

    /* renamed from: b, reason: collision with root package name */
    final Bucket f4921b = new Bucket();

    /* renamed from: c, reason: collision with root package name */
    final List f4922c = new ArrayList();

    static class Bucket {

        /* renamed from: a, reason: collision with root package name */
        long f4923a = 0;

        /* renamed from: b, reason: collision with root package name */
        Bucket f4924b;

        Bucket() {
        }

        private void c() {
            if (this.f4924b == null) {
                this.f4924b = new Bucket();
            }
        }

        void a(int i2) {
            if (i2 < 64) {
                this.f4923a &= ~(1 << i2);
                return;
            }
            Bucket bucket = this.f4924b;
            if (bucket != null) {
                bucket.a(i2 - 64);
            }
        }

        int b(int i2) {
            Bucket bucket = this.f4924b;
            if (bucket == null) {
                if (i2 >= 64) {
                    return Long.bitCount(this.f4923a);
                }
                return Long.bitCount(((1 << i2) - 1) & this.f4923a);
            }
            if (i2 >= 64) {
                return bucket.b(i2 - 64) + Long.bitCount(this.f4923a);
            }
            return Long.bitCount(((1 << i2) - 1) & this.f4923a);
        }

        boolean d(int i2) {
            if (i2 < 64) {
                return ((1 << i2) & this.f4923a) != 0;
            }
            c();
            return this.f4924b.d(i2 - 64);
        }

        void e(int i2, boolean z) {
            if (i2 >= 64) {
                c();
                this.f4924b.e(i2 - 64, z);
                return;
            }
            long j2 = this.f4923a;
            boolean z2 = (Long.MIN_VALUE & j2) != 0;
            long j3 = (1 << i2) - 1;
            this.f4923a = ((j2 & (~j3)) << 1) | (j2 & j3);
            if (z) {
                h(i2);
            } else {
                a(i2);
            }
            if (z2 || this.f4924b != null) {
                c();
                this.f4924b.e(0, z2);
            }
        }

        boolean f(int i2) {
            if (i2 >= 64) {
                c();
                return this.f4924b.f(i2 - 64);
            }
            long j2 = 1 << i2;
            long j3 = this.f4923a;
            boolean z = (j3 & j2) != 0;
            long j4 = j3 & (~j2);
            this.f4923a = j4;
            long j5 = j2 - 1;
            this.f4923a = (j4 & j5) | Long.rotateRight((~j5) & j4, 1);
            Bucket bucket = this.f4924b;
            if (bucket != null) {
                if (bucket.d(0)) {
                    h(63);
                }
                this.f4924b.f(0);
            }
            return z;
        }

        void g() {
            this.f4923a = 0L;
            Bucket bucket = this.f4924b;
            if (bucket != null) {
                bucket.g();
            }
        }

        void h(int i2) {
            if (i2 < 64) {
                this.f4923a |= 1 << i2;
            } else {
                c();
                this.f4924b.h(i2 - 64);
            }
        }

        public String toString() {
            if (this.f4924b == null) {
                return Long.toBinaryString(this.f4923a);
            }
            return this.f4924b.toString() + "xx" + Long.toBinaryString(this.f4923a);
        }
    }

    interface Callback {
        View a(int i2);

        void b(View view);

        int c();

        RecyclerView.ViewHolder d(View view);

        void e(int i2);

        void f(View view, int i2);

        void g();

        int h(View view);

        void i(View view);

        void j(int i2);

        void k(View view, int i2, ViewGroup.LayoutParams layoutParams);
    }

    ChildHelper(Callback callback) {
        this.f4920a = callback;
    }

    private int h(int i2) {
        if (i2 < 0) {
            return -1;
        }
        int c2 = this.f4920a.c();
        int i3 = i2;
        while (i3 < c2) {
            int b2 = i2 - (i3 - this.f4921b.b(i3));
            if (b2 == 0) {
                while (this.f4921b.d(i3)) {
                    i3++;
                }
                return i3;
            }
            i3 += b2;
        }
        return -1;
    }

    private void l(View view) {
        this.f4922c.add(view);
        this.f4920a.b(view);
    }

    private boolean t(View view) {
        if (!this.f4922c.remove(view)) {
            return false;
        }
        this.f4920a.i(view);
        return true;
    }

    void a(View view, int i2, boolean z) {
        int c2 = i2 < 0 ? this.f4920a.c() : h(i2);
        this.f4921b.e(c2, z);
        if (z) {
            l(view);
        }
        this.f4920a.f(view, c2);
    }

    void b(View view, boolean z) {
        a(view, -1, z);
    }

    void c(View view, int i2, ViewGroup.LayoutParams layoutParams, boolean z) {
        int c2 = i2 < 0 ? this.f4920a.c() : h(i2);
        this.f4921b.e(c2, z);
        if (z) {
            l(view);
        }
        this.f4920a.k(view, c2, layoutParams);
    }

    void d(int i2) {
        int h2 = h(i2);
        this.f4921b.f(h2);
        this.f4920a.e(h2);
    }

    View e(int i2) {
        int size = this.f4922c.size();
        for (int i3 = 0; i3 < size; i3++) {
            View view = (View) this.f4922c.get(i3);
            RecyclerView.ViewHolder d2 = this.f4920a.d(view);
            if (d2.n() == i2 && !d2.u() && !d2.w()) {
                return view;
            }
        }
        return null;
    }

    View f(int i2) {
        return this.f4920a.a(h(i2));
    }

    int g() {
        return this.f4920a.c() - this.f4922c.size();
    }

    View i(int i2) {
        return this.f4920a.a(i2);
    }

    int j() {
        return this.f4920a.c();
    }

    void k(View view) {
        int h2 = this.f4920a.h(view);
        if (h2 >= 0) {
            this.f4921b.h(h2);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    int m(View view) {
        int h2 = this.f4920a.h(view);
        if (h2 == -1 || this.f4921b.d(h2)) {
            return -1;
        }
        return h2 - this.f4921b.b(h2);
    }

    boolean n(View view) {
        return this.f4922c.contains(view);
    }

    void o() {
        this.f4921b.g();
        for (int size = this.f4922c.size() - 1; size >= 0; size--) {
            this.f4920a.i((View) this.f4922c.get(size));
            this.f4922c.remove(size);
        }
        this.f4920a.g();
    }

    void p(View view) {
        int h2 = this.f4920a.h(view);
        if (h2 < 0) {
            return;
        }
        if (this.f4921b.f(h2)) {
            t(view);
        }
        this.f4920a.j(h2);
    }

    void q(int i2) {
        int h2 = h(i2);
        View a2 = this.f4920a.a(h2);
        if (a2 == null) {
            return;
        }
        if (this.f4921b.f(h2)) {
            t(a2);
        }
        this.f4920a.j(h2);
    }

    boolean r(View view) {
        int h2 = this.f4920a.h(view);
        if (h2 == -1) {
            t(view);
            return true;
        }
        if (!this.f4921b.d(h2)) {
            return false;
        }
        this.f4921b.f(h2);
        t(view);
        this.f4920a.j(h2);
        return true;
    }

    void s(View view) {
        int h2 = this.f4920a.h(view);
        if (h2 < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f4921b.d(h2)) {
            this.f4921b.a(h2);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public String toString() {
        return this.f4921b.toString() + ", hidden list:" + this.f4922c.size();
    }
}
