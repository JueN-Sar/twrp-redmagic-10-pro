package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.expandable.ExpandableWidget;
import java.util.List;

@Deprecated
/* loaded from: classes.dex */
public abstract class ExpandableBehavior extends CoordinatorLayout.Behavior<View> {

    /* renamed from: c, reason: collision with root package name */
    private int f15523c;

    public ExpandableBehavior() {
        this.f15523c = 0;
    }

    private boolean K(boolean z) {
        if (!z) {
            return this.f15523c == 1;
        }
        int i2 = this.f15523c;
        return i2 == 0 || i2 == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected ExpandableWidget L(CoordinatorLayout coordinatorLayout, View view) {
        List v = coordinatorLayout.v(view);
        int size = v.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view2 = (View) v.get(i2);
            if (j(coordinatorLayout, view, view2)) {
                return (ExpandableWidget) view2;
            }
        }
        return null;
    }

    protected abstract boolean M(View view, View view2, boolean z, boolean z2);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public abstract boolean j(CoordinatorLayout coordinatorLayout, View view, View view2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean m(CoordinatorLayout coordinatorLayout, View view, View view2) {
        ExpandableWidget expandableWidget = (ExpandableWidget) view2;
        if (!K(expandableWidget.a())) {
            return false;
        }
        this.f15523c = expandableWidget.a() ? 1 : 2;
        return M((View) expandableWidget, view, expandableWidget.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean q(CoordinatorLayout coordinatorLayout, final View view, int i2) {
        final ExpandableWidget L;
        if (ViewCompat.N(view) || (L = L(coordinatorLayout, view)) == null || !K(L.a())) {
            return false;
        }
        final int i3 = L.a() ? 1 : 2;
        this.f15523c = i3;
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.material.transformation.ExpandableBehavior.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                if (ExpandableBehavior.this.f15523c == i3) {
                    ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                    ExpandableWidget expandableWidget = L;
                    expandableBehavior.M((View) expandableWidget, view, expandableWidget.a(), false);
                }
                return false;
            }
        });
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f15523c = 0;
    }
}
