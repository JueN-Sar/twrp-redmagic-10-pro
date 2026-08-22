package com.google.android.material.carousel;

import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public class CarouselSnapHelper extends SnapHelper {

    /* renamed from: d, reason: collision with root package name */
    private final boolean f14147d;

    /* renamed from: e, reason: collision with root package name */
    private RecyclerView f14148e;

    /* JADX INFO: Access modifiers changed from: private */
    public int[] o(RecyclerView.LayoutManager layoutManager, View view, boolean z) {
        if (!(layoutManager instanceof CarouselLayoutManager)) {
            return new int[]{0, 0};
        }
        int p2 = p(view, (CarouselLayoutManager) layoutManager, z);
        return layoutManager.q() ? new int[]{p2, 0} : layoutManager.r() ? new int[]{0, p2} : new int[]{0, 0};
    }

    private int p(View view, CarouselLayoutManager carouselLayoutManager, boolean z) {
        return carouselLayoutManager.z2(carouselLayoutManager.p0(view), z);
    }

    private View q(RecyclerView.LayoutManager layoutManager) {
        int P = layoutManager.P();
        View view = null;
        if (P != 0 && (layoutManager instanceof CarouselLayoutManager)) {
            CarouselLayoutManager carouselLayoutManager = (CarouselLayoutManager) layoutManager;
            int i2 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            for (int i3 = 0; i3 < P; i3++) {
                View O = layoutManager.O(i3);
                int abs = Math.abs(carouselLayoutManager.z2(layoutManager.p0(O), false));
                if (abs < i2) {
                    view = O;
                    i2 = abs;
                }
            }
        }
        return view;
    }

    private boolean r(RecyclerView.LayoutManager layoutManager, int i2, int i3) {
        return layoutManager.q() ? i2 > 0 : i3 > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean s(RecyclerView.LayoutManager layoutManager) {
        PointF c2;
        int f2 = layoutManager.f();
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) || (c2 = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager).c(f2 - 1)) == null) {
            return false;
        }
        return c2.x < 0.0f || c2.y < 0.0f;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int[] c(RecyclerView.LayoutManager layoutManager, View view) {
        return o(layoutManager, view, false);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    protected RecyclerView.SmoothScroller e(final RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return new LinearSmoothScroller(this.f14148e.getContext()) { // from class: com.google.android.material.carousel.CarouselSnapHelper.1
                @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
                protected void o(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                    if (CarouselSnapHelper.this.f14148e != null) {
                        CarouselSnapHelper carouselSnapHelper = CarouselSnapHelper.this;
                        int[] o2 = carouselSnapHelper.o(carouselSnapHelper.f14148e.getLayoutManager(), view, true);
                        int i2 = o2[0];
                        int i3 = o2[1];
                        int w = w(Math.max(Math.abs(i2), Math.abs(i3)));
                        if (w > 0) {
                            action.d(i2, i3, w, this.f5109j);
                        }
                    }
                }

                @Override // androidx.recyclerview.widget.LinearSmoothScroller
                protected float v(DisplayMetrics displayMetrics) {
                    float f2;
                    float f3;
                    if (layoutManager.r()) {
                        f2 = displayMetrics.densityDpi;
                        f3 = 50.0f;
                    } else {
                        f2 = displayMetrics.densityDpi;
                        f3 = 100.0f;
                    }
                    return f3 / f2;
                }
            };
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public View h(RecyclerView.LayoutManager layoutManager) {
        return q(layoutManager);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int i(RecyclerView.LayoutManager layoutManager, int i2, int i3) {
        int f2;
        if (!this.f14147d || (f2 = layoutManager.f()) == 0) {
            return -1;
        }
        int P = layoutManager.P();
        View view = null;
        int i4 = Integer.MAX_VALUE;
        int i5 = Integer.MIN_VALUE;
        View view2 = null;
        for (int i6 = 0; i6 < P; i6++) {
            View O = layoutManager.O(i6);
            if (O != null) {
                int p2 = p(O, (CarouselLayoutManager) layoutManager, false);
                if (p2 <= 0 && p2 > i5) {
                    view2 = O;
                    i5 = p2;
                }
                if (p2 >= 0 && p2 < i4) {
                    view = O;
                    i4 = p2;
                }
            }
        }
        boolean r2 = r(layoutManager, i2, i3);
        if (r2 && view != null) {
            return layoutManager.p0(view);
        }
        if (!r2 && view2 != null) {
            return layoutManager.p0(view2);
        }
        if (r2) {
            view = view2;
        }
        if (view == null) {
            return -1;
        }
        int p0 = layoutManager.p0(view) + (s(layoutManager) == r2 ? -1 : 1);
        if (p0 < 0 || p0 >= f2) {
            return -1;
        }
        return p0;
    }
}
