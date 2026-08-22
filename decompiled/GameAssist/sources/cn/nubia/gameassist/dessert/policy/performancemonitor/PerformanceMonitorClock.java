package cn.nubia.gameassist.dessert.policy.performancemonitor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextClock;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class PerformanceMonitorClock extends TextClock {
    private static final String TAG = "PerformanceMonitorClock";

    public PerformanceMonitorClock(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextClock
    public boolean is24HourModeEnabled() {
        return true;
    }

    @Override // android.widget.TextClock, android.view.View
    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (Exception e2) {
            GaLog.b(TAG, "onDetachedFromWindow: " + e2.toString());
        }
    }

    public PerformanceMonitorClock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PerformanceMonitorClock(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PerformanceMonitorClock(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
