package cn.nubia.plugin.gameshader;

import com.zte.distbus.basetransfer.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ShaderItemData {

    /* renamed from: a, reason: collision with root package name */
    public boolean f8460a = false;

    /* renamed from: b, reason: collision with root package name */
    public boolean f8461b = false;

    /* renamed from: c, reason: collision with root package name */
    public String f8462c = "";

    /* renamed from: d, reason: collision with root package name */
    public int f8463d = 0;

    /* renamed from: e, reason: collision with root package name */
    public int f8464e = 300;

    /* renamed from: f, reason: collision with root package name */
    public int f8465f = 300;

    /* renamed from: g, reason: collision with root package name */
    public boolean f8466g = false;

    public JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constants.EXTRA_ENABLE, this.f8460a);
            jSONObject.put("switch", this.f8461b);
            jSONObject.put("package", this.f8462c);
            jSONObject.put("type", this.f8463d);
            jSONObject.put("pointx", this.f8464e);
            jSONObject.put("pointy", this.f8465f);
            jSONObject.put("dynamic", this.f8466g);
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void b(JSONObject jSONObject) {
        try {
            this.f8460a = jSONObject.getBoolean(Constants.EXTRA_ENABLE);
            this.f8461b = jSONObject.getBoolean("switch");
            this.f8462c = jSONObject.getString("package");
            this.f8463d = jSONObject.getInt("type");
            this.f8464e = jSONObject.getInt("pointx");
            this.f8465f = jSONObject.getInt("pointy");
            this.f8466g = jSONObject.getBoolean("dynamic");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "[enable=" + this.f8460a + ",switch=" + this.f8461b + ",package=" + this.f8462c + ",type=" + ShaderUtils.c(this.f8463d) + ",pointx=" + this.f8464e + ",pointy=" + this.f8465f + ']';
    }
}
