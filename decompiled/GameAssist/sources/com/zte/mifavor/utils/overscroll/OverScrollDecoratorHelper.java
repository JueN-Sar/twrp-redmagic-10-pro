package com.zte.mifavor.utils.overscroll;

import android.util.Log;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.HorizontalScrollView;
import androidx.appcompat.app.RecycleListView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.app.AlertController;
import com.zte.mifavor.androidx.widget.NestedScrollView;
import com.zte.mifavor.utils.SpringAnimationCommon;
import com.zte.mifavor.utils.overscroll.adapters.AbsListViewOverScrollDecorAdapter;
import com.zte.mifavor.utils.overscroll.adapters.HorizontalScrollViewOverScrollDecorAdapter;
import com.zte.mifavor.utils.overscroll.adapters.NestedScrollViewOverScrollDecorAdapter;
import com.zte.mifavor.utils.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;
import com.zte.mifavor.utils.overscroll.adapters.ScrollViewOverScrollDecorAdapter;
import com.zte.mifavor.widget.GridView;
import com.zte.mifavor.widget.ListView;
import com.zte.mifavor.widget.ScrollView;

/* loaded from: classes2.dex */
public class OverScrollDecoratorHelper {

    /* renamed from: b, reason: collision with root package name */
    private SpringAnimation f17495b;

    /* renamed from: e, reason: collision with root package name */
    private final View f17498e;

    /* renamed from: c, reason: collision with root package name */
    private boolean f17496c = true;

    /* renamed from: d, reason: collision with root package name */
    private float f17497d = SpringAnimationCommon.f17440s;

    /* renamed from: a, reason: collision with root package name */
    private VelocityTracker f17494a = VelocityTracker.obtain();

    public OverScrollDecoratorHelper(View view) {
        RecyclerView.LayoutManager layoutManager;
        this.f17498e = view;
        DynamicAnimation.ViewProperty viewProperty = DynamicAnimation.f3652o;
        if ((view instanceof com.zte.mifavor.androidx.widget.RecyclerView) && (layoutManager = ((com.zte.mifavor.androidx.widget.RecyclerView) view).getLayoutManager()) != null && layoutManager.q() && !layoutManager.r()) {
            viewProperty = DynamicAnimation.f3651n;
        }
        a(view, viewProperty);
    }

    private void a(View view, FloatPropertyCompat floatPropertyCompat) {
        float q2 = SpringAnimationCommon.q(view.getContext());
        SpringAnimation springAnimation = new SpringAnimation(view, floatPropertyCompat, 0.0f);
        this.f17495b = springAnimation;
        springAnimation.p().f(q2);
        this.f17495b.p().d(this.f17497d);
        Log.d("Z#QScroll-DecoHelper", "set Spring Animation Property out. stiffness = " + q2 + ", mDampingRatio = " + this.f17497d);
    }

    public IOverScrollDecor b() {
        AlertController.RecycleListView recycleListView = this.f17498e;
        if (recycleListView instanceof com.zte.mifavor.androidx.widget.RecyclerView) {
            com.zte.mifavor.androidx.widget.RecyclerView recyclerView = (com.zte.mifavor.androidx.widget.RecyclerView) recycleListView;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            return (layoutManager == null || !layoutManager.q() || layoutManager.r()) ? new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView), this.f17495b, this.f17494a) : new HorizontalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView), this.f17495b, this.f17494a);
        }
        if (recycleListView instanceof ListView) {
            return new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter((ListView) recycleListView), this.f17495b, this.f17494a);
        }
        if (recycleListView instanceof RecycleListView) {
            return new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter((RecycleListView) recycleListView), this.f17495b, this.f17494a);
        }
        if (recycleListView instanceof com.zte.mifavor.custom.internal.app.RecycleListView) {
            return new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter((com.zte.mifavor.custom.internal.app.RecycleListView) recycleListView), this.f17495b, this.f17494a);
        }
        if (recycleListView instanceof GridView) {
            return new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter((GridView) recycleListView), this.f17495b, this.f17494a);
        }
        if (recycleListView instanceof ScrollView) {
            return new VerticalOverScrollBounceEffectDecorator(new ScrollViewOverScrollDecorAdapter((ScrollView) recycleListView), this.f17495b, this.f17494a);
        }
        if (recycleListView instanceof HorizontalScrollView) {
            return new HorizontalOverScrollBounceEffectDecorator(new HorizontalScrollViewOverScrollDecorAdapter((HorizontalScrollView) recycleListView), this.f17495b, this.f17494a);
        }
        if (recycleListView instanceof NestedScrollView) {
            return new VerticalOverScrollBounceEffectDecorator(new NestedScrollViewOverScrollDecorAdapter((NestedScrollView) recycleListView), this.f17495b, this.f17494a);
        }
        Log.e("Z#QScroll-DecoHelper", "setUpOverScroll error. mView = " + this.f17498e);
        return null;
    }
}
