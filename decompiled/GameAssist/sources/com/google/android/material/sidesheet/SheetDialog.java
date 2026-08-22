package com.google.android.material.sidesheet;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.sidesheet.SheetCallback;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
abstract class SheetDialog<C extends SheetCallback> extends AppCompatDialog {
    private static final int t = R.id.coordinator;
    private static final int u = R.id.touch_outside;

    /* renamed from: l, reason: collision with root package name */
    private Sheet f15231l;

    /* renamed from: m, reason: collision with root package name */
    private FrameLayout f15232m;

    /* renamed from: n, reason: collision with root package name */
    private FrameLayout f15233n;

    /* renamed from: o, reason: collision with root package name */
    boolean f15234o;

    /* renamed from: p, reason: collision with root package name */
    boolean f15235p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f15236q;

    /* renamed from: r, reason: collision with root package name */
    private boolean f15237r;

    /* renamed from: s, reason: collision with root package name */
    private MaterialBackOrchestrator f15238s;

    private void A() {
        FrameLayout frameLayout;
        Window window = getWindow();
        if (window == null || (frameLayout = this.f15233n) == null || !(frameLayout.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            return;
        }
        window.setWindowAnimations(GravityCompat.b(((CoordinatorLayout.LayoutParams) this.f15233n.getLayoutParams()).f2582c, ViewCompat.v(this.f15233n)) == 3 ? R.style.Animation_Material3_SideSheetDialog_Left : R.style.Animation_Material3_SideSheetDialog_Right);
    }

    private boolean B() {
        if (!this.f15237r) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{android.R.attr.windowCloseOnTouchOutside});
            this.f15236q = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
            this.f15237r = true;
        }
        return this.f15236q;
    }

    private void C() {
        MaterialBackOrchestrator materialBackOrchestrator = this.f15238s;
        if (materialBackOrchestrator == null) {
            return;
        }
        if (this.f15235p) {
            materialBackOrchestrator.c();
        } else {
            materialBackOrchestrator.f();
        }
    }

    private View D(int i2, View view, ViewGroup.LayoutParams layoutParams) {
        o();
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) t().findViewById(t);
        if (i2 != 0 && view == null) {
            view = getLayoutInflater().inflate(i2, (ViewGroup) coordinatorLayout, false);
        }
        FrameLayout x = x();
        x.removeAllViews();
        if (layoutParams == null) {
            x.addView(view);
        } else {
            x.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(u).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.sidesheet.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SheetDialog.this.z(view2);
            }
        });
        ViewCompat.i0(x(), new AccessibilityDelegateCompat() { // from class: com.google.android.material.sidesheet.SheetDialog.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void g(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view2, accessibilityNodeInfoCompat);
                if (!SheetDialog.this.f15235p) {
                    accessibilityNodeInfoCompat.m0(false);
                } else {
                    accessibilityNodeInfoCompat.a(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY);
                    accessibilityNodeInfoCompat.m0(true);
                }
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean j(View view2, int i3, Bundle bundle) {
                if (i3 == 1048576) {
                    SheetDialog sheetDialog = SheetDialog.this;
                    if (sheetDialog.f15235p) {
                        sheetDialog.cancel();
                        return true;
                    }
                }
                return super.j(view2, i3, bundle);
            }
        });
        return this.f15232m;
    }

    private void o() {
        if (this.f15232m == null) {
            FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), w(), null);
            this.f15232m = frameLayout;
            FrameLayout frameLayout2 = (FrameLayout) frameLayout.findViewById(v());
            this.f15233n = frameLayout2;
            Sheet s2 = s(frameLayout2);
            this.f15231l = s2;
            m(s2);
            this.f15238s = new MaterialBackOrchestrator(this.f15231l, this.f15233n);
        }
    }

    private FrameLayout t() {
        if (this.f15232m == null) {
            o();
        }
        return this.f15232m;
    }

    private FrameLayout x() {
        if (this.f15233n == null) {
            o();
        }
        return this.f15233n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(View view) {
        if (this.f15235p && isShowing() && B()) {
            cancel();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        Sheet r2 = r();
        if (!this.f15234o || r2.getState() == 5) {
            super.cancel();
        } else {
            r2.setState(5);
        }
    }

    abstract void m(Sheet sheet);

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        A();
        C();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setStatusBarColor(0);
            window.addFlags(Integer.MIN_VALUE);
            window.setLayout(-1, -1);
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MaterialBackOrchestrator materialBackOrchestrator = this.f15238s;
        if (materialBackOrchestrator != null) {
            materialBackOrchestrator.f();
        }
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    protected void onStart() {
        super.onStart();
        Sheet sheet = this.f15231l;
        if (sheet == null || sheet.getState() != 5) {
            return;
        }
        this.f15231l.setState(y());
    }

    Sheet r() {
        if (this.f15231l == null) {
            o();
        }
        return this.f15231l;
    }

    abstract Sheet s(FrameLayout frameLayout);

    @Override // android.app.Dialog
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.f15235p != z) {
            this.f15235p = z;
        }
        if (getWindow() != null) {
            C();
        }
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.f15235p) {
            this.f15235p = true;
        }
        this.f15236q = z;
        this.f15237r = true;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(int i2) {
        super.setContentView(D(i2, null, null));
    }

    abstract int v();

    abstract int w();

    abstract int y();

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View view) {
        super.setContentView(D(0, view, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(D(0, view, layoutParams));
    }
}
