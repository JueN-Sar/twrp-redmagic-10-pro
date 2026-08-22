package com.airbnb.lottie;

import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import java.io.File;

/* loaded from: classes.dex */
public class LottieConfig {

    public static final class Builder {

        /* renamed from: com.airbnb.lottie.LottieConfig$Builder$1, reason: invalid class name */
        class AnonymousClass1 implements LottieNetworkCacheProvider {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ File f9283a;

            @Override // com.airbnb.lottie.network.LottieNetworkCacheProvider
            public File a() {
                if (this.f9283a.isDirectory()) {
                    return this.f9283a;
                }
                throw new IllegalArgumentException("cache file must be a directory");
            }
        }

        /* renamed from: com.airbnb.lottie.LottieConfig$Builder$2, reason: invalid class name */
        class AnonymousClass2 implements LottieNetworkCacheProvider {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ LottieNetworkCacheProvider f9284a;

            @Override // com.airbnb.lottie.network.LottieNetworkCacheProvider
            public File a() {
                File a2 = this.f9284a.a();
                if (a2.isDirectory()) {
                    return a2;
                }
                throw new IllegalArgumentException("cache file must be a directory");
            }
        }
    }
}
