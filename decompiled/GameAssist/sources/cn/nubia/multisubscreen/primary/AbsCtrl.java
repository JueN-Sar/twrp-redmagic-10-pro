package cn.nubia.multisubscreen.primary;

import android.content.Context;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.GameAssistComService;
import com.zte.gameassist.common.GameMonitor;

/* loaded from: classes.dex */
public abstract class AbsCtrl implements ICtrl, GameMonitor.Callback {

    /* renamed from: c, reason: collision with root package name */
    private boolean f7937c;

    /* renamed from: h, reason: collision with root package name */
    protected Context f7938h;

    /* renamed from: i, reason: collision with root package name */
    private GameAssistComService f7939i;

    public AbsCtrl(Context context) {
        this.f7938h = context;
        if (e()) {
            PrimaryDeviceDataMgr.C().v(this);
        }
    }

    protected abstract void b();

    protected GameAssistComService c() {
        if (this.f7939i == null) {
            this.f7939i = (GameAssistComService) Router.getInstance().getService(GameAssistComService.class.getSimpleName());
        }
        return this.f7939i;
    }

    public boolean d() {
        return this.f7937c;
    }

    protected boolean e() {
        return false;
    }

    public abstract void f();

    public abstract void g();

    public void h() {
        if (e()) {
            PrimaryDeviceDataMgr.C().Z(this);
        }
    }

    public void i() {
        if (this.f7937c) {
            return;
        }
        this.f7937c = true;
        f();
    }

    public void j() {
        if (this.f7937c) {
            this.f7937c = false;
            g();
            b();
        }
    }
}
