package com.google.android.material.timepicker;

import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

/* loaded from: classes.dex */
class TimePickerTextInputKeyController implements TextView.OnEditorActionListener, View.OnKeyListener {

    /* renamed from: c, reason: collision with root package name */
    private final ChipTextInputComboView f15492c;

    /* renamed from: h, reason: collision with root package name */
    private final ChipTextInputComboView f15493h;

    /* renamed from: i, reason: collision with root package name */
    private final TimeModel f15494i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f15495j = false;

    TimePickerTextInputKeyController(ChipTextInputComboView chipTextInputComboView, ChipTextInputComboView chipTextInputComboView2, TimeModel timeModel) {
        this.f15492c = chipTextInputComboView;
        this.f15493h = chipTextInputComboView2;
        this.f15494i = timeModel;
    }

    private void b(EditText editText) {
        if (editText.getSelectionStart() == 0 && editText.length() == 2) {
            editText.getText().clear();
        }
    }

    private void c(int i2) {
        this.f15493h.setChecked(i2 == 12);
        this.f15492c.setChecked(i2 == 10);
        this.f15494i.f15480l = i2;
    }

    private boolean d(int i2, KeyEvent keyEvent, EditText editText) {
        Editable text = editText.getText();
        if (text == null) {
            return false;
        }
        if (i2 >= 7 && i2 <= 16 && keyEvent.getAction() == 1 && editText.getSelectionStart() == 2 && text.length() == 2) {
            c(12);
            return true;
        }
        b(editText);
        return false;
    }

    private boolean e(int i2, KeyEvent keyEvent, EditText editText) {
        if (i2 == 67 && keyEvent.getAction() == 0 && TextUtils.isEmpty(editText.getText())) {
            c(10);
            return true;
        }
        b(editText);
        return false;
    }

    public void a() {
        TextInputLayout e2 = this.f15492c.e();
        TextInputLayout e3 = this.f15493h.e();
        EditText editText = e2.getEditText();
        EditText editText2 = e3.getEditText();
        editText.setImeOptions(268435461);
        editText2.setImeOptions(268435462);
        editText.setOnEditorActionListener(this);
        editText.setOnKeyListener(this);
        editText2.setOnKeyListener(this);
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
        boolean z = i2 == 5;
        if (z) {
            c(12);
        }
        return z;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (this.f15495j) {
            return false;
        }
        this.f15495j = true;
        EditText editText = (EditText) view;
        boolean e2 = this.f15494i.f15480l == 12 ? e(i2, keyEvent, editText) : d(i2, keyEvent, editText);
        this.f15495j = false;
        return e2;
    }
}
