package cn.nubia.gamecenter.settings.net;

import android.content.Context;
import android.content.DialogInterface;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.gamecenter.settings.R;

/* loaded from: classes.dex */
public class NetAcceleratedDialog extends AlertDialog.Builder {
    public NetAcceleratedDialog(Context context, int i, final DialogInterface.OnClickListener onClickListener) {
        super(context, R.style.Theme_Nubia_Dialog_Alert);
        CharSequence[] netAcceleratedCharSequences = NetAcceleratedConfig.getNetAcceleratedCharSequences(context.getApplicationContext());
        NetAcceleratedConfig.getNetAcceleratedValues(context.getApplicationContext());
        setSingleChoiceItems(netAcceleratedCharSequences, i, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.net.NetAcceleratedDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                DialogInterface.OnClickListener onClickListener2 = onClickListener;
                if (onClickListener2 != null) {
                    onClickListener2.onClick(dialogInterface, i2);
                }
                dialogInterface.dismiss();
            }
        });
        setPositiveButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
    }
}
