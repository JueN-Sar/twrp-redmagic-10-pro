package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import java.util.List;

/* loaded from: classes.dex */
public class PeriConnWinItem extends Item {
    public static final String KEY = "gamemode_peripheral_connection_pop_up_window";
    private static final String SETTINGS_NAME = "switch_peri_conn_pop_up_window";

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean enable(List<String> list) {
        return FeatureUtil.isSupportXGravityGamepad();
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public String getKey() {
        return KEY;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean getSettings(Context context) {
        return SettingUtil.getBoolean(context, SETTINGS_NAME, true);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getSummary() {
        return !FeatureUtil.getBoolean(FeatureUtil.ZTE_FEATURE_KEY_MOUSE_MAP, false).booleanValue() ? R.string.peripheral_connection_window_summary : R.string.gamemode_peripheral_connection_pop_up_window_summary;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.gamemode_peripheral_connection_pop_up_window_title;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void setSettings(Context context, boolean z) {
        SettingUtil.putBoolean(context, SETTINGS_NAME, z);
    }
}
