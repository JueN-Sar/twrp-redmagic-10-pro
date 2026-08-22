package com.zte.gameassist.aiagent.bean;

import cn.nubia.gameassist.view.NubiaTextClock;
import com.zte.gameassist.aiagent.policy.CommandParser;

/* loaded from: classes2.dex */
public class NLP {

    /* renamed from: a, reason: collision with root package name */
    private final String f16408a;

    /* renamed from: b, reason: collision with root package name */
    private final String f16409b;

    /* renamed from: c, reason: collision with root package name */
    private final NLP_Results f16410c;

    public NLP(String str, String str2, String str3) {
        this.f16408a = str;
        this.f16409b = str2;
        this.f16410c = CommandParser.b(str3);
    }

    public String a() {
        NLP_Results nLP_Results = this.f16410c;
        if (nLP_Results != null) {
            return nLP_Results.a();
        }
        return null;
    }

    public String b() {
        NLP_Results nLP_Results = this.f16410c;
        if (nLP_Results != null) {
            return nLP_Results.b();
        }
        return null;
    }

    public String c() {
        NLP_Results nLP_Results = this.f16410c;
        if (nLP_Results != null) {
            return nLP_Results.c();
        }
        return null;
    }

    public String d() {
        return this.f16409b;
    }

    public String toString() {
        return "{raw_text='" + this.f16408a + NubiaTextClock.QUOTE + ", status='" + this.f16409b + NubiaTextClock.QUOTE + ", results=[" + this.f16410c + "]}";
    }
}
