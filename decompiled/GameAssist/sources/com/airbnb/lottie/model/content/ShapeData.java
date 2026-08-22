package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.MiscUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ShapeData {

    /* renamed from: a, reason: collision with root package name */
    private final List f9703a;

    /* renamed from: b, reason: collision with root package name */
    private PointF f9704b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f9705c;

    public ShapeData(PointF pointF, boolean z, List list) {
        this.f9704b = pointF;
        this.f9705c = z;
        this.f9703a = new ArrayList(list);
    }

    public List a() {
        return this.f9703a;
    }

    public PointF b() {
        return this.f9704b;
    }

    public void c(ShapeData shapeData, ShapeData shapeData2, float f2) {
        if (this.f9704b == null) {
            this.f9704b = new PointF();
        }
        this.f9705c = shapeData.d() || shapeData2.d();
        if (shapeData.a().size() != shapeData2.a().size()) {
            Logger.c("Curves must have the same number of control points. Shape 1: " + shapeData.a().size() + "\tShape 2: " + shapeData2.a().size());
        }
        int min = Math.min(shapeData.a().size(), shapeData2.a().size());
        if (this.f9703a.size() < min) {
            for (int size = this.f9703a.size(); size < min; size++) {
                this.f9703a.add(new CubicCurveData());
            }
        } else if (this.f9703a.size() > min) {
            for (int size2 = this.f9703a.size() - 1; size2 >= min; size2--) {
                List list = this.f9703a;
                list.remove(list.size() - 1);
            }
        }
        PointF b2 = shapeData.b();
        PointF b3 = shapeData2.b();
        f(MiscUtils.i(b2.x, b3.x, f2), MiscUtils.i(b2.y, b3.y, f2));
        for (int size3 = this.f9703a.size() - 1; size3 >= 0; size3--) {
            CubicCurveData cubicCurveData = (CubicCurveData) shapeData.a().get(size3);
            CubicCurveData cubicCurveData2 = (CubicCurveData) shapeData2.a().get(size3);
            PointF a2 = cubicCurveData.a();
            PointF b4 = cubicCurveData.b();
            PointF c2 = cubicCurveData.c();
            PointF a3 = cubicCurveData2.a();
            PointF b5 = cubicCurveData2.b();
            PointF c3 = cubicCurveData2.c();
            ((CubicCurveData) this.f9703a.get(size3)).d(MiscUtils.i(a2.x, a3.x, f2), MiscUtils.i(a2.y, a3.y, f2));
            ((CubicCurveData) this.f9703a.get(size3)).e(MiscUtils.i(b4.x, b5.x, f2), MiscUtils.i(b4.y, b5.y, f2));
            ((CubicCurveData) this.f9703a.get(size3)).f(MiscUtils.i(c2.x, c3.x, f2), MiscUtils.i(c2.y, c3.y, f2));
        }
    }

    public boolean d() {
        return this.f9705c;
    }

    public void e(boolean z) {
        this.f9705c = z;
    }

    public void f(float f2, float f3) {
        if (this.f9704b == null) {
            this.f9704b = new PointF();
        }
        this.f9704b.set(f2, f3);
    }

    public String toString() {
        return "ShapeData{numCurves=" + this.f9703a.size() + "closed=" + this.f9705c + '}';
    }

    public ShapeData() {
        this.f9703a = new ArrayList();
    }
}
