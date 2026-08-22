package com.zte.gameassist.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.provider.Settings;
import com.zte.gameassist.common.R;
import com.zte.gameassist.common.SystemMgr;

/* loaded from: classes2.dex */
public class NubiaTrackManager {

    /* renamed from: f, reason: collision with root package name */
    static HandlerThread f17036f;

    /* renamed from: g, reason: collision with root package name */
    static TrackHandler f17037g;

    /* renamed from: a, reason: collision with root package name */
    private Context f17038a;

    /* renamed from: b, reason: collision with root package name */
    private Messenger f17039b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f17040c;

    /* renamed from: d, reason: collision with root package name */
    private ServiceConnection f17041d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f17042e;

    private static class SingleInstance {

        /* renamed from: a, reason: collision with root package name */
        static NubiaTrackManager f17044a = new NubiaTrackManager();
    }

    final class TrackHandler extends Handler {
        public TrackHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Context context;
            int i2;
            int i3 = message.what;
            if (i3 == 1) {
                if (ActivityManager.isUserAMonkey()) {
                    return;
                }
                try {
                    synchronized (NubiaTrackManager.f17036f) {
                        try {
                            if (NubiaTrackManager.this.f17040c && NubiaTrackManager.this.f17039b != null) {
                                NubiaTrackManager.this.f17039b.send(message);
                                return;
                            }
                            NubiaTrackManager.this.i();
                            NubiaTrackManager.f17036f.wait();
                            if (NubiaTrackManager.this.f17040c && NubiaTrackManager.this.f17039b != null) {
                                NubiaTrackManager.this.f17039b.send(message);
                            }
                            return;
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                    return;
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                    return;
                }
            }
            if (i3 == 2) {
                synchronized (NubiaTrackManager.f17036f) {
                    try {
                        if (NubiaTrackManager.this.f17040c) {
                            NubiaTrackManager.this.f17039b = null;
                            NubiaTrackManager.this.f17040c = false;
                            if (NubiaTrackManager.this.f17038a != null) {
                                NubiaTrackManager.this.f17038a.unbindService(NubiaTrackManager.this.f17041d);
                                return;
                            }
                            return;
                        }
                        return;
                    } finally {
                    }
                }
            }
            switch (i3) {
                case 2000:
                    Bundle bundle = new Bundle();
                    bundle.putString("running_game_package_name", SystemMgr.t());
                    NubiaTrackManager.this.x("cn.nubia.gamelauncher", "gamecontrol_launch", bundle);
                    return;
                case 2001:
                    Object obj = message.obj;
                    if (obj == "gamemode_moblieqq_picture_in_picture" || obj == "gamemode_wechat_picture_in_picture" || obj == "gamemode_qqbrowser_picture_in_picture") {
                        NubiaTrackManager.this.z("cn.nubia.gamelauncher", (String) obj, "access_way", "控制中心");
                        return;
                    } else {
                        NubiaTrackManager.this.w("cn.nubia.gamelauncher", (String) obj);
                        return;
                    }
                case 2002:
                    NubiaTrackManager.this.A("cn.nubia.gamelauncher", (String) message.obj, "switch_on", message.arg1 == 1);
                    return;
                case 2003:
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("accessway", NubiaTrackManager.this.f17038a.getString(R.string.track_pkg));
                    bundle2.putInt("light_value", Math.round(((Float) message.obj).floatValue()));
                    if (message.arg1 == 1) {
                        NubiaTrackManager.this.x("cn.nubia.gamelauncher", message.arg2 == 1 ? "auto_brightness_on" : "auto_brightness_off", bundle2);
                        return;
                    }
                    if (message.arg2 == 1) {
                        context = NubiaTrackManager.this.f17038a;
                        i2 = R.string.track_up;
                    } else {
                        context = NubiaTrackManager.this.f17038a;
                        i2 = R.string.track_down;
                    }
                    bundle2.putString("mode", context.getString(i2));
                    bundle2.putInt("screen_value", Settings.System.getInt(NubiaTrackManager.this.f17038a.getContentResolver(), "screen_brightness", 0));
                    NubiaTrackManager.this.x("cn.nubia.gamelauncher", "adjust_brightness", bundle2);
                    return;
                case 2004:
                    NubiaTrackManager.this.z("cn.nubia.gamelauncher", "gamecontrol_click", "name", (String) message.obj);
                    return;
                case 2005:
                    Bundle bundle3 = new Bundle();
                    String str = (String) message.obj;
                    bundle3.putString("package_name", str);
                    bundle3.putString("app_name", NubiaTrackManager.o(NubiaTrackManager.this.f17038a, str));
                    bundle3.putInt("duration", message.arg1);
                    NubiaTrackManager.this.x("cn.nubia.gamelauncher", "back_record_status", bundle3);
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(String str, String str2, String str3, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("owner_name", str);
        bundle.putString("event_name", str2);
        bundle.putBoolean(str3, z);
        f17037g.removeMessages(2);
        Message obtainMessage = f17037g.obtainMessage(1);
        obtainMessage.setData(bundle);
        f17037g.sendMessage(obtainMessage);
        TrackHandler trackHandler = f17037g;
        trackHandler.sendMessageDelayed(trackHandler.obtainMessage(2), 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.zte.analytics", "com.zte.analytics.datacollection.DataCollectionService"));
        Context context = this.f17038a;
        if (context != null) {
            context.bindService(intent, this.f17041d, 1);
        }
    }

    public static String o(Context context, String str) {
        String str2 = "";
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(SystemMgr.A(str), 128);
            if (applicationInfo != null) {
                str2 = packageManager.getApplicationLabel(applicationInfo).toString();
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        GaLog.a("NubiaTrackManager", "getAppName: " + str2);
        return str2;
    }

    public static NubiaTrackManager p() {
        return SingleInstance.f17044a;
    }

    private String q(String str) {
        if ("performance".equals(str)) {
            return "gamecontrol_performance_switch";
        }
        if ("vibrate".equals(str)) {
            return "gamecontrol_4DShock_switch";
        }
        if ("touch".equals(str)) {
            return "gamecontrol_touch_button_switch";
        }
        if ("handler".equals(str)) {
            return "gamecontrol_handle_setting";
        }
        if ("fan".equals(str)) {
            return "gamecontrol_fan_switch";
        }
        if ("game_wechat".equals(str)) {
            return "gamemode_wechat_picture_in_picture";
        }
        if ("game_qq".equals(str)) {
            return "gamemode_moblieqq_picture_in_picture";
        }
        if ("clean".equals(str)) {
            return "gamecontrol_cleanup";
        }
        if ("tel".equals(str)) {
            return "gamecontrol_shielded_phone_switch";
        }
        if ("noti".equals(str)) {
            return "gamecontrol_shielded_notification_switch";
        }
        if ("snap".equals(str)) {
            return "gamecontrol_super_screenshot";
        }
        if ("record".equals(str)) {
            return "gamecontrol_screen_record";
        }
        if ("afk".equals(str)) {
            return "screenoff_hung_up";
        }
        if ("brightness".equals(str)) {
            return "gamecontrol_brightness_bar";
        }
        if ("brightness_mode".equals(str)) {
            return "gamecontrol_brightness_switch";
        }
        if ("3".equals(str)) {
            return "gamecontrol_gamespace_click";
        }
        if ("4".equals(str)) {
            return "gamecontrol_back_click";
        }
        if ("sight_assist".equals(str)) {
            return "gamecontrol_sightassist";
        }
        if ("game_enhance".equals(str)) {
            return "gamecontrol_game_boost";
        }
        if ("keylink".equals(str)) {
            return "gamecontrol_onekeylink";
        }
        if ("game_browser".equals(str)) {
            return "gamecontrol_browser";
        }
        if ("game_qq_browser".equals(str)) {
            return "gamemode_qqbrowser_picture_in_picture";
        }
        if ("charge_separation".equals(str)) {
            return "charge_separate_usage";
        }
        return null;
    }

    public void B(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("action_type", str2);
        bundle.putString("action_value", str3);
        bundle.putInt("report_interval", 1);
        x("cn.nubia.gamelauncher", str, bundle);
    }

    public void C(String str, boolean z) {
        f17037g.removeMessages(2002);
        f17037g.sendMessage(f17037g.obtainMessage(2002, z ? 1 : 0, 0, q(str)));
    }

    public void j() {
        f17037g.removeMessages(2000);
        f17037g.sendEmptyMessage(2000);
    }

    public void k(String str) {
        f17037g.removeMessages(2001);
        f17037g.sendMessage(f17037g.obtainMessage(2001, q(str)));
    }

    public void l(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("owner_name", "cn.nubia.gamelauncher");
        bundle.putString("event_name", q(str));
        bundle.putString(str2, str3);
        f17037g.removeMessages(2);
        Message obtainMessage = f17037g.obtainMessage(1);
        obtainMessage.setData(bundle);
        f17037g.sendMessage(obtainMessage);
        TrackHandler trackHandler = f17037g;
        trackHandler.sendMessageDelayed(trackHandler.obtainMessage(2), 3000L);
    }

    public void m(String str, int i2) {
        f17037g.removeMessages(2005);
        Message obtainMessage = f17037g.obtainMessage(2005, str);
        obtainMessage.arg1 = i2;
        f17037g.sendMessage(obtainMessage);
    }

    public void n(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(z ? "open_position" : "close_position", "game_control_center");
        x("com.android.settings", "manual_fan_used", bundle);
    }

    public synchronized void r(Context context) {
        try {
            if (!this.f17042e) {
                this.f17038a = context;
                if (f17037g == null) {
                    HandlerThread handlerThread = new HandlerThread("NubiaTrackEvent", 10);
                    f17036f = handlerThread;
                    handlerThread.start();
                    f17037g = new TrackHandler(f17036f.getLooper());
                }
                this.f17042e = true;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public boolean s() {
        return this.f17042e;
    }

    public void t(String str) {
        f17037g.removeMessages(2004);
        f17037g.sendMessage(f17037g.obtainMessage(2004, str));
    }

    public void u() {
        w("cn.nubia.gamelauncher", "game_assistant_plugin_area_click");
    }

    public void v(String str, Bundle bundle) {
        x("cn.nubia.gamelauncher", str, bundle);
    }

    public void w(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("owner_name", str);
        bundle.putString("event_name", str2);
        f17037g.removeMessages(2);
        Message obtainMessage = f17037g.obtainMessage(1);
        obtainMessage.setData(bundle);
        f17037g.sendMessage(obtainMessage);
        TrackHandler trackHandler = f17037g;
        trackHandler.sendMessageDelayed(trackHandler.obtainMessage(2), 3000L);
    }

    public void x(String str, String str2, Bundle bundle) {
        bundle.putString("owner_name", str);
        bundle.putString("event_name", str2);
        f17037g.removeMessages(2);
        Message obtainMessage = f17037g.obtainMessage(1);
        obtainMessage.setData(bundle);
        f17037g.sendMessage(obtainMessage);
        TrackHandler trackHandler = f17037g;
        trackHandler.sendMessageDelayed(trackHandler.obtainMessage(2), 3000L);
    }

    public void y(String str, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("owner_name", str);
        bundle.putString("event_name", str2);
        bundle.putInt(str3, i2);
        f17037g.removeMessages(2);
        Message obtainMessage = f17037g.obtainMessage(1);
        obtainMessage.setData(bundle);
        f17037g.sendMessage(obtainMessage);
        TrackHandler trackHandler = f17037g;
        trackHandler.sendMessageDelayed(trackHandler.obtainMessage(2), 3000L);
    }

    public void z(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString("owner_name", str);
        bundle.putString("event_name", str2);
        bundle.putString(str3, str4);
        f17037g.removeMessages(2);
        Message obtainMessage = f17037g.obtainMessage(1);
        obtainMessage.setData(bundle);
        f17037g.sendMessage(obtainMessage);
        TrackHandler trackHandler = f17037g;
        trackHandler.sendMessageDelayed(trackHandler.obtainMessage(2), 3000L);
    }

    private NubiaTrackManager() {
        this.f17041d = new ServiceConnection() { // from class: com.zte.gameassist.utils.NubiaTrackManager.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (NubiaTrackManager.f17036f) {
                    NubiaTrackManager.this.f17039b = new Messenger(iBinder);
                    NubiaTrackManager.this.f17040c = true;
                    NubiaTrackManager.f17036f.notify();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                synchronized (NubiaTrackManager.f17036f) {
                    NubiaTrackManager.this.f17039b = null;
                    NubiaTrackManager.this.f17040c = false;
                    NubiaTrackManager.f17036f.notify();
                }
            }
        };
    }
}
