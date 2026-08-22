package cn.nubia.gameassist.dessert.tiles;

import android.content.ContentProviderClient;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class LinkMicsTranslationTile extends QSTile implements GameMonitor.Callback {
    private static final Uri w = Uri.parse("content://com.zte.aitranslation.contentProvider");
    private String v;

    public LinkMicsTranslationTile(QSTile.Host host) {
        super(host);
        SystemMgr.y(this.f6153i).h(this);
    }

    private boolean A0(String str) {
        String string = Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_ai_translation_enabled_pkg");
        if (!TextUtils.isEmpty(string)) {
            for (String str2 : string.split(",")) {
                if (str2 != null && str2.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void C0(boolean z) {
        e0(null);
        if (this.f6157m.f6175i != z) {
            S();
        }
    }

    public void B0(String str, Bundle bundle) {
        ContentProviderClient acquireUnstableContentProviderClient;
        ContentProviderClient contentProviderClient = null;
        try {
            try {
                acquireUnstableContentProviderClient = this.f6153i.getContentResolver().acquireUnstableContentProviderClient(w);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        if (acquireUnstableContentProviderClient == null) {
            if (acquireUnstableContentProviderClient != null) {
                acquireUnstableContentProviderClient.close();
                return;
            }
            return;
        }
        try {
            acquireUnstableContentProviderClient.call(str, null, bundle);
            acquireUnstableContentProviderClient.close();
        } catch (Exception e3) {
            e = e3;
            contentProviderClient = acquireUnstableContentProviderClient;
            GaLog.c(this.f6151c, "linkTranslationProvider: e = ", e);
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

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        Bundle bundle = new Bundle();
        bundle.putString("packageName", this.v);
        bundle.putInt("user_id", SystemMgr.w());
        B0(this.f6157m.f6175i ? "switch_close" : "switch_open", bundle);
        this.f6152h.b();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            o0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6175i = A0(this.v);
        state.f6169c = this.f6153i.getString(R.string.ic_qs_link_mics_captions_title);
        state.f6168b = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.game_ic_qs_link_mics_translation_light : R.drawable.game_ic_qs_link_mics_translation_normal);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_turn_on_game_voice_translation".equals(str)) {
            C0(true);
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_link_mics_captions_title);
        } else if ("game_turn_off_game_voice_translation".equals(str)) {
            C0(false);
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_link_mics_captions_title);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        z0();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        z0();
    }

    public void z0() {
        String t = SystemMgr.t();
        this.v = t;
        if (A0(t)) {
            Bundle bundle = new Bundle();
            bundle.putString("packageName", this.v);
            bundle.putInt("user_id", SystemMgr.w());
            B0("GameUpdate", bundle);
        }
    }
}
