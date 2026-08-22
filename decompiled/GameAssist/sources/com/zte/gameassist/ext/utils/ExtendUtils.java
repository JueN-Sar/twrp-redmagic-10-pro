package com.zte.gameassist.ext.utils;

import android.os.Bundle;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.ext.common.GAControllerProxy;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class ExtendUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final List f16679a = new ArrayList();

    /* renamed from: com.zte.gameassist.ext.utils.ExtendUtils$1, reason: invalid class name */
    class AnonymousClass1 extends ICallback.Stub {
        final /* synthetic */ Consumer val$callback;

        AnonymousClass1(Consumer consumer) {
            this.val$callback = consumer;
        }

        @Override // com.zte.gameassist.aidl.ICallback
        public void callback(String str, Bundle bundle) {
            ExtendUtils.f16679a.remove(this);
            if (bundle.containsKey(AbsGameAssistToken.BUNDLE_KEY_VALUE)) {
                this.val$callback.accept(Boolean.valueOf(bundle.getBoolean(AbsGameAssistToken.BUNDLE_KEY_VALUE)));
            } else {
                this.val$callback.accept(Boolean.FALSE);
            }
        }
    }

    /* renamed from: com.zte.gameassist.ext.utils.ExtendUtils$2, reason: invalid class name */
    class AnonymousClass2 extends ICallback.Stub {
        final /* synthetic */ Consumer val$callback;

        AnonymousClass2(Consumer consumer) {
            this.val$callback = consumer;
        }

        @Override // com.zte.gameassist.aidl.ICallback
        public void callback(String str, Bundle bundle) {
            ExtendUtils.f16679a.remove(this);
            if (bundle.containsKey(AbsGameAssistToken.BUNDLE_KEY_VALUE)) {
                this.val$callback.accept(bundle.getString(AbsGameAssistToken.BUNDLE_KEY_VALUE));
            } else {
                this.val$callback.accept("");
            }
        }
    }

    public static void b(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_NAME, str);
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_VALUE, str2);
        c("write_file", bundle);
    }

    public static void c(String str, Bundle bundle) {
        d(str, bundle, null);
    }

    public static void d(String str, Bundle bundle, ICallback iCallback) {
        try {
            GAControllerProxy.c().e(str, bundle, iCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
