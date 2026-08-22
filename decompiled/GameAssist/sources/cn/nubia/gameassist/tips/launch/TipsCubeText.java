package cn.nubia.gameassist.tips.launch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.LocaleList;
import cn.nubia.gameassist.tips.GameAssistLaunchTips;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class TipsCubeText extends TipsBase {

    /* renamed from: h, reason: collision with root package name */
    private Bitmap f7585h;

    /* renamed from: i, reason: collision with root package name */
    private Paint f7586i;

    /* renamed from: j, reason: collision with root package name */
    private final Rect f7587j;

    /* renamed from: k, reason: collision with root package name */
    private final RectF f7588k;

    /* renamed from: l, reason: collision with root package name */
    private int f7589l;

    /* renamed from: m, reason: collision with root package name */
    private int f7590m;

    public TipsCubeText(GameAssistLaunchTips gameAssistLaunchTips, Context context) {
        super(gameAssistLaunchTips, context, context.getResources());
        Rect rect = new Rect();
        this.f7587j = rect;
        this.f7588k = new RectF();
        try {
            LocaleList locales = context.getResources().getConfiguration().getLocales();
            InputStream open = c().open(locales.size() > 0 && locales.get(0).getLanguage().toLowerCase().equals("zh") ? "tips/CUBE_TEXT_ZH.png" : "tips/CUBE_TEXT_EN.png");
            this.f7585h = BitmapFactory.decodeStream(open);
            open.close();
            this.f7589l = this.f7585h.getWidth();
            int height = this.f7585h.getHeight();
            this.f7590m = height;
            rect.set(0, 0, this.f7589l, height);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        Paint paint = new Paint();
        this.f7586i = paint;
        paint.setStrokeWidth(2.0f);
        this.f7586i.setColor(-65536);
        this.f7586i.setTextSize(40.0f);
    }

    private RectF o(int i2) {
        float minPixels;
        float f2 = this.f7589l * (this.f7571a.n() ? 1.0f : 0.89f);
        float f3 = f2 * 0.5f;
        float f4 = this.f7590m * (this.f7571a.n() ? 1.0f : 0.89f) * 0.5f;
        float[] fArr = this.f7574d;
        if (fArr != null) {
            minPixels = fArr[i2];
            f3 = 336.0f;
        } else {
            minPixels = this.f7571a.getMinPixels() / 2;
        }
        float m2 = m(minPixels - f3);
        float f5 = f2 + m2;
        float[] fArr2 = this.f7575e;
        float n2 = n(fArr2 == null ? 375.0f : fArr2[i2]);
        this.f7588k.set(m2, n2 - f4, f5, n2 + f4);
        return this.f7588k;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public boolean a(Canvas canvas, long j2, long j3, int i2, float f2) {
        if (this.f7585h == null || 42 > i2) {
            return true;
        }
        RectF o2 = o(i2);
        float f3 = this.f7577g[i2];
        int save = canvas.save();
        this.f7586i.setAlpha((int) this.f7576f[i2]);
        canvas.scale(f3, f3, this.f7571a.getFixWidth() / 2, this.f7571a.getFixHeight() / 2);
        canvas.drawBitmap(this.f7585h, this.f7587j, o2, this.f7586i);
        canvas.restoreToCount(save);
        return true;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path b() {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(53.0f, 0.0f);
        path.cubicTo(62.5f, 0.0f, 53.95f, 255.0f, 72.0f, 255.0f);
        path.lineTo(113.0f, 255.0f);
        path.cubicTo(119.0f, 255.0f, 113.6f, 51.0f, 125.0f, 51.0f);
        return path;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path f() {
        Path path = new Path();
        path.moveTo(0.0f, 1.0f);
        path.lineTo(113.0f, 1.0f);
        path.cubicTo(114.5f, 1.0f, 113.15f, 1.1f, 116.0f, 1.1f);
        path.cubicTo(120.5f, 1.1f, 116.45f, 0.72f, 125.0f, 0.72f);
        return path;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path h() {
        if (!this.f7571a.n()) {
            return null;
        }
        Path path = new Path();
        float n2 = n(5.0f);
        float m2 = m(1248.0f + n2);
        float m3 = m(n2 + 1566.0f);
        path.moveTo(0.0f, m2);
        path.lineTo(53.0f, m2);
        path.cubicTo(56.78f, m2, 58.22f, m3, 62.0f, m3);
        path.lineTo(125.0f, m3);
        return path;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path i() {
        if (this.f7571a.n()) {
            return null;
        }
        Path path = new Path();
        float n2 = n(1120.0f);
        float n3 = n(1412.0f);
        path.moveTo(0.0f, n2);
        path.lineTo(53.0f, n2);
        path.cubicTo(56.78f, n2, 58.22f, n3, 62.0f, n3);
        path.lineTo(125.0f, n3);
        return path;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public void k() {
        Bitmap bitmap = this.f7585h;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.f7585h.recycle();
        this.f7585h = null;
    }
}
