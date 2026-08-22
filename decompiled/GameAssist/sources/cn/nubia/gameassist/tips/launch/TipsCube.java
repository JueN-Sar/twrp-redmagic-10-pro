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
import cn.nubia.gameassist.tips.launch.TipsCube;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class TipsCube extends TipsBase {

    /* renamed from: h, reason: collision with root package name */
    private Bitmap f7578h;

    /* renamed from: i, reason: collision with root package name */
    private Paint f7579i;

    /* renamed from: j, reason: collision with root package name */
    private String f7580j;

    /* renamed from: k, reason: collision with root package name */
    private final Rect f7581k;

    /* renamed from: l, reason: collision with root package name */
    private final RectF f7582l;

    /* renamed from: m, reason: collision with root package name */
    private int f7583m;

    /* renamed from: n, reason: collision with root package name */
    private int f7584n;

    public TipsCube(GameAssistLaunchTips gameAssistLaunchTips, Context context) {
        super(gameAssistLaunchTips, context, context.getResources());
        Rect rect = new Rect();
        this.f7581k = rect;
        this.f7582l = new RectF();
        try {
            this.f7580j = q(0);
            InputStream open = c().open(this.f7580j);
            this.f7578h = BitmapFactory.decodeStream(open);
            open.close();
            this.f7583m = this.f7578h.getWidth();
            int height = this.f7578h.getHeight();
            this.f7584n = height;
            rect.set(0, 0, this.f7583m, height);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        Paint paint = new Paint();
        this.f7579i = paint;
        paint.setStrokeWidth(2.0f);
        this.f7579i.setColor(-65536);
        this.f7579i.setTextSize(40.0f);
    }

    private RectF p(int i2) {
        float f2 = this.f7583m * 0.5f;
        float f3 = this.f7584n * 0.5f;
        float[] fArr = this.f7574d;
        float m2 = m(fArr != null ? fArr[i2] : this.f7571a.getMinPixels() / 2);
        float f4 = this.f7575e[i2];
        this.f7582l.set(m2 - f2, f4 - f3, m2 + f2, f4 + f3);
        return this.f7582l;
    }

    private String q(int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(i2 >= 100 ? "" : i2 >= 10 ? "0" : "00");
        sb.append(i2);
        return String.format("tips/cube/cube%s.png", sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(int i2) {
        String q2 = q((i2 + 1) % 141);
        if (q2.equals(this.f7580j)) {
            return;
        }
        this.f7580j = q2;
        try {
            InputStream open = c().open(this.f7580j);
            Bitmap decodeStream = BitmapFactory.decodeStream(open);
            synchronized (this) {
                try {
                    Bitmap bitmap = this.f7578h;
                    if (bitmap != null) {
                        bitmap.recycle();
                        this.f7578h = decodeStream;
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
    public boolean a(Canvas canvas, long j2, long j3, int i2, float f2) {
        final int i3 = (i2 * 141) / 125;
        if (this.f7578h == null) {
            return false;
        }
        synchronized (this) {
            canvas.drawBitmap(this.f7578h, this.f7581k, p(i2 % 125), this.f7579i);
        }
        this.f7571a.l(new Runnable() { // from class: i.a
            @Override // java.lang.Runnable
            public final void run() {
                TipsCube.this.r(i3);
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
        path.lineTo(53.0f, m2);
        path.cubicTo(54.26f, m2, 54.74f, m3, 56.0f, m3);
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
            path.lineTo(53.0f, n2);
            path.cubicTo(54.26f, n2, 54.74f, n3, 56.0f, n3);
            path.lineTo(125.0f, n3);
            return path;
        }
        float n4 = n(1200.0f);
        float n5 = n(1099.0f);
        Path path2 = new Path();
        path2.moveTo(0.0f, n4);
        path2.lineTo(53.0f, n4);
        path2.cubicTo(54.26f, n4, 54.74f, n5, 56.0f, n5);
        path2.lineTo(125.0f, n5);
        return path2;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public void k() {
        synchronized (this) {
            try {
                Bitmap bitmap = this.f7578h;
                if (bitmap != null && !bitmap.isRecycled()) {
                    this.f7578h.recycle();
                }
                this.f7578h = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
