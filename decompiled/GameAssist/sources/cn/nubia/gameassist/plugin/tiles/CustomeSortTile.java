package cn.nubia.gameassist.plugin.tiles;

import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.plugin.sort.PluginSortWindow;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.SharedPreferencesUtil;

/* loaded from: classes.dex */
public class CustomeSortTile extends QSTile {
    public CustomeSortTile(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public String O() {
        return "custome_sort";
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        SharedPreferencesUtil.k(this.f6153i).g0(this.f6163s, 3);
        ((PluginSortWindow) InflaterHelper.e(R.layout.plugin_custome_panel_content)).l();
        this.f6152h.b();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
    }
}
