package cn.nubia.multisubscreen.primary;

import android.content.Context;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public abstract class SwitchCtrl extends AbsFunCtrl {

    /* renamed from: n, reason: collision with root package name */
    protected boolean f7994n;

    public SwitchCtrl(Context context, String str) {
        super(context, str);
    }

    private String s(boolean z) {
        return z ? "1" : "0";
    }

    @Override // cn.nubia.multisubscreen.primary.AbsFunCtrl
    public String l() {
        String str = this.f7943m;
        return str != null ? str : s(t());
    }

    @Override // cn.nubia.multisubscreen.primary.AbsFunCtrl
    public boolean o(String str) {
        if (!u(str)) {
            return false;
        }
        GaLog.e("MultiSubScreen_PrimaryData", "modify " + k() + " to " + str);
        x(v(str));
        return true;
    }

    protected abstract boolean t();

    protected boolean u(String str) {
        return "1".equals(str) || "0".equals(str);
    }

    protected boolean v(String str) {
        return "1".equals(str);
    }

    protected abstract boolean x(boolean z);

    protected boolean y(boolean z) {
        this.f7994n = z;
        return q(s(z));
    }
}
