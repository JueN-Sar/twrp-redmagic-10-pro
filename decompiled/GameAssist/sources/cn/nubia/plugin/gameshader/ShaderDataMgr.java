package cn.nubia.plugin.gameshader;

import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.utils.ThreadPoolUtils;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ShaderDataMgr {

    /* renamed from: a, reason: collision with root package name */
    private SharedPreferences f8454a;

    /* renamed from: d, reason: collision with root package name */
    public boolean f8457d;

    /* renamed from: b, reason: collision with root package name */
    private ArrayList f8455b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    public ShaderItemData f8456c = new ShaderItemData();

    /* renamed from: e, reason: collision with root package name */
    private boolean f8458e = false;

    public ShaderDataMgr() {
        this.f8457d = false;
        SharedPreferences sharedPreferences = GameAssistApplication.j().getSharedPreferences("plugin_gameshader_sp", 0);
        this.f8454a = sharedPreferences;
        this.f8457d = sharedPreferences.getBoolean("plugin_gameshader_first_time_launch", true);
    }

    private JSONObject h() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < this.f8455b.size(); i2++) {
                jSONArray.put(i2, ((ShaderItemData) this.f8455b.get(i2)).a());
            }
            jSONObject.put("itemlist", jSONArray);
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void k() {
        ShaderItemData shaderItemData = this.f8456c;
        shaderItemData.f8464e = 200;
        shaderItemData.f8465f = 200;
        shaderItemData.f8463d = 1;
        GaLog.e("GameShaderMgr", "initDefaultData mCurApp=" + this.f8456c.f8462c);
    }

    private void n(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("itemlist");
            this.f8455b.clear();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                ShaderItemData shaderItemData = new ShaderItemData();
                shaderItemData.b((JSONObject) jSONArray.get(i2));
                this.f8455b.add(shaderItemData);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void a(String str, boolean z) {
        this.f8456c.f8460a = z;
        p(str);
    }

    public void b(String str, int i2, int i3) {
        ShaderItemData shaderItemData = this.f8456c;
        shaderItemData.f8464e = i2;
        shaderItemData.f8465f = i3;
        p(str);
    }

    public void c(String str) {
        this.f8456c.f8461b = !r0.f8461b;
        p(str);
    }

    public void d(String str, int i2) {
        ShaderItemData shaderItemData = this.f8456c;
        shaderItemData.f8460a = true;
        shaderItemData.f8463d = i2;
        shaderItemData.f8466g = ShaderUtils.h(i2);
        p(str);
    }

    public boolean e() {
        ShaderItemData shaderItemData = this.f8456c;
        if (shaderItemData.f8464e < 0) {
            shaderItemData.f8464e = 100;
            GaLog.e("GameShaderMgr", "invalidPoint stage1");
            return true;
        }
        if (shaderItemData.f8465f < 0) {
            shaderItemData.f8465f = 100;
            GaLog.e("GameShaderMgr", "invalidPoint stage2");
            return true;
        }
        boolean j2 = RotationMgr.j();
        int g2 = RotationMgr.g();
        int f2 = RotationMgr.f();
        if (j2) {
            if (shaderItemData.f8464e > f2) {
                shaderItemData.f8464e = f2 - 100;
                GaLog.e("GameShaderMgr", "invalidPoint stage3");
                return true;
            }
            if (shaderItemData.f8465f <= g2) {
                return false;
            }
            shaderItemData.f8465f = g2 - 100;
            GaLog.e("GameShaderMgr", "invalidPoint stage4");
            return true;
        }
        if (shaderItemData.f8464e > g2) {
            shaderItemData.f8464e = g2 - 100;
            GaLog.e("GameShaderMgr", "invalidPoint stage5");
            return true;
        }
        if (shaderItemData.f8465f <= f2) {
            return false;
        }
        shaderItemData.f8465f = f2 - 100;
        GaLog.e("GameShaderMgr", "invalidPoint stage5");
        return true;
    }

    public int f() {
        return this.f8456c.f8463d;
    }

    public String g() {
        JSONObject h2 = h();
        if (h2 != null) {
            return h2.toString();
        }
        return null;
    }

    public boolean i() {
        return this.f8458e;
    }

    public void j(String str) {
        boolean z;
        String string = this.f8454a.getString("plugin_gameshader_prefer", null);
        if (string == null) {
            string = "";
        }
        if (TextUtils.isEmpty(string)) {
            GaLog.e("GameShaderMgr", "initData " + string);
        }
        n(string);
        Iterator it = this.f8455b.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            ShaderItemData shaderItemData = (ShaderItemData) it.next();
            if (str.equals(shaderItemData.f8462c)) {
                this.f8456c = shaderItemData;
                z = true;
                break;
            }
        }
        this.f8458e = z;
        if (z) {
            return;
        }
        ShaderItemData shaderItemData2 = new ShaderItemData();
        this.f8456c = shaderItemData2;
        shaderItemData2.f8462c = str;
        k();
    }

    public boolean l() {
        return this.f8456c.f8460a;
    }

    public boolean m() {
        return this.f8456c.f8461b;
    }

    public void o(ShaderItemData shaderItemData) {
        StringBuilder sb = new StringBuilder();
        sb.append(shaderItemData.f8462c);
        sb.append(",");
        sb.append(shaderItemData.f8460a ? "1" : "0");
        sb.append(",");
        sb.append(ShaderUtils.c(shaderItemData.f8463d));
        final String sb2 = sb.toString();
        ThreadPoolUtils.b(new Runnable(this) { // from class: cn.nubia.plugin.gameshader.ShaderDataMgr.1
            @Override // java.lang.Runnable
            public void run() {
                Settings.Global.putString(GameAssistApplication.j().getContentResolver(), "gameassist_track_hunt_mode", sb2);
            }
        });
    }

    public void p(String str) {
        Iterator it = this.f8455b.iterator();
        while (true) {
            if (!it.hasNext()) {
                this.f8455b.add(this.f8456c);
                break;
            } else if (str.equals(((ShaderItemData) it.next()).f8462c)) {
                break;
            }
        }
        SharedPreferences.Editor edit = this.f8454a.edit();
        edit.putString("plugin_gameshader_prefer", g());
        edit.apply();
        GaLog.e("GameShaderMgr", "saveData app= data=" + g());
        o(this.f8456c);
    }

    public void q() {
        this.f8457d = false;
        SharedPreferences.Editor edit = this.f8454a.edit();
        edit.putBoolean("plugin_gameshader_first_time_launch", false);
        edit.apply();
    }
}
