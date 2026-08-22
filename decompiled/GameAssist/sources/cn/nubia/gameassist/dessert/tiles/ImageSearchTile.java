package cn.nubia.gameassist.dessert.tiles;

import android.content.Intent;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.common.SystemMgr;

/* loaded from: classes.dex */
public class ImageSearchTile extends QSTile {
    public ImageSearchTile(QSTile.Host host) {
        super(host);
    }

    private void z0() {
        Intent intent = new Intent();
        intent.setAction("cn.zte.gamefloat.image.search");
        intent.setPackage("cn.zte.gamefloat");
        intent.putExtra("event", "start");
        intent.putExtra("packageName", SystemMgr.t());
        this.f6153i.startService(intent);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        this.f6152h.b();
        z0();
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
        super.c0(state, obj);
        state.f6176j = this.f6162r;
        state.f6169c = this.f6153i.getString(R.string.ic_qs_image_search);
        state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_image_search_off);
    }
}
