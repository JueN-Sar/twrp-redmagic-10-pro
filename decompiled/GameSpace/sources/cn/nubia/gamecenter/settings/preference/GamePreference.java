package cn.nubia.gamecenter.settings.preference;

import android.content.Context;
import android.util.AttributeSet;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.Preference;

/* loaded from: classes.dex */
public class GamePreference extends Preference {
    private static String TAG = "GamePreference";

    public GamePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R.layout.gcs_normal_preference);
        setWidgetLayoutResource(R.layout.arrow_layout);
    }
}
