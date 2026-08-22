package cn.nubia.gameassist.dessert.policy.performancemonitor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class PerformanceMonitorLayout extends LinearLayout {
    private int mPerformanceMonitorChildLeftOffset;
    private int mPerformanceMonitorChildRightOffset;

    public PerformanceMonitorLayout(Context context) {
        this(context, null);
    }

    private void a(Context context) {
        this.mPerformanceMonitorChildLeftOffset = getResources().getInteger(R.integer.performance_monitor_child_left_offset);
        this.mPerformanceMonitorChildRightOffset = getResources().getInteger(R.integer.performance_monitor_child_right_offset);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        for (int i6 = 0; i6 < getChildCount(); i6++) {
            View childAt = getChildAt(i6);
            childAt.layout(childAt.getLeft() - this.mPerformanceMonitorChildLeftOffset, childAt.getTop(), childAt.getRight() + this.mPerformanceMonitorChildRightOffset, childAt.getBottom());
        }
    }

    public PerformanceMonitorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PerformanceMonitorLayout(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PerformanceMonitorLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a(context);
    }
}
