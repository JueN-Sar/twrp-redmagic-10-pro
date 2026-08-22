package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.Positioning;
import com.google.android.material.transformation.FabTransformationBehavior;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* loaded from: classes.dex */
public class FabTransformationSheetBehavior extends FabTransformationBehavior {

    /* renamed from: o, reason: collision with root package name */
    private Map f15554o;

    public FabTransformationSheetBehavior() {
    }

    private void l0(View view, boolean z) {
        ViewParent parent = view.getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                this.f15554o = new HashMap(childCount);
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = coordinatorLayout.getChildAt(i2);
                boolean z2 = (childAt.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) && (((CoordinatorLayout.LayoutParams) childAt.getLayoutParams()).f() instanceof FabTransformationScrimBehavior);
                if (childAt != view && !z2) {
                    if (z) {
                        this.f15554o.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        ViewCompat.s0(childAt, 4);
                    } else {
                        Map map = this.f15554o;
                        if (map != null && map.containsKey(childAt)) {
                            ViewCompat.s0(childAt, ((Integer) this.f15554o.get(childAt)).intValue());
                        }
                    }
                }
            }
            if (z) {
                return;
            }
            this.f15554o = null;
        }
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior, com.google.android.material.transformation.ExpandableBehavior
    protected boolean M(View view, View view2, boolean z, boolean z2) {
        l0(view2, z);
        return super.M(view, view2, z, z2);
    }

    @Override // com.google.android.material.transformation.FabTransformationBehavior
    protected FabTransformationBehavior.FabTransformationSpec j0(Context context, boolean z) {
        int i2 = z ? R.animator.mtrl_fab_transformation_sheet_expand_spec : R.animator.mtrl_fab_transformation_sheet_collapse_spec;
        FabTransformationBehavior.FabTransformationSpec fabTransformationSpec = new FabTransformationBehavior.FabTransformationSpec();
        fabTransformationSpec.f15547a = MotionSpec.d(context, i2);
        fabTransformationSpec.f15548b = new Positioning(17, 0.0f, 0.0f);
        return fabTransformationSpec;
    }

    public FabTransformationSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
