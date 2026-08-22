package cn.nubia.nbgame.sdk.interfaces;

import android.os.Bundle;

/* loaded from: classes.dex */
public class ListenerManager {

    /* renamed from: a, reason: collision with root package name */
    private static CallbackListener f8265a;

    /* renamed from: b, reason: collision with root package name */
    private static CallbackListener f8266b;

    /* renamed from: c, reason: collision with root package name */
    private static CallbackListener f8267c;

    /* renamed from: d, reason: collision with root package name */
    private static CallbackListener f8268d;

    /* renamed from: e, reason: collision with root package name */
    private static CallbackListener f8269e;

    /* renamed from: f, reason: collision with root package name */
    private static CallbackListener f8270f;

    /* renamed from: g, reason: collision with root package name */
    private static CallbackListener f8271g;

    /* renamed from: h, reason: collision with root package name */
    private static CallbackListener f8272h;

    /* renamed from: i, reason: collision with root package name */
    private static CallbackListener f8273i;

    /* renamed from: j, reason: collision with root package name */
    private static CallbackListener f8274j;

    public static void a(int i2, Bundle bundle) {
        CallbackListener callbackListener = f8271g;
        if (callbackListener != null) {
            callbackListener.a(i2, bundle);
        }
    }

    public static void b(int i2, Bundle bundle) {
        CallbackListener callbackListener = f8269e;
        if (callbackListener != null) {
            callbackListener.a(i2, bundle);
        }
    }

    public static void c(int i2, Bundle bundle) {
        CallbackListener callbackListener = f8268d;
        if (callbackListener != null) {
            callbackListener.a(i2, bundle);
        }
    }

    public static void d(int i2, Bundle bundle) {
        CallbackListener callbackListener = f8267c;
        if (callbackListener != null) {
            callbackListener.a(i2, bundle);
        }
    }

    public static void e(int i2, Bundle bundle) {
        CallbackListener callbackListener = f8272h;
        if (callbackListener != null) {
            callbackListener.a(i2, bundle);
        }
    }

    public static void f(int i2, Bundle bundle) {
        CallbackListener callbackListener = f8270f;
        if (callbackListener != null) {
            callbackListener.a(i2, bundle);
        }
    }

    public static void g(int i2, Bundle bundle) {
        CallbackListener callbackListener = f8266b;
        if (callbackListener != null) {
            callbackListener.a(i2, bundle);
        }
    }

    public static void h(int i2, Bundle bundle) {
        CallbackListener callbackListener = f8265a;
        if (callbackListener != null) {
            callbackListener.a(i2, bundle);
        }
    }

    public static void i(int i2, Bundle bundle) {
        CallbackListener callbackListener = f8273i;
        if (callbackListener != null) {
            callbackListener.a(i2, bundle);
        }
    }

    public static void j(int i2, Bundle bundle) {
        CallbackListener callbackListener = f8274j;
        if (callbackListener != null) {
            callbackListener.a(i2, bundle);
        }
    }

    public static void k(CallbackListener callbackListener) {
        f8267c = callbackListener;
    }

    public static void l(CallbackListener callbackListener) {
        f8266b = callbackListener;
    }

    public static void m(CallbackListener callbackListener) {
        f8265a = callbackListener;
    }
}
