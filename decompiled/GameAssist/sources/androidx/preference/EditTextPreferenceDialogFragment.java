package androidx.preference;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/* loaded from: classes.dex */
public class EditTextPreferenceDialogFragment extends PreferenceDialogFragment {

    /* renamed from: o, reason: collision with root package name */
    private EditText f4648o;

    /* renamed from: p, reason: collision with root package name */
    private CharSequence f4649p;

    private EditTextPreference h() {
        return (EditTextPreference) a();
    }

    public static EditTextPreferenceDialogFragment i(String str) {
        EditTextPreferenceDialogFragment editTextPreferenceDialogFragment = new EditTextPreferenceDialogFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        editTextPreferenceDialogFragment.setArguments(bundle);
        return editTextPreferenceDialogFragment;
    }

    @Override // androidx.preference.PreferenceDialogFragment
    protected boolean b() {
        return true;
    }

    @Override // androidx.preference.PreferenceDialogFragment
    protected void c(View view) {
        super.c(view);
        EditText editText = (EditText) view.findViewById(android.R.id.edit);
        this.f4648o = editText;
        editText.requestFocus();
        EditText editText2 = this.f4648o;
        if (editText2 == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        }
        editText2.setText(this.f4649p);
        EditText editText3 = this.f4648o;
        editText3.setSelection(editText3.getText().length());
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public void e(boolean z) {
        if (z) {
            String obj = this.f4648o.getText().toString();
            if (h().c(obj)) {
                h().H0(obj);
            }
        }
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.f4649p = h().G0();
        } else {
            this.f4649p = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
        }
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", this.f4649p);
    }
}
