package cn.nubia.gameassist.plugin.tiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.display.DisplayManager;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.SystemWindowMonitor;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.shared.wrapper.WindowManagerWrapper;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class FloatButtonQSTile extends QSTile implements GameMonitor.Callback {
    private static int o0 = 230;
    private static int p0 = 420;
    protected String A;
    protected String B;
    protected View C;
    protected WindowManager.LayoutParams D;
    protected TextView E;
    protected TextView F;
    protected View G;
    protected ImageView H;
    protected String I;
    private int J;
    private int K;
    private float L;
    private float M;
    private float N;
    private float O;
    private float P;
    private float Q;
    private int R;
    private int S;
    private long T;
    private long U;
    private boolean V;
    private boolean W;
    private boolean X;
    private int Y;
    private int Z;
    private boolean a0;
    private DisplayManager b0;
    private int c0;
    private int d0;
    private int e0;
    private int f0;
    private SharedPreferencesUtil g0;
    private int h0;
    private FoldMgr.Callback i0;
    private final RotationMgr.Callback j0;
    private SystemWindowMonitor.ICallback k0;
    private final Runnable l0;
    private Runnable m0;
    private final Runnable n0;
    protected Context v;
    protected WindowManager w;
    protected SharedPreferences x;
    private boolean y;
    private boolean z;

    /* renamed from: cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile$2, reason: invalid class name */
    class AnonymousClass2 implements RotationMgr.Callback {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(int i2) {
            if (FloatButtonQSTile.this.Z != i2) {
                FloatButtonQSTile.this.Z = i2;
                FloatButtonQSTile.this.O1();
                FloatButtonQSTile.this.P1();
                if (FloatButtonQSTile.this.y && SystemMgr.H()) {
                    GaLog.e("FloatButtonQSTile", "onRotationChanged: mRotation=" + FloatButtonQSTile.this.Z + " mOrientation=" + FloatButtonQSTile.this.Y + " " + FloatButtonQSTile.this.c0 + "*" + FloatButtonQSTile.this.d0);
                    SharedPreferencesUtil sharedPreferencesUtil = FloatButtonQSTile.this.g0;
                    StringBuilder sb = new StringBuilder();
                    sb.append(((QSTile) FloatButtonQSTile.this).f6163s);
                    sb.append("_");
                    sb.append(FloatButtonQSTile.this.B);
                    sb.append("_dataOrientation");
                    int l2 = sharedPreferencesUtil.l(sb.toString(), -1);
                    if (l2 == -1 || l2 == FloatButtonQSTile.this.Y) {
                        return;
                    }
                    GaLog.e("FloatButtonQSTile", "onRotationChanged: dataOrientation=" + l2 + " mOrientation=" + FloatButtonQSTile.this.Y);
                    FloatButtonQSTile.this.M1();
                    FloatButtonQSTile.this.g0.U(((QSTile) FloatButtonQSTile.this).f6163s + "_" + FloatButtonQSTile.this.B + "_dataOrientation", FloatButtonQSTile.this.Y);
                    FloatButtonQSTile.this.N1(0, 0);
                }
            }
        }

        @Override // com.zte.gameassist.common.RotationMgr.Callback
        /* renamed from: onRotationChanged */
        public void y(final int i2) {
            ((QSTile) FloatButtonQSTile.this).f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.l
                @Override // java.lang.Runnable
                public final void run() {
                    FloatButtonQSTile.AnonymousClass2.this.b(i2);
                }
            }, 300L);
        }
    }

    protected FloatButtonQSTile(QSTile.Host host) {
        super(host);
        this.y = false;
        this.z = false;
        this.I = "";
        this.W = false;
        this.X = false;
        this.Z = -1;
        this.a0 = false;
        this.c0 = 0;
        this.d0 = 0;
        this.e0 = 0;
        this.f0 = 0;
        this.i0 = new FoldMgr.Callback() { // from class: cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile.1
            @Override // com.zte.gameassist.common.FoldMgr.Callback
            public void onDisplayInUseStateChanged(int i2) {
                FloatButtonQSTile.this.B1();
            }
        };
        this.j0 = new AnonymousClass2();
        this.k0 = new SystemWindowMonitor.ICallback() { // from class: cn.nubia.gameassist.plugin.tiles.j
            @Override // com.zte.gameassist.common.SystemWindowMonitor.ICallback
            public final void a(boolean z, String str) {
                FloatButtonQSTile.this.C1(z, str);
            }
        };
        Runnable runnable = new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile.6
            @Override // java.lang.Runnable
            public void run() {
                FloatButtonQSTile.this.b1();
                FloatButtonQSTile floatButtonQSTile = FloatButtonQSTile.this;
                SharedPreferences sharedPreferences = floatButtonQSTile.x;
                if (sharedPreferences != null) {
                    floatButtonQSTile.p1(sharedPreferences.getString(((QSTile) FloatButtonQSTile.this).f6163s + "_" + FloatButtonQSTile.this.B, null));
                }
            }
        };
        this.l0 = runnable;
        this.m0 = new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile.7
            @Override // java.lang.Runnable
            public void run() {
                if ("cn.nubia.gameassist".equals(SystemMgr.v())) {
                    GaLog.b("FloatButtonQSTile", "Block adding FloatButton!");
                    return;
                }
                FloatButtonQSTile floatButtonQSTile = FloatButtonQSTile.this;
                SharedPreferences sharedPreferences = floatButtonQSTile.x;
                if (sharedPreferences != null) {
                    floatButtonQSTile.p1(sharedPreferences.getString(((QSTile) FloatButtonQSTile.this).f6163s + "_" + FloatButtonQSTile.this.B, null));
                }
            }
        };
        this.n0 = new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.k
            @Override // java.lang.Runnable
            public final void run() {
                FloatButtonQSTile.this.w1();
            }
        };
        Context applicationContext = ((TileHost) host).getContext().getApplicationContext();
        this.v = applicationContext;
        this.h0 = applicationContext.getResources().getInteger(R.integer.performance_monitor_space_up_down);
        this.g0 = SharedPreferencesUtil.k(this.v);
        this.x = this.v.getSharedPreferences("nubia_game_plugin", 0);
        this.w = (WindowManager) this.v.getSystemService(WindowManager.class);
        this.A = k1();
        this.f6163s = SystemMgr.t();
        this.B = n1();
        this.Y = this.g0.l(this.f6163s + "_" + this.B + "_dataOrientation", -1);
        p0 = this.v.getResources().getDimensionPixelSize(R.dimen.plugin_timer_delete_btn_width);
        o0 = this.v.getResources().getDimensionPixelSize(R.dimen.plugin_timer_delete_btn_height);
        GaLog.e("FloatButtonQSTile", "---SP_NAME--- " + this.f6163s + "_" + this.B + " mOrientation=" + this.Y);
        s1();
        this.f6155k.postDelayed(runnable, 300L);
        FoldMgr.c().a(this.i0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0025, code lost:
    
        if (r0 != 3) goto L63;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean A1(android.view.MotionEvent r15) {
        /*
            Method dump skipped, instructions count: 488
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile.A1(android.view.MotionEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C1(boolean z, String str) {
        if (this.y && z && "VirtualGameHandleDisplay".equals(str)) {
            g1();
            K1();
        }
    }

    private void D1() {
        int i2;
        int i3;
        WindowManager.LayoutParams layoutParams = this.D;
        int i4 = layoutParams.x;
        if (i4 < 0 || (i2 = layoutParams.y) < (i3 = this.h0) || i4 > this.c0 - this.e0 || i2 > (this.d0 - this.f0) - i3) {
            GaLog.e("FloatButtonQSTile", "outScreenCheck: " + this.f6163s + "_" + this.B + " " + this.D.x + "*" + this.D.y + " " + this.c0 + "*" + this.d0);
            WindowManager.LayoutParams layoutParams2 = this.D;
            layoutParams2.x = Math.min(layoutParams2.x, this.c0 - this.e0);
            WindowManager.LayoutParams layoutParams3 = this.D;
            layoutParams3.x = Math.max(layoutParams3.x, 0);
            WindowManager.LayoutParams layoutParams4 = this.D;
            layoutParams4.y = Math.min(layoutParams4.y, (this.d0 - this.f0) - this.h0);
            WindowManager.LayoutParams layoutParams5 = this.D;
            layoutParams5.y = Math.max(layoutParams5.y, this.h0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E1() {
        this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.e
            @Override // java.lang.Runnable
            public final void run() {
                FloatButtonQSTile.this.y1();
            }
        });
    }

    private void F1() {
        q1();
        String L1 = L1();
        if (L1 != null) {
            SharedPreferences.Editor edit = this.x.edit();
            edit.putString(this.f6163s + "_" + this.B, L1);
            edit.commit();
            GaLog.e("FloatButtonQSTile", "removeLocale: " + this.f6163s + "_" + this.B + " : " + L1);
        }
    }

    private void H1() {
        SharedPreferences sharedPreferences;
        String L1 = L1();
        if (L1 == null || "cn.nubia.gameassist".equals(this.f6163s) || (sharedPreferences = this.x) == null) {
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.f6163s + "_" + this.B, L1);
        edit.commit();
        GaLog.e("FloatButtonQSTile", "saveToLocale: " + this.f6163s + "_" + this.B + " : " + L1);
    }

    private String L1() {
        JSONObject m1 = m1();
        if (m1 != null) {
            return m1.toString();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M1() {
        WindowManager.LayoutParams layoutParams = this.D;
        int i2 = layoutParams.x;
        layoutParams.x = layoutParams.y;
        layoutParams.y = i2;
        GaLog.a("FloatButtonQSTile", "tranPositionValue, x:" + this.D.x + " y:" + this.D.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N1(int i2, int i3) {
        WindowManager windowManager;
        WindowManager.LayoutParams layoutParams = this.D;
        layoutParams.x += i2;
        layoutParams.y += i3;
        D1();
        View view = this.C;
        if (view == null || (windowManager = this.w) == null) {
            return;
        }
        try {
            if (this.y) {
                windowManager.updateViewLayout(view, this.D);
            }
        } catch (IllegalArgumentException e2) {
            GaLog.b("FloatButtonQSTile", "updateViewPosition: " + e2.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O1() {
        int i2 = this.Z;
        if (i2 == 0) {
            this.Y = 1;
        } else if (i2 == 1 || i2 == 3) {
            this.Y = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P1() {
        int[] U = GameAssistApplication.i().U();
        this.c0 = U[0];
        this.d0 = U[1];
        GaLog.e("FloatButtonQSTile", "updateScreenSize: mRotation= " + this.Z);
        if ((this.Z == 0 || this.Y != 2) && this.c0 > this.d0) {
            this.Y = 2;
        }
    }

    private void Q1() {
        if (this.W) {
            ((Vibrator) this.v.getSystemService("vibrator")).vibrate(100L);
        }
    }

    private void a1() {
        this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.i
            @Override // java.lang.Runnable
            public final void run() {
                FloatButtonQSTile.this.t1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b1() {
        if (!this.y || this.C == null) {
            this.Z = i1();
            this.e0 = l1();
            this.f0 = j1();
            P1();
            D1();
            O1();
            GaLog.e("FloatButtonQSTile", "addFloatButtonView: mRotation=" + this.Z + " mOrientation=" + this.Y + " " + this.c0 + "*" + this.d0);
            View r1 = r1();
            this.C = r1;
            TextView textView = (TextView) r1.findViewById(R.id.plugin_button_title);
            this.E = textView;
            textView.setText(k1());
            if (this.G == null) {
                View f2 = InflaterHelper.f(R.layout.plugin_button_delete, null);
                this.G = f2;
                this.H = (ImageView) f2.findViewById(R.id.plugin_button_delete_img);
                this.F = (TextView) this.G.findViewById(R.id.plugin_button_delete_txt);
            } else {
                TextView textView2 = this.F;
                if (textView2 != null) {
                    textView2.setText(this.v.getString(R.string.plugin_button_delete_text));
                }
            }
            this.C.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile.3
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return FloatButtonQSTile.this.A1(motionEvent);
                }
            });
        }
    }

    private void d1(int i2) {
        this.f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile.4
            @Override // java.lang.Runnable
            public void run() {
                FloatButtonQSTile.this.E1();
            }
        }, i2);
    }

    public static WindowManager.LayoutParams h1(String str) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.height = o0;
        layoutParams.width = p0;
        layoutParams.gravity = 51;
        int P = GameAssistWindowManager.P();
        int Q = GameAssistWindowManager.Q();
        if (RotationMgr.j()) {
            layoutParams.x = (P - p0) / 2;
            layoutParams.y = (Q - o0) - 50;
        } else {
            layoutParams.x = (Q - p0) / 2;
            layoutParams.y = (P - o0) - 200;
        }
        layoutParams.setTitle("windowName");
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        layoutParams.format = -2;
        layoutParams.type = 2038;
        layoutParams.flags = 792;
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        return layoutParams;
    }

    private int i1() {
        if (this.b0 == null) {
            this.b0 = (DisplayManager) this.v.getSystemService("display");
        }
        return this.b0.getDisplay(0).getRotation();
    }

    private JSONObject m1() {
        if (this.D == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constants.EXTRA_ENABLE, this.f6157m.f6175i);
            jSONObject.put("x", this.D.x);
            jSONObject.put("y", this.D.y);
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private WindowManager.LayoutParams o1() {
        return h1("FloatButtonDelete");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p1(String str) {
        if (str == null || str.isEmpty()) {
            this.Z = i1();
            this.f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.h
                @Override // java.lang.Runnable
                public final void run() {
                    FloatButtonQSTile.this.q1();
                }
            }, 300L);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f6157m.f6175i = jSONObject.getBoolean(Constants.EXTRA_ENABLE);
            this.D.x = jSONObject.getInt("x");
            this.D.y = jSONObject.getInt("y");
            GaLog.e("FloatButtonQSTile", "initData: " + this.f6163s + "_" + this.B + " " + jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.h
                @Override // java.lang.Runnable
                public final void run() {
                    FloatButtonQSTile.this.q1();
                }
            });
        }
        if (this.f6157m.f6175i) {
            J1();
        } else {
            f1(false);
        }
    }

    private void s1() {
        if (this.D == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2038, 75826952, -3);
            this.D = layoutParams;
            layoutParams.height = j1();
            this.D.width = l1();
            WindowManager.LayoutParams layoutParams2 = this.D;
            layoutParams2.flags = (layoutParams2.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
            layoutParams2.gravity = 51;
            layoutParams2.setTitle("Plugin_Float_Button_" + n1());
            WindowManagerWrapper.LayoutParams.setFitInsetsTypes(this.D);
            WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.D);
        }
        GaLog.e("FloatButtonQSTile", "initLayoutParams : " + this.f6163s + "_" + this.B + " " + this.D.x + " " + this.D.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t1() {
        synchronized (this) {
            try {
                if (!this.z) {
                    WindowManager.LayoutParams o1 = o1();
                    boolean j2 = RotationMgr.j();
                    int P = GameAssistWindowManager.P();
                    int Q = GameAssistWindowManager.Q();
                    if (j2) {
                        this.J = P / 2;
                        this.K = (Q - (o0 / 2)) - 50;
                    } else {
                        this.J = Q / 2;
                        this.K = (P - (o0 / 2)) - 200;
                    }
                    if (this.G == null) {
                        View f2 = InflaterHelper.f(R.layout.plugin_button_delete, null);
                        this.G = f2;
                        this.H = (ImageView) f2.findViewById(R.id.plugin_button_delete_img);
                        TextView textView = (TextView) this.G.findViewById(R.id.plugin_button_delete_txt);
                        this.F = textView;
                        textView.setText(this.v.getString(R.string.plugin_button_delete_text));
                    }
                    this.G.setVisibility(0);
                    this.w.addView(this.G, o1);
                    this.z = true;
                    GaLog.e("FloatButtonQSTile", "addButtonDeleteView: " + j2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u1() {
        synchronized (this) {
            try {
                if (this.y) {
                    this.y = false;
                    this.w.removeView(this.C);
                    this.C = null;
                    I1(false);
                    GaLog.e("FloatButtonQSTile", "closeLastCurAppPluginWindow: " + this.f6163s + "_" + this.B + " " + this.f6157m.f6175i);
                    c1();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v1(boolean z) {
        synchronized (this) {
            try {
                GaLog.e("FloatButtonQSTile", "closePluginWindow: isFloatButtonAdd = " + this.y + " ,reset = " + z);
                if (this.y) {
                    this.y = false;
                    this.w.removeView(this.C);
                    this.C = null;
                    this.E = null;
                    I1(false);
                    GaLog.e("FloatButtonQSTile", "closePluginWindow: " + this.f6163s + "_" + this.B + " " + this.f6157m.f6175i);
                    c1();
                }
                if (z) {
                    F1();
                    if (this.a0) {
                        RotationMgr.e(this.v).p(this.j0);
                        this.a0 = false;
                    }
                    d1(300);
                } else {
                    H1();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w1() {
        synchronized (this) {
            try {
                if (!this.f6157m.f6175i) {
                    e1();
                } else if (!this.y) {
                    this.y = true;
                    s1();
                    if (!this.a0) {
                        RotationMgr.e(this.v).c(this.j0);
                        this.a0 = true;
                    }
                    b1();
                    this.w.addView(this.C, this.D);
                    I1(true);
                    H1();
                    GaLog.e("FloatButtonQSTile", "showPluginWindow: " + this.f6163s + "_" + this.B + " mOrientation= " + this.Y);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x1() {
        this.f6163s = SystemMgr.t();
        GaLog.e("FloatButtonQSTile", "---onGameUpdate--- " + this.f6163s + "_" + this.B + " : " + this.x.getAll());
        SharedPreferences sharedPreferences = this.x;
        if (sharedPreferences != null) {
            p1(sharedPreferences.getString(this.f6163s + "_" + this.B, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y1() {
        synchronized (this) {
            try {
                if (this.z) {
                    this.w.removeView(this.G);
                    this.G = null;
                    this.H = null;
                    this.F = null;
                    this.z = false;
                    GaLog.e("FloatButtonQSTile", "removeButtonDeleteView");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void B1() {
        if (this.y) {
            this.f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile.5
                @Override // java.lang.Runnable
                public void run() {
                    GaLog.e("FloatButtonQSTile", "onFoldChange");
                    FloatButtonQSTile.this.P1();
                    FloatButtonQSTile.this.N1(0, 0);
                }
            }, 2000L);
        }
    }

    protected void G1() {
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void I() {
        super.I();
        FoldMgr.c().h(this.i0);
    }

    abstract void I1(boolean z);

    public void J1() {
        this.f6155k.removeCallbacks(this.n0);
        this.f6155k.postDelayed(this.n0, 800L);
    }

    protected boolean K1() {
        QSTile.State state = this.f6157m;
        if (state.f6175i) {
            return false;
        }
        state.f6175i = true;
        J1();
        this.g0.U(this.f6163s + "_" + this.B + "_dataOrientation", this.Y);
        return true;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("FloatButtonQSTile", "handleClick= " + this.f6157m.f6169c + " " + this.f6157m.f6175i);
        QSTile.State state = this.f6157m;
        if (state.f6175i) {
            state.f6175i = false;
            f1(false);
            G1();
        } else {
            state.f6175i = true;
            J1();
            this.g0.U(this.f6163s + "_" + this.B + "_dataOrientation", this.Y);
        }
        o0();
        return true;
    }

    abstract void c1();

    public void e1() {
        this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.g
            @Override // java.lang.Runnable
            public final void run() {
                FloatButtonQSTile.this.u1();
            }
        });
    }

    public void f1(final boolean z) {
        this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.f
            @Override // java.lang.Runnable
            public final void run() {
                FloatButtonQSTile.this.v1(z);
            }
        });
    }

    protected void g1() {
        QSTile.State state = this.f6157m;
        if (state.f6175i) {
            state.f6175i = false;
            f1(false);
            G1();
        }
    }

    abstract int j1();

    abstract String k1();

    @Override // cn.nubia.gameassist.common.QSTile
    public void l0(TileHost tileHost) {
        SystemMgr.y(this.v).h(this);
        SystemWindowMonitor.h().d("VirtualGameHandleDisplay", "cn.nubia.virtualgamehandle", this.k0, this.f6155k);
        o0();
    }

    abstract int l1();

    @Override // cn.nubia.gameassist.common.QSTile
    public void n0(TileHost tileHost) {
        SystemMgr.y(this.v).i(this);
        SystemWindowMonitor.h().k(this.k0);
    }

    abstract String n1();

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        this.f6163s = SystemMgr.t();
        this.I = SystemMgr.t();
        this.f6155k.removeCallbacks(this.m0);
        this.f6155k.post(this.m0);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        GaLog.e("FloatButtonQSTile", "onGameStop: ");
        this.f6163s = SystemMgr.t();
        this.f6155k.removeCallbacks(this.m0);
        this.f6155k.removeCallbacks(this.l0);
        this.f6155k.removeCallbacks(this.n0);
        f1(false);
        E1();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        this.I = SystemMgr.t();
        synchronized (this) {
            e1();
            this.f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.d
                @Override // java.lang.Runnable
                public final void run() {
                    FloatButtonQSTile.this.x1();
                }
            }, 300L);
        }
    }

    abstract void q1();

    abstract View r1();

    abstract void z1();
}
