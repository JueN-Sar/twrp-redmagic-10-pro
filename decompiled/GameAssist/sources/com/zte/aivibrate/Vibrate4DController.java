package com.zte.aivibrate;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.google.mlkit.common.MlKitException;
import com.zte.aivibrate.processor.GameLabDataProcessor;
import com.zte.aivibrate.processor.YoloDataProcessor;
import com.zte.aivibrate.scene.VibrateSceneState;
import com.zte.aivibrate.util.AIVibrateLog;
import com.zte.gameassist.common.DumpController;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.richtap.ZTERichtapUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class Vibrate4DController implements GameMonitor.Callback, DumpController.Dump {

    /* renamed from: s, reason: collision with root package name */
    public static int f16182s;
    public static int t;

    /* renamed from: c, reason: collision with root package name */
    private final Context f16183c;

    /* renamed from: h, reason: collision with root package name */
    private HandlerThread f16184h;

    /* renamed from: i, reason: collision with root package name */
    private Handler f16185i;

    /* renamed from: j, reason: collision with root package name */
    private final GameLabDataProcessor f16186j;

    /* renamed from: k, reason: collision with root package name */
    private final YoloDataProcessor f16187k;

    /* renamed from: l, reason: collision with root package name */
    private final OCRController f16188l;

    /* renamed from: m, reason: collision with root package name */
    private final ZTERichtapUtils f16189m;

    /* renamed from: n, reason: collision with root package name */
    private String f16190n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f16191o;

    /* renamed from: p, reason: collision with root package name */
    private final Handler f16192p;

    /* renamed from: q, reason: collision with root package name */
    private final Runnable f16193q = new Runnable() { // from class: com.zte.aivibrate.Vibrate4DController.3
        @Override // java.lang.Runnable
        public void run() {
            if (Vibrate4DController.this.y()) {
                Vibrate4DController.this.f16185i.removeMessages(4);
                Vibrate4DController.this.f16185i.sendEmptyMessage(4);
            } else if (Vibrate4DController.this.A() && Vibrate4DController.this.m()) {
                Vibrate4DController.this.D();
            }
        }
    };

    /* renamed from: r, reason: collision with root package name */
    private final Runnable f16194r = new Runnable() { // from class: com.zte.aivibrate.Vibrate4DController.4
        @Override // java.lang.Runnable
        public void run() {
            AIVibrateLog.b(".Controller", "settings change " + Vibrate4DController.this.A());
            if (Vibrate4DController.this.I()) {
                Vibrate4DController.this.D();
            } else {
                Vibrate4DController.this.l();
            }
        }
    };

    /* renamed from: com.zte.aivibrate.Vibrate4DController$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f16199a;

        static {
            int[] iArr = new int[VibrateSceneState.values().length];
            f16199a = iArr;
            try {
                iArr[VibrateSceneState.ATTACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f16199a[VibrateSceneState.YS_ATTACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f16199a[VibrateSceneState.SMALL_SKILL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f16199a[VibrateSceneState.YS_SMALL_SKILL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f16199a[VibrateSceneState.YS_RUNNING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f16199a[VibrateSceneState.KILL_ENEMY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f16199a[VibrateSceneState.CONTINUOUS_KILL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f16199a[VibrateSceneState.LONG_PRESS_SKILL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f16199a[VibrateSceneState.YS_LONG_PRESS_SKILL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f16199a[VibrateSceneState.ULTIMATE_SKILL.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f16199a[VibrateSceneState.ENEMY_DEAD_ALL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f16199a[VibrateSceneState.YS_FIVE_START.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f16199a[VibrateSceneState.YS_ULTIMATE_SKILL.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f16199a[VibrateSceneState.VICTORY.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f16199a[VibrateSceneState.WHEN_KILLED.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f16199a[VibrateSceneState.YS_PERSON_DIED.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f16199a[VibrateSceneState.DEFEAT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f16199a[VibrateSceneState.YS_FLYING.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    public Vibrate4DController(Context context) {
        this.f16183c = context;
        j();
        Handler handler = new Handler(Looper.getMainLooper());
        this.f16192p = handler;
        this.f16186j = new GameLabDataProcessor(context, this);
        this.f16187k = new YoloDataProcessor(context, this);
        OCRController e2 = OCRController.e();
        this.f16188l = e2;
        e2.g(context, this);
        this.f16189m = new ZTERichtapUtils(context);
        SystemMgr.y(context).h(this);
        DumpController.c().a(this);
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("nubia_4d_shocks"), true, new ContentObserver(handler) { // from class: com.zte.aivibrate.Vibrate4DController.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                if (DetectStrategyFactory.d(SystemMgr.t())) {
                    Vibrate4DController.this.f16192p.removeCallbacks(Vibrate4DController.this.f16194r);
                    Vibrate4DController.this.f16192p.postDelayed(Vibrate4DController.this.f16194r, 2000L);
                }
            }
        });
    }

    private void B(VibrateSceneState vibrateSceneState) {
        if (vibrateSceneState == null) {
            AIVibrateLog.b(".Controller", "makeVibrate, state is null !");
        }
        switch (AnonymousClass5.f16199a[vibrateSceneState.ordinal()]) {
            case 1:
            case 2:
                this.f16189m.richtapVibrate(R.raw.vibrate_a);
                break;
            case 3:
            case 4:
            case 5:
                this.f16189m.richtapVibrate(R.raw.vibrate_b);
                break;
            case 6:
                this.f16189m.richtapVibrate(R.raw.vibrate_c);
                break;
            case 7:
            case 8:
            case 9:
                this.f16189m.richtapVibrate(R.raw.vibrate_d);
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                this.f16189m.richtapVibrate(R.raw.vibrate_e);
                break;
            case 14:
                this.f16189m.richtapVibrate(R.raw.vibrate_f);
                break;
            case 15:
            case 16:
            case MlKitException.NETWORK_ISSUE /* 17 */:
                this.f16189m.richtapVibrate(R.raw.vibrate_g);
                break;
            case MlKitException.UNSUPPORTED /* 18 */:
                this.f16189m.richtapVibrate(ZteFeature.isTabletProduct() ? R.raw.vibrate_h_pad : R.raw.vibrate_h);
                break;
        }
    }

    private void j() {
        DisplayMetrics displayMetrics = this.f16183c.getResources().getDisplayMetrics();
        f16182s = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        t = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (I()) {
            AIVibrateLog.b(".Controller", "vibrate4D work,not handle close");
            return;
        }
        u();
        this.f16186j.m();
        Handler handler = this.f16185i;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (this.f16184h != null) {
            AIVibrateLog.b(".Controller", "thread quit");
            this.f16184h.quit();
        }
        this.f16184h = null;
        this.f16191o = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        AIVibrateLog.b(".Controller", "end data processor " + this.f16191o);
        this.f16187k.l();
        this.f16186j.o();
        this.f16188l.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        boolean A = A();
        this.f16191o = A;
        if (A) {
            n();
        } else {
            o();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        AIVibrateLog.b(".Controller", "open data processor");
        this.f16190n = SystemMgr.t();
        this.f16186j.t();
        this.f16187k.v();
        this.f16188l.i();
        F();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean y() {
        return (this.f16184h == null || this.f16185i == null) ? false : true;
    }

    private void z() {
        HandlerThread handlerThread = new HandlerThread("vibrate4dThread", -2);
        this.f16184h = handlerThread;
        handlerThread.start();
        this.f16185i = new Handler(this.f16184h.getLooper()) { // from class: com.zte.aivibrate.Vibrate4DController.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i2 = message.what;
                if (i2 == 1) {
                    Vibrate4DController.this.w();
                    return;
                }
                if (i2 == 2) {
                    Vibrate4DController.this.t();
                } else if (i2 == 3) {
                    Vibrate4DController.this.u();
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    Vibrate4DController.this.v();
                }
            }
        };
    }

    public boolean A() {
        return SystemMgr.H();
    }

    public void C() {
        this.f16185i.removeMessages(1);
        this.f16185i.sendEmptyMessage(1);
    }

    public void D() {
        AIVibrateLog.b(".Controller", "open plugin " + this.f16190n + ",h:" + y());
        if (y()) {
            return;
        }
        z();
        C();
    }

    public void E(VibrateSceneState vibrateSceneState) {
        AIVibrateLog.b(".Controller", "receiveScene " + vibrateSceneState.d());
        B(vibrateSceneState);
        this.f16187k.s(vibrateSceneState);
    }

    public void F() {
        AIVibrateLog.b(".Controller", "start data processor");
        this.f16191o = true;
        this.f16190n = SystemMgr.t();
        this.f16186j.v();
        this.f16187k.B();
    }

    public boolean G() {
        HandlerThread handlerThread = this.f16184h;
        return handlerThread != null && handlerThread.isAlive();
    }

    public void H(boolean z, String str) {
        if (this.f16191o != z) {
            AIVibrateLog.b(".Controller", "update play game state:" + z + ",pkg:" + q() + ",r:" + str);
            if (z) {
                this.f16185i.removeMessages(3);
                this.f16187k.B();
            } else {
                this.f16187k.l();
            }
            this.f16191o = z;
        }
    }

    public boolean I() {
        return A() && m();
    }

    @Override // com.zte.gameassist.common.DumpController.Dump
    public void c(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println(getClass().getSimpleName());
        printWriter.println("  has gaming:" + this.f16191o);
        this.f16186j.n(printWriter);
        this.f16187k.k(printWriter);
    }

    public void k() {
        this.f16185i.removeMessages(2);
        this.f16185i.sendEmptyMessage(2);
    }

    public void l() {
        AIVibrateLog.b(".Controller", "close ai 4d plugin ,h:" + y());
        if (y()) {
            k();
        }
    }

    public boolean m() {
        String t2 = SystemMgr.t();
        if (!DetectStrategyFactory.d(t2)) {
            return false;
        }
        String str = t2 + ",";
        String string = Settings.Global.getString(this.f16183c.getContentResolver(), "nubia_4d_shocks");
        return !TextUtils.isEmpty(string) && string.contains(str);
    }

    public void n() {
        boolean z = TextUtils.isEmpty(this.f16190n) || !this.f16190n.equals(SystemMgr.t());
        AIVibrateLog.b(".Controller", "game start change " + z);
        this.f16185i.removeMessages(3);
        if (!z) {
            this.f16186j.r();
            this.f16187k.t();
            return;
        }
        u();
        if (I()) {
            F();
        } else {
            k();
        }
    }

    public void o() {
        this.f16186j.s();
        this.f16187k.u();
        this.f16188l.h();
        AIVibrateLog.b(".Controller", "game stop delay end data processor");
        this.f16185i.removeMessages(3);
        this.f16185i.sendEmptyMessageDelayed(3, 10000L);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameSceneStateChanged */
    public void m0(boolean z) {
        this.f16192p.removeCallbacks(this.f16193q);
        this.f16192p.postDelayed(this.f16193q, 1000L);
    }

    public GameLabDataProcessor p() {
        return this.f16186j;
    }

    public String q() {
        return this.f16190n;
    }

    public Looper r() {
        return this.f16184h.getLooper();
    }

    public YoloDataProcessor s() {
        return this.f16187k;
    }

    public boolean x() {
        return this.f16191o;
    }
}
