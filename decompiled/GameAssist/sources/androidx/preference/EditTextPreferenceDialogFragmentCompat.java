package androidx.preference;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/* loaded from: classes.dex */
public class EditTextPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {
    private EditText G0;
    private CharSequence H0;

    private EditTextPreference y2() {
        return (EditTextPreference) r2();
    }

    public static EditTextPreferenceDialogFragmentCompat z2(String str) {
        EditTextPreferenceDialogFragmentCompat editTextPreferenceDialogFragmentCompat = new EditTextPreferenceDialogFragmentCompat();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        editTextPreferenceDialogFragmentCompat.J1(bundle);
        return editTextPreferenceDialogFragmentCompat;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void X0(Bundle bundle) {
        super.X0(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", this.H0);
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.H0 = y2().G0();
        } else {
            this.H0 = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
        }
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    protected boolean s2() {
        return true;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    protected void t2(View view) {
        super.t2(view);
        EditText editText = (EditText) view.findViewById(android.R.id.edit);
        this.G0 = editText;
        editText.requestFocus();
        EditText editText2 = this.G0;
        if (editText2 == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        }
        editText2.setText(this.H0);
        EditText editText3 = this.G0;
        editText3.setSelection(editText3.getText().length());
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public void v2(boolean z) {
        if (z) {
            String obj = this.G0.getText().toString();
            if (y2().c(obj)) {
                y2().H0(obj);
            }
        }
    }
}
