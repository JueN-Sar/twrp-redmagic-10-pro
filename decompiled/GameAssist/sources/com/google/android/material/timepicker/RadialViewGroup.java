package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RelativeCornerSize;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class RadialViewGroup extends ConstraintLayout {
    static final int LEVEL_1 = 1;
    static final int LEVEL_2 = 2;
    static final float LEVEL_RADIUS_RATIO = 0.66f;
    private static final String SKIP_TAG = "skip";
    private MaterialShapeDrawable background;
    private int radius;
    private final Runnable updateLayoutParametersRunnable;

    public RadialViewGroup(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void D(List list, ConstraintSet constraintSet, int i2) {
        Iterator it = list.iterator();
        float f2 = 0.0f;
        while (it.hasNext()) {
            constraintSet.r(((View) it.next()).getId(), R.id.circle_center, i2, f2);
            f2 += 360.0f / list.size();
        }
    }

    private Drawable E() {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.background = materialShapeDrawable;
        materialShapeDrawable.Y(new RelativeCornerSize(0.5f));
        this.background.a0(ColorStateList.valueOf(-1));
        return this.background;
    }

    private static boolean I(View view) {
        return SKIP_TAG.equals(view.getTag());
    }

    private void K() {
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.updateLayoutParametersRunnable);
            handler.post(this.updateLayoutParametersRunnable);
        }
    }

    int F(int i2) {
        int i3 = this.radius;
        return i2 == 2 ? Math.round(i3 * LEVEL_RADIUS_RATIO) : i3;
    }

    public int G() {
        return this.radius;
    }

    public void H(int i2) {
        this.radius = i2;
        J();
    }

    protected void J() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.o(this);
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getId() != R.id.circle_center && !I(childAt)) {
                int i3 = (Integer) childAt.getTag(R.id.material_clock_level);
                if (i3 == null) {
                    i3 = 1;
                }
                if (!hashMap.containsKey(i3)) {
                    hashMap.put(i3, new ArrayList());
                }
                ((List) hashMap.get(i3)).add(childAt);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            D((List) entry.getValue(), constraintSet, F(((Integer) entry.getKey()).intValue()));
        }
        constraintSet.i(this);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (view.getId() == -1) {
            view.setId(ViewCompat.i());
        }
        K();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        J();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        K();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        this.background.a0(ColorStateList.valueOf(i2));
    }

    public RadialViewGroup(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        LayoutInflater.from(context).inflate(R.layout.material_radial_view_group, this);
        ViewCompat.m0(this, E());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RadialViewGroup, i2, 0);
        this.radius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RadialViewGroup_materialCircleRadius, 0);
        this.updateLayoutParametersRunnable = new Runnable() { // from class: com.google.android.material.timepicker.c
            @Override // java.lang.Runnable
            public final void run() {
                RadialViewGroup.this.J();
            }
        };
        obtainStyledAttributes.recycle();
    }
}
