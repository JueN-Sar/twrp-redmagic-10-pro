package cn.nubia.nbgame.sdk.service;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import cn.nubia.nbgame.sdk.GameInnerSdk;
import cn.nubia.nbgame.sdk.GameSdk;
import cn.nubia.nbgame.sdk.entities.FcmInfo;
import cn.nubia.nbgame.sdk.interfaces.ListenerManager;
import cn.nubia.nbgame.sdk.upgrade.UpgradeHandler;
import cn.nubia.nbgame.sdk.util.Constant;
import cn.nubia.nbgame.sdk.util.NeoLog;

/* loaded from: classes.dex */
public class ResponseHelper {

    /* renamed from: a, reason: collision with root package name */
    private static String f8280a = "cn.nubia.nbgame.sdk.service.ResponseHelper";

    /* renamed from: b, reason: collision with root package name */
    public static boolean f8281b = false;

    public class FcmTimer extends CountDownTimer {
        @Override // android.os.CountDownTimer
        public void onFinish() {
            NeoLog.g(ResponseHelper.f8280a, "FcmTimer onFinish........");
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
            NeoLog.g(ResponseHelper.f8280a, "FcmTimer onTick........");
        }
    }

    public static void b(Context context) {
        FcmInfo.isFcmStatus = false;
        GameSdk.a(context);
    }

    public static void c(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        ListenerManager.b(i2, bundle);
        NeoLog.l(f8280a, "dealChangeAvatar: " + i2);
    }

    public static void d(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        GameInnerSdk.j().G(bundle.getString("nickName"));
        ListenerManager.c(i2, bundle);
        NeoLog.l(f8280a, "dealChangeNickname: " + i2);
    }

    public static void e(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        if (i2 == 0) {
            GameInnerSdk.j().c();
        }
        ListenerManager.f(i2, bundle);
        NeoLog.l(f8280a, "dealFindPwd:" + i2);
    }

    public static void f(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        if (!TextUtils.isEmpty(bundle.getString("tempSessionId")) && i2 == 0) {
            GameInnerSdk.j().E(true);
        }
        ListenerManager.g(i2, bundle);
        NeoLog.l(f8280a, "dealInit: " + i2);
    }

    public static void g(String str, Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        String string = bundle.getString("uid");
        String string2 = bundle.getString("gameId");
        String string3 = bundle.getString("nickName");
        String string4 = bundle.getString("userName");
        String string5 = bundle.getString("avatarPath");
        String string6 = bundle.getString("sessionId");
        GameInnerSdk.w = bundle.getBoolean("maigcUser");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string6) || i2 != 0) {
            GameInnerSdk.j().J(false);
            if (TextUtils.equals(str, "requestTypeLogin")) {
                ListenerManager.h(-150, bundle);
                NeoLog.l(f8280a, "dealLogin:-150");
                return;
            } else {
                if (TextUtils.equals(str, "requestTypeChangeAccount")) {
                    ListenerManager.a(-150, bundle);
                    NeoLog.l(f8280a, "dealChangeAccount:-150");
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
            NeoLog.l(f8280a, "dealLogin:" + i2);
            return;
        }
        if (TextUtils.equals(str, "requestTypeChangeAccount")) {
            ListenerManager.a(i2, bundle);
            NeoLog.l(f8280a, "dealChangeAccount:" + i2);
        }
    }

    public static void h(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        if (f8281b) {
            NeoLog.l(f8280a, "isQuit is:" + f8281b);
            return;
        }
        NeoLog.l(f8280a, "isQuit is:" + f8281b);
        if (i2 == 0) {
            f8281b = true;
            GameInnerSdk.j().c();
            ListenerManager.i(0, null);
            new Handler().postDelayed(new Runnable() { // from class: cn.nubia.nbgame.sdk.service.ResponseHelper.2
                @Override // java.lang.Runnable
                public void run() {
                    ResponseHelper.f8281b = false;
                }
            }, 4000L);
        }
        NeoLog.g(f8280a, "dealLogout:" + i2);
    }

    public static void i(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        ListenerManager.e(i2, bundle);
        NeoLog.l(f8280a, "dealQuit:" + i2);
    }

    public static void j(Bundle bundle) {
        int i2 = bundle.getInt("errorCode");
        ListenerManager.d(i2, bundle);
        NeoLog.l(f8280a, "dealRealIdentity:" + i2);
    }

    public static void k(Bundle bundle, Context context) {
        int i2 = bundle.getInt("errorCode");
        String string = bundle.getString("packageName");
        UpgradeHandler upgradeHandler = new UpgradeHandler(context);
        if (31 == i2) {
            upgradeHandler.sendEmptyMessage(6);
        } else if (32 == i2) {
            upgradeHandler.sendEmptyMessage(5);
        } else if (38 == i2) {
            upgradeHandler.sendEmptyMessage(8);
        }
        NeoLog.l(f8280a, "dealVersionUpgrade: " + i2 + ", " + string);
    }

    public static void l(final Context context, Bundle bundle, Handler handler) {
        if (bundle == null) {
            return;
        }
        boolean z = bundle.getBoolean("isWcm");
        NeoLog.g(f8280a, "isWcm is:" + z);
        if (!z) {
            b(context);
            return;
        }
        String string = bundle.getString("isHoliday");
        String string2 = bundle.getString("isLimitedPeroid");
        long j2 = bundle.getLong("currentTime");
        long j3 = bundle.getLong("startTime");
        long j4 = bundle.getLong("endTime");
        String string3 = bundle.getString("type");
        boolean z2 = bundle.getBoolean("isLogin");
        FcmInfo fcmInfo = new FcmInfo();
        fcmInfo.isHoliday = string;
        fcmInfo.currentTime = j2;
        fcmInfo.startTime = j3;
        fcmInfo.endTime = j4;
        fcmInfo.isLimitedPeroid = string2;
        fcmInfo.type = string3;
        NeoLog.g(f8280a, "fcmInfo is:" + fcmInfo.toString());
        if (z2) {
            Constant.a(context, true);
        }
        if (!"0".equals(string2)) {
            if ("1".equals(string2)) {
                m(context);
                return;
            }
            return;
        }
        b(context);
        long j5 = j4 - j2;
        NeoLog.g(f8280a, "disTime is:" + j5);
        if (j5 > 0) {
            handler.postDelayed(new Runnable() { // from class: cn.nubia.nbgame.sdk.service.ResponseHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    ResponseHelper.m(context);
                }
            }, j5);
        }
    }

    public static void m(Context context) {
        FcmInfo.isFcmStatus = true;
        GameSdk.c(context);
    }
}
