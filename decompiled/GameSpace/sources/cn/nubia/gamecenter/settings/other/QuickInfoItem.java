package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.settings.trackclient.Track;
import java.util.List;

/* loaded from: classes.dex */
public class QuickInfoItem extends Item {
    public static final String KEY = "quick_info";
    private static final String SETTINGS_NAME = "db_game_quick_info";
    private static final String TRACK_EVENT = "game_Info_quick_view_switch_status";

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean enable(List<String> list) {
        return FeatureUtil.isSupportGameQuickInfo();
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
        return R.string.game_quick_info_summary;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.game_quick_info;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void setSettings(Context context, boolean z) {
        SettingUtil.putBoolean(context, SETTINGS_NAME, z);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void track(boolean z) {
        super.track(z);
        Track.switchStatus(TRACK_EVENT, z);
    }
}
