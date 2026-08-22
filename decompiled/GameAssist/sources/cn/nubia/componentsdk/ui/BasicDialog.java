package cn.nubia.componentsdk.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import cn.nubia.componentsdk.until.PayLog;

/* loaded from: classes.dex */
public class BasicDialog extends DialogFragment {
    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        PayLog.a("BasicDialog", "BasicDialog onCreateDialog run ！");
        return ProgressDialog.show(getActivity(), "", getArguments() != null ? getArguments().getString("MSG") : "正在检测新版本", true);
    }
}
