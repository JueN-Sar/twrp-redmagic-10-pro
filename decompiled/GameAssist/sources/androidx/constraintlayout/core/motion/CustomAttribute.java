package androidx.constraintlayout.core.motion;

/* loaded from: classes.dex */
public class CustomAttribute {

    /* renamed from: a, reason: collision with root package name */
    private AttributeType f1673a;

    /* renamed from: b, reason: collision with root package name */
    private int f1674b;

    /* renamed from: c, reason: collision with root package name */
    private float f1675c;

    /* renamed from: d, reason: collision with root package name */
    boolean f1676d;

    /* renamed from: e, reason: collision with root package name */
    private int f1677e;

    public enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE,
        REFERENCE_TYPE
    }

    public void a(float[] fArr) {
        switch (this.f1673a) {
            case INT_TYPE:
                fArr[0] = this.f1674b;
                return;
            case FLOAT_TYPE:
                fArr[0] = this.f1675c;
                return;
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                int i2 = (this.f1677e >> 24) & 255;
                float pow = (float) Math.pow(((r9 >> 16) & 255) / 255.0f, 2.2d);
                float pow2 = (float) Math.pow(((r9 >> 8) & 255) / 255.0f, 2.2d);
                float pow3 = (float) Math.pow((r9 & 255) / 255.0f, 2.2d);
                fArr[0] = pow;
                fArr[1] = pow2;
                fArr[2] = pow3;
                fArr[3] = i2 / 255.0f;
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case BOOLEAN_TYPE:
                fArr[0] = this.f1676d ? 1.0f : 0.0f;
                return;
            case DIMENSION_TYPE:
                fArr[0] = this.f1675c;
                return;
            default:
                return;
        }
    }

    public int b() {
        int ordinal = this.f1673a.ordinal();
        return (ordinal == 2 || ordinal == 3) ? 4 : 1;
    }
}
