package com.android.systemui.shared.system;

import android.app.ActivityOptions;
import android.content.Context;
import android.os.Handler;

/* loaded from: classes2.dex */
public abstract class ActivityOptionsCompat {
    public static ActivityOptions makeCustomAnimation(Context context, int i, int i2, final Runnable runnable, final Handler handler) {
        return ActivityOptions.makeCustomAnimation(context, i, i2, handler, new ActivityOptions.OnAnimationStartedListener() { // from class: com.android.systemui.shared.system.ActivityOptionsCompat.1
            public void onAnimationStarted() {
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    handler.post(runnable2);
                }
            }
        }, null);
    }

    public static ActivityOptions makeFreeformOptions() {
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchWindowingMode(5);
        return makeBasic;
    }

    public static ActivityOptions makeRemoteAnimation(RemoteAnimationAdapterCompat remoteAnimationAdapterCompat) {
        return ActivityOptions.makeRemoteAnimation(remoteAnimationAdapterCompat.getWrapped());
    }

    public static ActivityOptions makeSplitScreenOptions(boolean z) {
        return makeSplitScreenOptions(z, true);
    }

    public static ActivityOptions makeSplitScreenOptions(boolean z, boolean z2) {
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchWindowingMode(z2 ? 3 : 4);
        makeBasic.setSplitScreenCreateMode(!z ? 1 : 0);
        return makeBasic;
    }

    public static ActivityOptions setFreezeRecentTasksList(ActivityOptions activityOptions) {
        activityOptions.setFreezeRecentTasksReordering();
        return activityOptions;
    }
}
