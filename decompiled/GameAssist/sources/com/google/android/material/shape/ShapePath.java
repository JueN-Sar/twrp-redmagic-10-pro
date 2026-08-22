package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.google.android.material.shadow.ShadowRenderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ShapePath {

    /* renamed from: a, reason: collision with root package name */
    public float f15176a;

    /* renamed from: b, reason: collision with root package name */
    public float f15177b;

    /* renamed from: c, reason: collision with root package name */
    public float f15178c;

    /* renamed from: d, reason: collision with root package name */
    public float f15179d;

    /* renamed from: e, reason: collision with root package name */
    public float f15180e;

    /* renamed from: f, reason: collision with root package name */
    public float f15181f;

    /* renamed from: g, reason: collision with root package name */
    private final List f15182g = new ArrayList();

    /* renamed from: h, reason: collision with root package name */
    private final List f15183h = new ArrayList();

    /* renamed from: i, reason: collision with root package name */
    private boolean f15184i;

    static class ArcShadowOperation extends ShadowCompatOperation {

        /* renamed from: c, reason: collision with root package name */
        private final PathArcOperation f15188c;

        public ArcShadowOperation(PathArcOperation pathArcOperation) {
            this.f15188c = pathArcOperation;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void a(Matrix matrix, ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
            shadowRenderer.a(canvas, matrix, new RectF(this.f15188c.k(), this.f15188c.o(), this.f15188c.l(), this.f15188c.j()), i2, this.f15188c.m(), this.f15188c.n());
        }
    }

    static class InnerCornerShadowOperation extends ShadowCompatOperation {

        /* renamed from: c, reason: collision with root package name */
        private final PathLineOperation f15189c;

        /* renamed from: d, reason: collision with root package name */
        private final PathLineOperation f15190d;

        /* renamed from: e, reason: collision with root package name */
        private final float f15191e;

        /* renamed from: f, reason: collision with root package name */
        private final float f15192f;

        public InnerCornerShadowOperation(PathLineOperation pathLineOperation, PathLineOperation pathLineOperation2, float f2, float f3) {
            this.f15189c = pathLineOperation;
            this.f15190d = pathLineOperation2;
            this.f15191e = f2;
            this.f15192f = f3;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void a(Matrix matrix, ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
            float e2 = e();
            if (e2 > 0.0f) {
                return;
            }
            double hypot = Math.hypot(this.f15189c.f15209b - this.f15191e, this.f15189c.f15210c - this.f15192f);
            double hypot2 = Math.hypot(this.f15190d.f15209b - this.f15189c.f15209b, this.f15190d.f15210c - this.f15189c.f15210c);
            float min = (float) Math.min(i2, Math.min(hypot, hypot2));
            double d2 = min;
            double tan = Math.tan(Math.toRadians((-e2) / 2.0f)) * d2;
            if (hypot > tan) {
                RectF rectF = new RectF(0.0f, 0.0f, (float) (hypot - tan), 0.0f);
                this.f15217a.set(matrix);
                this.f15217a.preTranslate(this.f15191e, this.f15192f);
                this.f15217a.preRotate(d());
                shadowRenderer.b(canvas, this.f15217a, rectF, i2);
            }
            float f2 = 2.0f * min;
            RectF rectF2 = new RectF(0.0f, 0.0f, f2, f2);
            this.f15217a.set(matrix);
            this.f15217a.preTranslate(this.f15189c.f15209b, this.f15189c.f15210c);
            this.f15217a.preRotate(d());
            this.f15217a.preTranslate((float) ((-tan) - d2), (-2.0f) * min);
            shadowRenderer.c(canvas, this.f15217a, rectF2, (int) min, 450.0f, e2, new float[]{(float) (d2 + tan), f2});
            if (hypot2 > tan) {
                RectF rectF3 = new RectF(0.0f, 0.0f, (float) (hypot2 - tan), 0.0f);
                this.f15217a.set(matrix);
                this.f15217a.preTranslate(this.f15189c.f15209b, this.f15189c.f15210c);
                this.f15217a.preRotate(c());
                this.f15217a.preTranslate((float) tan, 0.0f);
                shadowRenderer.b(canvas, this.f15217a, rectF3, i2);
            }
        }

        float c() {
            return (float) Math.toDegrees(Math.atan((this.f15190d.f15210c - this.f15189c.f15210c) / (this.f15190d.f15209b - this.f15189c.f15209b)));
        }

        float d() {
            return (float) Math.toDegrees(Math.atan((this.f15189c.f15210c - this.f15192f) / (this.f15189c.f15209b - this.f15191e)));
        }

        float e() {
            float c2 = ((c() - d()) + 360.0f) % 360.0f;
            return c2 <= 180.0f ? c2 : c2 - 360.0f;
        }
    }

    static class LineShadowOperation extends ShadowCompatOperation {

        /* renamed from: c, reason: collision with root package name */
        private final PathLineOperation f15193c;

        /* renamed from: d, reason: collision with root package name */
        private final float f15194d;

        /* renamed from: e, reason: collision with root package name */
        private final float f15195e;

        public LineShadowOperation(PathLineOperation pathLineOperation, float f2, float f3) {
            this.f15193c = pathLineOperation;
            this.f15194d = f2;
            this.f15195e = f3;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void a(Matrix matrix, ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.f15193c.f15210c - this.f15195e, this.f15193c.f15209b - this.f15194d), 0.0f);
            this.f15217a.set(matrix);
            this.f15217a.preTranslate(this.f15194d, this.f15195e);
            this.f15217a.preRotate(c());
            shadowRenderer.b(canvas, this.f15217a, rectF, i2);
        }

        float c() {
            return (float) Math.toDegrees(Math.atan((this.f15193c.f15210c - this.f15195e) / (this.f15193c.f15209b - this.f15194d)));
        }
    }

    public static class PathArcOperation extends PathOperation {

        /* renamed from: h, reason: collision with root package name */
        private static final RectF f15196h = new RectF();

        /* renamed from: b, reason: collision with root package name */
        public float f15197b;

        /* renamed from: c, reason: collision with root package name */
        public float f15198c;

        /* renamed from: d, reason: collision with root package name */
        public float f15199d;

        /* renamed from: e, reason: collision with root package name */
        public float f15200e;

        /* renamed from: f, reason: collision with root package name */
        public float f15201f;

        /* renamed from: g, reason: collision with root package name */
        public float f15202g;

        public PathArcOperation(float f2, float f3, float f4, float f5) {
            q(f2);
            u(f3);
            r(f4);
            p(f5);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float j() {
            return this.f15200e;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float k() {
            return this.f15197b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float l() {
            return this.f15199d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float m() {
            return this.f15201f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float n() {
            return this.f15202g;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float o() {
            return this.f15198c;
        }

        private void p(float f2) {
            this.f15200e = f2;
        }

        private void q(float f2) {
            this.f15197b = f2;
        }

        private void r(float f2) {
            this.f15199d = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s(float f2) {
            this.f15201f = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void t(float f2) {
            this.f15202g = f2;
        }

        private void u(float f2) {
            this.f15198c = f2;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f15211a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = f15196h;
            rectF.set(k(), o(), l(), j());
            path.arcTo(rectF, m(), n(), false);
            path.transform(matrix);
        }
    }

    public static class PathCubicOperation extends PathOperation {

        /* renamed from: b, reason: collision with root package name */
        private float f15203b;

        /* renamed from: c, reason: collision with root package name */
        private float f15204c;

        /* renamed from: d, reason: collision with root package name */
        private float f15205d;

        /* renamed from: e, reason: collision with root package name */
        private float f15206e;

        /* renamed from: f, reason: collision with root package name */
        private float f15207f;

        /* renamed from: g, reason: collision with root package name */
        private float f15208g;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f15211a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.cubicTo(this.f15203b, this.f15204c, this.f15205d, this.f15206e, this.f15207f, this.f15208g);
            path.transform(matrix);
        }
    }

    public static class PathLineOperation extends PathOperation {

        /* renamed from: b, reason: collision with root package name */
        private float f15209b;

        /* renamed from: c, reason: collision with root package name */
        private float f15210c;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f15211a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f15209b, this.f15210c);
            path.transform(matrix);
        }
    }

    public static abstract class PathOperation {

        /* renamed from: a, reason: collision with root package name */
        protected final Matrix f15211a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    public static class PathQuadOperation extends PathOperation {

        /* renamed from: b, reason: collision with root package name */
        public float f15212b;

        /* renamed from: c, reason: collision with root package name */
        public float f15213c;

        /* renamed from: d, reason: collision with root package name */
        public float f15214d;

        /* renamed from: e, reason: collision with root package name */
        public float f15215e;

        private float b() {
            return this.f15212b;
        }

        private float c() {
            return this.f15213c;
        }

        private float d() {
            return this.f15214d;
        }

        private float e() {
            return this.f15215e;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f15211a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.quadTo(b(), c(), d(), e());
            path.transform(matrix);
        }
    }

    static abstract class ShadowCompatOperation {

        /* renamed from: b, reason: collision with root package name */
        static final Matrix f15216b = new Matrix();

        /* renamed from: a, reason: collision with root package name */
        final Matrix f15217a = new Matrix();

        ShadowCompatOperation() {
        }

        public abstract void a(Matrix matrix, ShadowRenderer shadowRenderer, int i2, Canvas canvas);

        public final void b(ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
            a(f15216b, shadowRenderer, i2, canvas);
        }
    }

    public ShapePath() {
        o(0.0f, 0.0f);
    }

    private void b(float f2) {
        if (g() == f2) {
            return;
        }
        float g2 = ((f2 - g()) + 360.0f) % 360.0f;
        if (g2 > 180.0f) {
            return;
        }
        PathArcOperation pathArcOperation = new PathArcOperation(i(), j(), i(), j());
        pathArcOperation.s(g());
        pathArcOperation.t(g2);
        this.f15183h.add(new ArcShadowOperation(pathArcOperation));
        q(f2);
    }

    private void c(ShadowCompatOperation shadowCompatOperation, float f2, float f3) {
        b(f2);
        this.f15183h.add(shadowCompatOperation);
        q(f3);
    }

    private float g() {
        return this.f15180e;
    }

    private float h() {
        return this.f15181f;
    }

    private void q(float f2) {
        this.f15180e = f2;
    }

    private void r(float f2) {
        this.f15181f = f2;
    }

    private void s(float f2) {
        this.f15178c = f2;
    }

    private void t(float f2) {
        this.f15179d = f2;
    }

    private void u(float f2) {
        this.f15176a = f2;
    }

    private void v(float f2) {
        this.f15177b = f2;
    }

    public void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        PathArcOperation pathArcOperation = new PathArcOperation(f2, f3, f4, f5);
        pathArcOperation.s(f6);
        pathArcOperation.t(f7);
        this.f15182g.add(pathArcOperation);
        ArcShadowOperation arcShadowOperation = new ArcShadowOperation(pathArcOperation);
        float f8 = f6 + f7;
        boolean z = f7 < 0.0f;
        if (z) {
            f6 = (f6 + 180.0f) % 360.0f;
        }
        c(arcShadowOperation, f6, z ? (180.0f + f8) % 360.0f : f8);
        double d2 = f8;
        s(((f2 + f4) * 0.5f) + (((f4 - f2) / 2.0f) * ((float) Math.cos(Math.toRadians(d2)))));
        t(((f3 + f5) * 0.5f) + (((f5 - f3) / 2.0f) * ((float) Math.sin(Math.toRadians(d2)))));
    }

    public void d(Matrix matrix, Path path) {
        int size = this.f15182g.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((PathOperation) this.f15182g.get(i2)).a(matrix, path);
        }
    }

    boolean e() {
        return this.f15184i;
    }

    ShadowCompatOperation f(Matrix matrix) {
        b(h());
        final Matrix matrix2 = new Matrix(matrix);
        final ArrayList arrayList = new ArrayList(this.f15183h);
        return new ShadowCompatOperation() { // from class: com.google.android.material.shape.ShapePath.1
            @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
            public void a(Matrix matrix3, ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((ShadowCompatOperation) it.next()).a(matrix2, shadowRenderer, i2, canvas);
                }
            }
        };
    }

    float i() {
        return this.f15178c;
    }

    float j() {
        return this.f15179d;
    }

    float k() {
        return this.f15176a;
    }

    float l() {
        return this.f15177b;
    }

    public void m(float f2, float f3) {
        PathLineOperation pathLineOperation = new PathLineOperation();
        pathLineOperation.f15209b = f2;
        pathLineOperation.f15210c = f3;
        this.f15182g.add(pathLineOperation);
        LineShadowOperation lineShadowOperation = new LineShadowOperation(pathLineOperation, i(), j());
        c(lineShadowOperation, lineShadowOperation.c() + 270.0f, lineShadowOperation.c() + 270.0f);
        s(f2);
        t(f3);
    }

    public void n(float f2, float f3, float f4, float f5) {
        if ((Math.abs(f2 - i()) < 0.001f && Math.abs(f3 - j()) < 0.001f) || (Math.abs(f2 - f4) < 0.001f && Math.abs(f3 - f5) < 0.001f)) {
            m(f4, f5);
            return;
        }
        PathLineOperation pathLineOperation = new PathLineOperation();
        pathLineOperation.f15209b = f2;
        pathLineOperation.f15210c = f3;
        this.f15182g.add(pathLineOperation);
        PathLineOperation pathLineOperation2 = new PathLineOperation();
        pathLineOperation2.f15209b = f4;
        pathLineOperation2.f15210c = f5;
        this.f15182g.add(pathLineOperation2);
        InnerCornerShadowOperation innerCornerShadowOperation = new InnerCornerShadowOperation(pathLineOperation, pathLineOperation2, i(), j());
        if (innerCornerShadowOperation.e() > 0.0f) {
            m(f2, f3);
            m(f4, f5);
        } else {
            c(innerCornerShadowOperation, innerCornerShadowOperation.d() + 270.0f, innerCornerShadowOperation.c() + 270.0f);
            s(f4);
            t(f5);
        }
    }

    public void o(float f2, float f3) {
        p(f2, f3, 270.0f, 0.0f);
    }

    public void p(float f2, float f3, float f4, float f5) {
        u(f2);
        v(f3);
        s(f2);
        t(f3);
        q(f4);
        r((f4 + f5) % 360.0f);
        this.f15182g.clear();
        this.f15183h.clear();
        this.f15184i = false;
    }
}
