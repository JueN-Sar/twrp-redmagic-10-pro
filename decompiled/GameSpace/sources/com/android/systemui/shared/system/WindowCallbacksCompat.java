package com.android.systemui.shared.system;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.WindowCallbacks;

/* loaded from: classes2.dex */
public class WindowCallbacksCompat {
    private final View mView;
    private final WindowCallbacks mWindowCallbacks = new WindowCallbacks() { // from class: com.android.systemui.shared.system.WindowCallbacksCompat.1
        public boolean onContentDrawn(int i, int i2, int i3, int i4) {
            return WindowCallbacksCompat.this.onContentDrawn(i, i2, i3, i4);
        }

        public void onPostDraw(RecordingCanvas recordingCanvas) {
            WindowCallbacksCompat.this.onPostDraw(recordingCanvas);
        }

        public void onRequestDraw(boolean z) {
            WindowCallbacksCompat.this.onRequestDraw(z);
        }

        public void onWindowDragResizeEnd() {
            WindowCallbacksCompat.this.onWindowDragResizeEnd();
        }

        public void onWindowDragResizeStart(Rect rect, boolean z, Rect rect2, Rect rect3, int i) {
            WindowCallbacksCompat.this.onWindowDragResizeStart(rect, z, rect2, rect3, i);
        }

        public void onWindowSizeIsChanging(Rect rect, boolean z, Rect rect2, Rect rect3) {
            WindowCallbacksCompat.this.onWindowSizeIsChanging(rect, z, rect2, rect3);
        }
    };

    public WindowCallbacksCompat(View view) {
        this.mView = view;
    }

    public boolean attach() {
        ViewRootImpl viewRootImpl = this.mView.getViewRootImpl();
        if (viewRootImpl == null) {
            return false;
        }
        viewRootImpl.addWindowCallbacks(this.mWindowCallbacks);
        viewRootImpl.requestInvalidateRootRenderNode();
        return true;
    }

    public void detach() {
        ViewRootImpl viewRootImpl = this.mView.getViewRootImpl();
        if (viewRootImpl != null) {
            viewRootImpl.removeWindowCallbacks(this.mWindowCallbacks);
        }
    }

    public boolean onContentDrawn(int i, int i2, int i3, int i4) {
        return false;
    }

    public void onPostDraw(Canvas canvas) {
    }

    public void onRequestDraw(boolean z) {
        if (z) {
            reportDrawFinish();
        }
    }

    public void onWindowDragResizeEnd() {
    }

    public void onWindowDragResizeStart(Rect rect, boolean z, Rect rect2, Rect rect3, int i) {
    }

    public void onWindowSizeIsChanging(Rect rect, boolean z, Rect rect2, Rect rect3) {
    }

    public void reportDrawFinish() {
        this.mView.getViewRootImpl().reportDrawFinish();
    }
}
