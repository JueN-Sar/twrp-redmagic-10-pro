package com.google.android.material.textfield;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
class StartCompoundLayout extends LinearLayout {
    private boolean hintExpanded;

    @Nullable
    private CharSequence prefixText;
    private final TextView prefixTextView;
    private int startIconMinSize;
    private View.OnLongClickListener startIconOnLongClickListener;

    @NonNull
    private ImageView.ScaleType startIconScaleType;
    private ColorStateList startIconTintList;
    private PorterDuff.Mode startIconTintMode;
    private final CheckableImageButton startIconView;
    private final TextInputLayout textInputLayout;

    StartCompoundLayout(TextInputLayout textInputLayout, TintTypedArray tintTypedArray) {
        super(textInputLayout.getContext());
        this.textInputLayout = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_start_icon, (ViewGroup) this, false);
        this.startIconView = checkableImageButton;
        IconHelper.e(checkableImageButton);
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.prefixTextView = appCompatTextView;
        j(tintTypedArray);
        i(tintTypedArray);
        addView(checkableImageButton);
        addView(appCompatTextView);
    }

    private void C() {
        int i2 = (this.prefixText == null || this.hintExpanded) ? 8 : 0;
        setVisibility((this.startIconView.getVisibility() == 0 || i2 == 0) ? 0 : 8);
        this.prefixTextView.setVisibility(i2);
        this.textInputLayout.n0();
    }

    private void i(TintTypedArray tintTypedArray) {
        this.prefixTextView.setVisibility(8);
        this.prefixTextView.setId(R.id.textinput_prefix_text);
        this.prefixTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        ViewCompat.k0(this.prefixTextView, 1);
        o(tintTypedArray.n(R.styleable.TextInputLayout_prefixTextAppearance, 0));
        if (tintTypedArray.s(R.styleable.TextInputLayout_prefixTextColor)) {
            p(tintTypedArray.c(R.styleable.TextInputLayout_prefixTextColor));
        }
        n(tintTypedArray.p(R.styleable.TextInputLayout_prefixText));
    }

    private void j(TintTypedArray tintTypedArray) {
        if (MaterialResources.j(getContext())) {
            MarginLayoutParamsCompat.c((ViewGroup.MarginLayoutParams) this.startIconView.getLayoutParams(), 0);
        }
        u(null);
        v(null);
        if (tintTypedArray.s(R.styleable.TextInputLayout_startIconTint)) {
            this.startIconTintList = MaterialResources.b(getContext(), tintTypedArray, R.styleable.TextInputLayout_startIconTint);
        }
        if (tintTypedArray.s(R.styleable.TextInputLayout_startIconTintMode)) {
            this.startIconTintMode = ViewUtils.r(tintTypedArray.k(R.styleable.TextInputLayout_startIconTintMode, -1), null);
        }
        if (tintTypedArray.s(R.styleable.TextInputLayout_startIconDrawable)) {
            s(tintTypedArray.g(R.styleable.TextInputLayout_startIconDrawable));
            if (tintTypedArray.s(R.styleable.TextInputLayout_startIconContentDescription)) {
                r(tintTypedArray.p(R.styleable.TextInputLayout_startIconContentDescription));
            }
            q(tintTypedArray.a(R.styleable.TextInputLayout_startIconCheckable, true));
        }
        t(tintTypedArray.f(R.styleable.TextInputLayout_startIconMinSize, getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size)));
        if (tintTypedArray.s(R.styleable.TextInputLayout_startIconScaleType)) {
            w(IconHelper.b(tintTypedArray.k(R.styleable.TextInputLayout_startIconScaleType, -1)));
        }
    }

    void A(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (this.prefixTextView.getVisibility() != 0) {
            accessibilityNodeInfoCompat.L0(this.startIconView);
        } else {
            accessibilityNodeInfoCompat.t0(this.prefixTextView);
            accessibilityNodeInfoCompat.L0(this.prefixTextView);
        }
    }

    void B() {
        EditText editText = this.textInputLayout.editText;
        if (editText == null) {
            return;
        }
        ViewCompat.y0(this.prefixTextView, k() ? 0 : ViewCompat.z(editText), editText.getCompoundPaddingTop(), getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding), editText.getCompoundPaddingBottom());
    }

    CharSequence a() {
        return this.prefixText;
    }

    ColorStateList b() {
        return this.prefixTextView.getTextColors();
    }

    int c() {
        return ViewCompat.z(this) + ViewCompat.z(this.prefixTextView) + (k() ? this.startIconView.getMeasuredWidth() + MarginLayoutParamsCompat.a((ViewGroup.MarginLayoutParams) this.startIconView.getLayoutParams()) : 0);
    }

    TextView d() {
        return this.prefixTextView;
    }

    CharSequence e() {
        return this.startIconView.getContentDescription();
    }

    Drawable f() {
        return this.startIconView.getDrawable();
    }

    int g() {
        return this.startIconMinSize;
    }

    ImageView.ScaleType h() {
        return this.startIconScaleType;
    }

    boolean k() {
        return this.startIconView.getVisibility() == 0;
    }

    void l(boolean z) {
        this.hintExpanded = z;
        C();
    }

    void m() {
        IconHelper.d(this.textInputLayout, this.startIconView, this.startIconTintList);
    }

    void n(CharSequence charSequence) {
        this.prefixText = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.prefixTextView.setText(charSequence);
        C();
    }

    void o(int i2) {
        TextViewCompat.p(this.prefixTextView, i2);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        B();
    }

    void p(ColorStateList colorStateList) {
        this.prefixTextView.setTextColor(colorStateList);
    }

    void q(boolean z) {
        this.startIconView.setCheckable(z);
    }

    void r(CharSequence charSequence) {
        if (e() != charSequence) {
            this.startIconView.setContentDescription(charSequence);
        }
    }

    void s(Drawable drawable) {
        this.startIconView.setImageDrawable(drawable);
        if (drawable != null) {
            IconHelper.a(this.textInputLayout, this.startIconView, this.startIconTintList, this.startIconTintMode);
            z(true);
            m();
        } else {
            z(false);
            u(null);
            v(null);
            r(null);
        }
    }

    void t(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        }
        if (i2 != this.startIconMinSize) {
            this.startIconMinSize = i2;
            IconHelper.g(this.startIconView, i2);
        }
    }

    void u(View.OnClickListener onClickListener) {
        IconHelper.h(this.startIconView, onClickListener, this.startIconOnLongClickListener);
    }

    void v(View.OnLongClickListener onLongClickListener) {
        this.startIconOnLongClickListener = onLongClickListener;
        IconHelper.i(this.startIconView, onLongClickListener);
    }

    void w(ImageView.ScaleType scaleType) {
        this.startIconScaleType = scaleType;
        IconHelper.j(this.startIconView, scaleType);
    }

    void x(ColorStateList colorStateList) {
        if (this.startIconTintList != colorStateList) {
            this.startIconTintList = colorStateList;
            IconHelper.a(this.textInputLayout, this.startIconView, colorStateList, this.startIconTintMode);
        }
    }

    void y(PorterDuff.Mode mode) {
        if (this.startIconTintMode != mode) {
            this.startIconTintMode = mode;
            IconHelper.a(this.textInputLayout, this.startIconView, this.startIconTintList, mode);
        }
    }

    void z(boolean z) {
        if (k() != z) {
            this.startIconView.setVisibility(z ? 0 : 8);
            B();
            C();
        }
    }
}
