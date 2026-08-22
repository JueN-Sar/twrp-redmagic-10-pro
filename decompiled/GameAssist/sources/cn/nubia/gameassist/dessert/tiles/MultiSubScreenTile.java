package cn.nubia.gameassist.dessert.tiles;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.multisubscreen.callback.StatusCallback;
import cn.nubia.multisubscreen.ui.MultiSubScreenSinkActivity;
import cn.nubia.multisubscreen.ui.MultiSubScreenSourceActivity;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class MultiSubScreenTile extends QSTile {
    private boolean v;
    private StatusCallback w;

    public MultiSubScreenTile(QSTile.Host host) {
        super(host);
        this.w = new StatusCallback() { // from class: cn.nubia.gameassist.dessert.tiles.MultiSubScreenTile.1
            @Override // cn.nubia.multisubscreen.callback.StatusCallback
            public void b(String str, int i2) {
                MultiSubScreenTile.this.o0();
            }
        };
    }

    private void A0(Context context) {
        if (MultiSubScreenUtils.f8174d == 2 && !MultiSubScreenUtils.v()) {
            z0();
            return;
        }
        Intent flags = new Intent(context, (Class<?>) MultiSubScreenSourceActivity.class).setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        flags.putExtra("IS_OPEN_SOURCE_ALT", false);
        context.startActivity(flags);
    }

    private void z0() {
        Intent intent = new Intent();
        intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        intent.setClass(GameAssistApplication.j(), MultiSubScreenSinkActivity.class);
        GameAssistApplication.j().startActivity(intent);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        if (MultiSubScreenUtils.d()) {
            A0(this.f6153i);
        } else {
            ToastUtil.a(this.f6153i.getString(R.string.multi_subscreen_connect_failure_wifi_bt));
        }
        this.f6152h.b();
        GaLog.e(this.f6151c, "Custom.handleClick");
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        Log.e(this.f6151c, "MultiSubScreenTile setListening listening = " + z);
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (!z) {
            MultiSubScreenUtils.N(this.w);
        } else {
            MultiSubScreenUtils.C(this.w);
            o0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6169c = this.f6153i.getString(R.string.ic_qs_multi_subscreen);
        if (MultiSubScreenUtils.f8174d == 2) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_multi_subscreen_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_multi_subscreen_off);
            state.f6175i = false;
        }
    }
}
