package cn.nubia.nbgame.sdk.upgrade;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import cn.nubia.nbgame.sdk.GameInnerSdk;
import cn.nubia.nbgame.sdk.interfaces.ListenerManager;
import cn.nubia.nbgame.sdk.ui.ShowActivity;
import cn.nubia.nbgame.sdk.util.NeoLog;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class UpgradeHandler extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private Context f8285a;

    /* renamed from: b, reason: collision with root package name */
    private InstallTimer f8286b;

    /* renamed from: c, reason: collision with root package name */
    BroadcastReceiver f8287c = new BroadcastReceiver() { // from class: cn.nubia.nbgame.sdk.upgrade.UpgradeHandler.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                UpgradeHandler.this.sendEmptyMessage(7);
            }
        }
    };

    /* renamed from: d, reason: collision with root package name */
    boolean f8288d = false;

    public class InstallTimer extends CountDownTimer {
        public InstallTimer(long j2, long j3) {
            super(j2, j3);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            NeoLog.g("UpgradeHandler", "onTick onFinish........ is:" + GameInnerSdk.t);
            if (!UpgradeHandler.this.f8288d) {
                Bundle bundle = new Bundle();
                bundle.putInt("errorCode", 34);
                ListenerManager.g(34, bundle);
                Toast.makeText(UpgradeHandler.this.f8285a, "升级失败，请稍后重试", 0).show();
            }
            UpgradeHandler.this.f8286b.cancel();
            UpgradeHandler.this.c();
            UpgradeHandler.this.g();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
            NeoLog.g("UpgradeHandler", "onTick start ......isUpdateSuccess is:" + GameInnerSdk.t);
            if (GameInnerSdk.t) {
                UpgradeHandler upgradeHandler = UpgradeHandler.this;
                if (upgradeHandler.f8288d) {
                    return;
                }
                upgradeHandler.f8288d = true;
                GameInnerSdk.t = false;
                upgradeHandler.sendEmptyMessage(7);
            }
        }
    }

    public UpgradeHandler(Context context) {
        this.f8285a = context;
    }

    private void e(int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("errorCode", i2);
        ListenerManager.g(i2, bundle);
    }

    public void c() {
        NeoLog.g("UpgradeHandler", "cancelInstallProgress!");
        ShowActivity showActivity = ShowActivity.f8283h;
        if (showActivity != null) {
            showActivity.finish();
        }
    }

    public void d() {
        NeoLog.g("UpgradeHandler", "register install broadcast!");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        if (Build.VERSION.SDK_INT > 33) {
            this.f8285a.getApplicationContext().registerReceiver(this.f8287c, intentFilter, 2);
        } else {
            this.f8285a.getApplicationContext().registerReceiver(this.f8287c, intentFilter);
        }
    }

    public void f(String str) {
        NeoLog.g("UpgradeHandler", "showInstallProgress!");
        Intent intent = new Intent(this.f8285a, (Class<?>) ShowActivity.class);
        intent.putExtra("msg", str);
        intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        this.f8285a.startActivity(intent);
    }

    public void g() {
        NeoLog.g("UpgradeHandler", "unregister install broadcast!");
        if (this.f8287c != null) {
            this.f8285a.getApplicationContext().unregisterReceiver(this.f8287c);
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i2 = message.what;
        if (i2 == 5) {
            NeoLog.l("UpgradeHandler", "NORMAL_UPGRADE");
            e(32);
            return;
        }
        if (i2 == 6) {
            NeoLog.l("UpgradeHandler", "SILENT_UPGRADE");
            d();
            f("正在升级游戏中心组件");
            InstallTimer installTimer = new InstallTimer(30000L, 2000L);
            this.f8286b = installTimer;
            installTimer.start();
            return;
        }
        if (i2 != 7) {
            if (i2 != 8) {
                return;
            }
            NeoLog.l("UpgradeHandler", "VERSION_CHECK_TASK_EXIST");
            e(38);
            return;
        }
        NeoLog.l("UpgradeHandler", "SILENT_UPGRADE_COMPLETED");
        this.f8286b.cancel();
        c();
        g();
        e(35);
    }
}
