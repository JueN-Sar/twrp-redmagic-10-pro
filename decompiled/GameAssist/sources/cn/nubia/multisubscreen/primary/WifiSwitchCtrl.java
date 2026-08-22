package cn.nubia.multisubscreen.primary;

import android.content.Context;
import cn.nubia.componentcenter.api.dessert.IWifiProxy;
import com.zte.gameassist.common.IModuleProxy;

/* loaded from: classes.dex */
public class WifiSwitchCtrl extends SwitchCtrl implements IModuleProxy.ICallback<IWifiProxy> {

    /* renamed from: o, reason: collision with root package name */
    private IWifiProxy f7996o;

    public WifiSwitchCtrl(Context context, String str) {
        super(context, str);
    }

    private synchronized IWifiProxy z() {
        try {
            if (this.f7996o == null) {
                this.f7996o = (IWifiProxy) c().a(IWifiProxy.class);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f7996o;
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public void onChanged(IWifiProxy iWifiProxy) {
        y(z().c());
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void f() {
        z().setListening(true, this);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void g() {
        z().setListening(false, this);
    }

    @Override // cn.nubia.multisubscreen.primary.SwitchCtrl
    protected boolean t() {
        return z().c();
    }

    @Override // cn.nubia.multisubscreen.primary.SwitchCtrl
    protected boolean x(boolean z) {
        z().d();
        return true;
    }
}
