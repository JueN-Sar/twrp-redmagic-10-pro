package cn.nubia.gamecenter.settings.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.PreferenceViewHolder;

/* loaded from: classes.dex */
public class GameCenterNetAccelerationDetailsPreference extends Preference {
    private static String TAG = "GameCenterNetAccelerationDetailsPreference";
    private Context mContext;
    private int mStatusResId;
    private TextView mStatusTextView;
    private int mSummaryResId;
    private TextView mSummaryTextView;
    private int mTitleResId;
    private TextView mTitleTextView;

    public GameCenterNetAccelerationDetailsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        setLayoutResource(R.layout.gcs_network_acceleration_details);
        setWidgetLayoutResource(R.layout.arrow_layout);
    }

    @Override // cn.nubia.gamecenter.settings.compatible.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.mTitleTextView = (TextView) preferenceViewHolder.findViewById(R.id.gamemode_network_acceleration_title);
        this.mSummaryTextView = (TextView) preferenceViewHolder.findViewById(R.id.gamemode_network_acceleration_summary);
        this.mStatusTextView = (TextView) preferenceViewHolder.findViewById(R.id.gamemode_network_acceleration_status);
        setTitle(this.mTitleResId);
        setSummary(this.mSummaryResId);
        setStatus(this.mStatusResId);
    }

    public void setStatus(int i) {
        TextView textView;
        if (this.mStatusResId == 0 || (textView = this.mStatusTextView) == null) {
            return;
        }
        textView.setText(i);
    }

    public void setStatusResId(int i) {
        this.mStatusResId = i;
        setStatus(i);
    }

    @Override // androidx.preference.Preference
    public void setSummary(int i) {
        TextView textView;
        if (this.mSummaryResId == 0 || (textView = this.mSummaryTextView) == null) {
            return;
        }
        if (i == -1) {
            textView.setVisibility(8);
        } else {
            textView.setText(i);
        }
    }

    public void setSummaryResId(int i) {
        this.mSummaryResId = i;
        setSummary(i);
    }

    @Override // androidx.preference.Preference
    public void setTitle(int i) {
        TextView textView;
        if (this.mTitleResId == 0 || (textView = this.mTitleTextView) == null) {
            return;
        }
        textView.setText(i);
    }

    public void setTitleResId(int i) {
        this.mTitleResId = i;
        setTitle(i);
    }
}
