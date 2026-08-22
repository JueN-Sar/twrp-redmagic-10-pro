package com.google.android.material.materialswitch;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;

/* loaded from: classes.dex */
public class MaterialSwitch extends SwitchCompat {
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_CompoundButton_MaterialSwitch;
    private static final int[] STATE_SET_WITH_ICON = {R.attr.state_with_icon};
    private int[] currentStateChecked;
    private int[] currentStateUnchecked;

    @Nullable
    private Drawable thumbDrawable;

    @Nullable
    private Drawable thumbIconDrawable;

    @Px
    private int thumbIconSize;

    @Nullable
    private ColorStateList thumbIconTintList;

    @NonNull
    private PorterDuff.Mode thumbIconTintMode;

    @Nullable
    private ColorStateList thumbTintList;

    @Nullable
    private Drawable trackDecorationDrawable;

    @Nullable
    private ColorStateList trackDecorationTintList;

    @NonNull
    private PorterDuff.Mode trackDecorationTintMode;

    @Nullable
    private Drawable trackDrawable;

    @Nullable
    private ColorStateList trackTintList;

    public MaterialSwitch(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialSwitchStyle);
    }

    private void r() {
        this.thumbDrawable = DrawableUtils.c(this.thumbDrawable, this.thumbTintList, getThumbTintMode());
        this.thumbIconDrawable = DrawableUtils.c(this.thumbIconDrawable, this.thumbIconTintList, this.thumbIconTintMode);
        u();
        Drawable drawable = this.thumbDrawable;
        Drawable drawable2 = this.thumbIconDrawable;
        int i2 = this.thumbIconSize;
        super.setThumbDrawable(DrawableUtils.b(drawable, drawable2, i2, i2));
        refreshDrawableState();
    }

    private void s() {
        this.trackDrawable = DrawableUtils.c(this.trackDrawable, this.trackTintList, getTrackTintMode());
        this.trackDecorationDrawable = DrawableUtils.c(this.trackDecorationDrawable, this.trackDecorationTintList, this.trackDecorationTintMode);
        u();
        Drawable drawable = this.trackDrawable;
        if (drawable != null && this.trackDecorationDrawable != null) {
            drawable = new LayerDrawable(new Drawable[]{this.trackDrawable, this.trackDecorationDrawable});
        } else if (drawable == null) {
            drawable = this.trackDecorationDrawable;
        }
        if (drawable != null) {
            setSwitchMinWidth(drawable.getIntrinsicWidth());
        }
        super.setTrackDrawable(drawable);
    }

    private static void t(Drawable drawable, ColorStateList colorStateList, int[] iArr, int[] iArr2, float f2) {
        if (drawable == null || colorStateList == null) {
            return;
        }
        DrawableCompat.n(drawable, ColorUtils.c(colorStateList.getColorForState(iArr, 0), colorStateList.getColorForState(iArr2, 0), f2));
    }

    private void u() {
        if (this.thumbTintList == null && this.thumbIconTintList == null && this.trackTintList == null && this.trackDecorationTintList == null) {
            return;
        }
        float thumbPosition = getThumbPosition();
        ColorStateList colorStateList = this.thumbTintList;
        if (colorStateList != null) {
            t(this.thumbDrawable, colorStateList, this.currentStateUnchecked, this.currentStateChecked, thumbPosition);
        }
        ColorStateList colorStateList2 = this.thumbIconTintList;
        if (colorStateList2 != null) {
            t(this.thumbIconDrawable, colorStateList2, this.currentStateUnchecked, this.currentStateChecked, thumbPosition);
        }
        ColorStateList colorStateList3 = this.trackTintList;
        if (colorStateList3 != null) {
            t(this.trackDrawable, colorStateList3, this.currentStateUnchecked, this.currentStateChecked, thumbPosition);
        }
        ColorStateList colorStateList4 = this.trackDecorationTintList;
        if (colorStateList4 != null) {
            t(this.trackDecorationDrawable, colorStateList4, this.currentStateUnchecked, this.currentStateChecked, thumbPosition);
        }
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    @Nullable
    public Drawable getThumbDrawable() {
        return this.thumbDrawable;
    }

    @Nullable
    public Drawable getThumbIconDrawable() {
        return this.thumbIconDrawable;
    }

    @Px
    public int getThumbIconSize() {
        return this.thumbIconSize;
    }

    @Nullable
    public ColorStateList getThumbIconTintList() {
        return this.thumbIconTintList;
    }

    @NonNull
    public PorterDuff.Mode getThumbIconTintMode() {
        return this.thumbIconTintMode;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    @Nullable
    public ColorStateList getThumbTintList() {
        return this.thumbTintList;
    }

    @Nullable
    public Drawable getTrackDecorationDrawable() {
        return this.trackDecorationDrawable;
    }

    @Nullable
    public ColorStateList getTrackDecorationTintList() {
        return this.trackDecorationTintList;
    }

    @NonNull
    public PorterDuff.Mode getTrackDecorationTintMode() {
        return this.trackDecorationTintMode;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    @Nullable
    public Drawable getTrackDrawable() {
        return this.trackDrawable;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    @Nullable
    public ColorStateList getTrackTintList() {
        return this.trackTintList;
    }

    @Override // android.view.View
    public void invalidate() {
        u();
        super.invalidate();
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (this.thumbIconDrawable != null) {
            CompoundButton.mergeDrawableStates(onCreateDrawableState, STATE_SET_WITH_ICON);
        }
        this.currentStateUnchecked = DrawableUtils.j(onCreateDrawableState);
        this.currentStateChecked = DrawableUtils.f(onCreateDrawableState);
        return onCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbDrawable(@Nullable Drawable drawable) {
        this.thumbDrawable = drawable;
        r();
    }

    public void setThumbIconDrawable(@Nullable Drawable drawable) {
        this.thumbIconDrawable = drawable;
        r();
    }

    public void setThumbIconResource(@DrawableRes int i2) {
        setThumbIconDrawable(AppCompatResources.b(getContext(), i2));
    }

    public void setThumbIconSize(@Px int i2) {
        if (this.thumbIconSize != i2) {
            this.thumbIconSize = i2;
            r();
        }
    }

    public void setThumbIconTintList(@Nullable ColorStateList colorStateList) {
        this.thumbIconTintList = colorStateList;
        r();
    }

    public void setThumbIconTintMode(@NonNull PorterDuff.Mode mode) {
        this.thumbIconTintMode = mode;
        r();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintList(@Nullable ColorStateList colorStateList) {
        this.thumbTintList = colorStateList;
        r();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintMode(@Nullable PorterDuff.Mode mode) {
        super.setThumbTintMode(mode);
        r();
    }

    public void setTrackDecorationDrawable(@Nullable Drawable drawable) {
        this.trackDecorationDrawable = drawable;
        s();
    }

    public void setTrackDecorationResource(@DrawableRes int i2) {
        setTrackDecorationDrawable(AppCompatResources.b(getContext(), i2));
    }

    public void setTrackDecorationTintList(@Nullable ColorStateList colorStateList) {
        this.trackDecorationTintList = colorStateList;
        s();
    }

    public void setTrackDecorationTintMode(@NonNull PorterDuff.Mode mode) {
        this.trackDecorationTintMode = mode;
        s();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackDrawable(@Nullable Drawable drawable) {
        this.trackDrawable = drawable;
        s();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintList(@Nullable ColorStateList colorStateList) {
        this.trackTintList = colorStateList;
        s();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintMode(@Nullable PorterDuff.Mode mode) {
        super.setTrackTintMode(mode);
        s();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialSwitch(@androidx.annotation.NonNull android.content.Context r8, @androidx.annotation.Nullable android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            int r4 = com.google.android.material.materialswitch.MaterialSwitch.DEF_STYLE_RES
            android.content.Context r8 = com.google.android.material.theme.overlay.MaterialThemeOverlay.c(r8, r9, r10, r4)
            r7.<init>(r8, r9, r10)
            r8 = -1
            r7.thumbIconSize = r8
            android.content.Context r0 = r7.getContext()
            android.graphics.drawable.Drawable r1 = super.getThumbDrawable()
            r7.thumbDrawable = r1
            android.content.res.ColorStateList r1 = super.getThumbTintList()
            r7.thumbTintList = r1
            r1 = 0
            super.setThumbTintList(r1)
            android.graphics.drawable.Drawable r2 = super.getTrackDrawable()
            r7.trackDrawable = r2
            android.content.res.ColorStateList r2 = super.getTrackTintList()
            r7.trackTintList = r2
            super.setTrackTintList(r1)
            int[] r2 = com.google.android.material.R.styleable.MaterialSwitch
            r6 = 0
            int[] r5 = new int[r6]
            r1 = r9
            r3 = r10
            androidx.appcompat.widget.TintTypedArray r9 = com.google.android.material.internal.ThemeEnforcement.j(r0, r1, r2, r3, r4, r5)
            int r10 = com.google.android.material.R.styleable.MaterialSwitch_thumbIcon
            android.graphics.drawable.Drawable r10 = r9.g(r10)
            r7.thumbIconDrawable = r10
            int r10 = com.google.android.material.R.styleable.MaterialSwitch_thumbIconSize
            int r10 = r9.f(r10, r8)
            r7.thumbIconSize = r10
            int r10 = com.google.android.material.R.styleable.MaterialSwitch_thumbIconTint
            android.content.res.ColorStateList r10 = r9.c(r10)
            r7.thumbIconTintList = r10
            int r10 = com.google.android.material.R.styleable.MaterialSwitch_thumbIconTintMode
            int r10 = r9.k(r10, r8)
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r10 = com.google.android.material.internal.ViewUtils.r(r10, r0)
            r7.thumbIconTintMode = r10
            int r10 = com.google.android.material.R.styleable.MaterialSwitch_trackDecoration
            android.graphics.drawable.Drawable r10 = r9.g(r10)
            r7.trackDecorationDrawable = r10
            int r10 = com.google.android.material.R.styleable.MaterialSwitch_trackDecorationTint
            android.content.res.ColorStateList r10 = r9.c(r10)
            r7.trackDecorationTintList = r10
            int r10 = com.google.android.material.R.styleable.MaterialSwitch_trackDecorationTintMode
            int r8 = r9.k(r10, r8)
            android.graphics.PorterDuff$Mode r8 = com.google.android.material.internal.ViewUtils.r(r8, r0)
            r7.trackDecorationTintMode = r8
            r9.x()
            r7.setEnforceSwitchWidth(r6)
            r7.r()
            r7.s()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.materialswitch.MaterialSwitch.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }
}
