package cn.nubia.plugin.timer;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class TimerItemData {

    /* renamed from: a, reason: collision with root package name */
    public int f8729a = 0;

    /* renamed from: b, reason: collision with root package name */
    public boolean f8730b = true;

    /* renamed from: c, reason: collision with root package name */
    public int f8731c = 0;

    /* renamed from: d, reason: collision with root package name */
    public boolean f8732d = false;

    /* renamed from: e, reason: collision with root package name */
    public int f8733e = 0;

    public JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(VirtualHandleWrapper.KEY_INDEX, this.f8729a);
            jSONObject.put("increase", this.f8730b);
            jSONObject.put("total", this.f8731c);
            jSONObject.put("running", this.f8732d);
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void b(JSONObject jSONObject) {
        try {
            this.f8730b = jSONObject.getBoolean("increase");
            this.f8729a = jSONObject.getInt(VirtualHandleWrapper.KEY_INDEX);
            this.f8731c = jSONObject.getInt("total");
            this.f8732d = jSONObject.getBoolean("running");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "[increase=" + this.f8730b + ",index=" + this.f8729a + ",total=" + this.f8731c + ",running=" + this.f8732d + ",sec=" + this.f8733e + ']';
    }
}
