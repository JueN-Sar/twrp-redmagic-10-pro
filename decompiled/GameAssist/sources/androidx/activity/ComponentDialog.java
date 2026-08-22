package androidx.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public class ComponentDialog extends Dialog implements LifecycleOwner, OnBackPressedDispatcherOwner, SavedStateRegistryOwner {

    /* renamed from: c, reason: collision with root package name */
    private LifecycleRegistry f37c;

    /* renamed from: h, reason: collision with root package name */
    private final SavedStateRegistryController f38h;

    /* renamed from: i, reason: collision with root package name */
    private final OnBackPressedDispatcher f39i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComponentDialog(Context context, int i2) {
        super(context, i2);
        Intrinsics.e(context, "context");
        this.f38h = SavedStateRegistryController.f5343d.a(this);
        this.f39i = new OnBackPressedDispatcher(new Runnable() { // from class: androidx.activity.f
            @Override // java.lang.Runnable
            public final void run() {
                ComponentDialog.e(ComponentDialog.this);
            }
        });
    }

    private final LifecycleRegistry c() {
        LifecycleRegistry lifecycleRegistry = this.f37c;
        if (lifecycleRegistry != null) {
            return lifecycleRegistry;
        }
        LifecycleRegistry lifecycleRegistry2 = new LifecycleRegistry(this);
        this.f37c = lifecycleRegistry2;
        return lifecycleRegistry2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void e(ComponentDialog this$0) {
        Intrinsics.e(this$0, "this$0");
        super.onBackPressed();
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle a() {
        return c();
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        Intrinsics.e(view, "view");
        d();
        super.addContentView(view, layoutParams);
    }

    public void d() {
        Window window = getWindow();
        Intrinsics.b(window);
        View decorView = window.getDecorView();
        Intrinsics.d(decorView, "window!!.decorView");
        ViewTreeLifecycleOwner.a(decorView, this);
        Window window2 = getWindow();
        Intrinsics.b(window2);
        View decorView2 = window2.getDecorView();
        Intrinsics.d(decorView2, "window!!.decorView");
        ViewTreeOnBackPressedDispatcherOwner.a(decorView2, this);
        Window window3 = getWindow();
        Intrinsics.b(window3);
        View decorView3 = window3.getDecorView();
        Intrinsics.d(decorView3, "window!!.decorView");
        ViewTreeSavedStateRegistryOwner.a(decorView3, this);
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public SavedStateRegistry i() {
        return this.f38h.b();
    }

    @Override // androidx.activity.OnBackPressedDispatcherOwner
    public final OnBackPressedDispatcher n() {
        return this.f39i;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        this.f39i.k();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        OnBackPressedDispatcher onBackPressedDispatcher = this.f39i;
        OnBackInvokedDispatcher onBackInvokedDispatcher = getOnBackInvokedDispatcher();
        Intrinsics.d(onBackInvokedDispatcher, "onBackInvokedDispatcher");
        onBackPressedDispatcher.n(onBackInvokedDispatcher);
        this.f38h.d(bundle);
        c().h(Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        Intrinsics.d(onSaveInstanceState, "super.onSaveInstanceState()");
        this.f38h.e(onSaveInstanceState);
        return onSaveInstanceState;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        c().h(Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        c().h(Lifecycle.Event.ON_DESTROY);
        this.f37c = null;
        super.onStop();
    }

    @Override // android.app.Dialog
    public void setContentView(int i2) {
        d();
        super.setContentView(i2);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        Intrinsics.e(view, "view");
        d();
        super.setContentView(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        Intrinsics.e(view, "view");
        d();
        super.setContentView(view, layoutParams);
    }
}
