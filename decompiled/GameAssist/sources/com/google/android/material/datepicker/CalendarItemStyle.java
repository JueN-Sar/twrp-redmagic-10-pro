package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.TextView;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

/* loaded from: classes.dex */
final class CalendarItemStyle {

    /* renamed from: a, reason: collision with root package name */
    private final Rect f14430a;

    /* renamed from: b, reason: collision with root package name */
    private final ColorStateList f14431b;

    /* renamed from: c, reason: collision with root package name */
    private final ColorStateList f14432c;

    /* renamed from: d, reason: collision with root package name */
    private final ColorStateList f14433d;

    /* renamed from: e, reason: collision with root package name */
    private final int f14434e;

    /* renamed from: f, reason: collision with root package name */
    private final ShapeAppearanceModel f14435f;

    private CalendarItemStyle(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i2, ShapeAppearanceModel shapeAppearanceModel, Rect rect) {
        Preconditions.e(rect.left);
        Preconditions.e(rect.top);
        Preconditions.e(rect.right);
        Preconditions.e(rect.bottom);
        this.f14430a = rect;
        this.f14431b = colorStateList2;
        this.f14432c = colorStateList;
        this.f14433d = colorStateList3;
        this.f14434e = i2;
        this.f14435f = shapeAppearanceModel;
    }

    static CalendarItemStyle a(Context context, int i2) {
        Preconditions.b(i2 != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, R.styleable.MaterialCalendarItem);
        Rect rect = new Rect(obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialCalendarItem_android_insetLeft, 0), obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialCalendarItem_android_insetTop, 0), obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialCalendarItem_android_insetRight, 0), obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList a2 = MaterialResources.a(context, obtainStyledAttributes, R.styleable.MaterialCalendarItem_itemFillColor);
        ColorStateList a3 = MaterialResources.a(context, obtainStyledAttributes, R.styleable.MaterialCalendarItem_itemTextColor);
        ColorStateList a4 = MaterialResources.a(context, obtainStyledAttributes, R.styleable.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MaterialCalendarItem_itemStrokeWidth, 0);
        ShapeAppearanceModel m2 = ShapeAppearanceModel.b(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendarItem_itemShapeAppearance, 0), obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).m();
        obtainStyledAttributes.recycle();
        return new CalendarItemStyle(a2, a3, a4, dimensionPixelSize, m2, rect);
    }

    int b() {
        return this.f14430a.bottom;
    }

    int c() {
        return this.f14430a.top;
    }

    void d(TextView textView) {
        e(textView, null, null);
    }

    void e(TextView textView, ColorStateList colorStateList, ColorStateList colorStateList2) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable();
        materialShapeDrawable.setShapeAppearanceModel(this.f14435f);
        materialShapeDrawable2.setShapeAppearanceModel(this.f14435f);
        if (colorStateList == null) {
            colorStateList = this.f14432c;
        }
        materialShapeDrawable.a0(colorStateList);
        materialShapeDrawable.k0(this.f14434e, this.f14433d);
        if (colorStateList2 == null) {
            colorStateList2 = this.f14431b;
        }
        textView.setTextColor(colorStateList2);
        RippleDrawable rippleDrawable = new RippleDrawable(this.f14431b.withAlpha(30), materialShapeDrawable, materialShapeDrawable2);
        Rect rect = this.f14430a;
        ViewCompat.m0(textView, new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
