package cn.nubia.gameassist.power;

import java.util.Objects;

/* loaded from: classes.dex */
public class GlobalData {

    /* renamed from: a, reason: collision with root package name */
    final int f7363a;

    /* renamed from: b, reason: collision with root package name */
    final long f7364b;

    /* renamed from: c, reason: collision with root package name */
    final int f7365c;

    /* renamed from: d, reason: collision with root package name */
    final int f7366d;

    /* renamed from: e, reason: collision with root package name */
    final String f7367e;

    /* renamed from: f, reason: collision with root package name */
    final String f7368f;

    /* renamed from: g, reason: collision with root package name */
    final long f7369g;

    public GlobalData(int i2, long j2, int i3, int i4, String str, String str2, long j3) {
        this.f7363a = i2;
        this.f7364b = j2;
        this.f7365c = i3;
        this.f7366d = i4;
        this.f7367e = str;
        this.f7368f = str2;
        this.f7369g = j3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GlobalData globalData = (GlobalData) obj;
        return this.f7363a == globalData.f7363a && this.f7364b == globalData.f7364b && this.f7365c == globalData.f7365c && this.f7366d == globalData.f7366d && Objects.equals(this.f7367e, globalData.f7367e) && Objects.equals(this.f7368f, globalData.f7368f);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.f7363a), Long.valueOf(this.f7364b), Integer.valueOf(this.f7365c), Integer.valueOf(this.f7366d), this.f7367e, this.f7368f);
    }

    public String toString() {
        return "mWakefulness=" + this.f7363a + "， mEventTime=" + this.f7364b + ", mUid=" + this.f7365c + ", mPackageName=" + this.f7367e + ", mDetails=" + this.f7368f;
    }
}
