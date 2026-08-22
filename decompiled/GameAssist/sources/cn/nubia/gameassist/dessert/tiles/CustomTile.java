package cn.nubia.gameassist.dessert.tiles;

import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.custom.CustomTileOrder;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class CustomTile extends QSTile {
    public CustomTile(QSTile.Host host) {
        super(host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A0() {
        ((CustomTileOrder) InflaterHelper.e(R.layout.qs_customize_panel_content)).l();
        this.f6152h.b();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.c
            @Override // java.lang.Runnable
            public final void run() {
                CustomTile.this.A0();
            }
        });
        GaLog.e(this.f6151c, "Custom.handleClick");
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
        state.f6169c = this.f6153i.getString(R.string.ic_qs_custome);
        state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_custome_off);
    }
}
