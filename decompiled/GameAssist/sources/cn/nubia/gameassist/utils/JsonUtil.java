package cn.nubia.gameassist.utils;

import android.content.Context;
import android.text.TextUtils;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JsonUtil<T> {

    /* renamed from: a, reason: collision with root package name */
    private Context f7674a;

    public JsonUtil(Context context) {
        this.f7674a = context;
    }

    public String a(String str, String str2, Object obj) {
        JSONObject jSONObject;
        String f2 = SharedPreferencesUtil.k(this.f7674a).f(str2, "{json_list : {}}");
        try {
            if (TextUtils.isEmpty(f2)) {
                jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(str, obj);
                jSONObject.put("json_list", jSONObject2);
            } else {
                jSONObject = new JSONObject(f2);
                JSONObject jSONObject3 = (JSONObject) jSONObject.get("json_list");
                jSONObject3.put(str, obj);
                jSONObject.put("json_list", jSONObject3);
            }
            String jSONObject4 = jSONObject.toString();
            GaLog.a("JsonUtil", "formatDataToJson: jsonString = " + jSONObject4 + " , curApp = " + str + " , dataName = " + str2 + " , lastData = " + f2);
            return jSONObject4;
        } catch (Exception e2) {
            GaLog.l("JsonUtil", "formatDataToJson exception!", e2);
            return "";
        }
    }

    public Object b(String str, String str2) {
        Object obj = null;
        try {
            JSONObject jSONObject = (JSONObject) new JSONObject(SharedPreferencesUtil.k(this.f7674a).f(str2, "{json_list : {}}")).get("json_list");
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                if (str.equals(keys.next())) {
                    obj = jSONObject.get(str);
                }
            }
        } catch (Exception e2) {
            GaLog.l("JsonUtil", "parseJsonDuration exception!", e2);
        }
        GaLog.a("JsonUtil", "parseJsonToData: t = " + obj + " , curApp = " + str + " , dataName = " + str2);
        return obj;
    }
}
