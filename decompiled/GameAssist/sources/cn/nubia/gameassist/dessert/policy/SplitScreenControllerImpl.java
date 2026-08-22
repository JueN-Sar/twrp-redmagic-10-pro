package cn.nubia.gameassist.dessert.policy;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.dessert.policy.SplitScreenController;
import com.zte.gameassist.common.ObserverManager;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public final class SplitScreenControllerImpl implements SplitScreenController, ObserverManager.SettingCallback {

    /* renamed from: c, reason: collision with root package name */
    private final Context f6299c;

    /* renamed from: h, reason: collision with root package name */
    private final CopyOnWriteArrayList f6300h = new CopyOnWriteArrayList();

    public SplitScreenControllerImpl(Context context) {
        this.f6299c = context;
        c(true);
    }

    private void a() {
        Iterator it = this.f6300h.iterator();
        while (it.hasNext()) {
            b((SplitScreenController.SplitScreenControllerCallback) it.next());
        }
    }

    private void b(SplitScreenController.SplitScreenControllerCallback splitScreenControllerCallback) {
        splitScreenControllerCallback.a(o());
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            ObserverManager.c().b(this.f6299c, Settings.Global.getUriFor("navigation_hidden"), this);
            ObserverManager.c().b(this.f6299c, Settings.System.getUriFor("ss_multi_window_enabled"), this);
        }
    }

    @Override // cn.nubia.gameassist.dessert.policy.SplitScreenController
    public boolean o() {
        return Settings.System.getInt(this.f6299c.getContentResolver(), "ss_multi_window_enabled", 0) != 0;
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        a();
    }
}
