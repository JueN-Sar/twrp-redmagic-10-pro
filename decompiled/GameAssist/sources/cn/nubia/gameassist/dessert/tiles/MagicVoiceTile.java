package cn.nubia.gameassist.dessert.tiles;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class MagicVoiceTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;
    private final PackageManager w;
    private boolean x;
    private String y;
    private boolean z;

    public MagicVoiceTile(QSTile.Host host) {
        super(host);
        this.z = false;
        this.w = this.f6153i.getPackageManager();
    }

    private boolean A0() {
        String string = Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_magic_voice_enables");
        return !TextUtils.isEmpty(string) && string.contains(SystemMgr.t());
    }

    private void B0() {
        Intent intent = new Intent();
        intent.putExtra("nubia_game_magic_voice_event_type", 0);
        intent.putExtra("nubia_game_magic_voice_package_name", this.f6163s);
        intent.setClassName("cn.nubia.game.magicvoice", "cn.nubia.game.magicvoice.GameMagicVoiceService");
        this.f6153i.startService(intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
    
        r1 = cn.nubia.gameassist.R.string.ic_qs_magic_voice_no_permission;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void C0(com.zte.gameassist.aiagent.IGameAssistClientCallback r9, com.zte.gameassist.aiagent.bean.InMsg r10) {
        /*
            r8 = this;
            java.lang.String r0 = "MagicVoiceTile"
            boolean r1 = r8.f6162r
            r2 = 0
            if (r1 != 0) goto L1a
            if (r9 == 0) goto Le
            android.content.Context r0 = r8.f6153i
            com.zte.gameassist.aiagent.GameAgentUtil.l(r0, r9, r10, r2)
        Le:
            android.content.Context r8 = r8.f6153i
            int r9 = cn.nubia.gameassist.R.string.toast_unsupport_app
            java.lang.String r8 = r8.getString(r9)
            cn.nubia.gameassist.utils.ToastUtil.a(r8)
            return
        L1a:
            int r1 = cn.nubia.gameassist.R.string.ic_qs_magic_voice_no_permission_declare
            android.content.pm.PackageManager r3 = r8.w     // Catch: java.lang.NullPointerException -> L3f android.content.pm.PackageManager.NameNotFoundException -> L41
            java.lang.String r4 = com.zte.gameassist.common.SystemMgr.z()     // Catch: java.lang.NullPointerException -> L3f android.content.pm.PackageManager.NameNotFoundException -> L41
            r5 = 4096(0x1000, float:5.74E-42)
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r4, r5)     // Catch: java.lang.NullPointerException -> L3f android.content.pm.PackageManager.NameNotFoundException -> L41
            if (r3 == 0) goto L6f
            java.lang.String[] r3 = r3.requestedPermissions     // Catch: java.lang.NullPointerException -> L3f android.content.pm.PackageManager.NameNotFoundException -> L41
            if (r3 == 0) goto L6f
            int r4 = r3.length     // Catch: java.lang.NullPointerException -> L3f android.content.pm.PackageManager.NameNotFoundException -> L41
            r5 = r2
        L30:
            if (r5 >= r4) goto L6f
            r6 = r3[r5]     // Catch: java.lang.NullPointerException -> L3f android.content.pm.PackageManager.NameNotFoundException -> L41
            java.lang.String r7 = "android.permission.RECORD_AUDIO"
            boolean r6 = r7.equals(r6)     // Catch: java.lang.NullPointerException -> L3f android.content.pm.PackageManager.NameNotFoundException -> L41
            if (r6 == 0) goto L43
            int r1 = cn.nubia.gameassist.R.string.ic_qs_magic_voice_no_permission     // Catch: java.lang.NullPointerException -> L3f android.content.pm.PackageManager.NameNotFoundException -> L41
            goto L6f
        L3f:
            r3 = move-exception
            goto L46
        L41:
            r3 = move-exception
            goto L5b
        L43:
            int r5 = r5 + 1
            goto L30
        L46:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "get permission error = "
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.zte.gameassist.utils.GaLog.a(r0, r3)
            goto L6f
        L5b:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "getPackageInfo error = "
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.zte.gameassist.utils.GaLog.a(r0, r3)
        L6f:
            if (r9 == 0) goto L76
            android.content.Context r0 = r8.f6153i
            com.zte.gameassist.aiagent.GameAgentUtil.l(r0, r9, r10, r2)
        L76:
            android.content.Context r8 = r8.f6153i
            java.lang.String r8 = r8.getString(r1)
            cn.nubia.gameassist.utils.ToastUtil.a(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.dessert.tiles.MagicVoiceTile.C0(com.zte.gameassist.aiagent.IGameAssistClientCallback, com.zte.gameassist.aiagent.bean.InMsg):void");
    }

    private boolean D0(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg, boolean z) {
        this.z = Utils.y(this.f6153i, "cn.nubia.game.magicvoice");
        GaLog.a("MagicVoiceTile", "mInstall= " + this.z + "  mIsGameScene: " + this.f6162r + " mValue: " + this.x + " mCurPackage= " + this.f6163s);
        if (!this.z) {
            if (iGameAssistClientCallback != null) {
                GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
            }
            return true;
        }
        if (!this.f6162r || !z0()) {
            C0(iGameAssistClientCallback, inMsg);
            return true;
        }
        this.f6152h.b();
        if (Utils.P(this.f6153i)) {
            if (iGameAssistClientCallback != null) {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
            }
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("MagicVoiceTile", "isInFreeformMode");
            return true;
        }
        if (iGameAssistClientCallback == null) {
            B0();
            GaLog.a("MagicVoiceTile", "start Service");
        } else if (A0()) {
            if (z) {
                GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_magic_voice);
            } else {
                B0();
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, true);
            }
        } else if (z) {
            GameAgentUtil.a(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_magic_voice);
        } else {
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_magic_voice);
        }
        NubiaTrackManager.p().k("voice");
        return false;
    }

    private boolean z0() {
        return Utils.w("android.permission.RECORD_AUDIO", this.f6163s, SystemMgr.F());
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        return D0(null, null, !this.f6157m.f6175i);
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("nubia_magic_voice_enables"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("nubia_magic_voice_enables"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        if (!this.z) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_magicvoice_unpress);
            state.f6175i = false;
            state.f6169c = this.f6153i.getString(R.string.ic_qs_magic_voice);
        } else if (!this.f6162r) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_magicvoice_normal);
            state.f6175i = false;
            state.f6169c = this.f6153i.getString(R.string.ic_qs_magic_voice);
        } else if (this.x) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_magicvoice_light);
            state.f6175i = true;
            state.f6169c = this.y;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_magicvoice_normal);
            state.f6175i = false;
            state.f6169c = this.f6153i.getString(R.string.ic_qs_magic_voice);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_turn_on_voice_style_config".equals(str)) {
            D0(iGameAssistClientCallback, inMsg, true);
            return;
        }
        if ("game_turn_off_voice_style_config".equals(str)) {
            D0(iGameAssistClientCallback, inMsg, false);
        } else if ("positive".equals(str) && "game_turn_on_voice_style_config".equals(inMsg.f().e())) {
            B0();
            GameAgentUtil.c(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_magic_voice);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        this.f6163s = SystemMgr.t();
        this.z = Utils.y(this.f6153i, "cn.nubia.game.magicvoice");
        String string = Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_magic_voice_enables");
        boolean z2 = !"-1".equals(Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_youme_accesstoken"));
        GaLog.a("MagicVoiceTile", "onChange: mCurPackage=" + this.f6163s + " isLogin=" + z2 + " enables=" + string);
        int i2 = 0;
        this.x = false;
        if (z2 && !TextUtils.isEmpty(string)) {
            if (string.contains(this.f6163s + ":") && string.contains(",")) {
                String[] split = string.split(",");
                int length = split.length;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    String str = split[i2];
                    if (!TextUtils.isEmpty(str)) {
                        if (str.contains(this.f6163s + ":")) {
                            GaLog.a("MagicVoiceTile", "onChange: mState.label : " + this.f6157m.f6169c);
                            String[] split2 = str.split(":");
                            this.x = true;
                            if (split2.length == 2) {
                                this.y = split2[1];
                                GaLog.a("MagicVoiceTile", "onChange: mCurLabel : " + this.y);
                            }
                        }
                    }
                    i2++;
                }
            }
        }
        o0();
    }
}
