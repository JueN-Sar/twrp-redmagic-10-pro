package cn.nubia.multisubscreen.primary;

import android.content.Context;
import cn.nubia.componentcenter.api.dessert.IMisOperateProxy;
import com.zte.gameassist.common.IModuleProxy;

/* loaded from: classes.dex */
public class MisOperateSwitchCtrl extends SwitchCtrl implements IModuleProxy.ICallback<IMisOperateProxy> {

    /* renamed from: o, reason: collision with root package name */
    private IMisOperateProxy f7956o;

    public MisOperateSwitchCtrl(Context context, String str) {
        super(context, str);
    }

    private synchronized IMisOperateProxy z() {
        try {
            if (this.f7956o == null) {
                this.f7956o = (IMisOperateProxy) c().a(IMisOperateProxy.class);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f7956o;
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public void onChanged(IMisOperateProxy iMisOperateProxy) {
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
