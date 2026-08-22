package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import java.util.List;

/* loaded from: classes.dex */
public class ShoulderKeyItem extends Item {
    public static final String KEY = "shoulder_key_launch_gamespace";
    private static final String SETTINGS_NAME = "shoulder_key_launch_gamespace";

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean enable(List<String> list) {
        return FeatureUtil.isSupportShoulderKeyLaunchGamespace();
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public String getKey() {
        return "shoulder_key_launch_gamespace";
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean getSettings(Context context) {
        return SettingUtil.getBoolean(context, "shoulder_key_launch_gamespace", !CommonUtil.isP820S01());
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.shoulder_key_launch_gamespace;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void setSettings(Context context, boolean z) {
        SettingUtil.putBoolean(context, "shoulder_key_launch_gamespace", z);
    }
}
