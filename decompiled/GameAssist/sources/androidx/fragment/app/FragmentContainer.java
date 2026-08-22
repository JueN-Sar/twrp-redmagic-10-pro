package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/* loaded from: classes.dex */
public abstract class FragmentContainer {
    public Fragment j(Context context, String str, Bundle bundle) {
        return Fragment.l0(context, str, bundle);
    }

    public abstract View m(int i2);

    public abstract boolean p();
}
