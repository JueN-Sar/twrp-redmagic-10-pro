package com.google.android.material.bottomsheet;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class BottomSheetDialog extends AppCompatDialog {

    /* renamed from: l, reason: collision with root package name */
    private BottomSheetBehavior f14050l;

    /* renamed from: m, reason: collision with root package name */
    private FrameLayout f14051m;

    /* renamed from: n, reason: collision with root package name */
    private CoordinatorLayout f14052n;

    /* renamed from: o, reason: collision with root package name */
    private FrameLayout f14053o;

    /* renamed from: p, reason: collision with root package name */
    boolean f14054p;

    /* renamed from: q, reason: collision with root package name */
    boolean f14055q;

    /* renamed from: r, reason: collision with root package name */
    private boolean f14056r;

    /* renamed from: s, reason: collision with root package name */
    private boolean f14057s;
    private EdgeToEdgeCallback t;
    private boolean u;
    private MaterialBackOrchestrator v;
    private BottomSheetBehavior.BottomSheetCallback w;

    private static class EdgeToEdgeCallback extends BottomSheetBehavior.BottomSheetCallback {

        /* renamed from: a, reason: collision with root package name */
        private final Boolean f14063a;

        /* renamed from: b, reason: collision with root package name */
        private final WindowInsetsCompat f14064b;

        /* renamed from: c, reason: collision with root package name */
        private Window f14065c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f14066d;

        private void d(View view) {
            if (view.getTop() < this.f14064b.l()) {
                Window window = this.f14065c;
                if (window != null) {
                    Boolean bool = this.f14063a;
                    EdgeToEdgeUtils.f(window, bool == null ? this.f14066d : bool.booleanValue());
                }
                view.setPadding(view.getPaddingLeft(), this.f14064b.l() - view.getTop(), view.getPaddingRight(), view.getPaddingBottom());
                return;
            }
            if (view.getTop() != 0) {
                Window window2 = this.f14065c;
                if (window2 != null) {
                    EdgeToEdgeUtils.f(window2, this.f14066d);
                }
                view.setPadding(view.getPaddingLeft(), 0, view.getPaddingRight(), view.getPaddingBottom());
            }
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        void a(View view) {
            d(view);
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void b(View view, float f2) {
            d(view);
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void c(View view, int i2) {
            d(view);
        }

        void e(Window window) {
            if (this.f14065c == window) {
                return;
            }
            this.f14065c = window;
            if (window != null) {
                this.f14066d = WindowCompat.a(window, window.getDecorView()).b();
            }
        }

        private EdgeToEdgeCallback(View view, WindowInsetsCompat windowInsetsCompat) {
            this.f14064b = windowInsetsCompat;
            MaterialShapeDrawable t0 = BottomSheetBehavior.q0(view).t0();
            ColorStateList x = t0 != null ? t0.x() : ViewCompat.o(view);
            if (x != null) {
                this.f14063a = Boolean.valueOf(MaterialColors.i(x.getDefaultColor()));
                return;
            }
            Integer i2 = ViewUtils.i(view);
            if (i2 != null) {
                this.f14063a = Boolean.valueOf(MaterialColors.i(i2.intValue()));
            } else {
                this.f14063a = null;
            }
        }
    }

    public BottomSheetDialog(Context context, int i2) {
        super(context, g(context, i2));
        this.f14055q = true;
        this.f14056r = true;
        this.w = new BottomSheetBehavior.BottomSheetCallback() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.5
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void b(View view, float f2) {
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void c(View view, int i3) {
                if (i3 == 5) {
                    BottomSheetDialog.this.cancel();
                }
            }
        };
        k(1);
        this.u = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.enableEdgeToEdge}).getBoolean(0, false);
    }

    private static int g(Context context, int i2) {
        if (i2 != 0) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(R.attr.bottomSheetDialogTheme, typedValue, true) ? typedValue.resourceId : R.style.Theme_Design_Light_BottomSheetDialog;
    }

    private FrameLayout s() {
        if (this.f14051m == null) {
            FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), R.layout.design_bottom_sheet_dialog, null);
            this.f14051m = frameLayout;
            this.f14052n = (CoordinatorLayout) frameLayout.findViewById(R.id.coordinator);
            FrameLayout frameLayout2 = (FrameLayout) this.f14051m.findViewById(R.id.design_bottom_sheet);
            this.f14053o = frameLayout2;
            BottomSheetBehavior q0 = BottomSheetBehavior.q0(frameLayout2);
            this.f14050l = q0;
            q0.d0(this.w);
            this.f14050l.P0(this.f14055q);
            this.v = new MaterialBackOrchestrator(this.f14050l, this.f14053o);
        }
        return this.f14051m;
    }

    private void w() {
        MaterialBackOrchestrator materialBackOrchestrator = this.v;
        if (materialBackOrchestrator == null) {
            return;
        }
        if (this.f14055q) {
            materialBackOrchestrator.c();
        } else {
            materialBackOrchestrator.f();
        }
    }

    private View x(int i2, View view, ViewGroup.LayoutParams layoutParams) {
        s();
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.f14051m.findViewById(R.id.coordinator);
        if (i2 != 0 && view == null) {
            view = getLayoutInflater().inflate(i2, (ViewGroup) coordinatorLayout, false);
        }
        if (this.u) {
            ViewCompat.x0(this.f14053o, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.1
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public WindowInsetsCompat a(View view2, WindowInsetsCompat windowInsetsCompat) {
                    if (BottomSheetDialog.this.t != null) {
                        BottomSheetDialog.this.f14050l.E0(BottomSheetDialog.this.t);
                    }
                    if (windowInsetsCompat != null) {
                        BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                        bottomSheetDialog.t = new EdgeToEdgeCallback(bottomSheetDialog.f14053o, windowInsetsCompat);
                        BottomSheetDialog.this.t.e(BottomSheetDialog.this.getWindow());
                        BottomSheetDialog.this.f14050l.d0(BottomSheetDialog.this.t);
                    }
                    return windowInsetsCompat;
                }
            });
        }
        this.f14053o.removeAllViews();
        if (layoutParams == null) {
            this.f14053o.addView(view);
        } else {
            this.f14053o.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(R.id.touch_outside).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                if (bottomSheetDialog.f14055q && bottomSheetDialog.isShowing() && BottomSheetDialog.this.v()) {
                    BottomSheetDialog.this.cancel();
                }
            }
        });
        ViewCompat.i0(this.f14053o, new AccessibilityDelegateCompat() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.3
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void g(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view2, accessibilityNodeInfoCompat);
                if (!BottomSheetDialog.this.f14055q) {
                    accessibilityNodeInfoCompat.m0(false);
                } else {
                    accessibilityNodeInfoCompat.a(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY);
                    accessibilityNodeInfoCompat.m0(true);
                }
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean j(View view2, int i3, Bundle bundle) {
                if (i3 == 1048576) {
                    BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                    if (bottomSheetDialog.f14055q) {
                        bottomSheetDialog.cancel();
                        return true;
                    }
                }
                return super.j(view2, i3, bundle);
            }
        });
        this.f14053o.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                return true;
            }
        });
        return this.f14051m;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        BottomSheetBehavior t = t();
        if (!this.f14054p || t.getState() == 5) {
            super.cancel();
        } else {
            t.setState(5);
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        if (window != null) {
            boolean z = this.u && Color.alpha(window.getNavigationBarColor()) < 255;
            FrameLayout frameLayout = this.f14051m;
            if (frameLayout != null) {
                frameLayout.setFitsSystemWindows(!z);
            }
            CoordinatorLayout coordinatorLayout = this.f14052n;
            if (coordinatorLayout != null) {
                coordinatorLayout.setFitsSystemWindows(!z);
            }
            WindowCompat.b(window, !z);
            EdgeToEdgeCallback edgeToEdgeCallback = this.t;
            if (edgeToEdgeCallback != null) {
                edgeToEdgeCallback.e(window);
            }
        }
        w();
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
        EdgeToEdgeCallback edgeToEdgeCallback = this.t;
        if (edgeToEdgeCallback != null) {
            edgeToEdgeCallback.e(null);
        }
        MaterialBackOrchestrator materialBackOrchestrator = this.v;
        if (materialBackOrchestrator != null) {
            materialBackOrchestrator.f();
        }
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    protected void onStart() {
        super.onStart();
        BottomSheetBehavior bottomSheetBehavior = this.f14050l;
        if (bottomSheetBehavior == null || bottomSheetBehavior.getState() != 5) {
            return;
        }
        this.f14050l.setState(4);
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.f14055q != z) {
            this.f14055q = z;
            BottomSheetBehavior bottomSheetBehavior = this.f14050l;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.P0(z);
            }
            if (getWindow() != null) {
                w();
            }
        }
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.f14055q) {
            this.f14055q = true;
        }
        this.f14056r = z;
        this.f14057s = true;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(int i2) {
        super.setContentView(x(i2, null, null));
    }

    public BottomSheetBehavior t() {
        if (this.f14050l == null) {
            s();
        }
        return this.f14050l;
    }

    boolean v() {
        if (!this.f14057s) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{android.R.attr.windowCloseOnTouchOutside});
            this.f14056r = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
            this.f14057s = true;
        }
        return this.f14056r;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View view) {
        super.setContentView(x(0, view, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(x(0, view, layoutParams));
    }
}
