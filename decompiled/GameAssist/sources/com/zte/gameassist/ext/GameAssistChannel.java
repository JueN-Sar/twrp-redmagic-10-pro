package com.zte.gameassist.ext;

import android.os.Bundle;
import com.zte.gameassist.aidl.ICallback;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class GameAssistChannel {

    /* renamed from: a, reason: collision with root package name */
    private static final List f16661a = new ArrayList();

    /* renamed from: com.zte.gameassist.ext.GameAssistChannel$1, reason: invalid class name */
    class AnonymousClass1 extends ICallback.Stub {
        final /* synthetic */ Consumer val$callback;

        AnonymousClass1(Consumer consumer) {
            this.val$callback = consumer;
        }

        @Override // com.zte.gameassist.aidl.ICallback
        public void callback(String str, Bundle bundle) {
            GameAssistChannel.f16661a.remove(this);
            this.val$callback.accept(bundle);
        }
    }
}
