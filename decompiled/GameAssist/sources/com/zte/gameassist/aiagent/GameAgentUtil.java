package com.zte.gameassist.aiagent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.aiagent.bean.OutMsg;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes2.dex */
public class GameAgentUtil {

    /* renamed from: a, reason: collision with root package name */
    private static InMsg f16390a;

    public static void a(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, int i2) {
        f(context, iGameAssistClientCallback, inMsg, context.getResources().getString(R.string.aiagent_uninitial_function, context.getString(i2)), 0);
    }

    public static void b(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, int i2) {
        f(context, iGameAssistClientCallback, inMsg, context.getResources().getString(R.string.aiagent_disabled_function, context.getString(i2)), 0);
    }

    public static void c(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, int i2) {
        h(context, iGameAssistClientCallback, inMsg, context.getString(R.string.aiagent_open_function_settings, context.getString(i2)), 0, true);
    }

    public static void d(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, int i2) {
        h(context, iGameAssistClientCallback, inMsg, context.getString(R.string.aiagent_turn_off_function, context.getString(i2)), 0, true);
    }

    public static void e(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, int i2) {
        h(context, iGameAssistClientCallback, inMsg, context.getString(R.string.aiagent_turn_on_function, context.getString(i2)), 0, true);
    }

    private static void f(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, String str, int i2) {
        if (iGameAssistClientCallback == null) {
            return;
        }
        try {
            iGameAssistClientCallback.onReceivedCallback(2, new OutMsg.UnconfirmedMsg(inMsg.c(), i2, str).toString());
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        s(context, inMsg, true);
    }

    public static void g(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, String str) {
        GaLog.e("GameAgentUtil", inMsg + ",reply empty message " + str);
        h(context, iGameAssistClientCallback, inMsg, "", 1, false);
    }

    public static void h(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, String str, int i2, boolean z) {
        if (iGameAssistClientCallback == null) {
            return;
        }
        try {
            iGameAssistClientCallback.onReceivedCallback(1, new OutMsg(inMsg.c(), i2, str, "").toString());
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        s(context, inMsg, z);
    }

    public static void i(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, String str, boolean z) {
        h(context, iGameAssistClientCallback, inMsg, str, 0, z);
    }

    public static void j(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        h(context, iGameAssistClientCallback, inMsg, context.getString(R.string.aiagent_guide_to_game), 0, false);
    }

    public static void k(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        h(context, iGameAssistClientCallback, inMsg, context.getString(R.string.aiagent_handled), 0, true);
    }

    public static void l(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, boolean z) {
        h(context, iGameAssistClientCallback, inMsg, context.getString(R.string.aiagent_ok), 0, z);
    }

    public static void m(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        h(context, iGameAssistClientCallback, inMsg, context.getString(R.string.aiagent_to_be_supported), 0, false);
    }

    public static void n(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        h(context, iGameAssistClientCallback, inMsg, context.getString(R.string.aiagent_unsupported), 0, false);
    }

    public static void o(Context context, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if (iGameAssistClientCallback == null) {
            return;
        }
        try {
            iGameAssistClientCallback.onReceivedCallback(1, new OutMsg(inMsg.c(), 0, context.getString(R.string.aiagent_wait_to_think), "").toString());
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    public static void p(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("message", str);
        GaLog.e("GameAgentUtil", "sendMessage result=" + context.getContentResolver().call(Uri.parse("content://cn.nubia.redmagickyi.AigcProvider"), "cn.nubia.gameassist", "sendMessage", bundle).getBoolean(Constants.EXTRA_RESULT));
    }

    public static void q(Context context) {
        context.getContentResolver().call(Uri.parse("content://cn.nubia.redmagickyi.AigcProvider"), "cn.nubia.gameassist", "quit", (Bundle) null);
        GaLog.e("GameAgentUtil", "quit voice client");
    }

    public static void r(Context context, InMsg inMsg, InMsg inMsg2, boolean z) {
        if (inMsg2 == null || TextUtils.isEmpty(inMsg2.c()) || !inMsg2.c().equals(inMsg.c())) {
            return;
        }
        Bundle bundle = new Bundle();
        String t = SystemMgr.t();
        String u = SystemMgr.M(t) ? SystemMgr.u() : "";
        if (TextUtils.isEmpty(u)) {
            u = NubiaTrackManager.o(context, t);
        }
        bundle.putString("app_name", u);
        bundle.putString("package_name", t);
        bundle.putInt("executed", z ? 1 : 0);
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "game_voice_interaction_effect", bundle);
    }

    private static void s(Context context, InMsg inMsg, boolean z) {
        r(context, inMsg, f16390a, z);
        f16390a = null;
    }

    public static void t(Context context, InMsg inMsg) {
        if ("negative".equals(inMsg.e()) || "set_game_alarm".equals(inMsg.e()) || "game_refresh_rate".equals(inMsg.e())) {
            f16390a = null;
        } else {
            f16390a = inMsg;
        }
    }
}
