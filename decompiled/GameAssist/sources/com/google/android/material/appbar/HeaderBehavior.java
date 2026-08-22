package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {

    /* renamed from: j, reason: collision with root package name */
    private Runnable f13876j;

    /* renamed from: k, reason: collision with root package name */
    OverScroller f13877k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f13878l;

    /* renamed from: m, reason: collision with root package name */
    private int f13879m;

    /* renamed from: n, reason: collision with root package name */
    private int f13880n;

    /* renamed from: o, reason: collision with root package name */
    private int f13881o;

    /* renamed from: p, reason: collision with root package name */
    private VelocityTracker f13882p;

    private class FlingRunnable implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private final CoordinatorLayout f13883c;

        /* renamed from: h, reason: collision with root package name */
        private final View f13884h;

        FlingRunnable(CoordinatorLayout coordinatorLayout, View view) {
            this.f13883c = coordinatorLayout;
            this.f13884h = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.f13884h == null || (overScroller = HeaderBehavior.this.f13877k) == null) {
                return;
            }
            if (!overScroller.computeScrollOffset()) {
                HeaderBehavior.this.S(this.f13883c, this.f13884h);
                return;
            }
            HeaderBehavior headerBehavior = HeaderBehavior.this;
            headerBehavior.U(this.f13883c, this.f13884h, headerBehavior.f13877k.getCurrY());
            ViewCompat.a0(this.f13884h, this);
        }
    }

    public HeaderBehavior() {
        this.f13879m = -1;
        this.f13881o = -1;
    }

    private void N() {
        if (this.f13882p == null) {
            this.f13882p = VelocityTracker.obtain();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007b  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean I(androidx.coordinatorlayout.widget.CoordinatorLayout r12, android.view.View r13, android.view.MotionEvent r14) {
        /*
            r11 = this;
            int r0 = r14.getActionMasked()
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 == r2) goto L4e
            r4 = 2
            if (r0 == r4) goto L2d
            r12 = 3
            if (r0 == r12) goto L72
            r12 = 6
            if (r0 == r12) goto L13
            goto L4c
        L13:
            int r12 = r14.getActionIndex()
            if (r12 != 0) goto L1b
            r12 = r2
            goto L1c
        L1b:
            r12 = r3
        L1c:
            int r13 = r14.getPointerId(r12)
            r11.f13879m = r13
            float r12 = r14.getY(r12)
            r13 = 1056964608(0x3f000000, float:0.5)
            float r12 = r12 + r13
            int r12 = (int) r12
            r11.f13880n = r12
            goto L4c
        L2d:
            int r0 = r11.f13879m
            int r0 = r14.findPointerIndex(r0)
            if (r0 != r1) goto L36
            return r3
        L36:
            float r0 = r14.getY(r0)
            int r0 = (int) r0
            int r1 = r11.f13880n
            int r7 = r1 - r0
            r11.f13880n = r0
            int r8 = r11.P(r13)
            r9 = 0
            r4 = r11
            r5 = r12
            r6 = r13
            r4.T(r5, r6, r7, r8, r9)
        L4c:
            r12 = r3
            goto L81
        L4e:
            android.view.VelocityTracker r0 = r11.f13882p
            if (r0 == 0) goto L72
            r0.addMovement(r14)
            android.view.VelocityTracker r0 = r11.f13882p
            r4 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r4)
            android.view.VelocityTracker r0 = r11.f13882p
            int r4 = r11.f13879m
            float r10 = r0.getYVelocity(r4)
            int r0 = r11.Q(r13)
            int r8 = -r0
            r9 = 0
            r5 = r11
            r6 = r12
            r7 = r13
            r5.O(r6, r7, r8, r9, r10)
            r12 = r2
            goto L73
        L72:
            r12 = r3
        L73:
            r11.f13878l = r3
            r11.f13879m = r1
            android.view.VelocityTracker r13 = r11.f13882p
            if (r13 == 0) goto L81
            r13.recycle()
            r13 = 0
            r11.f13882p = r13
        L81:
            android.view.VelocityTracker r13 = r11.f13882p
            if (r13 == 0) goto L88
            r13.addMovement(r14)
        L88:
            boolean r11 = r11.f13878l
            if (r11 != 0) goto L90
            if (r12 == 0) goto L8f
            goto L90
        L8f:
            r2 = r3
        L90:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.HeaderBehavior.I(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    boolean M(View view) {
        return false;
    }

    final boolean O(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, float f2) {
        Runnable runnable = this.f13876j;
        if (runnable != null) {
            view.removeCallbacks(runnable);
            this.f13876j = null;
        }
        if (this.f13877k == null) {
            this.f13877k = new OverScroller(view.getContext());
        }
        this.f13877k.fling(0, J(), 0, Math.round(f2), 0, 0, i2, i3);
        if (!this.f13877k.computeScrollOffset()) {
            S(coordinatorLayout, view);
            return false;
        }
        FlingRunnable flingRunnable = new FlingRunnable(coordinatorLayout, view);
        this.f13876j = flingRunnable;
        ViewCompat.a0(view, flingRunnable);
        return true;
    }

    int P(View view) {
        return -view.getHeight();
    }

    int Q(View view) {
        return view.getHeight();
    }

    int R() {
        return J();
    }

    void S(CoordinatorLayout coordinatorLayout, View view) {
    }

    final int T(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4) {
        return V(coordinatorLayout, view, R() - i2, i3, i4);
    }

    int U(CoordinatorLayout coordinatorLayout, View view, int i2) {
        return V(coordinatorLayout, view, i2, Integer.MIN_VALUE, Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    int V(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4) {
        int b2;
        int J = J();
        if (i3 == 0 || J < i3 || J > i4 || J == (b2 = MathUtils.b(i2, i3, i4))) {
            return 0;
        }
        L(b2);
        return J - b2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean p(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        int findPointerIndex;
        if (this.f13881o < 0) {
            this.f13881o = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.f13878l) {
            int i2 = this.f13879m;
            if (i2 == -1 || (findPointerIndex = motionEvent.findPointerIndex(i2)) == -1) {
                return false;
            }
            int y = (int) motionEvent.getY(findPointerIndex);
            if (Math.abs(y - this.f13880n) > this.f13881o) {
                this.f13880n = y;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f13879m = -1;
            int x = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            boolean z = M(view) && coordinatorLayout.F(view, x, y2);
            this.f13878l = z;
            if (z) {
                this.f13880n = y2;
                this.f13879m = motionEvent.getPointerId(0);
                N();
                OverScroller overScroller = this.f13877k;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.f13877k.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.f13882p;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    public HeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13879m = -1;
        this.f13881o = -1;
    }
}
