package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;

/* loaded from: classes.dex */
public class GameControlOrientationManager {
    private static final String TAG = "GameControlOrientation";
    private static GameControlOrientationManager mInstance;
    private boolean mIsPortrait;
    private boolean mIsSupportPortrait = CommonUtil.isNubia();

    private GameControlOrientationManager() {
    }

    public static GameControlOrientationManager getInstance() {
        if (mInstance == null) {
            mInstance = new GameControlOrientationManager();
        }
        return mInstance;
    }

    public void init(Context context) {
        this.mIsPortrait = context.getResources().getConfiguration().orientation == 1 && this.mIsSupportPortrait;
        LogUtil.i(TAG, "init: mIsPortrait= " + this.mIsPortrait + ", isEnabled= " + this.mIsSupportPortrait);
    }

    public boolean isPortrait() {
        return this.mIsPortrait;
    }

    public boolean isPortraitEnabled() {
        return this.mIsSupportPortrait;
    }
}
