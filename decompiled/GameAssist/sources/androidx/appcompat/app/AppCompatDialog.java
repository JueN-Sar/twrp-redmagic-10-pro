package androidx.appcompat.app;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.ComponentDialog;
import androidx.activity.ViewTreeOnBackPressedDispatcherOwner;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionMode;
import androidx.core.view.KeyEventDispatcher;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;

/* loaded from: classes.dex */
public class AppCompatDialog extends ComponentDialog implements AppCompatCallback {

    /* renamed from: j, reason: collision with root package name */
    private AppCompatDelegate f286j;

    /* renamed from: k, reason: collision with root package name */
    private final KeyEventDispatcher.Component f287k;

    public AppCompatDialog(Context context, int i2) {
        super(context, g(context, i2));
        this.f287k = new KeyEventDispatcher.Component() { // from class: androidx.appcompat.app.d
            @Override // androidx.core.view.KeyEventDispatcher.Component
            public final boolean j(KeyEvent keyEvent) {
                return AppCompatDialog.this.j(keyEvent);
            }
        };
        AppCompatDelegate f2 = f();
        f2.L(g(context, i2));
        f2.w(null);
    }

    private static int g(Context context, int i2) {
        if (i2 != 0) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    private void h() {
        ViewTreeLifecycleOwner.a(getWindow().getDecorView(), this);
        ViewTreeSavedStateRegistryOwner.a(getWindow().getDecorView(), this);
        ViewTreeOnBackPressedDispatcherOwner.a(getWindow().getDecorView(), this);
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        f().e(view, layoutParams);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        f().x();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return KeyEventDispatcher.b(this.f287k, getWindow().getDecorView(), this, keyEvent);
    }

    public AppCompatDelegate f() {
        if (this.f286j == null) {
            this.f286j = AppCompatDelegate.i(this, this);
        }
        return this.f286j;
    }

    @Override // android.app.Dialog
    public View findViewById(int i2) {
        return f().j(i2);
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        f().s();
    }

    boolean j(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean k(int i2) {
        return f().F(i2);
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        f().r();
        super.onCreate(bundle);
        f().w(bundle);
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    protected void onStop() {
        super.onStop();
        f().C();
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public void p(ActionMode actionMode) {
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public void q(ActionMode actionMode) {
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(int i2) {
        h();
        f().G(i2);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        f().M(charSequence);
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public ActionMode u(ActionMode.Callback callback) {
        return null;
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View view) {
        h();
        f().H(view);
    }

    @Override // android.app.Dialog
    public void setTitle(int i2) {
        super.setTitle(i2);
        f().M(getContext().getString(i2));
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        h();
        f().I(view, layoutParams);
    }
}
