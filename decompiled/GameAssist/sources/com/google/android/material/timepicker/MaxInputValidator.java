package com.google.android.material.timepicker;

import android.text.InputFilter;
import android.text.Spanned;

/* loaded from: classes.dex */
class MaxInputValidator implements InputFilter {

    /* renamed from: a, reason: collision with root package name */
    private int f15474a;

    public MaxInputValidator(int i2) {
        this.f15474a = i2;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        try {
            StringBuilder sb = new StringBuilder(spanned);
            sb.replace(i4, i5, charSequence.subSequence(i2, i3).toString());
            if (Integer.parseInt(sb.toString()) <= this.f15474a) {
                return null;
            }
            return "";
        } catch (NumberFormatException unused) {
            return "";
        }
    }
}
