package com.zte.gameassist.permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.zte.extres.R;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.mifavor.widget.AlertDialog;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes2.dex */
public class PermissionApplyActivity extends Activity implements RotationMgr.Callback {

    /* renamed from: i, reason: collision with root package name */
    private static RequestPermissionListener f17028i;

    /* renamed from: c, reason: collision with root package name */
    private AlertDialog f17029c;

    /* renamed from: h, reason: collision with root package name */
    private SharedPreferencesUtil f17030h;

    public interface RequestPermissionListener {
        default void a() {
        }

        void onSuccess();
    }

    private String d(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(getPackageName(), 128)).toString();
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private boolean f(String[] strArr) {
        for (String str : strArr) {
            if (ContextCompat.a(this, str) != 0) {
                return false;
            }
        }
        return true;
    }

    private void g() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", "cn.nubia.gameassist", null));
        intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(DialogInterface dialogInterface, int i2) {
        g();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(DialogInterface dialogInterface, int i2) {
        c();
        RequestPermissionListener requestPermissionListener = f17028i;
        if (requestPermissionListener != null) {
            requestPermissionListener.a();
        }
    }

    private void j(String[] strArr) {
        AlertDialog alertDialog = this.f17029c;
        if (alertDialog == null || !alertDialog.isShowing()) {
            AlertDialog a2 = new AlertDialog.Builder(this, R.style.Theme_ZTE_Light_Dialog_Alert).m(getString(com.zte.gameassist.common.R.string.permission_management)).c(true).e(e(strArr)).b(false).i(com.zte.gameassist.common.R.string.set_up, new DialogInterface.OnClickListener() { // from class: com.zte.gameassist.permission.a
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    PermissionApplyActivity.this.h(dialogInterface, i2);
                }
            }).f(com.zte.gameassist.common.R.string.decline, new DialogInterface.OnClickListener() { // from class: com.zte.gameassist.permission.b
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    PermissionApplyActivity.this.i(dialogInterface, i2);
                }
            }).a();
            this.f17029c = a2;
            a2.getWindow().setType(2038);
            this.f17029c.show();
            GameAssistDialog.f(this.f17029c.getWindow());
            this.f17029c.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    public static void k(Context context, RequestPermissionListener requestPermissionListener, String[] strArr) {
        f17028i = requestPermissionListener;
        Intent intent = new Intent(context, (Class<?>) PermissionApplyActivity.class);
        intent.putExtra("permission", strArr);
        intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        context.startActivity(intent);
    }

    public void c() {
        AlertDialog alertDialog = this.f17029c;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f17029c.dismiss();
        }
        finish();
    }

    public String e(String[] strArr) {
        PackageManager packageManager = getPackageManager();
        StringBuilder sb = new StringBuilder();
        sb.append((char) 8220);
        sb.append(d(this));
        sb.append((char) 8221);
        sb.append(" ");
        sb.append(getString(com.zte.gameassist.common.R.string.permission_management_content));
        try {
            for (String str : strArr) {
                sb.append("\n");
                sb.append(packageManager.getPermissionInfo(str, 0).loadLabel(packageManager));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return sb.toString();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        String[] strArr = (String[]) intent.getExtras().get("permission");
        this.f17030h = SharedPreferencesUtil.k(this);
        if (f(strArr)) {
            RequestPermissionListener requestPermissionListener = f17028i;
            if (requestPermissionListener != null) {
                requestPermissionListener.onSuccess();
            }
            finish();
        } else if (this.f17030h.t(strArr[0]) >= 2) {
            j(strArr);
        } else {
            ActivityCompat.p(this, strArr, 1);
        }
        RotationMgr.e(this).c(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        RotationMgr.e(this).p(this);
        f17028i = null;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (i2 == 1) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                if (strArr.length > 0) {
                    SharedPreferencesUtil sharedPreferencesUtil = this.f17030h;
                    String str = strArr[0];
                    sharedPreferencesUtil.F(str, sharedPreferencesUtil.t(str) + 1);
                }
                RequestPermissionListener requestPermissionListener = f17028i;
                if (requestPermissionListener != null) {
                    requestPermissionListener.a();
                }
            } else {
                RequestPermissionListener requestPermissionListener2 = f17028i;
                if (requestPermissionListener2 != null) {
                    requestPermissionListener2.onSuccess();
                }
            }
        }
        finish();
    }

    @Override // com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        AlertDialog alertDialog = this.f17029c;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        GameAssistDialog.f(this.f17029c.getWindow());
    }
}
