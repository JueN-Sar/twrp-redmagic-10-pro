package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.PreferenceManager;

/* loaded from: classes.dex */
public final class PreferenceScreen extends PreferenceGroup {
    private boolean b0;

    @RestrictTo
    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.a(context, R.attr.preferenceScreenStyle, android.R.attr.preferenceScreenStyle));
        this.b0 = true;
    }

    @Override // androidx.preference.PreferenceGroup
    protected boolean F0() {
        return false;
    }

    public boolean I0() {
        return this.b0;
    }

    @Override // androidx.preference.Preference
    protected void S() {
        PreferenceManager.OnNavigateToScreenListener d2;
        if (t() != null || p() != null || E0() == 0 || (d2 = E().d()) == null) {
            return;
        }
        d2.e(this);
    }
}
