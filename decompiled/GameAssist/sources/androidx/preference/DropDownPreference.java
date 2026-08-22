package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/* loaded from: classes.dex */
public class DropDownPreference extends ListPreference {
    private final Context d0;
    private final ArrayAdapter e0;
    private Spinner f0;
    private final AdapterView.OnItemSelectedListener g0;

    public DropDownPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.dropdownPreferenceStyle);
    }

    private void P0() {
        this.e0.clear();
        if (H0() != null) {
            for (CharSequence charSequence : H0()) {
                this.e0.add(charSequence.toString());
            }
        }
    }

    @Override // androidx.preference.Preference
    protected void N() {
        super.N();
        this.e0.notifyDataSetChanged();
    }

    protected ArrayAdapter N0() {
        return new ArrayAdapter(this.d0, android.R.layout.simple_spinner_dropdown_item);
    }

    public int O0(String str) {
        CharSequence[] J0 = J0();
        if (str == null || J0 == null) {
            return -1;
        }
        for (int length = J0.length - 1; length >= 0; length--) {
            if (J0[length].equals(str)) {
                return length;
            }
        }
        return -1;
    }

    @Override // androidx.preference.Preference
    public void R(PreferenceViewHolder preferenceViewHolder) {
        Spinner spinner = (Spinner) preferenceViewHolder.f5252a.findViewById(R.id.spinner);
        this.f0 = spinner;
        spinner.setAdapter((SpinnerAdapter) this.e0);
        this.f0.setOnItemSelectedListener(this.g0);
        this.f0.setSelection(O0(K0()));
        super.R(preferenceViewHolder);
    }

    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    protected void S() {
        this.f0.performClick();
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.g0 = new AdapterView.OnItemSelectedListener() { // from class: androidx.preference.DropDownPreference.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView adapterView, View view, int i4, long j2) {
                if (i4 >= 0) {
                    String charSequence = DropDownPreference.this.J0()[i4].toString();
                    if (charSequence.equals(DropDownPreference.this.K0()) || !DropDownPreference.this.c(charSequence)) {
                        return;
                    }
                    DropDownPreference.this.M0(charSequence);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView adapterView) {
            }
        };
        this.d0 = context;
        this.e0 = N0();
        P0();
    }
}
