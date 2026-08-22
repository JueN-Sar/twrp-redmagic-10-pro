package cn.nubia.plugin.gameratio;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.ObserverData;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class GameRatioIndicatorWindow {
    private DisplayManager.DisplayListener A;
    private Handler B;
    private int C;

    /* renamed from: a, reason: collision with root package name */
    private Context f8375a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f8376b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f8377c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f8378d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f8379e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f8380f;

    /* renamed from: g, reason: collision with root package name */
    private ImageView f8381g;

    /* renamed from: h, reason: collision with root package name */
    private ImageView f8382h;

    /* renamed from: i, reason: collision with root package name */
    private ImageView f8383i;

    /* renamed from: j, reason: collision with root package name */
    private ImageView f8384j;

    /* renamed from: k, reason: collision with root package name */
    private RotationMgr.Callback f8385k;

    /* renamed from: l, reason: collision with root package name */
    private String f8386l;

    /* renamed from: m, reason: collision with root package name */
    public float f8387m;

    /* renamed from: n, reason: collision with root package name */
    private float f8388n;

    /* renamed from: o, reason: collision with root package name */
    private WindowManager f8389o;

    /* renamed from: p, reason: collision with root package name */
    private DisplayManager f8390p;

    /* renamed from: q, reason: collision with root package name */
    private float f8391q;

    /* renamed from: r, reason: collision with root package name */
    private float f8392r;

    /* renamed from: s, reason: collision with root package name */
    private boolean f8393s;
    private int u;
    private int v;
    private int w;
    private String y;
    private ObserverData.Observer z;
    private List t = new ArrayList();
    private int x = 0;

    public GameRatioIndicatorWindow(Context context) {
        Context createConfigurationContext = context.createConfigurationContext(InflaterHelper.d().getConfiguration());
        this.f8375a = createConfigurationContext;
        this.f8389o = (WindowManager) createConfigurationContext.getSystemService("window");
        this.f8390p = (DisplayManager) context.getSystemService("display");
        k(context);
        this.f8385k = new RotationMgr.Callback() { // from class: cn.nubia.plugin.gameratio.h
            @Override // com.zte.gameassist.common.RotationMgr.Callback
            /* renamed from: onRotationChanged */
            public final void y(int i2) {
                GameRatioIndicatorWindow.this.F(i2);
            }
        };
        r();
        Y();
        if (GameRatioMgr.f8396r) {
            this.z = new ObserverData.Observer() { // from class: cn.nubia.plugin.gameratio.i
                @Override // com.zte.gameassist.common.ObserverData.Observer
                public final void a(Object obj) {
                    GameRatioIndicatorWindow.this.G((InflaterHelper.FixedScreenState) obj);
                }
            };
        }
    }

    private boolean A(float f2) {
        return f2 < this.f8388n ? ((float) this.w) - (((float) this.v) * f2) >= ((float) 150) : ((float) this.v) - (((float) this.w) / f2) >= ((float) 150);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(View view) {
        if (q(this.f8391q, 0.0f)) {
            this.f8391q = 0.5f;
            R(0.5f);
            X();
        } else if (q(this.f8391q, 0.5f)) {
            this.f8391q = 1.0f;
            R(1.0f);
            X();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C(View view) {
        if (q(this.f8392r, 1.0f)) {
            this.f8392r = 0.5f;
            Q(0.5f);
            X();
        } else if (q(this.f8392r, 0.5f)) {
            this.f8392r = 0.0f;
            Q(0.0f);
            X();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D(View view) {
        if (q(this.f8392r, 0.0f)) {
            this.f8392r = 0.5f;
            Q(0.5f);
            X();
        } else if (q(this.f8392r, 0.5f)) {
            this.f8392r = 1.0f;
            Q(1.0f);
            X();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(View view) {
        if (q(this.f8391q, 1.0f)) {
            this.f8391q = 0.5f;
            R(0.5f);
            X();
        } else if (q(this.f8391q, 0.5f)) {
            this.f8391q = 0.0f;
            R(0.0f);
            X();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F(int i2) {
        W();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G(InflaterHelper.FixedScreenState fixedScreenState) {
        if (fixedScreenState != null) {
            V(fixedScreenState);
            W();
        }
    }

    private float H(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception unused) {
            GaLog.a("GameRatio", "parse error " + str);
            return 0.0f;
        }
    }

    private void I() {
        if (this.x > 0) {
            if (this.A == null) {
                this.A = new DisplayManager.DisplayListener() { // from class: cn.nubia.plugin.gameratio.GameRatioIndicatorWindow.1
                    @Override // android.hardware.display.DisplayManager.DisplayListener
                    public void onDisplayAdded(int i2) {
                    }

                    @Override // android.hardware.display.DisplayManager.DisplayListener
                    public void onDisplayChanged(int i2) {
                        if (i2 == 0) {
                            int rotation = GameRatioIndicatorWindow.this.f8390p.getDisplay(0).getRotation();
                            Log.i("laiaifang", "rotation=" + rotation + ",mRotation=" + GameRatioIndicatorWindow.this.C);
                            if (rotation != GameRatioIndicatorWindow.this.C) {
                                GameRatioIndicatorWindow.this.C = rotation;
                                GameRatioIndicatorWindow.this.W();
                            }
                        }
                    }

                    @Override // android.hardware.display.DisplayManager.DisplayListener
                    public void onDisplayRemoved(int i2) {
                    }
                };
                this.B = new Handler(Looper.getMainLooper());
            }
            Display display = this.f8390p.getDisplay(0);
            if (display != null) {
                this.C = display.getRotation();
            }
            this.f8390p.registerDisplayListener(this.A, this.B);
        }
    }

    private void J() {
        M();
        N();
        O();
        K();
    }

    private void K() {
        if (this.f8380f) {
            this.f8380f = false;
            this.f8389o.removeView(this.f8384j);
        }
    }

    private void M() {
        if (this.f8377c) {
            this.f8377c = false;
            this.f8389o.removeView(this.f8381g);
        }
    }

    private void N() {
        if (this.f8378d) {
            this.f8378d = false;
            this.f8389o.removeView(this.f8382h);
        }
    }

    private void O() {
        if (this.f8379e) {
            this.f8379e = false;
            this.f8389o.removeView(this.f8383i);
        }
    }

    private void P(String str) {
        this.y = str;
        Settings.Global.putString(this.f8375a.getContentResolver(), "nubia_gameratio_app_bound", str);
    }

    private void Q(float f2) {
        Settings.Global.putFloat(this.f8375a.getContentResolver(), "nubia_gameratio_horizontal_factor", f2);
    }

    private void R(float f2) {
        Settings.Global.putFloat(this.f8375a.getContentResolver(), "nubia_gameratio_vertical_factor", f2);
    }

    private void S(int i2) {
        this.u = i2;
        Iterator it = this.t.iterator();
        while (it.hasNext()) {
            ((GameRatioCallback) it.next()).a(i2);
        }
    }

    private void U() {
        if (this.x > 0) {
            this.f8390p.unregisterDisplayListener(this.A);
        }
    }

    private void V(InflaterHelper.FixedScreenState fixedScreenState) {
        int i2 = fixedScreenState.f16527f;
        int i3 = fixedScreenState.f16528g;
        int i4 = i2 < i3 ? i2 : i3;
        this.v = i4;
        if (i2 < i3) {
            i2 = i3;
        }
        this.w = i2;
        this.f8388n = (i2 * 1.0f) / i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W() {
        if (this.f8393s) {
            if (!this.f8376b) {
                s();
            } else {
                J();
                Z();
            }
        }
    }

    private void X() {
        Utils.c0(this.f8386l);
        Z();
    }

    private void Y() {
        InflaterHelper.FixedScreenState fixedScreenState;
        if (GameRatioMgr.f8396r && (fixedScreenState = (InflaterHelper.FixedScreenState) InflaterHelper.f16516e.b()) != null) {
            V(fixedScreenState);
            return;
        }
        Display display = ((DisplayManager) this.f8375a.getSystemService("display")).getDisplay(0);
        Point point = new Point();
        display.getRealSize(point);
        int i2 = point.x;
        int i3 = point.y;
        int i4 = i2 < i3 ? i2 : i3;
        this.v = i4;
        if (i2 < i3) {
            i2 = i3;
        }
        this.w = i2;
        this.f8388n = (i2 * 1.0f) / i4;
    }

    private void Z() {
        int i2;
        if (RotationMgr.k()) {
            if (this.f8387m < this.f8388n) {
                boolean z = x() == 0;
                if (q(this.f8391q, 0.0f)) {
                    S(1);
                    O();
                    l(z ? 0 : this.x);
                } else if (q(this.f8391q, 0.5f)) {
                    S(2);
                    int i3 = z ? this.x : 0;
                    i2 = z ? 0 : this.x;
                    p(i3);
                    l(i2);
                } else {
                    S(3);
                    p(z ? this.x : 0);
                    K();
                }
            } else {
                S(0);
                if (q(this.f8392r, 0.0f)) {
                    M();
                    o(0);
                } else if (q(this.f8392r, 0.5f)) {
                    n(0);
                    o(0);
                } else {
                    n(0);
                    N();
                }
            }
        } else if (this.f8387m < this.f8388n) {
            S(0);
            boolean z2 = x() == 1;
            if (q(this.f8392r, 0.0f)) {
                M();
                o(z2 ? 0 : this.x);
            } else if (q(this.f8392r, 0.5f)) {
                int i4 = z2 ? this.x : 0;
                i2 = z2 ? 0 : this.x;
                n(i4);
                o(i2);
            } else {
                n(z2 ? this.x : 0);
                N();
            }
        } else if (q(this.f8391q, 0.0f)) {
            S(1);
            O();
            l(0);
        } else if (q(this.f8391q, 0.5f)) {
            S(2);
            p(0);
            l(0);
        } else {
            S(3);
            p(0);
            K();
        }
        s();
    }

    private void k(Context context) {
        DisplayCutout displayCutout;
        try {
            displayCutout = this.f8389o.getCurrentWindowMetrics().getWindowInsets().getDisplayCutout();
        } catch (Exception e2) {
            GaLog.e("GameRatio", "acquire display cutout size error for " + e2.getMessage());
            displayCutout = null;
        }
        if (displayCutout == null) {
            return;
        }
        int i2 = context.getResources().getConfiguration().orientation;
        List<Rect> boundingRects = displayCutout.getBoundingRects();
        if (boundingRects == null || boundingRects.isEmpty()) {
            return;
        }
        if (i2 == 2) {
            this.x = boundingRects.get(0).width();
        } else {
            this.x = boundingRects.get(0).height();
        }
        GaLog.e("GameRatio", "display cutout size " + this.x + ", orientation " + i2);
    }

    private void l(int i2) {
        if (this.f8380f) {
            return;
        }
        this.f8380f = true;
        ImageView imageView = new ImageView(this.f8375a);
        this.f8384j = imageView;
        imageView.setImageResource(R.drawable.gameratio_arrow);
        WindowManager.LayoutParams w = w("PluginGameRatioBottomInd");
        w.gravity = 81;
        w.y = this.f8375a.getResources().getDimensionPixelSize(R.dimen.gameratio_indicator_margin) + i2;
        this.f8384j.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.plugin.gameratio.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameRatioIndicatorWindow.this.B(view);
            }
        });
        this.f8389o.addView(this.f8384j, w);
    }

    private void n(int i2) {
        if (this.f8377c) {
            return;
        }
        this.f8377c = true;
        ImageView imageView = new ImageView(this.f8375a);
        this.f8381g = imageView;
        imageView.setImageResource(R.drawable.gameratio_arrow);
        this.f8381g.setRotation(90.0f);
        WindowManager.LayoutParams w = w("PluginGameRatioLeftInd");
        w.gravity = 19;
        w.x = this.f8375a.getResources().getDimensionPixelSize(R.dimen.gameratio_indicator_margin) + i2;
        this.f8381g.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.plugin.gameratio.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameRatioIndicatorWindow.this.C(view);
            }
        });
        this.f8389o.addView(this.f8381g, w);
    }

    private void o(int i2) {
        if (this.f8378d) {
            return;
        }
        this.f8378d = true;
        ImageView imageView = new ImageView(this.f8375a);
        this.f8382h = imageView;
        imageView.setImageResource(R.drawable.gameratio_arrow);
        this.f8382h.setRotation(-90.0f);
        WindowManager.LayoutParams w = w("PluginGameRatioRightInd");
        w.gravity = 21;
        w.x = this.f8375a.getResources().getDimensionPixelSize(R.dimen.gameratio_indicator_margin) + i2;
        this.f8382h.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.plugin.gameratio.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameRatioIndicatorWindow.this.D(view);
            }
        });
        this.f8389o.addView(this.f8382h, w);
    }

    private void p(int i2) {
        if (this.f8379e) {
            return;
        }
        this.f8379e = true;
        ImageView imageView = new ImageView(this.f8375a);
        this.f8383i = imageView;
        imageView.setImageResource(R.drawable.gameratio_arrow);
        this.f8383i.setRotation(180.0f);
        WindowManager.LayoutParams w = w("PluginGameRatioTopInd");
        w.gravity = 49;
        w.y = this.f8375a.getResources().getDimensionPixelSize(R.dimen.gameratio_indicator_margin) + i2;
        this.f8383i.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.plugin.gameratio.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameRatioIndicatorWindow.this.E(view);
            }
        });
        this.f8389o.addView(this.f8383i, w);
    }

    private boolean q(float f2, float f3) {
        return Math.abs(f2 - f3) < 0.01f;
    }

    private void r() {
        P("0");
    }

    private void s() {
        if (RotationMgr.k()) {
            float f2 = this.f8387m;
            if (f2 >= this.f8388n) {
                int i2 = this.w;
                int ceil = (int) Math.ceil((this.v - r0) * this.f8392r);
                P(ceil + ",0," + (ceil + ((int) ((i2 / f2) + 0.5f))) + "," + i2);
                return;
            }
            int i3 = this.v;
            int i4 = (int) ((i3 * f2) + 0.5f);
            int ceil2 = (int) Math.ceil((this.w - i4) * this.f8391q);
            if (this.x != 0) {
                ceil2 += q(this.f8391q, 1.0f) ? 0 : this.x;
            }
            P("0," + ceil2 + "," + i3 + "," + (i4 + ceil2));
            return;
        }
        float f3 = this.f8387m;
        if (f3 >= this.f8388n) {
            int i5 = this.w;
            int ceil3 = (int) Math.ceil((this.v - r0) * this.f8391q);
            P("0," + ceil3 + "," + i5 + "," + (ceil3 + ((int) ((i5 / f3) + 0.5f))));
            return;
        }
        int i6 = this.v;
        int i7 = (int) ((i6 * f3) + 0.5f);
        int ceil4 = (int) Math.ceil((this.w - i7) * this.f8392r);
        if (this.x != 0) {
            ceil4 += q(this.f8392r, 1.0f) ? 0 : this.x;
        }
        P(ceil4 + ",0," + (ceil4 + i7) + "," + i6);
    }

    private float v() {
        return Settings.Global.getFloat(this.f8375a.getContentResolver(), "nubia_gameratio_horizontal_factor", 0.5f);
    }

    private WindowManager.LayoutParams w(String str) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2038);
        layoutParams.flags = 67110696;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -2;
        layoutParams.setTitle(str);
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        return layoutParams;
    }

    private int x() {
        return this.x > 0 ? this.C : RotationMgr.h();
    }

    private float y() {
        return Settings.Global.getFloat(this.f8375a.getContentResolver(), "nubia_gameratio_vertical_factor", 0.5f);
    }

    public void L(GameRatioCallback gameRatioCallback) {
        if (this.t.contains(gameRatioCallback)) {
            this.t.remove(gameRatioCallback);
        }
    }

    public void T(String str, String str2) {
        if (this.f8393s) {
            float H = H(str2);
            if (q(this.f8387m, H)) {
                this.f8386l = str;
                this.f8387m = H;
                return;
            } else {
                t();
                T(str, str2);
                return;
            }
        }
        this.f8387m = H(str2);
        if (GameRatioMgr.f8396r) {
            InflaterHelper.f16516e.e(true, this.z);
        }
        this.f8393s = true;
        this.f8386l = str;
        this.f8391q = y();
        this.f8392r = v();
        if (A(this.f8387m)) {
            this.f8376b = true;
            J();
            Z();
            GaLog.e("GameRatio", "Indicator show");
            I();
        } else {
            s();
            GaLog.e("GameRatio", "Indicator enable");
        }
        RotationMgr.e(this.f8375a).c(this.f8385k);
    }

    public void m(GameRatioCallback gameRatioCallback) {
        if (this.t.contains(gameRatioCallback)) {
            return;
        }
        this.t.add(gameRatioCallback);
    }

    public void t() {
        if (this.f8393s) {
            this.f8393s = false;
            S(0);
            RotationMgr.e(this.f8375a).p(this.f8385k);
            if (GameRatioMgr.f8396r) {
                InflaterHelper.f16516e.e(false, this.z);
            }
            r();
            if (!this.f8376b) {
                GaLog.e("GameRatio", "Indicator disable");
                return;
            }
            this.f8376b = false;
            U();
            J();
            GaLog.e("GameRatio", "Indicator dismiss");
        }
    }

    public void u(PrintWriter printWriter) {
        printWriter.print("  Vertical factor:");
        printWriter.println(this.f8391q);
        printWriter.print("  Horizontal factor:");
        printWriter.println(this.f8392r);
        printWriter.print("  App Bound:");
        printWriter.println(this.y);
        printWriter.print("  Game ratio:");
        printWriter.println(this.f8387m);
        printWriter.print("  Screen ratio:");
        printWriter.println(this.f8388n);
        printWriter.print("  Screen width:");
        printWriter.println(this.v);
        printWriter.print("  Screen height:");
        printWriter.println(this.w);
        printWriter.print("  Rotation:");
        printWriter.println(RotationMgr.h());
        if (this.x != 0) {
            if (this.f8376b) {
                printWriter.print("  Rotation:");
                printWriter.print(this.C);
            }
            printWriter.print("  Display cutout size:");
            printWriter.println(this.x);
        }
        if (this.f8393s) {
            printWriter.println("  Indicator enable");
            printWriter.print("  Vertical pos:");
            printWriter.println(this.u);
        }
        if (this.f8376b) {
            printWriter.println("  Indicator show");
        }
    }

    public int z() {
        return this.u;
    }
}
