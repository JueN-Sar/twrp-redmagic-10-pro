package cn.nubia.gameassist.plugin;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.plugin.panel.PluginTilesAdapter;

/* loaded from: classes.dex */
public class PluginLibraryView extends LinearLayout {
    private static final String TAG = "PluginLibraryView";
    private Context mContext;
    private PluginTilesAdapter mPluginTilesAdapter;

    public PluginLibraryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPluginTilesAdapter = null;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (i2 == 0) {
            setBackgroundResource(R.drawable.plugin_library_background);
        } else {
            setBackground(null);
        }
    }

    public PluginLibraryView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPluginTilesAdapter = null;
    }
}
