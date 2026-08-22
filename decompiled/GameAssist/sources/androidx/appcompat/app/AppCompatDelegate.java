package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.LocaleManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.window.OnBackInvokedDispatcher;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.collection.ArraySet;
import androidx.core.app.AppLocalesStorageHelper;
import androidx.core.os.LocaleListCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public abstract class AppCompatDelegate {

    /* renamed from: c, reason: collision with root package name */
    static SerialExecutor f223c = new SerialExecutor(new ThreadPerTaskExecutor());

    /* renamed from: h, reason: collision with root package name */
    private static int f224h = -100;

    /* renamed from: i, reason: collision with root package name */
    private static LocaleListCompat f225i = null;

    /* renamed from: j, reason: collision with root package name */
    private static LocaleListCompat f226j = null;

    /* renamed from: k, reason: collision with root package name */
    private static Boolean f227k = null;

    /* renamed from: l, reason: collision with root package name */
    private static boolean f228l = false;

    /* renamed from: m, reason: collision with root package name */
    private static final ArraySet f229m = new ArraySet();

    /* renamed from: n, reason: collision with root package name */
    private static final Object f230n = new Object();

    /* renamed from: o, reason: collision with root package name */
    private static final Object f231o = new Object();

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static LocaleList a(String str) {
            return LocaleList.forLanguageTags(str);
        }
    }

    @RequiresApi
    static class Api33Impl {
        @DoNotInline
        static LocaleList a(Object obj) {
            return ((LocaleManager) obj).getApplicationLocales();
        }

        @DoNotInline
        static void b(Object obj, LocaleList localeList) {
            ((LocaleManager) obj).setApplicationLocales(localeList);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface NightMode {
    }

    static class SerialExecutor implements Executor {

        /* renamed from: c, reason: collision with root package name */
        private final Object f232c = new Object();

        /* renamed from: h, reason: collision with root package name */
        final Queue f233h = new ArrayDeque();

        /* renamed from: i, reason: collision with root package name */
        final Executor f234i;

        /* renamed from: j, reason: collision with root package name */
        Runnable f235j;

        SerialExecutor(Executor executor) {
            this.f234i = executor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(Runnable runnable) {
            try {
                runnable.run();
            } finally {
                c();
            }
        }

        protected void c() {
            synchronized (this.f232c) {
                try {
                    Runnable runnable = (Runnable) this.f233h.poll();
                    this.f235j = runnable;
                    if (runnable != null) {
                        this.f234i.execute(runnable);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // java.util.concurrent.Executor
        public void execute(final Runnable runnable) {
            synchronized (this.f232c) {
                try {
                    this.f233h.add(new Runnable() { // from class: androidx.appcompat.app.b
                        @Override // java.lang.Runnable
                        public final void run() {
                            AppCompatDelegate.SerialExecutor.this.b(runnable);
                        }
                    });
                    if (this.f235j == null) {
                        c();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    static class ThreadPerTaskExecutor implements Executor {
        ThreadPerTaskExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            new Thread(runnable).start();
        }
    }

    AppCompatDelegate() {
    }

    static void D(AppCompatDelegate appCompatDelegate) {
        synchronized (f230n) {
            E(appCompatDelegate);
        }
    }

    private static void E(AppCompatDelegate appCompatDelegate) {
        synchronized (f230n) {
            try {
                Iterator it = f229m.iterator();
                while (it.hasNext()) {
                    AppCompatDelegate appCompatDelegate2 = (AppCompatDelegate) ((WeakReference) it.next()).get();
                    if (appCompatDelegate2 == appCompatDelegate || appCompatDelegate2 == null) {
                        it.remove();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static void N(Context context) {
        ComponentName componentName = new ComponentName(context, "androidx.appcompat.app.AppLocalesMetadataHolderService");
        if (context.getPackageManager().getComponentEnabledSetting(componentName) != 1) {
            if (k().f()) {
                String a2 = AppLocalesStorageHelper.a(context);
                Object systemService = context.getSystemService("locale");
                if (systemService != null) {
                    Api33Impl.b(systemService, Api24Impl.a(a2));
                }
            }
            context.getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
        }
    }

    static void O(final Context context) {
        if (t(context) && !f228l) {
            f223c.execute(new Runnable() { // from class: androidx.appcompat.app.a
                @Override // java.lang.Runnable
                public final void run() {
                    AppCompatDelegate.u(context);
                }
            });
        }
    }

    static void d(AppCompatDelegate appCompatDelegate) {
        synchronized (f230n) {
            E(appCompatDelegate);
            f229m.add(new WeakReference(appCompatDelegate));
        }
    }

    public static AppCompatDelegate h(Activity activity, AppCompatCallback appCompatCallback) {
        return new AppCompatDelegateImpl(activity, appCompatCallback);
    }

    public static AppCompatDelegate i(Dialog dialog, AppCompatCallback appCompatCallback) {
        return new AppCompatDelegateImpl(dialog, appCompatCallback);
    }

    public static LocaleListCompat k() {
        Object o2 = o();
        return o2 != null ? LocaleListCompat.j(Api33Impl.a(o2)) : LocaleListCompat.e();
    }

    public static int m() {
        return f224h;
    }

    static Object o() {
        Context l2;
        Iterator it = f229m.iterator();
        while (it.hasNext()) {
            AppCompatDelegate appCompatDelegate = (AppCompatDelegate) ((WeakReference) it.next()).get();
            if (appCompatDelegate != null && (l2 = appCompatDelegate.l()) != null) {
                return l2.getSystemService("locale");
            }
        }
        return null;
    }

    @VisibleForTesting
    static void resetStaticRequestedAndStoredLocales() {
        f225i = null;
        f226j = null;
    }

    @VisibleForTesting
    static void setIsAutoStoreLocalesOptedIn(boolean z) {
        f227k = Boolean.valueOf(z);
    }

    static boolean t(Context context) {
        if (f227k == null) {
            try {
                Bundle bundle = AppLocalesMetadataHolderService.a(context).metaData;
                if (bundle != null) {
                    f227k = Boolean.valueOf(bundle.getBoolean("autoStoreLocales"));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.d("AppCompatDelegate", "Checking for metadata for AppLocalesMetadataHolderService : Service not found");
                f227k = Boolean.FALSE;
            }
        }
        return f227k.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void u(Context context) {
        N(context);
        f228l = true;
    }

    public abstract void A(Bundle bundle);

    public abstract void B();

    public abstract void C();

    public abstract boolean F(int i2);

    public abstract void G(int i2);

    public abstract void H(View view);

    public abstract void I(View view, ViewGroup.LayoutParams layoutParams);

    public void J(OnBackInvokedDispatcher onBackInvokedDispatcher) {
    }

    public abstract void K(Toolbar toolbar);

    public void L(int i2) {
    }

    public abstract void M(CharSequence charSequence);

    public abstract void e(View view, ViewGroup.LayoutParams layoutParams);

    public void f(Context context) {
    }

    public Context g(Context context) {
        f(context);
        return context;
    }

    public abstract View j(int i2);

    public Context l() {
        return null;
    }

    public int n() {
        return -100;
    }

    public abstract MenuInflater p();

    public abstract ActionBar q();

    public abstract void r();

    public abstract void s();

    public abstract void v(Configuration configuration);

    public abstract void w(Bundle bundle);

    public abstract void x();

    public abstract void y(Bundle bundle);

    public abstract void z();
}
