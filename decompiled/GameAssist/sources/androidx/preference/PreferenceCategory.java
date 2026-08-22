package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* loaded from: classes.dex */
public class PreferenceCategory extends PreferenceGroup {
    public PreferenceCategory(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    @Override // androidx.preference.Preference
    public boolean J() {
        return false;
    }

    @Override // androidx.preference.Preference
    public void R(PreferenceViewHolder preferenceViewHolder) {
        super.R(preferenceViewHolder);
        preferenceViewHolder.f5252a.setAccessibilityHeading(true);
    }

    @Override // androidx.preference.Preference
    public void W(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.W(accessibilityNodeInfoCompat);
    }

    @Override // androidx.preference.Preference
    public boolean u0() {
        return !super.J();
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.a(context, R.attr.preferenceCategoryStyle, android.R.attr.preferenceCategoryStyle));
    }
}
