package cn.nubia.gameassist.dessert.tiles;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.AppsHelper;
import cn.nubia.gameassist.utils.TilesUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.systemwrapper.ActivityManagerWrapper;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.mifavor.widget.AlertDialog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.Objects;

/* loaded from: classes.dex */
public class GameThirdAppTitle extends QSTile implements ObserverManager.SettingCallback {
    private static PackageManager y;
    private boolean v;
    private Dialog w;
    private View.OnClickListener x;

    public GameThirdAppTitle(QSTile.Host host) {
        super(host);
        this.x = new View.OnClickListener() { // from class: cn.nubia.gameassist.dessert.tiles.GameThirdAppTitle.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameThirdAppTitle.this.C0();
                if (view.getId() == R.id.sourtitle) {
                    GameThirdAppTitle.this.I0();
                } else if (view.getId() == R.id.twintitle) {
                    GameThirdAppTitle.this.J0();
                }
                GameThirdAppTitle.this.w.dismiss();
            }
        };
        y = this.f6153i.getPackageManager();
    }

    private Drawable E0() {
        Drawable drawable = null;
        try {
            drawable = y.getApplicationInfo(F0(), 0).loadIcon(y);
            GaLog.a(this.f6151c, "getIcon: icon=" + drawable);
            return drawable;
        } catch (Exception e2) {
            GaLog.a(this.f6151c, "updatePreference: pkg=" + e2.getMessage());
            return drawable;
        }
    }

    private Drawable G0() {
        Drawable E0 = E0();
        return E0 != null ? y.getUserBadgedIcon(E0, ActivityManagerWrapper.b().c(this.f6153i, 9999)) : E0;
    }

    private void H0() {
        Dialog dialog = this.w;
        if (dialog == null || !dialog.isShowing()) {
            View f2 = InflaterHelper.f(R.layout.qs_wechat_dialog, null);
            TextView textView = (TextView) f2.findViewById(R.id.sourtitle);
            textView.setText(N());
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, E0(), (Drawable) null, (Drawable) null);
            TextView textView2 = (TextView) f2.findViewById(R.id.twintitle);
            textView2.setText(N());
            textView2.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, G0(), (Drawable) null, (Drawable) null);
            textView.setOnClickListener(this.x);
            textView2.setOnClickListener(this.x);
            AlertDialog a2 = new AlertDialog.Builder(this.f6153i, com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).l(R.string.app_clone).n(f2).f(com.zte.gameassist.common.R.string.single_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.dessert.tiles.GameThirdAppTitle.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    GameThirdAppTitle.this.w.dismiss();
                }
            }).a();
            this.w = a2;
            a2.getWindow().setType(2038);
            this.w.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.w.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I0() {
        GaLog.a(this.f6151c, "--->startApp() packageName : " + F0());
        Intent intent = new Intent();
        intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        intent.setComponent(new ComponentName(F0(), D0()));
        ActivityManagerWrapper.b().f(intent, this.f6153i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J0() {
        GaLog.a(this.f6151c, "--->startTwinApp() packageName : " + F0());
        Intent intent = new Intent();
        intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        intent.setComponent(new ComponentName(F0(), D0()));
        ActivityManagerWrapper.b().g(intent, this.f6153i, ActivityManagerWrapper.b().c(this.f6153i, 9999));
    }

    public void C0() {
        int i2;
        String F0 = F0();
        if (TextUtils.isEmpty(F0)) {
            return;
        }
        if (F0.equals("com.tencent.mm")) {
            i2 = 2;
        } else if (!F0.equals("com.tencent.mobileqq")) {
            return;
        } else {
            i2 = 3;
        }
        GaLog.a(this.f6151c, "closeCurrentPipAppIfNeed() pkg : " + F0 + ", value : " + i2);
        Settings.Global.putInt(this.f6153i.getContentResolver(), "cn.nubia.close_freeform", i2);
    }

    public String D0() {
        return Utils.i(this.f6153i, F0());
    }

    public String F0() {
        return TilesUtil.l(O());
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public String O() {
        String str;
        QSTile.State state = this.f6157m;
        return (state == null || (str = state.f6177k) == null) ? "" : str;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        if (!this.f6157m.f6176j) {
            return false;
        }
        if (super.S()) {
            return true;
        }
        if (GameKeysWrapper.b().d(this.f6153i, F0(), 9999)) {
            H0();
        } else {
            I0();
        }
        Handler handler = this.f6155k;
        QSTile.Host host = this.f6152h;
        Objects.requireNonNull(host);
        handler.postDelayed(new e(host), 100L);
        NubiaTrackManager.p().k(O());
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (!z) {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("pip_pkg"), this);
        } else {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("pip_pkg"), this);
            o0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6169c = AppsHelper.b(F0());
        state.f6168b = QSTile.ResourceIcon.b(TilesUtil.m(state.f6177k));
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void m0(boolean z) {
        super.m0(z);
        if (z) {
            return;
        }
        Dialog dialog = this.w;
        if (dialog != null && dialog.isShowing()) {
            this.w.dismiss();
        }
        this.w = null;
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        Settings.Global.getUriFor("pip_pkg").equals(uri);
    }
}
