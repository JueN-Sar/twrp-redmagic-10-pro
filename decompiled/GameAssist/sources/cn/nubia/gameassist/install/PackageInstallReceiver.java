package cn.nubia.gameassist.install;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class PackageInstallReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private CopyOnWriteArrayList f6514a = new CopyOnWriteArrayList();

    public void a(InstallListener installListener) {
        if (this.f6514a.contains(installListener)) {
            return;
        }
        this.f6514a.add(installListener);
    }

    public void b() {
        this.f6514a.clear();
        this.f6514a = null;
    }

    public String c(Intent intent) {
        return intent.getData().getEncodedSchemeSpecificPart();
    }

    public void d(InstallListener installListener) {
        this.f6514a.remove(installListener);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        char c2;
        String c3 = c(intent);
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode == -810471698) {
            if (action.equals("android.intent.action.PACKAGE_REPLACED")) {
                c2 = 2;
            }
            c2 = 65535;
        } else if (hashCode != 525384130) {
            if (hashCode == 1544582882 && action.equals("android.intent.action.PACKAGE_ADDED")) {
                c2 = 0;
            }
            c2 = 65535;
        } else {
            if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            Iterator it = this.f6514a.iterator();
            while (it.hasNext()) {
                ((InstallListener) it.next()).f(c3);
            }
        } else {
            if (c2 != 1) {
                return;
            }
            Iterator it2 = this.f6514a.iterator();
            while (it2.hasNext()) {
                ((InstallListener) it2.next()).x(c3);
            }
        }
    }
}
