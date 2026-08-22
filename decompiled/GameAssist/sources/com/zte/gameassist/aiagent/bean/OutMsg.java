package com.zte.gameassist.aiagent.bean;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class OutMsg {

    /* renamed from: a, reason: collision with root package name */
    private String f16415a;

    /* renamed from: b, reason: collision with root package name */
    private int f16416b;

    /* renamed from: c, reason: collision with root package name */
    private String f16417c;

    /* renamed from: d, reason: collision with root package name */
    private String f16418d;

    /* renamed from: e, reason: collision with root package name */
    private String f16419e;

    /* renamed from: f, reason: collision with root package name */
    private List f16420f;

    /* renamed from: g, reason: collision with root package name */
    private List f16421g;

    /* renamed from: h, reason: collision with root package name */
    private int f16422h;

    /* renamed from: i, reason: collision with root package name */
    private String f16423i;

    /* renamed from: j, reason: collision with root package name */
    private int f16424j;

    public static class EmptyMsg extends OutMsg {
    }

    public static class UnconfirmedMsg extends OutMsg {
        public UnconfirmedMsg(String str, int i2, String str2) {
            super(str, i2, str2, "");
        }

        @Override // com.zte.gameassist.aiagent.bean.OutMsg
        public JSONObject e() {
            JSONObject e2 = super.e();
            try {
                e2.put("confirm_content", 1);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            return e2;
        }

        @Override // com.zte.gameassist.aiagent.bean.OutMsg
        public String toString() {
            return e().toString();
        }
    }

    public OutMsg(String str) {
        this(0, str, "");
    }

    public void a(ButtonAction buttonAction) {
        if (this.f16420f == null) {
            this.f16420f = new ArrayList();
        }
        this.f16420f.add(buttonAction);
    }

    public void b(String str) {
        this.f16419e = str;
    }

    public void c(String str) {
        this.f16415a = str;
    }

    public void d(String str) {
        this.f16423i = str;
    }

    public JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ID", this.f16415a);
            jSONObject.put("output_type", this.f16416b);
            jSONObject.put("output", this.f16417c);
            jSONObject.put("scene_id", this.f16418d);
            jSONObject.put("wait_asr", this.f16422h);
            if (!TextUtils.isEmpty(this.f16423i)) {
                jSONObject.put("name", this.f16423i);
            }
            if (!TextUtils.isEmpty(this.f16419e)) {
                jSONObject.put("history", this.f16419e);
            }
            List list = this.f16420f;
            if (list != null && list.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                try {
                    Iterator it = this.f16420f.iterator();
                    while (it.hasNext()) {
                        jSONArray.put(new JSONObject(((ButtonAction) it.next()).toString()));
                    }
                    jSONObject.put("button_action", jSONArray);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            List list2 = this.f16421g;
            if (list2 != null && list2.size() > 0) {
                JSONArray jSONArray2 = new JSONArray();
                try {
                    Iterator it2 = this.f16421g.iterator();
                    while (it2.hasNext()) {
                        jSONArray2.put(new JSONObject(((ListAction) it2.next()).toString()));
                    }
                    jSONObject.put("list_action", jSONArray2);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
            int i2 = this.f16424j;
            if (i2 != 0) {
                jSONObject.put("show_notification", i2);
            }
        } catch (JSONException e4) {
            e4.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return e().toString();
    }

    public OutMsg(int i2, String str, String str2) {
        this.f16415a = "";
        this.f16422h = 1;
        this.f16424j = 0;
        this.f16417c = str;
        this.f16418d = str2;
        this.f16416b = i2;
    }

    public OutMsg(String str, int i2, String str2, String str3) {
        this.f16422h = 1;
        this.f16424j = 0;
        this.f16415a = str;
        this.f16416b = i2;
        this.f16417c = str2;
        this.f16418d = str3;
    }
}
