package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
class ScrollbarHelper {
    static int a(RecyclerView.State state, OrientationHelper orientationHelper, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z) {
        if (layoutManager.P() == 0 || state.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(layoutManager.p0(view) - layoutManager.p0(view2)) + 1;
        }
        return Math.min(orientationHelper.n(), orientationHelper.d(view2) - orientationHelper.g(view));
    }

    static int b(RecyclerView.State state, OrientationHelper orientationHelper, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z, boolean z2) {
        if (layoutManager.P() == 0 || state.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int max = z2 ? Math.max(0, (state.b() - Math.max(layoutManager.p0(view), layoutManager.p0(view2))) - 1) : Math.max(0, Math.min(layoutManager.p0(view), layoutManager.p0(view2)));
        if (z) {
            return Math.round((max * (Math.abs(orientationHelper.d(view2) - orientationHelper.g(view)) / (Math.abs(layoutManager.p0(view) - layoutManager.p0(view2)) + 1))) + (orientationHelper.m() - orientationHelper.g(view)));
        }
        return max;
    }

    static int c(RecyclerView.State state, OrientationHelper orientationHelper, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z) {
        if (layoutManager.P() == 0 || state.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return state.b();
        }
        return (int) (((orientationHelper.d(view2) - orientationHelper.g(view)) / (Math.abs(layoutManager.p0(view) - layoutManager.p0(view2)) + 1)) * state.b());
    }
}
