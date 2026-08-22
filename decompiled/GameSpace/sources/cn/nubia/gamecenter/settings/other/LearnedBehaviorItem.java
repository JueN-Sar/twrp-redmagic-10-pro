package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import java.util.List;

/* loaded from: classes.dex */
public class LearnedBehaviorItem extends Item {
    public static final String KEY = "learned_behavior";
    private static final String SETTINGS_NAME = "zte_learned_behavior_enable";

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean enable(List<String> list) {
        return !GameSpaceConfig.supportBase() && FeatureUtil.behaviorLearnedEnable();
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public String getKey() {
        return KEY;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean getSettings(Context context) {
        return SettingUtil.getBoolean(context, SETTINGS_NAME, false);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getSummary() {
        return R.string.learned_behavior_content_1;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.learned_behavior_name;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void setSettings(Context context, boolean z) {
        SettingUtil.putBoolean(context, SETTINGS_NAME, z);
    }
}
