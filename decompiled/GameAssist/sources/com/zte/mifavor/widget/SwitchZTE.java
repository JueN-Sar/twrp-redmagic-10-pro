package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Switch;
import com.zte.mifavor.utils.UIUtils;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class SwitchZTE extends Switch {
    int mCheckedBgColor;
    int mCheckedThumbColor;
    private Context mContext;
    private boolean mOnActionBar;
    int mUnCheckedBgColor;
    int mUnCheckedThumbColor;
    public static final int[] STATE_THMB_UNCHECKED = {R.attr.state_enabled, -16842912};
    public static final int[] STATE_THMB_CHECKED = {R.attr.state_enabled, R.attr.state_checked};
    private static final int[] STATE_ENABLE_UNCHECKED = {R.attr.state_enabled, -16842912};
    private static final int[] STATE_ENABLE_CHECKED = {R.attr.state_enabled, R.attr.state_checked};
    private static final int[] STATE_DISABLE_UNCHECKED = {-16842910, -16842912};
    private static final int[] STATE_DISABLE_CHECKED = {-16842910, R.attr.state_checked};
    private static String TAG = "SwitchZTE";

    public SwitchZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.zte.extres.R.attr.switchStyle);
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        try {
            Field declaredField = Switch.class.getDeclaredField("mSwitchWidth");
            declaredField.setAccessible(true);
            boolean j2 = UIUtils.j(this.mContext);
            Log.d(TAG, "on Measure: isOutScreen=" + j2);
            if (j2) {
                declaredField.setInt(this, Utils.c(this.mContext, 50));
                if (getMeasuredWidthAndState() == Utils.c(this.mContext, 50)) {
                    setMeasuredDimension(Utils.c(this.mContext, 50), Utils.c(this.mContext, 30));
                }
            } else {
                declaredField.setInt(this, Utils.c(this.mContext, 40));
                if (getMeasuredWidthAndState() == Utils.c(this.mContext, 40)) {
                    setMeasuredDimension(Utils.c(this.mContext, 40), Utils.c(this.mContext, 24));
                }
            }
        } catch (Exception e2) {
            Log.d(TAG, "Exception=" + e2);
        }
    }

    public void setNightStyle(boolean z) {
        if (z) {
            setTrackDrawable(this.mContext.getDrawable(com.zte.extres.R.drawable.switch_track_drawable_zte_dark));
        } else {
            setTrackDrawable(this.mContext.getDrawable(com.zte.extres.R.drawable.switch_track_drawable_zte));
        }
    }

    public SwitchZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mCheckedThumbColor = -1;
        this.mUnCheckedThumbColor = -1;
        this.mCheckedBgColor = -1;
        this.mUnCheckedBgColor = -1;
        this.mOnActionBar = false;
        setTextOn("");
        setTextOff("");
        this.mContext = context;
        setThumbDrawable(context.getDrawable(com.zte.extres.R.drawable.switch_thumb_drawable_zte));
        setTrackDrawable(this.mContext.getDrawable(com.zte.extres.R.drawable.switch_track_drawable_zte));
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }

    public SwitchZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mCheckedThumbColor = -1;
        this.mUnCheckedThumbColor = -1;
        this.mCheckedBgColor = -1;
        this.mUnCheckedBgColor = -1;
        this.mOnActionBar = false;
    }
}
