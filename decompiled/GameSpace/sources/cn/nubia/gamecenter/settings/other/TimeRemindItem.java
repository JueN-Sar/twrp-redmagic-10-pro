package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.settings.trackclient.Track;
import java.util.List;

/* loaded from: classes.dex */
public class TimeRemindItem extends Item {
    public static final String KEY = "game_time_remind";
    private static final String SETTINGS_NAME = "switch_game_time_remind";
    private static final String TRACK_EVENT = "gamespace_health_weekly_switch";

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
        return SettingUtil.getBoolean(context, "switch_game_time_remind", true);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.gcs_game_time_remind_title;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void setSettings(Context context, boolean z) {
        SettingUtil.putBoolean(context, "switch_game_time_remind", z);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void track(boolean z) {
        super.track(z);
        Track.switchStatus(TRACK_EVENT, z);
    }
}
