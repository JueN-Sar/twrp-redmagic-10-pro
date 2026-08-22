package com.android.systemui.shared.system;

import android.content.Context;
import com.android.internal.util.LatencyTracker;

/* loaded from: classes2.dex */
public class LatencyTrackerCompat {
    public static boolean isEnabled(Context context) {
        return LatencyTracker.isEnabled(context);
    }

    public static void logToggleRecents(int i) {
        LatencyTracker.logAction(1, i);
    }
}
