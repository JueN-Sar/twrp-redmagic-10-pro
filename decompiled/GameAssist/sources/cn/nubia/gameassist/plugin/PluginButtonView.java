package cn.nubia.gameassist.plugin;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* loaded from: classes.dex */
public class PluginButtonView extends LinearLayout {
    private static final String TAG = "PluginButtonView";
    private Context mContext;

    public PluginButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public PluginButtonView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
