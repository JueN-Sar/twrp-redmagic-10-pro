package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.RestrictTo;
import androidx.core.util.Pair;
import com.google.android.material.R;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

@RestrictTo
/* loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<Pair<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new Parcelable.Creator<RangeDateSelector>() { // from class: com.google.android.material.datepicker.RangeDateSelector.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.f14517j = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.f14518k = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector[] newArray(int i2) {
            return new RangeDateSelector[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private CharSequence f14514c;

    /* renamed from: h, reason: collision with root package name */
    private String f14515h;

    /* renamed from: i, reason: collision with root package name */
    private final String f14516i = " ";

    /* renamed from: j, reason: collision with root package name */
    private Long f14517j = null;

    /* renamed from: k, reason: collision with root package name */
    private Long f14518k = null;

    /* renamed from: l, reason: collision with root package name */
    private Long f14519l = null;

    /* renamed from: m, reason: collision with root package name */
    private Long f14520m = null;

    /* renamed from: n, reason: collision with root package name */
    private SimpleDateFormat f14521n;

    private void i(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        if (textInputLayout.getError() != null && this.f14515h.contentEquals(textInputLayout.getError())) {
            textInputLayout.setError(null);
        }
        if (textInputLayout2.getError() == null || !" ".contentEquals(textInputLayout2.getError())) {
            return;
        }
        textInputLayout2.setError(null);
    }

    private boolean l(long j2, long j3) {
        return j2 <= j3;
    }

    private void m(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        textInputLayout.setError(this.f14515h);
        textInputLayout2.setError(" ");
    }

    private void n(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        if (!TextUtils.isEmpty(textInputLayout.getError())) {
            this.f14514c = textInputLayout.getError();
        } else if (TextUtils.isEmpty(textInputLayout2.getError())) {
            this.f14514c = null;
        } else {
            this.f14514c = textInputLayout2.getError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(TextInputLayout textInputLayout, TextInputLayout textInputLayout2, OnSelectionChangedListener onSelectionChangedListener) {
        Long l2 = this.f14519l;
        if (l2 == null || this.f14520m == null) {
            i(textInputLayout, textInputLayout2);
            onSelectionChangedListener.a();
        } else if (l(l2.longValue(), this.f14520m.longValue())) {
            this.f14517j = this.f14519l;
            this.f14518k = this.f14520m;
            onSelectionChangedListener.b(F());
        } else {
            m(textInputLayout, textInputLayout2);
            onSelectionChangedListener.a();
        }
        n(textInputLayout, textInputLayout2);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View B(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, final OnSelectionChangedListener onSelectionChangedListener) {
        View inflate = layoutInflater.inflate(R.layout.mtrl_picker_text_input_date_range, viewGroup, false);
        final TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.mtrl_picker_text_input_range_start);
        final TextInputLayout textInputLayout2 = (TextInputLayout) inflate.findViewById(R.id.mtrl_picker_text_input_range_end);
        EditText editText = textInputLayout.getEditText();
        EditText editText2 = textInputLayout2.getEditText();
        if (ManufacturerUtils.b()) {
            editText.setInputType(17);
            editText2.setInputType(17);
        }
        this.f14515h = inflate.getResources().getString(R.string.mtrl_picker_invalid_range);
        SimpleDateFormat simpleDateFormat = this.f14521n;
        boolean z = simpleDateFormat != null;
        if (!z) {
            simpleDateFormat = UtcDates.f();
        }
        SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
        Long l2 = this.f14517j;
        if (l2 != null) {
            editText.setText(simpleDateFormat2.format(l2));
            this.f14519l = this.f14517j;
        }
        Long l3 = this.f14518k;
        if (l3 != null) {
            editText2.setText(simpleDateFormat2.format(l3));
            this.f14520m = this.f14518k;
        }
        String pattern = z ? simpleDateFormat2.toPattern() : UtcDates.g(inflate.getResources(), simpleDateFormat2);
        textInputLayout.setPlaceholderText(pattern);
        textInputLayout2.setPlaceholderText(pattern);
        editText.addTextChangedListener(new DateFormatTextWatcher(pattern, simpleDateFormat2, textInputLayout, calendarConstraints) { // from class: com.google.android.material.datepicker.RangeDateSelector.1
            @Override // com.google.android.material.datepicker.DateFormatTextWatcher
            void f() {
                RangeDateSelector.this.f14519l = null;
                RangeDateSelector.this.o(textInputLayout, textInputLayout2, onSelectionChangedListener);
            }

            @Override // com.google.android.material.datepicker.DateFormatTextWatcher
            void g(Long l4) {
                RangeDateSelector.this.f14519l = l4;
                RangeDateSelector.this.o(textInputLayout, textInputLayout2, onSelectionChangedListener);
            }
        });
        editText2.addTextChangedListener(new DateFormatTextWatcher(pattern, simpleDateFormat2, textInputLayout2, calendarConstraints) { // from class: com.google.android.material.datepicker.RangeDateSelector.2
            @Override // com.google.android.material.datepicker.DateFormatTextWatcher
            void f() {
                RangeDateSelector.this.f14520m = null;
                RangeDateSelector.this.o(textInputLayout, textInputLayout2, onSelectionChangedListener);
            }

            @Override // com.google.android.material.datepicker.DateFormatTextWatcher
            void g(Long l4) {
                RangeDateSelector.this.f14520m = l4;
                RangeDateSelector.this.o(textInputLayout, textInputLayout2, onSelectionChangedListener);
            }
        });
        DateSelector.A(editText, editText2);
        return inflate;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean D() {
        Long l2 = this.f14517j;
        return (l2 == null || this.f14518k == null || !l(l2.longValue(), this.f14518k.longValue())) ? false : true;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection E() {
        ArrayList arrayList = new ArrayList();
        Long l2 = this.f14517j;
        if (l2 != null) {
            arrayList.add(l2);
        }
        Long l3 = this.f14518k;
        if (l3 != null) {
            arrayList.add(l3);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void L(long j2) {
        Long l2 = this.f14517j;
        if (l2 == null) {
            this.f14517j = Long.valueOf(j2);
        } else if (this.f14518k == null && l(l2.longValue(), j2)) {
            this.f14518k = Long.valueOf(j2);
        } else {
            this.f14518k = null;
            this.f14517j = Long.valueOf(j2);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String e(Context context) {
        Resources resources = context.getResources();
        Pair a2 = DateStrings.a(this.f14517j, this.f14518k);
        Object obj = a2.f3270a;
        String string = obj == null ? resources.getString(R.string.mtrl_picker_announce_current_selection_none) : (String) obj;
        Object obj2 = a2.f3271b;
        return resources.getString(R.string.mtrl_picker_announce_current_range_selection, string, obj2 == null ? resources.getString(R.string.mtrl_picker_announce_current_selection_none) : (String) obj2);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int h(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return MaterialAttributes.d(context, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) > resources.getDimensionPixelSize(R.dimen.mtrl_calendar_maximum_default_fullscreen_minor_axis) ? R.attr.materialCalendarTheme : R.attr.materialCalendarFullscreenTheme, MaterialDatePicker.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public Pair F() {
        return new Pair(this.f14517j, this.f14518k);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String s(Context context) {
        Resources resources = context.getResources();
        Long l2 = this.f14517j;
        if (l2 == null && this.f14518k == null) {
            return resources.getString(R.string.mtrl_picker_range_header_unselected);
        }
        Long l3 = this.f14518k;
        if (l3 == null) {
            return resources.getString(R.string.mtrl_picker_range_header_only_start_selected, DateStrings.c(l2.longValue()));
        }
        if (l2 == null) {
            return resources.getString(R.string.mtrl_picker_range_header_only_end_selected, DateStrings.c(l3.longValue()));
        }
        Pair a2 = DateStrings.a(l2, l3);
        return resources.getString(R.string.mtrl_picker_range_header_selected, a2.f3270a, a2.f3271b);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection t() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair(this.f14517j, this.f14518k));
        return arrayList;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeValue(this.f14517j);
        parcel.writeValue(this.f14518k);
    }
}
