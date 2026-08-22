package cn.nubia.gameassist.plugin.policy;

import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cn.nubia.gameassist.plugin.tiles.MoraAiSpeakerTile;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class GameVoiceController implements ObserverManager.SettingCallback, GameMonitor.Callback {

    /* renamed from: l, reason: collision with root package name */
    private static volatile GameVoiceController f7286l;

    /* renamed from: m, reason: collision with root package name */
    private static final Uri f7287m = Settings.Global.getUriFor("nubia_game_scene");

    /* renamed from: n, reason: collision with root package name */
    private static final Uri f7288n = Uri.parse("content://com.zte.aispeaker.contentProvider");

    /* renamed from: c, reason: collision with root package name */
    private Context f7289c;

    /* renamed from: h, reason: collision with root package name */
    private Handler f7290h = new Handler(ThreadManager.c().a());

    /* renamed from: i, reason: collision with root package name */
    private boolean f7291i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f7292j;

    /* renamed from: k, reason: collision with root package name */
    private PhoneStateListener f7293k;

    private GameVoiceController() {
    }

    public static GameVoiceController f() {
        if (f7286l == null) {
            synchronized (GameVoiceController.class) {
                try {
                    if (f7286l == null) {
                        f7286l = new GameVoiceController();
                    }
                } finally {
                }
            }
        }
        return f7286l;
    }

    private void g(String str) {
        Intent intent = new Intent(str);
        intent.setComponent(new ComponentName("com.zte.onemorething", "com.zte.aispeaker.aigc.AudioRecordService"));
        intent.putExtra("name", "voice_controller");
        intent.putExtra("package", SystemMgr.t());
        this.f7289c.startService(intent);
    }

    private boolean i(String str) {
        String string = Settings.Global.getString(this.f7289c.getContentResolver(), "voice_controller_enabled_pkg");
        return !TextUtils.isEmpty(string) && string.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j() {
        if (Utils.C(this.f7289c)) {
            p();
        } else {
            m();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k() {
        if (i(SystemMgr.t())) {
            p();
        }
    }

    private void l(String str) {
        ContentProviderClient acquireUnstableContentProviderClient;
        ContentProviderClient contentProviderClient = null;
        try {
            try {
                GaLog.e("GameVoiceController", "notifyChanged:" + str);
                acquireUnstableContentProviderClient = this.f7289c.getContentResolver().acquireUnstableContentProviderClient(f7288n);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            acquireUnstableContentProviderClient.call(str, null, null);
            GaLog.e("GameVoiceController", "notifyChanged end");
            acquireUnstableContentProviderClient.close();
        } catch (Exception e3) {
            e = e3;
            contentProviderClient = acquireUnstableContentProviderClient;
            GaLog.b("GameVoiceController", "Exception: " + e.getMessage());
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
        } catch (Throwable th2) {
            th = th2;
            contentProviderClient = acquireUnstableContentProviderClient;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
            throw th;
        }
    }

    private void m() {
        l("game_scene_changed");
    }

    private void n() {
        l("game_update");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        boolean C = Utils.C(this.f7289c);
        String t = SystemMgr.t();
        boolean B0 = MoraAiSpeakerTile.B0(this.f7289c);
        GaLog.e("GameVoiceController", "isGameScene " + C + " curPackage: " + t + " isNetworkConnected: " + B0);
        if (i(t) && C && B0) {
            GaLog.e("GameVoiceController", "startASR");
            g("com.zte.onemorething.action.START_ASR");
            this.f7292j = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        GaLog.e("GameVoiceController", "stopASR");
        g("com.zte.onemorething.action.STOP_ASR");
        this.f7292j = false;
    }

    public synchronized void h(Context context) {
        if (!this.f7291i) {
            this.f7289c = context.getApplicationContext();
            ObserverManager.c().b(context, f7287m, this);
            TelephonyManager telephonyManager = (TelephonyManager) this.f7289c.getSystemService("phone");
            PhoneStateListener phoneStateListener = new PhoneStateListener() { // from class: cn.nubia.gameassist.plugin.policy.GameVoiceController.1
                @Override // android.telephony.PhoneStateListener
                public void onCallStateChanged(int i2, String str) {
                    super.onCallStateChanged(i2, str);
                    if (i2 == 0) {
                        GameVoiceController.this.p();
                    } else if (i2 == 2 && GameVoiceController.this.f7292j) {
                        GameVoiceController.this.q();
                    }
                }
            };
            this.f7293k = phoneStateListener;
            telephonyManager.listen(phoneStateListener, 32);
            SystemMgr.y(this.f7289c).h(this);
            this.f7291i = true;
        }
    }

    public void o(boolean z) {
        this.f7292j = z;
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        GaLog.e("GameVoiceController", "onGameUpdate: isVoiceController = " + this.f7292j);
        n();
        if (!this.f7292j) {
            p();
        } else {
            q();
            this.f7290h.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.policy.d
                @Override // java.lang.Runnable
                public final void run() {
                    GameVoiceController.this.k();
                }
            }, 1000L);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        if (f7287m.equals(uri)) {
            this.f7290h.post(new Runnable() { // from class: cn.nubia.gameassist.plugin.policy.c
                @Override // java.lang.Runnable
                public final void run() {
                    GameVoiceController.this.j();
                }
            });
        }
    }
}
