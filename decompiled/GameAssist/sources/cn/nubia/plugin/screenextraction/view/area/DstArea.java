package cn.nubia.plugin.screenextraction.view.area;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.plugin.screenextraction.view.SettingsDataView;
import cn.nubia.plugin.screenextraction.view.area.IArea;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class DstArea implements IArea {

    /* renamed from: f, reason: collision with root package name */
    private Drawable f8650f;

    /* renamed from: g, reason: collision with root package name */
    private int f8651g;

    /* renamed from: h, reason: collision with root package name */
    private final Paint f8652h;

    /* renamed from: i, reason: collision with root package name */
    private final SettingsDataView f8653i;

    /* renamed from: j, reason: collision with root package name */
    private IArea.Callback f8654j;

    /* renamed from: l, reason: collision with root package name */
    private ValueAnimator f8656l;

    /* renamed from: a, reason: collision with root package name */
    private final Rect f8645a = new Rect();

    /* renamed from: b, reason: collision with root package name */
    private final Rect f8646b = new Rect();

    /* renamed from: c, reason: collision with root package name */
    private final PointF f8647c = new PointF();

    /* renamed from: d, reason: collision with root package name */
    private final Rect f8648d = new Rect();

    /* renamed from: k, reason: collision with root package name */
    PointF f8655k = new PointF();

    /* renamed from: e, reason: collision with root package name */
    private final int f8649e = 5;

    public DstArea(Rect rect, Drawable drawable, SettingsDataView settingsDataView) {
        this.f8653i = settingsDataView;
        this.f8650f = drawable;
        Paint paint = new Paint();
        this.f8652h = paint;
        paint.setColor(872415231);
        paint.setStrokeWidth(1.0f);
        paint.setStyle(Paint.Style.STROKE);
        d(rect);
    }

    private int h(int i2, int i3) {
        return this.f8646b.contains(i2, i3) ? 1 : 2;
    }

    private boolean j(int i2, int i3) {
        return this.f8645a.contains(i2, i3) || this.f8646b.contains(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(Rect rect, ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        Rect rect2 = this.f8645a;
        rect2.right = rect2.left + intValue;
        rect2.bottom = rect2.top + ((rect2.width() * rect.height()) / rect.width());
        IArea.Callback callback = this.f8654j;
        if (callback != null) {
            callback.a(this, this.f8648d, this.f8645a);
        }
        m();
    }

    private void l(float f2, float f3, float f4, float f5) {
        Rect rect = new Rect(this.f8648d);
        int i2 = this.f8651g;
        if (i2 == 1) {
            int width = this.f8645a.width();
            int height = this.f8645a.height();
            float sqrt = ((f4 + f5 > 0.0f ? 1.0f : -1.0f) * ((float) Math.sqrt(Math.pow(f4, 2.0d) + Math.pow(f5, 2.0d)))) / ((float) Math.sqrt(Math.pow(width, 2.0d) + Math.pow(height, 2.0d)));
            float width2 = width - this.f8653i.getSrcData().width();
            if (width2 >= 0.0f && sqrt > 0.0f) {
                sqrt *= 50.0f / (width2 + 50.0f);
            }
            Rect rect2 = this.f8645a;
            float f6 = rect2.right + (width * sqrt);
            float f7 = rect2.bottom + (sqrt * height);
            int i3 = rect2.left;
            int i4 = this.f8649e;
            if (f6 > i3 + i4 && f7 > rect2.top + i4) {
                rect2.right = (int) f6;
                Rect srcData = this.f8653i.getSrcData();
                Rect rect3 = this.f8645a;
                rect3.bottom = rect3.top + ((rect3.width() * srcData.height()) / srcData.width());
            }
        } else if (i2 == 2) {
            RectF rectF = new RectF(this.f8648d);
            rectF.offset(f2, f3);
            PointF pointF = new PointF();
            float f8 = rectF.left;
            if (f8 < 0.0f) {
                pointF.x = 0.0f - f8;
            }
            float f9 = rectF.top;
            if (f9 < 0.0f) {
                pointF.y = 0.0f - f9;
            }
            float f10 = rectF.right;
            int i5 = GameAssistWindowManager.Q;
            if (f10 > i5) {
                pointF.x = i5 - f10;
            }
            float f11 = rectF.bottom;
            int i6 = GameAssistWindowManager.P;
            if (f11 > i6) {
                pointF.y = i6 - f11;
            }
            rectF.offset(pointF.x, pointF.y);
            if (rectF.left >= 0.0f && rectF.top >= 0.0f && rectF.right <= this.f8653i.getWidth() && rectF.bottom <= this.f8653i.getHeight()) {
                this.f8645a.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            }
        }
        Rect rect4 = new Rect(this.f8645a);
        IArea.Callback callback = this.f8654j;
        if (callback != null) {
            callback.a(this, rect, rect4);
        }
        m();
    }

    private void m() {
        Rect rect = this.f8645a;
        int i2 = rect.right + 5;
        int i3 = rect.bottom + 5;
        this.f8646b.set(i2, i3, this.f8650f.getIntrinsicWidth() + i2, this.f8650f.getIntrinsicHeight() + i3);
    }

    @Override // cn.nubia.plugin.screenextraction.view.area.IArea
    public Point a() {
        int i2 = this.f8649e;
        return new Point(i2, i2);
    }

    @Override // cn.nubia.plugin.screenextraction.view.area.IArea
    public Rect b() {
        return this.f8645a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
    
        if (r8 != 6) goto L26;
     */
    @Override // cn.nubia.plugin.screenextraction.view.area.IArea
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean c(android.view.MotionEvent r8) {
        /*
            r7 = this;
            float r0 = r8.getRawX()
            float r1 = r8.getRawY()
            int r8 = r8.getAction()
            r2 = 0
            r3 = 1
            if (r8 == 0) goto L89
            if (r8 == r3) goto L3e
            r4 = 2
            if (r8 == r4) goto L1d
            r0 = 3
            if (r8 == r0) goto L3e
            r0 = 6
            if (r8 == r0) goto L3e
            goto Lac
        L1d:
            int r8 = r7.f8651g
            if (r8 == 0) goto Lac
            android.graphics.PointF r8 = r7.f8647c
            float r4 = r8.x
            float r4 = r0 - r4
            float r8 = r8.y
            float r8 = r1 - r8
            android.graphics.PointF r5 = r7.f8655k
            float r6 = r5.x
            float r6 = r0 - r6
            float r5 = r5.y
            float r5 = r1 - r5
            r7.l(r4, r8, r6, r5)
            android.graphics.PointF r8 = r7.f8655k
            r8.set(r0, r1)
            goto Lac
        L3e:
            cn.nubia.plugin.screenextraction.view.SettingsDataView r8 = r7.f8653i
            android.graphics.Rect r8 = r8.getSrcData()
            int r0 = r7.f8651g
            if (r0 != r3) goto L86
            android.graphics.Rect r0 = r7.f8645a
            int r0 = r0.width()
            int r1 = r8.width()
            if (r0 <= r1) goto L86
            android.graphics.Rect r0 = r7.f8645a
            int r0 = r0.width()
            int r1 = r8.width()
            int[] r0 = new int[]{r0, r1}
            android.animation.ValueAnimator r0 = android.animation.ValueAnimator.ofInt(r0)
            r7.f8656l = r0
            r4 = 100
            r0.setDuration(r4)
            android.animation.ValueAnimator r0 = r7.f8656l
            cn.nubia.plugin.screenextraction.view.area.a r1 = new cn.nubia.plugin.screenextraction.view.area.a
            r1.<init>()
            r0.addUpdateListener(r1)
            android.animation.ValueAnimator r8 = r7.f8656l
            cn.nubia.plugin.screenextraction.view.area.DstArea$1 r0 = new cn.nubia.plugin.screenextraction.view.area.DstArea$1
            r0.<init>()
            r8.addListener(r0)
            android.animation.ValueAnimator r8 = r7.f8656l
            r8.start()
        L86:
            r7.f8651g = r2
            goto Lac
        L89:
            int r8 = (int) r0
            int r4 = (int) r1
            boolean r5 = r7.j(r8, r4)
            if (r5 == 0) goto Lac
            android.animation.ValueAnimator r5 = r7.f8656l
            if (r5 != 0) goto Lac
            int r8 = r7.h(r8, r4)
            r7.f8651g = r8
            android.graphics.PointF r8 = r7.f8647c
            r8.set(r0, r1)
            android.graphics.PointF r8 = r7.f8655k
            r8.set(r0, r1)
            android.graphics.Rect r8 = r7.f8648d
            android.graphics.Rect r0 = r7.f8645a
            r8.set(r0)
        Lac:
            int r7 = r7.f8651g
            if (r7 == 0) goto Lb1
            r2 = r3
        Lb1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.plugin.screenextraction.view.area.DstArea.c(android.view.MotionEvent):boolean");
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
        if (rect2.right > GameAssistWindowManager.Q + this.f8646b.width()) {
            point.x = (GameAssistWindowManager.Q - rect2.right) - this.f8646b.width();
        }
        if (rect2.bottom > GameAssistWindowManager.P + this.f8646b.height()) {
            point.y = (GameAssistWindowManager.P - rect2.bottom) - this.f8646b.height();
        }
        rect2.offset(point.x, point.y);
        if (!rect.equals(rect2)) {
            GaLog.j("ScreenExtraction", "DstArea setData data=" + rect + " to temp=" + rect2);
        }
        this.f8645a.set(rect2);
        m();
    }

    @Override // cn.nubia.plugin.screenextraction.view.area.IArea
    public void e(IArea.Callback callback) {
        this.f8654j = callback;
    }

    public Rect i() {
        return this.f8646b;
    }

    @Override // cn.nubia.plugin.screenextraction.view.area.IArea
    public void onDraw(Canvas canvas) {
        canvas.drawRect(this.f8645a, this.f8652h);
        int save = canvas.save();
        this.f8650f.setBounds(i());
        this.f8650f.draw(canvas);
        canvas.restoreToCount(save);
    }
}
