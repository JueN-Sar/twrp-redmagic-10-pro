package com.zte.gameassist.drawable.linechart;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import com.zte.gameassist.common.R;
import com.zte.gameassist.drawable.Chart;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes2.dex */
public class LineChart extends Chart {

    /* renamed from: l, reason: collision with root package name */
    private final Path f16643l;

    /* renamed from: m, reason: collision with root package name */
    private final Path f16644m;

    /* renamed from: n, reason: collision with root package name */
    protected float f16645n;

    /* renamed from: o, reason: collision with root package name */
    protected int f16646o;

    /* renamed from: p, reason: collision with root package name */
    protected int f16647p;

    /* renamed from: q, reason: collision with root package name */
    protected int f16648q;

    /* renamed from: r, reason: collision with root package name */
    private LinearGradient f16649r;

    private List i() {
        ArrayList arrayList = new ArrayList();
        float width = this.f16638h.getWidth() / ((((Integer) this.f16635e.second).intValue() - ((Integer) this.f16635e.first).intValue()) * 1000.0f);
        float height = this.f16638h.getHeight() / (((Float) this.f16636f.second).floatValue() - ((Float) this.f16636f.first).floatValue());
        PointF pointF = this.f16634d;
        Iterator it = d().iterator();
        while (it.hasNext()) {
            arrayList.add(new PointF(pointF.x + ((System.currentTimeMillis() - r5.f16642h) * width), pointF.y - ((((Chart.Item) it.next()).f16641c - ((Float) this.f16636f.first).floatValue()) * height)));
        }
        return arrayList;
    }

    private void n() {
        if (this.f16637g == null || this.f16638h == null || getBounds().isEmpty()) {
            return;
        }
        Rect bounds = getBounds();
        float height = this.f16634d.y - this.f16638h.getHeight();
        int i2 = bounds.left;
        this.f16649r = new LinearGradient(i2, height, i2, height + this.f16638h.getHeight(), new int[]{this.f16647p, this.f16648q}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawColor(285277952);
        int save = canvas.save();
        Rect bounds = getBounds();
        canvas.clipRect(new Rect(bounds.left + ((this.f16637g.getWidth() - this.f16638h.getWidth()) / 2), bounds.top + ((this.f16637g.getHeight() - this.f16638h.getHeight()) / 2), this.f16638h.getWidth(), this.f16638h.getHeight()));
        canvas.drawColor(570425599);
        List i2 = i();
        this.f16644m.reset();
        this.f16643l.reset();
        for (int i3 = 0; i3 < i2.size(); i3++) {
            PointF pointF = (PointF) i2.get(i3);
            if (i3 == 0) {
                Path path = this.f16644m;
                PointF pointF2 = this.f16634d;
                path.moveTo(pointF2.x, pointF2.y);
                this.f16644m.lineTo(pointF.x, this.f16634d.y);
                this.f16643l.moveTo(pointF.x, pointF.y);
            } else {
                this.f16643l.lineTo(pointF.x, pointF.y);
            }
            this.f16644m.lineTo(pointF.x, pointF.y);
            if (i3 == i2.size() - 1) {
                this.f16644m.lineTo(pointF.x, this.f16634d.y);
                this.f16644m.close();
            }
        }
        if (this.f16649r != null) {
            this.f16631a.setStyle(Paint.Style.FILL);
            this.f16631a.setShader(this.f16649r);
            canvas.drawPath(this.f16644m, this.f16631a);
        }
        this.f16631a.setShader(null);
        this.f16631a.setStyle(Paint.Style.STROKE);
        this.f16631a.setStrokeWidth(this.f16645n);
        this.f16631a.setColor(this.f16646o);
        canvas.drawPath(this.f16643l, this.f16631a);
        canvas.restoreToCount(save);
        if (this.f16632b.size() > 0) {
            invalidateSelf();
        }
    }

    @Override // com.zte.gameassist.drawable.Chart
    protected void e() {
        n();
    }

    @Override // com.zte.gameassist.drawable.Chart, android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R.styleable.LineChart);
        m(obtainAttributes.getDimensionPixelSize(R.styleable.LineChart_strokeWidth, 5));
        l(obtainAttributes.getColor(R.styleable.LineChart_strokeColor, -65536));
        k(obtainAttributes.getColor(R.styleable.LineChart_gradientStartColor, -65536));
        j(obtainAttributes.getColor(R.styleable.LineChart_gradientEndColor, -65536));
        obtainAttributes.recycle();
    }

    public void j(int i2) {
        this.f16648q = i2;
        n();
        invalidateSelf();
    }

    public void k(int i2) {
        this.f16647p = i2;
        n();
        invalidateSelf();
    }

    public void l(int i2) {
        this.f16646o = i2;
        invalidateSelf();
    }

    public void m(float f2) {
        this.f16645n = f2;
        invalidateSelf();
    }
}
