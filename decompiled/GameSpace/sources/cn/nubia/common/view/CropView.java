package cn.nubia.common.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cn.nubia.common.R;
import cn.nubia.common.util.CommonUtil;

/* loaded from: classes.dex */
public class CropView extends FrameLayout implements ScaleGestureDetector.OnScaleGestureListener {
    String TAG;
    private Canvas mCanvas;
    OnButtonClickListener mListener;
    private ImageView mMask;
    private Paint mPaint;
    private Rect mRect;
    private Bitmap mSavedBitmap;
    SimpleEditImageView mSourceImageView;

    public interface OnButtonClickListener {
        void onClickApply(View view);

        void onClickCancel(View view);
    }

    public CropView(Context context) {
        this(context, null);
    }

    public CropView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "wallpaper";
        this.mPaint = null;
        this.mCanvas = null;
        initView(context);
    }

    private void initPaintAndCanvas() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(-16711936);
        this.mSavedBitmap = Bitmap.createBitmap(this.mRect.width(), this.mRect.height(), Bitmap.Config.ARGB_8888);
        this.mCanvas = new Canvas(this.mSavedBitmap);
    }

    private void initView(Context context) {
        this.mRect = CommonUtil.getDisplayRect(context);
        LayoutInflater.from(context).inflate(R.layout.crop_view, this);
        SimpleEditImageView simpleEditImageView = (SimpleEditImageView) findViewById(R.id.simple_edit);
        this.mSourceImageView = simpleEditImageView;
        simpleEditImageView.setCropRect(this.mRect);
        this.mSourceImageView.setCropWallpaperFlag(true);
        this.mMask = (ImageView) findViewById(R.id.custom_mask);
        findViewById(R.id.crop_text).setVisibility(0);
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.common.view.CropView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CropView.this.clickCancel(view);
            }
        });
        findViewById(R.id.apply).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.common.view.CropView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CropView.this.clickApply(view);
            }
        });
        initPaintAndCanvas();
    }

    public void clickApply(View view) {
        OnButtonClickListener onButtonClickListener = this.mListener;
        if (onButtonClickListener == null) {
            return;
        }
        onButtonClickListener.onClickApply(view);
    }

    public void clickCancel(View view) {
        OnButtonClickListener onButtonClickListener = this.mListener;
        if (onButtonClickListener == null) {
            return;
        }
        onButtonClickListener.onClickCancel(view);
    }

    public Bitmap getCropBitmap() {
        RectF rectF = new RectF();
        this.mSourceImageView.getCurrentMatrix().mapRect(rectF);
        this.mCanvas.drawBitmap(this.mSourceImageView.getMatrixBitmap(), rectF.left, rectF.top, this.mPaint);
        Bitmap createBitmap = Bitmap.createBitmap(this.mSavedBitmap, this.mRect.left, this.mRect.top, this.mRect.width(), this.mRect.height());
        this.mSavedBitmap = createBitmap;
        return createBitmap;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        findViewById(R.id.crop_text).setVisibility(8);
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        Log.d(this.TAG, "crop - onScale() scaleGestureDetector : " + scaleGestureDetector);
        invalidate();
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        Log.d(this.TAG, "crop - onScale() onScaleBegin : " + scaleGestureDetector);
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        Log.d(this.TAG, "crop - onScale() onScaleEnd : " + scaleGestureDetector);
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.mListener = onButtonClickListener;
    }

    public void setSourceBitmap(Bitmap bitmap) {
        this.mSourceImageView.setBitmap(bitmap);
    }
}
