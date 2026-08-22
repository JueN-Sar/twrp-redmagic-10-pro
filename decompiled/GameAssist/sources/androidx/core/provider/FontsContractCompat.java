package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class FontsContractCompat {

    public static final class Columns implements BaseColumns {
    }

    public static class FontFamilyResult {

        /* renamed from: a, reason: collision with root package name */
        private final int f3160a;

        /* renamed from: b, reason: collision with root package name */
        private final FontInfo[] f3161b;

        public FontFamilyResult(int i2, FontInfo[] fontInfoArr) {
            this.f3160a = i2;
            this.f3161b = fontInfoArr;
        }

        static FontFamilyResult a(int i2, FontInfo[] fontInfoArr) {
            return new FontFamilyResult(i2, fontInfoArr);
        }

        public FontInfo[] b() {
            return this.f3161b;
        }

        public int c() {
            return this.f3160a;
        }
    }

    public static class FontInfo {

        /* renamed from: a, reason: collision with root package name */
        private final Uri f3162a;

        /* renamed from: b, reason: collision with root package name */
        private final int f3163b;

        /* renamed from: c, reason: collision with root package name */
        private final int f3164c;

        /* renamed from: d, reason: collision with root package name */
        private final boolean f3165d;

        /* renamed from: e, reason: collision with root package name */
        private final int f3166e;

        public FontInfo(Uri uri, int i2, int i3, boolean z, int i4) {
            this.f3162a = (Uri) Preconditions.h(uri);
            this.f3163b = i2;
            this.f3164c = i3;
            this.f3165d = z;
            this.f3166e = i4;
        }

        static FontInfo a(Uri uri, int i2, int i3, boolean z, int i4) {
            return new FontInfo(uri, i2, i3, z, i4);
        }

        public int b() {
            return this.f3166e;
        }

        public int c() {
            return this.f3163b;
        }

        public Uri d() {
            return this.f3162a;
        }

        public int e() {
            return this.f3164c;
        }

        public boolean f() {
            return this.f3165d;
        }
    }

    public static class FontRequestCallback {

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface FontRequestFailReason {
        }

        public void a(int i2) {
        }

        public void b(Typeface typeface) {
        }
    }

    public static Typeface a(Context context, CancellationSignal cancellationSignal, FontInfo[] fontInfoArr) {
        return TypefaceCompat.b(context, cancellationSignal, fontInfoArr, 0);
    }

    public static FontFamilyResult b(Context context, CancellationSignal cancellationSignal, FontRequest fontRequest) {
        return FontProvider.e(context, fontRequest, cancellationSignal);
    }

    public static Typeface c(Context context, FontRequest fontRequest, int i2, boolean z, int i3, Handler handler, FontRequestCallback fontRequestCallback) {
        CallbackWithHandler callbackWithHandler = new CallbackWithHandler(fontRequestCallback, handler);
        return z ? FontRequestWorker.e(context, fontRequest, callbackWithHandler, i2, i3) : FontRequestWorker.d(context, fontRequest, i2, null, callbackWithHandler);
    }

    @VisibleForTesting
    @Deprecated
    @Nullable
    @RestrictTo
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest fontRequest, @Nullable Resources resources) {
        return FontProvider.getProvider(packageManager, fontRequest, resources);
    }

    @RestrictTo
    @VisibleForTesting
    public static void resetTypefaceCache() {
        FontRequestWorker.f();
    }
}
