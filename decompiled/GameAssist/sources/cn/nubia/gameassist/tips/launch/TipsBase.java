package cn.nubia.gameassist.tips.launch;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Path;
import cn.nubia.gameassist.tips.GameAssistLaunchTips;

/* loaded from: classes.dex */
public abstract class TipsBase {

    /* renamed from: a, reason: collision with root package name */
    protected final GameAssistLaunchTips f7571a;

    /* renamed from: b, reason: collision with root package name */
    protected final Context f7572b;

    /* renamed from: c, reason: collision with root package name */
    protected final Resources f7573c;

    /* renamed from: d, reason: collision with root package name */
    protected float[] f7574d;

    /* renamed from: e, reason: collision with root package name */
    protected float[] f7575e;

    /* renamed from: f, reason: collision with root package name */
    protected float[] f7576f;

    /* renamed from: g, reason: collision with root package name */
    protected float[] f7577g;

    protected TipsBase(GameAssistLaunchTips gameAssistLaunchTips, Context context, Resources resources) {
        this.f7571a = gameAssistLaunchTips;
        this.f7572b = context;
        this.f7573c = resources;
        gameAssistLaunchTips.post(new Runnable() { // from class: cn.nubia.gameassist.tips.launch.a
            @Override // java.lang.Runnable
            public final void run() {
                TipsBase.this.l();
            }
        });
    }

    private float[] e(Path path) {
        float[] approximate = path.approximate(0.002f);
        int length = approximate.length / 3;
        float[] fArr = new float[length];
        float[] fArr2 = new float[length];
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            float f4 = approximate[i3];
            int i4 = i3 + 2;
            float f5 = approximate[i3 + 1];
            i3 += 3;
            float f6 = approximate[i4];
            if (f4 == f2 && f5 != f3) {
                throw new IllegalArgumentException("The Path cannot have discontinuity in the X axis. x=" + f5 + " prevX=" + f3);
            }
            if (f5 < f3) {
                throw new IllegalArgumentException("The Path cannot loop back on itself. x=" + f5 + " prevX=" + f3);
            }
            fArr[i2] = f5;
            fArr2[i2] = f6;
            i2++;
            f3 = f5;
            f2 = f4;
        }
        float[] fArr3 = new float[125];
        int i5 = 0;
        for (int i6 = 0; i6 < 125; i6++) {
            fArr3[i6] = -1.0f;
            while (true) {
                if (i5 < length) {
                    float f7 = fArr[i5];
                    float f8 = i6;
                    if (f7 <= f8) {
                        int i7 = i5 + 1;
                        float f9 = fArr[i7];
                        if (f8 <= f9) {
                            float f10 = fArr2[i5];
                            fArr3[i6] = f10 + (((f8 - f7) * (fArr2[i7] - f10)) / (f9 - f7));
                            if (i5 > 0) {
                                i5--;
                            }
                        }
                    }
                    i5++;
                }
            }
        }
        return fArr3;
    }

    public abstract boolean a(Canvas canvas, long j2, long j3, int i2, float f2);

    public abstract Path b();

    protected AssetManager c() {
        return this.f7573c.getAssets();
    }

    public String d() {
        return this.f7571a.getGamePackage();
    }

    public abstract Path f();

    public String g(int i2) {
        return this.f7572b.getString(i2);
    }

    public abstract Path h();

    public abstract Path i();

    public void j() {
        l();
    }

    public abstract void k();

    protected void l() {
        Path h2 = h();
        this.f7574d = h2 != null ? e(h2) : null;
        Path i2 = i();
        this.f7575e = i2 != null ? e(i2) : null;
        Path b2 = b();
        this.f7576f = b2 != null ? e(b2) : null;
        Path f2 = f();
        this.f7577g = f2 != null ? e(f2) : null;
    }

    protected float m(float f2) {
        return f2 * 1.0f;
    }

    protected float n(float f2) {
        return f2 * 1.0f;
    }
}
