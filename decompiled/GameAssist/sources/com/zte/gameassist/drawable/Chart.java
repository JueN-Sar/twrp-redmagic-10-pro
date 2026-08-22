package com.zte.gameassist.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Size;
import com.zte.gameassist.common.R;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes2.dex */
public abstract class Chart extends Drawable {

    /* renamed from: k, reason: collision with root package name */
    protected static final SimpleDateFormat f16630k = new SimpleDateFormat("mm:ss.SSS");

    /* renamed from: a, reason: collision with root package name */
    protected final Paint f16631a;

    /* renamed from: b, reason: collision with root package name */
    protected final List f16632b;

    /* renamed from: c, reason: collision with root package name */
    private final List f16633c;

    /* renamed from: d, reason: collision with root package name */
    protected final PointF f16634d;

    /* renamed from: e, reason: collision with root package name */
    protected Pair f16635e;

    /* renamed from: f, reason: collision with root package name */
    protected Pair f16636f;

    /* renamed from: g, reason: collision with root package name */
    protected Size f16637g;

    /* renamed from: h, reason: collision with root package name */
    protected Size f16638h;

    /* renamed from: i, reason: collision with root package name */
    protected ColorFilter f16639i;

    /* renamed from: j, reason: collision with root package name */
    protected int f16640j;

    public static class Item implements Comparable<Item> {

        /* renamed from: c, reason: collision with root package name */
        public final float f16641c;

        /* renamed from: h, reason: collision with root package name */
        public final long f16642h;

        public Item(float f2, long j2) {
            this.f16641c = f2;
            this.f16642h = j2;
        }

        @Override // java.lang.Comparable
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compareTo(Item item) {
            return (int) (this.f16642h - item.f16642h);
        }

        public String toString() {
            return this.f16641c + " - " + Chart.f16630k.format(Long.valueOf(this.f16642h));
        }
    }

    private void h() {
        if (this.f16637g == null || this.f16638h == null || getBounds().isEmpty()) {
            return;
        }
        Rect bounds = getBounds();
        this.f16634d.set(bounds.left + ((this.f16637g.getWidth() - this.f16638h.getWidth()) / 2), bounds.top + ((this.f16637g.getHeight() - this.f16638h.getHeight()) / 2) + this.f16638h.getHeight());
        e();
        invalidateSelf();
    }

    public void a(float f2) {
        b(f2, System.currentTimeMillis() + 1000);
    }

    public void b(float f2, long j2) {
        c(new Item(f2, j2));
    }

    public void c(Item item) {
        synchronized (this.f16632b) {
            try {
                this.f16632b.add(item);
                long currentTimeMillis = System.currentTimeMillis() - ((((Integer) this.f16635e.second).intValue() + 1) * 1000);
                for (int size = this.f16632b.size() - 1; size >= 0; size--) {
                    if (((Item) this.f16632b.get(size)).f16642h < currentTimeMillis) {
                        this.f16632b.remove(size);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        invalidateSelf();
    }

    protected List d() {
        this.f16633c.clear();
        long currentTimeMillis = System.currentTimeMillis();
        long intValue = currentTimeMillis - ((((Integer) this.f16635e.first).intValue() - 1) * 1000);
        long intValue2 = currentTimeMillis - ((((Integer) this.f16635e.second).intValue() + 1) * 1000);
        for (int size = this.f16632b.size() - 1; size >= 0; size--) {
            Item item = (Item) this.f16632b.get(size);
            long j2 = item.f16642h;
            if (j2 <= intValue && j2 >= intValue2) {
                this.f16633c.add(item);
            }
            if (item.f16642h < intValue2) {
                this.f16632b.remove(size);
            }
        }
        Collections.sort(this.f16633c);
        return this.f16633c;
    }

    protected abstract void e();

    public void f(int i2, int i3) {
        this.f16637g = new Size(i2, i3);
        h();
    }

    public void g(int i2, int i3) {
        this.f16638h = new Size(i2, i3);
        h();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R.styleable.Chart);
        f(obtainAttributes.getDimensionPixelSize(R.styleable.Chart_width, 100), obtainAttributes.getDimensionPixelSize(R.styleable.Chart_height, 100));
        g(obtainAttributes.getDimensionPixelSize(R.styleable.Chart_viewportWidth, 100), obtainAttributes.getDimensionPixelSize(R.styleable.Chart_viewportHeight, 100));
        this.f16635e = new Pair(Integer.valueOf(obtainAttributes.getInt(R.styleable.Chart_horizontalStart, 0)), Integer.valueOf(obtainAttributes.getInt(R.styleable.Chart_horizontalEnd, 7)));
        this.f16636f = new Pair(Float.valueOf(obtainAttributes.getFloat(R.styleable.Chart_verticalStart, 1.0f)), Float.valueOf(obtainAttributes.getFloat(R.styleable.Chart_verticalEnd, 5.0f)));
        String string = obtainAttributes.getString(R.styleable.Chart_data);
        if (string != null) {
            String[] split = string.split(",");
            long currentTimeMillis = ((System.currentTimeMillis() + 2000) / 1000) * 1000;
            for (String str : split) {
                b(Float.valueOf(str).floatValue(), currentTimeMillis);
                currentTimeMillis -= 1000;
            }
        }
        obtainAttributes.recycle();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f16640j = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i2, int i3, int i4, int i5) {
        super.setBounds(i2, i3, i4, i5);
        h();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f16639i = colorFilter;
        invalidateSelf();
    }

    public String toString() {
        return super.toString();
    }
}
