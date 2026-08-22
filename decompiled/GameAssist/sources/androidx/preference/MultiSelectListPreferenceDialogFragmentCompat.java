package androidx.preference;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.internal.AbstractMultiSelectListPreference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class MultiSelectListPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {
    Set G0 = new HashSet();
    boolean H0;
    CharSequence[] I0;
    CharSequence[] J0;

    private AbstractMultiSelectListPreference y2() {
        return (AbstractMultiSelectListPreference) r2();
    }

    public static MultiSelectListPreferenceDialogFragmentCompat z2(String str) {
        MultiSelectListPreferenceDialogFragmentCompat multiSelectListPreferenceDialogFragmentCompat = new MultiSelectListPreferenceDialogFragmentCompat();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        multiSelectListPreferenceDialogFragmentCompat.J1(bundle);
        return multiSelectListPreferenceDialogFragmentCompat;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void X0(Bundle bundle) {
        super.X0(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values", new ArrayList<>(this.G0));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", this.H0);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries", this.I0);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues", this.J0);
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.G0.clear();
            this.G0.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values"));
            this.H0 = bundle.getBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", false);
            this.I0 = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries");
            this.J0 = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues");
            return;
        }
        AbstractMultiSelectListPreference y2 = y2();
        if (y2.G0() == null || y2.H0() == null) {
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        this.G0.clear();
        this.G0.addAll(y2.I0());
        this.H0 = false;
        this.I0 = y2.G0();
        this.J0 = y2.H0();
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public void v2(boolean z) {
        AbstractMultiSelectListPreference y2 = y2();
        if (z && this.H0) {
            Set set = this.G0;
            if (y2.c(set)) {
                y2.J0(set);
            }
        }
        this.H0 = false;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    protected void w2(AlertDialog.Builder builder) {
        super.w2(builder);
        int length = this.J0.length;
        boolean[] zArr = new boolean[length];
        for (int i2 = 0; i2 < length; i2++) {
            zArr[i2] = this.G0.contains(this.J0[i2].toString());
        }
        builder.g(this.I0, zArr, new DialogInterface.OnMultiChoiceClickListener() { // from class: androidx.preference.MultiSelectListPreferenceDialogFragmentCompat.1
            @Override // android.content.DialogInterface.OnMultiChoiceClickListener
            public void onClick(DialogInterface dialogInterface, int i3, boolean z) {
                if (z) {
                    MultiSelectListPreferenceDialogFragmentCompat multiSelectListPreferenceDialogFragmentCompat = MultiSelectListPreferenceDialogFragmentCompat.this;
                    multiSelectListPreferenceDialogFragmentCompat.H0 |= multiSelectListPreferenceDialogFragmentCompat.G0.add(multiSelectListPreferenceDialogFragmentCompat.J0[i3].toString());
                } else {
                    MultiSelectListPreferenceDialogFragmentCompat multiSelectListPreferenceDialogFragmentCompat2 = MultiSelectListPreferenceDialogFragmentCompat.this;
                    multiSelectListPreferenceDialogFragmentCompat2.H0 |= multiSelectListPreferenceDialogFragmentCompat2.G0.remove(multiSelectListPreferenceDialogFragmentCompat2.J0[i3].toString());
                }
            }
        });
    }
}
