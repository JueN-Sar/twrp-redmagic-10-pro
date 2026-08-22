package cn.nubia.gameassist.dessert.tiles;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.utils.TilesUtil;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.mifavor.widget.AlertDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* loaded from: classes.dex */
public class ManualRecordTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback, PerformanceModeController.PerformanceModeCallback {
    private final HashMap A;
    private long B;
    private boolean v;
    private final Map w;
    private boolean x;
    private String y;
    private Dialog z;

    public ManualRecordTile(QSTile.Host host) {
        super(host);
        this.w = new HashMap();
        this.A = SharedPreferencesUtil.k(this.f6153i).c();
        PerformanceModeController.S().P(this);
        SystemMgr.y(this.f6153i).h(this);
    }

    private void D0(boolean z) {
        Integer num;
        if (z) {
            this.f6163s = SystemMgr.t();
            this.B = System.currentTimeMillis();
        } else if (this.w.containsKey(this.f6163s) && (num = (Integer) this.w.get(this.f6163s)) != null) {
            this.w.put(this.f6163s, Integer.valueOf(((int) ((System.currentTimeMillis() - this.B) / 1000)) + num.intValue()));
        }
    }

    private void E0() {
        String string = Settings.Global.getString(this.f6153i.getContentResolver(), "gameassist_click_floating_buttons");
        this.y = string;
        this.x = false;
        final ArrayList<String> e2 = TilesUtil.e(string);
        if (!e2.isEmpty()) {
            for (String str : e2) {
                if (!this.w.containsKey(str)) {
                    this.w.put(str, 0);
                }
            }
            this.w.entrySet().removeIf(new Predicate() { // from class: cn.nubia.gameassist.dessert.tiles.f
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean I0;
                    I0 = ManualRecordTile.I0(e2, (Map.Entry) obj);
                    return I0;
                }
            });
            if (this.w.containsKey(this.f6163s)) {
                this.x = true;
            }
        }
        GaLog.a("ManualRecordTile", "checkCurPackage, mIsGameScene:" + this.f6162r + " mCurValue:" + this.y + " mCurPackage:" + this.f6163s);
    }

    private void F0() {
        Integer num = (Integer) this.w.remove(this.f6163s);
        if (this.w.isEmpty()) {
            Settings.Global.putInt(this.f6153i.getContentResolver(), "manual_record", 0);
        }
        GaLog.a("ManualRecordTile", "closeManualRecord, mList=" + this.w + ",tt:" + num);
        if (num != null) {
            NubiaTrackManager.p().m(this.f6163s, ((int) ((System.currentTimeMillis() - this.B) / 1000)) + num.intValue());
        }
        Settings.Global.putString(this.f6153i.getContentResolver(), "gameassist_click_floating_buttons", G0());
    }

    private String G0() {
        return TilesUtil.d(new ArrayList(this.w.keySet()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean I0(List list, Map.Entry entry) {
        return !list.contains(entry.getKey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J0() {
        String string;
        Dialog dialog = this.z;
        if (dialog == null || !dialog.isShowing()) {
            if (ZteFeature.isSupportGameRandomRecord()) {
                string = this.f6153i.getString(Utils.b0() ? R.string.ic_qs_manual_record_dialog_warning_international_text : ZteFeature.isRedMagicProduct() ? R.string.ic_qs_manual_record_dialog_warning_redmagic_text : R.string.ic_qs_manual_record_dialog_warning_flagship_text);
            } else {
                string = this.f6153i.getString(R.string.ic_qs_manual_record_dialog_warning_text_new);
                if (!Utils.b0()) {
                    string = string + this.f6153i.getString(R.string.ic_qs_manual_record_dialog_warning_prompt_text);
                }
            }
            AlertDialog a2 = new AlertDialog.Builder(this.f6153i, com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).m(this.f6153i.getString(com.zte.gameassist.common.R.string.dialog_default_title)).c(true).e(string).i(com.zte.gameassist.common.R.string.single_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.dessert.tiles.ManualRecordTile.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    ManualRecordTile.this.L0();
                    GaLog.a("ManualRecordTile", "showAlertDialog: mCurPackage : " + ((QSTile) ManualRecordTile.this).f6163s);
                    dialogInterface.dismiss();
                    ManualRecordTile.this.r0(true);
                }
            }).f(com.zte.gameassist.common.R.string.single_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.dessert.tiles.ManualRecordTile.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                    ManualRecordTile.this.r0(true);
                }
            }).a();
            this.z = a2;
            a2.getWindow().setType(2008);
            this.z.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.z.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: cn.nubia.gameassist.dessert.tiles.ManualRecordTile.3
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    GaLog.a("ManualRecordTile", "showAlertDialog: onCancel ");
                    ManualRecordTile.this.r0(true);
                }
            });
            r0(false);
            this.z.show();
            GameAssistDialog.f(this.z.getWindow());
        }
    }

    private void K0(int i2) {
        this.f6152h.b();
        ToastUtil.a(this.f6153i.getString(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L0() {
        if (this.f6162r) {
            this.B = System.currentTimeMillis();
            this.w.put(this.f6163s, 0);
            GaLog.a("ManualRecordTile", "startManualRecord, mCurPackage:" + this.f6163s + " mList=" + this.w);
            Settings.Global.putInt(this.f6153i.getContentResolver(), "manual_record", 1);
            Settings.Global.putString(this.f6153i.getContentResolver(), "gameassist_click_floating_buttons", G0());
            this.A.remove(this.f6163s);
            SharedPreferencesUtil.k(this.f6153i).M(this.A);
        }
    }

    protected int H0() {
        return ZteFeature.isSupportGameRandomRecord() ? R.string.ic_qs_your_record : R.string.ic_qs_manual_record;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        if (this.f6160p.q0() == 1 || Utils.A(this.f6153i)) {
            return false;
        }
        E0();
        GaLog.a("ManualRecordTile", "handleClick, mIsGameScene:" + this.f6162r + " mInWhiteList:" + this.x + " curlist:" + this.w);
        boolean isSupportGameRandomRecord = ZteFeature.isSupportGameRandomRecord();
        if (!this.f6162r) {
            K0(R.string.toast_unsupport_app);
            return false;
        }
        int i2 = Settings.Global.getInt(this.f6153i.getContentResolver(), "redmagic_and_wifidisplay_status", 0);
        GaLog.a("ManualRecordTile", "handleClick: redmagicAndWifidisplayStatus:" + i2);
        if (Utils.J(this.f6153i) && i2 == 0) {
            GaLog.a("ManualRecordTile", "handleClick, is Mirror open");
            K0(isSupportGameRandomRecord ? R.string.ic_qs_manual_record_mirror_toast_text2 : R.string.ic_qs_manual_record_mirror_toast_text);
            return true;
        }
        if (this.x) {
            F0();
        } else {
            if (Utils.O(this.f6153i)) {
                GaLog.a("ManualRecordTile", "handleClick, is Small Mirror open");
                K0(isSupportGameRandomRecord ? R.string.ic_qs_manual_record_small_mirror_toast_text2 : R.string.ic_qs_manual_record_small_mirror_toast_text);
                return true;
            }
            GaLog.a("ManualRecordTile", "handleClick, is not Small Mirror open");
            if (Utils.P(this.f6153i)) {
                GaLog.a("ManualRecordTile", "handleClick, is SmallWindow Open ");
                K0(isSupportGameRandomRecord ? R.string.ic_qs_manual_record_toast_text2 : R.string.ic_qs_manual_record_toast_text);
                return true;
            }
            if (Utils.A(this.f6153i)) {
                GaLog.a("ManualRecordTile", "handleClick,is ChickenMode ");
                K0(isSupportGameRandomRecord ? R.string.ic_qs_manual_record_chicken_mode_toast_text2 : R.string.ic_qs_manual_record_chicken_mode_toast_text);
                return true;
            }
            if (i2 == 1) {
                this.f6152h.b();
                L0();
                GaLog.a("ManualRecordTile", "handleClick,mCurPackage : " + this.f6163s);
            } else {
                if (Utils.J(this.f6153i)) {
                    GaLog.a("ManualRecordTile", "handleClick, is Mirror open");
                    K0(isSupportGameRandomRecord ? R.string.ic_qs_manual_record_mirror_toast_text2 : R.string.ic_qs_manual_record_mirror_toast_text);
                    return true;
                }
                this.f6152h.b();
                this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        ManualRecordTile.this.J0();
                    }
                });
            }
        }
        return true;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("gameassist_click_floating_buttons"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("gameassist_click_floating_buttons"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        E0();
        boolean z = false;
        int i2 = Settings.Global.getInt(this.f6153i.getContentResolver(), "manual_record", 0);
        boolean P = Utils.P(this.f6153i);
        boolean J = Utils.J(this.f6153i);
        int i3 = Settings.Global.getInt(this.f6153i.getContentResolver(), "redmagic_and_wifidisplay_status", 0);
        boolean parseBoolean = Boolean.parseBoolean((String) this.A.get(this.f6163s));
        GaLog.a("ManualRecordTile", "handleUpdateState: mIsGameScene:" + this.f6162r + " mCurValue:" + this.y + " value:" + i2 + " isSmallWindow:" + P + " isMirror:" + J + " redmagicAndWifidisplayStatus:" + i3 + " mode:" + this.f6160p.q0() + " isChicken:" + Utils.A(this.f6153i) + " mInWhiteList:" + this.x + " mIsCloseByEnduranceMode:" + parseBoolean);
        if (this.f6160p.q0() == 1 || Utils.A(this.f6153i)) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_manual_record_unpress);
            if (this.x) {
                F0();
                this.A.put(this.f6163s, String.valueOf(true));
                SharedPreferencesUtil.k(this.f6153i).M(this.A);
            }
            this.t = false;
            z = parseBoolean;
        } else {
            this.t = true;
            if (parseBoolean) {
                L0();
            }
            if (this.f6162r && this.x && i2 == 1 && !P && (i3 == 1 || !J)) {
                state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_manual_record_light);
                z = true;
            } else {
                state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_manual_record_normal);
            }
        }
        state.f6175i = z;
        state.f6169c = this.f6153i.getString(H0());
        if (i2 == 1 && this.B == 0) {
            D0(SystemMgr.H());
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_turn_on_record".equals(str)) {
            e0(null);
            if (!this.t) {
                GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
                return;
            }
            if (this.f6157m.f6175i) {
                GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, H0());
                return;
            }
            if (!S()) {
                GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
                return;
            }
            E0();
            if (this.x) {
                GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, H0());
                return;
            } else {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, true);
                return;
            }
        }
        if ("game_turn_off_record".equals(str)) {
            e0(null);
            if (!this.t) {
                GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
                return;
            }
            if (!this.f6157m.f6175i) {
                GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, H0());
                return;
            }
            if (!S()) {
                GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
                return;
            }
            E0();
            if (this.x) {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, true);
            } else {
                GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, H0());
            }
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void m0(boolean z) {
        super.m0(z);
        D0(z);
        if (z) {
            o0();
            return;
        }
        Dialog dialog = this.z;
        if (dialog != null && dialog.isShowing()) {
            this.z.dismiss();
            r0(true);
        }
        this.z = null;
    }

    @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
    public void n(String str, int i2, boolean z) {
        o0();
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
        Dialog dialog = this.z;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        GameAssistDialog.f(this.z.getWindow());
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        GaLog.a("ManualRecordTile", "onChange: uri= " + uri);
        o0();
    }
}
