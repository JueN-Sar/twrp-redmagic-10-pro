package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemProperties;
import android.text.TextUtils;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.plugin.PluginUtils;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class SuperResolutionOldTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;
    private String w;

    public SuperResolutionOldTile(QSTile.Host host) {
        super(host);
    }

    private void A0(String str) {
        GaLog.e("SuperResolutionOldTile", "executeSuperResolution: operateType = " + str + " , mCurState = " + this.w);
        Intent intent = new Intent("cn.nubia.intent.action.PERFORMANCE_MODE_OPTION");
        intent.putExtra("packageName", SystemMgr.t());
        intent.putExtra("activity", SystemMgr.s());
        intent.putExtra("type", "super_resolution");
        intent.putExtra("operate_type", str);
        intent.putExtra("current_state", this.w);
        this.f6153i.sendBroadcast(intent);
        this.f6152h.b();
    }

    private boolean B0(String str) {
        String str2 = SystemProperties.get("persist.magic.super.resolution", "");
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        GaLog.e("SuperResolutionOldTile", "isAppEnableBeforeUpgrade: resolutionApps = " + str2 + " , packageName = " + str);
        String[] split = str2.split(",");
        int length = split.length;
        for (int i2 = 0; i2 < length; i2++) {
            String[] split2 = split[i2].split(":");
            String str3 = split2[0];
            String str4 = split2[1];
            if (str.equals(str3)) {
                this.w = str4;
                return !"0".equals(str4);
            }
        }
        return false;
    }

    private String z0(String str) {
        StringBuilder sb = new StringBuilder();
        if (str.length() > 25) {
            str = str.substring(str.length() - 25);
        }
        sb.append("persist.maso.");
        sb.append(str);
        GaLog.e("SuperResolutionOldTile", "constructPropertiesKeyByPkgName: targetPropertiesKey = " + sb.toString());
        return sb.toString();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void J(PrintWriter printWriter, String str) {
        super.J(printWriter, str);
        printWriter.println(str + "      SuperResolutionOldTile:");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("      mResolutionEnable = ");
        sb.append(this.t);
        printWriter.println(sb.toString());
        printWriter.println(str + "      mCurState = " + this.w);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("SuperResolutionOldTile", "handleClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r + " , mResolutionEnable = " + this.t);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a("SuperResolutionOldTile", "isInFreeformMode");
                return true;
            }
            this.u = true;
            if (this.t) {
                A0("close");
                this.t = false;
            } else {
                A0("open");
                this.t = true;
            }
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("SuperResolutionOldTile", "handleSettingsClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r + " , mTileEnable = " + this.t);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("SuperResolutionOldTile", "isInFreeformMode");
            return true;
        }
        if (this.t) {
            A0("switch");
            return false;
        }
        ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        QSTile.Icon icon;
        super.c0(state, obj);
        GaLog.e("SuperResolutionOldTile", "handleUpdateState: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r + " , mResolutionEnable = " + this.t);
        state.f6168b = QSTile.ResourceIcon.b((state.f6175i && this.f6162r) ? R.drawable.plugin_super_resolution_on : R.drawable.plugin_super_resolution_off);
        if (PluginUtils.f(this.f6153i).i()) {
            icon = QSTile.ResourceIcon.b((state.f6175i && this.f6162r) ? R.drawable.plugin_settings_on : R.drawable.plugin_settings_off);
        } else {
            icon = null;
        }
        state.f6171e = icon;
        state.f6169c = this.f6153i.getString(R.string.plugin_label_super_resolution);
        state.f6170d = this.f6153i.getString(R.string.plugin_label_super_resolution_introduction_new);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        String str = SystemProperties.get(z0(this.f6163s), "");
        if (TextUtils.isEmpty(str)) {
            return B0(this.f6163s);
        }
        this.w = str;
        return !"0".equals(str);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
