package cn.nubia.gameassist.meditationmode.danmu.painter;

import android.graphics.Canvas;
import android.graphics.Paint;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageBitmapModel;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageModel;

/* loaded from: classes.dex */
public class BarragePainter extends BaseBarragePainter {

    /* renamed from: d, reason: collision with root package name */
    private final Paint f6706d;

    public BarragePainter() {
        Paint paint = new Paint();
        this.f6706d = paint;
        paint.setFlags(3);
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.painter.BaseBarragePainter
    public void a(Object obj, BarrageModel barrageModel) {
        d(barrageModel);
        ((BarrageBitmapModel) barrageModel).U((Canvas) obj);
    }
}
