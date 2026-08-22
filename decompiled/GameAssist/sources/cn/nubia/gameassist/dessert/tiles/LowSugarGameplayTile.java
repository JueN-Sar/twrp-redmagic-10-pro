package cn.nubia.gameassist.dessert.tiles;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.LowSugarComService;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.R;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class LowSugarGameplayTile extends QSTile implements LowSugarComService.ICallback {
    private LowSugarComService v;
    private ContentObserver w;

    public LowSugarGameplayTile(QSTile.Host host) {
        super(host);
        this.w = new ContentObserver(null) { // from class: cn.nubia.gameassist.dessert.tiles.LowSugarGameplayTile.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                GaLog.b(((QSTile) LowSugarGameplayTile.this).f6151c, "mAiDataObserver onChange uri = " + uri.toString());
                if ("com.zte.aispeaker.contentProvider".equals(uri.getAuthority())) {
                    String queryParameter = uri.getQueryParameter("aigcUserLogged");
                    GaLog.a(((QSTile) LowSugarGameplayTile.this).f6151c, "mAiDataObserver aigcUserLogged = " + queryParameter);
                    ((QSTile) LowSugarGameplayTile.this).f6153i.getContentResolver().unregisterContentObserver(LowSugarGameplayTile.this.w);
                    if ("true".equals(queryParameter)) {
                        Settings.Global.putInt(((QSTile) LowSugarGameplayTile.this).f6153i.getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 1);
                        LowSugarGameplayTile.this.v.g(((QSTile) LowSugarGameplayTile.this).f6153i.getString(R.string.aiagent_turn_on_function, ((QSTile) LowSugarGameplayTile.this).f6153i.getString(LowSugarGameplayTile.this.K0())), ((QSTile) LowSugarGameplayTile.this).f6153i);
                    } else if (!"false".equals(queryParameter)) {
                        GaLog.b(((QSTile) LowSugarGameplayTile.this).f6151c, "mAiDataObserver is not user login info!");
                    } else {
                        Settings.Global.putInt(((QSTile) LowSugarGameplayTile.this).f6153i.getContentResolver(), "nubia_account_login_status", -1);
                        LowSugarGameplayTile.this.v.e(2);
                    }
                }
            }
        };
        this.v = (LowSugarComService) Router.getInstance().getService(LowSugarComService.class.getSimpleName());
    }

    protected int K0() {
        return cn.nubia.gameassist.R.string.ic_qs_low_sugar;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        String t = SystemMgr.t();
        this.f6163s = t;
        if (!this.v.f(t)) {
            return true;
        }
        this.f6152h.b();
        this.v.d();
        return true;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        this.v.i(z, this);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        this.f6163s = SystemMgr.t();
        state.f6169c = this.f6153i.getString(K0());
        if (!this.v.f(this.f6163s)) {
            state.f6175i = false;
            state.f6168b = QSTile.ResourceIcon.b(cn.nubia.gameassist.R.drawable.game_ic_qs_low_sugar_gameplay_unpress);
        } else {
            boolean c2 = this.v.c();
            state.f6175i = c2;
            state.f6168b = QSTile.ResourceIcon.b(c2 ? cn.nubia.gameassist.R.drawable.game_ic_qs_low_sugar_gameplay_light : cn.nubia.gameassist.R.drawable.game_ic_qs_low_sugar_gameplay_normal);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        InMsg f2;
        if (ZteFeature.isSupportLowSugar()) {
            GaLog.a(this.f6151c, "onAICommnadNotify cmd = " + str + ", inMsg.getType = " + inMsg.j());
            if (!"game_start_recognition_game_task".equals(str)) {
                if ("positive".equals(str) && (f2 = inMsg.f()) != null && "game_start_recognition_game_task".equals(f2.e())) {
                    if (!this.v.j(this.f6153i)) {
                        this.f6153i.getContentResolver().registerContentObserver(LowSugarComService.f5873a, true, this.w);
                        this.v.h(this.f6153i);
                        return;
                    } else {
                        this.f6153i.getContentResolver().unregisterContentObserver(this.w);
                        Settings.Global.putInt(this.f6153i.getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 1);
                        GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, K0());
                        return;
                    }
                }
                return;
            }
            if (inMsg.j() != 1) {
                if (Settings.Global.getInt(this.f6153i.getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 0) != 1) {
                    GameAgentUtil.b(this.f6153i, iGameAssistClientCallback, inMsg, K0());
                    return;
                }
                Context context = this.f6153i;
                GameAgentUtil.i(context, iGameAssistClientCallback, inMsg, context.getString(cn.nubia.gameassist.R.string.ic_qs_low_sugar_manual_start_processing), true);
                this.v.b();
                return;
            }
            String a2 = inMsg.a();
            GaLog.a(this.f6151c, "onAICommnadNotify pkgName = " + a2);
            if (TextUtils.isEmpty(a2)) {
                return;
            }
            try {
                this.f6153i.startActivity(this.f6153i.getPackageManager().getLaunchIntentForPackage(a2));
            } catch (Exception e2) {
                GaLog.a(this.f6151c, "onAICommnadNotify startActivity and has exception = " + e2);
            }
        }
    }

    @Override // cn.nubia.componentcenter.service.LowSugarComService.ICallback
    public void u() {
        o0();
    }
}
