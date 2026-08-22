package androidx.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.core.content.res.TypedArrayUtils;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class ArcMotion extends PathMotion {

    /* renamed from: g, reason: collision with root package name */
    private static final float f5409g = (float) Math.tan(Math.toRadians(35.0d));

    /* renamed from: a, reason: collision with root package name */
    private float f5410a;

    /* renamed from: b, reason: collision with root package name */
    private float f5411b;

    /* renamed from: c, reason: collision with root package name */
    private float f5412c;

    /* renamed from: d, reason: collision with root package name */
    private float f5413d;

    /* renamed from: e, reason: collision with root package name */
    private float f5414e;

    /* renamed from: f, reason: collision with root package name */
    private float f5415f;

    public ArcMotion(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5410a = 0.0f;
        this.f5411b = 0.0f;
        this.f5412c = 70.0f;
        this.f5413d = 0.0f;
        this.f5414e = 0.0f;
        this.f5415f = f5409g;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.f5517j);
        XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
        d(TypedArrayUtils.j(obtainStyledAttributes, xmlPullParser, "minimumVerticalAngle", 1, 0.0f));
        c(TypedArrayUtils.j(obtainStyledAttributes, xmlPullParser, "minimumHorizontalAngle", 0, 0.0f));
        b(TypedArrayUtils.j(obtainStyledAttributes, xmlPullParser, "maximumAngle", 2, 70.0f));
        obtainStyledAttributes.recycle();
    }

    private static float e(float f2) {
        if (f2 < 0.0f || f2 > 90.0f) {
            throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
        }
        return (float) Math.tan(Math.toRadians(f2 / 2.0f));
    }

    @Override // androidx.transition.PathMotion
    public Path a(float f2, float f3, float f4, float f5) {
        float f6;
        float f7;
        float f8;
        Path path = new Path();
        path.moveTo(f2, f3);
        float f9 = f4 - f2;
        float f10 = f5 - f3;
        float f11 = (f9 * f9) + (f10 * f10);
        float f12 = (f2 + f4) / 2.0f;
        float f13 = (f3 + f5) / 2.0f;
        float f14 = 0.25f * f11;
        boolean z = f3 > f5;
        if (Math.abs(f9) < Math.abs(f10)) {
            float abs = Math.abs(f11 / (f10 * 2.0f));
            if (z) {
                f7 = abs + f5;
                f6 = f4;
            } else {
                f7 = abs + f3;
                f6 = f2;
            }
            f8 = this.f5414e;
        } else {
            float f15 = f11 / (f9 * 2.0f);
            if (z) {
                f7 = f3;
                f6 = f15 + f2;
            } else {
                f6 = f4 - f15;
                f7 = f5;
            }
            f8 = this.f5413d;
        }
        float f16 = f14 * f8 * f8;
        float f17 = f12 - f6;
        float f18 = f13 - f7;
        float f19 = (f17 * f17) + (f18 * f18);
        float f20 = this.f5415f;
        float f21 = f14 * f20 * f20;
        if (f19 >= f16) {
            f16 = f19 > f21 ? f21 : 0.0f;
        }
        if (f16 != 0.0f) {
            float sqrt = (float) Math.sqrt(f16 / f19);
            f6 = ((f6 - f12) * sqrt) + f12;
            f7 = f13 + (sqrt * (f7 - f13));
        }
        path.cubicTo((f2 + f6) / 2.0f, (f3 + f7) / 2.0f, (f6 + f4) / 2.0f, (f7 + f5) / 2.0f, f4, f5);
        return path;
    }

    public void b(float f2) {
        this.f5412c = f2;
        this.f5415f = e(f2);
    }

    public void c(float f2) {
        this.f5410a = f2;
        this.f5413d = e(f2);
    }

    public void d(float f2) {
        this.f5411b = f2;
        this.f5414e = e(f2);
    }
}
