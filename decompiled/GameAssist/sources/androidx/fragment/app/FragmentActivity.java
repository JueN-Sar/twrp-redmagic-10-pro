package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class FragmentActivity extends ComponentActivity implements ActivityCompat.OnRequestPermissionsResultCallback, ActivityCompat.RequestPermissionsRequestCodeValidator {
    boolean C;
    boolean D;
    final FragmentController A = FragmentController.b(new HostCallbacks());
    final LifecycleRegistry B = new LifecycleRegistry(this);
    boolean E = true;

    class HostCallbacks extends FragmentHostCallback<FragmentActivity> implements OnConfigurationChangedProvider, OnTrimMemoryProvider, OnMultiWindowModeChangedProvider, OnPictureInPictureModeChangedProvider, ViewModelStoreOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, SavedStateRegistryOwner, FragmentOnAttachListener, MenuHost {
        public HostCallbacks() {
            super(FragmentActivity.this);
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public void A() {
            B();
        }

        public void B() {
            FragmentActivity.this.invalidateOptionsMenu();
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        /* renamed from: C, reason: merged with bridge method [inline-methods] */
        public FragmentActivity x() {
            return FragmentActivity.this;
        }

        @Override // androidx.lifecycle.LifecycleOwner
        public Lifecycle a() {
            return FragmentActivity.this.B;
        }

        @Override // androidx.core.content.OnConfigurationChangedProvider
        public void b(Consumer consumer) {
            FragmentActivity.this.b(consumer);
        }

        @Override // androidx.core.app.OnPictureInPictureModeChangedProvider
        public void c(Consumer consumer) {
            FragmentActivity.this.c(consumer);
        }

        @Override // androidx.core.content.OnTrimMemoryProvider
        public void d(Consumer consumer) {
            FragmentActivity.this.d(consumer);
        }

        @Override // androidx.core.app.OnPictureInPictureModeChangedProvider
        public void e(Consumer consumer) {
            FragmentActivity.this.e(consumer);
        }

        @Override // androidx.activity.result.ActivityResultRegistryOwner
        public ActivityResultRegistry f() {
            return FragmentActivity.this.f();
        }

        @Override // androidx.fragment.app.FragmentOnAttachListener
        public void g(FragmentManager fragmentManager, Fragment fragment) {
            FragmentActivity.this.d0(fragment);
        }

        @Override // androidx.lifecycle.ViewModelStoreOwner
        public ViewModelStore h() {
            return FragmentActivity.this.h();
        }

        @Override // androidx.savedstate.SavedStateRegistryOwner
        public SavedStateRegistry i() {
            return FragmentActivity.this.i();
        }

        @Override // androidx.core.content.OnTrimMemoryProvider
        public void k(Consumer consumer) {
            FragmentActivity.this.k(consumer);
        }

        @Override // androidx.core.app.OnMultiWindowModeChangedProvider
        public void l(Consumer consumer) {
            FragmentActivity.this.l(consumer);
        }

        @Override // androidx.fragment.app.FragmentHostCallback, androidx.fragment.app.FragmentContainer
        public View m(int i2) {
            return FragmentActivity.this.findViewById(i2);
        }

        @Override // androidx.activity.OnBackPressedDispatcherOwner
        public OnBackPressedDispatcher n() {
            return FragmentActivity.this.n();
        }

        @Override // androidx.core.view.MenuHost
        public void o(MenuProvider menuProvider) {
            FragmentActivity.this.o(menuProvider);
        }

        @Override // androidx.fragment.app.FragmentHostCallback, androidx.fragment.app.FragmentContainer
        public boolean p() {
            Window window = FragmentActivity.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // androidx.core.app.OnMultiWindowModeChangedProvider
        public void t(Consumer consumer) {
            FragmentActivity.this.t(consumer);
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public void u(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            FragmentActivity.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // androidx.core.content.OnConfigurationChangedProvider
        public void v(Consumer consumer) {
            FragmentActivity.this.v(consumer);
        }

        @Override // androidx.core.view.MenuHost
        public void w(MenuProvider menuProvider) {
            FragmentActivity.this.w(menuProvider);
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public LayoutInflater y() {
            return FragmentActivity.this.getLayoutInflater().cloneInContext(FragmentActivity.this);
        }
    }

    public FragmentActivity() {
        W();
    }

    private void W() {
        i().h("android:support:lifecycle", new SavedStateRegistry.SavedStateProvider() { // from class: androidx.fragment.app.a
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle a() {
                Bundle X;
                X = FragmentActivity.this.X();
                return X;
            }
        });
        b(new Consumer() { // from class: androidx.fragment.app.b
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                FragmentActivity.this.Y((Configuration) obj);
            }
        });
        H(new Consumer() { // from class: androidx.fragment.app.c
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                FragmentActivity.this.Z((Intent) obj);
            }
        });
        G(new OnContextAvailableListener() { // from class: androidx.fragment.app.d
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public final void a(Context context) {
                FragmentActivity.this.a0(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Bundle X() {
        b0();
        this.B.h(Lifecycle.Event.ON_STOP);
        return new Bundle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Y(Configuration configuration) {
        this.A.m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Z(Intent intent) {
        this.A.m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a0(Context context) {
        this.A.a(null);
    }

    private static boolean c0(FragmentManager fragmentManager, Lifecycle.State state) {
        boolean z = false;
        for (Fragment fragment : fragmentManager.z0()) {
            if (fragment != null) {
                if (fragment.I() != null) {
                    z |= c0(fragment.y(), state);
                }
                FragmentViewLifecycleOwner fragmentViewLifecycleOwner = fragment.Z;
                if (fragmentViewLifecycleOwner != null && fragmentViewLifecycleOwner.a().b().d(Lifecycle.State.STARTED)) {
                    fragment.Z.g(state);
                    z = true;
                }
                if (fragment.Y.b().d(Lifecycle.State.STARTED)) {
                    fragment.Y.m(state);
                    z = true;
                }
            }
        }
        return z;
    }

    final View U(View view, String str, Context context, AttributeSet attributeSet) {
        return this.A.n(view, str, context, attributeSet);
    }

    public FragmentManager V() {
        return this.A.l();
    }

    void b0() {
        while (c0(V(), Lifecycle.State.CREATED)) {
        }
    }

    public void d0(Fragment fragment) {
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (z(strArr)) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            String str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.C);
            printWriter.print(" mResumed=");
            printWriter.print(this.D);
            printWriter.print(" mStopped=");
            printWriter.print(this.E);
            if (getApplication() != null) {
                LoaderManager.b(this).a(str2, fileDescriptor, printWriter, strArr);
            }
            this.A.l().Z(str, fileDescriptor, printWriter, strArr);
        }
    }

    protected void e0() {
        this.B.h(Lifecycle.Event.ON_RESUME);
        this.A.h();
    }

    @Override // androidx.core.app.ActivityCompat.RequestPermissionsRequestCodeValidator
    public final void m(int i2) {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        this.A.m();
        super.onActivityResult(i2, i3, intent);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.B.h(Lifecycle.Event.ON_CREATE);
        this.A.e();
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View U = U(view, str, context, attributeSet);
        return U == null ? super.onCreateView(view, str, context, attributeSet) : U;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.A.f();
        this.B.h(Lifecycle.Event.ON_DESTROY);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        if (i2 == 6) {
            return this.A.d(menuItem);
        }
        return false;
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.D = false;
        this.A.g();
        this.B.h(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        e0();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        this.A.m();
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onResume() {
        this.A.m();
        super.onResume();
        this.D = true;
        this.A.k();
    }

    @Override // android.app.Activity
    protected void onStart() {
        this.A.m();
        super.onStart();
        this.E = false;
        if (!this.C) {
            this.C = true;
            this.A.c();
        }
        this.A.k();
        this.B.h(Lifecycle.Event.ON_START);
        this.A.i();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.A.m();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.E = true;
        b0();
        this.A.j();
        this.B.h(Lifecycle.Event.ON_STOP);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View U = U(null, str, context, attributeSet);
        return U == null ? super.onCreateView(str, context, attributeSet) : U;
    }
}
