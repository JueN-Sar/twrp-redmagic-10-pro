package cn.nubia.componentsdk.pay;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import cn.nubia.componentsdk.until.PayLog;
import com.zte.shared.wrapper.VirtualHandleWrapper;

/* loaded from: classes.dex */
public class BasicDialogFragment extends DialogFragment {
    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        PayLog.a("BasicDialog", "BasicDialog onCreate run ！");
        super.onCreate(bundle);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        PayLog.a("BasicDialog", "BasicDialog onCreateDialog run ！");
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(Util.c(getContext(), "loading_dialog", "layout"), (ViewGroup) null).findViewById(Util.c(getContext(), "dialog_view", VirtualHandleWrapper.KEY_ID));
        Dialog dialog = new Dialog(getActivity(), Util.c(getContext(), "loading_dialog", "style"));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
        return dialog;
    }
}
