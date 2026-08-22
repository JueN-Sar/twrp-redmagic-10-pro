package cn.nubia.gameassist.dessert.policy;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.ColorDrawable;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.policy.VirtualHandleAssistController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.common.EventListener;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.mifavor.widget.AlertDialog;
import com.zte.shared.wrapper.DisplayWrapper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/* loaded from: classes.dex */
public class VirtualHandleAssistController implements IBinder.DeathRecipient, ObserverManager.SettingCallback, GameMonitor.Callback, EventListener {
    public static boolean u = false;
    private static volatile VirtualHandleAssistController v;

    /* renamed from: c, reason: collision with root package name */
    private boolean f6301c;

    /* renamed from: h, reason: collision with root package name */
    private Context f6302h;

    /* renamed from: k, reason: collision with root package name */
    private final FoldVirtualHandle f6305k;

    /* renamed from: l, reason: collision with root package name */
    private IBinder f6306l;

    /* renamed from: m, reason: collision with root package name */
    private DisplayManager f6307m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f6308n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f6309o;

    /* renamed from: p, reason: collision with root package name */
    private Callback f6310p;
    private static final Uri w = Settings.Global.getUriFor("nubia_gamehalf_enable_pkgs");
    private static final Uri x = Settings.Global.getUriFor("nubia_virtual_handle_enable");
    private static final Uri y = Uri.parse("content://cn.nubia.virtualgamehandle");
    public static final Uri z = Uri.parse("content://cn.nubia.virtualgamehandle/switch");
    public static final int[] A = {R.string.gameratio_title, R.string.plugin_icon_keyposition, R.string.plugin_label_high_wheel};

    /* renamed from: i, reason: collision with root package name */
    private ArrayList f6303i = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    private Handler f6304j = new Handler(Looper.getMainLooper());

    /* renamed from: q, reason: collision with root package name */
    private final DisplayManager.DisplayListener f6311q = new DisplayManager.DisplayListener() { // from class: cn.nubia.gameassist.dessert.policy.VirtualHandleAssistController.1
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i2) {
            if (VirtualHandleAssistController.this.G(VirtualHandleAssistController.this.f6307m.getDisplay(i2)) && !VirtualHandleAssistController.this.f6303i.contains(Integer.valueOf(i2))) {
                VirtualHandleAssistController.this.f6303i.add(Integer.valueOf(i2));
                if (VirtualHandleAssistController.this.f6303i.size() == 1) {
                    VirtualHandleAssistController.this.z("onDisplayAdded");
                }
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i2) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i2) {
            VirtualHandleAssistController.this.f6303i.remove(Integer.valueOf(i2));
            if (VirtualHandleAssistController.this.f6303i.size() == 0) {
                VirtualHandleAssistController.this.K();
            }
        }
    };

    /* renamed from: r, reason: collision with root package name */
    private Runnable f6312r = new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.VirtualHandleAssistController.2
        @Override // java.lang.Runnable
        public void run() {
            try {
                if (VirtualHandleAssistController.this.f6308n) {
                    VirtualHandleAssistController.this.f6308n = false;
                    VirtualHandleAssistController.this.f6302h.unbindService(VirtualHandleAssistController.this.t);
                    GaLog.e("VirtualGameHandle", "unbindService ");
                }
            } catch (Exception e2) {
                GaLog.k("VirtualGameHandle", "unbind Virtual Handle Service error " + e2.getMessage());
            }
        }
    };

    /* renamed from: s, reason: collision with root package name */
    private Runnable f6313s = new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.VirtualHandleAssistController.3
        @Override // java.lang.Runnable
        public void run() {
            try {
                Bundle call = VirtualHandleAssistController.this.f6302h.getContentResolver().call(VirtualHandleAssistController.y, "getToken", (String) null, (Bundle) null);
                if (call == null || !call.containsKey("token")) {
                    return;
                }
                VirtualHandleAssistController.this.N(call.getBinder("token"));
            } catch (Exception e2) {
                GaLog.e("VirtualGameHandle", "bindToken e," + e2.getMessage());
            }
        }
    };
    private ServiceConnection t = new ServiceConnection() { // from class: cn.nubia.gameassist.dessert.policy.VirtualHandleAssistController.4
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            VirtualHandleAssistController.this.f6308n = true;
            if (VirtualHandleAssistController.this.y()) {
                VirtualHandleAssistController.this.N(iBinder);
            } else {
                VirtualHandleAssistController.this.f6304j.postDelayed(VirtualHandleAssistController.this.f6312r, 5000L);
            }
            if (VirtualHandleAssistController.this.f6305k != null) {
                VirtualHandleAssistController.this.f6305k.g();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            VirtualHandleAssistController.this.f6308n = false;
        }
    };

    public interface Callback {
        void h(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class FoldVirtualHandle implements ObserverManager.SettingCallback, FoldMgr.Callback {

        /* renamed from: c, reason: collision with root package name */
        private final Runnable f6318c;

        /* renamed from: h, reason: collision with root package name */
        private String f6319h;

        /* JADX INFO: Access modifiers changed from: private */
        public void f() {
            boolean z = SystemMgr.H() && FoldMgr.c().e() && j();
            if (VirtualHandleAssistController.u != z) {
                GaLog.e("VirtualGameHandle", "checkFoldVirtualHandle STATE_PRIMARY=" + FoldMgr.c().e() + " openVirtualHandle=" + j());
                VirtualHandleAssistController.this.B(z);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g() {
            if (FoldMgr.f()) {
                VirtualHandleAssistController.this.f6304j.removeCallbacks(this.f6318c);
                VirtualHandleAssistController.this.f6304j.postDelayed(this.f6318c, 1000L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void i(Context context) {
            this.f6319h = Settings.Global.getString(VirtualHandleAssistController.this.f6302h.getContentResolver(), "nubia_gamehalf_enable_pkgs");
            ObserverManager.c().b(context, VirtualHandleAssistController.w, this);
            FoldMgr.c().a(this);
            if (j() && SystemMgr.H()) {
                VirtualHandleAssistController.this.B(true);
            }
        }

        private boolean j() {
            String z = SystemMgr.z();
            String str = this.f6319h;
            if (str != null) {
                if (str.contains(z + ",")) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void k(Uri uri) {
            if (VirtualHandleAssistController.w.equals(uri)) {
                String string = Settings.Global.getString(VirtualHandleAssistController.this.f6302h.getContentResolver(), "nubia_gamehalf_enable_pkgs");
                if ((string == null && this.f6319h == null) || TextUtils.equals(this.f6319h, string)) {
                    return;
                }
                this.f6319h = string;
                GaLog.e("VirtualGameHandle", "nubia_gamehalf_enable_pkgs = " + this.f6319h);
                if (SystemMgr.H()) {
                    VirtualHandleAssistController.this.B(false);
                    Utils.W(SystemMgr.z(), "virtualhandle_click");
                    g();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void l() {
            w(true, VirtualHandleAssistController.w);
        }

        public void h(PrintWriter printWriter) {
            printWriter.println("FoldVirtualHandle: ");
            printWriter.println("  mVirtualHandlePackages: " + this.f6319h);
            printWriter.println("  PKGS: " + Settings.Global.getString(VirtualHandleAssistController.this.f6302h.getContentResolver(), "nubia_gamehalf_enable_pkgs"));
        }

        public void m(boolean z) {
            String str = this.f6319h;
            if (str == null) {
                str = "";
            }
            String str2 = SystemMgr.t() + ",";
            if (z && !str.contains(str2)) {
                str = str2 + str;
            } else if (!z && str.contains(str2)) {
                str = str.replace(str2, "");
            }
            if (!TextUtils.equals(this.f6319h, str)) {
                Settings.Global.putString(VirtualHandleAssistController.this.f6302h.getContentResolver(), "nubia_gamehalf_enable_pkgs", str);
                VirtualHandleAssistController.this.f6304j.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.n
                    @Override // java.lang.Runnable
                    public final void run() {
                        VirtualHandleAssistController.FoldVirtualHandle.this.l();
                    }
                }, 16L);
                return;
            }
            GaLog.e("VirtualGameHandle", "openVirtualHandle open=" + z + " enable=" + j());
            VirtualHandleAssistController.this.B(z);
        }

        @Override // com.zte.gameassist.common.FoldMgr.Callback
        public void onDisplayInUseStateChanged(int i2) {
            if (SystemMgr.H() && j()) {
                String z = SystemMgr.z();
                VirtualHandleAssistController.this.B(false);
                Utils.W(z, "virtualhandle_display");
                g();
            }
        }

        @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
        public void w(boolean z, final Uri uri) {
            VirtualHandleAssistController.this.f6304j.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.l
                @Override // java.lang.Runnable
                public final void run() {
                    VirtualHandleAssistController.FoldVirtualHandle.this.k(uri);
                }
            });
        }

        private FoldVirtualHandle() {
            this.f6318c = new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.m
                @Override // java.lang.Runnable
                public final void run() {
                    VirtualHandleAssistController.FoldVirtualHandle.this.f();
                }
            };
            this.f6319h = "";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private VirtualHandleAssistController() {
        this.f6305k = FoldMgr.f() ? new FoldVirtualHandle() : null;
    }

    private String D(boolean z2) {
        String string = this.f6302h.getString(R.string.virtual_game_handle_fold_restart_alert);
        if (!z2) {
            return string;
        }
        return string + "\n\t" + this.f6302h.getString(R.string.virtual_game_handle_fold_metex_alert, "\t" + ((String) Arrays.stream(A).mapToObj(new IntFunction() { // from class: cn.nubia.gameassist.dessert.policy.k
            @Override // java.util.function.IntFunction
            public final Object apply(int i2) {
                String H;
                H = VirtualHandleAssistController.this.H(i2);
                return H;
            }
        }).collect(Collectors.joining(", "))));
    }

    public static VirtualHandleAssistController E() {
        if (v == null) {
            synchronized (VirtualHandleAssistController.class) {
                try {
                    if (v == null) {
                        v = new VirtualHandleAssistController();
                    }
                } finally {
                }
            }
        }
        return v;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean G(Display display) {
        if (display == null || DisplayWrapper.getType(display) == 1) {
            return false;
        }
        if (DisplayWrapper.getType(display) == 5) {
            String name = display.getName();
            if (!"ScreenCastThread-display".equals(name) && !"ScreenCastThread-zte-display".equals(name)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String H(int i2) {
        return this.f6302h.getString(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I(Uri uri) {
        if (x.equals(uri)) {
            u = Settings.Global.getInt(this.f6302h.getContentResolver(), "nubia_virtual_handle_enable", 0) == 1;
            GameAssistWindowManager.O(this.f6302h).g0("nubia_virtual_handle_enable");
            GaLog.e("VirtualGameHandle", "onChange mIsVirtualHandleOpened : " + u);
            Callback callback = this.f6310p;
            if (callback != null) {
                callback.h(u);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J() {
        z("onGameStart");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L(boolean z2, DialogInterface dialogInterface, int i2) {
        this.f6305k.m(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N(IBinder iBinder) {
        if (iBinder == null || !iBinder.isBinderAlive() || this.f6301c) {
            return;
        }
        try {
            this.f6306l = iBinder;
            iBinder.linkToDeath(this, 0);
            this.f6301c = true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    private void O() {
        ContentResolver contentResolver = this.f6302h.getContentResolver();
        Settings.Global.putString(contentResolver, "nubia_virtual_handle_pkg_enable", "");
        Settings.Global.putInt(contentResolver, "nubia_virtual_handle_key_enable", 0);
        Settings.Global.putInt(contentResolver, "nubia_virtual_handle_on", 0);
        Settings.Global.putInt(contentResolver, "nubia_virtual_handle_enable", 0);
    }

    private void Q(final boolean z2) {
        this.f6302h.setTheme(com.zte.gameassist.common.R.style.GameAssist_Theme_ZTE_Light);
        AlertDialog a2 = new AlertDialog.Builder(this.f6302h, com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).l(R.string.ic_qs_virtual_handle).c(true).e(D(z2)).i(R.string.gameratio_restart_now, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.dessert.policy.g
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                VirtualHandleAssistController.this.L(z2, dialogInterface, i2);
            }
        }).f(com.zte.gameassist.common.R.string.single_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.dessert.policy.h
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        }).a();
        a2.getWindow().setType(2008);
        a2.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        a2.show();
        View findViewById = a2.getWindow().getDecorView().findViewById(com.zte.extres.R.id.scrollView);
        if (findViewById != null) {
            findViewById.setEnabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R, reason: merged with bridge method [inline-methods] */
    public void K() {
        if (!this.f6308n || y()) {
            return;
        }
        this.f6304j.postDelayed(this.f6312r, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean y() {
        return (!FoldMgr.f() && this.f6303i.size() > 0) || SystemMgr.H();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(String str) {
        this.f6304j.removeCallbacks(this.f6312r);
        if (this.f6308n || !y()) {
            return;
        }
        GaLog.e("VirtualGameHandle", "bind Virtual Handle Service reason=" + str);
        try {
            Intent intent = new Intent("cn.nubia.virtualgamehandle.intent.action.GAME_HANDLE");
            intent.setComponent(new ComponentName("cn.nubia.virtualgamehandle", "cn.nubia.virtualgamehandle.GameHandleService"));
            intent.putExtra("reason", "screen_projection");
            this.f6302h.bindService(intent, this.t, 1);
        } catch (Exception e2) {
            GaLog.k("VirtualGameHandle", "bind Virtual Handle Service error " + e2.getMessage());
        }
    }

    public void A(PrintWriter printWriter) {
        printWriter.println("VirtualHandle: ");
        printWriter.println("  sIsVirtualHandleOpened: " + u);
        printWriter.println("  mIdList: " + String.join(",", (Iterable<? extends CharSequence>) this.f6303i.stream().map(new Function() { // from class: cn.nubia.gameassist.dessert.policy.j
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return String.valueOf((Integer) obj);
            }
        }).collect(Collectors.toList())));
        printWriter.println("  mToken: " + this.f6306l);
        FoldVirtualHandle foldVirtualHandle = this.f6305k;
        if (foldVirtualHandle != null) {
            foldVirtualHandle.h(printWriter);
        }
    }

    public void B(boolean z2) {
        String str = z2 ? "1" : "0";
        try {
            Bundle bundle = new Bundle();
            bundle.putString("from", "game_control_center");
            this.f6302h.getContentResolver().call(y, Constants.EXTRA_ENABLE, str, bundle);
            this.f6304j.post(this.f6313s);
        } catch (Error e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void C(boolean z2) {
        if (Settings.Global.getInt(this.f6302h.getContentResolver(), "nubia_gameratio_state", 0) != 0) {
            ToastUtil.a(this.f6302h.getText(R.string.virtual_game_handle_fold_mutex_game_ratio).toString());
        } else {
            Q(z2);
        }
    }

    public synchronized void F(Context context) {
        try {
            if (!this.f6309o) {
                this.f6302h = context;
                O();
                DisplayManager displayManager = (DisplayManager) this.f6302h.getSystemService("display");
                this.f6307m = displayManager;
                displayManager.registerDisplayListener(this.f6311q, this.f6304j);
                for (Display display : this.f6307m.getDisplays()) {
                    if (G(display)) {
                        this.f6303i.add(Integer.valueOf(display.getDisplayId()));
                    }
                }
                if (this.f6303i.size() > 0) {
                    z("init");
                }
                ObserverManager.c().b(context, Settings.Global.getUriFor("nubia_virtual_handle_enable"), this);
                this.f6304j.post(this.f6313s);
                SystemMgr.y(this.f6302h).h(this);
                FoldVirtualHandle foldVirtualHandle = this.f6305k;
                if (foldVirtualHandle != null) {
                    foldVirtualHandle.i(this.f6302h);
                }
                EventListenerMgr.b(this, 5);
                this.f6309o = true;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public void P(Callback callback) {
        this.f6310p = callback;
    }

    @Override // com.zte.gameassist.common.EventListener
    public void a(int i2, Object... objArr) {
        if (i2 != 5 || objArr == null || objArr.length <= 0) {
            return;
        }
        boolean booleanValue = ((Boolean) objArr[0]).booleanValue();
        if (!FoldMgr.f()) {
            E().B(booleanValue);
        } else if (FoldMgr.c().e()) {
            E().C(booleanValue);
        } else {
            ToastUtil.a(this.f6302h.getText(R.string.disable_virtual_game_handle).toString());
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        GaLog.e("VirtualGameHandle", "virtual game handle app died.");
        this.f6306l = null;
        this.f6301c = false;
        O();
        try {
            this.f6302h.getContentResolver().notifyChange(z, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f6304j.postDelayed(this.f6313s, 5000L);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        this.f6304j.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.e
            @Override // java.lang.Runnable
            public final void run() {
                VirtualHandleAssistController.this.J();
            }
        });
        FoldVirtualHandle foldVirtualHandle = this.f6305k;
        if (foldVirtualHandle != null) {
            foldVirtualHandle.g();
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        this.f6304j.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.i
            @Override // java.lang.Runnable
            public final void run() {
                VirtualHandleAssistController.this.K();
            }
        });
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        FoldVirtualHandle foldVirtualHandle = this.f6305k;
        if (foldVirtualHandle != null) {
            foldVirtualHandle.g();
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public void onResumeFullscreenActivityPidChanged() {
        FoldVirtualHandle foldVirtualHandle = this.f6305k;
        if (foldVirtualHandle != null) {
            foldVirtualHandle.g();
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z2, final Uri uri) {
        this.f6304j.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.f
            @Override // java.lang.Runnable
            public final void run() {
                VirtualHandleAssistController.this.I(uri);
            }
        });
    }
}
