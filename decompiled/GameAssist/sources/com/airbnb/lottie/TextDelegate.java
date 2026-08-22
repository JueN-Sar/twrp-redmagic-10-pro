package com.airbnb.lottie;

import androidx.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class TextDelegate {

    /* renamed from: a, reason: collision with root package name */
    private final Map f9338a = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    private boolean f9341d = true;

    /* renamed from: b, reason: collision with root package name */
    private final LottieAnimationView f9339b = null;

    /* renamed from: c, reason: collision with root package name */
    private final LottieDrawable f9340c = null;

    @VisibleForTesting
    TextDelegate() {
    }

    public String a(String str) {
        return str;
    }

    public String b(String str, String str2) {
        return a(str2);
    }

    public final String c(String str, String str2) {
        if (this.f9341d && this.f9338a.containsKey(str2)) {
            return (String) this.f9338a.get(str2);
        }
        String b2 = b(str, str2);
        if (this.f9341d) {
            this.f9338a.put(str2, b2);
        }
        return b2;
    }
}
