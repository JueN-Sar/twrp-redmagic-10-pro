package com.zte.gameassist.aiagent.bean;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ListAction {

    /* renamed from: a, reason: collision with root package name */
    private String f16405a;

    /* renamed from: b, reason: collision with root package name */
    private String f16406b;

    /* renamed from: c, reason: collision with root package name */
    private String f16407c;

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.f16405a)) {
                jSONObject.put("action_id", this.f16405a);
            }
            if (!TextUtils.isEmpty(this.f16406b)) {
                jSONObject.put("title", this.f16406b);
            }
            if (!TextUtils.isEmpty(this.f16407c)) {
                jSONObject.put("content", this.f16407c);
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
