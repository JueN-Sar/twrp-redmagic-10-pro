package androidx.recyclerview.widget;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public abstract class SnapHelper extends RecyclerView.OnFlingListener {

    /* renamed from: a, reason: collision with root package name */
    RecyclerView f5274a;

    /* renamed from: b, reason: collision with root package name */
    private Scroller f5275b;

    /* renamed from: c, reason: collision with root package name */
    private final RecyclerView.OnScrollListener f5276c = new RecyclerView.OnScrollListener() { // from class: androidx.recyclerview.widget.SnapHelper.1

        /* renamed from: a, reason: collision with root package name */
        boolean f5277a = false;

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void a(RecyclerView recyclerView, int i2) {
            super.a(recyclerView, i2);
            if (i2 == 0 && this.f5277a) {
                this.f5277a = false;
                SnapHelper.this.l();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void b(RecyclerView recyclerView, int i2, int i3) {
            if (i2 == 0 && i3 == 0) {
                return;
            }
            this.f5277a = true;
        }
    };

    private void g() {
        this.f5274a.c1(this.f5276c);
        this.f5274a.setOnFlingListener(null);
    }

    private void j() {
        if (this.f5274a.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.f5274a.l(this.f5276c);
        this.f5274a.setOnFlingListener(this);
    }

    private boolean k(RecyclerView.LayoutManager layoutManager, int i2, int i3) {
        RecyclerView.SmoothScroller e2;
        int i4;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) || (e2 = e(layoutManager)) == null || (i4 = i(layoutManager, i2, i3)) == -1) {
            return false;
        }
        e2.p(i4);
        layoutManager.S1(e2);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnFlingListener
    public boolean a(int i2, int i3) {
        RecyclerView.LayoutManager layoutManager = this.f5274a.getLayoutManager();
        if (layoutManager == null || this.f5274a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.f5274a.getMinFlingVelocity();
        return (Math.abs(i3) > minFlingVelocity || Math.abs(i2) > minFlingVelocity) && k(layoutManager, i2, i3);
    }

    public void b(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f5274a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            g();
        }
        this.f5274a = recyclerView;
        if (recyclerView != null) {
            j();
            this.f5275b = new Scroller(this.f5274a.getContext(), new DecelerateInterpolator());
            l();
        }
    }

    public abstract int[] c(RecyclerView.LayoutManager layoutManager, View view);

    public int[] d(int i2, int i3) {
        this.f5275b.fling(0, 0, i2, i3, Integer.MIN_VALUE, Api.BaseClientBuilder.API_PRIORITY_OTHER, Integer.MIN_VALUE, Api.BaseClientBuilder.API_PRIORITY_OTHER);
        return new int[]{this.f5275b.getFinalX(), this.f5275b.getFinalY()};
    }

    protected RecyclerView.SmoothScroller e(RecyclerView.LayoutManager layoutManager) {
        return f(layoutManager);
    }

    protected LinearSmoothScroller f(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return new LinearSmoothScroller(this.f5274a.getContext()) { // from class: androidx.recyclerview.widget.SnapHelper.2
                @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
                protected void o(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                    SnapHelper snapHelper = SnapHelper.this;
                    RecyclerView recyclerView = snapHelper.f5274a;
                    if (recyclerView == null) {
                        return;
                    }
                    int[] c2 = snapHelper.c(recyclerView.getLayoutManager(), view);
                    int i2 = c2[0];
                    int i3 = c2[1];
                    int w = w(Math.max(Math.abs(i2), Math.abs(i3)));
                    if (w > 0) {
                        action.d(i2, i3, w, this.f5109j);
                    }
                }

                @Override // androidx.recyclerview.widget.LinearSmoothScroller
                protected float v(DisplayMetrics displayMetrics) {
                    return 100.0f / displayMetrics.densityDpi;
                }
            };
        }
        return null;
    }

    public abstract View h(RecyclerView.LayoutManager layoutManager);

    public abstract int i(RecyclerView.LayoutManager layoutManager, int i2, int i3);

    void l() {
        RecyclerView.LayoutManager layoutManager;
        View h2;
        RecyclerView recyclerView = this.f5274a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (h2 = h(layoutManager)) == null) {
            return;
        }
        int[] c2 = c(layoutManager, h2);
        int i2 = c2[0];
        if (i2 == 0 && c2[1] == 0) {
            return;
        }
        this.f5274a.o1(i2, c2[1]);
    }
}
