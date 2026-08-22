package cn.nubia.multisubscreen.data;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class BatchData extends AbsData {
    private HashMap<String, String> mMap = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$toString$0(StringBuilder sb, Map.Entry entry) {
        sb.append(((String) entry.getKey()) + ":" + ((String) entry.getValue()) + ";");
    }

    @Override // cn.nubia.multisubscreen.data.AbsData
    public String get(String str) {
        return this.mMap.get(str);
    }

    public List<String> getAllKeys() {
        return null;
    }

    @Override // cn.nubia.multisubscreen.data.AbsData
    public String[] getKeys() {
        if (this.mMap.size() == 0) {
            return null;
        }
        String[] strArr = new String[this.mMap.size()];
        this.mMap.keySet().toArray(strArr);
        return strArr;
    }

    @Override // cn.nubia.multisubscreen.data.AbsData
    public boolean isValid() {
        return this.mMap.size() > 0;
    }

    @Override // cn.nubia.multisubscreen.data.AbsData
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

    @Override // cn.nubia.multisubscreen.data.AbsData
    public void put(String str, String str2) {
        this.mMap.put(str, str2);
    }

    public void remove(String str) {
        this.mMap.remove(str);
    }

    @Override // cn.nubia.multisubscreen.data.AbsData
    public void reset() {
        this.mMap.clear();
    }

    @Override // cn.nubia.multisubscreen.data.AbsData
    public int size() {
        return this.mMap.size();
    }

    @NonNull
    public String toString() {
        if (!isValid()) {
            return super.toString();
        }
        final StringBuilder sb = new StringBuilder();
        this.mMap.entrySet().forEach(new Consumer() { // from class: cn.nubia.multisubscreen.data.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BatchData.lambda$toString$0(sb, (Map.Entry) obj);
            }
        });
        return sb.toString();
    }
}
