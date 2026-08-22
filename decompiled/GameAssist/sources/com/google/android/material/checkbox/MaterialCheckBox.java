package com.google.android.material.checkbox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ViewUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public class MaterialCheckBox extends AppCompatCheckBox {
    private static final int[][] CHECKBOX_STATES;
    private static final int[] ERROR_STATE_SET;

    @SuppressLint({"DiscouragedApi"})
    private static final int FRAMEWORK_BUTTON_DRAWABLE_RES_ID;
    public static final int STATE_CHECKED = 1;
    public static final int STATE_INDETERMINATE = 2;
    public static final int STATE_UNCHECKED = 0;
    private boolean broadcasting;

    @Nullable
    private Drawable buttonDrawable;

    @Nullable
    private Drawable buttonIconDrawable;

    @Nullable
    ColorStateList buttonIconTintList;

    @NonNull
    private PorterDuff.Mode buttonIconTintMode;

    @Nullable
    ColorStateList buttonTintList;
    private boolean centerIfNoTextEnabled;
    private int checkedState;
    private int[] currentStateChecked;

    @Nullable
    private CharSequence customStateDescription;

    @Nullable
    private CharSequence errorAccessibilityLabel;
    private boolean errorShown;

    @Nullable
    private ColorStateList materialThemeColorsTintList;

    @Nullable
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    @NonNull
    private final LinkedHashSet<OnCheckedStateChangedListener> onCheckedStateChangedListeners;

    @NonNull
    private final LinkedHashSet<OnErrorChangedListener> onErrorChangedListeners;

    @Nullable
    private final AnimatedVectorDrawableCompat transitionToUnchecked;
    private final Animatable2Compat.AnimationCallback transitionToUncheckedCallback;
    private boolean useMaterialThemeColors;
    private boolean usingMaterialButtonDrawable;
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_CompoundButton_CheckBox;
    private static final int[] INDETERMINATE_STATE_SET = {R.attr.state_indeterminate};

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface CheckedState {
    }

    public interface OnCheckedStateChangedListener {
        void a(MaterialCheckBox materialCheckBox, int i2);
    }

    public interface OnErrorChangedListener {
        void a(MaterialCheckBox materialCheckBox, boolean z);
    }

    static class SavedState extends View.BaseSavedState {

        @NonNull
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.google.android.material.checkbox.MaterialCheckBox.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        int f14191c;

        private String a() {
            int i2 = this.f14191c;
            return i2 != 1 ? i2 != 2 ? "unchecked" : "indeterminate" : "checked";
        }

        public String toString() {
            return "MaterialCheckBox.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " CheckedState=" + a() + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeValue(Integer.valueOf(this.f14191c));
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f14191c = ((Integer) parcel.readValue(getClass().getClassLoader())).intValue();
        }
    }

    static {
        int i2 = R.attr.state_error;
        ERROR_STATE_SET = new int[]{i2};
        CHECKBOX_STATES = new int[][]{new int[]{android.R.attr.state_enabled, i2}, new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}, new int[]{android.R.attr.state_enabled, -16842912}, new int[]{-16842910, android.R.attr.state_checked}, new int[]{-16842910, -16842912}};
        FRAMEWORK_BUTTON_DRAWABLE_RES_ID = Resources.getSystem().getIdentifier("btn_check_material_anim", "drawable", "android");
    }

    public MaterialCheckBox(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkboxStyle);
    }

    private boolean c(TintTypedArray tintTypedArray) {
        return tintTypedArray.n(R.styleable.MaterialCheckBox_android_button, 0) == FRAMEWORK_BUTTON_DRAWABLE_RES_ID && tintTypedArray.n(R.styleable.MaterialCheckBox_buttonCompat, 0) == 0;
    }

    private void e() {
        this.buttonDrawable = DrawableUtils.d(this.buttonDrawable, this.buttonTintList, CompoundButtonCompat.c(this));
        this.buttonIconDrawable = DrawableUtils.d(this.buttonIconDrawable, this.buttonIconTintList, this.buttonIconTintMode);
        f();
        g();
        super.setButtonDrawable(DrawableUtils.a(this.buttonDrawable, this.buttonIconDrawable));
        refreshDrawableState();
    }

    private void f() {
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
        if (this.usingMaterialButtonDrawable) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat2 = this.transitionToUnchecked;
            if (animatedVectorDrawableCompat2 != null) {
                animatedVectorDrawableCompat2.g(this.transitionToUncheckedCallback);
                this.transitionToUnchecked.c(this.transitionToUncheckedCallback);
            }
            Drawable drawable = this.buttonDrawable;
            if (!(drawable instanceof AnimatedStateListDrawable) || (animatedVectorDrawableCompat = this.transitionToUnchecked) == null) {
                return;
            }
            ((AnimatedStateListDrawable) drawable).addTransition(R.id.checked, R.id.unchecked, animatedVectorDrawableCompat, false);
            ((AnimatedStateListDrawable) this.buttonDrawable).addTransition(R.id.indeterminate, R.id.unchecked, this.transitionToUnchecked, false);
        }
    }

    private void g() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        Drawable drawable = this.buttonDrawable;
        if (drawable != null && (colorStateList2 = this.buttonTintList) != null) {
            DrawableCompat.o(drawable, colorStateList2);
        }
        Drawable drawable2 = this.buttonIconDrawable;
        if (drawable2 == null || (colorStateList = this.buttonIconTintList) == null) {
            return;
        }
        DrawableCompat.o(drawable2, colorStateList);
    }

    @NonNull
    private String getButtonStateDescription() {
        int i2 = this.checkedState;
        return i2 == 1 ? getResources().getString(R.string.mtrl_checkbox_state_description_checked) : i2 == 0 ? getResources().getString(R.string.mtrl_checkbox_state_description_unchecked) : getResources().getString(R.string.mtrl_checkbox_state_description_indeterminate);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.materialThemeColorsTintList == null) {
            int[][] iArr = CHECKBOX_STATES;
            int[] iArr2 = new int[iArr.length];
            int d2 = MaterialColors.d(this, R.attr.colorControlActivated);
            int d3 = MaterialColors.d(this, R.attr.colorError);
            int d4 = MaterialColors.d(this, R.attr.colorSurface);
            int d5 = MaterialColors.d(this, R.attr.colorOnSurface);
            iArr2[0] = MaterialColors.l(d4, d3, 1.0f);
            iArr2[1] = MaterialColors.l(d4, d2, 1.0f);
            iArr2[2] = MaterialColors.l(d4, d5, 0.54f);
            iArr2[3] = MaterialColors.l(d4, d5, 0.38f);
            iArr2[4] = MaterialColors.l(d4, d5, 0.38f);
            this.materialThemeColorsTintList = new ColorStateList(iArr, iArr2);
        }
        return this.materialThemeColorsTintList;
    }

    @Nullable
    private ColorStateList getSuperButtonTintList() {
        ColorStateList colorStateList = this.buttonTintList;
        return colorStateList != null ? colorStateList : super.getButtonTintList() != null ? super.getButtonTintList() : getSupportButtonTintList();
    }

    private void h() {
    }

    private void setDefaultStateDescription() {
        if (this.customStateDescription == null) {
            super.setStateDescription(getButtonStateDescription());
        }
    }

    public boolean d() {
        return this.errorShown;
    }

    @Override // android.widget.CompoundButton
    @Nullable
    public Drawable getButtonDrawable() {
        return this.buttonDrawable;
    }

    @Nullable
    public Drawable getButtonIconDrawable() {
        return this.buttonIconDrawable;
    }

    @Nullable
    public ColorStateList getButtonIconTintList() {
        return this.buttonIconTintList;
    }

    @NonNull
    public PorterDuff.Mode getButtonIconTintMode() {
        return this.buttonIconTintMode;
    }

    @Override // android.widget.CompoundButton
    @Nullable
    public ColorStateList getButtonTintList() {
        return this.buttonTintList;
    }

    public int getCheckedState() {
        return this.checkedState;
    }

    @Nullable
    public CharSequence getErrorAccessibilityLabel() {
        return this.errorAccessibilityLabel;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public boolean isChecked() {
        return this.checkedState == 1;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors && this.buttonTintList == null && this.buttonIconTintList == null) {
            setUseMaterialThemeColors(true);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (getCheckedState() == 2) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, INDETERMINATE_STATE_SET);
        }
        if (d()) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, ERROR_STATE_SET);
        }
        this.currentStateChecked = DrawableUtils.f(onCreateDrawableState);
        h();
        return onCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable a2;
        if (!this.centerIfNoTextEnabled || !TextUtils.isEmpty(getText()) || (a2 = CompoundButtonCompat.a(this)) == null) {
            super.onDraw(canvas);
            return;
        }
        int width = ((getWidth() - a2.getIntrinsicWidth()) / 2) * (ViewUtils.p(this) ? -1 : 1);
        int save = canvas.save();
        canvas.translate(width, 0.0f);
        super.onDraw(canvas);
        canvas.restoreToCount(save);
        if (getBackground() != null) {
            Rect bounds = a2.getBounds();
            DrawableCompat.l(getBackground(), bounds.left + width, bounds.top, bounds.right + width, bounds.bottom);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (accessibilityNodeInfo != null && d()) {
            accessibilityNodeInfo.setText(((Object) accessibilityNodeInfo.getText()) + ", " + ((Object) this.errorAccessibilityLabel));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCheckedState(savedState.f14191c);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f14191c = getCheckedState();
        return savedState;
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(@DrawableRes int i2) {
        setButtonDrawable(AppCompatResources.b(getContext(), i2));
    }

    public void setButtonIconDrawable(@Nullable Drawable drawable) {
        this.buttonIconDrawable = drawable;
        e();
    }

    public void setButtonIconDrawableResource(@DrawableRes int i2) {
        setButtonIconDrawable(AppCompatResources.b(getContext(), i2));
    }

    public void setButtonIconTintList(@Nullable ColorStateList colorStateList) {
        if (this.buttonIconTintList == colorStateList) {
            return;
        }
        this.buttonIconTintList = colorStateList;
        e();
    }

    public void setButtonIconTintMode(@NonNull PorterDuff.Mode mode) {
        if (this.buttonIconTintMode == mode) {
            return;
        }
        this.buttonIconTintMode = mode;
        e();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintList(@Nullable ColorStateList colorStateList) {
        if (this.buttonTintList == colorStateList) {
            return;
        }
        this.buttonTintList = colorStateList;
        e();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintMode(@Nullable PorterDuff.Mode mode) {
        setSupportButtonTintMode(mode);
        e();
    }

    public void setCenterIfNoTextEnabled(boolean z) {
        this.centerIfNoTextEnabled = z;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        setCheckedState(z ? 1 : 0);
    }

    public void setCheckedState(int i2) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        if (this.checkedState != i2) {
            this.checkedState = i2;
            super.setChecked(i2 == 1);
            refreshDrawableState();
            setDefaultStateDescription();
            if (this.broadcasting) {
                return;
            }
            this.broadcasting = true;
            LinkedHashSet<OnCheckedStateChangedListener> linkedHashSet = this.onCheckedStateChangedListeners;
            if (linkedHashSet != null) {
                Iterator<OnCheckedStateChangedListener> it = linkedHashSet.iterator();
                while (it.hasNext()) {
                    it.next().a(this, this.checkedState);
                }
            }
            if (this.checkedState != 2 && (onCheckedChangeListener = this.onCheckedChangeListener) != null) {
                onCheckedChangeListener.onCheckedChanged(this, isChecked());
            }
            AutofillManager autofillManager = (AutofillManager) getContext().getSystemService(AutofillManager.class);
            if (autofillManager != null) {
                autofillManager.notifyValueChanged(this);
            }
            this.broadcasting = false;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        h();
    }

    public void setErrorAccessibilityLabel(@Nullable CharSequence charSequence) {
        this.errorAccessibilityLabel = charSequence;
    }

    public void setErrorAccessibilityLabelResource(@StringRes int i2) {
        setErrorAccessibilityLabel(i2 != 0 ? getResources().getText(i2) : null);
    }

    public void setErrorShown(boolean z) {
        if (this.errorShown == z) {
            return;
        }
        this.errorShown = z;
        refreshDrawableState();
        Iterator<OnErrorChangedListener> it = this.onErrorChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().a(this, this.errorShown);
        }
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @Override // android.widget.CompoundButton, android.view.View
    @RequiresApi
    public void setStateDescription(@Nullable CharSequence charSequence) {
        this.customStateDescription = charSequence;
        if (charSequence == null) {
            setDefaultStateDescription();
        } else {
            super.setStateDescription(charSequence);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.useMaterialThemeColors = z;
        if (z) {
            CompoundButtonCompat.d(this, getMaterialThemeColorsTintList());
        } else {
            CompoundButtonCompat.d(this, null);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialCheckBox(android.content.Context r9, @androidx.annotation.Nullable android.util.AttributeSet r10, int r11) {
        /*
            r8 = this;
            int r4 = com.google.android.material.checkbox.MaterialCheckBox.DEF_STYLE_RES
            android.content.Context r9 = com.google.android.material.theme.overlay.MaterialThemeOverlay.c(r9, r10, r11, r4)
            r8.<init>(r9, r10, r11)
            java.util.LinkedHashSet r9 = new java.util.LinkedHashSet
            r9.<init>()
            r8.onErrorChangedListeners = r9
            java.util.LinkedHashSet r9 = new java.util.LinkedHashSet
            r9.<init>()
            r8.onCheckedStateChangedListeners = r9
            android.content.Context r9 = r8.getContext()
            int r0 = com.google.android.material.R.drawable.mtrl_checkbox_button_checked_unchecked
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r9 = androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat.a(r9, r0)
            r8.transitionToUnchecked = r9
            com.google.android.material.checkbox.MaterialCheckBox$1 r9 = new com.google.android.material.checkbox.MaterialCheckBox$1
            r9.<init>()
            r8.transitionToUncheckedCallback = r9
            android.content.Context r9 = r8.getContext()
            android.graphics.drawable.Drawable r0 = androidx.core.widget.CompoundButtonCompat.a(r8)
            r8.buttonDrawable = r0
            android.content.res.ColorStateList r0 = r8.getSuperButtonTintList()
            r8.buttonTintList = r0
            r6 = 0
            r8.setSupportButtonTintList(r6)
            int[] r2 = com.google.android.material.R.styleable.MaterialCheckBox
            r7 = 0
            int[] r5 = new int[r7]
            r0 = r9
            r1 = r10
            r3 = r11
            androidx.appcompat.widget.TintTypedArray r10 = com.google.android.material.internal.ThemeEnforcement.j(r0, r1, r2, r3, r4, r5)
            int r11 = com.google.android.material.R.styleable.MaterialCheckBox_buttonIcon
            android.graphics.drawable.Drawable r11 = r10.g(r11)
            r8.buttonIconDrawable = r11
            android.graphics.drawable.Drawable r11 = r8.buttonDrawable
            r0 = 1
            if (r11 == 0) goto L7c
            boolean r11 = com.google.android.material.internal.ThemeEnforcement.g(r9)
            if (r11 == 0) goto L7c
            boolean r11 = r8.c(r10)
            if (r11 == 0) goto L7c
            super.setButtonDrawable(r6)
            int r11 = com.google.android.material.R.drawable.mtrl_checkbox_button
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.b(r9, r11)
            r8.buttonDrawable = r11
            r8.usingMaterialButtonDrawable = r0
            android.graphics.drawable.Drawable r11 = r8.buttonIconDrawable
            if (r11 != 0) goto L7c
            int r11 = com.google.android.material.R.drawable.mtrl_checkbox_button_icon
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.b(r9, r11)
            r8.buttonIconDrawable = r11
        L7c:
            int r11 = com.google.android.material.R.styleable.MaterialCheckBox_buttonIconTint
            android.content.res.ColorStateList r9 = com.google.android.material.resources.MaterialResources.b(r9, r10, r11)
            r8.buttonIconTintList = r9
            int r9 = com.google.android.material.R.styleable.MaterialCheckBox_buttonIconTintMode
            r11 = -1
            int r9 = r10.k(r9, r11)
            android.graphics.PorterDuff$Mode r11 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r9 = com.google.android.material.internal.ViewUtils.r(r9, r11)
            r8.buttonIconTintMode = r9
            int r9 = com.google.android.material.R.styleable.MaterialCheckBox_useMaterialThemeColors
            boolean r9 = r10.a(r9, r7)
            r8.useMaterialThemeColors = r9
            int r9 = com.google.android.material.R.styleable.MaterialCheckBox_centerIfNoTextEnabled
            boolean r9 = r10.a(r9, r0)
            r8.centerIfNoTextEnabled = r9
            int r9 = com.google.android.material.R.styleable.MaterialCheckBox_errorShown
            boolean r9 = r10.a(r9, r7)
            r8.errorShown = r9
            int r9 = com.google.android.material.R.styleable.MaterialCheckBox_errorAccessibilityLabel
            java.lang.CharSequence r9 = r10.p(r9)
            r8.errorAccessibilityLabel = r9
            int r9 = com.google.android.material.R.styleable.MaterialCheckBox_checkedState
            boolean r9 = r10.s(r9)
            if (r9 == 0) goto Lc4
            int r9 = com.google.android.material.R.styleable.MaterialCheckBox_checkedState
            int r9 = r10.k(r9, r7)
            r8.setCheckedState(r9)
        Lc4:
            r10.x()
            r8.e()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.checkbox.MaterialCheckBox.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(@Nullable Drawable drawable) {
        this.buttonDrawable = drawable;
        this.usingMaterialButtonDrawable = false;
        e();
    }
}
