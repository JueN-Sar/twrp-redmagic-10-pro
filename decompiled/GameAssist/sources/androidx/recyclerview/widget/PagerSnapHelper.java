package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public class PagerSnapHelper extends SnapHelper {

    /* renamed from: d, reason: collision with root package name */
    private OrientationHelper f5146d;

    /* renamed from: e, reason: collision with root package name */
    private OrientationHelper f5147e;

    private int m(RecyclerView.LayoutManager layoutManager, View view, OrientationHelper orientationHelper) {
        return (orientationHelper.g(view) + (orientationHelper.e(view) / 2)) - (orientationHelper.m() + (orientationHelper.n() / 2));
    }

    private View n(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int P = layoutManager.P();
        View view = null;
        if (P == 0) {
            return null;
        }
        int m2 = orientationHelper.m() + (orientationHelper.n() / 2);
        int i2 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        for (int i3 = 0; i3 < P; i3++) {
            View O = layoutManager.O(i3);
            int abs = Math.abs((orientationHelper.g(O) + (orientationHelper.e(O) / 2)) - m2);
            if (abs < i2) {
                view = O;
                i2 = abs;
            }
        }
        return view;
    }

    private OrientationHelper o(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.f5147e;
        if (orientationHelper == null || orientationHelper.f5143a != layoutManager) {
            this.f5147e = OrientationHelper.a(layoutManager);
        }
        return this.f5147e;
    }

    private OrientationHelper p(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.r()) {
            return q(layoutManager);
        }
        if (layoutManager.q()) {
            return o(layoutManager);
        }
        return null;
    }

    private OrientationHelper q(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.f5146d;
        if (orientationHelper == null || orientationHelper.f5143a != layoutManager) {
            this.f5146d = OrientationHelper.c(layoutManager);
        }
        return this.f5146d;
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
        int[] iArr = new int[2];
        if (layoutManager.q()) {
            iArr[0] = m(layoutManager, view, o(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.r()) {
            iArr[1] = m(layoutManager, view, q(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    protected LinearSmoothScroller f(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return new LinearSmoothScroller(this.f5274a.getContext()) { // from class: androidx.recyclerview.widget.PagerSnapHelper.1
                @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
                protected void o(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                    PagerSnapHelper pagerSnapHelper = PagerSnapHelper.this;
                    int[] c2 = pagerSnapHelper.c(pagerSnapHelper.f5274a.getLayoutManager(), view);
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

                @Override // androidx.recyclerview.widget.LinearSmoothScroller
                protected int x(int i2) {
                    return Math.min(100, super.x(i2));
                }
            };
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public View h(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.r()) {
            return n(layoutManager, q(layoutManager));
        }
        if (layoutManager.q()) {
            return n(layoutManager, o(layoutManager));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int i(RecyclerView.LayoutManager layoutManager, int i2, int i3) {
        OrientationHelper p2;
        int f2 = layoutManager.f();
        if (f2 == 0 || (p2 = p(layoutManager)) == null) {
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
                int m2 = m(layoutManager, O, p2);
                if (m2 <= 0 && m2 > i5) {
                    view2 = O;
                    i5 = m2;
                }
                if (m2 >= 0 && m2 < i4) {
                    view = O;
                    i4 = m2;
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
