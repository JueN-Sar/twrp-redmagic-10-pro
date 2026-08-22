package androidx.recyclerview.widget;

import androidx.core.util.Pools;
import androidx.recyclerview.widget.OpReorderer;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class AdapterHelper implements OpReorderer.Callback {

    /* renamed from: a, reason: collision with root package name */
    private Pools.Pool f4859a;

    /* renamed from: b, reason: collision with root package name */
    final ArrayList f4860b;

    /* renamed from: c, reason: collision with root package name */
    final ArrayList f4861c;

    /* renamed from: d, reason: collision with root package name */
    final Callback f4862d;

    /* renamed from: e, reason: collision with root package name */
    Runnable f4863e;

    /* renamed from: f, reason: collision with root package name */
    final boolean f4864f;

    /* renamed from: g, reason: collision with root package name */
    final OpReorderer f4865g;

    /* renamed from: h, reason: collision with root package name */
    private int f4866h;

    interface Callback {
        void a(int i2, int i3);

        void b(UpdateOp updateOp);

        void c(UpdateOp updateOp);

        void d(int i2, int i3);

        void e(int i2, int i3, Object obj);

        RecyclerView.ViewHolder f(int i2);

        void g(int i2, int i3);

        void h(int i2, int i3);
    }

    static class UpdateOp {

        /* renamed from: a, reason: collision with root package name */
        int f4867a;

        /* renamed from: b, reason: collision with root package name */
        int f4868b;

        /* renamed from: c, reason: collision with root package name */
        Object f4869c;

        /* renamed from: d, reason: collision with root package name */
        int f4870d;

        UpdateOp(int i2, int i3, int i4, Object obj) {
            this.f4867a = i2;
            this.f4868b = i3;
            this.f4870d = i4;
            this.f4869c = obj;
        }

        String a() {
            int i2 = this.f4867a;
            return i2 != 1 ? i2 != 2 ? i2 != 4 ? i2 != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UpdateOp updateOp = (UpdateOp) obj;
            int i2 = this.f4867a;
            if (i2 != updateOp.f4867a) {
                return false;
            }
            if (i2 == 8 && Math.abs(this.f4870d - this.f4868b) == 1 && this.f4870d == updateOp.f4868b && this.f4868b == updateOp.f4870d) {
                return true;
            }
            if (this.f4870d != updateOp.f4870d || this.f4868b != updateOp.f4868b) {
                return false;
            }
            Object obj2 = this.f4869c;
            if (obj2 != null) {
                if (!obj2.equals(updateOp.f4869c)) {
                    return false;
                }
            } else if (updateOp.f4869c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f4867a * 31) + this.f4868b) * 31) + this.f4870d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f4868b + "c:" + this.f4870d + ",p:" + this.f4869c + "]";
        }
    }

    AdapterHelper(Callback callback) {
        this(callback, false);
    }

    private void c(UpdateOp updateOp) {
        v(updateOp);
    }

    private void d(UpdateOp updateOp) {
        v(updateOp);
    }

    private void f(UpdateOp updateOp) {
        boolean z;
        char c2;
        int i2 = updateOp.f4868b;
        int i3 = updateOp.f4870d + i2;
        char c3 = 65535;
        int i4 = i2;
        int i5 = 0;
        while (i4 < i3) {
            if (this.f4862d.f(i4) != null || h(i4)) {
                if (c3 == 0) {
                    k(a(2, i2, i5, null));
                    z = true;
                } else {
                    z = false;
                }
                c2 = 1;
            } else {
                if (c3 == 1) {
                    v(a(2, i2, i5, null));
                    z = true;
                } else {
                    z = false;
                }
                c2 = 0;
            }
            if (z) {
                i4 -= i5;
                i3 -= i5;
                i5 = 1;
            } else {
                i5++;
            }
            i4++;
            c3 = c2;
        }
        if (i5 != updateOp.f4870d) {
            b(updateOp);
            updateOp = a(2, i2, i5, null);
        }
        if (c3 == 0) {
            k(updateOp);
        } else {
            v(updateOp);
        }
    }

    private void g(UpdateOp updateOp) {
        int i2 = updateOp.f4868b;
        int i3 = updateOp.f4870d + i2;
        int i4 = 0;
        boolean z = -1;
        int i5 = i2;
        while (i2 < i3) {
            if (this.f4862d.f(i2) != null || h(i2)) {
                if (!z) {
                    k(a(4, i5, i4, updateOp.f4869c));
                    i5 = i2;
                    i4 = 0;
                }
                z = true;
            } else {
                if (z) {
                    v(a(4, i5, i4, updateOp.f4869c));
                    i5 = i2;
                    i4 = 0;
                }
                z = false;
            }
            i4++;
            i2++;
        }
        if (i4 != updateOp.f4870d) {
            Object obj = updateOp.f4869c;
            b(updateOp);
            updateOp = a(4, i5, i4, obj);
        }
        if (z) {
            v(updateOp);
        } else {
            k(updateOp);
        }
    }

    private boolean h(int i2) {
        int size = this.f4861c.size();
        for (int i3 = 0; i3 < size; i3++) {
            UpdateOp updateOp = (UpdateOp) this.f4861c.get(i3);
            int i4 = updateOp.f4867a;
            if (i4 == 8) {
                if (n(updateOp.f4870d, i3 + 1) == i2) {
                    return true;
                }
            } else if (i4 == 1) {
                int i5 = updateOp.f4868b;
                int i6 = updateOp.f4870d + i5;
                while (i5 < i6) {
                    if (n(i5, i3 + 1) == i2) {
                        return true;
                    }
                    i5++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    private void k(UpdateOp updateOp) {
        int i2;
        int i3 = updateOp.f4867a;
        if (i3 == 1 || i3 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int z = z(updateOp.f4868b, i3);
        int i4 = updateOp.f4868b;
        int i5 = updateOp.f4867a;
        if (i5 == 2) {
            i2 = 0;
        } else {
            if (i5 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + updateOp);
            }
            i2 = 1;
        }
        int i6 = 1;
        for (int i7 = 1; i7 < updateOp.f4870d; i7++) {
            int z2 = z(updateOp.f4868b + (i2 * i7), updateOp.f4867a);
            int i8 = updateOp.f4867a;
            if (i8 == 2 ? z2 != z : !(i8 == 4 && z2 == z + 1)) {
                UpdateOp a2 = a(i8, z, i6, updateOp.f4869c);
                l(a2, i4);
                b(a2);
                if (updateOp.f4867a == 4) {
                    i4 += i6;
                }
                i6 = 1;
                z = z2;
            } else {
                i6++;
            }
        }
        Object obj = updateOp.f4869c;
        b(updateOp);
        if (i6 > 0) {
            UpdateOp a3 = a(updateOp.f4867a, z, i6, obj);
            l(a3, i4);
            b(a3);
        }
    }

    private void v(UpdateOp updateOp) {
        this.f4861c.add(updateOp);
        int i2 = updateOp.f4867a;
        if (i2 == 1) {
            this.f4862d.g(updateOp.f4868b, updateOp.f4870d);
            return;
        }
        if (i2 == 2) {
            this.f4862d.d(updateOp.f4868b, updateOp.f4870d);
            return;
        }
        if (i2 == 4) {
            this.f4862d.e(updateOp.f4868b, updateOp.f4870d, updateOp.f4869c);
        } else {
            if (i2 == 8) {
                this.f4862d.a(updateOp.f4868b, updateOp.f4870d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + updateOp);
        }
    }

    private int z(int i2, int i3) {
        int i4;
        int i5;
        for (int size = this.f4861c.size() - 1; size >= 0; size--) {
            UpdateOp updateOp = (UpdateOp) this.f4861c.get(size);
            int i6 = updateOp.f4867a;
            if (i6 == 8) {
                int i7 = updateOp.f4868b;
                int i8 = updateOp.f4870d;
                if (i7 < i8) {
                    i5 = i7;
                    i4 = i8;
                } else {
                    i4 = i7;
                    i5 = i8;
                }
                if (i2 < i5 || i2 > i4) {
                    if (i2 < i7) {
                        if (i3 == 1) {
                            updateOp.f4868b = i7 + 1;
                            updateOp.f4870d = i8 + 1;
                        } else if (i3 == 2) {
                            updateOp.f4868b = i7 - 1;
                            updateOp.f4870d = i8 - 1;
                        }
                    }
                } else if (i5 == i7) {
                    if (i3 == 1) {
                        updateOp.f4870d = i8 + 1;
                    } else if (i3 == 2) {
                        updateOp.f4870d = i8 - 1;
                    }
                    i2++;
                } else {
                    if (i3 == 1) {
                        updateOp.f4868b = i7 + 1;
                    } else if (i3 == 2) {
                        updateOp.f4868b = i7 - 1;
                    }
                    i2--;
                }
            } else {
                int i9 = updateOp.f4868b;
                if (i9 <= i2) {
                    if (i6 == 1) {
                        i2 -= updateOp.f4870d;
                    } else if (i6 == 2) {
                        i2 += updateOp.f4870d;
                    }
                } else if (i3 == 1) {
                    updateOp.f4868b = i9 + 1;
                } else if (i3 == 2) {
                    updateOp.f4868b = i9 - 1;
                }
            }
        }
        for (int size2 = this.f4861c.size() - 1; size2 >= 0; size2--) {
            UpdateOp updateOp2 = (UpdateOp) this.f4861c.get(size2);
            if (updateOp2.f4867a == 8) {
                int i10 = updateOp2.f4870d;
                if (i10 == updateOp2.f4868b || i10 < 0) {
                    this.f4861c.remove(size2);
                    b(updateOp2);
                }
            } else if (updateOp2.f4870d <= 0) {
                this.f4861c.remove(size2);
                b(updateOp2);
            }
        }
        return i2;
    }

    @Override // androidx.recyclerview.widget.OpReorderer.Callback
    public UpdateOp a(int i2, int i3, int i4, Object obj) {
        UpdateOp updateOp = (UpdateOp) this.f4859a.acquire();
        if (updateOp == null) {
            return new UpdateOp(i2, i3, i4, obj);
        }
        updateOp.f4867a = i2;
        updateOp.f4868b = i3;
        updateOp.f4870d = i4;
        updateOp.f4869c = obj;
        return updateOp;
    }

    @Override // androidx.recyclerview.widget.OpReorderer.Callback
    public void b(UpdateOp updateOp) {
        if (this.f4864f) {
            return;
        }
        updateOp.f4869c = null;
        this.f4859a.release(updateOp);
    }

    public int e(int i2) {
        int size = this.f4860b.size();
        for (int i3 = 0; i3 < size; i3++) {
            UpdateOp updateOp = (UpdateOp) this.f4860b.get(i3);
            int i4 = updateOp.f4867a;
            if (i4 != 1) {
                if (i4 == 2) {
                    int i5 = updateOp.f4868b;
                    if (i5 <= i2) {
                        int i6 = updateOp.f4870d;
                        if (i5 + i6 > i2) {
                            return -1;
                        }
                        i2 -= i6;
                    } else {
                        continue;
                    }
                } else if (i4 == 8) {
                    int i7 = updateOp.f4868b;
                    if (i7 == i2) {
                        i2 = updateOp.f4870d;
                    } else {
                        if (i7 < i2) {
                            i2--;
                        }
                        if (updateOp.f4870d <= i2) {
                            i2++;
                        }
                    }
                }
            } else if (updateOp.f4868b <= i2) {
                i2 += updateOp.f4870d;
            }
        }
        return i2;
    }

    void i() {
        int size = this.f4861c.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f4862d.c((UpdateOp) this.f4861c.get(i2));
        }
        x(this.f4861c);
        this.f4866h = 0;
    }

    void j() {
        i();
        int size = this.f4860b.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = (UpdateOp) this.f4860b.get(i2);
            int i3 = updateOp.f4867a;
            if (i3 == 1) {
                this.f4862d.c(updateOp);
                this.f4862d.g(updateOp.f4868b, updateOp.f4870d);
            } else if (i3 == 2) {
                this.f4862d.c(updateOp);
                this.f4862d.h(updateOp.f4868b, updateOp.f4870d);
            } else if (i3 == 4) {
                this.f4862d.c(updateOp);
                this.f4862d.e(updateOp.f4868b, updateOp.f4870d, updateOp.f4869c);
            } else if (i3 == 8) {
                this.f4862d.c(updateOp);
                this.f4862d.a(updateOp.f4868b, updateOp.f4870d);
            }
            Runnable runnable = this.f4863e;
            if (runnable != null) {
                runnable.run();
            }
        }
        x(this.f4860b);
        this.f4866h = 0;
    }

    void l(UpdateOp updateOp, int i2) {
        this.f4862d.b(updateOp);
        int i3 = updateOp.f4867a;
        if (i3 == 2) {
            this.f4862d.h(i2, updateOp.f4870d);
        } else {
            if (i3 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f4862d.e(i2, updateOp.f4870d, updateOp.f4869c);
        }
    }

    int m(int i2) {
        return n(i2, 0);
    }

    int n(int i2, int i3) {
        int size = this.f4861c.size();
        while (i3 < size) {
            UpdateOp updateOp = (UpdateOp) this.f4861c.get(i3);
            int i4 = updateOp.f4867a;
            if (i4 == 8) {
                int i5 = updateOp.f4868b;
                if (i5 == i2) {
                    i2 = updateOp.f4870d;
                } else {
                    if (i5 < i2) {
                        i2--;
                    }
                    if (updateOp.f4870d <= i2) {
                        i2++;
                    }
                }
            } else {
                int i6 = updateOp.f4868b;
                if (i6 > i2) {
                    continue;
                } else if (i4 == 2) {
                    int i7 = updateOp.f4870d;
                    if (i2 < i6 + i7) {
                        return -1;
                    }
                    i2 -= i7;
                } else if (i4 == 1) {
                    i2 += updateOp.f4870d;
                }
            }
            i3++;
        }
        return i2;
    }

    boolean o(int i2) {
        return (this.f4866h & i2) != 0;
    }

    boolean p() {
        return this.f4860b.size() > 0;
    }

    boolean q() {
        return (this.f4861c.isEmpty() || this.f4860b.isEmpty()) ? false : true;
    }

    boolean r(int i2, int i3, Object obj) {
        if (i3 < 1) {
            return false;
        }
        this.f4860b.add(a(4, i2, i3, obj));
        this.f4866h |= 4;
        return this.f4860b.size() == 1;
    }

    boolean s(int i2, int i3) {
        if (i3 < 1) {
            return false;
        }
        this.f4860b.add(a(1, i2, i3, null));
        this.f4866h |= 1;
        return this.f4860b.size() == 1;
    }

    boolean t(int i2, int i3, int i4) {
        if (i2 == i3) {
            return false;
        }
        if (i4 != 1) {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        this.f4860b.add(a(8, i2, i3, null));
        this.f4866h |= 8;
        return this.f4860b.size() == 1;
    }

    boolean u(int i2, int i3) {
        if (i3 < 1) {
            return false;
        }
        this.f4860b.add(a(2, i2, i3, null));
        this.f4866h |= 2;
        return this.f4860b.size() == 1;
    }

    void w() {
        this.f4865g.b(this.f4860b);
        int size = this.f4860b.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = (UpdateOp) this.f4860b.get(i2);
            int i3 = updateOp.f4867a;
            if (i3 == 1) {
                c(updateOp);
            } else if (i3 == 2) {
                f(updateOp);
            } else if (i3 == 4) {
                g(updateOp);
            } else if (i3 == 8) {
                d(updateOp);
            }
            Runnable runnable = this.f4863e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f4860b.clear();
    }

    void x(List list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            b((UpdateOp) list.get(i2));
        }
        list.clear();
    }

    void y() {
        x(this.f4860b);
        x(this.f4861c);
        this.f4866h = 0;
    }

    AdapterHelper(Callback callback, boolean z) {
        this.f4859a = new Pools.SimplePool(30);
        this.f4860b = new ArrayList();
        this.f4861c = new ArrayList();
        this.f4866h = 0;
        this.f4862d = callback;
        this.f4864f = z;
        this.f4865g = new OpReorderer(this);
    }
}
