package com.zte.mifavor.custom.internal.app;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import com.zte.extres.R;
import com.zte.mifavor.custom.Config;
import com.zte.mifavor.widget.Utils;

/* loaded from: classes2.dex */
public class AlertDialog {
    private static final String TAG = "Z#AlertDialogCustom";

    public static void AlertDialog_showUI(Object[] objArr) {
        View findViewById;
        View findViewById2;
        View findViewById3;
        Log.d(TAG, "AlertDialog_showUI in. length=" + objArr.length);
        boolean z = false;
        Context context = (Context) objArr[0];
        Dialog dialog = (Dialog) objArr[1];
        if (context == null || dialog == null) {
            Log.w(TAG, "AlertDialog_showUI error, context=" + context + ", dialog=" + dialog);
            return;
        }
        Log.w(TAG, "AlertDialog_showUI in. context=" + context);
        if (dialog.getWindow() != null && dialog.getWindow().getDecorView() != null) {
            if (dialog.getWindow().getDecorView().findViewWithTag("mifavor") != null) {
                z = true;
            } else if (dialog.getWindow().getDecorView().findViewWithTag("is_not_mifavor") != null) {
                Log.w(TAG, "AlertDialog_showUI error, do nothing. is not mifavor.");
                return;
            }
        }
        boolean isMifavorTheme = Config.isMifavorTheme(context);
        if (!isMifavorTheme && !z) {
            Log.w(TAG, "AlertDialog_showUI is not Mifavor Theme. isMifavorTheme is " + isMifavorTheme + ", isSimilarMifavor=" + z);
            return;
        }
        Window window = dialog.getWindow();
        if (window != null) {
            try {
                findViewById = window.findViewById(R.id.options_ipsec_identity);
                findViewById2 = window.findViewById(R.id.ipsec_psk);
                findViewById3 = window.findViewById(R.id.ipsec_user);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (findViewById != null && findViewById2 != null && findViewById3 != null) {
                Log.d(TAG, "AlertDialog_showUI out return for v");
                return;
            }
            String view = window.getDecorView().toString();
            if (view != null && view.contains("Settings") && view.contains("ApnEditorActivity") && window.findViewById(R.id.select_dialog_listview) != null) {
                Log.d(TAG, "AlertDialog_showUI out return for a");
                return;
            }
            Utils.z(window);
            Utils.A(window);
            Utils.C(window);
        } else {
            Log.v(TAG, "AlertDialog_showUI window is null.");
        }
        Log.v(TAG, "AlertDialog_showUI out. dialog=" + dialog);
    }
}
