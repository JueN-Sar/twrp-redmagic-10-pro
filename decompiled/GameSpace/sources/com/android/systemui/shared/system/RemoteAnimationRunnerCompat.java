package com.android.systemui.shared.system;

/* loaded from: classes2.dex */
public interface RemoteAnimationRunnerCompat {
    void onAnimationCancelled();

    void onAnimationStart(RemoteAnimationTargetCompat[] remoteAnimationTargetCompatArr, Runnable runnable);
}
