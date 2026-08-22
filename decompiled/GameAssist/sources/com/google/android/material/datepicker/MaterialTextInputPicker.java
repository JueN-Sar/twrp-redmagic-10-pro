package com.google.android.material.datepicker;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.Iterator;

@RestrictTo
/* loaded from: classes.dex */
public final class MaterialTextInputPicker<S> extends PickerFragment<S> {
    private int j0;
    private DateSelector k0;
    private CalendarConstraints l0;

    static MaterialTextInputPicker c2(DateSelector dateSelector, int i2, CalendarConstraints calendarConstraints) {
        MaterialTextInputPicker materialTextInputPicker = new MaterialTextInputPicker();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i2);
        bundle.putParcelable("DATE_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        materialTextInputPicker.J1(bundle);
        return materialTextInputPicker;
    }

    @Override // androidx.fragment.app.Fragment
    public View H0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.k0.B(layoutInflater.cloneInContext(new ContextThemeWrapper(z(), this.j0)), viewGroup, bundle, this.l0, new OnSelectionChangedListener<S>() { // from class: com.google.android.material.datepicker.MaterialTextInputPicker.1
            @Override // com.google.android.material.datepicker.OnSelectionChangedListener
            public void a() {
                Iterator it = MaterialTextInputPicker.this.i0.iterator();
                while (it.hasNext()) {
                    ((OnSelectionChangedListener) it.next()).a();
                }
            }

            @Override // com.google.android.material.datepicker.OnSelectionChangedListener
            public void b(Object obj) {
                Iterator it = MaterialTextInputPicker.this.i0.iterator();
                while (it.hasNext()) {
                    ((OnSelectionChangedListener) it.next()).b(obj);
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void X0(Bundle bundle) {
        super.X0(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.j0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.k0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.l0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = x();
        }
        this.j0 = bundle.getInt("THEME_RES_ID_KEY");
        this.k0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.l0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }
}
