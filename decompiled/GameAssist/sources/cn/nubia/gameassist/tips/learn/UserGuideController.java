package cn.nubia.gameassist.tips.learn;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.tips.learn.UserGuideController;
import cn.nubia.gameassist.tips.learn.UserGuideView;
import cn.nubia.gameassist.utils.WindowManagerUtil;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.Arrays;

/* loaded from: classes.dex */
public class UserGuideController implements GameMonitor.Callback {

    /* renamed from: r, reason: collision with root package name */
    private static volatile UserGuideController f7621r;

    /* renamed from: h, reason: collision with root package name */
    private WindowManagerUtil f7623h;

    /* renamed from: i, reason: collision with root package name */
    private WindowManager.LayoutParams f7624i;

    /* renamed from: j, reason: collision with root package name */
    private Context f7625j;

    /* renamed from: k, reason: collision with root package name */
    private UserGuideView f7626k;

    /* renamed from: l, reason: collision with root package name */
    private View f7627l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f7628m;

    /* renamed from: o, reason: collision with root package name */
    private boolean f7630o;

    /* renamed from: p, reason: collision with root package name */
    private Dialog f7631p;

    /* renamed from: q, reason: collision with root package name */
    private Runnable f7632q;

    /* renamed from: c, reason: collision with root package name */
    private Handler f7622c = new Handler(ThreadManager.c().e());

    /* renamed from: n, reason: collision with root package name */
    private final boolean f7629n = ZteFeature.isSupportUserGuide();

    private UserGuideController(Context context) {
        this.f7625j = context;
        this.f7623h = new WindowManagerUtil((WindowManager) context.getSystemService("window"));
        g();
        SystemMgr.y(this.f7625j).h(this);
    }

    public static UserGuideController e(Context context) {
        if (f7621r == null) {
            synchronized (UserGuideController.class) {
                try {
                    if (f7621r == null) {
                        f7621r = new UserGuideController(context);
                    }
                } finally {
                }
            }
        }
        return f7621r;
    }

    private Intent f(String str, String str2) {
        String[] split = str.split(";");
        GaLog.e("UserGuideController", "getPlanIntent: lastDataS = " + Arrays.toString(split));
        Intent intent = new Intent();
        for (String str3 : split) {
            if (!TextUtils.isEmpty(str3) && str3.contains(str2)) {
                SharedPreferencesUtil.k(this.f7625j).P("pref_user_guide_x_gravity_" + str2, str3);
                String[] split2 = str3.split(",");
                long parseLong = Long.parseLong(split2[1]);
                int parseInt = Integer.parseInt(split2[2]);
                GaLog.e("UserGuideController", "getPlanIntent: id = " + parseLong + " , type = " + parseInt);
                if (parseInt == 1) {
                    intent.setAction("cn.nubia.gamepad.startGamepadService");
                    intent.setPackage("cn.nubia.gamepad");
                    intent.putExtra("packagename", str2);
                    intent.putExtra("case_id", parseLong);
                    intent.putExtra("action_type", 15);
                } else if (parseInt == 2) {
                    intent.setAction("cn.nubia.keymapcenter.intent.action.LKM_MAP");
                    intent.setPackage("cn.nubia.keymapcenter");
                    intent.putExtra("reason", "view_scheme");
                    intent.putExtra("package_name", str2);
                    intent.putExtra("scheme_id", parseLong);
                }
            }
        }
        return intent;
    }

    private void g() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2038, 75826944, -3);
        this.f7624i = layoutParams;
        layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
        WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.f7624i);
        this.f7624i.gravity = 17;
        GaLog.a("UserGuideController", "initLayoutParams: mLayoutParams = " + this.f7624i);
    }

    private boolean h(String str, String str2) {
        String f2 = SharedPreferencesUtil.k(this.f7625j).f("pref_user_guide_x_gravity_" + str, "");
        String[] split = str2.split(";");
        GaLog.e("UserGuideController", "isSameData: currentData = " + Arrays.toString(split) + " , lastData = " + f2);
        int length = split.length;
        for (int i2 = 0; i2 < length; i2++) {
            String str3 = split[i2];
            if (!TextUtils.isEmpty(str3)) {
                if (str3.contains(str + ",") && str3.equals(f2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(Intent intent) {
        p(intent);
        this.f7632q = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(DialogInterface dialogInterface) {
        l();
    }

    private void l() {
        Runnable runnable = this.f7632q;
        if (runnable != null) {
            this.f7622c.removeCallbacks(runnable);
            this.f7632q = null;
            GaLog.e("UserGuideController", "removeGuideSolutionDialog: cancelled pending dialog show task");
        }
        Dialog dialog = this.f7631p;
        if (dialog != null) {
            try {
                if (dialog.isShowing()) {
                    this.f7631p.dismiss();
                    GaLog.e("UserGuideController", "removeGuideSolutionDialog: dismissed dialog");
                }
            } catch (Exception e2) {
                GaLog.c("UserGuideController", "removeGuideSolutionDialog: exception while dismissing dialog", e2);
            }
            this.f7631p = null;
        }
    }

    private void p(Intent intent) {
        Dialog dialog = this.f7631p;
        if (dialog == null || !dialog.isShowing()) {
            GaLog.e("UserGuideController", "showGuideSolutionDialog:");
            UserGuideDialog userGuideDialog = new UserGuideDialog(this.f7625j, intent);
            this.f7631p = userGuideDialog;
            Window window = userGuideDialog.getWindow();
            window.setGravity(80);
            window.setType(2038);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.getDecorView().setSystemUiVisibility(6);
            window.setLayout(this.f7625j.getResources().getInteger(R.integer.user_guide_dialog_width), -2);
            this.f7631p.show();
            this.f7631p.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: j.b
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    UserGuideController.this.k(dialogInterface);
                }
            });
        }
    }

    public void c() {
        if (!this.f7630o || this.f7626k == null) {
            return;
        }
        GaLog.e("UserGuideController", "doLast: ");
        this.f7626k.b();
        this.f7630o = false;
    }

    public void d() {
        if (this.f7630o || this.f7626k == null) {
            return;
        }
        GaLog.e("UserGuideController", "doNext: ");
        this.f7626k.c();
        this.f7630o = true;
    }

    public boolean i() {
        return Settings.Global.getInt(this.f7625j.getContentResolver(), "gamespace_support_super_base_config", 0) == 1;
    }

    public void m() {
        if (this.f7629n) {
            GaLog.e("UserGuideController", "removeUserGuide: mIsWindowShowing = " + this.f7628m);
            if (this.f7628m) {
                this.f7623h.b(this.f7627l);
                this.f7628m = false;
            }
            this.f7626k = null;
            this.f7627l = null;
        }
    }

    public void n(boolean z) {
        GaLog.e("UserGuideController", "sendEventForLearningSwitchStatus: isOpen = " + z);
        NubiaTrackManager.p().B("behavioral_learning_switch_status", "switch_status", z ? "on" : "off");
    }

    public void o(String str) {
        String o2 = NubiaTrackManager.o(this.f7625j, str);
        GaLog.e("UserGuideController", "sendEventForLearningUsed: packageName = " + str + " , appName = " + o2);
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        bundle.putString("app_name", o2);
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "behavioral_learning_used", bundle);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        if (this.f7629n) {
            m();
            l();
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public void onLauncherFirstPackage(String str) {
        if (this.f7629n) {
            String string = Settings.Global.getString(this.f7625j.getContentResolver(), "x_gravity_new_case_data_for_intelligent");
            GaLog.e("UserGuideController", "onLauncherFirstPackage: lastData = " + string);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            if (!string.contains(str + ",") || h(str, string)) {
                return;
            }
            final Intent f2 = f(string, str);
            o(str);
            Runnable runnable = this.f7632q;
            if (runnable != null) {
                this.f7622c.removeCallbacks(runnable);
            }
            Runnable runnable2 = new Runnable() { // from class: j.a
                @Override // java.lang.Runnable
                public final void run() {
                    UserGuideController.this.j(f2);
                }
            };
            this.f7632q = runnable2;
            this.f7622c.postDelayed(runnable2, 2000L);
        }
    }

    public void q(String str, UserGuideView.UserGuideResource userGuideResource) {
        if (this.f7629n && Settings.Global.getInt(this.f7625j.getContentResolver(), "zte_learned_behavior_enable", 0) != 1) {
            int i2 = GameAssistApplication.i().U()[0];
            if (i2 != 0) {
                this.f7624i.width = i2;
            }
            GaLog.e("UserGuideController", "showUserGuide: mIsWindowShowing = " + this.f7628m + " , deviceWidth = " + i2);
            if (this.f7628m) {
                return;
            }
            UserGuideView userGuideView = new UserGuideView(this.f7625j, userGuideResource, this);
            this.f7626k = userGuideView;
            this.f7627l = userGuideView.a();
            this.f7624i.setTitle("UserGuideWindow" + str);
            this.f7623h.a(this.f7627l, this.f7624i);
            this.f7628m = true;
        }
    }
}
