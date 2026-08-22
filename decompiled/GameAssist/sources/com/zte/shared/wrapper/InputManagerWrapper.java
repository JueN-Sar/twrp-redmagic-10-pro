package com.zte.shared.wrapper;

import android.content.Context;
import android.graphics.Point;
import android.hardware.input.InputManager;
import android.hardware.input.InputSettings;
import android.os.Looper;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.InputMonitor;
import android.view.KeyEvent;
import com.zte.gameassist.config.ZteFeature;

/* loaded from: classes2.dex */
public class InputManagerWrapper {
    public static final boolean IS_SUPPORT_TGK_V4;
    public static final int MIN_POINTER_SPEED = -7;
    private static final String TAG = "InputManagerWrapper";
    public static final boolean ZTE_FEATURE_REDMAGIC_SPORTS_HANDLE;
    public static final boolean ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY;
    public static final boolean ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD;

    public interface InputEventListener {
        default void onDispose() {
        }

        void onInputEvent(InputEvent inputEvent);

        default void onReceiverInit(ZteInputEventReceiver zteInputEventReceiver) {
        }
    }

    public static class ZteGestureInputEventReciver extends ZteInputEventReceiver {
        private final InputMonitor mInputMonitor;

        public ZteGestureInputEventReciver(InputMonitor inputMonitor, Looper looper, InputEventListener inputEventListener) {
            super(inputMonitor.getInputChannel(), looper, inputEventListener);
            this.mInputMonitor = inputMonitor;
        }

        @Override // com.zte.shared.wrapper.InputManagerWrapper.ZteInputEventReceiver
        public void dispose() {
            this.mInputMonitor.dispose();
            super.dispose();
        }

        @Override // com.zte.shared.wrapper.InputManagerWrapper.ZteInputEventReceiver
        public void pilferPointers() {
            this.mInputMonitor.pilferPointers();
        }
    }

    public static class ZteInputEventReceiver {
        private InputEventListener mInputEventListener;
        private InputEventReceiver mInputEventReceiver;

        public ZteInputEventReceiver(InputChannel inputChannel, Looper looper, InputEventListener inputEventListener) {
            this.mInputEventReceiver = new InputEventReceiver(inputChannel, looper) { // from class: com.zte.shared.wrapper.InputManagerWrapper.ZteInputEventReceiver.1
                public void onInputEvent(InputEvent inputEvent) {
                    try {
                        try {
                            if (ZteInputEventReceiver.this.mInputEventListener != null) {
                                ZteInputEventReceiver.this.mInputEventListener.onInputEvent(inputEvent);
                            }
                        } catch (Exception e2) {
                            Log.i(InputManagerWrapper.TAG, "onInputEvent =" + e2.getMessage());
                        }
                    } finally {
                        finishInputEvent(inputEvent, false);
                    }
                }
            };
            this.mInputEventListener = inputEventListener;
            inputEventListener.onReceiverInit(this);
            Log.i(InputManagerWrapper.TAG, "ZteInputEventReceiver init success ");
        }

        public void dispose() {
            InputEventReceiver inputEventReceiver = this.mInputEventReceiver;
            if (inputEventReceiver != null) {
                inputEventReceiver.dispose();
                this.mInputEventReceiver = null;
            }
            InputEventListener inputEventListener = this.mInputEventListener;
            if (inputEventListener != null) {
                inputEventListener.onDispose();
                this.mInputEventListener = null;
            }
        }

        public void pilferPointers() {
        }
    }

    static {
        boolean z = ZteFeatureWrapper.getBoolean(ZteFeature.ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY, false);
        ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY = z;
        ZTE_FEATURE_REDMAGIC_SPORTS_HANDLE = ZteFeatureWrapper.getBoolean("ZTE_FEATURE_REDMAGIC_SPORTS_HANDLE", false);
        ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD = ZteFeatureWrapper.getBoolean(ZteFeature.ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD, false);
        IS_SUPPORT_TGK_V4 = z;
    }

    public static void enableTgkDrive(InputManager inputManager, boolean z) {
        try {
            inputManager.enableTgkDrive(z);
        } catch (Exception e2) {
            Log.w(TAG, "Unsupported enableTgkDrive, " + e2.getMessage());
        }
    }

    public static int getPointerSpeed(Context context) {
        return InputSettings.getPointerSpeed(context);
    }

    public static ZteInputEventReceiver initGestureInputMonitor(Context context, String str, Looper looper, InputEventListener inputEventListener) {
        return new ZteGestureInputEventReciver(((InputManager) context.getSystemService("input")).monitorGestureInput(str, context.getDisplayId()), looper, inputEventListener);
    }

    public static ZteInputEventReceiver initInputMonitor(Context context, String str, Looper looper, InputEventListener inputEventListener) {
        return new ZteInputEventReceiver(((InputManager) context.getSystemService("input")).myInput(str, context), looper, inputEventListener);
    }

    public static void inject(int i2) {
        sendEvent(i2, 0);
        sendEvent(i2, 1);
    }

    public static boolean isOpen4DNode() {
        return SystemProperties.get("persist.sys.vibrator4d", "off").equals("on");
    }

    private static void sendEvent(int i2, int i3) {
        sendEvent(i2, i3, 0, SystemClock.uptimeMillis());
    }

    public static void sendKeyBack(int i2, int i3) {
        long uptimeMillis = SystemClock.uptimeMillis();
        KeyEvent keyEvent = new KeyEvent(uptimeMillis, uptimeMillis, i2, 4, 0, 0, 0, 0, 72, 257);
        keyEvent.setDisplayId(i3);
        InputManager.getInstance().injectInputEvent(keyEvent, 0);
    }

    public static void setGameKeyEnable(InputManager inputManager, boolean z, Context context) {
        try {
            inputManager.setGameKeyEnable(z, context);
        } catch (Exception e2) {
            Log.w(TAG, "Unsupported setGameKeyEnable, " + e2.getMessage());
        }
    }

    public static void setGameLeftKeyLinkFunction(InputManager inputManager, int i2) {
        try {
            inputManager.setGameLeftKeyLinkFunction(i2);
        } catch (Exception e2) {
            Log.w(TAG, "Unsupported setGameLeftKeyLinkFunction, " + e2.getMessage());
        }
    }

    public static void setGamePoint(InputManager inputManager, Point point) {
        try {
            inputManager.setGamePoint(point);
        } catch (Exception e2) {
            Log.w(TAG, "Unsupported setGamePoint, " + e2.getMessage());
        }
    }

    public static void setGameRightKeyLinkFunction(InputManager inputManager, int i2) {
        try {
            inputManager.setGameRightKeyLinkFunction(i2);
        } catch (Exception e2) {
            Log.w(TAG, "Unsupported setGameRightKeyLinkFunction, " + e2.getMessage());
        }
    }

    public static void setLeftAndRightGameKeyEnable(InputManager inputManager, Point point, Point point2, boolean z, Context context) {
        try {
            inputManager.setLeftAndRightGameKeyEnable(point, point2, z, context);
        } catch (Exception e2) {
            Log.w(TAG, "Unsupported setLeftAndRightGameKeyEnable, " + e2.getMessage());
        }
    }

    public static void setOnlyLeftGameKeyEnable(InputManager inputManager, Point point, Context context) {
        try {
            inputManager.setOnlyLeftGameKeyEnable(point, context);
        } catch (Exception e2) {
            Log.w(TAG, "Unsupported setOnlyLeftGameKeyEnable, " + e2.getMessage());
        }
    }

    public static void setOnlyRightGameKeyEnable(InputManager inputManager, Point point, Context context) {
        try {
            inputManager.setOnlyRightGameKeyEnable(point, context);
        } catch (Exception e2) {
            Log.w(TAG, "Unsupported setOnlyRightGameKeyEnable, " + e2.getMessage());
        }
    }

    public static void setPointerSpeed(Context context, int i2) {
        InputSettings.setPointerSpeed(context, i2);
    }

    public static void setTgkVersion(InputManager inputManager, int i2) {
        try {
            inputManager.setTgkVersion(i2);
        } catch (Exception e2) {
            Log.w(TAG, "Unsupported setTgkVersion, " + e2.getMessage());
        }
    }

    public static void setTouchHapticFeedbackEnable(InputManager inputManager, boolean z, Context context) {
        try {
            inputManager.setTouchHapticFeedbackEnable(z, context);
        } catch (Exception e2) {
            Log.w(TAG, "Unsupported setTouchHapticFeedbackEnable, " + e2.getMessage());
        }
    }

    public static void tryPointerSpeed(InputManager inputManager, int i2) {
        inputManager.tryPointerSpeed(i2);
    }

    private static void sendEvent(int i2, int i3, int i4, long j2) {
        InputManager.getInstance().injectInputEvent(new KeyEvent(j2, j2, i3, i2, (i4 & 128) != 0 ? 1 : 0, 0, -1, 0, i4 | 72, 257), 0);
    }
}
