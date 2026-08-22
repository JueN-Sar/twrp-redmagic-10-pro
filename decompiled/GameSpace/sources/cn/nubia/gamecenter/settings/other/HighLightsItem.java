package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.settings.trackclient.Track;
import java.util.List;

/* loaded from: classes.dex */
public class HighLightsItem extends Item {
    public static final String KEY = "highlights";
    private static final String SETTINGS_NAME = "persist_sys_highlights_auto_switch";
    private static final String TRACK_EVENT = "redmagictime_smart_editing_switch_status";

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
        return SettingUtil.getBoolean(context, SETTINGS_NAME, true);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getSummary() {
        return CommonUtil.isZte() ? R.string.gcs_highlights_summary_zte : R.string.gcs_highlights_summary;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.gcs_highlights;
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
