package com.zte.gameassist.aiagent.bean;

import cn.nubia.gameassist.view.NubiaTextClock;

/* loaded from: classes2.dex */
public class NLP_Results {

    /* renamed from: a, reason: collision with root package name */
    private final int f16411a;

    /* renamed from: b, reason: collision with root package name */
    private final String f16412b;

    /* renamed from: c, reason: collision with root package name */
    private final String f16413c;

    /* renamed from: d, reason: collision with root package name */
    private final String f16414d;

    public NLP_Results(int i2, String str, String str2, String str3) {
        this.f16411a = i2;
        this.f16412b = str;
        this.f16413c = str2;
        this.f16414d = str3;
    }

    public String a() {
        return this.f16414d;
    }

    public String b() {
        return this.f16412b;
    }

    public String c() {
        return this.f16413c;
    }

    public String toString() {
        return "{score=" + this.f16411a + ", domain='" + this.f16412b + NubiaTextClock.QUOTE + ", intent='" + this.f16413c + NubiaTextClock.QUOTE + '}';
    }
}
