package androidx.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.ComponentActivity;
import androidx.activity.contextaware.ContextAware;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnNewIntentProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuHostHelper;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import androidx.tracing.Trace;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements ContextAware, LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, ActivityResultCaller, OnConfigurationChangedProvider, OnTrimMemoryProvider, OnNewIntentProvider, OnMultiWindowModeChangedProvider, OnPictureInPictureModeChangedProvider, MenuHost, FullyDrawnReporterOwner {

    /* renamed from: i, reason: collision with root package name */
    final ContextAwareHelper f7i = new ContextAwareHelper();

    /* renamed from: j, reason: collision with root package name */
    private final MenuHostHelper f8j = new MenuHostHelper(new Runnable() { // from class: androidx.activity.a
        @Override // java.lang.Runnable
        public final void run() {
            ComponentActivity.this.L();
        }
    });

    /* renamed from: k, reason: collision with root package name */
    private final LifecycleRegistry f9k = new LifecycleRegistry(this);

    /* renamed from: l, reason: collision with root package name */
    final SavedStateRegistryController f10l;

    /* renamed from: m, reason: collision with root package name */
    private ViewModelStore f11m;

    /* renamed from: n, reason: collision with root package name */
    private OnBackPressedDispatcher f12n;

    /* renamed from: o, reason: collision with root package name */
    final ReportFullyDrawnExecutor f13o;

    /* renamed from: p, reason: collision with root package name */
    final FullyDrawnReporter f14p;

    /* renamed from: q, reason: collision with root package name */
    private int f15q;

    /* renamed from: r, reason: collision with root package name */
    private final AtomicInteger f16r;

    /* renamed from: s, reason: collision with root package name */
    private final ActivityResultRegistry f17s;
    private final CopyOnWriteArrayList t;
    private final CopyOnWriteArrayList u;
    private final CopyOnWriteArrayList v;
    private final CopyOnWriteArrayList w;
    private final CopyOnWriteArrayList x;
    private boolean y;
    private boolean z;

    @RequiresApi
    static class Api19Impl {
        static void a(View view) {
            view.cancelPendingInputEvents();
        }
    }

    @RequiresApi
    static class Api33Impl {
        @DoNotInline
        static OnBackInvokedDispatcher a(Activity activity) {
            return activity.getOnBackInvokedDispatcher();
        }
    }

    static final class NonConfigurationInstances {

        /* renamed from: a, reason: collision with root package name */
        Object f30a;

        /* renamed from: b, reason: collision with root package name */
        ViewModelStore f31b;

        NonConfigurationInstances() {
        }
    }

    private interface ReportFullyDrawnExecutor extends Executor {
        void D(View view);

        void d();
    }

    static class ReportFullyDrawnExecutorApi1 implements ReportFullyDrawnExecutor {

        /* renamed from: c, reason: collision with root package name */
        final Handler f32c;

        @Override // androidx.activity.ComponentActivity.ReportFullyDrawnExecutor
        public void D(View view) {
        }

        @Override // androidx.activity.ComponentActivity.ReportFullyDrawnExecutor
        public void d() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f32c.postAtFrontOfQueue(runnable);
        }
    }

    @RequiresApi
    class ReportFullyDrawnExecutorApi16Impl implements ReportFullyDrawnExecutor, ViewTreeObserver.OnDrawListener, Runnable {

        /* renamed from: h, reason: collision with root package name */
        Runnable f34h;

        /* renamed from: c, reason: collision with root package name */
        final long f33c = SystemClock.uptimeMillis() + 10000;

        /* renamed from: i, reason: collision with root package name */
        boolean f35i = false;

        ReportFullyDrawnExecutorApi16Impl() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            Runnable runnable = this.f34h;
            if (runnable != null) {
                runnable.run();
                this.f34h = null;
            }
        }

        @Override // androidx.activity.ComponentActivity.ReportFullyDrawnExecutor
        public void D(View view) {
            if (this.f35i) {
                return;
            }
            this.f35i = true;
            view.getViewTreeObserver().addOnDrawListener(this);
        }

        @Override // androidx.activity.ComponentActivity.ReportFullyDrawnExecutor
        public void d() {
            ComponentActivity.this.getWindow().getDecorView().removeCallbacks(this);
            ComponentActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f34h = runnable;
            View decorView = ComponentActivity.this.getWindow().getDecorView();
            if (!this.f35i) {
                decorView.postOnAnimation(new Runnable() { // from class: androidx.activity.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        ComponentActivity.ReportFullyDrawnExecutorApi16Impl.this.b();
                    }
                });
            } else if (Looper.myLooper() == Looper.getMainLooper()) {
                decorView.invalidate();
            } else {
                decorView.postInvalidate();
            }
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public void onDraw() {
            Runnable runnable = this.f34h;
            if (runnable == null) {
                if (SystemClock.uptimeMillis() > this.f33c) {
                    this.f35i = false;
                    ComponentActivity.this.getWindow().getDecorView().post(this);
                    return;
                }
                return;
            }
            runnable.run();
            this.f34h = null;
            if (ComponentActivity.this.f14p.d()) {
                this.f35i = false;
                ComponentActivity.this.getWindow().getDecorView().post(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            ComponentActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }
    }

    public ComponentActivity() {
        SavedStateRegistryController a2 = SavedStateRegistryController.a(this);
        this.f10l = a2;
        this.f12n = null;
        ReportFullyDrawnExecutor I = I();
        this.f13o = I;
        this.f14p = new FullyDrawnReporter(I, new Function0() { // from class: androidx.activity.b
            @Override // kotlin.jvm.functions.Function0
            public final Object a() {
                Unit M;
                M = ComponentActivity.this.M();
                return M;
            }
        });
        this.f16r = new AtomicInteger();
        this.f17s = new ActivityResultRegistry() { // from class: androidx.activity.ComponentActivity.1
            @Override // androidx.activity.result.ActivityResultRegistry
            public void f(final int i2, ActivityResultContract activityResultContract, Object obj, ActivityOptionsCompat activityOptionsCompat) {
                Bundle a3;
                ComponentActivity componentActivity = ComponentActivity.this;
                final ActivityResultContract.SynchronousResult b2 = activityResultContract.b(componentActivity, obj);
                if (b2 != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: androidx.activity.ComponentActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            c(i2, b2.a());
                        }
                    });
                    return;
                }
                Intent a4 = activityResultContract.a(componentActivity, obj);
                if (a4.getExtras() != null && a4.getExtras().getClassLoader() == null) {
                    a4.setExtrasClassLoader(componentActivity.getClassLoader());
                }
                if (a4.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
                    Bundle bundleExtra = a4.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                    a4.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                    a3 = bundleExtra;
                } else {
                    a3 = activityOptionsCompat != null ? activityOptionsCompat.a() : null;
                }
                if ("androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(a4.getAction())) {
                    String[] stringArrayExtra = a4.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                    if (stringArrayExtra == null) {
                        stringArrayExtra = new String[0];
                    }
                    ActivityCompat.p(componentActivity, stringArrayExtra, i2);
                    return;
                }
                if (!"androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(a4.getAction())) {
                    ActivityCompat.q(componentActivity, a4, i2, a3);
                    return;
                }
                IntentSenderRequest intentSenderRequest = (IntentSenderRequest) a4.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
                try {
                    ActivityCompat.r(componentActivity, intentSenderRequest.f(), i2, intentSenderRequest.a(), intentSenderRequest.b(), intentSenderRequest.d(), 0, a3);
                } catch (IntentSender.SendIntentException e2) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: androidx.activity.ComponentActivity.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            b(i2, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", e2));
                        }
                    });
                }
            }
        };
        this.t = new CopyOnWriteArrayList();
        this.u = new CopyOnWriteArrayList();
        this.v = new CopyOnWriteArrayList();
        this.w = new CopyOnWriteArrayList();
        this.x = new CopyOnWriteArrayList();
        this.y = false;
        this.z = false;
        if (a() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        a().a(new LifecycleEventObserver() { // from class: androidx.activity.ComponentActivity.2
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_STOP) {
                    Window window = ComponentActivity.this.getWindow();
                    View peekDecorView = window != null ? window.peekDecorView() : null;
                    if (peekDecorView != null) {
                        Api19Impl.a(peekDecorView);
                    }
                }
            }
        });
        a().a(new LifecycleEventObserver() { // from class: androidx.activity.ComponentActivity.3
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    ComponentActivity.this.f7i.b();
                    if (!ComponentActivity.this.isChangingConfigurations()) {
                        ComponentActivity.this.h().a();
                    }
                    ComponentActivity.this.f13o.d();
                }
            }
        });
        a().a(new LifecycleEventObserver() { // from class: androidx.activity.ComponentActivity.4
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                ComponentActivity.this.J();
                ComponentActivity.this.a().c(this);
            }
        });
        a2.c();
        SavedStateHandleSupport.c(this);
        i().h("android:support:activity-result", new SavedStateRegistry.SavedStateProvider() { // from class: androidx.activity.c
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle a() {
                Bundle N;
                N = ComponentActivity.this.N();
                return N;
            }
        });
        G(new OnContextAvailableListener() { // from class: androidx.activity.d
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public final void a(Context context) {
                ComponentActivity.this.O(context);
            }
        });
    }

    private ReportFullyDrawnExecutor I() {
        return new ReportFullyDrawnExecutorApi16Impl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit M() {
        reportFullyDrawn();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Bundle N() {
        Bundle bundle = new Bundle();
        this.f17s.h(bundle);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O(Context context) {
        Bundle b2 = i().b("android:support:activity-result");
        if (b2 != null) {
            this.f17s.g(b2);
        }
    }

    public final void G(OnContextAvailableListener onContextAvailableListener) {
        this.f7i.a(onContextAvailableListener);
    }

    public final void H(Consumer consumer) {
        this.v.add(consumer);
    }

    void J() {
        if (this.f11m == null) {
            NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance();
            if (nonConfigurationInstances != null) {
                this.f11m = nonConfigurationInstances.f31b;
            }
            if (this.f11m == null) {
                this.f11m = new ViewModelStore();
            }
        }
    }

    public void K() {
        ViewTreeLifecycleOwner.a(getWindow().getDecorView(), this);
        ViewTreeViewModelStoreOwner.a(getWindow().getDecorView(), this);
        ViewTreeSavedStateRegistryOwner.a(getWindow().getDecorView(), this);
        ViewTreeOnBackPressedDispatcherOwner.a(getWindow().getDecorView(), this);
        ViewTreeFullyDrawnReporterOwner.a(getWindow().getDecorView(), this);
    }

    public void L() {
        invalidateOptionsMenu();
    }

    public Object P() {
        return null;
    }

    @Override // androidx.core.app.ComponentActivity, androidx.lifecycle.LifecycleOwner
    public Lifecycle a() {
        return this.f9k;
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        K();
        this.f13o.D(getWindow().getDecorView());
        super.addContentView(view, layoutParams);
    }

    @Override // androidx.core.content.OnConfigurationChangedProvider
    public final void b(Consumer consumer) {
        this.t.add(consumer);
    }

    @Override // androidx.core.app.OnPictureInPictureModeChangedProvider
    public final void c(Consumer consumer) {
        this.x.remove(consumer);
    }

    @Override // androidx.core.content.OnTrimMemoryProvider
    public final void d(Consumer consumer) {
        this.u.remove(consumer);
    }

    @Override // androidx.core.app.OnPictureInPictureModeChangedProvider
    public final void e(Consumer consumer) {
        this.x.add(consumer);
    }

    @Override // androidx.activity.result.ActivityResultRegistryOwner
    public final ActivityResultRegistry f() {
        return this.f17s;
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore h() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        J();
        return this.f11m;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public final SavedStateRegistry i() {
        return this.f10l.b();
    }

    @Override // androidx.core.content.OnTrimMemoryProvider
    public final void k(Consumer consumer) {
        this.u.add(consumer);
    }

    @Override // androidx.core.app.OnMultiWindowModeChangedProvider
    public final void l(Consumer consumer) {
        this.w.remove(consumer);
    }

    @Override // androidx.activity.OnBackPressedDispatcherOwner
    public final OnBackPressedDispatcher n() {
        if (this.f12n == null) {
            this.f12n = new OnBackPressedDispatcher(new Runnable() { // from class: androidx.activity.ComponentActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ComponentActivity.super.onBackPressed();
                    } catch (IllegalStateException e2) {
                        if (!TextUtils.equals(e2.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                            throw e2;
                        }
                    } catch (NullPointerException e3) {
                        if (!TextUtils.equals(e3.getMessage(), "Attempt to invoke virtual method 'android.os.Handler android.app.FragmentHostCallback.getHandler()' on a null object reference")) {
                            throw e3;
                        }
                    }
                }
            });
            a().a(new LifecycleEventObserver() { // from class: androidx.activity.ComponentActivity.6
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_CREATE) {
                        ComponentActivity.this.f12n.n(Api33Impl.a((ComponentActivity) lifecycleOwner));
                    }
                }
            });
        }
        return this.f12n;
    }

    @Override // androidx.core.view.MenuHost
    public void o(MenuProvider menuProvider) {
        this.f8j.f(menuProvider);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        if (this.f17s.b(i2, i3, intent)) {
            return;
        }
        super.onActivityResult(i2, i3, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        n().k();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Iterator it = this.t.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(configuration);
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        this.f10l.d(bundle);
        this.f7i.c(this);
        super.onCreate(bundle);
        ReportFragment.e(this);
        int i2 = this.f15q;
        if (i2 != 0) {
            setContentView(i2);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i2, Menu menu) {
        if (i2 != 0) {
            return true;
        }
        super.onCreatePanelMenu(i2, menu);
        this.f8j.b(menu, getMenuInflater());
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        if (i2 == 0) {
            return this.f8j.d(menuItem);
        }
        return false;
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        if (this.y) {
            return;
        }
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(new MultiWindowModeChangedInfo(z));
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Iterator it = this.v.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i2, Menu menu) {
        this.f8j.c(menu);
        super.onPanelClosed(i2, menu);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        if (this.z) {
            return;
        }
        Iterator it = this.x.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(new PictureInPictureModeChangedInfo(z));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i2, View view, Menu menu) {
        if (i2 != 0) {
            return true;
        }
        super.onPreparePanel(i2, view, menu);
        this.f8j.e(menu);
        return true;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (this.f17s.b(i2, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        NonConfigurationInstances nonConfigurationInstances;
        Object P = P();
        ViewModelStore viewModelStore = this.f11m;
        if (viewModelStore == null && (nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance()) != null) {
            viewModelStore = nonConfigurationInstances.f31b;
        }
        if (viewModelStore == null && P == null) {
            return null;
        }
        NonConfigurationInstances nonConfigurationInstances2 = new NonConfigurationInstances();
        nonConfigurationInstances2.f30a = P;
        nonConfigurationInstances2.f31b = viewModelStore;
        return nonConfigurationInstances2;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        Lifecycle a2 = a();
        if (a2 instanceof LifecycleRegistry) {
            ((LifecycleRegistry) a2).m(Lifecycle.State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.f10l.e(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i2) {
        super.onTrimMemory(i2);
        Iterator it = this.u.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(Integer.valueOf(i2));
        }
    }

    @Override // androidx.activity.contextaware.ContextAware
    public final void r(OnContextAvailableListener onContextAvailableListener) {
        this.f7i.d(onContextAvailableListener);
    }

    @Override // android.app.Activity
    public void reportFullyDrawn() {
        try {
            if (Trace.isEnabled()) {
                Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            this.f14p.c();
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public CreationExtras s() {
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras();
        if (getApplication() != null) {
            mutableCreationExtras.c(ViewModelProvider.AndroidViewModelFactory.f4404g, getApplication());
        }
        mutableCreationExtras.c(SavedStateHandleSupport.f4369a, this);
        mutableCreationExtras.c(SavedStateHandleSupport.f4370b, this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            mutableCreationExtras.c(SavedStateHandleSupport.f4371c, getIntent().getExtras());
        }
        return mutableCreationExtras;
    }

    @Override // android.app.Activity
    public void setContentView(int i2) {
        K();
        this.f13o.D(getWindow().getDecorView());
        super.setContentView(i2);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i2) {
        super.startActivityForResult(intent, i2);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5) {
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5);
    }

    @Override // androidx.core.app.OnMultiWindowModeChangedProvider
    public final void t(Consumer consumer) {
        this.w.add(consumer);
    }

    @Override // androidx.core.content.OnConfigurationChangedProvider
    public final void v(Consumer consumer) {
        this.t.remove(consumer);
    }

    @Override // androidx.core.view.MenuHost
    public void w(MenuProvider menuProvider) {
        this.f8j.a(menuProvider);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        super.startActivityForResult(intent, i2, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) {
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        this.y = true;
        try {
            super.onMultiWindowModeChanged(z, configuration);
            this.y = false;
            Iterator it = this.w.iterator();
            while (it.hasNext()) {
                ((Consumer) it.next()).accept(new MultiWindowModeChangedInfo(z, configuration));
            }
        } catch (Throwable th) {
            this.y = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        this.z = true;
        try {
            super.onPictureInPictureModeChanged(z, configuration);
            this.z = false;
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                ((Consumer) it.next()).accept(new PictureInPictureModeChangedInfo(z, configuration));
            }
        } catch (Throwable th) {
            this.z = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view) {
        K();
        this.f13o.D(getWindow().getDecorView());
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        K();
        this.f13o.D(getWindow().getDecorView());
        super.setContentView(view, layoutParams);
    }
}
