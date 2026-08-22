package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
final class IndicatorViewController {
    private ColorStateList A;
    private Typeface B;

    /* renamed from: a, reason: collision with root package name */
    private final int f15410a;

    /* renamed from: b, reason: collision with root package name */
    private final int f15411b;

    /* renamed from: c, reason: collision with root package name */
    private final int f15412c;

    /* renamed from: d, reason: collision with root package name */
    private final TimeInterpolator f15413d;

    /* renamed from: e, reason: collision with root package name */
    private final TimeInterpolator f15414e;

    /* renamed from: f, reason: collision with root package name */
    private final TimeInterpolator f15415f;

    /* renamed from: g, reason: collision with root package name */
    private final Context f15416g;

    /* renamed from: h, reason: collision with root package name */
    private final TextInputLayout f15417h;

    /* renamed from: i, reason: collision with root package name */
    private LinearLayout f15418i;

    /* renamed from: j, reason: collision with root package name */
    private int f15419j;

    /* renamed from: k, reason: collision with root package name */
    private FrameLayout f15420k;

    /* renamed from: l, reason: collision with root package name */
    private Animator f15421l;

    /* renamed from: m, reason: collision with root package name */
    private final float f15422m;

    /* renamed from: n, reason: collision with root package name */
    private int f15423n;

    /* renamed from: o, reason: collision with root package name */
    private int f15424o;

    /* renamed from: p, reason: collision with root package name */
    private CharSequence f15425p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f15426q;

    /* renamed from: r, reason: collision with root package name */
    private TextView f15427r;

    /* renamed from: s, reason: collision with root package name */
    private CharSequence f15428s;
    private int t;
    private int u;
    private ColorStateList v;
    private CharSequence w;
    private boolean x;
    private TextView y;
    private int z;

    public IndicatorViewController(TextInputLayout textInputLayout) {
        Context context = textInputLayout.getContext();
        this.f15416g = context;
        this.f15417h = textInputLayout;
        this.f15422m = context.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
        this.f15410a = MotionUtils.f(context, R.attr.motionDurationShort4, 217);
        this.f15411b = MotionUtils.f(context, R.attr.motionDurationMedium4, 167);
        this.f15412c = MotionUtils.f(context, R.attr.motionDurationShort4, 167);
        this.f15413d = MotionUtils.g(context, R.attr.motionEasingEmphasizedDecelerateInterpolator, AnimationUtils.f13817d);
        int i2 = R.attr.motionEasingEmphasizedDecelerateInterpolator;
        TimeInterpolator timeInterpolator = AnimationUtils.f13814a;
        this.f15414e = MotionUtils.g(context, i2, timeInterpolator);
        this.f15415f = MotionUtils.g(context, R.attr.motionEasingLinearInterpolator, timeInterpolator);
    }

    private boolean A(int i2) {
        return (i2 != 2 || this.y == null || TextUtils.isEmpty(this.w)) ? false : true;
    }

    private void F(int i2, int i3) {
        TextView m2;
        TextView m3;
        if (i2 == i3) {
            return;
        }
        if (i3 != 0 && (m3 = m(i3)) != null) {
            m3.setVisibility(0);
            m3.setAlpha(1.0f);
        }
        if (i2 != 0 && (m2 = m(i2)) != null) {
            m2.setVisibility(4);
            if (i2 == 1) {
                m2.setText((CharSequence) null);
            }
        }
        this.f15423n = i3;
    }

    private void O(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    private void Q(ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    private boolean R(TextView textView, CharSequence charSequence) {
        return ViewCompat.N(this.f15417h) && this.f15417h.isEnabled() && !(this.f15424o == this.f15423n && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    private void U(final int i2, final int i3, boolean z) {
        if (i2 == i3) {
            return;
        }
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f15421l = animatorSet;
            ArrayList arrayList = new ArrayList();
            i(arrayList, this.x, this.y, 2, i2, i3);
            i(arrayList, this.f15426q, this.f15427r, 1, i2, i3);
            AnimatorSetCompat.a(animatorSet, arrayList);
            final TextView m2 = m(i2);
            final TextView m3 = m(i3);
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.IndicatorViewController.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    IndicatorViewController.this.f15423n = i3;
                    IndicatorViewController.this.f15421l = null;
                    TextView textView = m2;
                    if (textView != null) {
                        textView.setVisibility(4);
                        if (i2 == 1 && IndicatorViewController.this.f15427r != null) {
                            IndicatorViewController.this.f15427r.setText((CharSequence) null);
                        }
                    }
                    TextView textView2 = m3;
                    if (textView2 != null) {
                        textView2.setTranslationY(0.0f);
                        m3.setAlpha(1.0f);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    TextView textView = m3;
                    if (textView != null) {
                        textView.setVisibility(0);
                        m3.setAlpha(0.0f);
                    }
                }
            });
            animatorSet.start();
        } else {
            F(i2, i3);
        }
        this.f15417h.o0();
        this.f15417h.t0(z);
        this.f15417h.z0();
    }

    private boolean g() {
        return (this.f15418i == null || this.f15417h.getEditText() == null) ? false : true;
    }

    private void i(List list, boolean z, TextView textView, int i2, int i3, int i4) {
        if (textView == null || !z) {
            return;
        }
        if (i2 == i4 || i2 == i3) {
            ObjectAnimator j2 = j(textView, i4 == i2);
            if (i2 == i4 && i3 != 0) {
                j2.setStartDelay(this.f15412c);
            }
            list.add(j2);
            if (i4 != i2 || i3 == 0) {
                return;
            }
            ObjectAnimator k2 = k(textView);
            k2.setStartDelay(this.f15412c);
            list.add(k2);
        }
    }

    private ObjectAnimator j(TextView textView, boolean z) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z ? 1.0f : 0.0f);
        ofFloat.setDuration(z ? this.f15411b : this.f15412c);
        ofFloat.setInterpolator(z ? this.f15414e : this.f15415f);
        return ofFloat;
    }

    private ObjectAnimator k(TextView textView) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.f15422m, 0.0f);
        ofFloat.setDuration(this.f15410a);
        ofFloat.setInterpolator(this.f15413d);
        return ofFloat;
    }

    private TextView m(int i2) {
        if (i2 == 1) {
            return this.f15427r;
        }
        if (i2 != 2) {
            return null;
        }
        return this.y;
    }

    private int v(boolean z, int i2, int i3) {
        return z ? this.f15416g.getResources().getDimensionPixelSize(i2) : i3;
    }

    private boolean z(int i2) {
        return (i2 != 1 || this.f15427r == null || TextUtils.isEmpty(this.f15425p)) ? false : true;
    }

    boolean B(int i2) {
        return i2 == 0 || i2 == 1;
    }

    boolean C() {
        return this.f15426q;
    }

    boolean D() {
        return this.x;
    }

    void E(TextView textView, int i2) {
        FrameLayout frameLayout;
        if (this.f15418i == null) {
            return;
        }
        if (!B(i2) || (frameLayout = this.f15420k) == null) {
            this.f15418i.removeView(textView);
        } else {
            frameLayout.removeView(textView);
        }
        int i3 = this.f15419j - 1;
        this.f15419j = i3;
        Q(this.f15418i, i3);
    }

    void G(int i2) {
        this.t = i2;
        TextView textView = this.f15427r;
        if (textView != null) {
            ViewCompat.k0(textView, i2);
        }
    }

    void H(CharSequence charSequence) {
        this.f15428s = charSequence;
        TextView textView = this.f15427r;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    void I(boolean z) {
        if (this.f15426q == z) {
            return;
        }
        h();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f15416g);
            this.f15427r = appCompatTextView;
            appCompatTextView.setId(R.id.textinput_error);
            this.f15427r.setTextAlignment(5);
            Typeface typeface = this.B;
            if (typeface != null) {
                this.f15427r.setTypeface(typeface);
            }
            J(this.u);
            K(this.v);
            H(this.f15428s);
            G(this.t);
            this.f15427r.setVisibility(4);
            e(this.f15427r, 0);
        } else {
            x();
            E(this.f15427r, 0);
            this.f15427r = null;
            this.f15417h.o0();
            this.f15417h.z0();
        }
        this.f15426q = z;
    }

    void J(int i2) {
        this.u = i2;
        TextView textView = this.f15427r;
        if (textView != null) {
            this.f15417h.b0(textView, i2);
        }
    }

    void K(ColorStateList colorStateList) {
        this.v = colorStateList;
        TextView textView = this.f15427r;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    void L(int i2) {
        this.z = i2;
        TextView textView = this.y;
        if (textView != null) {
            TextViewCompat.p(textView, i2);
        }
    }

    void M(boolean z) {
        if (this.x == z) {
            return;
        }
        h();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f15416g);
            this.y = appCompatTextView;
            appCompatTextView.setId(R.id.textinput_helper_text);
            this.y.setTextAlignment(5);
            Typeface typeface = this.B;
            if (typeface != null) {
                this.y.setTypeface(typeface);
            }
            this.y.setVisibility(4);
            ViewCompat.k0(this.y, 1);
            L(this.z);
            N(this.A);
            e(this.y, 1);
            this.y.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.google.android.material.textfield.IndicatorViewController.2
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                    EditText editText = IndicatorViewController.this.f15417h.getEditText();
                    if (editText != null) {
                        accessibilityNodeInfo.setLabeledBy(editText);
                    }
                }
            });
        } else {
            y();
            E(this.y, 1);
            this.y = null;
            this.f15417h.o0();
            this.f15417h.z0();
        }
        this.x = z;
    }

    void N(ColorStateList colorStateList) {
        this.A = colorStateList;
        TextView textView = this.y;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    void P(Typeface typeface) {
        if (typeface != this.B) {
            this.B = typeface;
            O(this.f15427r, typeface);
            O(this.y, typeface);
        }
    }

    void S(CharSequence charSequence) {
        h();
        this.f15425p = charSequence;
        this.f15427r.setText(charSequence);
        int i2 = this.f15423n;
        if (i2 != 1) {
            this.f15424o = 1;
        }
        U(i2, this.f15424o, R(this.f15427r, charSequence));
    }

    void T(CharSequence charSequence) {
        h();
        this.w = charSequence;
        this.y.setText(charSequence);
        int i2 = this.f15423n;
        if (i2 != 2) {
            this.f15424o = 2;
        }
        U(i2, this.f15424o, R(this.y, charSequence));
    }

    void e(TextView textView, int i2) {
        if (this.f15418i == null && this.f15420k == null) {
            LinearLayout linearLayout = new LinearLayout(this.f15416g);
            this.f15418i = linearLayout;
            linearLayout.setOrientation(0);
            this.f15417h.addView(this.f15418i, -1, -2);
            this.f15420k = new FrameLayout(this.f15416g);
            this.f15418i.addView(this.f15420k, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.f15417h.getEditText() != null) {
                f();
            }
        }
        if (B(i2)) {
            this.f15420k.setVisibility(0);
            this.f15420k.addView(textView);
        } else {
            this.f15418i.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f15418i.setVisibility(0);
        this.f15419j++;
    }

    void f() {
        if (g()) {
            EditText editText = this.f15417h.getEditText();
            boolean j2 = MaterialResources.j(this.f15416g);
            ViewCompat.y0(this.f15418i, v(j2, R.dimen.material_helper_text_font_1_3_padding_horizontal, ViewCompat.z(editText)), v(j2, R.dimen.material_helper_text_font_1_3_padding_top, this.f15416g.getResources().getDimensionPixelSize(R.dimen.material_helper_text_default_padding_top)), v(j2, R.dimen.material_helper_text_font_1_3_padding_horizontal, ViewCompat.y(editText)), 0);
        }
    }

    void h() {
        Animator animator = this.f15421l;
        if (animator != null) {
            animator.cancel();
        }
    }

    boolean l() {
        return z(this.f15424o);
    }

    int n() {
        return this.t;
    }

    CharSequence o() {
        return this.f15428s;
    }

    CharSequence p() {
        return this.f15425p;
    }

    int q() {
        TextView textView = this.f15427r;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    ColorStateList r() {
        TextView textView = this.f15427r;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    CharSequence s() {
        return this.w;
    }

    View t() {
        return this.y;
    }

    int u() {
        TextView textView = this.y;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    boolean w() {
        return A(this.f15423n);
    }

    void x() {
        this.f15425p = null;
        h();
        if (this.f15423n == 1) {
            if (!this.x || TextUtils.isEmpty(this.w)) {
                this.f15424o = 0;
            } else {
                this.f15424o = 2;
            }
        }
        U(this.f15423n, this.f15424o, R(this.f15427r, ""));
    }

    void y() {
        h();
        int i2 = this.f15423n;
        if (i2 == 2) {
            this.f15424o = 0;
        }
        U(i2, this.f15424o, R(this.y, ""));
    }
}
