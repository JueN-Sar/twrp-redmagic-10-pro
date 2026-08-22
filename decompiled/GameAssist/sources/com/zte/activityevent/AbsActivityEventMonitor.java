package com.zte.activityevent;

import com.zte.shared.wrapper.ActivityEventsManagerWrapper;

/* loaded from: classes.dex */
public abstract class AbsActivityEventMonitor extends ActivityEventsManagerWrapper.InnerCallback {
    abstract void a(int i2, String str, String str2, String str3);

    @Override // com.zte.shared.wrapper.ActivityEventsManagerWrapper.InnerCallback
    public void onActivityEvent(int i2, String str, String str2, String str3, int i3, int i4, int i5) {
        a(i2, str, str2, str3);
    }
}
