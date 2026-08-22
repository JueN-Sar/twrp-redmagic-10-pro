package cn.nubia.gamecenter.settings.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.PreferenceViewHolder;

/* loaded from: classes.dex */
public class GameCenterNetAccelerationAppPreference extends Preference {
    private static String TAG = "GameCenterNetAccelerationAppPreference";
    private Context mContext;
    private TextView mCountTextView;

    public GameCenterNetAccelerationAppPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        setLayoutResource(R.layout.gcs_network_acceleration_app);
        setWidgetLayoutResource(R.layout.arrow_layout);
    }

    @Override // cn.nubia.gamecenter.settings.compatible.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.mCountTextView = (TextView) preferenceViewHolder.findViewById(R.id.gamemode_network_acceleration_app_count);
        setCount();
    }

    public void setCount() {
        TextView textView = this.mCountTextView;
        if (textView != null) {
            textView.setText("");
        }
    }
}
