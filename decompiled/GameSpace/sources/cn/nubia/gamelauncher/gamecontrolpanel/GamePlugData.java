package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.content.Intent;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import java.io.Serializable;

/* loaded from: classes.dex */
public class GamePlugData implements Serializable {
    private static final String TAG = "GamePlugData";
    public boolean isEnabled = true;
    private int mContentId;
    private Context mContext;
    private int mDrawables;
    private int mEnable;
    private Intent mIntent;
    private String mKey;

    public GamePlugData(int i, int i2, String str, Intent intent) {
        this.mDrawables = i;
        this.mContentId = i2;
        this.mKey = str;
        this.mIntent = intent;
    }

    public void deactivatePluginFunction(Context context) {
        if (this.mIntent != null) {
            LogUtil.i(TAG, "action =  " + this.mIntent.getAction() + "  ;; packageName = " + this.mIntent.getPackage());
            context.startService(this.mIntent);
        }
    }

    public int getContentId() {
        return this.mContentId;
    }

    public int getDrawables() {
        return this.mDrawables;
    }

    public int getEnable() {
        return this.mEnable;
    }

    public String getKey() {
        return this.mKey;
    }

    public void setEnable(int i) {
        this.mEnable = i;
    }
}
