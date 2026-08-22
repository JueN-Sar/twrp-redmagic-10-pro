package cn.nubia.gameassist.plugin.tiles;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.plugin.policy.AiSpeakerController;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class MoraAiSpeakerTile extends QSTile implements ObserverManager.SettingCallback {
    private static final Uri w = Uri.parse("content://com.zte.aispeaker.contentProvider");
    private boolean v;

    public MoraAiSpeakerTile(QSTile.Host host) {
        super(host);
    }

    private int A0() {
        return Settings.Global.getInt(this.f6153i.getContentResolver(), "nubia_systemui_wifidisplay_status", 0);
    }

    public static boolean B0(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    private void C0(String str, Bundle bundle) {
        ContentProviderClient contentProviderClient = null;
        try {
            try {
                ContentProviderClient acquireUnstableContentProviderClient = GameAssistApplication.j().getApplicationContext().getContentResolver().acquireUnstableContentProviderClient(w);
                if (acquireUnstableContentProviderClient == null) {
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                        return;
                    }
                    return;
                }
                try {
                    acquireUnstableContentProviderClient.call(str, null, bundle);
                    if ("switch_open".equals(str)) {
                        AiSpeakerController.f().k(true);
                    } else if ("switch_close".equals(str)) {
                        AiSpeakerController.f().k(false);
                    }
                    GaLog.e("MoraAISpeakerTile", "linkAiSpeakerProvider " + str);
                    acquireUnstableContentProviderClient.close();
                } catch (Exception e2) {
                    e = e2;
                    contentProviderClient = acquireUnstableContentProviderClient;
                    GaLog.c("MoraAISpeakerTile", "linkAiSpeakerProvider: e = ", e);
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    contentProviderClient = acquireUnstableContentProviderClient;
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("MoraAISpeakerTile", "handleClick(): mTileEnable =" + this.t + ", mCurPackage = " + this.f6163s + " wifi status " + A0());
        if (A0() == 1) {
            Toast.makeText(this.f6153i, R.string.no_support_during_wifi_display_toast, 1).show();
            return false;
        }
        if (!this.f6162r || Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("MoraAISpeakerTile", "isInFreeformMode");
            return true;
        }
        if (!B0(this.f6153i)) {
            Toast.makeText(this.f6153i, R.string.upgrade_no_network, 1).show();
        } else if (this.f6162r) {
            Bundle bundle = new Bundle();
            bundle.putString("packageName", this.f6163s);
            this.u = true;
            if (this.t) {
                C0("switch_close", bundle);
            } else {
                C0("switch_open", bundle);
                this.f6152h.b();
            }
        }
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("MoraAISpeakerTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r || !this.t) {
            ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
            return false;
        }
        this.f6152h.b();
        C0("setting", null);
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("nubia_ai_speaker_enabled_pkg"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("nubia_ai_speaker_enabled_pkg"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        StringBuilder sb = new StringBuilder();
        sb.append("handleUpdateState() : ");
        sb.append(this.f6163s);
        sb.append(this.f6162r ? " isGameScene" : " ");
        sb.append(" mTileEnable = ");
        sb.append(this.t);
        GaLog.e("MoraAISpeakerTile", sb.toString());
        if (this.f6162r && this.t) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_ai_speaker_on);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_ai_speaker_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
            state.f6175i = false;
        }
        if (ZteFeature.isNeoProduct()) {
            state.f6169c = this.f6153i.getString(R.string.plugin_label_neo_ai_speaker);
            state.f6170d = this.f6153i.getString(R.string.ai_speaker_demi_introduction);
        } else if (ZteFeature.isSupportDemi()) {
            state.f6169c = this.f6153i.getString(R.string.plugin_label_demi_ai_speaker);
            state.f6170d = this.f6153i.getString(R.string.ai_speaker_demi_introduction);
        } else {
            state.f6169c = this.f6153i.getString(R.string.plugin_label_ai_speaker);
            state.f6170d = this.f6153i.getString(R.string.ai_speaker_introduction);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_ai_speaker_enabled_pkg"), this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if (!"game_turn_on_ai_speaker".equals(str) || !i0(iGameAssistClientCallback, inMsg)) {
            if ("game_turn_off_ai_speaker".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
                if (this.t) {
                    Bundle bundle = new Bundle();
                    bundle.putString("packageName", this.f6163s);
                    bundle.putBoolean("close_window", true);
                    C0("switch_close", bundle);
                }
                GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
                return;
            }
            return;
        }
        if (!this.t) {
            if (A0() == 1) {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
                Toast.makeText(this.f6153i, R.string.no_support_during_wifi_display_toast, 1).show();
                return;
            } else if (!B0(this.f6153i)) {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
                Toast.makeText(this.f6153i, R.string.upgrade_no_network, 1).show();
                return;
            } else {
                Bundle bundle2 = new Bundle();
                bundle2.putString("packageName", this.f6163s);
                C0("switch_open", bundle2);
            }
        }
        GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return ZteFeature.isSupportDemi() ? R.string.plugin_label_demi_ai_speaker : R.string.plugin_label_ai_speaker;
    }
}
