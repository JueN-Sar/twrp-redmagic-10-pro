package cn.nubia.gameassist.dessert.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import cn.nubia.componentcenter.api.dessert.IWifiProxy;
import cn.nubia.gameassist.common.AbsTileProxy;
import com.zte.gameassist.common.ModuleProxyContext;

/* loaded from: classes.dex */
public class WifiTileProxy extends AbsTileProxy implements IWifiProxy {

    /* renamed from: l, reason: collision with root package name */
    private final WifiManager f6325l;

    /* renamed from: m, reason: collision with root package name */
    private final BroadcastReceiver f6326m;

    public WifiTileProxy(ModuleProxyContext moduleProxyContext) {
        super(moduleProxyContext);
        this.f6326m = new BroadcastReceiver() { // from class: cn.nubia.gameassist.dessert.policy.WifiTileProxy.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                WifiTileProxy.this.j();
            }
        };
        this.f6325l = (WifiManager) this.f16454h.a().getSystemService("wifi");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(boolean z) {
        this.f6325l.setWifiEnabled(z);
    }

    @Override // cn.nubia.gameassist.common.AbsTileProxy, com.zte.gameassist.common.ITileProxy
    public boolean d() {
        super.d();
        p(this.f6111k);
        return false;
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    public void g() {
        this.f16454h.a().registerReceiver(this.f6326m, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"), null, this.f16454h.b(), 2);
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    public void i() {
        this.f16454h.a().unregisterReceiver(this.f6326m);
    }

    @Override // cn.nubia.gameassist.common.AbsTileProxy
    protected void k() {
        this.f6111k = this.f6325l.getWifiState() == 3;
    }

    public void p(final boolean z) {
        this.f16454h.b().post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.p
            @Override // java.lang.Runnable
            public final void run() {
                WifiTileProxy.this.o(z);
            }
        });
    }
}
