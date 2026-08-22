package cn.nubia.componentsdk.pay;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/* loaded from: classes.dex */
public class XToast {

    /* renamed from: a, reason: collision with root package name */
    private static Handler f6034a = new Handler(Looper.getMainLooper());

    /* renamed from: b, reason: collision with root package name */
    private static Toast f6035b = null;

    /* renamed from: c, reason: collision with root package name */
    private static Object f6036c = new Object();

    /* renamed from: cn.nubia.componentsdk.pay.XToast$2, reason: invalid class name */
    final class AnonymousClass2 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Context f6040c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f6041h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ int f6042i;

        @Override // java.lang.Runnable
        public void run() {
            synchronized (XToast.f6036c) {
                try {
                    if (XToast.f6035b != null) {
                        XToast.f6035b.cancel();
                    }
                    Context context = this.f6040c;
                    Toast unused = XToast.f6035b = Toast.makeText(context, context.getResources().getString(this.f6041h), this.f6042i);
                    XToast.f6035b.show();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public static void d(final String str, final int i2, final Context context) {
        f6034a.removeCallbacksAndMessages(null);
        f6034a.post(new Runnable() { // from class: cn.nubia.componentsdk.pay.XToast.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (XToast.f6036c) {
                    try {
                        if (XToast.f6035b != null) {
                            XToast.f6035b.cancel();
                        }
                        Toast unused = XToast.f6035b = Toast.makeText(context, str, i2);
                        XToast.f6035b.show();
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        });
    }
}
