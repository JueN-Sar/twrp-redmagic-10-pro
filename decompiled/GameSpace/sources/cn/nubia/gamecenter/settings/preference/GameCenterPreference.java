package cn.nubia.gamecenter.settings.preference;

import android.content.Context;
import android.util.AttributeSet;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.Preference;

/* loaded from: classes.dex */
public class GameCenterPreference extends Preference {
    private static String TAG = "GameCenterPreference";

    public GameCenterPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R.layout.gcs_preference);
        setWidgetLayoutResource(R.layout.arrow_layout);
    }
}
