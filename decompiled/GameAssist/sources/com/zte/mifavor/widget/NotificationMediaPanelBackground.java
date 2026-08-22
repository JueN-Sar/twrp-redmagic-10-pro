package com.zte.mifavor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.Nullable;

/* loaded from: classes2.dex */
public class NotificationMediaPanelBackground extends View {
    private static final String TAG = "notification_media_panel_bg";
    private Paint bottomLayerPaint;
    private float cornerRadius;
    private int mBgColor;
    private Context mContext;
    private int mHeight;
    private InnerShadowHelper mInnerShadowHelper;
    private final Handler mMainHandler;
    private Paint mPaint;
    private RectF mRect;
    private float mRectX;
    private float mRectY;
    private int mWidth;
    private Path roundedRectPath;
    private Paint topLayerPaint;

    public NotificationMediaPanelBackground(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mContext = context;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mWidth = 0;
        this.mHeight = 0;
        this.mRectX = 0.0f;
        this.mRectY = 0.0f;
        this.mBgColor = Color.parseColor("#1FFFFFFF");
        e();
    }

    private float b(float f2) {
        return f2 * getContext().getResources().getDisplayMetrics().density;
    }

    private void c() {
        this.roundedRectPath.reset();
        float f2 = this.mRectX;
        int i2 = this.mWidth;
        float f3 = this.mRectY;
        int i3 = this.mHeight;
        this.mRect = new RectF(f2 - (i2 / 2), f3 - (i3 / 2), f2 + (i2 / 2), f3 + (i3 / 2));
        Log.d(TAG, "drawPath: zjw0115, " + this.mRect.left + " , " + this.mRect.top + " , " + this.mRect.right + " , " + this.mRect.bottom);
        double d2 = (double) this.mWidth;
        double d3 = (double) this.mHeight;
        float f4 = this.cornerRadius;
        Path c2 = G2ContinuousRoundedPath.c(d2, d3, (double) f4, (double) f4, (double) f4, (double) f4);
        this.roundedRectPath = c2;
        c2.offset(this.mRectX - ((float) (this.mWidth / 2)), this.mRectY - ((float) (this.mHeight / 2)));
        f();
    }

    private void d(Canvas canvas) {
        Path path;
        try {
            if (this.mInnerShadowHelper == null || (path = this.roundedRectPath) == null || path.isEmpty()) {
                return;
            }
            this.mInnerShadowHelper.c(canvas, this.roundedRectPath);
        } catch (Exception e2) {
            Log.e(TAG, "drawVisualEffects exception: " + e2);
            e2.printStackTrace();
        }
    }

    private void e() {
        Paint paint = new Paint(1);
        this.bottomLayerPaint = paint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.bottomLayerPaint.setColor(436207616);
        Paint paint2 = new Paint(1);
        this.topLayerPaint = paint2;
        paint2.setStyle(style);
        this.topLayerPaint.setColor(this.mBgColor);
        this.roundedRectPath = new Path();
        this.cornerRadius = (int) b(21.0f);
        InnerShadowHelper innerShadowHelper = new InnerShadowHelper(getContext());
        this.mInnerShadowHelper = innerShadowHelper;
        innerShadowHelper.g(true, true);
    }

    private void f() {
        Path path = this.roundedRectPath;
        if (path == null || path.isEmpty() || this.mRect == null) {
            return;
        }
        this.mMainHandler.post(new Runnable() { // from class: com.zte.mifavor.widget.NotificationMediaPanelBackground.1
            @Override // java.lang.Runnable
            public void run() {
                NotificationMediaPanelBackground.this.setClipToOutline(true);
                NotificationMediaPanelBackground.this.setOutlineProvider(new ViewOutlineProvider() { // from class: com.zte.mifavor.widget.NotificationMediaPanelBackground.1.1
                    @Override // android.view.ViewOutlineProvider
                    public void getOutline(View view, Outline outline) {
                        if (NotificationMediaPanelBackground.this.roundedRectPath == null || NotificationMediaPanelBackground.this.roundedRectPath.isEmpty()) {
                            return;
                        }
                        outline.setPath(NotificationMediaPanelBackground.this.roundedRectPath);
                    }
                });
            }
        });
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRect == null || this.roundedRectPath.isEmpty()) {
            c();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.roundedRectPath, this.topLayerPaint);
        d(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int b2 = (int) b(42.0f);
        int b3 = (int) b(42.0f);
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? Math.min(b2, size) : b2;
        }
        if (mode2 != 1073741824) {
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(b3, size2) : b3;
        }
        setMeasuredDimension(size, size2);
        this.mWidth = size;
        this.mHeight = size2;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mWidth = i2;
        this.mHeight = i3;
        this.mRectX = i2 / 2.0f;
        this.mRectY = i3 / 2.0f;
        c();
        f();
    }

    public void setBgColor(int i2) {
        this.mBgColor = i2;
        this.topLayerPaint.setColor(i2);
    }

    protected void setDynamicPanelPaint(boolean z) {
        InnerShadowHelper innerShadowHelper = this.mInnerShadowHelper;
        if (innerShadowHelper == null || this.bottomLayerPaint == null || this.topLayerPaint == null) {
            return;
        }
        innerShadowHelper.g(z, true);
        if (z) {
            this.bottomLayerPaint.setColor(436207616);
            this.topLayerPaint.setColor(521111543);
        } else {
            this.bottomLayerPaint.setColor(452984831);
            this.topLayerPaint.setColor(521111543);
        }
    }
}
