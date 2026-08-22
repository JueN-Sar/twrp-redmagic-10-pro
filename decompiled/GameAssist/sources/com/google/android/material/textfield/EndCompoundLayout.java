package com.google.android.material.textfield;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Iterator;
import java.util.LinkedHashSet;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
class EndCompoundLayout extends LinearLayout {

    @Nullable
    private final AccessibilityManager accessibilityManager;
    private EditText editText;
    private final TextWatcher editTextWatcher;
    private final LinkedHashSet<TextInputLayout.OnEndIconChangedListener> endIconChangedListeners;
    private final EndIconDelegates endIconDelegates;

    @NonNull
    private final FrameLayout endIconFrame;
    private int endIconMinSize;
    private int endIconMode;
    private View.OnLongClickListener endIconOnLongClickListener;

    @NonNull
    private ImageView.ScaleType endIconScaleType;
    private ColorStateList endIconTintList;
    private PorterDuff.Mode endIconTintMode;

    @NonNull
    private final CheckableImageButton endIconView;
    private View.OnLongClickListener errorIconOnLongClickListener;
    private ColorStateList errorIconTintList;
    private PorterDuff.Mode errorIconTintMode;

    @NonNull
    private final CheckableImageButton errorIconView;
    private boolean hintExpanded;
    private final TextInputLayout.OnEditTextAttachedListener onEditTextAttachedListener;

    @Nullable
    private CharSequence suffixText;

    @NonNull
    private final TextView suffixTextView;
    final TextInputLayout textInputLayout;

    @Nullable
    private AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener;

    private static class EndIconDelegates {

        /* renamed from: a, reason: collision with root package name */
        private final SparseArray f15402a = new SparseArray();

        /* renamed from: b, reason: collision with root package name */
        private final EndCompoundLayout f15403b;

        /* renamed from: c, reason: collision with root package name */
        private final int f15404c;

        /* renamed from: d, reason: collision with root package name */
        private final int f15405d;

        EndIconDelegates(EndCompoundLayout endCompoundLayout, TintTypedArray tintTypedArray) {
            this.f15403b = endCompoundLayout;
            this.f15404c = tintTypedArray.n(R.styleable.TextInputLayout_endIconDrawable, 0);
            this.f15405d = tintTypedArray.n(R.styleable.TextInputLayout_passwordToggleDrawable, 0);
        }

        private EndIconDelegate b(int i2) {
            if (i2 == -1) {
                return new CustomEndIconDelegate(this.f15403b);
            }
            if (i2 == 0) {
                return new NoEndIconDelegate(this.f15403b);
            }
            if (i2 == 1) {
                return new PasswordToggleEndIconDelegate(this.f15403b, this.f15405d);
            }
            if (i2 == 2) {
                return new ClearTextEndIconDelegate(this.f15403b);
            }
            if (i2 == 3) {
                return new DropdownMenuEndIconDelegate(this.f15403b);
            }
            throw new IllegalArgumentException("Invalid end icon mode: " + i2);
        }

        EndIconDelegate c(int i2) {
            EndIconDelegate endIconDelegate = (EndIconDelegate) this.f15402a.get(i2);
            if (endIconDelegate != null) {
                return endIconDelegate;
            }
            EndIconDelegate b2 = b(i2);
            this.f15402a.append(i2, b2);
            return b2;
        }
    }

    EndCompoundLayout(TextInputLayout textInputLayout, TintTypedArray tintTypedArray) {
        super(textInputLayout.getContext());
        this.endIconMode = 0;
        this.endIconChangedListeners = new LinkedHashSet<>();
        this.editTextWatcher = new TextWatcherAdapter() { // from class: com.google.android.material.textfield.EndCompoundLayout.1
            @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                EndCompoundLayout.this.m().a(editable);
            }

            @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                EndCompoundLayout.this.m().b(charSequence, i2, i3, i4);
            }
        };
        TextInputLayout.OnEditTextAttachedListener onEditTextAttachedListener = new TextInputLayout.OnEditTextAttachedListener() { // from class: com.google.android.material.textfield.EndCompoundLayout.2
            @Override // com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener
            public void a(TextInputLayout textInputLayout2) {
                if (EndCompoundLayout.this.editText == textInputLayout2.getEditText()) {
                    return;
                }
                if (EndCompoundLayout.this.editText != null) {
                    EndCompoundLayout.this.editText.removeTextChangedListener(EndCompoundLayout.this.editTextWatcher);
                    if (EndCompoundLayout.this.editText.getOnFocusChangeListener() == EndCompoundLayout.this.m().e()) {
                        EndCompoundLayout.this.editText.setOnFocusChangeListener(null);
                    }
                }
                EndCompoundLayout.this.editText = textInputLayout2.getEditText();
                if (EndCompoundLayout.this.editText != null) {
                    EndCompoundLayout.this.editText.addTextChangedListener(EndCompoundLayout.this.editTextWatcher);
                }
                EndCompoundLayout.this.m().n(EndCompoundLayout.this.editText);
                EndCompoundLayout endCompoundLayout = EndCompoundLayout.this;
                endCompoundLayout.h0(endCompoundLayout.m());
            }
        };
        this.onEditTextAttachedListener = onEditTextAttachedListener;
        this.accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.textInputLayout = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.endIconFrame = frameLayout;
        frameLayout.setVisibility(8);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater from = LayoutInflater.from(getContext());
        CheckableImageButton i2 = i(this, from, R.id.text_input_error_icon);
        this.errorIconView = i2;
        CheckableImageButton i3 = i(frameLayout, from, R.id.text_input_end_icon);
        this.endIconView = i3;
        this.endIconDelegates = new EndIconDelegates(this, tintTypedArray);
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.suffixTextView = appCompatTextView;
        C(tintTypedArray);
        B(tintTypedArray);
        D(tintTypedArray);
        frameLayout.addView(i3);
        addView(appCompatTextView);
        addView(frameLayout);
        addView(i2);
        textInputLayout.i(onEditTextAttachedListener);
        addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.google.android.material.textfield.EndCompoundLayout.3
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                EndCompoundLayout.this.g();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                EndCompoundLayout.this.M();
            }
        });
    }

    private void B(TintTypedArray tintTypedArray) {
        if (!tintTypedArray.s(R.styleable.TextInputLayout_passwordToggleEnabled)) {
            if (tintTypedArray.s(R.styleable.TextInputLayout_endIconTint)) {
                this.endIconTintList = MaterialResources.b(getContext(), tintTypedArray, R.styleable.TextInputLayout_endIconTint);
            }
            if (tintTypedArray.s(R.styleable.TextInputLayout_endIconTintMode)) {
                this.endIconTintMode = ViewUtils.r(tintTypedArray.k(R.styleable.TextInputLayout_endIconTintMode, -1), null);
            }
        }
        if (tintTypedArray.s(R.styleable.TextInputLayout_endIconMode)) {
            U(tintTypedArray.k(R.styleable.TextInputLayout_endIconMode, 0));
            if (tintTypedArray.s(R.styleable.TextInputLayout_endIconContentDescription)) {
                Q(tintTypedArray.p(R.styleable.TextInputLayout_endIconContentDescription));
            }
            O(tintTypedArray.a(R.styleable.TextInputLayout_endIconCheckable, true));
        } else if (tintTypedArray.s(R.styleable.TextInputLayout_passwordToggleEnabled)) {
            if (tintTypedArray.s(R.styleable.TextInputLayout_passwordToggleTint)) {
                this.endIconTintList = MaterialResources.b(getContext(), tintTypedArray, R.styleable.TextInputLayout_passwordToggleTint);
            }
            if (tintTypedArray.s(R.styleable.TextInputLayout_passwordToggleTintMode)) {
                this.endIconTintMode = ViewUtils.r(tintTypedArray.k(R.styleable.TextInputLayout_passwordToggleTintMode, -1), null);
            }
            U(tintTypedArray.a(R.styleable.TextInputLayout_passwordToggleEnabled, false) ? 1 : 0);
            Q(tintTypedArray.p(R.styleable.TextInputLayout_passwordToggleContentDescription));
        }
        T(tintTypedArray.f(R.styleable.TextInputLayout_endIconMinSize, getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size)));
        if (tintTypedArray.s(R.styleable.TextInputLayout_endIconScaleType)) {
            X(IconHelper.b(tintTypedArray.k(R.styleable.TextInputLayout_endIconScaleType, -1)));
        }
    }

    private void C(TintTypedArray tintTypedArray) {
        if (tintTypedArray.s(R.styleable.TextInputLayout_errorIconTint)) {
            this.errorIconTintList = MaterialResources.b(getContext(), tintTypedArray, R.styleable.TextInputLayout_errorIconTint);
        }
        if (tintTypedArray.s(R.styleable.TextInputLayout_errorIconTintMode)) {
            this.errorIconTintMode = ViewUtils.r(tintTypedArray.k(R.styleable.TextInputLayout_errorIconTintMode, -1), null);
        }
        if (tintTypedArray.s(R.styleable.TextInputLayout_errorIconDrawable)) {
            c0(tintTypedArray.g(R.styleable.TextInputLayout_errorIconDrawable));
        }
        this.errorIconView.setContentDescription(getResources().getText(R.string.error_icon_content_description));
        ViewCompat.s0(this.errorIconView, 2);
        this.errorIconView.setClickable(false);
        this.errorIconView.setPressable(false);
        this.errorIconView.setFocusable(false);
    }

    private void D(TintTypedArray tintTypedArray) {
        this.suffixTextView.setVisibility(8);
        this.suffixTextView.setId(R.id.textinput_suffix_text);
        this.suffixTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 80.0f));
        ViewCompat.k0(this.suffixTextView, 1);
        q0(tintTypedArray.n(R.styleable.TextInputLayout_suffixTextAppearance, 0));
        if (tintTypedArray.s(R.styleable.TextInputLayout_suffixTextColor)) {
            r0(tintTypedArray.c(R.styleable.TextInputLayout_suffixTextColor));
        }
        p0(tintTypedArray.p(R.styleable.TextInputLayout_suffixText));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        AccessibilityManager accessibilityManager;
        AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener = this.touchExplorationStateChangeListener;
        if (touchExplorationStateChangeListener == null || (accessibilityManager = this.accessibilityManager) == null) {
            return;
        }
        AccessibilityManagerCompat.b(accessibilityManager, touchExplorationStateChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.touchExplorationStateChangeListener == null || this.accessibilityManager == null || !ViewCompat.M(this)) {
            return;
        }
        AccessibilityManagerCompat.a(this.accessibilityManager, this.touchExplorationStateChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h0(EndIconDelegate endIconDelegate) {
        if (this.editText == null) {
            return;
        }
        if (endIconDelegate.e() != null) {
            this.editText.setOnFocusChangeListener(endIconDelegate.e());
        }
        if (endIconDelegate.g() != null) {
            this.endIconView.setOnFocusChangeListener(endIconDelegate.g());
        }
    }

    private CheckableImageButton i(ViewGroup viewGroup, LayoutInflater layoutInflater, int i2) {
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflater.inflate(R.layout.design_text_input_end_icon, viewGroup, false);
        checkableImageButton.setId(i2);
        IconHelper.e(checkableImageButton);
        if (MaterialResources.j(getContext())) {
            MarginLayoutParamsCompat.d((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams(), 0);
        }
        return checkableImageButton;
    }

    private void j(int i2) {
        Iterator<TextInputLayout.OnEndIconChangedListener> it = this.endIconChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().a(this.textInputLayout, i2);
        }
    }

    private void s0(EndIconDelegate endIconDelegate) {
        endIconDelegate.s();
        this.touchExplorationStateChangeListener = endIconDelegate.h();
        g();
    }

    private int t(EndIconDelegate endIconDelegate) {
        int i2 = this.endIconDelegates.f15404c;
        return i2 == 0 ? endIconDelegate.d() : i2;
    }

    private void t0(EndIconDelegate endIconDelegate) {
        M();
        this.touchExplorationStateChangeListener = null;
        endIconDelegate.u();
    }

    private void u0(boolean z) {
        if (!z || n() == null) {
            IconHelper.a(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
            return;
        }
        Drawable mutate = DrawableCompat.r(n()).mutate();
        DrawableCompat.n(mutate, this.textInputLayout.getErrorCurrentTextColors());
        this.endIconView.setImageDrawable(mutate);
    }

    private void v0() {
        this.endIconFrame.setVisibility((this.endIconView.getVisibility() != 0 || G()) ? 8 : 0);
        setVisibility((F() || G() || !((this.suffixText == null || this.hintExpanded) ? 8 : false)) ? 0 : 8);
    }

    private void w0() {
        this.errorIconView.setVisibility(s() != null && this.textInputLayout.M() && this.textInputLayout.c0() ? 0 : 8);
        v0();
        x0();
        if (A()) {
            return;
        }
        this.textInputLayout.n0();
    }

    private void y0() {
        int visibility = this.suffixTextView.getVisibility();
        int i2 = (this.suffixText == null || this.hintExpanded) ? 8 : 0;
        if (visibility != i2) {
            m().q(i2 == 0);
        }
        v0();
        this.suffixTextView.setVisibility(i2);
        this.textInputLayout.n0();
    }

    boolean A() {
        return this.endIconMode != 0;
    }

    boolean E() {
        return A() && this.endIconView.isChecked();
    }

    boolean F() {
        return this.endIconFrame.getVisibility() == 0 && this.endIconView.getVisibility() == 0;
    }

    boolean G() {
        return this.errorIconView.getVisibility() == 0;
    }

    void H(boolean z) {
        this.hintExpanded = z;
        y0();
    }

    void I() {
        w0();
        K();
        J();
        if (m().t()) {
            u0(this.textInputLayout.c0());
        }
    }

    void J() {
        IconHelper.d(this.textInputLayout, this.endIconView, this.endIconTintList);
    }

    void K() {
        IconHelper.d(this.textInputLayout, this.errorIconView, this.errorIconTintList);
    }

    void L(boolean z) {
        boolean z2;
        boolean isActivated;
        boolean isChecked;
        EndIconDelegate m2 = m();
        boolean z3 = true;
        if (!m2.l() || (isChecked = this.endIconView.isChecked()) == m2.m()) {
            z2 = false;
        } else {
            this.endIconView.setChecked(!isChecked);
            z2 = true;
        }
        if (!m2.j() || (isActivated = this.endIconView.isActivated()) == m2.k()) {
            z3 = z2;
        } else {
            N(!isActivated);
        }
        if (z || z3) {
            J();
        }
    }

    void N(boolean z) {
        this.endIconView.setActivated(z);
    }

    void O(boolean z) {
        this.endIconView.setCheckable(z);
    }

    void P(int i2) {
        Q(i2 != 0 ? getResources().getText(i2) : null);
    }

    void Q(CharSequence charSequence) {
        if (l() != charSequence) {
            this.endIconView.setContentDescription(charSequence);
        }
    }

    void R(int i2) {
        S(i2 != 0 ? AppCompatResources.b(getContext(), i2) : null);
    }

    void S(Drawable drawable) {
        this.endIconView.setImageDrawable(drawable);
        if (drawable != null) {
            IconHelper.a(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
            J();
        }
    }

    void T(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("endIconSize cannot be less than 0");
        }
        if (i2 != this.endIconMinSize) {
            this.endIconMinSize = i2;
            IconHelper.g(this.endIconView, i2);
            IconHelper.g(this.errorIconView, i2);
        }
    }

    void U(int i2) {
        if (this.endIconMode == i2) {
            return;
        }
        t0(m());
        int i3 = this.endIconMode;
        this.endIconMode = i2;
        j(i3);
        a0(i2 != 0);
        EndIconDelegate m2 = m();
        R(t(m2));
        P(m2.c());
        O(m2.l());
        if (!m2.i(this.textInputLayout.getBoxBackgroundMode())) {
            throw new IllegalStateException("The current box background mode " + this.textInputLayout.getBoxBackgroundMode() + " is not supported by the end icon mode " + i2);
        }
        s0(m2);
        V(m2.f());
        EditText editText = this.editText;
        if (editText != null) {
            m2.n(editText);
            h0(m2);
        }
        IconHelper.a(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
        L(true);
    }

    void V(View.OnClickListener onClickListener) {
        IconHelper.h(this.endIconView, onClickListener, this.endIconOnLongClickListener);
    }

    void W(View.OnLongClickListener onLongClickListener) {
        this.endIconOnLongClickListener = onLongClickListener;
        IconHelper.i(this.endIconView, onLongClickListener);
    }

    void X(ImageView.ScaleType scaleType) {
        this.endIconScaleType = scaleType;
        IconHelper.j(this.endIconView, scaleType);
        IconHelper.j(this.errorIconView, scaleType);
    }

    void Y(ColorStateList colorStateList) {
        if (this.endIconTintList != colorStateList) {
            this.endIconTintList = colorStateList;
            IconHelper.a(this.textInputLayout, this.endIconView, colorStateList, this.endIconTintMode);
        }
    }

    void Z(PorterDuff.Mode mode) {
        if (this.endIconTintMode != mode) {
            this.endIconTintMode = mode;
            IconHelper.a(this.textInputLayout, this.endIconView, this.endIconTintList, mode);
        }
    }

    void a0(boolean z) {
        if (F() != z) {
            this.endIconView.setVisibility(z ? 0 : 8);
            v0();
            x0();
            this.textInputLayout.n0();
        }
    }

    void b0(int i2) {
        c0(i2 != 0 ? AppCompatResources.b(getContext(), i2) : null);
        K();
    }

    void c0(Drawable drawable) {
        this.errorIconView.setImageDrawable(drawable);
        w0();
        IconHelper.a(this.textInputLayout, this.errorIconView, this.errorIconTintList, this.errorIconTintMode);
    }

    void d0(View.OnClickListener onClickListener) {
        IconHelper.h(this.errorIconView, onClickListener, this.errorIconOnLongClickListener);
    }

    void e0(View.OnLongClickListener onLongClickListener) {
        this.errorIconOnLongClickListener = onLongClickListener;
        IconHelper.i(this.errorIconView, onLongClickListener);
    }

    void f0(ColorStateList colorStateList) {
        if (this.errorIconTintList != colorStateList) {
            this.errorIconTintList = colorStateList;
            IconHelper.a(this.textInputLayout, this.errorIconView, colorStateList, this.errorIconTintMode);
        }
    }

    void g0(PorterDuff.Mode mode) {
        if (this.errorIconTintMode != mode) {
            this.errorIconTintMode = mode;
            IconHelper.a(this.textInputLayout, this.errorIconView, this.errorIconTintList, mode);
        }
    }

    void h() {
        this.endIconView.performClick();
        this.endIconView.jumpDrawablesToCurrentState();
    }

    void i0(int i2) {
        j0(i2 != 0 ? getResources().getText(i2) : null);
    }

    void j0(CharSequence charSequence) {
        this.endIconView.setContentDescription(charSequence);
    }

    CheckableImageButton k() {
        if (G()) {
            return this.errorIconView;
        }
        if (A() && F()) {
            return this.endIconView;
        }
        return null;
    }

    void k0(int i2) {
        l0(i2 != 0 ? AppCompatResources.b(getContext(), i2) : null);
    }

    CharSequence l() {
        return this.endIconView.getContentDescription();
    }

    void l0(Drawable drawable) {
        this.endIconView.setImageDrawable(drawable);
    }

    EndIconDelegate m() {
        return this.endIconDelegates.c(this.endIconMode);
    }

    void m0(boolean z) {
        if (z && this.endIconMode != 1) {
            U(1);
        } else {
            if (z) {
                return;
            }
            U(0);
        }
    }

    Drawable n() {
        return this.endIconView.getDrawable();
    }

    void n0(ColorStateList colorStateList) {
        this.endIconTintList = colorStateList;
        IconHelper.a(this.textInputLayout, this.endIconView, colorStateList, this.endIconTintMode);
    }

    int o() {
        return this.endIconMinSize;
    }

    void o0(PorterDuff.Mode mode) {
        this.endIconTintMode = mode;
        IconHelper.a(this.textInputLayout, this.endIconView, this.endIconTintList, mode);
    }

    int p() {
        return this.endIconMode;
    }

    void p0(CharSequence charSequence) {
        this.suffixText = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.suffixTextView.setText(charSequence);
        y0();
    }

    ImageView.ScaleType q() {
        return this.endIconScaleType;
    }

    void q0(int i2) {
        TextViewCompat.p(this.suffixTextView, i2);
    }

    CheckableImageButton r() {
        return this.endIconView;
    }

    void r0(ColorStateList colorStateList) {
        this.suffixTextView.setTextColor(colorStateList);
    }

    Drawable s() {
        return this.errorIconView.getDrawable();
    }

    CharSequence u() {
        return this.endIconView.getContentDescription();
    }

    Drawable v() {
        return this.endIconView.getDrawable();
    }

    CharSequence w() {
        return this.suffixText;
    }

    ColorStateList x() {
        return this.suffixTextView.getTextColors();
    }

    void x0() {
        if (this.textInputLayout.editText == null) {
            return;
        }
        ViewCompat.y0(this.suffixTextView, getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding), this.textInputLayout.editText.getPaddingTop(), (F() || G()) ? 0 : ViewCompat.y(this.textInputLayout.editText), this.textInputLayout.editText.getPaddingBottom());
    }

    int y() {
        return ViewCompat.y(this) + ViewCompat.y(this.suffixTextView) + ((F() || G()) ? this.endIconView.getMeasuredWidth() + MarginLayoutParamsCompat.b((ViewGroup.MarginLayoutParams) this.endIconView.getLayoutParams()) : 0);
    }

    TextView z() {
        return this.suffixTextView;
    }
}
