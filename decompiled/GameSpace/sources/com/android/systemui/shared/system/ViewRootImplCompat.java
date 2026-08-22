package com.android.systemui.shared.system;

import android.graphics.HardwareRenderer;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;
import java.util.Objects;
import java.util.function.LongConsumer;

/* loaded from: classes2.dex */
public class ViewRootImplCompat {
    private final ViewRootImpl mViewRoot;

    public ViewRootImplCompat(View view) {
        this.mViewRoot = view == null ? null : view.getViewRootImpl();
    }

    public SurfaceControl getRenderSurfaceControl() {
        ViewRootImpl viewRootImpl = this.mViewRoot;
        if (viewRootImpl == null) {
            return null;
        }
        return viewRootImpl.getRenderSurfaceControl();
    }

    public SurfaceControl getSurfaceControl() {
        ViewRootImpl viewRootImpl = this.mViewRoot;
        if (viewRootImpl == null) {
            return null;
        }
        return viewRootImpl.getSurfaceControl();
    }

    public View getView() {
        ViewRootImpl viewRootImpl = this.mViewRoot;
        if (viewRootImpl == null) {
            return null;
        }
        return viewRootImpl.getView();
    }

    public boolean isValid() {
        return this.mViewRoot != null;
    }

    public void registerRtFrameCallback(final LongConsumer longConsumer) {
        ViewRootImpl viewRootImpl = this.mViewRoot;
        if (viewRootImpl != null) {
            Objects.requireNonNull(longConsumer);
            viewRootImpl.registerRtFrameCallback(new HardwareRenderer.FrameDrawingCallback() { // from class: com.android.systemui.shared.system.ViewRootImplCompat$$ExternalSyntheticLambda0
                public final void onFrameDraw(long j) {
                    longConsumer.accept(j);
                }
            });
        }
    }
}
