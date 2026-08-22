package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import java.util.List;

/* loaded from: classes.dex */
public class FiberCatcherItem extends Item {
    public static final String KEY = "fiber_catcher";
    private static final String SETTINGS_NAME = "nubia_fiber_catcher";

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
        return SettingUtil.getBoolean(context, SETTINGS_NAME, false);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getSummary() {
        return R.string.fiber_catcher_summary;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.fiber_catcher;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void setSettings(Context context, boolean z) {
        SettingUtil.putBoolean(context, SETTINGS_NAME, z);
    }
}
