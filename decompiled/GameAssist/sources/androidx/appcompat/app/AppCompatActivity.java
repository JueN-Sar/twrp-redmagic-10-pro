package androidx.appcompat.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.ViewTreeOnBackPressedDispatcherOwner;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;

/* loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements AppCompatCallback, TaskStackBuilder.SupportParentable, ActionBarDrawerToggle.DelegateProvider {
    private AppCompatDelegate F;
    private Resources G;

    public AppCompatActivity() {
        h0();
    }

    private void h0() {
        i().h("androidx:appcompat", new SavedStateRegistry.SavedStateProvider() { // from class: androidx.appcompat.app.AppCompatActivity.1
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public Bundle a() {
                Bundle bundle = new Bundle();
                AppCompatActivity.this.f0().A(bundle);
                return bundle;
            }
        });
        G(new OnContextAvailableListener() { // from class: androidx.appcompat.app.AppCompatActivity.2
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public void a(Context context) {
                AppCompatDelegate f0 = AppCompatActivity.this.f0();
                f0.r();
                f0.w(AppCompatActivity.this.i().b("androidx:appcompat"));
            }
        });
    }

    private void i0() {
        ViewTreeLifecycleOwner.a(getWindow().getDecorView(), this);
        ViewTreeViewModelStoreOwner.a(getWindow().getDecorView(), this);
        ViewTreeSavedStateRegistryOwner.a(getWindow().getDecorView(), this);
        ViewTreeOnBackPressedDispatcherOwner.a(getWindow().getDecorView(), this);
    }

    private boolean p0(KeyEvent keyEvent) {
        return false;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        i0();
        f0().e(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(f0().g(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        ActionBar g0 = g0();
        if (getWindow().hasFeature(0)) {
            if (g0 == null || !g0.a()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        ActionBar g0 = g0();
        if (keyCode == 82 && g0 != null && g0.j(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public AppCompatDelegate f0() {
        if (this.F == null) {
            this.F = AppCompatDelegate.h(this, this);
        }
        return this.F;
    }

    @Override // android.app.Activity
    public View findViewById(int i2) {
        return f0().j(i2);
    }

    @Override // androidx.core.app.TaskStackBuilder.SupportParentable
    public Intent g() {
        return NavUtils.a(this);
    }

    public ActionBar g0() {
        return f0().q();
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return f0().p();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.G == null && VectorEnabledTintResources.c()) {
            this.G = new VectorEnabledTintResources(this, super.getResources());
        }
        Resources resources = this.G;
        return resources == null ? super.getResources() : resources;
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        f0().s();
    }

    public void j0(TaskStackBuilder taskStackBuilder) {
        taskStackBuilder.d(this);
    }

    protected void k0(LocaleListCompat localeListCompat) {
    }

    protected void l0(int i2) {
    }

    public void m0(TaskStackBuilder taskStackBuilder) {
    }

    public void n0() {
    }

    public boolean o0() {
        Intent g2 = g();
        if (g2 == null) {
            return false;
        }
        if (!s0(g2)) {
            r0(g2);
            return true;
        }
        TaskStackBuilder g3 = TaskStackBuilder.g(this);
        j0(g3);
        m0(g3);
        g3.h();
        try {
            ActivityCompat.n(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        f0().v(configuration);
        if (this.G != null) {
            this.G.updateConfiguration(super.getResources().getConfiguration(), super.getResources().getDisplayMetrics());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        n0();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        f0().x();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (p0(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        ActionBar g0 = g0();
        if (menuItem.getItemId() != 16908332 || g0 == null || (g0.d() & 4) == 0) {
            return false;
        }
        return o0();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i2, Menu menu) {
        return super.onMenuOpened(i2, menu);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i2, Menu menu) {
        super.onPanelClosed(i2, menu);
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        f0().y(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        f0().z();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        f0().B();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        f0().C();
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i2) {
        super.onTitleChanged(charSequence, i2);
        f0().M(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        ActionBar g0 = g0();
        if (getWindow().hasFeature(0)) {
            if (g0 == null || !g0.k()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public void p(ActionMode actionMode) {
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public void q(ActionMode actionMode) {
    }

    public void q0(Toolbar toolbar) {
        f0().K(toolbar);
    }

    public void r0(Intent intent) {
        NavUtils.e(this, intent);
    }

    public boolean s0(Intent intent) {
        return NavUtils.f(this, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i2) {
        i0();
        f0().G(i2);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        super.setTheme(i2);
        f0().L(i2);
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public ActionMode u(ActionMode.Callback callback) {
        return null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        i0();
        f0().H(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        i0();
        f0().I(view, layoutParams);
    }
}
