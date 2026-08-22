package androidx.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class PatternPathMotion extends PathMotion {

    /* renamed from: a, reason: collision with root package name */
    private Path f5500a;

    /* renamed from: b, reason: collision with root package name */
    private final Path f5501b = new Path();

    /* renamed from: c, reason: collision with root package name */
    private final Matrix f5502c = new Matrix();

    public PatternPathMotion(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.f5518k);
        try {
            String m2 = TypedArrayUtils.m(obtainStyledAttributes, (XmlPullParser) attributeSet, "patternPathData", 0);
            if (m2 == null) {
                throw new RuntimeException("pathData must be supplied for patternPathMotion");
            }
            c(PathParser.e(m2));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private static float b(float f2, float f3) {
        return (float) Math.sqrt((f2 * f2) + (f3 * f3));
    }

    @Override // androidx.transition.PathMotion
    public Path a(float f2, float f3, float f4, float f5) {
        float f6 = f4 - f2;
        float f7 = f5 - f3;
        float b2 = b(f6, f7);
        double atan2 = Math.atan2(f7, f6);
        this.f5502c.setScale(b2, b2);
        this.f5502c.postRotate((float) Math.toDegrees(atan2));
        this.f5502c.postTranslate(f2, f3);
        Path path = new Path();
        this.f5501b.transform(this.f5502c, path);
        return path;
    }

    public void c(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float[] fArr = new float[2];
        pathMeasure.getPosTan(pathMeasure.getLength(), fArr, null);
        float f2 = fArr[0];
        float f3 = fArr[1];
        pathMeasure.getPosTan(0.0f, fArr, null);
        float f4 = fArr[0];
        float f5 = fArr[1];
        if (f4 == f2 && f5 == f3) {
            throw new IllegalArgumentException("pattern must not end at the starting point");
        }
        this.f5502c.setTranslate(-f4, -f5);
        float f6 = f2 - f4;
        float f7 = f3 - f5;
        float b2 = 1.0f / b(f6, f7);
        this.f5502c.postScale(b2, b2);
        this.f5502c.postRotate((float) Math.toDegrees(-Math.atan2(f7, f6)));
        path.transform(this.f5502c, this.f5501b);
        this.f5500a = path;
    }

    public PatternPathMotion(Path path) {
        c(path);
    }
}
