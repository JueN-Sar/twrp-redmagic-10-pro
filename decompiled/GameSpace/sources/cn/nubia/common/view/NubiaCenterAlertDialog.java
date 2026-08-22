package cn.nubia.common.view;

import android.content.Context;
import android.content.DialogInterface;
import cn.nubia.common.app.AlertDialog;

/* loaded from: classes.dex */
public class NubiaCenterAlertDialog extends AlertDialog {
    private static final String TAG = "NubiaCenterAlertDialog";

    protected NubiaCenterAlertDialog(Context context) {
        super(context);
    }

    protected NubiaCenterAlertDialog(Context context, int i) {
        super(context, i);
    }

    protected NubiaCenterAlertDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }
}
