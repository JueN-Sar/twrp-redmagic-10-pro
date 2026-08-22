package com.zte.mifavor.widget.remote;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Switch;
import com.zte.mifavor.utils.UIUtils;
import com.zte.mifavor.widget.Utils;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class RemoteSwitch extends Switch {
    private static String TAG = "RemoteSwitch";
    private Context mContext;

    public RemoteSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
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

    public RemoteSwitch(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public RemoteSwitch(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mContext = context;
    }
}
