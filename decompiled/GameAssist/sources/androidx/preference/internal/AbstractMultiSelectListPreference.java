package androidx.preference.internal;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.RestrictTo;
import androidx.preference.DialogPreference;
import java.util.Set;

@RestrictTo
/* loaded from: classes.dex */
public abstract class AbstractMultiSelectListPreference extends DialogPreference {
    public AbstractMultiSelectListPreference(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    public abstract CharSequence[] G0();

    public abstract CharSequence[] H0();

    public abstract Set I0();

    public abstract void J0(Set set);

    public AbstractMultiSelectListPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public AbstractMultiSelectListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
