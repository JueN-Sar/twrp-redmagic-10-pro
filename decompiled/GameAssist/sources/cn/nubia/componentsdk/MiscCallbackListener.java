package cn.nubia.componentsdk;

import cn.nubia.componentsdk.constant.CallbackListener;
import cn.nubia.componentsdk.until.TranslationErrorCode;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class MiscCallbackListener {

    /* renamed from: a, reason: collision with root package name */
    private static PayProcessListener f5874a;

    /* renamed from: cn.nubia.componentsdk.MiscCallbackListener$1, reason: invalid class name */
    final class AnonymousClass1 implements PayProcessListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ CallbackListener f5875a;

        @Override // cn.nubia.componentsdk.MiscCallbackListener.PayProcessListener
        public void a(int i2, String str) {
            CallbackListener callbackListener = this.f5875a;
            if (callbackListener != null) {
                callbackListener.a(i2, new ArrayList());
            }
        }
    }

    public interface PayProcessListener {
        void a(int i2, String str);
    }

    public static void a(int i2, String str) {
        int a2 = TranslationErrorCode.a(i2);
        PayProcessListener payProcessListener = f5874a;
        if (payProcessListener != null) {
            payProcessListener.a(a2, str);
            PayClientManager.I();
        }
    }

    public static synchronized void b(final CallbackListener callbackListener) {
        synchronized (MiscCallbackListener.class) {
            f5874a = new PayProcessListener() { // from class: cn.nubia.componentsdk.MiscCallbackListener.2
                @Override // cn.nubia.componentsdk.MiscCallbackListener.PayProcessListener
                public void a(int i2, String str) {
                    CallbackListener callbackListener2 = CallbackListener.this;
                    if (callbackListener2 != null) {
                        callbackListener2.a(i2, str);
                    }
                }
            };
        }
    }
}
