package cn.nubia.gameassist.plugin.panel;

import android.view.View;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class PluginViewController extends BaseViewController implements QSTile.Host.Callback {

    /* renamed from: q, reason: collision with root package name */
    private final PluginSwitchController f7258q;

    /* renamed from: r, reason: collision with root package name */
    private BasePluginMode f7259r;

    /* renamed from: s, reason: collision with root package name */
    private BasePluginMode f7260s;
    private final Runnable t;

    public PluginViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.t = new Runnable() { // from class: cn.nubia.gameassist.plugin.panel.PluginViewController.1
            @Override // java.lang.Runnable
            public void run() {
                PluginViewController.this.f7259r.d();
                PluginViewController.this.f7260s.d();
            }
        };
        this.f7258q = (PluginSwitchController) gameAssistWindowManager.T(PluginSwitchController.class);
        this.f7259r = new CardPluginMode(this.f6117c, gameAssistWindowManager);
        this.f7260s = new ListPluginMode(this.f6117c, gameAssistWindowManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Y() {
        this.f7259r.i(true);
        this.f7260s.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Z() {
        this.f7259r.i(true);
        this.f7260s.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a0() {
        this.f7259r.h();
        this.f7260s.h();
        GaLog.e("PluginViewController", "refreshSortState");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b0(String str) {
        if (this.f7258q.a0() == 1) {
            this.f7259r.f(str);
        } else {
            this.f7260s.f(str);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_plugins_container;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void H() {
        GaLog.a("PluginViewController", "onGameStart:");
        this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.panel.k
            @Override // java.lang.Runnable
            public final void run() {
                PluginViewController.this.Y();
            }
        }, 300L);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void I() {
        GaLog.a("PluginViewController", "onGameStop:");
        this.f6125o.removeCallbacks(this.t);
        this.f6125o.post(this.t);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void J() {
        GaLog.a("PluginViewController", "onGameUpdate:");
        this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.panel.l
            @Override // java.lang.Runnable
            public final void run() {
                PluginViewController.this.Z();
            }
        }, 300L);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        this.f7259r.e();
        this.f7260s.e();
    }

    public void c0() {
        this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.panel.m
            @Override // java.lang.Runnable
            public final void run() {
                PluginViewController.this.a0();
            }
        }, 20L);
    }

    public void d0(int i2) {
        if (i2 == 1) {
            this.f7259r.c();
        } else {
            this.f7260s.c();
        }
    }

    public void e0(final String str) {
        this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.panel.j
            @Override // java.lang.Runnable
            public final void run() {
                PluginViewController.this.b0(str);
            }
        }, 500L);
    }

    public void f0() {
        if (this.f7258q.y == 1) {
            this.f7259r.g(true);
            this.f7260s.g(false);
        } else {
            this.f7260s.g(true);
            this.f7259r.g(false);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        printWriter.println(str + "  mListPluginMode.mPluginTilesAdapter=" + this.f7259r.f7233b);
        printWriter.println(str + "  mListPluginMode.mPluginTilesAdapter=" + this.f7260s.f7233b);
        printWriter.println(str + "  mIsHorizontal=" + this.f6120j);
        PluginTilesAdapter pluginTilesAdapter = this.f7259r.f7233b;
        if (pluginTilesAdapter != null) {
            pluginTilesAdapter.N(printWriter, str);
        }
        PluginTilesAdapter pluginTilesAdapter2 = this.f7260s.f7233b;
        if (pluginTilesAdapter2 != null) {
            pluginTilesAdapter2.N(printWriter, str);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void o(View view) {
        boolean z = this.f6121k;
        this.f6120j = z;
        this.f7259r.b(z, view, 1);
        this.f7260s.b(this.f6120j, view, 0);
    }
}
