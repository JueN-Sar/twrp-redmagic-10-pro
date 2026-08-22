package cn.nubia.gameassist.dessert.tiles;

import android.provider.Settings;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.policy.VirtualHandleAssistController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.projection.R;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class VirtualHandleTile extends QSTile implements VirtualHandleAssistController.Callback {
    private boolean v;

    public VirtualHandleTile(QSTile.Host host) {
        super(host);
        VirtualHandleAssistController.E().P(this);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        if (!M().f6176j) {
            return false;
        }
        if (RotationMgr.k()) {
            ToastUtil.a(this.f6153i.getText(R.string.try_in_land_app).toString());
            return false;
        }
        this.f6152h.b();
        boolean z = Settings.Global.getInt(this.f6153i.getContentResolver(), "app_mirror_displayid", 0) > 0;
        if (FoldMgr.f() && FoldMgr.c().e()) {
            VirtualHandleAssistController.E().C(!this.f6157m.f6175i);
        } else {
            if (z) {
                GaLog.e("VirtualHandleTile", "isHostModeStart");
                ToastUtil.a(this.f6153i.getString(cn.nubia.gameassist.R.string.only_supports_mirror_projection));
                return false;
            }
            VirtualHandleAssistController.E().B(!this.f6157m.f6175i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("handleClick cmd=");
        sb.append(this.f6157m.f6175i ? "0" : "1");
        GaLog.e("VirtualHandleTile", sb.toString());
        return super.S();
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6169c = this.f6153i.getString(cn.nubia.gameassist.R.string.ic_qs_virtual_handle);
        state.f6175i = VirtualHandleAssistController.u;
        state.f6176j = true;
        GaLog.e("VirtualHandleTile", "state.value" + state.f6175i);
        if (SystemMgr.y(this.f6153i).E() || (FoldMgr.f() && !FoldMgr.c().e())) {
            state.f6168b = QSTile.ResourceIcon.b(cn.nubia.gameassist.R.drawable.game_ic_qs_virtualhandle_unpress);
            state.f6176j = false;
        } else if (state.f6175i) {
            state.f6168b = QSTile.ResourceIcon.b(cn.nubia.gameassist.R.drawable.game_ic_qs_virtualhandle_light);
        } else {
            state.f6168b = QSTile.ResourceIcon.b(cn.nubia.gameassist.R.drawable.game_ic_qs_virtualhandle_normal);
        }
    }

    @Override // cn.nubia.gameassist.dessert.policy.VirtualHandleAssistController.Callback
    public void h(boolean z) {
        if (GameAssistWindowManager.O(this.f6153i).d0()) {
            return;
        }
        o0();
    }
}
