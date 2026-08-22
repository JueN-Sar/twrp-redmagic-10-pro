package com.airbnb.lottie.network;

import android.content.Context;
import android.util.Pair;
import androidx.annotation.RestrictTo;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.utils.Logger;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

@RestrictTo
/* loaded from: classes.dex */
public class NetworkFetcher {

    /* renamed from: a, reason: collision with root package name */
    private final NetworkCache f9788a;

    /* renamed from: b, reason: collision with root package name */
    private final LottieNetworkFetcher f9789b;

    /* renamed from: com.airbnb.lottie.network.NetworkFetcher$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9790a;

        static {
            int[] iArr = new int[FileExtension.values().length];
            f9790a = iArr;
            try {
                iArr[FileExtension.ZIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9790a[FileExtension.GZIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public NetworkFetcher(NetworkCache networkCache, LottieNetworkFetcher lottieNetworkFetcher) {
        this.f9788a = networkCache;
        this.f9789b = lottieNetworkFetcher;
    }

    private LottieComposition a(Context context, String str, String str2) {
        NetworkCache networkCache;
        Pair a2;
        LottieResult y;
        if (str2 == null || (networkCache = this.f9788a) == null || (a2 = networkCache.a(str)) == null) {
            return null;
        }
        FileExtension fileExtension = (FileExtension) a2.first;
        InputStream inputStream = (InputStream) a2.second;
        int i2 = AnonymousClass1.f9790a[fileExtension.ordinal()];
        if (i2 == 1) {
            y = LottieCompositionFactory.y(context, new ZipInputStream(inputStream), str2);
        } else if (i2 != 2) {
            y = LottieCompositionFactory.o(inputStream, str2);
        } else {
            try {
                y = LottieCompositionFactory.o(new GZIPInputStream(inputStream), str2);
            } catch (IOException e2) {
                y = new LottieResult((Throwable) e2);
            }
        }
        if (y.b() != null) {
            return (LottieComposition) y.b();
        }
        return null;
    }

    private LottieResult b(Context context, String str, String str2) {
        Logger.a("Fetching " + str);
        Closeable closeable = null;
        try {
            try {
                LottieFetchResult a2 = this.f9789b.a(str);
                if (!a2.H()) {
                    LottieResult lottieResult = new LottieResult((Throwable) new IllegalArgumentException(a2.Z()));
                    try {
                        a2.close();
                    } catch (IOException e2) {
                        Logger.d("LottieFetchResult close failed ", e2);
                    }
                    return lottieResult;
                }
                LottieResult e3 = e(context, str, a2.x(), a2.u(), str2);
                StringBuilder sb = new StringBuilder();
                sb.append("Completed fetch from network. Success: ");
                sb.append(e3.b() != null);
                Logger.a(sb.toString());
                try {
                    a2.close();
                } catch (IOException e4) {
                    Logger.d("LottieFetchResult close failed ", e4);
                }
                return e3;
            } catch (Exception e5) {
                LottieResult lottieResult2 = new LottieResult((Throwable) e5);
                if (0 != 0) {
                    try {
                        closeable.close();
                    } catch (IOException e6) {
                        Logger.d("LottieFetchResult close failed ", e6);
                    }
                }
                return lottieResult2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (IOException e7) {
                    Logger.d("LottieFetchResult close failed ", e7);
                }
            }
            throw th;
        }
    }

    private LottieResult d(String str, InputStream inputStream, String str2) {
        NetworkCache networkCache;
        return (str2 == null || (networkCache = this.f9788a) == null) ? LottieCompositionFactory.o(new GZIPInputStream(inputStream), null) : LottieCompositionFactory.o(new GZIPInputStream(new FileInputStream(networkCache.g(str, inputStream, FileExtension.GZIP))), str);
    }

    private LottieResult e(Context context, String str, InputStream inputStream, String str2, String str3) {
        LottieResult g2;
        FileExtension fileExtension;
        NetworkCache networkCache;
        if (str2 == null) {
            str2 = "application/json";
        }
        if (str2.contains("application/zip") || str2.contains("application/x-zip") || str2.contains("application/x-zip-compressed") || str.split("\\?")[0].endsWith(".lottie")) {
            Logger.a("Handling zip response.");
            FileExtension fileExtension2 = FileExtension.ZIP;
            g2 = g(context, str, inputStream, str3);
            fileExtension = fileExtension2;
        } else if (str2.contains("application/gzip") || str2.contains("application/x-gzip") || str.split("\\?")[0].endsWith(".tgs")) {
            Logger.a("Handling gzip response.");
            fileExtension = FileExtension.GZIP;
            g2 = d(str, inputStream, str3);
        } else {
            Logger.a("Received json response.");
            fileExtension = FileExtension.JSON;
            g2 = f(str, inputStream, str3);
        }
        if (str3 != null && g2.b() != null && (networkCache = this.f9788a) != null) {
            networkCache.f(str, fileExtension);
        }
        return g2;
    }

    private LottieResult f(String str, InputStream inputStream, String str2) {
        NetworkCache networkCache;
        return (str2 == null || (networkCache = this.f9788a) == null) ? LottieCompositionFactory.o(inputStream, null) : LottieCompositionFactory.o(new FileInputStream(networkCache.g(str, inputStream, FileExtension.JSON).getAbsolutePath()), str);
    }

    private LottieResult g(Context context, String str, InputStream inputStream, String str2) {
        NetworkCache networkCache;
        return (str2 == null || (networkCache = this.f9788a) == null) ? LottieCompositionFactory.y(context, new ZipInputStream(inputStream), null) : LottieCompositionFactory.y(context, new ZipInputStream(new FileInputStream(networkCache.g(str, inputStream, FileExtension.ZIP))), str);
    }

    public LottieResult c(Context context, String str, String str2) {
        LottieComposition a2 = a(context, str, str2);
        if (a2 != null) {
            return new LottieResult(a2);
        }
        Logger.a("Animation for " + str + " not found in cache. Fetching from network.");
        return b(context, str, str2);
    }
}
