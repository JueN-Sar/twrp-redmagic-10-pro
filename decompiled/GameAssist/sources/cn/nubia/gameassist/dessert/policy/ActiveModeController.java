package cn.nubia.gameassist.dessert.policy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.ActivityManagerWrapper;
import java.util.Map;

/* loaded from: classes.dex */
public class ActiveModeController {

    /* renamed from: a, reason: collision with root package name */
    private Context f6278a;

    /* renamed from: b, reason: collision with root package name */
    private int f6279b = -1;

    /* renamed from: c, reason: collision with root package name */
    private Handler f6280c;

    /* renamed from: d, reason: collision with root package name */
    public WackLockController f6281d;

    /* renamed from: e, reason: collision with root package name */
    public SharedPreferences f6282e;

    public ActiveModeController(Context context) {
        this.f6280c = null;
        this.f6278a = context;
        this.f6280c = new Handler(ThreadManager.c().b());
        this.f6281d = new WackLockController(this.f6278a);
        this.f6282e = this.f6278a.getSharedPreferences("active_mode_free_form_list", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(boolean z) {
        Settings.Global.putInt(this.f6278a.getContentResolver(), "active_mode_on", z ? 1 : 0);
        GaLog.a("ActiveModeController", " setActiveModeOn : " + z);
    }

    private void n(String str) {
        SharedPreferences.Editor edit = this.f6282e.edit();
        edit.remove(e(str));
        edit.apply();
    }

    private void q() {
        SharedPreferences.Editor edit = this.f6282e.edit();
        for (Map.Entry<String, ?> entry : this.f6282e.getAll().entrySet()) {
            r(entry.getKey(), ((Integer) entry.getValue()).intValue());
            edit.remove(entry.getKey());
        }
        edit.apply();
    }

    public void b() {
        this.f6281d.a();
    }

    public void c() {
        GaLog.a("ActiveModeController", "getActiveModeLog: ActiveEntries=" + this.f6282e.getAll() + " WackLockEntries=" + this.f6281d.f6324d.getAll());
    }

    public int d(String str) {
        return this.f6282e.getInt(e(str), -1);
    }

    public String e(String str) {
        StringBuilder sb;
        if (str != null) {
            sb = new StringBuilder(str);
        } else {
            GaLog.a("ActiveModeController", "getKeyName: pkg is null, warning!!!");
            sb = new StringBuilder("");
        }
        sb.append("-");
        sb.append(this.f6279b);
        return sb.toString();
    }

    public int f() {
        return this.f6279b;
    }

    public boolean g() {
        return this.f6282e.getAll().isEmpty() && this.f6281d.f6324d.getAll().isEmpty();
    }

    public boolean h(String str) {
        return this.f6282e.getInt(e(str), -1) >= 0;
    }

    public boolean i(String str, int i2) {
        GaLog.a("ActiveModeController", " isAllowNubiaFreeform: packageName=" + e(str) + " taskId=" + i2);
        return ActivityManagerWrapper.checkTaskSupportWr(i2);
    }

    public boolean j(String str) {
        return this.f6281d.b(e(str));
    }

    public void l() {
        this.f6281d.c();
    }

    public void m(String str) {
        n(str);
        o(str);
    }

    public void o(String str) {
        this.f6281d.d(e(str));
    }

    public void p() {
        GaLog.a("ActiveModeController", " resetActiveModeSharedPreAllKey");
        q();
        this.f6281d.e();
        s(false);
    }

    public void r(String str, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("app_name", str);
        bundle.putInt("count", i2);
        NubiaTrackManager.p().x("cn.nubia.gameassist", "active_mode_used", bundle);
    }

    public void s(final boolean z) {
        this.f6280c.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.a
            @Override // java.lang.Runnable
            public final void run() {
                ActiveModeController.this.k(z);
            }
        });
    }

    public void t(String str, int i2) {
        SharedPreferences.Editor edit = this.f6282e.edit();
        edit.putInt(e(str), i2);
        edit.apply();
    }

    public void u(String str, int i2) {
        this.f6281d.f(e(str), i2);
    }

    public boolean v(int i2, int i3) {
        return ActivityManagerWrapper.toggleSwitchFromFullScreenToFreeformWr(i2, i3);
    }

    public void w() {
        this.f6279b = SystemMgr.w();
    }
}
