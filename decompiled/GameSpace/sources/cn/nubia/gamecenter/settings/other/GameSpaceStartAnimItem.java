package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.settings.trackclient.Track;
import java.util.List;

/* loaded from: classes.dex */
public class GameSpaceStartAnimItem extends Item {
    public static final String KEY = "game_space_start_anim_volume";
    private static final String SETTINGS_NAME = "switch_gamespace_start_anim_volume";
    private static final String TRACK_EVENT = "pers_center_start_animation_status";

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
        return SettingUtil.getBoolean(context, "switch_gamespace_start_anim_volume", true);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.gcs_game_space_start_anim_volume_title;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void setSettings(Context context, boolean z) {
        SettingUtil.putBoolean(context, "switch_gamespace_start_anim_volume", z);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void track(boolean z) {
        super.track(z);
        Track.switchStatus(TRACK_EVENT, z);
    }
}
