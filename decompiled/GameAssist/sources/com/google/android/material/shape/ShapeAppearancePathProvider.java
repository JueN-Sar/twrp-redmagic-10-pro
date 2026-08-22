package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.annotation.RestrictTo;

/* loaded from: classes.dex */
public class ShapeAppearancePathProvider {

    /* renamed from: a, reason: collision with root package name */
    private final ShapePath[] f15158a = new ShapePath[4];

    /* renamed from: b, reason: collision with root package name */
    private final Matrix[] f15159b = new Matrix[4];

    /* renamed from: c, reason: collision with root package name */
    private final Matrix[] f15160c = new Matrix[4];

    /* renamed from: d, reason: collision with root package name */
    private final PointF f15161d = new PointF();

    /* renamed from: e, reason: collision with root package name */
    private final Path f15162e = new Path();

    /* renamed from: f, reason: collision with root package name */
    private final Path f15163f = new Path();

    /* renamed from: g, reason: collision with root package name */
    private final ShapePath f15164g = new ShapePath();

    /* renamed from: h, reason: collision with root package name */
    private final float[] f15165h = new float[2];

    /* renamed from: i, reason: collision with root package name */
    private final float[] f15166i = new float[2];

    /* renamed from: j, reason: collision with root package name */
    private final Path f15167j = new Path();

    /* renamed from: k, reason: collision with root package name */
    private final Path f15168k = new Path();

    /* renamed from: l, reason: collision with root package name */
    private boolean f15169l = true;

    private static class Lazy {

        /* renamed from: a, reason: collision with root package name */
        static final ShapeAppearancePathProvider f15170a = new ShapeAppearancePathProvider();
    }

    @RestrictTo
    public interface PathListener {
        void a(ShapePath shapePath, Matrix matrix, int i2);

        void b(ShapePath shapePath, Matrix matrix, int i2);
    }

    static final class ShapeAppearancePathSpec {

        /* renamed from: a, reason: collision with root package name */
        public final ShapeAppearanceModel f15171a;

        /* renamed from: b, reason: collision with root package name */
        public final Path f15172b;

        /* renamed from: c, reason: collision with root package name */
        public final RectF f15173c;

        /* renamed from: d, reason: collision with root package name */
        public final PathListener f15174d;

        /* renamed from: e, reason: collision with root package name */
        public final float f15175e;

        ShapeAppearancePathSpec(ShapeAppearanceModel shapeAppearanceModel, float f2, RectF rectF, PathListener pathListener, Path path) {
            this.f15174d = pathListener;
            this.f15171a = shapeAppearanceModel;
            this.f15175e = f2;
            this.f15173c = rectF;
            this.f15172b = path;
        }
    }

    public ShapeAppearancePathProvider() {
        for (int i2 = 0; i2 < 4; i2++) {
            this.f15158a[i2] = new ShapePath();
            this.f15159b[i2] = new Matrix();
            this.f15160c[i2] = new Matrix();
        }
    }

    private float a(int i2) {
        return ((i2 + 1) % 4) * 90;
    }

    private void b(ShapeAppearancePathSpec shapeAppearancePathSpec, int i2) {
        this.f15165h[0] = this.f15158a[i2].k();
        this.f15165h[1] = this.f15158a[i2].l();
        this.f15159b[i2].mapPoints(this.f15165h);
        if (i2 == 0) {
            Path path = shapeAppearancePathSpec.f15172b;
            float[] fArr = this.f15165h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = shapeAppearancePathSpec.f15172b;
            float[] fArr2 = this.f15165h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f15158a[i2].d(this.f15159b[i2], shapeAppearancePathSpec.f15172b);
        PathListener pathListener = shapeAppearancePathSpec.f15174d;
        if (pathListener != null) {
            pathListener.a(this.f15158a[i2], this.f15159b[i2], i2);
        }
    }

    private void c(ShapeAppearancePathSpec shapeAppearancePathSpec, int i2) {
        int i3 = (i2 + 1) % 4;
        this.f15165h[0] = this.f15158a[i2].i();
        this.f15165h[1] = this.f15158a[i2].j();
        this.f15159b[i2].mapPoints(this.f15165h);
        this.f15166i[0] = this.f15158a[i3].k();
        this.f15166i[1] = this.f15158a[i3].l();
        this.f15159b[i3].mapPoints(this.f15166i);
        float f2 = this.f15165h[0];
        float[] fArr = this.f15166i;
        float max = Math.max(((float) Math.hypot(f2 - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float i4 = i(shapeAppearancePathSpec.f15173c, i2);
        this.f15164g.o(0.0f, 0.0f);
        EdgeTreatment j2 = j(i2, shapeAppearancePathSpec.f15171a);
        j2.b(max, i4, shapeAppearancePathSpec.f15175e, this.f15164g);
        this.f15167j.reset();
        this.f15164g.d(this.f15160c[i2], this.f15167j);
        if (this.f15169l && (j2.a() || l(this.f15167j, i2) || l(this.f15167j, i3))) {
            Path path = this.f15167j;
            path.op(path, this.f15163f, Path.Op.DIFFERENCE);
            this.f15165h[0] = this.f15164g.k();
            this.f15165h[1] = this.f15164g.l();
            this.f15160c[i2].mapPoints(this.f15165h);
            Path path2 = this.f15162e;
            float[] fArr2 = this.f15165h;
            path2.moveTo(fArr2[0], fArr2[1]);
            this.f15164g.d(this.f15160c[i2], this.f15162e);
        } else {
            this.f15164g.d(this.f15160c[i2], shapeAppearancePathSpec.f15172b);
        }
        PathListener pathListener = shapeAppearancePathSpec.f15174d;
        if (pathListener != null) {
            pathListener.b(this.f15164g, this.f15160c[i2], i2);
        }
    }

    private void f(int i2, RectF rectF, PointF pointF) {
        if (i2 == 1) {
            pointF.set(rectF.right, rectF.bottom);
            return;
        }
        if (i2 == 2) {
            pointF.set(rectF.left, rectF.bottom);
        } else if (i2 != 3) {
            pointF.set(rectF.right, rectF.top);
        } else {
            pointF.set(rectF.left, rectF.top);
        }
    }

    private CornerSize g(int i2, ShapeAppearanceModel shapeAppearanceModel) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? shapeAppearanceModel.t() : shapeAppearanceModel.r() : shapeAppearanceModel.j() : shapeAppearanceModel.l();
    }

    private CornerTreatment h(int i2, ShapeAppearanceModel shapeAppearanceModel) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? shapeAppearanceModel.s() : shapeAppearanceModel.q() : shapeAppearanceModel.i() : shapeAppearanceModel.k();
    }

    private float i(RectF rectF, int i2) {
        float[] fArr = this.f15165h;
        ShapePath shapePath = this.f15158a[i2];
        fArr[0] = shapePath.f15178c;
        fArr[1] = shapePath.f15179d;
        this.f15159b[i2].mapPoints(fArr);
        return (i2 == 1 || i2 == 3) ? Math.abs(rectF.centerX() - this.f15165h[0]) : Math.abs(rectF.centerY() - this.f15165h[1]);
    }

    private EdgeTreatment j(int i2, ShapeAppearanceModel shapeAppearanceModel) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? shapeAppearanceModel.o() : shapeAppearanceModel.p() : shapeAppearanceModel.n() : shapeAppearanceModel.h();
    }

    public static ShapeAppearancePathProvider k() {
        return Lazy.f15170a;
    }

    private boolean l(Path path, int i2) {
        this.f15168k.reset();
        this.f15158a[i2].d(this.f15159b[i2], this.f15168k);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        this.f15168k.computeBounds(rectF, true);
        path.op(this.f15168k, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }

    private void m(ShapeAppearancePathSpec shapeAppearancePathSpec, int i2) {
        h(i2, shapeAppearancePathSpec.f15171a).c(this.f15158a[i2], 90.0f, shapeAppearancePathSpec.f15175e, shapeAppearancePathSpec.f15173c, g(i2, shapeAppearancePathSpec.f15171a));
        float a2 = a(i2);
        this.f15159b[i2].reset();
        f(i2, shapeAppearancePathSpec.f15173c, this.f15161d);
        Matrix matrix = this.f15159b[i2];
        PointF pointF = this.f15161d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f15159b[i2].preRotate(a2);
    }

    private void n(int i2) {
        this.f15165h[0] = this.f15158a[i2].i();
        this.f15165h[1] = this.f15158a[i2].j();
        this.f15159b[i2].mapPoints(this.f15165h);
        float a2 = a(i2);
        this.f15160c[i2].reset();
        Matrix matrix = this.f15160c[i2];
        float[] fArr = this.f15165h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f15160c[i2].preRotate(a2);
    }

    public void d(ShapeAppearanceModel shapeAppearanceModel, float f2, RectF rectF, Path path) {
        e(shapeAppearanceModel, f2, rectF, null, path);
    }

    public void e(ShapeAppearanceModel shapeAppearanceModel, float f2, RectF rectF, PathListener pathListener, Path path) {
        path.rewind();
        this.f15162e.rewind();
        this.f15163f.rewind();
        this.f15163f.addRect(rectF, Path.Direction.CW);
        ShapeAppearancePathSpec shapeAppearancePathSpec = new ShapeAppearancePathSpec(shapeAppearanceModel, f2, rectF, pathListener, path);
        for (int i2 = 0; i2 < 4; i2++) {
            m(shapeAppearancePathSpec, i2);
            n(i2);
        }
        for (int i3 = 0; i3 < 4; i3++) {
            b(shapeAppearancePathSpec, i3);
            c(shapeAppearancePathSpec, i3);
        }
        path.close();
        this.f15162e.close();
        if (this.f15162e.isEmpty()) {
            return;
        }
        path.op(this.f15162e, Path.Op.UNION);
    }
}
