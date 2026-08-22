package cn.nubia.gameassist.panel.drawable.diplogen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public final class PanelDrawable extends DiplogenDrawable {
    public static final float t;

    /* renamed from: o, reason: collision with root package name */
    private Path f6967o;

    /* renamed from: p, reason: collision with root package name */
    private int f6968p;

    /* renamed from: q, reason: collision with root package name */
    private int f6969q;

    /* renamed from: r, reason: collision with root package name */
    private Paint f6970r;

    /* renamed from: s, reason: collision with root package name */
    private Paint f6971s;

    static {
        Resources d2 = InflaterHelper.d();
        int i2 = d2.getConfiguration().densityDpi;
        boolean isTabletProduct = ZteFeature.isTabletProduct();
        String str = i2 >= 560 ? isTabletProduct ? "diplogen_path_base_pad_right_560dpi" : "diplogen_path_base_right_560dpi" : i2 >= 520 ? isTabletProduct ? "diplogen_path_base_pad_right_520dpi" : "diplogen_path_base_right_520dpi" : i2 >= 480 ? isTabletProduct ? "diplogen_path_base_pad_right_480dpi" : "diplogen_path_base_right_480dpi" : i2 >= 400 ? isTabletProduct ? "diplogen_path_base_pad_right_400dpi" : "diplogen_path_base_right_400dpi" : i2 >= 320 ? isTabletProduct ? "diplogen_path_base_pad_right_320dpi" : "diplogen_path_base_right_320dpi" : i2 >= 300 ? isTabletProduct ? "diplogen_path_base_pad_right_300dpi" : "diplogen_path_base_right_300dpi" : i2 >= 260 ? isTabletProduct ? "diplogen_path_base_pad_right_260dpi" : "diplogen_path_base_right_260dpi" : isTabletProduct ? "diplogen_path_base_pad_right" : "diplogen_path_base_right";
        int identifier = d2.getIdentifier(str, "string", InflaterHelper.b().getPackageName());
        if (identifier != 0) {
            t = Float.parseFloat(d2.getString(identifier));
            return;
        }
        String string = Settings.Global.getString(InflaterHelper.b().getContentResolver(), "diplogen_path_base_right");
        if (string == null) {
            string = d2.getString(R.string.diplogen_path_base_right);
        }
        float parseFloat = Float.parseFloat(string);
        t = parseFloat;
        GaLog.a("GameAssistWindowManager", "DiplogenDrawable not find res string R.string." + str + " value=" + parseFloat);
    }

    public PanelDrawable(Context context, int i2) {
        super(context, i2);
        this.f6967o = new Path();
        Paint paint = new Paint();
        this.f6970r = paint;
        paint.setAntiAlias(true);
        this.f6970r.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.f6971s = paint2;
        paint2.setAntiAlias(true);
        this.f6971s.setStyle(Paint.Style.STROKE);
        this.f6971s.setColor(-65536);
        this.f6971s.setStrokeWidth(2.0f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0035, code lost:
    
        if (r3 != 3) goto L13;
     */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r13) {
        /*
            r12 = this;
            java.lang.Class<cn.nubia.gameassist.panel.drawable.diplogen.PanelDrawable> r0 = cn.nubia.gameassist.panel.drawable.diplogen.PanelDrawable.class
            java.lang.String r0 = r0.getSimpleName()
            r1 = 8
            com.zte.shared.wrapper.TraceWrapper.traceBegin(r1, r0)
            android.graphics.Paint r0 = r12.f6970r
            r3 = -654311424(0xffffffffd9000000, float:-2.2517998E15)
            r0.setColor(r3)
            android.graphics.Path r0 = r12.f6967o
            android.graphics.Paint r3 = r12.f6970r
            r13.drawPath(r0, r3)
            int r0 = r13.save()
            android.graphics.Path r3 = r12.f6967o
            r13.clipPath(r3)
            android.graphics.Paint r3 = r12.f6970r
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3.setColor(r4)
            int r3 = r12.f6849i
            r4 = 0
            if (r3 == 0) goto L63
            r5 = 1
            if (r3 == r5) goto L38
            r5 = 2
            if (r3 == r5) goto L63
            r5 = 3
            if (r3 == r5) goto L38
            goto L8b
        L38:
            int r3 = r12.f6968p
            int r5 = r12.f6969q
            int r5 = r3 - r5
            float r7 = (float) r5
            float r9 = (float) r3
            float r10 = (float) r3
            android.graphics.Paint r11 = r12.f6970r
            r8 = 0
            r6 = r13
            r6.drawRect(r7, r8, r9, r10, r11)
            android.graphics.Paint r3 = r12.f6971s
            float r3 = r3.getStrokeWidth()
            float r3 = -r3
            r13.translate(r3, r4)
            int r3 = r12.f6968p
            int r4 = r12.f6969q
            int r5 = r3 - r4
            float r7 = (float) r5
            int r4 = r3 - r4
            float r9 = (float) r4
            float r10 = (float) r3
            android.graphics.Paint r11 = r12.f6971s
            r6.drawLine(r7, r8, r9, r10, r11)
            goto L8b
        L63:
            int r3 = r12.f6968p
            float r8 = (float) r3
            int r3 = r12.f6969q
            float r9 = (float) r3
            android.graphics.Paint r10 = r12.f6970r
            r6 = 0
            r7 = 0
            r5 = r13
            r5.drawRect(r6, r7, r8, r9, r10)
            android.graphics.Paint r3 = r12.f6971s
            float r3 = r3.getStrokeWidth()
            r5 = 1073741824(0x40000000, float:2.0)
            float r3 = r3 / r5
            r13.translate(r4, r3)
            int r3 = r12.f6969q
            float r6 = (float) r3
            int r4 = r12.f6968p
            float r7 = (float) r4
            float r8 = (float) r3
            android.graphics.Paint r9 = r12.f6971s
            r5 = 0
            r4 = r13
            r4.drawLine(r5, r6, r7, r8, r9)
        L8b:
            r13.restoreToCount(r0)
            com.zte.shared.wrapper.TraceWrapper.traceEnd(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.panel.drawable.diplogen.PanelDrawable.draw(android.graphics.Canvas):void");
    }

    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable
    void f(boolean z) {
    }

    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable
    void g(Rect rect) {
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        float f2;
        float f3;
        float f4;
        int i2 = this.f6849i;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        return super.getIntrinsicHeight();
                    }
                }
            }
            if (DiplogenDrawable.e()) {
                f2 = this.f6968p;
                f3 = t;
                f4 = 0.49496f;
            } else {
                f2 = this.f6968p;
                f3 = t;
                f4 = 0.37133f;
            }
            return (int) (f2 * (f3 + f4));
        }
        return this.f6968p;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        float f2;
        float f3;
        float f4;
        int i2 = this.f6849i;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        return super.getIntrinsicWidth();
                    }
                }
            }
            return this.f6968p;
        }
        if (DiplogenDrawable.e()) {
            f2 = this.f6968p;
            f3 = t;
            f4 = 0.49496f;
        } else {
            f2 = this.f6968p;
            f3 = t;
            f4 = 0.37133f;
        }
        return (int) (f2 * (f3 + f4));
    }

    public void h() {
        onBoundsChange(getBounds());
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00bf A[LOOP:0: B:14:0x00bc->B:16:0x00bf, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    @Override // cn.nubia.gameassist.panel.drawable.diplogen.DiplogenDrawable, android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onBoundsChange(android.graphics.Rect r20) {
        /*
            Method dump skipped, instructions count: 532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.panel.drawable.diplogen.PanelDrawable.onBoundsChange(android.graphics.Rect):void");
    }
}
