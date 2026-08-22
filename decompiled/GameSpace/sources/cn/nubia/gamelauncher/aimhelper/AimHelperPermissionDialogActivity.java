package cn.nubia.gamelauncher.aimhelper;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import cn.nubia.common.app.AlertDialogCenter;
import cn.nubia.gamelauncher.R;

/* loaded from: classes.dex */
public class AimHelperPermissionDialogActivity extends Activity {
    private AlertDialogCenter mDialog;

    /* JADX INFO: Access modifiers changed from: private */
    public void startFloatingWindowPermissionActivity() {
        Intent intent = new Intent();
        intent.setAction("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setData(Uri.parse("package:" + getPackageName()));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialogCenter create = new AlertDialogCenter.Builder(this, 2131952381).setTitle(getString(R.string.floating_permission_dialog_message)).setPositiveButton(R.string.open_now, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.aimhelper.AimHelperPermissionDialogActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                AimHelperPermissionDialogActivity.this.startFloatingWindowPermissionActivity();
                dialogInterface.dismiss();
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.aimhelper.AimHelperPermissionDialogActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.nubia.gamelauncher.aimhelper.AimHelperPermissionDialogActivity.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                AimHelperPermissionDialogActivity.this.finish();
            }
        }).create();
        this.mDialog = create;
        create.show();
    }

    protected void onDestory() {
        AlertDialogCenter alertDialogCenter = this.mDialog;
        if (alertDialogCenter != null) {
            alertDialogCenter.dismiss();
        }
    }
}
