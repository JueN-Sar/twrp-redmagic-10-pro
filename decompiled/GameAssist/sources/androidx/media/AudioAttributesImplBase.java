package androidx.media;

import java.util.Arrays;

/* loaded from: classes.dex */
class AudioAttributesImplBase implements AudioAttributesImpl {

    /* renamed from: a, reason: collision with root package name */
    int f4499a = 0;

    /* renamed from: b, reason: collision with root package name */
    int f4500b = 0;

    /* renamed from: c, reason: collision with root package name */
    int f4501c = 0;

    /* renamed from: d, reason: collision with root package name */
    int f4502d = -1;

    AudioAttributesImplBase() {
    }

    public int a() {
        return this.f4500b;
    }

    public int b() {
        int i2 = this.f4501c;
        int c2 = c();
        if (c2 == 6) {
            i2 |= 4;
        } else if (c2 == 7) {
            i2 |= 1;
        }
        return i2 & 273;
    }

    public int c() {
        int i2 = this.f4502d;
        return i2 != -1 ? i2 : AudioAttributesCompat.a(false, this.f4501c, this.f4499a);
    }

    public int d() {
        return this.f4499a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        return this.f4500b == audioAttributesImplBase.a() && this.f4501c == audioAttributesImplBase.b() && this.f4499a == audioAttributesImplBase.d() && this.f4502d == audioAttributesImplBase.f4502d;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f4500b), Integer.valueOf(this.f4501c), Integer.valueOf(this.f4499a), Integer.valueOf(this.f4502d)});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.f4502d != -1) {
            sb.append(" stream=");
            sb.append(this.f4502d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.b(this.f4499a));
        sb.append(" content=");
        sb.append(this.f4500b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.f4501c).toUpperCase());
        return sb.toString();
    }
}
