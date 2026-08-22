package com.google.android.gms.common.stats;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@Deprecated
/* loaded from: classes.dex */
public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable {

    @KeepForSdk
    public interface Types {
    }

    public abstract int G();

    public abstract long P();

    public abstract String R();

    public final String toString() {
        return P() + "\t" + G() + "\t-1" + R();
    }
}
