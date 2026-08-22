package cn.nubia.gameassist.dessert.policy.liquidcooling;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Choreographer;
import android.view.WindowManager;
import cn.nubia.gameassist.dessert.policy.liquidcooling.LiquidCoolingAnimationController;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class LiquidCoolingAnimationController {

    /* renamed from: j, reason: collision with root package name */
    private static final String f6375j = "LiquidCoolingAnimationController";

    /* renamed from: k, reason: collision with root package name */
    private static volatile LiquidCoolingAnimationController f6376k;

    /* renamed from: a, reason: collision with root package name */
    private Context f6377a;

    /* renamed from: b, reason: collision with root package name */
    private HandlerThread f6378b;

    /* renamed from: c, reason: collision with root package name */
    private Handler f6379c;

    /* renamed from: d, reason: collision with root package name */
    private Handler f6380d;

    /* renamed from: e, reason: collision with root package name */
    private WindowManager f6381e;

    /* renamed from: f, reason: collision with root package name */
    private LiquidCoolingAnimView f6382f;

    /* renamed from: g, reason: collision with root package name */
    private final Choreographer.FrameCallback f6383g;

    /* renamed from: h, reason: collision with root package name */
    private long f6384h = 0;

    /* renamed from: i, reason: collision with root package name */
    private boolean f6385i = false;

    private LiquidCoolingAnimationController(Context context) {
        this.f6377a = context;
        this.f6381e = (WindowManager) context.getSystemService("window");
        HandlerThread handlerThread = new HandlerThread("LC_DecodeThread");
        this.f6378b = handlerThread;
        handlerThread.start();
        this.f6379c = new Handler(this.f6378b.getLooper());
        this.f6380d = new Handler(ThreadManager.c().i());
        this.f6383g = new Choreographer.FrameCallback() { // from class: cn.nubia.gameassist.dessert.policy.liquidcooling.LiquidCoolingAnimationController.1

            /* renamed from: cn.nubia.gameassist.dessert.policy.liquidcooling.LiquidCoolingAnimationController$1$1, reason: invalid class name and collision with other inner class name */
            class RunnableC00061 implements Runnable {

                /* renamed from: c, reason: collision with root package name */
                final /* synthetic */ int f6387c;

                RunnableC00061(int i2) {
                    this.f6387c = i2;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public /* synthetic */ void b(Bitmap bitmap, int i2) {
                    LiquidCoolingAnimationController.this.f6382f.b(bitmap, i2);
                }

                @Override // java.lang.Runnable
                public void run() {
                    final Bitmap g2 = LiquidCoolingAnimationController.this.g(this.f6387c);
                    Handler handler = LiquidCoolingAnimationController.this.f6380d;
                    final int i2 = this.f6387c;
                    handler.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.liquidcooling.a
                        @Override // java.lang.Runnable
                        public final void run() {
                            LiquidCoolingAnimationController.AnonymousClass1.RunnableC00061.this.b(g2, i2);
                        }
                    });
                }
            }

            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j2) {
                if (LiquidCoolingAnimationController.this.f6385i) {
                    int i2 = (int) (((j2 - LiquidCoolingAnimationController.this.f6384h) / 1000000) / 16.666666f);
                    if (i2 >= 170) {
                        LiquidCoolingAnimationController.this.k();
                    } else {
                        LiquidCoolingAnimationController.this.f6379c.post(new RunnableC00061(i2));
                        Choreographer.getInstance().postFrameCallback(this);
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Bitmap g(int r6) {
        /*
            r5 = this;
            java.lang.String r0 = "close inputStream error:"
            r1 = 0
            java.lang.String r2 = "liquidcooling%03d.png"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.String r6 = java.lang.String.format(r2, r6)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            android.content.Context r5 = r5.f6377a     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r2.<init>()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.String r3 = "liquidcooling/"
            r2.append(r3)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r2.append(r6)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.io.InputStream r5 = r5.open(r6)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeStream(r5)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            if (r5 == 0) goto L4b
            r5.close()     // Catch: java.lang.Exception -> L36
            goto L4b
        L36:
            r5 = move-exception
            java.lang.String r1 = cn.nubia.gameassist.dessert.policy.liquidcooling.LiquidCoolingAnimationController.f6375j
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            com.zte.gameassist.utils.GaLog.b(r1, r5)
        L4b:
            return r6
        L4c:
            r6 = move-exception
            r1 = r5
            goto L87
        L4f:
            r6 = move-exception
            goto L55
        L51:
            r6 = move-exception
            goto L87
        L53:
            r6 = move-exception
            r5 = r1
        L55:
            java.lang.String r2 = cn.nubia.gameassist.dessert.policy.liquidcooling.LiquidCoolingAnimationController.f6375j     // Catch: java.lang.Throwable -> L4c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4c
            r3.<init>()     // Catch: java.lang.Throwable -> L4c
            java.lang.String r4 = "getAssets resource error:"
            r3.append(r4)     // Catch: java.lang.Throwable -> L4c
            r3.append(r6)     // Catch: java.lang.Throwable -> L4c
            java.lang.String r6 = r3.toString()     // Catch: java.lang.Throwable -> L4c
            com.zte.gameassist.utils.GaLog.b(r2, r6)     // Catch: java.lang.Throwable -> L4c
            if (r5 == 0) goto L86
            r5.close()     // Catch: java.lang.Exception -> L71
            goto L86
        L71:
            r5 = move-exception
            java.lang.String r6 = cn.nubia.gameassist.dessert.policy.liquidcooling.LiquidCoolingAnimationController.f6375j
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            com.zte.gameassist.utils.GaLog.b(r6, r5)
        L86:
            return r1
        L87:
            if (r1 == 0) goto La2
            r1.close()     // Catch: java.lang.Exception -> L8d
            goto La2
        L8d:
            r5 = move-exception
            java.lang.String r1 = cn.nubia.gameassist.dessert.policy.liquidcooling.LiquidCoolingAnimationController.f6375j
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            com.zte.gameassist.utils.GaLog.b(r1, r5)
        La2:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.dessert.policy.liquidcooling.LiquidCoolingAnimationController.g(int):android.graphics.Bitmap");
    }

    public static LiquidCoolingAnimationController h(Context context) {
        if (f6376k == null) {
            synchronized (LiquidCoolingAnimationController.class) {
                try {
                    if (f6376k == null) {
                        f6376k = new LiquidCoolingAnimationController(context);
                    }
                } finally {
                }
            }
        }
        return f6376k;
    }

    private WindowManager.LayoutParams i() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2008;
        layoutParams.flags = 75564824;
        layoutParams.gravity = 17;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 0;
        WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
        layoutParams.setTitle("LiquidCoolingAnimView");
        layoutParams.layoutInDisplayCutoutMode = 1;
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        return layoutParams;
    }

    public void j() {
        try {
            if (this.f6385i) {
                return;
            }
            this.f6385i = true;
            LiquidCoolingAnimView liquidCoolingAnimView = new LiquidCoolingAnimView(this.f6377a);
            this.f6382f = liquidCoolingAnimView;
            this.f6381e.addView(liquidCoolingAnimView, i());
            this.f6384h = System.nanoTime();
            Choreographer.getInstance().postFrameCallback(this.f6383g);
        } catch (Exception e2) {
            GaLog.b(f6375j, "startAnimation error:" + e2);
        }
    }

    public void k() {
        try {
            if (this.f6385i) {
                this.f6385i = false;
                Choreographer.getInstance().removeFrameCallback(this.f6383g);
                if (this.f6382f.isAttachedToWindow()) {
                    this.f6381e.removeView(this.f6382f);
                }
                this.f6382f = null;
            }
        } catch (Exception e2) {
            GaLog.b(f6375j, "stopAnimation error:" + e2);
        }
    }
}
