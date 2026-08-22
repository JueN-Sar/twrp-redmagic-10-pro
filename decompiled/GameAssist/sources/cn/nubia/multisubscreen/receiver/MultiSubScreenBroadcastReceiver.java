package cn.nubia.multisubscreen.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.multisubscreen.mgr.ConnectCodeMgr;
import cn.nubia.multisubscreen.mgr.DistributeBusMgr;
import cn.nubia.multisubscreen.mgr.MultiSubScreenNotificationMgr;
import cn.nubia.multisubscreen.ui.MultiSubScreenSinkActivity;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class MultiSubScreenBroadcastReceiver extends BroadcastReceiver {
    private void a() {
        MultiSubScreenUtils.D(true);
        if (MultiSubScreenUtils.v()) {
            DistributeBusMgr.getInstance().disConnectDevice(MultiSubScreenUtils.k());
        } else {
            ConnectCodeMgr.h().x("SINK_REQUIRED_DISCONNECT_CODE");
        }
    }

    private void b() {
        if (MultiSubScreenUtils.f8174d != 2) {
            MultiSubScreenNotificationMgr.g().d();
        } else {
            if (MultiSubScreenUtils.v()) {
                return;
            }
            c();
        }
    }

    private void c() {
        Intent intent = new Intent(GameAssistApplication.j(), (Class<?>) MultiSubScreenSinkActivity.class);
        intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        GameAssistApplication.j().startActivity(intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!ZteFeature.isSupportMultiSubScreen() || intent == null) {
            return;
        }
        String action = intent.getAction();
        GaLog.a("MultiSubScreen_MultiSubScreenBroadcastReceiver", "onReceive action = " + action);
        action.hashCode();
        switch (action) {
            case "distributebus.action.onstartdistmanagerservice":
                DistributeBusMgr.getInstance().publishService(true);
                DistributeBusMgr.getInstance().subscribeService();
                break;
            case "cn.nubia.multisubscreen.show":
                b();
                break;
            case "cn.nubia.multisubscreen.disconnect":
                a();
                break;
        }
    }
}
