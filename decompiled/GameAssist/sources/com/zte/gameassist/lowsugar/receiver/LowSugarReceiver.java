package com.zte.gameassist.lowsugar.receiver;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.lowsugar.LowSugarApplication;
import com.zte.gameassist.lowsugar.ai.LowSugarAiMgr;
import com.zte.gameassist.lowsugar.provider.LowSugarColumn;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes2.dex */
public class LowSugarReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    protected final Handler f16956a = new Handler(ThreadManager.c().b());

    private void a(Intent intent) {
        LowSugarAiMgr.F().H(intent);
    }

    private void b(Intent intent) {
        final String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
        GaLog.e("LowSugarReceiver", "handlePackageRemoved packageName: " + encodedSchemeSpecificPart);
        if (TextUtils.isEmpty(encodedSchemeSpecificPart) || !LowSugarUtils.v.contains(encodedSchemeSpecificPart)) {
            return;
        }
        this.f16956a.post(new Runnable(this) { // from class: com.zte.gameassist.lowsugar.receiver.LowSugarReceiver.2
            @Override // java.lang.Runnable
            public void run() {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_exist", (Integer) 1);
                LowSugarApplication.c().b().getContentResolver().update(LowSugarColumn.f16922a, contentValues, "package=?", new String[]{encodedSchemeSpecificPart});
            }
        });
    }

    private void c(Intent intent) {
        final String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
        GaLog.e("LowSugarReceiver", "handlePackageRemoved packageName: " + encodedSchemeSpecificPart);
        if (TextUtils.isEmpty(encodedSchemeSpecificPart) || !LowSugarUtils.v.contains(encodedSchemeSpecificPart)) {
            return;
        }
        this.f16956a.post(new Runnable(this) { // from class: com.zte.gameassist.lowsugar.receiver.LowSugarReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_exist", (Integer) 0);
                LowSugarApplication.c().b().getContentResolver().update(LowSugarColumn.f16922a, contentValues, "package=?", new String[]{encodedSchemeSpecificPart});
            }
        });
    }

    public void d(Context context) {
        GaLog.a("LowSugarReceiver", "register");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(this, intentFilter, 2);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.zte.gameassist.lowsugar.OPERATION");
        context.registerReceiver(this, intentFilter2, 2);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        GaLog.a("LowSugarReceiver", " onReceive action is " + action);
        if ("com.zte.gameassist.lowsugar.OPERATION".equals(action)) {
            a(intent);
        }
        if ("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action)) {
            String action2 = intent.getAction();
            action2.hashCode();
            switch (action2) {
                case "android.intent.action.PACKAGE_REPLACED":
                case "android.intent.action.PACKAGE_ADDED":
                    b(intent);
                    break;
                case "android.intent.action.PACKAGE_REMOVED":
                    c(intent);
                    break;
            }
        }
    }
}
