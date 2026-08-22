package cn.nubia.gameassist.dessert.tiles;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.provider.Settings;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.utils.AppsHelper;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.mifavor.widget.AlertDialog;
import com.zte.performance.refreshrate.ScreenRefreshRateManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class RefreshRateTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback, PerformanceModeController.PerformanceModeCallback {
    private boolean v;
    protected String w;
    private int x;
    private Dialog y;
    private final List z;

    public RefreshRateTile(QSTile.Host host) {
        super(host);
        this.x = 0;
        this.z = new ArrayList();
        SystemMgr.y(this.f6153i).h(this);
        G0();
    }

    private void D0(final Window window) {
        if (window == null) {
            return;
        }
        window.getDecorView().post(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.i
            @Override // java.lang.Runnable
            public final void run() {
                RefreshRateTile.I0(window);
            }
        });
    }

    private void E0(boolean z, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        int indexOf = this.z.indexOf(Integer.valueOf(this.x));
        int i2 = z ? indexOf + 1 : indexOf - 1;
        if (i2 > this.z.size() - 1) {
            i2 = this.z.size() - 1;
        } else if (i2 < 0) {
            i2 = 0;
        }
        this.x = ((Integer) this.z.get(i2)).intValue();
        F0();
        o0();
        GameAgentUtil.k(this.f6153i, iGameAssistClientCallback, inMsg);
    }

    private void F0() {
        if (this.x == 144) {
            Toast.makeText(this.f6153i, R.string.ic_qs_refresh_rate_message144_ui90, 0).show();
        }
        if (this.x == 165) {
            Toast.makeText(this.f6153i, R.string.ic_qs_refresh_rate_message165, 0).show();
        }
        GaLog.a(this.f6151c, "applyChanged, mDefinedFps:" + this.x);
        SharedPreferencesUtil.k(this.f6153i).setFpsData(this.w, this.x);
        L0(this.w, this.x);
    }

    private static boolean H0(int i2) {
        return i2 == 0 || i2 == 60 || i2 == 90 || i2 == 120 || i2 == 144 || i2 == 165;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void I0(Window window) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (RotationMgr.k()) {
            attributes.width = -1;
        } else if (ZteFeature.isTabletProduct()) {
            attributes.width = (int) (GameAssistWindowManager.Q * 0.65f);
        } else {
            attributes.width = -2;
        }
        window.setAttributes(attributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J0(DialogInterface dialogInterface, int i2) {
        this.x = ((Integer) this.z.get(i2)).intValue();
        GaLog.a(this.f6151c, "onClick: which:" + i2 + " mDefinedFps:" + this.x);
        F0();
        o0();
        this.y.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K0(DialogInterface dialogInterface, int i2) {
        this.y.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M0() {
        Dialog dialog = this.y;
        if (dialog == null || !dialog.isShowing()) {
            String string = this.f6153i.getResources().getString(R.string.ic_qs_refresh_rate_title, AppsHelper.b(this.w));
            String[] strArr = new String[this.z.size()];
            strArr[0] = this.f6153i.getResources().getString(R.string.ic_qs_refresh_rate_follow_system);
            for (int i2 = 0; i2 < this.z.size(); i2++) {
                int intValue = ((Integer) this.z.get(i2)).intValue();
                if (intValue == 60) {
                    strArr[i2] = this.f6153i.getResources().getString(R.string.ic_qs_refresh_rate_60hz);
                } else if (intValue == 90) {
                    strArr[i2] = this.f6153i.getResources().getString(R.string.ic_qs_refresh_rate_90hz);
                } else if (intValue == 120) {
                    strArr[i2] = this.f6153i.getResources().getString(R.string.ic_qs_refresh_rate_120hz);
                } else if (intValue == 144) {
                    strArr[i2] = this.f6153i.getResources().getString(R.string.ic_qs_refresh_rate_144hz);
                } else if (intValue == 165) {
                    strArr[i2] = this.f6153i.getResources().getString(R.string.ic_qs_refresh_rate_165hz);
                }
            }
            AlertDialog a2 = new AlertDialog.Builder(this.f6153i, com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).m(string).k(strArr, this.z.indexOf(Integer.valueOf(this.x)), new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.dessert.tiles.k
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    RefreshRateTile.this.J0(dialogInterface, i3);
                }
            }).f(com.zte.gameassist.common.R.string.single_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.dessert.tiles.l
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    RefreshRateTile.this.K0(dialogInterface, i3);
                }
            }).a();
            this.y = a2;
            a2.getWindow().setType(2008);
            this.y.getWindow().getAttributes().setTitle("RateSelectSetting");
            this.y.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.y.show();
            D0(this.y.getWindow());
        }
    }

    @VisibleForTesting
    public static List<Integer> getSupportedRSS(Context context) {
        float[] supportedRefreshRates;
        String[] supportRefreshRate;
        ArrayList arrayList = new ArrayList();
        arrayList.add(0);
        if (!ZteFeature.liteOptionModeEnable() && (supportRefreshRate = ZteFeature.getSupportRefreshRate()) != null && supportRefreshRate.length > 0) {
            for (String str : supportRefreshRate) {
                arrayList.add(Integer.decode(str));
            }
            return arrayList;
        }
        for (Display display : ((DisplayManager) context.getSystemService("display")).getDisplays()) {
            if (display.getDisplayId() == 0 && (supportedRefreshRates = display.getSupportedRefreshRates()) != null && supportedRefreshRates.length > 0) {
                for (float f2 : supportedRefreshRates) {
                    int i2 = (int) f2;
                    if (H0(i2)) {
                        arrayList.add(Integer.valueOf(i2));
                    }
                }
                Collections.sort(arrayList);
            }
        }
        return arrayList;
    }

    public void G0() {
        this.z.addAll(getSupportedRSS(this.f6153i));
    }

    public void L0(String str, int i2) {
        try {
            ScreenRefreshRateManager.getInstance().setRefreshRateByGameAssist("GameAssist", str, i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        if (this.f6160p.q0() == 1 && !Utils.A(this.f6153i)) {
            return false;
        }
        this.w = Utils.j();
        if (this.z.size() <= 1) {
            return true;
        }
        this.f6152h.b();
        this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.j
            @Override // java.lang.Runnable
            public final void run() {
                RefreshRateTile.this.M0();
            }
        });
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.System.getUriFor("refresh_rate_mode"), this);
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("NubiaperformanceMode"), this);
            PerformanceModeController.S().P(this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.System.getUriFor("refresh_rate_mode"), this);
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("NubiaperformanceMode"), this);
            PerformanceModeController.S().x0(this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        if (this.f6160p.q0() != 1 || Utils.A(this.f6153i)) {
            int i2 = this.x;
            if (i2 == 0) {
                state.f6169c = this.f6153i.getString(R.string.ic_qs_refresh_rate_follow_system);
                state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_refresh_rate_auto);
            } else if (i2 == 60) {
                state.f6169c = this.f6153i.getString(R.string.ic_qs_refresh_rate_60hz);
                state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_refresh_rate_60hz);
            } else if (i2 == 120) {
                state.f6169c = this.f6153i.getString(R.string.ic_qs_refresh_rate_120hz);
                state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_refresh_rate_120hz);
            } else if (i2 == 144) {
                state.f6169c = this.f6153i.getString(R.string.ic_qs_refresh_rate_144hz);
                state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_refresh_rate_144hz);
            } else if (i2 != 165) {
                state.f6169c = this.f6153i.getString(R.string.ic_qs_refresh_rate_90hz);
                state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_refresh_rate_90hz);
            } else {
                state.f6169c = this.f6153i.getString(R.string.ic_qs_refresh_rate_165hz);
                state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_refresh_rate_165hz);
            }
        } else {
            state.f6169c = this.f6153i.getString(R.string.ic_qs_refresh_rate_60hz);
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_refresh_rate_60hz_unpress);
        }
        state.f6175i = true;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_set_fps_level_down".equals(str)) {
            E0(false, iGameAssistClientCallback, inMsg);
        } else if ("game_set_fps_level_up".equals(str)) {
            E0(true, iGameAssistClientCallback, inMsg);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void m0(boolean z) {
        super.m0(z);
        if (z) {
            return;
        }
        Dialog dialog = this.y;
        if (dialog != null && dialog.isShowing()) {
            this.y.dismiss();
        }
        this.y = null;
    }

    @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
    public void n(String str, int i2, boolean z) {
        o0();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        this.w = Utils.j();
        GaLog.e(this.f6151c, "--onGameStart-- " + this.w + " mDefinedFps= " + this.x);
        int fpsData = SharedPreferencesUtil.k(this.f6153i).getFpsData(this.w);
        this.x = fpsData;
        L0(this.w, fpsData);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        this.w = Utils.j();
        int fpsData = SharedPreferencesUtil.k(this.f6153i).getFpsData(this.w);
        this.x = fpsData;
        L0(this.w, fpsData);
        GaLog.j(this.f6151c, "--onGameUpdate-- " + this.w + " mDefinedFps= " + this.x);
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
        Dialog dialog = this.y;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        D0(this.y.getWindow());
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
