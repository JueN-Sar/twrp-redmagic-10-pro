package cn.nubia.gameassist.meditationmode;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import cn.nubia.componentcenter.api.meditation.IMeditationModeController;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.meditationmode.danmu.BarrageBundleCreator;
import cn.nubia.gameassist.meditationmode.danmu.BarrageFactory;
import cn.nubia.gameassist.meditationmode.danmu.BarrageManager;
import cn.nubia.gameassist.meditationmode.danmu.DanmuNotificationBean;
import cn.nubia.gameassist.meditationmode.glimmernotice.GlimmerNoticeManager;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.zte.gameassist.common.AbsModuleProxy;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ModuleProxyContext;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.NotificationManagerWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class MeditationController extends AbsModuleProxy<IMeditationModeController.MeditationModeCallback> implements GameMonitor.Callback, IMeditationModeController, ObserverManager.SettingCallback {

    /* renamed from: s, reason: collision with root package name */
    private static volatile MeditationController f6522s;

    /* renamed from: j, reason: collision with root package name */
    protected Context f6523j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f6524k;

    /* renamed from: l, reason: collision with root package name */
    private final int f6525l;

    /* renamed from: m, reason: collision with root package name */
    private String f6526m;

    /* renamed from: n, reason: collision with root package name */
    private HeadsUpType f6527n;

    /* renamed from: o, reason: collision with root package name */
    private BarrageManager f6528o;

    /* renamed from: p, reason: collision with root package name */
    private GlimmerNoticeManager f6529p;

    /* renamed from: q, reason: collision with root package name */
    private final Handler f6530q;

    /* renamed from: r, reason: collision with root package name */
    private int f6531r;

    /* renamed from: cn.nubia.gameassist.meditationmode.MeditationController$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f6532a;

        static {
            int[] iArr = new int[HeadsUpType.values().length];
            f6532a = iArr;
            try {
                iArr[HeadsUpType.BARRAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6532a[HeadsUpType.GLIMMER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private MeditationController(Context context) {
        super(new ModuleProxyContext(context));
        this.f6524k = true;
        this.f6525l = 10;
        this.f6526m = "on";
        this.f6527n = HeadsUpType.SKIP;
        this.f6530q = new Handler(Looper.getMainLooper());
        this.f6523j = context;
        z();
    }

    private boolean A(StatusBarNotification statusBarNotification, String str) {
        return statusBarNotification != null && str.equals(statusBarNotification.getPackageName());
    }

    private boolean B(StatusBarNotification statusBarNotification) {
        return A(statusBarNotification, "com.android.incallui") || A(statusBarNotification, "com.google.android.dialer") || A(statusBarNotification, "com.android.dialer");
    }

    private boolean C(StatusBarNotification statusBarNotification) {
        return (statusBarNotification.getId() == 41 && "com.tencent.mm".equals(statusBarNotification.getPackageName())) || ((statusBarNotification.getId() == 235 || statusBarNotification.getId() == 236 || statusBarNotification.getId() == 265) && "com.tencent.mobileqq".equals(statusBarNotification.getPackageName()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        final int meditationMode = getMeditationMode();
        GaLog.e("MeditationController", "onMeditationModeChange mode= " + meditationMode + " mNotificationMode = " + this.f6531r);
        if (this.f6531r != meditationMode) {
            this.f6531r = meditationMode;
            f(new Consumer() { // from class: cn.nubia.gameassist.meditationmode.b
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IMeditationModeController.MeditationModeCallback) obj).onMeditationModeCallback(meditationMode);
                }
            });
            H(this.f6523j.getString(v(meditationMode)));
        }
    }

    private void H(String str) {
        if (SystemMgr.H()) {
            ToastUtil.a(str);
        }
    }

    private void J(CustomNotificationEntry customNotificationEntry) {
        DanmuNotificationBean a2 = BarrageBundleCreator.b(this.f6523j).a(customNotificationEntry.b(), 0);
        GaLog.e("MeditationController", "showWithBarrage");
        this.f6528o.P(a2);
    }

    private void K() {
        GaLog.e("MeditationController", "showWithGlimmer");
        this.f6529p.p();
    }

    private void L(CustomNotificationEntry customNotificationEntry) {
        GaLog.e("MeditationController", "startToShow, mHeadsUpType:" + this.f6527n);
        int i2 = AnonymousClass1.f6532a[this.f6527n.ordinal()];
        if (i2 == 1) {
            J(customNotificationEntry);
        } else {
            if (i2 != 2) {
                return;
            }
            K();
        }
    }

    private void M(CustomNotificationEntry customNotificationEntry) {
        if (SystemMgr.H()) {
            x(customNotificationEntry.b());
        } else {
            y();
        }
    }

    private boolean m(StatusBarNotification statusBarNotification) {
        return (statusBarNotification == null || !C(statusBarNotification) || u()) ? false : true;
    }

    private void o(CustomNotificationEntry customNotificationEntry) {
        if (B(customNotificationEntry.b())) {
            this.f6527n = HeadsUpType.SKIP;
            GaLog.e("MeditationController", "checkNotificationTypeIfNecessary：isTelCallUI, mHeadsUpType:" + this.f6527n);
            return;
        }
        if (m(customNotificationEntry.b())) {
            this.f6527n = HeadsUpType.SKIP;
            GaLog.e("MeditationController", "checkNotificationTypeIfNecessary：blockTencentMediaNotification, mHeadsUpType:" + this.f6527n);
        }
    }

    private void q(boolean z) {
        if (z) {
            GaLog.e("MeditationController", "start enable NotificationAccessGranted!");
        } else {
            GaLog.e("MeditationController", "stop enable NotificationAccessGranted!");
        }
        try {
            NotificationManagerWrapper.setNotificationListenerAccessGranted((NotificationManager) this.f6523j.getSystemService("notification"), new ComponentName(this.f6523j.getPackageName(), "cn.nubia.gameassist.meditationmode.CustomNotificationListener"), z);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static MeditationController s() {
        if (f6522s == null) {
            synchronized (MeditationController.class) {
                try {
                    if (f6522s == null) {
                        f6522s = new MeditationController(ContextWrapper.getContext());
                    }
                } finally {
                }
            }
        }
        return f6522s;
    }

    private int v(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? R.string.meditation_notification_common : R.string.meditation_notification_hidden : R.string.meditation_notification_shimmer : R.string.meditation_notification_barrage : R.string.meditation_notification_common;
    }

    private void x(StatusBarNotification statusBarNotification) {
        if (this.f6529p.o(statusBarNotification)) {
            this.f6527n = HeadsUpType.GLIMMER;
            GaLog.e("MeditationController", "show headsUp with glimmer style pkg = " + statusBarNotification.getPackageName());
            return;
        }
        if (!this.f6528o.M(statusBarNotification)) {
            this.f6527n = HeadsUpType.SKIP;
            return;
        }
        this.f6527n = HeadsUpType.BARRAGE;
        GaLog.e("MeditationController", "show headsUp with barrage style pkg = " + statusBarNotification.getPackageName());
    }

    private void y() {
        this.f6527n = HeadsUpType.SKIP;
    }

    private void z() {
        BarrageFactory.d(this.f6523j);
        this.f6528o = BarrageManager.r();
        this.f6529p = GlimmerNoticeManager.e(this.f6523j);
        SystemMgr.y(this.f6523j).h(this);
        this.f6531r = getMeditationMode();
    }

    public void E(Configuration configuration) {
        BarrageManager barrageManager = this.f6528o;
        if (barrageManager != null) {
            barrageManager.D(configuration);
        }
    }

    public void G(CustomNotificationEntry customNotificationEntry) {
        GaLog.e("MeditationController", "postNotificationPosted");
        I(customNotificationEntry);
    }

    public void I(CustomNotificationEntry customNotificationEntry) {
        GaLog.e("MeditationController", "showNotification");
        M(customNotificationEntry);
        o(customNotificationEntry);
        L(customNotificationEntry);
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    protected void g() {
        ObserverManager.c().b(this.f6523j, Settings.Global.getUriFor("gsc_meditation_level"), this);
    }

    @Override // cn.nubia.componentcenter.api.meditation.IMeditationModeController
    public int getMeditationMode() {
        int i2 = Settings.Global.getInt(this.f6523j.getContentResolver(), "gsc_meditation_level", 0);
        GaLog.e("MeditationController", "getMeditationMode = " + i2);
        return i2;
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    protected void i() {
        ObserverManager.c().d(this.f6523j, Settings.Global.getUriFor("gsc_meditation_level"), this);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameSceneStateChanged */
    public void m0(boolean z) {
        BarrageManager barrageManager = this.f6528o;
        if (barrageManager != null) {
            barrageManager.F(z);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        q(true);
        this.f6531r = getMeditationMode();
        this.f6528o.G();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        q(false);
        this.f6528o.H();
    }

    public void p(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("MeditationController:");
        printWriter.println("currentMeditation = " + getMeditationMode());
        printWriter.println("isPhoneOn = " + u());
        printWriter.println("");
        BarrageManager barrageManager = this.f6528o;
        if (barrageManager != null) {
            barrageManager.q(printWriter);
        }
    }

    @Override // cn.nubia.componentcenter.api.meditation.IMeditationModeController
    public void setMeditationMode(int i2) {
        GaLog.e("MeditationController", "setMeditationMode mode= " + i2);
        Settings.Global.putInt(this.f6523j.getContentResolver(), "gsc_meditation_level", i2);
        if (i2 == 3) {
            GameKeysWrapper.b().e(this.f6523j, 4);
        } else {
            GameKeysWrapper.b().a(this.f6523j, 4);
        }
    }

    public boolean u() {
        int c2 = GameKeysWrapper.b().c(this.f6523j);
        this.f6524k = (c2 & 10) == 0;
        GaLog.e("MeditationController", "phoneState = " + this.f6524k + " gameValue = " + c2);
        return this.f6524k;
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        this.f6530q.post(new Runnable() { // from class: cn.nubia.gameassist.meditationmode.a
            @Override // java.lang.Runnable
            public final void run() {
                MeditationController.this.F();
            }
        });
    }
}
