package cn.nubia.gameassist.panel.drawable.diplogen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.PathInterpolator;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.test.GameAssistTestActivity;
import cn.nubia.gameassist.theme.Theme;
import com.zte.shared.wrapper.TraceWrapper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EffectDrawable extends DiplogenDrawable {

    /* renamed from: o, reason: collision with root package name */
    protected Matrix f6855o;

    /* renamed from: p, reason: collision with root package name */
    protected Matrix f6856p;

    /* renamed from: q, reason: collision with root package name */
    private final RectF f6857q;

    /* renamed from: r, reason: collision with root package name */
    private final Paint f6858r;

    /* renamed from: s, reason: collision with root package name */
    private String f6859s;
    private final BaseEffect[] t;

    private abstract class BaseEffect {

        /* renamed from: a, reason: collision with root package name */
        protected final RectF f6860a;

        /* renamed from: b, reason: collision with root package name */
        protected final Rect f6861b;

        protected RectF a(RectF rectF, float[] fArr) {
            float width = rectF.width();
            float f2 = fArr[0] * width;
            float f3 = fArr[1] * width;
            float centerX = rectF.centerX() - (f2 / 2.0f);
            float f4 = rectF.top + (width * fArr[2]);
            return new RectF(centerX, f4, f2 + centerX, f3 + f4);
        }

        abstract float[] b();

        protected boolean c() {
            int i2 = EffectDrawable.this.f6849i;
            return i2 == 0 || i2 == 1;
        }

        abstract void d(Canvas canvas);

        protected void e(Theme theme, boolean z) {
        }

        protected void f(boolean z) {
        }

        protected void g() {
        }

        protected void h(RectF rectF) {
            float[] b2 = b();
            if (b2 != null) {
                RectF a2 = a(rectF, b2);
                this.f6860a.set(a2);
                this.f6861b.set((int) a2.left, (int) a2.top, (int) a2.right, (int) a2.bottom);
            }
        }

        private BaseEffect() {
            this.f6860a = new RectF();
            this.f6861b = new Rect();
        }
    }

    private class Blusher extends BaseEffect {
        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        protected RectF a(RectF rectF, float[] fArr) {
            float width = rectF.width();
            float f2 = fArr[0];
            float f3 = width * f2;
            float f4 = f2 * width;
            float centerX = (rectF.centerX() - (f3 / 2.0f)) + (fArr[1] * width);
            float f5 = rectF.top + (width * fArr[2]);
            return new RectF(centerX, f5, f3 + centerX, f4 + f5);
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        float[] b() {
            return new float[]{1.1f, 0.4f, -0.2f};
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        public void d(Canvas canvas) {
            if (EffectDrawable.this.f6852l != null) {
                int save = canvas.save();
                Matrix matrix = canvas.getMatrix();
                canvas.setMatrix(EffectDrawable.this.f6856p);
                EffectDrawable effectDrawable = EffectDrawable.this;
                Drawable c2 = effectDrawable.c(effectDrawable.f6852l.t.f7463d);
                int sin = (int) ((Math.sin((((System.currentTimeMillis() / 10) % 360) * 3.141592653589793d) / 180.0d) * 35.0d) + 220.0d);
                float f2 = EffectDrawable.this.f6852l.f7449p;
                if (f2 != 1.0f) {
                    sin = (int) (sin * f2);
                } else {
                    c2.setColorFilter(null);
                }
                c2.setAlpha(sin);
                c2.setBounds(this.f6861b);
                c2.draw(canvas);
                canvas.setMatrix(matrix);
                canvas.restoreToCount(save);
            }
        }

        private Blusher() {
            super();
        }
    }

    private class BullHorn extends BaseEffect {

        /* renamed from: d, reason: collision with root package name */
        final Paint f6864d;

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        float[] b() {
            return new float[]{0.87f, 0.1328f, 0.643f};
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        void d(Canvas canvas) {
            Theme theme = EffectDrawable.this.f6852l;
            if (theme != null) {
                float f2 = theme.f7449p;
                int i2 = 255;
                if (f2 != 1.0f) {
                    int i3 = (int) (255.0f - (f2 * 255.0f));
                    this.f6864d.setAlpha(i3);
                    canvas.drawBitmap(EffectDrawable.this.b(Theme.EffectTheme.f7458n), (Rect) null, this.f6861b, this.f6864d);
                    i2 = 255 - i3;
                }
                int saveLayer = canvas.saveLayer(this.f6860a, null, 31);
                this.f6864d.setAlpha(i2);
                EffectDrawable effectDrawable = EffectDrawable.this;
                canvas.drawBitmap(effectDrawable.b(effectDrawable.f6852l.f7438e), (Rect) null, this.f6861b, this.f6864d);
                this.f6864d.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                canvas.drawBitmap(EffectDrawable.this.b(Theme.EffectTheme.f7458n), (Rect) null, this.f6861b, this.f6864d);
                this.f6864d.setXfermode(null);
                canvas.restoreToCount(saveLayer);
            }
        }

        private BullHorn() {
            super();
            Paint paint = new Paint(2);
            this.f6864d = paint;
            paint.setAntiAlias(true);
            paint.setDither(true);
        }
    }

    private class DialPlate extends BaseEffect {
        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        float[] b() {
            return new float[]{1.05f, 0.38f, 0.275f};
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        public void d(Canvas canvas) {
            EffectDrawable effectDrawable = EffectDrawable.this;
            Theme theme = effectDrawable.f6852l;
            if (theme != null) {
                Bitmap b2 = effectDrawable.b(theme.t.f7462c);
                EffectDrawable.this.f6858r.setColorFilter(EffectDrawable.this.f6852l.f7435b);
                EffectDrawable.this.f6858r.setAlpha(255);
                canvas.drawBitmap(b2, (Rect) null, this.f6861b, EffectDrawable.this.f6858r);
            }
        }

        private DialPlate() {
            super();
        }
    }

    private class Fire extends BaseEffect {

        /* renamed from: d, reason: collision with root package name */
        private final Paint f6867d;

        /* renamed from: e, reason: collision with root package name */
        protected final int f6868e;

        /* renamed from: f, reason: collision with root package name */
        protected final int f6869f;

        /* renamed from: g, reason: collision with root package name */
        private final PathInterpolator f6870g;

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        float[] b() {
            return new float[]{0.19f, 0.39f, 0.15f};
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        void d(Canvas canvas) {
            long currentTimeMillis = System.currentTimeMillis() % 3000;
            if (EffectDrawable.this.f6852l == null || currentTimeMillis > this.f6869f) {
                return;
            }
            int save = canvas.save();
            Matrix matrix = canvas.getMatrix();
            canvas.setMatrix(EffectDrawable.this.f6855o);
            float f2 = 1.0f;
            float f3 = (currentTimeMillis * 1.0f) / this.f6869f;
            if (f3 < 0.3f) {
                f2 = f3 / 0.3f;
            } else if (f3 >= 0.7d) {
                f2 = (1.0f - f3) / 0.3f;
            }
            float f4 = (((currentTimeMillis % 80) * 255) * EffectDrawable.this.f6852l.f7449p) / 80.0f;
            Theme theme = Theme.x;
            int length = ((int) (r7.length * f3)) % theme.t.f7467h.length;
            int interpolation = (int) (this.f6861b.top - ((this.f6870g.getInterpolation(f3) * this.f6861b.top) * 0.5f));
            EffectDrawable effectDrawable = EffectDrawable.this;
            Drawable c2 = effectDrawable.c(effectDrawable.f6852l.t.f7467h[length]);
            c2.setColorFilter(EffectDrawable.this.f6852l.f7435b);
            c2.setAlpha((int) (f2 * f4));
            Rect rect = this.f6861b;
            c2.setBounds(rect.left, interpolation, rect.right, rect.height() + interpolation);
            c2.draw(canvas);
            int length2 = ((length + r1.length) - 1) % theme.t.f7467h.length;
            int interpolation2 = (int) (this.f6861b.top - ((this.f6870g.getInterpolation(f3) * this.f6861b.top) * 0.5f));
            EffectDrawable effectDrawable2 = EffectDrawable.this;
            Drawable c3 = effectDrawable2.c(effectDrawable2.f6852l.t.f7467h[length2]);
            c3.setColorFilter(EffectDrawable.this.f6852l.f7435b);
            Rect rect2 = this.f6861b;
            c3.setBounds(rect2.left, interpolation2, rect2.right, rect2.height() + interpolation2);
            c3.setAlpha((int) (f2 * (255.0f - f4)));
            c3.draw(canvas);
            canvas.setMatrix(matrix);
            canvas.restoreToCount(save);
        }

        private Fire() {
            super();
            this.f6868e = 80;
            this.f6869f = Theme.x.t.f7467h.length * 80;
            this.f6870g = new PathInterpolator(0.8f, 0.3f, 0.55f, 0.82f);
            Paint paint = new Paint();
            this.f6867d = paint;
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setColor(2013200384);
            paint.setStyle(Paint.Style.FILL);
        }
    }

    private class Fog extends PathEffect {

        /* renamed from: j, reason: collision with root package name */
        private final boolean f6872j;

        /* renamed from: k, reason: collision with root package name */
        private RectF f6873k;

        public Fog(boolean z) {
            super(EffectDrawable.this, new float[][]{new float[]{0.165f, 0.021f, 0.269f}, new float[]{1.15f, 0.442f, 0.138f}});
            this.f6873k = new RectF();
            this.f6872j = z;
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        public void d(Canvas canvas) {
            if (EffectDrawable.this.f6852l != null) {
                int save = canvas.save();
                Matrix matrix = canvas.getMatrix();
                canvas.setMatrix(EffectDrawable.this.f6855o);
                this.f6906g.setColorFilter(EffectDrawable.this.f6852l.f7435b);
                EffectDrawable effectDrawable = EffectDrawable.this;
                canvas.drawBitmapMesh(effectDrawable.b(effectDrawable.f6852l.t.f7465f), this.f6908i.length - 1, 60, this.f6905f, (int) ((((4290.0f - (System.currentTimeMillis() % 3300)) * 100.0f) * 2.0f) / 3300.0f), null, 0, this.f6906g);
                canvas.setMatrix(matrix);
                canvas.restoreToCount(save);
            }
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.PathEffect, cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        protected void h(RectF rectF) {
            super.h(rectF);
            RectF rectF2 = this.f6873k;
            RectF rectF3 = this.f6860a;
            float f2 = rectF3.left;
            float centerX = this.f6872j ? rectF3.top : rectF3.centerX();
            RectF rectF4 = this.f6860a;
            rectF2.set(f2, centerX, rectF4.right, this.f6872j ? rectF4.centerX() : rectF4.bottom);
        }
    }

    private class Glow extends BaseEffect {

        /* renamed from: d, reason: collision with root package name */
        private final Paint f6875d;

        /* renamed from: e, reason: collision with root package name */
        private final float f6876e;

        /* renamed from: f, reason: collision with root package name */
        private final float f6877f;

        /* renamed from: g, reason: collision with root package name */
        private float f6878g;

        /* renamed from: h, reason: collision with root package name */
        private float f6879h;

        /* renamed from: i, reason: collision with root package name */
        private float f6880i;

        /* renamed from: j, reason: collision with root package name */
        private float f6881j;

        /* renamed from: k, reason: collision with root package name */
        private final int f6882k;

        /* renamed from: l, reason: collision with root package name */
        private final int f6883l;

        /* renamed from: m, reason: collision with root package name */
        private Path f6884m;

        /* renamed from: n, reason: collision with root package name */
        private PathMeasure f6885n;

        /* renamed from: o, reason: collision with root package name */
        private float f6886o;

        /* renamed from: p, reason: collision with root package name */
        private float f6887p;

        /* renamed from: q, reason: collision with root package name */
        private float f6888q;

        private RectF i(long j2) {
            float f2 = this.f6886o + 0.4f;
            this.f6886o = f2;
            float f3 = this.f6888q;
            if (f2 > f3 * 2.0f) {
                this.f6886o = f2 - (f3 * 2.0f);
            }
            float[] fArr = new float[2];
            PathMeasure pathMeasure = this.f6885n;
            float f4 = this.f6886o;
            if (f4 > f3) {
                f4 = (f3 * 2.0f) - f4;
            }
            pathMeasure.getPosTan(f4, fArr, null);
            float f5 = ((j2 % 6000) * 1.0f) / 6000.0f;
            float f6 = f5 < 0.5f ? f5 * 2.0f : (1.0f - f5) * 2.0f;
            float f7 = this.f6878g;
            float f8 = this.f6879h;
            float f9 = ((f6 * (f7 - f8)) / 6000.0f) + f8;
            float f10 = fArr[0];
            float f11 = fArr[1];
            return new RectF(f10 - f9, f11 - f9, f10 + f9, f11 + f9);
        }

        private RectF j(long j2) {
            float f2 = this.f6887p + 0.71999997f;
            this.f6887p = f2;
            float f3 = this.f6888q;
            if (f2 > f3 * 2.0f) {
                this.f6887p = f2 - (f3 * 2.0f);
            }
            float[] fArr = new float[2];
            PathMeasure pathMeasure = this.f6885n;
            float f4 = this.f6887p;
            if (f4 > f3) {
                f4 = (f3 * 2.0f) - f4;
            }
            pathMeasure.getPosTan(f4, fArr, null);
            float f5 = ((j2 % 3500) * 1.0f) / 3500.0f;
            float f6 = f5 < 0.5f ? f5 * 2.0f : (1.0f - f5) * 2.0f;
            float f7 = this.f6880i;
            float f8 = this.f6881j;
            float f9 = (f6 * (f7 - f8)) + f8;
            float f10 = fArr[0];
            float f11 = fArr[1];
            return new RectF(f10 - f9, f11 - f9, f10 + f9, f11 + f9);
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        float[] b() {
            return new float[]{1.15f, 0.5f, 0.2f};
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        void d(Canvas canvas) {
            Theme theme;
            if (this.f6861b.width() <= 0 || (theme = EffectDrawable.this.f6852l) == null) {
                return;
            }
            this.f6875d.setColorFilter(theme.f7435b);
            long currentTimeMillis = System.currentTimeMillis();
            int save = canvas.save();
            Matrix matrix = canvas.getMatrix();
            canvas.setMatrix(EffectDrawable.this.f6855o);
            EffectDrawable effectDrawable = EffectDrawable.this;
            canvas.drawBitmap(effectDrawable.b(effectDrawable.f6852l.t.f7460a), (Rect) null, i(currentTimeMillis), this.f6875d);
            EffectDrawable effectDrawable2 = EffectDrawable.this;
            canvas.drawBitmap(effectDrawable2.b(effectDrawable2.f6852l.t.f7461b), (Rect) null, j(currentTimeMillis), this.f6875d);
            canvas.setMatrix(matrix);
            canvas.restoreToCount(save);
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        protected void h(RectF rectF) {
            super.h(rectF);
            Path path = new Path();
            this.f6884m = path;
            path.addArc(this.f6860a, 250.0f, 90.0f);
            PathMeasure pathMeasure = new PathMeasure(this.f6884m, false);
            this.f6885n = pathMeasure;
            this.f6888q = pathMeasure.getLength();
            float width = this.f6860a.width() * 0.54f;
            this.f6878g = width;
            this.f6879h = width * 0.8f;
            float f2 = width * 0.7f;
            this.f6880i = f2;
            this.f6881j = f2 * 0.8f;
        }

        private Glow() {
            super();
            this.f6876e = 0.4f;
            this.f6877f = 0.71999997f;
            this.f6882k = 6000;
            this.f6883l = 3500;
            Paint paint = new Paint(2);
            this.f6875d = paint;
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setStyle(Paint.Style.STROKE);
        }
    }

    private class Mesh extends BaseEffect {

        /* renamed from: d, reason: collision with root package name */
        protected final long f6890d;

        /* renamed from: e, reason: collision with root package name */
        protected final float f6891e;

        /* renamed from: f, reason: collision with root package name */
        protected final float f6892f;

        /* renamed from: g, reason: collision with root package name */
        protected final float f6893g;

        /* renamed from: h, reason: collision with root package name */
        protected final float f6894h;

        /* renamed from: i, reason: collision with root package name */
        protected final float f6895i;

        /* renamed from: j, reason: collision with root package name */
        protected final float f6896j;

        /* renamed from: k, reason: collision with root package name */
        private final Wave[] f6897k;

        /* renamed from: l, reason: collision with root package name */
        private final Paint f6898l;

        private class Wave {

            /* renamed from: a, reason: collision with root package name */
            private final long f6900a;

            /* renamed from: b, reason: collision with root package name */
            protected float f6901b;

            public RectF a() {
                float cos;
                float width;
                this.f6901b = (((System.currentTimeMillis() + this.f6900a) % 8000) * 1.0f) / 8000.0f;
                float centerX = EffectDrawable.this.f6857q.centerX();
                float f2 = this.f6901b;
                if (f2 < 0.8f) {
                    cos = (float) (0.5299999713897705d - (Math.sin(((f2 * 3.141592653589793d) * 0.5d) / 0.800000011920929d) * 0.07999999821186066d));
                    width = EffectDrawable.this.f6857q.width();
                } else {
                    cos = (float) (0.5299999713897705d - (Math.cos((f2 - 0.8f) * 3.141592653589793d) * 0.07999999821186066d));
                    width = EffectDrawable.this.f6857q.width();
                }
                float f3 = cos * width;
                float width2 = ((this.f6901b * 0.38f) + 0.102f) * EffectDrawable.this.f6857q.width();
                float width3 = ((this.f6901b * 0.105000004f) + 0.042f) * EffectDrawable.this.f6857q.width();
                float f4 = (EffectDrawable.this.f6857q.top + f3) - width3;
                return new RectF(centerX - width2, f4, centerX + width2, (width3 * 2.0f) + f4);
            }

            private Wave(long j2) {
                this.f6900a = j2;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Mesh() {
            super();
            Object[] objArr = 0;
            this.f6890d = 8000L;
            this.f6891e = 0.53f;
            this.f6892f = 0.08f;
            this.f6893g = 0.102f;
            this.f6894h = 0.482f;
            this.f6895i = 0.042f;
            this.f6896j = 0.147f;
            this.f6897k = new Wave[5];
            long length = 8000 / r8.length;
            int i2 = 0;
            while (true) {
                Wave[] waveArr = this.f6897k;
                if (i2 >= waveArr.length) {
                    Paint paint = new Paint();
                    this.f6898l = paint;
                    paint.setDither(true);
                    paint.setAntiAlias(true);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(1.5f);
                    return;
                }
                waveArr[i2] = new Wave(i2 * length);
                i2++;
            }
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        float[] b() {
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0045  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0070 A[SYNTHETIC] */
        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void d(android.graphics.Canvas r12) {
            /*
                r11 = this;
                cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable r0 = cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.this
                cn.nubia.gameassist.theme.Theme r0 = r0.f6852l
                if (r0 == 0) goto L9d
                r0 = 0
            L7:
                cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable$Mesh$Wave[] r1 = r11.f6897k
                int r2 = r1.length
                if (r0 >= r2) goto L9d
                r1 = r1[r0]
                float r2 = r1.f6901b
                r3 = 1045220557(0x3e4ccccd, float:0.2)
                int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                r4 = 1065353216(0x3f800000, float:1.0)
                r5 = 1117782016(0x42a00000, float:80.0)
                if (r3 >= 0) goto L21
                r3 = 1084227584(0x40a00000, float:5.0)
                float r2 = r2 * r3
            L1e:
                float r2 = r2 * r5
                int r2 = (int) r2
                goto L32
            L21:
                r3 = 1058642330(0x3f19999a, float:0.6)
                int r6 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r6 <= 0) goto L30
                float r2 = r2 - r3
                r3 = 1053609165(0x3ecccccd, float:0.4)
                float r2 = r2 / r3
                float r2 = r4 - r2
                goto L1e
            L30:
                r2 = 80
            L32:
                android.graphics.RectF r1 = r1.a()
                cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable r3 = cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.this
                cn.nubia.gameassist.theme.Theme r3 = r3.f6852l
                float r3 = r3.f7449p
                int r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
                r5 = 1061997773(0x3f4ccccd, float:0.8)
                r6 = 1073741824(0x40000000, float:2.0)
                if (r4 == 0) goto L70
                float r4 = (float) r2
                float r3 = r3 * r4
                float r4 = r4 - r3
                int r3 = (int) r4
                android.graphics.Paint r4 = r11.f6898l
                r7 = -8947849(0xffffffffff777777, float:-3.2893961E38)
                r4.setColor(r7)
                android.graphics.Paint r4 = r11.f6898l
                float r7 = (float) r3
                float r7 = r7 * r5
                int r7 = (int) r7
                r4.setAlpha(r7)
                android.graphics.RectF r4 = new android.graphics.RectF
                float r7 = r1.left
                float r7 = r7 + r6
                float r8 = r1.top
                float r8 = r8 + r6
                float r9 = r1.right
                float r9 = r9 - r6
                float r10 = r1.bottom
                float r10 = r10 - r6
                r4.<init>(r7, r8, r9, r10)
                android.graphics.Paint r7 = r11.f6898l
                r12.drawOval(r4, r7)
                int r2 = r2 - r3
            L70:
                android.graphics.Paint r3 = r11.f6898l
                cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable r4 = cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.this
                cn.nubia.gameassist.theme.Theme r4 = r4.f6852l
                int r4 = r4.f7437d
                r3.setColor(r4)
                android.graphics.Paint r3 = r11.f6898l
                float r2 = (float) r2
                float r2 = r2 * r5
                int r2 = (int) r2
                r3.setAlpha(r2)
                android.graphics.RectF r2 = new android.graphics.RectF
                float r3 = r1.left
                float r3 = r3 + r6
                float r4 = r1.top
                float r4 = r4 + r6
                float r5 = r1.right
                float r5 = r5 - r6
                float r1 = r1.bottom
                float r1 = r1 - r6
                r2.<init>(r3, r4, r5, r1)
                android.graphics.Paint r1 = r11.f6898l
                r12.drawOval(r2, r1)
                int r0 = r0 + 1
                goto L7
            L9d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.Mesh.d(android.graphics.Canvas):void");
        }
    }

    private abstract class PathEffect extends BaseEffect {

        /* renamed from: d, reason: collision with root package name */
        protected final int f6903d;

        /* renamed from: e, reason: collision with root package name */
        protected final long f6904e;

        /* renamed from: f, reason: collision with root package name */
        protected final float[] f6905f;

        /* renamed from: g, reason: collision with root package name */
        protected final Paint f6906g;

        /* renamed from: h, reason: collision with root package name */
        protected List f6907h;

        /* renamed from: i, reason: collision with root package name */
        protected final float[][] f6908i;

        public PathEffect(EffectDrawable effectDrawable, float[][] fArr) {
            super();
            this.f6903d = 100;
            this.f6904e = 3300L;
            this.f6907h = new ArrayList();
            this.f6908i = fArr;
            this.f6905f = new float[fArr.length * 404];
            Paint paint = new Paint(2);
            this.f6906g = paint;
            paint.setAntiAlias(true);
            paint.setDither(true);
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        float[] b() {
            return this.f6908i[1];
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        protected void h(RectF rectF) {
            super.h(rectF);
            this.f6907h.clear();
            for (float[] fArr : this.f6908i) {
                Path path = new Path();
                path.addOval(a(rectF, fArr), Path.Direction.CW);
                this.f6907h.add(path);
            }
            int size = this.f6907h.size();
            for (int i2 = 0; i2 < size; i2++) {
                float[] e2 = DiplogenUtils.e((Path) this.f6907h.get(i2), 100, false);
                for (int i3 = 0; i3 <= 100; i3++) {
                    int i4 = ((size * i3) + i2) * 2;
                    int i5 = i3 * 2;
                    float[] fArr2 = this.f6905f;
                    fArr2[i4] = e2[i5];
                    fArr2[i4 + 1] = e2[i5 + 1];
                }
            }
            float[] fArr3 = this.f6905f;
            System.arraycopy(fArr3, 0, fArr3, fArr3.length / 2, fArr3.length / 2);
        }
    }

    private class Speed extends BaseEffect {

        /* renamed from: d, reason: collision with root package name */
        private final Paint f6909d;

        /* renamed from: e, reason: collision with root package name */
        private final Rect f6910e;

        private void i(Canvas canvas, int i2, RectF rectF) {
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 > 10) {
                i2 = 10;
            }
            canvas.drawBitmap(EffectDrawable.this.b(Theme.EffectTheme.f7453i[i2]), (Rect) null, rectF, this.f6909d);
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        float[] b() {
            return new float[]{0.624f, 0.2f, -0.04f};
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        public synchronized void d(Canvas canvas) {
            int i2;
            int i3;
            int i4;
            try {
                if (EffectDrawable.this.f6859s == null) {
                    if (c()) {
                        EffectDrawable.this.f6859s = "1.78";
                    } else {
                        EffectDrawable.this.f6859s = "220";
                    }
                }
                EffectDrawable effectDrawable = EffectDrawable.this;
                if (effectDrawable.f6852l != null && effectDrawable.f6859s != null && EffectDrawable.this.f6859s.length() >= 1) {
                    this.f6909d.setColorFilter(EffectDrawable.this.f6852l.f7435b);
                    int centerX = this.f6861b.centerX();
                    int width = this.f6910e.width();
                    char[] charArray = EffectDrawable.this.f6859s.toCharArray();
                    float f2 = width;
                    float f3 = centerX - ((EffectDrawable.this.f6859s.contains(".") ? 0.2f : 0.5f) * f2);
                    int i5 = charArray.length >= 3 ? 2 : 1;
                    float f4 = f3;
                    for (int length = charArray.length - i5; length < charArray.length; length++) {
                        char c2 = charArray[length];
                        int i6 = c2 - '0';
                        if (c2 == '.') {
                            i3 = (int) (0.4f * f2);
                            i4 = 10;
                        } else {
                            i3 = width;
                            i4 = i6;
                        }
                        Rect rect = this.f6861b;
                        RectF rectF = new RectF(f4, rect.top, f4 + f2, rect.bottom);
                        this.f6909d.setAlpha(255);
                        int saveLayer = canvas.saveLayer(rectF, null, 31);
                        i(canvas, i4, rectF);
                        this.f6909d.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                        EffectDrawable effectDrawable2 = EffectDrawable.this;
                        canvas.drawBitmap(effectDrawable2.b(effectDrawable2.f6852l.t.f7464e), (Rect) null, rectF, this.f6909d);
                        this.f6909d.setXfermode(null);
                        canvas.restoreToCount(saveLayer);
                        f4 += i3;
                    }
                    int length2 = (charArray.length - i5) - 1;
                    while (length2 >= 0) {
                        char c3 = charArray[length2];
                        int i7 = c3 - '0';
                        if (c3 == '.') {
                            i2 = (int) (f2 * 0.4f);
                            i7 = 10;
                        } else {
                            i2 = width;
                        }
                        if (length2 == 0 && i7 == 1) {
                            f3 += 12.0f;
                        }
                        float f5 = f3 - i2;
                        Rect rect2 = this.f6861b;
                        RectF rectF2 = new RectF(f5, rect2.top, f3, rect2.bottom);
                        this.f6909d.setAlpha(255);
                        int saveLayer2 = canvas.saveLayer(rectF2, null, 31);
                        i(canvas, i7, rectF2);
                        this.f6909d.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                        EffectDrawable effectDrawable3 = EffectDrawable.this;
                        canvas.drawBitmap(effectDrawable3.b(effectDrawable3.f6852l.t.f7464e), (Rect) null, rectF2, this.f6909d);
                        this.f6909d.setXfermode(null);
                        canvas.restoreToCount(saveLayer2);
                        length2--;
                        f3 = f5;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        protected void g() {
            super.g();
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        protected void h(RectF rectF) {
            super.h(rectF);
            g();
            int height = this.f6861b.height();
            this.f6910e.set(0, 0, (int) (height * 0.9f), height);
        }

        private Speed() {
            super();
            this.f6910e = new Rect();
            Paint paint = new Paint();
            this.f6909d = paint;
            paint.setDither(true);
            paint.setAntiAlias(true);
        }
    }

    private class Swimming extends PathEffect {

        /* renamed from: j, reason: collision with root package name */
        private final boolean f6912j;

        /* renamed from: k, reason: collision with root package name */
        private final RectF f6913k;

        /* renamed from: l, reason: collision with root package name */
        private final Paint f6914l;

        public Swimming(boolean z) {
            super(EffectDrawable.this, new float[][]{new float[]{0.516f, 0.164f, 0.23f}, new float[]{0.699f, 0.251f, 0.206f}});
            this.f6913k = new RectF();
            this.f6912j = z;
            Paint paint = new Paint(2);
            this.f6914l = paint;
            paint.setAntiAlias(true);
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        public void d(Canvas canvas) {
            if (EffectDrawable.this.f6852l != null) {
                int saveLayer = canvas.saveLayer(this.f6913k, null, 31);
                Matrix matrix = canvas.getMatrix();
                canvas.setMatrix(EffectDrawable.this.f6855o);
                this.f6914l.setColorFilter(EffectDrawable.this.f6852l.f7435b);
                EffectDrawable effectDrawable = EffectDrawable.this;
                Theme.EffectTheme effectTheme = effectDrawable.f6852l.t;
                canvas.drawBitmap(effectDrawable.b(Theme.EffectTheme.f7459o), (Rect) null, this.f6860a, this.f6914l);
                this.f6914l.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                int currentTimeMillis = (int) (((3300 - (System.currentTimeMillis() % 3300)) * 200) / 3300);
                EffectDrawable effectDrawable2 = EffectDrawable.this;
                canvas.drawBitmapMesh(effectDrawable2.b(effectDrawable2.f6852l.t.f7466g), this.f6908i.length - 1, 101, this.f6905f, currentTimeMillis, null, 0, this.f6914l);
                this.f6914l.setXfermode(null);
                canvas.setMatrix(matrix);
                canvas.restoreToCount(saveLayer);
            }
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.PathEffect, cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        protected void h(RectF rectF) {
            super.h(rectF);
            RectF rectF2 = this.f6913k;
            RectF rectF3 = this.f6860a;
            float f2 = rectF3.left;
            float centerX = this.f6912j ? rectF3.top : rectF3.centerX();
            RectF rectF4 = this.f6860a;
            rectF2.set(f2, centerX, rectF4.right, this.f6912j ? rectF4.centerX() : rectF4.bottom);
        }
    }

    private class Title extends BaseEffect {

        /* renamed from: d, reason: collision with root package name */
        final Paint f6916d;

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        float[] b() {
            return new float[]{0.17f, 0.051f, 0.715f};
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        public void d(Canvas canvas) {
            Theme theme = EffectDrawable.this.f6852l;
            if (theme != null) {
                float f2 = theme.f7449p;
                int i2 = 255;
                if (f2 != 1.0f) {
                    int i3 = (int) (255.0f - (f2 * 255.0f));
                    this.f6916d.setAlpha(i3);
                    if (c()) {
                        canvas.drawBitmap(EffectDrawable.this.b(Theme.EffectTheme.f7457m), (Rect) null, this.f6861b, this.f6916d);
                    } else {
                        canvas.drawBitmap(EffectDrawable.this.b(Theme.EffectTheme.f7456l), (Rect) null, this.f6861b, this.f6916d);
                    }
                    i2 = 255 - i3;
                }
                int saveLayer = canvas.saveLayer(this.f6860a, null, 31);
                this.f6916d.setAlpha(i2);
                if (c()) {
                    canvas.drawBitmap(EffectDrawable.this.b(Theme.EffectTheme.f7457m), (Rect) null, this.f6861b, this.f6916d);
                } else {
                    canvas.drawBitmap(EffectDrawable.this.b(Theme.EffectTheme.f7456l), (Rect) null, this.f6861b, this.f6916d);
                }
                this.f6916d.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                EffectDrawable effectDrawable = EffectDrawable.this;
                canvas.drawBitmap(effectDrawable.b(effectDrawable.f6852l.f7438e), (Rect) null, this.f6861b, this.f6916d);
                this.f6916d.setXfermode(null);
                canvas.restoreToCount(saveLayer);
            }
        }

        private Title() {
            super();
            Paint paint = new Paint(2);
            this.f6916d = paint;
            paint.setAntiAlias(true);
            paint.setDither(true);
        }
    }

    private class Unit extends BaseEffect {
        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        float[] b() {
            return new float[]{0.18824999f, 0.09825f, 0.154f};
        }

        @Override // cn.nubia.gameassist.panel.drawable.diplogen.EffectDrawable.BaseEffect
        public void d(Canvas canvas) {
            Drawable drawable = c() ? EffectDrawable.this.f6850j.getResources().getDrawable(Theme.EffectTheme.f7454j) : EffectDrawable.this.f6850j.getResources().getDrawable(Theme.EffectTheme.f7455k);
            drawable.setColorFilter(EffectDrawable.this.f6852l.f7435b);
            drawable.setBounds(this.f6861b);
            drawable.draw(canvas);
        }

        private Unit() {
            super();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EffectDrawable(Context context, int i2) {
        super(context, i2);
        this.f6857q = new RectF();
        this.t = new BaseEffect[]{new DialPlate(), new Mesh(), new Swimming(true), new Fog(true), new BullHorn(), new Title(), new Unit(), new Speed(), new Fire(), new Swimming(false), new Fog(false), new Glow(), new Blusher()};
        Paint paint = new Paint();
        this.f6858r = paint;
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setColor(587202304);
    }

    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable, cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        boolean z = this.f6852l != theme;
        super.d(theme);
        for (BaseEffect baseEffect : this.t) {
            TraceWrapper.traceBegin(8L, baseEffect.getClass().getSimpleName());
            baseEffect.e(theme, z);
            TraceWrapper.traceEnd(8L);
        }
        TraceWrapper.traceBegin(8L, "invalidateSelf");
        invalidateSelf();
        TraceWrapper.traceEnd(8L);
    }

    @Override // android.graphics.drawable.Drawable
    @VisibleForTesting
    public void draw(Canvas canvas) {
        TraceWrapper.traceBegin(8L, getClass().getSimpleName());
        if (this.f6852l != null) {
            int save = canvas.save();
            for (BaseEffect baseEffect : this.t) {
                TraceWrapper.traceBegin(8L, baseEffect.getClass().getSimpleName());
                baseEffect.d(canvas);
                TraceWrapper.traceEnd(8L);
            }
            canvas.restoreToCount(save);
        }
        if (isVisible() && !GameAssistTestActivity.f7433c) {
            invalidateSelf();
        }
        TraceWrapper.traceEnd(8L);
    }

    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable
    void f(boolean z) {
        for (BaseEffect baseEffect : this.t) {
            baseEffect.f(z);
        }
    }

    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable
    void g(Rect rect) {
        this.f6855o = new Matrix();
        this.f6856p = new Matrix();
        if (((View) getCallback()) != null) {
            this.f6857q.set(new RectF(rect.left + r0.getPaddingLeft(), rect.top + r0.getPaddingTop(), rect.right - r0.getPaddingRight(), rect.bottom - r0.getPaddingBottom()));
            int i2 = this.f6849i;
            if (i2 == 2 || i2 == 3) {
                DiplogenUtils.d(2, this.f6855o, rect);
            }
            DiplogenUtils.d(this.f6849i, this.f6856p, rect);
            for (BaseEffect baseEffect : this.t) {
                baseEffect.h(this.f6857q);
            }
        }
    }

    public void l(String str) {
        if (str != null && !str.contains(".")) {
            if (str.length() == 1) {
                str = "00" + str;
            } else if (str.length() == 2) {
                str = "0" + str;
            }
        }
        if (TextUtils.equals(str, this.f6859s)) {
            return;
        }
        this.f6859s = str;
        invalidateSelf();
    }
}
