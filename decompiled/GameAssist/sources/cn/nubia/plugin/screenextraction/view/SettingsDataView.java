package cn.nubia.plugin.screenextraction.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.plugin.screenextraction.bean.ScreenExtractionData;
import cn.nubia.plugin.screenextraction.view.area.DstArea;
import cn.nubia.plugin.screenextraction.view.area.IArea;
import cn.nubia.plugin.screenextraction.view.area.SrcArea;

/* loaded from: classes.dex */
public class SettingsDataView extends View implements IArea.Callback {
    private static final int BACKGROUND_COLOR = -2030043136;
    private static final boolean SHOW_DIM = false;
    private static final int SRC_LEFT_TOP_DP = 16;
    private static final int SRC_WIDTH_HEIGHT_DP = 144;
    private boolean isTouchDragArea;
    private Callback mCallback;
    private IArea mDstArea;
    private final Rect mDstDownArea;
    private Paint mPaint;
    private ScreenExtractionData mScreenExtractionData;
    private IArea mSrcData;
    private final Rect mSrcDownArea;

    interface Callback {
        void b();

        void c(boolean z);
    }

    public SettingsDataView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSrcDownArea = new Rect();
        this.mDstDownArea = new Rect();
    }

    private int b(int i2) {
        return (int) ((i2 * getResources().getDisplayMetrics().density) + 0.5d);
    }

    private int[] getDefaultData() {
        int i2 = GameAssistWindowManager.Q / 2;
        int i3 = GameAssistWindowManager.P / 2;
        return new int[]{b(16), b(16), b(160), b(160), i2 - b(72), i3 - b(72), b(72) + i2, i3 + b(72)};
    }

    @Override // cn.nubia.plugin.screenextraction.view.area.IArea.Callback
    public void a(IArea iArea, Rect rect, Rect rect2) {
        if (iArea == this.mSrcData && (rect2.width() != rect.width() || rect2.height() != rect.height())) {
            Rect rect3 = new Rect(this.mDstDownArea);
            int width = (rect2.width() * rect3.width()) / rect.width();
            int height = (rect2.height() * rect3.height()) / rect.height();
            rect3.right = rect3.left + width;
            rect3.bottom = rect3.top + height;
            Point point = new Point();
            int i2 = rect3.left;
            if (i2 < 0) {
                point.x = 0 - i2;
            }
            int i3 = rect3.top;
            if (i3 < 0) {
                point.y = 0 - i3;
            }
            int i4 = rect3.right;
            int i5 = GameAssistWindowManager.Q;
            if (i4 > i5) {
                point.x = i5 - i4;
            }
            int i6 = rect3.bottom;
            int i7 = GameAssistWindowManager.P;
            if (i6 > i7) {
                point.y = i7 - i6;
            }
            rect3.offset(point.x, point.y);
            if (rect3.width() > this.mDstArea.a().x && rect3.height() > this.mDstArea.a().y) {
                this.mDstArea.d(rect3);
            }
        } else if (iArea == this.mDstArea && (rect2.right > GameAssistWindowManager.Q || rect2.bottom > GameAssistWindowManager.P)) {
            Rect rect4 = new Rect(rect2);
            Point point2 = new Point();
            int i8 = rect4.right;
            int i9 = GameAssistWindowManager.Q;
            if (i8 > i9) {
                point2.x = i8 - i9;
            }
            int i10 = rect4.bottom;
            int i11 = GameAssistWindowManager.P;
            if (i10 > i11) {
                point2.y = i10 - i11;
            }
            rect4.offset(point2.x, point2.y);
            this.mDstArea.d(rect4);
        }
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.b();
        }
        invalidate();
    }

    public boolean c() {
        return this.isTouchDragArea;
    }

    public Rect getDstData() {
        return this.mDstArea.b();
    }

    public Rect getSrcData() {
        return this.mSrcData.b();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mSrcData.onDraw(canvas);
        this.mDstArea.onDraw(canvas);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int[] defaultData = getDefaultData();
        SrcArea srcArea = new SrcArea(new Rect(defaultData[0], defaultData[1], defaultData[2], defaultData[3]), getResources().getDrawable(R.drawable.screen_extraction_src_corner), 3.0f, this);
        this.mSrcData = srcArea;
        srcArea.e(this);
        DstArea dstArea = new DstArea(new Rect(defaultData[4], defaultData[5], defaultData[6], defaultData[7]), getResources().getDrawable(R.drawable.screen_extraction_src_scale), this);
        this.mDstArea = dstArea;
        dstArea.e(this);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(BACKGROUND_COLOR);
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        ScreenExtractionData screenExtractionData = this.mScreenExtractionData;
        if (screenExtractionData != null) {
            setScreenExtractionData(screenExtractionData);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        postInvalidate();
        if (motionEvent.getAction() == 0 && !isEnabled()) {
            return false;
        }
        boolean z = true;
        if (!this.mDstArea.c(motionEvent) && !this.mSrcData.c(motionEvent)) {
            z = super.onTouchEvent(motionEvent);
        }
        if (this.isTouchDragArea != z) {
            this.isTouchDragArea = z;
            if (z) {
                this.mSrcDownArea.set(this.mSrcData.b());
                this.mDstDownArea.set(this.mDstArea.b());
            }
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.c(this.isTouchDragArea);
            }
        }
        return z;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void setScreenExtractionData(ScreenExtractionData screenExtractionData) {
        this.mScreenExtractionData = screenExtractionData;
        IArea iArea = this.mSrcData;
        if (iArea != null && screenExtractionData != null) {
            iArea.d(screenExtractionData.h());
        }
        IArea iArea2 = this.mDstArea;
        if (iArea2 == null || this.mScreenExtractionData == null) {
            return;
        }
        iArea2.d(screenExtractionData.c());
    }

    public SettingsDataView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSrcDownArea = new Rect();
        this.mDstDownArea = new Rect();
    }
}
