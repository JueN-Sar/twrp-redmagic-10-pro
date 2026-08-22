package cn.nubia.multisubscreen.primary;

import android.content.Context;
import cn.nubia.componentcenter.api.dessert.IPerformanceMonitorProxy;
import com.zte.gameassist.common.IModuleProxy;

/* loaded from: classes.dex */
public class PerformaceMonitorSwitchCtrl extends SwitchCtrl implements IModuleProxy.ICallback<IPerformanceMonitorProxy> {

    /* renamed from: o, reason: collision with root package name */
    private volatile IPerformanceMonitorProxy f7975o;

    public PerformaceMonitorSwitchCtrl(Context context, String str) {
        super(context, str);
    }

    private IPerformanceMonitorProxy z() {
        if (this.f7975o == null) {
            synchronized (PerformaceMonitorSwitchCtrl.class) {
                try {
                    if (this.f7975o == null) {
                        this.f7975o = (IPerformanceMonitorProxy) c().a(IPerformanceMonitorProxy.class);
                    }
                } finally {
                }
            }
        }
        return this.f7975o;
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public void onChanged(IPerformanceMonitorProxy iPerformanceMonitorProxy) {
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
