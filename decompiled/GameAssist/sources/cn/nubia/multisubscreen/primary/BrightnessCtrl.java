package cn.nubia.multisubscreen.primary;

import android.content.Context;
import cn.nubia.componentcenter.api.dessert.IAppBrightnessProxy;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class BrightnessCtrl extends AbsFunCtrl implements IModuleProxy.ICallback<IAppBrightnessProxy> {

    /* renamed from: n, reason: collision with root package name */
    private IAppBrightnessProxy f7946n;

    public BrightnessCtrl(Context context, String str) {
        super(context, str);
    }

    private String t(int i2, int i3) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(i2);
        jSONArray.put(i3);
        jSONArray.put(this.f7942l);
        return jSONArray.toString();
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void f() {
        r(t(s().getProgress(), s().getMax()));
        s().setListening(true, this);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void g() {
        s().setListening(false, this);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsFunCtrl
    public boolean o(String str) {
        JSONArray jSONArray;
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (jSONArray.length() != 4) {
            GaLog.e("MultiSubScreen_PrimaryData", "receive brightness data length " + jSONArray.length());
            return true;
        }
        int i2 = jSONArray.getInt(0);
        int i3 = jSONArray.getInt(1);
        int i4 = jSONArray.getInt(2);
        this.f7942l = jSONArray.getLong(3);
        if (i3 != s().getMax()) {
            GaLog.e("MultiSubScreen_PrimaryData", "receive brightness data max " + i3 + ", current brightness is " + s().getMax());
            return true;
        }
        if (i4 == 0) {
            s().setProgress(i2, true);
        } else {
            if (i2 != s().getProgress()) {
                s().setProgress(i2, true);
            }
            GaLog.e("MultiSubScreen_PrimaryData", "modify brightness to " + i2);
            s().stopTrackingTouch(this);
        }
        return true;
    }

    protected IAppBrightnessProxy s() {
        if (this.f7946n == null) {
            this.f7946n = (IAppBrightnessProxy) c().a(IAppBrightnessProxy.class);
        }
        return this.f7946n;
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: u, reason: merged with bridge method [inline-methods] */
    public void onChanged(IAppBrightnessProxy iAppBrightnessProxy) {
        q(t(iAppBrightnessProxy.getProgress(), iAppBrightnessProxy.getMax()));
    }
}
