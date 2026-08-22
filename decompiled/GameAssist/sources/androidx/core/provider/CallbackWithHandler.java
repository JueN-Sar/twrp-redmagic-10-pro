package androidx.core.provider;

import android.graphics.Typeface;
import android.os.Handler;
import androidx.core.provider.FontRequestWorker;
import androidx.core.provider.FontsContractCompat;

/* loaded from: classes.dex */
class CallbackWithHandler {

    /* renamed from: a, reason: collision with root package name */
    private final FontsContractCompat.FontRequestCallback f3127a;

    /* renamed from: b, reason: collision with root package name */
    private final Handler f3128b;

    CallbackWithHandler(FontsContractCompat.FontRequestCallback fontRequestCallback, Handler handler) {
        this.f3127a = fontRequestCallback;
        this.f3128b = handler;
    }

    private void a(final int i2) {
        final FontsContractCompat.FontRequestCallback fontRequestCallback = this.f3127a;
        this.f3128b.post(new Runnable() { // from class: androidx.core.provider.CallbackWithHandler.2
            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.a(i2);
            }
        });
    }

    private void c(final Typeface typeface) {
        final FontsContractCompat.FontRequestCallback fontRequestCallback = this.f3127a;
        this.f3128b.post(new Runnable() { // from class: androidx.core.provider.CallbackWithHandler.1
            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.b(typeface);
            }
        });
    }

    void b(FontRequestWorker.TypefaceResult typefaceResult) {
        if (typefaceResult.a()) {
            c(typefaceResult.f3158a);
        } else {
            a(typefaceResult.f3159b);
        }
    }
}
