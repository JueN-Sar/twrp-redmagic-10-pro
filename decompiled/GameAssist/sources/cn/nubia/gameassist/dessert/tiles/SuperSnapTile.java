package cn.nubia.gameassist.dessert.tiles;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class SuperSnapTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;

    public SuperSnapTile(QSTile.Host host) {
        super(host);
    }

    private void A0() {
        GaLog.a("----->SuperSnap", "sendScrollBroadcast()");
        Intent intent = new Intent();
        intent.putExtra("step", 9);
        intent.setAction("com.zte.scrollscreenshot");
        this.f6153i.sendBroadcast(intent);
    }

    private void B0() {
        this.f6152h.b();
        this.f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.m
            @Override // java.lang.Runnable
            public final void run() {
                SuperSnapTile.this.C0();
            }
        }, 350L);
        NubiaTrackManager.p().k(O());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C0() {
        A0();
        ComponentName componentName = new ComponentName("com.android.ztescreenshot", "com.android.ztescreenshot.cropimage.CropImageService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        this.f6153i.startService(intent);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        if (!this.f6157m.f6176j) {
            return true;
        }
        B0();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            o0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        if (state.f6176j) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_supersnap_normal);
            state.f6175i = false;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_supersnap_uninstall);
            state.f6175i = false;
        }
        state.f6169c = this.f6153i.getString(R.string.ic_qs_snap);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_click_super_screenshot".equals(str)) {
            B0();
            GameAgentUtil.k(this.f6153i, iGameAssistClientCallback, inMsg);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
