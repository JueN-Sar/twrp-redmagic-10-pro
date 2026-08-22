package cn.nubia.gameassist.plugin.policy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import cn.nubia.gameassist.plugin.tiles.MoraAiSpeakerTile;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class AiSpeakerController implements ObserverManager.SettingCallback, GameMonitor.Callback {

    /* renamed from: l, reason: collision with root package name */
    private static volatile AiSpeakerController f7278l;

    /* renamed from: m, reason: collision with root package name */
    private static final Uri f7279m = Settings.Global.getUriFor("nubia_game_scene");

    /* renamed from: c, reason: collision with root package name */
    private Context f7280c;

    /* renamed from: h, reason: collision with root package name */
    private Handler f7281h = new Handler(ThreadManager.c().a());

    /* renamed from: i, reason: collision with root package name */
    private boolean f7282i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f7283j;

    /* renamed from: k, reason: collision with root package name */
    private PhoneStateListener f7284k;

    private AiSpeakerController() {
    }

    public static AiSpeakerController f() {
        if (f7278l == null) {
            synchronized (AiSpeakerController.class) {
                try {
                    if (f7278l == null) {
                        f7278l = new AiSpeakerController();
                    }
                } finally {
                }
            }
        }
        return f7278l;
    }

    private boolean h(String str) {
        return Utils.x(Settings.Global.getString(this.f7280c.getContentResolver(), "nubia_ai_speaker_enabled_pkg"), str, ",");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i() {
        if (Utils.C(this.f7280c)) {
            l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j() {
        if (h(SystemMgr.t())) {
            l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        boolean C = Utils.C(this.f7280c);
        String t = SystemMgr.t();
        boolean B0 = MoraAiSpeakerTile.B0(this.f7280c);
        GaLog.e("AiSpeakerController", "isGameScene " + C + " curPackage: " + t + " isNetworkConnected: " + B0);
        if (h(t) && C && B0) {
            try {
                GaLog.e("AiSpeakerController", "startAiSpeaker " + t);
                Intent intent = new Intent("com.zte.onemoreting.action.START_ASR_RECOGNIZE");
                intent.setComponent(new ComponentName("com.zte.onemorething", "com.zte.aispeaker.aigc.MediaCaptureService"));
                intent.putExtra("package_name", t);
                intent.putExtra("user_id", SystemMgr.w());
                this.f7280c.startForegroundService(intent);
                this.f7283j = true;
            } catch (Exception e2) {
                GaLog.k("AiSpeakerController", "startAiSpeaker error " + e2.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        try {
            this.f7280c.sendBroadcast(new Intent("com.zte.onemoreting.action.STOP_ASR_RECOGNIZE"));
            GaLog.e("AiSpeakerController", "stopAiSpeaker");
            this.f7283j = false;
        } catch (Exception e2) {
            GaLog.k("AiSpeakerController", "stopAiSpeaker error " + e2.getMessage());
        }
    }

    public synchronized void g(Context context) {
        if (!this.f7282i) {
            this.f7280c = context.getApplicationContext();
            ObserverManager.c().b(context, f7279m, this);
            TelephonyManager telephonyManager = (TelephonyManager) this.f7280c.getSystemService("phone");
            PhoneStateListener phoneStateListener = new PhoneStateListener() { // from class: cn.nubia.gameassist.plugin.policy.AiSpeakerController.1
                @Override // android.telephony.PhoneStateListener
                public void onCallStateChanged(int i2, String str) {
                    super.onCallStateChanged(i2, str);
                    if (i2 == 0) {
                        AiSpeakerController.this.l();
                    } else if (i2 == 2 && AiSpeakerController.this.f7283j) {
                        AiSpeakerController.this.m();
                    }
                }
            };
            this.f7284k = phoneStateListener;
            telephonyManager.listen(phoneStateListener, 32);
            SystemMgr.y(this.f7280c).h(this);
            this.f7282i = true;
        }
    }

    public void k(boolean z) {
        this.f7283j = z;
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        GaLog.e("AiSpeakerController", "onGameUpdate: isAiSpeakerStarted = " + this.f7283j);
        if (!this.f7283j) {
            l();
        } else {
            m();
            this.f7281h.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.policy.a
                @Override // java.lang.Runnable
                public final void run() {
                    AiSpeakerController.this.j();
                }
            }, 1000L);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        if (f7279m.equals(uri)) {
            this.f7281h.post(new Runnable() { // from class: cn.nubia.gameassist.plugin.policy.b
                @Override // java.lang.Runnable
                public final void run() {
                    AiSpeakerController.this.i();
                }
            });
        }
    }
}
