package cn.nubia.nbgame.sdk.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import cn.nubia.nbgame.sdk.GameInnerSdk;
import cn.nubia.nbgame.sdk.util.Constant;
import cn.nubia.nbgame.sdk.util.NeoLog;

/* loaded from: classes.dex */
public class ResponseBroadcastReceiver extends BroadcastReceiver {

    /* renamed from: b, reason: collision with root package name */
    Context f8278b;

    /* renamed from: a, reason: collision with root package name */
    private String f8277a = ResponseBroadcastReceiver.class.getName();

    /* renamed from: c, reason: collision with root package name */
    Handler f8279c = new Handler();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.f8278b = context;
        NeoLog.g("ResponseBroadcastReceiver", "onReceive");
        NeoLog.g("GameService", "get bundle:" + intent.getExtras().getString("requestType"));
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String string = extras.getString("requestType");
            NeoLog.i(this.f8277a, "callback requestType: " + string + ", bundle: " + extras.toString());
            if ("requestTypeVersionUpgrade".equals(string)) {
                ResponseHelper.k(extras, context);
                return;
            }
            if ("requestTypeInit".equals(string)) {
                ResponseHelper.f(extras);
                return;
            }
            if ("requestTypeLogin".equals(string)) {
                Constant.a(context, true);
                ResponseHelper.g(string, extras);
                return;
            }
            if ("requestTypeChangeNickname".equals(string)) {
                ResponseHelper.d(extras);
                return;
            }
            if ("requestTypeChangeAvatar".equals(string)) {
                ResponseHelper.c(extras);
                return;
            }
            if ("requestTypeFindPwd".equals(string)) {
                ResponseHelper.e(extras);
                return;
            }
            if ("requestTypeLogout".equals(string)) {
                ResponseHelper.h(extras);
                String string2 = extras.getString("QUIT_TYPE");
                NeoLog.i(this.f8277a, "REQUEST_TYPE_LOGOUT quitType: " + string2);
                return;
            }
            if ("requestTypeChangeAccount".equals(string)) {
                ResponseHelper.g(string, extras);
                return;
            }
            if ("requestTypeRealIdentity".equals(string)) {
                ResponseHelper.j(extras);
                return;
            }
            if (!"requestTypeQuit".equals(string)) {
                if ("requestTypeFcm".equals(string)) {
                    ResponseHelper.l(context, extras, this.f8279c);
                    return;
                }
                return;
            }
            ResponseHelper.i(extras);
            String string3 = extras.getString("QUIT_TYPE");
            NeoLog.i(this.f8277a, "REQUEST_TYPE_QUIT quitType: " + string3);
            if ("requestTypeFcm".equals(string3)) {
                ResponseHelper.b(context);
                GameInnerSdk.f();
            }
        }
    }
}
