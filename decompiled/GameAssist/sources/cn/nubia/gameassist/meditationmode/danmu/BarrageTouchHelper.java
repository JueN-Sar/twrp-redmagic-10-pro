package cn.nubia.gameassist.meditationmode.danmu;

import android.content.Context;
import android.view.MotionEvent;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.meditationmode.danmu.dispatch.BarrageConsumer;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageModel;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;
import com.zte.gameassist.input.FullScreenInputMonitor;
import com.zte.gameassist.input.InterfaceEventListener;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class BarrageTouchHelper implements InterfaceEventListener {

    /* renamed from: n, reason: collision with root package name */
    private static final String f6592n = "BarrageTouchHelper";

    /* renamed from: c, reason: collision with root package name */
    private Context f6593c;

    /* renamed from: h, reason: collision with root package name */
    private OnItemBarrageClickListener f6594h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f6595i = false;

    /* renamed from: j, reason: collision with root package name */
    private boolean f6596j = false;

    /* renamed from: k, reason: collision with root package name */
    private BarrageConsumer f6597k;

    /* renamed from: l, reason: collision with root package name */
    private BarrageController f6598l;

    /* renamed from: m, reason: collision with root package name */
    int f6599m;

    public interface OnItemBarrageClickListener {
        void b(BarrageModel barrageModel);
    }

    public BarrageTouchHelper(Context context, BarrageController barrageController) {
        this.f6593c = context;
        this.f6598l = barrageController;
        this.f6597k = barrageController.g();
    }

    private int a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionMasked() == 0 ? 0 : motionEvent.getActionIndex();
        if (actionIndex == -1) {
            return 0;
        }
        return actionIndex;
    }

    private void b(MotionEvent motionEvent) {
        if (this.f6598l.h() == null || !this.f6598l.h().o()) {
            BarrageLog.b(f6592n, "getBarrageStyle is null or not support reply !");
            return;
        }
        ArrayList f2 = this.f6597k.f();
        if (f2.isEmpty()) {
            BarrageLog.b(f6592n, "touchQueue is empty !");
            return;
        }
        int i2 = GameAssistApplication.i().U()[0];
        Iterator it = f2.iterator();
        while (it.hasNext()) {
            BarrageModel barrageModel = (BarrageModel) it.next();
            int a2 = a(motionEvent);
            int x = (int) motionEvent.getX(a2);
            int y = (int) motionEvent.getY(a2);
            if (barrageModel.J(i2 - x, y - this.f6599m)) {
                OnItemBarrageClickListener onItemBarrageClickListener = this.f6594h;
                if (onItemBarrageClickListener != null) {
                    onItemBarrageClickListener.b(barrageModel);
                    BarrageManager.r().R();
                    return;
                }
                return;
            }
            BarrageLog.b(f6592n, "not in touch rect because of isSupportReply = " + this.f6598l.h().o() + " displayWidth:" + i2 + " downX = " + x + " downY = " + y + " mViewLocationY:" + this.f6599m + " offsetX:" + barrageModel.r() + " offsetY:" + barrageModel.u() + " width:" + barrageModel.w() + " height:" + barrageModel.o());
        }
    }

    public void c() {
        FullScreenInputMonitor.e().c(this);
    }

    public void d(OnItemBarrageClickListener onItemBarrageClickListener) {
        this.f6594h = onItemBarrageClickListener;
    }

    @Override // com.zte.gameassist.input.InterfaceEventListener
    public void f(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f6595i = true;
            b(motionEvent);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f6595i = false;
            if (this.f6596j) {
                this.f6596j = false;
                h();
            }
        }
    }

    public void g(int i2) {
        this.f6599m = i2;
    }

    public void h() {
        if (!this.f6595i) {
            FullScreenInputMonitor.e().i(this);
        } else {
            BarrageLog.b(f6592n, "mNeedUpRelease");
            this.f6596j = true;
        }
    }

    @Override // com.zte.gameassist.input.InterfaceEventListener
    public void onDispose() {
        h();
    }
}
