package com.zte.mifavor.custom.preference;

import android.R;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.zte.mifavor.custom.Config;

/* loaded from: classes2.dex */
public class EditTextPreference {
    private static final String TAG = "EditTextPreference";

    public static Object onAddEditTextToDialogView_getContainer(Object[] objArr) {
        EditText editText;
        View view = (View) objArr[0];
        View view2 = (View) objArr[1];
        if (!Config.isMifavorTheme(view.getContext())) {
            return view2;
        }
        Log.d(TAG, "onAddEditTextToDialogView_getContainer");
        if (objArr.length > 2 && (editText = (EditText) objArr[2]) != null) {
            editText.requestFocus();
        }
        return view2 == null ? view.findViewById(R.id.content) : view2;
    }
}
