package cn.nubia.gameassist.tips.launch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.tips.GameAssistLaunchTips;

/* loaded from: classes.dex */
public class TipsBackground extends TipsBase {

    /* renamed from: h, reason: collision with root package name */
    private final Paint f7569h;

    /* renamed from: i, reason: collision with root package name */
    private Bitmap f7570i;

    public TipsBackground(GameAssistLaunchTips gameAssistLaunchTips, Context context) {
        super(gameAssistLaunchTips, context, context.getResources());
        Paint paint = new Paint();
        this.f7569h = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        this.f7570i = BitmapFactory.decodeResource(gameAssistLaunchTips.getResources(), R.drawable.black_color);
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public boolean a(Canvas canvas, long j2, long j3, int i2, float f2) {
        Bitmap bitmap = this.f7570i;
        if (bitmap == null || bitmap.isRecycled()) {
            return false;
        }
        this.f7569h.setAlpha(((int) this.f7576f[i2]) & 255);
        Rect rect = new Rect(0, 0, this.f7570i.getWidth(), this.f7570i.getHeight());
        GameAssistLaunchTips gameAssistLaunchTips = this.f7571a;
        Point point = gameAssistLaunchTips.mTranslate;
        int i3 = -point.x;
        int i4 = -point.y;
        int width = gameAssistLaunchTips.getWidth();
        GameAssistLaunchTips gameAssistLaunchTips2 = this.f7571a;
        canvas.drawBitmap(this.f7570i, rect, new Rect(i3, i4, width - gameAssistLaunchTips2.mTranslate.x, gameAssistLaunchTips2.getHeight() - this.f7571a.mTranslate.y), this.f7569h);
        return true;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path b() {
        Path path = new Path();
        float f2 = 229;
        path.moveTo(0.0f, f2);
        path.lineTo(29.0f, f2);
        float f3 = (int) (229 * 0.67d);
        path.cubicTo(69.32f, f2, 125.0f, f3, 125.0f, f3);
        path.lineTo(125.0f, f3);
        return path;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path f() {
        return null;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path h() {
        return null;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path i() {
        return null;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public void k() {
        Bitmap bitmap = this.f7570i;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f7570i.recycle();
        }
        this.f7570i = null;
    }
}
