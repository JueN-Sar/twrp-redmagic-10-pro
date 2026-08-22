package androidx.transition;

import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* loaded from: classes.dex */
class PathProperty<T> extends Property<T, Float> {

    /* renamed from: a, reason: collision with root package name */
    private final Property f5494a;

    /* renamed from: b, reason: collision with root package name */
    private final PathMeasure f5495b;

    /* renamed from: c, reason: collision with root package name */
    private final float f5496c;

    /* renamed from: d, reason: collision with root package name */
    private final float[] f5497d;

    /* renamed from: e, reason: collision with root package name */
    private final PointF f5498e;

    /* renamed from: f, reason: collision with root package name */
    private float f5499f;

    @Override // android.util.Property
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Float get(Object obj) {
        return Float.valueOf(this.f5499f);
    }

    @Override // android.util.Property
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void set(Object obj, Float f2) {
        this.f5499f = f2.floatValue();
        this.f5495b.getPosTan(this.f5496c * f2.floatValue(), this.f5497d, null);
        PointF pointF = this.f5498e;
        float[] fArr = this.f5497d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f5494a.set(obj, pointF);
    }
}
