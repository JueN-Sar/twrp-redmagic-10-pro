package cn.nubia.gameassist.performance;

import android.app.Dialog;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BasePerformanceView;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.hostassist.controller.HostViewController;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class GamePerformanceViewController implements View.OnClickListener, GameMonitor.Callback {
    private static volatile GamePerformanceViewController x;

    /* renamed from: c, reason: collision with root package name */
    private Context f7002c;

    /* renamed from: j, reason: collision with root package name */
    private LinearLayout f7005j;

    /* renamed from: k, reason: collision with root package name */
    private LinearLayout f7006k;

    /* renamed from: l, reason: collision with root package name */
    private ImageButton f7007l;

    /* renamed from: m, reason: collision with root package name */
    private BasePerformanceView f7008m;

    /* renamed from: n, reason: collision with root package name */
    private BasePerformanceView f7009n;

    /* renamed from: o, reason: collision with root package name */
    private PerformanceStatusView f7010o;

    /* renamed from: p, reason: collision with root package name */
    private PerformanceStatusView f7011p;

    /* renamed from: q, reason: collision with root package name */
    private BasePerformanceView f7012q;

    /* renamed from: s, reason: collision with root package name */
    private ViewGroup f7014s;
    private ViewGroup t;
    private int u;
    private Dialog v;
    private HostViewController w;

    /* renamed from: h, reason: collision with root package name */
    private String f7003h = "";

    /* renamed from: r, reason: collision with root package name */
    private int f7013r = -1;

    /* renamed from: i, reason: collision with root package name */
    private Handler f7004i = new Handler(ThreadManager.c().e());

    /* renamed from: cn.nubia.gameassist.performance.GamePerformanceViewController$1, reason: invalid class name */
    class AnonymousClass1 extends ContentObserver {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ GamePerformanceViewController f7015a;

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            if (this.f7015a.f7005j == null) {
                return;
            }
            if (this.f7015a.h() == 1) {
                View childAt = this.f7015a.f7005j.getChildAt(3);
                if (childAt != null) {
                    this.f7015a.x(childAt.getId(), false, false);
                }
                this.f7015a.f7006k.setVisibility(0);
                this.f7015a.f7005j.setVisibility(8);
                return;
            }
            if (this.f7015a.h() == 0) {
                GamePerformanceViewController gamePerformanceViewController = this.f7015a;
                gamePerformanceViewController.x(gamePerformanceViewController.f7005j.getChildAt(this.f7015a.l() - 1).getId(), false, false);
                this.f7015a.f7006k.setVisibility(8);
                this.f7015a.f7005j.setVisibility(0);
            }
        }
    }

    private GamePerformanceViewController(Context context) {
        this.f7002c = context;
    }

    private void f(int i2, boolean z) {
        if (z) {
            NubiaTrackManager.p().z("cn.nubia.gamelauncher", "assistant_dashboard", "performance", i2 != 0 ? i2 != 1 ? i2 != 2 ? "" : "high" : "middle" : "low");
        }
    }

    private void g() {
        Dialog dialog = this.v;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.v.dismiss();
        this.v = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int h() {
        int i2 = Settings.Global.getInt(this.f7002c.getContentResolver(), "game_chicken_mode_switch", -1);
        int i3 = Settings.Global.getInt(this.f7002c.getContentResolver(), "game_chicken_mode_type", -1);
        GaLog.a("GamePerformanceViewController", "getChickenModeType: switchNum = " + i2 + " , typeNum = " + i3);
        if (i2 == 2 && i3 == 1) {
            return 1;
        }
        return i2 == 0 ? 0 : -1;
    }

    private void i() {
        this.f7003h = Utils.j();
        GaLog.e("GamePerformanceViewController", "getCurrentPackageName: mCurrentPackageName = " + this.f7003h);
    }

    private String j() {
        String string;
        if (!TextUtils.isEmpty(this.f7003h) && (string = Settings.Global.getString(this.f7002c.getContentResolver(), "NubiaperformanceMode")) != null && string.indexOf(this.f7003h) != -1) {
            for (String str : string.split(",")) {
                String trim = str.trim();
                if (!trim.isEmpty() && trim.indexOf(this.f7003h) != -1) {
                    return trim;
                }
            }
        }
        return null;
    }

    public static GamePerformanceViewController k(Context context) {
        if (x == null) {
            synchronized (GamePerformanceViewController.class) {
                try {
                    if (x == null) {
                        x = new GamePerformanceViewController(context);
                    }
                } finally {
                }
            }
        }
        return x;
    }

    private int m(String str, int i2, int i3) {
        try {
            return Integer.parseInt(String.valueOf(str.charAt(i2 + i3)));
        } catch (Exception unused) {
            return i3 == 1 ? 2 : 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n() {
        ViewGroup viewGroup = this.f7014s;
        if (viewGroup == null || viewGroup.getWindowVisibility() == 0) {
            return;
        }
        g();
        o();
    }

    private void q(int i2) {
        int i3 = 0;
        while (true) {
            LinearLayout linearLayout = this.f7005j;
            if (linearLayout == null || i3 >= linearLayout.getChildCount()) {
                return;
            }
            int id = this.f7005j.getChildAt(i3).getId();
            NubiaPerformanceRadioButton nubiaPerformanceRadioButton = (NubiaPerformanceRadioButton) this.t.findViewById(id);
            if (id == i2) {
                nubiaPerformanceRadioButton.setChecked(true);
            } else {
                nubiaPerformanceRadioButton.setChecked(false);
            }
            i3++;
        }
    }

    private void s() {
        Window window = this.v.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.type = 2038;
        attributes.flags = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED;
        attributes.screenOrientation = 3;
        window.setAttributes(attributes);
        window.setGravity(80);
        window.getDecorView().setSystemUiVisibility(5638);
        window.setFlags(1024, 1024);
        window.setLayout(-1, -1);
        window.setBackgroundDrawable(new ColorDrawable(0));
    }

    private void t() {
        View f2 = InflaterHelper.f(R.layout.performance_super_confirm_dialog_view, null);
        f2.findViewById(R.id.nubia_game_strengthen_performance_super_cancel).setOnClickListener(this);
        f2.findViewById(R.id.nubia_game_strengthen_performance_super_ok).setOnClickListener(this);
        this.v.setContentView(f2);
    }

    private void v() {
        if (this.v == null) {
            this.v = new Dialog(this.f7002c, R.style.DualScreenMapDialog);
            s();
            t();
        }
        if (this.v.isShowing()) {
            return;
        }
        this.v.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(int i2, boolean z, boolean z2) {
        int i3 = 3;
        if (h() != 1) {
            int i4 = 0;
            if (i2 == R.id.nubia_game_strength_performance_balance) {
                i3 = 1;
            } else if (i2 == R.id.nubia_game_strength_performance_rise) {
                i4 = 1;
                i3 = 2;
            } else if (i2 == R.id.nubia_game_strength_performance_beyond) {
                i4 = 2;
            } else if (i2 == R.id.nubia_game_strength_performance_infinite) {
                i4 = 3;
            } else {
                i3 = 0;
            }
            if (z) {
                p(i3);
            }
            q(i2);
            f(i4, z);
            this.f7013r = i2;
            i3 = i4;
        }
        this.f7012q.e(i3, z);
        this.f7008m.e(i3, z);
        this.f7009n.e(i3, z);
    }

    public int l() {
        try {
        } catch (Exception unused) {
            GaLog.b("GamePerformanceViewController", "setGameStrengthenPerformanceMode Exception !");
        }
        if (TextUtils.isEmpty(this.f7003h)) {
            GaLog.b("GamePerformanceViewController", "resetGameStrengthenValueWithSWExpand runningTasks is null !");
            return 2;
        }
        String j2 = j();
        if (TextUtils.isEmpty(j2)) {
            return 2;
        }
        int m2 = m(j2, j2.indexOf("+"), 1) & 3;
        GaLog.a("GamePerformanceViewController", "getPerformanceMode: performanceMode = " + m2);
        if (m2 == 0) {
            return 1;
        }
        return m2;
    }

    public void o() {
        ViewGroup viewGroup = this.t;
        if (viewGroup == null || viewGroup.getParent() != this.f7014s) {
            return;
        }
        w();
        try {
            this.f7014s.removeView(this.t);
        } catch (Exception unused) {
            GaLog.b("GamePerformanceViewController", "removeView Exception !");
        }
        this.t = null;
        this.f7005j = null;
        this.f7006k = null;
        this.f7007l = null;
        this.f7008m = null;
        this.f7009n = null;
        this.f7010o = null;
        this.f7011p = null;
        GaLog.e("GamePerformanceViewController", "removeView");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.diablo_mode_button) {
            Settings.Global.putInt(this.f7002c.getContentResolver(), "game_chicken_mode_switch", 0);
            return;
        }
        if (view.getId() == R.id.nubia_game_strengthen_performance_super_ok) {
            g();
            LinearLayout linearLayout = this.f7005j;
            if (linearLayout == null) {
                return;
            }
            x(linearLayout.getChildAt(3).getId(), false, false);
            return;
        }
        if (view.getId() == R.id.nubia_game_strengthen_performance_super_cancel) {
            g();
            return;
        }
        if (this.f7013r == view.getId()) {
            return;
        }
        if (view.getId() != R.id.nubia_game_strength_performance_infinite) {
            x(view.getId(), true, false);
        } else {
            GameAssistWindowManager.O(this.f7002c).g0("dialogshow");
            v();
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        i();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        this.f7004i.post(new Runnable() { // from class: cn.nubia.gameassist.performance.a
            @Override // java.lang.Runnable
            public final void run() {
                GamePerformanceViewController.this.n();
            }
        });
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        i();
    }

    public void p(int i2) {
        GaLog.a("GamePerformanceViewController", "saveGameStrengthenNewValueToDB: mCurrentPackageName = " + this.f7003h);
        if (TextUtils.isEmpty(this.f7003h)) {
            return;
        }
        String string = Settings.Global.getString(this.f7002c.getContentResolver(), "NubiaperformanceMode");
        GaLog.a("GamePerformanceViewController", "saveGameStrengthenNewValueToDB-start: strengthenValue = " + string);
        if (!TextUtils.isEmpty(string)) {
            if (string.contains(this.f7003h + "+")) {
                String[] split = string.split(",");
                int length = split.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    String str = split[i3];
                    if (!TextUtils.isEmpty(str)) {
                        if (str.contains(this.f7003h + "+")) {
                            int indexOf = str.indexOf("+");
                            m(str, indexOf, 1);
                            string = string.replace(str, this.f7003h + "+" + (i2 & 3) + m(str, indexOf, 2) + m(str, indexOf, 3));
                            break;
                        }
                    }
                    i3++;
                }
                GaLog.a("GamePerformanceViewController", "saveGameStrengthenNewValueToDB-end: strengthenValue = " + string);
                Settings.Global.putString(this.f7002c.getContentResolver(), "NubiaperformanceMode", string);
            }
        }
        string = string + this.f7003h + "+" + i2 + "00,";
        GaLog.a("GamePerformanceViewController", "saveGameStrengthenNewValueToDB-end: strengthenValue = " + string);
        Settings.Global.putString(this.f7002c.getContentResolver(), "NubiaperformanceMode", string);
    }

    public void r(HostViewController hostViewController) {
        this.w = hostViewController;
    }

    public void u(int i2) {
        if (this.t == null) {
            GaLog.b("GamePerformanceViewController", "setVisibility: mRoot is null!");
            return;
        }
        GaLog.e("GamePerformanceViewController", "setVisibility: flag = " + i2 + " , mFlag = " + this.u);
        if (this.u != i2) {
            this.t.setVisibility(i2);
            this.u = i2;
        }
    }

    public void w() {
        if (this.t == null) {
            GaLog.k("GamePerformanceViewController", "stopPerformanceAnim,view null");
            return;
        }
        this.f7008m.h();
        this.f7009n.h();
        this.f7010o.j();
        this.f7011p.j();
        this.f7012q.h();
    }
}
