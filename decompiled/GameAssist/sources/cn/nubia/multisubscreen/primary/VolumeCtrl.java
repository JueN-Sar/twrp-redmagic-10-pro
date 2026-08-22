package cn.nubia.multisubscreen.primary;

import android.content.Context;
import cn.nubia.componentcenter.api.volume.IVolumeController;
import cn.nubia.componentcenter.api.volume.VolumeListener;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class VolumeCtrl extends AbsFunCtrl implements VolumeListener {

    /* renamed from: n, reason: collision with root package name */
    private IVolumeController f7995n;

    public VolumeCtrl(Context context, String str) {
        super(context, str);
    }

    private String s(int i2, int i3) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(i2);
        jSONArray.put(i3);
        jSONArray.put(this.f7942l);
        return jSONArray.toString();
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void f() {
        r(s(t().getProgress(), t().getMax()));
        t().setListening(true, this);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void g() {
        t().setListening(false, this);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsFunCtrl
    public boolean o(String str) {
        JSONArray jSONArray;
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (jSONArray.length() != 3) {
            GaLog.e("MultiSubScreen_PrimaryData", "receive volume data length " + jSONArray.length());
            return true;
        }
        int i2 = jSONArray.getInt(0);
        int i3 = jSONArray.getInt(1);
        this.f7942l = jSONArray.getLong(2);
        if (i3 == t().getMax()) {
            GaLog.e("MultiSubScreen_PrimaryData", "modify volume to " + i2);
            t().setVolume(i2);
            return true;
        }
        GaLog.e("MultiSubScreen_PrimaryData", "receive volume data max " + i3 + ", current volume is " + t().getMax());
        return true;
    }

    @Override // cn.nubia.componentcenter.api.volume.VolumeListener
    public void onVolumeChanged(int i2, int i3) {
        q(s(i2, i3));
    }

    protected IVolumeController t() {
        if (this.f7995n == null) {
            this.f7995n = (IVolumeController) c().a(IVolumeController.class);
        }
        return this.f7995n;
    }
}
