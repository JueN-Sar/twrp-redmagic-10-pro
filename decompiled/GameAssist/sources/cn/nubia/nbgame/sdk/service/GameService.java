package cn.nubia.nbgame.sdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import cn.nubia.nbgame.sdk.GameInnerSdk;
import cn.nubia.nbgame.sdk.interfaces.ListenerManager;
import cn.nubia.nbgame.sdk.upgrade.UpgradeHandler;
import cn.nubia.nbgame.sdk.util.NeoLog;

/* loaded from: classes.dex */
public class GameService extends Service {

    /* renamed from: c, reason: collision with root package name */
    public static boolean f8276c = false;

    private void a(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        ListenerManager.b(i2, bundle);
        NeoLog.l("GameService", "dealChangeAvatar: " + i2);
    }

    private void b(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        GameInnerSdk.j().G(bundle.getString("nickName"));
        ListenerManager.c(i2, bundle);
        NeoLog.l("GameService", "dealChangeNickname: " + i2);
    }

    private void c(Bundle bundle) {
        NeoLog.i("GameService", "dealFindPwd: start....");
        int i2 = bundle.getInt("errorCode");
        if (i2 == 0) {
            GameInnerSdk.j().c();
        }
        ListenerManager.f(i2, bundle);
        NeoLog.l("GameService", "dealFindPwd:" + i2);
    }

    private void d(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        if (!TextUtils.isEmpty(bundle.getString("tempSessionId")) && i2 == 0) {
            GameInnerSdk.j().E(true);
        }
        ListenerManager.g(i2, bundle);
        NeoLog.l("GameService", "dealInit: " + i2);
    }

    private void e(String str, Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        String string = bundle.getString("uid");
        String string2 = bundle.getString("gameId");
        String string3 = bundle.getString("nickName");
        String string4 = bundle.getString("userName");
        String string5 = bundle.getString("avatarPath");
        String string6 = bundle.getString("sessionId");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string6) || i2 != 0) {
            GameInnerSdk.j().J(false);
            if (TextUtils.equals(str, "requestTypeLogin")) {
                ListenerManager.h(-150, bundle);
                NeoLog.l("GameService", "dealLogin:-150");
                return;
            } else {
                if (TextUtils.equals(str, "requestTypeChangeAccount")) {
                    ListenerManager.a(-150, bundle);
                    NeoLog.l("GameService", "dealChangeAccount:-150");
                    return;
                }
                return;
            }
        }
        GameInnerSdk.j().J(true);
        GameInnerSdk.j().I(string);
        GameInnerSdk.j().D(string2);
        GameInnerSdk.j().H(string6);
        GameInnerSdk.j().G(string3);
        GameInnerSdk.j().K(string4);
        GameInnerSdk.j().C(string5);
        if (TextUtils.equals(str, "requestTypeLogin")) {
            ListenerManager.h(i2, bundle);
            NeoLog.l("GameService", "dealLogin:" + i2);
            return;
        }
        if (TextUtils.equals(str, "requestTypeChangeAccount")) {
            ListenerManager.a(i2, bundle);
            NeoLog.l("GameService", "dealChangeAccount:" + i2);
        }
    }

    public static void f(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        if (f8276c) {
            NeoLog.l("GameService", "isQuit is:" + f8276c);
            return;
        }
        NeoLog.l("GameService", "isQuit is:" + f8276c);
        if (i2 == 0) {
            f8276c = true;
            GameInnerSdk.j().c();
            ListenerManager.i(0, null);
            new Handler().postDelayed(new Runnable() { // from class: cn.nubia.nbgame.sdk.service.GameService.1
                @Override // java.lang.Runnable
                public void run() {
                    GameService.f8276c = false;
                }
            }, 4000L);
        }
        NeoLog.g("GameService", "dealLogout:" + i2);
    }

    private void g(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        ListenerManager.e(i2, bundle);
        NeoLog.l("GameService", "dealQuit:" + i2);
    }

    private void h(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        ListenerManager.d(i2, bundle);
        NeoLog.l("GameService", "dealRealIdentity:" + i2);
    }

    private void i(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        String string = bundle.getString("packageName");
        UpgradeHandler upgradeHandler = new UpgradeHandler(this);
        if (31 == i2) {
            upgradeHandler.sendEmptyMessage(6);
        } else if (32 == i2) {
            upgradeHandler.sendEmptyMessage(5);
        } else if (38 == i2) {
            upgradeHandler.sendEmptyMessage(8);
        }
        NeoLog.l("GameService", "dealVersionUpgrade: " + i2 + ", " + string);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        NeoLog.l("GameService", "onCreate");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        NeoLog.l("GameService", "onDestroy");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        Bundle extras;
        NeoLog.l("GameService", "onStartCommand");
        if (intent == null || (extras = intent.getExtras()) == null) {
            return 2;
        }
        String string = extras.getString("requestType");
        NeoLog.i("GameService", "callback requestType: " + string + ", bundle: " + extras.toString());
        if ("requestTypeVersionUpgrade".equals(string)) {
            i(extras);
            return 2;
        }
        if ("requestTypeInit".equals(string)) {
            d(extras);
            return 2;
        }
        if ("requestTypeLogin".equals(string)) {
            e(string, extras);
            return 2;
        }
        if ("requestTypeChangeNickname".equals(string)) {
            b(extras);
            return 2;
        }
        if ("requestTypeChangeAvatar".equals(string)) {
            a(extras);
            return 2;
        }
        if ("requestTypeFindPwd".equals(string)) {
            c(extras);
            return 2;
        }
        if ("requestTypeLogout".equals(string)) {
            f(extras);
            return 2;
        }
        if ("requestTypeChangeAccount".equals(string)) {
            e(string, extras);
            return 2;
        }
        if ("requestTypeRealIdentity".equals(string)) {
            h(extras);
            return 2;
        }
        if (!"requestTypeQuit".equals(string)) {
            return 2;
        }
        g(extras);
        return 2;
    }
}
