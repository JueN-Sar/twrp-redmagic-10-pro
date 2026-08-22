package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo
/* loaded from: classes.dex */
final class GradientColorInflaterCompat {
    private static ColorStops a(ColorStops colorStops, int i2, int i3, boolean z, int i4) {
        return colorStops != null ? colorStops : z ? new ColorStops(i2, i4, i3) : new ColorStops(i2, i3);
    }

    static Shader b(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        String name = xmlPullParser.getName();
        if (!name.equals("gradient")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
        TypedArray s2 = TypedArrayUtils.s(resources, theme, attributeSet, R.styleable.GradientColor);
        float j2 = TypedArrayUtils.j(s2, xmlPullParser, "startX", R.styleable.GradientColor_android_startX, 0.0f);
        float j3 = TypedArrayUtils.j(s2, xmlPullParser, "startY", R.styleable.GradientColor_android_startY, 0.0f);
        float j4 = TypedArrayUtils.j(s2, xmlPullParser, "endX", R.styleable.GradientColor_android_endX, 0.0f);
        float j5 = TypedArrayUtils.j(s2, xmlPullParser, "endY", R.styleable.GradientColor_android_endY, 0.0f);
        float j6 = TypedArrayUtils.j(s2, xmlPullParser, "centerX", R.styleable.GradientColor_android_centerX, 0.0f);
        float j7 = TypedArrayUtils.j(s2, xmlPullParser, "centerY", R.styleable.GradientColor_android_centerY, 0.0f);
        int k2 = TypedArrayUtils.k(s2, xmlPullParser, "type", R.styleable.GradientColor_android_type, 0);
        int f2 = TypedArrayUtils.f(s2, xmlPullParser, "startColor", R.styleable.GradientColor_android_startColor, 0);
        boolean r2 = TypedArrayUtils.r(xmlPullParser, "centerColor");
        int f3 = TypedArrayUtils.f(s2, xmlPullParser, "centerColor", R.styleable.GradientColor_android_centerColor, 0);
        int f4 = TypedArrayUtils.f(s2, xmlPullParser, "endColor", R.styleable.GradientColor_android_endColor, 0);
        int k3 = TypedArrayUtils.k(s2, xmlPullParser, "tileMode", R.styleable.GradientColor_android_tileMode, 0);
        float j8 = TypedArrayUtils.j(s2, xmlPullParser, "gradientRadius", R.styleable.GradientColor_android_gradientRadius, 0.0f);
        s2.recycle();
        ColorStops a2 = a(c(resources, xmlPullParser, attributeSet, theme), f2, f4, r2, f3);
        if (k2 != 1) {
            return k2 != 2 ? new LinearGradient(j2, j3, j4, j5, a2.f2888a, a2.f2889b, d(k3)) : new SweepGradient(j6, j7, a2.f2888a, a2.f2889b);
        }
        if (j8 > 0.0f) {
            return new RadialGradient(j6, j7, j8, a2.f2888a, a2.f2889b, d(k3));
        }
        throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0084, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException(r9.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.core.content.res.GradientColorInflaterCompat.ColorStops c(android.content.res.Resources r8, org.xmlpull.v1.XmlPullParser r9, android.util.AttributeSet r10, android.content.res.Resources.Theme r11) {
        /*
            int r0 = r9.getDepth()
            r1 = 1
            int r0 = r0 + r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 20
            r2.<init>(r3)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
        L12:
            int r3 = r9.next()
            if (r3 == r1) goto L85
            int r5 = r9.getDepth()
            if (r5 >= r0) goto L21
            r6 = 3
            if (r3 == r6) goto L85
        L21:
            r6 = 2
            if (r3 == r6) goto L25
            goto L12
        L25:
            if (r5 > r0) goto L12
            java.lang.String r3 = r9.getName()
            java.lang.String r5 = "item"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L34
            goto L12
        L34:
            int[] r3 = androidx.core.R.styleable.GradientColorItem
            android.content.res.TypedArray r3 = androidx.core.content.res.TypedArrayUtils.s(r8, r11, r10, r3)
            int r5 = androidx.core.R.styleable.GradientColorItem_android_color
            boolean r5 = r3.hasValue(r5)
            int r6 = androidx.core.R.styleable.GradientColorItem_android_offset
            boolean r6 = r3.hasValue(r6)
            if (r5 == 0) goto L6a
            if (r6 == 0) goto L6a
            int r5 = androidx.core.R.styleable.GradientColorItem_android_color
            r6 = 0
            int r5 = r3.getColor(r5, r6)
            int r6 = androidx.core.R.styleable.GradientColorItem_android_offset
            r7 = 0
            float r6 = r3.getFloat(r6, r7)
            r3.recycle()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r4.add(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r6)
            r2.add(r3)
            goto L12
        L6a:
            org.xmlpull.v1.XmlPullParserException r8 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r9 = r9.getPositionDescription()
            r10.append(r9)
            java.lang.String r9 = ": <item> tag requires a 'color' attribute and a 'offset' attribute!"
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            throw r8
        L85:
            int r8 = r4.size()
            if (r8 <= 0) goto L91
            androidx.core.content.res.GradientColorInflaterCompat$ColorStops r8 = new androidx.core.content.res.GradientColorInflaterCompat$ColorStops
            r8.<init>(r4, r2)
            return r8
        L91:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.GradientColorInflaterCompat.c(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):androidx.core.content.res.GradientColorInflaterCompat$ColorStops");
    }

    private static Shader.TileMode d(int i2) {
        return i2 != 1 ? i2 != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR : Shader.TileMode.REPEAT;
    }

    static final class ColorStops {

        /* renamed from: a, reason: collision with root package name */
        final int[] f2888a;

        /* renamed from: b, reason: collision with root package name */
        final float[] f2889b;

        ColorStops(List list, List list2) {
            int size = list.size();
            this.f2888a = new int[size];
            this.f2889b = new float[size];
            for (int i2 = 0; i2 < size; i2++) {
                this.f2888a[i2] = ((Integer) list.get(i2)).intValue();
                this.f2889b[i2] = ((Float) list2.get(i2)).floatValue();
            }
        }

        ColorStops(int i2, int i3) {
            this.f2888a = new int[]{i2, i3};
            this.f2889b = new float[]{0.0f, 1.0f};
        }

        ColorStops(int i2, int i3, int i4) {
            this.f2888a = new int[]{i2, i3, i4};
            this.f2889b = new float[]{0.0f, 0.5f, 1.0f};
        }
    }
}
