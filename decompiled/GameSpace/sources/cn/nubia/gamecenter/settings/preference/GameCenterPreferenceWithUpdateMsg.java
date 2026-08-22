package cn.nubia.gamecenter.settings.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.PreferenceViewHolder;
import cn.nubia.gamecenter.settings.utils.Utils;

/* loaded from: classes.dex */
public class GameCenterPreferenceWithUpdateMsg extends Preference {
    private static String TAG = "GameCenterPreferenceWithUpdateMsg";
    private ImageView mRedPointImage;
    private TextView mSummary;
    private String mVersionName;

    public GameCenterPreferenceWithUpdateMsg(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R.layout.gcs_preference_with_update_msg);
        setWidgetLayoutResource(R.layout.arrow_layout);
        this.mVersionName = context.getString(R.string.gcs_current_version, Utils.getVersionName(context));
    }

    public void displayUpdateIcon(boolean z) {
        ImageView imageView = this.mRedPointImage;
        if (imageView != null) {
            imageView.setVisibility(z ? 4 : 8);
        }
    }

    @Override // cn.nubia.gamecenter.settings.compatible.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        ImageView imageView = (ImageView) preferenceViewHolder.findViewById(R.id.msgUpdate);
        this.mRedPointImage = imageView;
        imageView.setVisibility(Utils.hasNewVersion() ? 0 : 8);
        TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.msgUpdateSummary);
        this.mSummary = textView;
        textView.setText(this.mVersionName);
    }

    public void setSummary(String str) {
        TextView textView = this.mSummary;
        if (textView != null) {
            textView.setText(str);
        }
    }
}
