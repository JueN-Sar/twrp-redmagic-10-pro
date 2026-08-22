package cn.nubia.gameassist.tips.launch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import cn.nubia.gameassist.tips.launch.TipsPacticles;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class TipsPacticles extends TipsBase {

    /* renamed from: h, reason: collision with root package name */
    private Bitmap f7613h;

    /* renamed from: i, reason: collision with root package name */
    private Paint f7614i;

    /* renamed from: j, reason: collision with root package name */
    private final Rect f7615j;

    /* renamed from: k, reason: collision with root package name */
    private final RectF f7616k;

    /* renamed from: l, reason: collision with root package name */
    private int f7617l;

    /* renamed from: m, reason: collision with root package name */
    private int f7618m;

    /* renamed from: n, reason: collision with root package name */
    private String f7619n;

    private RectF p(int i2) {
        float f2 = this.f7617l * 0.5f;
        float f3 = this.f7618m * 0.5f;
        float[] fArr = this.f7574d;
        float minPixels = fArr != null ? fArr[i2] : this.f7571a.getMinPixels() / 2;
        float f4 = this.f7575e[i2];
        this.f7616k.set(minPixels - f2, f4 - f3, minPixels + f2, f4 + f3);
        return this.f7616k;
    }

    private String q(int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(i2 >= 10 ? "" : "0");
        sb.append(i2);
        return String.format("tips/pacticles/pacticles%s.png", sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(int i2) {
        String q2 = q((i2 - 54) % 70);
        if (q2.equals(this.f7619n)) {
            return;
        }
        this.f7619n = q2;
        try {
            InputStream open = c().open(this.f7619n);
            Bitmap decodeStream = BitmapFactory.decodeStream(open);
            synchronized (this) {
                try {
                    Bitmap bitmap = this.f7613h;
                    if (bitmap != null) {
                        bitmap.recycle();
                        this.f7613h = decodeStream;
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
        if (this.f7613h == null || 55 >= i2 || i2 >= 125) {
            return false;
        }
        synchronized (this) {
            canvas.drawBitmap(this.f7613h, this.f7615j, p(i2), this.f7614i);
            canvas.drawBitmap(this.f7613h, this.f7615j, p(i2), this.f7614i);
        }
        this.f7571a.l(new Runnable() { // from class: i.d
            @Override // java.lang.Runnable
            public final void run() {
                TipsPacticles.this.r(i2);
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
        Path path = new Path();
        float m2 = m(1200.0f);
        float m3 = m(852.0f);
        path.moveTo(0.0f, m2);
        path.lineTo(42.0f, m2);
        path.cubicTo(43.68f, m2, 44.32f, 852.0f, 46.0f, m3);
        path.lineTo(125.0f, m3);
        return path;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path i() {
        if (this.f7571a.n()) {
            float n2 = n(376);
            Path path = new Path();
            path.moveTo(0.0f, n2);
            path.lineTo(125.0f, n2);
            return path;
        }
        float n3 = n((-70) + 735.5f + 250.0f);
        Path path2 = new Path();
        path2.moveTo(0.0f, n3);
        path2.lineTo(125.0f, n3);
        return path2;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public void k() {
        Bitmap bitmap = this.f7613h;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.f7613h.recycle();
        this.f7613h = null;
    }
}
