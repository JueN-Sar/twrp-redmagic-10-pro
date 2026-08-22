package cn.nubia.gameassist.dessert.policy.clean;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.policy.MemoryInfoController;
import cn.nubia.gameassist.dessert.policy.clean.NubiaSlideView;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.performance.mindsync.MindSyncManager;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/* loaded from: classes.dex */
public class CleanAnimationController {

    /* renamed from: g, reason: collision with root package name */
    private static volatile CleanAnimationController f6331g;

    /* renamed from: a, reason: collision with root package name */
    private final Context f6332a;

    /* renamed from: b, reason: collision with root package name */
    private final MemoryInfoController f6333b;

    /* renamed from: c, reason: collision with root package name */
    private final ActivityManager.MemoryInfo f6334c = new ActivityManager.MemoryInfo();

    /* renamed from: d, reason: collision with root package name */
    private NubiaSlideView f6335d = null;

    /* renamed from: f, reason: collision with root package name */
    private final List f6337f = new ArrayList<String>() { // from class: cn.nubia.gameassist.dessert.policy.clean.CleanAnimationController.1
        {
            add("cn.nubia.gameassist");
            add("cn.nubia.keymapcenter");
        }
    };

    /* renamed from: e, reason: collision with root package name */
    private final Handler f6336e = new Handler(ThreadManager.c().b());

    private CleanAnimationController(Context context) {
        this.f6332a = context;
        this.f6333b = new MemoryInfoController(context);
    }

    public static CleanAnimationController d(Context context) {
        if (f6331g == null) {
            synchronized (CleanAnimationController.class) {
                try {
                    if (f6331g == null) {
                        f6331g = new CleanAnimationController(context);
                    }
                } finally {
                }
            }
        }
        return f6331g;
    }

    private String e() {
        String string = Settings.Global.getString(this.f6332a.getContentResolver(), "app_mirror_list");
        if (string == null || string.length() <= 1) {
            return null;
        }
        return string.substring(0, string.indexOf("/"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        NubiaSlideView nubiaSlideView = this.f6335d;
        if (nubiaSlideView == null) {
            NubiaSlideView nubiaSlideView2 = (NubiaSlideView) InflaterHelper.f(R.layout.slide, null);
            this.f6335d = nubiaSlideView2;
            nubiaSlideView2.setGetMemFunction(new Supplier() { // from class: cn.nubia.gameassist.dessert.policy.clean.b
                @Override // java.util.function.Supplier
                public final Object get() {
                    return CleanAnimationController.this.c();
                }
            });
        } else if (nubiaSlideView.v()) {
            GaLog.a("CleanAnimationController", "startClean,isAnimating");
            return;
        }
        String e2 = e();
        if (e2 != null) {
            this.f6337f.add(e2);
        }
        g(this.f6332a, this.f6337f);
        this.f6335d.z(new NubiaSlideView.SlideViewCallback() { // from class: cn.nubia.gameassist.dessert.policy.clean.CleanAnimationController.2
            @Override // cn.nubia.gameassist.dessert.policy.clean.NubiaSlideView.SlideViewCallback
            public void a() {
                CleanAnimationController.this.f6335d = null;
            }

            @Override // cn.nubia.gameassist.dessert.policy.clean.NubiaSlideView.SlideViewCallback
            public void b() {
            }
        });
    }

    private void g(Context context, List list) {
        try {
            MindSyncManager mindSyncManager = MindSyncManager.getInstance(context);
            if (mindSyncManager != null) {
                mindSyncManager.startBgAppCleanupFromGameMode(list);
                GaLog.a("CleanAnimationController", "start clean exclude pkg " + list);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String c() {
        return this.f6333b.b(this.f6334c.availMem);
    }

    @VisibleForTesting
    public boolean cleanViewAnimating() {
        NubiaSlideView nubiaSlideView = this.f6335d;
        return nubiaSlideView != null && nubiaSlideView.v();
    }

    public void h() {
        this.f6333b.c(this.f6334c);
        this.f6336e.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.clean.a
            @Override // java.lang.Runnable
            public final void run() {
                CleanAnimationController.this.f();
            }
        });
    }
}
