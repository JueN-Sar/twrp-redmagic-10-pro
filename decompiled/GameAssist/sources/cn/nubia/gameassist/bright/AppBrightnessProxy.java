package cn.nubia.gameassist.bright;

import android.hardware.display.BrightnessInfo;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.MathUtils;
import androidx.annotation.VisibleForTesting;
import cn.nubia.componentcenter.api.dessert.IAppBrightnessProxy;
import cn.nubia.gameassist.bright.AppBrightnessProxy;
import com.android.internal.display.BrightnessSynchronizer;
import com.zte.gameassist.common.AbsSlideProxy;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ModuleProxyContext;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/* loaded from: classes.dex */
public class AppBrightnessProxy extends AbsSlideProxy implements IAppBrightnessProxy, GameMonitor.Callback, ObserverManager.SettingCallback {
    protected float A;
    protected boolean B;
    protected boolean C;
    private long D;
    private final Runnable E;

    /* renamed from: m, reason: collision with root package name */
    protected int f6096m;

    /* renamed from: n, reason: collision with root package name */
    protected int f6097n;

    /* renamed from: o, reason: collision with root package name */
    private float f6098o;

    /* renamed from: p, reason: collision with root package name */
    private float f6099p;

    /* renamed from: q, reason: collision with root package name */
    private final int f6100q;

    /* renamed from: r, reason: collision with root package name */
    private final Float[] f6101r;

    /* renamed from: s, reason: collision with root package name */
    private final Float[] f6102s;
    protected final DisplayManager t;
    private final float[] u;
    private final float[] v;
    private float w;
    private float x;
    private int y;
    private long z;

    public AppBrightnessProxy(ModuleProxyContext moduleProxyContext) {
        super(moduleProxyContext);
        this.f6098o = 0.0f;
        this.f6099p = 1.0f;
        this.f6100q = SystemProperties.getInt("ro.product.first_api_level", -1);
        this.u = new float[5];
        this.v = new float[5];
        this.w = -1.0f;
        this.x = -1.0f;
        this.y = -1;
        this.E = new Runnable() { // from class: c.a
            @Override // java.lang.Runnable
            public final void run() {
                AppBrightnessProxy.this.I();
            }
        };
        A();
        this.f6101r = (Float[]) Stream.of((Object[]) new Float[]{Float.valueOf(0.0f), Float.valueOf(3.0f), Float.valueOf(7.0f), Float.valueOf(10.0f), Float.valueOf(14.0f), Float.valueOf(15.0f)}).map(new Function() { // from class: c.b
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Float E;
                E = AppBrightnessProxy.this.E((Float) obj);
                return E;
            }
        }).toArray(new IntFunction() { // from class: c.c
            @Override // java.util.function.IntFunction
            public final Object apply(int i2) {
                Float[] F;
                F = AppBrightnessProxy.F(i2);
                return F;
            }
        });
        this.f6102s = (Float[]) Stream.of((Object[]) new Float[]{Float.valueOf(this.f6096m * 1.0f), Float.valueOf(20.0f), Float.valueOf(60.0f), Float.valueOf(100.0f), Float.valueOf(200.0f), Float.valueOf(255.0f)}).map(new Function() { // from class: c.d
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Float G;
                G = AppBrightnessProxy.this.G((Float) obj);
                return G;
            }
        }).toArray(new IntFunction() { // from class: c.e
            @Override // java.util.function.IntFunction
            public final Object apply(int i2) {
                Float[] H;
                H = AppBrightnessProxy.H(i2);
                return H;
            }
        });
        this.t = (DisplayManager) this.f16454h.a().getSystemService(DisplayManager.class);
        SystemMgr.y(this.f16454h.a()).h(this);
        m0(SystemMgr.H());
        int i2 = 0;
        while (i2 < 5) {
            int i3 = i2 + 1;
            this.u[i2] = C(this.f6101r[i2].floatValue(), this.f6102s[i2].floatValue(), this.f6101r[i3].floatValue(), this.f6102s[i3].floatValue());
            this.v[i2] = B(this.f6101r[i2].floatValue(), this.f6102s[i2].floatValue(), this.f6101r[i3].floatValue(), this.f6102s[i3].floatValue());
            i2 = i3;
        }
    }

    private boolean D() {
        return (Settings.Global.getInt(this.f16454h.a().getContentResolver(), "nubia_game_mode", 256) & 256) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Float E(Float f2) {
        return Float.valueOf((f2.floatValue() / 15.0f) * (this.f6097n - this.f6096m));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Float[] F(int i2) {
        return new Float[i2];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Float G(Float f2) {
        return Float.valueOf(f2.floatValue() / this.f6097n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Float[] H(int i2) {
        return new Float[i2];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I() {
        if (this.x == -1.0f) {
            this.x = getBrightnessValue();
            GaLog.e(this.f16453c, "get latest brightness " + this.x);
        }
        GaLog.e(this.f16453c, "set before game stop brightness");
        setBrightnessValue(this.x);
    }

    private void J(boolean z, int i2) {
        this.w = convertToBrightVal(i2);
        GaLog.a(this.f16453c, "t:" + z + ",b:" + this.w + ",tb:" + this.A + ",v:" + i2);
        if (z) {
            float f2 = this.w;
            if (f2 != this.A) {
                L(f2);
                this.A = this.w;
            }
        }
    }

    private void K(int i2) {
        Settings.System.putInt(this.f16454h.a().getContentResolver(), "screen_brightness_mode", i2);
        GaLog.a(this.f16453c, "set a " + i2);
    }

    private int z() {
        return Settings.System.getInt(this.f16454h.a().getContentResolver(), "screen_brightness_mode", 0);
    }

    public void A() {
        BrightnessInfo brightnessInfo = this.f16454h.a().getDisplay().getBrightnessInfo();
        if (brightnessInfo != null) {
            float f2 = brightnessInfo.brightnessMaximum;
            this.f6099p = f2;
            this.f6098o = brightnessInfo.brightnessMinimum;
            this.f6097n = BrightnessSynchronizer.brightnessFloatToInt(f2);
            int brightnessFloatToInt = BrightnessSynchronizer.brightnessFloatToInt(this.f6098o);
            this.f6096m = brightnessFloatToInt;
            this.f16456j = this.f6097n - brightnessFloatToInt;
            GaLog.a(this.f16453c, "get brightness info " + this.f6099p + "," + this.f6098o + "," + this.f6097n + "," + this.f6096m);
        }
    }

    public float B(float f2, float f3, float f4, float f5) {
        if (Math.abs(f2 - f4) >= 1.0E-6d) {
            return ((f3 * f4) - (f5 * f2)) / (f4 - f2);
        }
        GaLog.a(this.f16453c, "invalid x1, x2");
        return 0.0f;
    }

    public float C(float f2, float f3, float f4, float f5) {
        if (Math.abs(f2 - f4) >= 1.0E-6d) {
            return (f5 - f3) / (f4 - f2);
        }
        GaLog.a(this.f16453c, "invalid x1, x2");
        return 0.0f;
    }

    protected void L(float f2) {
        try {
            try {
                this.t.setTemporaryBrightness(0, f2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            this.C = true;
        }
    }

    public void M() {
        float brightnessValue = getBrightnessValue();
        this.f16457k = v(brightnessValue);
        j();
        GaLog.a(this.f16453c, "update slider p:" + this.f16457k + ",b:" + brightnessValue);
    }

    @Override // cn.nubia.componentcenter.api.dessert.IAppBrightnessProxy
    public void changeBrightnessMode() {
        K(z() == 1 ? 0 : 1);
    }

    @Override // cn.nubia.componentcenter.api.dessert.IAppBrightnessProxy
    @VisibleForTesting
    public float convertToBrightVal(float f2) {
        return MathUtils.min(((f2 + this.f6096m) / this.f6097n) * (this.f6099p - this.f6098o), 1.0f);
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    protected void g() {
        ObserverManager.c().b(this.f16454h.a(), Settings.System.getUriFor("screen_brightness"), this);
    }

    @Override // cn.nubia.componentcenter.api.dessert.IAppBrightnessProxy
    @VisibleForTesting
    public float getBrightnessValue() {
        try {
            return this.t.getBrightness(0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0f;
        }
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    protected void i() {
        ObserverManager.c().d(this.f16454h.a(), Settings.System.getUriFor("screen_brightness"), this);
        if (this.C) {
            setBrightnessValue(this.w);
        }
    }

    @Override // com.zte.gameassist.common.AbsSlideProxy
    public void k(int i2, boolean z) {
        if (this.B) {
            return;
        }
        J(z, i2);
    }

    @Override // com.zte.gameassist.common.AbsSlideProxy
    protected void l() {
    }

    @Override // com.zte.gameassist.common.AbsSlideProxy
    protected void m() {
        setBrightnessValue(this.w);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameSceneStateChanged */
    public void m0(boolean z) {
        boolean D = D();
        GaLog.e(this.f16453c, "g:" + z + ",nb:" + this.x + ",b:" + this.w + ",fb:" + D);
        if (!z) {
            if (D) {
                x();
            }
        } else {
            this.D = SystemClock.elapsedRealtime();
            if (D) {
                y();
            } else {
                this.x = -1.0f;
                M();
            }
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        this.f16454h.b().removeCallbacks(this.E);
        if (this.C) {
            setBrightnessValue(this.w);
        }
    }

    @Override // cn.nubia.componentcenter.api.dessert.IAppBrightnessProxy
    @VisibleForTesting
    public void setBrightnessValue(float f2) {
        try {
            try {
                this.t.setBrightness(0, f2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            GaLog.e(this.f16453c, "set b " + f2);
        } finally {
            this.C = false;
        }
    }

    public int v(float f2) {
        return (int) (((f2 / (this.f6099p - this.f6098o)) * this.f6097n) - this.f6096m);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        try {
            this.B = true;
            A();
            M();
        } finally {
            this.B = false;
        }
    }

    public void x() {
        if (SystemClock.elapsedRealtime() - this.D > 500) {
            this.x = getBrightnessValue();
            GaLog.e(this.f16453c, "exit next brightness " + this.x);
        }
        int z = z();
        GaLog.e(this.f16453c, "exit auto:" + z + " " + this.y);
        int i2 = this.y;
        if (i2 >= 0 && i2 != z) {
            K(i2);
            SharedPreferencesUtil.k(this.f16454h.a()).L(false);
        }
        this.z = System.currentTimeMillis();
    }

    public void y() {
        this.y = z();
        GaLog.e(this.f16453c, "auto:" + this.y);
        if (this.y == 1) {
            K(0);
            SharedPreferencesUtil.k(this.f16454h.a()).L(true);
        }
        if (System.currentTimeMillis() < this.z + 1800000) {
            this.f16454h.b().postDelayed(this.E, 500L);
        }
    }
}
