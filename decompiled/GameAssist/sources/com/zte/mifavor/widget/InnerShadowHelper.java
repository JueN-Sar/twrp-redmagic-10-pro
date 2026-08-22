package com.zte.mifavor.widget;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class InnerShadowHelper {

    /* renamed from: a, reason: collision with root package name */
    private Paint f17654a;

    /* renamed from: b, reason: collision with root package name */
    private List f17655b;

    /* renamed from: c, reason: collision with root package name */
    private Context f17656c;

    public static class InnerShadowLayer {

        /* renamed from: a, reason: collision with root package name */
        public int f17657a;

        /* renamed from: b, reason: collision with root package name */
        public float f17658b;

        /* renamed from: c, reason: collision with root package name */
        public float f17659c;

        /* renamed from: d, reason: collision with root package name */
        public float f17660d;

        /* renamed from: e, reason: collision with root package name */
        public float f17661e;

        /* renamed from: f, reason: collision with root package name */
        public BlendMode f17662f;

        public InnerShadowLayer(int i2, float f2, float f3, float f4, float f5, BlendMode blendMode) {
            this.f17657a = i2;
            this.f17658b = f2;
            this.f17659c = f3;
            this.f17660d = f4;
            this.f17661e = f5;
            this.f17662f = blendMode;
        }
    }

    public InnerShadowHelper(Context context) {
        this.f17656c = context;
        Paint paint = new Paint(1);
        this.f17654a = paint;
        paint.setStyle(Paint.Style.FILL);
        f();
    }

    private Path a(Path path, InnerShadowLayer innerShadowLayer) {
        Path path2 = new Path(path);
        RectF rectF = new RectF();
        path2.computeBounds(rectF, true);
        float width = rectF.width();
        float height = rectF.height();
        if (width > 0.0f && height > 0.0f) {
            float f2 = innerShadowLayer.f17661e * 2.0f;
            float f3 = (width - f2) / width;
            float f4 = (height - f2) / height;
            float max = Math.max(0.02f, f3);
            float max2 = Math.max(0.02f, f4);
            Matrix matrix = new Matrix();
            matrix.postScale(max, max2, rectF.centerX(), rectF.centerY());
            matrix.postTranslate(-innerShadowLayer.f17658b, -innerShadowLayer.f17659c);
            path2.transform(matrix);
        }
        return path2;
    }

    private float b(float f2) {
        return f2 * this.f17656c.getResources().getDisplayMetrics().density;
    }

    private void e(Canvas canvas, Path path, InnerShadowLayer innerShadowLayer) {
        canvas.save();
        canvas.clipPath(path);
        Path a2 = a(path, innerShadowLayer);
        if (a2 == null || a2.isEmpty()) {
            canvas.restore();
            return;
        }
        Path path2 = new Path(path);
        path2.op(a2, Path.Op.DIFFERENCE);
        this.f17654a.setColor(innerShadowLayer.f17657a);
        float max = Math.max(0.05f, innerShadowLayer.f17660d);
        if (max > 0.0f) {
            this.f17654a.setMaskFilter(new BlurMaskFilter(max, BlurMaskFilter.Blur.NORMAL));
        } else {
            this.f17654a.setMaskFilter(null);
        }
        this.f17654a.setBlendMode(innerShadowLayer.f17662f);
        canvas.drawPath(path2, this.f17654a);
        canvas.restore();
    }

    private void f() {
        ArrayList arrayList = new ArrayList();
        this.f17655b = arrayList;
        arrayList.add(new InnerShadowLayer(1090519039, 0.0f, 0.0f, b(0.22f), b(0.45f), null));
        List list = this.f17655b;
        float b2 = b(-1.2f);
        float b3 = b(-1.8f);
        float b4 = b(0.38f);
        float b5 = b(-0.8f);
        BlendMode blendMode = BlendMode.SCREEN;
        list.add(new InnerShadowLayer(-2030043137, b2, b3, b4, b5, blendMode));
        this.f17655b.add(new InnerShadowLayer(-1056964609, b(1.15f), b(1.7f), b(0.38f), b(-0.75f), blendMode));
        List list2 = this.f17655b;
        float b6 = b(1.15f);
        float b7 = b(1.7f);
        float b8 = b(0.28f);
        float b9 = b(-0.9f);
        BlendMode blendMode2 = BlendMode.DARKEN;
        list2.add(new InnerShadowLayer(1382770518, b6, b7, b8, b9, blendMode2));
        List list3 = this.f17655b;
        float b10 = b(1.0f);
        float b11 = b(1.5f);
        float b12 = b(0.32f);
        float b13 = b(-1.0f);
        BlendMode blendMode3 = BlendMode.LIGHTEN;
        list3.add(new InnerShadowLayer(1029792097, b10, b11, b12, b13, blendMode3));
        this.f17655b.add(new InnerShadowLayer(1660944383, b(-1.2f), b(-2.2f), b(0.35f), b(-0.85f), BlendMode.SOFT_LIGHT));
        this.f17655b.add(new InnerShadowLayer(1029792097, b(-1.0f), b(-1.5f), b(0.28f), b(-1.0f), blendMode3));
        this.f17655b.add(new InnerShadowLayer(436207616, 0.0f, 0.0f, b(1.2f), b(0.9f), BlendMode.OVERLAY));
        this.f17655b.add(new InnerShadowLayer(1191182335, 0.0f, 0.0f, b(0.18f), b(-0.4f), blendMode));
        this.f17655b.add(new InnerShadowLayer(359424584, 0.0f, b(0.7f), b(0.24f), b(-0.55f), blendMode2));
    }

    protected void c(Canvas canvas, Path path) {
        d(canvas, path, this.f17655b);
    }

    protected void d(Canvas canvas, Path path, List list) {
        if (path == null || path.isEmpty() || list == null || list.isEmpty()) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            e(canvas, path, (InnerShadowLayer) list.get(size));
        }
    }

    protected void g(boolean z, boolean z2) {
        if (z) {
            f();
        }
    }
}
