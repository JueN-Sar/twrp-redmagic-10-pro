package cn.nubia.plugin.screenextraction.controller;

import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import cn.nubia.gameassist.R;
import cn.nubia.plugin.screenextraction.ScreenExtractionManager;
import cn.nubia.plugin.screenextraction.bean.ScreenExtractionData;
import cn.nubia.plugin.screenextraction.utils.DefaultUtils;
import cn.nubia.plugin.screenextraction.view.SettingsRootView;
import com.zte.gameassist.common.InflaterHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class SettingsWindowController extends BaseWindowController<SettingsRootView> implements SettingsRootView.Callback, WindowInsetsController.OnControllableInsetsChangedListener {

    /* renamed from: p, reason: collision with root package name */
    private String f8613p;

    /* renamed from: q, reason: collision with root package name */
    private Runnable f8614q;

    /* renamed from: r, reason: collision with root package name */
    private boolean f8615r;

    public SettingsWindowController(ScreenExtractionManager screenExtractionManager) {
        super(screenExtractionManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    /* renamed from: D, reason: merged with bridge method [inline-methods] */
    public SettingsRootView j() {
        return (SettingsRootView) InflaterHelper.f(R.layout.screen_extraction_settings_layout, null);
    }

    public int E() {
        int identifier = this.f8593k.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return this.f8593k.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    /* renamed from: F, reason: merged with bridge method [inline-methods] */
    public void w(SettingsRootView settingsRootView) {
        super.w(settingsRootView);
        settingsRootView.i();
        WindowInsetsController windowInsetsController = settingsRootView.getWindowInsetsController();
        windowInsetsController.addOnControllableInsetsChangedListener(this);
        windowInsetsController.hide(WindowInsets.Type.statusBars());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public void x(SettingsRootView settingsRootView) {
        ScreenExtractionData c2 = DefaultUtils.c(this.f8593k, this.f8613p);
        if (c2 != null) {
            settingsRootView.setScreenExtractionData(c2);
        }
        settingsRootView.setCallback(this);
        super.x(settingsRootView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public void y(SettingsRootView settingsRootView) {
        super.y(settingsRootView);
        settingsRootView.getWindowInsetsController().removeOnControllableInsetsChangedListener(this);
        if (DefaultUtils.c(this.f8593k, this.f8613p) == null) {
            ScreenExtractionManager.w().q(this.f8613p);
        }
        ScreenExtractionManager.w().E();
        this.f8615r = false;
    }

    public SettingsWindowController I(Runnable runnable) {
        this.f8614q = runnable;
        return this;
    }

    public void J(String str, boolean z) {
        this.f8613p = str;
        this.f8614q = null;
        if (!v() || z) {
            B("switchWindow");
        } else {
            b("switchWindow");
        }
    }

    @Override // cn.nubia.plugin.screenextraction.controller.IWindowController
    public void a(FileDescriptor fileDescriptor, PrintWriter printWriter, String str) {
        printWriter.println(str + "ScreenExtraction.Window");
        String str2 = str + "  ";
        printWriter.println(str2 + "isShowWindow=" + v());
        printWriter.println(str2 + "setLayoutPadding=" + this.f8615r);
    }

    @Override // cn.nubia.plugin.screenextraction.view.SettingsRootView.Callback
    public void c(boolean z, ScreenExtractionData screenExtractionData) {
        if (z) {
            DefaultUtils.e(this.f8593k, screenExtractionData);
        }
        Runnable runnable = this.f8614q;
        if (runnable != null) {
            runnable.run();
            this.f8614q = null;
        }
        b("button click");
    }

    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    protected int l() {
        int l2 = super.l();
        return !this.f8615r ? l2 ^ 8 : l2;
    }

    @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
    public void onControllableInsetsChanged(WindowInsetsController windowInsetsController, int i2) {
        View view;
        if ((WindowInsets.Type.statusBars() & i2) == 0 || (view = this.f8594l) == null) {
            return;
        }
        this.f8615r = true;
        ((SettingsRootView) view).findViewById(R.id.settings_layout_view).setPadding(0, E(), 0, 0);
        this.f8592j.post(new Runnable() { // from class: cn.nubia.plugin.screenextraction.controller.f
            @Override // java.lang.Runnable
            public final void run() {
                SettingsWindowController.this.C();
            }
        });
    }

    @Override // cn.nubia.plugin.screenextraction.controller.BaseWindowController
    protected String r() {
        return "ScreenExtraction.Settings";
    }
}
