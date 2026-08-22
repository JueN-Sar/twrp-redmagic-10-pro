package cn.nubia.multisubscreen.tiles;

import cn.nubia.gameassist.common.QSTile;
import cn.nubia.multisubscreen.secondary.SecDeviceDataMgr;
import cn.nubia.multisubscreen.utils.MultiSubScreenTileUtils;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class MultiSubScreenDessertTile extends QSTile implements MultiSubScreenUtils.GameStatusCallback {
    private boolean v;
    private final String w;

    public MultiSubScreenDessertTile(QSTile.Host host, String str) {
        super(host);
        this.w = str;
        GaLog.b("MultiSubScreen_MultiSubScreenDessertTile", "MultiSubScreenDessertTile mSpec = " + str);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        GaLog.b("MultiSubScreen_MultiSubScreenDessertTile", "MultiSubScreenDessertTile handleClick MultiSubScreenUtils.S_SOURCE_IS_IN_GAME = " + MultiSubScreenUtils.f8182l);
        if (!MultiSubScreenUtils.f8182l) {
            return false;
        }
        SecDeviceDataMgr.f().k(this.w, this.f6157m.f6175i ? "0" : "1");
        return super.S();
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        GaLog.b("MultiSubScreen_MultiSubScreenDessertTile", "MultiSubScreenDessertTile setListening listening = " + z);
        if (!z) {
            MultiSubScreenUtils.M(this);
        } else {
            o0();
            MultiSubScreenUtils.B(this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        if (!MultiSubScreenUtils.f8182l) {
            state.f6168b = MultiSubScreenTileUtils.c(this.f6153i, this.w);
        } else if (this.v) {
            state.f6168b = MultiSubScreenTileUtils.i(this.f6153i, this.w);
        } else {
            state.f6168b = MultiSubScreenTileUtils.h(this.f6153i, this.w);
        }
        state.f6175i = this.v;
        state.f6169c = MultiSubScreenTileUtils.d(this.f6153i, this.w);
        GaLog.b("MultiSubScreen_MultiSubScreenDessertTile", "MultiSubScreenDessertTile handleUpdateState state = " + state);
    }

    @Override // cn.nubia.multisubscreen.utils.MultiSubScreenUtils.GameStatusCallback
    public void s(boolean z) {
        GaLog.b("MultiSubScreen_MultiSubScreenDessertTile", "MultiSubScreenDessertTile onGameStatusChange isInGame = " + z);
        o0();
    }

    public void z0(boolean z) {
        this.v = z;
        o0();
    }
}
