package cn.nubia.gameassist.fold;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import cn.nubia.gameassist.GameAssistApplication;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class FoldBigMgr implements FoldMgr.Callback, GameMonitor.Callback {

    /* renamed from: j, reason: collision with root package name */
    private static final String f6505j = "FoldBigMgr";

    /* renamed from: k, reason: collision with root package name */
    private static volatile FoldBigMgr f6506k;

    /* renamed from: i, reason: collision with root package name */
    private FoldBigDialog f6509i;

    /* renamed from: h, reason: collision with root package name */
    private Handler f6508h = new Handler(Looper.getMainLooper());

    /* renamed from: c, reason: collision with root package name */
    private Context f6507c = GameAssistApplication.j();

    private FoldBigMgr() {
        d();
    }

    public static FoldBigMgr c() {
        if (f6506k == null) {
            synchronized (FoldBigMgr.class) {
                try {
                    if (f6506k == null) {
                        f6506k = new FoldBigMgr();
                    }
                } finally {
                }
            }
        }
        return f6506k;
    }

    public void d() {
        if (ZteFeature.isSupportFoldBig()) {
            this.f6509i = new FoldBigDialog(this.f6507c);
            FoldMgr.c().a(this);
            SystemMgr.y(this.f6507c).h(this);
        }
    }

    public void e() {
        FoldBigDialog foldBigDialog = this.f6509i;
        if (foldBigDialog != null) {
            foldBigDialog.d();
        }
    }

    @Override // com.zte.gameassist.common.FoldMgr.Callback
    public void onDisplayInUseStateChanged(int i2) {
        FoldBigDialog foldBigDialog;
        GaLog.e(f6505j, "onDisplayInUseStateChanged isGameScene=" + SystemMgr.H());
        if (!SystemMgr.H() || (foldBigDialog = this.f6509i) == null) {
            return;
        }
        foldBigDialog.d();
        this.f6508h.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.fold.FoldBigMgr.1
            @Override // java.lang.Runnable
            public void run() {
                GaLog.e(FoldBigMgr.f6505j, "onDisplayInUseStateChanged222 isGameScene=" + SystemMgr.H());
                FoldBigMgr.this.f6509i.g(SystemMgr.v());
            }
        }, 1000L);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        FoldBigDialog foldBigDialog = this.f6509i;
        if (foldBigDialog != null) {
            foldBigDialog.d();
        }
    }
}
