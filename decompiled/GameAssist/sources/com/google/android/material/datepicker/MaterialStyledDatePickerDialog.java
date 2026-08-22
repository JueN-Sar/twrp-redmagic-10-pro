package com.google.android.material.datepicker;

import android.app.DatePickerDialog;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.RestrictTo;
import com.google.android.material.R;
import com.google.android.material.dialog.InsetDialogOnTouchListener;

@RestrictTo
/* loaded from: classes.dex */
public class MaterialStyledDatePickerDialog extends DatePickerDialog {

    /* renamed from: i, reason: collision with root package name */
    private static final int f14487i = R.style.MaterialAlertDialog_MaterialComponents_Picker_Date_Spinner;

    /* renamed from: c, reason: collision with root package name */
    private final Drawable f14488c;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f14489h;

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(this.f14488c);
        getWindow().getDecorView().setOnTouchListener(new InsetDialogOnTouchListener(this, this.f14489h));
    }
}
