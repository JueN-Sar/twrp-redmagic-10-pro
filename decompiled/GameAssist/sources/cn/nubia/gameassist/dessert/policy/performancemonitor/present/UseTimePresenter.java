package cn.nubia.gameassist.dessert.policy.performancemonitor.present;

import android.content.Context;
import android.os.Handler;
import cn.nubia.gameassist.dessert.policy.performancemonitor.model.GameAppInfo;
import cn.nubia.gameassist.dessert.policy.performancemonitor.model.GameDurationInfo;
import cn.nubia.gameassist.dessert.policy.performancemonitor.model.GameDurationModeImpl;
import com.zte.gameassist.utils.GaLog;
import java.util.List;

/* loaded from: classes.dex */
public class UseTimePresenter {

    /* renamed from: d, reason: collision with root package name */
    private static volatile UseTimePresenter f6463d = null;

    /* renamed from: e, reason: collision with root package name */
    private static boolean f6464e = false;

    /* renamed from: a, reason: collision with root package name */
    private Context f6465a;

    /* renamed from: b, reason: collision with root package name */
    private Handler f6466b;

    /* renamed from: c, reason: collision with root package name */
    private GameDurationModeImpl f6467c;

    private UseTimePresenter(Context context) {
        this.f6465a = context;
        this.f6467c = GameDurationModeImpl.getInstance(context, this.f6466b);
    }

    public static UseTimePresenter getInstance(Context context) {
        if (f6463d == null) {
            synchronized (UseTimePresenter.class) {
                try {
                    if (f6463d == null) {
                        f6463d = new UseTimePresenter(context);
                    }
                } finally {
                }
            }
        }
        return f6463d;
    }

    public void scheduleGetUseTime(final LoadDataCallback loadDataCallback) {
        GaLog.e("PerformanceMonitor-UseTimePresenter", "scheduleGetUseTime");
        if (f6464e) {
            return;
        }
        f6464e = true;
        this.f6467c.startLoadGameTimeParms(new PresenterCallback(this) { // from class: cn.nubia.gameassist.dessert.policy.performancemonitor.present.UseTimePresenter.1
            @Override // cn.nubia.gameassist.dessert.policy.performancemonitor.present.PresenterCallback
            public void onFaied() {
                GaLog.b("PerformanceMonitor-UseTimePresenter", "onFaied");
                UseTimePresenter.f6464e = false;
            }

            @Override // cn.nubia.gameassist.dessert.policy.performancemonitor.present.PresenterCallback
            public void onLoaded(GameDurationInfo[] gameDurationInfoArr, List<List<GameAppInfo>> list) {
                GaLog.e("PerformanceMonitor-UseTimePresenter", "onLoaded");
                loadDataCallback.loadedData(gameDurationInfoArr, list);
                UseTimePresenter.f6464e = false;
            }
        });
    }
}
