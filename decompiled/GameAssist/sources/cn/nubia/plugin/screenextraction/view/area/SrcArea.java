package cn.nubia.plugin.screenextraction.view.area;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.plugin.screenextraction.view.area.IArea;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class SrcArea implements IArea {

    /* renamed from: e, reason: collision with root package name */
    private final int f8662e;

    /* renamed from: g, reason: collision with root package name */
    private final int f8664g;

    /* renamed from: h, reason: collision with root package name */
    private Drawable f8665h;

    /* renamed from: i, reason: collision with root package name */
    private int f8666i;

    /* renamed from: j, reason: collision with root package name */
    private final Paint f8667j;

    /* renamed from: k, reason: collision with root package name */
    private final Paint f8668k;

    /* renamed from: l, reason: collision with root package name */
    private final Paint f8669l;

    /* renamed from: m, reason: collision with root package name */
    private final Path f8670m;

    /* renamed from: n, reason: collision with root package name */
    private final View f8671n;

    /* renamed from: o, reason: collision with root package name */
    private IArea.Callback f8672o;

    /* renamed from: a, reason: collision with root package name */
    private final Point f8658a = new Point();

    /* renamed from: b, reason: collision with root package name */
    private final Rect f8659b = new Rect();

    /* renamed from: c, reason: collision with root package name */
    private final Rect f8660c = new Rect();

    /* renamed from: d, reason: collision with root package name */
    private final Rect f8661d = new Rect();

    /* renamed from: f, reason: collision with root package name */
    private final Rect f8663f = new Rect();

    public SrcArea(Rect rect, Drawable drawable, float f2, View view) {
        this.f8671n = view;
        this.f8665h = drawable;
        int intrinsicWidth = drawable.getIntrinsicWidth() * 2;
        this.f8664g = intrinsicWidth;
        this.f8662e = intrinsicWidth;
        this.f8670m = new Path();
        Paint paint = new Paint();
        this.f8667j = paint;
        paint.setColor(182851385);
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(64);
        this.f8668k = paint2;
        paint2.setStrokeWidth(f2);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(1508251449);
        float f3 = view.getResources().getDisplayMetrics().density * 4.0f;
        paint2.setPathEffect(new DashPathEffect(new float[]{f3, f3}, 0.0f));
        this.f8669l = new Paint();
        d(rect);
    }

    private int f(int i2, int i3) {
        if (!this.f8660c.contains(i2, i3) || this.f8661d.contains(i2, i3)) {
            return 5;
        }
        PointF pointF = new PointF(this.f8663f.centerX(), this.f8663f.centerY());
        float f2 = i2;
        if (f2 < pointF.x && i3 < pointF.y && i(i2, i3)) {
            return 1;
        }
        if (f2 > pointF.x && i3 < pointF.y && k(i2, i3)) {
            return 2;
        }
        if (f2 >= pointF.x || i3 <= pointF.y || !h(i2, i3)) {
            return (f2 <= pointF.x || ((float) i3) <= pointF.y || !j(i2, i3)) ? 5 : 4;
        }
        return 3;
    }

    private boolean g(int i2, int i3) {
        return this.f8660c.contains(i2, i3);
    }

    private boolean h(int i2, int i3) {
        return Math.sqrt(Math.pow((double) (i2 - this.f8663f.left), 2.0d) + Math.pow((double) (i3 - this.f8663f.bottom), 2.0d)) < ((double) this.f8664g);
    }

    private boolean i(int i2, int i3) {
        return Math.sqrt(Math.pow((double) (i2 - this.f8663f.left), 2.0d) + Math.pow((double) (i3 - this.f8663f.top), 2.0d)) < ((double) this.f8664g);
    }

    private boolean j(int i2, int i3) {
        return Math.sqrt(Math.pow((double) (i2 - this.f8663f.right), 2.0d) + Math.pow((double) (i3 - this.f8663f.bottom), 2.0d)) < ((double) this.f8664g);
    }

    private boolean k(int i2, int i3) {
        return Math.sqrt(Math.pow((double) (i2 - this.f8663f.right), 2.0d) + Math.pow((double) (i3 - this.f8663f.top), 2.0d)) < ((double) this.f8664g);
    }

    private void l(int i2, int i3) {
        Rect rect = new Rect(this.f8659b);
        int i4 = this.f8666i;
        if (i4 == 1) {
            Rect rect2 = this.f8663f;
            Rect rect3 = this.f8659b;
            rect2.left = rect3.left + i2;
            rect2.top = rect3.top + i3;
            int width = rect2.width();
            int i5 = this.f8664g;
            if (width < i5) {
                Rect rect4 = this.f8663f;
                rect4.left = rect4.right - i5;
            }
            int height = this.f8663f.height();
            int i6 = this.f8664g;
            if (height < i6) {
                Rect rect5 = this.f8663f;
                rect5.top = rect5.bottom - i6;
            }
        } else if (i4 == 2) {
            Rect rect6 = this.f8663f;
            Rect rect7 = this.f8659b;
            rect6.right = rect7.right + i2;
            rect6.top = rect7.top + i3;
            int width2 = rect6.width();
            int i7 = this.f8664g;
            if (width2 < i7) {
                Rect rect8 = this.f8663f;
                rect8.right = rect8.left + i7;
            }
            int height2 = this.f8663f.height();
            int i8 = this.f8664g;
            if (height2 < i8) {
                Rect rect9 = this.f8663f;
                rect9.top = rect9.bottom - i8;
            }
        } else if (i4 == 3) {
            Rect rect10 = this.f8663f;
            Rect rect11 = this.f8659b;
            rect10.left = rect11.left + i2;
            rect10.bottom = rect11.bottom + i3;
            int width3 = rect10.width();
            int i9 = this.f8664g;
            if (width3 < i9) {
                Rect rect12 = this.f8663f;
                rect12.left = rect12.right - i9;
            }
            int height3 = this.f8663f.height();
            int i10 = this.f8664g;
            if (height3 < i10) {
                Rect rect13 = this.f8663f;
                rect13.bottom = rect13.top + i10;
            }
        } else if (i4 == 4) {
            Rect rect14 = this.f8663f;
            Rect rect15 = this.f8659b;
            rect14.right = rect15.right + i2;
            rect14.bottom = rect15.bottom + i3;
            int width4 = rect14.width();
            int i11 = this.f8664g;
            if (width4 < i11) {
                Rect rect16 = this.f8663f;
                rect16.right = rect16.left + i11;
            }
            int height4 = this.f8663f.height();
            int i12 = this.f8664g;
            if (height4 < i12) {
                Rect rect17 = this.f8663f;
                rect17.bottom = rect17.top + i12;
            }
        } else if (i4 == 5) {
            Rect rect18 = new Rect(this.f8659b);
            rect18.offset(i2, i3);
            Point point = new Point();
            int i13 = rect18.left;
            if (i13 < 0) {
                point.x = 0 - i13;
            }
            int i14 = rect18.top;
            if (i14 < 0) {
                point.y = 0 - i14;
            }
            int i15 = rect18.right;
            int i16 = GameAssistWindowManager.Q;
            if (i15 > i16) {
                point.x = i16 - i15;
            }
            int i17 = rect18.bottom;
            int i18 = GameAssistWindowManager.P;
            if (i17 > i18) {
                point.y = i18 - i17;
            }
            rect18.offset(point.x, point.y);
            if (rect18.left >= 0 && rect18.top >= 0 && rect18.right <= this.f8671n.getWidth() && rect18.bottom <= this.f8671n.getHeight()) {
                this.f8663f.set(rect18);
            }
        }
        Rect rect19 = this.f8663f;
        if (rect19.top < 0) {
            rect19.top = 0;
        }
        if (rect19.left < 0) {
            rect19.left = 0;
        }
        int i19 = rect19.right;
        int i20 = GameAssistWindowManager.Q;
        if (i19 > i20) {
            rect19.right = i20;
        }
        int i21 = rect19.bottom;
        int i22 = GameAssistWindowManager.P;
        if (i21 > i22) {
            rect19.bottom = i22;
        }
        int height5 = rect19.height();
        int i23 = GameAssistWindowManager.P;
        if (height5 > i23 / 2) {
            Rect rect20 = this.f8663f;
            rect20.bottom = rect20.top + (i23 / 2);
        }
        Rect rect21 = new Rect(this.f8663f);
        IArea.Callback callback = this.f8672o;
        if (callback != null) {
            callback.a(this, rect, rect21);
        }
        this.f8670m.reset();
        float strokeWidth = this.f8668k.getStrokeWidth() / 2.0f;
        Path path = this.f8670m;
        Rect rect22 = this.f8663f;
        path.addRect(new RectF(rect22.left + strokeWidth, rect22.top + strokeWidth, rect22.right - strokeWidth, rect22.bottom - strokeWidth), Path.Direction.CCW);
        m();
    }

    private void m() {
        Rect rect = this.f8660c;
        Rect rect2 = this.f8663f;
        int i2 = rect2.left;
        int i3 = this.f8662e;
        rect.set(i2 - i3, rect2.top - i3, rect2.right + i3, rect2.bottom + i3);
        Rect rect3 = this.f8661d;
        Rect rect4 = this.f8663f;
        int i4 = rect4.left;
        int i5 = this.f8662e;
        rect3.set(i4 + i5, rect4.top + i5, rect4.right - i5, rect4.bottom - i5);
    }

    @Override // cn.nubia.plugin.screenextraction.view.area.IArea
    public Point a() {
        int i2 = this.f8664g;
        return new Point(i2, i2);
    }

    @Override // cn.nubia.plugin.screenextraction.view.area.IArea
    public Rect b() {
        return this.f8663f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
    
        if (r6 != 6) goto L19;
     */
    @Override // cn.nubia.plugin.screenextraction.view.area.IArea
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean c(android.view.MotionEvent r6) {
        /*
            r5 = this;
            float r0 = r6.getRawX()
            int r0 = (int) r0
            float r1 = r6.getRawY()
            int r1 = (int) r1
            int r6 = r6.getAction()
            r2 = 0
            r3 = 1
            if (r6 == 0) goto L31
            if (r6 == r3) goto L2e
            r4 = 2
            if (r6 == r4) goto L1e
            r0 = 3
            if (r6 == r0) goto L2e
            r0 = 6
            if (r6 == r0) goto L2e
            goto L49
        L1e:
            int r6 = r5.f8666i
            if (r6 == 0) goto L49
            android.graphics.Point r6 = r5.f8658a
            int r4 = r6.x
            int r0 = r0 - r4
            int r6 = r6.y
            int r1 = r1 - r6
            r5.l(r0, r1)
            goto L49
        L2e:
            r5.f8666i = r2
            goto L49
        L31:
            boolean r6 = r5.g(r0, r1)
            if (r6 == 0) goto L49
            int r6 = r5.f(r0, r1)
            r5.f8666i = r6
            android.graphics.Point r6 = r5.f8658a
            r6.set(r0, r1)
            android.graphics.Rect r6 = r5.f8659b
            android.graphics.Rect r0 = r5.f8663f
            r6.set(r0)
        L49:
            int r5 = r5.f8666i
            if (r5 == 0) goto L4e
            r2 = r3
        L4e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.plugin.screenextraction.view.area.SrcArea.c(android.view.MotionEvent):boolean");
    }

    @Override // cn.nubia.plugin.screenextraction.view.area.IArea
    public void d(Rect rect) {
        Rect rect2 = new Rect(rect);
        Point point = new Point();
        int i2 = rect2.left;
        if (i2 < 0) {
            point.x = 0 - i2;
        }
        int i3 = rect2.top;
        if (i3 < 0) {
            point.y = 0 - i3;
        }
        int i4 = rect2.right;
        int i5 = GameAssistWindowManager.Q;
        if (i4 > i5) {
            point.x = i5 - i4;
        }
        int i6 = rect2.bottom;
        int i7 = GameAssistWindowManager.P;
        if (i6 > i7) {
            point.y = i7 - i6;
        }
        rect2.offset(point.x, point.y);
        if (!rect.equals(rect2)) {
            GaLog.j("ScreenExtraction", "ScrArea setData data=" + rect + " to temp=" + rect2);
        }
        this.f8663f.set(rect2);
        this.f8670m.reset();
        this.f8670m.addRect(new RectF(this.f8663f), Path.Direction.CCW);
        m();
    }

    @Override // cn.nubia.plugin.screenextraction.view.area.IArea
    public void e(IArea.Callback callback) {
        this.f8672o = callback;
    }

    @Override // cn.nubia.plugin.screenextraction.view.area.IArea
    public void onDraw(Canvas canvas) {
        canvas.drawPath(this.f8670m, this.f8668k);
        Bitmap bitmap = ((BitmapDrawable) this.f8665h).getBitmap();
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect rect = this.f8663f;
        PointF pointF = new PointF(rect.left, rect.top);
        Rect rect2 = this.f8663f;
        PointF pointF2 = new PointF(rect2.right - width, rect2.top);
        Rect rect3 = this.f8663f;
        PointF pointF3 = new PointF(rect3.right - width, rect3.bottom - height);
        Rect rect4 = this.f8663f;
        PointF[] pointFArr = {pointF, pointF2, pointF3, new PointF(rect4.left, rect4.bottom - height)};
        Matrix matrix = new Matrix();
        for (int i2 = 0; i2 < 4; i2++) {
            int save = canvas.save();
            PointF pointF4 = pointFArr[i2];
            canvas.translate(pointF4.x, pointF4.y);
            matrix.reset();
            matrix.setRotate(i2 * 90, width / 2, height / 2);
            canvas.drawBitmap(bitmap, matrix, this.f8669l);
            canvas.restoreToCount(save);
        }
    }
}
