package androidx.recyclerview.widget;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.recyclerview.widget.ThreadUtil;
import androidx.recyclerview.widget.TileList;

/* loaded from: classes.dex */
public class AsyncListUtil<T> {

    /* renamed from: a, reason: collision with root package name */
    final Class f4891a;

    /* renamed from: b, reason: collision with root package name */
    final int f4892b;

    /* renamed from: c, reason: collision with root package name */
    final DataCallback f4893c;

    /* renamed from: d, reason: collision with root package name */
    final ViewCallback f4894d;

    /* renamed from: e, reason: collision with root package name */
    final TileList f4895e;

    /* renamed from: f, reason: collision with root package name */
    final ThreadUtil.MainThreadCallback f4896f;

    /* renamed from: g, reason: collision with root package name */
    final ThreadUtil.BackgroundCallback f4897g;

    /* renamed from: h, reason: collision with root package name */
    final int[] f4898h;

    /* renamed from: i, reason: collision with root package name */
    final int[] f4899i;

    /* renamed from: j, reason: collision with root package name */
    final int[] f4900j;

    /* renamed from: k, reason: collision with root package name */
    boolean f4901k;

    /* renamed from: l, reason: collision with root package name */
    private int f4902l;

    /* renamed from: m, reason: collision with root package name */
    int f4903m;

    /* renamed from: n, reason: collision with root package name */
    int f4904n;

    /* renamed from: o, reason: collision with root package name */
    int f4905o;

    /* renamed from: p, reason: collision with root package name */
    final SparseIntArray f4906p;

    /* renamed from: androidx.recyclerview.widget.AsyncListUtil$1, reason: invalid class name */
    class AnonymousClass1 implements ThreadUtil.MainThreadCallback<T> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AsyncListUtil f4907a;

        private boolean d(int i2) {
            return i2 == this.f4907a.f4905o;
        }

        private void e() {
            for (int i2 = 0; i2 < this.f4907a.f4895e.e(); i2++) {
                AsyncListUtil asyncListUtil = this.f4907a;
                asyncListUtil.f4897g.d(asyncListUtil.f4895e.c(i2));
            }
            this.f4907a.f4895e.b();
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void a(int i2, int i3) {
            if (d(i2)) {
                TileList.Tile d2 = this.f4907a.f4895e.d(i3);
                if (d2 != null) {
                    this.f4907a.f4897g.d(d2);
                    return;
                }
                Log.e("AsyncListUtil", "tile not found @" + i3);
            }
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void b(int i2, TileList.Tile tile) {
            if (!d(i2)) {
                this.f4907a.f4897g.d(tile);
                return;
            }
            TileList.Tile a2 = this.f4907a.f4895e.a(tile);
            if (a2 != null) {
                Log.e("AsyncListUtil", "duplicate tile @" + a2.f5319b);
                this.f4907a.f4897g.d(a2);
            }
            int i3 = tile.f5319b + tile.f5320c;
            int i4 = 0;
            while (i4 < this.f4907a.f4906p.size()) {
                int keyAt = this.f4907a.f4906p.keyAt(i4);
                if (tile.f5319b > keyAt || keyAt >= i3) {
                    i4++;
                } else {
                    this.f4907a.f4906p.removeAt(i4);
                    this.f4907a.f4894d.d(keyAt);
                }
            }
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void c(int i2, int i3) {
            if (d(i2)) {
                AsyncListUtil asyncListUtil = this.f4907a;
                asyncListUtil.f4903m = i3;
                asyncListUtil.f4894d.c();
                AsyncListUtil asyncListUtil2 = this.f4907a;
                asyncListUtil2.f4904n = asyncListUtil2.f4905o;
                e();
                AsyncListUtil asyncListUtil3 = this.f4907a;
                asyncListUtil3.f4901k = false;
                asyncListUtil3.a();
            }
        }
    }

    /* renamed from: androidx.recyclerview.widget.AsyncListUtil$2, reason: invalid class name */
    class AnonymousClass2 implements ThreadUtil.BackgroundCallback<T> {

        /* renamed from: a, reason: collision with root package name */
        private TileList.Tile f4908a;

        /* renamed from: b, reason: collision with root package name */
        final SparseBooleanArray f4909b;

        /* renamed from: c, reason: collision with root package name */
        private int f4910c;

        /* renamed from: d, reason: collision with root package name */
        private int f4911d;

        /* renamed from: e, reason: collision with root package name */
        private int f4912e;

        /* renamed from: f, reason: collision with root package name */
        private int f4913f;

        /* renamed from: g, reason: collision with root package name */
        final /* synthetic */ AsyncListUtil f4914g;

        private TileList.Tile e() {
            TileList.Tile tile = this.f4908a;
            if (tile != null) {
                this.f4908a = tile.f5321d;
                return tile;
            }
            AsyncListUtil asyncListUtil = this.f4914g;
            return new TileList.Tile(asyncListUtil.f4891a, asyncListUtil.f4892b);
        }

        private void f(TileList.Tile tile) {
            this.f4909b.put(tile.f5319b, true);
            this.f4914g.f4896f.b(this.f4910c, tile);
        }

        private void g(int i2) {
            int b2 = this.f4914g.f4893c.b();
            while (this.f4909b.size() >= b2) {
                int keyAt = this.f4909b.keyAt(0);
                SparseBooleanArray sparseBooleanArray = this.f4909b;
                int keyAt2 = sparseBooleanArray.keyAt(sparseBooleanArray.size() - 1);
                int i3 = this.f4912e - keyAt;
                int i4 = keyAt2 - this.f4913f;
                if (i3 > 0 && (i3 >= i4 || i2 == 2)) {
                    j(keyAt);
                } else {
                    if (i4 <= 0) {
                        return;
                    }
                    if (i3 >= i4 && i2 != 1) {
                        return;
                    } else {
                        j(keyAt2);
                    }
                }
            }
        }

        private int h(int i2) {
            return i2 - (i2 % this.f4914g.f4892b);
        }

        private boolean i(int i2) {
            return this.f4909b.get(i2);
        }

        private void j(int i2) {
            this.f4909b.delete(i2);
            this.f4914g.f4896f.a(this.f4910c, i2);
        }

        private void k(int i2, int i3, int i4, boolean z) {
            int i5 = i2;
            while (i5 <= i3) {
                this.f4914g.f4897g.b(z ? (i3 + i2) - i5 : i5, i4);
                i5 += this.f4914g.f4892b;
            }
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void a(int i2, int i3, int i4, int i5, int i6) {
            if (i2 > i3) {
                return;
            }
            int h2 = h(i2);
            int h3 = h(i3);
            this.f4912e = h(i4);
            int h4 = h(i5);
            this.f4913f = h4;
            if (i6 == 1) {
                k(this.f4912e, h3, i6, true);
                k(h3 + this.f4914g.f4892b, this.f4913f, i6, false);
            } else {
                k(h2, h4, i6, false);
                k(this.f4912e, h2 - this.f4914g.f4892b, i6, true);
            }
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void b(int i2, int i3) {
            if (i(i2)) {
                return;
            }
            TileList.Tile e2 = e();
            e2.f5319b = i2;
            int min = Math.min(this.f4914g.f4892b, this.f4911d - i2);
            e2.f5320c = min;
            this.f4914g.f4893c.a(e2.f5318a, e2.f5319b, min);
            g(i3);
            f(e2);
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void c(int i2) {
            this.f4910c = i2;
            this.f4909b.clear();
            int d2 = this.f4914g.f4893c.d();
            this.f4911d = d2;
            this.f4914g.f4896f.c(this.f4910c, d2);
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void d(TileList.Tile tile) {
            this.f4914g.f4893c.c(tile.f5318a, tile.f5320c);
            tile.f5321d = this.f4908a;
            this.f4908a = tile;
        }
    }

    public static abstract class DataCallback<T> {
        public abstract void a(Object[] objArr, int i2, int i3);

        public int b() {
            return 10;
        }

        public void c(Object[] objArr, int i2) {
        }

        public abstract int d();
    }

    public static abstract class ViewCallback {
        public void a(int[] iArr, int[] iArr2, int i2) {
            int i3 = iArr[1];
            int i4 = iArr[0];
            int i5 = (i3 - i4) + 1;
            int i6 = i5 / 2;
            iArr2[0] = i4 - (i2 == 1 ? i5 : i6);
            if (i2 != 2) {
                i5 = i6;
            }
            iArr2[1] = i3 + i5;
        }

        public abstract void b(int[] iArr);

        public abstract void c();

        public abstract void d(int i2);
    }

    void a() {
        int i2;
        this.f4894d.b(this.f4898h);
        int[] iArr = this.f4898h;
        int i3 = iArr[0];
        int i4 = iArr[1];
        if (i3 > i4 || i3 < 0 || i4 >= this.f4903m) {
            return;
        }
        if (this.f4901k) {
            int[] iArr2 = this.f4899i;
            if (i3 > iArr2[1] || (i2 = iArr2[0]) > i4) {
                this.f4902l = 0;
            } else if (i3 < i2) {
                this.f4902l = 1;
            } else if (i3 > i2) {
                this.f4902l = 2;
            }
        } else {
            this.f4902l = 0;
        }
        int[] iArr3 = this.f4899i;
        iArr3[0] = i3;
        iArr3[1] = i4;
        this.f4894d.a(iArr, this.f4900j, this.f4902l);
        int[] iArr4 = this.f4900j;
        iArr4[0] = Math.min(this.f4898h[0], Math.max(iArr4[0], 0));
        int[] iArr5 = this.f4900j;
        iArr5[1] = Math.max(this.f4898h[1], Math.min(iArr5[1], this.f4903m - 1));
        ThreadUtil.BackgroundCallback backgroundCallback = this.f4897g;
        int[] iArr6 = this.f4898h;
        int i5 = iArr6[0];
        int i6 = iArr6[1];
        int[] iArr7 = this.f4900j;
        backgroundCallback.a(i5, i6, iArr7[0], iArr7[1], this.f4902l);
    }
}
