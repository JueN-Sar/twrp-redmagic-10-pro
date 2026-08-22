package cn.nubia.yolox;

import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.nubia.yolox.YOLOXncnn;
import com.google.mlkit.common.MlKitException;
import com.zte.distbus.basetransfer.Status;

/* loaded from: classes.dex */
public class YOLOXWindow extends View {
    private static final String URI_YOLOX_RECT_SHOW = "yolox_rect_show";
    final int[] colors;
    private ContentObserver mContentObserver;
    private boolean mIsAdd;
    private YOLOXncnn.Obj[] mObjects;
    private Paint mPaint;
    private boolean mShowRect;
    private Paint mTextbgpaint;
    private Paint mTextpaint;
    private Handler mUIHandler;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;

    public YOLOXWindow(Context context) {
        this(context, null);
    }

    private void d(Canvas canvas) {
        for (int i2 = 0; i2 < this.mObjects.length; i2++) {
            this.mPaint.setColor(this.colors[i2 % 19]);
            YOLOXncnn.Obj obj = this.mObjects[i2];
            float f2 = obj.x;
            float f3 = obj.y;
            canvas.drawRect(f2, f3, f2 + obj.w, f3 + obj.f9233h, this.mPaint);
            String str = this.mObjects[i2].label + " = " + String.format("%.1f", Float.valueOf(this.mObjects[i2].prob * 100.0f)) + "%";
            float measureText = this.mTextpaint.measureText(str);
            float descent = (-this.mTextpaint.ascent()) + this.mTextpaint.descent();
            YOLOXncnn.Obj obj2 = this.mObjects[i2];
            float f4 = obj2.x;
            float f5 = obj2.y - descent;
            if (f5 < 0.0f) {
                f5 = 0.0f;
            }
            if (f4 + measureText > getWidth()) {
                f4 = getWidth() - measureText;
            }
            canvas.drawRect(f4, f5, f4 + measureText, f5 + descent, this.mTextbgpaint);
            canvas.drawText(str, f4, f5 - this.mTextpaint.ascent(), this.mTextpaint);
        }
    }

    private void f() {
        Paint paint = new Paint();
        this.mTextpaint = paint;
        paint.setColor(-16777216);
        this.mTextpaint.setTextSize(18.0f);
        this.mTextpaint.setTextAlign(Paint.Align.LEFT);
        Paint paint2 = new Paint();
        this.mTextbgpaint = paint2;
        paint2.setColor(-1);
        this.mTextbgpaint.setStyle(Paint.Style.FILL);
        Paint paint3 = new Paint();
        this.mPaint = paint3;
        paint3.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(1.0f);
        this.mUIHandler = new Handler(Looper.getMainLooper());
        this.mContentObserver = new ContentObserver(this.mUIHandler) { // from class: cn.nubia.yolox.YOLOXWindow.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                YOLOXWindow.this.m();
            }
        };
        this.mWindowManager = (WindowManager) getContext().getSystemService("window");
    }

    private void g() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mWindowParams = layoutParams;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.format = -2;
        layoutParams.type = 2038;
        layoutParams.setTitle("yolox_window");
        WindowManager.LayoutParams layoutParams2 = this.mWindowParams;
        layoutParams2.screenOrientation = 0;
        layoutParams2.flags |= 16777232;
        layoutParams2.layoutInDisplayCutoutMode = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h() {
        if (this.mIsAdd) {
            try {
                try {
                    this.mWindowManager.removeViewImmediate(this);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                this.mIsAdd = false;
                l();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i() {
        if (this.mIsAdd) {
            return;
        }
        try {
            g();
            this.mWindowManager.addView(this, this.mWindowParams);
            this.mIsAdd = true;
            j();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void j() {
        getContext().getContentResolver().registerContentObserver(Settings.Global.getUriFor(URI_YOLOX_RECT_SHOW), true, this.mContentObserver);
        m();
    }

    private void l() {
        getContext().getContentResolver().unregisterContentObserver(this.mContentObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.mShowRect = Settings.Global.getInt(getContext().getContentResolver(), URI_YOLOX_RECT_SHOW, 0) == 1;
    }

    public void e() {
        this.mUIHandler.post(new Runnable() { // from class: cn.nubia.yolox.a
            @Override // java.lang.Runnable
            public final void run() {
                YOLOXWindow.this.h();
            }
        });
    }

    public void k() {
        this.mUIHandler.post(new Runnable() { // from class: cn.nubia.yolox.b
            @Override // java.lang.Runnable
            public final void run() {
                YOLOXWindow.this.i();
            }
        });
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        YOLOXncnn.Obj[] objArr;
        super.onDraw(canvas);
        if (!this.mShowRect || (objArr = this.mObjects) == null || objArr.length <= 0) {
            return;
        }
        d(canvas);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public void setData(YOLOXncnn.Obj[] objArr) {
        this.mObjects = objArr;
        postInvalidate();
    }

    public YOLOXWindow(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public YOLOXWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public YOLOXWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.colors = new int[]{Color.rgb(54, 67, 244), Color.rgb(99, 30, 233), Color.rgb(176, 39, 156), Color.rgb(183, 58, Status.BLE_DISCONNECTING), Color.rgb(181, 81, 63), Color.rgb(243, 150, 33), Color.rgb(244, 169, 3), Color.rgb(212, 188, 0), Color.rgb(136, 150, 0), Color.rgb(80, 175, 76), Color.rgb(74, 195, 139), Color.rgb(57, 220, MlKitException.CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR), Color.rgb(59, 235, 255), Color.rgb(7, 193, 255), Color.rgb(0, 152, 255), Color.rgb(34, 87, 255), Color.rgb(72, 85, 121), Color.rgb(158, 158, 158), Color.rgb(139, 125, 96)};
        f();
    }
}
