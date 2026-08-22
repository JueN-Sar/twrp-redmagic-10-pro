package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import android.os.Handler;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import java.util.List;

/* loaded from: classes.dex */
public class LiteModeItem extends Item {
    public static final String KEY = "lite_mode";
    private static final String SETTINGS_NAME = "db_lite_mode";
    private Handler mHandler = new Handler();
    private Runnable mLiteRunnable;

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean enable(List<String> list) {
        return list.contains(KEY);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public String getKey() {
        return KEY;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean getSettings(Context context) {
        return SettingUtil.getBoolean(context, "db_lite_mode", false);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.lite_mode;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void setSettings(final Context context, final boolean z) {
        Runnable runnable = this.mLiteRunnable;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
        }
        Runnable runnable2 = new Runnable() { // from class: cn.nubia.gamecenter.settings.other.LiteModeItem$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SettingUtil.putBoolean(context, "db_lite_mode", z);
            }
        };
        this.mLiteRunnable = runnable2;
        this.mHandler.postDelayed(runnable2, 250L);
    }
}
