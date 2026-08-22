package com.zte.gameassist.aiagent.bean;

import android.text.TextUtils;
import com.zte.gameassist.aiagent.policy.CommandParser;

/* loaded from: classes2.dex */
public class InMsg {

    /* renamed from: a, reason: collision with root package name */
    private String f16398a;

    /* renamed from: b, reason: collision with root package name */
    private int f16399b;

    /* renamed from: c, reason: collision with root package name */
    private String f16400c;

    /* renamed from: d, reason: collision with root package name */
    private NLP f16401d;

    /* renamed from: e, reason: collision with root package name */
    private String f16402e;

    /* renamed from: f, reason: collision with root package name */
    private String f16403f;

    /* renamed from: g, reason: collision with root package name */
    private InMsg f16404g;

    public InMsg(String str, int i2, String str2, String str3) {
        this.f16398a = str;
        this.f16399b = i2;
        this.f16400c = str2;
        this.f16401d = CommandParser.c(str3);
    }

    public String a() {
        return this.f16403f;
    }

    public String b() {
        String[] i2;
        NLP nlp = this.f16401d;
        return nlp == null ? (this.f16399b != 1 || (i2 = i()) == null || i2.length < 2) ? "" : i2[0] : nlp.b();
    }

    public String c() {
        return this.f16398a;
    }

    public String d() {
        return this.f16400c;
    }

    public String e() {
        String[] i2;
        NLP nlp = this.f16401d;
        return nlp == null ? (this.f16399b != 1 || (i2 = i()) == null || i2.length < 2) ? "" : i2[1] : nlp.c();
    }

    public InMsg f() {
        return this.f16404g;
    }

    public String g() {
        return this.f16402e;
    }

    public NLP h() {
        return this.f16401d;
    }

    public String[] i() {
        String str = this.f16402e;
        if (str == null) {
            return null;
        }
        return str.split("\\|");
    }

    public int j() {
        return this.f16399b;
    }

    public void k(String str) {
        this.f16403f = str;
    }

    public void l(InMsg inMsg) {
        this.f16404g = inMsg;
    }

    public void m(String str) {
        this.f16402e = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("ID");
        sb.append("='");
        sb.append(this.f16398a);
        sb.append("'");
        sb.append(", ");
        sb.append("type");
        sb.append("='");
        sb.append(this.f16399b);
        sb.append("'");
        int i2 = this.f16399b;
        if (i2 == 0) {
            sb.append(", ");
            sb.append("input");
            sb.append("='");
            sb.append(this.f16400c);
            sb.append("'");
            sb.append(", ");
            sb.append("nlp");
            sb.append("=\"");
            sb.append(this.f16401d);
            sb.append("\"");
        } else if (i2 == 1) {
            if (!TextUtils.isEmpty(this.f16402e)) {
                sb.append(", ");
                sb.append("name");
                sb.append("='");
                sb.append(this.f16402e);
                sb.append("'");
            }
            if (!TextUtils.isEmpty(this.f16403f)) {
                sb.append(", ");
                sb.append("action_id");
                sb.append("='");
                sb.append(this.f16403f);
                sb.append("'");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public InMsg(String str, int i2) {
        this.f16398a = str;
        this.f16399b = i2;
    }
}
