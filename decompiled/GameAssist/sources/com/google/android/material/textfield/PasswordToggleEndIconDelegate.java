package com.google.android.material.textfield;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.R;

/* loaded from: classes.dex */
class PasswordToggleEndIconDelegate extends EndIconDelegate {

    /* renamed from: e, reason: collision with root package name */
    private int f15439e;

    /* renamed from: f, reason: collision with root package name */
    private EditText f15440f;

    /* renamed from: g, reason: collision with root package name */
    private final View.OnClickListener f15441g;

    PasswordToggleEndIconDelegate(EndCompoundLayout endCompoundLayout, int i2) {
        super(endCompoundLayout);
        this.f15439e = R.drawable.design_password_eye;
        this.f15441g = new View.OnClickListener() { // from class: com.google.android.material.textfield.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PasswordToggleEndIconDelegate.this.y(view);
            }
        };
        if (i2 != 0) {
            this.f15439e = i2;
        }
    }

    private boolean w() {
        EditText editText = this.f15440f;
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    private static boolean x(EditText editText) {
        return editText != null && (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(View view) {
        EditText editText = this.f15440f;
        if (editText == null) {
            return;
        }
        int selectionEnd = editText.getSelectionEnd();
        if (w()) {
            this.f15440f.setTransformationMethod(null);
        } else {
            this.f15440f.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        if (selectionEnd >= 0) {
            this.f15440f.setSelection(selectionEnd);
        }
        r();
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void b(CharSequence charSequence, int i2, int i3, int i4) {
        r();
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    int c() {
        return R.string.password_toggle_content_description;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    int d() {
        return this.f15439e;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    View.OnClickListener f() {
        return this.f15441g;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    boolean l() {
        return true;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    boolean m() {
        return !w();
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void n(EditText editText) {
        this.f15440f = editText;
        r();
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void s() {
        if (x(this.f15440f)) {
            this.f15440f.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void u() {
        EditText editText = this.f15440f;
        if (editText != null) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
