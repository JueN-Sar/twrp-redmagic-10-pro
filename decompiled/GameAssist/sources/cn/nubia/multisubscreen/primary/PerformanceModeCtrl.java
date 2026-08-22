package cn.nubia.multisubscreen.primary;

import android.content.Context;
import android.text.TextUtils;
import cn.nubia.componentcenter.api.performance.IPerformanceModeController;
import com.zte.gameassist.common.SystemMgr;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class PerformanceModeCtrl extends AbsFunCtrl implements IPerformanceModeController.PerformanceModeCallback {

    /* renamed from: n, reason: collision with root package name */
    private String f7976n;

    /* renamed from: o, reason: collision with root package name */
    private IPerformanceModeController f7977o;

    /* renamed from: p, reason: collision with root package name */
    private int f7978p;

    public PerformanceModeCtrl(Context context, String str) {
        super(context, str);
        this.f7976n = "";
        this.f7978p = -1;
    }

    private IPerformanceModeController s() {
        if (this.f7977o == null) {
            this.f7977o = (IPerformanceModeController) c().a(IPerformanceModeController.class);
        }
        return this.f7977o;
    }

    private String t(int i2) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(i2);
        jSONArray.put(1);
        jSONArray.put(this.f7942l);
        return jSONArray.toString();
    }

    private void u(int i2) {
        this.f7978p = i2;
        q(t(i2));
    }

    @Override // cn.nubia.multisubscreen.primary.AbsFunCtrl, cn.nubia.multisubscreen.primary.AbsCtrl
    protected void b() {
        super.b();
        this.f7978p = -1;
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    protected boolean e() {
        return true;
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
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 2) {
                int i2 = jSONArray.getInt(0);
                if (i2 != this.f7978p && !TextUtils.isEmpty(this.f7976n)) {
                    s().setPerformanceMode(i2);
                }
                this.f7942l = jSONArray.getLong(1);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return true;
    }

    @Override // cn.nubia.componentcenter.api.performance.IPerformanceModeController.PerformanceModeCallback
    public void onDialogDismiss() {
        n(t(this.f7978p));
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        this.f7976n = SystemMgr.t();
        u(s().getPerformanceMode());
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        b();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        this.f7976n = SystemMgr.t();
        u(s().getPerformanceMode());
    }

    @Override // cn.nubia.componentcenter.api.performance.IPerformanceModeController.PerformanceModeCallback
    public void onPerformanceModeCallback(int i2) {
        if (this.f7978p != i2) {
            this.f7978p = i2;
            u(i2);
        }
    }
}
