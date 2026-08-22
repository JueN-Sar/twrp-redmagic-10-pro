package cn.nubia.multisubscreen.primary;

import android.content.Context;
import cn.nubia.componentcenter.api.meditation.IMeditationModeController;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class NotificationModeCtrl extends AbsFunCtrl implements IMeditationModeController.MeditationModeCallback {

    /* renamed from: n, reason: collision with root package name */
    private IMeditationModeController f7957n;

    /* renamed from: o, reason: collision with root package name */
    private int f7958o;

    public NotificationModeCtrl(Context context, String str) {
        super(context, str);
    }

    private IMeditationModeController s() {
        if (this.f7957n == null) {
            this.f7957n = (IMeditationModeController) c().a(IMeditationModeController.class);
        }
        return this.f7957n;
    }

    private String t(int i2) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(i2);
        jSONArray.put(1);
        jSONArray.put(this.f7942l);
        return jSONArray.toString();
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void f() {
        int meditationMode = s().getMeditationMode();
        this.f7958o = meditationMode;
        r(t(meditationMode));
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
                GaLog.e("MultiSubScreen_PrimaryData", "modify notification to " + i2 + " from " + this.f7958o);
                if (i2 != this.f7958o) {
                    s().setMeditationMode(i2);
                }
                this.f7942l = jSONArray.getLong(1);
            } else {
                GaLog.e("MultiSubScreen_PrimaryData", "receive notification data length " + jSONArray.length());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return true;
    }

    @Override // cn.nubia.componentcenter.api.meditation.IMeditationModeController.MeditationModeCallback
    public void onMeditationModeCallback(int i2) {
        if (this.f7958o != i2) {
            this.f7958o = i2;
            q(t(i2));
        }
    }
}
