package cn.nubia.gameassist.dessert.policy.fan;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.R;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.ThreadManager;

/* loaded from: classes.dex */
public class FanViewAnimationController {

    /* renamed from: c, reason: collision with root package name */
    private static volatile FanViewAnimationController f6359c;

    /* renamed from: a, reason: collision with root package name */
    private Handler f6360a = new Handler(ThreadManager.c().b());

    /* renamed from: b, reason: collision with root package name */
    private NubiaFanView f6361b;

    private FanViewAnimationController() {
    }

    public static FanViewAnimationController b(Context context) {
        if (f6359c == null) {
            synchronized (FanViewAnimationController.class) {
                try {
                    if (f6359c == null) {
                        f6359c = new FanViewAnimationController();
                    }
                } finally {
                }
            }
        }
        return f6359c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c() {
        NubiaFanView nubiaFanView = (NubiaFanView) InflaterHelper.f(R.layout.fan, null);
        this.f6361b = nubiaFanView;
        nubiaFanView.i();
    }

    public void d(Context context) {
        this.f6360a.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.fan.a
            @Override // java.lang.Runnable
            public final void run() {
                FanViewAnimationController.this.c();
            }
        });
    }

    @VisibleForTesting
    public boolean fanViewAnimating() {
        NubiaFanView nubiaFanView = this.f6361b;
        return nubiaFanView != null && nubiaFanView.g();
    }
}
