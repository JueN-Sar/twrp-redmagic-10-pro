package com.zte.gameassist.ext;

import android.os.Bundle;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.ext.common.GAControllerProxy;
import com.zte.gameassist.ext.utils.ExtendUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nonnull;

/* loaded from: classes2.dex */
public class GameAssistChannel {
    private static final List<ICallback> mCallbacks = new ArrayList();

    public static void sendToGameAssist(@Nonnull String str) {
        sendToGameAssist(str, null);
    }

    public static void sendToGameAssist(@Nonnull String str, @Nonnull Bundle bundle, final Consumer<Bundle> consumer) {
        bundle.putString(GAControllerProxy.BUNDLE_KEY_TO_GAMEASSIST_SERVICES_ACTION, str);
        ICallback.Stub stub = consumer == null ? null : new ICallback.Stub() { // from class: com.zte.gameassist.ext.GameAssistChannel.1
            @Override // com.zte.gameassist.aidl.ICallback
            public void callback(String str2, Bundle bundle2) {
                GameAssistChannel.mCallbacks.remove(this);
                consumer.accept(bundle2);
            }
        };
        if (stub != null) {
            mCallbacks.add(stub);
        }
        ExtendUtils.invokeWithBundle(GAControllerProxy.INVAKE_SEND_TO_GAMEASSIST_SERVICES, bundle, stub);
    }

    public static void sendToGameAssist(@Nonnull String str, String str2) {
        Bundle bundle = new Bundle();
        if (str2 != null) {
            bundle.putString("bundle_key_value", str2);
        }
        sendToGameAssist(str, bundle, null);
    }
}
