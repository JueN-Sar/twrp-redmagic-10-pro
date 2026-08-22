package com.android.systemui.shared.system;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.BatchedInputEventReceiver;
import android.view.Choreographer;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventSender;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.NubiaInput;

/* loaded from: classes2.dex */
public class InputChannelCompat {

    public static class InputEventDispatcher {
        private final InputChannel mInputChannel;
        private final InputEventSender mSender;

        public InputEventDispatcher(InputChannel inputChannel, Looper looper) {
            this.mInputChannel = inputChannel;
            this.mSender = new InputEventSender(inputChannel, looper) { // from class: com.android.systemui.shared.system.InputChannelCompat.InputEventDispatcher.1
            };
        }

        public void dispatch(InputEvent inputEvent) {
            this.mSender.sendInputEvent(inputEvent.getSequenceNumber(), inputEvent);
        }

        public void dispose() {
            this.mSender.dispose();
            this.mInputChannel.dispose();
        }
    }

    public interface InputEventListener {
        void onInputEvent(InputEvent inputEvent);
    }

    public static class InputEventReceiver {
        private final InputChannel mInputChannel;
        private final BatchedInputEventReceiver mReceiver;

        public InputEventReceiver(InputChannel inputChannel, Looper looper, Choreographer choreographer, final InputEventListener inputEventListener) {
            this.mInputChannel = inputChannel;
            this.mReceiver = new BatchedInputEventReceiver(inputChannel, looper, choreographer) { // from class: com.android.systemui.shared.system.InputChannelCompat.InputEventReceiver.1
                public void onInputEvent(InputEvent inputEvent) {
                    inputEventListener.onInputEvent(inputEvent);
                    finishInputEvent(inputEvent, true);
                }
            };
        }

        public void dispose() {
            this.mReceiver.dispose();
            this.mInputChannel.dispose();
        }
    }

    public static class NubiaInputEventReceiver {
        private final InputChannel mInputChannel;
        private final SysUiInputEventReceiver mReceiver;

        public NubiaInputEventReceiver(InputChannel inputChannel, Looper looper, final InputEventListener inputEventListener) {
            this.mInputChannel = inputChannel;
            this.mReceiver = new SysUiInputEventReceiver(inputChannel, looper) { // from class: com.android.systemui.shared.system.InputChannelCompat.NubiaInputEventReceiver.1
                public void onInputEvent(InputEvent inputEvent) {
                    try {
                        try {
                            inputEventListener.onInputEvent(inputEvent);
                        } catch (Exception e) {
                            Log.i("SysShared", "onInputEvent =" + e.getMessage());
                        }
                    } finally {
                        finishInputEvent(inputEvent, false);
                    }
                }
            };
            Log.i("SysShared", "NubiaInputEventReceiver init success ");
        }

        public void dispose() {
            this.mReceiver.dispose();
            this.mInputChannel.dispose();
        }
    }

    static class SysUiInputEventReceiver extends android.view.InputEventReceiver {
        SysUiInputEventReceiver(InputChannel inputChannel, Looper looper) {
            super(inputChannel, looper);
        }

        public void onInputEvent(InputEvent inputEvent, int i) {
        }
    }

    public static InputEventReceiver fromBundle(Bundle bundle, String str, Looper looper, Choreographer choreographer, InputEventListener inputEventListener) {
        return new InputEventReceiver(bundle.getParcelable(str), looper, choreographer, inputEventListener);
    }

    public static NubiaInputEventReceiver initInputMonitor(Context context, String str, Looper looper, InputEventListener inputEventListener) {
        return new NubiaInputEventReceiver(((InputManager) context.getSystemService("input")).myInput(str, context), looper, inputEventListener);
    }

    public static void inject(int i) {
        sendEvent(i, 0);
        sendEvent(i, 1);
    }

    public static boolean isInFreeformModeOpen() {
        return NubiaInput.getInstance().isInFreeformModeOpen();
    }

    public static boolean mergeMotionEvent(MotionEvent motionEvent, MotionEvent motionEvent2) {
        return motionEvent2.addBatch(motionEvent);
    }

    private static void sendEvent(int i, int i2) {
        sendEvent(i, i2, 0, SystemClock.uptimeMillis());
    }

    private static void sendEvent(int i, int i2, int i3, long j) {
        InputManager.getInstance().injectInputEvent(new KeyEvent(j, j, i2, i, (i3 & 128) != 0 ? 1 : 0, 0, -1, 0, i3 | 72, 257), 0);
    }
}
