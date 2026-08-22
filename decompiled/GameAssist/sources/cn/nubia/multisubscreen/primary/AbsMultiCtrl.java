package cn.nubia.multisubscreen.primary;

import android.content.Context;
import com.zte.gameassist.common.GameMonitor;

/* loaded from: classes.dex */
public abstract class AbsMultiCtrl extends AbsCtrl implements GameMonitor.Callback {

    /* renamed from: j, reason: collision with root package name */
    private String f7944j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f7945k;

    public AbsMultiCtrl(Context context, String str) {
        super(context);
        this.f7944j = str;
        boolean e2 = e();
        this.f7945k = e2;
        if (e2) {
            PrimaryDeviceDataMgr.C().v(this);
        }
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    protected boolean e() {
        return false;
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void h() {
        if (this.f7945k) {
            PrimaryDeviceDataMgr.C().Z(this);
        }
    }
}
