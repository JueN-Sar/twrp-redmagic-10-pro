package com.zte.plugin.reminder.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.zte.gameassist.reminder.R;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.plugin.reminder.GameReminderWindowManager;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes2.dex */
public abstract class RequestPermissionActivityBase extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {

    /* renamed from: c, reason: collision with root package name */
    private static final HashMap f18091c;

    /* renamed from: h, reason: collision with root package name */
    private static PermissionGrantedListener f18092h;

    static {
        HashMap hashMap = new HashMap();
        f18091c = hashMap;
        hashMap.put("org.codeaurora.permission.POWER_OFF_ALARM", Integer.valueOf(R.string.game_reminder_power_off_alarm));
    }

    protected static boolean b(Context context, String[] strArr) {
        for (String str : strArr) {
            if (ContextCompat.a(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean c(String[] strArr, int[] iArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (iArr[i2] != 0 && d(strArr[i2])) {
                return false;
            }
        }
        return true;
    }

    private boolean d(String str) {
        return Arrays.asList(a()).contains(str);
    }

    public static void h(PermissionGrantedListener permissionGrantedListener) {
        f18092h = permissionGrantedListener;
    }

    protected static boolean i(Context context, String[] strArr, Class cls) {
        if (b(context, strArr)) {
            return false;
        }
        Intent intent = new Intent(context, (Class<?>) cls);
        intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        context.startActivity(intent);
        return true;
    }

    protected abstract String[] a();

    protected void e(boolean z) {
        f(z);
        finish();
    }

    public void f(boolean z) {
        GameReminderWindowManager.G(this).E();
        PermissionGrantedListener permissionGrantedListener = f18092h;
        if (permissionGrantedListener != null) {
            permissionGrantedListener.a(z);
        }
    }

    protected void g() {
        ArrayList arrayList = new ArrayList();
        for (String str : a()) {
            if (ContextCompat.a(this, str) != 0) {
                arrayList.add(str);
            }
        }
        if (arrayList.size() == 0) {
            throw new RuntimeException("Request permission activity was called even though all permissions are satisfied.");
        }
        ActivityCompat.p(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 1);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            g();
        }
    }

    @Override // android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        SharedPreferencesUtil.k(this).S(true);
        if (strArr != null && strArr.length > 0 && c(strArr, iArr)) {
            e(true);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if (iArr[i3] != 0 && d(strArr[i3])) {
                HashMap hashMap = f18091c;
                if (!arrayList.contains(hashMap.get(strArr[i3]))) {
                    arrayList.add((Integer) hashMap.get(strArr[i3]));
                }
            }
        }
        if (arrayList.size() > 0) {
            e(false);
        }
    }
}
