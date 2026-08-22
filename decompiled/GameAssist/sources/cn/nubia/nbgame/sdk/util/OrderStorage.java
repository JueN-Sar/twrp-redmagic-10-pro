package cn.nubia.nbgame.sdk.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class OrderStorage {

    /* renamed from: b, reason: collision with root package name */
    private static volatile OrderStorage f8321b;

    /* renamed from: a, reason: collision with root package name */
    private SharedPreferences f8322a;

    public OrderStorage(Context context) {
        this.f8322a = context.getSharedPreferences("order", 0);
    }

    private void b(String str) {
        String d2 = d();
        NeoLog.g("OrderStorage", "deleteOrder sp json: " + d2);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(d2);
            int i2 = 0;
            while (true) {
                if (i2 >= jSONArray.length()) {
                    break;
                }
                if (str.equals(jSONArray.optJSONObject(i2).optString("cp_order_id"))) {
                    jSONArray.remove(i2);
                    break;
                }
                i2++;
            }
            f(jSONArray.toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static OrderStorage c(Context context) {
        if (f8321b == null) {
            synchronized (OrderStorage.class) {
                try {
                    if (f8321b == null) {
                        f8321b = new OrderStorage(context.getApplicationContext());
                    }
                } finally {
                }
            }
        }
        return f8321b;
    }

    private String d() {
        return this.f8322a.getString("orderList", "");
    }

    private void f(String str) {
        NeoLog.g("OrderStorage", "order list json saved: " + str);
        SharedPreferences.Editor edit = this.f8322a.edit();
        edit.putString("orderList", str);
        edit.apply();
    }

    public synchronized void a(String str) {
        b(str);
    }

    public void e(HashMap hashMap) {
        String d2 = d();
        NeoLog.g("OrderStorage", "saveOrder sp json: " + d2);
        if (hashMap != null) {
            hashMap.put("order_status", 1);
            try {
                JSONArray jSONArray = TextUtils.isEmpty(d2) ? new JSONArray() : new JSONArray(d2);
                jSONArray.put(new JSONObject(hashMap));
                f(jSONArray.toString());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }
}
