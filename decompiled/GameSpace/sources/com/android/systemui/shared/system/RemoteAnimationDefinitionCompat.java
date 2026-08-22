package com.android.systemui.shared.system;

import android.view.RemoteAnimationDefinition;

/* loaded from: classes2.dex */
public class RemoteAnimationDefinitionCompat {
    private final RemoteAnimationDefinition mWrapped = new RemoteAnimationDefinition();

    public void addRemoteAnimation(int i, int i2, RemoteAnimationAdapterCompat remoteAnimationAdapterCompat) {
        this.mWrapped.addRemoteAnimation(i, i2, remoteAnimationAdapterCompat.getWrapped());
    }

    public void addRemoteAnimation(int i, RemoteAnimationAdapterCompat remoteAnimationAdapterCompat) {
        this.mWrapped.addRemoteAnimation(i, remoteAnimationAdapterCompat.getWrapped());
    }

    RemoteAnimationDefinition getWrapped() {
        return this.mWrapped;
    }
}
