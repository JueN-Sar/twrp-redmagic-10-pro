package cn.nubia.plugin.timer;

import android.content.SharedPreferences;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class TimerDataMgr {

    /* renamed from: a, reason: collision with root package name */
    public boolean f8723a = false;

    /* renamed from: b, reason: collision with root package name */
    public int f8724b = 300;

    /* renamed from: c, reason: collision with root package name */
    public int f8725c = 200;

    /* renamed from: d, reason: collision with root package name */
    private ArrayList f8726d = new ArrayList();

    /* renamed from: f, reason: collision with root package name */
    private String f8728f = "";

    /* renamed from: e, reason: collision with root package name */
    private SharedPreferences f8727e = GameAssistApplication.j().getSharedPreferences("nubia_plugin_timer_sp", 0);

    private void c() {
        if (this.f8724b < 0) {
            this.f8724b = 100;
            GaLog.e("TimerDataMgr", "invalidPoint stage1");
        }
        if (this.f8725c < 0) {
            this.f8725c = 100;
            GaLog.e("TimerDataMgr", "invalidPoint stage2");
        }
        boolean j2 = RotationMgr.j();
        int g2 = RotationMgr.g();
        int f2 = RotationMgr.f();
        if (j2) {
            if (this.f8724b > f2) {
                this.f8724b = f2 - 200;
                GaLog.e("TimerDataMgr", "invalidPoint stage3");
            }
            if (this.f8725c > g2) {
                this.f8725c = g2 - 200;
                GaLog.e("TimerDataMgr", "invalidPoint stage4");
                return;
            }
            return;
        }
        if (this.f8724b > g2) {
            this.f8724b = g2 - 200;
            GaLog.e("TimerDataMgr", "invalidPoint stage5");
        }
        if (this.f8725c > f2) {
            this.f8725c = f2 - 200;
            GaLog.e("TimerDataMgr", "invalidPoint stage5");
        }
    }

    private int f(String str, int i2) {
        if ("com.tencent.lolm".equals(str)) {
            return 90;
        }
        return "com.tencent.KiHan".equals(str) ? i2 == 0 ? 15 : 50 : "com.tencent.tmgp.sgame".equals(str) ? i2 == 0 ? 60 : 90 : i2 == 0 ? 20 : 30;
    }

    private JSONObject g() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("curpkgopen", this.f8723a);
            jSONObject.put("x", this.f8724b);
            jSONObject.put("y", this.f8725c);
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < this.f8726d.size(); i2++) {
                jSONArray.put(i2, ((TimerItemData) this.f8726d.get(i2)).a());
            }
            jSONObject.put("itemlist", jSONArray);
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void i(String str) {
        this.f8723a = false;
        k();
        this.f8726d.clear();
        this.f8726d.add(j(0, true, 20));
        this.f8726d.add(j(1, true, 20));
        this.f8726d.add(j(2, false, f(str, 0)));
        this.f8726d.add(j(3, false, f(str, 1)));
        GaLog.e("TimerDataMgr", "initDefaultData mCurApp=" + Utils.j() + this.f8726d.size());
    }

    private TimerItemData j(int i2, boolean z, int i3) {
        TimerItemData timerItemData = new TimerItemData();
        timerItemData.f8729a = i2;
        timerItemData.f8730b = z;
        timerItemData.f8731c = i3;
        return timerItemData;
    }

    private void k() {
        if (RotationMgr.j()) {
            this.f8724b = 936;
            this.f8725c = 110;
        } else {
            this.f8724b = 276;
            this.f8725c = 200;
        }
    }

    private void m(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f8723a = jSONObject.getBoolean("curpkgopen");
            this.f8724b = jSONObject.getInt("x");
            this.f8725c = jSONObject.getInt("y");
            JSONArray jSONArray = jSONObject.getJSONArray("itemlist");
            this.f8726d.clear();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                TimerItemData timerItemData = new TimerItemData();
                timerItemData.b((JSONObject) jSONArray.get(i2));
                this.f8726d.add(timerItemData);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            i(str2);
        }
    }

    public void a(String str, int i2) {
        if (i2 >= this.f8726d.size()) {
            GaLog.b("TimerDataMgr", "changeMode size=" + this.f8726d.size());
        }
        ((TimerItemData) this.f8726d.get(i2)).f8730b = !((TimerItemData) this.f8726d.get(i2)).f8730b;
    }

    public void b(String str, boolean z) {
        if (this.f8726d.size() == 0) {
            h(str);
        }
        this.f8723a = z;
        o(str);
    }

    public TimerItemData d(int i2) {
        if (i2 >= this.f8726d.size()) {
            GaLog.b("TimerDataMgr", "get mDataList size=" + this.f8726d.size());
        }
        return (TimerItemData) this.f8726d.get(i2);
    }

    public String e() {
        JSONObject g2 = g();
        if (g2 != null) {
            return g2.toString();
        }
        return null;
    }

    public void h(String str) {
        String string = this.f8727e.getString(str, null);
        if (string == null) {
            i(str);
            return;
        }
        this.f8728f = string;
        GaLog.e("TimerDataMgr", "initData " + string);
        m(string, str);
        c();
    }

    public boolean l() {
        return this.f8723a;
    }

    public void n(List list) {
        if (list == null || list.isEmpty()) {
            GaLog.k("TimerDataMgr", "removeTimerData: packageNameList is null or empty");
            return;
        }
        SharedPreferences.Editor edit = this.f8727e.edit();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && !str.isEmpty()) {
                edit.remove(str);
                GaLog.e("TimerDataMgr", "removeTimerData: removed " + str + " from nubia_plugin_timer_sp");
            }
        }
        edit.commit();
    }

    public void o(String str) {
        SharedPreferences.Editor edit = this.f8727e.edit();
        edit.putString(str, e());
        edit.commit();
        GaLog.e("TimerDataMgr", "saveData app=" + str + " data=" + e());
    }

    public void p(String str, int i2, int i3) {
        this.f8724b = i2;
        this.f8725c = i3;
        o(str);
    }
}
