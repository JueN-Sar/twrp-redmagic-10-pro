package cn.nubia.multisubscreen.primary;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.gameassist.common.ThreadManager;

/* loaded from: classes.dex */
public class FanSwitchCtrl extends SwitchCtrl {

    /* renamed from: o, reason: collision with root package name */
    private ContentObserver f7954o;

    public FanSwitchCtrl(Context context, String str) {
        super(context, str);
        this.f7954o = new ContentObserver(new Handler(ThreadManager.c().f())) { // from class: cn.nubia.multisubscreen.primary.FanSwitchCtrl.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                FanSwitchCtrl fanSwitchCtrl = FanSwitchCtrl.this;
                fanSwitchCtrl.y(fanSwitchCtrl.t());
            }
        };
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void f() {
        this.f7938h.getContentResolver().registerContentObserver(Settings.System.getUriFor("fan_state_of_manual"), false, this.f7954o);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void g() {
        this.f7938h.getContentResolver().unregisterContentObserver(this.f7954o);
    }

    @Override // cn.nubia.multisubscreen.primary.SwitchCtrl
    protected boolean t() {
        return MultiSubScreenUtils.r(this.f7938h);
    }

    @Override // cn.nubia.multisubscreen.primary.SwitchCtrl
    protected boolean x(boolean z) {
        Settings.System.putInt(this.f7938h.getContentResolver(), "fan_state_of_manual", z ? 6 : -6);
        return true;
    }
}
