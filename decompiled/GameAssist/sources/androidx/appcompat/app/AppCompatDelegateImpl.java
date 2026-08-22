package androidx.appcompat.app;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.DecorContentParent;
import androidx.appcompat.widget.FitWindowsViewGroup;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.appcompat.widget.ViewUtils;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.lang.Thread;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo
/* loaded from: classes.dex */
class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
    private static final SimpleArrayMap p0 = new SimpleArrayMap();
    private static final boolean q0 = false;
    private static final int[] r0 = {R.attr.windowBackground};
    private static final boolean s0 = !"robolectric".equals(Build.FINGERPRINT);
    ActionMode A;
    ActionBarContextView B;
    PopupWindow C;
    Runnable D;
    ViewPropertyAnimatorCompat E;
    private boolean F;
    private boolean G;
    ViewGroup H;
    private TextView I;
    private View J;
    private boolean K;
    private boolean L;
    boolean M;
    boolean N;
    boolean O;
    boolean P;
    boolean Q;
    private boolean R;
    private PanelFeatureState[] S;
    private PanelFeatureState T;
    private boolean U;
    private boolean V;
    private boolean W;
    boolean X;
    private Configuration Y;
    private int Z;
    private int a0;
    private int b0;
    private boolean c0;
    private AutoNightModeManager d0;
    private AutoNightModeManager e0;
    boolean f0;
    int g0;
    private final Runnable h0;
    private boolean i0;
    private Rect j0;
    private Rect k0;
    private AppCompatViewInflater l0;
    private LayoutIncludeDetector m0;
    private OnBackInvokedDispatcher n0;
    private OnBackInvokedCallback o0;

    /* renamed from: p, reason: collision with root package name */
    final Object f236p;

    /* renamed from: q, reason: collision with root package name */
    final Context f237q;

    /* renamed from: r, reason: collision with root package name */
    Window f238r;

    /* renamed from: s, reason: collision with root package name */
    private AppCompatWindowCallback f239s;
    final AppCompatCallback t;
    ActionBar u;
    MenuInflater v;
    private CharSequence w;
    private DecorContentParent x;
    private ActionMenuPresenterCallback y;
    private PanelMenuPresenterCallback z;

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$1, reason: invalid class name */
    class AnonymousClass1 implements Thread.UncaughtExceptionHandler {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Thread.UncaughtExceptionHandler f240a;

        private boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            return message.contains("drawable") || message.contains("Drawable");
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (!a(th)) {
                this.f240a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f240a.uncaughtException(thread, notFoundException);
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$4, reason: invalid class name */
    class AnonymousClass4 implements FitWindowsViewGroup.OnFitSystemWindowsListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AppCompatDelegateImpl f243a;

        @Override // androidx.appcompat.widget.FitWindowsViewGroup.OnFitSystemWindowsListener
        public void a(Rect rect) {
            rect.top = this.f243a.d1(null, rect);
        }
    }

    private class ActionBarDrawableToggleImpl implements ActionBarDrawerToggle.Delegate {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AppCompatDelegateImpl f248a;

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void a(int i2) {
            ActionBar q2 = this.f248a.q();
            if (q2 != null) {
                q2.n(i2);
            }
        }
    }

    interface ActionBarMenuCallback {
        boolean a(int i2);

        View onCreatePanelView(int i2);
    }

    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        ActionMenuPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void a(MenuBuilder menuBuilder, boolean z) {
            AppCompatDelegateImpl.this.X(menuBuilder);
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean b(MenuBuilder menuBuilder) {
            Window.Callback t0 = AppCompatDelegateImpl.this.t0();
            if (t0 == null) {
                return true;
            }
            t0.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    class ActionModeCallbackWrapperV9 implements ActionMode.Callback {

        /* renamed from: a, reason: collision with root package name */
        private ActionMode.Callback f250a;

        public ActionModeCallbackWrapperV9(ActionMode.Callback callback) {
            this.f250a = callback;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public void a(ActionMode actionMode) {
            this.f250a.a(actionMode);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.C != null) {
                appCompatDelegateImpl.f238r.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.D);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.B != null) {
                appCompatDelegateImpl2.h0();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.E = ViewCompat.d(appCompatDelegateImpl3.B).b(0.0f);
                AppCompatDelegateImpl.this.E.h(new ViewPropertyAnimatorListenerAdapter() { // from class: androidx.appcompat.app.AppCompatDelegateImpl.ActionModeCallbackWrapperV9.1
                    @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
                    public void b(View view) {
                        AppCompatDelegateImpl.this.B.setVisibility(8);
                        AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
                        PopupWindow popupWindow = appCompatDelegateImpl4.C;
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        } else if (appCompatDelegateImpl4.B.getParent() instanceof View) {
                            ViewCompat.f0((View) AppCompatDelegateImpl.this.B.getParent());
                        }
                        AppCompatDelegateImpl.this.B.l();
                        AppCompatDelegateImpl.this.E.h(null);
                        AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
                        appCompatDelegateImpl5.E = null;
                        ViewCompat.f0(appCompatDelegateImpl5.H);
                    }
                });
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            AppCompatCallback appCompatCallback = appCompatDelegateImpl4.t;
            if (appCompatCallback != null) {
                appCompatCallback.q(appCompatDelegateImpl4.A);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.A = null;
            ViewCompat.f0(appCompatDelegateImpl5.H);
            AppCompatDelegateImpl.this.b1();
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean b(ActionMode actionMode, Menu menu) {
            return this.f250a.b(actionMode, menu);
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean c(ActionMode actionMode, MenuItem menuItem) {
            return this.f250a.c(actionMode, menuItem);
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean d(ActionMode actionMode, Menu menu) {
            ViewCompat.f0(AppCompatDelegateImpl.this.H);
            return this.f250a.d(actionMode, menu);
        }
    }

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static boolean a(PowerManager powerManager) {
            return powerManager.isPowerSaveMode();
        }

        @DoNotInline
        static String b(Locale locale) {
            return locale.toLanguageTag();
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static void a(@NonNull Configuration configuration, @NonNull Configuration configuration2, @NonNull Configuration configuration3) {
            LocaleList locales = configuration.getLocales();
            LocaleList locales2 = configuration2.getLocales();
            if (locales.equals(locales2)) {
                return;
            }
            configuration3.setLocales(locales2);
            configuration3.locale = configuration2.locale;
        }

        @DoNotInline
        static LocaleListCompat b(Configuration configuration) {
            return LocaleListCompat.c(configuration.getLocales().toLanguageTags());
        }

        @DoNotInline
        public static void c(LocaleListCompat localeListCompat) {
            LocaleList.setDefault(LocaleList.forLanguageTags(localeListCompat.h()));
        }

        @DoNotInline
        static void d(Configuration configuration, LocaleListCompat localeListCompat) {
            configuration.setLocales(LocaleList.forLanguageTags(localeListCompat.h()));
        }
    }

    @RequiresApi
    static class Api26Impl {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i2 = configuration.colorMode & 3;
            int i3 = configuration2.colorMode;
            if (i2 != (i3 & 3)) {
                configuration3.colorMode |= i3 & 3;
            }
            int i4 = configuration.colorMode & 12;
            int i5 = configuration2.colorMode;
            if (i4 != (i5 & 12)) {
                configuration3.colorMode |= i5 & 12;
            }
        }
    }

    @RequiresApi
    static class Api33Impl {
        @DoNotInline
        static OnBackInvokedDispatcher a(Activity activity) {
            return activity.getOnBackInvokedDispatcher();
        }

        @DoNotInline
        static OnBackInvokedCallback b(Object obj, final AppCompatDelegateImpl appCompatDelegateImpl) {
            Objects.requireNonNull(appCompatDelegateImpl);
            OnBackInvokedCallback onBackInvokedCallback = new OnBackInvokedCallback() { // from class: androidx.appcompat.app.c
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    AppCompatDelegateImpl.this.B0();
                }
            };
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(1000000, onBackInvokedCallback);
            return onBackInvokedCallback;
        }

        @DoNotInline
        static void c(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    class AppCompatWindowCallback extends WindowCallbackWrapper {

        /* renamed from: h, reason: collision with root package name */
        private ActionBarMenuCallback f253h;

        /* renamed from: i, reason: collision with root package name */
        private boolean f254i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f255j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f256k;

        AppCompatWindowCallback(Window.Callback callback) {
            super(callback);
        }

        public boolean b(Window.Callback callback, KeyEvent keyEvent) {
            try {
                this.f255j = true;
                return callback.dispatchKeyEvent(keyEvent);
            } finally {
                this.f255j = false;
            }
        }

        public void c(Window.Callback callback) {
            try {
                this.f254i = true;
                callback.onContentChanged();
            } finally {
                this.f254i = false;
            }
        }

        public void d(Window.Callback callback, int i2, Menu menu) {
            try {
                this.f256k = true;
                callback.onPanelClosed(i2, menu);
            } finally {
                this.f256k = false;
            }
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return this.f255j ? a().dispatchKeyEvent(keyEvent) : AppCompatDelegateImpl.this.f0(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.E0(keyEvent.getKeyCode(), keyEvent);
        }

        void e(ActionBarMenuCallback actionBarMenuCallback) {
            this.f253h = actionBarMenuCallback;
        }

        final android.view.ActionMode f(ActionMode.Callback callback) {
            SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImpl.this.f237q, callback);
            androidx.appcompat.view.ActionMode V0 = AppCompatDelegateImpl.this.V0(callbackWrapper);
            if (V0 != null) {
                return callbackWrapper.e(V0);
            }
            return null;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public void onContentChanged() {
            if (this.f254i) {
                a().onContentChanged();
            }
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i2, Menu menu) {
            if (i2 != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(i2, menu);
            }
            return false;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public View onCreatePanelView(int i2) {
            View onCreatePanelView;
            ActionBarMenuCallback actionBarMenuCallback = this.f253h;
            return (actionBarMenuCallback == null || (onCreatePanelView = actionBarMenuCallback.onCreatePanelView(i2)) == null) ? super.onCreatePanelView(i2) : onCreatePanelView;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean onMenuOpened(int i2, Menu menu) {
            super.onMenuOpened(i2, menu);
            AppCompatDelegateImpl.this.H0(i2);
            return true;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public void onPanelClosed(int i2, Menu menu) {
            if (this.f256k) {
                a().onPanelClosed(i2, menu);
            } else {
                super.onPanelClosed(i2, menu);
                AppCompatDelegateImpl.this.I0(i2);
            }
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean onPreparePanel(int i2, View view, Menu menu) {
            MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
            if (i2 == 0 && menuBuilder == null) {
                return false;
            }
            if (menuBuilder != null) {
                menuBuilder.f0(true);
            }
            ActionBarMenuCallback actionBarMenuCallback = this.f253h;
            boolean z = actionBarMenuCallback != null && actionBarMenuCallback.a(i2);
            if (!z) {
                z = super.onPreparePanel(i2, view, menu);
            }
            if (menuBuilder != null) {
                menuBuilder.f0(false);
            }
            return z;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List list, Menu menu, int i2) {
            MenuBuilder menuBuilder;
            PanelFeatureState r0 = AppCompatDelegateImpl.this.r0(0, true);
            if (r0 == null || (menuBuilder = r0.f272j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i2);
            } else {
                super.onProvideKeyboardShortcuts(list, menuBuilder, i2);
            }
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
            return (AppCompatDelegateImpl.this.z0() && i2 == 0) ? f(callback) : super.onWindowStartingActionMode(callback, i2);
        }
    }

    private class AutoBatteryNightModeManager extends AutoNightModeManager {

        /* renamed from: a, reason: collision with root package name */
        private final PowerManager f258a;

        AutoBatteryNightModeManager(Context context) {
            super();
            this.f258a = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        IntentFilter createIntentFilterForBroadcastReceiver() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public int getApplyableNightMode() {
            return Api21Impl.a(this.f258a) ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public void onChange() {
            AppCompatDelegateImpl.this.R();
        }
    }

    @RestrictTo
    @VisibleForTesting
    abstract class AutoNightModeManager {
        private BroadcastReceiver mReceiver;

        AutoNightModeManager() {
        }

        void cleanup() {
            BroadcastReceiver broadcastReceiver = this.mReceiver;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.f237q.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.mReceiver = null;
            }
        }

        @Nullable
        abstract IntentFilter createIntentFilterForBroadcastReceiver();

        abstract int getApplyableNightMode();

        boolean isListening() {
            return this.mReceiver != null;
        }

        abstract void onChange();

        void setup() {
            cleanup();
            IntentFilter createIntentFilterForBroadcastReceiver = createIntentFilterForBroadcastReceiver();
            if (createIntentFilterForBroadcastReceiver == null || createIntentFilterForBroadcastReceiver.countActions() == 0) {
                return;
            }
            if (this.mReceiver == null) {
                this.mReceiver = new BroadcastReceiver() { // from class: androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager.1
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context, Intent intent) {
                        AutoNightModeManager.this.onChange();
                    }
                };
            }
            AppCompatDelegateImpl.this.f237q.registerReceiver(this.mReceiver, createIntentFilterForBroadcastReceiver);
        }
    }

    private class AutoTimeNightModeManager extends AutoNightModeManager {

        /* renamed from: a, reason: collision with root package name */
        private final TwilightManager f261a;

        AutoTimeNightModeManager(TwilightManager twilightManager) {
            super();
            this.f261a = twilightManager;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        IntentFilter createIntentFilterForBroadcastReceiver() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public int getApplyableNightMode() {
            return this.f261a.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public void onChange() {
            AppCompatDelegateImpl.this.R();
        }
    }

    private class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(Context context) {
            super(context);
        }

        private boolean b(int i2, int i3) {
            return i2 < -5 || i3 < -5 || i2 > getWidth() + 5 || i3 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.f0(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !b((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.Z(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i2) {
            setBackgroundDrawable(AppCompatResources.b(getContext(), i2));
        }
    }

    protected static final class PanelFeatureState {

        /* renamed from: a, reason: collision with root package name */
        int f263a;

        /* renamed from: b, reason: collision with root package name */
        int f264b;

        /* renamed from: c, reason: collision with root package name */
        int f265c;

        /* renamed from: d, reason: collision with root package name */
        int f266d;

        /* renamed from: e, reason: collision with root package name */
        int f267e;

        /* renamed from: f, reason: collision with root package name */
        int f268f;

        /* renamed from: g, reason: collision with root package name */
        ViewGroup f269g;

        /* renamed from: h, reason: collision with root package name */
        View f270h;

        /* renamed from: i, reason: collision with root package name */
        View f271i;

        /* renamed from: j, reason: collision with root package name */
        MenuBuilder f272j;

        /* renamed from: k, reason: collision with root package name */
        ListMenuPresenter f273k;

        /* renamed from: l, reason: collision with root package name */
        Context f274l;

        /* renamed from: m, reason: collision with root package name */
        boolean f275m;

        /* renamed from: n, reason: collision with root package name */
        boolean f276n;

        /* renamed from: o, reason: collision with root package name */
        boolean f277o;

        /* renamed from: p, reason: collision with root package name */
        public boolean f278p;

        /* renamed from: q, reason: collision with root package name */
        boolean f279q = false;

        /* renamed from: r, reason: collision with root package name */
        boolean f280r;

        /* renamed from: s, reason: collision with root package name */
        Bundle f281s;

        @SuppressLint({"BanParcelableUsage"})
        private static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.appcompat.app.AppCompatDelegateImpl.PanelFeatureState.SavedState.1
                @Override // android.os.Parcelable.Creator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.a(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.a(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public SavedState[] newArray(int i2) {
                    return new SavedState[i2];
                }
            };

            /* renamed from: c, reason: collision with root package name */
            int f282c;

            /* renamed from: h, reason: collision with root package name */
            boolean f283h;

            /* renamed from: i, reason: collision with root package name */
            Bundle f284i;

            SavedState() {
            }

            static SavedState a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f282c = parcel.readInt();
                boolean z = parcel.readInt() == 1;
                savedState.f283h = z;
                if (z) {
                    savedState.f284i = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.f282c);
                parcel.writeInt(this.f283h ? 1 : 0);
                if (this.f283h) {
                    parcel.writeBundle(this.f284i);
                }
            }
        }

        PanelFeatureState(int i2) {
            this.f263a = i2;
        }

        MenuView a(MenuPresenter.Callback callback) {
            if (this.f272j == null) {
                return null;
            }
            if (this.f273k == null) {
                ListMenuPresenter listMenuPresenter = new ListMenuPresenter(this.f274l, androidx.appcompat.R.layout.abc_list_menu_item_layout);
                this.f273k = listMenuPresenter;
                listMenuPresenter.c(callback);
                this.f272j.b(this.f273k);
            }
            return this.f273k.h(this.f269g);
        }

        public boolean b() {
            if (this.f270h == null) {
                return false;
            }
            return this.f271i != null || this.f273k.g().getCount() > 0;
        }

        void c(MenuBuilder menuBuilder) {
            ListMenuPresenter listMenuPresenter;
            MenuBuilder menuBuilder2 = this.f272j;
            if (menuBuilder == menuBuilder2) {
                return;
            }
            if (menuBuilder2 != null) {
                menuBuilder2.R(this.f273k);
            }
            this.f272j = menuBuilder;
            if (menuBuilder == null || (listMenuPresenter = this.f273k) == null) {
                return;
            }
            menuBuilder.b(listMenuPresenter);
        }

        void d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(androidx.appcompat.R.attr.actionBarPopupTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                newTheme.applyStyle(i2, true);
            }
            newTheme.resolveAttribute(androidx.appcompat.R.attr.panelMenuListTheme, typedValue, true);
            int i3 = typedValue.resourceId;
            if (i3 != 0) {
                newTheme.applyStyle(i3, true);
            } else {
                newTheme.applyStyle(androidx.appcompat.R.style.Theme_AppCompat_CompactMenu, true);
            }
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
            contextThemeWrapper.getTheme().setTo(newTheme);
            this.f274l = contextThemeWrapper;
            TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(androidx.appcompat.R.styleable.AppCompatTheme);
            this.f264b = obtainStyledAttributes.getResourceId(androidx.appcompat.R.styleable.AppCompatTheme_panelBackground, 0);
            this.f268f = obtainStyledAttributes.getResourceId(androidx.appcompat.R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }
    }

    private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        PanelMenuPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void a(MenuBuilder menuBuilder, boolean z) {
            MenuBuilder F = menuBuilder.F();
            boolean z2 = F != menuBuilder;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                menuBuilder = F;
            }
            PanelFeatureState k0 = appCompatDelegateImpl.k0(menuBuilder);
            if (k0 != null) {
                if (!z2) {
                    AppCompatDelegateImpl.this.a0(k0, z);
                } else {
                    AppCompatDelegateImpl.this.W(k0.f263a, k0, F);
                    AppCompatDelegateImpl.this.a0(k0, true);
                }
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean b(MenuBuilder menuBuilder) {
            Window.Callback t0;
            if (menuBuilder != menuBuilder.F()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.M || (t0 = appCompatDelegateImpl.t0()) == null || AppCompatDelegateImpl.this.X) {
                return true;
            }
            t0.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    AppCompatDelegateImpl(Activity activity, AppCompatCallback appCompatCallback) {
        this(activity, null, appCompatCallback, activity);
    }

    private boolean D0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState r02 = r0(i2, true);
        if (r02.f277o) {
            return false;
        }
        return N0(r02, keyEvent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x005a, code lost:
    
        if (N0(r2, r6) != false) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean G0(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            androidx.appcompat.view.ActionMode r0 = r4.A
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r4.r0(r5, r0)
            if (r5 != 0) goto L43
            androidx.appcompat.widget.DecorContentParent r5 = r4.x
            if (r5 == 0) goto L43
            boolean r5 = r5.canShowOverflowMenu()
            if (r5 == 0) goto L43
            android.content.Context r5 = r4.f237q
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            boolean r5 = r5.hasPermanentMenuKey()
            if (r5 != 0) goto L43
            androidx.appcompat.widget.DecorContentParent r5 = r4.x
            boolean r5 = r5.isOverflowMenuShowing()
            if (r5 != 0) goto L3c
            boolean r5 = r4.X
            if (r5 != 0) goto L60
            boolean r5 = r4.N0(r2, r6)
            if (r5 == 0) goto L60
            androidx.appcompat.widget.DecorContentParent r5 = r4.x
            boolean r0 = r5.showOverflowMenu()
            goto L66
        L3c:
            androidx.appcompat.widget.DecorContentParent r5 = r4.x
            boolean r0 = r5.hideOverflowMenu()
            goto L66
        L43:
            boolean r5 = r2.f277o
            if (r5 != 0) goto L62
            boolean r3 = r2.f276n
            if (r3 == 0) goto L4c
            goto L62
        L4c:
            boolean r5 = r2.f275m
            if (r5 == 0) goto L60
            boolean r5 = r2.f280r
            if (r5 == 0) goto L5c
            r2.f275m = r1
            boolean r5 = r4.N0(r2, r6)
            if (r5 == 0) goto L60
        L5c:
            r4.K0(r2, r6)
            goto L66
        L60:
            r0 = r1
            goto L66
        L62:
            r4.a0(r2, r0)
            r0 = r5
        L66:
            if (r0 == 0) goto L83
            android.content.Context r4 = r4.f237q
            android.content.Context r4 = r4.getApplicationContext()
            java.lang.String r5 = "audio"
            java.lang.Object r4 = r4.getSystemService(r5)
            android.media.AudioManager r4 = (android.media.AudioManager) r4
            if (r4 == 0) goto L7c
            r4.playSoundEffect(r1)
            goto L83
        L7c:
            java.lang.String r4 = "AppCompatDelegate"
            java.lang.String r5 = "Couldn't get audio manager"
            android.util.Log.w(r4, r5)
        L83:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.G0(int, android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void K0(androidx.appcompat.app.AppCompatDelegateImpl.PanelFeatureState r12, android.view.KeyEvent r13) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.K0(androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState, android.view.KeyEvent):void");
    }

    private boolean M0(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        MenuBuilder menuBuilder;
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.f275m || N0(panelFeatureState, keyEvent)) && (menuBuilder = panelFeatureState.f272j) != null) {
            z = menuBuilder.performShortcut(i2, keyEvent, i3);
        }
        if (z && (i3 & 1) == 0 && this.x == null) {
            a0(panelFeatureState, true);
        }
        return z;
    }

    private boolean N0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        DecorContentParent decorContentParent;
        DecorContentParent decorContentParent2;
        DecorContentParent decorContentParent3;
        if (this.X) {
            return false;
        }
        if (panelFeatureState.f275m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.T;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            a0(panelFeatureState2, false);
        }
        Window.Callback t0 = t0();
        if (t0 != null) {
            panelFeatureState.f271i = t0.onCreatePanelView(panelFeatureState.f263a);
        }
        int i2 = panelFeatureState.f263a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (decorContentParent3 = this.x) != null) {
            decorContentParent3.setMenuPrepared();
        }
        if (panelFeatureState.f271i == null && (!z || !(L0() instanceof ToolbarActionBar))) {
            MenuBuilder menuBuilder = panelFeatureState.f272j;
            if (menuBuilder == null || panelFeatureState.f280r) {
                if (menuBuilder == null && (!x0(panelFeatureState) || panelFeatureState.f272j == null)) {
                    return false;
                }
                if (z && this.x != null) {
                    if (this.y == null) {
                        this.y = new ActionMenuPresenterCallback();
                    }
                    this.x.a(panelFeatureState.f272j, this.y);
                }
                panelFeatureState.f272j.i0();
                if (!t0.onCreatePanelMenu(panelFeatureState.f263a, panelFeatureState.f272j)) {
                    panelFeatureState.c(null);
                    if (z && (decorContentParent = this.x) != null) {
                        decorContentParent.a(null, this.y);
                    }
                    return false;
                }
                panelFeatureState.f280r = false;
            }
            panelFeatureState.f272j.i0();
            Bundle bundle = panelFeatureState.f281s;
            if (bundle != null) {
                panelFeatureState.f272j.S(bundle);
                panelFeatureState.f281s = null;
            }
            if (!t0.onPreparePanel(0, panelFeatureState.f271i, panelFeatureState.f272j)) {
                if (z && (decorContentParent2 = this.x) != null) {
                    decorContentParent2.a(null, this.y);
                }
                panelFeatureState.f272j.h0();
                return false;
            }
            boolean z2 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.f278p = z2;
            panelFeatureState.f272j.setQwertyMode(z2);
            panelFeatureState.f272j.h0();
        }
        panelFeatureState.f275m = true;
        panelFeatureState.f276n = false;
        this.T = panelFeatureState;
        return true;
    }

    private void O0(boolean z) {
        DecorContentParent decorContentParent = this.x;
        if (decorContentParent == null || !decorContentParent.canShowOverflowMenu() || (ViewConfiguration.get(this.f237q).hasPermanentMenuKey() && !this.x.isOverflowMenuShowPending())) {
            PanelFeatureState r02 = r0(0, true);
            r02.f279q = true;
            a0(r02, false);
            K0(r02, null);
            return;
        }
        Window.Callback t0 = t0();
        if (this.x.isOverflowMenuShowing() && z) {
            this.x.hideOverflowMenu();
            if (this.X) {
                return;
            }
            t0.onPanelClosed(108, r0(0, true).f272j);
            return;
        }
        if (t0 == null || this.X) {
            return;
        }
        if (this.f0 && (this.g0 & 1) != 0) {
            this.f238r.getDecorView().removeCallbacks(this.h0);
            this.h0.run();
        }
        PanelFeatureState r03 = r0(0, true);
        MenuBuilder menuBuilder = r03.f272j;
        if (menuBuilder == null || r03.f280r || !t0.onPreparePanel(0, r03.f271i, menuBuilder)) {
            return;
        }
        t0.onMenuOpened(108, r03.f272j);
        this.x.showOverflowMenu();
    }

    private boolean P(boolean z) {
        return Q(z, true);
    }

    private int P0(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        }
        if (i2 != 9) {
            return i2;
        }
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
        return 109;
    }

    private boolean Q(boolean z, boolean z2) {
        if (this.X) {
            return false;
        }
        int V = V();
        boolean a1 = a1(A0(this.f237q, V), null, z);
        if (V == 0) {
            p0(this.f237q).setup();
        } else {
            AutoNightModeManager autoNightModeManager = this.d0;
            if (autoNightModeManager != null) {
                autoNightModeManager.cleanup();
            }
        }
        if (V == 3) {
            o0(this.f237q).setup();
        } else {
            AutoNightModeManager autoNightModeManager2 = this.e0;
            if (autoNightModeManager2 != null) {
                autoNightModeManager2.cleanup();
            }
        }
        return a1;
    }

    private void S() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.H.findViewById(R.id.content);
        View decorView = this.f238r.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.f237q.obtainStyledAttributes(androidx.appcompat.R.styleable.AppCompatTheme);
        obtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private void T(Window window) {
        if (this.f238r != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof AppCompatWindowCallback) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        AppCompatWindowCallback appCompatWindowCallback = new AppCompatWindowCallback(callback);
        this.f239s = appCompatWindowCallback;
        window.setCallback(appCompatWindowCallback);
        TintTypedArray u = TintTypedArray.u(this.f237q, null, r0);
        Drawable h2 = u.h(0);
        if (h2 != null) {
            window.setBackgroundDrawable(h2);
        }
        u.x();
        this.f238r = window;
        if (this.n0 == null) {
            J(null);
        }
    }

    private boolean T0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f238r.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || ((View) viewParent).isAttachedToWindow()) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private int V() {
        int i2 = this.Z;
        return i2 != -100 ? i2 : AppCompatDelegate.m();
    }

    private void X0() {
        if (this.G) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private void Y() {
        AutoNightModeManager autoNightModeManager = this.d0;
        if (autoNightModeManager != null) {
            autoNightModeManager.cleanup();
        }
        AutoNightModeManager autoNightModeManager2 = this.e0;
        if (autoNightModeManager2 != null) {
            autoNightModeManager2.cleanup();
        }
    }

    private AppCompatActivity Y0() {
        for (Context context = this.f237q; context != null; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof AppCompatActivity) {
                return (AppCompatActivity) context;
            }
            if (!(context instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void Z0(Configuration configuration) {
        Activity activity = (Activity) this.f236p;
        if (activity instanceof LifecycleOwner) {
            if (((LifecycleOwner) activity).a().b().d(Lifecycle.State.CREATED)) {
                activity.onConfigurationChanged(configuration);
            }
        } else {
            if (!this.W || this.X) {
                return;
            }
            activity.onConfigurationChanged(configuration);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a1(int r10, androidx.core.os.LocaleListCompat r11, boolean r12) {
        /*
            r9 = this;
            android.content.Context r1 = r9.f237q
            r4 = 0
            r5 = 0
            r0 = r9
            r2 = r10
            r3 = r11
            android.content.res.Configuration r0 = r0.b0(r1, r2, r3, r4, r5)
            android.content.Context r1 = r9.f237q
            int r1 = r9.n0(r1)
            android.content.res.Configuration r2 = r9.Y
            if (r2 != 0) goto L1f
            android.content.Context r2 = r9.f237q
            android.content.res.Resources r2 = r2.getResources()
            android.content.res.Configuration r2 = r2.getConfiguration()
        L1f:
            int r3 = r2.uiMode
            r3 = r3 & 48
            int r4 = r0.uiMode
            r4 = r4 & 48
            androidx.core.os.LocaleListCompat r2 = r9.q0(r2)
            r5 = 0
            if (r11 != 0) goto L30
            r6 = r5
            goto L34
        L30:
            androidx.core.os.LocaleListCompat r6 = r9.q0(r0)
        L34:
            r7 = 0
            if (r3 == r4) goto L3a
            r3 = 512(0x200, float:7.17E-43)
            goto L3b
        L3a:
            r3 = r7
        L3b:
            if (r6 == 0) goto L45
            boolean r2 = r2.equals(r6)
            if (r2 != 0) goto L45
            r3 = r3 | 8196(0x2004, float:1.1485E-41)
        L45:
            int r2 = ~r1
            r2 = r2 & r3
            r8 = 1
            if (r2 == 0) goto L86
            if (r12 == 0) goto L86
            boolean r12 = r9.V
            if (r12 == 0) goto L86
            boolean r12 = androidx.appcompat.app.AppCompatDelegateImpl.s0
            if (r12 != 0) goto L58
            boolean r12 = r9.W
            if (r12 == 0) goto L86
        L58:
            java.lang.Object r12 = r9.f236p
            boolean r2 = r12 instanceof android.app.Activity
            if (r2 == 0) goto L86
            android.app.Activity r12 = (android.app.Activity) r12
            boolean r12 = r12.isChild()
            if (r12 != 0) goto L86
            r12 = r3 & 8192(0x2000, float:1.148E-41)
            if (r12 == 0) goto L7d
            java.lang.Object r12 = r9.f236p
            android.app.Activity r12 = (android.app.Activity) r12
            android.view.Window r12 = r12.getWindow()
            android.view.View r12 = r12.getDecorView()
            int r0 = r0.getLayoutDirection()
            r12.setLayoutDirection(r0)
        L7d:
            java.lang.Object r12 = r9.f236p
            android.app.Activity r12 = (android.app.Activity) r12
            androidx.core.app.ActivityCompat.o(r12)
            r12 = r8
            goto L87
        L86:
            r12 = r7
        L87:
            if (r12 != 0) goto L94
            if (r3 == 0) goto L94
            r12 = r3 & r1
            if (r12 != r3) goto L90
            r7 = r8
        L90:
            r9.c1(r4, r6, r7, r5)
            goto L95
        L94:
            r8 = r12
        L95:
            if (r8 == 0) goto Lb1
            java.lang.Object r12 = r9.f236p
            boolean r0 = r12 instanceof androidx.appcompat.app.AppCompatActivity
            if (r0 == 0) goto Lb1
            r0 = r3 & 512(0x200, float:7.17E-43)
            if (r0 == 0) goto La6
            androidx.appcompat.app.AppCompatActivity r12 = (androidx.appcompat.app.AppCompatActivity) r12
            r12.l0(r10)
        La6:
            r10 = r3 & 4
            if (r10 == 0) goto Lb1
            java.lang.Object r10 = r9.f236p
            androidx.appcompat.app.AppCompatActivity r10 = (androidx.appcompat.app.AppCompatActivity) r10
            r10.k0(r11)
        Lb1:
            if (r6 == 0) goto Lc4
            android.content.Context r10 = r9.f237q
            android.content.res.Resources r10 = r10.getResources()
            android.content.res.Configuration r10 = r10.getConfiguration()
            androidx.core.os.LocaleListCompat r10 = r9.q0(r10)
            r9.R0(r10)
        Lc4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.a1(int, androidx.core.os.LocaleListCompat, boolean):boolean");
    }

    private Configuration b0(Context context, int i2, LocaleListCompat localeListCompat, Configuration configuration, boolean z) {
        int i3 = i2 != 1 ? i2 != 2 ? z ? 0 : context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        if (localeListCompat != null) {
            Q0(configuration2, localeListCompat);
        }
        return configuration2;
    }

    private ViewGroup c0() {
        ViewGroup viewGroup;
        TypedArray obtainStyledAttributes = this.f237q.obtainStyledAttributes(androidx.appcompat.R.styleable.AppCompatTheme);
        if (!obtainStyledAttributes.hasValue(androidx.appcompat.R.styleable.AppCompatTheme_windowActionBar)) {
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (obtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_windowNoTitle, false)) {
            F(1);
        } else if (obtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_windowActionBar, false)) {
            F(108);
        }
        if (obtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            F(109);
        }
        if (obtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            F(10);
        }
        this.P = obtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_android_windowIsFloating, false);
        obtainStyledAttributes.recycle();
        j0();
        this.f238r.getDecorView();
        LayoutInflater from = LayoutInflater.from(this.f237q);
        if (this.Q) {
            viewGroup = this.O ? (ViewGroup) from.inflate(androidx.appcompat.R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) from.inflate(androidx.appcompat.R.layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.P) {
            viewGroup = (ViewGroup) from.inflate(androidx.appcompat.R.layout.abc_dialog_title_material, (ViewGroup) null);
            this.N = false;
            this.M = false;
        } else if (this.M) {
            TypedValue typedValue = new TypedValue();
            this.f237q.getTheme().resolveAttribute(androidx.appcompat.R.attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new ContextThemeWrapper(this.f237q, typedValue.resourceId) : this.f237q).inflate(androidx.appcompat.R.layout.abc_screen_toolbar, (ViewGroup) null);
            DecorContentParent decorContentParent = (DecorContentParent) viewGroup.findViewById(androidx.appcompat.R.id.decor_content_parent);
            this.x = decorContentParent;
            decorContentParent.setWindowCallback(t0());
            if (this.N) {
                this.x.initFeature(109);
            }
            if (this.K) {
                this.x.initFeature(2);
            }
            if (this.L) {
                this.x.initFeature(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.M + ", windowActionBarOverlay: " + this.N + ", android:windowIsFloating: " + this.P + ", windowActionModeOverlay: " + this.O + ", windowNoTitle: " + this.Q + " }");
        }
        ViewCompat.x0(viewGroup, new OnApplyWindowInsetsListener() { // from class: androidx.appcompat.app.AppCompatDelegateImpl.3
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat) {
                int l2 = windowInsetsCompat.l();
                int d1 = AppCompatDelegateImpl.this.d1(windowInsetsCompat, null);
                if (l2 != d1) {
                    windowInsetsCompat = windowInsetsCompat.q(windowInsetsCompat.j(), d1, windowInsetsCompat.k(), windowInsetsCompat.i());
                }
                return ViewCompat.U(view, windowInsetsCompat);
            }
        });
        if (this.x == null) {
            this.I = (TextView) viewGroup.findViewById(androidx.appcompat.R.id.title);
        }
        ViewUtils.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(androidx.appcompat.R.id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f238r.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.f238r.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new ContentFrameLayout.OnAttachListener() { // from class: androidx.appcompat.app.AppCompatDelegateImpl.5
            @Override // androidx.appcompat.widget.ContentFrameLayout.OnAttachListener
            public void a() {
            }

            @Override // androidx.appcompat.widget.ContentFrameLayout.OnAttachListener
            public void onDetachedFromWindow() {
                AppCompatDelegateImpl.this.e0();
            }
        });
        return viewGroup;
    }

    private void c1(int i2, LocaleListCompat localeListCompat, boolean z, Configuration configuration) {
        Resources resources = this.f237q.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        if (localeListCompat != null) {
            Q0(configuration2, localeListCompat);
        }
        resources.updateConfiguration(configuration2, null);
        int i3 = this.a0;
        if (i3 != 0) {
            this.f237q.setTheme(i3);
            this.f237q.getTheme().applyStyle(this.a0, true);
        }
        if (z && (this.f236p instanceof Activity)) {
            Z0(configuration2);
        }
    }

    private void e1(View view) {
        view.setBackgroundColor((ViewCompat.G(view) & 8192) != 0 ? ContextCompat.c(this.f237q, androidx.appcompat.R.color.abc_decor_view_status_guard_light) : ContextCompat.c(this.f237q, androidx.appcompat.R.color.abc_decor_view_status_guard));
    }

    private void i0() {
        if (this.G) {
            return;
        }
        this.H = c0();
        CharSequence s02 = s0();
        if (!TextUtils.isEmpty(s02)) {
            DecorContentParent decorContentParent = this.x;
            if (decorContentParent != null) {
                decorContentParent.setWindowTitle(s02);
            } else if (L0() != null) {
                L0().p(s02);
            } else {
                TextView textView = this.I;
                if (textView != null) {
                    textView.setText(s02);
                }
            }
        }
        S();
        J0(this.H);
        this.G = true;
        PanelFeatureState r02 = r0(0, false);
        if (this.X) {
            return;
        }
        if (r02 == null || r02.f272j == null) {
            y0(108);
        }
    }

    private void j0() {
        if (this.f238r == null) {
            Object obj = this.f236p;
            if (obj instanceof Activity) {
                T(((Activity) obj).getWindow());
            }
        }
        if (this.f238r == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private static Configuration l0(Configuration configuration, Configuration configuration2) {
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = 0.0f;
        if (configuration2 != null && configuration.diff(configuration2) != 0) {
            float f2 = configuration.fontScale;
            float f3 = configuration2.fontScale;
            if (f2 != f3) {
                configuration3.fontScale = f3;
            }
            int i2 = configuration.mcc;
            int i3 = configuration2.mcc;
            if (i2 != i3) {
                configuration3.mcc = i3;
            }
            int i4 = configuration.mnc;
            int i5 = configuration2.mnc;
            if (i4 != i5) {
                configuration3.mnc = i5;
            }
            Api24Impl.a(configuration, configuration2, configuration3);
            int i6 = configuration.touchscreen;
            int i7 = configuration2.touchscreen;
            if (i6 != i7) {
                configuration3.touchscreen = i7;
            }
            int i8 = configuration.keyboard;
            int i9 = configuration2.keyboard;
            if (i8 != i9) {
                configuration3.keyboard = i9;
            }
            int i10 = configuration.keyboardHidden;
            int i11 = configuration2.keyboardHidden;
            if (i10 != i11) {
                configuration3.keyboardHidden = i11;
            }
            int i12 = configuration.navigation;
            int i13 = configuration2.navigation;
            if (i12 != i13) {
                configuration3.navigation = i13;
            }
            int i14 = configuration.navigationHidden;
            int i15 = configuration2.navigationHidden;
            if (i14 != i15) {
                configuration3.navigationHidden = i15;
            }
            int i16 = configuration.orientation;
            int i17 = configuration2.orientation;
            if (i16 != i17) {
                configuration3.orientation = i17;
            }
            int i18 = configuration.screenLayout & 15;
            int i19 = configuration2.screenLayout;
            if (i18 != (i19 & 15)) {
                configuration3.screenLayout |= i19 & 15;
            }
            int i20 = configuration.screenLayout & 192;
            int i21 = configuration2.screenLayout;
            if (i20 != (i21 & 192)) {
                configuration3.screenLayout |= i21 & 192;
            }
            int i22 = configuration.screenLayout & 48;
            int i23 = configuration2.screenLayout;
            if (i22 != (i23 & 48)) {
                configuration3.screenLayout |= i23 & 48;
            }
            int i24 = configuration.screenLayout & 768;
            int i25 = configuration2.screenLayout;
            if (i24 != (i25 & 768)) {
                configuration3.screenLayout |= i25 & 768;
            }
            Api26Impl.a(configuration, configuration2, configuration3);
            int i26 = configuration.uiMode & 15;
            int i27 = configuration2.uiMode;
            if (i26 != (i27 & 15)) {
                configuration3.uiMode |= i27 & 15;
            }
            int i28 = configuration.uiMode & 48;
            int i29 = configuration2.uiMode;
            if (i28 != (i29 & 48)) {
                configuration3.uiMode |= i29 & 48;
            }
            int i30 = configuration.screenWidthDp;
            int i31 = configuration2.screenWidthDp;
            if (i30 != i31) {
                configuration3.screenWidthDp = i31;
            }
            int i32 = configuration.screenHeightDp;
            int i33 = configuration2.screenHeightDp;
            if (i32 != i33) {
                configuration3.screenHeightDp = i33;
            }
            int i34 = configuration.smallestScreenWidthDp;
            int i35 = configuration2.smallestScreenWidthDp;
            if (i34 != i35) {
                configuration3.smallestScreenWidthDp = i35;
            }
            int i36 = configuration.densityDpi;
            int i37 = configuration2.densityDpi;
            if (i36 != i37) {
                configuration3.densityDpi = i37;
            }
        }
        return configuration3;
    }

    private int n0(Context context) {
        if (!this.c0 && (this.f236p instanceof Activity)) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return 0;
            }
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(context, this.f236p.getClass()), 269221888);
                if (activityInfo != null) {
                    this.b0 = activityInfo.configChanges;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                this.b0 = 0;
            }
        }
        this.c0 = true;
        return this.b0;
    }

    private AutoNightModeManager o0(Context context) {
        if (this.e0 == null) {
            this.e0 = new AutoBatteryNightModeManager(context);
        }
        return this.e0;
    }

    private AutoNightModeManager p0(Context context) {
        if (this.d0 == null) {
            this.d0 = new AutoTimeNightModeManager(TwilightManager.a(context));
        }
        return this.d0;
    }

    private void u0() {
        i0();
        if (this.M && this.u == null) {
            Object obj = this.f236p;
            if (obj instanceof Activity) {
                this.u = new WindowDecorActionBar((Activity) this.f236p, this.N);
            } else if (obj instanceof Dialog) {
                this.u = new WindowDecorActionBar((Dialog) this.f236p);
            }
            ActionBar actionBar = this.u;
            if (actionBar != null) {
                actionBar.l(this.i0);
            }
        }
    }

    private boolean v0(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.f271i;
        if (view != null) {
            panelFeatureState.f270h = view;
            return true;
        }
        if (panelFeatureState.f272j == null) {
            return false;
        }
        if (this.z == null) {
            this.z = new PanelMenuPresenterCallback();
        }
        View view2 = (View) panelFeatureState.a(this.z);
        panelFeatureState.f270h = view2;
        return view2 != null;
    }

    private boolean w0(PanelFeatureState panelFeatureState) {
        panelFeatureState.d(m0());
        panelFeatureState.f269g = new ListMenuDecorView(panelFeatureState.f274l);
        panelFeatureState.f265c = 81;
        return true;
    }

    private boolean x0(PanelFeatureState panelFeatureState) {
        Resources.Theme theme;
        Context context = this.f237q;
        int i2 = panelFeatureState.f263a;
        if ((i2 == 0 || i2 == 108) && this.x != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme2 = context.getTheme();
            theme2.resolveAttribute(androidx.appcompat.R.attr.actionBarTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme = context.getResources().newTheme();
                theme.setTo(theme2);
                theme.applyStyle(typedValue.resourceId, true);
                theme.resolveAttribute(androidx.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme2.resolveAttribute(androidx.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
                theme = null;
            }
            if (typedValue.resourceId != 0) {
                if (theme == null) {
                    theme = context.getResources().newTheme();
                    theme.setTo(theme2);
                }
                theme.applyStyle(typedValue.resourceId, true);
            }
            if (theme != null) {
                ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
                contextThemeWrapper.getTheme().setTo(theme);
                context = contextThemeWrapper;
            }
        }
        MenuBuilder menuBuilder = new MenuBuilder(context);
        menuBuilder.W(this);
        panelFeatureState.c(menuBuilder);
        return true;
    }

    private void y0(int i2) {
        this.g0 = (1 << i2) | this.g0;
        if (this.f0) {
            return;
        }
        ViewCompat.a0(this.f238r.getDecorView(), this.h0);
        this.f0 = true;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void A(Bundle bundle) {
    }

    int A0(Context context, int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 == 0) {
                if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                    return -1;
                }
                return p0(context).getApplyableNightMode();
            }
            if (i2 != 1 && i2 != 2) {
                if (i2 == 3) {
                    return o0(context).getApplyableNightMode();
                }
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
        return i2;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void B() {
        Q(true, false);
    }

    boolean B0() {
        boolean z = this.U;
        this.U = false;
        PanelFeatureState r02 = r0(0, false);
        if (r02 != null && r02.f277o) {
            if (!z) {
                a0(r02, true);
            }
            return true;
        }
        androidx.appcompat.view.ActionMode actionMode = this.A;
        if (actionMode != null) {
            actionMode.c();
            return true;
        }
        ActionBar q2 = q();
        return q2 != null && q2.b();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void C() {
        ActionBar q2 = q();
        if (q2 != null) {
            q2.o(false);
        }
    }

    boolean C0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.U = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            D0(0, keyEvent);
            return true;
        }
        return false;
    }

    boolean E0(int i2, KeyEvent keyEvent) {
        ActionBar q2 = q();
        if (q2 != null && q2.i(i2, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.T;
        if (panelFeatureState != null && M0(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState panelFeatureState2 = this.T;
            if (panelFeatureState2 != null) {
                panelFeatureState2.f276n = true;
            }
            return true;
        }
        if (this.T == null) {
            PanelFeatureState r02 = r0(0, true);
            N0(r02, keyEvent);
            boolean M0 = M0(r02, keyEvent.getKeyCode(), keyEvent, 1);
            r02.f275m = false;
            if (M0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public boolean F(int i2) {
        int P0 = P0(i2);
        if (this.Q && P0 == 108) {
            return false;
        }
        if (this.M && P0 == 1) {
            this.M = false;
        }
        if (P0 == 1) {
            X0();
            this.Q = true;
            return true;
        }
        if (P0 == 2) {
            X0();
            this.K = true;
            return true;
        }
        if (P0 == 5) {
            X0();
            this.L = true;
            return true;
        }
        if (P0 == 10) {
            X0();
            this.O = true;
            return true;
        }
        if (P0 == 108) {
            X0();
            this.M = true;
            return true;
        }
        if (P0 != 109) {
            return this.f238r.requestFeature(P0);
        }
        X0();
        this.N = true;
        return true;
    }

    boolean F0(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            if (i2 == 82) {
                G0(0, keyEvent);
                return true;
            }
        } else if (B0()) {
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void G(int i2) {
        i0();
        ViewGroup viewGroup = (ViewGroup) this.H.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f237q).inflate(i2, viewGroup);
        this.f239s.c(this.f238r.getCallback());
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void H(View view) {
        i0();
        ViewGroup viewGroup = (ViewGroup) this.H.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f239s.c(this.f238r.getCallback());
    }

    void H0(int i2) {
        ActionBar q2;
        if (i2 != 108 || (q2 = q()) == null) {
            return;
        }
        q2.c(true);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void I(View view, ViewGroup.LayoutParams layoutParams) {
        i0();
        ViewGroup viewGroup = (ViewGroup) this.H.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f239s.c(this.f238r.getCallback());
    }

    void I0(int i2) {
        if (i2 == 108) {
            ActionBar q2 = q();
            if (q2 != null) {
                q2.c(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            PanelFeatureState r02 = r0(i2, true);
            if (r02.f277o) {
                a0(r02, false);
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void J(OnBackInvokedDispatcher onBackInvokedDispatcher) {
        OnBackInvokedCallback onBackInvokedCallback;
        super.J(onBackInvokedDispatcher);
        OnBackInvokedDispatcher onBackInvokedDispatcher2 = this.n0;
        if (onBackInvokedDispatcher2 != null && (onBackInvokedCallback = this.o0) != null) {
            Api33Impl.c(onBackInvokedDispatcher2, onBackInvokedCallback);
            this.o0 = null;
        }
        if (onBackInvokedDispatcher == null) {
            Object obj = this.f236p;
            if ((obj instanceof Activity) && ((Activity) obj).getWindow() != null) {
                this.n0 = Api33Impl.a((Activity) this.f236p);
                b1();
            }
        }
        this.n0 = onBackInvokedDispatcher;
        b1();
    }

    void J0(ViewGroup viewGroup) {
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void K(Toolbar toolbar) {
        if (this.f236p instanceof Activity) {
            ActionBar q2 = q();
            if (q2 instanceof WindowDecorActionBar) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.v = null;
            if (q2 != null) {
                q2.h();
            }
            this.u = null;
            if (toolbar != null) {
                ToolbarActionBar toolbarActionBar = new ToolbarActionBar(toolbar, s0(), this.f239s);
                this.u = toolbarActionBar;
                this.f239s.e(toolbarActionBar.f302c);
                toolbar.setBackInvokedCallbackEnabled(true);
            } else {
                this.f239s.e(null);
            }
            s();
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void L(int i2) {
        this.a0 = i2;
    }

    final ActionBar L0() {
        return this.u;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void M(CharSequence charSequence) {
        this.w = charSequence;
        DecorContentParent decorContentParent = this.x;
        if (decorContentParent != null) {
            decorContentParent.setWindowTitle(charSequence);
            return;
        }
        if (L0() != null) {
            L0().p(charSequence);
            return;
        }
        TextView textView = this.I;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    void Q0(Configuration configuration, LocaleListCompat localeListCompat) {
        Api24Impl.d(configuration, localeListCompat);
    }

    public boolean R() {
        return P(true);
    }

    void R0(LocaleListCompat localeListCompat) {
        Api24Impl.c(localeListCompat);
    }

    final boolean S0() {
        ViewGroup viewGroup;
        return this.G && (viewGroup = this.H) != null && viewGroup.isLaidOut();
    }

    LocaleListCompat U(Context context) {
        return null;
    }

    boolean U0() {
        if (this.n0 == null) {
            return false;
        }
        PanelFeatureState r02 = r0(0, false);
        return (r02 != null && r02.f277o) || this.A != null;
    }

    public androidx.appcompat.view.ActionMode V0(ActionMode.Callback callback) {
        AppCompatCallback appCompatCallback;
        if (callback == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        androidx.appcompat.view.ActionMode actionMode = this.A;
        if (actionMode != null) {
            actionMode.c();
        }
        ActionModeCallbackWrapperV9 actionModeCallbackWrapperV9 = new ActionModeCallbackWrapperV9(callback);
        ActionBar q2 = q();
        if (q2 != null) {
            androidx.appcompat.view.ActionMode q3 = q2.q(actionModeCallbackWrapperV9);
            this.A = q3;
            if (q3 != null && (appCompatCallback = this.t) != null) {
                appCompatCallback.p(q3);
            }
        }
        if (this.A == null) {
            this.A = W0(actionModeCallbackWrapperV9);
        }
        b1();
        return this.A;
    }

    void W(int i2, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i2 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.S;
                if (i2 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i2];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.f272j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.f277o) && !this.X) {
            this.f239s.d(this.f238r.getCallback(), i2, menu);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    androidx.appcompat.view.ActionMode W0(androidx.appcompat.view.ActionMode.Callback r8) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.W0(androidx.appcompat.view.ActionMode$Callback):androidx.appcompat.view.ActionMode");
    }

    void X(MenuBuilder menuBuilder) {
        if (this.R) {
            return;
        }
        this.R = true;
        this.x.dismissPopups();
        Window.Callback t0 = t0();
        if (t0 != null && !this.X) {
            t0.onPanelClosed(108, menuBuilder);
        }
        this.R = false;
    }

    void Z(int i2) {
        a0(r0(i2, true), true);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        PanelFeatureState k0;
        Window.Callback t0 = t0();
        if (t0 == null || this.X || (k0 = k0(menuBuilder.F())) == null) {
            return false;
        }
        return t0.onMenuItemSelected(k0.f263a, menuItem);
    }

    void a0(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        DecorContentParent decorContentParent;
        if (z && panelFeatureState.f263a == 0 && (decorContentParent = this.x) != null && decorContentParent.isOverflowMenuShowing()) {
            X(panelFeatureState.f272j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f237q.getSystemService("window");
        if (windowManager != null && panelFeatureState.f277o && (viewGroup = panelFeatureState.f269g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                W(panelFeatureState.f263a, panelFeatureState, null);
            }
        }
        panelFeatureState.f275m = false;
        panelFeatureState.f276n = false;
        panelFeatureState.f277o = false;
        panelFeatureState.f270h = null;
        panelFeatureState.f279q = true;
        if (this.T == panelFeatureState) {
            this.T = null;
        }
        if (panelFeatureState.f263a == 0) {
            b1();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public void b(MenuBuilder menuBuilder) {
        O0(true);
    }

    void b1() {
        OnBackInvokedCallback onBackInvokedCallback;
        boolean U0 = U0();
        if (U0 && this.o0 == null) {
            this.o0 = Api33Impl.b(this.n0, this);
        } else {
            if (U0 || (onBackInvokedCallback = this.o0) == null) {
                return;
            }
            Api33Impl.c(this.n0, onBackInvokedCallback);
            this.o0 = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View d0(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        if (this.l0 == null) {
            TypedArray obtainStyledAttributes = this.f237q.obtainStyledAttributes(androidx.appcompat.R.styleable.AppCompatTheme);
            String string = obtainStyledAttributes.getString(androidx.appcompat.R.styleable.AppCompatTheme_viewInflaterClass);
            obtainStyledAttributes.recycle();
            if (string == null) {
                this.l0 = new AppCompatViewInflater();
            } else {
                try {
                    this.l0 = (AppCompatViewInflater) this.f237q.getClassLoader().loadClass(string).getDeclaredConstructor(null).newInstance(null);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.l0 = new AppCompatViewInflater();
                }
            }
        }
        boolean z2 = q0;
        boolean z3 = false;
        if (z2) {
            if (this.m0 == null) {
                this.m0 = new LayoutIncludeDetector();
            }
            if (this.m0.a(attributeSet)) {
                z = true;
                return this.l0.createView(view, str, context, attributeSet, z, z2, true, VectorEnabledTintResources.c());
            }
            if (!(attributeSet instanceof XmlPullParser)) {
                z3 = T0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                z3 = true;
            }
        }
        z = z3;
        return this.l0.createView(view, str, context, attributeSet, z, z2, true, VectorEnabledTintResources.c());
    }

    final int d1(WindowInsetsCompat windowInsetsCompat, Rect rect) {
        boolean z;
        boolean z2;
        int l2 = windowInsetsCompat != null ? windowInsetsCompat.l() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.B;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.B.getLayoutParams();
            if (this.B.isShown()) {
                if (this.j0 == null) {
                    this.j0 = new Rect();
                    this.k0 = new Rect();
                }
                Rect rect2 = this.j0;
                Rect rect3 = this.k0;
                if (windowInsetsCompat == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(windowInsetsCompat.j(), windowInsetsCompat.l(), windowInsetsCompat.k(), windowInsetsCompat.i());
                }
                ViewUtils.a(this.H, rect2, rect3);
                int i2 = rect2.top;
                int i3 = rect2.left;
                int i4 = rect2.right;
                WindowInsetsCompat B = ViewCompat.B(this.H);
                int j2 = B == null ? 0 : B.j();
                int k2 = B == null ? 0 : B.k();
                if (marginLayoutParams.topMargin == i2 && marginLayoutParams.leftMargin == i3 && marginLayoutParams.rightMargin == i4) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i2;
                    marginLayoutParams.leftMargin = i3;
                    marginLayoutParams.rightMargin = i4;
                    z2 = true;
                }
                if (i2 <= 0 || this.J != null) {
                    View view = this.J;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i5 = marginLayoutParams2.height;
                        int i6 = marginLayoutParams.topMargin;
                        if (i5 != i6 || marginLayoutParams2.leftMargin != j2 || marginLayoutParams2.rightMargin != k2) {
                            marginLayoutParams2.height = i6;
                            marginLayoutParams2.leftMargin = j2;
                            marginLayoutParams2.rightMargin = k2;
                            this.J.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.f237q);
                    this.J = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = j2;
                    layoutParams.rightMargin = k2;
                    this.H.addView(this.J, -1, layoutParams);
                }
                View view3 = this.J;
                r5 = view3 != null;
                if (r5 && view3.getVisibility() != 0) {
                    e1(this.J);
                }
                if (!this.O && r5) {
                    l2 = 0;
                }
                z = r5;
                r5 = z2;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z = false;
            } else {
                z = false;
                r5 = false;
            }
            if (r5) {
                this.B.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.J;
        if (view4 != null) {
            view4.setVisibility(z ? 0 : 8);
        }
        return l2;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void e(View view, ViewGroup.LayoutParams layoutParams) {
        i0();
        ((ViewGroup) this.H.findViewById(R.id.content)).addView(view, layoutParams);
        this.f239s.c(this.f238r.getCallback());
    }

    void e0() {
        MenuBuilder menuBuilder;
        DecorContentParent decorContentParent = this.x;
        if (decorContentParent != null) {
            decorContentParent.dismissPopups();
        }
        if (this.C != null) {
            this.f238r.getDecorView().removeCallbacks(this.D);
            if (this.C.isShowing()) {
                try {
                    this.C.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.C = null;
        }
        h0();
        PanelFeatureState r02 = r0(0, false);
        if (r02 == null || (menuBuilder = r02.f272j) == null) {
            return;
        }
        menuBuilder.close();
    }

    boolean f0(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.f236p;
        if (((obj instanceof KeyEventDispatcher.Component) || (obj instanceof AppCompatDialog)) && (decorView = this.f238r.getDecorView()) != null && KeyEventDispatcher.a(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.f239s.b(this.f238r.getCallback(), keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? C0(keyCode, keyEvent) : F0(keyCode, keyEvent);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public Context g(Context context) {
        this.V = true;
        int A0 = A0(context, V());
        if (AppCompatDelegate.t(context)) {
            AppCompatDelegate.O(context);
        }
        LocaleListCompat U = U(context);
        if (context instanceof android.view.ContextThemeWrapper) {
            try {
                ((android.view.ContextThemeWrapper) context).applyOverrideConfiguration(b0(context, A0, U, null, false));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof ContextThemeWrapper) {
            try {
                ((ContextThemeWrapper) context).a(b0(context, A0, U, null, false));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!s0) {
            return super.g(context);
        }
        Configuration configuration = new Configuration();
        configuration.uiMode = -1;
        configuration.fontScale = 0.0f;
        Configuration configuration2 = context.createConfigurationContext(configuration).getResources().getConfiguration();
        Configuration configuration3 = context.getResources().getConfiguration();
        configuration2.uiMode = configuration3.uiMode;
        Configuration b0 = b0(context, A0, U, !configuration2.equals(configuration3) ? l0(configuration2, configuration3) : null, true);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, androidx.appcompat.R.style.Theme_AppCompat_Empty);
        contextThemeWrapper.a(b0);
        try {
            if (context.getTheme() != null) {
                ResourcesCompat.ThemeCompat.a(contextThemeWrapper.getTheme());
            }
        } catch (NullPointerException unused3) {
        }
        return super.g(contextThemeWrapper);
    }

    void g0(int i2) {
        PanelFeatureState r02;
        PanelFeatureState r03 = r0(i2, true);
        if (r03.f272j != null) {
            Bundle bundle = new Bundle();
            r03.f272j.U(bundle);
            if (bundle.size() > 0) {
                r03.f281s = bundle;
            }
            r03.f272j.i0();
            r03.f272j.clear();
        }
        r03.f280r = true;
        r03.f279q = true;
        if ((i2 != 108 && i2 != 0) || this.x == null || (r02 = r0(0, false)) == null) {
            return;
        }
        r02.f275m = false;
        N0(r02, null);
    }

    @NonNull
    @RestrictTo
    @VisibleForTesting
    final AutoNightModeManager getAutoTimeNightModeManager() {
        return p0(this.f237q);
    }

    void h0() {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.E;
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.c();
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public View j(int i2) {
        i0();
        return this.f238r.findViewById(i2);
    }

    PanelFeatureState k0(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.S;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
            if (panelFeatureState != null && panelFeatureState.f272j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public Context l() {
        return this.f237q;
    }

    final Context m0() {
        ActionBar q2 = q();
        Context e2 = q2 != null ? q2.e() : null;
        return e2 == null ? this.f237q : e2;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public int n() {
        return this.Z;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return d0(view, str, context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public MenuInflater p() {
        if (this.v == null) {
            u0();
            ActionBar actionBar = this.u;
            this.v = new SupportMenuInflater(actionBar != null ? actionBar.e() : this.f237q);
        }
        return this.v;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public ActionBar q() {
        u0();
        return this.u;
    }

    LocaleListCompat q0(Configuration configuration) {
        return Api24Impl.b(configuration);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void r() {
        LayoutInflater from = LayoutInflater.from(this.f237q);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.a(from, this);
        } else {
            if (from.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    protected PanelFeatureState r0(int i2, boolean z) {
        PanelFeatureState[] panelFeatureStateArr = this.S;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i2) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i2 + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.S = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i2);
        panelFeatureStateArr[i2] = panelFeatureState2;
        return panelFeatureState2;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void s() {
        if (L0() == null || q().f()) {
            return;
        }
        y0(0);
    }

    final CharSequence s0() {
        Object obj = this.f236p;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.w;
    }

    final Window.Callback t0() {
        return this.f238r.getCallback();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void v(Configuration configuration) {
        ActionBar q2;
        if (this.M && this.G && (q2 = q()) != null) {
            q2.g(configuration);
        }
        AppCompatDrawableManager.b().g(this.f237q);
        this.Y = new Configuration(this.f237q.getResources().getConfiguration());
        Q(false, false);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void w(Bundle bundle) {
        String str;
        this.V = true;
        P(false);
        j0();
        Object obj = this.f236p;
        if (obj instanceof Activity) {
            try {
                str = NavUtils.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
                str = null;
            }
            if (str != null) {
                ActionBar L0 = L0();
                if (L0 == null) {
                    this.i0 = true;
                } else {
                    L0.l(true);
                }
            }
            AppCompatDelegate.d(this);
        }
        this.Y = new Configuration(this.f237q.getResources().getConfiguration());
        this.W = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
    @Override // androidx.appcompat.app.AppCompatDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void x() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f236p
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L9
            androidx.appcompat.app.AppCompatDelegate.D(r3)
        L9:
            boolean r0 = r3.f0
            if (r0 == 0) goto L18
            android.view.Window r0 = r3.f238r
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.h0
            r0.removeCallbacks(r1)
        L18:
            r0 = 1
            r3.X = r0
            int r0 = r3.Z
            r1 = -100
            if (r0 == r1) goto L45
            java.lang.Object r0 = r3.f236p
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L45
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L45
            androidx.collection.SimpleArrayMap r0 = androidx.appcompat.app.AppCompatDelegateImpl.p0
            java.lang.Object r1 = r3.f236p
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.Z
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L54
        L45:
            androidx.collection.SimpleArrayMap r0 = androidx.appcompat.app.AppCompatDelegateImpl.p0
            java.lang.Object r1 = r3.f236p
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L54:
            androidx.appcompat.app.ActionBar r0 = r3.u
            if (r0 == 0) goto L5b
            r0.h()
        L5b:
            r3.Y()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.x():void");
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void y(Bundle bundle) {
        i0();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void z() {
        ActionBar q2 = q();
        if (q2 != null) {
            q2.o(true);
        }
    }

    public boolean z0() {
        return this.F;
    }

    AppCompatDelegateImpl(Dialog dialog, AppCompatCallback appCompatCallback) {
        this(dialog.getContext(), dialog.getWindow(), appCompatCallback, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private AppCompatDelegateImpl(Context context, Window window, AppCompatCallback appCompatCallback, Object obj) {
        AppCompatActivity Y0;
        this.E = null;
        this.F = true;
        this.Z = -100;
        this.h0 = new Runnable() { // from class: androidx.appcompat.app.AppCompatDelegateImpl.2
            @Override // java.lang.Runnable
            public void run() {
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                if ((appCompatDelegateImpl.g0 & 1) != 0) {
                    appCompatDelegateImpl.g0(0);
                }
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                if ((appCompatDelegateImpl2.g0 & 4096) != 0) {
                    appCompatDelegateImpl2.g0(108);
                }
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.f0 = false;
                appCompatDelegateImpl3.g0 = 0;
            }
        };
        this.f237q = context;
        this.t = appCompatCallback;
        this.f236p = obj;
        if (this.Z == -100 && (obj instanceof Dialog) && (Y0 = Y0()) != null) {
            this.Z = Y0.f0().n();
        }
        if (this.Z == -100) {
            SimpleArrayMap simpleArrayMap = p0;
            Integer num = (Integer) simpleArrayMap.get(obj.getClass().getName());
            if (num != null) {
                this.Z = num.intValue();
                simpleArrayMap.remove(obj.getClass().getName());
            }
        }
        if (window != null) {
            T(window);
        }
        AppCompatDrawableManager.h();
    }
}
