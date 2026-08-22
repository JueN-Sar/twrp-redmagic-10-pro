package cn.nubia.gameassist.panel.drawable.diplogen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.provider.Settings;
import android.view.animation.PathInterpolator;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.test.GameAssistTestActivity;
import cn.nubia.gameassist.theme.Theme;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.TraceWrapper;

/* loaded from: classes.dex */
public class LaunchDrawable extends DiplogenDrawable {
    private static final float A;
    public static final RatioPoint[][] B = {new RatioPoint[]{new RatioPoint(0.178f, -0.9f)}, new RatioPoint[]{new RatioPoint(0.178f, 0.0f)}, new RatioPoint[]{new RatioPoint(0.307f, 0.239f)}, new RatioPoint[]{new RatioPoint(0.336f, 0.289f), new RatioPoint(0.336f, 0.322f), new RatioPoint(0.336f, 0.348f)}, new RatioPoint[]{new RatioPoint(0.295f, 0.422f)}, new RatioPoint[]{new RatioPoint(0.005f, 1.0f)}, new RatioPoint[]{new RatioPoint(0.005f, 1.9f)}};
    public static final RatioPoint[][] C = {new RatioPoint[]{new RatioPoint(0.37733f, -0.5f)}, new RatioPoint[]{new RatioPoint(0.37733f, 0.0f)}, new RatioPoint[]{new RatioPoint(0.45764f, 0.14764f)}, new RatioPoint[]{new RatioPoint(0.47909f, 0.18127f), new RatioPoint(0.47836f, 0.19909f), new RatioPoint(0.478f, 0.21673f)}, new RatioPoint[]{new RatioPoint(0.45567f, 0.25767f)}, new RatioPoint[]{new RatioPoint(0.20382f, 0.77191f)}, new RatioPoint[]{new RatioPoint(0.19634f, 0.78784f), new RatioPoint(0.19227f, 0.79427f), new RatioPoint(0.18821f, 0.80071f)}, new RatioPoint[]{new RatioPoint(0.17994f, 0.81125f)}, new RatioPoint[]{new RatioPoint(0.0f, 0.99425f)}, new RatioPoint[]{new RatioPoint(-0.1f, 0.99425f)}};
    private static final float z;

    /* renamed from: o, reason: collision with root package name */
    private final PathInterpolator f6919o;

    /* renamed from: p, reason: collision with root package name */
    protected float f6920p;

    /* renamed from: q, reason: collision with root package name */
    protected float[] f6921q;

    /* renamed from: r, reason: collision with root package name */
    protected Paint f6922r;

    /* renamed from: s, reason: collision with root package name */
    protected Paint f6923s;
    protected Path t;
    protected Path u;
    protected int v;
    private final Rect w;
    protected Matrix x;
    protected float y;

    static {
        Resources d2 = InflaterHelper.d();
        int i2 = d2.getConfiguration().densityDpi;
        boolean isTabletProduct = ZteFeature.isTabletProduct();
        String str = i2 >= 560 ? isTabletProduct ? "diplogen_path_launch_mask_pad_offset_560dpi" : "diplogen_path_launch_mask_offset_560dpi" : i2 >= 520 ? isTabletProduct ? "diplogen_path_launch_mask_pad_offset_520dpi" : "diplogen_path_launch_mask_offset_520dpi" : i2 >= 480 ? isTabletProduct ? "diplogen_path_launch_mask_pad_offset_480dpi" : "diplogen_path_launch_mask_offset_480dpi" : i2 >= 420 ? isTabletProduct ? "diplogen_path_launch_mask_pad_offset_420dpi" : "diplogen_path_launch_mask_offset_420dpi" : i2 >= 400 ? isTabletProduct ? "diplogen_path_launch_mask_pad_offset_400dpi" : "diplogen_path_launch_mask_offset_400dpi" : i2 >= 320 ? isTabletProduct ? "diplogen_path_launch_mask_pad_offset_320dpi" : "diplogen_path_launch_mask_offset_320dpi" : i2 >= 300 ? isTabletProduct ? "diplogen_path_launch_mask_pad_offset_300dpi" : "diplogen_path_launch_mask_offset_300dpi" : i2 >= 260 ? isTabletProduct ? "diplogen_path_launch_mask_pad_offset_260dpi" : "diplogen_path_launch_mask_offset_260dpi" : isTabletProduct ? "diplogen_path_launch_mask_pad_offset" : "diplogen_path_launch_mask_offset";
        int identifier = d2.getIdentifier(str, "string", InflaterHelper.b().getPackageName());
        if (identifier != 0) {
            z = Float.parseFloat(d2.getString(identifier));
        } else {
            String string = Settings.Global.getString(InflaterHelper.b().getContentResolver(), "diplogen_path_launch_mask_offset");
            if (string == null) {
                string = d2.getString(R.string.diplogen_path_launch_mask_offset);
            }
            float parseFloat = Float.parseFloat(string);
            z = parseFloat;
            GaLog.a("GameAssistWindowManager", "DiplogenDrawable not find res string R.string." + str + " value=" + parseFloat);
        }
        A = 0.3617f;
    }

    public LaunchDrawable(Context context, int i2) {
        super(context, i2);
        this.f6919o = new PathInterpolator(0.2f, 0.0f, 0.8f, 1.0f);
        this.w = new Rect();
        Paint paint = new Paint(2);
        this.f6922r = paint;
        paint.setAntiAlias(true);
        this.f6922r.setDither(true);
        Paint paint2 = new Paint();
        this.f6923s = paint2;
        paint2.setColor(Color.pack(-1));
        this.f6923s.setTextSize(40.0f);
        this.f6923s.setStrokeWidth(3.0f);
        this.f6923s.setStyle(Paint.Style.STROKE);
        this.f6923s.setAntiAlias(true);
        this.f6923s.setDither(true);
        this.f6923s.setTextAlign(Paint.Align.CENTER);
    }

    private int h(float f2) {
        return f2 < 0.5f ? (int) ((f2 * 2.0f * 200.0f) + 400.0f) : (int) (((1.0f - f2) * 2.0f * 200.0f) + 400.0f);
    }

    private int i(float f2, int i2) {
        return ((int) ((1001 - i2) * (1.0f - f2))) * 2;
    }

    private void j(Canvas canvas) {
        float f2 = this.y;
        if (f2 >= 1.0f || f2 < 0.0f) {
            return;
        }
        float interpolation = this.f6919o.getInterpolation(f2);
        int saveLayer = canvas.saveLayer(null, null, 31);
        int h2 = h(interpolation);
        canvas.drawBitmapMesh(b(this.f6852l.f7452s.f7473f), 1, h2, this.f6921q, i(interpolation, h2), null, 0, this.f6922r);
        this.f6922r.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawBitmap(b(this.f6852l.f7452s.f7474g), (Rect) null, this.w, this.f6922r);
        this.f6922r.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }

    private void l() {
        if (this.f6852l != null && getBounds().width() > 0) {
            Bitmap b2 = b(this.f6852l.f7452s.f7474g);
            int i2 = (int) this.f6920p;
            int width = (int) ((b2.getWidth() * this.f6920p) / b2.getHeight());
            if (DiplogenDrawable.e()) {
                this.w.set(0, 0, width, i2);
            } else {
                Rect rect = this.w;
                int i3 = this.v;
                rect.set(i3, 0, width + i3, i2);
            }
        }
        invalidateSelf();
    }

    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable, cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        super.d(theme);
        l();
    }

    @Override // android.graphics.drawable.Drawable
    @VisibleForTesting
    public void draw(Canvas canvas) {
        TraceWrapper.traceBegin(8L, getClass().getSimpleName());
        if (this.f6852l != null) {
            int save = canvas.save();
            Matrix matrix = canvas.getMatrix();
            canvas.setMatrix(this.x);
            j(canvas);
            canvas.setMatrix(matrix);
            canvas.restoreToCount(save);
        }
        if (!GameAssistTestActivity.f7433c) {
            invalidateSelf();
        }
        TraceWrapper.traceEnd(8L);
    }

    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable
    void f(boolean z2) {
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a3  */
    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void g(android.graphics.Rect r11) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.panel.drawable.diplogen.LaunchDrawable.g(android.graphics.Rect):void");
    }

    public void k(float f2) {
        this.y = f2;
        invalidateSelf();
    }
}
