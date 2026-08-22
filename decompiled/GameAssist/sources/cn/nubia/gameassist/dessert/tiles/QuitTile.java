package cn.nubia.gameassist.dessert.tiles;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import cn.nubia.gameassist.common.QSTile;
import com.zte.extres.R;
import com.zte.mifavor.widget.AlertDialog;

/* loaded from: classes.dex */
public class QuitTile extends QSTile {
    private Dialog v;

    public QuitTile(QSTile.Host host) {
        super(host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B0() {
        Dialog dialog = this.v;
        if (dialog == null || !dialog.isShowing()) {
            AlertDialog a2 = new AlertDialog.Builder(this.f6153i, R.style.Theme_ZTE_Light_Dialog_Alert).m(this.f6153i.getString(cn.nubia.gameassist.R.string.ic_qs_quit_dialog_title)).i(cn.nubia.gameassist.R.string.ic_qs_quit_dialog_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.dessert.tiles.QuitTile.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    Settings.Global.putInt(((QSTile) QuitTile.this).f6153i.getContentResolver(), "virtual_game_key", 0);
                    dialogInterface.dismiss();
                }
            }).f(com.zte.gameassist.common.R.string.single_cancel, new DialogInterface.OnClickListener(this) { // from class: cn.nubia.gameassist.dessert.tiles.QuitTile.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            }).a();
            this.v = a2;
            a2.getWindow().setType(2027);
            this.v.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.v.show();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        if (ActivityManager.isUserAMonkey()) {
            return true;
        }
        this.f6152h.b();
        this.f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.h
            @Override // java.lang.Runnable
            public final void run() {
                QuitTile.this.B0();
            }
        }, 300L);
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            o0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6169c = this.f6153i.getString(cn.nubia.gameassist.R.string.ic_qs_quit);
        state.f6168b = QSTile.ResourceIcon.b(cn.nubia.gameassist.R.drawable.game_ic_qs_quit_off);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void m0(boolean z) {
        super.m0(z);
        if (z) {
            return;
        }
        Dialog dialog = this.v;
        if (dialog != null && dialog.isShowing()) {
            this.v.dismiss();
        }
        this.v = null;
    }
}
