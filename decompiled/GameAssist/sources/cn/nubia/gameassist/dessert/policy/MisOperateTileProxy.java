package cn.nubia.gameassist.dessert.policy;

import android.net.Uri;
import android.provider.Settings;
import cn.nubia.componentcenter.api.dessert.IMisOperateProxy;
import cn.nubia.gameassist.common.AbsTileProxy;
import com.zte.gameassist.common.ModuleProxyContext;
import com.zte.gameassist.common.ObserverManager;

/* loaded from: classes.dex */
public class MisOperateTileProxy extends AbsTileProxy implements IMisOperateProxy, ObserverManager.SettingCallback {
    public MisOperateTileProxy(ModuleProxyContext moduleProxyContext) {
        super(moduleProxyContext);
    }

    @Override // com.zte.gameassist.common.ITileProxy
    public void a(boolean z) {
        if (l() != z) {
            m(z);
        }
    }

    @Override // cn.nubia.gameassist.common.AbsTileProxy, com.zte.gameassist.common.ITileProxy
    public boolean d() {
        super.d();
        m(c());
        return false;
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    public void g() {
        ObserverManager.c().b(this.f16454h.a(), Settings.Global.getUriFor("cc_game_mis_operate"), this);
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    public void i() {
        ObserverManager.c().d(this.f16454h.a(), Settings.Global.getUriFor("cc_game_mis_operate"), this);
    }

    @Override // cn.nubia.gameassist.common.AbsTileProxy
    protected void k() {
        this.f6111k = l();
    }

    public boolean l() {
        return Settings.Global.getInt(this.f16454h.a().getContentResolver(), "cc_game_mis_operate", 1) == 1;
    }

    public void m(boolean z) {
        Settings.Global.putInt(this.f16454h.a().getContentResolver(), "cc_game_mis_operate", z ? 1 : 0);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        j();
    }
}
