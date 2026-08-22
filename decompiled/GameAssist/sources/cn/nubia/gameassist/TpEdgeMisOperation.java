package cn.nubia.gameassist;

import android.content.Context;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.ext.utils.ExtendUtils;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class TpEdgeMisOperation implements RotationMgr.Callback, GameMonitor.Callback {

    /* renamed from: i, reason: collision with root package name */
    private static volatile TpEdgeMisOperation f6092i;

    /* renamed from: c, reason: collision with root package name */
    private final String f6093c = "proc/touchscreen/play_game";

    /* renamed from: h, reason: collision with root package name */
    private int f6094h;

    private TpEdgeMisOperation(Context context) {
        RotationMgr.e(context).c(this);
        SystemMgr.y(context).h(this);
    }

    public static TpEdgeMisOperation a(Context context) {
        if (f6092i == null) {
            synchronized (TpEdgeMisOperation.class) {
                try {
                    if (f6092i == null) {
                        f6092i = new TpEdgeMisOperation(context);
                    }
                } finally {
                }
            }
        }
        return f6092i;
    }

    private void b(String str, String str2) {
        GaLog.e("TpEdgeMisOperation", "noteGameToTp reason=" + str + " value=" + str2);
        ExtendUtils.b("proc/touchscreen/play_game", str2);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        b("onGameStart", "1");
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        b("onGameStop", "0");
    }

    @Override // com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        if (this.f6094h != i2) {
            this.f6094h = i2;
            if (SystemMgr.H()) {
                b("onRotationChanged_" + i2, "1");
            }
        }
    }
}
