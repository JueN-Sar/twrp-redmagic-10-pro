package androidx.appcompat.widget;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;

@RestrictTo
/* loaded from: classes.dex */
class TooltipCompatHandler implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {

    /* renamed from: q, reason: collision with root package name */
    private static TooltipCompatHandler f1074q;

    /* renamed from: r, reason: collision with root package name */
    private static TooltipCompatHandler f1075r;

    /* renamed from: c, reason: collision with root package name */
    private final View f1076c;

    /* renamed from: h, reason: collision with root package name */
    private final CharSequence f1077h;

    /* renamed from: i, reason: collision with root package name */
    private final int f1078i;

    /* renamed from: j, reason: collision with root package name */
    private final Runnable f1079j;

    /* renamed from: k, reason: collision with root package name */
    private final Runnable f1080k;

    /* renamed from: l, reason: collision with root package name */
    private int f1081l;

    /* renamed from: m, reason: collision with root package name */
    private int f1082m;

    /* renamed from: n, reason: collision with root package name */
    private TooltipPopup f1083n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f1084o;

    /* renamed from: p, reason: collision with root package name */
    private boolean f1085p;

    private void a() {
        this.f1076c.removeCallbacks(this.f1079j);
    }

    private void b() {
        this.f1085p = true;
    }

    private void d() {
        this.f1076c.postDelayed(this.f1079j, ViewConfiguration.getLongPressTimeout());
    }

    private static void e(TooltipCompatHandler tooltipCompatHandler) {
        TooltipCompatHandler tooltipCompatHandler2 = f1074q;
        if (tooltipCompatHandler2 != null) {
            tooltipCompatHandler2.a();
        }
        f1074q = tooltipCompatHandler;
        if (tooltipCompatHandler != null) {
            tooltipCompatHandler.d();
        }
    }

    private boolean g(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (!this.f1085p && Math.abs(x - this.f1081l) <= this.f1078i && Math.abs(y - this.f1082m) <= this.f1078i) {
            return false;
        }
        this.f1081l = x;
        this.f1082m = y;
        this.f1085p = false;
        return true;
    }

    void c() {
        if (f1075r == this) {
            f1075r = null;
            TooltipPopup tooltipPopup = this.f1083n;
            if (tooltipPopup != null) {
                tooltipPopup.c();
                this.f1083n = null;
                b();
                this.f1076c.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f1074q == this) {
            e(null);
        }
        this.f1076c.removeCallbacks(this.f1080k);
    }

    void f(boolean z) {
        long longPressTimeout;
        long j2;
        long j3;
        if (this.f1076c.isAttachedToWindow()) {
            e(null);
            TooltipCompatHandler tooltipCompatHandler = f1075r;
            if (tooltipCompatHandler != null) {
                tooltipCompatHandler.c();
            }
            f1075r = this;
            this.f1084o = z;
            TooltipPopup tooltipPopup = new TooltipPopup(this.f1076c.getContext());
            this.f1083n = tooltipPopup;
            tooltipPopup.e(this.f1076c, this.f1081l, this.f1082m, this.f1084o, this.f1077h);
            this.f1076c.addOnAttachStateChangeListener(this);
            if (this.f1084o) {
                j3 = 2500;
            } else {
                if ((ViewCompat.G(this.f1076c) & 1) == 1) {
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    j2 = 3000;
                } else {
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    j2 = 15000;
                }
                j3 = j2 - longPressTimeout;
            }
            this.f1076c.removeCallbacks(this.f1080k);
            this.f1076c.postDelayed(this.f1080k, j3);
        }
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.f1083n != null && this.f1084o) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f1076c.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.f1076c.isEnabled() && this.f1083n == null && g(motionEvent)) {
            e(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.f1081l = view.getWidth() / 2;
        this.f1082m = view.getHeight() / 2;
        f(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        c();
    }
}
