package com.zte.shared.wrapper;

import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;

/* loaded from: classes2.dex */
public class SurfaceControlWrapper {
    private static final String TAG = "SurfaceControlCompat";
    final SurfaceControl mSurfaceControl;

    public SurfaceControlWrapper(SurfaceControl surfaceControl) {
        this.mSurfaceControl = surfaceControl;
    }

    public SurfaceControl getSurfaceControl() {
        return this.mSurfaceControl;
    }

    public boolean isValid() {
        SurfaceControl surfaceControl = this.mSurfaceControl;
        return surfaceControl != null && surfaceControl.isValid();
    }

    public SurfaceControlWrapper(View view) {
        ViewRootImpl viewRootImpl = view.getViewRootImpl();
        this.mSurfaceControl = viewRootImpl != null ? viewRootImpl.getSurfaceControl() : null;
    }
}
