package androidx.preference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.preference.internal.AbstractMultiSelectListPreference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class MultiSelectListPreferenceDialogFragment extends PreferenceDialogFragment {

    /* renamed from: o, reason: collision with root package name */
    Set f4657o = new HashSet();

    /* renamed from: p, reason: collision with root package name */
    boolean f4658p;

    /* renamed from: q, reason: collision with root package name */
    CharSequence[] f4659q;

    /* renamed from: r, reason: collision with root package name */
    CharSequence[] f4660r;

    private AbstractMultiSelectListPreference h() {
        return (AbstractMultiSelectListPreference) a();
    }

    public static MultiSelectListPreferenceDialogFragment i(String str) {
        MultiSelectListPreferenceDialogFragment multiSelectListPreferenceDialogFragment = new MultiSelectListPreferenceDialogFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        multiSelectListPreferenceDialogFragment.setArguments(bundle);
        return multiSelectListPreferenceDialogFragment;
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public void e(boolean z) {
        AbstractMultiSelectListPreference h2 = h();
        if (z && this.f4658p) {
            Set set = this.f4657o;
            if (h2.c(set)) {
                h2.J0(set);
            }
        }
        this.f4658p = false;
    }

    @Override // androidx.preference.PreferenceDialogFragment
    protected void f(AlertDialog.Builder builder) {
        super.f(builder);
        int length = this.f4660r.length;
        boolean[] zArr = new boolean[length];
        for (int i2 = 0; i2 < length; i2++) {
            zArr[i2] = this.f4657o.contains(this.f4660r[i2].toString());
        }
        builder.setMultiChoiceItems(this.f4659q, zArr, new DialogInterface.OnMultiChoiceClickListener() { // from class: androidx.preference.MultiSelectListPreferenceDialogFragment.1
            @Override // android.content.DialogInterface.OnMultiChoiceClickListener
            public void onClick(DialogInterface dialogInterface, int i3, boolean z) {
                if (z) {
                    MultiSelectListPreferenceDialogFragment multiSelectListPreferenceDialogFragment = MultiSelectListPreferenceDialogFragment.this;
                    multiSelectListPreferenceDialogFragment.f4658p |= multiSelectListPreferenceDialogFragment.f4657o.add(multiSelectListPreferenceDialogFragment.f4660r[i3].toString());
                } else {
                    MultiSelectListPreferenceDialogFragment multiSelectListPreferenceDialogFragment2 = MultiSelectListPreferenceDialogFragment.this;
                    multiSelectListPreferenceDialogFragment2.f4658p |= multiSelectListPreferenceDialogFragment2.f4657o.remove(multiSelectListPreferenceDialogFragment2.f4660r[i3].toString());
                }
            }
        });
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f4657o.clear();
            this.f4657o.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragment.values"));
            this.f4658p = bundle.getBoolean("MultiSelectListPreferenceDialogFragment.changed", false);
            this.f4659q = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries");
            this.f4660r = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues");
            return;
        }
        AbstractMultiSelectListPreference h2 = h();
        if (h2.G0() == null || h2.H0() == null) {
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        this.f4657o.clear();
        this.f4657o.addAll(h2.I0());
        this.f4658p = false;
        this.f4659q = h2.G0();
        this.f4660r = h2.H0();
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragment.values", new ArrayList<>(this.f4657o));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragment.changed", this.f4658p);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries", this.f4659q);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues", this.f4660r);
    }
}
