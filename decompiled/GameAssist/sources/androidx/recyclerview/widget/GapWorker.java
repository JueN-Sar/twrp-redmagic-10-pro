package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import androidx.core.os.TraceCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class GapWorker implements Runnable {

    /* renamed from: k, reason: collision with root package name */
    static final ThreadLocal f5005k = new ThreadLocal();

    /* renamed from: l, reason: collision with root package name */
    static Comparator f5006l = new Comparator<Task>() { // from class: androidx.recyclerview.widget.GapWorker.1
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Task task, Task task2) {
            RecyclerView recyclerView = task.f5018d;
            if ((recyclerView == null) != (task2.f5018d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = task.f5015a;
            if (z != task2.f5015a) {
                return z ? -1 : 1;
            }
            int i2 = task2.f5016b - task.f5016b;
            if (i2 != 0) {
                return i2;
            }
            int i3 = task.f5017c - task2.f5017c;
            if (i3 != 0) {
                return i3;
            }
            return 0;
        }
    };

    /* renamed from: h, reason: collision with root package name */
    long f5008h;

    /* renamed from: i, reason: collision with root package name */
    long f5009i;

    /* renamed from: c, reason: collision with root package name */
    ArrayList f5007c = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    private ArrayList f5010j = new ArrayList();

    @SuppressLint({"VisibleForTests"})
    static class LayoutPrefetchRegistryImpl implements RecyclerView.LayoutManager.LayoutPrefetchRegistry {

        /* renamed from: a, reason: collision with root package name */
        int f5011a;

        /* renamed from: b, reason: collision with root package name */
        int f5012b;

        /* renamed from: c, reason: collision with root package name */
        int[] f5013c;

        /* renamed from: d, reason: collision with root package name */
        int f5014d;

        LayoutPrefetchRegistryImpl() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry
        public void a(int i2, int i3) {
            if (i2 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i3 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i4 = this.f5014d;
            int i5 = i4 * 2;
            int[] iArr = this.f5013c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f5013c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i5 >= iArr.length) {
                int[] iArr3 = new int[i4 * 4];
                this.f5013c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f5013c;
            iArr4[i5] = i2;
            iArr4[i5 + 1] = i3;
            this.f5014d++;
        }

        void b() {
            int[] iArr = this.f5013c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f5014d = 0;
        }

        void c(RecyclerView recyclerView, boolean z) {
            this.f5014d = 0;
            int[] iArr = this.f5013c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.mLayout;
            if (recyclerView.mAdapter == null || layoutManager == null || !layoutManager.C0()) {
                return;
            }
            if (z) {
                if (!recyclerView.mAdapterHelper.p()) {
                    layoutManager.v(recyclerView.mAdapter.m(), this);
                }
            } else if (!recyclerView.p0()) {
                layoutManager.u(this.f5011a, this.f5012b, recyclerView.mState, this);
            }
            int i2 = this.f5014d;
            if (i2 > layoutManager.f5179m) {
                layoutManager.f5179m = i2;
                layoutManager.f5180n = z;
                recyclerView.mRecycler.K();
            }
        }

        boolean d(int i2) {
            if (this.f5013c != null) {
                int i3 = this.f5014d * 2;
                for (int i4 = 0; i4 < i3; i4 += 2) {
                    if (this.f5013c[i4] == i2) {
                        return true;
                    }
                }
            }
            return false;
        }

        void e(int i2, int i3) {
            this.f5011a = i2;
            this.f5012b = i3;
        }
    }

    static class Task {

        /* renamed from: a, reason: collision with root package name */
        public boolean f5015a;

        /* renamed from: b, reason: collision with root package name */
        public int f5016b;

        /* renamed from: c, reason: collision with root package name */
        public int f5017c;

        /* renamed from: d, reason: collision with root package name */
        public RecyclerView f5018d;

        /* renamed from: e, reason: collision with root package name */
        public int f5019e;

        Task() {
        }

        public void a() {
            this.f5015a = false;
            this.f5016b = 0;
            this.f5017c = 0;
            this.f5018d = null;
            this.f5019e = 0;
        }
    }

    GapWorker() {
    }

    private void b() {
        Task task;
        int size = this.f5007c.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            RecyclerView recyclerView = (RecyclerView) this.f5007c.get(i3);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.mPrefetchRegistry.c(recyclerView, false);
                i2 += recyclerView.mPrefetchRegistry.f5014d;
            }
        }
        this.f5010j.ensureCapacity(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView recyclerView2 = (RecyclerView) this.f5007c.get(i5);
            if (recyclerView2.getWindowVisibility() == 0) {
                LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView2.mPrefetchRegistry;
                int abs = Math.abs(layoutPrefetchRegistryImpl.f5011a) + Math.abs(layoutPrefetchRegistryImpl.f5012b);
                for (int i6 = 0; i6 < layoutPrefetchRegistryImpl.f5014d * 2; i6 += 2) {
                    if (i4 >= this.f5010j.size()) {
                        task = new Task();
                        this.f5010j.add(task);
                    } else {
                        task = (Task) this.f5010j.get(i4);
                    }
                    int[] iArr = layoutPrefetchRegistryImpl.f5013c;
                    int i7 = iArr[i6 + 1];
                    task.f5015a = i7 <= abs;
                    task.f5016b = abs;
                    task.f5017c = i7;
                    task.f5018d = recyclerView2;
                    task.f5019e = iArr[i6];
                    i4++;
                }
            }
        }
        Collections.sort(this.f5010j, f5006l);
    }

    private void c(Task task, long j2) {
        RecyclerView.ViewHolder i2 = i(task.f5018d, task.f5019e, task.f5015a ? Long.MAX_VALUE : j2);
        if (i2 == null || i2.f5253b == null || !i2.t() || i2.u()) {
            return;
        }
        h((RecyclerView) i2.f5253b.get(), j2);
    }

    private void d(long j2) {
        for (int i2 = 0; i2 < this.f5010j.size(); i2++) {
            Task task = (Task) this.f5010j.get(i2);
            if (task.f5018d == null) {
                return;
            }
            c(task, j2);
            task.a();
        }
    }

    static boolean e(RecyclerView recyclerView, int i2) {
        int j2 = recyclerView.mChildHelper.j();
        for (int i3 = 0; i3 < j2; i3++) {
            RecyclerView.ViewHolder i0 = RecyclerView.i0(recyclerView.mChildHelper.i(i3));
            if (i0.f5254c == i2 && !i0.u()) {
                return true;
            }
        }
        return false;
    }

    private void h(RecyclerView recyclerView, long j2) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.j() != 0) {
            recyclerView.X0();
        }
        LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView.mPrefetchRegistry;
        layoutPrefetchRegistryImpl.c(recyclerView, true);
        if (layoutPrefetchRegistryImpl.f5014d != 0) {
            try {
                TraceCompat.a("RV Nested Prefetch");
                recyclerView.mState.f(recyclerView.mAdapter);
                for (int i2 = 0; i2 < layoutPrefetchRegistryImpl.f5014d * 2; i2 += 2) {
                    i(recyclerView, layoutPrefetchRegistryImpl.f5013c[i2], j2);
                }
            } finally {
                TraceCompat.b();
            }
        }
    }

    private RecyclerView.ViewHolder i(RecyclerView recyclerView, int i2, long j2) {
        if (e(recyclerView, i2)) {
            return null;
        }
        RecyclerView.Recycler recycler = recyclerView.mRecycler;
        try {
            recyclerView.J0();
            RecyclerView.ViewHolder I = recycler.I(i2, false, j2);
            if (I != null) {
                if (!I.t() || I.u()) {
                    recycler.a(I, false);
                } else {
                    recycler.B(I.f5252a);
                }
            }
            recyclerView.L0(false);
            return I;
        } catch (Throwable th) {
            recyclerView.L0(false);
            throw th;
        }
    }

    public void a(RecyclerView recyclerView) {
        this.f5007c.add(recyclerView);
    }

    void f(RecyclerView recyclerView, int i2, int i3) {
        if (recyclerView.isAttachedToWindow() && this.f5008h == 0) {
            this.f5008h = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.mPrefetchRegistry.e(i2, i3);
    }

    void g(long j2) {
        b();
        d(j2);
    }

    public void j(RecyclerView recyclerView) {
        this.f5007c.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            TraceCompat.a("RV Prefetch");
            if (!this.f5007c.isEmpty()) {
                int size = this.f5007c.size();
                long j2 = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    RecyclerView recyclerView = (RecyclerView) this.f5007c.get(i2);
                    if (recyclerView.getWindowVisibility() == 0) {
                        j2 = Math.max(recyclerView.getDrawingTime(), j2);
                    }
                }
                if (j2 != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(j2) + this.f5009i);
                    this.f5008h = 0L;
                    TraceCompat.b();
                }
            }
        } finally {
            this.f5008h = 0L;
            TraceCompat.b();
        }
    }
}
