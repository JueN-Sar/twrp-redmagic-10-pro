package cn.nubia.gamecenter.settings.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.PreferenceViewHolder;
import cn.nubia.gamecenter.settings.utils.Utils;

/* loaded from: classes.dex */
public class GameCenterPreferenceICP extends Preference {
    private Context mContext;

    public GameCenterPreferenceICP(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        setLayoutResource(R.layout.gcs_preference_icp);
        setWidgetLayoutResource(R.layout.arrow_layout);
    }

    @Override // cn.nubia.gamecenter.settings.compatible.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.icp_game_launcher);
        textView.setText(this.mContext.getString(R.string.gcs_system_update_title));
        textView.append("：");
        textView.append(this.mContext.getString(R.string.icp_game_launcher));
        if (Utils.isAppExist(this.mContext, Utils.PACKAGE_ARKBASE)) {
            TextView textView2 = (TextView) preferenceViewHolder.findViewById(R.id.icp_arkbase);
            textView2.setVisibility(0);
            textView2.setText(this.mContext.getString(R.string.gcs_gamecenter_menu_arkbase));
            textView2.append("：");
            textView2.append(this.mContext.getString(R.string.icp_arkbase));
        }
        if (Utils.isAppExist(this.mContext, Utils.PACKAGE_ONE_MORE_THING)) {
            TextView textView3 = (TextView) preferenceViewHolder.findViewById(R.id.icp_one_more_thing);
            textView3.setVisibility(0);
            textView3.setText(this.mContext.getString(R.string.one_more_thing));
            textView3.append("：");
            textView3.append(this.mContext.getString(R.string.icp_one_more_thing));
        }
    }
}
