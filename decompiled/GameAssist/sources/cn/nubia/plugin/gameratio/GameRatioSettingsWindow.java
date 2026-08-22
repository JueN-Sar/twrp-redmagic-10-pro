package cn.nubia.plugin.gameratio;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.gameratio.GameRatioSettingsPanel;
import cn.nubia.plugin.gameratio.GameRatioSettingsWindow;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.PrintWriter;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class GameRatioSettingsWindow {

    /* renamed from: a, reason: collision with root package name */
    private Context f8408a;

    /* renamed from: b, reason: collision with root package name */
    private WindowManager f8409b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f8410c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f8411d;

    /* renamed from: e, reason: collision with root package name */
    private GameRatioSettingsPanel f8412e;

    /* renamed from: f, reason: collision with root package name */
    private View f8413f;

    /* renamed from: g, reason: collision with root package name */
    private WindowManager.LayoutParams f8414g;

    /* renamed from: h, reason: collision with root package name */
    private GameRatioDataMgr f8415h;

    /* renamed from: i, reason: collision with root package name */
    private GameRatioMgr f8416i;

    /* renamed from: j, reason: collision with root package name */
    private GameRatioData f8417j;

    /* renamed from: k, reason: collision with root package name */
    private int f8418k;

    /* renamed from: l, reason: collision with root package name */
    private final Handler f8419l = new Handler(Looper.getMainLooper());

    /* renamed from: m, reason: collision with root package name */
    private RotationMgr.Callback f8420m = new RotationMgr.Callback() { // from class: cn.nubia.plugin.gameratio.GameRatioSettingsWindow.1
        @Override // com.zte.gameassist.common.RotationMgr.Callback
        /* renamed from: onRotationChanged */
        public void y(int i2) {
            if (GameRatioSettingsWindow.this.f8410c) {
                GameRatioSettingsWindow.this.f8409b.updateViewLayout(GameRatioSettingsWindow.this.f8412e, GameRatioSettingsWindow.this.t());
            }
        }
    };

    /* renamed from: cn.nubia.plugin.gameratio.GameRatioSettingsWindow$2, reason: invalid class name */
    class AnonymousClass2 implements GameRatioSettingsPanel.OnOperationListener {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h(GameRatioData gameRatioData) {
            GaLog.e("GameRatio", "set ori=" + gameRatioData.a() + ", size=" + gameRatioData.c());
            if (GameRatioSettingsWindow.this.f8417j.a() != gameRatioData.a()) {
                GameRatioSettingsWindow.this.f8415h.D(gameRatioData.b(), GameRatioSettingsWindow.this.f8415h.m(gameRatioData.a()));
            }
            if (GameRatioSettingsWindow.this.f8417j.c() != gameRatioData.c()) {
                GameRatioSettingsWindow.this.f8415h.E(gameRatioData.b(), GameRatioSettingsWindow.this.f8415h.n(gameRatioData.c()));
            }
            if (gameRatioData.d()) {
                GameRatioSettingsWindow.this.f8415h.c(gameRatioData.b());
            } else {
                GameRatioSettingsWindow.this.f8415h.d(GameRatioSettingsWindow.this.f8417j.b());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void i(GameRatioData gameRatioData) {
            GameRatioSettingsWindow gameRatioSettingsWindow = GameRatioSettingsWindow.this;
            gameRatioSettingsWindow.C(gameRatioSettingsWindow.f8417j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void j(GameRatioData gameRatioData) {
            GameRatioSettingsWindow.this.f8415h.d(gameRatioData.b());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void k(GameRatioData gameRatioData) {
            GameRatioSettingsWindow gameRatioSettingsWindow = GameRatioSettingsWindow.this;
            gameRatioSettingsWindow.C(gameRatioSettingsWindow.f8417j);
        }

        @Override // cn.nubia.plugin.gameratio.GameRatioSettingsPanel.OnOperationListener
        public void a(GameRatioData gameRatioData, boolean z) {
            if (GameRatioSettingsWindow.this.f8410c) {
                if (z) {
                    GameRatioSettingsWindow.this.z(gameRatioData, new Consumer() { // from class: cn.nubia.plugin.gameratio.A
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            GameRatioSettingsWindow.AnonymousClass2.this.h((GameRatioData) obj);
                        }
                    }, new Consumer() { // from class: cn.nubia.plugin.gameratio.B
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            GameRatioSettingsWindow.AnonymousClass2.this.i((GameRatioData) obj);
                        }
                    });
                    GameRatioSettingsWindow.this.q();
                    return;
                }
                if (GameRatioSettingsWindow.this.f8415h.r(GameRatioSettingsWindow.this.f8417j.b()) || GameRatioSettingsWindow.this.f8417j.d()) {
                    GameRatioSettingsWindow.this.p();
                    return;
                }
                Consumer consumer = new Consumer() { // from class: cn.nubia.plugin.gameratio.C
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        GameRatioSettingsWindow.AnonymousClass2.this.j((GameRatioData) obj);
                    }
                };
                Consumer consumer2 = new Consumer() { // from class: cn.nubia.plugin.gameratio.D
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        GameRatioSettingsWindow.AnonymousClass2.this.k((GameRatioData) obj);
                    }
                };
                GameRatioSettingsWindow gameRatioSettingsWindow = GameRatioSettingsWindow.this;
                gameRatioSettingsWindow.z(gameRatioSettingsWindow.f8417j, consumer, consumer2);
                GameRatioSettingsWindow.this.q();
            }
        }

        @Override // cn.nubia.plugin.gameratio.GameRatioSettingsPanel.OnOperationListener
        public void b() {
            GameRatioSettingsWindow.this.y();
        }

        @Override // cn.nubia.plugin.gameratio.GameRatioSettingsPanel.OnOperationListener
        public void c(GameRatioData gameRatioData) {
            GameRatioSettingsWindow.this.p();
        }
    }

    public GameRatioSettingsWindow(Context context, GameRatioDataMgr gameRatioDataMgr, GameRatioMgr gameRatioMgr) {
        this.f8408a = context;
        this.f8409b = (WindowManager) context.getSystemService("window");
        this.f8415h = gameRatioDataMgr;
        this.f8416i = gameRatioMgr;
    }

    private void A() {
        int i2 = this.f8418k - 1;
        this.f8418k = i2;
        if (i2 == 0) {
            RotationMgr.e(this.f8408a).p(this.f8420m);
        }
    }

    private void B(GameRatioData gameRatioData) {
        Utils.W(gameRatioData.b(), "GameRatio");
        o(gameRatioData);
    }

    private void l(String str) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2038);
        layoutParams.flags = 67110688;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -2;
        layoutParams.gravity = 81;
        layoutParams.setTitle(str);
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        this.f8411d = true;
        this.f8409b.addView(this.f8413f, layoutParams);
        m();
    }

    private void m() {
        if (this.f8418k == 0) {
            RotationMgr.e(this.f8408a).c(this.f8420m);
        }
        this.f8418k++;
    }

    private void n() {
        if (this.f8411d) {
            this.f8411d = false;
            this.f8409b.removeView(this.f8413f);
            A();
            this.f8413f = null;
        }
    }

    private void o(final GameRatioData gameRatioData) {
        if ("com.tencent.tmgp.cod".equals(gameRatioData.b())) {
            this.f8419l.postDelayed(new Runnable(this) { // from class: cn.nubia.plugin.gameratio.GameRatioSettingsWindow.3
                @Override // java.lang.Runnable
                public void run() {
                    Utils.W(gameRatioData.b(), "GameRatio");
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.f8410c) {
            this.f8410c = false;
            this.f8409b.removeView(this.f8412e);
            A();
        }
    }

    private WindowManager.LayoutParams s() {
        WindowManager.LayoutParams layoutParams = this.f8414g;
        if (layoutParams != null) {
            return layoutParams;
        }
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(2038);
        this.f8414g = layoutParams2;
        layoutParams2.flags = 67110688;
        layoutParams2.format = -2;
        layoutParams2.layoutInDisplayCutoutMode = 3;
        layoutParams2.setTitle("PluginGameRatioSettings");
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.f8414g);
        return this.f8414g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WindowManager.LayoutParams t() {
        WindowManager.LayoutParams s2 = s();
        if (RotationMgr.k()) {
            s2.gravity = 81;
            s2.width = this.f8408a.getResources().getDimensionPixelSize(R.dimen.gameratio_settings_panel_width);
            s2.x = 0;
            s2.y = this.f8408a.getResources().getDimensionPixelSize(R.dimen.gameratio_settings_panel_end);
            s2.height = this.f8408a.getResources().getDimensionPixelSize(R.dimen.gameratio_settings_panel_height);
        } else {
            s2.width = this.f8408a.getResources().getDimensionPixelSize(R.dimen.gameratio_settings_panel_width);
            s2.height = this.f8408a.getResources().getDimensionPixelSize(R.dimen.gameratio_settings_panel_height);
            s2.x = this.f8408a.getResources().getDimensionPixelSize(R.dimen.gameratio_settings_panel_end);
            s2.y = 0;
            s2.gravity = 8388629;
        }
        return s2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(View view) {
        n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(Consumer consumer, GameRatioData gameRatioData, View view) {
        n();
        consumer.accept(gameRatioData);
        this.f8416i.l(gameRatioData.b());
        B(gameRatioData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(Consumer consumer, GameRatioData gameRatioData, View view) {
        n();
        if (consumer != null) {
            consumer.accept(gameRatioData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        if (this.f8411d) {
            return;
        }
        View f2 = InflaterHelper.f(R.layout.gameratio_help, null);
        this.f8413f = f2;
        TextView textView = (TextView) f2.findViewById(R.id.title);
        textView.setText(R.string.gameratio_title);
        textView.setVisibility(0);
        TextView textView2 = (TextView) this.f8413f.findViewById(R.id.msg);
        if (GameRatioMgr.f8397s) {
            textView2.setText(R.string.gameratio_help_title);
        } else {
            textView2.setText(R.string.gameratio_help_title_without_orientation);
        }
        textView2.setVisibility(0);
        TextView textView3 = (TextView) this.f8413f.findViewById(R.id.summary);
        textView3.setText(R.string.gameratio_help_summary);
        textView3.setVisibility(0);
        TextView textView4 = (TextView) this.f8413f.findViewById(R.id.neutral);
        textView4.setText(R.string.gameratio_ok);
        textView4.setVisibility(0);
        textView4.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.plugin.gameratio.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameRatioSettingsWindow.this.v(view);
            }
        });
        l("PluginGameRatioAlertHelp");
    }

    public void C(GameRatioData gameRatioData) {
        if (this.f8410c) {
            if (gameRatioData.b() != this.f8417j.b()) {
                p();
                C(gameRatioData);
                return;
            }
            return;
        }
        n();
        GaLog.e("GameRatio", "settings show for " + gameRatioData);
        this.f8410c = true;
        GameRatioSettingsPanel gameRatioSettingsPanel = (GameRatioSettingsPanel) InflaterHelper.f(R.layout.gameratio_settings_panel, null);
        this.f8412e = gameRatioSettingsPanel;
        gameRatioSettingsPanel.setOnOperationListener(new AnonymousClass2());
        this.f8417j = gameRatioData;
        this.f8412e.setData(gameRatioData);
        this.f8409b.addView(this.f8412e, t());
        m();
    }

    public void p() {
        n();
        q();
    }

    public void r(PrintWriter printWriter) {
        if (this.f8410c) {
            printWriter.println("  Settings show");
        }
        if (this.f8411d) {
            printWriter.println("  Alert show");
        }
    }

    public boolean u() {
        return this.f8410c;
    }

    public void z(final GameRatioData gameRatioData, final Consumer consumer, final Consumer consumer2) {
        if (this.f8411d) {
            return;
        }
        View f2 = InflaterHelper.f(R.layout.gameratio_alert, null);
        this.f8413f = f2;
        TextView textView = (TextView) f2.findViewById(R.id.title);
        textView.setText(R.string.gameratio_title);
        textView.setVisibility(0);
        TextView textView2 = (TextView) this.f8413f.findViewById(R.id.msg);
        textView2.setText(R.string.gameratio_restart_alert);
        textView2.setVisibility(0);
        TextView textView3 = (TextView) this.f8413f.findViewById(R.id.positive);
        textView3.setText(R.string.gameratio_restart_now);
        textView3.setVisibility(0);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.plugin.gameratio.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameRatioSettingsWindow.this.w(consumer, gameRatioData, view);
            }
        });
        TextView textView4 = (TextView) this.f8413f.findViewById(R.id.negative);
        textView4.setText(R.string.gameratio_cancel);
        textView4.setVisibility(0);
        textView4.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.plugin.gameratio.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameRatioSettingsWindow.this.x(consumer2, gameRatioData, view);
            }
        });
        l("PluginGameRatioAlertRestart");
    }
}
