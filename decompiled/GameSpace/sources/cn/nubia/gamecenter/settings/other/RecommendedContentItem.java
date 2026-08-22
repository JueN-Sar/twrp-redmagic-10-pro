package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import java.util.List;

/* loaded from: classes.dex */
public class RecommendedContentItem extends Item {
    public static final String KEY = "key_game_recommended_content";
    private static final String SETTINGS_NAME = "db_recommended_content_game";

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
        return SettingUtil.getBoolean(context, "db_recommended_content_game", true);
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getSummary() {
        return R.string.gcs_recommended_content_summary;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.gcs_game_recommended_content_title;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void setSettings(Context context, boolean z) {
        SettingUtil.putBoolean(context, "db_recommended_content_game", z);
    }
}
