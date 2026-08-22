package com.android.systemui.shared.system;

import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;

/* loaded from: classes2.dex */
public class SurfaceControlCompat {
    final SurfaceControl mSurfaceControl;

    public SurfaceControlCompat(SurfaceControl surfaceControl) {
        this.mSurfaceControl = surfaceControl;
    }

    public SurfaceControlCompat(View view) {
        ViewRootImpl viewRootImpl = view.getViewRootImpl();
        this.mSurfaceControl = viewRootImpl != null ? viewRootImpl.getSurfaceControl() : null;
    }

    public SurfaceControl getSurfaceControl() {
        return this.mSurfaceControl;
    }

    public boolean isValid() {
        SurfaceControl surfaceControl = this.mSurfaceControl;
        return surfaceControl != null && surfaceControl.isValid();
    }
}
