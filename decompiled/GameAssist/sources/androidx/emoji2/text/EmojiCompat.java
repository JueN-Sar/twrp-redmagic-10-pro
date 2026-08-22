package androidx.emoji2.text;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.annotation.AnyThread;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import com.google.android.gms.common.api.Api;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@AnyThread
/* loaded from: classes.dex */
public class EmojiCompat {

    /* renamed from: o, reason: collision with root package name */
    private static final Object f3695o = new Object();

    /* renamed from: p, reason: collision with root package name */
    private static final Object f3696p = new Object();

    /* renamed from: q, reason: collision with root package name */
    private static volatile EmojiCompat f3697q;

    /* renamed from: b, reason: collision with root package name */
    private final Set f3699b;

    /* renamed from: e, reason: collision with root package name */
    private final CompatInternal f3702e;

    /* renamed from: f, reason: collision with root package name */
    final MetadataRepoLoader f3703f;

    /* renamed from: g, reason: collision with root package name */
    private final SpanFactory f3704g;

    /* renamed from: h, reason: collision with root package name */
    final boolean f3705h;

    /* renamed from: i, reason: collision with root package name */
    final boolean f3706i;

    /* renamed from: j, reason: collision with root package name */
    final int[] f3707j;

    /* renamed from: k, reason: collision with root package name */
    private final boolean f3708k;

    /* renamed from: l, reason: collision with root package name */
    private final int f3709l;

    /* renamed from: m, reason: collision with root package name */
    private final int f3710m;

    /* renamed from: n, reason: collision with root package name */
    private final GlyphChecker f3711n;

    /* renamed from: a, reason: collision with root package name */
    private final ReadWriteLock f3698a = new ReentrantReadWriteLock();

    /* renamed from: c, reason: collision with root package name */
    private volatile int f3700c = 3;

    /* renamed from: d, reason: collision with root package name */
    private final Handler f3701d = new Handler(Looper.getMainLooper());

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface CodepointSequenceMatchResult {
    }

    private static class CompatInternal {

        /* renamed from: a, reason: collision with root package name */
        final EmojiCompat f3712a;

        CompatInternal(EmojiCompat emojiCompat) {
            this.f3712a = emojiCompat;
        }

        void a() {
            this.f3712a.o();
        }

        CharSequence b(CharSequence charSequence, int i2, int i3, int i4, boolean z) {
            return charSequence;
        }

        void c(EditorInfo editorInfo) {
        }
    }

    @RequiresApi
    private static final class CompatInternal19 extends CompatInternal {

        /* renamed from: b, reason: collision with root package name */
        private volatile EmojiProcessor f3713b;

        /* renamed from: c, reason: collision with root package name */
        private volatile MetadataRepo f3714c;

        CompatInternal19(EmojiCompat emojiCompat) {
            super(emojiCompat);
        }

        @Override // androidx.emoji2.text.EmojiCompat.CompatInternal
        void a() {
            try {
                this.f3712a.f3703f.a(new MetadataRepoLoaderCallback() { // from class: androidx.emoji2.text.EmojiCompat.CompatInternal19.1
                    @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
                    public void a(Throwable th) {
                        CompatInternal19.this.f3712a.n(th);
                    }

                    @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
                    public void b(MetadataRepo metadataRepo) {
                        CompatInternal19.this.d(metadataRepo);
                    }
                });
            } catch (Throwable th) {
                this.f3712a.n(th);
            }
        }

        @Override // androidx.emoji2.text.EmojiCompat.CompatInternal
        CharSequence b(CharSequence charSequence, int i2, int i3, int i4, boolean z) {
            return this.f3713b.h(charSequence, i2, i3, i4, z);
        }

        @Override // androidx.emoji2.text.EmojiCompat.CompatInternal
        void c(EditorInfo editorInfo) {
            editorInfo.extras.putInt("android.support.text.emoji.emojiCompat_metadataVersion", this.f3714c.e());
            editorInfo.extras.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", this.f3712a.f3705h);
        }

        void d(MetadataRepo metadataRepo) {
            if (metadataRepo == null) {
                this.f3712a.n(new IllegalArgumentException("metadataRepo cannot be null"));
                return;
            }
            this.f3714c = metadataRepo;
            MetadataRepo metadataRepo2 = this.f3714c;
            SpanFactory spanFactory = this.f3712a.f3704g;
            GlyphChecker glyphChecker = this.f3712a.f3711n;
            EmojiCompat emojiCompat = this.f3712a;
            this.f3713b = new EmojiProcessor(metadataRepo2, spanFactory, glyphChecker, emojiCompat.f3706i, emojiCompat.f3707j, EmojiExclusions.a());
            this.f3712a.o();
        }
    }

    public static abstract class Config {

        /* renamed from: a, reason: collision with root package name */
        final MetadataRepoLoader f3716a;

        /* renamed from: b, reason: collision with root package name */
        SpanFactory f3717b;

        /* renamed from: c, reason: collision with root package name */
        boolean f3718c;

        /* renamed from: d, reason: collision with root package name */
        boolean f3719d;

        /* renamed from: e, reason: collision with root package name */
        int[] f3720e;

        /* renamed from: f, reason: collision with root package name */
        Set f3721f;

        /* renamed from: g, reason: collision with root package name */
        boolean f3722g;

        /* renamed from: h, reason: collision with root package name */
        int f3723h = -16711936;

        /* renamed from: i, reason: collision with root package name */
        int f3724i = 0;

        /* renamed from: j, reason: collision with root package name */
        GlyphChecker f3725j = new DefaultGlyphChecker();

        protected Config(MetadataRepoLoader metadataRepoLoader) {
            Preconditions.i(metadataRepoLoader, "metadataLoader cannot be null.");
            this.f3716a = metadataRepoLoader;
        }

        protected final MetadataRepoLoader a() {
            return this.f3716a;
        }

        public Config b(int i2) {
            this.f3724i = i2;
            return this;
        }
    }

    @RestrictTo
    public static class DefaultSpanFactory implements SpanFactory {
        @Override // androidx.emoji2.text.EmojiCompat.SpanFactory
        public EmojiSpan a(TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            return new TypefaceEmojiSpan(typefaceEmojiRasterizer);
        }
    }

    public interface GlyphChecker {
        boolean a(CharSequence charSequence, int i2, int i3, int i4);
    }

    public static abstract class InitCallback {
        public void a(Throwable th) {
        }

        public void b() {
        }
    }

    private static class ListenerDispatcher implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private final List f3726c;

        /* renamed from: h, reason: collision with root package name */
        private final Throwable f3727h;

        /* renamed from: i, reason: collision with root package name */
        private final int f3728i;

        ListenerDispatcher(InitCallback initCallback, int i2) {
            this(Arrays.asList((InitCallback) Preconditions.i(initCallback, "initCallback cannot be null")), i2, null);
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.f3726c.size();
            int i2 = 0;
            if (this.f3728i != 1) {
                while (i2 < size) {
                    ((InitCallback) this.f3726c.get(i2)).a(this.f3727h);
                    i2++;
                }
            } else {
                while (i2 < size) {
                    ((InitCallback) this.f3726c.get(i2)).b();
                    i2++;
                }
            }
        }

        ListenerDispatcher(Collection collection, int i2) {
            this(collection, i2, null);
        }

        ListenerDispatcher(Collection collection, int i2, Throwable th) {
            Preconditions.i(collection, "initCallbacks cannot be null");
            this.f3726c = new ArrayList(collection);
            this.f3728i = i2;
            this.f3727h = th;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface LoadStrategy {
    }

    public interface MetadataRepoLoader {
        void a(MetadataRepoLoaderCallback metadataRepoLoaderCallback);
    }

    public static abstract class MetadataRepoLoaderCallback {
        public abstract void a(Throwable th);

        public abstract void b(MetadataRepo metadataRepo);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ReplaceStrategy {
    }

    public interface SpanFactory {
        EmojiSpan a(TypefaceEmojiRasterizer typefaceEmojiRasterizer);
    }

    private EmojiCompat(Config config) {
        this.f3705h = config.f3718c;
        this.f3706i = config.f3719d;
        this.f3707j = config.f3720e;
        this.f3708k = config.f3722g;
        this.f3709l = config.f3723h;
        this.f3703f = config.f3716a;
        this.f3710m = config.f3724i;
        this.f3711n = config.f3725j;
        ArraySet arraySet = new ArraySet();
        this.f3699b = arraySet;
        SpanFactory spanFactory = config.f3717b;
        this.f3704g = spanFactory == null ? new DefaultSpanFactory() : spanFactory;
        Set set = config.f3721f;
        if (set != null && !set.isEmpty()) {
            arraySet.addAll(config.f3721f);
        }
        this.f3702e = new CompatInternal19(this);
        m();
    }

    public static EmojiCompat c() {
        EmojiCompat emojiCompat;
        synchronized (f3695o) {
            emojiCompat = f3697q;
            Preconditions.k(emojiCompat != null, "EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
        }
        return emojiCompat;
    }

    public static boolean f(InputConnection inputConnection, Editable editable, int i2, int i3, boolean z) {
        return EmojiProcessor.b(inputConnection, editable, i2, i3, z);
    }

    public static boolean g(Editable editable, int i2, KeyEvent keyEvent) {
        return EmojiProcessor.c(editable, i2, keyEvent);
    }

    public static EmojiCompat h(Config config) {
        EmojiCompat emojiCompat = f3697q;
        if (emojiCompat == null) {
            synchronized (f3695o) {
                try {
                    emojiCompat = f3697q;
                    if (emojiCompat == null) {
                        emojiCompat = new EmojiCompat(config);
                        f3697q = emojiCompat;
                    }
                } finally {
                }
            }
        }
        return emojiCompat;
    }

    public static boolean i() {
        return f3697q != null;
    }

    private boolean k() {
        return e() == 1;
    }

    private void m() {
        this.f3698a.writeLock().lock();
        try {
            if (this.f3710m == 0) {
                this.f3700c = 0;
            }
            this.f3698a.writeLock().unlock();
            if (e() == 0) {
                this.f3702e.a();
            }
        } catch (Throwable th) {
            this.f3698a.writeLock().unlock();
            throw th;
        }
    }

    public int d() {
        return this.f3709l;
    }

    public int e() {
        this.f3698a.readLock().lock();
        try {
            return this.f3700c;
        } finally {
            this.f3698a.readLock().unlock();
        }
    }

    public boolean j() {
        return this.f3708k;
    }

    public void l() {
        Preconditions.k(this.f3710m == 1, "Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        if (k()) {
            return;
        }
        this.f3698a.writeLock().lock();
        try {
            if (this.f3700c == 0) {
                return;
            }
            this.f3700c = 0;
            this.f3698a.writeLock().unlock();
            this.f3702e.a();
        } finally {
            this.f3698a.writeLock().unlock();
        }
    }

    void n(Throwable th) {
        ArrayList arrayList = new ArrayList();
        this.f3698a.writeLock().lock();
        try {
            this.f3700c = 2;
            arrayList.addAll(this.f3699b);
            this.f3699b.clear();
            this.f3698a.writeLock().unlock();
            this.f3701d.post(new ListenerDispatcher(arrayList, this.f3700c, th));
        } catch (Throwable th2) {
            this.f3698a.writeLock().unlock();
            throw th2;
        }
    }

    void o() {
        ArrayList arrayList = new ArrayList();
        this.f3698a.writeLock().lock();
        try {
            this.f3700c = 1;
            arrayList.addAll(this.f3699b);
            this.f3699b.clear();
            this.f3698a.writeLock().unlock();
            this.f3701d.post(new ListenerDispatcher(arrayList, this.f3700c));
        } catch (Throwable th) {
            this.f3698a.writeLock().unlock();
            throw th;
        }
    }

    public CharSequence p(CharSequence charSequence) {
        return q(charSequence, 0, charSequence == null ? 0 : charSequence.length());
    }

    public CharSequence q(CharSequence charSequence, int i2, int i3) {
        return r(charSequence, i2, i3, Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    public CharSequence r(CharSequence charSequence, int i2, int i3, int i4) {
        return s(charSequence, i2, i3, i4, 0);
    }

    public CharSequence s(CharSequence charSequence, int i2, int i3, int i4, int i5) {
        boolean z;
        Preconditions.k(k(), "Not initialized yet");
        Preconditions.f(i2, "start cannot be negative");
        Preconditions.f(i3, "end cannot be negative");
        Preconditions.f(i4, "maxEmojiCount cannot be negative");
        Preconditions.b(i2 <= i3, "start should be <= than end");
        if (charSequence == null) {
            return null;
        }
        Preconditions.b(i2 <= charSequence.length(), "start should be < than charSequence length");
        Preconditions.b(i3 <= charSequence.length(), "end should be < than charSequence length");
        if (charSequence.length() == 0 || i2 == i3) {
            return charSequence;
        }
        if (i5 != 1) {
            z = i5 != 2 ? this.f3705h : false;
        } else {
            z = true;
        }
        return this.f3702e.b(charSequence, i2, i3, i4, z);
    }

    public void t(InitCallback initCallback) {
        Preconditions.i(initCallback, "initCallback cannot be null");
        this.f3698a.writeLock().lock();
        try {
            if (this.f3700c != 1 && this.f3700c != 2) {
                this.f3699b.add(initCallback);
                this.f3698a.writeLock().unlock();
            }
            this.f3701d.post(new ListenerDispatcher(initCallback, this.f3700c));
            this.f3698a.writeLock().unlock();
        } catch (Throwable th) {
            this.f3698a.writeLock().unlock();
            throw th;
        }
    }

    public void u(InitCallback initCallback) {
        Preconditions.i(initCallback, "initCallback cannot be null");
        this.f3698a.writeLock().lock();
        try {
            this.f3699b.remove(initCallback);
        } finally {
            this.f3698a.writeLock().unlock();
        }
    }

    public void v(EditorInfo editorInfo) {
        if (!k() || editorInfo == null) {
            return;
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        this.f3702e.c(editorInfo);
    }
}
