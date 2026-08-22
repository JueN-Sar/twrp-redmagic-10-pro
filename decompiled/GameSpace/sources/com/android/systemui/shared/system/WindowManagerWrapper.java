package com.android.systemui.shared.system;

import android.graphics.Rect;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.WindowManagerGlobal;
import com.android.systemui.shared.recents.view.AppTransitionAnimationSpecsFuture;
import com.android.systemui.shared.recents.view.RecentsTransition;
import com.android.systemui.shared.system.PinnedStackListenerForwarder;

/* loaded from: classes2.dex */
public class WindowManagerWrapper {
    public static final int ACTIVITY_TYPE_STANDARD = 1;
    public static final int NAV_BAR_POS_BOTTOM = 4;
    public static final int NAV_BAR_POS_INVALID = -1;
    public static final int NAV_BAR_POS_LEFT = 1;
    public static final int NAV_BAR_POS_RIGHT = 2;
    private static final String TAG = "WindowManagerWrapper";
    public static final int TRANSIT_ACTIVITY_CLOSE = 7;
    public static final int TRANSIT_ACTIVITY_OPEN = 6;
    public static final int TRANSIT_ACTIVITY_RELAUNCH = 18;
    public static final int TRANSIT_DOCK_TASK_FROM_RECENTS = 19;
    public static final int TRANSIT_KEYGUARD_GOING_AWAY = 20;
    public static final int TRANSIT_KEYGUARD_GOING_AWAY_ON_WALLPAPER = 21;
    public static final int TRANSIT_KEYGUARD_OCCLUDE = 22;
    public static final int TRANSIT_KEYGUARD_UNOCCLUDE = 23;
    public static final int TRANSIT_NONE = 0;
    public static final int TRANSIT_TASK_CLOSE = 9;
    public static final int TRANSIT_TASK_OPEN = 8;
    public static final int TRANSIT_TASK_OPEN_BEHIND = 16;
    public static final int TRANSIT_TASK_TO_BACK = 11;
    public static final int TRANSIT_TASK_TO_FRONT = 10;
    public static final int TRANSIT_UNSET = -1;
    public static final int TRANSIT_WALLPAPER_CLOSE = 12;
    public static final int TRANSIT_WALLPAPER_INTRA_CLOSE = 15;
    public static final int TRANSIT_WALLPAPER_INTRA_OPEN = 14;
    public static final int TRANSIT_WALLPAPER_OPEN = 13;
    public static final int WINDOWING_MODE_FREEFORM = 5;
    public static final int WINDOWING_MODE_FULLSCREEN = 1;
    public static final int WINDOWING_MODE_MULTI_WINDOW = 6;
    public static final int WINDOWING_MODE_PINNED = 2;
    public static final int WINDOWING_MODE_SPLIT_SCREEN_PRIMARY = 3;
    public static final int WINDOWING_MODE_SPLIT_SCREEN_SECONDARY = 4;
    public static final int WINDOWING_MODE_UNDEFINED = 0;
    private static final WindowManagerWrapper sInstance = new WindowManagerWrapper();
    private PinnedStackListenerForwarder mPinnedStackListenerForwarder = new PinnedStackListenerForwarder();

    public static WindowManagerWrapper getInstance() {
        return sInstance;
    }

    public void addPinnedStackListener(PinnedStackListenerForwarder.PinnedStackListener pinnedStackListener) throws RemoteException {
        this.mPinnedStackListenerForwarder.addListener(pinnedStackListener);
        WindowManagerGlobal.getWindowManagerService().registerPinnedStackListener(0, this.mPinnedStackListenerForwarder);
    }

    public int getNavBarPosition(int i) {
        try {
            return WindowManagerGlobal.getWindowManagerService().getNavBarPosition(i);
        } catch (RemoteException unused) {
            Log.w(TAG, "Failed to get nav bar position");
            return -1;
        }
    }

    public void getStableInsets(Rect rect) {
        try {
            WindowManagerGlobal.getWindowManagerService().getStableInsets(0, rect);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get stable insets", e);
        }
    }

    public boolean hasSoftNavigationBar(int i) {
        try {
            return WindowManagerGlobal.getWindowManagerService().hasNavigationBar(i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public SurfaceControl mirrorDisplay(int i) {
        try {
            SurfaceControl surfaceControl = new SurfaceControl();
            WindowManagerGlobal.getWindowManagerService().mirrorDisplay(i, surfaceControl);
            return surfaceControl;
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to reach window manager", e);
            return null;
        }
    }

    public void overridePendingAppTransitionMultiThumbFuture(AppTransitionAnimationSpecsFuture appTransitionAnimationSpecsFuture, Runnable runnable, Handler handler, boolean z, int i) {
        try {
            WindowManagerGlobal.getWindowManagerService().overridePendingAppTransitionMultiThumbFuture(appTransitionAnimationSpecsFuture.getFuture(), RecentsTransition.wrapStartedListener(handler, runnable), z, i);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to override pending app transition (multi-thumbnail future): ", e);
        }
    }

    public void overridePendingAppTransitionRemote(RemoteAnimationAdapterCompat remoteAnimationAdapterCompat, int i) {
        try {
            WindowManagerGlobal.getWindowManagerService().overridePendingAppTransitionRemote(remoteAnimationAdapterCompat.getWrapped(), i);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to override pending app transition (remote): ", e);
        }
    }

    public void removePinnedStackListener(PinnedStackListenerForwarder.PinnedStackListener pinnedStackListener) {
        this.mPinnedStackListenerForwarder.removeListener(pinnedStackListener);
    }

    public void setNavBarVirtualKeyHapticFeedbackEnabled(boolean z) {
        try {
            WindowManagerGlobal.getWindowManagerService().setNavBarVirtualKeyHapticFeedbackEnabled(z);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to enable or disable navigation bar button haptics: ", e);
        }
    }

    public void setPipVisibility(boolean z) {
        try {
            WindowManagerGlobal.getWindowManagerService().setPipVisibility(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to reach window manager", e);
        }
    }

    public void setRecentsVisibility(boolean z) {
        try {
            WindowManagerGlobal.getWindowManagerService().setRecentsVisibility(z);
        } catch (RemoteException unused) {
            Log.w(TAG, "Failed to set recents visibility");
        }
    }

    public void setShelfHeight(boolean z, int i) {
    }
}
