package cn.nubia.gameassist.dessert.policy.performancemonitor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class PerformanceMonitorItemView extends TextView {
    private static final String TAG = "PerformanceMonitorItemView";

    public PerformanceMonitorItemView(Context context) {
        this(context, null);
    }

    public void click() {
        GaLog.a(TAG, "click: ");
    }

    public PerformanceMonitorItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PerformanceMonitorItemView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PerformanceMonitorItemView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
