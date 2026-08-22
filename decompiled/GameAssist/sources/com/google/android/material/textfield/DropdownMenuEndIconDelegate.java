package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;

/* loaded from: classes.dex */
class DropdownMenuEndIconDelegate extends EndIconDelegate {

    /* renamed from: s, reason: collision with root package name */
    private static final boolean f15383s = true;

    /* renamed from: e, reason: collision with root package name */
    private final int f15384e;

    /* renamed from: f, reason: collision with root package name */
    private final int f15385f;

    /* renamed from: g, reason: collision with root package name */
    private final TimeInterpolator f15386g;

    /* renamed from: h, reason: collision with root package name */
    private AutoCompleteTextView f15387h;

    /* renamed from: i, reason: collision with root package name */
    private final View.OnClickListener f15388i;

    /* renamed from: j, reason: collision with root package name */
    private final View.OnFocusChangeListener f15389j;

    /* renamed from: k, reason: collision with root package name */
    private final AccessibilityManagerCompat.TouchExplorationStateChangeListener f15390k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f15391l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f15392m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f15393n;

    /* renamed from: o, reason: collision with root package name */
    private long f15394o;

    /* renamed from: p, reason: collision with root package name */
    private AccessibilityManager f15395p;

    /* renamed from: q, reason: collision with root package name */
    private ValueAnimator f15396q;

    /* renamed from: r, reason: collision with root package name */
    private ValueAnimator f15397r;

    DropdownMenuEndIconDelegate(EndCompoundLayout endCompoundLayout) {
        super(endCompoundLayout);
        this.f15388i = new View.OnClickListener() { // from class: com.google.android.material.textfield.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DropdownMenuEndIconDelegate.this.J(view);
            }
        };
        this.f15389j = new View.OnFocusChangeListener() { // from class: com.google.android.material.textfield.j
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                DropdownMenuEndIconDelegate.this.K(view, z);
            }
        };
        this.f15390k = new AccessibilityManagerCompat.TouchExplorationStateChangeListener() { // from class: com.google.android.material.textfield.k
            @Override // androidx.core.view.accessibility.AccessibilityManagerCompat.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z) {
                DropdownMenuEndIconDelegate.this.L(z);
            }
        };
        this.f15394o = Long.MAX_VALUE;
        this.f15385f = MotionUtils.f(endCompoundLayout.getContext(), R.attr.motionDurationShort3, 67);
        this.f15384e = MotionUtils.f(endCompoundLayout.getContext(), R.attr.motionDurationShort3, 50);
        this.f15386g = MotionUtils.g(endCompoundLayout.getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.f13814a);
    }

    private static AutoCompleteTextView D(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    private ValueAnimator E(int i2, float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(this.f15386g);
        ofFloat.setDuration(i2);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.textfield.f
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                DropdownMenuEndIconDelegate.this.I(valueAnimator);
            }
        });
        return ofFloat;
    }

    private void F() {
        this.f15397r = E(this.f15385f, 0.0f, 1.0f);
        ValueAnimator E = E(this.f15384e, 1.0f, 0.0f);
        this.f15396q = E;
        E.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.DropdownMenuEndIconDelegate.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                DropdownMenuEndIconDelegate.this.r();
                DropdownMenuEndIconDelegate.this.f15397r.start();
            }
        });
    }

    private boolean G() {
        long currentTimeMillis = System.currentTimeMillis() - this.f15394o;
        return currentTimeMillis < 0 || currentTimeMillis > 300;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H() {
        boolean isPopupShowing = this.f15387h.isPopupShowing();
        O(isPopupShowing);
        this.f15392m = isPopupShowing;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I(ValueAnimator valueAnimator) {
        this.f15409d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J(View view) {
        Q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K(View view, boolean z) {
        this.f15391l = z;
        r();
        if (z) {
            return;
        }
        O(false);
        this.f15392m = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L(boolean z) {
        AutoCompleteTextView autoCompleteTextView = this.f15387h;
        if (autoCompleteTextView == null || EditTextUtils.a(autoCompleteTextView)) {
            return;
        }
        ViewCompat.s0(this.f15409d, z ? 2 : 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean M(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (G()) {
                this.f15392m = false;
            }
            Q();
            R();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N() {
        R();
        O(false);
    }

    private void O(boolean z) {
        if (this.f15393n != z) {
            this.f15393n = z;
            this.f15397r.cancel();
            this.f15396q.start();
        }
    }

    private void P() {
        this.f15387h.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.material.textfield.g
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean M;
                M = DropdownMenuEndIconDelegate.this.M(view, motionEvent);
                return M;
            }
        });
        if (f15383s) {
            this.f15387h.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() { // from class: com.google.android.material.textfield.h
                @Override // android.widget.AutoCompleteTextView.OnDismissListener
                public final void onDismiss() {
                    DropdownMenuEndIconDelegate.this.N();
                }
            });
        }
        this.f15387h.setThreshold(0);
    }

    private void Q() {
        if (this.f15387h == null) {
            return;
        }
        if (G()) {
            this.f15392m = false;
        }
        if (this.f15392m) {
            this.f15392m = false;
            return;
        }
        if (f15383s) {
            O(!this.f15393n);
        } else {
            this.f15393n = !this.f15393n;
            r();
        }
        if (!this.f15393n) {
            this.f15387h.dismissDropDown();
        } else {
            this.f15387h.requestFocus();
            this.f15387h.showDropDown();
        }
    }

    private void R() {
        this.f15392m = true;
        this.f15394o = System.currentTimeMillis();
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void a(Editable editable) {
        if (this.f15395p.isTouchExplorationEnabled() && EditTextUtils.a(this.f15387h) && !this.f15409d.hasFocus()) {
            this.f15387h.dismissDropDown();
        }
        this.f15387h.post(new Runnable() { // from class: com.google.android.material.textfield.l
            @Override // java.lang.Runnable
            public final void run() {
                DropdownMenuEndIconDelegate.this.H();
            }
        });
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    int c() {
        return R.string.exposed_dropdown_menu_content_description;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    int d() {
        return f15383s ? R.drawable.mtrl_dropdown_arrow : R.drawable.mtrl_ic_arrow_drop_down;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    View.OnFocusChangeListener e() {
        return this.f15389j;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    View.OnClickListener f() {
        return this.f15388i;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public AccessibilityManagerCompat.TouchExplorationStateChangeListener h() {
        return this.f15390k;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    boolean i(int i2) {
        return i2 != 0;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    boolean j() {
        return true;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    boolean k() {
        return this.f15391l;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    boolean l() {
        return true;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    boolean m() {
        return this.f15393n;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void n(EditText editText) {
        this.f15387h = D(editText);
        P();
        this.f15406a.setErrorIconDrawable((Drawable) null);
        if (!EditTextUtils.a(editText) && this.f15395p.isTouchExplorationEnabled()) {
            ViewCompat.s0(this.f15409d, 2);
        }
        this.f15406a.setEndIconVisible(true);
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void o(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (!EditTextUtils.a(this.f15387h)) {
            accessibilityNodeInfoCompat.h0(Spinner.class.getName());
        }
        if (accessibilityNodeInfoCompat.S()) {
            accessibilityNodeInfoCompat.s0(null);
        }
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void p(View view, AccessibilityEvent accessibilityEvent) {
        if (!this.f15395p.isEnabled() || EditTextUtils.a(this.f15387h)) {
            return;
        }
        boolean z = (accessibilityEvent.getEventType() == 32768 || accessibilityEvent.getEventType() == 8) && this.f15393n && !this.f15387h.isPopupShowing();
        if (accessibilityEvent.getEventType() == 1 || z) {
            Q();
            R();
        }
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void s() {
        F();
        this.f15395p = (AccessibilityManager) this.f15408c.getSystemService("accessibility");
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    boolean t() {
        return true;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void u() {
        AutoCompleteTextView autoCompleteTextView = this.f15387h;
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setOnTouchListener(null);
            if (f15383s) {
                this.f15387h.setOnDismissListener(null);
            }
        }
    }
}
