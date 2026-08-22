package cn.nubia.nbgame.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.nubia.nbgame.sdk.GameInnerSdk;
import cn.nubia.nbgame.sdk.util.NeoLog;

/* loaded from: classes.dex */
public class PackageReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8275a = "PackageReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
            if ("cn.nubia.nbgame".equals(encodedSchemeSpecificPart) && "android.intent.action.PACKAGE_REMOVED".equals(action)) {
                NeoLog.g(f8275a, encodedSchemeSpecificPart + " is uninstalled");
                GameInnerSdk.j().c();
                GameInnerSdk.j().d();
            }
        }
    }
}
