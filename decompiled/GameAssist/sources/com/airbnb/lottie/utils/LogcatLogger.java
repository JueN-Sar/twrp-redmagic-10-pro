package com.airbnb.lottie.utils;

import android.util.Log;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieLogger;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class LogcatLogger implements LottieLogger {

    /* renamed from: a, reason: collision with root package name */
    private static final Set f9912a = new HashSet();

    @Override // com.airbnb.lottie.LottieLogger
    public void a(String str) {
        d(str, null);
    }

    @Override // com.airbnb.lottie.LottieLogger
    public void b(String str, Throwable th) {
        if (L.f9241a) {
            Log.d("LOTTIE", str, th);
        }
    }

    @Override // com.airbnb.lottie.LottieLogger
    public void c(String str) {
        e(str, null);
    }

    @Override // com.airbnb.lottie.LottieLogger
    public void d(String str, Throwable th) {
        Set set = f9912a;
        if (set.contains(str)) {
            return;
        }
        Log.w("LOTTIE", str, th);
        set.add(str);
    }

    public void e(String str, Throwable th) {
        if (L.f9241a) {
            Log.d("LOTTIE", str, th);
        }
    }
}
