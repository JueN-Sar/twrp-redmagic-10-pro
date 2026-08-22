package com.zte.mifavor.widget;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.zte.extres.R;
import com.zte.mifavor.widget.AlertDialog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class PermissionActivity extends ActivityZTE {

    /* renamed from: j, reason: collision with root package name */
    private AlertDialog f17718j;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(DialogInterface dialogInterface, int i2) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
        dialogInterface.dismiss();
        finish();
    }

    private void f() {
        Log.d("Z#Search_PermissionActivity", "sendBroadcast START_VOICE_ACTION... ...");
        sendBroadcast(new Intent(VoiceSearchViewZTE.START_VOICE_ACTION));
    }

    private AlertDialog g(List list) {
        StringBuilder sb = new StringBuilder(getString(R.string.permissions_denied));
        Iterator it = list.iterator();
        int i2 = 1;
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            sb.append('\n');
            sb.append(i2);
            sb.append(". ");
            sb.append(getString(intValue));
            i2++;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.l(R.string.authority_title);
        builder.e(sb.toString());
        builder.f(R.string.button_cancel, new DialogInterface.OnClickListener() { // from class: com.zte.mifavor.widget.h
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                PermissionActivity.this.d(dialogInterface, i3);
            }
        });
        builder.i(R.string.button_set, new DialogInterface.OnClickListener() { // from class: com.zte.mifavor.widget.i
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                PermissionActivity.this.e(dialogInterface, i3);
            }
        });
        return builder.o();
    }

    @Override // com.zte.mifavor.widget.ActivityZTE, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(null);
        String[] stringArrayExtra = getIntent().getStringArrayExtra("required_permissions");
        if (stringArrayExtra != null) {
            Log.d("Z#Search_PermissionActivity", "requiredPermissions size=" + stringArrayExtra.length);
            requestPermissions(stringArrayExtra, 1000);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (this.f17718j != null) {
            Log.d("Z#Search_PermissionActivity", "onDestroy: dismiss mMissingPermissionDialog");
            this.f17718j.dismiss();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 != 1000) {
            finish();
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        for (int i4 = 0; i4 < strArr.length; i4++) {
            Log.d("Z#Search_PermissionActivity", "getPermissionsNeededName, permission[" + i4 + "]=" + strArr[i4] + " grantResults[" + i4 + "]=" + iArr[i4]);
            String str = strArr[i4];
            if (iArr[i4] == 0) {
                i3++;
            } else if (!shouldShowRequestPermissionRationale(str)) {
                str.hashCode();
                if (str.equals("android.permission.RECORD_AUDIO")) {
                    Log.e("Z#Search_PermissionActivity", "RECORD_AUDIO is not granted!!!");
                }
            }
        }
        if (i3 != strArr.length) {
            this.f17718j = g(arrayList);
            return;
        }
        Log.d("Z#Search_PermissionActivity", "All requested permission are granted");
        f();
        finish();
    }
}
