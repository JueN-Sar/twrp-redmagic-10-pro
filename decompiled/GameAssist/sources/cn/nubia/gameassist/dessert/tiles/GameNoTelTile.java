package cn.nubia.gameassist.dessert.tiles;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.zte.extres.R;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.mifavor.widget.AlertDialog;
import com.zte.shared.wrapper.GameKeysHelperWrapper;

/* loaded from: classes.dex */
public class GameNoTelTile extends QSTile implements ObserverManager.SettingCallback {
    private final int v;
    private int w;
    private Dialog x;

    public GameNoTelTile(QSTile.Host host) {
        super(host);
        this.v = 10;
    }

    private void B0(boolean z) {
        e0(null);
        if (this.f6157m.f6175i != z) {
            S();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C0() {
        Dialog dialog = this.x;
        if (dialog == null || !dialog.isShowing()) {
            AlertDialog a2 = new AlertDialog.Builder(this.f6153i, R.style.Theme_ZTE_Light_Dialog_Alert).m(this.f6153i.getString(com.zte.gameassist.common.R.string.dialog_default_title)).c(true).e(this.f6153i.getString(cn.nubia.gameassist.R.string.ic_qs_no_tel_toast)).i(com.zte.gameassist.common.R.string.single_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.dessert.tiles.GameNoTelTile.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    GameKeysWrapper.b().e(((QSTile) GameNoTelTile.this).f6153i, 10);
                    dialogInterface.dismiss();
                    NubiaTrackManager.p().C(GameNoTelTile.this.O(), true);
                }
            }).f(com.zte.gameassist.common.R.string.single_cancel, new DialogInterface.OnClickListener(this) { // from class: cn.nubia.gameassist.dessert.tiles.GameNoTelTile.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            }).a();
            this.x = a2;
            a2.getWindow().setType(2008);
            this.x.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.x.show();
            GameAssistDialog.f(this.x.getWindow());
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        GaLog.a(this.f6151c, "handleClick " + this.f6157m.f6175i);
        if (this.f6157m.f6175i) {
            GameKeysWrapper.b().a(this.f6153i, 10);
            NubiaTrackManager.p().C(O(), false);
        } else {
            this.f6152h.b();
            this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.d
                @Override // java.lang.Runnable
                public final void run() {
                    GameNoTelTile.this.C0();
                }
            });
        }
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor(GameKeysHelperWrapper.SETTING_GAME_MODE_STATUS), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor(GameKeysHelperWrapper.SETTING_GAME_MODE_STATUS), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6175i = (this.w & 10) != 0;
        state.f6169c = this.f6153i.getString(cn.nubia.gameassist.R.string.ic_qs_no_tel);
        state.f6168b = QSTile.ResourceIcon.b(state.f6175i ? cn.nubia.gameassist.R.drawable.game_ic_qs_tel_on : cn.nubia.gameassist.R.drawable.game_ic_qs_tel_off);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_turn_on_no_tel_mode".equals(str)) {
            B0(true);
            GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, true);
        } else if ("game_turn_off_no_tel_mode".equals(str)) {
            B0(false);
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, cn.nubia.gameassist.R.string.ic_qs_no_tel);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void m0(boolean z) {
        super.m0(z);
        if (z) {
            return;
        }
        Dialog dialog = this.x;
        if (dialog != null && dialog.isShowing()) {
            this.x.dismiss();
        }
        this.x = null;
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
        Dialog dialog = this.x;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        GameAssistDialog.f(this.x.getWindow());
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        this.w = GameKeysWrapper.b().c(this.f6153i);
        o0();
    }
}
