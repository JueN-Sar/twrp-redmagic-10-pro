package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class ChatAssistTile extends FloatButtonQSTile implements ObserverManager.SettingCallback {
    private boolean q0;
    private int r0;

    public ChatAssistTile(QSTile.Host host) {
        super(host);
        this.r0 = 0;
    }

    private void U1(int i2) {
        GaLog.e("ChatAssistTile", "startChatAssistService: type = " + i2);
        Intent intent = new Intent();
        if (Utils.R()) {
            intent.setAction("cn.nubia.chatassistant.VOICEASSISTANTSERVICE");
            intent.setPackage("cn.nubia.gamelauncher");
        } else {
            intent.setAction("cn.zte.chatassistant.VOICEASSISTANTSERVICE");
            intent.setPackage("cn.zte.gamefloat");
        }
        intent.putExtra("currentPkg", this.f6163s);
        intent.putExtra("type", i2);
        if (i2 == 1) {
            intent.putExtra("pointX", this.D.x);
            intent.putExtra("pointY", this.D.y);
        }
        this.v.startService(intent);
    }

    private void V1() {
        if (!this.f6162r) {
            ToastUtil.a(this.v.getString(R.string.toast_unsupport_app));
            return;
        }
        try {
            Intent intent = new Intent();
            if (Utils.R()) {
                intent.setAction("cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity");
                intent.setPackage("cn.nubia.gamelauncher");
            } else {
                intent.setAction("cn.zte.gamefloat.chatassistant.ui.ChatAssistantSettingsActivity");
                intent.setPackage("cn.zte.gamefloat");
            }
            intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
            this.v.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        NubiaTrackManager.p().l("chat_assist", "app_name", Utils.j());
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    public void I1(boolean z) {
        GaLog.a("ChatAssistTile", "setFloatButtonListening ：" + z);
        if (this.q0 == z) {
            return;
        }
        this.q0 = z;
        if (z) {
            ObserverManager.c().b(this.v, Settings.Global.getUriFor("chat_assistant_show"), this);
        } else {
            ObserverManager.c().d(this.v, Settings.Global.getUriFor("chat_assistant_show"), this);
        }
    }

    protected int R1() {
        return ZteFeature.isSupportDemi() ? R.string.plugin_icon_demi_chat : R.string.plugin_icon_chat;
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile, cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("ChatAssistTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.v.getString(R.string.toast_unsupport_app));
            return false;
        }
        if (!Utils.P(this.v)) {
            this.u = true;
            return false;
        }
        ToastUtil.a(this.v.getString(R.string.game_close_pip));
        GaLog.a("ChatAssistTile", "isInFreeformMode");
        return true;
    }

    protected void S1() {
        this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.ChatAssistTile.1
            @Override // java.lang.Runnable
            public void run() {
                ChatAssistTile chatAssistTile = ChatAssistTile.this;
                View view = chatAssistTile.C;
                if (view != null) {
                    view.setBackgroundResource(chatAssistTile.T1() ? R.drawable.plugin_button_open_chat : R.drawable.plugin_button_close_chat);
                }
                GaLog.a("ChatAssistTile", "handleUpdateFloatButton= " + ChatAssistTile.this.T1());
            }
        });
    }

    boolean T1() {
        int i2 = Settings.Global.getInt(this.v.getContentResolver(), "chat_assistant_show", 0);
        this.r0 = i2;
        return i2 == 1;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("ChatAssistTile", "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.v.getString(R.string.toast_unsupport_app));
            return false;
        }
        if (Utils.P(this.v)) {
            ToastUtil.a(this.v.getString(R.string.game_close_pip));
            GaLog.a("ChatAssistTile", "isInFreeformMode");
            return true;
        }
        if (!this.f6157m.f6175i) {
            ToastUtil.a(this.v.getString(R.string.ic_qs_function_enable));
            return false;
        }
        this.f6152h.b();
        V1();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        Settings.Global.putInt(this.v.getContentResolver(), "chat_assist_floatbutton", this.t ? 1 : 0);
        state.f6168b = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.plugin_chat_on : R.drawable.plugin_chat_off);
        state.f6171e = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.plugin_settings_on : R.drawable.plugin_settings_off);
        if (ZteFeature.isSupportDemi()) {
            state.f6169c = this.v.getString(R.string.plugin_icon_demi_chat);
            state.f6170d = this.v.getString(R.string.plugin_chat_demi_introduction);
        } else {
            state.f6169c = this.v.getString(R.string.plugin_icon_chat);
            state.f6170d = this.v.getString(R.string.plugin_chat_introduction);
        }
        GaLog.a("ChatAssistTile", "handleUpdateState= " + state.f6169c + " " + state.f6175i + " , mIsGameScene = " + this.f6162r);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    protected void c1() {
        GaLog.e("ChatAssistTile", "callStopPluginService ChatAssist : " + this.t);
        U1(0);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return this.f6157m.f6175i;
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    int j1() {
        return 126;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if (str.equals("game_turn_on_chat_assit") && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.f6157m.f6175i) {
                S();
            }
            if (this.r0 == 0) {
                z1();
            }
            GameAgentUtil.e(this.v, iGameAssistClientCallback, inMsg, R1());
        }
        if (str.equals("game_turn_off_chat_assit") && i0(iGameAssistClientCallback, inMsg)) {
            if (this.f6157m.f6175i) {
                S();
            }
            if (this.r0 != 0) {
                U1(0);
            }
            GameAgentUtil.d(this.v, iGameAssistClientCallback, inMsg, R1());
        }
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    String k1() {
        return this.v.getString(R.string.plugin_float_button_chat);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    int l1() {
        return 178;
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    String n1() {
        return "chat_assit";
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    void q1() {
        this.f6157m.f6175i = false;
        WindowManager.LayoutParams layoutParams = this.D;
        if (layoutParams == null) {
            GaLog.b("ChatAssistTile", "mLayoutParams is null");
            return;
        }
        layoutParams.x = 235;
        layoutParams.y = 323;
        GaLog.e("ChatAssistTile", "initDefaultValue: " + this.B);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    View r1() {
        return InflaterHelper.f(R.layout.plugin_button_root_chat, null);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        GaLog.a("ChatAssistTile", "refreshState-->");
        o0();
        S1();
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    void z1() {
        this.r0 = Settings.Global.getInt(this.v.getContentResolver(), "chat_assistant_show", 0);
        GaLog.e("ChatAssistTile", "handleClick: mIsGameScene = " + this.f6162r + " , isChatAssistEnable = " + this.r0);
        if (!this.f6162r) {
            ToastUtil.a(this.v.getString(R.string.toast_unsupport_app));
        } else if (this.r0 == 0) {
            U1(1);
            this.r0 = 1;
        } else {
            this.r0 = 0;
            U1(0);
        }
        NubiaTrackManager.p().u();
    }
}
