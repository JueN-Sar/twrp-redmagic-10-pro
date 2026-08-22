package com.google.android.material.button;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public class MaterialButton extends AppCompatButton implements Checkable, Shapeable {
    private static final int[] CHECKABLE_STATE_SET = {R.attr.state_checkable};
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int DEF_STYLE_RES = com.google.android.material.R.style.Widget_MaterialComponents_Button;
    public static final int ICON_GRAVITY_END = 3;
    public static final int ICON_GRAVITY_START = 1;
    public static final int ICON_GRAVITY_TEXT_END = 4;
    public static final int ICON_GRAVITY_TEXT_START = 2;
    public static final int ICON_GRAVITY_TEXT_TOP = 32;
    public static final int ICON_GRAVITY_TOP = 16;
    private static final String LOG_TAG = "MaterialButton";

    @Nullable
    private String accessibilityClassName;
    private boolean broadcasting;
    private boolean checked;

    @Nullable
    private Drawable icon;
    private int iconGravity;

    @Px
    private int iconLeft;

    @Px
    private int iconPadding;

    @Px
    private int iconSize;

    @Nullable
    private ColorStateList iconTint;

    @Nullable
    private PorterDuff.Mode iconTintMode;

    @Px
    private int iconTop;

    @NonNull
    private final MaterialButtonHelper materialButtonHelper;

    @NonNull
    private final LinkedHashSet<OnCheckedChangeListener> onCheckedChangeListeners;

    @Nullable
    private OnPressedChangeListener onPressedChangeListenerInternal;

    @Retention(RetentionPolicy.SOURCE)
    public @interface IconGravity {
    }

    @RequiresApi
    @RestrictTo
    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<MaterialButton> {

        /* renamed from: a, reason: collision with root package name */
        private boolean f14075a;

        /* renamed from: b, reason: collision with root package name */
        private int f14076b;

        @Override // android.view.inspector.InspectionCompanion
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void readProperties(MaterialButton materialButton, PropertyReader propertyReader) {
            if (!this.f14075a) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readInt(this.f14076b, materialButton.getIconPadding());
        }

        @Override // android.view.inspector.InspectionCompanion
        public void mapProperties(PropertyMapper propertyMapper) {
            this.f14076b = propertyMapper.mapInt("iconPadding", com.google.android.material.R.attr.iconPadding);
            this.f14075a = true;
        }
    }

    public interface OnCheckedChangeListener {
        void a(MaterialButton materialButton, boolean z);
    }

    interface OnPressedChangeListener {
        void a(MaterialButton materialButton, boolean z);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.button.MaterialButton.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: i, reason: collision with root package name */
        boolean f14077i;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private void b(Parcel parcel) {
            this.f14077i = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f14077i ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                getClass().getClassLoader();
            }
            b(parcel);
        }
    }

    public MaterialButton(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.material.R.attr.materialButtonStyle);
    }

    private boolean b() {
        int i2 = this.iconGravity;
        return i2 == 3 || i2 == 4;
    }

    private boolean c() {
        int i2 = this.iconGravity;
        return i2 == 1 || i2 == 2;
    }

    private boolean d() {
        int i2 = this.iconGravity;
        return i2 == 16 || i2 == 32;
    }

    private boolean e() {
        return ViewCompat.v(this) == 1;
    }

    private boolean f() {
        MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
        return (materialButtonHelper == null || materialButtonHelper.o()) ? false : true;
    }

    private void g() {
        if (c()) {
            TextViewCompat.j(this, this.icon, null, null, null);
        } else if (b()) {
            TextViewCompat.j(this, null, null, this.icon, null);
        } else if (d()) {
            TextViewCompat.j(this, null, this.icon, null, null);
        }
    }

    private Layout.Alignment getActualTextAlignment() {
        int textAlignment = getTextAlignment();
        return textAlignment != 1 ? (textAlignment == 6 || textAlignment == 3) ? Layout.Alignment.ALIGN_OPPOSITE : textAlignment != 4 ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_CENTER : getGravityTextAlignment();
    }

    private Layout.Alignment getGravityTextAlignment() {
        int gravity = getGravity() & 8388615;
        return gravity != 1 ? (gravity == 5 || gravity == 8388613) ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_CENTER;
    }

    private int getTextHeight() {
        if (getLineCount() > 1) {
            return getLayout().getHeight();
        }
        TextPaint paint = getPaint();
        String charSequence = getText().toString();
        if (getTransformationMethod() != null) {
            charSequence = getTransformationMethod().getTransformation(charSequence, this).toString();
        }
        Rect rect = new Rect();
        paint.getTextBounds(charSequence, 0, charSequence.length(), rect);
        return Math.min(rect.height(), getLayout().getHeight());
    }

    private int getTextLayoutWidth() {
        int lineCount = getLineCount();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < lineCount; i2++) {
            f2 = Math.max(f2, getLayout().getLineWidth(i2));
        }
        return (int) Math.ceil(f2);
    }

    private void h(boolean z) {
        Drawable drawable = this.icon;
        if (drawable != null) {
            Drawable mutate = DrawableCompat.r(drawable).mutate();
            this.icon = mutate;
            DrawableCompat.o(mutate, this.iconTint);
            PorterDuff.Mode mode = this.iconTintMode;
            if (mode != null) {
                DrawableCompat.p(this.icon, mode);
            }
            int i2 = this.iconSize;
            if (i2 == 0) {
                i2 = this.icon.getIntrinsicWidth();
            }
            int i3 = this.iconSize;
            if (i3 == 0) {
                i3 = this.icon.getIntrinsicHeight();
            }
            Drawable drawable2 = this.icon;
            int i4 = this.iconLeft;
            int i5 = this.iconTop;
            drawable2.setBounds(i4, i5, i2 + i4, i3 + i5);
            this.icon.setVisible(true, z);
        }
        if (z) {
            g();
            return;
        }
        Drawable[] a2 = TextViewCompat.a(this);
        Drawable drawable3 = a2[0];
        Drawable drawable4 = a2[1];
        Drawable drawable5 = a2[2];
        if ((!c() || drawable3 == this.icon) && ((!b() || drawable5 == this.icon) && (!d() || drawable4 == this.icon))) {
            return;
        }
        g();
    }

    private void i(int i2, int i3) {
        if (this.icon == null || getLayout() == null) {
            return;
        }
        if (!c() && !b()) {
            if (d()) {
                this.iconLeft = 0;
                if (this.iconGravity == 16) {
                    this.iconTop = 0;
                    h(false);
                    return;
                }
                int i4 = this.iconSize;
                if (i4 == 0) {
                    i4 = this.icon.getIntrinsicHeight();
                }
                int max = Math.max(0, (((((i3 - getTextHeight()) - getPaddingTop()) - i4) - this.iconPadding) - getPaddingBottom()) / 2);
                if (this.iconTop != max) {
                    this.iconTop = max;
                    h(false);
                    return;
                }
                return;
            }
            return;
        }
        this.iconTop = 0;
        Layout.Alignment actualTextAlignment = getActualTextAlignment();
        int i5 = this.iconGravity;
        if (i5 == 1 || i5 == 3 || ((i5 == 2 && actualTextAlignment == Layout.Alignment.ALIGN_NORMAL) || (i5 == 4 && actualTextAlignment == Layout.Alignment.ALIGN_OPPOSITE))) {
            this.iconLeft = 0;
            h(false);
            return;
        }
        int i6 = this.iconSize;
        if (i6 == 0) {
            i6 = this.icon.getIntrinsicWidth();
        }
        int textLayoutWidth = ((((i2 - getTextLayoutWidth()) - ViewCompat.y(this)) - i6) - this.iconPadding) - ViewCompat.z(this);
        if (actualTextAlignment == Layout.Alignment.ALIGN_CENTER) {
            textLayoutWidth /= 2;
        }
        if (e() != (this.iconGravity == 4)) {
            textLayoutWidth = -textLayoutWidth;
        }
        if (this.iconLeft != textLayoutWidth) {
            this.iconLeft = textLayoutWidth;
            h(false);
        }
    }

    public boolean a() {
        MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
        return materialButtonHelper != null && materialButtonHelper.p();
    }

    @NonNull
    String getA11yClassName() {
        if (TextUtils.isEmpty(this.accessibilityClassName)) {
            return (a() ? CompoundButton.class : Button.class).getName();
        }
        return this.accessibilityClassName;
    }

    @Override // android.view.View
    @Nullable
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    @Override // android.view.View
    @Nullable
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    @Px
    public int getCornerRadius() {
        if (f()) {
            return this.materialButtonHelper.b();
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public int getIconGravity() {
        return this.iconGravity;
    }

    @Px
    public int getIconPadding() {
        return this.iconPadding;
    }

    @Px
    public int getIconSize() {
        return this.iconSize;
    }

    public ColorStateList getIconTint() {
        return this.iconTint;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.iconTintMode;
    }

    @Dimension
    public int getInsetBottom() {
        return this.materialButtonHelper.c();
    }

    @Dimension
    public int getInsetTop() {
        return this.materialButtonHelper.d();
    }

    @Nullable
    public ColorStateList getRippleColor() {
        if (f()) {
            return this.materialButtonHelper.h();
        }
        return null;
    }

    @Override // com.google.android.material.shape.Shapeable
    @NonNull
    public ShapeAppearanceModel getShapeAppearanceModel() {
        if (f()) {
            return this.materialButtonHelper.i();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (f()) {
            return this.materialButtonHelper.j();
        }
        return null;
    }

    @Px
    public int getStrokeWidth() {
        if (f()) {
            return this.materialButtonHelper.k();
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    @Nullable
    @RestrictTo
    public ColorStateList getSupportBackgroundTintList() {
        return f() ? this.materialButtonHelper.l() : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    @Nullable
    @RestrictTo
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return f() ? this.materialButtonHelper.m() : super.getSupportBackgroundTintMode();
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (f()) {
            MaterialShapeUtils.f(this, this.materialButtonHelper.f());
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (a()) {
            Button.mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        if (isChecked()) {
            Button.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(a());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        i(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        setChecked(savedState.f14077i);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f14077i = this.checked;
        return savedState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        i(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public boolean performClick() {
        if (this.materialButtonHelper.q()) {
            toggle();
        }
        return super.performClick();
    }

    @Override // android.view.View
    public void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.icon != null) {
            if (this.icon.setState(getDrawableState())) {
                invalidate();
            }
        }
    }

    void setA11yClassName(@Nullable String str) {
        this.accessibilityClassName = str;
    }

    @Override // android.view.View
    public void setBackground(@NonNull Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(@ColorInt int i2) {
        if (f()) {
            this.materialButtonHelper.s(i2);
        } else {
            super.setBackgroundColor(i2);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(@NonNull Drawable drawable) {
        if (!f()) {
            super.setBackgroundDrawable(drawable);
        } else {
            if (drawable == getBackground()) {
                getBackground().setState(drawable.getState());
                return;
            }
            Log.w(LOG_TAG, "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
            this.materialButtonHelper.t();
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(@DrawableRes int i2) {
        setBackgroundDrawable(i2 != 0 ? AppCompatResources.b(getContext(), i2) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z) {
        if (f()) {
            this.materialButtonHelper.u(z);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (a() && isEnabled() && this.checked != z) {
            this.checked = z;
            refreshDrawableState();
            if (getParent() instanceof MaterialButtonToggleGroup) {
                ((MaterialButtonToggleGroup) getParent()).n(this, this.checked);
            }
            if (this.broadcasting) {
                return;
            }
            this.broadcasting = true;
            Iterator<OnCheckedChangeListener> it = this.onCheckedChangeListeners.iterator();
            while (it.hasNext()) {
                it.next().a(this, this.checked);
            }
            this.broadcasting = false;
        }
    }

    public void setCornerRadius(@Px int i2) {
        if (f()) {
            this.materialButtonHelper.v(i2);
        }
    }

    public void setCornerRadiusResource(@DimenRes int i2) {
        if (f()) {
            setCornerRadius(getResources().getDimensionPixelSize(i2));
        }
    }

    @Override // android.view.View
    @RequiresApi
    public void setElevation(float f2) {
        super.setElevation(f2);
        if (f()) {
            this.materialButtonHelper.f().Z(f2);
        }
    }

    public void setIcon(@Nullable Drawable drawable) {
        if (this.icon != drawable) {
            this.icon = drawable;
            h(true);
            i(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i2) {
        if (this.iconGravity != i2) {
            this.iconGravity = i2;
            i(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(@Px int i2) {
        if (this.iconPadding != i2) {
            this.iconPadding = i2;
            setCompoundDrawablePadding(i2);
        }
    }

    public void setIconResource(@DrawableRes int i2) {
        setIcon(i2 != 0 ? AppCompatResources.b(getContext(), i2) : null);
    }

    public void setIconSize(@Px int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.iconSize != i2) {
            this.iconSize = i2;
            h(true);
        }
    }

    public void setIconTint(@Nullable ColorStateList colorStateList) {
        if (this.iconTint != colorStateList) {
            this.iconTint = colorStateList;
            h(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.iconTintMode != mode) {
            this.iconTintMode = mode;
            h(false);
        }
    }

    public void setIconTintResource(@ColorRes int i2) {
        setIconTint(AppCompatResources.a(getContext(), i2));
    }

    public void setInsetBottom(@Dimension int i2) {
        this.materialButtonHelper.w(i2);
    }

    public void setInsetTop(@Dimension int i2) {
        this.materialButtonHelper.x(i2);
    }

    void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    void setOnPressedChangeListenerInternal(@Nullable OnPressedChangeListener onPressedChangeListener) {
        this.onPressedChangeListenerInternal = onPressedChangeListener;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        OnPressedChangeListener onPressedChangeListener = this.onPressedChangeListenerInternal;
        if (onPressedChangeListener != null) {
            onPressedChangeListener.a(this, z);
        }
        super.setPressed(z);
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        if (f()) {
            this.materialButtonHelper.y(colorStateList);
        }
    }

    public void setRippleColorResource(@ColorRes int i2) {
        if (f()) {
            setRippleColor(AppCompatResources.a(getContext(), i2));
        }
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        if (!f()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.materialButtonHelper.z(shapeAppearanceModel);
    }

    void setShouldDrawSurfaceColorStroke(boolean z) {
        if (f()) {
            this.materialButtonHelper.A(z);
        }
    }

    public void setStrokeColor(@Nullable ColorStateList colorStateList) {
        if (f()) {
            this.materialButtonHelper.B(colorStateList);
        }
    }

    public void setStrokeColorResource(@ColorRes int i2) {
        if (f()) {
            setStrokeColor(AppCompatResources.a(getContext(), i2));
        }
    }

    public void setStrokeWidth(@Px int i2) {
        if (f()) {
            this.materialButtonHelper.C(i2);
        }
    }

    public void setStrokeWidthResource(@DimenRes int i2) {
        if (f()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i2));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    @RestrictTo
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        if (f()) {
            this.materialButtonHelper.D(colorStateList);
        } else {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    @RestrictTo
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        if (f()) {
            this.materialButtonHelper.E(mode);
        } else {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    @Override // android.view.View
    @RequiresApi
    public void setTextAlignment(int i2) {
        super.setTextAlignment(i2);
        i(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setToggleCheckedStateOnClick(boolean z) {
        this.materialButtonHelper.F(z);
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.checked);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialButton(@androidx.annotation.NonNull android.content.Context r9, @androidx.annotation.Nullable android.util.AttributeSet r10, int r11) {
        /*
            r8 = this;
            int r6 = com.google.android.material.button.MaterialButton.DEF_STYLE_RES
            android.content.Context r9 = com.google.android.material.theme.overlay.MaterialThemeOverlay.c(r9, r10, r11, r6)
            r8.<init>(r9, r10, r11)
            java.util.LinkedHashSet r9 = new java.util.LinkedHashSet
            r9.<init>()
            r8.onCheckedChangeListeners = r9
            r9 = 0
            r8.checked = r9
            r8.broadcasting = r9
            android.content.Context r7 = r8.getContext()
            int[] r2 = com.google.android.material.R.styleable.MaterialButton
            int[] r5 = new int[r9]
            r0 = r7
            r1 = r10
            r3 = r11
            r4 = r6
            android.content.res.TypedArray r0 = com.google.android.material.internal.ThemeEnforcement.i(r0, r1, r2, r3, r4, r5)
            int r1 = com.google.android.material.R.styleable.MaterialButton_iconPadding
            int r1 = r0.getDimensionPixelSize(r1, r9)
            r8.iconPadding = r1
            int r1 = com.google.android.material.R.styleable.MaterialButton_iconTintMode
            r2 = -1
            int r1 = r0.getInt(r1, r2)
            android.graphics.PorterDuff$Mode r2 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r1 = com.google.android.material.internal.ViewUtils.r(r1, r2)
            r8.iconTintMode = r1
            android.content.Context r1 = r8.getContext()
            int r2 = com.google.android.material.R.styleable.MaterialButton_iconTint
            android.content.res.ColorStateList r1 = com.google.android.material.resources.MaterialResources.a(r1, r0, r2)
            r8.iconTint = r1
            android.content.Context r1 = r8.getContext()
            int r2 = com.google.android.material.R.styleable.MaterialButton_icon
            android.graphics.drawable.Drawable r1 = com.google.android.material.resources.MaterialResources.e(r1, r0, r2)
            r8.icon = r1
            int r1 = com.google.android.material.R.styleable.MaterialButton_iconGravity
            r2 = 1
            int r1 = r0.getInteger(r1, r2)
            r8.iconGravity = r1
            int r1 = com.google.android.material.R.styleable.MaterialButton_iconSize
            int r1 = r0.getDimensionPixelSize(r1, r9)
            r8.iconSize = r1
            com.google.android.material.shape.ShapeAppearanceModel$Builder r10 = com.google.android.material.shape.ShapeAppearanceModel.e(r7, r10, r11, r6)
            com.google.android.material.shape.ShapeAppearanceModel r10 = r10.m()
            com.google.android.material.button.MaterialButtonHelper r11 = new com.google.android.material.button.MaterialButtonHelper
            r11.<init>(r8, r10)
            r8.materialButtonHelper = r11
            r11.r(r0)
            r0.recycle()
            int r10 = r8.iconPadding
            r8.setCompoundDrawablePadding(r10)
            android.graphics.drawable.Drawable r10 = r8.icon
            if (r10 == 0) goto L84
            r9 = r2
        L84:
            r8.h(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.button.MaterialButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }
}
