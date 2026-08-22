package cn.nubia.gameassist.tips.launch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import cn.nubia.gameassist.tips.GameAssistLaunchTips;
import cn.nubia.gameassist.tips.launch.TipsIce;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class TipsIce extends TipsBase {

    /* renamed from: h, reason: collision with root package name */
    private final int f7591h;

    /* renamed from: i, reason: collision with root package name */
    private final int f7592i;

    /* renamed from: j, reason: collision with root package name */
    private Bitmap f7593j;

    /* renamed from: k, reason: collision with root package name */
    private Paint f7594k;

    /* renamed from: l, reason: collision with root package name */
    private final Rect f7595l;

    /* renamed from: m, reason: collision with root package name */
    private final RectF f7596m;

    /* renamed from: n, reason: collision with root package name */
    private int f7597n;

    /* renamed from: o, reason: collision with root package name */
    private int f7598o;

    /* renamed from: p, reason: collision with root package name */
    private String f7599p;

    public TipsIce(GameAssistLaunchTips gameAssistLaunchTips, Context context) {
        super(gameAssistLaunchTips, context, context.getResources());
        this.f7591h = 8;
        this.f7592i = 16;
        Rect rect = new Rect();
        this.f7595l = rect;
        this.f7596m = new RectF();
        try {
            this.f7599p = q(0);
            InputStream open = c().open(this.f7599p);
            this.f7593j = BitmapFactory.decodeStream(open);
            open.close();
            this.f7597n = this.f7593j.getWidth();
            int height = this.f7593j.getHeight();
            this.f7598o = height;
            rect.set(0, 0, this.f7597n, height);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        Paint paint = new Paint();
        this.f7594k = paint;
        paint.setStrokeWidth(2.0f);
        this.f7594k.setColor(-65536);
        this.f7594k.setTextSize(40.0f);
    }

    private RectF p(int i2) {
        float f2 = this.f7597n * 0.5f;
        float f3 = this.f7598o * 0.5f;
        float[] fArr = this.f7574d;
        float m2 = fArr != null ? fArr[i2] : m(this.f7571a.getMinPixels() / 2);
        float f4 = this.f7575e[i2];
        this.f7596m.set(m2 - f2, f4 - f3, m2 + f2, f4 + f3);
        return this.f7596m;
    }

    private String q(int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(i2 >= 10 ? "" : "0");
        sb.append(i2);
        return String.format("tips/ice/ice%s.png", sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(int i2) {
        String q2 = q((i2 - 7) % 16);
        if (q2.equals(this.f7599p)) {
            return;
        }
        this.f7599p = q2;
        try {
            InputStream open = c().open(this.f7599p);
            Bitmap decodeStream = BitmapFactory.decodeStream(open);
            synchronized (this) {
                try {
                    Bitmap bitmap = this.f7593j;
                    if (bitmap != null) {
                        bitmap.recycle();
                        this.f7593j = decodeStream;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            open.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public boolean a(Canvas canvas, long j2, long j3, final int i2, float f2) {
        if (this.f7593j == null || 8 >= i2 || i2 >= 24) {
            return false;
        }
        synchronized (this) {
            canvas.drawBitmap(this.f7593j, this.f7595l, p(i2), this.f7594k);
        }
        this.f7571a.l(new Runnable() { // from class: i.b
            @Override // java.lang.Runnable
            public final void run() {
                TipsIce.this.r(i2);
            }
        });
        return true;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path b() {
        return null;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path f() {
        return null;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path h() {
        if (!this.f7571a.n()) {
            return null;
        }
        float m2 = m(1200.0f);
        float m3 = m(852.0f);
        Path path = new Path();
        path.moveTo(0.0f, m2);
        path.lineTo(42.0f, m2);
        path.cubicTo(43.68f, m2, 44.32f, m3, 46.0f, m3);
        path.lineTo(125.0f, m3);
        return path;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path i() {
        if (this.f7571a.n()) {
            float n2 = n(540.0f);
            float n3 = n(568.0f);
            Path path = new Path();
            path.moveTo(0.0f, n2);
            path.lineTo(42.0f, n2);
            path.cubicTo(43.68f, n2, 44.32f, n3, 46.0f, n3);
            path.lineTo(125.0f, n3);
            return path;
        }
        float n4 = n(1200.0f);
        float n5 = n(849.0f);
        Path path2 = new Path();
        path2.moveTo(0.0f, n4);
        path2.lineTo(42.0f, n4);
        path2.cubicTo(43.68f, n4, 44.32f, n5, 46.0f, n5);
        path2.lineTo(125.0f, n5);
        return path2;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public void k() {
        synchronized (this) {
            try {
                Bitmap bitmap = this.f7593j;
                if (bitmap != null && !bitmap.isRecycled()) {
                    this.f7593j.recycle();
                }
                this.f7593j = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
