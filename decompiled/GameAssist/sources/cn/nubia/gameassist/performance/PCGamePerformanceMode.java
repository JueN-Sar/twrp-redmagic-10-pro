package cn.nubia.gameassist.performance;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.performance.PCGamePerformanceMode;
import cn.nubia.hostassist.HostAssistMgr;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.IGameAssistCommander;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class PCGamePerformanceMode {

    /* renamed from: m, reason: collision with root package name */
    public static final ComponentName f7031m = ComponentName.unflattenFromString("com.xiaoji.egggame.redmagic/com.xj.winemu.WineActivity");

    /* renamed from: a, reason: collision with root package name */
    private final PCGameHost f7032a = new PCGameHost();

    /* renamed from: b, reason: collision with root package name */
    private final DisplayMetrics f7033b;

    /* renamed from: c, reason: collision with root package name */
    private Context f7034c;

    /* renamed from: d, reason: collision with root package name */
    private Context f7035d;

    /* renamed from: e, reason: collision with root package name */
    private DisplayManager f7036e;

    /* renamed from: f, reason: collision with root package name */
    private final Messenger f7037f;

    /* renamed from: g, reason: collision with root package name */
    private final Handler f7038g;

    /* renamed from: h, reason: collision with root package name */
    private final Handler f7039h;

    /* renamed from: i, reason: collision with root package name */
    private int f7040i;

    /* renamed from: j, reason: collision with root package name */
    private HostIndWindow f7041j;

    /* renamed from: k, reason: collision with root package name */
    private HostIndData f7042k;

    /* renamed from: l, reason: collision with root package name */
    private Display f7043l;

    public static class HostIndData {

        /* renamed from: d, reason: collision with root package name */
        private static final String f7047d = "HostIndData";

        /* renamed from: a, reason: collision with root package name */
        private Context f7048a;

        /* renamed from: b, reason: collision with root package name */
        public int f7049b = 10;

        /* renamed from: c, reason: collision with root package name */
        public int f7050c = 500;

        public HostIndData(Context context) {
            this.f7048a = context;
            String string = Settings.Global.getString(context.getContentResolver(), "pc_game_host_ind");
            if (string == null) {
                c();
            }
            GaLog.k(f7047d, "init data=" + string);
            d(string);
        }

        private String b() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("mPosX", this.f7049b);
                jSONObject.put("mPosY", this.f7050c);
                return jSONObject.toString();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return "";
            }
        }

        private void c() {
            Settings.Global.putString(this.f7048a.getContentResolver(), "pc_game_host_ind", b());
        }

        private void d(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.f7049b = jSONObject.getInt("mPosX");
                this.f7050c = jSONObject.getInt("mPosY");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            Settings.Global.putString(this.f7048a.getContentResolver(), "pc_game_host_ind", b());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class HostIndWindow {
        private static int z;

        /* renamed from: a, reason: collision with root package name */
        private WindowManager.LayoutParams f7051a;

        /* renamed from: b, reason: collision with root package name */
        protected View f7052b;

        /* renamed from: c, reason: collision with root package name */
        private float f7053c;

        /* renamed from: d, reason: collision with root package name */
        private float f7054d;

        /* renamed from: e, reason: collision with root package name */
        private float f7055e;

        /* renamed from: f, reason: collision with root package name */
        private float f7056f;

        /* renamed from: g, reason: collision with root package name */
        private float f7057g;

        /* renamed from: h, reason: collision with root package name */
        private float f7058h;

        /* renamed from: i, reason: collision with root package name */
        private int f7059i;

        /* renamed from: j, reason: collision with root package name */
        private int f7060j;

        /* renamed from: k, reason: collision with root package name */
        private long f7061k;

        /* renamed from: l, reason: collision with root package name */
        private long f7062l;

        /* renamed from: p, reason: collision with root package name */
        private int f7066p;

        /* renamed from: q, reason: collision with root package name */
        private int f7067q;

        /* renamed from: r, reason: collision with root package name */
        private WindowManager f7068r;
        private HostIndData u;

        /* renamed from: m, reason: collision with root package name */
        private boolean f7063m = false;

        /* renamed from: n, reason: collision with root package name */
        private int f7064n = 10;

        /* renamed from: o, reason: collision with root package name */
        private int f7065o = 500;

        /* renamed from: s, reason: collision with root package name */
        private boolean f7069s = false;
        private boolean t = false;
        private Runnable v = new Runnable() { // from class: cn.nubia.gameassist.performance.PCGamePerformanceMode.HostIndWindow.1
            @Override // java.lang.Runnable
            public void run() {
                HostIndWindow.this.f7052b.setAlpha(0.5f);
                HostIndWindow.this.f7052b.invalidate();
            }
        };
        private Runnable w = new Runnable() { // from class: cn.nubia.gameassist.performance.c
            @Override // java.lang.Runnable
            public final void run() {
                PCGamePerformanceMode.HostIndWindow.this.p();
            }
        };
        private Runnable x = new Runnable() { // from class: cn.nubia.gameassist.performance.d
            @Override // java.lang.Runnable
            public final void run() {
                PCGamePerformanceMode.HostIndWindow.this.q();
            }
        };

        public HostIndWindow() {
            this.f7066p = 0;
            this.f7067q = 0;
            this.f7068r = (WindowManager) (PCGamePerformanceMode.this.f7035d != null ? PCGamePerformanceMode.this.f7035d : PCGamePerformanceMode.this.f7034c).getSystemService(WindowManager.class);
            int j2 = j(20);
            this.f7067q = j2;
            this.f7066p = j2;
        }

        private void g() {
            this.f7052b.setAlpha(1.0f);
            PCGamePerformanceMode.this.f7038g.removeCallbacks(this.v);
            PCGamePerformanceMode.this.f7038g.postDelayed(this.v, 3000L);
        }

        private void h() {
            int i2;
            boolean j2 = RotationMgr.j();
            int g2 = RotationMgr.g();
            int f2 = RotationMgr.f();
            int i3 = z;
            if (j2) {
                int i4 = this.f7064n;
                if (i4 < 0) {
                    i4 = 0;
                }
                this.f7064n = i4;
                int i5 = this.f7066p;
                if (i4 > (f2 - i5) - i3) {
                    i4 = (f2 - i5) - i3;
                }
                this.f7064n = i4;
                int i6 = this.f7065o;
                i2 = i6 >= 0 ? i6 : 0;
                this.f7065o = i2;
                int i7 = this.f7067q;
                if (i2 > g2 - i7) {
                    i2 = g2 - i7;
                }
                this.f7065o = i2;
                return;
            }
            int i8 = this.f7064n;
            if (i8 < 0) {
                i8 = 0;
            }
            this.f7064n = i8;
            int i9 = this.f7066p;
            if (i8 > g2 - i9) {
                i8 = g2 - i9;
            }
            this.f7064n = i8;
            int i10 = this.f7065o;
            i2 = i10 >= 0 ? i10 : 0;
            this.f7065o = i2;
            int i11 = this.f7067q;
            if (i2 > (f2 - i11) - i3) {
                i2 = (f2 - i11) - i3;
            }
            this.f7065o = i2;
        }

        private void i(float f2) {
            GaLog.e("PCGamePerformanceMode", "onTouch: clickButton");
            final Display display = PCGamePerformanceMode.this.f7043l;
            PCGamePerformanceMode.this.f7039h.post(new Runnable() { // from class: cn.nubia.gameassist.performance.f
                @Override // java.lang.Runnable
                public final void run() {
                    PCGamePerformanceMode.HostIndWindow.n(display);
                }
            });
        }

        private int j(int i2) {
            Context context = PCGamePerformanceMode.this.f7035d;
            PCGamePerformanceMode pCGamePerformanceMode = PCGamePerformanceMode.this;
            return (int) ((i2 * (context != null ? pCGamePerformanceMode.f7035d : pCGamePerformanceMode.f7034c).getResources().getDisplayMetrics().density) + 0.5f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void k() {
            PCGamePerformanceMode.this.f7038g.removeCallbacks(this.x);
            PCGamePerformanceMode.this.f7038g.removeCallbacks(this.w);
            PCGamePerformanceMode.this.f7038g.post(this.w);
        }

        private void l() {
            if (this.f7051a == null) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2027, 75826952, -3);
                this.f7051a = layoutParams;
                layoutParams.width = this.f7066p;
                layoutParams.height = this.f7067q;
                layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
                layoutParams.gravity = 51;
                layoutParams.setTitle("HostIndWindow");
            }
            WindowManager.LayoutParams layoutParams2 = this.f7051a;
            layoutParams2.x = this.f7064n;
            layoutParams2.y = this.f7065o;
        }

        private void m() {
            if (this.f7052b != null) {
                return;
            }
            View inflate = LayoutInflater.from(PCGamePerformanceMode.this.f7035d != null ? PCGamePerformanceMode.this.f7035d : PCGamePerformanceMode.this.f7034c).inflate(R.layout.host_ind_window_root, (ViewGroup) null);
            this.f7052b = inflate;
            inflate.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.gameassist.performance.e
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    boolean o2;
                    o2 = PCGamePerformanceMode.HostIndWindow.this.o(view, motionEvent);
                    return o2;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void n(Display display) {
            if (display == null || display.getDisplayId() <= 0) {
                return;
            }
            HostAssistMgr.n().D(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean o(View view, MotionEvent motionEvent) {
            return r(motionEvent);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void p() {
            if (this.f7069s) {
                PCGamePerformanceMode.this.f7038g.removeCallbacks(this.v);
                this.f7068r.removeView(this.f7052b);
                this.f7069s = false;
                GaLog.e("PCGamePerformanceMode", "close");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void q() {
            if (this.f7069s) {
                return;
            }
            HostIndData hostIndData = this.u;
            this.f7064n = hostIndData.f7049b;
            this.f7065o = hostIndData.f7050c;
            l();
            m();
            this.f7068r.addView(this.f7052b, this.f7051a);
            this.f7069s = true;
            this.f7052b.setAlpha(0.0f);
            g();
            GaLog.e("PCGamePerformanceMode", "showPluginWindow: " + this.f7064n + " " + this.f7065o);
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
        
            if (r0 != 3) goto L35;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private boolean r(android.view.MotionEvent r13) {
            /*
                Method dump skipped, instructions count: 330
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.performance.PCGamePerformanceMode.HostIndWindow.r(android.view.MotionEvent):boolean");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s(HostIndData hostIndData) {
            this.u = hostIndData;
            PCGamePerformanceMode.this.f7038g.removeCallbacks(this.x);
            PCGamePerformanceMode.this.f7038g.removeCallbacks(this.w);
            PCGamePerformanceMode.this.f7038g.post(this.x);
        }

        private void t(int i2, int i3, boolean z2) {
            this.f7064n += i2;
            this.f7065o += i3;
            h();
            WindowManager.LayoutParams layoutParams = this.f7051a;
            int i4 = layoutParams.x;
            int i5 = this.f7064n;
            if (i4 != i5 || layoutParams.y != this.f7065o) {
                layoutParams.x = i5;
                layoutParams.y = this.f7065o;
                this.f7068r.updateViewLayout(this.f7052b, layoutParams);
            }
            if (z2) {
                HostIndData hostIndData = this.u;
                hostIndData.f7049b = this.f7064n;
                hostIndData.f7050c = this.f7065o;
                hostIndData.e();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class PCGameHost {

        /* renamed from: a, reason: collision with root package name */
        private Messenger f7071a;

        /* renamed from: b, reason: collision with root package name */
        private IBinder.DeathRecipient f7072b;

        /* JADX INFO: Access modifiers changed from: private */
        public void c(IBinder iBinder) {
            d();
            if (iBinder != null) {
                try {
                    iBinder.linkToDeath(this.f7072b, 0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                this.f7071a = new Messenger(iBinder);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            Messenger messenger = this.f7071a;
            if (messenger != null) {
                try {
                    messenger.getBinder().unlinkToDeath(this.f7072b, 0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.f7071a = null;
        }

        private PCGameHost() {
            this.f7072b = new IBinder.DeathRecipient() { // from class: cn.nubia.gameassist.performance.g
                @Override // android.os.IBinder.DeathRecipient
                public final void binderDied() {
                    PCGamePerformanceMode.PCGameHost.this.d();
                }
            };
        }
    }

    public PCGamePerformanceMode(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f7033b = displayMetrics;
        this.f7034c = context;
        Handler handler = new Handler(Looper.getMainLooper());
        this.f7038g = handler;
        Handler handler2 = new Handler(ThreadManager.c().j(), new Handler.Callback() { // from class: cn.nubia.gameassist.performance.b
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                boolean l2;
                l2 = PCGamePerformanceMode.this.l(message);
                return l2;
            }
        });
        this.f7039h = handler2;
        this.f7037f = new Messenger(handler2);
        this.f7042k = new HostIndData(this.f7034c);
        SystemMgr.y(this.f7034c).h(new GameMonitor.Callback() { // from class: cn.nubia.gameassist.performance.PCGamePerformanceMode.1
            @Override // com.zte.gameassist.common.GameMonitor.Callback
            public void onProjectionActivityResumed(ComponentName componentName, int i2) {
                boolean z = false;
                int i3 = Settings.Global.getInt(PCGamePerformanceMode.this.f7034c.getContentResolver(), "gamebox_mirror_displayid", 0);
                boolean equals = PCGamePerformanceMode.f7031m.equals(componentName);
                if (i2 == i3 && i3 != 0 && equals) {
                    z = true;
                }
                PCGamePerformanceMode.this.n(z);
            }
        });
        SystemMgr.y(this.f7034c).o(new IGameAssistCommander() { // from class: cn.nubia.gameassist.performance.PCGamePerformanceMode.2
            @Override // com.zte.gameassist.common.IGameAssistCommander, com.zte.gameassist.AbsGameAssistToken.ICommander
            public void executive(String str, Bundle bundle, AbsGameAssistToken.Callback callback) {
                PCGamePerformanceMode.this.j(str, bundle, callback);
            }
        });
        this.f7034c.getContentResolver().registerContentObserver(Settings.Global.getUriFor("gamebox_mirror_displayid"), false, new ContentObserver(handler) { // from class: cn.nubia.gameassist.performance.PCGamePerformanceMode.3
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                PCGamePerformanceMode.this.o();
            }
        });
        DisplayManager displayManager = (DisplayManager) this.f7034c.getSystemService(DisplayManager.class);
        this.f7036e = displayManager;
        displayManager.getDisplay(0).getMetrics(displayMetrics);
        int i2 = (int) (displayMetrics.density * 160.0f);
        int i3 = SystemProperties.getInt("ro.sf.lcd_density", i2);
        if (i2 != i3) {
            displayMetrics.density = i3 / 160.0f;
        }
        o();
        GaLog.a("PCGamePerformanceMode", "init " + displayMetrics);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(String str, Bundle bundle, AbsGameAssistToken.Callback callback) {
        if (!"get_pc_game_performance_mode_token".equals(str) || callback == null) {
            return;
        }
        GaLog.a("PCGamePerformanceMode", "executePCGameComm " + str);
        bundle.putBinder("token", this.f7037f.getBinder());
        callback.callback(str, bundle);
    }

    private float k(DisplayMetrics displayMetrics) {
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        if (i2 <= i3) {
            i2 = i3;
        }
        return i2 / displayMetrics.density;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l(Message message) {
        int i2 = message.what;
        if (i2 == 1) {
            int i3 = message.arg1;
            Settings.Global.putInt(this.f7034c.getContentResolver(), "redmagic_pc_game_performance_mode", i3);
            GaLog.a("PCGamePerformanceMode", "set pc_performance_mode = " + i3);
            return false;
        }
        if (i2 != 257) {
            return false;
        }
        Bundle data = message.getData();
        if (!data.containsKey("token")) {
            return false;
        }
        IBinder binder = data.getBinder("token");
        this.f7032a.c(binder);
        GaLog.a("PCGamePerformanceMode", "set PCGameHostToken = " + binder);
        return false;
    }

    private void m() {
        HostIndWindow hostIndWindow;
        if (this.f7040i != 0 || (hostIndWindow = this.f7041j) == null) {
            return;
        }
        hostIndWindow.k();
        this.f7041j = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(boolean z) {
        if (z) {
            if (this.f7041j == null) {
                this.f7041j = new HostIndWindow();
            }
            this.f7041j.s(this.f7042k);
        } else {
            HostIndWindow hostIndWindow = this.f7041j;
            if (hostIndWindow != null) {
                hostIndWindow.k();
                this.f7041j = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        int i2 = Settings.Global.getInt(this.f7034c.getContentResolver(), "gamebox_mirror_displayid", 0);
        if (this.f7040i != i2) {
            this.f7040i = i2;
            p();
            m();
        }
    }

    private void p() {
        Display display;
        if (this.f7040i == 0) {
            return;
        }
        if (this.f7035d == null || (display = this.f7043l) == null || display.getDisplayId() != this.f7040i) {
            try {
                int i2 = this.f7040i;
                if (i2 > 0) {
                    Display display2 = this.f7036e.getDisplay(i2);
                    this.f7043l = display2;
                    this.f7035d = ContextWrapper.createWindowContext(this.f7034c.createDisplayContext(display2), 2027);
                }
                DisplayMetrics displayMetrics = this.f7035d.getResources().getDisplayMetrics();
                float k2 = k(displayMetrics);
                float k3 = k(this.f7033b);
                if (Math.abs(k2 - k3) > 1.0f) {
                    float f2 = k2 / k3;
                    GaLog.a("PCGamePerformanceMode", "adjustContext " + Math.round(k3) + "dp => " + Math.round(k2) + "dp scale=" + f2);
                    Configuration configuration = this.f7035d.getResources().getConfiguration();
                    configuration.fontScale = configuration.fontScale * f2;
                    configuration.densityDpi = (int) (((float) configuration.densityDpi) * f2);
                    int i3 = (int) (((float) configuration.screenWidthDp) * f2);
                    configuration.screenWidthDp = i3;
                    int i4 = (int) (((float) configuration.screenHeightDp) * f2);
                    configuration.screenHeightDp = i4;
                    if (i3 >= i4) {
                        i3 = i4;
                    }
                    configuration.smallestScreenWidthDp = i3;
                    this.f7035d.getResources().updateConfiguration(configuration, displayMetrics);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
