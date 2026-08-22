package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.RestrictTo;
import com.google.android.material.R;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

@RestrictTo
/* loaded from: classes.dex */
public class SingleDateSelector implements DateSelector<Long> {
    public static final Parcelable.Creator<SingleDateSelector> CREATOR = new Parcelable.Creator<SingleDateSelector>() { // from class: com.google.android.material.datepicker.SingleDateSelector.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SingleDateSelector createFromParcel(Parcel parcel) {
            SingleDateSelector singleDateSelector = new SingleDateSelector();
            singleDateSelector.f14531h = (Long) parcel.readValue(Long.class.getClassLoader());
            return singleDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public SingleDateSelector[] newArray(int i2) {
            return new SingleDateSelector[i2];
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private CharSequence f14530c;

    /* renamed from: h, reason: collision with root package name */
    private Long f14531h;

    /* renamed from: i, reason: collision with root package name */
    private SimpleDateFormat f14532i;

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.f14531h = null;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View B(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, final OnSelectionChangedListener onSelectionChangedListener) {
        View inflate = layoutInflater.inflate(R.layout.mtrl_picker_text_input_date, viewGroup, false);
        final TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.mtrl_picker_text_input_date);
        EditText editText = textInputLayout.getEditText();
        if (ManufacturerUtils.b()) {
            editText.setInputType(17);
        }
        SimpleDateFormat simpleDateFormat = this.f14532i;
        boolean z = simpleDateFormat != null;
        if (!z) {
            simpleDateFormat = UtcDates.f();
        }
        SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
        String pattern = z ? simpleDateFormat2.toPattern() : UtcDates.g(inflate.getResources(), simpleDateFormat2);
        textInputLayout.setPlaceholderText(pattern);
        Long l2 = this.f14531h;
        if (l2 != null) {
            editText.setText(simpleDateFormat2.format(l2));
        }
        editText.addTextChangedListener(new DateFormatTextWatcher(pattern, simpleDateFormat2, textInputLayout, calendarConstraints) { // from class: com.google.android.material.datepicker.SingleDateSelector.1
            @Override // com.google.android.material.datepicker.DateFormatTextWatcher
            void f() {
                SingleDateSelector.this.f14530c = textInputLayout.getError();
                onSelectionChangedListener.a();
            }

            @Override // com.google.android.material.datepicker.DateFormatTextWatcher
            void g(Long l3) {
                if (l3 == null) {
                    SingleDateSelector.this.f();
                } else {
                    SingleDateSelector.this.L(l3.longValue());
                }
                SingleDateSelector.this.f14530c = null;
                onSelectionChangedListener.b(SingleDateSelector.this.F());
            }
        });
        DateSelector.A(editText);
        return inflate;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean D() {
        return this.f14531h != null;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection E() {
        ArrayList arrayList = new ArrayList();
        Long l2 = this.f14531h;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void L(long j2) {
        this.f14531h = Long.valueOf(j2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String e(Context context) {
        Resources resources = context.getResources();
        Long l2 = this.f14531h;
        return resources.getString(R.string.mtrl_picker_announce_current_selection, l2 == null ? resources.getString(R.string.mtrl_picker_announce_current_selection_none) : DateStrings.m(l2.longValue()));
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public Long F() {
        return this.f14531h;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int h(Context context) {
        return MaterialAttributes.d(context, R.attr.materialCalendarTheme, MaterialDatePicker.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String s(Context context) {
        Resources resources = context.getResources();
        Long l2 = this.f14531h;
        if (l2 == null) {
            return resources.getString(R.string.mtrl_picker_date_header_unselected);
        }
        return resources.getString(R.string.mtrl_picker_date_header_selected, DateStrings.m(l2.longValue()));
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection t() {
        return new ArrayList();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeValue(this.f14531h);
    }
}
