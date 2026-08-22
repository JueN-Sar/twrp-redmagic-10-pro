package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R;

/* loaded from: classes.dex */
class ItemTouchUIUtilImpl implements ItemTouchUIUtil {

    /* renamed from: a, reason: collision with root package name */
    static final ItemTouchUIUtil f5072a = new ItemTouchUIUtilImpl();

    ItemTouchUIUtilImpl() {
    }

    private static float e(RecyclerView recyclerView, View view) {
        int childCount = recyclerView.getChildCount();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            if (childAt != view) {
                float r2 = ViewCompat.r(childAt);
                if (r2 > f2) {
                    f2 = r2;
                }
            }
        }
        return f2;
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void a(View view) {
        Object tag = view.getTag(R.id.item_touch_helper_previous_elevation);
        if (tag instanceof Float) {
            ViewCompat.q0(view, ((Float) tag).floatValue());
        }
        view.setTag(R.id.item_touch_helper_previous_elevation, null);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void b(View view) {
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void c(Canvas canvas, RecyclerView recyclerView, View view, float f2, float f3, int i2, boolean z) {
        if (z && view.getTag(R.id.item_touch_helper_previous_elevation) == null) {
            Float valueOf = Float.valueOf(ViewCompat.r(view));
            ViewCompat.q0(view, e(recyclerView, view) + 1.0f);
            view.setTag(R.id.item_touch_helper_previous_elevation, valueOf);
        }
        view.setTranslationX(f2);
        view.setTranslationY(f3);
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void d(Canvas canvas, RecyclerView recyclerView, View view, float f2, float f3, int i2, boolean z) {
    }
}
