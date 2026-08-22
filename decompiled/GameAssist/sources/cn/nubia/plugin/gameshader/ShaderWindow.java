package cn.nubia.plugin.gameshader;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class ShaderWindow {
    private static int O;
    private static int P;
    private static int Q;
    private ImageView A;
    private View B;
    private ShaderSettingWindow C;
    protected View F;
    protected ImageView G;
    protected WindowManager.LayoutParams H;
    private int I;
    private int J;
    private ShaderItemData L;

    /* renamed from: a, reason: collision with root package name */
    private WindowManager.LayoutParams f8516a;

    /* renamed from: b, reason: collision with root package name */
    protected View f8517b;

    /* renamed from: c, reason: collision with root package name */
    private float f8518c;

    /* renamed from: d, reason: collision with root package name */
    private float f8519d;

    /* renamed from: e, reason: collision with root package name */
    private float f8520e;

    /* renamed from: f, reason: collision with root package name */
    private float f8521f;

    /* renamed from: g, reason: collision with root package name */
    private float f8522g;

    /* renamed from: h, reason: collision with root package name */
    private float f8523h;

    /* renamed from: i, reason: collision with root package name */
    private float f8524i;

    /* renamed from: j, reason: collision with root package name */
    private int f8525j;

    /* renamed from: k, reason: collision with root package name */
    private int f8526k;

    /* renamed from: l, reason: collision with root package name */
    private long f8527l;

    /* renamed from: m, reason: collision with root package name */
    private long f8528m;
    private Context t;
    private WindowManager u;
    private TextView z;

    /* renamed from: n, reason: collision with root package name */
    private boolean f8529n = false;

    /* renamed from: o, reason: collision with root package name */
    private int f8530o = 300;

    /* renamed from: p, reason: collision with root package name */
    private int f8531p = 300;

    /* renamed from: q, reason: collision with root package name */
    private int f8532q = 0;

    /* renamed from: r, reason: collision with root package name */
    private int f8533r = 0;

    /* renamed from: s, reason: collision with root package name */
    private int f8534s = 0;
    private String v = "";
    private boolean w = false;
    private final Handler x = new Handler(Looper.getMainLooper());
    private boolean y = false;
    private boolean D = false;
    private boolean E = false;
    private boolean K = false;
    private Handler M = new Handler();
    private Runnable N = new Runnable() { // from class: cn.nubia.plugin.gameshader.ShaderWindow.4
        @Override // java.lang.Runnable
        public void run() {
            ShaderWindow.this.f8517b.setAlpha(0.5f);
            ShaderWindow.this.f8517b.invalidate();
        }
    };

    public ShaderWindow(Context context) {
        this.t = context;
        i();
    }

    private void c() {
        if (this.E) {
            return;
        }
        j();
        boolean j2 = RotationMgr.j();
        int P2 = GameAssistWindowManager.P();
        int Q2 = GameAssistWindowManager.Q();
        if (j2) {
            this.I = P2 / 2;
            this.J = (Q2 - (P / 2)) - 50;
        } else {
            this.I = Q2 / 2;
            this.J = (P2 - (P / 2)) - 200;
        }
        this.F.setVisibility(0);
        this.u.addView(this.F, this.H);
        this.E = true;
    }

    private void d() {
        int i2;
        boolean j2 = RotationMgr.j();
        int g2 = RotationMgr.g();
        int f2 = RotationMgr.f();
        int i3 = O;
        if (j2) {
            int i4 = this.f8530o;
            if (i4 < 0) {
                i4 = 0;
            }
            this.f8530o = i4;
            int i5 = this.f8532q;
            if (i4 > (f2 - i5) - i3) {
                i4 = (f2 - i5) - i3;
            }
            this.f8530o = i4;
            int i6 = this.f8531p;
            i2 = i6 >= 0 ? i6 : 0;
            this.f8531p = i2;
            int i7 = this.f8533r;
            if (i2 > g2 - i7) {
                i2 = g2 - i7;
            }
            this.f8531p = i2;
            return;
        }
        int i8 = this.f8530o;
        if (i8 < 0) {
            i8 = 0;
        }
        this.f8530o = i8;
        int i9 = this.f8532q;
        if (i8 > g2 - i9) {
            i8 = g2 - i9;
        }
        this.f8530o = i8;
        int i10 = this.f8531p;
        i2 = i10 >= 0 ? i10 : 0;
        this.f8531p = i2;
        int i11 = this.f8533r;
        if (i2 > (f2 - i11) - i3) {
            i2 = (f2 - i11) - i3;
        }
        this.f8531p = i2;
    }

    private void e(float f2) {
        GaLog.e("GameShaderMgr", "onTouch: clickButton= " + f2 + " " + this.f8534s);
        if (f2 >= this.f8534s) {
            w();
        } else {
            ShaderMgr.t().p();
            t();
        }
    }

    private void g(int i2) {
        this.x.postDelayed(new Runnable() { // from class: cn.nubia.plugin.gameshader.ShaderWindow.3
            @Override // java.lang.Runnable
            public void run() {
                ShaderWindow.this.q();
            }
        }, i2);
    }

    private WindowManager.LayoutParams j() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.height = P;
        layoutParams.width = Q;
        layoutParams.gravity = 51;
        int P2 = GameAssistWindowManager.P();
        int Q2 = GameAssistWindowManager.Q();
        if (RotationMgr.j()) {
            layoutParams.x = (P2 - Q) / 2;
            layoutParams.y = (Q2 - P) - 50;
        } else {
            layoutParams.x = (Q2 - Q) / 2;
            layoutParams.y = (P2 - P) - 200;
        }
        layoutParams.setTitle("PluginTimerDelete");
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        layoutParams.format = -2;
        layoutParams.type = 2038;
        layoutParams.flags = 792;
        this.H = layoutParams;
        return layoutParams;
    }

    private void k() {
        if (this.f8516a == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2038, 75826952, -3);
            this.f8516a = layoutParams;
            layoutParams.width = this.f8532q;
            layoutParams.height = this.f8533r;
            layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
            WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
            WindowManager.LayoutParams layoutParams2 = this.f8516a;
            layoutParams2.gravity = 51;
            layoutParams2.setTitle("PluginGameShader");
            WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(this.f8516a);
        }
        WindowManager.LayoutParams layoutParams3 = this.f8516a;
        layoutParams3.x = this.f8530o;
        layoutParams3.y = this.f8531p;
    }

    private void l() {
        if (this.f8517b != null) {
            return;
        }
        if (this.F == null) {
            View f2 = InflaterHelper.f(R.layout.plugin_button_delete, null);
            this.F = f2;
            this.G = (ImageView) f2.findViewById(R.id.plugin_button_delete_img);
        }
        View f3 = InflaterHelper.f(R.layout.plugin_shader_root, null);
        this.f8517b = f3;
        f3.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.plugin.gameshader.ShaderWindow.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return ShaderWindow.this.p(motionEvent);
            }
        });
        m();
    }

    private void m() {
        this.z = (TextView) this.f8517b.findViewById(R.id.plugin_shader_root_title);
        this.A = (ImageView) this.f8517b.findViewById(R.id.plugin_shader_root_switch);
        this.B = this.f8517b.findViewById(R.id.plugin_shader_root_setting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0025, code lost:
    
        if (r0 != 3) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean p(android.view.MotionEvent r15) {
        /*
            Method dump skipped, instructions count: 461
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.plugin.gameshader.ShaderWindow.p(android.view.MotionEvent):boolean");
    }

    private void s() {
        ShaderMgr.t().o(this.f8530o, this.f8531p);
    }

    private void w() {
        GaLog.e("GameShaderMgr", "onClick");
        if (this.D) {
            h();
        } else {
            v();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(int i2, int i3, boolean z) {
        if (this.w) {
            this.f8530o += i2;
            this.f8531p += i3;
            d();
            WindowManager.LayoutParams layoutParams = this.f8516a;
            int i4 = layoutParams.x;
            int i5 = this.f8530o;
            if (i4 != i5 || layoutParams.y != this.f8531p) {
                layoutParams.x = i5;
                layoutParams.y = this.f8531p;
                this.u.updateViewLayout(this.f8517b, layoutParams);
            }
            if (z) {
                s();
            }
        }
    }

    private void y() {
        this.z.setText(ShaderUtils.f(ShaderUtils.c(1)));
    }

    private void z() {
        ((Vibrator) this.t.getSystemService("vibrator")).vibrate(100L);
    }

    public void f() {
        if (this.w) {
            this.w = false;
            this.u.removeView(this.f8517b);
            h();
            GaLog.e("GameShaderMgr", "closePluginWindow: " + this.v);
        }
    }

    public void h() {
        this.D = false;
        ShaderSettingWindow shaderSettingWindow = this.C;
        if (shaderSettingWindow != null) {
            shaderSettingWindow.c();
        }
    }

    public void i() {
        GameAssistApplication j2 = GameAssistApplication.j();
        this.t = j2;
        this.u = (WindowManager) j2.getSystemService(WindowManager.class);
        this.v = Utils.j();
        this.f8532q = this.t.getResources().getDimensionPixelSize(R.dimen.plugin_gameshader_window_width);
        this.f8533r = this.t.getResources().getDimensionPixelSize(R.dimen.plugin_gameshader_window_height);
        this.f8534s = this.t.getResources().getDimensionPixelSize(R.dimen.plugin_gameshader_window_switch_click_width);
        O = this.t.getResources().getDimensionPixelSize(R.dimen.plugin_timer_navigation_size);
        Q = this.t.getResources().getDimensionPixelSize(R.dimen.plugin_timer_delete_btn_width);
        P = this.t.getResources().getDimensionPixelSize(R.dimen.plugin_timer_delete_btn_height);
    }

    public boolean n() {
        return this.D;
    }

    public void o() {
        if (this.w) {
            this.x.postDelayed(new Runnable() { // from class: cn.nubia.plugin.gameshader.ShaderWindow.2
                @Override // java.lang.Runnable
                public void run() {
                    GaLog.e("GameShaderMgr", "onFoldChange");
                    ShaderWindow.this.x(0, 0, true);
                }
            }, 2000L);
        }
    }

    public void q() {
        if (this.E) {
            this.u.removeView(this.F);
            this.E = false;
        }
    }

    public void r() {
    }

    public void t() {
        if (ShaderMgr.t().y()) {
            this.A.setImageResource(R.drawable.gameshader_btn_open);
        } else {
            this.A.setImageResource(R.drawable.gameshader_btn_close);
        }
    }

    public void u(ShaderItemData shaderItemData, boolean z) {
        this.L = shaderItemData;
        if (!this.w) {
            this.w = true;
            this.v = Utils.j();
            ShaderItemData shaderItemData2 = this.L;
            this.f8530o = shaderItemData2.f8464e;
            this.f8531p = shaderItemData2.f8465f;
            k();
            l();
            t();
            this.u.addView(this.f8517b, this.f8516a);
            this.f8517b.setAlpha(1.0f);
            this.M.removeCallbacks(this.N);
            this.M.postDelayed(this.N, 3000L);
            if (z) {
                v();
            }
            GaLog.e("GameShaderMgr", "showPluginWindow: " + this.v + " " + this.f8530o + " ");
        }
        y();
        ((ShaderWindowView) this.f8517b).setDynamic(this.L.f8466g);
    }

    public void v() {
        Log.d("GameShaderMgr", "showFloatingViewLayout isPortrait:");
        this.D = true;
        if (this.C == null) {
            this.C = new ShaderSettingWindow(this.t);
        }
        this.C.j();
    }
}
