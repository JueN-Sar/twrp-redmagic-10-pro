package androidx.emoji2.text;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.os.TraceCompat;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.FontRequestEmojiCompatConfig;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes.dex */
public class FontRequestEmojiCompatConfig extends EmojiCompat.Config {

    /* renamed from: k, reason: collision with root package name */
    private static final FontProviderHelper f3759k = new FontProviderHelper();

    public static class ExponentialBackoffRetryPolicy extends RetryPolicy {

        /* renamed from: a, reason: collision with root package name */
        private final long f3760a;

        /* renamed from: b, reason: collision with root package name */
        private long f3761b;

        @Override // androidx.emoji2.text.FontRequestEmojiCompatConfig.RetryPolicy
        public long a() {
            if (this.f3761b == 0) {
                this.f3761b = SystemClock.uptimeMillis();
                return 0L;
            }
            long uptimeMillis = SystemClock.uptimeMillis() - this.f3761b;
            if (uptimeMillis > this.f3760a) {
                return -1L;
            }
            return Math.min(Math.max(uptimeMillis, 1000L), this.f3760a - uptimeMillis);
        }
    }

    @RestrictTo
    public static class FontProviderHelper {
        public Typeface a(Context context, FontsContractCompat.FontInfo fontInfo) {
            return FontsContractCompat.a(context, null, new FontsContractCompat.FontInfo[]{fontInfo});
        }

        public FontsContractCompat.FontFamilyResult b(Context context, FontRequest fontRequest) {
            return FontsContractCompat.b(context, null, fontRequest);
        }

        public void c(Context context, Uri uri, ContentObserver contentObserver) {
            context.getContentResolver().registerContentObserver(uri, false, contentObserver);
        }

        public void d(Context context, ContentObserver contentObserver) {
            context.getContentResolver().unregisterContentObserver(contentObserver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class FontRequestMetadataLoader implements EmojiCompat.MetadataRepoLoader {

        /* renamed from: a, reason: collision with root package name */
        private final Context f3762a;

        /* renamed from: b, reason: collision with root package name */
        private final FontRequest f3763b;

        /* renamed from: c, reason: collision with root package name */
        private final FontProviderHelper f3764c;

        /* renamed from: d, reason: collision with root package name */
        private final Object f3765d = new Object();

        /* renamed from: e, reason: collision with root package name */
        private Handler f3766e;

        /* renamed from: f, reason: collision with root package name */
        private Executor f3767f;

        /* renamed from: g, reason: collision with root package name */
        private ThreadPoolExecutor f3768g;

        /* renamed from: h, reason: collision with root package name */
        private RetryPolicy f3769h;

        /* renamed from: i, reason: collision with root package name */
        EmojiCompat.MetadataRepoLoaderCallback f3770i;

        /* renamed from: j, reason: collision with root package name */
        private ContentObserver f3771j;

        /* renamed from: k, reason: collision with root package name */
        private Runnable f3772k;

        FontRequestMetadataLoader(Context context, FontRequest fontRequest, FontProviderHelper fontProviderHelper) {
            Preconditions.i(context, "Context cannot be null");
            Preconditions.i(fontRequest, "FontRequest cannot be null");
            this.f3762a = context.getApplicationContext();
            this.f3763b = fontRequest;
            this.f3764c = fontProviderHelper;
        }

        private void b() {
            synchronized (this.f3765d) {
                try {
                    this.f3770i = null;
                    ContentObserver contentObserver = this.f3771j;
                    if (contentObserver != null) {
                        this.f3764c.d(this.f3762a, contentObserver);
                        this.f3771j = null;
                    }
                    Handler handler = this.f3766e;
                    if (handler != null) {
                        handler.removeCallbacks(this.f3772k);
                    }
                    this.f3766e = null;
                    ThreadPoolExecutor threadPoolExecutor = this.f3768g;
                    if (threadPoolExecutor != null) {
                        threadPoolExecutor.shutdown();
                    }
                    this.f3767f = null;
                    this.f3768g = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        private FontsContractCompat.FontInfo e() {
            try {
                FontsContractCompat.FontFamilyResult b2 = this.f3764c.b(this.f3762a, this.f3763b);
                if (b2.c() == 0) {
                    FontsContractCompat.FontInfo[] b3 = b2.b();
                    if (b3 == null || b3.length == 0) {
                        throw new RuntimeException("fetchFonts failed (empty result)");
                    }
                    return b3[0];
                }
                throw new RuntimeException("fetchFonts failed (" + b2.c() + ")");
            } catch (PackageManager.NameNotFoundException e2) {
                throw new RuntimeException("provider not found", e2);
            }
        }

        private void f(Uri uri, long j2) {
            synchronized (this.f3765d) {
                try {
                    Handler handler = this.f3766e;
                    if (handler == null) {
                        handler = ConcurrencyHelpers.d();
                        this.f3766e = handler;
                    }
                    if (this.f3771j == null) {
                        ContentObserver contentObserver = new ContentObserver(handler) { // from class: androidx.emoji2.text.FontRequestEmojiCompatConfig.FontRequestMetadataLoader.1
                            @Override // android.database.ContentObserver
                            public void onChange(boolean z, Uri uri2) {
                                FontRequestMetadataLoader.this.d();
                            }
                        };
                        this.f3771j = contentObserver;
                        this.f3764c.c(this.f3762a, uri, contentObserver);
                    }
                    if (this.f3772k == null) {
                        this.f3772k = new Runnable() { // from class: androidx.emoji2.text.d
                            @Override // java.lang.Runnable
                            public final void run() {
                                FontRequestEmojiCompatConfig.FontRequestMetadataLoader.this.d();
                            }
                        };
                    }
                    handler.postDelayed(this.f3772k, j2);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoader
        public void a(EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback) {
            Preconditions.i(metadataRepoLoaderCallback, "LoaderCallback cannot be null");
            synchronized (this.f3765d) {
                this.f3770i = metadataRepoLoaderCallback;
            }
            d();
        }

        void c() {
            synchronized (this.f3765d) {
                try {
                    if (this.f3770i == null) {
                        return;
                    }
                    try {
                        FontsContractCompat.FontInfo e2 = e();
                        int b2 = e2.b();
                        if (b2 == 2) {
                            synchronized (this.f3765d) {
                                try {
                                    RetryPolicy retryPolicy = this.f3769h;
                                    if (retryPolicy != null) {
                                        long a2 = retryPolicy.a();
                                        if (a2 >= 0) {
                                            f(e2.d(), a2);
                                            return;
                                        }
                                    }
                                } finally {
                                }
                            }
                        }
                        if (b2 != 0) {
                            throw new RuntimeException("fetchFonts result is not OK. (" + b2 + ")");
                        }
                        try {
                            TraceCompat.a("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                            Typeface a3 = this.f3764c.a(this.f3762a, e2);
                            ByteBuffer f2 = TypefaceCompatUtil.f(this.f3762a, null, e2.d());
                            if (f2 == null || a3 == null) {
                                throw new RuntimeException("Unable to open file.");
                            }
                            MetadataRepo b3 = MetadataRepo.b(a3, f2);
                            TraceCompat.b();
                            synchronized (this.f3765d) {
                                try {
                                    EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback = this.f3770i;
                                    if (metadataRepoLoaderCallback != null) {
                                        metadataRepoLoaderCallback.b(b3);
                                    }
                                } finally {
                                }
                            }
                            b();
                        } catch (Throwable th) {
                            TraceCompat.b();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        synchronized (this.f3765d) {
                            try {
                                EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback2 = this.f3770i;
                                if (metadataRepoLoaderCallback2 != null) {
                                    metadataRepoLoaderCallback2.a(th2);
                                }
                                b();
                            } finally {
                            }
                        }
                    }
                } finally {
                }
            }
        }

        void d() {
            synchronized (this.f3765d) {
                try {
                    if (this.f3770i == null) {
                        return;
                    }
                    if (this.f3767f == null) {
                        ThreadPoolExecutor b2 = ConcurrencyHelpers.b("emojiCompat");
                        this.f3768g = b2;
                        this.f3767f = b2;
                    }
                    this.f3767f.execute(new Runnable() { // from class: androidx.emoji2.text.c
                        @Override // java.lang.Runnable
                        public final void run() {
                            FontRequestEmojiCompatConfig.FontRequestMetadataLoader.this.c();
                        }
                    });
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void g(Executor executor) {
            synchronized (this.f3765d) {
                this.f3767f = executor;
            }
        }
    }

    public static abstract class RetryPolicy {
        public abstract long a();
    }

    public FontRequestEmojiCompatConfig(Context context, FontRequest fontRequest) {
        super(new FontRequestMetadataLoader(context, fontRequest, f3759k));
    }

    public FontRequestEmojiCompatConfig c(Executor executor) {
        ((FontRequestMetadataLoader) a()).g(executor);
        return this;
    }
}
