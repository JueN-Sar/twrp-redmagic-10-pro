package cn.nubia.gamecenter.settings.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.PreferenceViewHolder;

/* loaded from: classes.dex */
public class GameCenterNetAccelerationPreference extends Preference {
    private static String TAG = "NetFragment";
    private Context mContext;
    private int mSDKValue;
    private TextView vSDK;

    public GameCenterNetAccelerationPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSDKValue = -1;
        this.mContext = context;
        setLayoutResource(R.layout.gcs_network_acceleration);
        setWidgetLayoutResource(R.layout.arrow_layout);
    }

    @Override // cn.nubia.gamecenter.settings.compatible.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.vSDK = (TextView) preferenceViewHolder.findViewById(R.id.gamemode_network_acceleration_sdk);
        setAcceleratedSDKName(this.mSDKValue);
    }

    @Override // androidx.preference.Preference
    protected void performClick(View view) {
        super.performClick(view);
    }

    public void setAcceleratedSDKName(int i) {
        this.mSDKValue = i;
        TextView textView = this.vSDK;
        if (textView == null) {
            return;
        }
        if (i == 0) {
            textView.setText(R.string.gamemode_network_acceleration_app_off);
            return;
        }
        if (i == 2) {
            textView.setText(R.string.gamemode_network_acceleration_app_tencent);
        } else if (i == 1) {
            textView.setText(R.string.gamemode_network_acceleration_app_xunyou);
        } else if (i == 3) {
            textView.setText(R.string.gamemode_network_acceleration_trial);
        }
    }
}
