package cn.nubia.gamecenter.settings.about;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.FlickerUtils;
import java.util.List;

/* loaded from: classes.dex */
public class FunctionPreference extends Preference {
    private static final String TYPE_BASE = "base";
    private static final String TYPE_FLAGSHIP = "flagship";
    private static final String TYPE_REDMAGIC = "redmagic";

    public FunctionPreference(Context context) {
        this(context, null);
    }

    public FunctionPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FunctionPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public FunctionPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, R.attr.preferenceStyle, R.attr.preferenceStyle), i2);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.adout_message_summary);
        List<String> gameCenterAbout = FeatureUtil.getGameCenterAbout();
        int i = gameCenterAbout.contains("base") ? R.string.gcs_game_new_message_update_summary_base : gameCenterAbout.contains(TYPE_REDMAGIC) ? R.string.gcs_game_new_message_update_summary_redmagic : R.string.gcs_game_new_message_update_summary_flagship;
        if (CommonUtil.isP720P01()) {
            i = R.string.gcs_game_new_message_update_summary_base;
        }
        textView.setText(i);
        FlickerUtils.setFlickerName(preferenceViewHolder.findViewById(R.id.flicker), getKey());
    }
}
