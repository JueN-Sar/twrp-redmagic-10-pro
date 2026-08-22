package cn.nubia.magicwindow;

import android.content.ComponentName;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.GameAssistApplication;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.common.DumpController;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class MagicWindowMgr implements GameMonitor.Callback, DumpController.Dump {

    /* renamed from: o, reason: collision with root package name */
    private static volatile MagicWindowMgr f7865o;

    /* renamed from: c, reason: collision with root package name */
    private Context f7866c;

    /* renamed from: h, reason: collision with root package name */
    private Handler f7867h;

    /* renamed from: i, reason: collision with root package name */
    private String f7868i;

    /* renamed from: j, reason: collision with root package name */
    private ContentObserver f7869j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f7870k;

    /* renamed from: l, reason: collision with root package name */
    private MagicWindowIndicator f7871l;

    /* renamed from: m, reason: collision with root package name */
    private MagicWindowPosition f7872m;

    /* renamed from: n, reason: collision with root package name */
    private String f7873n;

    private MagicWindowMgr() {
        if (ZteFeature.isSupportMagicWindow()) {
            n();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(String str) {
        if (this.f7870k) {
            GaLog.e("MagicWindowMgr", "disable due to " + str);
            this.f7870k = false;
            this.f7871l.j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        GaLog.e("MagicWindowMgr", Constants.EXTRA_ENABLE);
        this.f7870k = true;
        this.f7871l.w(this.f7872m);
    }

    public static MagicWindowMgr l() {
        if (f7865o == null) {
            synchronized (MagicWindowMgr.class) {
                try {
                    if (f7865o == null) {
                        f7865o = new MagicWindowMgr();
                    }
                } finally {
                }
            }
        }
        return f7865o;
    }

    private String m() {
        return Settings.Global.getString(this.f7866c.getContentResolver(), "magic_window_letterbox_postion_info");
    }

    private void n() {
        this.f7866c = GameAssistApplication.j();
        this.f7867h = new Handler(Looper.getMainLooper());
        this.f7871l = new MagicWindowIndicator(this.f7866c);
        this.f7869j = new ContentObserver(this.f7867h) { // from class: cn.nubia.magicwindow.MagicWindowMgr.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                MagicWindowPosition q2 = MagicWindowMgr.this.q();
                if (q2 == null) {
                    GaLog.e("MagicWindowMgr", "no magic window");
                    if (MagicWindowMgr.this.f7872m != null) {
                        MagicWindowMgr.this.f7872m = null;
                        MagicWindowMgr.this.j("magic window change");
                        return;
                    }
                    return;
                }
                GaLog.e("MagicWindowMgr", "magic window position change to " + q2);
                if (q2.equals(MagicWindowMgr.this.f7872m)) {
                    return;
                }
                MagicWindowMgr.this.f7872m = q2;
                if (MagicWindowMgr.this.f7872m.b().equals(MagicWindowMgr.this.f7868i)) {
                    MagicWindowMgr.this.k();
                }
            }
        };
        this.f7867h.post(new Runnable() { // from class: cn.nubia.magicwindow.f
            @Override // java.lang.Runnable
            public final void run() {
                MagicWindowMgr.this.o();
            }
        });
        this.f7866c.getContentResolver().registerContentObserver(Settings.Global.getUriFor("magic_window_letterbox_postion_info"), false, this.f7869j);
        SystemMgr.y(this.f7866c).h(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o() {
        this.f7868i = SystemMgr.z();
        MagicWindowPosition q2 = q();
        if (q2 != null) {
            this.f7872m = q2;
            if (q2.b().equals(this.f7868i)) {
                GaLog.e("MagicWindowMgr", "magic window info " + this.f7872m);
                k();
                return;
            }
            GaLog.e("MagicWindowMgr", "magic window info " + this.f7872m + ", current pkg " + this.f7868i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MagicWindowPosition q() {
        String m2 = m();
        String str = this.f7873n;
        if (str != null && str.equals(m2)) {
            return this.f7872m;
        }
        this.f7873n = m2;
        if (TextUtils.isEmpty(m2)) {
            return null;
        }
        String[] split = m2.split(",");
        if (split == null || split.length < 3) {
            GaLog.e("MagicWindowMgr", "magic window position " + m2);
            return null;
        }
        String str2 = split[0];
        String str3 = split[1];
        String str4 = split[2];
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            GaLog.e("MagicWindowMgr", "magic window position " + m2);
            return null;
        }
        try {
            return new MagicWindowPosition(str2, str3, Float.parseFloat(str4));
        } catch (NumberFormatException e2) {
            GaLog.l("MagicWindowMgr", "magic window position " + m2, e2);
            return null;
        }
    }

    @Override // com.zte.gameassist.common.DumpController.Dump
    public void c(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.f7870k) {
            printWriter.println("MagicWindowMgr: enable");
            printWriter.print("  magic window position: ");
            printWriter.println(this.f7872m);
            printWriter.print("  current pkg: ");
            printWriter.println(this.f7868i);
            printWriter.print("  saved magic window value: ");
            printWriter.println(m());
            printWriter.print("  cached magic window value: ");
            printWriter.println(this.f7873n);
            this.f7871l.k(printWriter);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onFullscreenActivityChange, reason: merged with bridge method [inline-methods] */
    public void p(final ComponentName componentName) {
        if (!this.f7867h.getLooper().isCurrentThread()) {
            this.f7867h.post(new Runnable() { // from class: cn.nubia.magicwindow.e
                @Override // java.lang.Runnable
                public final void run() {
                    MagicWindowMgr.this.p(componentName);
                }
            });
            return;
        }
        String packageName = componentName.getPackageName();
        if (packageName.equals(this.f7868i)) {
            return;
        }
        this.f7868i = packageName;
        MagicWindowPosition magicWindowPosition = this.f7872m;
        if (magicWindowPosition != null && packageName.equals(magicWindowPosition.b())) {
            k();
        } else if (this.f7870k) {
            j(this.f7868i);
        }
    }
}
