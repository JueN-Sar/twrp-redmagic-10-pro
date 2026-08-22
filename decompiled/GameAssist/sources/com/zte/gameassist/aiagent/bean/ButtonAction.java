package com.zte.gameassist.aiagent.bean;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ButtonAction {

    /* renamed from: a, reason: collision with root package name */
    private String f16394a;

    /* renamed from: b, reason: collision with root package name */
    private String f16395b;

    /* renamed from: c, reason: collision with root package name */
    private String f16396c;

    /* renamed from: d, reason: collision with root package name */
    private String f16397d;

    public ButtonAction(String str, String str2) {
        this.f16394a = str;
        this.f16395b = str2;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.f16394a)) {
                jSONObject.put("action_id", this.f16394a);
            }
            if (!TextUtils.isEmpty(this.f16395b)) {
                jSONObject.put("title", this.f16395b);
            }
            if (!TextUtils.isEmpty(this.f16396c)) {
                jSONObject.put("content", this.f16396c);
            }
            if (!TextUtils.isEmpty(this.f16397d)) {
                jSONObject.put("thumbnail", this.f16397d);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return a().toString();
    }
}
