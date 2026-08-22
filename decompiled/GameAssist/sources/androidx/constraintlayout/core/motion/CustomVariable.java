package androidx.constraintlayout.core.motion;

/* loaded from: classes.dex */
public class CustomVariable {

    /* renamed from: a, reason: collision with root package name */
    String f1678a;

    /* renamed from: b, reason: collision with root package name */
    private int f1679b;

    /* renamed from: c, reason: collision with root package name */
    private int f1680c;

    /* renamed from: d, reason: collision with root package name */
    private float f1681d;

    /* renamed from: e, reason: collision with root package name */
    private String f1682e;

    /* renamed from: f, reason: collision with root package name */
    boolean f1683f;

    public static String a(int i2) {
        return "#" + ("00000000" + Integer.toHexString(i2)).substring(r2.length() - 8);
    }

    public void b(float[] fArr) {
        switch (this.f1679b) {
            case 900:
                fArr[0] = this.f1680c;
                return;
            case 901:
                fArr[0] = this.f1681d;
                return;
            case 902:
                int i2 = (this.f1680c >> 24) & 255;
                float pow = (float) Math.pow(((r9 >> 16) & 255) / 255.0f, 2.2d);
                float pow2 = (float) Math.pow(((r9 >> 8) & 255) / 255.0f, 2.2d);
                float pow3 = (float) Math.pow((r9 & 255) / 255.0f, 2.2d);
                fArr[0] = pow;
                fArr[1] = pow2;
                fArr[2] = pow3;
                fArr[3] = i2 / 255.0f;
                return;
            case 903:
                throw new RuntimeException("Cannot interpolate String");
            case 904:
                fArr[0] = this.f1683f ? 1.0f : 0.0f;
                return;
            case 905:
                fArr[0] = this.f1681d;
                return;
            default:
                return;
        }
    }

    public int c() {
        return this.f1679b != 902 ? 1 : 4;
    }

    public String toString() {
        String str = this.f1678a + ':';
        switch (this.f1679b) {
            case 900:
                return str + this.f1680c;
            case 901:
                return str + this.f1681d;
            case 902:
                return str + a(this.f1680c);
            case 903:
                return str + this.f1682e;
            case 904:
                return str + Boolean.valueOf(this.f1683f);
            case 905:
                return str + this.f1681d;
            default:
                return str + "????";
        }
    }
}
