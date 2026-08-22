package cn.nubia.gameassist.meditationmode.danmu.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.meditationmode.danmu.BarrageFactory;
import cn.nubia.gameassist.meditationmode.danmu.DanmuNotificationBean;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;
import com.zte.gameassist.common.RotationMgr;

/* loaded from: classes.dex */
public class BarrageBitmapModel extends BarrageModel {
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private float F;
    private String z;

    public BarrageBitmapModel() {
        V();
    }

    private void V() {
    }

    public void U(Canvas canvas) {
        W(canvas, n().d() - ((int) r()), n().e() + n().b().h());
    }

    protected void W(Canvas canvas, int i2, int i3) {
        Drawable e2;
        BarrageLog.a("onDraw");
        BarrageConfig b2 = n().b();
        TextPaint b3 = b2.b().b();
        b3.setColor(Color.parseColor("#ffffff"));
        b3.setAlpha(v().g());
        if (z() && v().n()) {
            v().h().setBounds(i2, i3, (((this.f6675o + i2) - b2.k()) - b2.i()) - b2.a(), o() + i3);
        } else {
            v().h().setBounds(i2, i3, this.f6675o + i2, o() + i3);
        }
        v().h().setAlpha(v().g());
        v().h().draw(canvas);
        if (q() > 0 && (e2 = ContextCompat.e(BarrageFactory.a(), q())) != null) {
            int i4 = this.A;
            e2.setBounds(i2 + i4, this.B + i3, i4 + i2 + b2.a(), this.B + i3 + b2.a());
            e2.setAlpha(v().g());
            e2.draw(canvas);
        }
        Paint.FontMetrics fontMetrics = b3.getFontMetrics();
        float f2 = fontMetrics.bottom;
        float f3 = (((f2 - fontMetrics.top) / 2.0f) - f2) + this.F;
        if (!TextUtils.isEmpty(this.z)) {
            float f4 = this.A;
            if (q() > 0) {
                f4 += b2.a() + b2.l();
            }
            int i5 = this.C;
            if (i5 < this.D) {
                this.z = TextUtils.ellipsize(this.z, b3, i5, TextUtils.TruncateAt.END).toString();
            }
            canvas.drawText(this.z, i2 + f4, i3 + f3, b3);
        }
        if (z()) {
            if (v().n()) {
                Drawable e3 = ContextCompat.e(BarrageFactory.a(), R.drawable.bg_danmu_klxq_reply_bg);
                if (e3 != null) {
                    int i6 = ((this.f6675o - b2.i()) - b2.a()) + i2;
                    int o2 = ((o() - b2.j()) / 2) + i3;
                    e3.setBounds(i6, o2, b2.j() + i6, b2.j() + o2);
                    e3.setAlpha(v().g());
                    e3.draw(canvas);
                }
            } else {
                b3.setColor(Color.parseColor("#33ffffff"));
                b3.setStrokeWidth(b2.f());
                b3.setStyle(Paint.Style.FILL);
                float a2 = i2 + ((((this.f6675o - this.E) - b2.a()) - b2.f()) - b2.i());
                float f5 = i3 + f3 + (fontMetrics.bottom / 2.0f);
                canvas.drawLine(a2, f5 - b2.e(), a2, f5, b3);
            }
            Drawable e4 = ContextCompat.e(BarrageFactory.a(), R.drawable.danmu_reply);
            if (e4 != null) {
                int i7 = i2 + (v().n() ? ((this.f6675o - b2.i()) - b2.a()) + ((b2.j() - b2.a()) / 2) : (this.f6675o - this.E) - b2.a());
                e4.setBounds(i7, this.B + i3, b2.a() + i7, i3 + this.B + b2.a());
                e4.setAlpha(v().g());
                e4.draw(canvas);
            }
        }
        BarrageLog.e();
    }

    protected void X() {
        if (TextUtils.isEmpty(this.z)) {
            return;
        }
        BarrageLog.a("onMeasure");
        BarrageConfig b2 = n().b();
        TextPaint b3 = b2.b().b();
        b3.setTextAlign(Paint.Align.LEFT);
        b3.setColor(Color.parseColor("#ffffff"));
        b3.setTextSize(v().m());
        b3.setAlpha(v().g());
        if (v().i().bottom - v().i().top < b2.a()) {
            Q(((v().i().top + b2.a()) + v().h().getIntrinsicHeight()) - v().i().bottom);
        } else {
            Q(v().h().getIntrinsicHeight());
        }
        this.F = v().i().top + (v().i().height() / 2.0f);
        this.A = v().i().left;
        this.B = (int) (this.F - (b2.a() / 2));
        this.E = v().h().getIntrinsicWidth() - v().i().right;
        int measureText = (int) (b3.measureText(this.z) + 0.5f);
        this.D = measureText;
        this.C = Math.min(measureText, v().m() * (RotationMgr.j() ? v().k() : v().l()));
        int i2 = this.A;
        if (q() > 0) {
            i2 += b2.a() + b2.l();
        }
        int i3 = i2 + this.C + this.E;
        if (z()) {
            i3 += b2.k() + b2.f() + b2.a() + b2.i();
        }
        T(i3);
        R(n().e() + b2.h());
        M(true);
        BarrageLog.e();
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.DanmuNotificationBean
    public void k(DanmuNotificationBean danmuNotificationBean) {
        super.k(danmuNotificationBean);
        if (TextUtils.isEmpty(danmuNotificationBean.g())) {
            this.z = danmuNotificationBean.b();
            return;
        }
        this.z = danmuNotificationBean.g() + ": " + danmuNotificationBean.b();
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.model.BarrageModel
    public boolean m() {
        X();
        return true;
    }
}
