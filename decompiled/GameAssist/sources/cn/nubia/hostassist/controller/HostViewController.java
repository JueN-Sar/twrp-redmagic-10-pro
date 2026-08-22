package cn.nubia.hostassist.controller;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BasePerformanceView;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.performance.GamePerformanceViewController;
import cn.nubia.gameassist.performance.NubiaPerformanceRadioButton;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.performance.PerformanceStatusView;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.hostassist.HostAssistMgr;
import cn.nubia.hostassist.HostDensityHelper;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.Arrays;

/* loaded from: classes.dex */
public class HostViewController implements View.OnClickListener, GameMonitor.Callback, PerformanceModeController.PerformanceModeCallback {
    private static volatile HostViewController A;

    /* renamed from: c, reason: collision with root package name */
    private Context f7837c;

    /* renamed from: j, reason: collision with root package name */
    private LinearLayout f7840j;

    /* renamed from: k, reason: collision with root package name */
    private LinearLayout f7841k;

    /* renamed from: l, reason: collision with root package name */
    private ImageButton f7842l;

    /* renamed from: m, reason: collision with root package name */
    private BasePerformanceView f7843m;

    /* renamed from: n, reason: collision with root package name */
    private BasePerformanceView f7844n;

    /* renamed from: o, reason: collision with root package name */
    private PerformanceStatusView f7845o;

    /* renamed from: p, reason: collision with root package name */
    private PerformanceStatusView f7846p;

    /* renamed from: q, reason: collision with root package name */
    private BasePerformanceView f7847q;

    /* renamed from: s, reason: collision with root package name */
    private ViewGroup f7849s;
    private ViewGroup t;
    private Dialog v;
    private GamePerformanceViewController w;
    private boolean x;
    private boolean y;

    /* renamed from: h, reason: collision with root package name */
    private String f7838h = "";

    /* renamed from: r, reason: collision with root package name */
    private int f7848r = -1;
    private int u = -1;

    /* renamed from: i, reason: collision with root package name */
    private Handler f7839i = new Handler(ThreadManager.c().e());
    public final PerformanceModeController z = PerformanceModeController.S();

    private HostViewController(Context context) {
        this.f7837c = context;
    }

    private void b(int i2, boolean z) {
        if (z) {
            NubiaTrackManager.p().z("cn.nubia.gamelauncher", "assistant_dashboard", "performance", i2 != 1 ? i2 != 2 ? "low" : "high" : "middle");
        }
    }

    private void c() {
        Dialog dialog = this.v;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.v.dismiss();
        this.v = null;
    }

    private GameAssistWindowManager d() {
        return GameAssistWindowManager.O(this.f7837c);
    }

    public static HostViewController e(Context context) {
        if (A == null) {
            synchronized (HostViewController.class) {
                try {
                    if (A == null) {
                        A = new HostViewController(context);
                    }
                } finally {
                }
            }
        }
        return A;
    }

    private void g(boolean z) {
        this.y = this.z.getPerformanceMode(this.f7838h) == 5;
        GaLog.e("HostViewController", "inflateView: mRoot = " + this.t + " , isHorizontal = " + z + " , mIsHorizontal = " + this.x + " , mIsDiablo = " + this.y);
        this.x = z;
        int i2 = z ? R.layout.layout_performance_host : R.layout.layout_performance_host_port;
        HostDensityHelper.d(this.f7837c);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.f7837c).inflate(i2, (ViewGroup) null);
        this.t = viewGroup;
        ((TextClock) viewGroup.findViewById(R.id.time)).setTypeface(Utils.h("YouSheBiaoTiHei-2.ttf"));
        this.f7843m = (BasePerformanceView) this.t.findViewById(R.id.performance_view_cpu);
        this.f7844n = (BasePerformanceView) this.t.findViewById(R.id.performance_view_gpu);
        this.f7843m.setViewSize(z);
        this.f7844n.setViewSize(z);
        this.f7845o = (PerformanceStatusView) this.t.findViewById(R.id.electricity);
        this.f7846p = (PerformanceStatusView) this.t.findViewById(R.id.net_speed);
        BasePerformanceView basePerformanceView = (BasePerformanceView) this.t.findViewById(R.id.performance_bg);
        this.f7847q = basePerformanceView;
        basePerformanceView.setViewSize(z);
        LinearLayout linearLayout = (LinearLayout) this.t.findViewById(R.id.nubia_game_performance_group);
        this.f7840j = linearLayout;
        NubiaPerformanceRadioButton nubiaPerformanceRadioButton = (NubiaPerformanceRadioButton) linearLayout.findViewById(R.id.nubia_game_strength_performance_custom);
        if (ZteFeature.isSupportCustom()) {
            nubiaPerformanceRadioButton.setVisibility(0);
        }
        this.f7841k = (LinearLayout) this.t.findViewById(R.id.diablo_mode_group);
        ImageButton imageButton = (ImageButton) this.t.findViewById(R.id.diablo_mode_button);
        this.f7842l = imageButton;
        imageButton.setOnClickListener(this);
        if (this.y) {
            this.f7841k.setVisibility(0);
            this.f7840j.setVisibility(8);
            TextView textView = (TextView) this.f7841k.findViewById(R.id.diablo_mode_tile);
            TextView textView2 = (TextView) this.f7841k.findViewById(R.id.diablo_mode_text);
            if (textView != null && textView2 != null) {
                l(textView);
                l(textView2);
            }
            View childAt = this.f7840j.getChildAt(3);
            if (childAt != null) {
                v(childAt.getId(), false);
            }
        } else {
            this.f7841k.setVisibility(8);
            this.f7840j.setVisibility(0);
            w();
        }
        for (int i3 = 0; i3 < this.f7840j.getChildCount(); i3++) {
            this.f7840j.getChildAt(i3).setOnClickListener(this);
        }
        this.f7849s.addView(this.t, 0);
    }

    private void k(int i2) {
        int i3 = 0;
        while (true) {
            LinearLayout linearLayout = this.f7840j;
            if (linearLayout == null || i3 >= linearLayout.getChildCount()) {
                return;
            }
            int id = this.f7840j.getChildAt(i3).getId();
            NubiaPerformanceRadioButton nubiaPerformanceRadioButton = (NubiaPerformanceRadioButton) this.t.findViewById(id);
            if (id == i2) {
                nubiaPerformanceRadioButton.setChecked(true);
            } else {
                nubiaPerformanceRadioButton.setChecked(false);
            }
            i3++;
        }
    }

    private void l(TextView textView) {
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSingleLine(true);
        textView.setSelected(true);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        textView.setMarqueeRepeatLimit(5);
    }

    private void o() {
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

    private void p() {
        HostDensityHelper.d(this.f7837c);
        View inflate = LayoutInflater.from(this.f7837c).inflate(R.layout.performance_super_confirm_dialog_view, (ViewGroup) null);
        inflate.findViewById(R.id.nubia_game_strengthen_performance_super_cancel).setOnClickListener(this);
        inflate.findViewById(R.id.nubia_game_strengthen_performance_super_ok).setOnClickListener(this);
        this.v.setContentView(inflate);
    }

    private void q() {
        if (this.v == null) {
            this.v = new Dialog(this.f7837c, R.style.DualScreenMapDialog);
            o();
            p();
        }
        if (this.v.isShowing()) {
            return;
        }
        this.v.show();
    }

    private void u() {
        GamePerformanceViewController gamePerformanceViewController = this.w;
        if (gamePerformanceViewController != null) {
            gamePerformanceViewController.u(0);
        }
        this.f7843m.h();
        this.f7844n.h();
        this.f7845o.j();
        this.f7846p.j();
        this.f7847q.h();
    }

    private void v(int i2, boolean z) {
        int i3 = 0;
        if (this.z.Z()) {
            if (i2 != R.id.nubia_game_strength_performance_balance) {
                Context p2 = HostAssistMgr.n().p();
                if (p2 == null) {
                    p2 = this.f7837c;
                }
                Toast.makeText(p2, p2.getText(R.string.performancemode_is_lowpowermode_tip), 0).show();
            }
            i2 = R.id.nubia_game_strength_performance_balance;
        }
        int i4 = 3;
        if (!this.y) {
            if (i2 == R.id.nubia_game_strength_performance_rise) {
                i4 = 2;
                i3 = 1;
            } else if (i2 == R.id.nubia_game_strength_performance_beyond) {
                i3 = 2;
            } else if (i2 == R.id.nubia_game_strength_performance_infinite) {
                i3 = 3;
            } else if (i2 != R.id.nubia_game_strength_performance_balance && i2 == R.id.nubia_game_strength_performance_custom) {
                i3 = 4;
                i4 = 4;
            } else {
                i4 = 1;
            }
            if (z && !this.z.Z()) {
                this.z.savePerformanceMode(this.f7838h, i4);
            }
            k(i2);
            b(i3, z);
            this.f7848r = i2;
            i4 = i3;
        }
        this.f7847q.e(i4, z);
        this.f7843m.e(i4, z);
        this.f7844n.e(i4, z);
    }

    private void w() {
        if (this.t == null) {
            GaLog.b("HostViewController", "updateView: mRoot is null!");
            return;
        }
        int performanceMode = this.z.getPerformanceMode(this.f7838h);
        GaLog.e("HostViewController", "updateView: mode = " + performanceMode);
        LinearLayout linearLayout = this.f7840j;
        if (performanceMode != 4) {
            performanceMode--;
        }
        View childAt = linearLayout.getChildAt(performanceMode);
        if (childAt != null) {
            v(childAt.getId(), false);
        }
    }

    public void a() {
        this.z.P(this);
    }

    public void f() {
        String string = Settings.Global.getString(this.f7837c.getContentResolver(), "app_mirror_list");
        String[] split = string.split("/");
        if (split.length > 0) {
            this.f7838h = split[0];
        }
        GaLog.a("HostViewController", "getTopPkg: mCurrentPackageName = " + this.f7838h + " , names = " + Arrays.toString(split) + " , className = " + string);
    }

    public void h(ViewGroup viewGroup) {
        this.u = -1;
        this.f7849s = viewGroup;
        SystemMgr.y(this.f7837c).h(this);
    }

    public boolean i() {
        ViewGroup viewGroup = this.t;
        return viewGroup != null && viewGroup.getVisibility() == 0;
    }

    public void j() {
        GaLog.e("HostViewController", "removeView: mRoot = " + this.t);
        if (this.t != null) {
            u();
            this.t = null;
            this.f7840j = null;
            this.f7841k = null;
            this.f7842l = null;
            this.f7843m = null;
            this.f7844n = null;
            this.f7845o = null;
            this.f7846p = null;
        }
    }

    public void m(GamePerformanceViewController gamePerformanceViewController) {
        this.w = gamePerformanceViewController;
    }

    @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
    public void n(String str, int i2, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("onPerformanceModeCallback: mGroup != null: ");
        sb.append(this.f7840j != null);
        sb.append(" , mDiabloModeGroup != null: ");
        sb.append(this.f7841k != null);
        sb.append(" , isDiablo = ");
        sb.append(z);
        sb.append(" , mIsDiablo = ");
        sb.append(this.y);
        sb.append(" , packageName = ");
        sb.append(str);
        sb.append(" , mCurrentPackageName = ");
        sb.append(this.f7838h);
        GaLog.e("HostViewController", sb.toString());
        if (this.f7838h.equals(str)) {
            LinearLayout linearLayout = this.f7840j;
            if (linearLayout == null || this.f7841k == null) {
                this.y = z;
                return;
            }
            if (z) {
                this.y = true;
                v(linearLayout.getChildAt(3).getId(), false);
                this.f7841k.setVisibility(0);
                this.f7840j.setVisibility(8);
                return;
            }
            if (this.y) {
                this.y = false;
                v(linearLayout.getChildAt(this.z.getPerformanceMode(this.f7838h) - 1).getId(), false);
                this.f7841k.setVisibility(8);
                this.f7840j.setVisibility(0);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.diablo_mode_button) {
            this.z.B0(this.f7838h, false);
            this.f7841k.setVisibility(8);
            this.f7840j.setVisibility(0);
            int performanceMode = this.z.getPerformanceMode(this.f7838h);
            this.y = false;
            v(this.f7840j.getChildAt(performanceMode - 1).getId(), false);
            return;
        }
        if (view.getId() == R.id.nubia_game_strengthen_performance_super_ok) {
            c();
            LinearLayout linearLayout = this.f7840j;
            if (linearLayout == null) {
                return;
            }
            v(linearLayout.getChildAt(3).getId(), false);
            return;
        }
        if (view.getId() == R.id.nubia_game_strengthen_performance_super_cancel) {
            c();
            return;
        }
        if (this.f7848r == view.getId()) {
            return;
        }
        if (view.getId() != R.id.nubia_game_strength_performance_infinite) {
            v(view.getId(), true);
        } else {
            d().g0("dialogshow");
            q();
        }
    }

    @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
    public void r(boolean z) {
        HostAssistMgr.n().m();
    }

    public void s(boolean z) {
        g(z);
        this.f7843m.f();
        this.f7844n.f();
        this.f7847q.f();
        this.f7845o.h();
        this.f7846p.h();
        d().g0("host performance show!");
    }

    @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
    public void t(String str, int i2) {
        if (this.f7838h.equals(str)) {
            w();
        }
    }
}
