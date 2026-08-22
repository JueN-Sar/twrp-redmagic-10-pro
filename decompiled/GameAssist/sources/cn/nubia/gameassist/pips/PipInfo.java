package cn.nubia.gameassist.pips;

import cn.nubia.gameassist.view.NubiaTextClock;

/* loaded from: classes.dex */
public class PipInfo {

    /* renamed from: a, reason: collision with root package name */
    public String f7154a;

    /* renamed from: b, reason: collision with root package name */
    public String f7155b;

    public PipInfo(String str, String str2) {
        this.f7154a = str;
        this.f7155b = str2;
    }

    public boolean a() {
        return this.f7154a == null || this.f7155b == null;
    }

    public String toString() {
        return "PipInfo{label='" + this.f7154a + NubiaTextClock.QUOTE + ", pkg='" + this.f7155b + NubiaTextClock.QUOTE + '}';
    }
}
