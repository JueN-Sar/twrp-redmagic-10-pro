package cn.nubia.gameassist.panel.drawable.diplogen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.test.GameAssistTestActivity;
import cn.nubia.gameassist.theme.Theme;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.TraceWrapper;

/* loaded from: classes.dex */
public class NeonLampDrawable extends DiplogenDrawable {
    public static final float E;
    public static final RatioPoint[][] F;
    public static final RatioPoint[][] G;
    public static final RatioPoint[][] H;
    public static final RatioPoint[][] I;
    public static final RatioPoint[] J;
    public static final RatioPoint[] K;
    public static final float[] L;
    public static final float[] M;
    protected static final int N;
    protected static final int O;
    private LightOff A;
    private Background B;
    private LightBase C;
    private LightShare D;

    /* renamed from: o, reason: collision with root package name */
    protected final int f6924o;

    /* renamed from: p, reason: collision with root package name */
    protected float f6925p;

    /* renamed from: q, reason: collision with root package name */
    protected float f6926q;

    /* renamed from: r, reason: collision with root package name */
    protected float f6927r;

    /* renamed from: s, reason: collision with root package name */
    protected Path f6928s;
    protected Path t;
    protected Matrix u;
    protected final Paint v;
    protected RatioPoint[][] w;
    protected RatioPoint[][] x;
    protected RatioPoint[][] y;
    protected RatioPoint[][] z;

    private class Background {

        /* renamed from: a, reason: collision with root package name */
        private int f6929a;

        /* renamed from: b, reason: collision with root package name */
        private int f6930b;

        /* renamed from: c, reason: collision with root package name */
        private int f6931c;

        /* renamed from: d, reason: collision with root package name */
        private int f6932d;

        /* renamed from: e, reason: collision with root package name */
        private float f6933e;

        /* renamed from: f, reason: collision with root package name */
        private int f6934f;

        /* renamed from: g, reason: collision with root package name */
        private int f6935g;

        public Background() {
        }

        protected void a(Rect rect) {
            if (DiplogenDrawable.e()) {
                this.f6934f = 0;
            } else {
                this.f6934f = (int) (NeonLampDrawable.this.f6925p * 0.07f);
            }
        }

        public void b(Canvas canvas) {
            NeonLampDrawable neonLampDrawable = NeonLampDrawable.this;
            Theme theme = neonLampDrawable.f6852l;
            if (theme != null) {
                Drawable c2 = neonLampDrawable.c(theme.f7452s.f7470c);
                int i2 = this.f6934f;
                c2.setBounds(i2, 0, this.f6932d + i2, this.f6931c);
                c2.draw(canvas);
            }
        }

        public void c(Theme theme) {
            if (this.f6929a == 0 || theme.f7436c != this.f6935g) {
                this.f6935g = theme.f7436c;
                NeonLampDrawable neonLampDrawable = NeonLampDrawable.this;
                Bitmap b2 = neonLampDrawable.b(neonLampDrawable.f6852l.f7452s.f7470c);
                this.f6929a = b2.getHeight();
                int width = b2.getWidth();
                this.f6930b = width;
                float f2 = NeonLampDrawable.this.f6925p;
                int i2 = this.f6929a;
                float f3 = f2 / i2;
                this.f6933e = f3;
                this.f6931c = (int) (i2 * f3);
                this.f6932d = (int) (f3 * width);
            }
        }
    }

    private class LightBase {

        /* renamed from: a, reason: collision with root package name */
        private ComposeShader f6937a;

        /* renamed from: b, reason: collision with root package name */
        private int f6938b;

        /* renamed from: c, reason: collision with root package name */
        private int f6939c;

        /* renamed from: d, reason: collision with root package name */
        private int f6940d;

        /* renamed from: e, reason: collision with root package name */
        private int f6941e;

        /* renamed from: f, reason: collision with root package name */
        private int f6942f;

        /* renamed from: g, reason: collision with root package name */
        private RectF f6943g = new RectF();

        /* renamed from: h, reason: collision with root package name */
        protected final Paint f6944h;

        /* renamed from: i, reason: collision with root package name */
        private float f6945i;

        /* renamed from: j, reason: collision with root package name */
        private int f6946j;

        public LightBase() {
            Paint paint = new Paint();
            this.f6944h = paint;
            paint.setAntiAlias(true);
            paint.setAlpha(255);
        }

        protected void a(Rect rect) {
            if (DiplogenDrawable.e()) {
                this.f6938b = 0;
            } else {
                this.f6938b = (int) (NeonLampDrawable.this.f6925p * 0.072f);
            }
        }

        public void b(Canvas canvas) {
            if (NeonLampDrawable.this.f6852l == null || this.f6937a == null) {
                return;
            }
            int save = canvas.save();
            this.f6944h.setAlpha(255);
            RectF rectF = this.f6943g;
            canvas.translate(rectF.left, rectF.top);
            float f2 = this.f6945i;
            canvas.scale(f2, f2);
            this.f6944h.setShader(this.f6937a);
            canvas.drawRect(0.0f, 0.0f, this.f6943g.width() / this.f6945i, this.f6943g.height() / this.f6945i, this.f6944h);
            this.f6944h.setShader(null);
            canvas.restoreToCount(save);
        }

        public void c(Theme theme) {
            if (this.f6939c == 0 || theme.f7436c != this.f6946j) {
                this.f6946j = theme.f7436c;
                NeonLampDrawable neonLampDrawable = NeonLampDrawable.this;
                Bitmap b2 = neonLampDrawable.b(neonLampDrawable.f6852l.f7452s.f7471d);
                this.f6939c = b2.getHeight();
                int width = b2.getWidth();
                this.f6940d = width;
                float f2 = NeonLampDrawable.this.f6925p;
                int i2 = this.f6939c;
                float f3 = f2 / i2;
                this.f6945i = f3;
                int i3 = (int) (i2 * f3);
                this.f6941e = i3;
                this.f6942f = (int) (f3 * width);
                this.f6943g.set(this.f6938b, 0.0f, r3 + r4, i3);
            }
        }

        public void d(boolean z) {
            if (z) {
                return;
            }
            this.f6937a = null;
        }

        protected void e() {
            Theme.NeonLampTheme neonLampTheme;
            NeonLampDrawable neonLampDrawable = NeonLampDrawable.this;
            Theme theme = neonLampDrawable.f6852l;
            if (theme == null || (neonLampTheme = theme.f7452s) == null) {
                return;
            }
            Bitmap b2 = neonLampDrawable.b(neonLampTheme.f7471d);
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            this.f6937a = new ComposeShader(new BitmapShader(b2, tileMode, tileMode), new LinearGradient(0.0f, b2.getHeight(), 0.0f, 0.0f, NeonLampDrawable.this.h(-0.1f, false), DiplogenDrawable.e() ? NeonLampDrawable.M : NeonLampDrawable.L, Shader.TileMode.MIRROR), PorterDuff.Mode.DST_IN);
        }
    }

    private class LightOff {

        /* renamed from: a, reason: collision with root package name */
        private int f6948a;

        /* renamed from: b, reason: collision with root package name */
        private int f6949b;

        /* renamed from: c, reason: collision with root package name */
        private int f6950c;

        /* renamed from: d, reason: collision with root package name */
        private int f6951d;

        /* renamed from: e, reason: collision with root package name */
        private int f6952e;

        /* renamed from: f, reason: collision with root package name */
        private float f6953f;

        /* renamed from: g, reason: collision with root package name */
        private int f6954g;

        public LightOff() {
        }

        protected void a(Rect rect) {
            if (DiplogenDrawable.e()) {
                this.f6948a = 0;
            } else {
                this.f6948a = (int) (NeonLampDrawable.this.f6925p * 0.078999996f);
            }
        }

        public void b(Canvas canvas) {
            NeonLampDrawable neonLampDrawable = NeonLampDrawable.this;
            Theme theme = neonLampDrawable.f6852l;
            if (theme != null) {
                Drawable c2 = neonLampDrawable.c(theme.f7452s.f7469b);
                Theme theme2 = NeonLampDrawable.this.f6852l;
                if (theme2 != null) {
                    if (theme2.f7450q) {
                        c2.setAlpha((int) (theme2.f7449p * 255.0f));
                    } else {
                        boolean z = theme2.f7451r;
                        if (!z || theme2.f7449p >= 0.5d) {
                            if (z) {
                                float f2 = theme2.f7449p;
                                if (f2 < 1.0f) {
                                    c2.setAlpha((int) ((f2 - 0.5f) * 2.0f * 255.0f));
                                }
                            }
                            c2.setAlpha(255);
                        } else {
                            c2.setAlpha(0);
                        }
                    }
                }
                int i2 = this.f6948a;
                c2.setBounds(i2, 0, this.f6952e + i2, this.f6951d);
                c2.draw(canvas);
            }
        }

        public void c(Theme theme) {
            if (this.f6949b == 0 || theme.f7436c != this.f6954g) {
                this.f6954g = theme.f7436c;
                NeonLampDrawable neonLampDrawable = NeonLampDrawable.this;
                Bitmap b2 = neonLampDrawable.b(neonLampDrawable.f6852l.f7452s.f7469b);
                this.f6949b = b2.getHeight();
                int width = b2.getWidth();
                this.f6950c = width;
                float f2 = NeonLampDrawable.this.f6925p;
                int i2 = this.f6949b;
                float f3 = f2 / i2;
                this.f6953f = f3;
                this.f6951d = (int) (i2 * f3);
                this.f6952e = (int) (f3 * width);
            }
        }
    }

    private class LightShare {

        /* renamed from: a, reason: collision with root package name */
        private ComposeShader f6956a;

        /* renamed from: b, reason: collision with root package name */
        private int f6957b;

        /* renamed from: c, reason: collision with root package name */
        private int f6958c;

        /* renamed from: d, reason: collision with root package name */
        private int f6959d;

        /* renamed from: e, reason: collision with root package name */
        private int f6960e;

        /* renamed from: f, reason: collision with root package name */
        private int f6961f;

        /* renamed from: g, reason: collision with root package name */
        private RectF f6962g = new RectF();

        /* renamed from: h, reason: collision with root package name */
        private float f6963h;

        /* renamed from: i, reason: collision with root package name */
        private int f6964i;

        /* renamed from: j, reason: collision with root package name */
        private final Paint f6965j;

        public LightShare() {
            Paint paint = new Paint(2);
            this.f6965j = paint;
            paint.setAntiAlias(true);
            paint.setDither(true);
        }

        protected void a(Rect rect) {
            if (DiplogenDrawable.e()) {
                this.f6957b = 0;
            } else {
                this.f6957b = (int) (NeonLampDrawable.this.f6925p * 0.0f);
            }
        }

        public void b(Canvas canvas) {
            if (NeonLampDrawable.this.f6852l == null || this.f6956a == null) {
                return;
            }
            int save = canvas.save();
            this.f6965j.setAlpha(255);
            RectF rectF = this.f6962g;
            canvas.translate(rectF.left, rectF.top);
            float f2 = this.f6963h;
            canvas.scale(f2, f2);
            this.f6965j.setShader(this.f6956a);
            canvas.drawRect(0.0f, 0.0f, this.f6962g.width() / this.f6963h, this.f6962g.height() / this.f6963h, this.f6965j);
            this.f6965j.setShader(null);
            canvas.restoreToCount(save);
        }

        public void c(Theme theme) {
            if (this.f6958c == 0 || theme.f7436c != this.f6964i) {
                this.f6964i = theme.f7436c;
                NeonLampDrawable neonLampDrawable = NeonLampDrawable.this;
                Bitmap b2 = neonLampDrawable.b(neonLampDrawable.f6852l.f7452s.f7472e);
                this.f6958c = b2.getHeight();
                int width = b2.getWidth();
                this.f6959d = width;
                float f2 = NeonLampDrawable.this.f6925p;
                int i2 = this.f6958c;
                float f3 = f2 / i2;
                this.f6963h = f3;
                int i3 = (int) (i2 * f3);
                this.f6960e = i3;
                this.f6961f = (int) (f3 * width);
                this.f6962g.set(this.f6957b, 0.0f, r3 + r4, i3);
            }
        }

        public void d(boolean z) {
            if (z) {
                return;
            }
            this.f6956a = null;
        }

        protected void e() {
            Theme.NeonLampTheme neonLampTheme;
            NeonLampDrawable neonLampDrawable = NeonLampDrawable.this;
            Theme theme = neonLampDrawable.f6852l;
            if (theme == null || (neonLampTheme = theme.f7452s) == null) {
                return;
            }
            Bitmap b2 = neonLampDrawable.b(neonLampTheme.f7472e);
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            this.f6956a = new ComposeShader(new BitmapShader(b2, tileMode, tileMode), new LinearGradient(0.0f, b2.getHeight(), 0.0f, 0.0f, NeonLampDrawable.this.h(0.0f, true), DiplogenDrawable.e() ? NeonLampDrawable.M : NeonLampDrawable.L, Shader.TileMode.MIRROR), PorterDuff.Mode.DST_IN);
        }
    }

    static {
        Resources d2 = InflaterHelper.d();
        int i2 = d2.getConfiguration().densityDpi;
        boolean isTabletProduct = ZteFeature.isTabletProduct();
        String str = i2 >= 560 ? isTabletProduct ? "diplogen_path_pint_x_pad_scale_560dpi" : "diplogen_path_pint_x_scale_560dpi" : i2 >= 520 ? isTabletProduct ? "diplogen_path_pint_x_pad_scale_520dpi" : "diplogen_path_pint_x_scale_520dpi" : i2 >= 480 ? isTabletProduct ? "diplogen_path_pint_x_pad_scale_480dpi" : "diplogen_path_pint_x_scale_480dpi" : i2 >= 420 ? isTabletProduct ? "diplogen_path_pint_x_pad_scale_420dpi" : "diplogen_path_pint_x_scale_420dpi" : i2 >= 400 ? isTabletProduct ? "diplogen_path_pint_x_pad_scale_400dpi" : "diplogen_path_pint_x_scale_400dpi" : i2 >= 320 ? isTabletProduct ? "diplogen_path_pint_x_pad_scale_320dpi" : "diplogen_path_pint_x_scale_320dpi" : i2 >= 300 ? isTabletProduct ? "diplogen_path_pint_x_pad_scale_300dpi" : "diplogen_path_pint_x_scale_300dpi" : i2 >= 260 ? isTabletProduct ? "diplogen_path_pint_x_pad_scale_260dpi" : "diplogen_path_pint_x_scale_260dpi" : isTabletProduct ? "diplogen_path_pint_x_pad_scale" : "diplogen_path_pint_x_scale";
        int identifier = d2.getIdentifier(str, "string", InflaterHelper.b().getPackageName());
        if (identifier != 0) {
            E = Float.parseFloat(d2.getString(identifier));
        } else {
            String string = Settings.Global.getString(InflaterHelper.b().getContentResolver(), "diplogen_path_pint_x_scale");
            if (string == null) {
                string = d2.getString(R.string.diplogen_path_pint_x_scale);
            }
            float parseFloat = Float.parseFloat(string);
            E = parseFloat;
            GaLog.a("GameAssistWindowManager", "DiplogenDrawable not find res string R.string." + str + " value=" + parseFloat);
        }
        float f2 = E;
        F = new RatioPoint[][]{new RatioPoint[]{new RatioPoint(0.1572f * f2, 0.0f)}, new RatioPoint[]{new RatioPoint(0.3065f * f2, 0.2818f)}, new RatioPoint[]{new RatioPoint(f2 * 0.315f, 0.298f), new RatioPoint(f2 * 0.315f, 0.32133f), new RatioPoint(0.315f * f2, 0.3422f)}, new RatioPoint[]{new RatioPoint(0.3063f * f2, 0.3581f)}, new RatioPoint[]{new RatioPoint(0.008f * f2, 1.0f)}};
        G = new RatioPoint[][]{new RatioPoint[]{new RatioPoint(0.2115f * f2, 0.0f)}, new RatioPoint[]{new RatioPoint(0.3616f * f2, 0.277f)}, new RatioPoint[]{new RatioPoint(f2 * 0.37133f, 0.29467f), new RatioPoint(f2 * 0.37133f, 0.32133f), new RatioPoint(0.37133f * f2, 0.3472f)}, new RatioPoint[]{new RatioPoint(0.3563f * f2, 0.37167f)}, new RatioPoint[]{new RatioPoint(f2 * 0.018f, 1.0f)}};
        H = new RatioPoint[][]{new RatioPoint[]{new RatioPoint(0.3606f, 0.0f)}, new RatioPoint[]{new RatioPoint(0.4515f, 0.17122f)}, new RatioPoint[]{new RatioPoint(0.45814f, 0.18353f), new RatioPoint(0.4591f, 0.19659f), new RatioPoint(0.46007f, 0.20966f)}, new RatioPoint[]{new RatioPoint(0.45364f, 0.22433f)}, new RatioPoint[]{new RatioPoint(0.20174f, 0.76875f)}, new RatioPoint[]{new RatioPoint(0.19196f, 0.79095f), new RatioPoint(0.18882f, 0.79527f), new RatioPoint(0.18568f, 0.79959f)}, new RatioPoint[]{new RatioPoint(0.17512f, 0.81251f)}, new RatioPoint[]{new RatioPoint(0.0f, 0.993f)}};
        I = new RatioPoint[][]{new RatioPoint[]{new RatioPoint(0.39477f, 0.0f)}, new RatioPoint[]{new RatioPoint(0.48854f, 0.17282f)}, new RatioPoint[]{new RatioPoint(0.49593f, 0.1861f), new RatioPoint(0.49496f, 0.19981f), new RatioPoint(0.49464f, 0.21608f)}, new RatioPoint[]{new RatioPoint(0.48822f, 0.22701f)}, new RatioPoint[]{new RatioPoint(0.20252f, 0.77897f)}, new RatioPoint[]{new RatioPoint(0.19792f, 0.78916f), new RatioPoint(0.19162f, 0.79874f), new RatioPoint(0.18568f, 0.80724f)}, new RatioPoint[]{new RatioPoint(0.17816f, 0.81549f)}, new RatioPoint[]{new RatioPoint(0.0f, 0.997f)}};
        RatioPoint[] ratioPointArr = {new RatioPoint(1.0f, 0.95f), new RatioPoint(0.94f, 0.89f), new RatioPoint(0.88f, 0.832f), new RatioPoint(0.822f, 0.772f), new RatioPoint(0.762f, 0.713f), new RatioPoint(0.703f, 0.654f), new RatioPoint(0.645f, 0.594f), new RatioPoint(0.584f, 0.535f), new RatioPoint(0.525f, 0.476f), new RatioPoint(0.466f, 0.417f), new RatioPoint(0.408f, 0.358f), new RatioPoint(0.348f, 0.299f), new RatioPoint(0.289f, 0.239f), new RatioPoint(0.23f, 0.18f), new RatioPoint(0.172f, 0.121f), new RatioPoint(0.113f, 0.062f), new RatioPoint(0.053f, 0.0f)};
        J = ratioPointArr;
        RatioPoint[] ratioPointArr2 = {new RatioPoint(1.0f, 0.966f), new RatioPoint(0.961f, 0.929f), new RatioPoint(0.923f, 0.892f), new RatioPoint(0.886f, 0.855f), new RatioPoint(0.849f, 0.818f), new RatioPoint(0.812f, 0.781f), new RatioPoint(0.775f, 0.744f), new RatioPoint(0.738f, 0.707f), new RatioPoint(0.701f, 0.67f), new RatioPoint(0.664f, 0.633f), new RatioPoint(0.627f, 0.595f), new RatioPoint(0.59f, 0.558f), new RatioPoint(0.553f, 0.521f), new RatioPoint(0.516f, 0.484f), new RatioPoint(0.478f, 0.447f), new RatioPoint(0.441f, 0.41f), new RatioPoint(0.404f, 0.0f)};
        K = ratioPointArr2;
        L = new float[ratioPointArr.length * 2];
        M = new float[ratioPointArr2.length * 2];
        int i3 = 0;
        int i4 = 0;
        while (true) {
            RatioPoint[] ratioPointArr3 = J;
            if (i4 >= ratioPointArr3.length) {
                break;
            }
            float[] fArr = L;
            int i5 = i4 * 2;
            RatioPoint ratioPoint = ratioPointArr3[(ratioPointArr3.length - 1) - i4];
            fArr[i5] = ratioPoint.f6973b;
            fArr[i5 + 1] = ratioPoint.f6972a;
            i4++;
        }
        while (true) {
            RatioPoint[] ratioPointArr4 = K;
            if (i3 >= ratioPointArr4.length) {
                N = J.length;
                O = ratioPointArr4.length;
                return;
            }
            float[] fArr2 = M;
            int i6 = i3 * 2;
            RatioPoint ratioPoint2 = ratioPointArr4[(ratioPointArr4.length - 1) - i3];
            fArr2[i6] = ratioPoint2.f6973b;
            fArr2[i6 + 1] = ratioPoint2.f6972a;
            i3++;
        }
    }

    public NeonLampDrawable(Context context, int i2) {
        super(context, i2);
        this.f6924o = 6;
        this.f6926q = 0.0f;
        this.v = new Paint();
        this.D = new LightShare();
        this.C = new LightBase();
        this.B = new Background();
        this.A = new LightOff();
    }

    private void j(Canvas canvas) {
        this.B.b(canvas);
        Theme theme = this.f6852l;
        if (theme == null || theme.f7449p != 0.0f) {
            this.A.b(canvas);
            this.C.b(canvas);
            this.D.b(canvas);
        }
    }

    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable, cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        super.d(theme);
        float f2 = theme.f7449p;
        if (theme.f7450q) {
            i(((this.f6927r + 0.5f) * f2) - 0.5f);
        } else if (theme.f7451r && f2 >= 0.12f) {
            if (f2 < 0.42f) {
                i((4.0000005f * f2) - 0.7800001f);
            } else if (f2 < 0.5f) {
                i(1.5f);
            } else {
                i(((-2.6f) * f2) + 2.8f);
            }
        }
        this.C.c(theme);
        this.D.c(theme);
        this.B.c(theme);
        this.A.c(theme);
    }

    @Override // android.graphics.drawable.Drawable
    @VisibleForTesting
    public void draw(Canvas canvas) {
        TraceWrapper.traceBegin(8L, getClass().getSimpleName());
        int save = canvas.save();
        Matrix matrix = canvas.getMatrix();
        canvas.setMatrix(this.u);
        j(canvas);
        canvas.setMatrix(matrix);
        canvas.restoreToCount(save);
        if (isVisible() && !GameAssistTestActivity.f7433c) {
            invalidateSelf();
        }
        TraceWrapper.traceEnd(8L);
    }

    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable
    void f(boolean z) {
        this.D.d(z);
        this.C.d(z);
    }

    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable
    void g(Rect rect) {
        this.u = DiplogenUtils.d(this.f6849i, new Matrix(), rect);
    }

    public int[] h(float f2, boolean z) {
        float f3;
        int i2 = DiplogenDrawable.e() ? O : N;
        float f4 = i2;
        float f5 = this.f6926q + f2;
        int i3 = (int) (f5 / (1.0f / f4));
        float f6 = ((f5 * f4) - i3) / 6.0f;
        int[] iArr = new int[(DiplogenDrawable.e() ? K.length : J.length) * 2];
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 >= i3) {
                f3 = (1.0f - ((i4 - i3) * 0.16666667f)) + f6;
                if (!z) {
                    f3 *= 2.0f;
                }
                if (f3 < 0.0f) {
                    f3 = 0.0f;
                } else if (f3 <= 1.0f) {
                }
                int i5 = i4 * 2;
                int i6 = (((int) (f3 * 255.0f)) & 255) << 24;
                iArr[i5] = i6;
                iArr[i5 + 1] = i6;
            }
            f3 = 1.0f;
            int i52 = i4 * 2;
            int i62 = (((int) (f3 * 255.0f)) & 255) << 24;
            iArr[i52] = i62;
            iArr[i52 + 1] = i62;
        }
        return iArr;
    }

    protected void i(float f2) {
        this.f6926q = f2;
        this.D.e();
        this.C.e();
        invalidateSelf();
    }

    public void k(float f2) {
        this.f6927r = f2;
        Theme theme = this.f6852l;
        if (theme == null || theme.f7449p == 1.0f) {
            i(f2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0057  */
    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable, android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onBoundsChange(android.graphics.Rect r6) {
        /*
            r5 = this;
            super.onBoundsChange(r6)
            int r0 = r5.f6849i
            r1 = 1
            if (r0 == 0) goto L20
            if (r0 == r1) goto L18
            r2 = 2
            if (r0 == r2) goto L20
            r2 = 3
            if (r0 == r2) goto L18
            int r0 = cn.nubia.gameassist.panel.GameAssistWindowManager.Q()
            float r0 = (float) r0
            r5.f6925p = r0
            goto L27
        L18:
            int r0 = r6.width()
            float r0 = (float) r0
            r5.f6925p = r0
            goto L27
        L20:
            int r0 = r6.height()
            float r0 = (float) r0
            r5.f6925p = r0
        L27:
            boolean r0 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable.e()
            r2 = 0
            if (r0 == 0) goto L57
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable.H
            float r3 = r5.f6925p
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.h(r0, r3, r3)
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r3 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.i(r0, r2, r2)
            r5.w = r3
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.i(r0, r2, r2)
            r5.x = r0
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable.I
            float r3 = r5.f6925p
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.h(r0, r3, r3)
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r3 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.i(r0, r2, r2)
            r5.y = r3
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.i(r0, r2, r2)
            r5.z = r0
            goto L97
        L57:
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable.F
            float r3 = r5.f6925p
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.h(r0, r3, r3)
            r3 = -1138501878(0xffffffffbc23d70a, float:-0.01)
            float r4 = r5.f6925p
            float r4 = r4 * r3
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r3 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.i(r0, r4, r2)
            r5.w = r3
            r3 = -1153131610(0xffffffffbb449ba6, float:-0.003)
            float r4 = r5.f6925p
            float r4 = r4 * r3
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.i(r0, r4, r2)
            r5.x = r0
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable.G
            float r3 = r5.f6925p
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.h(r0, r3, r3)
            r3 = 1014350479(0x3c75c28f, float:0.015)
            float r4 = r5.f6925p
            float r4 = r4 * r3
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r3 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.i(r0, r4, r2)
            r5.y = r3
            r3 = 1000593162(0x3ba3d70a, float:0.005)
            float r4 = r5.f6925p
            float r4 = r4 * r3
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r0 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.i(r0, r4, r2)
            r5.z = r0
        L97:
            android.graphics.Path r0 = new android.graphics.Path
            r0.<init>()
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r2 = r5.w
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r3 = r5.y
            android.graphics.Path r0 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.c(r0, r2, r3)
            r5.f6928s = r0
            android.graphics.Path r0 = new android.graphics.Path
            r0.<init>()
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r2 = r5.x
            cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint[][] r3 = r5.z
            android.graphics.Path r0 = cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils.c(r0, r2, r3)
            r5.t = r0
            android.graphics.Paint r0 = r5.v
            r0.setAntiAlias(r1)
            cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable$LightShare r0 = r5.D
            r0.a(r6)
            cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable$LightBase r0 = r5.C
            r0.a(r6)
            cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable$Background r0 = r5.B
            r0.a(r6)
            cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable$LightOff r0 = r5.A
            r0.a(r6)
            r6 = 1056964608(0x3f000000, float:0.5)
            r5.k(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable.onBoundsChange(android.graphics.Rect):void");
    }
}
