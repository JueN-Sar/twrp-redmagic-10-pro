package cn.nubia.gamecenter.settings.compatible;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.FlickerUtils;

/* loaded from: classes.dex */
public class Preference extends androidx.preference.Preference {
    private OnPreferenceChangeListener mOnChangeListener;

    public interface OnPreferenceChangeListener {
        boolean onPreferenceChange(Preference preference, Object obj);
    }

    public Preference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.preference.Preference
    public boolean callChangeListener(Object obj) {
        OnPreferenceChangeListener onPreferenceChangeListener = this.mOnChangeListener;
        return onPreferenceChangeListener != null ? onPreferenceChangeListener.onPreferenceChange(this, obj) : super.callChangeListener(obj);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(androidx.preference.PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        onBindViewHolder(new PreferenceViewHolder(preferenceViewHolder));
        FlickerUtils.setFlickerName(preferenceViewHolder.findViewById(R.id.flicker), getKey());
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
    }

    @Override // androidx.preference.Preference
    public void setOnPreferenceChangeListener(Preference.OnPreferenceChangeListener onPreferenceChangeListener) {
        if (onPreferenceChangeListener instanceof OnPreferenceChangeListener) {
            this.mOnChangeListener = (OnPreferenceChangeListener) onPreferenceChangeListener;
        } else {
            super.setOnPreferenceChangeListener(onPreferenceChangeListener);
        }
    }
}
