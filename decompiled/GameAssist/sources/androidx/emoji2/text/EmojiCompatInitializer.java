package androidx.emoji2.text;

import android.content.Context;
import androidx.annotation.RequiresApi;
import androidx.core.os.TraceCompat;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.EmojiCompatInitializer;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleInitializer;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes.dex */
public class EmojiCompatInitializer implements Initializer<Boolean> {

    @RequiresApi
    static class BackgroundDefaultConfig extends EmojiCompat.Config {
        protected BackgroundDefaultConfig(Context context) {
            super(new BackgroundDefaultLoader(context));
            b(1);
        }
    }

    @RequiresApi
    static class BackgroundDefaultLoader implements EmojiCompat.MetadataRepoLoader {

        /* renamed from: a, reason: collision with root package name */
        private final Context f3731a;

        BackgroundDefaultLoader(Context context) {
            this.f3731a = context.getApplicationContext();
        }

        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoader
        public void a(final EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback) {
            final ThreadPoolExecutor b2 = ConcurrencyHelpers.b("EmojiCompatInitializer");
            b2.execute(new Runnable() { // from class: androidx.emoji2.text.b
                @Override // java.lang.Runnable
                public final void run() {
                    EmojiCompatInitializer.BackgroundDefaultLoader.this.d(metadataRepoLoaderCallback, b2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void d(final EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback, final ThreadPoolExecutor threadPoolExecutor) {
            try {
                FontRequestEmojiCompatConfig a2 = DefaultEmojiCompatConfig.a(this.f3731a);
                if (a2 == null) {
                    throw new RuntimeException("EmojiCompat font provider not available on this device.");
                }
                a2.c(threadPoolExecutor);
                a2.a().a(new EmojiCompat.MetadataRepoLoaderCallback() { // from class: androidx.emoji2.text.EmojiCompatInitializer.BackgroundDefaultLoader.1
                    @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
                    public void a(Throwable th) {
                        try {
                            metadataRepoLoaderCallback.a(th);
                        } finally {
                            threadPoolExecutor.shutdown();
                        }
                    }

                    @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
                    public void b(MetadataRepo metadataRepo) {
                        try {
                            metadataRepoLoaderCallback.b(metadataRepo);
                        } finally {
                            threadPoolExecutor.shutdown();
                        }
                    }
                });
            } catch (Throwable th) {
                metadataRepoLoaderCallback.a(th);
                threadPoolExecutor.shutdown();
            }
        }
    }

    static class LoadEmojiCompatRunnable implements Runnable {
        LoadEmojiCompatRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                TraceCompat.a("EmojiCompat.EmojiCompatInitializer.run");
                if (EmojiCompat.i()) {
                    EmojiCompat.c().l();
                }
            } finally {
                TraceCompat.b();
            }
        }
    }

    @Override // androidx.startup.Initializer
    public List a() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    @Override // androidx.startup.Initializer
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Boolean create(Context context) {
        EmojiCompat.h(new BackgroundDefaultConfig(context));
        c(context);
        return Boolean.TRUE;
    }

    void c(Context context) {
        final Lifecycle a2 = ((LifecycleOwner) AppInitializer.e(context).f(ProcessLifecycleInitializer.class)).a();
        a2.a(new DefaultLifecycleObserver() { // from class: androidx.emoji2.text.EmojiCompatInitializer.1
            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public void d(LifecycleOwner lifecycleOwner) {
                EmojiCompatInitializer.this.d();
                a2.c(this);
            }
        });
    }

    void d() {
        ConcurrencyHelpers.d().postDelayed(new LoadEmojiCompatRunnable(), 500L);
    }
}
