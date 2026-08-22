package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;

/* loaded from: classes.dex */
class ClearTextEndIconDelegate extends EndIconDelegate {

    /* renamed from: e, reason: collision with root package name */
    private final int f15372e;

    /* renamed from: f, reason: collision with root package name */
    private final int f15373f;

    /* renamed from: g, reason: collision with root package name */
    private final TimeInterpolator f15374g;

    /* renamed from: h, reason: collision with root package name */
    private final TimeInterpolator f15375h;

    /* renamed from: i, reason: collision with root package name */
    private EditText f15376i;

    /* renamed from: j, reason: collision with root package name */
    private final View.OnClickListener f15377j;

    /* renamed from: k, reason: collision with root package name */
    private final View.OnFocusChangeListener f15378k;

    /* renamed from: l, reason: collision with root package name */
    private AnimatorSet f15379l;

    /* renamed from: m, reason: collision with root package name */
    private ValueAnimator f15380m;

    ClearTextEndIconDelegate(EndCompoundLayout endCompoundLayout) {
        super(endCompoundLayout);
        this.f15377j = new View.OnClickListener() { // from class: com.google.android.material.textfield.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ClearTextEndIconDelegate.this.G(view);
            }
        };
        this.f15378k = new View.OnFocusChangeListener() { // from class: com.google.android.material.textfield.b
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                ClearTextEndIconDelegate.this.H(view, z);
            }
        };
        this.f15372e = MotionUtils.f(endCompoundLayout.getContext(), R.attr.motionDurationShort3, 100);
        this.f15373f = MotionUtils.f(endCompoundLayout.getContext(), R.attr.motionDurationShort3, 150);
        this.f15374g = MotionUtils.g(endCompoundLayout.getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.f13814a);
        this.f15375h = MotionUtils.g(endCompoundLayout.getContext(), R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.f13817d);
    }

    private void A(boolean z) {
        boolean z2 = this.f15407b.F() == z;
        if (z && !this.f15379l.isRunning()) {
            this.f15380m.cancel();
            this.f15379l.start();
            if (z2) {
                this.f15379l.end();
                return;
            }
            return;
        }
        if (z) {
            return;
        }
        this.f15379l.cancel();
        this.f15380m.start();
        if (z2) {
            this.f15380m.end();
        }
    }

    private ValueAnimator B(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(this.f15374g);
        ofFloat.setDuration(this.f15372e);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.textfield.c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ClearTextEndIconDelegate.this.E(valueAnimator);
            }
        });
        return ofFloat;
    }

    private ValueAnimator C() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        ofFloat.setInterpolator(this.f15375h);
        ofFloat.setDuration(this.f15373f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.textfield.e
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ClearTextEndIconDelegate.this.F(valueAnimator);
            }
        });
        return ofFloat;
    }

    private void D() {
        ValueAnimator C = C();
        ValueAnimator B = B(0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.f15379l = animatorSet;
        animatorSet.playTogether(C, B);
        this.f15379l.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                ClearTextEndIconDelegate.this.f15407b.a0(true);
            }
        });
        ValueAnimator B2 = B(1.0f, 0.0f);
        this.f15380m = B2;
        B2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ClearTextEndIconDelegate.this.f15407b.a0(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(ValueAnimator valueAnimator) {
        this.f15409d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f15409d.setScaleX(floatValue);
        this.f15409d.setScaleY(floatValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G(View view) {
        EditText editText = this.f15376i;
        if (editText == null) {
            return;
        }
        Editable text = editText.getText();
        if (text != null) {
            text.clear();
        }
        r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H(View view, boolean z) {
        A(J());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I() {
        A(true);
    }

    private boolean J() {
        EditText editText = this.f15376i;
        return editText != null && (editText.hasFocus() || this.f15409d.hasFocus()) && this.f15376i.getText().length() > 0;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void a(Editable editable) {
        if (this.f15407b.w() != null) {
            return;
        }
        A(J());
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    int c() {
        return R.string.clear_text_end_icon_content_description;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    int d() {
        return R.drawable.mtrl_ic_cancel;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    View.OnFocusChangeListener e() {
        return this.f15378k;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    View.OnClickListener f() {
        return this.f15377j;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    View.OnFocusChangeListener g() {
        return this.f15378k;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void n(EditText editText) {
        this.f15376i = editText;
        this.f15406a.setEndIconVisible(J());
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void q(boolean z) {
        if (this.f15407b.w() == null) {
            return;
        }
        A(z);
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void s() {
        D();
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void u() {
        EditText editText = this.f15376i;
        if (editText != null) {
            editText.post(new Runnable() { // from class: com.google.android.material.textfield.d
                @Override // java.lang.Runnable
                public final void run() {
                    ClearTextEndIconDelegate.this.I();
                }
            });
        }
    }
}
