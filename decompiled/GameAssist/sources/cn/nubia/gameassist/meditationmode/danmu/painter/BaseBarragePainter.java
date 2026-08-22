package cn.nubia.gameassist.meditationmode.danmu.painter;

import android.text.TextPaint;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageModel;

/* loaded from: classes.dex */
public abstract class BaseBarragePainter {

    /* renamed from: a, reason: collision with root package name */
    private final TextPaint f6707a = new TextPaint(1);

    /* renamed from: b, reason: collision with root package name */
    protected int f6708b;

    /* renamed from: c, reason: collision with root package name */
    protected int f6709c;

    public abstract void a(Object obj, BarrageModel barrageModel);

    public TextPaint b() {
        return this.f6707a;
    }

    protected void c(BarrageModel barrageModel) {
        if (barrageModel.r() > this.f6708b + barrageModel.w()) {
            barrageModel.M(false);
        } else {
            barrageModel.G();
        }
    }

    protected void d(BarrageModel barrageModel) {
        if (barrageModel.x()) {
            c(barrageModel);
        }
    }

    public void e(int i2, int i3) {
        this.f6708b = i2;
        this.f6709c = i3;
    }

    public void f(Object obj) {
    }
}
