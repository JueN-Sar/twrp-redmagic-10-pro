package cn.nubia.gameassist.operation;

import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.systemwrapper.InputChannelWrapper;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.EventListener;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class SubViewController extends BaseViewController<ViewGroup> implements View.OnClickListener {

    /* renamed from: q, reason: collision with root package name */
    protected GameAssistWindowManager f6745q;

    /* renamed from: r, reason: collision with root package name */
    private View f6746r;

    /* renamed from: s, reason: collision with root package name */
    private ImageView f6747s;
    private View t;
    private View u;
    private final EventListener v;

    public SubViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        EventListener eventListener = new EventListener() { // from class: cn.nubia.gameassist.operation.SubViewController.1
            @Override // com.zte.gameassist.common.EventListener
            public void a(int i2, Object... objArr) {
                if (i2 == 4) {
                    SubViewController.this.D((String) objArr[0], (String) objArr[1], (IGameAssistClientCallback) objArr[2], (InMsg) objArr[3]);
                }
            }
        };
        this.v = eventListener;
        this.f6745q = gameAssistWindowManager;
        EventListenerMgr.b(eventListener, 4);
    }

    private void S() {
        Intent intent = new Intent("cn.nubia.tgk.TGKSERVICE");
        intent.setPackage("cn.nubia.gamelauncher");
        intent.putExtra("type", 13);
        intent.putExtra("packagename", SystemMgr.t());
        this.f6117c.startService(intent);
    }

    private void T() {
        GaLog.a("SubViewController", "closeNote");
        String j2 = Utils.j();
        Intent intent = new Intent("cn.nubia.gamenotes.ACTION_SHOW_WINDOWN");
        intent.setPackage("cn.nubia.gamenotes");
        intent.putExtra("request_code", 5);
        intent.putExtra("packageName", j2);
        j().startService(intent);
        this.f6745q.g0("clicksNote");
    }

    private void V() {
        GaLog.a("SubViewController", "startHome");
        if (ZteFeature.isSupport3D()) {
            Utils.a0(0);
        } else {
            InputChannelWrapper.b();
        }
    }

    private void W() {
        GaLog.a("SubViewController", "startKeytrue");
        if (!SystemMgr.H() && RotationMgr.j()) {
            ToastUtil.a(j().getString(R.string.touch_key_toast));
            return;
        }
        j().sendBroadcast(new Intent(ZteFeature.isSupportTouchGameKey() ? "cn.nubia.intent.action.TOUCH_GAME_KEY_MAP_OPTION" : "cn.zte.intent.action.camerakey_virtual_touch"));
        this.f6745q.g0("clickskey");
        NubiaTrackManager.p().l("touch", "app_name", Settings.System.getString(j().getContentResolver(), "touch_game_key_enable_game_list"));
    }

    private void X() {
        GaLog.a("SubViewController", "startNote");
        String j2 = Utils.j();
        Intent intent = new Intent("cn.nubia.gamenotes.ACTION_SHOW_WINDOWN");
        intent.setPackage("cn.nubia.gamenotes");
        intent.putExtra("request_code", 1);
        intent.putExtra("packageName", j2);
        j().startService(intent);
        this.f6745q.g0("clicksNote");
    }

    private void Y() {
        GaLog.a("SubViewController", "startSetting");
        Intent intent = new Intent("cn.nubia.intent.action.PERFORMANCE_MODE_OPTION");
        intent.putExtra("packageName", SystemMgr.t());
        intent.putExtra("activity", SystemMgr.s());
        if (SystemMgr.L()) {
            intent.putExtra("shortcutLabel", SystemMgr.u());
            intent.putExtra("isShortCut", true);
        }
        j().sendBroadcast(intent);
        this.f6745q.g0("clickSetting");
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_buttons;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    protected void D(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_open_touch_key".equals(str)) {
            if (Utils.L(SystemMgr.t())) {
                GameAgentUtil.m(this.f6117c, iGameAssistClientCallback, inMsg);
                return;
            } else if (!ZteFeature.isSupportTouchGameKey() && !ZteFeature.isSupportTouchCameraKey()) {
                GameAgentUtil.m(this.f6117c, iGameAssistClientCallback, inMsg);
                return;
            } else {
                W();
                GameAgentUtil.e(this.f6117c, iGameAssistClientCallback, inMsg, R.string.touch_key);
                return;
            }
        }
        if ("game_open_game_note".equals(str)) {
            X();
            if (ZteFeature.isSupportStrategyStation()) {
                GameAgentUtil.e(this.f6117c, iGameAssistClientCallback, inMsg, R.string.plugin_game_strategy_station_name);
                return;
            } else {
                GameAgentUtil.e(this.f6117c, iGameAssistClientCallback, inMsg, R.string.plugin_game_note_name);
                return;
            }
        }
        if (!"game_turn_off_touch_key".equals(str)) {
            if ("game_turn_off_game_note".equals(str)) {
                T();
                if (ZteFeature.isSupportStrategyStation()) {
                    GameAgentUtil.d(this.f6117c, iGameAssistClientCallback, inMsg, R.string.plugin_game_strategy_station_name);
                    return;
                } else {
                    GameAgentUtil.d(this.f6117c, iGameAssistClientCallback, inMsg, R.string.plugin_game_note_name);
                    return;
                }
            }
            return;
        }
        if (Utils.L(SystemMgr.t())) {
            GameAgentUtil.m(this.f6117c, iGameAssistClientCallback, inMsg);
        } else if (!ZteFeature.isSupportTouchGameKey() && !ZteFeature.isSupportTouchCameraKey()) {
            GameAgentUtil.m(this.f6117c, iGameAssistClientCallback, inMsg);
        } else {
            S();
            GameAgentUtil.d(this.f6117c, iGameAssistClientCallback, inMsg, R.string.touch_key);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        View view = this.f6746r;
        if (view != null) {
            view.setOnClickListener(null);
            this.f6746r = null;
        }
        ImageView imageView = this.f6747s;
        if (imageView != null) {
            imageView.setOnClickListener(null);
            this.f6747s = null;
        }
        View view2 = this.t;
        if (view2 != null) {
            view2.setOnClickListener(null);
            this.t = null;
        }
        View view3 = this.u;
        if (view3 != null) {
            view3.setOnClickListener(null);
            this.u = null;
        }
        EventListenerMgr.i(this.v);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    /* renamed from: U, reason: merged with bridge method [inline-methods] */
    public void o(ViewGroup viewGroup) {
        View i2 = i(R.id.game_assist_button_home);
        this.f6746r = i2;
        GlobalSearchUtil.r(i2, "game_assist_button_home");
        ImageView imageView = (ImageView) i(R.id.game_assist_button_note);
        this.f6747s = imageView;
        GlobalSearchUtil.r(imageView, "game_assist_button_note");
        if (ZteFeature.isSupportGameNote()) {
            this.f6747s.setVisibility(0);
        }
        if (ZteFeature.isSupportStrategyStation()) {
            this.f6747s.setImageResource(R.drawable.game_assist_button_strategy_station);
        } else {
            this.f6747s.setImageResource(R.drawable.game_assist_button_note);
        }
        this.t = i(R.id.game_assist_button_key);
        if (ZteFeature.isSupportTouchGameKey() || ZteFeature.isSupportTouchCameraKey()) {
            if (Utils.L(SystemMgr.t())) {
                this.t.setVisibility(8);
            } else {
                this.t.setVisibility(0);
            }
            if (ZteFeature.isSupportTouchGameKey()) {
                GlobalSearchUtil.r(this.t, "game_assist_button_key");
            } else {
                GlobalSearchUtil.r(this.t, "game_assist_button_camera");
            }
        }
        this.u = i(R.id.game_assist_button_setting);
        this.f6746r.setOnClickListener(this);
        this.f6747s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.u.setOnClickListener(this);
        GlobalSearchUtil.r(this.u, "game_assist_button_setting");
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        printWriter.println(str + "  mSettings=" + this.u);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.game_assist_button_home) {
            V();
            return;
        }
        if (id == R.id.game_assist_button_note) {
            X();
        } else if (id == R.id.game_assist_button_key) {
            W();
        } else if (id == R.id.game_assist_button_setting) {
            Y();
        }
    }
}
