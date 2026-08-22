package androidx.preference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

/* loaded from: classes.dex */
public class ListPreferenceDialogFragment extends PreferenceDialogFragment {

    /* renamed from: o, reason: collision with root package name */
    int f4651o;

    /* renamed from: p, reason: collision with root package name */
    private CharSequence[] f4652p;

    /* renamed from: q, reason: collision with root package name */
    private CharSequence[] f4653q;

    private ListPreference h() {
        return (ListPreference) a();
    }

    public static ListPreferenceDialogFragment i(String str) {
        ListPreferenceDialogFragment listPreferenceDialogFragment = new ListPreferenceDialogFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        listPreferenceDialogFragment.setArguments(bundle);
        return listPreferenceDialogFragment;
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public void e(boolean z) {
        int i2;
        ListPreference h2 = h();
        if (!z || (i2 = this.f4651o) < 0) {
            return;
        }
        String charSequence = this.f4653q[i2].toString();
        if (h2.c(charSequence)) {
            h2.M0(charSequence);
        }
    }

    @Override // androidx.preference.PreferenceDialogFragment
    protected void f(AlertDialog.Builder builder) {
        super.f(builder);
        builder.setSingleChoiceItems(this.f4652p, this.f4651o, new DialogInterface.OnClickListener() { // from class: androidx.preference.ListPreferenceDialogFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                ListPreferenceDialogFragment listPreferenceDialogFragment = ListPreferenceDialogFragment.this;
                listPreferenceDialogFragment.f4651o = i2;
                listPreferenceDialogFragment.onClick(dialogInterface, -1);
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton((CharSequence) null, (DialogInterface.OnClickListener) null);
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f4651o = bundle.getInt("ListPreferenceDialogFragment.index", 0);
            this.f4652p = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entries");
            this.f4653q = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
            return;
        }
        ListPreference h2 = h();
        if (h2.H0() == null || h2.J0() == null) {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
        }
        this.f4651o = h2.G0(h2.K0());
        this.f4652p = h2.H0();
        this.f4653q = h2.J0();
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("ListPreferenceDialogFragment.index", this.f4651o);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entries", this.f4652p);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", this.f4653q);
    }
}
