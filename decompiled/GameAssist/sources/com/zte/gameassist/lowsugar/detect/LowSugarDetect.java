package com.zte.gameassist.lowsugar.detect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.MotionEvent;
import cn.nubia.yolox.YOLOXWindow;
import cn.nubia.yolox.YOLOXncnn;
import com.zte.gameassist.input.FullScreenInputMonitor;
import com.zte.gameassist.input.InterfaceEventListener;
import com.zte.gameassist.lowsugar.common.DetectParam;
import com.zte.gameassist.lowsugar.detect.ILowSugarDetect;
import com.zte.gameassist.lowsugar.detect.ISceneDetect;
import com.zte.gameassist.lowsugar.detect.scene.Genshin.GenshinDetect;
import com.zte.gameassist.lowsugar.detect.scene.IYoloXScene;
import com.zte.gameassist.lowsugar.detect.scene.PubgGlobal.PubgGlobalDetect;
import com.zte.gameassist.lowsugar.detect.scene.SGame.SGameDetect;
import com.zte.gameassist.lowsugar.detect.scene.SGameGlobal.SGameGlobalDetect;
import com.zte.gameassist.lowsugar.detect.scene.Wildrift.WildriftDetect;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;
import com.zte.zscreenshot.ZScreenshot;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public class LowSugarDetect implements ILowSugarDetect, InterfaceEventListener, Runnable {
    public static final boolean u = GaLog.f17035c;

    /* renamed from: c, reason: collision with root package name */
    private final YOLOXncnn f16800c;

    /* renamed from: h, reason: collision with root package name */
    private ISceneDetect f16801h;

    /* renamed from: i, reason: collision with root package name */
    private final Context f16802i;

    /* renamed from: j, reason: collision with root package name */
    private ILowSugarDetect.DetectCallback f16803j;

    /* renamed from: k, reason: collision with root package name */
    private FullScreenInputMonitor f16804k;

    /* renamed from: l, reason: collision with root package name */
    private Supplier f16805l;

    /* renamed from: m, reason: collision with root package name */
    private Handler f16806m;

    /* renamed from: n, reason: collision with root package name */
    private int f16807n;

    /* renamed from: o, reason: collision with root package name */
    private ZScreenshot f16808o;

    /* renamed from: p, reason: collision with root package name */
    private boolean f16809p;

    /* renamed from: q, reason: collision with root package name */
    private YOLOXWindow f16810q;

    /* renamed from: r, reason: collision with root package name */
    private String f16811r;

    /* renamed from: s, reason: collision with root package name */
    private boolean f16812s;
    private List t;

    public LowSugarDetect(Context context, String str, Handler handler, Supplier supplier) {
        YOLOXncnn yOLOXncnn = new YOLOXncnn("yolox/lowsugar/lowsugar.param", "yolox/lowsugar/lowsugar.bin", IYoloXScene.f16861a);
        this.f16800c = yOLOXncnn;
        this.f16807n = 0;
        this.f16809p = false;
        this.t = new ArrayList();
        this.f16802i = context;
        this.f16806m = handler;
        this.f16805l = supplier;
        this.f16811r = str;
        o(str);
        this.f16804k = FullScreenInputMonitor.e();
        this.f16808o = new ZScreenshot();
        GaLog.e("LowSugarGameplay.LowSugarDetect", "mYOLOXncnn=" + yOLOXncnn + ", mCurrPkg = " + this.f16811r);
    }

    private void o(String str) {
        GaLog.e("LowSugarGameplay.LowSugarDetect", "initYoloXScene " + str);
        if (LowSugarUtils.f17022q.contains(str)) {
            SGameDetect sGameDetect = new SGameDetect(this.f16802i);
            this.f16801h = sGameDetect;
            sGameDetect.g(new ISceneDetect.SceneDetectCallback() { // from class: com.zte.gameassist.lowsugar.detect.LowSugarDetect.1
                @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect.SceneDetectCallback
                public void a(int i2) {
                    LowSugarDetect.this.f16807n = i2;
                    if (i2 == 4) {
                        LowSugarDetect.this.f16808o.f();
                    }
                }

                @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect.SceneDetectCallback
                public void b() {
                    LowSugarDetect.this.f16806m.post(LowSugarDetect.this);
                }
            });
        } else {
            if (LowSugarUtils.t.contains(str)) {
                this.f16801h = new GenshinDetect(this.f16802i);
                return;
            }
            if (LowSugarUtils.u.contains(str)) {
                this.f16801h = new WildriftDetect(this.f16802i);
            } else if (LowSugarUtils.f17023r.contains(str)) {
                this.f16801h = new SGameGlobalDetect(this.f16802i);
            } else if (LowSugarUtils.f17024s.contains(str)) {
                this.f16801h = new PubgGlobalDetect(this.f16802i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x012b A[Catch: all -> 0x006f, Exception -> 0x0072, TryCatch #0 {Exception -> 0x0072, blocks: (B:14:0x0054, B:16:0x006b, B:17:0x0075, B:29:0x00b1, B:30:0x0127, B:32:0x012b, B:33:0x013f, B:42:0x0136, B:44:0x013a, B:47:0x00bc, B:50:0x00c7, B:58:0x00d9, B:61:0x00e4, B:64:0x00ef, B:67:0x00fa, B:70:0x0105, B:71:0x010c, B:72:0x0113, B:73:0x011a), top: B:13:0x0054, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0136 A[Catch: all -> 0x006f, Exception -> 0x0072, TryCatch #0 {Exception -> 0x0072, blocks: (B:14:0x0054, B:16:0x006b, B:17:0x0075, B:29:0x00b1, B:30:0x0127, B:32:0x012b, B:33:0x013f, B:42:0x0136, B:44:0x013a, B:47:0x00bc, B:50:0x00c7, B:58:0x00d9, B:61:0x00e4, B:64:0x00ef, B:67:0x00fa, B:70:0x0105, B:71:0x010c, B:72:0x0113, B:73:0x011a), top: B:13:0x0054, outer: #1 }] */
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void p(final android.graphics.Bitmap r14) {
        /*
            Method dump skipped, instructions count: 393
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.detect.LowSugarDetect.p(android.graphics.Bitmap):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s, reason: merged with bridge method [inline-methods] */
    public void q(final Bitmap bitmap) {
        if (!this.f16806m.getLooper().isCurrentThread()) {
            this.f16806m.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.detect.b
                @Override // java.lang.Runnable
                public final void run() {
                    LowSugarDetect.this.q(bitmap);
                }
            });
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = true;
        if (this.f16808o != null) {
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        }
        GaLog.e("LowSugarGameplay.LowSugarDetect", "onTakeScreenShot = " + (System.currentTimeMillis() - currentTimeMillis));
        try {
            try {
                Size size = new Size(bitmap.getWidth(), bitmap.getHeight());
                YOLOXncnn.Obj[] a2 = this.f16800c.a(bitmap, false);
                YOLOXWindow yOLOXWindow = this.f16810q;
                if (yOLOXWindow != null) {
                    yOLOXWindow.setData(a2);
                }
                DetectParam detectParam = new DetectParam();
                ISceneDetect iSceneDetect = this.f16801h;
                List h2 = SceneConfig.h(a2, size);
                this.t = h2;
                int e2 = iSceneDetect.e(h2, detectParam);
                this.f16807n = e2;
                if (e2 == 0) {
                    if (!this.f16801h.f()) {
                        this.f16806m.postDelayed(this, 1000L);
                    }
                } else if (e2 == 2) {
                    z = this.f16803j.a(1, 1, bitmap, detectParam);
                } else if (e2 == 3) {
                    z = this.f16803j.a(1, 2, bitmap, detectParam);
                } else if (e2 == 5) {
                    z = this.f16803j.a(1, 3, bitmap, detectParam);
                } else if (e2 == 6) {
                    z = this.f16803j.a(1, 4, bitmap, detectParam);
                } else if (e2 == 9) {
                    z = this.f16803j.a(1, 5, bitmap, detectParam);
                } else if (e2 == 10) {
                    z = this.f16803j.a(1, 6, bitmap, detectParam);
                } else if (e2 == 14) {
                    z = this.f16803j.a(1, 7, bitmap, detectParam);
                } else if (e2 == 15) {
                    z = this.f16803j.a(1, 8, bitmap, detectParam);
                } else if (e2 == 17) {
                    z = this.f16803j.a(1, 9, bitmap, detectParam);
                }
                if (this.f16807n != 0) {
                    this.f16808o.f();
                }
                GaLog.e("LowSugarGameplay.LowSugarDetect", "mSceneIndex = " + this.f16807n);
                if (bitmap.isRecycled() || z) {
                    return;
                }
                bitmap.recycle();
            } catch (Exception e3) {
                GaLog.b("LowSugarGameplay.LowSugarDetect", "onTakeScreenShot = " + e3.getMessage());
                throw new RuntimeException(e3);
            }
        } catch (Throwable th) {
            if (bitmap != null && !bitmap.isRecycled() && !z) {
                bitmap.recycle();
            }
            throw th;
        }
    }

    @Override // com.zte.gameassist.lowsugar.detect.ILowSugarDetect
    public void a() {
        GaLog.e("LowSugarGameplay.LowSugarDetect", "stopDetect");
        this.f16812s = false;
        this.f16806m.removeCallbacks(this);
        this.f16800c.d();
        this.f16809p = false;
        this.f16804k.i(this);
        this.f16801h.a();
        ZScreenshot zScreenshot = this.f16808o;
        if (zScreenshot != null) {
            zScreenshot.f();
        }
        YOLOXWindow yOLOXWindow = this.f16810q;
        if (yOLOXWindow != null) {
            yOLOXWindow.e();
            this.f16810q = null;
        }
    }

    @Override // com.zte.gameassist.lowsugar.detect.ILowSugarDetect
    public void b() {
        GaLog.k("LowSugarGameplay.LowSugarDetect", "startManualPurpose mZScreenshot=" + this.f16808o);
        if (!this.f16800c.c()) {
            GaLog.k("LowSugarGameplay.LowSugarDetect", "startManualPurpose YOLOXncnn mNativePtr is null and init!");
            this.f16800c.b(this.f16802i.getAssets());
        }
        if (this.f16808o != null) {
            DisplayMetrics displayMetrics = this.f16802i.getResources().getDisplayMetrics();
            this.f16808o.e("LowSugarGameplay.LowSugarDetect", 0L, 1.0f, new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels), new ZScreenshot.OnBufferCallback() { // from class: com.zte.gameassist.lowsugar.detect.c
                @Override // com.zte.zscreenshot.ZScreenshot.OnBufferCallback
                public final void a(Bitmap bitmap) {
                    LowSugarDetect.this.p(bitmap);
                }
            });
        }
    }

    @Override // com.zte.gameassist.lowsugar.detect.ILowSugarDetect
    public void c() {
        this.f16812s = true;
        GaLog.e("LowSugarGameplay.LowSugarDetect", "startDetect delay=" + ((System.currentTimeMillis() - ((Long) this.f16805l.get()).longValue()) / 1000));
        this.f16800c.b(this.f16802i.getAssets());
        this.f16809p = true;
        this.f16804k.c(this);
        this.f16801h.c();
        this.f16806m.postDelayed(this, 500L);
        if (u) {
            if (this.f16810q == null) {
                this.f16810q = new YOLOXWindow(this.f16802i);
            }
            this.f16810q.k();
        }
    }

    @Override // com.zte.gameassist.lowsugar.detect.ILowSugarDetect
    public List d() {
        return LowSugarUtils.v;
    }

    @Override // com.zte.gameassist.input.InterfaceEventListener
    public void f(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 5) {
            int i2 = this.f16807n;
            if (((i2 == 2 || i2 == 3 || i2 == 5 || i2 == 6 || i2 == 9 || i2 == 14 || i2 == 15 || i2 == 17) && this.f16801h.b(i2, motionEvent)) || this.f16801h.d(this.f16807n, motionEvent)) {
                this.f16806m.removeCallbacks(this);
                long i3 = this.f16801h.i();
                if (i3 == 1000) {
                    int i4 = this.f16807n;
                    if (i4 == 6) {
                        i3 = 1800;
                    } else if (i4 == 5) {
                        i3 = 1500;
                    }
                }
                this.f16806m.postDelayed(this, i3);
            }
        }
    }

    @Override // com.zte.gameassist.lowsugar.detect.ILowSugarDetect
    public void g(ILowSugarDetect.DetectCallback detectCallback) {
        this.f16803j = detectCallback;
    }

    @Override // com.zte.gameassist.lowsugar.detect.ILowSugarDetect
    public int getSceneIndex() {
        return this.f16807n;
    }

    @Override // com.zte.gameassist.lowsugar.detect.ILowSugarDetect
    public boolean isDetecting() {
        return this.f16812s;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f16806m.removeCallbacks(this);
        if (!this.f16800c.c()) {
            GaLog.e("LowSugarGameplay.LowSugarDetect", "YOLOXncnn mNativePtr is null");
        } else {
            if (this.f16801h.h()) {
                GaLog.e("LowSugarGameplay.LowSugarDetect", "now is in sgame game and not takeshot!");
                return;
            }
            DisplayMetrics displayMetrics = this.f16802i.getResources().getDisplayMetrics();
            this.f16808o.e("LowSugarGameplay.LowSugarDetect", 0L, 1.0f, new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels), new ZScreenshot.OnBufferCallback() { // from class: com.zte.gameassist.lowsugar.detect.a
                @Override // com.zte.zscreenshot.ZScreenshot.OnBufferCallback
                public final void a(Bitmap bitmap) {
                    LowSugarDetect.this.q(bitmap);
                }
            });
        }
    }

    public String toString() {
        return "LowSugarDetect{, mSceneIndex=" + this.f16807n + ", mLastLabels=" + this.t + '}';
    }
}
