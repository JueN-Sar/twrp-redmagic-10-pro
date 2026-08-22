package com.zte.gameassist.lowsugar.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class AccountChangeReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    protected List f16955a = new ArrayList();

    public interface AccountChangeCallback {
        default void a(Context context, String str) {
        }
    }

    public void a(AccountChangeCallback accountChangeCallback) {
        if (this.f16955a.contains(accountChangeCallback)) {
            return;
        }
        this.f16955a.add(accountChangeCallback);
    }

    public void b(Context context) {
        GaLog.a("AccountChangeReceiver", "register");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("org_zx_AuthComp_zte_account_logout");
        intentFilter.addAction("org_zx_AuthComp_zte_account_login");
        intentFilter.addAction("cn.nubia.account.broadcastchange");
        intentFilter.addAction("zte_account_change");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        GaLog.a("AccountChangeReceiver", " onReceive action is " + action);
        if ("org_zx_AuthComp_zte_account_logout".equals(action) || "org_zx_AuthComp_zte_account_login".equals(action) || "zte_account_change".equals(action) || "cn.nubia.account.broadcastchange".equals(action)) {
            String stringExtra = intent.getStringExtra("change");
            String str = "logout";
            if ("org_zx_AuthComp_zte_account_logout".equals(action) || "logout".equals(stringExtra)) {
                Settings.Global.putInt(context.getContentResolver(), "nubia_account_login_status", -1);
            } else {
                str = "login";
                if ("org_zx_AuthComp_zte_account_login".equals(action) || "login".equals(stringExtra)) {
                    Settings.Global.putInt(context.getContentResolver(), "nubia_account_login_status", 1);
                } else {
                    str = "userinfo";
                }
            }
            GaLog.a("AccountChangeReceiver", "accountChange extra is " + str);
            Iterator it = this.f16955a.iterator();
            while (it.hasNext()) {
                ((AccountChangeCallback) it.next()).a(context, str);
            }
            GaLog.a("AccountChangeReceiver", "onReceive end");
        }
    }
}
