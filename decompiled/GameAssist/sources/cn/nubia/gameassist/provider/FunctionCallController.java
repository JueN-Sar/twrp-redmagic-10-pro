package cn.nubia.gameassist.provider;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.provider.FunctionCallController;
import cn.nubia.gameassist.utils.ToastUtil;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class FunctionCallController {

    /* renamed from: d, reason: collision with root package name */
    private static volatile FunctionCallController f7377d;

    /* renamed from: a, reason: collision with root package name */
    final ConcurrentHashMap f7378a = new ConcurrentHashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Handler f7379b = new Handler(Looper.getMainLooper());

    /* renamed from: c, reason: collision with root package name */
    private Context f7380c;

    public interface Callback {
        void j(String... strArr);
    }

    private FunctionCallController(Context context) {
        this.f7380c = context;
    }

    public static FunctionCallController c(Context context) {
        if (f7377d == null) {
            synchronized (FunctionCallController.class) {
                try {
                    if (f7377d == null) {
                        f7377d = new FunctionCallController(context);
                    }
                } finally {
                }
            }
        }
        return f7377d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(String str, String str2, String str3) {
        if (!SystemMgr.H()) {
            ToastUtil.a(this.f7380c.getString(R.string.toast_unsupport_app));
            return;
        }
        Callback callback = (Callback) this.f7378a.get(str);
        if (callback != null) {
            try {
                callback.j(str2, str3);
            } catch (Exception e2) {
                GaLog.c("FunctionCallController", "handleData: callback is null!", e2);
            }
        }
    }

    public void b(String str, Callback callback) {
        if (this.f7378a.containsKey(str)) {
            return;
        }
        this.f7378a.put(str, callback);
    }

    public void d(final String str, final String str2, final String str3) {
        this.f7379b.post(new Runnable() { // from class: g.a
            @Override // java.lang.Runnable
            public final void run() {
                FunctionCallController.this.e(str, str2, str3);
            }
        });
    }

    public void f(String str) {
        this.f7378a.remove(str);
    }
}
