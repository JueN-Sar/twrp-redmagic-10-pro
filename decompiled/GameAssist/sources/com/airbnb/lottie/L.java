package com.airbnb.lottie;

import android.content.Context;
import androidx.annotation.RestrictTo;
import com.airbnb.lottie.network.DefaultLottieNetworkFetcher;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import com.airbnb.lottie.network.NetworkCache;
import com.airbnb.lottie.network.NetworkFetcher;
import com.airbnb.lottie.utils.LottieTrace;
import java.io.File;

@RestrictTo
/* loaded from: classes.dex */
public class L {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f9241a = false;

    /* renamed from: b, reason: collision with root package name */
    private static boolean f9242b = false;

    /* renamed from: c, reason: collision with root package name */
    private static boolean f9243c = true;

    /* renamed from: d, reason: collision with root package name */
    private static boolean f9244d = true;

    /* renamed from: e, reason: collision with root package name */
    private static AsyncUpdates f9245e = AsyncUpdates.AUTOMATIC;

    /* renamed from: f, reason: collision with root package name */
    private static LottieNetworkFetcher f9246f;

    /* renamed from: g, reason: collision with root package name */
    private static LottieNetworkCacheProvider f9247g;

    /* renamed from: h, reason: collision with root package name */
    private static volatile NetworkFetcher f9248h;

    /* renamed from: i, reason: collision with root package name */
    private static volatile NetworkCache f9249i;

    /* renamed from: j, reason: collision with root package name */
    private static ThreadLocal f9250j;

    public static void b(String str) {
        if (f9242b) {
            f().a(str);
        }
    }

    public static float c(String str) {
        if (f9242b) {
            return f().b(str);
        }
        return 0.0f;
    }

    public static AsyncUpdates d() {
        return f9245e;
    }

    public static boolean e() {
        return f9244d;
    }

    private static LottieTrace f() {
        LottieTrace lottieTrace = (LottieTrace) f9250j.get();
        if (lottieTrace != null) {
            return lottieTrace;
        }
        LottieTrace lottieTrace2 = new LottieTrace();
        f9250j.set(lottieTrace2);
        return lottieTrace2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ File g(Context context) {
        return new File(context.getCacheDir(), "lottie_network_cache");
    }

    public static NetworkCache h(Context context) {
        if (!f9243c) {
            return null;
        }
        final Context applicationContext = context.getApplicationContext();
        NetworkCache networkCache = f9249i;
        if (networkCache == null) {
            synchronized (NetworkCache.class) {
                try {
                    networkCache = f9249i;
                    if (networkCache == null) {
                        LottieNetworkCacheProvider lottieNetworkCacheProvider = f9247g;
                        if (lottieNetworkCacheProvider == null) {
                            lottieNetworkCacheProvider = new LottieNetworkCacheProvider() { // from class: com.airbnb.lottie.a
                                @Override // com.airbnb.lottie.network.LottieNetworkCacheProvider
                                public final File a() {
                                    File g2;
                                    g2 = L.g(applicationContext);
                                    return g2;
                                }
                            };
                        }
                        networkCache = new NetworkCache(lottieNetworkCacheProvider);
                        f9249i = networkCache;
                    }
                } finally {
                }
            }
        }
        return networkCache;
    }

    public static NetworkFetcher i(Context context) {
        NetworkFetcher networkFetcher = f9248h;
        if (networkFetcher == null) {
            synchronized (NetworkFetcher.class) {
                try {
                    networkFetcher = f9248h;
                    if (networkFetcher == null) {
                        NetworkCache h2 = h(context);
                        LottieNetworkFetcher lottieNetworkFetcher = f9246f;
                        if (lottieNetworkFetcher == null) {
                            lottieNetworkFetcher = new DefaultLottieNetworkFetcher();
                        }
                        networkFetcher = new NetworkFetcher(h2, lottieNetworkFetcher);
                        f9248h = networkFetcher;
                    }
                } finally {
                }
            }
        }
        return networkFetcher;
    }
}
