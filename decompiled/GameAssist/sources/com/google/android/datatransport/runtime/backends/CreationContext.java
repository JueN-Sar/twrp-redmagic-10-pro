package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.auto.value.AutoValue;

@AutoValue
/* loaded from: classes.dex */
public abstract class CreationContext {
    public static CreationContext a(Context context, Clock clock, Clock clock2, String str) {
        return new AutoValue_CreationContext(context, clock, clock2, str);
    }

    public abstract Context b();

    public abstract String c();

    public abstract Clock d();

    public abstract Clock e();
}
