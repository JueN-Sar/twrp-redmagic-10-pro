package cn.nubia.multisubscreen.data;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class AbsData {
    public abstract String get(String str);

    public JSONObject getJson() {
        JSONObject jSONObject = new JSONObject();
        if (size() == 0) {
            return jSONObject;
        }
        try {
            for (String str : getKeys()) {
                try {
                    String str2 = get(str);
                    if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put(str, str2);
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            return jSONObject;
        } catch (Exception e3) {
            e3.printStackTrace();
            return jSONObject;
        }
    }

    public String getJsonString() {
        return getJson().toString();
    }

    public abstract String[] getKeys();

    public boolean isValid() {
        return true;
    }

    protected boolean isValidKey(String str) {
        return true;
    }

    public JSONObject keysToJson(List<String> list) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            jSONObject.put("keys", jSONArray);
            return jSONObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public abstract void put(String str, String str2);

    public void reset() {
    }

    public abstract int size();

    public void updateFromJson(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (isValidKey(next)) {
                        put(next, jSONObject.getString(next));
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
