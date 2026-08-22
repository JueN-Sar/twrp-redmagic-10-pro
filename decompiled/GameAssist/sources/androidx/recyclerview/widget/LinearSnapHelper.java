package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public class LinearSnapHelper extends SnapHelper {

    /* renamed from: d, reason: collision with root package name */
    private OrientationHelper f5116d;

    /* renamed from: e, reason: collision with root package name */
    private OrientationHelper f5117e;

    private float m(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int P = layoutManager.P();
        if (P == 0) {
            return 1.0f;
        }
        View view = null;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        View view2 = null;
        for (int i4 = 0; i4 < P; i4++) {
            View O = layoutManager.O(i4);
            int p0 = layoutManager.p0(O);
            if (p0 != -1) {
                if (p0 < i3) {
                    view = O;
                    i3 = p0;
                }
                if (p0 > i2) {
                    view2 = O;
                    i2 = p0;
                }
            }
        }
        if (view == null || view2 == null) {
            return 1.0f;
        }
        int max = Math.max(orientationHelper.d(view), orientationHelper.d(view2)) - Math.min(orientationHelper.g(view), orientationHelper.g(view2));
        if (max == 0) {
            return 1.0f;
        }
        return (max * 1.0f) / ((i2 - i3) + 1);
    }

    private int n(RecyclerView.LayoutManager layoutManager, View view, OrientationHelper orientationHelper) {
        return (orientationHelper.g(view) + (orientationHelper.e(view) / 2)) - (orientationHelper.m() + (orientationHelper.n() / 2));
    }

    private int o(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper, int i2, int i3) {
        int[] d2 = d(i2, i3);
        float m2 = m(layoutManager, orientationHelper);
        if (m2 <= 0.0f) {
            return 0;
        }
        return Math.round((Math.abs(d2[0]) > Math.abs(d2[1]) ? d2[0] : d2[1]) / m2);
    }

    private View p(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
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

    private OrientationHelper q(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.f5117e;
        if (orientationHelper == null || orientationHelper.f5143a != layoutManager) {
            this.f5117e = OrientationHelper.a(layoutManager);
        }
        return this.f5117e;
    }

    private OrientationHelper r(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.f5116d;
        if (orientationHelper == null || orientationHelper.f5143a != layoutManager) {
            this.f5116d = OrientationHelper.c(layoutManager);
        }
        return this.f5116d;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int[] c(RecyclerView.LayoutManager layoutManager, View view) {
        int[] iArr = new int[2];
        if (layoutManager.q()) {
            iArr[0] = n(layoutManager, view, q(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.r()) {
            iArr[1] = n(layoutManager, view, r(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public View h(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.r()) {
            return p(layoutManager, r(layoutManager));
        }
        if (layoutManager.q()) {
            return p(layoutManager, q(layoutManager));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.SnapHelper
    public int i(RecyclerView.LayoutManager layoutManager, int i2, int i3) {
        int f2;
        View h2;
        int p0;
        int i4;
        PointF c2;
        int i5;
        int i6;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) || (f2 = layoutManager.f()) == 0 || (h2 = h(layoutManager)) == null || (p0 = layoutManager.p0(h2)) == -1 || (c2 = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager).c(f2 - 1)) == null) {
            return -1;
        }
        if (layoutManager.q()) {
            i5 = o(layoutManager, q(layoutManager), i2, 0);
            if (c2.x < 0.0f) {
                i5 = -i5;
            }
        } else {
            i5 = 0;
        }
        if (layoutManager.r()) {
            i6 = o(layoutManager, r(layoutManager), 0, i3);
            if (c2.y < 0.0f) {
                i6 = -i6;
            }
        } else {
            i6 = 0;
        }
        if (layoutManager.r()) {
            i5 = i6;
        }
        if (i5 == 0) {
            return -1;
        }
        int i7 = p0 + i5;
        int i8 = i7 >= 0 ? i7 : 0;
        return i8 >= f2 ? i4 : i8;
    }
}
