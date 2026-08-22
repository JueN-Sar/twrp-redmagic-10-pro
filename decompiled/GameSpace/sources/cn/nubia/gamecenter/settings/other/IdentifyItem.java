package cn.nubia.gamecenter.settings.other;

import android.content.Context;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.helper.IdentifyHelper;
import cn.nubia.gamecenter.settings.R;
import java.util.List;

/* loaded from: classes.dex */
public class IdentifyItem extends Item {
    public static final String KEY = "identify";

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean enable(List<String> list) {
        return GameSpaceConfig.supportIdentify();
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public String getKey() {
        return "identify";
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public boolean getSettings(Context context) {
        return IdentifyHelper.getInstance().isIdentifyOpen();
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public int getTitle() {
        return R.string.identify_title;
    }

    @Override // cn.nubia.gamecenter.settings.other.Item
    public void setSettings(Context context, boolean z) {
        if (z) {
            IdentifyHelper.getInstance().setIdentifyOpen();
        } else {
            IdentifyHelper.getInstance().setIdentifyClose();
        }
    }
}
