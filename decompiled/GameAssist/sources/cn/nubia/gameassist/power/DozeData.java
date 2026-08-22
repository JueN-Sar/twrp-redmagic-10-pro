package cn.nubia.gameassist.power;

import java.util.Objects;

/* loaded from: classes.dex */
public class DozeData {

    /* renamed from: a, reason: collision with root package name */
    final boolean f7359a;

    /* renamed from: b, reason: collision with root package name */
    final int f7360b;

    /* renamed from: c, reason: collision with root package name */
    final int f7361c;

    /* renamed from: d, reason: collision with root package name */
    final long f7362d;

    public DozeData(boolean z, int i2, int i3, long j2) {
        this.f7359a = z;
        this.f7360b = i2;
        this.f7361c = i3;
        this.f7362d = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DozeData dozeData = (DozeData) obj;
        return this.f7359a == dozeData.f7359a && this.f7360b == dozeData.f7360b && this.f7361c == dozeData.f7361c;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.f7359a), Integer.valueOf(this.f7360b), Integer.valueOf(this.f7361c));
    }

    public String toString() {
        return "mISdoze=" + this.f7359a + "， mGroundId=" + this.f7360b + ", mWakefulness=" + this.f7361c;
    }
}
