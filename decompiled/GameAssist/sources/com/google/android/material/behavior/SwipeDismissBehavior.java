package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.widget.ViewDragHelper;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    /* renamed from: c, reason: collision with root package name */
    ViewDragHelper f13968c;

    /* renamed from: h, reason: collision with root package name */
    OnDismissListener f13969h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f13970i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f13971j;

    /* renamed from: l, reason: collision with root package name */
    private boolean f13973l;

    /* renamed from: k, reason: collision with root package name */
    private float f13972k = 0.0f;

    /* renamed from: m, reason: collision with root package name */
    int f13974m = 2;

    /* renamed from: n, reason: collision with root package name */
    float f13975n = 0.5f;

    /* renamed from: o, reason: collision with root package name */
    float f13976o = 0.0f;

    /* renamed from: p, reason: collision with root package name */
    float f13977p = 0.5f;

    /* renamed from: q, reason: collision with root package name */
    private final ViewDragHelper.Callback f13978q = new ViewDragHelper.Callback() { // from class: com.google.android.material.behavior.SwipeDismissBehavior.1

        /* renamed from: a, reason: collision with root package name */
        private int f13979a;

        /* renamed from: b, reason: collision with root package name */
        private int f13980b = -1;

        private boolean n(View view, float f2) {
            if (f2 == 0.0f) {
                return Math.abs(view.getLeft() - this.f13979a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.f13975n);
            }
            boolean z = ViewCompat.v(view) == 1;
            int i2 = SwipeDismissBehavior.this.f13974m;
            if (i2 == 2) {
                return true;
            }
            if (i2 == 0) {
                if (z) {
                    if (f2 >= 0.0f) {
                        return false;
                    }
                } else if (f2 <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i2 != 1) {
                return false;
            }
            if (z) {
                if (f2 <= 0.0f) {
                    return false;
                }
            } else if (f2 >= 0.0f) {
                return false;
            }
            return true;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int a(View view, int i2, int i3) {
            int width;
            int width2;
            int width3;
            boolean z = ViewCompat.v(view) == 1;
            int i4 = SwipeDismissBehavior.this.f13974m;
            if (i4 == 0) {
                if (z) {
                    width = this.f13979a - view.getWidth();
                    width2 = this.f13979a;
                } else {
                    width = this.f13979a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i4 != 1) {
                width = this.f13979a - view.getWidth();
                width2 = this.f13979a + view.getWidth();
            } else if (z) {
                width = this.f13979a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.f13979a - view.getWidth();
                width2 = this.f13979a;
            }
            return SwipeDismissBehavior.M(width, i2, width2);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int b(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int d(View view) {
            return view.getWidth();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void i(View view, int i2) {
            this.f13980b = i2;
            this.f13979a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                SwipeDismissBehavior.this.f13971j = true;
                parent.requestDisallowInterceptTouchEvent(true);
                SwipeDismissBehavior.this.f13971j = false;
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void j(int i2) {
            OnDismissListener onDismissListener = SwipeDismissBehavior.this.f13969h;
            if (onDismissListener != null) {
                onDismissListener.b(i2);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void k(View view, int i2, int i3, int i4, int i5) {
            float width = view.getWidth() * SwipeDismissBehavior.this.f13976o;
            float width2 = view.getWidth() * SwipeDismissBehavior.this.f13977p;
            float abs = Math.abs(i2 - this.f13979a);
            if (abs <= width) {
                view.setAlpha(1.0f);
            } else if (abs >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.L(0.0f, 1.0f - SwipeDismissBehavior.O(width, width2, abs), 1.0f));
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void l(View view, float f2, float f3) {
            int i2;
            boolean z;
            OnDismissListener onDismissListener;
            this.f13980b = -1;
            int width = view.getWidth();
            if (n(view, f2)) {
                if (f2 >= 0.0f) {
                    int left = view.getLeft();
                    int i3 = this.f13979a;
                    if (left >= i3) {
                        i2 = i3 + width;
                        z = true;
                    }
                }
                i2 = this.f13979a - width;
                z = true;
            } else {
                i2 = this.f13979a;
                z = false;
            }
            if (SwipeDismissBehavior.this.f13968c.P(i2, view.getTop())) {
                ViewCompat.a0(view, new SettleRunnable(view, z));
            } else {
                if (!z || (onDismissListener = SwipeDismissBehavior.this.f13969h) == null) {
                    return;
                }
                onDismissListener.a(view);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean m(View view, int i2) {
            int i3 = this.f13980b;
            return (i3 == -1 || i3 == i2) && SwipeDismissBehavior.this.K(view);
        }
    };

    public interface OnDismissListener {
        void a(View view);

        void b(int i2);
    }

    private class SettleRunnable implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private final View f13983c;

        /* renamed from: h, reason: collision with root package name */
        private final boolean f13984h;

        SettleRunnable(View view, boolean z) {
            this.f13983c = view;
            this.f13984h = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            OnDismissListener onDismissListener;
            ViewDragHelper viewDragHelper = SwipeDismissBehavior.this.f13968c;
            if (viewDragHelper != null && viewDragHelper.n(true)) {
                ViewCompat.a0(this.f13983c, this);
            } else {
                if (!this.f13984h || (onDismissListener = SwipeDismissBehavior.this.f13969h) == null) {
                    return;
                }
                onDismissListener.a(this.f13983c);
            }
        }
    }

    static float L(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    static int M(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), i4);
    }

    private void N(ViewGroup viewGroup) {
        if (this.f13968c == null) {
            this.f13968c = this.f13973l ? ViewDragHelper.o(viewGroup, this.f13972k, this.f13978q) : ViewDragHelper.p(viewGroup, this.f13978q);
        }
    }

    static float O(float f2, float f3, float f4) {
        return (f4 - f2) / (f3 - f2);
    }

    private void T(View view) {
        ViewCompat.c0(view, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY);
        if (K(view)) {
            ViewCompat.e0(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.y, null, new AccessibilityViewCommand() { // from class: com.google.android.material.behavior.SwipeDismissBehavior.2
                @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                public boolean a(View view2, AccessibilityViewCommand.CommandArguments commandArguments) {
                    if (!SwipeDismissBehavior.this.K(view2)) {
                        return false;
                    }
                    boolean z = ViewCompat.v(view2) == 1;
                    int i2 = SwipeDismissBehavior.this.f13974m;
                    ViewCompat.S(view2, (!(i2 == 0 && z) && (i2 != 1 || z)) ? view2.getWidth() : -view2.getWidth());
                    view2.setAlpha(0.0f);
                    OnDismissListener onDismissListener = SwipeDismissBehavior.this.f13969h;
                    if (onDismissListener != null) {
                        onDismissListener.a(view2);
                    }
                    return true;
                }
            });
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean I(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (this.f13968c == null) {
            return false;
        }
        if (this.f13971j && motionEvent.getActionMasked() == 3) {
            return true;
        }
        this.f13968c.G(motionEvent);
        return true;
    }

    public boolean K(View view) {
        return true;
    }

    public void P(float f2) {
        this.f13977p = L(0.0f, f2, 1.0f);
    }

    public void Q(OnDismissListener onDismissListener) {
        this.f13969h = onDismissListener;
    }

    public void R(float f2) {
        this.f13976o = L(0.0f, f2, 1.0f);
    }

    public void S(int i2) {
        this.f13974m = i2;
    }

    @Nullable
    @VisibleForTesting
    public OnDismissListener getListener() {
        return this.f13969h;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean p(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z = this.f13970i;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z = coordinatorLayout.F(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f13970i = z;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f13970i = false;
        }
        if (!z) {
            return false;
        }
        N(coordinatorLayout);
        return !this.f13971j && this.f13968c.Q(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean q(CoordinatorLayout coordinatorLayout, View view, int i2) {
        boolean q2 = super.q(coordinatorLayout, view, i2);
        if (ViewCompat.t(view) == 0) {
            ViewCompat.s0(view, 1);
            T(view);
        }
        return q2;
    }
}
