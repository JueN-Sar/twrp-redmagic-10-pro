package androidx.preference;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;

/* loaded from: classes.dex */
public class ListPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {
    int G0;
    private CharSequence[] H0;
    private CharSequence[] I0;

    private ListPreference y2() {
        return (ListPreference) r2();
    }

    public static ListPreferenceDialogFragmentCompat z2(String str) {
        ListPreferenceDialogFragmentCompat listPreferenceDialogFragmentCompat = new ListPreferenceDialogFragmentCompat();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        listPreferenceDialogFragmentCompat.J1(bundle);
        return listPreferenceDialogFragmentCompat;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void X0(Bundle bundle) {
        super.X0(bundle);
        bundle.putInt("ListPreferenceDialogFragment.index", this.G0);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entries", this.H0);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", this.I0);
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.G0 = bundle.getInt("ListPreferenceDialogFragment.index", 0);
            this.H0 = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entries");
            this.I0 = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
            return;
        }
        ListPreference y2 = y2();
        if (y2.H0() == null || y2.J0() == null) {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
        }
        this.G0 = y2.G0(y2.K0());
        this.H0 = y2.H0();
        this.I0 = y2.J0();
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public void v2(boolean z) {
        int i2;
        ListPreference y2 = y2();
        if (!z || (i2 = this.G0) < 0) {
            return;
        }
        String charSequence = this.I0[i2].toString();
        if (y2.c(charSequence)) {
            y2.M0(charSequence);
        }
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    protected void w2(AlertDialog.Builder builder) {
        super.w2(builder);
        builder.l(this.H0, this.G0, new DialogInterface.OnClickListener() { // from class: androidx.preference.ListPreferenceDialogFragmentCompat.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                ListPreferenceDialogFragmentCompat listPreferenceDialogFragmentCompat = ListPreferenceDialogFragmentCompat.this;
                listPreferenceDialogFragmentCompat.G0 = i2;
                listPreferenceDialogFragmentCompat.onClick(dialogInterface, -1);
                dialogInterface.dismiss();
            }
        });
        builder.j(null, null);
    }
}
