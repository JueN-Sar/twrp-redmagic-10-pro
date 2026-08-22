package cn.nubia.gameassist.pips;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.tiles.e;
import cn.nubia.gameassist.utils.AppsHelper;
import cn.nubia.gameassist.utils.RecycleWatch;
import cn.nubia.gameassist.utils.TilesUtil;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.mifavor.widget.AlertDialog;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes.dex */
public class PipTiles extends QSTile {
    private final View.OnClickListener A;
    protected final QSTile.Host v;
    protected final Context w;
    private final String x;
    private final String y;
    private Dialog z;

    public PipTiles(QSTile.Host host, String str, String str2) {
        super(host);
        this.A = new View.OnClickListener() { // from class: cn.nubia.gameassist.pips.PipTiles.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.getId() == R.id.sourtitle) {
                    PipTiles.this.J0(false);
                } else if (view.getId() == R.id.twintitle) {
                    PipTiles.this.J0(true);
                }
                if (PipTiles.this.z != null) {
                    PipTiles.this.z.dismiss();
                }
            }
        };
        this.v = host;
        this.w = host.getContext();
        this.x = str2;
        this.y = str;
        RecycleWatch.j(this, 16);
    }

    private boolean E0() {
        return GameKeysWrapper.b().d(this.w, D0(), 999);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F0(DialogInterface dialogInterface, int i2) {
        this.z.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G0(String str, boolean z) {
        AppsHelper.l(this.w, str, z ? 999 : 0);
    }

    private void H0(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("pip_app_name", str);
        bundle.putString("background_app_name", Utils.j());
        NubiaTrackManager.p().v("game_pip_app", bundle);
    }

    private void I0() {
        Dialog dialog = this.z;
        if (dialog == null || !dialog.isShowing()) {
            GaLog.a("PipTiles", "showDialog(" + D0() + ")");
            View f2 = InflaterHelper.f(R.layout.qs_wechat_dialog, null);
            TextView textView = (TextView) f2.findViewById(R.id.sourtitle);
            textView.setText(N());
            Drawable f3 = AppsHelper.f(this.w, this.x);
            int dimensionPixelSize = this.w.getResources().getDimensionPixelSize(R.dimen.pip_dialog_icon_width);
            if (f3 != null) {
                f3.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            }
            textView.setCompoundDrawables(null, f3, null, null);
            TextView textView2 = (TextView) f2.findViewById(R.id.twintitle);
            textView2.setText(N());
            Drawable j2 = AppsHelper.j(this.w, this.x);
            if (j2 != null) {
                j2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            }
            textView2.setCompoundDrawables(null, j2, null, null);
            textView.setOnClickListener(this.A);
            textView2.setOnClickListener(this.A);
            AlertDialog a2 = new AlertDialog.Builder(this.w, com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).l(R.string.app_clone).c(true).n(f2).f(com.zte.gameassist.common.R.string.single_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.pips.c
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    PipTiles.this.F0(dialogInterface, i2);
                }
            }).a();
            this.z = a2;
            a2.getWindow().setType(2008);
            this.z.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.z.show();
            GameAssistDialog.f(this.z.getWindow());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J0(final boolean z) {
        try {
            final String D0 = D0();
            GaLog.a("PipTiles", "start pn:" + D0 + ",twin:" + z);
            this.f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.pips.b
                @Override // java.lang.Runnable
                public final void run() {
                    PipTiles.this.G0(D0, z);
                }
            }, 300L);
            H0(D0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String D0() {
        return this.x;
    }

    @Override // cn.nubia.gameassist.common.QSTile, java.lang.Comparable
    /* renamed from: H */
    public int compareTo(QSTile qSTile) {
        return Collator.getInstance().compare(N(), qSTile.N());
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public String N() {
        return this.y;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public String O() {
        return D0();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        String D0 = D0();
        if (!Utils.y(this.w, D0)) {
            GaLog.a("PipTiles", "app not install");
            this.v.b();
            ToastUtil.a(this.w.getString(R.string.app_not_install));
            return true;
        }
        if (Utils.z(this.w, D0)) {
            GaLog.a("PipTiles", "app is disabled");
            this.v.b();
            ToastUtil.a(this.w.getString(R.string.app_is_disabled));
            return true;
        }
        if (E0()) {
            ArrayList k2 = TilesUtil.k(this.w);
            String str = D0 + "#999";
            if (k2.contains(D0) || k2.contains(str)) {
                J0(!k2.contains(str));
            } else {
                I0();
            }
        } else {
            J0(false);
        }
        Handler handler = this.f6155k;
        QSTile.Host host = this.v;
        Objects.requireNonNull(host);
        handler.postDelayed(new e(host), 100L);
        NubiaTrackManager.p().k(O());
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void m0(boolean z) {
        super.m0(z);
        if (z) {
            return;
        }
        Dialog dialog = this.z;
        if (dialog != null && dialog.isShowing()) {
            this.z.dismiss();
        }
        this.z = null;
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
}
