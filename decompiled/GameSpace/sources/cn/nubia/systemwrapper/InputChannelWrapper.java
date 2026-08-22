package cn.nubia.systemwrapper;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.InputEvent;
import com.android.systemui.shared.system.InputChannelCompat;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class InputChannelWrapper {
    private HashMap<String, InputChannelCompat.NubiaInputEventReceiver> mReceiver;

    public interface EventListener {
        default void onInputEvent(InputEvent inputEvent) {
        }
    }

    private static class InstanceHolder {
        private static InputChannelWrapper sInstance = new InputChannelWrapper();

        private InstanceHolder() {
        }
    }

    private InputChannelWrapper() {
        this.mReceiver = new HashMap<>();
    }

    public static InputChannelWrapper getInstance() {
        return InstanceHolder.sInstance;
    }

    public static void injectBackEvent() {
        InputChannelCompat.inject(4);
    }

    public static void injectHomeEvent() {
        InputChannelCompat.inject(3);
    }

    public boolean isInFreeformModeOpen() {
        Log.d("zteg", "isInFreeformModeOpen()");
        return InputChannelCompat.isInFreeformModeOpen();
    }

    public void registerInputMonitor(Looper looper, Context context, String str, final EventListener eventListener) {
        this.mReceiver.put(str, InputChannelCompat.initInputMonitor(context, str, looper, new InputChannelCompat.InputEventListener() { // from class: cn.nubia.systemwrapper.InputChannelWrapper.1
            @Override // com.android.systemui.shared.system.InputChannelCompat.InputEventListener
            public void onInputEvent(InputEvent inputEvent) {
                eventListener.onInputEvent(inputEvent);
            }
        }));
    }

    public void unRegister(String str) {
        InputChannelCompat.NubiaInputEventReceiver nubiaInputEventReceiver = this.mReceiver.get(str);
        if (nubiaInputEventReceiver != null) {
            nubiaInputEventReceiver.dispose();
        }
    }
}
