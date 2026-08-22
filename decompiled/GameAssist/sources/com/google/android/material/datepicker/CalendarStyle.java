package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;

/* loaded from: classes.dex */
final class CalendarStyle {

    /* renamed from: a, reason: collision with root package name */
    final CalendarItemStyle f14436a;

    /* renamed from: b, reason: collision with root package name */
    final CalendarItemStyle f14437b;

    /* renamed from: c, reason: collision with root package name */
    final CalendarItemStyle f14438c;

    /* renamed from: d, reason: collision with root package name */
    final CalendarItemStyle f14439d;

    /* renamed from: e, reason: collision with root package name */
    final CalendarItemStyle f14440e;

    /* renamed from: f, reason: collision with root package name */
    final CalendarItemStyle f14441f;

    /* renamed from: g, reason: collision with root package name */
    final CalendarItemStyle f14442g;

    /* renamed from: h, reason: collision with root package name */
    final Paint f14443h;

    CalendarStyle(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(MaterialAttributes.d(context, R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), R.styleable.MaterialCalendar);
        this.f14436a = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_dayStyle, 0));
        this.f14442g = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_dayInvalidStyle, 0));
        this.f14437b = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_daySelectedStyle, 0));
        this.f14438c = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList a2 = MaterialResources.a(context, obtainStyledAttributes, R.styleable.MaterialCalendar_rangeFillColor);
        this.f14439d = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_yearStyle, 0));
        this.f14440e = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_yearSelectedStyle, 0));
        this.f14441f = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_yearTodayStyle, 0));
        Paint paint = new Paint();
        this.f14443h = paint;
        paint.setColor(a2.getDefaultColor());
        obtainStyledAttributes.recycle();
    }
}
