package cn.nubia.multisubscreen.primary;

import android.content.Context;
import cn.nubia.componentcenter.api.dessert.IChargeSeparationProxy;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class ChargeSeparateSwitchCtrl extends AbsFunCtrl implements IModuleProxy.ICallback<IChargeSeparationProxy> {

    /* renamed from: n, reason: collision with root package name */
    private IChargeSeparationProxy f7947n;

    public ChargeSeparateSwitchCtrl(Context context, String str) {
        super(context, str);
    }

    private IChargeSeparationProxy s() {
        if (this.f7947n == null) {
            this.f7947n = (IChargeSeparationProxy) c().a(IChargeSeparationProxy.class);
        }
        return this.f7947n;
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void f() {
        s().setListening(true, this);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void g() {
        s().setListening(false, this);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsFunCtrl
    public boolean o(String str) {
        s().d();
        return true;
    }

    @Override // cn.nubia.multisubscreen.primary.AbsFunCtrl
    public boolean q(String str) {
        this.f7943m = str;
        GaLog.b("MultiSubScreen_PrimaryData", "onChanged value = " + str);
        if (!d()) {
            return true;
        }
        n(str);
        return true;
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: t, reason: merged with bridge method [inline-methods] */
    public void onChanged(IChargeSeparationProxy iChargeSeparationProxy) {
        int chargeSeparationResult = iChargeSeparationProxy.getChargeSeparationResult();
        GaLog.b("MultiSubScreen_PrimaryData", "onChanged result = " + chargeSeparationResult);
        q(Integer.toString(chargeSeparationResult));
    }
}
