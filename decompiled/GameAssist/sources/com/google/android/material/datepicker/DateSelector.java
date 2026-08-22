package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.RestrictTo;
import com.google.android.material.internal.ViewUtils;
import java.util.Collection;

@RestrictTo
/* loaded from: classes.dex */
public interface DateSelector<S> extends Parcelable {
    static void A(final EditText... editTextArr) {
        if (editTextArr.length == 0) {
            return;
        }
        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.google.android.material.datepicker.c
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                DateSelector.p(editTextArr, view, z);
            }
        };
        for (EditText editText : editTextArr) {
            editText.setOnFocusChangeListener(onFocusChangeListener);
        }
        final EditText editText2 = editTextArr[0];
        editText2.postDelayed(new Runnable() { // from class: com.google.android.material.datepicker.d
            @Override // java.lang.Runnable
            public final void run() {
                ViewUtils.v(editText2, false);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static /* synthetic */ void p(EditText[] editTextArr, View view, boolean z) {
        for (EditText editText : editTextArr) {
            if (editText.hasFocus()) {
                return;
            }
        }
        ViewUtils.o(view, false);
    }

    View B(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, OnSelectionChangedListener onSelectionChangedListener);

    boolean D();

    Collection E();

    Object F();

    void L(long j2);

    String e(Context context);

    int h(Context context);

    String s(Context context);

    Collection t();
}
