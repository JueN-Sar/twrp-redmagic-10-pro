package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.ShowableListMenu;

@RestrictTo
/* loaded from: classes.dex */
public abstract class ForwardingListener implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* renamed from: c, reason: collision with root package name */
    private final float f886c;

    /* renamed from: h, reason: collision with root package name */
    private final int f887h;

    /* renamed from: i, reason: collision with root package name */
    private final int f888i;

    /* renamed from: j, reason: collision with root package name */
    final View f889j;

    /* renamed from: k, reason: collision with root package name */
    private Runnable f890k;

    /* renamed from: l, reason: collision with root package name */
    private Runnable f891l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f892m;

    /* renamed from: n, reason: collision with root package name */
    private int f893n;

    /* renamed from: o, reason: collision with root package name */
    private final int[] f894o = new int[2];

    private class DisallowIntercept implements Runnable {
        DisallowIntercept() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = ForwardingListener.this.f889j.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    private class TriggerLongPress implements Runnable {
        TriggerLongPress() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ForwardingListener.this.e();
        }
    }

    public ForwardingListener(View view) {
        this.f889j = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f886c = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f887h = tapTimeout;
        this.f888i = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    private void a() {
        Runnable runnable = this.f891l;
        if (runnable != null) {
            this.f889j.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f890k;
        if (runnable2 != null) {
            this.f889j.removeCallbacks(runnable2);
        }
    }

    private boolean f(MotionEvent motionEvent) {
        DropDownListView dropDownListView;
        View view = this.f889j;
        ShowableListMenu b2 = b();
        if (b2 == null || !b2.isShowing() || (dropDownListView = (DropDownListView) b2.getListView()) == null || !dropDownListView.isShown()) {
            return false;
        }
        MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        i(view, obtainNoHistory);
        j(dropDownListView, obtainNoHistory);
        boolean e2 = dropDownListView.e(obtainNoHistory, this.f893n);
        obtainNoHistory.recycle();
        int actionMasked = motionEvent.getActionMasked();
        return e2 && (actionMasked != 1 && actionMasked != 3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
    
        if (r1 != 3) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean g(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.f889j
            boolean r1 = r0.isEnabled()
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            int r1 = r6.getActionMasked()
            if (r1 == 0) goto L41
            r3 = 1
            if (r1 == r3) goto L3d
            r4 = 2
            if (r1 == r4) goto L1a
            r6 = 3
            if (r1 == r6) goto L3d
            goto L6d
        L1a:
            int r1 = r5.f893n
            int r1 = r6.findPointerIndex(r1)
            if (r1 < 0) goto L6d
            float r4 = r6.getX(r1)
            float r6 = r6.getY(r1)
            float r1 = r5.f886c
            boolean r6 = h(r0, r4, r6, r1)
            if (r6 != 0) goto L6d
            r5.a()
            android.view.ViewParent r5 = r0.getParent()
            r5.requestDisallowInterceptTouchEvent(r3)
            return r3
        L3d:
            r5.a()
            goto L6d
        L41:
            int r6 = r6.getPointerId(r2)
            r5.f893n = r6
            java.lang.Runnable r6 = r5.f890k
            if (r6 != 0) goto L52
            androidx.appcompat.widget.ForwardingListener$DisallowIntercept r6 = new androidx.appcompat.widget.ForwardingListener$DisallowIntercept
            r6.<init>()
            r5.f890k = r6
        L52:
            java.lang.Runnable r6 = r5.f890k
            int r1 = r5.f887h
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
            java.lang.Runnable r6 = r5.f891l
            if (r6 != 0) goto L65
            androidx.appcompat.widget.ForwardingListener$TriggerLongPress r6 = new androidx.appcompat.widget.ForwardingListener$TriggerLongPress
            r6.<init>()
            r5.f891l = r6
        L65:
            java.lang.Runnable r6 = r5.f891l
            int r5 = r5.f888i
            long r3 = (long) r5
            r0.postDelayed(r6, r3)
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ForwardingListener.g(android.view.MotionEvent):boolean");
    }

    private static boolean h(View view, float f2, float f3, float f4) {
        float f5 = -f4;
        return f2 >= f5 && f3 >= f5 && f2 < ((float) (view.getRight() - view.getLeft())) + f4 && f3 < ((float) (view.getBottom() - view.getTop())) + f4;
    }

    private boolean i(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f894o);
        motionEvent.offsetLocation(r1[0], r1[1]);
        return true;
    }

    private boolean j(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f894o);
        motionEvent.offsetLocation(-r1[0], -r1[1]);
        return true;
    }

    public abstract ShowableListMenu b();

    protected boolean c() {
        ShowableListMenu b2 = b();
        if (b2 == null || b2.isShowing()) {
            return true;
        }
        b2.show();
        return true;
    }

    protected boolean d() {
        ShowableListMenu b2 = b();
        if (b2 == null || !b2.isShowing()) {
            return true;
        }
        b2.dismiss();
        return true;
    }

    void e() {
        a();
        View view = this.f889j;
        if (view.isEnabled() && !view.isLongClickable() && c()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(obtain);
            obtain.recycle();
            this.f892m = true;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        boolean z2 = this.f892m;
        if (z2) {
            z = f(motionEvent) || !d();
        } else {
            z = g(motionEvent) && c();
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.f889j.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        this.f892m = z;
        return z || z2;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.f892m = false;
        this.f893n = -1;
        Runnable runnable = this.f890k;
        if (runnable != null) {
            this.f889j.removeCallbacks(runnable);
        }
    }
}
