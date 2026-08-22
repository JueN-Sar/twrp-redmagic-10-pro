package cn.nubia.gameassist.meditationmode.danmu;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Choreographer;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;

/* loaded from: classes.dex */
public class BarrageHandler extends Handler {

    /* renamed from: c, reason: collision with root package name */
    private static final String f6572c = "BarrageHandler";

    /* renamed from: a, reason: collision with root package name */
    private final BarrageController f6573a;

    /* renamed from: b, reason: collision with root package name */
    private final Choreographer.FrameCallback f6574b;

    public BarrageHandler(Looper looper, BarrageController barrageController) {
        super(looper);
        this.f6574b = new Choreographer.FrameCallback() { // from class: cn.nubia.gameassist.meditationmode.danmu.a
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                BarrageHandler.this.b(j2);
            }
        };
        this.f6573a = barrageController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(long j2) {
        sendEmptyMessage(1);
    }

    private void c() {
        this.f6573a.n();
    }

    private void f() {
        Choreographer.getInstance().postFrameCallback(this.f6574b);
        c();
        removeMessages(1);
    }

    public void d() {
        BarrageLog.b(f6572c, "barrage handler looper start");
        sendEmptyMessage(1);
    }

    public void e() {
        removeMessages(1);
        Choreographer.getInstance().removeFrameCallback(this.f6574b);
        BarrageLog.b(f6572c, "barrage handler looper finish");
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 1) {
            f();
        }
    }
}
