package com.google.android.material.internal;

import android.widget.Checkable;
import androidx.annotation.RestrictTo;
import com.google.android.material.internal.MaterialCheckable;

@RestrictTo
/* loaded from: classes.dex */
public interface MaterialCheckable<T extends MaterialCheckable<T>> extends Checkable {

    public interface OnCheckedChangeListener<C> {
        void a(Object obj, boolean z);
    }

    int getId();

    void setInternalOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener);
}
