package com.zte.shared.wrapper;

import android.util.Log;
import android.view.WindowManager;

/* loaded from: classes2.dex */
public class WindowManagerWrapper {
    public static final int ACTIVITY_TYPE_ASSISTANT = 4;
    public static final int ACTIVITY_TYPE_DREAM = 5;
    public static final int ACTIVITY_TYPE_HOME = 2;
    public static final int ACTIVITY_TYPE_RECENTS = 3;
    public static final int ACTIVITY_TYPE_STANDARD = 1;
    public static final int ACTIVITY_TYPE_UNDEFINED = 0;
    private static final String TAG = "WindowManagerWrapper";
    public static final int WINDOWING_MODE_FREEFORM = 5;
    public static final int WINDOWING_MODE_FULLSCREEN = 1;
    public static final int WINDOWING_MODE_MULTI_WINDOW = 6;
    public static final int WINDOWING_MODE_PINNED = 2;
    public static final int WINDOWING_MODE_UNDEFINED = 0;

    public static class LayoutParams {
        public static final int PRIVATE_FLAG_ALLOW_ACTION_KEY_EVENTS = 8388608;
        public static final int PRIVATE_FLAG_APP_PROGRESS_GENERATION_ALLOWED = 128;
        public static final int PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC = 16777216;
        public static final int PRIVATE_FLAG_CONSUME_IME_INSETS = 33554432;
        public static final int PRIVATE_FLAG_DISABLE_WALLPAPER_TOUCH_EVENTS = 1024;
        public static final int PRIVATE_FLAG_EDGE_TO_EDGE_ENFORCED = 2048;
        public static final int PRIVATE_FLAG_EXCLUDE_FROM_SCREEN_MAGNIFICATION = 2097152;
        public static final int PRIVATE_FLAG_FIT_INSETS_CONTROLLED = 268435456;
        public static final int PRIVATE_FLAG_FORCE_DECOR_VIEW_VISIBILITY = 8192;
        public static final int PRIVATE_FLAG_FORCE_DRAW_BAR_BACKGROUNDS = 32768;
        public static final int PRIVATE_FLAG_FORCE_HARDWARE_ACCELERATED = 2;
        public static final int PRIVATE_FLAG_IMMERSIVE_CONFIRMATION_WINDOW = 131072;
        public static final int PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME = 1073741824;
        public static final int PRIVATE_FLAG_INTERCEPT_GLOBAL_DRAG_AND_DROP = Integer.MIN_VALUE;
        public static final int PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY = 1048576;
        public static final int PRIVATE_FLAG_LAYOUT_CHILD_WINDOW_IN_PARENT_FRAME = 16384;
        public static final int PRIVATE_FLAG_LAYOUT_SIZE_EXTENDED_BY_CUTOUT = 4096;
        public static final int PRIVATE_FLAG_NOT_MAGNIFIABLE = 4194304;
        public static final int PRIVATE_FLAG_NO_MOVE_ANIMATION = 64;
        public static final int PRIVATE_FLAG_OPTIMIZE_MEASURE = 512;
        public static final int PRIVATE_FLAG_OPT_OUT_EDGE_TO_EDGE = 67108864;
        public static final int PRIVATE_FLAG_OVERRIDE_LAYOUT_IN_DISPLAY_CUTOUT_MODE = 262144;
        public static final int PRIVATE_FLAG_SUSTAINED_PERFORMANCE_MODE = 65536;
        public static final int PRIVATE_FLAG_SYSTEM_APPLICATION_OVERLAY = 8;
        public static final int PRIVATE_FLAG_SYSTEM_ERROR = 256;
        public static final int PRIVATE_FLAG_TRUSTED_OVERLAY = 536870912;
        public static final int PRIVATE_FLAG_UNRESTRICTED_GESTURE_EXCLUSION = 32;
        public static final int PRIVATE_FLAG_WANTS_OFFSET_NOTIFICATIONS = 4;
        public static final int SYSTEM_FLAG_HIDE_NON_SYSTEM_OVERLAY_WINDOWS = 524288;
        public static final int SYSTEM_FLAG_SHOW_FOR_ALL_USERS = 16;

        public static void addHidePrivateFlags(WindowManager.LayoutParams layoutParams, int i2) {
            layoutParams.privateFlags = i2 | layoutParams.privateFlags;
        }

        public static void addHidePrivateTrustedOverlayFlags(WindowManager.LayoutParams layoutParams) {
            layoutParams.privateFlags |= PRIVATE_FLAG_TRUSTED_OVERLAY;
        }

        public static void addPrivateFlags(WindowManager.LayoutParams layoutParams, int i2) {
            setPrivateFlags(layoutParams, i2, i2);
        }

        public static void clearPrivateFlags(WindowManager.LayoutParams layoutParams, int i2) {
            setPrivateFlags(layoutParams, 0, i2);
        }

        public static void setFitInsetsTypes(WindowManager.LayoutParams layoutParams) {
            layoutParams.setFitInsetsTypes(0);
        }

        private static void setPrivateFlags(WindowManager.LayoutParams layoutParams, int i2, int i3) {
            layoutParams.privateFlags = (i2 & i3) | (layoutParams.privateFlags & (~i3));
        }

        public static void setFitInsetsTypes(WindowManager.LayoutParams layoutParams, int i2) {
            layoutParams.setFitInsetsTypes(i2);
        }
    }

    public static WindowManager.LayoutParams createOverlayLayoutParams() {
        Log.v(TAG, "");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2026, 8389416, -3);
        layoutParams.privateFlags = LayoutParams.PRIVATE_FLAG_TRUSTED_OVERLAY;
        return layoutParams;
    }
}
