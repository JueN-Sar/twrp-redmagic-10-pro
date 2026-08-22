package com.zte.gameassist.ext.netspeedup;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public final class NetSpeedupConfig {

    /* renamed from: a, reason: collision with root package name */
    public final String f16667a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f16668b = false;

    /* renamed from: c, reason: collision with root package name */
    public String f16669c = "leigod";

    public NetSpeedupConfig(String str) {
        this.f16667a = str;
    }

    public static String a(String str) {
        return (!TextUtils.isEmpty(str) && "subao".equals(str.trim().toLowerCase())) ? "subao" : "leigod";
    }

    public static NetSpeedupConfig c(String str) {
        return new NetSpeedupConfig(str);
    }

    public NetSpeedupConfig b() {
        NetSpeedupConfig netSpeedupConfig = new NetSpeedupConfig(this.f16667a);
        netSpeedupConfig.f16668b = this.f16668b;
        netSpeedupConfig.f16669c = a(this.f16669c);
        return netSpeedupConfig;
    }
}
