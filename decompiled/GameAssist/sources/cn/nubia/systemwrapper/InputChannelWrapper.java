package cn.nubia.systemwrapper;

import android.content.Context;
import android.os.Looper;
import android.view.InputEvent;
import com.zte.shared.wrapper.InputManagerWrapper;
import java.util.HashMap;

/* loaded from: classes.dex */
public class InputChannelWrapper {

    /* renamed from: a, reason: collision with root package name */
    private HashMap f9223a;

    public interface EventListener {
        default void onInputEvent(InputEvent inputEvent) {
        }
    }

    private static class InstanceHolder {

        /* renamed from: a, reason: collision with root package name */
        private static InputChannelWrapper f9225a = new InputChannelWrapper();
    }

    public static InputChannelWrapper a() {
        return InstanceHolder.f9225a;
    }

    public static void b() {
        InputManagerWrapper.inject(3);
    }

    public void c(Looper looper, Context context, String str, final EventListener eventListener) {
        this.f9223a.put(str, InputManagerWrapper.initInputMonitor(context, str, looper, new InputManagerWrapper.InputEventListener(this) { // from class: cn.nubia.systemwrapper.InputChannelWrapper.1
            @Override // com.zte.shared.wrapper.InputManagerWrapper.InputEventListener
            public void onInputEvent(InputEvent inputEvent) {
                eventListener.onInputEvent(inputEvent);
            }
        }));
    }

    public void d(String str) {
        InputManagerWrapper.ZteInputEventReceiver zteInputEventReceiver = (InputManagerWrapper.ZteInputEventReceiver) this.f9223a.get(str);
        if (zteInputEventReceiver != null) {
            zteInputEventReceiver.dispose();
        }
    }

    private InputChannelWrapper() {
        this.f9223a = new HashMap();
    }
}
